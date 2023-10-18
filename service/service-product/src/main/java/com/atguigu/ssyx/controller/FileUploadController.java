package com.atguigu.ssyx.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.service.FileUploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author user
 * @date 2023/10/18
 */
@Api(tags = "文件上传接口")
@CrossOrigin
@RestController
@RequestMapping("/admin/product")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    //文件上传
    @PostMapping("fileUpload")
    public Result<String> fileUpload(MultipartFile file) {
        return Result.ok(fileUploadService.fileUpload(file));
    }
}
