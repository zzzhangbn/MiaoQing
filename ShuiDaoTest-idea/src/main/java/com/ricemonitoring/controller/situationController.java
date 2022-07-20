package com.ricemonitoring.controller;

import com.ricemonitoring.mapper.SituationMapper;
import com.ricemonitoring.pojo.*;

import com.ricemonitoring.service.SituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class situationController {
    @Autowired
    SituationService situationService;
    @Autowired
    SituationMapper situationMapper;


    //查询该用户下的所有表1-2数据
    @PostMapping(value = "/getUserAllData")
    public List<crop_01_01_table_01_02> getUserAllData(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("flag")int flag){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationService.getUserAllData(people,column_id,flag,currentYear);
    }

    //表3-2数据对比可视化分析
    @GetMapping("/dataComparison_03_02")
    public Map<String,Object> dataComparison_03_02(String userId, int startYear, int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_03_02(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //表3-3数据对比可视化分析
    @GetMapping("/dataComparison_03_03")
    public Map<String,Object> dataComparison_03_03(String userId,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_03_03(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻：表3-3数据对比可视化分析
    @GetMapping("/dataComparison_two_03_03")
    public Map<String,Object> dataComparison_two_03_03(String userId,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_03_03(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻：表3-4数据对比可视化分析
    @GetMapping("/dataComparison_two_03_04")
    public Map<String,Object> dataComparison_two_03_04(String userId,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_03_04(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻：表3-5数据对比可视化分析
    @GetMapping("/dataComparison_two_03_05")
    public Map<String,Object> dataComparison_two_03_05(String userId,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_03_05(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻：表3-6数据对比可视化分析
    @GetMapping("/dataComparison_two_03_06")
    public Map<String,Object> dataComparison_two_03_06(String userId,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_03_06(userId,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
    //表4-1数据对比可视化分析
    @GetMapping("/dataComparison_04_01")
    public Map<String,Object> dataComparison_04_01(String userId,int startYear,int endYear,String point){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_04_01(userId,startYear,endYear,point);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻 ： 表4-1数据对比可视化分析
    @GetMapping("/dataComparison_two_04_01")
    public Map<String,Object> dataComparison_two_04_01(String userId,int startYear,int endYear,String point){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_04_01(userId,startYear,endYear,point);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //表4-2数据对比可视化分析
    @GetMapping("/dataComparison_04_02")
    public Map<String,Object> dataComparison_04_02(String userId,int startYear,int endYear,String point){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_04_02(userId,startYear,endYear,point);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //二季稻：表4-2数据对比可视化分析
    @GetMapping("/dataComparison_two_04_02")
    public Map<String,Object> dataComparison_two_04_02(String userId,int startYear,int endYear,String point){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_two_04_02(userId,startYear,endYear,point);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
    //表10-1数据对比可视化分析
    @GetMapping("/dataComparison_10_01")
    public Map<String,Object> dataComparison_10_01(String userId,String point,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataComparison_10_01(userId,point,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
    //表10-1数据对比可视化分析
    @GetMapping("/dataTwoComparison_10_01")
    public Map<String,Object> dataTwoComparison_10_01(String userId,String point,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataTwoComparison_10_01(userId,point,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
    //表10-1数据对比可视化分析
    @GetMapping("/dataTwoComparison_10_02")
    public Map<String,Object> dataTwoComparison_10_02(String userId,String point,int startYear,int endYear){
        Map<String,Object> map = new HashMap<>();
        try {
            map = situationService.dataTwoComparison_10_02(userId,point,startYear,endYear);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取品种类型及品种名
    @PostMapping(value = "/getPinZhongInfo")
    public List<table_variety> getPinZhongInfo(@RequestParam("varieties")String varieties){
        return situationService.getPinZhongInfo(varieties);
    }

    //查询该用户下的所有表1-1数据
    @PostMapping(value = "/getUserAllData01")
    public List<crop_01_01_table_01_01> getUserAllData01(@RequestParam("people")String people){
        return situationService.getUserAllData01(people);
    }

    //查询该用户下所有表2-2数据
    @PostMapping(value = "/getUserAllData0202")
    public List<crop_01_01_table_02_02> getUserAllData0202(@RequestParam("people")String people){
        return situationService.getUserAllData0202(people);
    }

    //查询该用户下表2-1数据
    @GetMapping(value = "/getUserAllData0201")
    public List<crop_01_01_table_02_01> getUserAllData0201(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0201(people,column_id,startYear,endYear);
    }

    //查询该用户下表2-1数据
    @GetMapping(value = "/getUserAllDataTwo0201")
    public List<crop_01_02_table_02_01> getUserAllDataTwo0201(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0201(people,column_id,startYear,endYear);
    }

    //查询该用户下表3-1数据
    @PostMapping(value = "/getUserAllData0301")
    public List<crop_01_01_table_03_01> getUserAllData0301(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0301(people,column_id,startYear,endYear);
    }

    //将建档卡中的已知内容插入到表3-1中
    @PostMapping(value = "/getMonitorCardContentInto0301")
    public int getMonitorCardContentInto0301(@RequestParam("people")String people){
        return situationService.getMonitorCardContentInto0301(people);
    }

    //将建档卡中的已知内容插入到表3-2中
    @PostMapping(value = "/getMonitorCardContentInto0302")
    public int getMonitorCardContentInto0302(@RequestParam("people")String people){
        return situationService.getMonitorCardContentInto0302(people);
    }

    //将建档卡中的已知内容插入到表3-3中
    @PostMapping(value = "/getMonitorCardContentInto0303")
    public int getMonitorCardContentInto0303(@RequestParam("people")String people){
        return situationService.getMonitorCardContentInto0303(people);
    }

    //查询该用户下表3-2数据
    @PostMapping(value = "/getUserAllData0302")
    public List<crop_01_01_table_03_02> getUserAllData0302(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0302(people,column_id,startYear,endYear);
    }

    //查询该用户下表3-3数据
    @PostMapping(value = "/getUserAllData0303")
    public List<crop_01_01_table_03_03> getUserAllData0303(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0  && endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0303(people,column_id,startYear,endYear);
    }

    //将已知内容插入表4-1
    @PostMapping(value = "/insertMonitorInfoToTable0401")
    public int insertMonitorInfoToTable0401(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容更新入表4-1
    @PostMapping(value = "/updateMonitorInfoToTable0401")
    public int updateMonitorInfoToTable0401(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容插入表4-2
    @PostMapping(value = "/insertMonitorInfoToTable0402")
    public int insertMonitorInfoToTable0402(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容更新入表4-2
    @PostMapping(value = "/updateMonitorInfoToTable0402")
    public int updateMonitorInfoToTable0402(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容插入表4-3
    @PostMapping(value = "/insertMonitorInfoToTable0403")
    public int insertMonitorInfoToTable0403(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("jiBenMiao")String jiBenMiao,@RequestParam("people")String people
            ,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
    }

    //将已知内容更新入表4-3
    @PostMapping(value = "/updateMonitorInfoToTable0403")
    public int updateMonitorInfoToTable0403(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("jiBenMiao")String jiBenMiao,@RequestParam("people")String people
            ,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
    }

    //将已知内容插入表4-4
    @PostMapping(value = "/insertMonitorInfoToTable0404")
    public int insertMonitorInfoToTable0404(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容更新入表4-4
    @PostMapping(value = "/updateMonitorInfoToTable0404")
    public int updateMonitorInfoToTable0404(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表5-1
    @PostMapping(value = "/insertMonitorInfoToTable0501")
    public int insertMonitorInfoToTable0501(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表5-1
    @PostMapping(value = "/updateMonitorInfoToTable0501")
    public int updateMonitorInfoToTable0501(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表6-1
    @PostMapping(value = "/insertMonitorInfoToTable0601")
    public int insertMonitorInfoToTable0601(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表6-1
    @PostMapping(value = "/updateMonitorInfoToTable0601")
    public int updateMonitorInfoToTable0601(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表6-2
    @PostMapping(value = "/insertMonitorInfoToTable0602")
    public int insertMonitorInfoToTable0602(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表6-2
    @PostMapping(value = "/updateMonitorInfoToTable0602")
    public int updateMonitorInfoToTable0602(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表6-3
    @PostMapping(value = "/insertMonitorInfoToTable0603")
    public int insertMonitorInfoToTable0603(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表6-3
    @PostMapping(value = "/updateMonitorInfoToTable0603")
    public int updateMonitorInfoToTable0603(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表6-4
    @PostMapping(value = "/insertMonitorInfoToTable0604")
    public int insertMonitorInfoToTable0604(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表6-4
    @PostMapping(value = "/updateMonitorInfoToTable0604")
    public int updateMonitorInfoToTable0604(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息插入表6-5
    @PostMapping(value = "/insertMonitorInfoToTable0605")
    public int insertMonitorInfoToTable0605(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //将监测点信息更新入表6-5
    @PostMapping(value = "/updateMonitorInfoToTable0605")
    public int updateMonitorInfoToTable0605(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("daoZuoType")String daoZuoType,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //查询该用户下表4-1数据
    @PostMapping(value = "/getUserAllData0401")
    public List<crop_01_01_table_04_01> getUserAllData0401(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                           @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0401(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户下表4-2数据
    @PostMapping(value = "/getUserAllData0402")
    public List<crop_01_01_table_04_02> getUserAllData0402(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                           @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0402(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户下表4-3数据
    @PostMapping(value = "/getUserAllData0403")
    public List<crop_01_01_table_04_03> getUserAllData0403(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                           @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0403(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户下表4-4数据
    @PostMapping(value = "/getUserAllData0404")
    public List<crop_01_01_table_04_04> getUserAllData0404(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("point")String point,
                                                           @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0404(people,column_id,point,startYear,endYear);
    }

    //查询该用户下表8-1数据
    @PostMapping(value = "/getUserAllData0801")
    public List<crop_01_01_table_08_01> getUserAllData0801(@RequestParam("people")String people,
                                                           @RequestParam("point")String point,@RequestParam("column_id")String column_id,
                                                           @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllData0801(people,point,column_id,startYear,endYear);
    }

    //获取下拉框内容getSelectContent
    @PostMapping(value = "/getSelectContent")
    public List<sum_select_list> getSelectContentList(@RequestParam("fieldName")String fieldName,
                                                      @RequestParam("riceType")String riceType){
        return situationService.getSelectContentList(fieldName,riceType);
    }

    //保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    @PostMapping(value = "/getSituationInputDataTable1")
    public int getInputDataTable1(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_01_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_01_01 crop = JSONObject.parseObject(s ,  crop_01_01_table_01_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable1(crops);
        return res;
    }

    //查询表1-1 下该用户所有的数据数
    @PostMapping(value = "/checkUserAllKnownData1")
    public List<crop_01_01_table_01_01> checkUserAllKnownData1(@RequestParam("UserID")String UserID,@RequestParam("column_id")String column_id,@RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.checkUserAllKnownData1(UserID,column_id,startYear,endYear);
    }

    //保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表
    @PostMapping(value = "/getSituationInputDataTable2")
    public int getInputDataTable2(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_01_02> crops = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for (String s : dataList){
            crop_01_01_table_01_02 crop = JSONObject.parseObject(s ,  crop_01_01_table_01_02.class);
            System.out.println(crop);
            System.out.println(situationService.getByPinZhongName(crop.getPeople(),crop.getPin_zhong_name(),crop.getColumn_id(),0,currentYear));
            if(situationService.getByPinZhongName(crop.getPeople(),crop.getPin_zhong_name(),crop.getColumn_id(),0,currentYear).isEmpty()){
//                System.out.println("没有记录");

                situationService.getInputDataTable2(crop);
            }
            crops.add(crop);
        }
//        int res = situationService.getInputDataTable2(crops);
        return 1;
    }

    //增加品系信息
    @PostMapping(value = "/addNewPinXiInfo")
    public int addNewPinXiInfo(@RequestParam("varieties")String varieties,@RequestParam("variety_name")String variety_name){
        return situationMapper.addNewPinXiInfo(varieties,variety_name);
    }

    //读取表2-1建档卡内容至表2-2中
    @PostMapping(value = "/getMonitorCardContent")
    public int  getMonitorCardContent(@RequestParam("people")String people){
        return situationService.getMonitorCardContent(people);
    }

    //获取监测点代码
    @PostMapping(value = "/getMonitorCode")
    public List<monitoring_point> getMonitorCode(@RequestParam("UserID")String UserID){
        return situationService.getMonitorCode(UserID);
    }

    //保存 表2-1 2021年度水稻监测点建档卡
    @PostMapping(value = "/getSituationInputDataTable3")
    public int getInputDataTable3(@RequestParam("data")String data){
        crop_01_01_table_02_01 obj1 = JSONObject.parseObject(data , crop_01_01_table_02_01.class);
        return situationService.getInputDataTable3(obj1);
    }

    //保存 表3-1
    @PostMapping(value = "/getSituationInputDataTable5")
    public int getInputDataTable5(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_03_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_03_01 crop = JSONObject.parseObject(s ,  crop_01_01_table_03_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable5(crops);
        return res;
    }

    //保存 表3-2
    @PostMapping(value = "/getSituationInputDataTable0302")
    public int getInputDataTable0302(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_03_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_03_02 crop = JSONObject.parseObject(s ,  crop_01_01_table_03_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0302(crops);
        return res;
    }

    //保存 表3-3
    @PostMapping(value = "/getSituationInputDataTable0303")
    public int getInputDataTable0303(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_03_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_03_03 crop = JSONObject.parseObject(s ,  crop_01_01_table_03_03.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0303(crops);
        return res;
    }

    //保存 表4-1
    @PostMapping(value = "/getSituationInputDataTable0401")
    public int getInputDataTable0401(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_04_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_04_01 crop = JSONObject.parseObject(s ,  crop_01_01_table_04_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0401(crops);
        return res;
    }

    //保存 表4-2
    @PostMapping(value = "/getSituationInputDataTable0402")
    public int getInputDataTable0402(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_04_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_04_02 crop = JSONObject.parseObject(s ,  crop_01_01_table_04_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0402(crops);
        return res;
    }

    //保存 表4-3
    @PostMapping(value = "/getSituationInputDataTable0403")
    public int getInputDataTable0403(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_04_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_04_03 crop = JSONObject.parseObject(s ,  crop_01_01_table_04_03.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0403(crops);
        return res;
    }

    //保存 表4-4
    @PostMapping(value = "/getSituationInputDataTable0404")
    public int getInputDataTable0404(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_04_04> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_04_04 crop = JSONObject.parseObject(s ,  crop_01_01_table_04_04.class);
            System.out.println(crop);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0404(crops);
        return res;
    }

    //保存 表8-1
    @PostMapping(value = "/getSituationInputDataTable0801")
    public int getInputDataTable0801(@RequestParam("data")String data){
        System.out.println(data);
        System.out.println("-----------------------------------");
//        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_08_01> crops = new ArrayList<>();
        System.out.println(crops);

//        for (String s : dataList){
//            crop_01_01_table_08_01 crop = JSONObject.parseObject(s ,  crop_01_01_table_08_01.class);
//            System.out.println(crop.getIn_use());
//            if(crop.getIn_use().equals('0')){
//                int res = situationService.getInputDataTable0801(crop);
//                crops.add(crop);
//            }
//        }
        crop_01_01_table_08_01 crop = JSONObject.parseObject(data ,  crop_01_01_table_08_01.class);
        System.out.println(crop.getIn_use());
//        if(crop.getIn_use().equals('0')){
            int res = situationService.getInputDataTable0801(crop);
            crops.add(crop);
//        }
        return 1;
    }

    //保存监测点建档卡数据至表1-1中
    @PostMapping(value = "/insertMonitorInfoToTable0101")
    public int insertMonitorInfoToTable0101(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0101(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表1-1中
    @PostMapping(value = "/updateMonitorInfoToTable0101")
    public int updateMonitorInfoToTable0101(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0101(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表1-2中
    @PostMapping(value = "/insertMonitorInfoToTable0102")
    public int insertMonitorInfoToTable0102(@RequestParam("area")String area,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0102(area,people,time);
    }


    //保存监测点建档卡数据至表3-1中
    @PostMapping(value = "/insertMonitorInfoToTable0301")
    public int insertMonitorInfoToTable0301(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0301(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表3-1中
    @PostMapping(value = "/updateMonitorInfoToTable0301")
    public int updateMonitorInfoToTable0301(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0301(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表3-2中
    @PostMapping(value = "/insertMonitorInfoToTable0302")
    public int insertMonitorInfoToTable0302(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0302(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表3-2中
    @PostMapping(value = "/updateMonitorInfoToTable0302")
    public int updateMonitorInfoToTable0302(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0302(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表3-3中
    @PostMapping(value = "/insertMonitorInfoToTable0303")
    public int insertMonitorInfoToTable0303(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0303(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表3-3中
    @PostMapping(value = "/updateMonitorInfoToTable0303")
    public int updateMonitorInfoToTable0303(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0303(area,areaCate,people,time);
    }


    //保存监测点建档卡数据至表7-1中
    @PostMapping(value = "/insertMonitorInfoToTable0701")
    public int insertMonitorInfoToTable0701(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0701(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表7-1中
    @PostMapping(value = "/updateMonitorInfoToTable0701")
    public int updateMonitorInfoToTable0701(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0701(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表7-2中
    @PostMapping(value = "/insertMonitorInfoToTable0702")
    public int insertMonitorInfoToTable0702(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0702(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表7-2中
    @PostMapping(value = "/updateMonitorInfoToTable0702")
    public int updateMonitorInfoToTable0702(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0702(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表7-3中
    @PostMapping(value = "/insertMonitorInfoToTable0703")
    public int insertMonitorInfoToTable0703(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0703(area,areaCate,people,time);
    }

    //更新监测点建档卡数据至表7-3中
    @PostMapping(value = "/updateMonitorInfoToTable0703")
    public int updateMonitorInfoToTable0703(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0703(area,areaCate,people,time);
    }

    //保存监测点建档卡数据至表9-3中
    @PostMapping(value = "/insertMonitorInfoToTable0903")
    public int insertMonitorInfoToTable0903(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0903(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-3中
    @PostMapping(value = "/updateMonitorInfoToTable0903")
    public int updateMonitorInfoToTable0903(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0903(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-4中
    @PostMapping(value = "/insertMonitorInfoToTable0904")
    public int insertMonitorInfoToTable0904(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0904(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-4中
    @PostMapping(value = "/updateMonitorInfoToTable0904")
    public int updateMonitorInfoToTable0904(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0904(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-5中
    @PostMapping(value = "/insertMonitorInfoToTable0905")
    public int insertMonitorInfoToTable0905(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0905(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //更新监测点建档卡数据至表9-5中
    @PostMapping(value = "/updateMonitorInfoToTable0905")
    public int updateMonitorInfoToTable0905(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0905(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-6中
    @PostMapping(value = "/insertMonitorInfoToTable0906")
    public int insertMonitorInfoToTable0906(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0906(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-6中
    @PostMapping(value = "/updateMonitorInfoToTable0906")
    public int updateMonitorInfoToTable0906(@RequestParam("area")String area,@RequestParam("diQuType")String diQuType,
                                            @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0906(area,diQuType,daoZuoType,pinXiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-7中
    @PostMapping(value = "/insertMonitorInfoToTable0907")
    public int insertMonitorInfoToTable0903(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0907(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,modification_time,people,currentYear);
    }

    //保存监测点建档卡数据至表9-7中
    @PostMapping(value = "/updateMonitorInfoToTable0907")
    public int updateMonitorInfoToTable0907(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("diQuType")String areaCate,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                            @RequestParam("modification_time")String modification_time,@RequestParam("people")String people){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0907(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,modification_time,people,currentYear);
    }

    //将已知内容插入表10-1
    @PostMapping(value = "/insertMonitorInfoToTable1001")
    public int insertMonitorInfoToTable1001(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){

        return situationService.insertMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time);
    }

    //将已知内容插入表10-1
    @PostMapping(value = "/updateMonitorInfoToTable1001")
    public int updateMonitorInfoToTable1001(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                            @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType, @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time);
    }

    //保存监测点建档卡数据至表8-1中
    @PostMapping(value = "/insertMonitorInfoToTable0801")
    public int insertMonitorInfoToTable0801(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,
                                            @RequestParam("areaCate")String areaCate,@RequestParam("zaiZhiType")String zaiZhiType,
                                            @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //更新监测点建档卡数据至表8-1中
    @PostMapping(value = "/updateMonitorInfoToTable0801")
    public int updateMonitorInfoToTable0801(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,
                                            @RequestParam("areaCate")String areaCate,@RequestParam("zaiZhiType")String zaiZhiType,
                                            @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //查询 表06-01 获取监测点代码
    @GetMapping("/getAllData0601")
    public List<crop_01_01_table_06_01> getAllData0601(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int startYear,
                                                       @RequestParam("endYear")int endYear){

        System.out.println(people);
        System.out.println("point:"+point);
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0601(people,point,column_id,startYear,endYear);
    }

    //查询 表06-02 获取监测点代码
    @GetMapping("/getAllData0602")
    public List<crop_01_01_table_06_02> getAllData0602(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,@RequestParam("column_id")String column_id,@RequestParam("beginYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0602(people,point,column_id,startYear,endYear);
    }

    //查询 表06-03 获取监测点代码
    @GetMapping("/getAllData0603")
    public List<crop_01_01_table_06_03> getAllData0603(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,@RequestParam("column_id")String column_id,@RequestParam("beginYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0603(people,point,column_id,startYear,endYear);
    }

    //查询 表06-04 获取监测点代码
    @GetMapping("/getAllData0604")
    public List<crop_01_01_table_06_04> getAllData0604(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0 && endYear == 0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0604(people,point,column_id,beginYear,endYear);
    }

    //查询 表06-05 获取监测点代码
    @GetMapping("/getAllData0605")
    public List<crop_01_01_table_06_05> getAllData0605(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0605(people,point,column_id,beginYear,endYear);
    }

    //查询 表07-01 获取监测点代码
    @GetMapping("/getAllData0701")
    public List<crop_01_01_table_07_01> getAllData0701(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0701(people,column_id,beginYear,endYear);
    }

    //查询 表07-02 获取监测点代码
    @GetMapping("/getAllData0702")
    public List<crop_01_01_table_07_02> getAllData0702(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0702(people,column_id,beginYear,endYear);
    }

    //查询 表07-03 获取监测点代码
    @GetMapping("/getAllData0703")
    //@GetMapping("/getAllData0901/{account}")
    public List<crop_01_01_table_07_03> getAllData0703(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0703(people,column_id,beginYear,endYear);
    }


    //查询 表09-01 获取监测点代码
    @GetMapping("/getAllData0901")
    //@GetMapping("/getAllData0901/{account}")
    public List<crop_01_01_table_09_01> getAllData0901(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0901(people,column_id,beginYear,endYear);
    }

    //查询 表09-02 获取监测点代码
    @GetMapping("/getAllData0902")
    //@GetMapping("/getAllData0903/{account}")
    public List<crop_01_01_table_09_02> getAllData0902(@RequestParam("people")String people,
                                                       @RequestParam("year")String year,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0902(people,year,column_id,beginYear,endYear);
    }

    //查询 表09-03 获取监测点代码
    @GetMapping("/getAllData0903")
    //@GetMapping("/getAllData0903/{account}")
    public List<crop_01_01_table_09_03> getAllData0903(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0903(people,column_id,beginYear,endYear);
    }

    //查询 表09-04 获取监测点代码
    @GetMapping("/getAllData0904")
    //@GetMapping("/getAllData0903/{account}")
    public List<crop_01_01_table_09_04> getAllData0904(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0904(people,column_id,beginYear,endYear);
    }

    //查询 表09-05 获取监测点代码
    @GetMapping("/getAllData0905")
    //@GetMapping("/getAllData0905/{account}")
    public List<crop_01_01_table_09_05> getAllData0905(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0905(people,column_id,beginYear,endYear);
    }

    //查询 表09-06 获取监测点代码
    @GetMapping("/getAllData0906")
    //@GetMapping("/getAllData0905/{account}")
    public List<crop_01_01_table_09_06> getAllData0906(@RequestParam("people")String people,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0906(people,column_id,beginYear,endYear);
    }

    //查询 表09-07 获取监测点代码
    @GetMapping("/getAllData0907")
    //@GetMapping("/getAllData0907/{account}")
    public List<crop_01_01_table_09_07> getAllData0907(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData0907(people,point,column_id,beginYear,endYear);
    }

    //查询 表10-01 获取监测点代码
    @GetMapping("/getAllData1001")
    public List<crop_01_01_table_10_01> getAllData1001(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("beginYear")int beginYear,
                                                       @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllData1001(people,point,column_id,beginYear,endYear);
    }

    //查询 表02-01 自动获取监测点信息
    @GetMapping("/getAllData0201")
    public List<crop_01_01_table_02_01> getAllData0201(@RequestParam("point")String point){
        List<crop_01_01_table_02_01> tableList = situationService.getAllData0201(point);
        //System.out.println(tableList);
        return tableList;
    }


    //查询 表monitoring_point 自动获取监测点信息
    @GetMapping("/getMonitoringPoint/{account}")
    public List<monitoring_point> getMonitoringPoint(@PathVariable("account")String account){
        List<monitoring_point> dateList = situationService.getMonitoringPoint(account);
        return dateList;
    }

    //查询 表05-01 获取监测点代码
    @GetMapping("/getAllData0501")
    public List<crop_01_01_table_05_01> getAllData0501(@RequestParam("people")String people,
                                                       @RequestParam("point")String point,
                                                       @RequestParam("column_id")String column_id,
                                                       @RequestParam("startYear")int startYear,
                                                       @RequestParam("endYear")int endYear){
        return this.situationService.getAllData0501(people,point,column_id,startYear,endYear);
    }

    //更新 表05-01
    @PostMapping(value = "/getInputDataTable0501")
    public int getInputDataTable0501(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_05_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_05_01 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_05_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0501(crops);
        return res;
    }



    //保存 表6-1
    @PostMapping(value = "/getInputDataTable0601")
    public int getInputDataTable0601(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_06_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_06_01 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_06_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0601(crops);
        return res;
    }

    //保存 表6-2
    @PostMapping(value = "/getInputDataTable0602")
    public int getInputDataTable0602(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_06_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_06_02 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_06_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0602(crops);
        return res;
    }

    //保存 表6-3
    @PostMapping(value = "/getInputDataTable0603")
    public int getInputDataTable0603(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_06_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_06_03 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_06_03.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0603(crops);
        return res;
    }

    //保存 表6-4
    @PostMapping(value = "/getInputDataTable0604")
    public int getInputDataTable0604(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_06_04> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_06_04 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_06_04.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0604(crops);
        return res;
    }

    //保存 表6-5
    @PostMapping(value = "/getInputDataTable0605")
    public int getInputDataTable0605(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_01_table_06_05> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_06_05 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_06_05.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0605(crops);
        return res;
    }


    //保存 表07-02
    @PostMapping(value = "/getInputDataTable0702")
    public int getInputDataTable0702(@RequestParam("data")String data){

        crop_01_01_table_07_02 obj1 = JSONObject.parseObject(data , crop_01_01_table_07_02.class);

        return situationService.getInputDataTable0702(obj1);
    }

    //保存 表07-03
    @PostMapping(value = "/getInputDataTable0703")
    public int getInputDataTable0703(@RequestParam("data")String data){

        crop_01_01_table_07_03 obj1 = JSONObject.parseObject(data , crop_01_01_table_07_03.class);

        return situationService.getInputDataTable0703(obj1);
    }

    //保存 表07-01
    @PostMapping(value = "/getInputDataTable0701")
    public int getInputDataTable0701(@RequestParam("data")String data){

        crop_01_01_table_07_01 obj1 = JSONObject.parseObject(data , crop_01_01_table_07_01.class);

        return situationService.getInputDataTable0701(obj1);
    }

    //保存 表09-01
    @PostMapping(value = "/getInputDataTable0901")
    public int getInputDataTable0901(@RequestParam("data")String data){
        crop_01_01_table_09_01 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_01.class);

        return situationService.getInputDataTable0901(obj1);
    }

    //保存 表09-02
    @PostMapping(value = "/getInputDataTable0902")
    public int getInputDataTable0902(@RequestParam("data")String data){

        crop_01_01_table_09_02 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_02.class);

        return situationService.getInputDataTable0902(obj1);
    }

    //保存 表09-03
    @PostMapping(value = "/getInputDataTable0903")
    public int getInputDataTable0903(@RequestParam("data")String data){

        crop_01_01_table_09_03 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_03.class);
        System.out.println(obj1);
        return situationService.getInputDataTable0903(obj1);
    }

    //保存 表09-04
    @PostMapping(value = "/getInputDataTable0904")
    public int getInputDataTable0904(@RequestParam("data")String data){

        crop_01_01_table_09_04 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_04.class);

        return situationService.getInputDataTable0904(obj1);
    }


    //保存 表09-05
    @PostMapping(value = "/getInputDataTable0905")
    public int getInputDataTable0905(@RequestParam("data")String data){

        crop_01_01_table_09_05 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_05.class);

        return situationService.getInputDataTable0905(obj1);
    }

    //保存 表09-06
    @PostMapping(value = "/getInputDataTable0906")
    public int getInputDataTable0906(@RequestParam("data")String data){
        crop_01_01_table_09_06 obj1 = JSONObject.parseObject(data , crop_01_01_table_09_06.class);
        return situationService.getInputDataTable0906(obj1);
    }


    //保存 表0907
    @PostMapping(value = "/getInputDataTable0907")
    public int getInputDataTable0907(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_09_07> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_09_07 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_09_07.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTable0907(crops);
        return res;
    }

    //保存 表10-01
    @PostMapping(value = "/getInputDataTable1001")
    public int getInputDataTable1001(@RequestParam("data")String data){

        String[] dataList = data.split("#");
        ArrayList<crop_01_01_table_10_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_01_table_10_01 crop = JSONObject.parseObject(s , (Type) crop_01_01_table_10_01.class);

            crops.add(crop);
        }
        int res = situationService.getInputDataTable1001(crops);
        return res;
    }

    /*******************************二季稻**************************************/
    /*******************************查询开始****************************************/
    //查询 表02-02-01 自动获取监测点信息
    @GetMapping("/getAllDataTwo0201")
    public List<crop_01_02_table_02_01> getAllDataTwo0201(@RequestParam("people")String people,
                                                          @RequestParam("point")String point){
        List<crop_01_02_table_02_01> tableList = situationService.getAllDataTwo0201(people,point);
        return tableList;
    }

    //查询 表02-05-01 获取监测点代码
    @GetMapping("/getAllDataTwo0501")
    public List<crop_01_02_table_05_01> getAllDataTwo0501(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("startYear")int startYear,
                                                          @RequestParam("endYear")int endYear){
        return this.situationService.getAllDataTwo0501(people,point,column_id,startYear,endYear);
    }

    //查询 表02-06-01 获取监测点代码
    @GetMapping("/getAllDataTwo0601")
    public List<crop_01_02_table_06_01> getAllDataTwo0601(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int startYear,
                                                          @RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0601(people,point,column_id,startYear,endYear);
    }

    //查询 表02-06-02 获取监测点代码
    @GetMapping("/getAllDataTwo0602")
    public List<crop_01_02_table_06_02> getAllDataTwo0602(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int startYear,
                                                          @RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0602(people,point,column_id,startYear,endYear);
    }

    //查询 表02-06-03 获取监测点代码
    @GetMapping("/getAllDataTwo0603")
    public List<crop_01_02_table_06_03> getAllDataTwo0603(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int startYear,
                                                          @RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0603(people,point,column_id,startYear,endYear);
    }

    //查询 表02-06-04 获取监测点代码
    @GetMapping("/getAllDataTwo0604")
    public List<crop_01_02_table_06_04> getAllDataTwo0604(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0 && endYear == 0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0604(people,point,column_id,beginYear,endYear);
    }

    //查询 表02-06-05 获取监测点代码
    @GetMapping("/getAllDataTwo0605")
    public List<crop_01_02_table_06_05> getAllDataTwo0605(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0605(people,point,column_id,beginYear,endYear);
    }

    //查询 表02-07-01 获取监测点代码
    @GetMapping("/getAllDataTwo0701")
    public List<crop_01_02_table_07_01> getAllDataTwo0701(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0701(people,column_id,beginYear,endYear);
    }

    //查询 表02-07-02 获取监测点代码
    @GetMapping("/getAllDataTwo0702")
    public List<crop_01_02_table_07_02> getAllDataTwo0702(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0702(people,column_id,beginYear,endYear);
    }

    //查询 表02-07-03 获取监测点代码
    @GetMapping("/getAllDataTwo0703")
    public List<crop_01_02_table_07_03> getAllDataTwo0703(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0703(people,column_id,beginYear,endYear);
    }

    //查询 表02-07-04 获取监测点代码
    @GetMapping("/getAllDataTwo0704")
    public List<crop_01_02_table_07_04> getAllDataTwo0704(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0704(people,column_id,beginYear,endYear);
    }


    //查询 表02-09-01 获取监测点代码
    @GetMapping("/getAllDataTwo0901")
    //@GetMapping("/getAllData0901/{account}")
    public List<crop_01_02_table_09_01> getAllDataTwo0901(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0901(people,column_id,beginYear,endYear);
    }

    //查询 表02-09-02 获取监测点代码
    @GetMapping("/getAllDataTwo0902")
    public List<crop_01_02_table_09_02> getAllDataTwo0902(@RequestParam("people")String people,
                                                          @RequestParam("year")String year,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0902(people,year,column_id,beginYear,endYear);
    }

    //查询 表02-09-03 获取监测点代码
    @GetMapping("/getAllDataTwo0903")
    //@GetMapping("/getAllData0903/{account}")
    public List<crop_01_02_table_09_03> getAllDataTwo0903(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0903(people,column_id,beginYear,endYear);
    }

    //查询 表02-09-04 获取监测点代码
    @GetMapping("/getAllDataTwo0904")
    //@GetMapping("/getAllData0903/{account}")
    public List<crop_01_02_table_09_04> getAllDataTwo0904(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0904(people,column_id,beginYear,endYear);
    }

    //查询 表02-09-05 获取监测点代码
    @GetMapping("/getAllDataTwo0905")
    //@GetMapping("/getAllData0905/{account}")
    public List<crop_01_02_table_09_05> getAllDataTwo0905(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0905(people,column_id,beginYear,endYear);
    }

    //查询 表02-09-06 获取监测点代码
    @GetMapping("/getAllDataTwo0906")
    //@GetMapping("/getAllData0905/{account}")
    public List<crop_01_02_table_09_06> getAllDataTwo0906(@RequestParam("people")String people,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0906(people,column_id,beginYear,endYear);
    }

    //查询 表02-09-07 获取监测点代码
    @GetMapping("/getAllDataTwo0907")
    //@GetMapping("/getAllData0907/{account}")
    public List<crop_01_02_table_09_07> getAllDataTwo0907(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&&endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo0907(people,point,column_id,beginYear,endYear);
    }

    //查询 表02-10-01 获取监测点代码
    @GetMapping("/getAllDataTwo1001")
    public List<crop_01_02_table_10_01> getAllDataTwo1001(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo1001(people,point,column_id,beginYear,endYear);
    }

    //查询 表02-10-02 获取监测点代码
    @GetMapping("/getAllDataTwo1002")
    public List<crop_01_02_table_10_02> getAllDataTwo1002(@RequestParam("people")String people,
                                                          @RequestParam("point")String point,
                                                          @RequestParam("column_id")String column_id,
                                                          @RequestParam("beginYear")int beginYear,
                                                          @RequestParam("endYear")int endYear){
        if(beginYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            beginYear = cal.get(Calendar.YEAR);
        }
        return this.situationService.getAllDataTwo1002(people,point,column_id,beginYear,endYear);
    }
    /*******************************查询结束****************************************/
    /*******************************保存开始****************************************/
    // 表02-05-01
    @PostMapping(value = "/getInputDataTableTwo0501")
    public int getInputDataTableTwo0501(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_05_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_05_01 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_05_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0501(crops);
        return res;
    }

    //保存 表6-1
    @PostMapping(value = "/getInputDataTableTwo0601")
    public int getInputDataTableTwo0601(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_06_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_06_01 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_06_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0601(crops);
        return res;
    }

    //保存 表6-2
    @PostMapping(value = "/getInputDataTableTwo0602")
    public int getInputDataTableTwo0602(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_06_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_06_02 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_06_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0602(crops);
        return res;
    }

    //保存 表6-3
    @PostMapping(value = "/getInputDataTableTwo0603")
    public int getInputDataTableTwo0603(@RequestParam("data")String data){
        //System.out.println(data);
        String[] dataList = data.split("#");

        ArrayList<crop_01_02_table_06_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_06_03 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_06_03.class);
            //System.out.println(crop);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0603(crops);
        return res;
    }

    //保存 表6-4
    @PostMapping(value = "/getInputDataTableTwo0604")
    public int getInputDataTableTwo0604(@RequestParam("data")String data){
        //System.out.println(data);
        String[] dataList = data.split("#");

        ArrayList<crop_01_02_table_06_04> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_06_04 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_06_04.class);
            //System.out.println(crop);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0604(crops);
        return res;
    }

    //保存 表6-5
    @PostMapping(value = "/getInputDataTableTwo0605")
    public int getInputDataTableTwo0605(@RequestParam("data")String data){
        //System.out.println(data);
        String[] dataList = data.split("#");

        ArrayList<crop_01_02_table_06_05> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_06_05 crop = JSONObject.parseObject(s , (Type) crop_01_02_table_06_05.class);
            //System.out.println(crop);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0605(crops);
        return res;
    }

    //保存 表07-01
    @PostMapping(value = "/getInputDataTableTwo0701")
    public int getInputDataTableTwo0701(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_07_01 obj1 = JSONObject.parseObject(data , crop_01_02_table_07_01.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0701(obj1);
    }

    //保存 表07-02
    @PostMapping(value = "/getInputDataTableTwo0702")
    public int getInputDataTableTwo0702(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_07_02 obj1 = JSONObject.parseObject(data , crop_01_02_table_07_02.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0702(obj1);
    }

    //保存 表07-03
    @PostMapping(value = "/getInputDataTableTwo0703")
    public int getInputDataTableTwo0703(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_07_03 obj1 = JSONObject.parseObject(data , crop_01_02_table_07_03.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0703(obj1);
    }

    //保存 表07-04
    @PostMapping(value = "/getInputDataTableTwo0704")
    public int getInputDataTableTwo0704(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_07_04 obj1 = JSONObject.parseObject(data , crop_01_02_table_07_04.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0704(obj1);
    }

    //保存 表09-01
    @PostMapping(value = "/getInputDataTableTwo0901")
    public int getInputDataTableTwo0901(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_01 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_01.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0901(obj1);
    }

    //保存 表09-02
    @PostMapping(value = "/getInputDataTableTwo0902")
    public int getInputDataTableTwo0902(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_02 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_02.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0902(obj1);
    }

    //保存 表09-03
    @PostMapping(value = "/getInputDataTableTwo0903")
    public int getInputDataTableTwo0903(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_03 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_03.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0903(obj1);
    }

    //保存 表09-04
    @PostMapping(value = "/getInputDataTableTwo0904")
    public int getInputDataTableTwo0904(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_04 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_04.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0904(obj1);
    }


    //保存 表09-05
    @PostMapping(value = "/getInputDataTableTwo0905")
    public int getInputDataTableTwo0905(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_05 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_05.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0905(obj1);
    }

    //保存 表09-06
    @PostMapping(value = "/getInputDataTableTwo0906")
    public int getInputDataTableTwo0906(@RequestParam("data")String data){
        //System.out.println(data);
        crop_01_02_table_09_06 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_06.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0906(obj1);
    }


    //保存 表0907
    @PostMapping(value = "/getInputDataTableTwo0907")
    public int getInputDataTableTwo0907(@RequestParam("data")String data){
        crop_01_02_table_09_07 obj1 = JSONObject.parseObject(data , crop_01_02_table_09_07.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo0907(obj1);
    }

    //保存 表10-01
    @PostMapping(value = "/getInputDataTableTwo1001")
    public int getInputDataTableTwo1001(@RequestParam("data")String data){
        System.out.println(data);
        crop_01_02_table_10_01 obj1 = JSONObject.parseObject(data , crop_01_02_table_10_01.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo1001(obj1);
    }

    //保存 表10-02
    @PostMapping(value = "/getInputDataTableTwo1002")
    public int getInputDataTableTwo1002(@RequestParam("data")String data){
        crop_01_02_table_10_02 obj1 = JSONObject.parseObject(data , crop_01_02_table_10_02.class);
        //System.out.println(obj1);
        return situationService.getInputDataTableTwo1002(obj1);
    }
    /********************************保存结束*************************************/
    /********************************监测点开始*************************************///
    // 查询本年 表02-01
    @PostMapping("/getAllDataPointTwo0201")
    public List<crop_01_02_table_02_01> getAllDataPointTwo0201(@RequestParam("people")String people,
                                                               @RequestParam("myDate")String myDate){
        System.out.print(myDate+"----------------------");
        return situationService.getAllDataPointTwo0201(people,myDate);
    }

    //查询历史 表02-01
    @GetMapping("/getAllDataHistoryPointTwo0201")
    public List<crop_01_02_table_02_01> getAllDataHistoryPointTwo0201(@RequestParam("people")String people,
                                                                      @RequestParam("myDate")String myDate){
        return situationService.getAllDataHistoryPointTwo0201(people,myDate);
    }

    //更新监测点
    //保存 表2-1 2021年度水稻监测点建档卡
    @PostMapping(value = "/updateSituationInputDataTable3")
    public int updateInputDataTable3(@RequestParam("data")String data){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String myDate = String.valueOf(yearTime);
        //System.out.println(data);
        crop_01_01_table_02_01 obj1 = JSONObject.parseObject(data , crop_01_01_table_02_01.class);
        //System.out.println(obj1);
        return situationService.updateInputDataTable3(obj1);
    }

    //保存 表2-1 2021年度水稻监测点建档卡
    @PostMapping(value = "/updateSituationInputDataTableTwo3")
    public int updateInputDataTableTwo3(@RequestParam("data")String data){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String myDate = String.valueOf(yearTime);
        crop_01_02_table_02_01 obj1 = JSONObject.parseObject(data , crop_01_02_table_02_01.class);
        System.out.print(data);
        return situationService.updateInputDataTableTwo3(obj1);
    }
    /********************************监测点结束*************************************/
    /********************************二季稻结束*************************************/


//    //查询 表02-01
//    @GetMapping("/getAllDataPoint0201")
//    public List<crop_01_01_table_02_01> getAllDataPoint0601(@RequestParam("people")String people){
//        return this.situationMapper.getAllDataPoint0201(people);
//    }

    //查询本年 表02-01
    @GetMapping("/getAllDataPoint0201")
    public List<crop_01_01_table_02_01> getAllDataPoint0201(@RequestParam("people")String people,
                                                            @RequestParam("myDate")String myDate){
        return situationService.getAllDataPoint0201(people,myDate);
    }

    //查询历史 表02-01
    @GetMapping("/getAllDataHistoryPoint0201")
    public List<crop_01_01_table_02_01> getAllDataHistoryPoint0201(@RequestParam("people")String people,
                                                                   @RequestParam("myDate")String myDate){
        return situationService.getAllDataHistoryPoint0201(people,myDate);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //二季稻 查询该用户下的所有表1-2数据
    @PostMapping(value = "/getUserAllDataTwo")
    public List<crop_01_02_table_01_02> getUserAllDataTwo(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("flag")int flag){

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationService.getUserAllDataTwo(people,column_id,flag,currentYear);
    }

    //二季稻 查询该用户下的所有表1-1数据
    @PostMapping(value = "/getUserAllDataTwo01")
    public List<crop_01_02_table_01_01> getUserAllDataTwo01(@RequestParam("people")String people){

        return situationService.getUserAllDataTwo01(people);
    }

    //二季稻 查询该用户下所有表2-2数据
    @PostMapping(value = "/getUserAllDataTwo0202")
    public List<crop_01_02_table_02_02> getUserAllDataTwo0202(@RequestParam("people")String people){
        return situationService.getUserAllDataTwo0202(people);
    }

    //二季稻 查询该用户下表3-1数据
    @PostMapping(value = "/getUserAllDataTwo0301")
    public List<crop_01_02_table_03_01> getUserAllDataTwo0301(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0301(people,column_id,startYear,endYear);
    }

    //二季稻 将建档卡中的已知内容插入到表3-1中
    @PostMapping(value = "/getMonitorCardContentIntoTwo0301")
    public int getMonitorCardContentIntoTwo0301(@RequestParam("people")String people){
        return situationService.getMonitorCardContentIntoTwo0301(people);
    }

    //二季稻 将建档卡中的已知内容插入到表3-2中
    @PostMapping(value = "/getMonitorCardContentIntoTwo0302")
    public int getMonitorCardContentIntoTwo0302(@RequestParam("people")String people){
        return situationService.getMonitorCardContentIntoTwo0302(people);
    }

    //二季稻 将建档卡中的已知内容插入到表3-3中
    @PostMapping(value = "/getMonitorCardContentIntoTwo0303")
    public int getMonitorCardContentIntoTwo0303(@RequestParam("people")String people){
        return situationService.getMonitorCardContentIntoTwo0303(people);
    }

    //二季稻 查询该用户下表3-2数据
    @PostMapping(value = "/getUserAllDataTwo0302")
    public List<crop_01_02_table_03_02> getUserAllDataTwo0302(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0302(people,column_id,startYear,endYear);
    }

    //二季稻 查询该用户下表3-3数据
    @PostMapping(value = "/getUserAllDataTwo0303")
    public List<crop_01_02_table_03_03> getUserAllDataTwo0303(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0303(people,column_id,startYear,endYear);
    }

    //二季稻 查询该用户下表3-4数据
    @PostMapping(value = "/getUserAllDataTwo0304")
    public List<crop_01_02_table_03_04> getUserAllDataTwo0304(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0304(people,column_id,startYear,endYear);
    }

    //二季稻 查询该用户下表3-5数据
    @PostMapping(value = "/getUserAllDataTwo0305")
    public List<crop_01_02_table_03_05> getUserAllDataTwo0305(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0305(people,column_id,startYear,endYear);
    }

    //二季稻 查询该用户下表3-6数据
    @PostMapping(value = "/getUserAllDataTwo0306")
    public List<crop_01_02_table_03_06> getUserAllDataTwo0306(@RequestParam("people")String people,@RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0306(people,column_id,startYear,endYear);
    }

//    //二季稻 将已知内容插入表4-1
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0401")
//    public int insertMonitorInfoToTableTwo0401(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 将已知内容插入表4-2
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0402")
//    public int insertMonitorInfoToTableTwo0402(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 将已知内容插入表4-3
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0403")
//    public int insertMonitorInfoToTableTwo0403(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("jiBenMiao")String jiBenMiao,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
//    }
//
//    //二季稻 将已知内容插入表4-4
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0404")
//    public int insertMonitorInfoToTableTwo0404(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }


    //二季稻 查询该用户下表4-1数据
    @PostMapping(value = "/getUserAllDataTwo0401")
    public List<crop_01_02_table_04_01> getUserAllDataTwo0401(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0401(people,column_id,monitor_location,startYear,endYear);
    }

    //二季稻 查询该用户下表4-2数据
    @PostMapping(value = "/getUserAllDataTwo0402")
    public List<crop_01_02_table_04_02> getUserAllDataTwo0402(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        //System.out.println(456);
//        System.out.println(column_id);
//        System.out.println(monitor_location);
//        System.out.println(startYear);
//        System.out.println(endYear);
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0402(people,column_id,monitor_location,startYear,endYear);
    }

    //二季稻 查询该用户下表4-2数据新增用于查看页面总数据
    @PostMapping(value = "/getUserAllDataTwo0402new")
    public List<crop_01_02_table_04_02> getUserAllDataTwo0402new(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
//        System.out.println(123);
//        System.out.println(people);
//        System.out.println(column_id);
//        System.out.println(monitor_location);
//        System.out.println(startYear);
//        System.out.println(endYear);
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0402new(people,column_id,monitor_location,startYear,endYear);
    }
    //二季稻 查询该用户下表4-3数据
    @PostMapping(value = "/getUserAllDataTwo0403")
    public List<crop_01_02_table_04_03> getUserAllDataTwo0403(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0403(people,column_id,monitor_location,startYear,endYear);
    }

    //二季稻 查询该用户下表4-4数据
    @PostMapping(value = "/getUserAllDataTwo0404")
    public List<crop_01_02_table_04_04> getUserAllDataTwo0404(@RequestParam("people")String people,@RequestParam("column_id")String column_id,@RequestParam("monitor_location")String monitor_location,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0404(people,column_id,monitor_location,startYear,endYear);
    }

    //二季稻 查询该用户下表8-1数据
    @PostMapping(value = "/getUserAllDataTwo0801")
    public List<crop_01_02_table_08_01> getUserAllDataTwo0801(@RequestParam("people")String people,
                                                              @RequestParam("point")String point,
                                                              @RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,
                                                              @RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0801(people,point,column_id,startYear,endYear);
    }

    //二季稻 查询该用户下表8-2数据
    @PostMapping(value = "/getUserAllDataTwo0802")
    public List<crop_01_02_table_08_02> getUserAllDataTwo0802(@RequestParam("people")String people,
                                                              @RequestParam("point")String point,
                                                              @RequestParam("column_id")String column_id,
                                                              @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0&& endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        return situationService.getUserAllDataTwo0802(people,point,column_id,startYear,endYear);
    }

    //获取该账号下所有的监测点信息
    @PostMapping(value = "/getCurrentAccountMonitorInfo")
    public ArrayList<crop_01_02_table_02_01> getCurrentAccountMonitorInfo(@RequestParam("account")String account){
        return situationService.getCurrentAccountMonitorInfo(account);
    }

    //二季稻 保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    @PostMapping(value = "/getSituationInputDataTableTwo1")
    public int getInputDataTableTwo1(@RequestParam("data")String data){

//        String[] dataList = data.split("#");
//
        ArrayList<crop_01_02_table_01_01> crops = new ArrayList<>();
//        for (String s : dataList){
//            crop_01_02_table_01_01 crop = JSONObject.parseObject(s ,  crop_01_02_table_01_01.class);
//            crops.add(crop);
//        }
        crop_01_02_table_01_01 crop = JSONObject.parseObject(data ,  crop_01_02_table_01_01.class);
        crops.add(crop);

        int res = situationService.getInputDataTableTwo1(crops);
        return res;
    }

    //二季稻 查询表1-1 下该用户所有的数据数
    @PostMapping(value = "/checkUserAllKnownDataTwo1")
    public List<crop_01_02_table_01_01> checkUserAllKnownDataTwo1(@RequestParam("UserID")String UserID,@RequestParam("column_id")String column_id,
                                                                  @RequestParam("startYear")int startYear,@RequestParam("endYear")int endYear){
        if(startYear == 0 && endYear==0){
            Calendar cal = Calendar.getInstance();
            startYear = cal.get(Calendar.YEAR);
        }
        System.out.println(situationService.checkUserAllKnownDataTwo1(UserID,column_id,startYear,endYear));
        return situationService.checkUserAllKnownDataTwo1(UserID,column_id,startYear,endYear);
    }

    //二季稻 保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表
    @PostMapping(value = "/getSituationInputDataTableTwo2")
    public int getInputDataTableTwo2(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_01_02> crops = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for (String s : dataList){
            crop_01_02_table_01_02 crop = JSONObject.parseObject(s ,  crop_01_02_table_01_02.class);
            System.out.println(crop);
            System.out.println(situationService.getTwoByPinZhongName(crop.getPeople(),crop.getPin_zhong_name(),crop.getColumn_id(),0,currentYear));
            if(situationService.getTwoByPinZhongName(crop.getPeople(),crop.getPin_zhong_name(),crop.getColumn_id(),0,currentYear).isEmpty()){
//                System.out.println("没有记录");

                situationService.getInputDataTableTwo2(crop);
            }
            crops.add(crop);
        }
//        int res = situationService.getInputDataTable2(crops);
        return 1;
    }

    //二季稻 读取表2-1建档卡内容至表2-2中
    @PostMapping(value = "/getMonitorCardContentTwo")
    public int  getMonitorCardContentTwo(@RequestParam("people")String people){
        return situationService.getMonitorCardContentTwo(people);
    }

    //二季稻 获取监测点代码
    @PostMapping(value = "/getMonitorCodeTwo")
    public List<monitoring_point> getMonitorCodeTwo(@RequestParam("UserID")String UserID){
        return situationService.getMonitorCodeTwo(UserID);
    }

    //二季稻 保存 表2-1 2021年度水稻监测点建档卡
    @PostMapping(value = "/getSituationInputDataTableTwo3")
    public int getInputDataTableTwo3(@RequestParam("data")String data){
        crop_01_02_table_02_01 obj1 = JSONObject.parseObject(data , crop_01_02_table_02_01.class);

        return situationService.getInputDataTableTwo3(obj1);
    }

    //二季稻 保存 表3-1
    @PostMapping(value = "/getSituationInputDataTableTwo5")
    public int getInputDataTableTwo5(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_02_table_03_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_01 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo5(crops);
        return res;
    }

    //二季稻 保存 表3-2
    @PostMapping(value = "/getSituationInputDataTableTwo0302")
    public int getInputDataTableTwo0302(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_03_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_02 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0302(crops);
        return res;
    }

    //二季稻 保存 表3-3
    @PostMapping(value = "/getSituationInputDataTableTwo0303")
    public int getInputDataTableTwo0303(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_03_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_03 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_03.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0303(crops);
        return res;
    }

    //二季稻 保存 表3-4
    @PostMapping(value = "/getSituationInputDataTableTwo0304")
    public int getInputDataTableTwo0304(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_03_04> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_04 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_04.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0304(crops);
        return res;
    }

    //二季稻 保存 表3-5
    @PostMapping(value = "/getSituationInputDataTableTwo0305")
    public int getInputDataTableTwo0305(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_03_05> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_05 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_05.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0305(crops);
        return res;
    }

    //二季稻 保存 表3-6
    @PostMapping(value = "/getSituationInputDataTableTwo0306")
    public int getInputDataTableTwo0306(@RequestParam("data")String data){
        String[] dataList = data.split("#");

        ArrayList<crop_01_02_table_03_06> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_03_06 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_06.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0306(crops);
        return res;
    }

//    //二季稻 保存 表3-3
//    @PostMapping(value = "/getSituationInputDataTableTwo0303")
//    public int getInputDataTableTwo0303(@RequestParam("data")String data){
//        String[] dataList = data.split("#");
//
//        ArrayList<crop_01_02_table_03_03> crops = new ArrayList<>();
//        for (String s : dataList){
//            crop_01_02_table_03_03 crop = JSONObject.parseObject(s ,  crop_01_02_table_03_03.class);
//            //System.out.println(crop);
//            crops.add(crop);
//        }
//        int res = situationService.getInputDataTableTwo0303(crops);
//        return res;
//    }

    //二季稻 保存 表4-1
    @PostMapping(value = "/getSituationInputDataTableTwo0401")
    public int getInputDataTableTwo0401(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_04_01> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_04_01 crop = JSONObject.parseObject(s ,  crop_01_02_table_04_01.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0401(crops);
        return res;
    }

    //二季稻 保存 表4-2
    @PostMapping(value = "/getSituationInputDataTableTwo0402")
    public int getInputDataTableTwo0402(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_04_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_04_02 crop = JSONObject.parseObject(s ,  crop_01_02_table_04_02.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0402(crops);
        return res;
    }

    //二季稻 保存 表4-3
    @PostMapping(value = "/getSituationInputDataTableTwo0403")
    public int getInputDataTableTwo0403(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_04_03> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_04_03 crop = JSONObject.parseObject(s ,  crop_01_02_table_04_03.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0403(crops);
        return res;
    }

    //二季稻 保存 表4-4
    @PostMapping(value = "/getSituationInputDataTableTwo0404")
    public int getInputDataTableTwo0404(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_04_04> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_04_04 crop = JSONObject.parseObject(s ,  crop_01_02_table_04_04.class);
            crops.add(crop);
        }
        int res = situationService.getInputDataTableTwo0404(crops);
        return res;
    }

    //二季稻 保存 表8-1
//    @PostMapping(value = "/getSituationInputDataTableTwo0801")
//    public int getInputDataTableTwo0801(@RequestParam("data")String data){
//        String[] dataList = data.split("#");
//        ArrayList<crop_01_02_table_08_01> crops = new ArrayList<>();
//        for (String s : dataList){
//            crop_01_02_table_08_01 crop = JSONObject.parseObject(s ,  crop_01_02_table_08_01.class);
//            int res = situationService.getInputDataTableTwo0801(crop);
//            crops.add(crop);
//        }
//
//        return 1;
//    }
    @PostMapping(value = "/getSituationInputDataTableTwo0801")
    public int getInputDataTableTwo0801(@RequestParam("data")String data){
        ArrayList<crop_01_02_table_08_01> crops = new ArrayList<>();
        crop_01_02_table_08_01 crop = JSONObject.parseObject(data ,  crop_01_02_table_08_01.class);
        System.out.println(crop.getIn_use());
        int res = situationService.getInputDataTableTwo0801(crop);
        crops.add(crop);
        return 1;
    }

    //二季稻 保存 表8-2
    @PostMapping(value = "/getSituationInputDataTableTwo0802")
    public int getInputDataTableTwo0802(@RequestParam("data")String data){
        String[] dataList = data.split("#");
        ArrayList<crop_01_02_table_08_02> crops = new ArrayList<>();
        for (String s : dataList){
            crop_01_02_table_08_02 crop = JSONObject.parseObject(s ,  crop_01_02_table_08_02.class);
            System.out.println(crop);
            int res = situationService.getInputDataTableTwo0802(crop);
            crops.add(crop);
        }

        return 1;
    }
//
//    //二季稻 保存监测点建档卡数据至表1-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0101")
//    public int insertMonitorInfoToTableTwo0101(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0101(area,areaCate,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表1-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0102")
//    public int insertMonitorInfoToTableTwo0102(@RequestParam("area")String area,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0102(area,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0301")
//    public int insertMonitorInfoToTableTwo0301(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0302")
//    public int insertMonitorInfoToTableTwo0302(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-3中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0303")
//    public int insertMonitorInfoToTableTwo0303(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-4中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0304")
//    public int insertMonitorInfoToTableTwo0304(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate
//            ,@RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-5中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0305")
//    public int insertMonitorInfoToTableTwo0305(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0305(area,areaCate,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表3-6中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0306")
//    public int insertMonitorInfoToTableTwo0306(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表5-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0501")
//    public int insertMonitorInfoToTableTwo0501(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表6-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0601")
//    public int insertMonitorInfoToTableTwo0601(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表6-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0602")
//    public int insertMonitorInfoToTableTwo0602(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表6-3中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0603")
//    public int insertMonitorInfoToTableTwo0603(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表6-4中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0604")
//    public int insertMonitorInfoToTableTwo0604(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表6-5中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0605")
//    public int insertMonitorInfoToTableTwo0605(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表7-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0701")
//    public int insertMonitorInfoToTableTwo0701(@RequestParam("area")String area,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0701(area,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表7-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0702")
//    public int insertMonitorInfoToTableTwo0702(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0702(area,areaCate,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表7-3中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0703")
//    public int insertMonitorInfoToTableTwo0703(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0703(area,areaCate,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表7-4中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0704")
//    public int insertMonitorInfoToTableTwo0704(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表9-3中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0903")
//    public int insertMonitorInfoToTableTwo0903(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表9-4中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0904")
//    public int insertMonitorInfoToTableTwo0904(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表9-5中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0905")
//    public int insertMonitorInfoToTableTwo0905(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表9-6中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0906")
//    public int insertMonitorInfoToTableTwo0906(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表9-7中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0907")
//    public int insertMonitorInfoToTableTwo0907(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表10-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo1001")
//    public int insertMonitorInfoToTableTwo1001(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表10-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo1002")
//    public int insertMonitorInfoToTableTwo1002(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
//                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表8-1中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0801")
//    public int insertMonitorInfoToTableTwo0801(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0801(area,areaCate,zaiZhiType,people,time);
//    }
//
//    //二季稻 保存监测点建档卡数据至表8-2中
//    @PostMapping(value = "/insertMonitorInfoToTableTwo0802")
//    public int insertMonitorInfoToTableTwo0802(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
//                                               @RequestParam("zaiZhiType")String zaiZhiType,
//                                               @RequestParam("people")String people,@RequestParam("time")String time){
//        return situationService.insertMonitorInfoToTableTwo0802(area,areaCate,zaiZhiType,people,time);
//    }


    //二季稻 保存监测点建档卡数据至表1-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0101")
    public int insertMonitorInfoToTableTwo0101(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0101(area,areaCate,people,time);
    }

    //二季稻 修改监测点建档卡数据至表1-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0101")
    public int updateMonitorInfoToTableTwo0101(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0101(area,areaCate,people,time);
    }

    //二季稻 保存监测点建档卡数据至表1-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0102")
    public int insertMonitorInfoToTableTwo0102(@RequestParam("area")String area,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0102(area,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0301")
    public int insertMonitorInfoToTableTwo0301(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0301")
    public int updateMonitorInfoToTableTwo0301(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0302")
    public int insertMonitorInfoToTableTwo0302(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-2中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0302")
    public int updateMonitorInfoToTableTwo0302(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-3中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0303")
    public int insertMonitorInfoToTableTwo0303(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-3中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0303")
    public int updateMonitorInfoToTableTwo0303(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-4中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0304")
    public int insertMonitorInfoToTableTwo0304(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate
            ,@RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-4中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0304")
    public int updateMonitorInfoToTableTwo0304(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate
            ,@RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-5中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0305")
    public int insertMonitorInfoToTableTwo0305(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0305(area,areaCate,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-5中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0305")
    public int updateMonitorInfoToTableTwo0305(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0305(area,areaCate,people,time);
    }

    //二季稻 保存监测点建档卡数据至表3-6中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0306")
    public int insertMonitorInfoToTableTwo0306(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表3-6中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0306")
    public int updateMonitorInfoToTableTwo0306(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 将已知内容插入表4-1
    @PostMapping(value = "/insertMonitorInfoToTableTwo0401")
    public int insertMonitorInfoToTableTwo0401(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 将已知内容插入表4-2
    @PostMapping(value = "/insertMonitorInfoToTableTwo0402")
    public int insertMonitorInfoToTableTwo0402(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 将已知内容插入表4-3
    @PostMapping(value = "/insertMonitorInfoToTableTwo0403")
    public int insertMonitorInfoToTableTwo0403(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("jiBenMiao")String jiBenMiao,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
    }

    //二季稻 将已知内容插入表4-4
    @PostMapping(value = "/insertMonitorInfoToTableTwo0404")
    public int insertMonitorInfoToTableTwo0404(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 将已知内容修改表4-1
    @PostMapping(value = "/updateMonitorInfoToTableTwo0401")
    public int updateMonitorInfoToTableTwo0401(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 将已知内容修改表4-2
    @PostMapping(value = "/updateMonitorInfoToTableTwo0402")
    public int updateMonitorInfoToTableTwo0402(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 将已知内容修改表4-3
    @PostMapping(value = "/updateMonitorInfoToTableTwo0403")
    public int updateMonitorInfoToTableTwo0403(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("jiBenMiao")String jiBenMiao,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
    }

    //二季稻 将已知内容修改表4-4
    @PostMapping(value = "/updateMonitorInfoToTableTwo0404")
    public int updateMonitorInfoToTableTwo0404(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("pinZhongType")String pinZhongType,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表5-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0501")
    public int insertMonitorInfoToTableTwo0501(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表5-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0501")
    public int updateMonitorInfoToTableTwo0501(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表6-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0601")
    public int insertMonitorInfoToTableTwo0601(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表6-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0601")
    public int updateMonitorInfoToTableTwo0601(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表6-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0602")
    public int insertMonitorInfoToTableTwo0602(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表6-2中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0602")
    public int updateMonitorInfoToTableTwo0602(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表6-3中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0603")
    public int insertMonitorInfoToTableTwo0603(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表6-3中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0603")
    public int updateMonitorInfoToTableTwo0603(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表6-4中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0604")
    public int insertMonitorInfoToTableTwo0604(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表6-4中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0604")
    public int updateMonitorInfoToTableTwo0604(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表6-5中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0605")
    public int insertMonitorInfoToTableTwo0605(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表6-5中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0605")
    public int updateMonitorInfoToTableTwo0605(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表7-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0701")
    public int insertMonitorInfoToTableTwo0701(@RequestParam("area")String area,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0701(area,people,time);
    }

    //二季稻 保存监测点建档卡数据至表7-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0702")
    public int insertMonitorInfoToTableTwo0702(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0702(area,areaCate,people,time);
    }

    //二季稻 修改监测点建档卡数据至表7-2中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0702")
    public int updateMonitorInfoToTableTwo0702(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0702(area,areaCate,people,time);
    }

    //二季稻 保存监测点建档卡数据至表7-3中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0703")
    public int insertMonitorInfoToTableTwo0703(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0703(area,areaCate,people,time);
    }

    //二季稻 修改监测点建档卡数据至表7-3中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0703")
    public int updateMonitorInfoToTableTwo0703(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0703(area,areaCate,people,time);
    }

    //二季稻 保存监测点建档卡数据至表7-4中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0704")
    public int insertMonitorInfoToTableTwo0704(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表7-4中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0704")
    public int updateMonitorInfoToTableTwo0704(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表8-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0801")
    public int insertMonitorInfoToTableTwo0801(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表8-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0801")
    public int updateMonitorInfoToTableTwo0801(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表8-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0802")
    public int insertMonitorInfoToTableTwo0802(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表8-2中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0802")
    public int updateMonitorInfoToTableTwo0802(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表9-3中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0903")
    public int insertMonitorInfoToTableTwo0903(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表9-3中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0903")
    public int updateMonitorInfoToTableTwo0903(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表9-4中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0904")
    public int insertMonitorInfoToTableTwo0904(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表9-4中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0904")
    public int updateMonitorInfoToTableTwo0904(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表9-5中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0905")
    public int insertMonitorInfoToTableTwo0905(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表9-5中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0905")
    public int updateMonitorInfoToTableTwo0905(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表9-6中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0906")
    public int insertMonitorInfoToTableTwo0906(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表9-6中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0906")
    public int updateMonitorInfoToTableTwo0906(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表9-7中
    @PostMapping(value = "/insertMonitorInfoToTableTwo0907")
    public int insertMonitorInfoToTableTwo0907(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表9-7中
    @PostMapping(value = "/updateMonitorInfoToTableTwo0907")
    public int updateMonitorInfoToTableTwo0907(@RequestParam("area")String area,@RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinXiType")String pinXiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表10-1中
    @PostMapping(value = "/insertMonitorInfoToTableTwo1001")
    public int insertMonitorInfoToTableTwo1001(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表10-1中
    @PostMapping(value = "/updateMonitorInfoToTableTwo1001")
    public int updateMonitorInfoToTableTwo1001(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 保存监测点建档卡数据至表10-2中
    @PostMapping(value = "/insertMonitorInfoToTableTwo1002")
    public int insertMonitorInfoToTableTwo1002(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.insertMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }

    //二季稻 修改监测点建档卡数据至表10-2中
    @PostMapping(value = "/updateMonitorInfoToTableTwo1002")
    public int updateMonitorInfoToTableTwo1002(@RequestParam("area")String area,@RequestParam("areaCate")String areaCate,
                                               @RequestParam("daoZuoType")String daoZuoType,@RequestParam("pinZhongType")String pinZhongType,
                                               @RequestParam("MonitorBlock")String MonitorBlock,@RequestParam("zaiZhiType")String zaiZhiType,
                                               @RequestParam("people")String people,@RequestParam("time")String time){
        return situationService.updateMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //数据退还

    //获取数据退还列表
    @PostMapping(value = "/getDataBackListInfo")
    public List<table_record_back> getDataBackListInfo(@RequestParam("people")String people){
        return situationService.getDataBackListInfo(people);
    }

    //修改数据退还状态
    @PostMapping(value = "/fixDataBackState")
    public int fixDataBackState(@RequestParam("tableName")String tableName,@RequestParam("tableId")String tableId){
        //System.out.println(tableName+"-----"+tableId);
        return situationService.fixDataBackState(tableName,tableId);
    }

    //获取 年度监测县(市、区)水稻播栽进度调查表 的当前进程

    //获取 年度监测县(市、区)水稻苗情评价动态列表(移栽) 的当前进程

    //获取 年度监测县(市、区)水稻苗情评价动态列表(直播) 的当前进程

    //获取 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的当前进程

    //获取 年度监测县(市、区)中稻生育进度调查表（直播稻） 的当前进程

    //////////////////////////////////////
    //查询该用户下所有表2-2数据
    @GetMapping("/getAreaInfo")
    public List<sys_code> getAreaInfo(@RequestParam("people")String people){
        return this.situationService.getAreaInfo(people);
    }

    //查询所有品种
    @GetMapping("/getAllVarieties")
    public List<table_variety> getAllVarieties(){
        return this.situationService.getAllVarieties();
    }

    @GetMapping("/getPerson")
    public List<person_login> getPerson(@RequestParam("people")String people){
        return this.situationService.getPerson(people);
    }


}
