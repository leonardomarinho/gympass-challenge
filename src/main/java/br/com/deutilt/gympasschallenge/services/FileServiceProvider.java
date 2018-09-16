package br.com.deutilt.gympasschallenge.services;

import br.com.deutilt.gympasschallenge.interfaces.IFileReader;
import br.com.deutilt.gympasschallenge.interfaces.IFileService;
import br.com.deutilt.gympasschallenge.interfaces.ILapRecordConverter;
import br.com.deutilt.gympasschallenge.models.LapRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceProvider implements IFileService {

    private IFileReader logReader;
    private ILapRecordConverter converter;

    @Autowired
    public FileServiceProvider(IFileReader logReader, ILapRecordConverter converter) {
        this.logReader = logReader;
        this.converter = converter;
    }


    public List<LapRecord> getFromFile(MultipartFile file){
        List<String> fileLines = logReader.readFile(file);

        return fileLines.stream()
                .skip(1)
                .map(line -> converter.convert(line)).collect(Collectors.toList());
    }
}
