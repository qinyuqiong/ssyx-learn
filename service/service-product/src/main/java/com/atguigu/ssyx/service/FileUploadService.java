package com.atguigu.ssyx.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author user
 * @date 2023/10/18
 */
public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
