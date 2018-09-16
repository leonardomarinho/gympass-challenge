package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.ILapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;

public class LapRecordToLapRecordDTOConverter implements ILapRecordDTOConverter {

    public LapRecordDTO convert(LapRecord lapRecord) {

        String hour = String.valueOf(lapRecord.getHour());
        String driverId = lapRecord.getDriver().getId();
        String driverName = lapRecord.getDriver().getName();
        String lapNumber = String.valueOf(lapRecord.getLapNumber());
        String lapDuration = DurationUtils.getFormattedStringFrom(lapRecord.getLapDuration());
        String averageLapSpeed = String.valueOf(lapRecord.getAverageLapSpeed());

        return new LapRecordDTO.Builder()
                                .withHour(hour)
                                .withDriverId(driverId)
                                .withDriverName(driverName)
                                .withLapNumber(lapNumber)
                                .withlapDuration(lapDuration)
                                .withAverageLapSpeed(averageLapSpeed)
                                .build();

    }
}
