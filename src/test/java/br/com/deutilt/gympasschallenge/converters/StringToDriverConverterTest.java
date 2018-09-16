package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.models.Driver;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StringToDriverConverterTest {
    private String string;
    private StringToDriverConverter converter;
    private Driver driver;

    @Test
    public void shouldConvertAStringIntoADriverWithouthNullValues(){
        givenAStringWithIdAndName();
        givenAStringToDriverConverter();
        whenConverting();
        shouldReturnADriverWithoutNullValues();
        andShouldReturnADriverWithTheSameValuesOfTheString();

    }

    private void givenAStringWithIdAndName(){
        this.string = "123 – Ayrton Senna";
    }

    private void givenAStringToDriverConverter(){
        this.converter = new StringToDriverConverter();
    }

    private void whenConverting(){
        this.driver = this.converter.convert(string);
    }

    private void shouldReturnADriverWithoutNullValues(){
        assertNotNull(this.driver.getName());
        assertNotNull(this.driver.getId());
    }


    private void andShouldReturnADriverWithTheSameValuesOfTheString(){
        List<String> originalValues = Arrays.asList(string.split(" – "));
        assertEquals(originalValues.get(0), driver.getId());
        assertEquals(originalValues.get(1), driver.getName());
    }
}