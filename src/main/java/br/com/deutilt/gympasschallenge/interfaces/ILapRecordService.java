package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ILapRecordService {
    List<LapRecord> getLapsFromDriver(List<LapRecord> lapRecords, String driverId);
    Set<String> getDriversIds(List<LapRecord> lapRecords);
    LapRecord getLastLapFromDriver(List<LapRecord> lapRecords, String driverId);
    BigDecimal getTotalAverageSpeedFrom(List<LapRecord> lapRecordsFromDriver);
    LapRecord getBestLapFrom(List<LapRecord> lapRecordsFromDriver);
}
