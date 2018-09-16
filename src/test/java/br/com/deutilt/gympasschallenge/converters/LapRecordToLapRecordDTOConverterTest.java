package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;
import br.com.deutilt.gympasschallenge.utils.LapRecordTestUtils;
import org.junit.Test;


import static org.junit.Assert.*;

public class LapRecordToLapRecordDTOConverterTest {

    private LapRecord lapRecord;
    private LapRecordDTO lapRecordDTO;
    private LapRecordToLapRecordDTOConverter converter;

    @Test
    public void shouldConvertALapRecordWithoutNullValues(){
        givenALapRecordWithoutNullValues();
        givenALapRecordToLapRecordDTOConverter();
        whenConverting();
        shouldReturnARecordDTOWithoutNullValues();
        andWithSameValuesAsTheLapRecord();
    }

    @Test
    public void shouldConvertALapRecordWithNullValues(){
        givenALapRecordWithNullValues();
        givenALapRecordToLapRecordDTOConverter();
        whenConverting();
        shouldReturnARecordDTOWithTheSameValues();
    }


    private void givenALapRecordWithoutNullValues(){
        this.lapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    }

    private void givenALapRecordWithNullValues(){
        this.lapRecord = LapRecordTestUtils.getLapRecordWithNullValues();
    }

    private void givenALapRecordToLapRecordDTOConverter(){
        this.converter = new LapRecordToLapRecordDTOConverter();
    }

    private void whenConverting(){
        this.lapRecordDTO = this.converter.convert(lapRecord);
    }

    private void shouldReturnARecordDTOWithoutNullValues(){
        assertNotNull(lapRecordDTO.getAverageLapSpeed());
        assertNotNull(lapRecordDTO.getDriverId());
        assertNotNull(lapRecordDTO.getDriverName());
        assertNotNull(lapRecordDTO.getHour());
        assertNotNull(lapRecordDTO.getLapDuration());
        assertNotNull(lapRecordDTO.getLapNumber());
    }

    private void shouldReturnARecordDTOWithTheSameValues(){
        assertEquals("null", lapRecordDTO.getAverageLapSpeed());
        assertEquals(lapRecord.getDriver().getId(), lapRecordDTO.getDriverId());
        assertEquals(lapRecord.getDriver().getName(), lapRecordDTO.getDriverName());
        assertEquals("null", lapRecordDTO.getHour());
        assertEquals(DurationUtils.getFormattedStringFrom(lapRecord.getLapDuration()), lapRecordDTO.getLapDuration());
        assertEquals(String.valueOf(lapRecord.getLapNumber()), lapRecordDTO.getLapNumber());
    }


    private void andWithSameValuesAsTheLapRecord(){
        assertEquals(String.valueOf(lapRecord.getAverageLapSpeed()), lapRecordDTO.getAverageLapSpeed());
        assertEquals(lapRecord.getDriver().getId(), lapRecordDTO.getDriverId());
        assertEquals(lapRecord.getDriver().getName(), lapRecordDTO.getDriverName());
        assertEquals(String.valueOf(lapRecord.getHour()), lapRecordDTO.getHour());
        assertEquals(DurationUtils.getFormattedStringFrom(lapRecord.getLapDuration()), lapRecordDTO.getLapDuration());
        assertEquals(String.valueOf(lapRecord.getLapNumber()), lapRecordDTO.getLapNumber());
    }

}