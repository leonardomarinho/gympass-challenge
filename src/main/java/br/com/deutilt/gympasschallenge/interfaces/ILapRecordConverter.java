package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;

public interface ILapRecordConverter {
    LapRecord convert(String fileLine);
}
