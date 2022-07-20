package com.ricemonitoring.service;

import com.ricemonitoring.pojo.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface TableTwoService {
    List<sum_select_list> getCounty2();
    List<crop_01_02_table_02_01> getPointData2(String year);
    String noPassPoint2(String recordId);
    int PassPoint2(String pointCode, String recordId);

    List<sum_select_list> getCitys2();
    //双季稻表1-1搜索查询
    List<crop_01_02_table_01_01> getRiceTableTwo11(String county, String areaType, String year);
    List<crop_01_02_table_01_01> getAuditRiceTableTwo11(String year);
    List<crop_01_02_table_01_01> getSeekInfoTwo11(String county, String areaType, String riceType, String year);

    //双季稻表1-2搜索查询
    List<crop_01_02_table_01_02> getRiceTableTwo12(String county, String areaType, String year);
    List<crop_01_02_table_01_02> getAuditRiceTableTwo12(String year);
    List<crop_01_02_table_01_02> getSeekInfoTwo12(String county, String areaType, String riceType, String year);

    //双季稻表2-2搜索查询
    List<crop_01_02_table_02_02> getRiceTableTwo22(String county, String areaType, String year);
    List<crop_01_02_table_02_02> getAuditRiceTableTwo22(String year);
    List<crop_01_02_table_02_02> getSeekInfoTwo22(String county, String city, String year);

    //双季稻表3-1搜索查询
    List<crop_01_02_table_03_01> getRiceTableTwo31(String county, String areaType, String year);
    List<crop_01_02_table_03_01> getAuditRiceTableTwo31(String year);
    List<crop_01_02_table_03_01> getSeekInfoTwo31(String county, String areaType, String riceType, String year);

    //双季稻表3-2搜索查询
    List<crop_01_02_table_03_02> getRiceTableTwo32(String county, String areaType, String year);
    List<crop_01_02_table_03_02> getAuditRiceTableTwo32(String year);
    List<crop_01_02_table_03_02> getSeekInfoTwo32(String county, String areaType, String riceType, String year);

    //双季稻表3-3搜索查询
    List<crop_01_02_table_03_03> getRiceTableTwo33(String county, String areaType, String year);
    List<crop_01_02_table_03_03> getAuditRiceTableTwo33(String year);
    List<crop_01_02_table_03_03> getSeekInfoTwo33(String county, String areaType, String riceType, String year);

    //双季稻表3-4搜索查询
    List<crop_01_02_table_03_04> getRiceTableTwo34(String county, String areaType, String year);
    List<crop_01_02_table_03_04> getAuditRiceTableTwo34(String year);
    List<crop_01_02_table_03_04> getSeekInfoTwo34(String county, String areaType, String riceType, String year);

    //双季稻表3-5搜索查询
    List<crop_01_02_table_03_05> getRiceTableTwo35(String county, String areaType, String year);
    List<crop_01_02_table_03_05> getAuditRiceTableTwo35(String year);
    List<crop_01_02_table_03_05> getSeekInfoTwo35(String county, String areaType, String riceType, String year);

    //双季稻表3-6搜索查询
    List<crop_01_02_table_03_06> getRiceTableTwo36(String county, String areaType, String year);
    List<crop_01_02_table_03_06> getAuditRiceTableTwo36(String year);
    List<crop_01_02_table_03_06> getSeekInfoTwo36(String county, String areaType, String riceType, String year);

    //双季稻表4-1搜索查询
    List<crop_01_02_table_04_01> getRiceTableTwo41(String county, String areaType, String year);
    List<crop_01_02_table_04_01> getAuditRiceTableTwo41(String year);
    List<crop_01_02_table_04_01> getSeekInfoTwo41(String county, String areaType, String riceType, String category, String plantWay, String year);

    //双季稻表4-2搜索查询
    List<crop_01_02_table_04_02> getRiceTableTwo42(String county, String areaType, String year);
    List<crop_01_02_table_04_02> getAuditRiceTableTwo42(String year);
    List<crop_01_02_table_04_02> getSeekInfoTwo42(String county, String areaType, String riceType, String category, String plantWay, String year);

    //双季稻表4-3搜索查询
    List<crop_01_02_table_04_03> getRiceTableTwo43(String county, String areaType, String year);
    List<crop_01_02_table_04_03> getAuditRiceTableTwo43(String year);
    List<crop_01_02_table_04_03> getSeekInfoTwo43(String county, String areaType, String riceType, String category, String plantWay, String year);

    //双季稻表4-4搜索查询
    List<crop_01_02_table_04_04> getRiceTableTwo44(String county, String areaType, String year);
    List<crop_01_02_table_04_04> getAuditRiceTableTwo44(String year);
    List<crop_01_02_table_04_04> getSeekInfoTwo44(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表5-1搜索查询
    List<crop_01_02_table_05_01> getRiceTableTwo51(String county, String areaType, String year);
    List<crop_01_02_table_05_01> getAuditRiceTableTwo51(String year);
    List<crop_01_02_table_05_01> getSeekInfoTwo51(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表6-1搜索查询
    List<crop_01_02_table_06_01> getRiceTableTwo61(String county, String areaType, String year);
    List<crop_01_02_table_06_01> getAuditRiceTableTwo61(String year);
    List<crop_01_02_table_06_01> getSeekInfoTwo61(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表6-2搜索查询
    List<crop_01_02_table_06_02> getRiceTableTwo62(String county, String areaType, String year);
    List<crop_01_02_table_06_02> getAuditRiceTableTwo62(String year);
    List<crop_01_02_table_06_02> getSeekInfoTwo62(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表6-3搜索查询
    List<crop_01_02_table_06_03> getRiceTableTwo63(String county, String areaType, String year);
    List<crop_01_02_table_06_03> getAuditRiceTableTwo63(String year);
    List<crop_01_02_table_06_03> getSeekInfoTwo63(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表6-4搜索查询
    List<crop_01_02_table_06_04> getRiceTableTwo64(String county, String areaType, String year);
    List<crop_01_02_table_06_04> getAuditRiceTableTwo64(String year);
    List<crop_01_02_table_06_04> getSeekInfoTwo64(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表6-5搜索查询
    List<crop_01_02_table_06_05> getRiceTableTwo65(String county, String areaType, String year);
    List<crop_01_02_table_06_05> getAuditRiceTableTwo65(String year);
    List<crop_01_02_table_06_05> getSeekInfoTwo65(String county, String areaType, String riceType, String category, String plantWay, String year);


    //双季稻表7-1搜索查询
    List<crop_01_02_table_07_01> getRiceTableTwo71(String county, String areaType, String year);
    List<crop_01_02_table_07_01> getAuditRiceTableTwo71(String year);
    List<crop_01_02_table_07_01> getSeekInfoTwo71(String county, String areaType, String year);


    //双季稻表7-2搜索查询
    List<crop_01_02_table_07_02> getRiceTableTwo72(String county, String areaType, String year);
    List<crop_01_02_table_07_02> getAuditRiceTableTwo72(String year);
    List<crop_01_02_table_07_02> getSeekInfoTwo72(String county, String areaType, String year);


    //双季稻表7-3搜索查询
    List<crop_01_02_table_07_03> getRiceTableTwo73(String county, String areaType, String year);
    List<crop_01_02_table_07_03> getAuditRiceTableTwo73(String year);
    List<crop_01_02_table_07_03> getSeekInfoTwo73(String county, String areaType, String year);


    //双季稻表7-4搜索查询
    List<crop_01_02_table_07_04> getRiceTableTwo74(String county, String areaType, String year);
    List<crop_01_02_table_07_04> getAuditRiceTableTwo74(String year);
    List<crop_01_02_table_07_04> getSeekInfoTwo74(String county, String areaType, String riceType, String year);

    //双季稻表8-1搜索查询
    List<crop_01_02_table_08_01> getRiceTableTwo81(String county, String areaType, String year);
    List<crop_01_02_table_08_01> getAuditRiceTableTwo81(String year);
    List<crop_01_02_table_08_01> getSeekInfoTwo81(String county, String year);

    //双季稻表8-2搜索查询
    List<crop_01_02_table_08_02> getRiceTableTwo82(String county, String areaType, String year);
    List<crop_01_02_table_08_02> getAuditRiceTableTwo82(String year);
    List<crop_01_02_table_08_02> getSeekInfoTwo82(String county, String year);


    //双季稻表9-1搜索查询
    List<crop_01_02_table_09_01> getRiceTableTwo91(String county, String areaType, String year);
    List<crop_01_02_table_09_01> getAuditRiceTableTwo91(String year);
    List<crop_01_02_table_09_01> getSeekInfoTwo91(String county, String year);


    //双季稻表9-2搜索查询
    List<crop_01_02_table_09_02> getRiceTableTwo92(String currentYear, String previousYear, String county, String areaType, String year);
    List<crop_01_02_table_09_02> getAuditRiceTableTwo92(String currentYear, String year);
    String[] getCurrentCounty2(String county,String year);
    List<crop_01_02_table_09_02> getSeekInfoTwo92(String county, String currentYear, String previousYear, String year);


    //双季稻表9-3搜索查询
    List<crop_01_02_table_09_03> getRiceTableTwo93(String county, String areaType, String year);
    List<crop_01_02_table_09_03> getAuditRiceTableTwo93(String year);
    List<crop_01_02_table_09_03> getSeekInfoTwo93(String county, String areaType, String riceType, String category, String year);


    //双季稻表9-4搜索查询
    List<crop_01_02_table_09_04> getRiceTableTwo94(String county, String areaType, String year);
    List<crop_01_02_table_09_04> getAuditRiceTableTwo94(String year);
    List<crop_01_02_table_09_04> getSeekInfoTwo94(String county, String areaType, String riceType, String category, String year);


    //双季稻表9-5搜索查询
    List<crop_01_02_table_09_05> getRiceTableTwo95(String county, String areaType, String year);
    List<crop_01_02_table_09_05> getAuditRiceTableTwo95(String year);
    List<crop_01_02_table_09_05> getSeekInfoTwo95(String county, String areaType, String riceType, String category, String year);


    //双季稻表9-6搜索查询
    List<crop_01_02_table_09_06> getRiceTableTwo96(String county, String areaType, String year);
    List<crop_01_02_table_09_06> getAuditRiceTableTwo96(String year);
    List<crop_01_02_table_09_06> getSeekInfoTwo96(String county, String areaType, String riceType, String category, String year);


    //双季稻表9-7搜索查询
    List<crop_01_02_table_09_07> getRiceTableTwo97(String county, String areaType, String year);
    List<crop_01_02_table_09_07> getAuditRiceTableTwo97(String year);
    List<crop_01_02_table_09_07> getSeekInfoTwo97(String county, String areaType, String riceType, String year);

    //双季稻表10-1搜索查询
    List<crop_01_02_table_10_01> getRiceTableTwo101(String county, String areaType, String year);
    List<crop_01_02_table_10_01> getAuditRiceTableTwo101(String year);
    List<crop_01_02_table_10_01> getSeekInfoTwo101(String county, String areaType, String riceType, String plantWay, String year);

    //双季稻表10-2搜索查询
    List<crop_01_02_table_10_02> getRiceTableTwo102(String county, String areaType, String year);
    List<crop_01_02_table_10_02> getAuditRiceTableTwo102(String year);
    List<crop_01_02_table_10_02> getSeekInfoTwo102(String county, String areaType, String riceType, String plantWay, String year);

    int getAuditData2(String city, String name, String chinese, int dataId, String times, String auditPeople);
    int deleteRepeatData2();
    List<table_audit_2> getNoAuditAllData2();
    pageBean<table_audit_2> getNoAuditInfo2(String params1, int start, int pageSize);
    int deleteRecord2(int rowId, String rowName, String rowTime);
    List<crop_01_02_table_04_04> getPeriod2(String year);

    //获取未审核各表数据分页
    pageBean<? extends Object> getRiceTwoAudit1(String params1, String riceTableName, int start, int pageSize, String year);
    //搜索未审核各表数据分页
    pageBean<? extends Object> searchRiceAuditTwo1(String params1, String month, String riceTableName, int start, int pageSize, String year);
    //双季稻监测点审核
    List<crop_01_02_table_02_01> getRiceTableTwo21(String year, String county);
    int insertInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo);
    int updateInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo);
    //获取未审核表1-2数据分页
    ArrayList<HashMap<String,Object> > getRiceTableAuditTwo12(String params1, int start, int pageSize, String year);
    //搜索未审核表1-2数据分页
    ArrayList<HashMap<String,Object> > searchRiceAuditTwo12(String params1, String month, String riceTableName, int start, int pageSize, String year);

    //处理双季稻未审核数据
    List<HashMap<String ,Object>> getNoReportData2();

    //删除监测点
    int deletePoint2(crop_01_02_table_02_02 deleteInfo,String year);

    List<crop_01_02_table_04_02> getRiceTableTwo42new(String area, String areaType, String year);

    List<crop_01_02_table_04_03> getRiceTableTwo43new(String area, String areaType, String year);
    List<crop_01_02_table_04_04> getRiceTableTwo44new(String area, String areaType, String year);
    List<crop_01_02_table_06_01> getRiceTableTwo61new(String area, String areaType, String year);
    List<crop_01_02_table_06_02> getRiceTableTwo62new(String area, String areaType, String year);
    List<crop_01_02_table_06_03> getRiceTableTwo63new(String area, String areaType, String year);
    List<crop_01_02_table_06_04> getRiceTableTwo64new(String area, String areaType, String year);
    List<crop_01_02_table_06_05> getRiceTableTwo65new(String area, String areaType, String year);
    List<crop_01_02_table_09_07> getRiceTableTwo97new(String area, String areaType, String year);
    List<crop_01_02_table_10_01> getRiceTableTwo101new(String area, String areaType, String year);
    List<crop_01_02_table_10_02> getRiceTableTwo102new(String area, String areaType, String year);
    List<crop_01_02_table_05_01> getRiceTableTwo51new(String area, String areaType, String year);
    List<crop_01_02_table_08_01> getRiceTableTwo81new(String area, String areaType, String year);
    List<crop_01_02_table_08_02> getRiceTableTwo82new(String area, String areaType, String year);
}
