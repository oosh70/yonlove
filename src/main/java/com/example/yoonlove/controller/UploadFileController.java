package com.example.yoonlove.controller;

import com.example.yoonlove.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadFileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String basePath = "C:/Users/강의실3_PC014/AppData/Local/Temp/tomcat.8080.13394834142935433607/work/Tomcat/localhost/ROOT";

        return fileService.uploadFile(file,basePath);
    }
}
