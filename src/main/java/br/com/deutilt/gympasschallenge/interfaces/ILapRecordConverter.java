package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;

import java.util.List;

public interface ILapRecordConverter {
    LapRecord convert(String fileLine);
}
