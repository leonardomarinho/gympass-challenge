package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.ILapRecordConverter;
import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class StringToLapRecordConverter implements ILapRecordConverter{

    private static final int LAP_HOUR_POSITION = 0;
    private static final int DRIVER_POSITION = 1;
    private static final int LAP_NUMBER_POSITION = 2;
    private static final int LAP_TIME_POSITION = 3;
    private static final int AVERAGE_LAP_SPEED_POSITION = 4;

    private static final String SPLIT_CHAR = "    ";
    private static final String DOT = ".";
    private static final String COMMA = ",";

    public LapRecord convert(String fileLine) {
        List<String> data = Arrays.asList(fileLine.split(SPLIT_CHAR));
        StringToDriverConverter driverConverter = new StringToDriverConverter();

        LocalTime lapHour = LocalTime.parse(data.get(LAP_HOUR_POSITION).trim());
        Driver driver = driverConverter.convert(data.get(DRIVER_POSITION).trim());
        Integer lapNumber = Integer.valueOf(data.get(LAP_NUMBER_POSITION).trim());
        Duration lapDuration = DurationUtils.getDurationFrom(data.get(LAP_TIME_POSITION).trim());
        BigDecimal averageLapSpeed = new BigDecimal(data.get(AVERAGE_LAP_SPEED_POSITION).replace(COMMA, DOT).trim());

        LapRecord record = new LapRecord.Builder()
                                        .withHour(lapHour)
                                        .withDriver(driver)
                                        .withLapNumber(lapNumber)
                                        .withLapDuration(lapDuration)
                                        .withAverageLapSpeed(averageLapSpeed)
                                        .build();

        return record;
    }
}
