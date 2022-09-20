package com.ricemonitoring.service;


import com.ricemonitoring.pojo.login_record;

import java.util.Map;

public interface UserReportService {

    //Map<String,Object> getAllRecord(Integer currentpage, Integer pagesize, String account, String logintime, String status);

    Map<String, Object> getAllRecord(Integer currentpage, Integer pagesize, String time1, String time2,login_record record);
}
