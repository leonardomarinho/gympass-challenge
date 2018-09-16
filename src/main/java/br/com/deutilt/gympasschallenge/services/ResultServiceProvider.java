package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.LapRecordToLapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.converters.RaceDTOToResultDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.IResultService;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResultServiceProvider implements IResultService {

    private static final int FIRST_PLACE = 0;

    private RaceDTOToResultDTOConverter raceDTOToResultDTOConverter = new RaceDTOToResultDTOConverter();
    private LapRecordToLapRecordDTOConverter lapRecordToLapRecordDTOConverter = new LapRecordToLapRecordDTOConverter();

    private List<LapRecord> getLapsFromDriver(List<LapRecord> lapRecords, String driverId){
        return lapRecords.stream().filter(lap -> lap.getDriver().getId().equals(driverId)).collect(Collectors.toList());
    }

    private Set<String> getDriversIds(List<LapRecord> lapRecords) {
        return lapRecords.stream().map(lapRecord -> lapRecord.getDriver().getId()).collect(Collectors.toSet());
    }

    private LapRecord getLastLapFromDriver(List<LapRecord> lapRecords, String driverId){
        Comparator<LapRecord> lapNumberComparator = Comparator.comparing(LapRecord::getLapNumber);
        List<LapRecord> lapsFromDriver = getLapsFromDriver(lapRecords, driverId);

        return lapsFromDriver.stream().max(lapNumberComparator).get();
    }

    private Duration getTotalDurationFrom(List<LapRecord> lapRecordsFromDriver){
        return lapRecordsFromDriver
                .stream()
                .map(lapRecord -> lapRecord.getLapDuration())
                    .reduce((firstValue, secondValue) -> firstValue.plus(secondValue))
                .get();
    }

    private BigDecimal getTotalAverageSpeedFrom(List<LapRecord> lapRecordsFromDriver){
        BigDecimal sum = lapRecordsFromDriver
                .stream()
                .map(lapRecord -> lapRecord.getAverageLapSpeed())
                .reduce((firstValue, secondValue) -> firstValue.add(secondValue))
                .get();

        return sum.divide(new BigDecimal(lapRecordsFromDriver.size()), RoundingMode.UP);
    }


    private LapRecord getBestLapFrom(List<LapRecord> lapRecordsFromDriver){
        Comparator<LapRecord> durationComparator = Comparator.comparing(LapRecord::getLapDuration);
        return lapRecordsFromDriver.stream().min(durationComparator).get();
    }

    private Duration getDelayBetween(LapRecord first, LapRecord second){
        if (first.getLapNumber().equals(second.getLapNumber())) {
            return second.getLapDuration().minus(first.getLapDuration());
        } else{
            //Didn't finish the race
            return first.getLapDuration().plus(second.getLapDuration().minus(first.getLapDuration()));
        }
    }

    public List<ResultDTO> getResults(List<LapRecord> lapRecords){
        Set<String> driversIds = getDriversIds(lapRecords);
        List<RaceDTO> raceDTOFromEachDriver = new ArrayList<>();
        List<ResultDTO> result = new ArrayList<>();
        int position = 1;

        for (String driverId : driversIds) {
            List<LapRecord> lapsFromDriver = getLapsFromDriver(lapRecords, driverId);
            LapRecord lastLapFromDriver = getLastLapFromDriver(lapRecords, driverId);

            Duration totalDuration = getTotalDurationFrom(lapsFromDriver);
            LapRecord bestLapFromDriver = getBestLapFrom(lapsFromDriver);
            LapRecordDTO bestLapFromDriverDTO = lapRecordToLapRecordDTOConverter.convert(bestLapFromDriver);
            BigDecimal averageSpeedFromDriver = getTotalAverageSpeedFrom(lapsFromDriver);

            RaceDTO raceDTO = new RaceDTO.Builder()
                                                .withLapRecord(lastLapFromDriver)
                                                .withTotalDuration(totalDuration)
                                                .withBestLap(bestLapFromDriverDTO)
                                                .withTotalAverageSpeed(averageSpeedFromDriver)
                                                .build();

            raceDTOFromEachDriver.add(raceDTO);
        }

        Comparator<RaceDTO> totalDurationComparator = Comparator.comparing(RaceDTO::getTotalDuration);
        List<RaceDTO> sortedRaceDTO = raceDTOFromEachDriver.stream().sorted(totalDurationComparator).collect(Collectors.toList());

        for(RaceDTO raceDTO : sortedRaceDTO) {
            Duration delayBetweenDrivers = getDelayBetween(sortedRaceDTO.get(FIRST_PLACE).getLapRecord(), raceDTO.getLapRecord());
            raceDTO.setDelayAfterWinner(delayBetweenDrivers);
            result.add(raceDTOToResultDTOConverter.convert(position, raceDTO));
            position += 1;
        }

        return result;
    }
}