package com.ricemonitoring.controller;

import com.ricemonitoring.pojo.research_report;
import com.ricemonitoring.service.researchReportService;
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

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 15:22
 */
@RestController
//解决跨域问题
@CrossOrigin
public class researchReportController {

    @Autowired
    researchReportService researchReportService;
    //研究报告上传功能
    @PostMapping("/upload")
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
            researchReportService.upload(realName,fileName,crop,userId);
        }
    }

    @GetMapping("/getFiles")
    public List<research_report> getFiles(String crop, String year,String account,String code_name){
//        System.out.println(code_name);
//        System.out.println(year);
//        System.out.println(account);
        researchReportService.getaccount(code_name);
//        System.out.println(researchReportService.getaccount(code_name));
        return researchReportService.getFiles(crop,year,account,code_name);
    }
    /*
    将html代码转化为word
     */
    @PostMapping("/article/htmlFormat")
    public Map<String,Object> html(String html) throws Exception {
        return researchReportService.formatHtmlStyle(html);
    }

    @GetMapping("/yizaiqiTemplate")
    public Map<String,Object> yizaiqiTemplate(){
        Map<String,Object> map = new HashMap<>();
        try {
//            map = researchReportService.yizaiqiTemplate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping("/template")
    public Map<String,Object> template(String templateName) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ParseException {
        Map<String,Object> map = new HashMap<>();
        switch (templateName){
            case "一季稻栽播期期苗情":
                map = researchReportService.yizaiqiTemplate();
                break;
            case "一季稻分孽期苗情":
                map = researchReportService.fenLieQiTemplate();
                break;
            case "一季稻抽穗期苗情-1":
                map = researchReportService.chouHuiQiTemplate_1();
                break;
            case "一季稻抽穗期苗情-2":
                map = researchReportService.chouHuiQiTemplate_2();
                break;
            case "一季稻抽穗期苗情-3":
                map = researchReportService.chouHuiQiTemplate_3();
                break;
            case "一季稻抽穗－灌浆期苗情":
                map = researchReportService.guanJiangQiTemplate();
                break;
            case "一季稻预测产情况":
                map = researchReportService.yuCeChanTemplate();
                break;
        }
        return map;
    }

    /*
    双季稻的模板
     */
    @GetMapping("/template_double")
    public Map<String,Object> template_double(String templateName,String account) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ParseException {
        Map<String,Object> map = new HashMap<>();
        switch (templateName){
            case "早稻播栽期苗情":
                map = researchReportService.zaodao_bozaiqiTemplate(account);
                break;
            case "早稻分蘖期苗情":
                map = researchReportService.zaodao_fenLieQiTemplate(account);
                break;
            case "早稻分蘖－孕穗初期苗情":
                map = researchReportService.zaodao_yunHuiQiTemplate(account);
                break;
            case "早稻孕穗－抽穗期苗情":
                map = researchReportService.zaodao_chouHuiQiTemplate(account);
                break;
            case "早稻预测产结果":
                map = researchReportService.zaodao_yuChanTemplate(account);
                break;
            case "双季晚稻分蘖期苗情":
                map = researchReportService.wandao_fenleiqiTemplate(account);
                break;
            case "双季晚稻分蘖－孕穗期苗情":
                map = researchReportService.wandao_yunhuiTemplate(account);
                break;
            case "双季晚稻灌浆期预估产":
                map = researchReportService.wandao_yuGuChanTemplate(account);
                break;
        }
        return map;
    }


    /*
    存储报告，并将报告html存储到数据库
     */
    @PostMapping("/saveReport")
    public void saveReport(@RequestParam("html") String html,
                           @RequestParam("crop")String crop,
                           @RequestParam("fileName")String fileName,
                           @RequestParam("userId")String userId){
        Map<String,Object> map = new HashMap<>();
        map.put("html",html);
        map.put("crop",crop);
        map.put("fileName",fileName);
        map.put("userId",userId);
        researchReportService.saveReport(map);
    }

}
