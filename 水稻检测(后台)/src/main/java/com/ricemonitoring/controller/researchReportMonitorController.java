package com.ricemonitoring.controller;


import com.ricemonitoring.pojo.research_report;
import com.ricemonitoring.service.researchReportService;
import com.ricemonitoring.service.researchReportMonitorService;
import com.ricemonitoring.util.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class researchReportMonitorController {

    @Autowired
    researchReportMonitorService researchReportMonitorService;
    //研究报告上传功能
    @PostMapping("/upload1")
    public void upload(@RequestParam("files") MultipartFile[] files,
                       @RequestParam("crop")String crop,
                       @RequestParam("fileName")String fileName,
                       @RequestParam("userId")String userId) throws Exception {
        for (MultipartFile file : files) {
            //1.调用ftp工具类将图片上传到服务器
            byte[] bytes = file.getBytes();
            String realName = new Date().getTime()+"--"+file.getOriginalFilename();
            FTPUtil.sshSftp(bytes,realName);
            //2.调用service完成将数据存储到数据库中
            researchReportMonitorService.upload(realName,fileName,crop,userId);
        }
    }

    @GetMapping("/getFiles1")
    public List<research_report> getFiles(String crop, String year,String people){
        return researchReportMonitorService.getFiles(crop,year,people);
    }

    /*
    将html代码转化为word
     */
    @PostMapping("/article/htmlFormat1")
    public Map<String,Object> html(String html) throws Exception {
        return researchReportMonitorService.formatHtmlStyle(html);
    }

    @GetMapping("/yizaiqiTemplate1")
    public Map<String,Object> yizaiqiTemplate(){
        Map<String,Object> map = new HashMap<>();
        try {
//            map = researchReportService.yizaiqiTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping("/template1")
    public Map<String,Object> template(String templateName,String country) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ParseException {
        Map<String,Object> map = new HashMap<>();
        System.out.println(country);
        switch (templateName){
            case "一季稻栽播期期苗情":
                map = researchReportMonitorService.yiZaiQiTemplate(country);
                break;
            case "一季稻分孽期苗情":
                map = researchReportMonitorService.fenLieQiTemplate(country);
                break;
            case "一季稻抽穗期苗情-1":
                map = researchReportMonitorService.chouHuiQiTemplate_1(country);
                break;
            case "一季稻抽穗期苗情-2":
                map = researchReportMonitorService.chouHuiQiTemplate_2(country);
                break;
            case "一季稻抽穗期苗情-3":
                map = researchReportMonitorService.chouHuiQiTemplate_3(country);
                break;
            case "一季稻抽穗－灌浆期苗情":
                map = researchReportMonitorService.guanJiangQiTemplate(country);
                break;
            case "一季稻预测产情况":
                map = researchReportMonitorService.yuCeChanTemplate(country);
                break;
        }
        return map;
    }
    @GetMapping("/templateTwo")
    public Map<String,Object> templateTwo(String templateTwoName,String country) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ParseException {
        Map<String, Object> map = new HashMap<>();
//        System.out.println(country);
//        String account = "1702202101";
//        String templateTwoName="双季稻早稻测产结果";
        switch (templateTwoName) {
            case "双季稻早稻测产结果":
                map = researchReportMonitorService.ceChanTemplateTwoZao(country);
                break;
            case "双季稻晚稻测产结果":
                map = researchReportMonitorService.ceChanTemplateTwoWan(country);
                break;
            case "全省双季稻早稻测产结果":
                map = researchReportMonitorService.ceChanTemplateTwoZao_Sheng();
                break;
            case "全省双季稻晚稻测产结果":
                map = researchReportMonitorService.ceChanTemplateTwoWan_Sheng();
                break;
        }
        return map;
    }
    /*
    存储报告，并将报告html存储到数据库
     */
    @PostMapping("/saveReport1")
    public void saveReport(@RequestParam("html") String html,
                           @RequestParam("crop")String crop,
                           @RequestParam("fileName")String fileName,
                           @RequestParam("userId")String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("html",html);
        map.put("crop",crop);
        map.put("fileName",fileName);
        map.put("userId",userId);
        researchReportMonitorService.saveReport(map);
    }

}
