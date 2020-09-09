package com.explore.galaxy.basic.utils.tool.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.explore.galaxy.basic.utils.tool.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/getFiles")
    public List<String> getFiles(@RequestParam(value = "folderName", defaultValue = "null") String folderName) {
        return fileService.getFiles(folderName);
    }

    /**
     * @importance: 上传需要使用PostMapping，参数使用@RequeParam进行接受
     * 前端需要用formData对数据进行组装
     */
    @PostMapping(value = "/upload")
    public int upload(@RequestParam("file") MultipartFile[] file, @RequestParam("folderName") String folderName) throws Exception {
        return fileService.fileUpload(file, folderName);
    }

    /**
     * @param file       文件
     * @param folderName
     * @description: 上传后刷新文件列表, 为了解决element-upload组件多选上传，前端页面不及时刷新的问题
     */
    @PostMapping(value = "/getFilesAfterUpload")
    public List<String> getFilesAfterUpload(@RequestParam("file") MultipartFile[] file, @RequestParam("folderName") String folderName) throws Exception {
        fileService.fileUpload(file, folderName);
        return fileService.getFiles(folderName);
    }

    /**
     * @param params:folderName->文件夹名；fileName->文件名
     */
    @DeleteMapping(value = "deleteFiles")
    public int deleteFiles(@RequestBody JSONObject params) {
        String folderName = params.getString("folderName");
        String fileName = params.getString("fileName");
        return fileService.deleteFiles(folderName, fileName);
    }

    @PostMapping("/downloadFile")
    public ResponseEntity<FileSystemResource> downloadFile(@RequestBody JSONObject jsonObject) {
        try {
            String folderName = jsonObject.getString("folderName");
            String fileName = jsonObject.getString("fileName");
            ResponseEntity<FileSystemResource> result = fileService.downloadFile(folderName, fileName);
            return result;
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @PostMapping("/preview")
    public ResponseEntity<FileSystemResource> previewSinglePDF(@RequestBody JSONObject jsonObject) {
        try {
            String folderName = jsonObject.getString("folderName");
            String fileName = jsonObject.getString("fileName");
            return fileService.preview(folderName, fileName);
        } catch (Exception e) {
            return null;
        }
    }
}
