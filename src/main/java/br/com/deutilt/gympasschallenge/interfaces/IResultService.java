package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;

import java.util.List;

public interface IResultService {
    ResultDTO getResult(List<LapRecord> lapRecords);
}
