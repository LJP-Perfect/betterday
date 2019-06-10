package me.freelee.betterday.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * Aliyun对象存储
 * Date:2019/1/14
 *
 * @author:Lee
 */
public class AliyunOSSClientUtil {
    //log日志
    private static Logger logger = LoggerFactory.getLogger(AliyunOSSClientUtil.class);
    //阿里云API的内或外网域名
    private static String ENDPOINT="";
    //阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID="";
    //阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET="";
    //阿里云API的bucket名称
    private static String BACKET_NAME="";
    private static String PROTOCOL="";


    /**
     * 获取阿里云OSS客户端对象
     * @return ossClient
     */
    public static  OSSClient getOSSClient(){
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }

    /**
     * 创建存储空间
     * @param ossClient      OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public  static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames=bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     * @param ossClient  oss对象
     * @param bucketName  存储空间
     */
    public static  void deleteBucket(OSSClient ossClient, String bucketName){
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     * @param ossClient oss连接
     * @param bucketName 存储空间
     * @param folder   模拟文件夹名如"qj_nanjing/"
     * @return  文件夹名
     */
    public  static String createFolder(OSSClient ossClient,String bucketName,String folder){
        //文件夹名
        final String keySuffixWithSlash =folder;
        //判断文件夹是否存在，不存在则创建
        if(!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)){
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir=object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     * @param ossClient  oss连接
     * @param bucketName  存储空间
     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 上传图片至OSS
     * @return String 返回的唯一MD5数字签名
     * */
    public static  String uploadObject2OSS( InputStream in, String folderName,String fileName) {
        OSSClient ossClient=AliyunOSSClientUtil.getOSSClient();
        String resultStr = null;
        try {

            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(in.available());
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
            metadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(BACKET_NAME, folderName +"/"+ fileName, in, metadata);
            //解析结果
            resultStr = "http://"+BACKET_NAME+"."+ENDPOINT+"/"+folderName+"/"+ DateUtil.getCurrentTime().getTime()+"-"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName){
        try {
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
        }catch (Exception e){
            logger.warn("没有后缀名");
        }
        //默认返回类型
        return "image/jpeg";
    }

    public static String GenerateImage(String imgStr, String fileName) {
        imgStr=imgStr.substring(imgStr.indexOf(",")+1);
        logger.info("imgStr:"+imgStr);
        // 文件字节数组字符串数据为空
        if (imgStr == null)
            return null;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                {// 调整异常数据
                    if (b[i] < 0)
                        b[i] += 256;
                }
            }
            InputStream in= new ByteArrayInputStream(b);
            return AliyunOSSClientUtil.uploadObject2OSS(in,"product",fileName);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> getBucketObjectsByPrefix(String prefix){
        OSSClient ossClient=AliyunOSSClientUtil.getOSSClient();
        ObjectListing objectListing=ossClient.listObjects(BACKET_NAME,"habit/icon/");
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        List<String> urls=new ArrayList<>();
        for (int i=1;i<sums.size();i++) {
            urls.add(PROTOCOL+BACKET_NAME+"."+ENDPOINT+"/"+sums.get(i).getKey());
        }
        for(String url:urls){
            logger.info(url);
        }
        return urls;
    }

    //测试
    public static void main(String[] args) throws FileNotFoundException {
        //初始化OSSClient
        //上传文件
        getBucketObjectsByPrefix("habit/icon/");

    }


}
