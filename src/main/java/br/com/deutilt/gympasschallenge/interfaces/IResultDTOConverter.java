package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.PositionDTO;

public interface IResultDTOConverter {

    PositionDTO convert(int position, RaceDTO raceDTO);
}
