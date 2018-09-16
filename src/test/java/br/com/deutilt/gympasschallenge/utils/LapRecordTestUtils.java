package br.com.deutilt.gympasschallenge.utils;

import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

public class LapRecordTestUtils {

    public static LapRecord getLapRecordWithoutNullValues(){
        Driver driver = new Driver(String.valueOf(new Random().nextInt(1) + 1), "Ayrton Senna");

        return new LapRecord.Builder()
                .withAverageLapSpeed(new BigDecimal(Math.random()))
                .withLapDuration(Duration.ofSeconds(LocalTime.now().getSecond()))
                .withLapNumber(new Random().nextInt(6) + 5)
                .withHour(LocalTime.now())
                .withDriver(driver)
                .build();
    }

    public static LapRecord getLapRecordWithNullValues(){
        Driver driver = new Driver(String.valueOf(new Random().nextInt(1) + 1), "Ayrton Senna");

        return new LapRecord.Builder()
                .withAverageLapSpeed(null)
                .withLapDuration(Duration.ofSeconds(LocalTime.now().getSecond()))
                .withLapNumber(new Random().nextInt(6) + 5)
                .withHour(null)
                .withDriver(driver)
                .build();
    }
}
