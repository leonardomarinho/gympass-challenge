package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.LapRecordToLapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.converters.RaceDTOToPositionDTOConverter;
import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.utils.DurationUtils;
import br.com.deutilt.gympasschallenge.utils.LapRecordTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

public class PositionServiceProviderTest {

    Driver firstDriver = new Driver("123", "Primeiro");
    Driver secondDriver = new Driver("456", "Segundo");
    Driver thirdDriver = new Driver("789", "Terceiro");

    private LapRecord firstLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord secondLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord thirdLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord fourthLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord fifthLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord sixthLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();

    private List<LapRecord> lapRecords;
    private List<PositionDTO> positionDTOs;

    @InjectMocks
    private PositionServiceProvider positionServiceProvider;

    @Mock
    private LapRecordToLapRecordDTOConverter lapRecordToLapRecordDTOConverter;

    @Mock
    private RaceDTOToPositionDTOConverter raceDTOToPositionDTOConverter;

    @Mock
    private LapRecordServiceProvider lapRecordServiceProvider;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldReturnThePositionsInTheRightOrder(){
        givenSomeLapRecords();
        whenRequestingThePositions();
        shouldReturnThePositionsInTheRightOrder();
    }

    private void givenSomeLapRecords(){
        firstLapRecord.setDriver(firstDriver);
        firstLapRecord.setLapNumber(1);
        firstLapRecord.setAverageLapSpeed(new BigDecimal(10));

        secondLapRecord.setDriver(firstDriver);
        secondLapRecord.setLapNumber(2);
        secondLapRecord.setAverageLapSpeed(new BigDecimal(20));

        thirdLapRecord.setDriver(secondDriver);
        thirdLapRecord.setLapNumber(1);
        thirdLapRecord.setAverageLapSpeed(new BigDecimal(30));

        fourthLapRecord.setDriver(secondDriver);
        fourthLapRecord.setLapNumber(2);
        fourthLapRecord.setAverageLapSpeed(new BigDecimal(40));

        fifthLapRecord.setDriver(thirdDriver);
        fifthLapRecord.setLapNumber(1);
        fifthLapRecord.setAverageLapSpeed(new BigDecimal(50));

        sixthLapRecord.setDriver(thirdDriver);
        sixthLapRecord.setLapNumber(2);
        sixthLapRecord.setAverageLapSpeed(new BigDecimal(60));

        this.lapRecords = Arrays.asList(firstLapRecord, secondLapRecord, thirdLapRecord, fourthLapRecord, fifthLapRecord, sixthLapRecord);
    }

