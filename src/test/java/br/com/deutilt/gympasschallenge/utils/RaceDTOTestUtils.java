package br.com.deutilt.gympasschallenge.utils;

import br.com.deutilt.gympasschallenge.converters.LapRecordToLapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class RaceDTOTestUtils {

    public static RaceDTO getRaceDTOWithoutNullValues(){
        LapRecord bestLap = LapRecordTestUtils.getLapRecordWithoutNullValues();
        LapRecordToLapRecordDTOConverter lapRecordToLapRecordDTOConverter = new LapRecordToLapRecordDTOConverter();

        return new RaceDTO.Builder()
                .withBestLap(lapRecordToLapRecordDTOConverter.convert(bestLap))
                .withTotalAverageSpeed(new BigDecimal(Math.random()))
                .withTotalDuration(Duration.ofSeconds(LocalTime.now().getSecond()))
                .withLapRecord(bestLap)
                .withDelayAfterWinner(Duration.ofSeconds(LocalTime.now().getSecond()))
                .build();
    }

    public static RaceDTO getRaceDTOWithNullValues(){
        LapRecord bestLap = LapRecordTestUtils.getLapRecordWithNullValues();
        LapRecordToLapRecordDTOConverter lapRecordToLapRecordDTOConverter = new LapRecordToLapRecordDTOConverter();

        return new RaceDTO.Builder()
                .withBestLap(lapRecordToLapRecordDTOConverter.convert(bestLap))
                .withTotalAverageSpeed(new BigDecimal(Math.random()))
                .withTotalDuration(Duration.ofSeconds(LocalTime.now().getSecond()))
                .withLapRecord(bestLap)
                .withDelayAfterWinner(null)
                .build();
    }
}
