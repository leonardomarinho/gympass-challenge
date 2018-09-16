package br.com.deutilt.gympasschallenge.models.dtos;

public class ResultDTO {

    private int position;
    private String driverId;
    private String driverName;
    private String completedLaps;
    private String totalDuration;
    private String totalAverageSpeed;
    private String delayAfterWinner;
    private LapRecordDTO bestLap;

    public ResultDTO(Builder builder) {
        this.position = builder.position;
        this.driverId = builder.driverId;
        this.driverName = builder.driverName;
        this.completedLaps = builder.completedLaps;
        this.totalDuration = builder.totalDuration;
        this.bestLap = builder.bestLap;
        this.totalAverageSpeed = builder.totalAverageSpeed;
        this.delayAfterWinner = builder.delayAfterWinner;
    }

    public int getPosition() {
        return position;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getCompletedLaps() {
        return completedLaps;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public String getTotalAverageSpeed() {
        return totalAverageSpeed;
    }

    public LapRecordDTO getBestLap() {
        return bestLap;
    }

    public String getDelayAfterWinner() {
        return delayAfterWinner;
    }

    public static class Builder {
        private int position;
        private String driverId;
        private String driverName;
        private String completedLaps;
        private String totalDuration;
        private LapRecordDTO bestLap;
        private String totalAverageSpeed;
        private String delayAfterWinner;

        public Builder withPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder withDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public Builder withDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public Builder withCompletedLaps(String completedLaps) {
            this.completedLaps = completedLaps;
            return this;
        }

        public Builder withTotalDuration(String totalDuration) {
            this.totalDuration = totalDuration;
            return this;
        }

        public Builder withTotalAverageSpeed(String totalAverageSpeed) {
            this.totalAverageSpeed = totalAverageSpeed;
            return this;
        }

        public Builder withBestLap(LapRecordDTO bestLap) {
            this.bestLap = bestLap;
            return this;
        }

        public Builder withDelayAfterWinner(String delayAfterWinner){
            this.delayAfterWinner = delayAfterWinner;
            return this;
        }

        public ResultDTO build(){
            return new ResultDTO(this);
        }
    }
}
