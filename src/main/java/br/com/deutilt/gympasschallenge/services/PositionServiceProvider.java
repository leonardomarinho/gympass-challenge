package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.LapRecordToLapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.converters.RaceDTOToPositionDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.ILapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.ILapRecordService;
import br.com.deutilt.gympasschallenge.interfaces.IPositionDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.IPositionService;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PositionServiceProvider implements IPositionService {

    private static final int FIRST_PLACE = 0;

    private IPositionDTOConverter raceDTOToResultDTOConverter = new RaceDTOToPositionDTOConverter();
    private ILapRecordDTOConverter lapRecordToLapRecordDTOConverter = new LapRecordToLapRecordDTOConverter();
    private ILapRecordService lapRecordService;

    @Autowired
    public PositionServiceProvider(LapRecordServiceProvider lapRecordServiceProvider) {
        this.lapRecordService = lapRecordServiceProvider;
    }


    public List<PositionDTO> getPositions(List<LapRecord> lapRecords){
        Set<String> driversIds = lapRecordService.getDriversIds(lapRecords);
        List<RaceDTO> raceDTOFromEachDriver = new ArrayList<>();
        List<PositionDTO> result = new ArrayList<>();
        int position = 1;

        for (String driverId : driversIds) {
            List<LapRecord> lapsFromDriver = lapRecordService.getLapsFromDriver(lapRecords, driverId);
            LapRecord lastLapFromDriver = lapRecordService.getLastLapFromDriver(lapRecords, driverId);

            Duration totalDuration = DurationUtils.getTotalDurationFrom(lapsFromDriver);
            LapRecord bestLapFromDriver = lapRecordService.getBestLapFrom(lapsFromDriver);
            LapRecordDTO bestLapFromDriverDTO = lapRecordToLapRecordDTOConverter.convert(bestLapFromDriver);
            BigDecimal averageSpeedFromDriver = lapRecordService.getTotalAverageSpeedFrom(lapsFromDriver);

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
            Duration delayBetweenDrivers = DurationUtils.getDelayBetween(sortedRaceDTO.get(FIRST_PLACE).getLapRecord(), raceDTO.getLapRecord());
            raceDTO.setDelayAfterWinner(delayBetweenDrivers);
            result.add(raceDTOToResultDTOConverter.convert(position, raceDTO));
            position += 1;
        }

        return result;
    }
}