package com.ricemonitoring.util;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UploadPictureUtil {
    private static Map<String,Object> map =new HashMap<>();
    public static Map<String,Object> getPicUrl(@RequestParam() MultipartFile file, HttpServletRequest request) throws Exception {

        //1.判断前台传过来的数据是否为图片
        if (!(file.getContentType().endsWith("jpg")||file.getContentType().endsWith("png"))){
            //传来的数据格式错误
            map.put("token",1);
            map.put("msg","你输入的不是一张图片，请重新输入");
            return map;
        }
        //2.调用ftp工具类将图片上传到服务器
        byte[] bytes = file.getBytes();
        String realName = new Date().getTime()+"--"+file.getOriginalFilename();
        FTPUtil.sshSftp(bytes,realName);
//            3.将图片的地址存入数据库
//            3.1生成图片的连接地址
        String baseUrl = "http://139.196.208.205:8080/img/";
        String realUrl = baseUrl+realName;
        //3.3设置返回信息
        map.put("token",0);
        map.put("msg","上传成功");
        map.put("name",file.getOriginalFilename());
        map.put("url",realUrl);

        return map;
    }
}
