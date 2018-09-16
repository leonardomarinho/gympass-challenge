package br.com.deutilt.gympasschallenge.models.dtos;

import java.util.List;

public class ResultDTO {
    LapRecordDTO bestLapFromRace;
    List<PositionDTO> positions;

    public ResultDTO(LapRecordDTO bestLapFromRace, List<PositionDTO> positions) {
        this.bestLapFromRace = bestLapFromRace;
        this.positions = positions;
    }

    public LapRecordDTO getBestLapFromRace() {
        return bestLapFromRace;
    }

    public void setBestLapFromRace(LapRecordDTO bestLapFromRace) {
        this.bestLapFromRace = bestLapFromRace;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionDTO> positions) {
        this.positions = positions;
    }
}
