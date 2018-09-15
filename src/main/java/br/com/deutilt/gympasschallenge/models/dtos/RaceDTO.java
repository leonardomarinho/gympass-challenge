package br.com.deutilt.gympasschallenge.models.dtos;

import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.time.Duration;

public class RaceDTO {

    private LapRecord lapRecord;
    private Duration totalDuration;

    public RaceDTO(Builder builder){
        this.lapRecord = builder.lapRecord;
        this.totalDuration = builder.totalDuration;
    }

    public LapRecord getLapRecord() {
        return lapRecord;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public static class Builder{
        private LapRecord lapRecord;
        private Duration totalDuration;

        public Builder withLapRecord (LapRecord lapRecord){
            this.lapRecord = lapRecord;
            return this;
        }

        public Builder withTotalDuration(Duration totalDuration){
            this.totalDuration = totalDuration;
            return this;
        }

        public RaceDTO build(){
            return new RaceDTO(this);
        }
    }
}
