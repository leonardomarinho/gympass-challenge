package br.com.deutilt.gympasschallenge.controllers;

import br.com.deutilt.gympasschallenge.interfaces.IFileService;
import br.com.deutilt.gympasschallenge.interfaces.IResultService;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AppController {

    private IFileService fileService;
    private IResultService resultService;

    @Autowired
    public AppController(IFileService fileService, IResultService resultService) {
        this.fileService = fileService;
        this.resultService = resultService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDTO handleFileUpload(@RequestParam("file") MultipartFile file){
        return resultService.getResult(fileService.getFromFile(file));
    }
}
