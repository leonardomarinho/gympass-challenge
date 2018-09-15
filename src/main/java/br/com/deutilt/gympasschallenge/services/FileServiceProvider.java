package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.converters.StringToLapRecordConverter;
import br.com.deutilt.gympasschallenge.interfaces.IFileReader;
import br.com.deutilt.gympasschallenge.interfaces.IFileService;
import br.com.deutilt.gympasschallenge.interfaces.ILapRecordConverter;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import br.com.deutilt.gympasschallenge.readers.LogReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceProvider implements IFileService {

    private IFileReader logReader = new LogReader();
    private ILapRecordConverter converter = new StringToLapRecordConverter();

    public List<LapRecord> getFromFile(MultipartFile file){
        List<String> fileLines = logReader.readFile(file);

        return fileLines.stream()
                .skip(1)
                .map(line -> converter.convert(line)).collect(Collectors.toList());
    }
}
