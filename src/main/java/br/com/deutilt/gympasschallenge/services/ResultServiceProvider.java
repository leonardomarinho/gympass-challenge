package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.RaceDTOToResultDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.IResultService;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResultServiceProvider implements IResultService {

    private RaceDTOToResultDTOConverter converter = new RaceDTOToResultDTOConverter();

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

    private Duration getTotalDurationFrom(List<LapRecord> lapRecords){
        return lapRecords
                .stream()
                .map(lapRecord -> lapRecord.getLapDuration())
                    .reduce((firstValue, secondValue) -> firstValue.plus(secondValue))
                .get();
    }

    public List<ResultDTO> getResults(List<LapRecord> lapRecords){
        Set<String> driversIds = getDriversIds(lapRecords);
        List<RaceDTO> raceDTOFromEachDriver = new ArrayList<>();
        List<ResultDTO> result = new ArrayList<>();
        int position = 1;

        for (String driverId : driversIds) {
            List<LapRecord> lapsFromDriver = getLapsFromDriver(lapRecords, driverId);
            LapRecord lastLapFromDriver = getLastLapFromDriver(lapRecords, driverId);

            RaceDTO raceDTO = new RaceDTO.Builder()
                                                .withLapRecord(lastLapFromDriver)
                                                .withTotalDuration(getTotalDurationFrom(lapsFromDriver)).build();

            raceDTOFromEachDriver.add(raceDTO);
        }

        Comparator<RaceDTO> totalDurationComparator = Comparator.comparing(RaceDTO::getTotalDuration);
        List<RaceDTO> sortedRaceDTO = raceDTOFromEachDriver.stream().sorted(totalDurationComparator).collect(Collectors.toList());

        for(RaceDTO raceDTO : sortedRaceDTO) {
            result.add(converter.convert(position, raceDTO));
            position += 1;
        }

        return result;
    }
}