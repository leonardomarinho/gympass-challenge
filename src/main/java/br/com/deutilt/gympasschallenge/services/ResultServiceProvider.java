package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.interfaces.ILapRecordDTOConverter;
import br.com.deutilt.gympasschallenge.interfaces.ILapRecordService;
import br.com.deutilt.gympasschallenge.interfaces.IPositionService;
import br.com.deutilt.gympasschallenge.interfaces.IResultService;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceProvider implements IResultService{

    private IPositionService positionService;
    private ILapRecordService lapRecordServiceProvider;
    private ILapRecordDTOConverter converter;

    @Autowired
    public ResultServiceProvider(IPositionService positionService, ILapRecordService lapRecordServiceProvider, ILapRecordDTOConverter converter) {
        this.positionService = positionService;
        this.lapRecordServiceProvider = lapRecordServiceProvider;
        this.converter = converter;
    }

    @Override
    public ResultDTO getResult(List<LapRecord> lapRecords) {
        List<PositionDTO> results = positionService.getPositions(lapRecords);
        LapRecordDTO bestLapFromRace = converter.convert(lapRecordServiceProvider.getBestLapFrom(lapRecords));

        return new ResultDTO(bestLapFromRace, results);
    }
}
