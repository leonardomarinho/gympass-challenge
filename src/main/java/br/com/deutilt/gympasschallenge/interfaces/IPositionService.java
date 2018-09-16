package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;

import java.util.List;

public interface IPositionService {
    List<PositionDTO> getPositions(List<LapRecord> lapRecords);
}
