package br.com.deutilt.gympasschallenge.readers;

import br.com.deutilt.gympasschallenge.interfaces.IFileReader;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogReader implements IFileReader{

    public List<String> readFile(MultipartFile source) {
        List<String> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(source.getInputStream()));
            result = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
