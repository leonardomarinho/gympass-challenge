package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.interfaces.IDriverConverter;
import br.com.deutilt.gympasschallenge.models.Driver;

public class StringToDriverConverter implements IDriverConverter{

    private static final String SPLIT_CHAR = " – ";

    public Driver convert(String line){
        String splitResult[] = line.split(SPLIT_CHAR);
        return new Driver(splitResult[0], splitResult[1]);
    }
}
