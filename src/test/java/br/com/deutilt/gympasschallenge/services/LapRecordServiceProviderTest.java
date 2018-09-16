package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.models.Driver;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.utils.LapRecordTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class LapRecordServiceProviderTest {

    private List<LapRecord> lapRecords;
    private List<LapRecord> result;
    private Set<String> driversResult;
    private List<Driver> drivers;
    private LapRecord lapRecord;
    private BigDecimal totalAverageSpeed;

    Driver firstDriver = new Driver("123", "Primeiro");
    Driver secondDriver = new Driver("456", "Segundo");
    Driver thirdDriver = new Driver("789", "Terceiro");

    @InjectMocks
    private LapRecordServiceProvider serviceProvider;

    private LapRecord firstLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord secondLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord thirdLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();
    private LapRecord fourthLapRecord = LapRecordTestUtils.getLapRecordWithoutNullValues();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLapsFromDriver(){
        givenSomeDrivers();
        givenSomeLapRecords();
        whenRequestingTheLapsForTheDriver();
        shouldReturnTheLapsBelongingToThatDriver();
    }

    @Test
    public void testGetDriversId(){
        givenSomeDrivers();
        givenSomeLapRecords();
        whenRequestingTheDriversFromTheLapRecords();
        shouldReturnAllDrivers();
    }

    @Test
    public void testGetLastLapFromDriver(){
        givenSomeDrivers();
        givenSomeLapRecords();
        whenRequestingTheLastLapFromADriver();
        shouldReturnTheLastLapFromThatDriver();
    }

    @Test
    public void testGetTotalAverageSpeedFrom(){
        givenSomeDrivers();
        givenSomeLapRecords();
        whenRequestingTheTotalAverageSpeedFromADriver();
        shouldReturnTheTotalAverageSpeedFromThatDriver();
    }

    @Test
    public void testGetBestLapFrom(){
        givenSomeDrivers();
        givenSomeLapRecords();
        whenRequestingTheBestLapFromADriver();
        shouldReturnTheBestLapFromThatDriver();
    }

    private void givenSomeDrivers(){
        this.drivers = Arrays.asList(firstDriver, secondDriver, thirdDriver);
    }

    private void givenSomeLapRecords(){
        firstLapRecord.setDriver(firstDriver);
        firstLapRecord.setLapNumber(1);
        firstLapRecord.setAverageLapSpeed(new BigDecimal(30));
        secondLapRecord.setDriver(firstDriver);
        secondLapRecord.setLapNumber(2);
        secondLapRecord.setAverageLapSpeed(new BigDecimal(40));
        thirdLapRecord.setDriver(secondDriver);
        fourthLapRecord.setDriver(thirdDriver);
        lapRecords = Arrays.asList(firstLapRecord, secondLapRecord, thirdLapRecord, fourthLapRecord);
    }

    private void whenRequestingTheLapsForTheDriver(){
        this.result = serviceProvider.getLapsFromDriver(lapRecords, firstDriver.getId());
    }

    private void whenRequestingTheDriversFromTheLapRecords(){
        this.driversResult = serviceProvider.getDriversIds(lapRecords);
    }

    private void whenRequestingTheLastLapFromADriver(){
        this.lapRecord = serviceProvider.getLastLapFromDriver(lapRecords, firstDriver.getId());
    }

    private void whenRequestingTheTotalAverageSpeedFromADriver(){
        this.totalAverageSpeed = serviceProvider.getTotalAverageSpeedFrom(Arrays.asList(firstLapRecord, secondLapRecord));
    }

    private void whenRequestingTheBestLapFromADriver(){
        this.lapRecord = serviceProvider.getBestLapFrom(Arrays.asList(firstLapRecord, secondLapRecord));
    }

    private void shouldReturnTheLapsBelongingToThatDriver(){
        assertEquals(2, result.size());
        assertEquals(firstDriver.getName(), result.get(0).getDriver().getName());
        assertEquals(firstDriver.getId(), result.get(0).getDriver().getId());

        assertEquals(firstDriver.getName(), result.get(1).getDriver().getName());
        assertEquals(firstDriver.getId(), result.get(1).getDriver().getId());
    }

    private void shouldReturnAllDrivers(){
        assertEquals(drivers.size(), driversResult.size());
    }

    private void shouldReturnTheLastLapFromThatDriver(){
        assertEquals(2, lapRecord.getLapNumber(), 0);
        assertEquals(firstDriver.getId(), lapRecord.getDriver().getId());
        assertEquals(firstDriver.getName(), lapRecord.getDriver().getName());
    }

    private void shouldReturnTheTotalAverageSpeedFromThatDriver(){
        assertEquals(new BigDecimal(35), this.totalAverageSpeed);
    }

    private void shouldReturnTheBestLapFromThatDriver(){
        assertEquals(firstLapRecord.getLapNumber(), lapRecord.getLapNumber());
        assertEquals(firstLapRecord.getLapDuration(), lapRecord.getLapDuration());
        assertEquals(firstLapRecord.getAverageLapSpeed(), lapRecord.getAverageLapSpeed());
        assertEquals(firstLapRecord.getHour(), lapRecord.getHour());
        assertEquals(firstLapRecord.getDriver().getId(), lapRecord.getDriver().getId());
        assertEquals(firstLapRecord.getDriver().getName(), lapRecord.getDriver().getName());
    }
}