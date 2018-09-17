package br.com.deutilt.gympasschallenge.controllers;

import br.com.deutilt.gympasschallenge.interfaces.IFileService;
import br.com.deutilt.gympasschallenge.interfaces.IResultService;
import br.com.deutilt.gympasschallenge.models.dtos.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {
            "multipart/form-data", MediaType.APPLICATION_JSON_VALUE })
    public ResultDTO handleFileUpload(@RequestParam("file") MultipartFile file){
        return resultService.getResult(fileService.getFromFile(file));
    }
}
