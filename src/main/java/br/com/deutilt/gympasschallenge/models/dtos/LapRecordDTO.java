package br.com.deutilt.gympasschallenge.models.dtos;

public class LapRecordDTO {
    private String hour;
    private String lapNumber;
    private String lapDuration;
    private String averageLapSpeed;

    private LapRecordDTO(Builder builder){
        this.hour = builder.hour;
        this.lapNumber = builder.lapNumber;
        this.lapDuration = builder.lapDuration;
        this.averageLapSpeed = builder.averageLapSpeed;
    }

    public String getHour() {
        return hour;
    }

    public String getLapNumber() {
        return lapNumber;
    }

    public String getLapDuration() {
        return lapDuration;
    }

    public String getAverageLapSpeed() {
        return averageLapSpeed;
    }

    public static class Builder{

        private String hour;
        private String lapNumber;
        private String lapDuration;
        private String averageLapSpeed;

        public Builder(){}

        public Builder withHour(String hour){
            this.hour = hour;
            return this;
        }

        public Builder withLapNumber(String lapNumber){
            this.lapNumber = lapNumber;
            return this;
        }

        public Builder withlapDuration(String lapDuration){
            this.lapDuration = lapDuration;
            return this;
        }

        public Builder withAverageLapSpeed(String averageLapSpeed){
            this.averageLapSpeed = averageLapSpeed;
            return this;
        }

        public LapRecordDTO build(){
            return new LapRecordDTO(this);
        }
    }
}
