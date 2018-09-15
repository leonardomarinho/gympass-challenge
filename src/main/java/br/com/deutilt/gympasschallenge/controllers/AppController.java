package br.com.deutilt.gympasschallenge.controllers;

import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import br.com.deutilt.gympasschallenge.services.FileServiceProvider;
import br.com.deutilt.gympasschallenge.services.ResultServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AppController {

    private FileServiceProvider fileServiceProvider;
    private ResultServiceProvider resultServiceProvider;

    @Autowired
    public AppController(FileServiceProvider fileServiceProvider, ResultServiceProvider resultServiceProvider) {
        this.fileServiceProvider = fileServiceProvider;
        this.resultServiceProvider = resultServiceProvider;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<ResultDTO> handleFileUpload(@RequestParam("file") MultipartFile file){
        return resultServiceProvider.getResults(fileServiceProvider.getFromFile(file));
    }
}
