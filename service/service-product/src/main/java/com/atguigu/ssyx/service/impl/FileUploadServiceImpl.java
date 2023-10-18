package com.atguigu.ssyx.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atguigu.ssyx.service.FileUploadService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author user
 * @date 2023/10/18
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${aliyun.endpoint}")
    private String endpoint;

    @Value("${aliyun.keyid}")
    private String accessKeyId;

    @Value("${aliyun.keysecret}")
    private String secretKey;

    @Value("${aliyun.bucketname}")
    private String bucketName;

    @Override
    public String fileUpload(MultipartFile file) {


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, secretKey);

        try {
            InputStream inputStream = file.getInputStream();
            String objectName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String currentDateTime = new DateTime().toString("yyyy/MM/dd");
            String newFileName = currentDateTime + "/" + uuid + objectName;

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFileName, inputStream);
            // 创建PutObject请求。
            ossClient.putObject(putObjectRequest);
            ossClient.shutdown();
            //上传之后文件路径
            // https://ssyx-atguigu.oss-cn-beijing.aliyuncs.com/01.jpg
            //返回
            return "https://" + bucketName + "." + endpoint + "/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
