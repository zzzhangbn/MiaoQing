package com.ricemonitoring.service;

import com.ricemonitoring.pojo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface TableService {

     List<sum_select_list> getCounty1();
     List<sum_select_list> getCitys();
     String returnTable12(String recordCounty, String year);
     String returnTableTwo12(String recordCounty, String year);
     String returnRecord(String returnTableName, int recordId);
     String recordAdd(String returnTableName, String recordCounty, int recordId, String recordReason, String recordTime, String recordPeople);
     String confirmTable12(String recordCounty, String year);
     String confirmTableTwo12(String recordCounty, String year);
     String confirmPass(String returnTableName, int recordId);
     List<crop_01_01_table_02_01> getPointData(String year);
     int noPassPoint(String recordId);
     int PassPoint(String pointCode, String recordId);
     List<crop_01_01_table_02_01> getRiceTable21(String year, String county);
     int insertInfoToRiceTable0202(crop_01_01_table_02_01 insertInfo);
     int updateInfoToRiceTable0202(crop_01_01_table_02_01 insertInfo);

     List<crop_01_01_table_01_01> getRiceTable11(String county, String areaType, String year);
     List<crop_01_01_table_01_01> getAuditRiceTable11(String year);
     List<crop_01_01_table_01_01> getSeekInfo11(String county, String areaType, String riceType, String year);

     //分页数据获取
     pageBean<crop_01_01_table_01_02> getPageRiceTable12(String county, String areaType, String year, int start, int pageSize);
     List<crop_01_01_table_01_02> getRiceTable12(String county, String areaType, String year);
     List<crop_01_01_table_01_02> getAuditRiceTable12(String year);
     List<crop_01_01_table_01_02> getAssist12(String auditCounty, String year);
     List<crop_01_01_table_01_02> getSeekInfo12(String county, String areaType, String riceType, String year);

     List<crop_01_01_table_02_02> getRiceTable22(String county, String areaType, String year);
     List<crop_01_01_table_02_02> getAuditRiceTable22(String year);
     List<crop_01_01_table_02_02> getSeekInfo22(String county, String city, String year);

     List<crop_01_01_table_03_01> getRiceTable31(String county, String areaType, String year);
     List<crop_01_01_table_03_01> getAuditRiceTable31(String year);
     List<crop_01_01_table_03_01> getSeekInfo31(String county, String areaType, String riceType, String year);

     List<crop_01_01_table_03_02> getRiceTable32(String county, String areaType, String year);
     List<crop_01_01_table_03_02> getAuditRiceTable32(String year);
     List<crop_01_01_table_03_02> getSeekInfo32(String county, String areaType, String riceType, String year);

     List<crop_01_01_table_03_03> getRiceTable33(String county, String areaType, String year);
     List<crop_01_01_table_03_03> getAuditRiceTable33(String year);
     List<crop_01_01_table_03_03> getSeekInfo33(String county, String areaType, String riceType, String year);

     List<crop_01_01_table_04_01> getRiceTable41(String county, String areaType, String year);
     List<crop_01_01_table_04_01> getAuditRiceTable41(String year);
     List<crop_01_01_table_04_01> getSeekInfo41(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_04_02> getRiceTable42(String county, String areaType, String year);
     List<crop_01_01_table_04_02> getAuditRiceTable42(String year);
     List<crop_01_01_table_04_02> getSeekInfo42(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_04_03> getRiceTable43(String county, String areaType, String year);
     List<crop_01_01_table_04_03> getAuditRiceTable43(String year);
     List<crop_01_01_table_04_03> getSeekInfo43(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_04_04> getRiceTable44(String county, String areaType, String year);
     List<crop_01_01_table_04_04> getAuditRiceTable44(String year);
     List<crop_01_01_table_04_04> getSeekInfo44(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_05_01> getRiceTable51(String county, String areaType, String year);
     List<crop_01_01_table_05_01> getAuditRiceTable51(String year);
     List<crop_01_01_table_05_01> getSeekInfo51(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_06_01> getRiceTable61(String county, String areaType, String year);
     List<crop_01_01_table_06_01> getAuditRiceTable61(String year);
     List<crop_01_01_table_06_01> getSeekInfo61(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_06_02> getRiceTable62(String county, String areaType, String year);
     List<crop_01_01_table_06_02> getAuditRiceTable62(String year);
     List<crop_01_01_table_06_02> getSeekInfo62(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_06_03> getRiceTable63(String county, String areaType, String year);
     List<crop_01_01_table_06_03> getAuditRiceTable63(String year);
     List<crop_01_01_table_06_03> getSeekInfo63(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_06_04> getRiceTable64(String county, String areaType, String year);
     List<crop_01_01_table_06_04> getAuditRiceTable64(String year);
     List<crop_01_01_table_06_04> getSeekInfo64(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_06_05> getRiceTable65(String county, String areaType, String year);
     List<crop_01_01_table_06_05> getAuditRiceTable65(String year);
     List<crop_01_01_table_06_05> getSeekInfo65(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_07_01> getRiceTable71(String county, String areaType, String year);
     List<crop_01_01_table_07_01> getAuditRiceTable71(String year);
     List<crop_01_01_table_07_01> getSeekInfo71(String county, String areaType, String year);

     List<crop_01_01_table_07_02> getRiceTable72(String county, String areaType, String year);
     List<crop_01_01_table_07_02> getAuditRiceTable72(String year);
     List<crop_01_01_table_07_02> getSeekInfo72(String county, String areaType, String year);

     List<crop_01_01_table_07_03> getRiceTable73(String county, String areaType, String year);
     List<crop_01_01_table_07_03> getAuditRiceTable73(String year);
     List<crop_01_01_table_07_03> getSeekInfo73(String county, String areaType, String year);

     //8-1第二版
     List<crop_01_01_table_08_01> attainRiceTable81(String county, String areaType, String year);
     List<crop_01_01_table_08_01> getRiceTable81(String county, String areaType, String year);
     List<crop_01_01_table_08_01> getAuditRiceTable81(String year);
     List<crop_01_01_table_08_01> getSeekInfo81(String county, String areaType, String riceType, String category, String plantWay, String year);

     List<crop_01_01_table_09_01> getRiceTable91(String county, String areaType, String year);
     List<crop_01_01_table_09_01> getAuditRiceTable91(String year);
     List<crop_01_01_table_09_01> getSeekInfo91(String county, String year);

     List<crop_01_01_table_09_02> getRiceTable92(String currentYear, String previousYear, String county, String areaType, String year);
     String[] getCurrentCounty(String county,String year);
     List<crop_01_01_table_09_02> getAuditRiceTable92(String currentYear, String year);
     List<crop_01_01_table_09_02> getSeekInfo92(String county, String currentYear, String previousYear, String year);

     //分页数据获取
     pageBean<crop_01_01_table_09_03> getPageRiceTable93(String county, String areaType, String year, int start, int pageSize);
     List<crop_01_01_table_09_03> getRiceTable93(String county, String areaType, String year);
     List<crop_01_01_table_09_03> getAuditRiceTable93(String year);
     List<crop_01_01_table_09_03> getSeekInfo93(String county, String areaType, String riceType, String category, String year);

     List<crop_01_01_table_09_04> getRiceTable94(String county, String areaType, String year);
     List<crop_01_01_table_09_04> getAuditRiceTable94(String year);
     List<crop_01_01_table_09_04> getSeekInfo94(String county, String areaType, String riceType, String category, String year);

     List<crop_01_01_table_09_05> getRiceTable95(String county, String areaType, String year);
     List<crop_01_01_table_09_05> getAuditRiceTable95(String year);
     List<crop_01_01_table_09_05> getSeekInfo95(String county, String areaType, String riceType, String category, String year);

     List<crop_01_01_table_09_06> getRiceTable96(String county, String areaType, String year);
     List<crop_01_01_table_09_06> getAuditRiceTable96(String year);
     List<crop_01_01_table_09_06> getSeekInfo96(String county, String areaType, String riceType, String category, String year);

     List<crop_01_01_table_09_07> getRiceTable97(String county, String areaType, String year);
     List<crop_01_01_table_09_07> getAuditRiceTable97(String year);
     List<crop_01_01_table_09_07> getSeekInfo97(String county, String areaType, String plantWay, String year);

     List<crop_01_01_table_10_01> getRiceTable101(String county, String areaType, String year);
     List<crop_01_01_table_10_01> getAuditRiceTable101(String year);
     List<crop_01_01_table_10_01> getSeekInfo101(String county, String areaType, String plantWay, String year);

     int getAuditData(String city, String name, String chinese, int dataId, String times, String auditPeople);
     int deleteRepeatData();
     List<table_audit> getNoAuditAllData();
     pageBean<table_audit> getNoAuditInfo(String params1, int start, int pageSize);
     int deleteRecord(int rowId, String rowName, String rowTime);
     List<sum_select_list> getCountyInfo();
     pageBean<table_record_back> getBackInfo(String year, int start, int pageSize);
     pageBean<table_record_back> getAllBackData(String params1, String params2, String year, int start, int pageSize);
     List<crop_01_01_table_04_04> getPeriod(String year);

     //获取未审核各表数据分页
     pageBean<? extends Object> getRiceAudit1(String params1, String riceTableName, int start, int pageSize, String year);
     //获取未审核表1-2数据分页
     ArrayList<HashMap<String,Object> > getRiceTableAudit12(String params1, int start, int pageSize, String year);
     //搜索未审核各表数据分页
     pageBean<? extends Object> searchRiceAudit1(String params1, String month, String riceTableName, int start, int pageSize, String year);
     //搜索未审核表1-2数据分页
     ArrayList<HashMap<String,Object> > searchRiceAudit12(String params1, String month, String riceTableName, int start, int pageSize, String year);

     //获取各表未上报县数据
     List<HashMap<String,Object>> getNoSumbitData(String year);

//     处理未上报数据
     List<crop_01_01_table_01_01> getRice11(String year);
     List<crop_01_01_table_01_02> getRice12(String year);
     List<crop_01_01_table_03_01> getRice31(String year);
     List<crop_01_01_table_03_02> getRice32(String year);
     List<crop_01_01_table_03_03> getRice33(String year);
     List<crop_01_01_table_04_01> getRice41(String year);
     List<crop_01_01_table_04_02> getRice42(String year);
     List<crop_01_01_table_04_03> getRice43(String year);
     List<crop_01_01_table_04_04> getRice44(String year);
     List<crop_01_01_table_05_01> getRice51(String year);
     List<crop_01_01_table_06_01> getRice61(String year);
     List<crop_01_01_table_06_02> getRice62(String year);
     List<crop_01_01_table_06_03> getRice63(String year);
     List<crop_01_01_table_06_04> getRice64(String year);
     List<crop_01_01_table_06_05> getRice65(String year);
     List<crop_01_01_table_07_01> getRice71(String year);
     List<crop_01_01_table_07_02> getRice72(String year);
     List<crop_01_01_table_07_03> getRice73(String year);
     List<crop_01_01_table_08_01> getRice81(String year);
     List<crop_01_01_table_09_01> getRice91(String year);
     List<crop_01_01_table_09_02> getRice92(String year);
     List<crop_01_01_table_09_03> getRice93(String year);
     List<crop_01_01_table_09_04> getRice94(String year);
     List<crop_01_01_table_09_05> getRice95(String year);
     List<crop_01_01_table_09_06> getRice96(String year);
     List<crop_01_01_table_09_07> getRice97(String year);
     List<crop_01_01_table_10_01> getRice101(String year);
     List<table_second_name> getTabName();

     int deleteAudit();
     //删除监测点
     int deletePoint(crop_01_01_table_02_02 deleteInfo,String year);

     //处理未上报数据
     List<HashMap<String,Object>> getSubmitData(String year);
     //获取各个监测县上报与未上报具体表信息
     List<List<HashMap<String,Object>>> getSubmitInfo(String countyName,String year);
     //双季稻
     List<List<HashMap<String,Object>>> getSubmitTwoInfo(String countyName,String year);

     //    增加单季稻监测点信息查看部分
     List<crop_01_01_table_04_01> getRiceTable41_Message(String area,String areaType, String year);
     List<crop_01_01_table_04_03> getRiceTable43_Message(String area,String areaType, String year);
     List<crop_01_01_table_04_04> getRiceTable44_Message(String area,String areaType,String year);
     List<crop_01_01_table_06_01> getRiceTable61_Message(String area, String areaType,String year);
     List<crop_01_01_table_06_02> getRiceTable62_Message(String area, String areaType,String year);
     List<crop_01_01_table_06_03> getRiceTable63_Message(String area, String areaType,String year);
     List<crop_01_01_table_06_04> getRiceTable64_Message(String area, String areaType,String year);
     List<crop_01_01_table_06_05> getRiceTable65_Message(String area, String areaType,String year);
     List<crop_01_01_table_09_07> getRiceTable97_Message(String area, String areaType,String year);
     List<crop_01_01_table_10_01> getRiceTable101_Message(String area, String areaType,String year);
     List<crop_01_01_table_05_01> getRiceTable51_Message(String area, String areaType,String year);
     List<crop_01_01_table_08_01> getRiceTable81_Message(String area, String areaType,String year);




}
