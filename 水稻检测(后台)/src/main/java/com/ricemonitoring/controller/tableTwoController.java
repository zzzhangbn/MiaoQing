package com.ricemonitoring.controller;


import com.alibaba.fastjson.JSONObject;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.TableTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class tableTwoController {
    @Autowired
    TableTwoService tableTwoService;

    //双季稻获取类型
    @GetMapping("/getCounty2")
    public List<sum_select_list>getCounty2(){
        List<sum_select_list> countyList2 = tableTwoService.getCounty2();
        ////System.out.println(countyList);
        return countyList2;
    };
    //双季稻监测点审核
    @GetMapping("/getPointData2")
    public List<crop_01_02_table_02_01> getPointData2(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        //System.out.println(year);
        List<crop_01_02_table_02_01> tableList21 = tableTwoService.getPointData2(year);
        //System.out.println("tableList21:"+tableList21);
        return tableList21;
    }
    //双季稻监测点不通过
    @GetMapping("/noPassPoint2")
    public String noPassPoint2(@RequestParam("recordId") String recordId){
        String tableList21 = tableTwoService.noPassPoint2(recordId);
        ////System.out.println(tableList);
        return tableList21;
    }
    //双季稻监测点通过
    @PostMapping("/PassPoint2")
    public String PassPoint2(@RequestParam("recordId") String recordId,
                             @RequestParam("county") String county,
                             @RequestParam("pointCode") String pointCode){
        int  list21 = tableTwoService.PassPoint2(pointCode,recordId);
        //System.out.println(list21);
        return null;
    }
    //双季稻各表审核数目
    @GetMapping("/getAuditNumber2")
    public String[] getAuditNumber2(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //System.out.println(currentYear);
        List<crop_01_02_table_01_01> table11 = tableTwoService.getAuditRiceTableTwo11(year);
        List<crop_01_02_table_01_02> table12 = tableTwoService.getAuditRiceTableTwo12(year);
        List<crop_01_02_table_02_02> table22 = tableTwoService.getAuditRiceTableTwo22(year);
        List<crop_01_02_table_03_01> table31 = tableTwoService.getAuditRiceTableTwo31(year);
        List<crop_01_02_table_03_02> table32 = tableTwoService.getAuditRiceTableTwo32(year);
        List<crop_01_02_table_03_03> table33 = tableTwoService.getAuditRiceTableTwo33(year);
        List<crop_01_02_table_03_04> table34 = tableTwoService.getAuditRiceTableTwo34(year);
        List<crop_01_02_table_03_05> table35 = tableTwoService.getAuditRiceTableTwo35(year);
        List<crop_01_02_table_03_06> table36 = tableTwoService.getAuditRiceTableTwo36(year);
        List<crop_01_02_table_04_01> table41 = tableTwoService.getAuditRiceTableTwo41(year);
        List<crop_01_02_table_04_02> table42 = tableTwoService.getAuditRiceTableTwo42(year);
        List<crop_01_02_table_04_03> table43 = tableTwoService.getAuditRiceTableTwo43(year);
        List<crop_01_02_table_04_04> table44 = tableTwoService.getAuditRiceTableTwo44(year);
        List<crop_01_02_table_05_01> table51 = tableTwoService.getAuditRiceTableTwo51(year);
        List<crop_01_02_table_06_01> table61 = tableTwoService.getAuditRiceTableTwo61(year);
        List<crop_01_02_table_06_02> table62 = tableTwoService.getAuditRiceTableTwo62(year);
        List<crop_01_02_table_06_03> table63 = tableTwoService.getAuditRiceTableTwo63(year);
        List<crop_01_02_table_06_04> table64 = tableTwoService.getAuditRiceTableTwo64(year);
        List<crop_01_02_table_06_05> table65 = tableTwoService.getAuditRiceTableTwo65(year);
        List<crop_01_02_table_07_01> table71 = tableTwoService.getAuditRiceTableTwo71(year);
        List<crop_01_02_table_07_02> table72 = tableTwoService.getAuditRiceTableTwo72(year);
        List<crop_01_02_table_07_03> table73 = tableTwoService.getAuditRiceTableTwo73(year);
        List<crop_01_02_table_07_04> table74 = tableTwoService.getAuditRiceTableTwo74(year);
        List<crop_01_02_table_08_01> table81 = tableTwoService.getAuditRiceTableTwo81(year);
        List<crop_01_02_table_08_02> table82 = tableTwoService.getAuditRiceTableTwo82(year);
        List<crop_01_02_table_09_01> table91 = tableTwoService.getAuditRiceTableTwo91(year);
        List<crop_01_02_table_09_02> table92 = tableTwoService.getAuditRiceTableTwo92(currentYear,year);
        List<crop_01_02_table_09_03> table93 = tableTwoService.getAuditRiceTableTwo93(year);
        List<crop_01_02_table_09_04> table94 = tableTwoService.getAuditRiceTableTwo94(year);
        List<crop_01_02_table_09_05> table95 = tableTwoService.getAuditRiceTableTwo95(year);
        List<crop_01_02_table_09_06> table96 = tableTwoService.getAuditRiceTableTwo96(year);
        List<crop_01_02_table_09_07> table97 = tableTwoService.getAuditRiceTableTwo97(year);
        List<crop_01_02_table_10_01> table101 = tableTwoService.getAuditRiceTableTwo101(year);
        List<crop_01_02_table_10_02> table102 = tableTwoService.getAuditRiceTableTwo102(year);

        String numberData[] = new String[34];
        String num1 = String.valueOf(table11.size()); String num2 = String.valueOf(table12.size()); String num3 = String.valueOf(table22.size()); String num4 = String.valueOf(table31.size());
        String num5 = String.valueOf(table32.size()); String num6 = String.valueOf(table33.size()); String num7 = String.valueOf(table34.size()); String num8 = String.valueOf(table35.size());
        String num9 = String.valueOf(table36.size()); String num10 = String.valueOf(table41.size()); String num11 = String.valueOf(table42.size()); String num12 = String.valueOf(table43.size());
        String num13 = String.valueOf(table44.size()); String num14 = String.valueOf(table51.size()); String num15 = String.valueOf(table61.size()); String num16 = String.valueOf(table62.size());
        String num17 = String.valueOf(table63.size()); String num18 = String.valueOf(table64.size()); String num19 = String.valueOf(table65.size()); String num20 = String.valueOf(table71.size());
        String num21 = String.valueOf(table72.size()); String num22 = String.valueOf(table73.size()); String num23 = String.valueOf(table74.size()); String num24 = String.valueOf(table81.size());
        String num25 = String.valueOf(table82.size()); String num26 = String.valueOf(table91.size()); String num27 = String.valueOf(table92.size()); String num28 = String.valueOf(table93.size());
        String num29 = String.valueOf(table94.size()); String num30 = String.valueOf(table95.size()); String num31 = String.valueOf(table96.size()); String num32 = String.valueOf(table97.size());
        String num33 = String.valueOf(table101.size()); String num34 = String.valueOf(table102.size());
        //System.out.println(table11.size());
        //System.out.println(table33.size());
        numberData[0]=num1;numberData[1]=num2;numberData[2]=num3;numberData[3]=num4;numberData[4]=num5;numberData[5]=num6;numberData[6]=num7;numberData[7]=num8;numberData[8]=num9;numberData[9]=num10;
        numberData[10]=num11;numberData[11]=num12;numberData[12]=num13;numberData[13]=num14;numberData[14]=num15;numberData[15]=num16;numberData[16]=num17;numberData[17]=num18;numberData[18]=num19;numberData[19]=num20;
        numberData[20]=num21;numberData[21]=num22;numberData[22]=num23;numberData[23]=num24;numberData[24]=num25;numberData[25]=num26;numberData[26]=num27;numberData[27]=num28;numberData[28]=num29;numberData[29]=num30;
        numberData[30]=num31;numberData[31]=num32;numberData[32]=num33;numberData[33]=num34;
        return numberData;
    };
    //一季稻生育期监测点个数
    @GetMapping("/getPeriod2")
    public List<HashMap> getPeriod2(){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_04_04> info =tableTwoService.getPeriod2(year);
        //System.out.println(info);
        //String[] point=null;
        ArrayList<String> point1 =new ArrayList<String>();
        ArrayList<String> point2 =new ArrayList<String>();
        ArrayList<String> point3 =new ArrayList<String>();
        ArrayList<String> point4 =new ArrayList<String>();
        ArrayList<String> point5 =new ArrayList<String>();
        ArrayList<String> point6 =new ArrayList<String>();
        ArrayList<String> point7 =new ArrayList<String>();
        ArrayList<String> point8 =new ArrayList<String>();
        ArrayList<String> point9 =new ArrayList<String>();
        ArrayList<String> point10 =new ArrayList<String>();
        ArrayList<String> point11 =new ArrayList<String>();

        for(int i=0;i<info.size();i++){
            if(info.get(i).getField_8()!=null && info.get(i).getField_9()==null){
                point1.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_9()!=null && info.get(i).getField_10()==null){
                point2.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_10()!=null && info.get(i).getField_fennie()==null){
                point3.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_fennie()!=null && info.get(i).getField_11()==null){
                point4.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_11()!=null && info.get(i).getField_12()==null){
                point5.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_12()!=null && info.get(i).getField_13()==null){
                point6.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_13()!=null && info.get(i).getField_14()==null){
                point7.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_14()!=null && info.get(i).getField_15()==null){
                point8.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_15()!=null && info.get(i).getField_16()==null){
                point9.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_16()!=null && info.get(i).getField_17()==null){
                point10.add(info.get(i).getField_2());
            }
            if(info.get(i).getField_17()!=null){
                point11.add(info.get(i).getField_2());
            }
        };
        //System.out.println(point6);
        //System.out.println(point6.size());
        HashMap<String,Object> map1 =new HashMap<String, Object>();
        HashMap<String,Object> map2 =new HashMap<String, Object>();
        HashMap<String,Object> map3 =new HashMap<String, Object>();
        HashMap<String,Object> map4 =new HashMap<String, Object>();
        HashMap<String,Object> map5 =new HashMap<String, Object>();
        HashMap<String,Object> map6 =new HashMap<String, Object>();
        HashMap<String,Object> map7 =new HashMap<String, Object>();
        HashMap<String,Object> map8 =new HashMap<String, Object>();
        HashMap<String,Object> map9 =new HashMap<String, Object>();
        HashMap<String,Object> map10 =new HashMap<String, Object>();
        HashMap<String,Object> map11 =new HashMap<String, Object>();
        List<HashMap> pointInfo = new ArrayList<HashMap>();
        map1.put("pointName",point1);
        map1.put("value",point1.size());
        pointInfo.add(0,map1);
        map2.put("pointName",point2);
        map2.put("value",point2.size());
        pointInfo.add(1,map2);
        map3.put("pointName",point3);
        map3.put("value",point3.size());
        pointInfo.add(2,map3);
        map4.put("pointName",point4);
        map4.put("value",point4.size());
        pointInfo.add(3,map4);
        map5.put("pointName",point5);
        map5.put("value",point5.size());
        pointInfo.add(4,map5);
        map6.put("pointName",point6);
        map6.put("value",point6.size());
        pointInfo.add(5,map6);
        map7.put("pointName",point7);
        map7.put("value",point7.size());
        pointInfo.add(6,map7);
        map8.put("pointName",point8);
        map8.put("value",point8.size());
        pointInfo.add(7,map8);
        map9.put("pointName",point9);
        map9.put("value",point9.size());
        pointInfo.add(8,map9);
        map10.put("pointName",point10);
        map10.put("value",point10.size());
        pointInfo.add(9,map10);
        map11.put("pointName",point11);
        map11.put("value",point11.size());
        pointInfo.add(10,map11);
        //System.out.println(pointInfo);
        return pointInfo;
    }

    //双季稻监测点信息插入到表2-2中
    @PostMapping("/insertInfoToRiceTableTwo0202")
    public int insertInfoToRiceTableTwo0202(@RequestParam("infoData")String infoData,
                                         @RequestParam("county") String county){
        //System.out.println("infoData:"+infoData);
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        String areaType="";
        List<crop_01_02_table_02_02> table22 = tableTwoService.getRiceTableTwo22(county,areaType,year);
        crop_01_02_table_02_01 insertInfo = JSONObject.parseObject(infoData , crop_01_02_table_02_01.class);
        ////System.out.println(table22);
        insertInfo.setIn_use("3");
        String pointCode = insertInfo.getField_1();
        int number = 0;
        for(int i=0;i<table22.size();i++){
            if(table22.get(i).getField_1().equals(pointCode)){
                number++;
                break;
            }
        }
        if(number == 0){
            int res = tableTwoService.insertInfoToRiceTableTwo0202(insertInfo);
        }else{
            int res = tableTwoService.updateInfoToRiceTableTwo0202(insertInfo);
        }

//        int tabNumber = table22.size();
//        int tabLength=0;
//        String pointCode = "";
//        if(tabNumber<9){
//            tabLength = tabNumber+1;
//            pointCode = county +"_" + "00"+String.valueOf(tabLength);
//        }else if(9<=tabNumber && tabNumber<99){
//            tabLength = tabNumber+1;
//            pointCode = county +"_" + "0"+String.valueOf(tabLength);
//        }else{
//            tabLength = tabNumber+1;
//            pointCode = county +"_" + String.valueOf(tabLength);
//        };
//        ////System.out.println(pointCode);
//        //更改pointCode
//        insertInfo.setField_1(pointCode);
//        insertInfo.setIn_use("3");
//        //System.out.println("insertInfo:"+insertInfo);


        return 1;
    };

    //双季稻表1-1
    @PostMapping("/getRiceTableTwo11")
    public List<crop_01_02_table_01_01> getRiceTableTwo11(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_01_01> tableList11 = tableTwoService.getRiceTableTwo11(county,areaType,year);
        ////System.out.println(tableList);
        return tableList11;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo11")
    public List<crop_01_02_table_01_01> getAuditRiceTableTwo11(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_01_01> tableList11 = tableTwoService.getAuditRiceTableTwo11(year);
        ////System.out.println(tableList);
        return tableList11;
    };
    //双季稻表1-1搜索查询
    @PostMapping("/getSeekInfoTwo11")
    public List<crop_01_02_table_01_01> getSeekInfoTwo11(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_01_01> Info11 = tableTwoService.getSeekInfoTwo11(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info11;
    };

    //双季稻表1-2
    @PostMapping("/getRiceTableTwo12")
    public List<crop_01_02_table_01_02> getRiceTableTwo12(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_01_02> tableList12 = tableTwoService.getRiceTableTwo12(county,areaType,year);
        ////System.out.println(tableList12);
        return tableList12;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo12")
    public List<crop_01_02_table_01_02> getAuditRiceTableTwo12(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_01_02> tableList12 = tableTwoService.getAuditRiceTableTwo12(year);
        ////System.out.println(tableList);
        return tableList12;
    };
    //双季稻表1-2搜索查询
    @PostMapping("/getSeekInfoTwo12")
    public List<crop_01_02_table_01_02> getSeekInfoTwo12(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_01_02> Info12 = tableTwoService.getSeekInfoTwo12(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info12;
    };

    //双季稻表2-2
    @PostMapping("/getRiceTableTwo22")
    public List<crop_01_02_table_02_02> getRiceTableTwo22(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_02_02> tableList22 = tableTwoService.getRiceTableTwo22(county,areaType,year);
        ////System.out.println(tableList);
        return tableList22;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo22")
    public List<crop_01_02_table_02_02> getAuditRiceTableTwo22(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_02_02> tableList22 = tableTwoService.getAuditRiceTableTwo22(year);
        ////System.out.println(tableList);
        return tableList22;
    };
    //双季稻表2-2搜索查询
    @PostMapping("/getSeekInfoTwo22")
    public List<crop_01_02_table_02_02> getSeekInfoTwo22(@RequestParam("county") String county,
                                                         @RequestParam("city") String city,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_02_02> Info22 = tableTwoService.getSeekInfoTwo22(county,city,year);
        ////System.out.println(Info11);
        return Info22;
    };

    //双季稻表3-1
    @PostMapping("/getRiceTableTwo31")
    public List<crop_01_02_table_03_01> getRiceTableTwo31(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_01> tableList31 = tableTwoService.getRiceTableTwo31(county,areaType,year);
        ////System.out.println(tableList);
        return tableList31;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo31")
    public List<crop_01_02_table_03_01> getAuditRiceTableTwo31(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_01> tableList31 = tableTwoService.getAuditRiceTableTwo31(year);
        ////System.out.println(tableList);
        return tableList31;
    };
    //双季稻表3-1搜索查询
    @PostMapping("/getSeekInfoTwo31")
    public List<crop_01_02_table_03_01> getSeekInfoTwo31(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_01> Info31 = tableTwoService.getSeekInfoTwo31(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info31;
    };


    //双季稻表3-2
    @PostMapping("/getRiceTableTwo32")
    public List<crop_01_02_table_03_02> getRiceTableTwo32(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_02> tableList32 = tableTwoService.getRiceTableTwo32(county,areaType,year);
        ////System.out.println(tableList);
        return tableList32;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo32")
    public List<crop_01_02_table_03_02> getAuditRiceTableTwo32(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_02> tableList32 = tableTwoService.getAuditRiceTableTwo32(year);
        ////System.out.println(tableList);
        return tableList32;
    };
    //双季稻表3-2搜索查询
    @PostMapping("/getSeekInfoTwo32")
    public List<crop_01_02_table_03_02> getSeekInfoTwo32(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_02> Info32 = tableTwoService.getSeekInfoTwo32(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info32;
    };


    //双季稻表3-3
    @PostMapping("/getRiceTableTwo33")
    public List<crop_01_02_table_03_03> getRiceTableTwo33(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_03> tableList33 = tableTwoService.getRiceTableTwo33(county,areaType,year);
        ////System.out.println(tableList);
        return tableList33;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo33")
    public List<crop_01_02_table_03_03> getAuditRiceTableTwo33(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_03> tableList33 = tableTwoService.getAuditRiceTableTwo33(year);
        ////System.out.println(tableList);
        return tableList33;
    };
    //双季稻表3-3搜索查询
    @PostMapping("/getSeekInfoTwo33")
    public List<crop_01_02_table_03_03> getSeekInfoTwo33(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_03> Info33 = tableTwoService.getSeekInfoTwo33(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info33;
    };


    //双季稻表3-4
    @PostMapping("/getRiceTableTwo34")
    public List<crop_01_02_table_03_04> getRiceTableTwo34(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_04> tableList34 = tableTwoService.getRiceTableTwo34(county,areaType,year);
        ////System.out.println(tableList);
        return tableList34;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo34")
    public List<crop_01_02_table_03_04> getAuditRiceTableTwo34(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_04> tableList34 = tableTwoService.getAuditRiceTableTwo34(year);
        ////System.out.println(tableList);
        return tableList34;
    };
    //双季稻表3-4搜索查询
    @PostMapping("/getSeekInfoTwo34")
    public List<crop_01_02_table_03_04> getSeekInfoTwo34(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_04> Info34 = tableTwoService.getSeekInfoTwo34(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info34;
    };


    //双季稻表3-5
    @PostMapping("/getRiceTableTwo35")
    public List<crop_01_02_table_03_05> getRiceTableTwo35(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_05> tableList35 = tableTwoService.getRiceTableTwo35(county,areaType,year);
        ////System.out.println(tableList);
        return tableList35;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo35")
    public List<crop_01_02_table_03_05> getAuditRiceTableTwo35(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_05> tableList35 = tableTwoService.getAuditRiceTableTwo35(year);
        ////System.out.println(tableList);
        return tableList35;
    };
    //双季稻表3-5搜索查询
    @PostMapping("/getSeekInfoTwo35")
    public List<crop_01_02_table_03_05> getSeekInfoTwo35(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_05> Info35 = tableTwoService.getSeekInfoTwo35(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info35;
    };


    //双季稻表3-6
    @PostMapping("/getRiceTableTwo36")
    public List<crop_01_02_table_03_06> getRiceTableTwo36(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_03_06> tableList36 = tableTwoService.getRiceTableTwo36(county,areaType,year);
        ////System.out.println(tableList);
        return tableList36;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo36")
    public List<crop_01_02_table_03_06> getAuditRiceTableTwo36(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_03_06> tableList36 = tableTwoService.getAuditRiceTableTwo36(year);
        ////System.out.println(tableList);
        return tableList36;
    };
    //双季稻表3-6搜索查询
    @PostMapping("/getSeekInfoTwo36")
    public List<crop_01_02_table_03_06> getSeekInfoTwo36(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_03_06> Info36 = tableTwoService.getSeekInfoTwo36(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info36;
    };

    //双季稻表4-1
    @PostMapping("/getRiceTableTwo41")
    public List<crop_01_02_table_04_01> getRiceTableTwo41(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_04_01> tableList41 = tableTwoService.getRiceTableTwo41(county,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo41")
    public List<crop_01_02_table_04_01> getAuditRiceTableTwo41(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_04_01> tableList41 = tableTwoService.getAuditRiceTableTwo41(year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-1搜索查询
    @PostMapping("/getSeekInfoTwo41")
    public List<crop_01_02_table_04_01> getSeekInfoTwo41(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_04_01> Info41 = tableTwoService.getSeekInfoTwo41(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info41;
    };

    //双季稻表4-2
    @PostMapping("/getRiceTableTwo42")
    public List<crop_01_02_table_04_02> getRiceTableTwo42(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_04_02> tableList41 = tableTwoService.getRiceTableTwo42(county,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-2
    @PostMapping("/getRiceTableTwo42new")
    public List<crop_01_02_table_04_02> getRiceTableTwo42new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
//        System.out.println(123488696);
        //System.out.println(area);
//        System.out.println(areaType);
//        System.out.println(year);
        List<crop_01_02_table_04_02> tableList41 = tableTwoService.getRiceTableTwo42new(area,areaType,year);
        //System.out.println(tableList41);
        return tableList41;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo42")
    public List<crop_01_02_table_04_02> getAuditRiceTableTwo42(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_04_02> tableList41 = tableTwoService.getAuditRiceTableTwo42(year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-2搜索查询
    @PostMapping("/getSeekInfoTwo42")
    public List<crop_01_02_table_04_02> getSeekInfoTwo42(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_04_02> Info41 = tableTwoService.getSeekInfoTwo42(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info41;
    };

    //双季稻表4-3
    @PostMapping("/getRiceTableTwo43")
    public List<crop_01_02_table_04_03> getRiceTableTwo43(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_04_03> tableList41 = tableTwoService.getRiceTableTwo43(county,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-3搜索查询
    @PostMapping("/getRiceTableTwo43new")
    public List<crop_01_02_table_04_03> getRiceTableTwo43new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
//        System.out.println("getRiceTableTwo43new");
        List<crop_01_02_table_04_03> tableList41 = tableTwoService.getRiceTableTwo43new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo43")
    public List<crop_01_02_table_04_03> getAuditRiceTableTwo43(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_04_03> tableList41 = tableTwoService.getAuditRiceTableTwo43(year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-3搜索查询
    @PostMapping("/getSeekInfoTwo43")
    public List<crop_01_02_table_04_03> getSeekInfoTwo43(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_04_03> Info41 = tableTwoService.getSeekInfoTwo43(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info41;
    };

    //双季稻表4-4
    @PostMapping("/getRiceTableTwo44")
    public List<crop_01_02_table_04_04> getRiceTableTwo44(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_04_04> tableList41 = tableTwoService.getRiceTableTwo44(county,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-4搜索查询用于单区显示全部数据
    @PostMapping("/getRiceTableTwo44new")
    public List<crop_01_02_table_04_04> getRiceTableTwo44new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        System.out.println("getRiceTableTwo44new");
        List<crop_01_02_table_04_04> tableList41 = tableTwoService.getRiceTableTwo44new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo44")
    public List<crop_01_02_table_04_04> getAuditRiceTableTwo44(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_04_04> tableList41 = tableTwoService.getAuditRiceTableTwo44(year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //双季稻表4-4搜索查询
    @PostMapping("/getSeekInfoTwo44")
    public List<crop_01_02_table_04_04> getSeekInfoTwo44(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_04_04> Info41 = tableTwoService.getSeekInfoTwo44(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info41;
    };

    //双季稻表5-1
    @PostMapping("/getRiceTableTwo51")
    public List<crop_01_02_table_05_01> getRiceTableTwo51(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_05_01> tableList51 = tableTwoService.getRiceTableTwo51(county,areaType,year);
        ////System.out.println(tableList);
        return tableList51;
    };
    //双季稻表5-1
    @PostMapping("/getRiceTableTwo51new")
    public List<crop_01_02_table_05_01> getRiceTableTwo51new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_05_01> tableList51 = tableTwoService.getRiceTableTwo51new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList51;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo51")
    public List<crop_01_02_table_05_01> getAuditRiceTableTwo51(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_05_01> tableList51 = tableTwoService.getAuditRiceTableTwo51(year);
        ////System.out.println(tableList);
        return tableList51;
    };
    //双季稻表5-1搜索查询
    @PostMapping("/getSeekInfoTwo51")
    public List<crop_01_02_table_05_01> getSeekInfoTwo51(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_05_01> Info51 = tableTwoService.getSeekInfoTwo51(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info51;
    };


    //双季稻表6-1
    @PostMapping("/getRiceTableTwo61")
    public List<crop_01_02_table_06_01> getRiceTableTwo61(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_06_01> tableList61 = tableTwoService.getRiceTableTwo61(county,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-1
    @PostMapping("/getRiceTableTwo61new")
    public List<crop_01_02_table_06_01> getRiceTableTwo61new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        System.out.println("getRiceTableTwo61new");
        List<crop_01_02_table_06_01> tableList61 = tableTwoService.getRiceTableTwo61new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo61")
    public List<crop_01_02_table_06_01> getAuditRiceTableTwo61(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_06_01> tableList61 = tableTwoService.getAuditRiceTableTwo61(year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-1搜索查询
    @PostMapping("/getSeekInfoTwo61")
    public List<crop_01_02_table_06_01> getSeekInfoTwo61(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_06_01> Info61 = tableTwoService.getSeekInfoTwo61(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };

    //双季稻表6-2
    @PostMapping("/getRiceTableTwo62")
    public List<crop_01_02_table_06_02> getRiceTableTwo62(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_06_02> tableList61 = tableTwoService.getRiceTableTwo62(county,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-2
    @PostMapping("/getRiceTableTwo62new")
    public List<crop_01_02_table_06_02> getRiceTableTwo62new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        System.out.println("/getRiceTableTwo62new");
        List<crop_01_02_table_06_02> tableList61 = tableTwoService.getRiceTableTwo62new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo62")
    public List<crop_01_02_table_06_02> getAuditRiceTableTwo62(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_06_02> tableList61 = tableTwoService.getAuditRiceTableTwo62(year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-2搜索查询
    @PostMapping("/getSeekInfoTwo62")
    public List<crop_01_02_table_06_02> getSeekInfoTwo62(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_06_02> Info61 = tableTwoService.getSeekInfoTwo62(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };


    //双季稻表6-3
    @PostMapping("/getRiceTableTwo63")
    public List<crop_01_02_table_06_03> getRiceTableTwo63(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_06_03> tableList61 = tableTwoService.getRiceTableTwo63(county,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-3
    @PostMapping("/getRiceTableTwo63new")
    public List<crop_01_02_table_06_03> getRiceTableTwo63new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_06_03> tableList61 = tableTwoService.getRiceTableTwo63new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo63")
    public List<crop_01_02_table_06_03> getAuditRiceTableTwo63(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_06_03> tableList61 = tableTwoService.getAuditRiceTableTwo63(year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-3搜索查询
    @PostMapping("/getSeekInfoTwo63")
    public List<crop_01_02_table_06_03> getSeekInfoTwo63(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_06_03> Info61 = tableTwoService.getSeekInfoTwo63(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };


    //双季稻表6-4
    @PostMapping("/getRiceTableTwo64")
    public List<crop_01_02_table_06_04> getRiceTableTwo64(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_06_04> tableList61 = tableTwoService.getRiceTableTwo64(county,areaType,year);
        ////System.out.println(tableList);4(county,areaType,ye
        return tableList61;
    };
    //双季稻表6-4
    @PostMapping("/getRiceTableTwo64new")
    public List<crop_01_02_table_06_04> getRiceTableTwo64new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_06_04> tableList61 = tableTwoService.getRiceTableTwo64new(area,areaType,year);
        ////System.out.println(tableList);4(county,areaType,ye
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo64")
    public List<crop_01_02_table_06_04> getAuditRiceTableTwo64(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_06_04> tableList61 = tableTwoService.getAuditRiceTableTwo64(year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-4搜索查询
    @PostMapping("/getSeekInfoTwo64")
    public List<crop_01_02_table_06_04> getSeekInfoTwo64(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_06_04> Info61 = tableTwoService.getSeekInfoTwo64(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };


    //双季稻表6-5
    @PostMapping("/getRiceTableTwo65")
    public List<crop_01_02_table_06_05> getRiceTableTwo65(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_06_05> tableList61 = tableTwoService.getRiceTableTwo65(county,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-5
    @PostMapping("/getRiceTableTwo65new")
    public List<crop_01_02_table_06_05> getRiceTableTwo65new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_06_05> tableList61 = tableTwoService.getRiceTableTwo65new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo65")
    public List<crop_01_02_table_06_05> getAuditRiceTableTwo65(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_06_05> tableList61 = tableTwoService.getAuditRiceTableTwo65(year);
        ////System.out.println(tableList);
        return tableList61;
    };
    //双季稻表6-5搜索查询
    @PostMapping("/getSeekInfoTwo65")
    public List<crop_01_02_table_06_05> getSeekInfoTwo65(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_06_05> Info61 = tableTwoService.getSeekInfoTwo65(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };


    //双季稻表7-1
    @PostMapping("/getRiceTableTwo71")
    public List<crop_01_02_table_07_01> getRiceTableTwo71(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_07_01> tableList71 = tableTwoService.getRiceTableTwo71(county,areaType,year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo71")
    public List<crop_01_02_table_07_01> getAuditRiceTableTwo71(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_07_01> tableList71 = tableTwoService.getAuditRiceTableTwo71(year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //双季稻表7-1搜索查询
    @PostMapping("/getSeekInfoTwo71")
    public List<crop_01_02_table_07_01> getSeekInfoTwo71(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_07_01> Info71 = tableTwoService.getSeekInfoTwo71(county,areaType,year);
        ////System.out.println(Info11);
        return Info71;
    };


    //双季稻表7-2
    @PostMapping("/getRiceTableTwo72")
    public List<crop_01_02_table_07_02> getRiceTableTwo72(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_07_02> tableList71 = tableTwoService.getRiceTableTwo72(county,areaType,year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo72")
    public List<crop_01_02_table_07_02> getAuditRiceTableTwo72(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_07_02> tableList71 = tableTwoService.getAuditRiceTableTwo72(year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //双季稻表7-2搜索查询
    @PostMapping("/getSeekInfoTwo72")
    public List<crop_01_02_table_07_02> getSeekInfoTwo72(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_07_02> Info71 = tableTwoService.getSeekInfoTwo72(county,areaType,year);
        ////System.out.println(Info11);
        return Info71;
    };


    //双季稻表7-3
    @PostMapping("/getRiceTableTwo73")
    public List<crop_01_02_table_07_03> getRiceTableTwo73(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_07_03> tableList71 = tableTwoService.getRiceTableTwo73(county,areaType,year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo73")
    public List<crop_01_02_table_07_03> getAuditRiceTableTwo73(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_07_03> tableList71 = tableTwoService.getAuditRiceTableTwo73(year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //双季稻表7-3搜索查询
    @PostMapping("/getSeekInfoTwo73")
    public List<crop_01_02_table_07_03> getSeekInfoTwo73(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_07_03> Info71 = tableTwoService.getSeekInfoTwo73(county,areaType,year);
        ////System.out.println(Info11);
        return Info71;
    };


    //双季稻表7-4
    @PostMapping("/getRiceTableTwo74")
    public List<crop_01_02_table_07_04> getRiceTableTwo74(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_07_04> tableList71 = tableTwoService.getRiceTableTwo74(county,areaType,year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo74")
    public List<crop_01_02_table_07_04> getAuditRiceTableTwo74(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_07_04> tableList71 = tableTwoService.getAuditRiceTableTwo74(year);
        ////System.out.println(tableList);
        return tableList71;
    };
    //双季稻表7-4搜索查询
    @PostMapping("/getSeekInfoTwo74")
    public List<crop_01_02_table_07_04> getSeekInfoTwo74(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_07_04> Info71 = tableTwoService.getSeekInfoTwo74(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info71;
    };

    //双季稻表8-1
    @PostMapping("/getRiceTableTwo81")
    public List<crop_01_02_table_08_01> getRiceTableTwo81(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_08_01> tableList81 = tableTwoService.getRiceTableTwo81(county,areaType,year);
        ////System.out.println(tableList);
        return tableList81;
    };
    //双季稻表8-1
    @PostMapping("/getRiceTableTwo81new")
    public List<crop_01_02_table_08_01> getRiceTableTwo81new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
//        System.out.println(111);
//        System.out.println(area);
//        System.out.println(areaType);
//        System.out.println(year);
        List<crop_01_02_table_08_01> tableList81 = tableTwoService.getRiceTableTwo81new(area,areaType,year);
        //System.out.println(tableList81);
        return tableList81;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo81")
    public List<crop_01_02_table_08_01> getAuditRiceTableTwo81(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_08_01> tableList81 = tableTwoService.getAuditRiceTableTwo81(year);
        ////System.out.println(tableList);
        return tableList81;
    };
    //双季稻表8-1搜索查询
    @PostMapping("/getSeekInfoTwo81")
    public List<crop_01_02_table_08_01> getSeekInfoTwo81(@RequestParam("county") String county,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_08_01> Info81 = tableTwoService.getSeekInfoTwo81(county,year);
        ////System.out.println(Info11);
        return Info81;
    };


    //双季稻表8-2
    @PostMapping("/getRiceTableTwo82")
    public List<crop_01_02_table_08_02> getRiceTableTwo82(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_08_02> tableList81 = tableTwoService.getRiceTableTwo82(county,areaType,year);
        ////System.out.println(tableList);
        return tableList81;
    };
    //双季稻表8-2
    @PostMapping("/getRiceTableTwo82new")
    public List<crop_01_02_table_08_02> getRiceTableTwo82new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_08_02> tableList81 = tableTwoService.getRiceTableTwo82new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList81;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo82")
    public List<crop_01_02_table_08_02> getAuditRiceTableTwo82(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_08_02> tableList81 = tableTwoService.getAuditRiceTableTwo82(year);
        ////System.out.println(tableList);
        return tableList81;
    };
    //双季稻表8-2搜索查询
    @PostMapping("/getSeekInfoTwo82")
    public List<crop_01_02_table_08_02> getSeekInfoTwo82(@RequestParam("county") String county,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_08_02> Info81 = tableTwoService.getSeekInfoTwo82(county,year);
        ////System.out.println(Info11);
        return Info81;
    };


    //双季稻表9-1
    @PostMapping("/getRiceTableTwo91")
    public List<crop_01_02_table_09_01> getRiceTableTwo91(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_01> tableList91 = tableTwoService.getRiceTableTwo91(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo91")
    public List<crop_01_02_table_09_01> getAuditRiceTableTwo91(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_01> tableList91 = tableTwoService.getAuditRiceTableTwo91(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-1搜索查询
    @PostMapping("/getSeekInfoTwo91")
    public List<crop_01_02_table_09_01> getSeekInfoTwo91(@RequestParam("county") String county,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_01> Info91 = tableTwoService.getSeekInfoTwo91(county,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-2
    @PostMapping("/getRiceTableTwo92")
    public List<crop_01_02_table_09_02> getRiceTableTwo92(@RequestParam("currentYear") String currentYear,
                                                          @RequestParam("previousYear") String previousYear,
                                                          @RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_02> tableList92 = tableTwoService.getRiceTableTwo92(currentYear,previousYear,county,areaType,year);
        //System.out.println("table92:"+tableList92);
        return tableList92;
    };

    //获取当年县
    @PostMapping("/getCurrentCounty2")
    public String[] getCurrentCounty2(@RequestParam("county") String county,
                                      @RequestParam("year") String year){
        String[] tableList92 = tableTwoService.getCurrentCounty2(county,year);
        return tableList92;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo92")
    public List<crop_01_02_table_09_02> getAuditRiceTableTwo92(@RequestParam("currentYear") String currentYear){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_02> tableList91 = tableTwoService.getAuditRiceTableTwo92(currentYear,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-2搜索查询
    @PostMapping("/getSeekInfoTwo92")
    public List<crop_01_02_table_09_02> getSeekInfoTwo92(@RequestParam("county") String county,
                                                         @RequestParam("currentYear") String currentYear,
                                                         @RequestParam("previousYear") String previousYear,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_02> Info91 = tableTwoService.getSeekInfoTwo92(county,currentYear,previousYear,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-3
    @PostMapping("/getRiceTableTwo93")
    public List<crop_01_02_table_09_03> getRiceTableTwo93(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_03> tableList91 = tableTwoService.getRiceTableTwo93(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo93")
    public List<crop_01_02_table_09_03> getAuditRiceTableTwo93(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_03> tableList91 = tableTwoService.getAuditRiceTableTwo93(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-3搜索查询
    @PostMapping("/getSeekInfoTwo93")
    public List<crop_01_02_table_09_03> getSeekInfoTwo93(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_03> Info91 = tableTwoService.getSeekInfoTwo93(county, areaType, riceType, category,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-4
    @PostMapping("/getRiceTableTwo94")
    public List<crop_01_02_table_09_04> getRiceTableTwo94(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_04> tableList91 = tableTwoService.getRiceTableTwo94(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo94")
    public List<crop_01_02_table_09_04> getAuditRiceTableTwo94(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_04> tableList91 = tableTwoService.getAuditRiceTableTwo94(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-4搜索查询
    @PostMapping("/getSeekInfoTwo94")
    public List<crop_01_02_table_09_04> getSeekInfoTwo94(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_04> Info91 = tableTwoService.getSeekInfoTwo94(county, areaType, riceType, category,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-5
    @PostMapping("/getRiceTableTwo95")
    public List<crop_01_02_table_09_05> getRiceTableTwo95(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_05> tableList91 = tableTwoService.getRiceTableTwo95(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo95")
    public List<crop_01_02_table_09_05> getAuditRiceTableTwo95(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_05> tableList91 = tableTwoService.getAuditRiceTableTwo95(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-5搜索查询
    @PostMapping("/getSeekInfoTwo95")
    public List<crop_01_02_table_09_05> getSeekInfoTwo95(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_05> Info91 = tableTwoService.getSeekInfoTwo95(county, areaType, riceType, category,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-6
    @PostMapping("/getRiceTableTwo96")
    public List<crop_01_02_table_09_06> getRiceTableTwo96(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_06> tableList91 = tableTwoService.getRiceTableTwo96(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo96")
    public List<crop_01_02_table_09_06> getAuditRiceTableTwo96(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_06> tableList91 = tableTwoService.getAuditRiceTableTwo96(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-6搜索查询
    @PostMapping("/getSeekInfoTwo96")
    public List<crop_01_02_table_09_06> getSeekInfoTwo96(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                         @RequestParam("category") String category,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_06> Info91 = tableTwoService.getSeekInfoTwo96(county, areaType, riceType, category,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表9-7
    @PostMapping("/getRiceTableTwo97")
    public List<crop_01_02_table_09_07> getRiceTableTwo97(@RequestParam("county") String county,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){
        
        List<crop_01_02_table_09_07> tableList91 = tableTwoService.getRiceTableTwo97(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-7
    @PostMapping("/getRiceTableTwo97new")
    public List<crop_01_02_table_09_07> getRiceTableTwo97new(@RequestParam("temp") String area,
                                                          @RequestParam("areaType") String areaType,
                                                          @RequestParam("year") String year){

        List<crop_01_02_table_09_07> tableList91 = tableTwoService.getRiceTableTwo97new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo97")
    public List<crop_01_02_table_09_07> getAuditRiceTableTwo97(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_09_07> tableList91 = tableTwoService.getAuditRiceTableTwo97(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表9-7搜索查询
    @PostMapping("/getSeekInfoTwo97")
    public List<crop_01_02_table_09_07> getSeekInfoTwo97(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("plantWay") String plantWay,
                                                         @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_09_07> Info91 = tableTwoService.getSeekInfoTwo97(county,areaType,plantWay,year);
        ////System.out.println(Info11);
        return Info91;
    };


    //双季稻表10-1
    @PostMapping("/getRiceTableTwo101")
    public List<crop_01_02_table_10_01> getRiceTableTwo101(@RequestParam("county") String county,
                                                           @RequestParam("areaType") String areaType,
                                                           @RequestParam("year") String year){
        
        List<crop_01_02_table_10_01> tableList91 = tableTwoService.getRiceTableTwo101(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表10-1
    @PostMapping("/getRiceTableTwo101new")
    public List<crop_01_02_table_10_01> getRiceTableTwo101new(@RequestParam("temp") String area,
                                                           @RequestParam("areaType") String areaType,
                                                           @RequestParam("year") String year){

        List<crop_01_02_table_10_01> tableList91 = tableTwoService.getRiceTableTwo101new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo101")
    public List<crop_01_02_table_10_01> getAuditRiceTableTwo101(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_10_01> tableList91 = tableTwoService.getAuditRiceTableTwo101(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表10-1搜索查询
    @PostMapping("/getSeekInfoTwo101")
    public List<crop_01_02_table_10_01> getSeekInfoTwo101(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                          @RequestParam("plantWay") String plantWay,
                                                          @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_10_01> Info91 = tableTwoService.getSeekInfoTwo101(county,areaType,riceType,plantWay,year);
        ////System.out.println(Info11);
        return Info91;
    };

    //双季稻表10-2
    @PostMapping("/getRiceTableTwo102")
    public List<crop_01_02_table_10_02> getRiceTableTwo102(@RequestParam("county") String county,
                                                           @RequestParam("areaType") String areaType,
                                                           @RequestParam("year") String year){
        
        List<crop_01_02_table_10_02> tableList91 = tableTwoService.getRiceTableTwo102(county,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表10-2
    @PostMapping("/getRiceTableTwo102new")
    public List<crop_01_02_table_10_02> getRiceTableTwo102new(@RequestParam("temp") String area,
                                                           @RequestParam("areaType") String areaType,
                                                           @RequestParam("year") String year){

        List<crop_01_02_table_10_02> tableList91 = tableTwoService.getRiceTableTwo102new(area,areaType,year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTableTwo102")
    public List<crop_01_02_table_10_02> getAuditRiceTableTwo102(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_10_02> tableList91 = tableTwoService.getAuditRiceTableTwo102(year);
        ////System.out.println(tableList);
        return tableList91;
    };
    //双季稻表10-2搜索查询
    @PostMapping("/getSeekInfoTwo102")
    public List<crop_01_02_table_10_02> getSeekInfoTwo102(@RequestParam("county") String county,
                                                         @RequestParam("areaType") String areaType,
                                                         @RequestParam("riceType") String riceType,
                                                          @RequestParam("plantWay") String plantWay,
                                                          @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_02_table_10_02> Info91 = tableTwoService.getSeekInfoTwo102(county,areaType,riceType,plantWay,year);
        ////System.out.println(Info11);
        return Info91;
    };
    //所有县未审核数据
    @GetMapping("/getAllAuditData2")
    public  String getAllAuditData2(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //双季稻
        List<crop_01_02_table_01_01> two11 = tableTwoService.getAuditRiceTableTwo11(year);
        List<crop_01_02_table_01_02> two12 = tableTwoService.getAuditRiceTableTwo12(year);
        List<crop_01_02_table_02_02> two22 = tableTwoService.getAuditRiceTableTwo22(year);
        List<crop_01_02_table_03_01> two31 = tableTwoService.getAuditRiceTableTwo31(year);
        List<crop_01_02_table_03_02> two32 = tableTwoService.getAuditRiceTableTwo32(year);
        List<crop_01_02_table_03_03> two33 = tableTwoService.getAuditRiceTableTwo33(year);
        List<crop_01_02_table_03_04> two34 = tableTwoService.getAuditRiceTableTwo34(year);
        List<crop_01_02_table_03_05> two35 = tableTwoService.getAuditRiceTableTwo35(year);
        List<crop_01_02_table_03_06> two36 = tableTwoService.getAuditRiceTableTwo36(year);
        List<crop_01_02_table_04_01> two41 = tableTwoService.getAuditRiceTableTwo41(year);
        List<crop_01_02_table_04_02> two42 = tableTwoService.getAuditRiceTableTwo42(year);
        List<crop_01_02_table_04_03> two43 = tableTwoService.getAuditRiceTableTwo43(year);
        List<crop_01_02_table_04_04> two44 = tableTwoService.getAuditRiceTableTwo44(year);
        List<crop_01_02_table_05_01> two51 = tableTwoService.getAuditRiceTableTwo51(year);
        List<crop_01_02_table_06_01> two61 = tableTwoService.getAuditRiceTableTwo61(year);
        List<crop_01_02_table_06_02> two62 = tableTwoService.getAuditRiceTableTwo62(year);
        List<crop_01_02_table_06_03> two63 = tableTwoService.getAuditRiceTableTwo63(year);
        List<crop_01_02_table_06_04> two64 = tableTwoService.getAuditRiceTableTwo64(year);
        List<crop_01_02_table_06_05> two65 = tableTwoService.getAuditRiceTableTwo65(year);
        List<crop_01_02_table_07_01> two71 = tableTwoService.getAuditRiceTableTwo71(year);
        List<crop_01_02_table_07_02> two72 = tableTwoService.getAuditRiceTableTwo72(year);
        List<crop_01_02_table_07_03> two73 = tableTwoService.getAuditRiceTableTwo73(year);
        List<crop_01_02_table_07_04> two74 = tableTwoService.getAuditRiceTableTwo74(year);
        List<crop_01_02_table_08_01> two81 = tableTwoService.getAuditRiceTableTwo81(year);
        List<crop_01_02_table_08_02> two82 = tableTwoService.getAuditRiceTableTwo82(year);
        List<crop_01_02_table_09_01> two91 = tableTwoService.getAuditRiceTableTwo91(year);
        List<crop_01_02_table_09_02> two92 = tableTwoService.getAuditRiceTableTwo92(currentYear,year);
        List<crop_01_02_table_09_03> two93 = tableTwoService.getAuditRiceTableTwo93(year);
        List<crop_01_02_table_09_04> two94 = tableTwoService.getAuditRiceTableTwo94(year);
        List<crop_01_02_table_09_05> two95 = tableTwoService.getAuditRiceTableTwo95(year);
        List<crop_01_02_table_09_06> two96 = tableTwoService.getAuditRiceTableTwo96(year);
        List<crop_01_02_table_09_07> two97 = tableTwoService.getAuditRiceTableTwo97(year);
        List<crop_01_02_table_10_01> two101 = tableTwoService.getAuditRiceTableTwo101(year);
        List<crop_01_02_table_10_02> two102 = tableTwoService.getAuditRiceTableTwo102(year);
        int num1=two11.size();int num2=two12.size();int num3=two22.size();int num4=two31.size();int num5=two32.size();int num6=two33.size();int num7=two34.size();int num8=two35.size();int num9=two36.size();int num10=two41.size();
        int num11=two42.size();int num12=two43.size();int num13=two44.size();int num14=two51.size();int num15=two61.size();int num16=two62.size();int num17=two63.size();int num18=two64.size();int num19=two65.size();int num20=two71.size();
        int num21=two72.size();int num22=two73.size();int num23=two74.size();int num24=two81.size();int num25=two82.size();int num26=two91.size();int num27=two92.size();int num28=two93.size();int num29=two94.size();int num30=two95.size();
        int num31=two96.size();int num32=two97.size();int num33=two101.size();int num34=two102.size();
        int num[]= new int[62];
        num[0]=num1;num[1]=num[0]+num2;num[2]=num[1]+num3;num[3]=num[2]+num4;num[4]=num[3]+num5;num[5]=num[4]+num6;num[6]=num[5]+num7;num[7]=num[6]+num8;num[8]=num[7]+num9;num[9]=num[8]+num10;
        num[10]=num[9]+num11;num[11]=num[10]+num12;num[12]=num[11]+num13;num[13]=num[12]+num14;num[14]=num[13]+num15;num[15]=num[14]+num16;num[16]=num[15]+num17;num[17]=num[16]+num18;num[18]=num[17]+num19;num[19]=num[18]+num20;
        num[20]=num[19]+num21;num[21]=num[20]+num22;num[22]=num[21]+num23;num[23]=num[22]+num24;num[24]=num[23]+num25;num[25]=num[24]+num26;num[26]=num[25]+num27;num[27]=num[26]+num28;
        num[28]=num[27]+num29;num[29]=num[28]+num30;num[30]=num[29]+num31;num[31]=num[30]+num32;num[32]=num[31]+num33;num[33]=num[32]+num34;
        int total=num[33];
        String county[]=new String[total];
        String tableName[]=new String[total];
        String chineseName[]=new String[total];
        String record[]=new String[total];
        String time[]=new String[total];
        String people[]=new String[total];
        //双季稻
        for(int i=0;i<num1;++i){
            county[i]=two11.get(i).getField_1();
            tableName[i]="crop_01_02_table_01_01";
            chineseName[i]="年度监测县(市、区)水稻栽插概况调查表";
            record[i]= String.valueOf(two11.get(i).getId());
            time[i]= String.valueOf(two11.get(i).getModification_time());
            people[i]=two11.get(i).getPeople();
        };
        for(int i=0;i<num2;++i){
            county[i+num[0]]=two12.get(i).getCounty();
            tableName[i+num[0]]="crop_01_02_table_01_02";
            chineseName[i+num[0]]="年度监测县(市、区)水稻生产主推品种调查表";
            record[i+num[0]]= String.valueOf(two12.get(i).getId());
            time[i+num[0]]= String.valueOf(two12.get(i).getModification_time());
            people[i+num[0]]=two12.get(i).getPeople();
        };
        for(int i=0;i<num3;++i){
            county[i+num[1]]=two22.get(i).getField_2_2();
            tableName[i+num[1]]="crop_01_02_table_02_02";
            chineseName[i+num[1]]="年度水稻监测点汇总表";
            record[i+num[1]]= String.valueOf(two22.get(i).getId());
            time[i+num[1]]= String.valueOf(two22.get(i).getModification_time());
            people[i+num[1]]=two22.get(i).getPeople();
        };
        for(int i=0;i<num4;++i){
            county[i+num[2]]=two31.get(i).getField_1();
            tableName[i+num[2]]="crop_01_02_table_03_01";
            chineseName[i+num[2]]="年度监测县(市、区)水稻播栽进度调查表（早稻）";
            record[i+num[2]]= String.valueOf(two31.get(i).getId());
            time[i+num[2]]= String.valueOf(two31.get(i).getModification_time());
            people[i+num[2]]=two31.get(i).getPeople();
        };
        for(int i=0;i<num5;++i){
            county[i+num[3]]=two32.get(i).getField_1();
            tableName[i+num[3]]="crop_01_02_table_03_02";
            chineseName[i+num[3]]="年度监测县(市、区)水稻播栽进度调查表（晚稻）";
            record[i+num[3]]= String.valueOf(two32.get(i).getId());
            time[i+num[3]]= String.valueOf(two32.get(i).getModification_time());
            people[i+num[3]]=two32.get(i).getPeople();
        };
        for(int i=0;i<num6;++i){
            county[i+num[4]]=two33.get(i).getField_1();
            tableName[i+num[4]]="crop_01_02_table_03_03";
            chineseName[i+num[4]]="年度监测县(市、区)水稻苗情评价动态列表（移栽早稻）";
            record[i+num[4]]= String.valueOf(two33.get(i).getId());
            time[i+num[4]]= String.valueOf(two33.get(i).getModification_time());
            people[i+num[4]]=two33.get(i).getPeople();
        };
        for(int i=0;i<num7;++i){
            county[i+num[5]]=two34.get(i).getField_1();
            tableName[i+num[5]]="crop_01_02_table_03_04";
            chineseName[i+num[5]]="年度监测县(市、区)水稻苗情评价动态列表（直播早稻）";
            record[i+num[5]]= String.valueOf(two34.get(i).getId());
            time[i+num[5]]= String.valueOf(two34.get(i).getModification_time());
            people[i+num[5]]=two34.get(i).getPeople();
        };
        for(int i=0;i<num8;++i){
            county[i+num[6]]=two35.get(i).getField_1();
            tableName[i+num[6]]="crop_01_02_table_03_05";
            chineseName[i+num[6]]="年度监测县(市、区)水稻苗情评价动态列表（移栽晚稻）";
            record[i+num[6]]= String.valueOf(two35.get(i).getId());
            time[i+num[6]]= String.valueOf(two35.get(i).getModification_time());
            people[i+num[6]]=two35.get(i).getPeople();
        };
        for(int i=0;i<num9;++i){
            county[i+num[7]]=two36.get(i).getField_1();
            tableName[i+num[7]]="crop_01_02_table_03_06";
            chineseName[i+num[7]]="年度监测县(市、区)水稻苗情评价动态列表（直播晚稻）";
            record[i+num[7]]= String.valueOf(two36.get(i).getId());
            time[i+num[7]]= String.valueOf(two36.get(i).getModification_time());
            people[i+num[7]]=two36.get(i).getPeople();
        };
        for(int i=0;i<num10;++i){
            county[i+num[8]]=two41.get(i).getField_1();
            tableName[i+num[8]]="crop_01_02_table_04_01";
            chineseName[i+num[8]]="年度监测县(市、区)双季稻生育进度调查表（移栽稻）";
            record[i+num[8]]= String.valueOf(two41.get(i).getId());
            time[i+num[8]]= String.valueOf(two41.get(i).getModification_time());
            people[i+num[8]]=two41.get(i).getPeople();
        };
        for(int i=0;i<num11;++i){
            county[i+num[9]]=two42.get(i).getField_1();
            tableName[i+num[9]]="crop_01_02_table_04_02";
            chineseName[i+num[9]]="年度监测县(市、区)双季稻生育进度调查表（直播稻）";
            record[i+num[9]]= String.valueOf(two42.get(i).getId());
            time[i+num[9]]= String.valueOf(two42.get(i).getModification_time());
            people[i+num[9]]=two42.get(i).getPeople();
        };
        for(int i=0;i<num12;++i){
            county[i+num[10]]=two43.get(i).getField_1();
            tableName[i+num[10]]="crop_01_02_table_04_03";
            chineseName[i+num[10]]="年度水稻监测点茎蘖数叶面积构成表";
            record[i+num[10]]= String.valueOf(two43.get(i).getId());
            time[i+num[10]]= String.valueOf(two43.get(i).getModification_time());
            people[i+num[10]]=two43.get(i).getPeople();
        };
        for(int i=0;i<num13;++i){
            county[i+num[11]]=two44.get(i).getField_1();
            tableName[i+num[11]]="crop_01_02_table_04_04";
            chineseName[i+num[11]]="年度水稻监测点苗情生育期统计表";
            record[i+num[11]]= String.valueOf(two44.get(i).getId());
            time[i+num[11]]= String.valueOf(two44.get(i).getModification_time());
            people[i+num[11]]=two44.get(i).getPeople();
        };
        for(int i=0;i<num14;++i){
            county[i+num[12]]=two51.get(i).getField_1();
            tableName[i+num[12]]="crop_01_02_table_05_01";
            chineseName[i+num[12]]="叶片含氮测定仪导出数据表";
            record[i+num[12]]= String.valueOf(two51.get(i).getId());
            time[i+num[12]]= String.valueOf(two51.get(i).getModification_time());
            people[i+num[12]]=two51.get(i).getPeople();
        };
        for(int i=0;i<num15;++i){
            county[i+num[13]]=two61.get(i).getField_1();
            tableName[i+num[13]]="crop_01_02_table_06_01";
            chineseName[i+num[13]]="年度水稻监测点水分管理记录表";
            record[i+num[13]]= String.valueOf(two61.get(i).getId());
            time[i+num[13]]= String.valueOf(two61.get(i).getModification_time());
            people[i+num[13]]=two61.get(i).getPeople();
        };
        for(int i=0;i<num16;++i){
            county[i+num[14]]=two62.get(i).getField_1();
            tableName[i+num[14]]="crop_01_02_table_06_02";
            chineseName[i+num[14]]="年度水稻监测点化除管理记录表";
            record[i+num[14]]= String.valueOf(two62.get(i).getId());
            time[i+num[14]]= String.valueOf(two62.get(i).getModification_time());
            people[i+num[14]]=two62.get(i).getPeople();
        };
        for(int i=0;i<num17;++i){
            county[i+num[15]]=two63.get(i).getField_1();
            tableName[i+num[15]]="crop_01_02_table_06_03";
            chineseName[i+num[15]]="年度水稻监测点病虫害防治记录表";
            record[i+num[15]]= String.valueOf(two63.get(i).getId());
            time[i+num[15]]= String.valueOf(two63.get(i).getModification_time());
            people[i+num[15]]=two63.get(i).getPeople();
        };
        for(int i=0;i<num18;++i){
            county[i+num[16]]=two64.get(i).getField_1();
            tableName[i+num[16]]="crop_01_02_table_06_04";
            chineseName[i+num[16]]="年度水稻监测点施肥管理记录表";
            record[i+num[16]]= String.valueOf(two64.get(i).getId());
            time[i+num[16]]= String.valueOf(two64.get(i).getModification_time());
            people[i+num[16]]=two64.get(i).getPeople();
        };
        for(int i=0;i<num19;++i){
            county[i+num[17]]=two65.get(i).getField_1();
            tableName[i+num[17]]="crop_01_02_table_06_05";
            chineseName[i+num[17]]="年度水稻监测点化学调节剂使用记录表";
            record[i+num[17]]= String.valueOf(two65.get(i).getId());
            time[i+num[17]]= String.valueOf(two65.get(i).getModification_time());
            people[i+num[17]]=two65.get(i).getPeople();
        };
        for(int i=0;i<num20;++i){
            county[i+num[18]]=two71.get(i).getField_1();
            tableName[i+num[18]]="crop_01_02_table_07_01";
            chineseName[i+num[18]]="年度监测县(市、区)水稻双季稻苗情监测预测产表";
            record[i+num[18]]= String.valueOf(two71.get(i).getId());
            time[i+num[18]]= String.valueOf(two71.get(i).getModification_time());
            people[i+num[18]]=two71.get(i).getPeople();
        };
        for(int i=0;i<num21;++i){
            county[i+num[19]]=two72.get(i).getField_1();
            tableName[i+num[19]]="crop_01_02_table_07_02";
            chineseName[i+num[19]]="年度监测县(市、区)水稻双季稻收获进度调查（早稻）";
            record[i+num[19]]= String.valueOf(two72.get(i).getId());
            time[i+num[19]]= String.valueOf(two72.get(i).getModification_time());
            people[i+num[19]]=two72.get(i).getPeople();
        };
        for(int i=0;i<num22;++i){
            county[i+num[20]]=two73.get(i).getField_1();
            tableName[i+num[20]]="crop_01_02_table_07_03";
            chineseName[i+num[20]]="年度监测县(市、区)水稻双季稻收获进度调查（晚稻）";
            record[i+num[20]]= String.valueOf(two73.get(i).getId());
            time[i+num[20]]= String.valueOf(two73.get(i).getModification_time());
            people[i+num[20]]=two73.get(i).getPeople();
        };
        for(int i=0;i<num23;++i){
            county[i+num[21]]=two74.get(i).getField_1();
            tableName[i+num[21]]="crop_01_02_table_07_04";
            chineseName[i+num[21]]="年度监测县(市、区)水稻双季稻测产表";
            record[i+num[21]]= String.valueOf(two74.get(i).getId());
            time[i+num[21]]= String.valueOf(two74.get(i).getModification_time());
            people[i+num[21]]=two74.get(i).getPeople();
        };
        for(int i=0;i<num24;++i){
            county[i+num[22]]=two81.get(i).getField_1();
            tableName[i+num[22]]="crop_01_02_table_08_01";
            chineseName[i+num[22]]="年度监测县(市、区)水稻生产成本效益统计表（早稻）";
            record[i+num[22]]= String.valueOf(two81.get(i).getId());
            time[i+num[22]]= String.valueOf(two81.get(i).getModification_time());
            people[i+num[22]]=two81.get(i).getPeople();
        };
        for(int i=0;i<num25;++i){
            county[i+num[23]]=two82.get(i).getField_1();
            tableName[i+num[23]]="crop_01_02_table_08_02";
            chineseName[i+num[23]]="年度监测县(市、区)水稻生产成本效益统计表（晚稻）";
            record[i+num[23]]= String.valueOf(two82.get(i).getId());
            time[i+num[23]]= String.valueOf(two82.get(i).getModification_time());
            people[i+num[23]]=two82.get(i).getPeople();
        };
        for(int i=0;i<num26;++i){
            county[i+num[24]]=two91.get(i).getField_1();
            tableName[i+num[24]]="crop_01_02_table_09_01";
            chineseName[i+num[24]]="年度监测县(市、区)气象资料列表";
            record[i+num[24]]= String.valueOf(two91.get(i).getId());
            time[i+num[24]]= String.valueOf(two91.get(i).getModification_time());
            people[i+num[24]]=two91.get(i).getPeople();
        };
        for(int i=0;i<num27;++i){
            county[i+num[25]]=two92.get(i).getField_1_1();
            tableName[i+num[25]]="crop_01_02_table_09_02";
            chineseName[i+num[25]]="年度监测县(市、区)抽穗期前、后各15天气象资料";
            record[i+num[25]]= String.valueOf(two92.get(i).getId());
            time[i+num[25]]= String.valueOf(two92.get(i).getModification_time());
            people[i+num[25]]=two92.get(i).getPeople();
        };
        for(int i=0;i<num28;++i){
            county[i+num[26]]=two93.get(i).getField_1();
            tableName[i+num[26]]="crop_01_02_table_09_03";
            chineseName[i+num[26]]="年度监测县(市、区)水稻倒伏灾害表";
            record[i+num[26]]= String.valueOf(two93.get(i).getId());
            time[i+num[26]]= String.valueOf(two93.get(i).getModification_time());
            people[i+num[26]]=two93.get(i).getPeople();
        };
        for(int i=0;i<num29;++i){
            county[i+num[27]]=two94.get(i).getField_1();
            tableName[i+num[27]]="crop_01_02_table_09_04";
            chineseName[i+num[27]]="年度监测县(市、区)水稻低温冷害表";
            record[i+num[27]]= String.valueOf(two94.get(i).getId());
            time[i+num[27]]= String.valueOf(two94.get(i).getModification_time());
            people[i+num[27]]=two94.get(i).getPeople();
        };
        for(int i=0;i<num30;++i){
            county[i+num[28]]=two95.get(i).getField_1();
            tableName[i+num[28]]="crop_01_02_table_09_05";
            chineseName[i+num[28]]="年度监测县(市、区)高温热害表";
            record[i+num[28]]= String.valueOf(two95.get(i).getId());
            time[i+num[28]]= String.valueOf(two95.get(i).getModification_time());
            people[i+num[28]]=two95.get(i).getPeople();
        };
        for(int i=0;i<num31;++i){
            county[i+num[29]]=two96.get(i).getField_1();
            tableName[i+num[29]]="crop_01_02_table_09_06";
            chineseName[i+num[29]]="年度监测县(市、区)水稻涝害调查表";
            record[i+num[29]]= String.valueOf(two96.get(i).getId());
            time[i+num[29]]= String.valueOf(two96.get(i).getModification_time());
            people[i+num[29]]=two96.get(i).getPeople();
        };
        for(int i=0;i<num32;++i){
            county[i+num[30]]=two97.get(i).getField_1();
            tableName[i+num[30]]="crop_01_02_table_09_07";
            chineseName[i+num[30]]="年度水稻监测点洪涝灾害表";
            record[i+num[30]]= String.valueOf(two97.get(i).getId());
            time[i+num[30]]= String.valueOf(two97.get(i).getModification_time());
            people[i+num[30]]=two97.get(i).getPeople();
        };
        for(int i=0;i<num33;++i){
            county[i+num[31]]=two101.get(i).getField_1();
            tableName[i+num[31]]="crop_01_02_table_10_01";
            chineseName[i+num[31]]="年度水稻监测点灌浆速率调查表（早稻）";
            record[i+num[31]]= String.valueOf(two101.get(i).getId());
            time[i+num[31]]= String.valueOf(two101.get(i).getModification_time());
            people[i+num[31]]=two101.get(i).getPeople();
        };
        for(int i=0;i<num34;++i){
            county[i+num[32]]=two102.get(i).getField_1();
            tableName[i+num[32]]="crop_01_02_table_10_02";
            chineseName[i+num[32]]="年度水稻监测点灌浆速率调查表（晚稻）";
            record[i+num[32]]= String.valueOf(two102.get(i).getId());
            time[i+num[32]]= String.valueOf(two102.get(i).getModification_time());
            people[i+num[32]]=two102.get(i).getPeople();
        };
        for(int i=0;i<total;i++){
            //System.out.println("县："+county[i]+",表名："+tableName[i]+",记录："+record[i]+",时间:"+time[i]+",审核人:"+people[i]);
        };
        for(int i=0;i<total;i++) {
            String city=county[i];
            String name=tableName[i];
            String chinese=chineseName[i];
            String recordId =record[i];
            int dataId = Integer.parseInt(recordId);
            String times= time[i];
            String auditPeople=people[i];
            int auditData = tableTwoService.getAuditData2(city,name,chinese,dataId,times,auditPeople);
        };
        int deleteData=tableTwoService.deleteRepeatData2();
        return null;
    };

    //获取未审核数据
    @GetMapping("/getNoAuditAllData2")
    public List<table_audit_2> getNoAuditAllData2(){
        List<table_audit_2> auditData=tableTwoService.getNoAuditAllData2();
        return auditData;
    }
    //获取未审核数据
    @PostMapping("/getNoAuditInfo2")
    public pageBean<table_audit_2> getNoAuditInfo2(@RequestParam("params1") String params1,
                                                    @RequestParam("start") int start,
                                                    @RequestParam("pageSize") int pageSize){

        pageBean<table_audit_2> auditData=tableTwoService.getNoAuditInfo2(params1,start,pageSize);
        //System.out.println(auditData);
        return auditData;
    };

    //获取双季稻未审核表各表数据分页
    @PostMapping("/getRiceTwoAudit1")
    public pageBean<? extends Object> getRiceTwoAudit1(@RequestParam("params1") String params1,
                                                    @RequestParam("riceTableName") String riceTableName,
                                                    @RequestParam("start") int start,
                                                    @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+riceTableName+start+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<? extends Object> auditInfo = tableTwoService.getRiceTwoAudit1(params1,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //搜素双季稻未审核表各表数据分页
    @PostMapping("/searchRiceAuditTwo1")
    public pageBean<? extends Object> searchRiceAuditTwo1(@RequestParam("params1") String params1,
                                                          @RequestParam("month") String month,
                                                       @RequestParam("riceTableName") String riceTableName,
                                                       @RequestParam("start") int start,
                                                       @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+riceTableName+start+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<? extends Object> auditInfo = tableTwoService.searchRiceAuditTwo1(params1,month,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //删除辅助审核表对应数据
    @PostMapping("/deleteRecord2")
    public String deleteRecord2(@RequestParam("rowId") int rowId,
                               @RequestParam("rowName") String rowName,
                               @RequestParam("rowTime") String rowTime){
        int deleteData= tableTwoService.deleteRecord2(rowId, rowName, rowTime);
        //System.out.println(deleteData);
        return "success";
    }
    //获取双季稻通过监测点数
    @PostMapping("/getRiceTableTwo21")
    public List<crop_01_02_table_02_01> getRiceTableTwo21(@RequestParam("county") String county){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_02_table_02_01> table21 = tableTwoService.getRiceTableTwo21(year,county);
        //System.out.println(table21);
        return table21;
    };

    //获取未审核表1-2各表数据分页
    @PostMapping("/getRiceTableAuditTwo12")
    public ArrayList<HashMap<String,Object> > getRiceTableAuditTwo12(@RequestParam("params1") String params1,
                                                                  @RequestParam("start") int start,
                                                                  @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        ArrayList<HashMap<String,Object> > auditInfo = tableTwoService.getRiceTableAuditTwo12(params1,start,pageSize,year);
        return auditInfo;
    };
    //搜索未审核表1-2数据分页
    @PostMapping("/searchRiceAuditTwo12")
    public ArrayList<HashMap<String,Object> > searchRiceAuditTwo12(@RequestParam("params1") String params1,
                                                                @RequestParam("month") String month,
                                                                @RequestParam("riceTableName") String riceTableName,
                                                                @RequestParam("start") int start,
                                                                @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        ArrayList<HashMap<String,Object> > auditInfo = tableTwoService.searchRiceAuditTwo12(params1,month,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //处理未上报县数据
    @GetMapping("/getNoReportData2")
    public List<HashMap<String,Object>> getNoReportData2(){
        List<HashMap<String ,Object>> info = tableTwoService.getNoReportData2();
        return info;
    }
    //获取各表中所有县未审核数据县名和个数
    @GetMapping("/getAllCountyNoAuditNumber2")
    public List<String> getAllCountyNoAuditNumber2(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //双季稻
        List<crop_01_02_table_01_01> two11 = tableTwoService.getAuditRiceTableTwo11(year);
        List<crop_01_02_table_01_02> two12 = tableTwoService.getAuditRiceTableTwo12(year);
        List<crop_01_02_table_03_01> two31 = tableTwoService.getAuditRiceTableTwo31(year);
        List<crop_01_02_table_03_02> two32 = tableTwoService.getAuditRiceTableTwo32(year);
        List<crop_01_02_table_03_03> two33 = tableTwoService.getAuditRiceTableTwo33(year);
        List<crop_01_02_table_03_04> two34 = tableTwoService.getAuditRiceTableTwo34(year);
        List<crop_01_02_table_03_05> two35 = tableTwoService.getAuditRiceTableTwo35(year);
        List<crop_01_02_table_03_06> two36 = tableTwoService.getAuditRiceTableTwo36(year);
        List<crop_01_02_table_04_01> two41 = tableTwoService.getAuditRiceTableTwo41(year);
        List<crop_01_02_table_04_02> two42 = tableTwoService.getAuditRiceTableTwo42(year);
        List<crop_01_02_table_04_03> two43 = tableTwoService.getAuditRiceTableTwo43(year);
        List<crop_01_02_table_04_04> two44 = tableTwoService.getAuditRiceTableTwo44(year);
        List<crop_01_02_table_05_01> two51 = tableTwoService.getAuditRiceTableTwo51(year);
        List<crop_01_02_table_06_01> two61 = tableTwoService.getAuditRiceTableTwo61(year);
        List<crop_01_02_table_06_02> two62 = tableTwoService.getAuditRiceTableTwo62(year);
        List<crop_01_02_table_06_03> two63 = tableTwoService.getAuditRiceTableTwo63(year);
        List<crop_01_02_table_06_04> two64 = tableTwoService.getAuditRiceTableTwo64(year);
        List<crop_01_02_table_06_05> two65 = tableTwoService.getAuditRiceTableTwo65(year);
        List<crop_01_02_table_07_01> two71 = tableTwoService.getAuditRiceTableTwo71(year);
        List<crop_01_02_table_07_02> two72 = tableTwoService.getAuditRiceTableTwo72(year);
        List<crop_01_02_table_07_03> two73 = tableTwoService.getAuditRiceTableTwo73(year);
        List<crop_01_02_table_07_04> two74 = tableTwoService.getAuditRiceTableTwo74(year);
        List<crop_01_02_table_08_01> two81 = tableTwoService.getAuditRiceTableTwo81(year);
        List<crop_01_02_table_08_02> two82 = tableTwoService.getAuditRiceTableTwo82(year);
        List<crop_01_02_table_09_03> two93 = tableTwoService.getAuditRiceTableTwo93(year);
        List<crop_01_02_table_09_04> two94 = tableTwoService.getAuditRiceTableTwo94(year);
        List<crop_01_02_table_09_05> two95 = tableTwoService.getAuditRiceTableTwo95(year);
        List<crop_01_02_table_09_06> two96 = tableTwoService.getAuditRiceTableTwo96(year);
        List<crop_01_02_table_09_07> two97 = tableTwoService.getAuditRiceTableTwo97(year);
        List<crop_01_02_table_10_01> two101 = tableTwoService.getAuditRiceTableTwo101(year);
        List<crop_01_02_table_10_02> two102 = tableTwoService.getAuditRiceTableTwo102(year);
        List<String> auditInfo = new ArrayList<String>();
        for(int i=0;i<two11.size();i++){
            if(two11.get(i).getIn_use().equals("1")){
                auditInfo.add(two11.get(i).getField_1());
            }
        };
        for(int i=0;i<two12.size();i++){
            if(two12.get(i).getIn_use().equals("1")){
                auditInfo.add(two12.get(i).getCounty());
            }
        };
        for(int i=0;i<two31.size();i++){
            if(two31.get(i).getIn_use().equals("1")){
                auditInfo.add(two31.get(i).getField_1());
            }
        };
        for(int i=0;i<two32.size();i++){
            if(two32.get(i).getIn_use().equals("1")){
                auditInfo.add(two32.get(i).getField_1());
            }
        };
        for(int i=0;i<two33.size();i++){
            if(two33.get(i).getIn_use().equals("1")){
                auditInfo.add(two33.get(i).getField_1());
            }
        };
        for(int i=0;i<two34.size();i++){
            if(two34.get(i).getIn_use().equals("1")){
                auditInfo.add(two34.get(i).getField_1());
            }
        };
        for(int i=0;i<two35.size();i++){
            if(two35.get(i).getIn_use().equals("1")){
                auditInfo.add(two35.get(i).getField_1());
            }
        };
        for(int i=0;i<two36.size();i++){
            if(two36.get(i).getIn_use().equals("1")){
                auditInfo.add(two36.get(i).getField_1());
            }
        };
        for(int i=0;i<two41.size();i++){
            if(two41.get(i).getIn_use().equals("1")){
                auditInfo.add(two41.get(i).getField_1());
            }
        };
        for(int i=0;i<two42.size();i++){
            if(two42.get(i).getIn_use().equals("1")){
                auditInfo.add(two42.get(i).getField_1());
            }
        };
        for(int i=0;i<two43.size();i++){
            if(two43.get(i).getIn_use().equals("1")){
                auditInfo.add(two43.get(i).getField_1());
            }
        };
        for(int i=0;i<two44.size();i++){
            if(two44.get(i).getIn_use().equals("1")){
                auditInfo.add(two44.get(i).getField_1());
            }
        };
        for(int i=0;i<two51.size();i++){
            if(two51.get(i).getIn_use().equals("1")){
                auditInfo.add(two51.get(i).getField_1());
            }
        };
        for(int i=0;i<two61.size();i++){
            if(two61.get(i).getIn_use().equals("1")){
                auditInfo.add(two61.get(i).getField_1());
            }
        };
        for(int i=0;i<two62.size();i++){
            if(two62.get(i).getIn_use().equals("1")){
                auditInfo.add(two62.get(i).getField_1());
            }
        };
        for(int i=0;i<two63.size();i++){
            if(two63.get(i).getIn_use().equals("1")){
                auditInfo.add(two63.get(i).getField_1());
            }
        };
        for(int i=0;i<two64.size();i++){
            if(two64.get(i).getIn_use().equals("1")){
                auditInfo.add(two64.get(i).getField_1());
            }
        };
        for(int i=0;i<two65.size();i++){
            if(two65.get(i).getIn_use().equals("1")){
                auditInfo.add(two65.get(i).getField_1());
            }
        };
        for(int i=0;i<two71.size();i++){
            if(two71.get(i).getIn_use().equals("1")){
                auditInfo.add(two71.get(i).getField_1());
            }
        };
        for(int i=0;i<two72.size();i++){
            if(two72.get(i).getIn_use().equals("1")){
                auditInfo.add(two72.get(i).getField_1());
            }
        };
        for(int i=0;i<two73.size();i++){
            if(two73.get(i).getIn_use().equals("1")){
                auditInfo.add(two73.get(i).getField_1());
            }
        };
        for(int i=0;i<two74.size();i++){
            if(two74.get(i).getIn_use().equals("1")){
                auditInfo.add(two74.get(i).getField_1());
            }
        };
        for(int i=0;i<two81.size();i++){
            if(two81.get(i).getIn_use().equals("1")){
                auditInfo.add(two81.get(i).getField_1());
            }
        };
        for(int i=0;i<two82.size();i++){
            if(two82.get(i).getIn_use().equals("1")){
                auditInfo.add(two82.get(i).getField_1());
            }
        };
        for(int i=0;i<two93.size();i++){
            if(two93.get(i).getIn_use().equals("1")){
                auditInfo.add(two93.get(i).getField_1());
            }
        };
        for(int i=0;i<two94.size();i++){
            if(two94.get(i).getIn_use().equals("1")){
                auditInfo.add(two94.get(i).getField_1());
            }
        };
        for(int i=0;i<two95.size();i++){
            if(two95.get(i).getIn_use().equals("1")){
                auditInfo.add(two95.get(i).getField_1());
            }
        };
        for(int i=0;i<two96.size();i++){
            if(two96.get(i).getIn_use().equals("1")){
                auditInfo.add(two96.get(i).getField_1());
            }
        };
        for(int i=0;i<two97.size();i++){
            if(two97.get(i).getIn_use().equals("1")){
                auditInfo.add(two97.get(i).getField_1());
            }
        };
        for(int i=0;i<two101.size();i++){
            if(two101.get(i).getIn_use().equals("1")){
                auditInfo.add(two101.get(i).getField_1());
            }
        };
        for(int i=0;i<two102.size();i++){
            if(two102.get(i).getIn_use().equals("1")){
                auditInfo.add(two102.get(i).getField_1());
            }
        };
        return auditInfo;
    }

    //获取各表中未审核数据个数
    @GetMapping("/getDataNumber2")
    public List<Integer> getDataNumber2(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //双季稻
        List<crop_01_02_table_01_01> two11 = tableTwoService.getAuditRiceTableTwo11(year);
        List<crop_01_02_table_01_02> two12 = tableTwoService.getAuditRiceTableTwo12(year);
        List<crop_01_02_table_03_01> two31 = tableTwoService.getAuditRiceTableTwo31(year);
        List<crop_01_02_table_03_02> two32 = tableTwoService.getAuditRiceTableTwo32(year);
        List<crop_01_02_table_03_03> two33 = tableTwoService.getAuditRiceTableTwo33(year);
        List<crop_01_02_table_03_04> two34 = tableTwoService.getAuditRiceTableTwo34(year);
        List<crop_01_02_table_03_05> two35 = tableTwoService.getAuditRiceTableTwo35(year);
        List<crop_01_02_table_03_06> two36 = tableTwoService.getAuditRiceTableTwo36(year);
        List<crop_01_02_table_04_01> two41 = tableTwoService.getAuditRiceTableTwo41(year);
        List<crop_01_02_table_04_02> two42 = tableTwoService.getAuditRiceTableTwo42(year);
        List<crop_01_02_table_04_03> two43 = tableTwoService.getAuditRiceTableTwo43(year);
        List<crop_01_02_table_04_04> two44 = tableTwoService.getAuditRiceTableTwo44(year);
        List<crop_01_02_table_05_01> two51 = tableTwoService.getAuditRiceTableTwo51(year);
        List<crop_01_02_table_06_01> two61 = tableTwoService.getAuditRiceTableTwo61(year);
        List<crop_01_02_table_06_02> two62 = tableTwoService.getAuditRiceTableTwo62(year);
        List<crop_01_02_table_06_03> two63 = tableTwoService.getAuditRiceTableTwo63(year);
        List<crop_01_02_table_06_04> two64 = tableTwoService.getAuditRiceTableTwo64(year);
        List<crop_01_02_table_06_05> two65 = tableTwoService.getAuditRiceTableTwo65(year);
        List<crop_01_02_table_07_01> two71 = tableTwoService.getAuditRiceTableTwo71(year);
        List<crop_01_02_table_07_02> two72 = tableTwoService.getAuditRiceTableTwo72(year);
        List<crop_01_02_table_07_03> two73 = tableTwoService.getAuditRiceTableTwo73(year);
        List<crop_01_02_table_07_04> two74 = tableTwoService.getAuditRiceTableTwo74(year);
        List<crop_01_02_table_08_01> two81 = tableTwoService.getAuditRiceTableTwo81(year);
        List<crop_01_02_table_08_02> two82 = tableTwoService.getAuditRiceTableTwo82(year);
        List<crop_01_02_table_09_03> two93 = tableTwoService.getAuditRiceTableTwo93(year);
        List<crop_01_02_table_09_04> two94 = tableTwoService.getAuditRiceTableTwo94(year);
        List<crop_01_02_table_09_05> two95 = tableTwoService.getAuditRiceTableTwo95(year);
        List<crop_01_02_table_09_06> two96 = tableTwoService.getAuditRiceTableTwo96(year);
        List<crop_01_02_table_09_07> two97 = tableTwoService.getAuditRiceTableTwo97(year);
        List<crop_01_02_table_10_01> two101 = tableTwoService.getAuditRiceTableTwo101(year);
        List<crop_01_02_table_10_02> two102 = tableTwoService.getAuditRiceTableTwo102(year);
        List<Integer> dataNumber = new ArrayList<Integer>();
        dataNumber.add(two11.size());
        dataNumber.add(two12.size());
        dataNumber.add(two31.size());
        dataNumber.add(two32.size());
        dataNumber.add(two33.size());
        dataNumber.add(two34.size());
        dataNumber.add(two35.size());
        dataNumber.add(two36.size());
        dataNumber.add(two41.size());
        dataNumber.add(two42.size());
        dataNumber.add(two43.size());
        dataNumber.add(two44.size());
        dataNumber.add(two51.size());
        dataNumber.add(two61.size());
        dataNumber.add(two62.size());
        dataNumber.add(two63.size());
        dataNumber.add(two64.size());
        dataNumber.add(two65.size());
        dataNumber.add(two71.size());
        dataNumber.add(two72.size());
        dataNumber.add(two73.size());
        dataNumber.add(two74.size());
        dataNumber.add(two81.size());
        dataNumber.add(two82.size());
        dataNumber.add(two93.size());
        dataNumber.add(two94.size());
        dataNumber.add(two95.size());
        dataNumber.add(two96.size());
        dataNumber.add(two97.size());
        dataNumber.add(two101.size());
        dataNumber.add(two102.size());
        return dataNumber;
    }

    //双季稻监测点删除
    @PostMapping("/deletePoint2")
    public int deletePoint2(@RequestParam("pointInfo")String pointInfo){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        crop_01_02_table_02_02 deleteInfo = JSONObject.parseObject(pointInfo , crop_01_02_table_02_02.class);
        int info = tableTwoService.deletePoint2(deleteInfo, year);

        return 1;
    }


}
