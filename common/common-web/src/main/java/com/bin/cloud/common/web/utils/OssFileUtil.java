package com.bin.cloud.common.web.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.bin.cloud.common.core.entity.dto.ImgDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * @Description TODO
 * @Author hubin
 * @Date 2019-09-17 14:37
 * @Version 1.0
 **/
@Component
@Slf4j
public class OssFileUtil {

    private static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";

//    private static final String ACCESS_KEY_ID = "";

    private static final String ACCESS_KEY_ID = "";

//    private static final String ACCESS_KEY_SECRET = "";

    private static final String ACCESS_KEY_SECRET = "";

    // bucket 名称
    private static final String BUCKET_NAME = "img-services";
    // 文件夹名称
    private static final String FOLDER = "jcc-business/";
    // 图片样式
    private static final String IMG_STYLE = "style/img_style";

    /**
     * 创建存储空间
     * @param ossClient      OSS连接
     * @return
     */
    private static String createBucketName(OSS ossClient){
        //存储空间
        final String bucketNames=BUCKET_NAME;
        if(!ossClient.doesBucketExist(BUCKET_NAME)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(BUCKET_NAME);
            log.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    private static OSS ossBuilder(){
        return new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 基础上传
     * @param file
     * @return
     */
    public static ImgDTO baseImgUpload(MultipartFile file){
        // 创建OSSClient实例。
        OSS ossClient = null;
        ImgDTO imgDTO = null;
        try {
            ossClient = ossBuilder();
            Long fileSize = file.getSize();
            String fileName = file.getOriginalFilename();
            String filePath = FOLDER + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileSize);
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");

            PutObjectResult request = ossClient.putObject(
                    createBucketName(ossClient),
                    filePath,
                    file.getInputStream(),metadata);
            // 设置URL过期时间为10年  3600l* 1000*24*365*10
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            // 生成URL
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(BUCKET_NAME, filePath, HttpMethod.GET);
            req.setExpiration(expiration);
            req.setProcess(IMG_STYLE);
            URL url = ossClient.generatePresignedUrl(req);
            String responseMsg = request.getETag();
            imgDTO = new ImgDTO(url.toString(), responseMsg);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }finally {
            if (Objects.nonNull(ossClient)) {
                ossClient.shutdown();
            }
            return imgDTO;
        }
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    private static  String getContentType(String fileName) {
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension) || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }

}
