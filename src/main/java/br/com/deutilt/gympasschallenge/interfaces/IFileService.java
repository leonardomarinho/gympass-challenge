package br.com.deutilt.gympasschallenge.interfaces;

import br.com.deutilt.gympasschallenge.models.LapRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    List<LapRecord> getFromFile(MultipartFile file);
}
