package com.demo.p2.controller;

import com.demo.p2.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    public static final String PATH = "D:/upload";

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String message = "";
        if (FileUploadUtil.upload(file, PATH, fileName)) {
            return "上传成功";
        } else {
            return "上传失败";
        }
    }

}
