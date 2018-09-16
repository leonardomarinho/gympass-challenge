package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class StringToLapRecordConverterTest {

    private String string;

    @Mock
    private StringToDriverConverter stringToDriverConverter;

    @InjectMocks
    private StringToLapRecordConverter converter;

    private LapRecord lapRecord;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConvertAStringIntoALapRecord(){
        givenAString();
        givenAStringToLapRecordConverter();
        whenConverting();
        shouldReturnALapRecordWithoutNullValues();
        andShouldReturnALapRecordWithTheSameValuesOfTheString();
    }

    private void givenAString(){
        this.string = "23:49:10.858    033 – R.BARRICHELLO    1    1:04.352    43,243";
    }

    private void givenAStringToLapRecordConverter(){
        this.converter = new StringToLapRecordConverter();
    }

    private void whenConverting(){
        when(stringToDriverConverter.convert(anyString())).thenReturn(new Driver("033", "R.BARRICHELLO"));
        this.lapRecord = this.converter.convert(string);
    }

    private void shouldReturnALapRecordWithoutNullValues(){
        assertNotNull(this.lapRecord.getDriver().getName());
        assertNotNull(this.lapRecord.getDriver().getId());
        assertNotNull(this.lapRecord.getLapNumber());
        assertNotNull(this.lapRecord.getLapDuration());
        assertNotNull(this.lapRecord.getHour());
        assertNotNull(this.lapRecord.getAverageLapSpeed());
    }


    private void andShouldReturnALapRecordWithTheSameValuesOfTheString(){
        List<String> originalValues = Arrays.asList(string.split("    "));
        assertNotNull(originalValues.get(0), this.lapRecord.getHour());
        assertNotNull(originalValues.get(1), this.lapRecord.getDriver().getId().concat(" – ").concat(this.lapRecord.getDriver().getName()));
        assertNotNull(originalValues.get(2), this.lapRecord.getLapNumber());
        assertNotNull(originalValues.get(3), this.lapRecord.getLapDuration());
        assertNotNull(originalValues.get(4), this.lapRecord.getAverageLapSpeed());
    }


}