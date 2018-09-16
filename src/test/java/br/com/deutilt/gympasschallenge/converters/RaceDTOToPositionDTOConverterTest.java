package br.com.deutilt.gympasschallenge.converters;

import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaceDTOToPositionDTOConverterTest {

    private RaceDTO raceDTO;
    private RaceDTOToPositionDTOConverter converter;
    private PositionDTO positionDTO;

    @Test
    public void shouldConvertARaceDTOWithoutNullValues(){
        givenARaceDTOWithoutNullValues();
        givenARaceDTOToPositionDTOConverter();
        whenConverting();
        shouldReturnAPositionDTOWithoutNullValues();
        andWithSameValuesAsTheRaceDTO();
    }

    @Test
    public void shouldConvertARaceDTOWithNullValues(){
        givenARaceDTOWithNullValues();
        givenARaceDTOToPositionDTOConverter();
        whenConverting();
        shouldReturnAPositionDTOWithTheSameValues();
    }


    private void givenARaceDTOWithoutNullValues(){
        this.raceDTO = RaceDTOTestUtils.getRaceDTOWithoutNullValues();
    }

    private void givenARaceDTOWithNullValues(){
        this.raceDTO = RaceDTOTestUtils.getRaceDTOWithNullValues();
    }

    private void givenARaceDTOToPositionDTOConverter(){
        this.converter = new RaceDTOToPositionDTOConverter();
    }

    private void whenConverting(){
        this.positionDTO = this.converter.convert(1, raceDTO);
    }

    private void shouldReturnAPositionDTOWithoutNullValues(){
        assertNotNull(positionDTO.getBestLap().getLapNumber());
        assertNotNull(positionDTO.getBestLap().getLapDuration());
        assertNotNull(positionDTO.getBestLap().getHour());
        assertNotNull(positionDTO.getBestLap().getDriverName());
        assertNotNull(positionDTO.getBestLap().getDriverId());
        assertNotNull(positionDTO.getBestLap().getAverageLapSpeed());
        assertNotNull(positionDTO.getDelayAfterWinner());
        assertNotNull(positionDTO.getTotalAverageSpeed());
        assertNotNull(positionDTO.getCompletedLaps());
        assertNotNull(positionDTO.getDriverName());
        assertNotNull(positionDTO.getDriverId());
        assertNotNull(positionDTO.getPosition());
    }                                               

    private void shouldReturnAPositionDTOWithTheSameValues(){
        assertNotNull(raceDTO.getBestLap().getLapNumber(), positionDTO.getBestLap().getLapNumber());
        assertNotNull(raceDTO.getBestLap().getLapDuration(), positionDTO.getBestLap().getLapDuration());
        assertNotNull(raceDTO.getBestLap().getHour(), positionDTO.getBestLap().getHour());
        assertNotNull(raceDTO.getBestLap().getDriverName(), positionDTO.getBestLap().getDriverName());
        assertNotNull(raceDTO.getBestLap().getDriverId(), positionDTO.getBestLap().getDriverId());
        assertNotNull(raceDTO.getBestLap().getAverageLapSpeed(), positionDTO.getBestLap().getAverageLapSpeed());
    }


    private void andWithSameValuesAsTheRaceDTO() {
        assertEquals(raceDTO.getBestLap().getLapNumber(), positionDTO.getBestLap().getLapNumber());
        assertEquals(raceDTO.getBestLap().getLapDuration(), positionDTO.getBestLap().getLapDuration());
        assertEquals(raceDTO.getBestLap().getHour(), positionDTO.getBestLap().getHour());
        assertEquals(raceDTO.getBestLap().getDriverName(), positionDTO.getBestLap().getDriverName());
        assertEquals(raceDTO.getBestLap().getDriverId(), positionDTO.getBestLap().getDriverId());
        assertEquals(raceDTO.getBestLap().getAverageLapSpeed(), positionDTO.getBestLap().getAverageLapSpeed());
        assertEquals(raceDTO.getDelayAfterWinner(), positionDTO.getDelayAfterWinner());
        assertEquals(raceDTO.getTotalAverageSpeed(), positionDTO.getTotalAverageSpeed());
        assertEquals(raceDTO.get, positionDTO.getCompletedLaps());
    }