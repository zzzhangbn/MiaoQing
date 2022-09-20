package com.ricemonitoring.controller;

import com.ricemonitoring.pojo.login_record;
import com.ricemonitoring.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//解决跨域问题
@CrossOrigin

public class UserReportController {

    @Autowired
    UserReportService userRecordService;

    @PostMapping("/getAllRecord")
    /*public Map<String,Object> getAllRecord(@RequestParam("currentpage") Integer currentpage,
                                           @RequestParam("pagesize") Integer pagesize,
                                           @RequestParam("account") String account,
                                           @RequestParam("logintime") String logintime,
                                           @RequestParam("status") String status){
        return userRecordService.getAllRecord(currentpage,pagesize, account, logintime,status);
    }*/
    public Map<String,Object> getAllRecord(@RequestParam("currentpage") Integer currentpage,
                                           @RequestParam("pagesize") Integer pagesize,
                                           @RequestParam("time1") String time1,
                                           @RequestParam("time2") String time2,
                                           @RequestBody login_record record){
        //System.out.println(record);
        return userRecordService.getAllRecord(currentpage,pagesize,time1,time2,record);
    }
}
