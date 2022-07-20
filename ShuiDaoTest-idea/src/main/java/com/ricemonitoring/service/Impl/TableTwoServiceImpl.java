package com.ricemonitoring.service.Impl;



import com.ricemonitoring.mapper.TableTwoMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.TableTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TableTwoServiceImpl implements TableTwoService{
    @Autowired
    TableTwoMapper tableTwoMapper;

    @Override
    public List<sum_select_list> getCounty2() {

        return tableTwoMapper.getCounty2();
    }
    //监测点审核
    @Override
    public List<crop_01_02_table_02_01> getPointData2(String year) {
        //System.out.println("data:"+tableTwoMapper.getPointData2(year));
        return tableTwoMapper.getPointData2(year);
    }
    @Override
    public String noPassPoint2(String recordId) {
        return tableTwoMapper.noPassPoint2(recordId);
    }
    @Override
    public int PassPoint2(String pointCode,String recordId) {
        ////System.out.println(recordId+"——" +pointCode);
        return tableTwoMapper.PassPoint2(pointCode,recordId);
    }

    @Override
    public List<sum_select_list> getCitys2() {
        return tableTwoMapper.getCitys2();
    }

    //双季稻表1-1搜索查询
    @Override
    public List<crop_01_02_table_01_01> getRiceTableTwo11(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo11(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_01_01> getAuditRiceTableTwo11(String year) {
        return tableTwoMapper.getAuditRiceTableTwo11(year);
    }
    @Override
    public List<crop_01_02_table_01_01> getSeekInfoTwo11(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo11(county,areaType,riceType,year);
    }

    //双季稻表1-2搜索查询
    @Override
    public List<crop_01_02_table_01_02> getRiceTableTwo12(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo12(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_01_02> getAuditRiceTableTwo12(String year) {
        return tableTwoMapper.getAuditRiceTableTwo12(year);
    }
    @Override
    public List<crop_01_02_table_01_02> getSeekInfoTwo12(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo12(county,areaType,riceType,year);
    }

    //双季稻表2-2搜索查询
    @Override
    public List<crop_01_02_table_02_02> getRiceTableTwo22(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo22(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_02_02> getAuditRiceTableTwo22(String year) {
        return tableTwoMapper.getAuditRiceTableTwo22(year);
    }
    @Override
    public List<crop_01_02_table_02_02> getSeekInfoTwo22(String county, String city,String year) {
        return tableTwoMapper.getSeekInfoTwo22(county,city,year);
    }


    //双季稻表3-1搜索查询
    @Override
    public List<crop_01_02_table_03_01> getRiceTableTwo31(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo31(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_01> getAuditRiceTableTwo31(String year) {
        return tableTwoMapper.getAuditRiceTableTwo31(year);
    }
    @Override
    public List<crop_01_02_table_03_01> getSeekInfoTwo31(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo31(county,areaType,riceType,year);
    }

    //双季稻表3-2搜索查询
    @Override
    public List<crop_01_02_table_03_02> getRiceTableTwo32(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo32(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_02> getAuditRiceTableTwo32(String year) {
        return tableTwoMapper.getAuditRiceTableTwo32(year);
    }
    @Override
    public List<crop_01_02_table_03_02> getSeekInfoTwo32(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo32(county,areaType,riceType,year);
    }

    //双季稻表3-3搜索查询
    @Override
    public List<crop_01_02_table_03_03> getRiceTableTwo33(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo33(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_03> getAuditRiceTableTwo33(String year) {
        return tableTwoMapper.getAuditRiceTableTwo33(year);
    }
    @Override
    public List<crop_01_02_table_03_03> getSeekInfoTwo33(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo33(county,areaType,riceType,year);
    }

    //双季稻表3-4搜索查询
    @Override
    public List<crop_01_02_table_03_04> getRiceTableTwo34(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo34(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_04> getAuditRiceTableTwo34(String year) {
        return tableTwoMapper.getAuditRiceTableTwo34(year);
    }
    @Override
    public List<crop_01_02_table_03_04> getSeekInfoTwo34(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo34(county,areaType,riceType,year);
    }

    //双季稻表3-5搜索查询
    @Override
    public List<crop_01_02_table_03_05> getRiceTableTwo35(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo35(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_05> getAuditRiceTableTwo35(String year) {
        return tableTwoMapper.getAuditRiceTableTwo35(year);
    }
    @Override
    public List<crop_01_02_table_03_05> getSeekInfoTwo35(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo35(county,areaType,riceType,year);
    }

    //双季稻表3-6搜索查询
    @Override
    public List<crop_01_02_table_03_06> getRiceTableTwo36(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo36(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_03_06> getAuditRiceTableTwo36(String year) {
        return tableTwoMapper.getAuditRiceTableTwo36(year);
    }
    @Override
    public List<crop_01_02_table_03_06> getSeekInfoTwo36(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo36(county,areaType,riceType,year);
    }

    //双季稻表4-1搜索查询
    @Override
    public List<crop_01_02_table_04_01> getRiceTableTwo41(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo41(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_04_01> getAuditRiceTableTwo41(String year) {
        return tableTwoMapper.getAuditRiceTableTwo41(year);
    }
    @Override
    public List<crop_01_02_table_04_01> getSeekInfoTwo41(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo41(county,areaType,riceType,category,plantWay,year);
    }

    //双季稻表4-2搜索查询
    @Override
    public List<crop_01_02_table_04_02> getRiceTableTwo42(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo42(county,areaType,year);
    }
    //双季稻表4-2搜索查询新增在各区内部看总的数据
    @Override
    public List<crop_01_02_table_04_02> getRiceTableTwo42new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo42new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_04_02> getAuditRiceTableTwo42(String year) {
        return tableTwoMapper.getAuditRiceTableTwo42(year);
    }
    @Override
    public List<crop_01_02_table_04_02> getSeekInfoTwo42(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo42(county,areaType,riceType,category,plantWay,year);
    }

    //双季稻表4-3搜索查询
    @Override
    public List<crop_01_02_table_04_03> getRiceTableTwo43(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo43(area,areaType,year);
    }
    //双季稻表4-3搜索查询
    @Override
    public List<crop_01_02_table_04_03> getRiceTableTwo43new(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo43new(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_04_03> getAuditRiceTableTwo43(String year) {
        return tableTwoMapper.getAuditRiceTableTwo43(year);
    }
    @Override
    public List<crop_01_02_table_04_03> getSeekInfoTwo43(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo43(county,areaType,riceType,category,plantWay,year);
    }

    //双季稻表4-4搜索查询
    @Override
    public List<crop_01_02_table_04_04> getRiceTableTwo44(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo44(county,areaType,year);
    }
    //双季稻表4-4搜索查询
    @Override
    public List<crop_01_02_table_04_04> getRiceTableTwo44new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo44new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_04_04> getAuditRiceTableTwo44(String year) {
        return tableTwoMapper.getAuditRiceTableTwo44(year);
    }
    @Override
    public List<crop_01_02_table_04_04> getSeekInfoTwo44(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo44(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表5-1搜索查询
    @Override
    public List<crop_01_02_table_05_01> getRiceTableTwo51(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo51(county,areaType,year);
    }
    //双季稻表5-1搜索查询
    @Override
    public List<crop_01_02_table_05_01> getRiceTableTwo51new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo51new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_05_01> getAuditRiceTableTwo51(String year) {
        return tableTwoMapper.getAuditRiceTableTwo51(year);
    }
    @Override
    public List<crop_01_02_table_05_01> getSeekInfoTwo51(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo51(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表6-1搜索查询
    @Override
    public List<crop_01_02_table_06_01> getRiceTableTwo61(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo61(county,areaType,year);
    }
    //双季稻表6-1搜索查询
    @Override
    public List<crop_01_02_table_06_01> getRiceTableTwo61new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo61new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_01> getAuditRiceTableTwo61(String year) {
        return tableTwoMapper.getAuditRiceTableTwo61(year);
    }
    @Override
    public List<crop_01_02_table_06_01> getSeekInfoTwo61(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo61(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表6-2搜索查询
    @Override
    public List<crop_01_02_table_06_02> getRiceTableTwo62(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo62(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_02> getRiceTableTwo62new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo62new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_02> getAuditRiceTableTwo62(String year) {
        return tableTwoMapper.getAuditRiceTableTwo62(year);
    }
    @Override
    public List<crop_01_02_table_06_02> getSeekInfoTwo62(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo62(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表6-3搜索查询
    @Override
    public List<crop_01_02_table_06_03> getRiceTableTwo63(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo63(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_03> getRiceTableTwo63new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo63new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_03> getAuditRiceTableTwo63(String year) {
        return tableTwoMapper.getAuditRiceTableTwo63(year);
    }
    @Override
    public List<crop_01_02_table_06_03> getSeekInfoTwo63(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo63(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表6-4搜索查询
    @Override
    public List<crop_01_02_table_06_04> getRiceTableTwo64(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo64(county,areaType,year);
    }
    //双季稻表6-4搜索查询
    @Override
    public List<crop_01_02_table_06_04> getRiceTableTwo64new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo64new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_04> getAuditRiceTableTwo64(String year) {
        return tableTwoMapper.getAuditRiceTableTwo64(year);
    }
    @Override
    public List<crop_01_02_table_06_04> getSeekInfoTwo64(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo64(county,areaType,riceType,category,plantWay,year);
    }


    //双季稻表6-5搜索查询
    @Override
    public List<crop_01_02_table_06_05> getRiceTableTwo65(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo65(county,areaType,year);
    }
    //双季稻表6-5搜索查询
    @Override
    public List<crop_01_02_table_06_05> getRiceTableTwo65new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo65new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_06_05> getAuditRiceTableTwo65(String year) {
        return tableTwoMapper.getAuditRiceTableTwo65(year);
    }
    @Override
    public List<crop_01_02_table_06_05> getSeekInfoTwo65(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo65(county,areaType,riceType,category,plantWay,year);
    }

    //双季稻表7-1搜索查询
    @Override
    public List<crop_01_02_table_07_01> getRiceTableTwo71(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo71(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_07_01> getAuditRiceTableTwo71(String year) {
        return tableTwoMapper.getAuditRiceTableTwo71(year);
    }
    @Override
    public List<crop_01_02_table_07_01> getSeekInfoTwo71(String county, String areaType,String year) {
        return tableTwoMapper.getSeekInfoTwo71(county,areaType,year);
    }

    //双季稻表7-2搜索查询
    @Override
    public List<crop_01_02_table_07_02> getRiceTableTwo72(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo72(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_07_02> getAuditRiceTableTwo72(String year) {
        return tableTwoMapper.getAuditRiceTableTwo72(year);
    }
    @Override
    public List<crop_01_02_table_07_02> getSeekInfoTwo72(String county, String areaType,String year) {
        return tableTwoMapper.getSeekInfoTwo72(county,areaType,year);
    }

    //双季稻表7-3搜索查询
    @Override
    public List<crop_01_02_table_07_03> getRiceTableTwo73(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo73(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_07_03> getAuditRiceTableTwo73(String year) {
        return tableTwoMapper.getAuditRiceTableTwo73(year);
    }
    @Override
    public List<crop_01_02_table_07_03> getSeekInfoTwo73(String county, String areaType,String year) {
        return tableTwoMapper.getSeekInfoTwo73(county,areaType,year);
    }

    //双季稻表7-4搜索查询
    @Override
    public List<crop_01_02_table_07_04> getRiceTableTwo74(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo74(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_07_04> getAuditRiceTableTwo74(String year) {
        return tableTwoMapper.getAuditRiceTableTwo74(year);
    }
    @Override
    public List<crop_01_02_table_07_04> getSeekInfoTwo74(String county, String areaType, String riceType,String year) {
        return tableTwoMapper.getSeekInfoTwo74(county,areaType,riceType,year);
    }

    //双季稻表8-1搜索查询
    @Override
    public List<crop_01_02_table_08_01> getRiceTableTwo81(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo81(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_08_01> getRiceTableTwo81new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo81new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_08_01> getAuditRiceTableTwo81(String year) {
        return tableTwoMapper.getAuditRiceTableTwo81(year);
    }
    @Override
    public List<crop_01_02_table_08_01> getSeekInfoTwo81(String county,String year) {
        return tableTwoMapper.getSeekInfoTwo81(county,year);
    }

    //双季稻表8-2搜索查询
    @Override
    public List<crop_01_02_table_08_02> getRiceTableTwo82(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo82(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_08_02> getRiceTableTwo82new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo82new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_08_02> getAuditRiceTableTwo82(String year) {
        return tableTwoMapper.getAuditRiceTableTwo82(year);
    }
    @Override
    public List<crop_01_02_table_08_02> getSeekInfoTwo82(String county,String year) {
        return tableTwoMapper.getSeekInfoTwo82(county,year);
    }


    //双季稻表9-1搜索查询
    @Override
    public List<crop_01_02_table_09_01> getRiceTableTwo91(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo91(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_01> getAuditRiceTableTwo91(String year) {
        return tableTwoMapper.getAuditRiceTableTwo91(year);
    }
    @Override
    public List<crop_01_02_table_09_01> getSeekInfoTwo91(String county,String year) {
        return tableTwoMapper.getSeekInfoTwo91(county,year);
    }


    //双季稻表9-2搜索查询
    @Override
    public List<crop_01_02_table_09_02> getRiceTableTwo92(String currentYear,String previousYear,String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo92(currentYear,previousYear,county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_02> getAuditRiceTableTwo92(String currentYear,String year) {
        return tableTwoMapper.getAuditRiceTableTwo92(currentYear,year);
    }
    @Override
    public List<crop_01_02_table_09_02> getSeekInfoTwo92(String county,String currentYear,String previousYear,String year) {
        return tableTwoMapper.getSeekInfoTwo92(county,currentYear,previousYear,year);
    }


    //双季稻表9-3搜索查询
    @Override
    public List<crop_01_02_table_09_03> getRiceTableTwo93(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo93(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_03> getAuditRiceTableTwo93(String year) {
        return tableTwoMapper.getAuditRiceTableTwo93(year);
    }
    @Override
    public List<crop_01_02_table_09_03> getSeekInfoTwo93(String county, String areaType, String riceType, String category,String year) {
        return tableTwoMapper.getSeekInfoTwo93(county,areaType,riceType,category,year);
    }


    //双季稻表9-4搜索查询
    @Override
    public List<crop_01_02_table_09_04> getRiceTableTwo94(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo94(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_04> getAuditRiceTableTwo94(String year) {
        return tableTwoMapper.getAuditRiceTableTwo94(year);
    }
    @Override
    public List<crop_01_02_table_09_04> getSeekInfoTwo94(String county, String areaType, String riceType, String category,String year) {
        return tableTwoMapper.getSeekInfoTwo94(county,areaType,riceType,category,year);
    }


    //双季稻表9-5搜索查询
    @Override
    public List<crop_01_02_table_09_05> getRiceTableTwo95(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo95(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_05> getAuditRiceTableTwo95(String year) {
        return tableTwoMapper.getAuditRiceTableTwo95(year);
    }
    @Override
    public List<crop_01_02_table_09_05> getSeekInfoTwo95(String county, String areaType, String riceType, String category,String year) {
        return tableTwoMapper.getSeekInfoTwo95(county,areaType,riceType,category,year);
    }


    //双季稻表9-6搜索查询
    @Override
    public List<crop_01_02_table_09_06> getRiceTableTwo96(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo96(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_06> getAuditRiceTableTwo96(String year) {
        return tableTwoMapper.getAuditRiceTableTwo96(year);
    }
    @Override
    public List<crop_01_02_table_09_06> getSeekInfoTwo96(String county, String areaType, String riceType, String category,String year) {
        return tableTwoMapper.getSeekInfoTwo96(county,areaType,riceType,category,year);
    }
    //双季稻表9-7搜索查询
    @Override
    public List<crop_01_02_table_09_07> getRiceTableTwo97(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo97(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_07> getRiceTableTwo97new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo97new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_09_07> getAuditRiceTableTwo97(String year) {
        return tableTwoMapper.getAuditRiceTableTwo97(year);
    }
    @Override
    public List<crop_01_02_table_09_07> getSeekInfoTwo97(String county, String areaType, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo97(county,areaType,plantWay,year);
    }

    //双季稻表10-1搜索查询
    @Override
    public List<crop_01_02_table_10_01> getRiceTableTwo101(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo101(county,areaType,year);
    }
    @Override
    public List<crop_01_02_table_10_01> getRiceTableTwo101new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo101new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_10_01> getAuditRiceTableTwo101(String year) {
        return tableTwoMapper.getAuditRiceTableTwo101(year);
    }
    @Override
    public List<crop_01_02_table_10_01> getSeekInfoTwo101(String county, String areaType, String riceType, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo101(county,areaType,riceType,plantWay,year);
    }

    //双季稻表10-2搜索查询
    @Override
    public List<crop_01_02_table_10_02> getRiceTableTwo102(String county, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo102(county,areaType,year);
    }
    //双季稻表10-2搜索查询
    @Override
    public List<crop_01_02_table_10_02> getRiceTableTwo102new(String area, String areaType,String year) {
        return tableTwoMapper.getRiceTableTwo102new(area,areaType,year);
    }
    @Override
    public List<crop_01_02_table_10_02> getAuditRiceTableTwo102(String year) {
        return tableTwoMapper.getAuditRiceTableTwo102(year);
    }
    @Override
    public List<crop_01_02_table_10_02> getSeekInfoTwo102(String county, String areaType, String riceType, String plantWay,String year) {
        return tableTwoMapper.getSeekInfoTwo102(county,areaType,riceType,plantWay,year);
    }


    @Override
    public int getAuditData2(String city, String name, String chinese, int dataId, String times,String auditPeople) {
        return tableTwoMapper.getAuditData2(city,name,chinese,dataId,times,auditPeople);
    }

    @Override
    public int deleteRepeatData2() {
        return tableTwoMapper.deleteRepeatData2();
    }

    @Override
    public List<table_audit_2> getNoAuditAllData2() {
        return tableTwoMapper.getNoAuditAllData2();
    }

    @Override
    public pageBean<table_audit_2> getNoAuditInfo2(String params1, int start, int pageSize) {
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<table_audit_2> backInfo = new pageBean<table_audit_2>();
        //封装当前页
        backInfo.setCurrPage(start);
        //封装页码
        backInfo.setPageSize(pageSize);
        //封装总记录数
        int total = (tableTwoMapper.getNoAuditAllInfo2(params1).size());
        backInfo.setTotalCount(total);
        //封装总页码
        double totalPage=total;
        Double num=Math.ceil(totalPage/pageSize);
        backInfo.setTotalPage(num.intValue());
        //传参
        map.put("params1",params1);
        map.put("start",(start-1)*pageSize);
        map.put("size",backInfo.getPageSize());
        //封装每页显示的数据
        List<table_audit_2> lists= tableTwoMapper.getNoAuditAllDataMap2(map);
        backInfo.setLists(lists);
        return backInfo;
        //return tableMapper.getNoAuditAllData();
    }


    @Override
    public int deleteRecord2(int rowId, String rowName, String rowTime) {
        return tableTwoMapper.deleteRecord2(rowId,rowName,rowTime);
    };

    @Override
    public List<crop_01_02_table_04_04> getPeriod2(String year) {
        return tableTwoMapper.getPeriod2(year);
    }

    //处理未审核各表数据
    @Override
    public pageBean<? extends Object> getRiceTwoAudit1(String params1, String riceTableName, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<? extends Object> info= new pageBean<>();
        if(riceTableName.equals("crop_01_02_table_01_01")){
            pageBean<crop_01_02_table_01_01> TableInfo = new pageBean<crop_01_02_table_01_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo11(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_01_01> lists= tableTwoMapper.getRiceAuditTwo11(map);
            TableInfo.setLists(lists);
            //System.out.println("lists"+lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_01_02")){
            pageBean<crop_01_02_table_01_02> TableInfo = new pageBean<crop_01_02_table_01_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo12(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_01_02> lists= tableTwoMapper.getRiceAuditTwo12(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_01")){
            pageBean<crop_01_02_table_03_01> TableInfo = new pageBean<crop_01_02_table_03_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo31(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_01> lists= tableTwoMapper.getRiceAuditTwo31(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_02")){
            pageBean<crop_01_02_table_03_02> TableInfo = new pageBean<crop_01_02_table_03_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo32(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_02> lists= tableTwoMapper.getRiceAuditTwo32(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_03")){
            pageBean<crop_01_02_table_03_03> TableInfo = new pageBean<crop_01_02_table_03_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo33(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_03> lists= tableTwoMapper.getRiceAuditTwo33(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_04")){
            pageBean<crop_01_02_table_03_04> TableInfo = new pageBean<crop_01_02_table_03_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo34(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_04> lists= tableTwoMapper.getRiceAuditTwo34(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_05")){
            pageBean<crop_01_02_table_03_05> TableInfo = new pageBean<crop_01_02_table_03_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo35(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_05> lists= tableTwoMapper.getRiceAuditTwo35(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_06")){
            pageBean<crop_01_02_table_03_06> TableInfo = new pageBean<crop_01_02_table_03_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo36(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_06> lists= tableTwoMapper.getRiceAuditTwo36(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_01")){
            pageBean<crop_01_02_table_04_01> TableInfo = new pageBean<crop_01_02_table_04_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo41(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_01> lists= tableTwoMapper.getRiceAuditTwo41(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_02")){
            pageBean<crop_01_02_table_04_02> TableInfo = new pageBean<crop_01_02_table_04_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo42(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_02> lists= tableTwoMapper.getRiceAuditTwo42(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_03")){
            pageBean<crop_01_02_table_04_03> TableInfo = new pageBean<crop_01_02_table_04_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo43(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_03> lists= tableTwoMapper.getRiceAuditTwo43(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_04")){
            pageBean<crop_01_02_table_04_04> TableInfo = new pageBean<crop_01_02_table_04_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo44(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_04> lists= tableTwoMapper.getRiceAuditTwo44(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_05_01")){
            pageBean<crop_01_02_table_05_01> TableInfo = new pageBean<crop_01_02_table_05_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo51(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_05_01> lists= tableTwoMapper.getRiceAuditTwo51(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_01")){
            pageBean<crop_01_02_table_06_01> TableInfo = new pageBean<crop_01_02_table_06_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo61(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_01> lists= tableTwoMapper.getRiceAuditTwo61(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_02")){
            pageBean<crop_01_02_table_06_02> TableInfo = new pageBean<crop_01_02_table_06_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo62(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_02> lists= tableTwoMapper.getRiceAuditTwo62(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_03")){
            pageBean<crop_01_02_table_06_03> TableInfo = new pageBean<crop_01_02_table_06_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo63(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_03> lists= tableTwoMapper.getRiceAuditTwo63(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_04")){
            pageBean<crop_01_02_table_06_04> TableInfo = new pageBean<crop_01_02_table_06_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo64(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_04> lists= tableTwoMapper.getRiceAuditTwo64(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_05")){
            pageBean<crop_01_02_table_06_05> TableInfo = new pageBean<crop_01_02_table_06_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo65(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_05> lists= tableTwoMapper.getRiceAuditTwo65(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_01")){
            pageBean<crop_01_02_table_07_01> TableInfo = new pageBean<crop_01_02_table_07_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo71(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_01> lists= tableTwoMapper.getRiceAuditTwo71(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_02")){
            pageBean<crop_01_02_table_07_02> TableInfo = new pageBean<crop_01_02_table_07_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo72(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_02> lists= tableTwoMapper.getRiceAuditTwo72(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_03")){
            pageBean<crop_01_02_table_07_03> TableInfo = new pageBean<crop_01_02_table_07_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo73(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_03> lists= tableTwoMapper.getRiceAuditTwo73(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_04")){
            pageBean<crop_01_02_table_07_04> TableInfo = new pageBean<crop_01_02_table_07_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo74(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_04> lists= tableTwoMapper.getRiceAuditTwo74(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_08_01")){
            pageBean<crop_01_02_table_08_01> TableInfo = new pageBean<crop_01_02_table_08_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo81(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_08_01> lists= tableTwoMapper.getRiceAuditTwo81(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_08_02")){
            pageBean<crop_01_02_table_08_02> TableInfo = new pageBean<crop_01_02_table_08_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo82(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_08_02> lists= tableTwoMapper.getRiceAuditTwo82(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_03")){
            pageBean<crop_01_02_table_09_03> TableInfo = new pageBean<crop_01_02_table_09_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo93(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_03> lists= tableTwoMapper.getRiceAuditTwo93(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_04")){
            pageBean<crop_01_02_table_09_04> TableInfo = new pageBean<crop_01_02_table_09_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo94(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_04> lists= tableTwoMapper.getRiceAuditTwo94(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_05")){
            pageBean<crop_01_02_table_09_05> TableInfo = new pageBean<crop_01_02_table_09_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo95(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_05> lists= tableTwoMapper.getRiceAuditTwo95(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_06")){
            pageBean<crop_01_02_table_09_06> TableInfo = new pageBean<crop_01_02_table_09_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo96(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_06> lists= tableTwoMapper.getRiceAuditTwo96(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_07")){
            pageBean<crop_01_02_table_09_07> TableInfo = new pageBean<crop_01_02_table_09_07>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo97(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_07> lists= tableTwoMapper.getRiceAuditTwo97(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_10_01")){
            pageBean<crop_01_02_table_10_01> TableInfo = new pageBean<crop_01_02_table_10_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo101(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_10_01> lists= tableTwoMapper.getRiceAuditTwo101(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_10_02")){
            pageBean<crop_01_02_table_10_02> TableInfo = new pageBean<crop_01_02_table_10_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getAuditRiceTableTwo102(year).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",year);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_10_02> lists= tableTwoMapper.getRiceAuditTwo102(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        };
        //System.out.println("INFO:"+info);
        return info;
    }
    @Override
    public pageBean<? extends Object> searchRiceAuditTwo1(String params1,String month,  String riceTableName, int start, int pageSize, String year) {
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<? extends Object> info= new pageBean<>();
        StringBuilder Str = new StringBuilder();
        if(month != null || month !=""){
            Str.append(year).append("-").append(month);
        }else{
            Str.append(year);
        };
        String time = Str.toString();
        System.out.println(time);
        if(riceTableName.equals("crop_01_02_table_01_01")){
            pageBean<crop_01_02_table_01_01> TableInfo = new pageBean<crop_01_02_table_01_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo11(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_01_01> lists= tableTwoMapper.getRiceAuditTwo11(map);
            TableInfo.setLists(lists);
            System.out.println("lists"+lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_01")){
            pageBean<crop_01_02_table_03_01> TableInfo = new pageBean<crop_01_02_table_03_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo31(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_01> lists= tableTwoMapper.getRiceAuditTwo31(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_02")){
            pageBean<crop_01_02_table_03_02> TableInfo = new pageBean<crop_01_02_table_03_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo32(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_02> lists= tableTwoMapper.getRiceAuditTwo32(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_03")){
            pageBean<crop_01_02_table_03_03> TableInfo = new pageBean<crop_01_02_table_03_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo33(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_03> lists= tableTwoMapper.getRiceAuditTwo33(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_04")){
            pageBean<crop_01_02_table_03_04> TableInfo = new pageBean<crop_01_02_table_03_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo34(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_04> lists= tableTwoMapper.getRiceAuditTwo34(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_05")){
            pageBean<crop_01_02_table_03_05> TableInfo = new pageBean<crop_01_02_table_03_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo35(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_05> lists= tableTwoMapper.getRiceAuditTwo35(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_03_06")){
            pageBean<crop_01_02_table_03_06> TableInfo = new pageBean<crop_01_02_table_03_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo36(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_03_06> lists= tableTwoMapper.getRiceAuditTwo36(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_01")){
            pageBean<crop_01_02_table_04_01> TableInfo = new pageBean<crop_01_02_table_04_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo41(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_01> lists= tableTwoMapper.getRiceAuditTwo41(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_02")){
            pageBean<crop_01_02_table_04_02> TableInfo = new pageBean<crop_01_02_table_04_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo42(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_02> lists= tableTwoMapper.getRiceAuditTwo42(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_03")){
            pageBean<crop_01_02_table_04_03> TableInfo = new pageBean<crop_01_02_table_04_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo43(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_03> lists= tableTwoMapper.getRiceAuditTwo43(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_04_04")){
            pageBean<crop_01_02_table_04_04> TableInfo = new pageBean<crop_01_02_table_04_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo44(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_04_04> lists= tableTwoMapper.getRiceAuditTwo44(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_05_01")){
            pageBean<crop_01_02_table_05_01> TableInfo = new pageBean<crop_01_02_table_05_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo51(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_05_01> lists= tableTwoMapper.getRiceAuditTwo51(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_01")){
            pageBean<crop_01_02_table_06_01> TableInfo = new pageBean<crop_01_02_table_06_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo61(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_01> lists= tableTwoMapper.getRiceAuditTwo61(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_02")){
            pageBean<crop_01_02_table_06_02> TableInfo = new pageBean<crop_01_02_table_06_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo62(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_02> lists= tableTwoMapper.getRiceAuditTwo62(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_03")){
            pageBean<crop_01_02_table_06_03> TableInfo = new pageBean<crop_01_02_table_06_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo63(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_03> lists= tableTwoMapper.getRiceAuditTwo63(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_04")){
            pageBean<crop_01_02_table_06_04> TableInfo = new pageBean<crop_01_02_table_06_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo64(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_04> lists= tableTwoMapper.getRiceAuditTwo64(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_06_05")){
            pageBean<crop_01_02_table_06_05> TableInfo = new pageBean<crop_01_02_table_06_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo65(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_06_05> lists= tableTwoMapper.getRiceAuditTwo65(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_01")){
            pageBean<crop_01_02_table_07_01> TableInfo = new pageBean<crop_01_02_table_07_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo71(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_01> lists= tableTwoMapper.getRiceAuditTwo71(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_02")){
            pageBean<crop_01_02_table_07_02> TableInfo = new pageBean<crop_01_02_table_07_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo72(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_02> lists= tableTwoMapper.getRiceAuditTwo72(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_03")){
            pageBean<crop_01_02_table_07_03> TableInfo = new pageBean<crop_01_02_table_07_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo73(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_03> lists= tableTwoMapper.getRiceAuditTwo73(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_07_04")){
            pageBean<crop_01_02_table_07_04> TableInfo = new pageBean<crop_01_02_table_07_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo74(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_07_04> lists= tableTwoMapper.getRiceAuditTwo74(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_08_01")){
            pageBean<crop_01_02_table_08_01> TableInfo = new pageBean<crop_01_02_table_08_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo81(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_08_01> lists= tableTwoMapper.getRiceAuditTwo81(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_08_02")){
            pageBean<crop_01_02_table_08_02> TableInfo = new pageBean<crop_01_02_table_08_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo82(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_08_02> lists= tableTwoMapper.getRiceAuditTwo82(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_03")){
            pageBean<crop_01_02_table_09_03> TableInfo = new pageBean<crop_01_02_table_09_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo93(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_03> lists= tableTwoMapper.getRiceAuditTwo93(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_04")){
            pageBean<crop_01_02_table_09_04> TableInfo = new pageBean<crop_01_02_table_09_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo94(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_04> lists= tableTwoMapper.getRiceAuditTwo94(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_05")){
            pageBean<crop_01_02_table_09_05> TableInfo = new pageBean<crop_01_02_table_09_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo95(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_05> lists= tableTwoMapper.getRiceAuditTwo95(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_06")){
            pageBean<crop_01_02_table_09_06> TableInfo = new pageBean<crop_01_02_table_09_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo96(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_06> lists= tableTwoMapper.getRiceAuditTwo96(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_09_07")){
            pageBean<crop_01_02_table_09_07> TableInfo = new pageBean<crop_01_02_table_09_07>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo97(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_09_07> lists= tableTwoMapper.getRiceAuditTwo97(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_10_01")){
            pageBean<crop_01_02_table_10_01> TableInfo = new pageBean<crop_01_02_table_10_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo101(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_10_01> lists= tableTwoMapper.getRiceAuditTwo101(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_02_table_10_02")){
            pageBean<crop_01_02_table_10_02> TableInfo = new pageBean<crop_01_02_table_10_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableTwoMapper.getSearchRiceAuditTwo102(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("year",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_02_table_10_02> lists= tableTwoMapper.getRiceAuditTwo102(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        };
        System.out.println("INFO:"+info);
        return info;
    }
    //获取双季稻监测点
    @Override
    public List<crop_01_02_table_02_01> getRiceTableTwo21(String year, String county) {
        return tableTwoMapper.getRiceTableTwo21(year,county);
    }
    @Override
    public int insertInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo) {
        return tableTwoMapper.insertInfoToRiceTableTwo0202(insertInfo);
    }
    @Override
    public int updateInfoToRiceTableTwo0202(crop_01_02_table_02_01 insertInfo) {
        return tableTwoMapper.updateInfoToRiceTableTwo0202(insertInfo);
    }

    //获取未审核表1-2数据
    @Override
    public ArrayList<HashMap<String,Object> > getRiceTableAuditTwo12(String params1, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        //处理表1-2数据
        //传参
        map.put("params1", params1);
        map.put("year", year);
        //取出表1-2所有值
        List<crop_01_02_table_01_02> lists = tableTwoMapper.getRiceAuditTwo12(map);
        //System.out.println(lists);
        ////封装总记录数
        List<crop_01_02_table_01_02> list2 = tableTwoMapper.getAuditRiceTableTwo12(year);
        int total1 = list2.size();
        String[] countyArray = new String[total1];
        String[] areaArray = new String[total1];
        String[] peopleArray = new String[total1];
        for(int i=0; i<total1;i ++){
            countyArray[i] = list2.get(i).getCounty();
            areaArray[i] = list2.get(i).getCounty_area();
            peopleArray[i] = list2.get(i).getPeople();
        };
        ArrayList<HashMap<String,Object> > temp = new ArrayList<>();
        for(int i=0; i<countyArray.length; i++){
            ArrayList<Object> temp1 = new ArrayList<>();
            for(int j=0;j<lists.size();j++){
                if(lists.get(j).getCounty().equals(countyArray[i])){
                    temp1.add(lists.get(j));
                }
            }
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("id",String.valueOf(i+1));
            map1.put("county",countyArray[i]);
            map1.put("area",areaArray[i]);
            map1.put("people",peopleArray[i]);
            map1.put("variety",temp1);
            ////System.out.println("品种:"+map1.get("variety"));
            temp.add(map1);
        }
        //System.out.println("temp:"+temp);

        return temp;
    }
    //搜索未审核表1-2数据
    @Override
    public ArrayList<HashMap<String,Object> > searchRiceAuditTwo12(String params1,String month, String riceTableName, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        //处理表1-2数据
        //传参
        map.put("params1", params1);
        map.put("year", year);
        //取出表1-2所有值
        List<crop_01_02_table_01_02> lists = tableTwoMapper.searchRiceAuditTwo12(map);
        //System.out.println(lists);
        ////封装总记录数
        List<crop_01_02_table_01_02> list2 = tableTwoMapper.getSearchRiceAuditTwo12(params1,year);
        int total1 = list2.size();
        String[] countyArray = new String[total1];
        String[] areaArray = new String[total1];
        String[] peopleArray = new String[total1];
        for(int i=0; i<total1;i ++){
            countyArray[i] = list2.get(i).getCounty();
            areaArray[i] = list2.get(i).getCounty_area();
            peopleArray[i] = list2.get(i).getPeople();
        };
        ArrayList<HashMap<String,Object> > temp = new ArrayList<>();
        for(int i=0; i<countyArray.length; i++){
            ArrayList<Object> temp1 = new ArrayList<>();
            for(int j=0;j<lists.size();j++){
                if(lists.get(j).getCounty().equals(countyArray[i])){
                    temp1.add(lists.get(j));
                }
            }
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("id",String.valueOf(i+1));
            map1.put("county",countyArray[i]);
            map1.put("area",areaArray[i]);
            map1.put("people",peopleArray[i]);
            map1.put("variety",temp1);
            ////System.out.println("品种:"+map1.get("variety"));
            temp.add(map1);
        }
        //System.out.println("temp:"+temp);
        return temp;
    }

    @Override
    public String[] getCurrentCounty2(String county,String year) {

        List<crop_01_02_table_09_02> list92 = tableTwoMapper.getCurrentCounty2(year,county);
        String[] data1 = new String[list92.size()];
        for(int i=0; i<list92.size();i++){
            data1[i] = list92.get(i).getField_1_1();
        }
        return data1;
    }


    @Override
    public List<HashMap<String, Object>> getNoReportData2() {
        //获取本年度年份
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_01_01> two11 = tableTwoMapper.getRiceTwo11(year);
        List<crop_01_02_table_01_02> two12 = tableTwoMapper.getRiceTwo12(year);
        List<crop_01_02_table_03_01> two31 = tableTwoMapper.getRiceTwo31(year);
        List<crop_01_02_table_03_02> two32 = tableTwoMapper.getRiceTwo32(year);
        List<crop_01_02_table_03_03> two33 = tableTwoMapper.getRiceTwo33(year);
        List<crop_01_02_table_03_04> two34 = tableTwoMapper.getRiceTwo34(year);
        List<crop_01_02_table_03_05> two35 = tableTwoMapper.getRiceTwo35(year);
        List<crop_01_02_table_03_06> two36 = tableTwoMapper.getRiceTwo36(year);
        List<crop_01_02_table_04_01> two41 = tableTwoMapper.getRiceTwo41(year);
        List<crop_01_02_table_04_02> two42 = tableTwoMapper.getRiceTwo42(year);
        List<crop_01_02_table_04_03> two43 = tableTwoMapper.getRiceTwo43(year);
        List<crop_01_02_table_04_04> two44 = tableTwoMapper.getRiceTwo44(year);
        List<crop_01_02_table_05_01> two51 = tableTwoMapper.getRiceTwo51(year);
        List<crop_01_02_table_06_01> two61 = tableTwoMapper.getRiceTwo61(year);
        List<crop_01_02_table_06_02> two62 = tableTwoMapper.getRiceTwo62(year);
        List<crop_01_02_table_06_03> two63 = tableTwoMapper.getRiceTwo63(year);
        List<crop_01_02_table_06_04> two64 = tableTwoMapper.getRiceTwo64(year);
        List<crop_01_02_table_06_05> two65 = tableTwoMapper.getRiceTwo65(year);
        List<crop_01_02_table_07_01> two71 = tableTwoMapper.getRiceTwo71(year);
        List<crop_01_02_table_07_02> two72 = tableTwoMapper.getRiceTwo72(year);
        List<crop_01_02_table_07_03> two73 = tableTwoMapper.getRiceTwo73(year);
        List<crop_01_02_table_07_04> two74 = tableTwoMapper.getRiceTwo74(year);
        List<crop_01_02_table_08_01> two81 = tableTwoMapper.getRiceTwo81(year);
        List<crop_01_02_table_08_02> two82 = tableTwoMapper.getRiceTwo82(year);
        List<crop_01_02_table_09_03> two93 = tableTwoMapper.getRiceTwo93(year);
        List<crop_01_02_table_09_04> two94 = tableTwoMapper.getRiceTwo94(year);
        List<crop_01_02_table_09_05> two95 = tableTwoMapper.getRiceTwo95(year);
        List<crop_01_02_table_09_06> two96 = tableTwoMapper.getRiceTwo96(year);
        List<crop_01_02_table_09_07> two97 = tableTwoMapper.getRiceTwo97(year);
        List<crop_01_02_table_10_01> two101 = tableTwoMapper.getRiceTwo101(year);
        List<crop_01_02_table_10_02> two102 = tableTwoMapper.getRiceTwo102(year);
        List<sum_select_list> selectInfo = tableTwoMapper.getCounty2();
        ArrayList<String> countyInfo = new ArrayList<String>();
        //System.out.println(selectInfo);
        for(int i=0;i<selectInfo.size();i++){
            ////System.out.println(selectInfo.get(i).getCaption() instanceof String);
            if(selectInfo.get(i).getCaption().equals("县（市、区）") ){
                ////System.out.println(selectInfo.get(i).getList());
                countyInfo.add(selectInfo.get(i).getList());
            };
        };
        //System.out.println(countyInfo);
        ArrayList<ArrayList> noCounty =new ArrayList<ArrayList>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        if(two11.size()==0){
            noCounty.add(0,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two11.size();i++){
                temp.add(i,two11.get(i).getField_1());
            };
            ////System.out.println(temp);
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            ////System.out.println(temp);
            noCounty.add(0,temp);
            ////System.out.println(noCounty);
        };
        if(two12.size()==0){
            noCounty.add(1,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two12.size();i++){
                temp.add(i,two12.get(i).getCounty());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(1,temp);
        };
        if(two31.size()==0){
            noCounty.add(2,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two31.size();i++){
                temp.add(i,two31.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(2,temp);
        };
        if(two32.size()==0){
            noCounty.add(3,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two32.size();i++){
                temp.add(i,two32.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(3,temp);
        };
        if(two33.size()==0){
            noCounty.add(4,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two33.size();i++){
                temp.add(i,two33.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(4,temp);
        };
        if(two34.size()==0){
            noCounty.add(5,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two34.size();i++){
                temp.add(i,two34.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(5,temp);
        };
        if(two35.size()==0){
            noCounty.add(6,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two35.size();i++){
                temp.add(i,two35.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(6,temp);
        };
        if(two36.size()==0){
            noCounty.add(7,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two36.size();i++){
                temp.add(i,two36.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(7,temp);
        };
        if(two41.size()==0){
            noCounty.add(8,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two41.size();i++){
                temp.add(i,two41.get(i).getField_1());
            };
            ////System.out.println(temp);
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            ////System.out.println(temp);
            noCounty.add(8,temp);
            ////System.out.println(noCounty);
        };
        if(two42.size()==0){
            noCounty.add(9,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two42.size();i++){
                temp.add(i,two42.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(9,temp);
        };
        if(two43.size()==0){
            noCounty.add(10,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two43.size();i++){
                temp.add(i,two43.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(10,temp);
        };
        if(two44.size()==0){
            noCounty.add(11,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two44.size();i++){
                temp.add(i,two44.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(11,temp);
        };
        if(two51.size()==0){
            noCounty.add(12,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two51.size();i++){
                temp.add(i,two51.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(12,temp);
        };
        if(two61.size()==0){
            noCounty.add(13,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two61.size();i++){
                temp.add(i,two61.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(13,temp);
        };
        if(two62.size()==0){
            noCounty.add(14,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two62.size();i++){
                temp.add(i,two62.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(14,temp);
        };
        if(two63.size()==0){
            noCounty.add(15,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two63.size();i++){
                temp.add(i,two63.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(15,temp);
        };
        if(two64.size()==0){
            noCounty.add(16,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two64.size();i++){
                temp.add(i,two64.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(16,temp);
        };
        if(two65.size()==0){
            noCounty.add(17,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two65.size();i++){
                temp.add(i,two65.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(17,temp);
        };
        if(two71.size()==0){
            noCounty.add(18,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two71.size();i++){
                temp.add(i,two71.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(18,temp);
        };
        if(two72.size()==0){
            noCounty.add(19,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two72.size();i++){
                temp.add(i,two72.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(19,temp);
        };
        if(two73.size()==0){
            noCounty.add(20,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two73.size();i++){
                temp.add(i,two73.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(20,temp);
        };
        if(two74.size()==0){
            noCounty.add(21,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two74.size();i++){
                temp.add(i,two74.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(21,temp);
        };
        if(two81.size()==0){
            noCounty.add(22,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two81.size();i++){
                temp.add(i,two81.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(22,temp);
        };
        if(two82.size()==0){
            noCounty.add(23,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two82.size();i++){
                temp.add(i,two82.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(23,temp);
        };
        if(two93.size()==0){
            noCounty.add(24,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two93.size();i++){
                temp.add(i,two93.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(24,temp);
        };
        if(two94.size()==0){
            noCounty.add(25,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two94.size();i++){
                temp.add(i,two94.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(25,temp);
        };
        if(two95.size()==0){
            noCounty.add(26,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two95.size();i++){
                temp.add(i,two95.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(26,temp);
        };
        if(two96.size()==0){
            noCounty.add(27,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two96.size();i++){
                temp.add(i,two96.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(27,temp);
        };
        if(two97.size()==0){
            noCounty.add(28,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two97.size();i++){
                temp.add(i,two97.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(28,temp);
        };
        if(two101.size()==0){
            noCounty.add(29,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two101.size();i++){
                temp.add(i,two101.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(29,temp);
        };
        if(two102.size()==0){
            noCounty.add(30,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<two102.size();i++){
                temp.add(i,two102.get(i).getField_1());
            };
            for (String string:temp){
                map.put(string,0);
            };
            temp.clear();//清空临时数组，用于存放未上报的县
            Integer in;
            for(String string:countyInfo){
                in=map.get(string);
                if(in ==null){
                    temp.add(string);
                }
            };
            noCounty.add(30,temp);
        };
        //存表名
        List<table_second_name> tableName = tableTwoMapper.getTabName();
        ArrayList<String > table =new ArrayList<String>();
        for(int i=0;i<tableName.size();i++){
            table.add(i,tableName.get(i).getSecond_name());
        }
        ArrayList<String> countyNumber =new ArrayList<String>();
        for(int i=0;i<noCounty.size();i++){
            String number = String.valueOf(noCounty.get(i).size());
            countyNumber.add(i,number);
        }
        ////System.out.println(table);
        ////System.out.println(table.size());
        ////System.out.println(countyNumber);
        ////System.out.println(noCounty);
        ////System.out.println(noCounty.size());
        ArrayList<HashMap<String, Object>> reportInfo = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<countyNumber.size();i++){
            HashMap<String,Object> map1 =new HashMap<String, Object>();
            if(countyNumber.get(i).equals("6")){
//                map1.put("id",String.valueOf(i+1));
//                map1.put("table_name",table.get(i));
//                map1.put("number",0);
//                map1.put("county_name",null);
//                reportInfo.add(map1);
            }else{
                map1.put("id",String.valueOf(i+1));
                map1.put("table_name",table.get(i));
                map1.put("number",countyNumber.get(i));
                map1.put("county_name",noCounty.get(i));
                reportInfo.add(map1);
            }
        };
        //System.out.println(reportInfo);
        return reportInfo;
    }

    //删除监测点
    @Override
    public int deletePoint2(crop_01_02_table_02_02 deleteInfo,String year){
        System.out.println(deleteInfo);
        String pointCode = deleteInfo.getField_1();
        String people = deleteInfo.getPeople();
        int deleteId = deleteInfo.getId();
        //删除监测点信息
        List<String> tableName = new ArrayList<>();
        tableName.add("crop_01_02_table_02_01");
        tableName.add("crop_01_02_table_02_02");
        tableName.add("crop_01_02_table_04_01");
        tableName.add("crop_01_02_table_04_02");
        tableName.add("crop_01_02_table_04_03");
        tableName.add("crop_01_02_table_04_04");
        tableName.add("crop_01_02_table_05_01");
        tableName.add("crop_01_02_table_06_01");
        tableName.add("crop_01_02_table_06_02");
        tableName.add("crop_01_02_table_06_03");
        tableName.add("crop_01_02_table_06_04");
        tableName.add("crop_01_02_table_06_05");
        tableName.add("crop_01_02_table_09_07");
        tableName.add("crop_01_02_table_10_01");
        tableName.add("crop_01_02_table_10_02");
        int info1 = 1;
        int info2 = 1;
        for(int i=0;i<2;i++){
            //表2对应监测点代码为field_1
            String name = tableName.get(i);
            tableTwoMapper.deleteTableTwo1(name,pointCode,people,deleteId,year);
            System.out.println(name);
        }
        for(int i=2;i<tableName.size();i++){
            //二类表对应监测点代码为field_2
            String name = tableName.get(i);
            tableTwoMapper.deleteTableTwo2(name,pointCode,people,deleteId,year);
            System.out.println(name);
        }
        return 1;
    }


}
