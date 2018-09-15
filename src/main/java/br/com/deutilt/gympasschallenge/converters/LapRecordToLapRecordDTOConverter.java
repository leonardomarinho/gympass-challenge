package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.ILapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;

public class LapRecordToLapRecordDTOConverter implements ILapRecordDTOConverter {
    @Override
    public LapRecordDTO convert(LapRecord lapRecord) {

        String hour = lapRecord.getHour().toString();
        String lapNumber = lapRecord.getLapNumber().toString();
        String lapDuration = DurationUtils.getFormattedStringFrom(lapRecord.getLapDuration());
        String averageLapSpeed = lapRecord.getAverageLapSpeed().toString();

        return new LapRecordDTO.Builder()
                                .withHour(hour)
                                .withLapNumber(lapNumber)
                                .withlapDuration(lapDuration)
                                .withAverageLapSpeed(averageLapSpeed)
                                .build();

    }
}
