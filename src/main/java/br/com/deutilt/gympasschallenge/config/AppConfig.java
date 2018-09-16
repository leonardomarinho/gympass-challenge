package br.com.deutilt.gympasschallenge.config;

import br.com.deutilt.gympasschallenge.converters.LapRecordToLapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.converters.RaceDTOToPositionDTOConverter;
import br.com.deutilt.gympasschallenge.converters.StringToDriverConverter;
import br.com.deutilt.gympasschallenge.converters.StringToLapRecordConverter;
import br.com.deutilt.gympasschallenge.readers.LogReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LogReader logReader() {
        return new LogReader();
    }

    @Bean
    public StringToDriverConverter driverConverter(){
        return new StringToDriverConverter();
    }

    @Bean
    public StringToLapRecordConverter stringToLapRecordConverter(){
        return new StringToLapRecordConverter();
    }

    @Bean
    public LapRecordToLapRecordDTOConverter lapRecordToLapRecordDTOConverter (){
        return new LapRecordToLapRecordDTOConverter();
    }

    @Bean
    public RaceDTOToPositionDTOConverter raceDTOToPositionDTOConverter(){
        return new RaceDTOToPositionDTOConverter();
    }
}
