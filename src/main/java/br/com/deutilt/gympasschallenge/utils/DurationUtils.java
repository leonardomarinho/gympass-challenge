package br.com.deutilt.gympasschallenge.utils;

import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class DurationUtils {

    private static final Integer HOURS_IN_MILLISECONDS = 3600000;
    private static final Integer MINUTES_IN_MILLISECONDS = 60000;
    private static final Integer SECONDS_IN_MILLISECONDS = 1000;

    private static final String DOT = ".";
    private static final String COLON = ":";

    private static final Integer ZERO_HOUR_DURATION = 3;

    public static Duration getDurationFrom(String value){
        String formatedValue = value.replace(DOT, COLON);
        List<String> splittedValues = Arrays.asList(formatedValue.split(COLON));
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        if (splittedValues.size() == ZERO_HOUR_DURATION){
            minutes = Integer.valueOf(splittedValues.get(0));
            seconds = Integer.valueOf(splittedValues.get(1));
            milliseconds = Integer.valueOf(splittedValues.get(2));

            return Duration.ofMillis(( MINUTES_IN_MILLISECONDS * minutes) + (SECONDS_IN_MILLISECONDS * seconds) + milliseconds);
        } else{
            hours = Integer.valueOf(splittedValues.get(0));
            minutes = Integer.valueOf(splittedValues.get(1));
            seconds = Integer.valueOf(splittedValues.get(2));
            milliseconds = Integer.valueOf(splittedValues.get(3));

            return Duration.ofMillis((HOURS_IN_MILLISECONDS * hours) + (MINUTES_IN_MILLISECONDS * minutes) + (SECONDS_IN_MILLISECONDS * seconds) + milliseconds);
        }
    }

    public static String getFormattedStringFrom(Duration duration){
        if (duration != null) {
            long durationInMillis = duration.toMillis();
            long millis = durationInMillis % 1000;
            long second = (durationInMillis / 1000) % 60;
            long minute = (durationInMillis / (1000 * 60)) % 60;
            long hour = (durationInMillis / (1000 * 60 * 60)) % 24;

            return String.format("%02d:%02d:%02d.%d", hour, minute, second, millis);
        }

        return null;
    }

    public static Duration getTotalDurationFrom(List<LapRecord> lapRecordsFromDriver){
        return lapRecordsFromDriver
                .stream()
                .map(lapRecord -> lapRecord.getLapDuration())
                .reduce((firstValue, secondValue) -> firstValue.plus(secondValue))
                .get();
    }

    public static Duration getDelayBetween(LapRecord first, LapRecord second){
        if (first.getLapNumber().equals(second.getLapNumber())) {
            return second.getLapDuration().minus(first.getLapDuration());
        } else{
            //Didn't finish the race
            return first.getLapDuration().plus(second.getLapDuration().minus(first.getLapDuration()));
        }
    }
}
