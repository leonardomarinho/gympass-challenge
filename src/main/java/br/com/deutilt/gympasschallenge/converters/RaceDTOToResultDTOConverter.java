package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.IResultDTOConverter;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;

public class RaceDTOToResultDTOConverter implements IResultDTOConverter{

    @Override
    public ResultDTO convert(int position, RaceDTO raceDTO) {

        String driverId = raceDTO.getLapRecord().getDriver().getId();
        String driverName = raceDTO.getLapRecord().getDriver().getName();
        String completedLaps = String.valueOf(raceDTO.getLapRecord().getLapNumber());
        String totalDuration = DurationUtils.getFormattedStringFrom(raceDTO.getTotalDuration());
        String totalAverageSpeed = String.valueOf(raceDTO.getTotalAverageSpeed());
        LapRecordDTO bestLap = raceDTO.getBestLap();

        return new ResultDTO.Builder()
                .withPosition(position)
                .withDriverId(driverId)
                .withDriverName(driverName)
                .withCompletedLaps(completedLaps)
                .withTotalDuration(totalDuration)
                .withTotalAverageSpeed(totalAverageSpeed)
                .withBestLap(bestLap)
                .build();
    }
}
