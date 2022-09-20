package com.ricemonitoring.controller;

import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.ricemonitoring.pojo.person_people;
import com.ricemonitoring.pojo.picture;
import com.ricemonitoring.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)

public class UserdataController {

    @Autowired
    UserDataService userDataService;

    @PostMapping("/Userdata")
    public Map<String, Object> getData(@RequestBody person_people people){
        return userDataService.getData(people);
    }
    @Value("${file.upload-path}")
    private String pictureurl;

    //    单文件上传
    @RequestMapping("/UpdateImage")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("name") String name) {
        return userDataService.upload(file, name);
    }
    @GetMapping("/getImage")
    public String getImage(@RequestParam("account") String account){
        return userDataService.getImage(account);
    }


}

