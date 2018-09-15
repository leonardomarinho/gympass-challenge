package br.com.deutilt.gympasschallenge.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileReader {

    List<String> readFile(MultipartFile source);
}
