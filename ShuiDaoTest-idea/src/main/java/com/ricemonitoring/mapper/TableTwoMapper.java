package com.ricemonitoring.mapper;


import com.ricemonitoring.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface TableTwoMapper {
    List<sum_select_list> getCounty2();
    List<sum_select_list> getCitys2();
    List<crop_01_02_table_02_01> getPointData2(String year);
    String noPassPoint2(String recordId);
    int PassPoint2(String pointCode, String recordId);

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

    //双季稻表6-2索查询
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
    List<crop_01_02_table_09_02> getCurrentCounty2(String year, String county);
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
    List<crop_01_02_table_09_07> getSeekInfoTwo97(String county, String areaType, String plantWay, String year);


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
    List<table_audit_2> getNoAuditAllInfo2(String params1);
    List<table_audit_2> getNoAuditAllDataMap2(HashMap<String, Object> map);
    int deleteRecord2(int rowId, String rowName, String rowTime);
    List<crop_01_02_table_04_04> getPeriod2(String year);

    //获取未审核各表数据分页
    List<crop_01_02_table_01_01> getRiceAuditTwo11(HashMap<String, Object> map);
    List<crop_01_02_table_01_02> getRiceAuditTwo12(HashMap<String, Object> map);
    List<crop_01_02_table_03_01> getRiceAuditTwo31(HashMap<String, Object> map);
    List<crop_01_02_table_03_02> getRiceAuditTwo32(HashMap<String, Object> map);
    List<crop_01_02_table_03_03> getRiceAuditTwo33(HashMap<String, Object> map);
    List<crop_01_02_table_03_04> getRiceAuditTwo34(HashMap<String, Object> map);
    List<crop_01_02_table_03_05> getRiceAuditTwo35(HashMap<String, Object> map);
    List<crop_01_02_table_03_06> getRiceAuditTwo36(HashMap<String, Object> map);
    List<crop_01_02_table_04_01> getRiceAuditTwo41(HashMap<String, Object> map);
    List<crop_01_02_table_04_02> getRiceAuditTwo42(HashMap<String, Object> map);
    List<crop_01_02_table_04_03> getRiceAuditTwo43(HashMap<String, Object> map);
    List<crop_01_02_table_04_04> getRiceAuditTwo44(HashMap<String, Object> map);
    List<crop_01_02_table_05_01> getRiceAuditTwo51(HashMap<String, Object> map);
    List<crop_01_02_table_06_01> getRiceAuditTwo61(HashMap<String, Object> map);
    List<crop_01_02_table_06_02> getRiceAuditTwo62(HashMap<String, Object> map);
    List<crop_01_02_table_06_03> getRiceAuditTwo63(HashMap<String, Object> map);
    List<crop_01_02_table_06_04> getRiceAuditTwo64(HashMap<String, Object> map);
    List<crop_01_02_table_06_05> getRiceAuditTwo65(HashMap<String, Object> map);
    List<crop_01_02_table_07_01> getRiceAuditTwo71(HashMap<String, Object> map);
    List<crop_01_02_table_07_02> getRiceAuditTwo72(HashMap<String, Object> map);
    List<crop_01_02_table_07_03> getRiceAuditTwo73(HashMap<String, Object> map);
    List<crop_01_02_table_07_04> getRiceAuditTwo74(HashMap<String, Object> map);
    List<crop_01_02_table_08_01> getRiceAuditTwo81(HashMap<String, Object> map);
    List<crop_01_02_table_08_02> getRiceAuditTwo82(HashMap<String, Object> map);
    List<crop_01_02_table_09_03> getRiceAuditTwo93(HashMap<String, Object> map);
    List<crop_01_02_table_09_04> getRiceAuditTwo94(HashMap<String, Object> map);
    List<crop_01_02_table_09_05> getRiceAuditTwo95(HashMap<String, Object> map);
    List<crop_01_02_table_09_06> getRiceAuditTwo96(HashMap<String, Object> map);
    List<crop_01_02_table_09_07> getRiceAuditTwo97(HashMap<String, Object> map);
    List<crop_01_02_table_10_01> getRiceAuditTwo101(HashMap<String, Object> map);
    List<crop_01_02_table_10_02> getRiceAuditTwo102(HashMap<String, Object> map);


    //    处理双季稻未上报县数据
    List<crop_01_02_table_01_01> getRiceTwo11(String year);
    List<crop_01_02_table_01_02> getRiceTwo12(String year);
    List<crop_01_02_table_03_01> getRiceTwo31(String year);
    List<crop_01_02_table_03_02> getRiceTwo32(String year);
    List<crop_01_02_table_03_03> getRiceTwo33(String year);
    List<crop_01_02_table_03_04> getRiceTwo34(String year);
    List<crop_01_02_table_03_05> getRiceTwo35(String year);
    List<crop_01_02_table_03_06> getRiceTwo36(String year);
    List<crop_01_02_table_04_01> getRiceTwo41(String year);
    List<crop_01_02_table_04_02> getRiceTwo42(String year);
    List<crop_01_02_table_04_03> getRiceTwo43(String year);
    List<crop_01_02_table_04_04> getRiceTwo44(String year);
    List<crop_01_02_table_05_01> getRiceTwo51(String year);
    List<crop_01_02_table_06_01> getRiceTwo61(String year);
    List<crop_01_02_table_06_02> getRiceTwo62(String year);
    List<crop_01_02_table_06_03> getRiceTwo63(String year);
    List<crop_01_02_table_06_04> getRiceTwo64(String year);
    List<crop_01_02_table_06_05> getRiceTwo65(String year);
    List<crop_01_02_table_07_01> getRiceTwo71(String year);
    List<crop_01_02_table_07_02> getRiceTwo72(String year);
    List<crop_01_02_table_07_03> getRiceTwo73(String year);
    List<crop_01_02_table_07_04> getRiceTwo74(String year);
    List<crop_01_02_table_08_01> getRiceTwo81(String year);
    List<crop_01_02_table_08_02> getRiceTwo82(String year);
    List<crop_01_02_table_09_01> getRiceTwo91(String year);
    List<crop_01_02_table_09_02> getRiceTwo92(String year);
    List<crop_01_02_table_09_03> getRiceTwo93(String year);
    List<crop_01_02_table_09_04> getRiceTwo94(String year);
    List<crop_01_02_table_09_05> getRiceTwo95(String year);
    List<crop_01_02_table_09_06> getRiceTwo96(String year);
    List<crop_01_02_table_09_07> getRiceTwo97(String year);
    List<crop_01_02_table_10_01> getRiceTwo101(String year);
    List<crop_01_02_table_10_02> getRiceTwo102(String year);
    List<table_second_name> getTabName();
    //获取双季稻监测点
    List<crop_01_02_table_02_01> getRiceTableTwo21(String year, String county);
    int insertInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo);

    int updateInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo);
    //搜索表1-2未审核数据
    List<crop_01_02_table_01_02> searchRiceAuditTwo12(HashMap<String, Object> map);
    List<crop_01_02_table_01_02> getSearchRiceAuditTwo12(String params1, String year);
    //搜索表的未审核总数
    List<crop_01_02_table_01_01> getSearchRiceAuditTwo11(String params1,String time);
    List<crop_01_02_table_03_01> getSearchRiceAuditTwo31(String params1,String time);
    List<crop_01_02_table_03_02> getSearchRiceAuditTwo32(String params1,String time);
    List<crop_01_02_table_03_03> getSearchRiceAuditTwo33(String params1,String time);
    List<crop_01_02_table_03_04> getSearchRiceAuditTwo34(String params1,String time);
    List<crop_01_02_table_03_05> getSearchRiceAuditTwo35(String params1,String time);
    List<crop_01_02_table_03_06> getSearchRiceAuditTwo36(String params1,String time);
    List<crop_01_02_table_04_01> getSearchRiceAuditTwo41(String params1,String time);
    List<crop_01_02_table_04_02> getSearchRiceAuditTwo42(String params1,String time);
    List<crop_01_02_table_04_03> getSearchRiceAuditTwo43(String params1,String time);
    List<crop_01_02_table_04_04> getSearchRiceAuditTwo44(String params1,String time);
    List<crop_01_02_table_05_01> getSearchRiceAuditTwo51(String params1,String time);
    List<crop_01_02_table_06_01> getSearchRiceAuditTwo61(String params1,String time);
    List<crop_01_02_table_06_02> getSearchRiceAuditTwo62(String params1,String time);
    List<crop_01_02_table_06_03> getSearchRiceAuditTwo63(String params1,String time);
    List<crop_01_02_table_06_04> getSearchRiceAuditTwo64(String params1,String time);
    List<crop_01_02_table_06_05> getSearchRiceAuditTwo65(String params1,String time);
    List<crop_01_02_table_07_01> getSearchRiceAuditTwo71(String params1,String time);
    List<crop_01_02_table_07_02> getSearchRiceAuditTwo72(String params1,String time);
    List<crop_01_02_table_07_03> getSearchRiceAuditTwo73(String params1,String time);
    List<crop_01_02_table_07_04> getSearchRiceAuditTwo74(String params1,String time);
    List<crop_01_02_table_08_01> getSearchRiceAuditTwo81(String params1,String time);
    List<crop_01_02_table_08_02> getSearchRiceAuditTwo82(String params1,String time);
    List<crop_01_02_table_09_03> getSearchRiceAuditTwo93(String params1,String time);
    List<crop_01_02_table_09_04> getSearchRiceAuditTwo94(String params1,String time);
    List<crop_01_02_table_09_05> getSearchRiceAuditTwo95(String params1,String time);
    List<crop_01_02_table_09_06> getSearchRiceAuditTwo96(String params1,String time);
    List<crop_01_02_table_09_07> getSearchRiceAuditTwo97(String params1,String time);
    List<crop_01_02_table_10_01> getSearchRiceAuditTwo101(String params1,String time);
    List<crop_01_02_table_10_02> getSearchRiceAuditTwo102(String params1,String time);

    //删除监测点
    int deleteTableTwo1(String name,String pointCode,String people,int deleteId,String year);
    int deleteTableTwo2(String name,String pointCode,String people,int deleteId,String year);

    //    处理各个监测县未上报表数据
    List<crop_01_02_table_01_01> getTableTwo11(String countyName,String year);
    List<crop_01_02_table_01_02> getTableTwo12(String countyName,String year);
    List<crop_01_02_table_03_01> getTableTwo31(String countyName,String year);
    List<crop_01_02_table_03_02> getTableTwo32(String countyName,String year);
    List<crop_01_02_table_03_03> getTableTwo33(String countyName,String year);
    List<crop_01_02_table_03_04> getTableTwo34(String countyName,String year);
    List<crop_01_02_table_03_05> getTableTwo35(String countyName,String year);
    List<crop_01_02_table_03_06> getTableTwo36(String countyName,String year);
    List<crop_01_02_table_04_01> getTableTwo41(String countyName,String year);
    List<crop_01_02_table_04_02> getTableTwo42(String countyName,String year);
    List<crop_01_02_table_04_03> getTableTwo43(String countyName,String year);
    List<crop_01_02_table_04_04> getTableTwo44(String countyName,String year);
    List<crop_01_02_table_05_01> getTableTwo51(String countyName,String year);
    List<crop_01_02_table_06_01> getTableTwo61(String countyName,String year);
    List<crop_01_02_table_06_02> getTableTwo62(String countyName,String year);
    List<crop_01_02_table_06_03> getTableTwo63(String countyName,String year);
    List<crop_01_02_table_06_04> getTableTwo64(String countyName,String year);
    List<crop_01_02_table_06_05> getTableTwo65(String countyName,String year);
    List<crop_01_02_table_07_01> getTableTwo71(String countyName,String year);
    List<crop_01_02_table_07_02> getTableTwo72(String countyName,String year);
    List<crop_01_02_table_07_03> getTableTwo73(String countyName,String year);
    List<crop_01_02_table_07_04> getTableTwo74(String countyName,String year);
    List<crop_01_02_table_08_01> getTableTwo81(String countyName,String year);
    List<crop_01_02_table_08_02> getTableTwo82(String countyName,String year);
    List<crop_01_02_table_09_03> getTableTwo93(String countyName,String year);
    List<crop_01_02_table_09_04> getTableTwo94(String countyName,String year);
    List<crop_01_02_table_09_05> getTableTwo95(String countyName,String year);
    List<crop_01_02_table_09_06> getTableTwo96(String countyName,String year);
    List<crop_01_02_table_09_07> getTableTwo97(String countyName,String year);
    List<crop_01_02_table_10_01> getTableTwo101(String countyName,String year);
    List<crop_01_02_table_10_02> getTableTwo102(String countyName,String year);

   ////双季稻表4-2搜索查询新增在各区内部看总的数据
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
