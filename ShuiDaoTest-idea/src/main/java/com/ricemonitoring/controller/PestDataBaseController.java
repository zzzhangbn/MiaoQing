package com.ricemonitoring.controller;


import com.ricemonitoring.mapper.PestDataBaseMapper;
import com.ricemonitoring.util.FTPUtil;
import com.ricemonitoring.util.UploadPictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class PestDataBaseController {

   @Autowired
   PestDataBaseMapper pestDataBaseMapper;

    @PostMapping("/addPestInfor")
    public void addPestInfor(@RequestParam("picUrl") String picUrl,
                             @RequestParam("performance") String performance,
                             @RequestParam("pestName") String pestName,
                             @RequestParam("drugName") String drugName) {
        pestDataBaseMapper.addPestInfor( picUrl,pestName,performance, drugName);
    }

    /*
    文件的上传功能
     */
    @RequestMapping("/uploadPestPic")
    public Map<String,Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {

      return UploadPictureUtil.getPicUrl(file,request);

    }

}