    private void whenRequestingThePositions(){

        Set<String> driversId = new HashSet<>();
        driversId.add(firstDriver.getId());
        driversId.add(secondDriver.getId());
        driversId.add(thirdDriver.getId());

        List<LapRecord> firstDriverLaps = Arrays.asList(firstLapRecord, secondLapRecord);
        List<LapRecord> secondDriverLaps = Arrays.asList(thirdLapRecord, fourthLapRecord);
        List<LapRecord> thirdDriverLaps = Arrays.asList(fifthLapRecord, sixthLapRecord);

        LapRecordDTO firstLapRecordDTO = new LapRecordDTO.Builder()
                .withAverageLapSpeed("15")
                .withLapNumber("1")
                .withDriverId(firstDriver.getId())
                .withDriverName(firstDriver.getName())
                .withHour(String.valueOf(firstLapRecord.getHour()))
                .withlapDuration(DurationUtils.getFormattedStringFrom(firstLapRecord.getLapDuration()))
                .build();

        LapRecordDTO thirdLapRecordDTO = new LapRecordDTO.Builder()
                .withAverageLapSpeed("35")
                .withLapNumber("1")
                .withDriverId(secondDriver.getId())
                .withDriverName(secondDriver.getName())
                .withHour(String.valueOf(thirdLapRecord.getHour()))
                .withlapDuration(DurationUtils.getFormattedStringFrom(thirdLapRecord.getLapDuration()))
                .build();

        LapRecordDTO fifthLapRecordDTO = new LapRecordDTO.Builder()
                .withAverageLapSpeed("55")
                .withLapNumber("1")
                .withDriverId(thirdDriver.getId())
                .withDriverName(thirdDriver.getName())
                .withHour(String.valueOf(fifthLapRecord.getHour()))
                .withlapDuration(DurationUtils.getFormattedStringFrom(fifthLapRecord.getLapDuration()))
                .build();

        RaceDTO firstRaceDTO = new RaceDTO.Builder()
                .withLapRecord(secondLapRecord)
                .withTotalDuration(DurationUtils.getTotalDurationFrom(firstDriverLaps))
                .withBestLap(firstLapRecordDTO)
                .withTotalAverageSpeed(new BigDecimal(15))
                .build();

        RaceDTO secondRaceDTO = new RaceDTO.Builder()
                .withLapRecord(fourthLapRecord)
                .withTotalDuration(DurationUtils.getTotalDurationFrom(secondDriverLaps))
                .withBestLap(thirdLapRecordDTO)
                .withTotalAverageSpeed(new BigDecimal(35))
                .build();

        RaceDTO thirdRaceDTO = new RaceDTO.Builder()
                .withLapRecord(sixthLapRecord)
                .withTotalDuration(DurationUtils.getTotalDurationFrom(thirdDriverLaps))
                .withBestLap(fifthLapRecordDTO)
                .withTotalAverageSpeed(new BigDecimal(55))
                .build();


        PositionDTO firstPositionDTO = new PositionDTO.Builder()
                .withPosition(1)
                .withBestLap(firstLapRecordDTO)
                .withCompletedLaps("2")
                .withDelayAfterWinner(DurationUtils.getFormattedStringFrom(DurationUtils.getDelayBetween(secondLapRecord, secondLapRecord)))
                .withDriverId(firstDriver.getId())
                .withDriverName(firstDriver.getName())
                .withTotalAverageSpeed("15")
                .withTotalDuration(DurationUtils.getFormattedStringFrom(DurationUtils.getTotalDurationFrom(firstDriverLaps)))
                .build();

        PositionDTO secondPositionDTO = new PositionDTO.Builder()
                .withPosition(2)
                .withBestLap(thirdLapRecordDTO)
                .withCompletedLaps("2")
                .withDelayAfterWinner(DurationUtils.getFormattedStringFrom(DurationUtils.getDelayBetween(secondLapRecord, fourthLapRecord)))
                .withDriverId(secondDriver.getId())
                .withDriverName(secondDriver.getName())
                .withTotalAverageSpeed("35")
                .withTotalDuration(DurationUtils.getFormattedStringFrom(DurationUtils.getTotalDurationFrom(secondDriverLaps)))
                .build();

        PositionDTO thirdPositionDTO = new PositionDTO.Builder()
                .withPosition(3)
                .withBestLap(fifthLapRecordDTO)
                .withCompletedLaps("2")
                .withDelayAfterWinner(DurationUtils.getFormattedStringFrom(DurationUtils.getDelayBetween(secondLapRecord, sixthLapRecord)))
                .withDriverId(thirdDriver.getId())
                .withDriverName(thirdDriver.getName())
                .withTotalAverageSpeed("55")
                .withTotalDuration(DurationUtils.getFormattedStringFrom(DurationUtils.getTotalDurationFrom(thirdDriverLaps)))
                .build();


        when(lapRecordServiceProvider.getDriversIds(lapRecords)).thenReturn(driversId);

        when(lapRecordServiceProvider.getLapsFromDriver(lapRecords, firstDriver.getId())).thenReturn(firstDriverLaps);
        when(lapRecordServiceProvider.getLapsFromDriver(lapRecords, secondDriver.getId())).thenReturn(secondDriverLaps);
        when(lapRecordServiceProvider.getLapsFromDriver(lapRecords, thirdDriver.getId())).thenReturn(thirdDriverLaps);

        when(lapRecordServiceProvider.getLastLapFromDriver(lapRecords, firstDriver.getId())).thenReturn(secondLapRecord);
        when(lapRecordServiceProvider.getLastLapFromDriver(lapRecords, secondDriver.getId())).thenReturn(fourthLapRecord);
        when(lapRecordServiceProvider.getLastLapFromDriver(lapRecords, thirdDriver.getId())).thenReturn(sixthLapRecord);

        when(lapRecordServiceProvider.getBestLapFrom(firstDriverLaps)).thenReturn(firstLapRecord);
        when(lapRecordServiceProvider.getBestLapFrom(secondDriverLaps)).thenReturn(thirdLapRecord);
        when(lapRecordServiceProvider.getBestLapFrom(thirdDriverLaps)).thenReturn(fifthLapRecord);

        when(lapRecordToLapRecordDTOConverter.convert(firstLapRecord)).thenReturn(firstLapRecordDTO);
        when(lapRecordToLapRecordDTOConverter.convert(thirdLapRecord)).thenReturn(thirdLapRecordDTO);
        when(lapRecordToLapRecordDTOConverter.convert(fifthLapRecord)).thenReturn(fifthLapRecordDTO);

        when(lapRecordServiceProvider.getTotalAverageSpeedFrom(firstDriverLaps)).thenReturn(new BigDecimal(15));
        when(lapRecordServiceProvider.getTotalAverageSpeedFrom(secondDriverLaps)).thenReturn(new BigDecimal(35));
        when(lapRecordServiceProvider.getTotalAverageSpeedFrom(thirdDriverLaps)).thenReturn(new BigDecimal(55));

        when(raceDTOToPositionDTOConverter.convert(anyInt(), anyObject())).thenCallRealMethod();

        positionDTOs = positionServiceProvider.getPositions(lapRecords);
    }

    private void shouldReturnThePositionsInTheRightOrder(){
        assertEquals(1, positionDTOs.get(0).getPosition());
        assertEquals(firstLapRecord.getDriver().getName(), positionDTOs.get(0).getDriverName());
        assertEquals(firstLapRecord.getDriver().getId(), positionDTOs.get(0).getDriverId());

        assertEquals(2, positionDTOs.get(1).getPosition());
        assertEquals(thirdLapRecord.getDriver().getName(), positionDTOs.get(1).getDriverName());
        assertEquals(thirdLapRecord.getDriver().getId(), positionDTOs.get(1).getDriverId());

        assertEquals(3, positionDTOs.get(2).getPosition());
        assertEquals(fifthLapRecord.getDriver().getName(), positionDTOs.get(2).getDriverName());
        assertEquals(fifthLapRecord.getDriver().getId(), positionDTOs.get(2).getDriverId());
    }
}