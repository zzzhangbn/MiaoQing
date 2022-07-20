package com.ricemonitoring.controller;


import com.ricemonitoring.mapper.RiceCheckPointPicMapper;
import com.ricemonitoring.util.UploadPictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;


@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class RiceCheckPointPicController {

   @Autowired
    RiceCheckPointPicMapper riceCheckPointPicMapper;



    @PostMapping("/addCKPicInfor")
    public void addCKPicInfor(@RequestParam("country") String country,
                             @RequestParam("picUrl") String picUrl,
                             @RequestParam("description") String description,
                             @RequestParam("code") String code) {
        riceCheckPointPicMapper.addCKPicInfor( country,picUrl,description, code);
    }

    /*
    文件的上传功能
     */
    @RequestMapping("/uploadCKPic")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        return UploadPictureUtil.getPicUrl( file,  request);
    }

}
