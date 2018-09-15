package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.models.dtos.LapRecordDTO;

public interface ILapRecordDTOConverter {
    LapRecordDTO convert(LapRecord lapRecord);
}
