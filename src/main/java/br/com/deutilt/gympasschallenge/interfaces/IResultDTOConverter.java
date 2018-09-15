package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.dtos.RaceDTO;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;

public interface IResultDTOConverter {

    ResultDTO convert(int position, RaceDTO raceDTO);
}
