package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.IPositionDTOConverter;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;

public class RaceDTOToPositionDTOConverter implements IPositionDTOConverter {

    @Override
    public PositionDTO convert(int position, RaceDTO raceDTO) {

        String driverId = raceDTO.getLapRecord().getDriver().getId();
        String driverName = raceDTO.getLapRecord().getDriver().getName();
        String completedLaps = String.valueOf(raceDTO.getLapRecord().getLapNumber());
        String totalDuration = DurationUtils.getFormattedStringFrom(raceDTO.getTotalDuration());
        String totalAverageSpeed = String.valueOf(raceDTO.getTotalAverageSpeed());
        String delayAfterWinner = DurationUtils.getFormattedStringFrom(raceDTO.getDelayAfterWinner());
        LapRecordDTO bestLap = raceDTO.getBestLap();

        return new PositionDTO.Builder()
                .withPosition(position)
                .withDriverId(driverId)
                .withDriverName(driverName)
                .withCompletedLaps(completedLaps)
                .withTotalDuration(totalDuration)
                .withTotalAverageSpeed(totalAverageSpeed)
                .withDelayAfterWinner(delayAfterWinner)
                .withBestLap(bestLap)
                .build();
    }
}
