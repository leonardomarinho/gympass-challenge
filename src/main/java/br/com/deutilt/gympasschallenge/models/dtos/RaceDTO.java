package br.com.deutilt.gympasschallenge.models.dtos;

import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.time.Duration;

public class RaceDTO {

    private LapRecord lapRecord;
    private Duration totalDuration;
    private LapRecordDTO bestLap;

    public RaceDTO(Builder builder){
        this.lapRecord = builder.lapRecord;
        this.totalDuration = builder.totalDuration;
        this.bestLap = builder.bestLap;
    }

    public LapRecord getLapRecord() {
        return lapRecord;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public LapRecordDTO getBestLap() {
        return bestLap;
    }

    public static class Builder{
        private LapRecord lapRecord;
        private Duration totalDuration;
        private LapRecordDTO bestLap;

        public Builder withLapRecord (LapRecord lapRecord){
            this.lapRecord = lapRecord;
            return this;
        }

        public Builder withTotalDuration(Duration totalDuration){
            this.totalDuration = totalDuration;
            return this;
        }

        public Builder withBestLap(LapRecordDTO bestLap) {
            this.bestLap = bestLap;
            return this;
        }

        public RaceDTO build(){
            return new RaceDTO(this);
        }
    }
}
