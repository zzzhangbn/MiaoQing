package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.UserReportMapper;
import com.ricemonitoring.pojo.login_record;
import com.ricemonitoring.service.UserReportService;
import com.ricemonitoring.util.SplitString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    UserReportMapper userReportMapper;

   /* @Override
    public Map<String, Object> getAllRecord(Integer currentpage, Integer pagesize, String account, String logintime, String status) {
        Map<String,Object> map = new HashMap<>();
        currentpage = (currentpage - 1) * pagesize;
        List<login_record> Pages = userReportMapper.getAllRecord(currentpage , pagesize,account, logintime, status);
        int count = userReportMapper.selectCount(account, logintime, status);
        map.put("data",Pages);
        map.put("total",count);
        int i = currentpage % 10;
        map.put("currentpage", i);
        map.put("pagesize", pagesize);
        return map;
    }*/


    @Override
    public Map<String, Object> getAllRecord(Integer currentpage, Integer pagesize, String time1, String time2, login_record record) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> records = new HashMap<>();
        currentpage = (currentpage - 1) * pagesize;
        records.put("account", record.getAccount());
        records.put("time1",time1);
        records.put("time2",time2);
        records.put("loginip", record.getLoginip());
        String str = record.getLoginname();
        String loginname = SplitString.setSplitString(str);
        //System.out.println(loginname);
        records.put("loginname", loginname);
        records.put("status", record.getStatus());
        records.put("currentpage", currentpage);
        records.put("pagesize",pagesize);
        /*List<login_record> Pages = userReportMapper.getAllRecord(currentpage , pagesize, record);*/
        List<login_record> Pages = userReportMapper.getAllRecord(records);
        int count = userReportMapper.selectCount(records);
        map.put("data",Pages);
        map.put("total",count);
        int i = currentpage % 10;
        map.put("currentpage", i);
        map.put("pagesize", pagesize);
        return map;
    }
}
