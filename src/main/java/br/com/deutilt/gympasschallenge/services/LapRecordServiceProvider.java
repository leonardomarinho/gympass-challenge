package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.interfaces.ILapRecordService;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LapRecordServiceProvider implements ILapRecordService{

    public List<LapRecord> getLapsFromDriver(List<LapRecord> lapRecords, String driverId){
        return lapRecords.stream().filter(lap -> lap.getDriver().getId().equals(driverId)).collect(Collectors.toList());
    }

    public Set<String> getDriversIds(List<LapRecord> lapRecords) {
        return lapRecords.stream().map(lapRecord -> lapRecord.getDriver().getId()).collect(Collectors.toSet());
    }

    public LapRecord getLastLapFromDriver(List<LapRecord> lapRecords, String driverId){
        Comparator<LapRecord> lapNumberComparator = Comparator.comparing(LapRecord::getLapNumber);
        List<LapRecord> lapsFromDriver = getLapsFromDriver(lapRecords, driverId);

        return lapsFromDriver.stream().max(lapNumberComparator).get();
    }

    public BigDecimal getTotalAverageSpeedFrom(List<LapRecord> lapRecordsFromDriver){
        BigDecimal sum = lapRecordsFromDriver
                .stream()
                .map(lapRecord -> lapRecord.getAverageLapSpeed())
                .reduce((firstValue, secondValue) -> firstValue.add(secondValue))
                .get();

        return sum.divide(new BigDecimal(lapRecordsFromDriver.size()), RoundingMode.UP);
    }

    public LapRecord getBestLapFrom(List<LapRecord> lapRecordsFromDriver){
        Comparator<LapRecord> durationComparator = Comparator.comparing(LapRecord::getLapDuration);
        return lapRecordsFromDriver.stream().min(durationComparator).get();
    }
}
