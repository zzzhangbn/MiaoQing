package com.ricemonitoring.controller;


import com.alibaba.fastjson.JSONObject;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//解决跨域问题
@CrossOrigin(origins = "http://192.168.1.10:8888", maxAge = 3600)
public class tableController {
    @Autowired
    TableService tableService;

    //一季稻获取类型
    @GetMapping("/getCounty1")
    public List<sum_select_list>getCounty1(){
        List<sum_select_list> countyList = tableService.getCounty1();
        return countyList;
    };
    //记录有误退回
    @RequestMapping("/returnRecord")
    public String returnRecord(@RequestParam("returnTableName") String returnTableName,
                               @RequestParam("recordCounty") String recordCounty,
                               @RequestParam("recordId") int recordId,
                               @RequestParam("recordReason") String recordReason,
                               @RequestParam("recordTime") String recordTime,
                               @RequestParam("recordPeople") String recordPeople){
        // 今年数据要审核
         Calendar date = Calendar.getInstance();
         int yearTime = date.get(Calendar.YEAR);
         String year = String.valueOf(yearTime);
        if(returnTableName.equals("crop_01_01_table_01_02")){
            String result = tableService.returnTable12(recordCounty,year);
        }else if(returnTableName.equals("crop_01_02_table_01_02")){
            String result = tableService.returnTableTwo12(recordCounty,year);
        }else if(returnTableName != "crop_01_01_table_02_01" || returnTableName != "crop_01_02_table_02_01") {
            String result = tableService.returnRecord(returnTableName, recordId);
        }
        //记录退回到退回表中
        String recordResult =tableService.recordAdd(returnTableName,recordCounty,recordId,recordReason,recordTime,recordPeople);
        if(recordResult!= null){
            return "error";
        }else{
            return "success";
        }
    };
    //记录确认通过
    @RequestMapping("/confirmPass")
    public String confirmPass(@RequestParam("returnTableName") String returnTableName,
                              @RequestParam("recordCounty") String recordCounty,
                              @RequestParam("recordId") int recordId){
        // 今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        String passInfo="";
        if(returnTableName.equals("crop_01_01_table_01_02")){
            passInfo = tableService.confirmTable12(recordCounty,year);
        }else if(returnTableName.equals("crop_01_02_table_01_02")){
            passInfo = tableService.confirmTableTwo12(recordCounty,year);
        }
        else{
            passInfo = tableService.confirmPass(returnTableName,recordId);
        }
        if(passInfo!= null){
            return "error";
        }else{
            return "success";
        }
    };
    //一季稻各表审核数目
    @GetMapping("/getAuditNumber")
    public String[] getAuditNumber(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //System.out.println(currentYear);

        List<crop_01_01_table_01_01> table11 = tableService.getAuditRiceTable11(year);
        List<crop_01_01_table_01_02> table12 = tableService.getAuditRiceTable12(year);
        List<crop_01_01_table_02_02> table22 = tableService.getAuditRiceTable22(year);
        List<crop_01_01_table_03_01> table31 = tableService.getAuditRiceTable31(year);
        List<crop_01_01_table_03_02> table32 = tableService.getAuditRiceTable32(year);
        List<crop_01_01_table_03_03> table33 = tableService.getAuditRiceTable33(year);
        List<crop_01_01_table_04_01> table41 = tableService.getAuditRiceTable41(year);
        List<crop_01_01_table_04_02> table42 = tableService.getAuditRiceTable42(year);
        List<crop_01_01_table_04_03> table43 = tableService.getAuditRiceTable43(year);
        List<crop_01_01_table_04_04> table44 = tableService.getAuditRiceTable44(year);
        List<crop_01_01_table_05_01> table51 = tableService.getAuditRiceTable51(year);
        List<crop_01_01_table_06_01> table61 = tableService.getAuditRiceTable61(year);
        List<crop_01_01_table_06_02> table62 = tableService.getAuditRiceTable62(year);
        List<crop_01_01_table_06_03> table63 = tableService.getAuditRiceTable63(year);
        List<crop_01_01_table_06_04> table64 = tableService.getAuditRiceTable64(year);
        List<crop_01_01_table_06_05> table65 = tableService.getAuditRiceTable65(year);
        List<crop_01_01_table_07_01> table71 = tableService.getAuditRiceTable71(year);
        List<crop_01_01_table_07_02> table72 = tableService.getAuditRiceTable72(year);
        List<crop_01_01_table_07_03> table73 = tableService.getAuditRiceTable73(year);
        List<crop_01_01_table_08_01> table81 = tableService.getAuditRiceTable81(year);
        List<crop_01_01_table_09_01> table91 = tableService.getAuditRiceTable91(year);
        List<crop_01_01_table_09_02> table92 = tableService.getAuditRiceTable92(currentYear,year);
        List<crop_01_01_table_09_03> table93 = tableService.getAuditRiceTable93(year);
        List<crop_01_01_table_09_04> table94 = tableService.getAuditRiceTable94(year);
        List<crop_01_01_table_09_05> table95 = tableService.getAuditRiceTable95(year);
        List<crop_01_01_table_09_06> table96 = tableService.getAuditRiceTable96(year);
        List<crop_01_01_table_09_07> table97 = tableService.getAuditRiceTable97(year);
        List<crop_01_01_table_10_01> table101 = tableService.getAuditRiceTable101(year);
        String numberData[] = new String[28];
        String num1 = String.valueOf(table11.size()); String num2 = String.valueOf(table12.size()); String num3 = String.valueOf(table22.size()); String num4 = String.valueOf(table31.size());
        String num5 = String.valueOf(table32.size()); String num6 = String.valueOf(table33.size()); String num7 = String.valueOf(table41.size()); String num8 = String.valueOf(table42.size());
        String num9 = String.valueOf(table43.size()); String num10 = String.valueOf(table44.size()); String num11 = String.valueOf(table51.size()); String num12 = String.valueOf(table61.size());
        String num13 = String.valueOf(table62.size()); String num14 = String.valueOf(table63.size()); String num15 = String.valueOf(table64.size()); String num16 = String.valueOf(table65.size());
        String num17 = String.valueOf(table71.size()); String num18 = String.valueOf(table72.size()); String num19 = String.valueOf(table73.size()); String num20 = String.valueOf(table81.size());
        String num21 = String.valueOf(table91.size()); String num22 = String.valueOf(table92.size()); String num23 = String.valueOf(table93.size()); String num24 = String.valueOf(table94.size());
        String num25 = String.valueOf(table95.size()); String num26 = String.valueOf(table96.size()); String num27 = String.valueOf(table97.size()); String num28 = String.valueOf(table101.size());
        //System.out.println(table11.size());
        //System.out.println(table12.size());
        numberData[0]=num1;numberData[1]=num2;numberData[2]=num3;numberData[3]=num4;numberData[4]=num5;numberData[5]=num6;numberData[6]=num7;numberData[7]=num8;numberData[8]=num9;numberData[9]=num10;
        numberData[10]=num11;numberData[11]=num12;numberData[12]=num13;numberData[13]=num14;numberData[14]=num15;numberData[15]=num16;numberData[16]=num17;numberData[17]=num18;numberData[18]=num19;numberData[19]=num20;
        numberData[20]=num21;numberData[21]=num22;numberData[22]=num23;numberData[23]=num24;numberData[24]=num25;numberData[25]=num26;numberData[26]=num27;numberData[27]=num28;
        return numberData;
    };

    //各县对应未审核数据
    @GetMapping("/getNoAuditData")
    public String getNoAuditData(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //System.out.println(currentYear);
        List<crop_01_01_table_01_01> table11 = tableService.getAuditRiceTable11(year);
        List<crop_01_01_table_01_02> table12 = tableService.getAuditRiceTable12(year);
        List<crop_01_01_table_02_02> table22 = tableService.getAuditRiceTable22(year);
        List<crop_01_01_table_03_01> table31 = tableService.getAuditRiceTable31(year);
        List<crop_01_01_table_03_02> table32 = tableService.getAuditRiceTable32(year);
        List<crop_01_01_table_03_03> table33 = tableService.getAuditRiceTable33(year);
        List<crop_01_01_table_04_01> table41 = tableService.getAuditRiceTable41(year);
        List<crop_01_01_table_04_02> table42 = tableService.getAuditRiceTable42(year);
        List<crop_01_01_table_04_03> table43 = tableService.getAuditRiceTable43(year);
        List<crop_01_01_table_04_04> table44 = tableService.getAuditRiceTable44(year);
        List<crop_01_01_table_05_01> table51 = tableService.getAuditRiceTable51(year);
        List<crop_01_01_table_06_01> table61 = tableService.getAuditRiceTable61(year);
        List<crop_01_01_table_06_02> table62 = tableService.getAuditRiceTable62(year);
        List<crop_01_01_table_06_03> table63 = tableService.getAuditRiceTable63(year);
        List<crop_01_01_table_06_04> table64 = tableService.getAuditRiceTable64(year);
        List<crop_01_01_table_06_05> table65 = tableService.getAuditRiceTable65(year);
        List<crop_01_01_table_07_01> table71 = tableService.getAuditRiceTable71(year);
        List<crop_01_01_table_07_02> table72 = tableService.getAuditRiceTable72(year);
        List<crop_01_01_table_07_03> table73 = tableService.getAuditRiceTable73(year);
        List<crop_01_01_table_08_01> table81 = tableService.getAuditRiceTable81(year);
        List<crop_01_01_table_09_01> table91 = tableService.getAuditRiceTable91(year);
        List<crop_01_01_table_09_02> table92 = tableService.getAuditRiceTable92(currentYear,year);
        List<crop_01_01_table_09_03> table93 = tableService.getAuditRiceTable93(year);
        List<crop_01_01_table_09_04> table94 = tableService.getAuditRiceTable94(year);
        List<crop_01_01_table_09_05> table95 = tableService.getAuditRiceTable95(year);
        List<crop_01_01_table_09_06> table96 = tableService.getAuditRiceTable96(year);
        List<crop_01_01_table_09_07> table97 = tableService.getAuditRiceTable97(year);
        List<crop_01_01_table_10_01> table101 = tableService.getAuditRiceTable101(year);
        //获取所有县
        List<sum_select_list> citys=tableService.getCitys();
        String cityArray[] = new String[citys.size()];
        for(int i=0; i<citys.size(); i++){
            cityArray[i]= citys.get(i).getList();
            ////System.out.println(cityArray[i]);
        };
        //存县数据
        String cityNumber[]=new String[cityArray.length];
        for(int i=0; i<cityArray.length; i++) {
            int num1 = 0;int num2 = 0;int num3 = 0;int num4 = 0;int num5 = 0;int num6 = 0;int num7 = 0;int num8 = 0;int num9 = 0;int num10 = 0;
            int num11 = 0;int num12 = 0;int num13 = 0;int num14 = 0;int num15 = 0;int num16 = 0;int num17 = 0;int num18 = 0;int num19 = 0;int num20 = 0;
            int num21 = 0;int num22 = 0;int num23 = 0;int num24 = 0;int num25 = 0;int num26 = 0;int num27 = 0;int num28 = 0;
            for (int j = 0; j < table11.size(); j++) {
                if (cityArray[i] == table11.get(j).getField_1()) {
                    num1++;
                    //System.out.println(num1);
                }
            }
            ;
            for (int j = 0; j < table12.size(); j++) {
                if (cityArray[i] == table12.get(j).getCounty()) {
                    num2++;
                }
            }
            ;
            for (int j = 0; j < table22.size(); j++) {
                if (cityArray[i] == table22.get(j).getField_2_2()) {
                    num3++;
                }
            }
            ;
            for (int j = 0; j < table31.size(); j++) {
                if (cityArray[i] == table31.get(j).getField_1()) {
                    num4++;
                }
            }
            ;
            for (int j = 0; j < table32.size(); j++) {
                if (cityArray[i] == table32.get(j).getField_1()) {
                    num5++;
                }
            }
            ;
            for (int j = 0; j < table33.size(); j++) {
                if (cityArray[i] == table33.get(j).getField_1()) {
                    num6++;
                }
            }
            ;
            for (int j = 0; j < table41.size(); j++) {
                if (cityArray[i] == table41.get(j).getField_1()) {
                    num7++;
                }
            }
            ;
            for (int j = 0; j < table42.size(); j++) {
                if (cityArray[i] == table42.get(j).getField_1()) {
                    num8++;
                }
            }
            ;
            for (int j = 0; j < table43.size(); j++) {
                if (cityArray[i] == table43.get(j).getField_1()) {
                    num9++;
                }
            }
            ;
            for (int j = 0; j < table44.size(); j++) {
                if (cityArray[i] == table44.get(j).getField_1()) {
                    num10++;
                }
            }
            ;
            for (int j = 0; j < table51.size(); j++) {
                if (cityArray[i] == table51.get(j).getField_1()) {
                    num11++;
                }
            }
            ;
            for (int j = 0; j < table61.size(); j++) {
                if (cityArray[i] == table61.get(j).getField_1()) {
                    num12++;
                }
            }
            ;
            for (int j = 0; j < table62.size(); j++) {
                if (cityArray[i] == table62.get(j).getField_1()) {
                    num13++;
                }
            }
            ;
            for (int j = 0; j < table63.size(); j++) {
                if (cityArray[i] == table63.get(j).getField_1()) {
                    num14++;
                }
            }
            ;
            for (int j = 0; j < table64.size(); j++) {
                if (cityArray[i] == table64.get(j).getField_1()) {
                    num15++;
                }
            }
            ;
            for (int j = 0; j < table65.size(); j++) {
                if (cityArray[i] == table65.get(j).getField_1()) {
                    num16++;
                }
            }
            ;
            for (int j = 0; j < table71.size(); j++) {
                if (cityArray[i] == table71.get(j).getField_1()) {
                    num17++;
                }
            }
            ;
            for (int j = 0; j < table72.size(); j++) {
                if (cityArray[i] == table72.get(j).getField_1()) {
                    num18++;
                }
            }
            ;
            for (int j = 0; j < table73.size(); j++) {
                if (cityArray[i] == table73.get(j).getField_1()) {
                    num19++;
                }
            }
            ;
            for (int j = 0; j < table81.size(); j++) {
                if (cityArray[i] == table81.get(j).getField_1()) {
                    num20++;
                }
            }
            ;
            for (int j = 0; j < table91.size(); j++) {
                if (cityArray[i] == table91.get(j).getField_1()) {
                    num21++;
                }
            }
            ;
            for (int j = 0; j < table92.size(); j++) {
                if (cityArray[i] == table92.get(j).getField_1_1()) {
                    num22++;
                }
            }
            ;
            for (int j = 0; j < table93.size(); j++) {
                if (cityArray[i] == table93.get(j).getField_1()) {
                    num23++;
                }
            }
            ;
            for (int j = 0; j < table94.size(); j++) {
                if (cityArray[i] == table94.get(j).getField_1()) {
                    num24++;
                }
            }
            ;
            for (int j = 0; j < table95.size(); j++) {
                if (cityArray[i] == table95.get(j).getField_1()) {
                    num25++;
                }
            }
            ;
            for (int j = 0; j < table96.size(); j++) {
                if (cityArray[i] == table96.get(j).getField_1()) {
                    num26++;
                }
            }
            ;
            for (int j = 0; j < table97.size(); j++) {
                if (cityArray[i] == table97.get(j).getField_1()) {
                    num27++;
                }
            }
            ;
            for (int j = 0; j < table101.size(); j++) {
                if (cityArray[i] == table101.get(j).getField_1()) {
                    num28++;
                }
            };
            int total = (num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + num12 + num13 + num14 + num15 + num16 + num17 + num18 + num19 + num20 + num21 + num22 + num23 + num24 + num25 + num26 + num27 + num28);
            cityNumber[i] = String.valueOf(total);
        };
        //System.out.println(cityNumber.length);
        //System.out.println(cityArray.length);
//        for(int i=0;i<cityArray.length;i++){
//            //System.out.println(cityArray[i]+"为"+cityNumber[i]);
//        };
        String cityData ="";
        for(int i=0; i<cityArray.length;i++){
            cityData =cityData + "{\"cityName2\":\""+cityArray[i]+"\",\"cityNumber2\":\""+cityNumber[i]+"\"}"+"," ;
        };
//        for(int i=(cityArray.length/2);i<cityArray.length;i++){
//            cityData =cityData + "{\"cityName\":\""+cityArray[i]+"\",\"cityNumber\":\""+cityNumber[i]+"\"}"+"," ;
//        }
        String Data ="[" + cityData.substring(0,cityData.length()-1) + "]";
        //System.out.println(Data);


     return Data;
    }

    //一季稻监测点审核
    @GetMapping("/getPointData")
    public List<crop_01_01_table_02_01> getPointData(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_02_01> tableList21 = tableService.getPointData(year);
        ////System.out.println(tableList);
        return tableList21;
    }
    //获取一季稻通过监测点数
    @PostMapping("/getRiceTable21")
    public List<crop_01_01_table_02_01> getRiceTable21(@RequestParam("county") String county){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_02_01> table21 = tableService.getRiceTable21(year,county);
        //System.out.println(table21);
        return table21;
    };
    //一季稻监测点不通过
    @GetMapping("/noPassPoint")
    public String noPassPoint(@RequestParam("recordId") String recordId){
        int tableList21 = tableService.noPassPoint(recordId);
        ////System.out.println(tableList);
        return "success";
    }
    //一季稻监测点通过
    @PostMapping("/PassPoint")
    public String PassPoint(@RequestParam("recordId") String recordId,
                            @RequestParam("county") String county,
                            @RequestParam("pointCode") String pointCode){
        int  list21 = tableService.PassPoint(pointCode,recordId);
        //System.out.println(list21);
        return null;
    }
    //一季稻监测点信息插入到表2-2中
    @PostMapping("/insertInfoToRiceTable0202")
    public int insertInfoToRiceTable0202(@RequestParam("infoData")String infoData,
                                         @RequestParam("county") String county){
        //System.out.println(infoData);

        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        String areaType="";
        List<crop_01_01_table_02_02> table22 = tableService.getRiceTable22(county,areaType,year);
        crop_01_01_table_02_01 insertInfo = JSONObject.parseObject(infoData , crop_01_01_table_02_01.class);
        insertInfo.setIn_use("3");
        System.out.println(insertInfo);
        String pointCode = insertInfo.getField_1();
        int number = 0;
        for(int i=0;i<table22.size();i++){
            if(table22.get(i).getField_1().equals(pointCode)){
                number++;
                break;
            }
        }
        if(number == 0){
            int res = tableService.insertInfoToRiceTable0202(insertInfo);
        }else{
            int res = tableService.updateInfoToRiceTable0202(insertInfo);
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
//        //System.out.println(pointCode);
        return 1;
    };

    //一季稻表1-1
    @PostMapping("/getRiceTable11")
    public List<crop_01_01_table_01_01> getRiceTable11(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        //System.out.println(county);
        //System.out.println(areaType);
        List<crop_01_01_table_01_01> tableList11 = tableService.getRiceTable11(county,areaType,year);
        //System.out.println("tableList11:"+tableList11);
        return tableList11;
    };
    //一季稻表1-1搜索查询
    @PostMapping("/getSeekInfo11")
    public List<crop_01_01_table_01_01> getSeekInfo11(@RequestParam("county") String county,
                                                     @RequestParam("areaType") String areaType,
                                                     @RequestParam("riceType") String riceType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_01_01> Info11 = tableService.getSeekInfo11(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info11;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable11")
    public List<crop_01_01_table_01_01> getAuditRiceTable11(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_01_01> tableList11 = tableService.getAuditRiceTable11(year);
        ////System.out.println(tableList);
        return tableList11;
    };

    //一季稻表1-2
    @PostMapping("/getRiceTable12")
    public List<crop_01_01_table_01_02> getRiceTable12(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        List<crop_01_01_table_01_02> tableList12 = tableService.getRiceTable12(county,areaType,year);
        //System.out.println(tableList12);
        return tableList12;
    };
    //一季稻表分页1-2数据
    @PostMapping("/getPageRiceTable12")
    public pageBean<crop_01_01_table_01_02> getPageRiceTable12(@RequestParam("county") String county,
                                                               @RequestParam("areaType") String areaType,
                                                               @RequestParam("year") String year,
                                                               @RequestParam("start") int start,
                                                               @RequestParam("pageSize") int pageSize){

        pageBean<crop_01_01_table_01_02> tableList12 = tableService.getPageRiceTable12(county,areaType,year,start,pageSize);
        //System.out.println(tableList12);
        return tableList12;
    };
    //数据审核辅助
    @GetMapping("/getAuditRiceTable12")
    public List<crop_01_01_table_01_02> getAuditRiceTable12(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_01_02> tableList12 = tableService.getAuditRiceTable12(year);
        ////System.out.println(tableList);
        return tableList12;
    };
    //数据审核
    @PostMapping("/getAssist12")
    public List<crop_01_01_table_01_02> getAssist12(@RequestParam("auditCounty") String auditCounty){
        //今年数据要审核
        //System.out.println(auditCounty);
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_01_02> tableList12 = tableService.getAssist12(auditCounty,year);
        //System.out.println(tableList12);
        return tableList12;
    };
    //一季稻表1-2搜索查询
    @PostMapping("/getSeekInfo12")
    public List<crop_01_01_table_01_02> getSeekInfo12(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_01_02> Info12 = tableService.getSeekInfo12(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info12;
    };
    //一季稻表2-2数据
    @PostMapping("/getRiceTable22")
    public List<crop_01_01_table_02_02> getRiceTable22(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_02_02> tableList22 = tableService.getRiceTable22(county,areaType,year);
        //System.out.println("tableList22:"+tableList22);;
        return tableList22;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable22")
    public List<crop_01_01_table_02_02> getAuditRiceTable22(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_02_02> tableList22 = tableService.getAuditRiceTable22(year);
        ////System.out.println(tableList);
        return tableList22;
    };
    //一季稻表2-2搜索查询
    @PostMapping("/getSeekInfo22")
    public List<crop_01_01_table_02_02> getSeekInfo22(@RequestParam("county") String county,
                                                      @RequestParam("city") String city,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_02_02> Info22 = tableService.getSeekInfo22(county,city,year);
        ////System.out.println(Info11);
        return Info22;
    };
    //一季稻表3-1数据
    @PostMapping("/getRiceTable31")
    public List<crop_01_01_table_03_01> getRiceTable31(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_03_01> tableList31 = tableService.getRiceTable31(county,areaType,year);
        //System.out.println("tableList31:"+tableList31);
        return tableList31;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable31")
    public List<crop_01_01_table_03_01> getAuditRiceTable31(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_03_01> tableList31 = tableService.getAuditRiceTable31(year);
//        //System.out.println(tableList31);
        return tableList31;
    };
    //一季稻表3-1搜索查询
    @PostMapping("/getSeekInfo31")
    public List<crop_01_01_table_03_01> getSeekInfo31(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_03_01> Info31 = tableService.getSeekInfo31(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info31;
    };
    //一季稻表3-2数据
    @PostMapping("/getRiceTable32")
    public List<crop_01_01_table_03_02> getRiceTable32(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_03_02> tableList32 = tableService.getRiceTable32(county,areaType,year);
        //System.out.println("tableList32:"+tableList32);
        return tableList32;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable32")
    public List<crop_01_01_table_03_02> getAuditRiceTable32(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_03_02> tableList32 = tableService.getAuditRiceTable32(year);
        ////System.out.println(tableList);
        return tableList32;
    };
    //一季稻表3-2搜索查询
    @PostMapping("/getSeekInfo32")
    public List<crop_01_01_table_03_02> getSeekInfo32(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_03_02> Info32 = tableService.getSeekInfo32(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info32;
    };
    //一季稻表3-3数据
    @PostMapping("/getRiceTable33")
    public List<crop_01_01_table_03_03> getRiceTable33(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_03_03> tableList33 = tableService.getRiceTable33(county,areaType,year);
        //System.out.println("tableList33:"+tableList33);
        return tableList33;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable33")
    public List<crop_01_01_table_03_03> getAuditRiceTable33(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_03_03> tableList33 = tableService.getAuditRiceTable33(year);
        ////System.out.println(tableList);
        return tableList33;
    };
    //一季稻表3-3搜索查询
    @PostMapping("/getSeekInfo33")
    public List<crop_01_01_table_03_03> getSeekInfo33(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_03_03> Info33 = tableService.getSeekInfo33(county,areaType,riceType,year);
        ////System.out.println(Info11);
        return Info33;
    };
    //一季稻表4-1数据
    @PostMapping("/getRiceTable41")
    public List<crop_01_01_table_04_01> getRiceTable41(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_04_01> tableList41 = tableService.getRiceTable41(county,areaType,year);
        //System.out.println("tableList41:"+tableList41);
        return tableList41;
    };

    //增加了监测点一季稻信息查看部分
    //一季稻表4-1数据
    @PostMapping("/getRiceTable41_Message")
    public List<crop_01_01_table_04_01> getRiceTable41_Message(@RequestParam("area") String area,
                                                               @RequestParam("areaType") String areaType,
                                                               @RequestParam("year") String year){
        System.out.println("area"+area);
        System.out.println("year"+year);
        List<crop_01_01_table_04_01> tableList41 = tableService.getRiceTable41_Message(area,areaType,year);
        System.out.println("tableList41:"+tableList41);
        return tableList41;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable41")
    public List<crop_01_01_table_04_01> getAuditRiceTable41(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_04_01> tableList41 = tableService.getAuditRiceTable41(year);
        ////System.out.println(tableList);
        return tableList41;
    };
    //一季稻表4-1搜索查询
    @PostMapping("/getSeekInfo41")
    public List<crop_01_01_table_04_01> getSeekInfo41(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_04_01> Info41 = tableService.getSeekInfo41(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info41;
    };
    //一季稻表4-2数据
    @PostMapping("/getRiceTable42")
    public List<crop_01_01_table_04_02> getRiceTable42(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_04_02> tableList42 = tableService.getRiceTable42(county,areaType,year);
        //System.out.println("tableList42:"+tableList42);
        return tableList42;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable42")
    public List<crop_01_01_table_04_02> getAuditRiceTable42(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_04_02> tableList42 = tableService.getAuditRiceTable42(year);
        ////System.out.println(tableList);
        return tableList42;
    };
    //一季稻表4-2搜索查询
    @PostMapping("/getSeekInfo42")
    public List<crop_01_01_table_04_02> getSeekInfo42(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_04_02> Info42 = tableService.getSeekInfo42(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info42;
    };
    //一季稻表4-3数据
    @PostMapping("/getRiceTable43")
    public List<crop_01_01_table_04_03> getRiceTable43(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_04_03> tableList43 = tableService.getRiceTable43(county,areaType,year);
        //System.out.println("tableList43:"+tableList43);
        return tableList43;
    };
//    增加监测点信息查看部分
    //一季稻表4-3数据
    @PostMapping("/getRiceTable43_Message")
    public List<crop_01_01_table_04_03> getRiceTable43_Message(@RequestParam("area") String area,
                                                               @RequestParam("areaType")String areaType,
                                                       @RequestParam("year") String year){
        System.out.println("area:"+area);
        System.out.println("areaType:"+areaType);
        System.out.println("year:"+year);

        List<crop_01_01_table_04_03> tableList43 = tableService.getRiceTable43_Message(area,areaType,year);
        System.out.println("tableList43:"+tableList43);
        return tableList43;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable43")
    public List<crop_01_01_table_04_03> getAuditRiceTable43(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_04_03> tableList43 = tableService.getAuditRiceTable43(year);
        ////System.out.println(tableList);
        return tableList43;
    };
    //一季稻表4-3搜索查询
    @PostMapping("/getSeekInfo43")
    public List<crop_01_01_table_04_03> getSeekInfo43(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_04_03> Info43 = tableService.getSeekInfo43(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info43;
    };
    //一季稻表4-4数据
    @PostMapping("/getRiceTable44")
    public List<crop_01_01_table_04_04> getRiceTable44(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_04_04> tableList44 = tableService.getRiceTable44(county,areaType,year);
//        System.out.println("tableList44:"+tableList44);
        return tableList44;
    };
    //一季稻表4-4数据
    @PostMapping("/getRiceTable44_Message")
    public List<crop_01_01_table_04_04> getRiceTable44_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        System.out.println("area"+area);
        System.out.println("year"+year);

        List<crop_01_01_table_04_04> tableList44 = tableService.getRiceTable44_Message(area,areaType,year);
        System.out.println("tableList44:"+tableList44);
        return tableList44;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable44")
    public List<crop_01_01_table_04_04> getAuditRiceTable44(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_04_04> tableList44 = tableService.getAuditRiceTable44(year);
        ////System.out.println(tableList);
        return tableList44;
    };
    //一季稻表4-4搜索查询
    @PostMapping("/getSeekInfo44")
    public List<crop_01_01_table_04_04> getSeekInfo44(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_04_04> Info44 = tableService.getSeekInfo44(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info44;
    };
    //一季稻表5-1数据
    @PostMapping("/getRiceTable51")
    public List<crop_01_01_table_05_01> getRiceTable51(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_05_01> tableList51 = tableService.getRiceTable51(county,areaType,year);
        //System.out.println("tableList51:"+tableList51);
        return tableList51;
    };
    @PostMapping("/getRiceTable51_Message")
    public List<crop_01_01_table_05_01> getRiceTable51_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_05_01> tableList51 = tableService.getRiceTable51_Message(area,areaType,year);
        //System.out.println("tableList51:"+tableList51);
        return tableList51;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable51")
    public List<crop_01_01_table_05_01> getAuditRiceTable51(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_05_01> tableList51 = tableService.getAuditRiceTable51(year);
        ////System.out.println(tableList);
        return tableList51;
    };
    //一季稻表5-1搜索查询
    @PostMapping("/getSeekInfo51")
    public List<crop_01_01_table_05_01> getSeekInfo51(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_05_01> Info51 = tableService.getSeekInfo51(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info51;
    };
    //一季稻表6-1数据
    @PostMapping("/getRiceTable61")
    public List<crop_01_01_table_06_01> getRiceTable61(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_06_01> tableList61 = tableService.getRiceTable61(county,areaType,year);
        //System.out.println("tableList61:"+tableList61);
        return tableList61;
    };
    //一季稻表6-1数据
    @PostMapping("/getRiceTable61_Message")
    public List<crop_01_01_table_06_01> getRiceTable61_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_06_01> tableList61 = tableService.getRiceTable61_Message(area,areaType,year);
        //System.out.println("tableList61:"+tableList61);
        return tableList61;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable61")
    public List<crop_01_01_table_06_01> getAuditRiceTable61(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_06_01> tableList61 = tableService.getAuditRiceTable61(year);
        //System.out.println(tableList61);
        return tableList61;
    };
    //一季稻表6-1搜索查询
    @PostMapping("/getSeekInfo61")
    public List<crop_01_01_table_06_01> getSeekInfo61(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_06_01> Info61 = tableService.getSeekInfo61(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info61;
    };
    //一季稻表6-2数据
    @PostMapping("/getRiceTable62")
    public List<crop_01_01_table_06_02> getRiceTable62(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_06_02> tableList62 = tableService.getRiceTable62(county,areaType,year);
        //System.out.println("tableList62:"+tableList62);
        return tableList62;
    };
    @PostMapping("/getRiceTable62_Message")
    public List<crop_01_01_table_06_02> getRiceTable62_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_06_02> tableList62 = tableService.getRiceTable62_Message(area,areaType,year);
        //System.out.println("tableList62:"+tableList62);
        return tableList62;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable62")
    public List<crop_01_01_table_06_02> getAuditRiceTable62(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_06_02> tableList62 = tableService.getAuditRiceTable62(year);
        ////System.out.println(tableList);
        return tableList62;
    };
    //一季稻表6-2搜索查询
    @PostMapping("/getSeekInfo62")
    public List<crop_01_01_table_06_02> getSeekInfo62(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_06_02> Info62 = tableService.getSeekInfo62(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info62;
    };
    //一季稻表6-3数据
    @PostMapping("/getRiceTable63")
    public List<crop_01_01_table_06_03> getRiceTable63(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_06_03> tableList63 = tableService.getRiceTable63(county,areaType,year);
        //System.out.println("tableList63:"+tableList63);
        return tableList63;
    };
    @PostMapping("/getRiceTable63_Message")
    public List<crop_01_01_table_06_03> getRiceTable63_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_06_03> tableList63 = tableService.getRiceTable63_Message(area,areaType,year);
        //System.out.println("tableList63:"+tableList63);
        return tableList63;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable63")
    public List<crop_01_01_table_06_03> getAuditRiceTable63(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_06_03> tableList63 = tableService.getAuditRiceTable63(year);
        ////System.out.println(tableList);
        return tableList63;
    };
    //一季稻表6-3搜索查询
    @PostMapping("/getSeekInfo63")
    public List<crop_01_01_table_06_03> getSeekInfo63(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year) {
        //System.out.println("11111");
        List<crop_01_01_table_06_03> Info63 = tableService.getSeekInfo63(county, areaType, riceType, category, plantWay,year);
        ////System.out.println(Info11);
        return Info63;
    };
    //一季稻表6-4数据
    @PostMapping("/getRiceTable64")
    public List<crop_01_01_table_06_04> getRiceTable64(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_06_04> tableList64 = tableService.getRiceTable64(county,areaType,year);
        //System.out.println("tableList64:"+tableList64);
        return tableList64;
    };
    @PostMapping("/getRiceTable64_Message")
    public List<crop_01_01_table_06_04> getRiceTable64_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_06_04> tableList64 = tableService.getRiceTable64_Message(area,areaType,year);
        //System.out.println("tableList64:"+tableList64);
        return tableList64;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable64")
    public List<crop_01_01_table_06_04> getAuditRiceTable64(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_06_04> tableList64 = tableService.getAuditRiceTable64(year);
        ////System.out.println(tableList);
        return tableList64;
    };
    //一季稻表6-4搜索查询
    @PostMapping("/getSeekInfo64")
    public List<crop_01_01_table_06_04> getSeekInfo64(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_06_04> Info64 = tableService.getSeekInfo64(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info64;
    };
    //一季稻表6-5数据
    @PostMapping("/getRiceTable65")
    public List<crop_01_01_table_06_05> getRiceTable65(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_06_05> tableList65 = tableService.getRiceTable65(county,areaType,year);
        //System.out.println("tableList65:"+tableList65);
        return tableList65;
    };
    @PostMapping("/getRiceTable65_Message")
    public List<crop_01_01_table_06_05> getRiceTable65_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_06_05> tableList65 = tableService.getRiceTable65_Message(area,areaType,year);
        //System.out.println("tableList65:"+tableList65);
        return tableList65;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable65")
    public List<crop_01_01_table_06_05> getAuditRiceTable65(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_06_05> tableList65 = tableService.getAuditRiceTable65(year);
        ////System.out.println(tableList);
        return tableList65;
    };
    //一季稻表6-5搜索查询
    @PostMapping("/getSeekInfo65")
    public List<crop_01_01_table_06_05> getSeekInfo65(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_06_05> Info65 = tableService.getSeekInfo65(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info65;
    };
    //一季稻表7-1数据
    @PostMapping("/getRiceTable71")
    public List<crop_01_01_table_07_01> getRiceTable71(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_07_01> tableList71 = tableService.getRiceTable71(county,areaType,year);
        //System.out.println("tableList71:"+tableList71);
        return tableList71;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable71")
    public List<crop_01_01_table_07_01> getAuditRiceTable71(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_07_01> tableList71 = tableService.getAuditRiceTable71(year);
        //System.out.println(tableList71);
        return tableList71;
    };
    //一季稻表7-1搜索查询
    @PostMapping("/getSeekInfo71")
    public List<crop_01_01_table_07_01> getSeekInfo71(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_07_01> Info71 = tableService.getSeekInfo71(county,areaType,year);
        ////System.out.println(Info11);
        return Info71;
    };
    //一季稻表7-2数据
    @PostMapping("/getRiceTable72")
    public List<crop_01_01_table_07_02> getRiceTable72(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_07_02> tableList72 = tableService.getRiceTable72(county,areaType,year);
        //System.out.println("tableList72:"+tableList72);
        return tableList72;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable72")
    public List<crop_01_01_table_07_02> getAuditRiceTable72(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_07_02> tableList72 = tableService.getAuditRiceTable72(year);
        ////System.out.println(tableList);
        return tableList72;
    };
    //一季稻表7-2搜索查询
    @PostMapping("/getSeekInfo72")
    public List<crop_01_01_table_07_02> getSeekInfo72(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_07_02> Info72 = tableService.getSeekInfo72(county,areaType,year);
        ////System.out.println(Info11);
        return Info72;
    };
    //一季稻表7-3数据
    @PostMapping("/getRiceTable73")
    public List<crop_01_01_table_07_03> getRiceTable73(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_07_03> tableList73 = tableService.getRiceTable73(county,areaType,year);
        //System.out.println("tableList73:"+tableList73);
        return tableList73;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable73")
    public List<crop_01_01_table_07_03> getAuditRiceTable73(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_07_03> tableList73 = tableService.getAuditRiceTable73(year);
        ////System.out.println(tableList);
        return tableList73;
    };
    //一季稻表7-3搜索查询
    @PostMapping("/getSeekInfo73")
    public List<crop_01_01_table_07_03> getSeekInfo73(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("year") String year) {
        //System.out.println("11111");
        List<crop_01_01_table_07_03> Info73 = tableService.getSeekInfo73(county, areaType,year);
        ////System.out.println(Info11);
        return Info73;
    };
    //一季稻表8-1数据
    @PostMapping("/getRiceTable81")
    public List<crop_01_01_table_08_01> getRiceTable81(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_08_01> tableList81 = tableService.getRiceTable81(county,areaType,year);
//        System.out.println("tableList81:"+tableList81);
        return tableList81;
    };
    @PostMapping("/getRiceTable81_Message")
    public List<crop_01_01_table_08_01> getRiceTable81_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_08_01> tableList81 = tableService.getRiceTable81_Message(area,areaType,year);
        System.out.println("tableList81:"+tableList81);
        return tableList81;
    };
    //表8-1第二版数据获取
    @GetMapping("/attainRiceTable81")
    public List<crop_01_01_table_08_01> attainRiceTable81(){
        String county = "";
        String areaType = "";
        String year = "2021";
        List<crop_01_01_table_08_01> attain81 = tableService.attainRiceTable81(county,areaType,year);
        return attain81;
    }

    //数据审核
    @GetMapping("/getAuditRiceTable81")
    public List<crop_01_01_table_08_01> getAuditRiceTable81(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();//获取对象
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_08_01> tableList81 = tableService.getAuditRiceTable81(year);
        //System.out.println(tableList81);
        return tableList81;
    };
    //一季稻表8-1搜索查询
    @PostMapping("/getSeekInfo81")
    public List<crop_01_01_table_08_01> getSeekInfo81(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_08_01> Info81 = tableService.getSeekInfo81(county,areaType,riceType,category,plantWay,year);
        ////System.out.println(Info11);
        return Info81;
    };
    //一季稻表9-1数据
    @PostMapping("/getRiceTable91")
    public List<crop_01_01_table_09_01> getRiceTable91(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_01> tableList91 = tableService.getRiceTable91(county,areaType,year);
        //System.out.println("tableList91:"+tableList91);
        return tableList91;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable91")
    public List<crop_01_01_table_09_01> getAuditRiceTable91(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_01> tableList91 = tableService.getAuditRiceTable91(year);
        //System.out.println(tableList91);
        return tableList91;
    };
    //一季稻表9-1搜索查询
    @PostMapping("/getSeekInfo91")
    public List<crop_01_01_table_09_01> getSeekInfo91(@RequestParam("county") String county,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_09_01> Info91 = tableService.getSeekInfo91(county,year);
        ////System.out.println(Info11);
        return Info91;
    };
    //一季稻表9-2数据
    @PostMapping("/getRiceTable92")
    public List<crop_01_01_table_09_02> getRiceTable92(@RequestParam("currentYear") String currentYear,
                                                       @RequestParam("previousYear") String previousYear,
                                                       @RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        List<crop_01_01_table_09_02> tableList92 = tableService.getRiceTable92(currentYear,previousYear,county,areaType,year);
        //System.out.println("tableList92:"+tableList92);
        return tableList92;
    };
    //获取当年县
    @PostMapping("/getCurrentCounty")
    public String[] getCurrentCounty(@RequestParam("county") String county,
                                     @RequestParam("year") String year){
        String[] tableList92 = tableService.getCurrentCounty(county,year);
        return tableList92;
    };
    //数据审核
    @PostMapping("/getAuditRiceTable92")
    public List<crop_01_01_table_09_02> getAuditRiceTable92(@RequestParam("currentYear") String currentYear){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_02> tableList92 = tableService.getAuditRiceTable92(currentYear,year);
        ////System.out.println(tableList92);
        return tableList92;
    };
    //一季稻表9-2搜索查询
    @PostMapping("/getSeekInfo92")
    public List<crop_01_01_table_09_02> getSeekInfo92(@RequestParam("county") String county,
                                                      @RequestParam("currentYear") String currentYear,
                                                      @RequestParam("previousYear") String previousYear,
                                                      @RequestParam("year") String year){
       
        List<crop_01_01_table_09_02> Info92 = tableService.getSeekInfo92(county,currentYear,previousYear,year);
        ////System.out.println(Info11);
        return Info92;
    };
    //一季稻表9-3数据
    @PostMapping("/getRiceTable93")
    public List<crop_01_01_table_09_03> getRiceTable93(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_03> tableList93 = tableService.getRiceTable93(county,areaType,year);
        //System.out.println("tableList93:"+tableList93);
        return tableList93;
    };

    //一季稻表分页9-3数据
    @PostMapping("/getPageRiceTable93")
    public pageBean<crop_01_01_table_09_03> getPageRiceTable93(@RequestParam("county") String county,
                                                           @RequestParam("areaType") String areaType,
                                                           @RequestParam("year") String year,
                                                           @RequestParam("start") int start,
                                                           @RequestParam("pageSize") int pageSize){

        pageBean<crop_01_01_table_09_03> tableList93 = tableService.getPageRiceTable93(county,areaType,year,start,pageSize);
        //System.out.println(tableList93);
        return tableList93;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable93")
    public List<crop_01_01_table_09_03> getAuditRiceTable93(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_03> tableList93 = tableService.getAuditRiceTable93(year);
        ////System.out.println(tableList);
        return tableList93;
    };
    //一季稻表9-3搜索查询
    @PostMapping("/getSeekInfo93")
    public List<crop_01_01_table_09_03> getSeekInfo93(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("year") String year) {
        //System.out.println("11111");
        List<crop_01_01_table_09_03> Info93 = tableService.getSeekInfo93(county, areaType, riceType, category,year);
        ////System.out.println(Info11);
        return Info93;
    };
    //一季稻表9-4数据
    @PostMapping("/getRiceTable94")
    public List<crop_01_01_table_09_04> getRiceTable94(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_04> tableList94 = tableService.getRiceTable94(county,areaType,year);
        //System.out.println("tableList94:"+tableList94);
        return tableList94;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable94")
    public List<crop_01_01_table_09_04> getAuditRiceTable94(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_04> tableList94 = tableService.getAuditRiceTable94(year);
        ////System.out.println(tableList);
        return tableList94;
    };
    //一季稻表9-4搜索查询
    @PostMapping("/getSeekInfo94")
    public List<crop_01_01_table_09_04> getSeekInfo94(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_09_04> Info94 = tableService.getSeekInfo94(county,areaType,riceType,category,year);
        ////System.out.println(Info11);
        return Info94;
    };
    //一季稻表9-5数据
    @PostMapping("/getRiceTable95")
    public List<crop_01_01_table_09_05> getRiceTable95(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_05> tableList95 = tableService.getRiceTable95(county,areaType,year);
        //System.out.println("tableList95:"+tableList95);
        return tableList95;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable95")
    public List<crop_01_01_table_09_05> getAuditRiceTable95(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_05> tableList95 = tableService.getAuditRiceTable95(year);
        ////System.out.println(tableList);
        return tableList95;
    };
    //一季稻表9-5搜索查询
    @PostMapping("/getSeekInfo95")
    public List<crop_01_01_table_09_05> getSeekInfo95(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_09_05> Info95 = tableService.getSeekInfo95(county,areaType,riceType,category,year);
        ////System.out.println(Info11);
        return Info95;
    };
    //一季稻表9-6数据
    @PostMapping("/getRiceTable96")
    public List<crop_01_01_table_09_06> getRiceTable96(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_06> tableList96 = tableService.getRiceTable96(county,areaType,year);
        //System.out.println("tableList96:"+tableList96);
        return tableList96;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable96")
    public List<crop_01_01_table_09_06> getAuditRiceTable96(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_06> tableList96 = tableService.getAuditRiceTable96(year);
        ////System.out.println(tableList);
        return tableList96;
    };
    //一季稻表9-6搜索查询
    @PostMapping("/getSeekInfo96")
    public List<crop_01_01_table_09_06> getSeekInfo96(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("riceType") String riceType,
                                                      @RequestParam("category") String category,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_09_06> Info96 = tableService.getSeekInfo96(county,areaType,riceType,category,year);
        ////System.out.println(Info11);
        return Info96;
    };
    //一季稻表9-7数据
    @PostMapping("/getRiceTable97")
    public List<crop_01_01_table_09_07> getRiceTable97(@RequestParam("county") String county,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){
        
        List<crop_01_01_table_09_07> tableList97 = tableService.getRiceTable97(county,areaType,year);
        //System.out.println("tableList97:"+tableList97);
        return tableList97;
    };
    @PostMapping("/getRiceTable97_Message")
    public List<crop_01_01_table_09_07> getRiceTable97_Message(@RequestParam("area") String area,
                                                       @RequestParam("areaType") String areaType,
                                                       @RequestParam("year") String year){

        List<crop_01_01_table_09_07> tableList97 = tableService.getRiceTable97_Message(area,areaType,year);
        //System.out.println("tableList97:"+tableList97);
        return tableList97;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable97")
    public List<crop_01_01_table_09_07> getAuditRiceTable97(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_09_07> tableList97 = tableService.getAuditRiceTable97(year);
        ////System.out.println(tableList);
        return tableList97;
    };
    //一季稻表9-7搜索查询
    @PostMapping("/getSeekInfo97")
    public List<crop_01_01_table_09_07> getSeekInfo97(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("plantWay") String plantWay,
                                                      @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_09_07> Info97 = tableService.getSeekInfo97(county,areaType,plantWay,year);
        ////System.out.println(Info11);
        return Info97;
    };
    //一季稻表10-1数据
    @PostMapping("/getRiceTable101")
    public List<crop_01_01_table_10_01> getRiceTable101(@RequestParam("county") String county,
                                                        @RequestParam("areaType") String areaType,
                                                        @RequestParam("year") String year){
        
        List<crop_01_01_table_10_01> tableList101 = tableService.getRiceTable101(county,areaType,year);
        //System.out.println("tableList101:"+tableList101);
        return tableList101;
    };
    @PostMapping("/getRiceTable101_Message")
    public List<crop_01_01_table_10_01> getRiceTable101_Message(@RequestParam("area") String area,
                                                        @RequestParam("areaType") String areaType,
                                                        @RequestParam("year") String year){

        List<crop_01_01_table_10_01> tableList101 = tableService.getRiceTable101_Message(area,areaType,year);
        //System.out.println("tableList101:"+tableList101);
        return tableList101;
    };
    //数据审核
    @GetMapping("/getAuditRiceTable101")
    public List<crop_01_01_table_10_01> getAuditRiceTable101(){
        //今年数据要审核
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_10_01> tableList101 = tableService.getAuditRiceTable101(year);
        ////System.out.println(tableList);
        return tableList101;
    };
    //一季稻表10-1搜索查询
    @PostMapping("/getSeekInfo101")
    public List<crop_01_01_table_10_01> getSeekInfo101(@RequestParam("county") String county,
                                                      @RequestParam("areaType") String areaType,
                                                      @RequestParam("plantWay") String plantWay,
                                                       @RequestParam("year") String year){
        //System.out.println("11111");
        List<crop_01_01_table_10_01> Info101 = tableService.getSeekInfo101(county,areaType,plantWay,year);
        ////System.out.println(Info11);
        return Info101;
    };
    //所有县未审核数据
    @GetMapping("/getAllAuditData")
    public  String getAllAuditData(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //一季稻
        List<crop_01_01_table_01_01> one11 = tableService.getAuditRiceTable11(year);
        List<crop_01_01_table_01_02> one12 = tableService.getAuditRiceTable12(year);
        List<crop_01_01_table_02_02> one22 = tableService.getAuditRiceTable22(year);
        List<crop_01_01_table_03_01> one31 = tableService.getAuditRiceTable31(year);
        List<crop_01_01_table_03_02> one32 = tableService.getAuditRiceTable32(year);
        List<crop_01_01_table_03_03> one33 = tableService.getAuditRiceTable33(year);
        List<crop_01_01_table_04_01> one41 = tableService.getAuditRiceTable41(year);
        List<crop_01_01_table_04_02> one42 = tableService.getAuditRiceTable42(year);
        List<crop_01_01_table_04_03> one43 = tableService.getAuditRiceTable43(year);
        List<crop_01_01_table_04_04> one44 = tableService.getAuditRiceTable44(year);
        List<crop_01_01_table_05_01> one51 = tableService.getAuditRiceTable51(year);
        List<crop_01_01_table_06_01> one61 = tableService.getAuditRiceTable61(year);
        List<crop_01_01_table_06_02> one62 = tableService.getAuditRiceTable62(year);
        List<crop_01_01_table_06_03> one63 = tableService.getAuditRiceTable63(year);
        List<crop_01_01_table_06_04> one64 = tableService.getAuditRiceTable64(year);
        List<crop_01_01_table_06_05> one65 = tableService.getAuditRiceTable65(year);
        List<crop_01_01_table_07_01> one71 = tableService.getAuditRiceTable71(year);
        List<crop_01_01_table_07_02> one72 = tableService.getAuditRiceTable72(year);
        List<crop_01_01_table_07_03> one73 = tableService.getAuditRiceTable73(year);
        List<crop_01_01_table_08_01> one81 = tableService.getAuditRiceTable81(year);
        List<crop_01_01_table_09_01> one91 = tableService.getAuditRiceTable91(year);
        List<crop_01_01_table_09_02> one92 = tableService.getAuditRiceTable92(currentYear,year);
        List<crop_01_01_table_09_03> one93 = tableService.getAuditRiceTable93(year);
        List<crop_01_01_table_09_04> one94 = tableService.getAuditRiceTable94(year);
        List<crop_01_01_table_09_05> one95 = tableService.getAuditRiceTable95(year);
        List<crop_01_01_table_09_06> one96 = tableService.getAuditRiceTable96(year);
        List<crop_01_01_table_09_07> one97 = tableService.getAuditRiceTable97(year);
        List<crop_01_01_table_10_01> one101 = tableService.getAuditRiceTable101(year);
        int num1=one11.size();int num2=one12.size();int num3=one22.size();int num4=one31.size();int num5=one32.size();int num6=one33.size();int num7=one41.size();int num8=one42.size();int num9=one43.size();int num10=one44.size();
        int num11=one51.size();int num12=one61.size();int num13=one62.size();int num14=one63.size();int num15=one64.size();int num16=one65.size();int num17=one71.size();int num18=one72.size();int num19=one73.size();int num20=one81.size();
        int num21=one91.size();int num22=one92.size();int num23=one93.size();int num24=one94.size();int num25=one95.size();int num26=one96.size();int num27=one97.size();int num28=one101.size();
        int num[]= new int[62];
        num[0]=num1;num[1]=num[0]+num2;num[2]=num[1]+num3;num[3]=num[2]+num4;num[4]=num[3]+num5;num[5]=num[4]+num6;num[6]=num[5]+num7;num[7]=num[6]+num8;num[8]=num[7]+num9;num[9]=num[8]+num10;
        num[10]=num[9]+num11;num[11]=num[10]+num12;num[12]=num[11]+num13;num[13]=num[12]+num14;num[14]=num[13]+num15;num[15]=num[14]+num16;num[16]=num[15]+num17;num[17]=num[16]+num18;num[18]=num[17]+num19;num[19]=num[18]+num20;
        num[20]=num[19]+num21;num[21]=num[20]+num22;num[22]=num[21]+num23;num[23]=num[22]+num24;num[24]=num[23]+num25;num[25]=num[24]+num26;num[26]=num[25]+num27;num[27]=num[26]+num28;
        int total=num[27];
        String county[]=new String[total];
        String tableName[]=new String[total];
        String chineseName[]=new String[total];
        String record[]=new String[total];
        String time[]=new String[total];
        String people[]=new String[total];
        for(int i=0;i<num1;++i){
            county[i]=one11.get(i).getField_1();
            tableName[i]="crop_01_01_table_01_01";
            chineseName[i]="年度监测县(市、区)水稻栽插概况调查表";
            record[i]= String.valueOf(one11.get(i).getId());
            time[i]=one11.get(i).getModification_time();
            people[i]=one11.get(i).getPeople();
        };
        for(int i=0;i<num2;++i){
            county[i+num[0]]=one12.get(i).getCounty();
            tableName[i+num[0]]="crop_01_01_table_01_02";
            chineseName[i+num[0]]="年度监测县(市、区)水稻生产主推品种调查表";
            record[i+num[0]]= String.valueOf(one12.get(i).getId());
            time[i+num[0]]=one12.get(i).getModification_time();
            people[i+num[0]]=one12.get(i).getPeople();
        };
        for(int i=0;i<num3;++i){
            county[i+num[1]]=one22.get(i).getField_2_2();
            tableName[i+num[1]]="crop_01_01_table_02_02";
            chineseName[i+num[1]]="年度水稻监测点汇总表";
            record[i+num[1]]= String.valueOf(one22.get(i).getId());
            time[i+num[1]]= String.valueOf(one22.get(i).getModification_time());
            people[i+num[1]]= one22.get(i).getPeople();
        };
        for(int i=0;i<num4;++i){
            county[i+num[2]]=one31.get(i).getField_1();
            tableName[i+num[2]]="crop_01_01_table_03_01";
            chineseName[i+num[2]]="年度监测县(市、区)水稻播栽进度调查表";
            record[i+num[2]]= String.valueOf(one31.get(i).getId());
            time[i+num[2]]= String.valueOf(one31.get(i).getModification_time());
            people[i+num[2]]=one31.get(i).getPeople();
        };
        for(int i=0;i<num5;++i){
            county[i+num[3]]=one32.get(i).getField_1();
            tableName[i+num[3]]="crop_01_01_table_03_02";
            chineseName[i+num[3]]="年度监测县(市、区)水稻苗情评价动态列表（移栽稻）";
            record[i+num[3]]= String.valueOf(one32.get(i).getId());
            time[i+num[3]]= String.valueOf(one32.get(i).getModification_time());
            people[i+num[3]]=one32.get(i).getPeople();
        };
        for(int i=0;i<num6;i++){
            county[i+num[4]]=one33.get(i).getField_1();
            tableName[i+num[4]]="crop_01_01_table_03_03";
            chineseName[i+num[4]]="年度监测县(市、区)水稻苗情评价动态列表（直播稻）";
            record[i+num[4]]= String.valueOf(one33.get(i).getId());
            time[i+num[4]]= String.valueOf(one33.get(i).getModification_time());
            people[i+num[4]]=one33.get(i).getPeople();
        };
        for(int i=0;i<num7;++i){
            county[i+num[5]]=one41.get(i).getField_1();
            tableName[i+num[5]]="crop_01_01_table_04_01";
            chineseName[i+num[5]]="年度监测县(市、区)中稻生育进度调查表（移栽稻）";
            record[i+num[5]]= String.valueOf(one41.get(i).getId());
            time[i+num[5]]= String.valueOf(one41.get(i).getModification_time());
            people[i+num[5]]=one41.get(i).getPeople();
        };
        for(int i=0;i<num8;++i){
            county[i+num[6]]=one42.get(i).getField_1();
            tableName[i+num[6]]="crop_01_01_table_04_02";
            chineseName[i+num[6]]="年度监测县(市、区)直播稻生育进度调查表（直播稻）";
            record[i+num[6]]= String.valueOf(one42.get(i).getId());
            time[i+num[6]]= String.valueOf(one42.get(i).getModification_time());
            people[i+num[6]]=one42.get(i).getPeople();
        };
        for(int i=0;i<num9;i++){
            county[i+num[7]]=one43.get(i).getField_1();
            tableName[i+num[7]]="crop_01_01_table_04_03";
            chineseName[i+num[7]]="年度水稻监测点茎蘖数叶面积构成表";
            record[i+num[7]]= String.valueOf(one43.get(i).getId());
            time[i+num[7]]= String.valueOf(one43.get(i).getModification_time());
            people[i+num[7]]=one43.get(i).getPeople();
        };
        for(int i=0;i<num10;i++){
            county[i+num[8]]=one44.get(i).getField_1();
            tableName[i+num[8]]="crop_01_01_table_04_04";
            chineseName[i+num[8]]="年度水稻监测点苗情生育期统计表";
            record[i+num[8]]= String.valueOf(one44.get(i).getId());
            time[i+num[8]]= String.valueOf(one44.get(i).getModification_time());
            people[i+num[8]]=one44.get(i).getPeople();
        };
        for(int i=0;i<num11;++i){
            county[i+num[9]]=one51.get(i).getField_1();
            tableName[i+num[9]]="crop_01_01_table_05_01";
            chineseName[i+num[9]]="叶片含氮测定仪导出数据表";
            record[i+num[9]]= String.valueOf(one51.get(i).getId());
            time[i+num[9]]= String.valueOf(one51.get(i).getModification_time());
            people[i+num[9]]=one51.get(i).getPeople();
        };
        for(int i=0;i<num12;++i){
            county[i+num[10]]=one61.get(i).getField_1();
            tableName[i+num[10]]="crop_01_01_table_06_01";
            chineseName[i+num[10]]="年度水稻监测点水分管理记录表";
            record[i+num[10]]= String.valueOf(one61.get(i).getId());
            time[i+num[10]]= String.valueOf(one61.get(i).getModification_time());
            people[i+num[10]]=one61.get(i).getPeople();
        };
        for(int i=0;i<num13;++i){
            county[i+num[11]]=one62.get(i).getField_1();
            tableName[i+num[11]]="crop_01_01_table_06_02";
            chineseName[i+num[11]]="年度水稻监测点化除管理记录表";
            record[i+num[11]]= String.valueOf(one62.get(i).getId());
            time[i+num[11]]= String.valueOf(one62.get(i).getModification_time());
            people[i+num[11]]=one62.get(i).getPeople();
        };
        for(int i=0;i<num14;i++){
            county[i+num[12]]=one63.get(i).getField_1();
            tableName[i+num[12]]="crop_01_01_table_06_03";
            chineseName[i+num[12]]="年度水稻监测点病虫害防治记录表";
            record[i+num[12]]= String.valueOf(one63.get(i).getId());
            time[i+num[12]]= String.valueOf(one63.get(i).getModification_time());
            people[i+num[12]]=one63.get(i).getPeople();
        };
        for(int i=0;i<num15;i++){
            county[i+num[13]]=one64.get(i).getField_1();
            tableName[i+num[13]]="crop_01_01_table_06_04";
            chineseName[i+num[13]]="年度水稻监测点施肥管理记录表";
            record[i+num[13]]= String.valueOf(one64.get(i).getId());
            time[i+num[13]]= String.valueOf(one64.get(i).getModification_time());
            people[i+num[13]]=one64.get(i).getPeople();
        };
        for(int i=0;i<num16;i++){
            county[i+num[14]]=one65.get(i).getField_1();
            tableName[i+num[14]]="crop_01_01_table_06_05";
            chineseName[i+num[14]]="年度水稻监测点化学调节剂使用记录表";
            record[i+num[14]]= String.valueOf(one65.get(i).getId());
            time[i+num[14]]= String.valueOf(one65.get(i).getModification_time());
            people[i+num[14]]=one65.get(i).getPeople();
        };
        for(int i=0;i<num17;++i){
            county[i+num[15]]=one71.get(i).getField_1();
            tableName[i+num[15]]="crop_01_01_table_07_01";
            chineseName[i+num[15]]="年度监测县(市、区)水稻单季稻苗情监测预测产表";
            record[i+num[15]]= String.valueOf(one71.get(i).getId());
            time[i+num[15]]= String.valueOf(one71.get(i).getModification_time());
            people[i+num[15]]=one71.get(i).getPeople();
        };
        for(int i=0;i<num18;++i){
            county[i+num[16]]=one72.get(i).getField_1();
            tableName[i+num[16]]="crop_01_01_table_07_02";
            chineseName[i+num[16]]="年度监测县(市、区)水稻单季稻收获进度调查表";
            record[i+num[16]]= String.valueOf(one72.get(i).getId());
            time[i+num[16]]= String.valueOf(one72.get(i).getModification_time());
            people[i+num[16]]=one72.get(i).getPeople();
        };
        for(int i=0;i<num19;i++){
            county[i+num[17]]=one73.get(i).getField_1();
            tableName[i+num[17]]="crop_01_01_table_07_03";
            chineseName[i+num[17]]="年度监测县(市、区)水稻单季稻测产表";
            record[i+num[17]]= String.valueOf(one73.get(i).getId());
            time[i+num[17]]= String.valueOf(one73.get(i).getModification_time());
            people[i+num[17]]=one73.get(i).getPeople();
        };
        for(int i=0;i<num20;++i){
            county[i+num[18]]=one81.get(i).getField_1();
            tableName[i+num[18]]="crop_01_01_table_08_01";
            chineseName[i+num[18]]="年度监测县(市、区)水稻生产成本效益统计表";
            record[i+num[18]]= String.valueOf(one81.get(i).getId());
            time[i+num[18]]= String.valueOf(one81.get(i).getModification_time());
            people[i+num[18]]=one81.get(i).getPeople();
        };
        for(int i=0;i<num21;++i){
            county[i+num[19]]=one91.get(i).getField_1();
            tableName[i+num[19]]="crop_01_01_table_09_01";
            chineseName[i+num[19]]="年度监测县(市、区)气象资料列表";
            record[i+num[19]]= String.valueOf(one91.get(i).getId());
            time[i+num[19]]= String.valueOf(one91.get(i).getModification_time());
            people[i+num[19]]=one91.get(i).getPeople();
        };
        for(int i=0;i<num22;++i){
            county[i+num[20]]=one92.get(i).getField_1_1();
            tableName[i+num[20]]="crop_01_01_table_09_02";
            chineseName[i+num[20]]="年度监测县(市、区)抽穗期前、后各15天气象资料";
            record[i+num[20]]= String.valueOf(one92.get(i).getId());
            time[i+num[20]]= String.valueOf(one92.get(i).getModification_time());
            people[i+num[20]]=one92.get(i).getPeople();
        };
        for(int i=0;i<num23;i++){
            county[i+num[21]]=one93.get(i).getField_1();
            tableName[i+num[21]]="crop_01_01_table_09_03";
            chineseName[i+num[21]]="年度监测县(市、区)水稻倒伏灾害表";
            record[i+num[21]]= String.valueOf(one93.get(i).getId());
            time[i+num[21]]= String.valueOf(one93.get(i).getModification_time());
            people[i+num[21]]=one93.get(i).getPeople();
        };
        for(int i=0;i<num24;i++){
            county[i+num[22]]=one94.get(i).getField_1();
            tableName[i+num[22]]="crop_01_01_table_09_04";
            chineseName[i+num[22]]="年度监测县(市、区)水稻低温冷害表";
            record[i+num[22]]= String.valueOf(one94.get(i).getId());
            time[i+num[22]]= String.valueOf(one94.get(i).getModification_time());
            people[i+num[22]]=one94.get(i).getPeople();
        };
        for(int i=0;i<num25;i++){
            county[i+num[23]]=one95.get(i).getField_1();
            tableName[i+num[23]]="crop_01_01_table_09_05";
            chineseName[i+num[23]]="年度监测县(市、区)水稻高温热害表";
            record[i+num[23]]= String.valueOf(one95.get(i).getId());
            time[i+num[23]]= String.valueOf(one95.get(i).getModification_time());
            people[i+num[23]]=one95.get(i).getPeople();
        };
        for(int i=0;i<num26;++i){
            county[i+num[24]]=one96.get(i).getField_1();
            tableName[i+num[24]]="crop_01_01_table_09_06";
            chineseName[i+num[24]]="年度监测县(市、区)水稻涝害调查表";
            record[i+num[24]]= String.valueOf(one96.get(i).getId());
            time[i+num[24]]= String.valueOf(one96.get(i).getModification_time());
            people[i+num[24]]=one96.get(i).getPeople();
        };
        for(int i=0;i<num27;++i){
            county[i+num[25]]=one97.get(i).getField_1();
            tableName[i+num[25]]="crop_01_01_table_09_07";
            chineseName[i+num[25]]="年度水稻监测点洪涝灾害表";
            record[i+num[25]]= String.valueOf(one97.get(i).getId());
            time[i+num[25]]= String.valueOf(one97.get(i).getModification_time());
            people[i+num[25]]=one97.get(i).getPeople();
        };
        for(int i=0;i<num28;++i){
            county[i+num[26]]=one101.get(i).getField_1();
            tableName[i+num[26]]="crop_01_01_table_10_01";
            chineseName[i+num[26]]="年度水稻监测点灌浆速率调查表";
            record[i+num[26]]= String.valueOf(one101.get(i).getId());
            time[i+num[26]]= String.valueOf(one101.get(i).getModification_time());
            people[i+num[26]]=one101.get(i).getPeople();
        };
//        for(int i=0;i<total;i++){
//            //System.out.println("县："+county[i]+",表名："+tableName[i]+",记录："+record[i]+",时间:"+time[i]+",审核人："+people[i]);
//        };
        for(int i=0;i<total;i++) {
            String city=county[i];
            String name=tableName[i];
            String chinese=chineseName[i];
            String recordId =record[i];
            int dataId = Integer.parseInt(recordId);
            String times= time[i];
            String auditPeople=people[i];
            int auditData = tableService.getAuditData(city,name,chinese,dataId,times,auditPeople);
        };
        int deleteData=tableService.deleteRepeatData();
        return null;
    };

    //获取所有未审核数据
    @GetMapping("/getNoAuditAllData")
    public List<table_audit> getNoAuditAllData(){
        List<table_audit> auditData=tableService.getNoAuditAllData();
        return auditData;
    }
    //获取未审核数据
    @PostMapping("/getNoAuditInfo")
    public pageBean<table_audit> getNoAuditInfo(@RequestParam("params1") String params1,
                                                   @RequestParam("start") int start,
                                                   @RequestParam("pageSize") int pageSize){

       pageBean<table_audit> auditData=tableService.getNoAuditInfo(params1,start,pageSize);
       return auditData;
    }


    //获取未审核表各表数据分页
    @PostMapping("/getRiceAudit1")
    public pageBean<? extends Object> getRiceAudit1(@RequestParam("params1") String params1,
                                                   @RequestParam("riceTableName") String riceTableName,
                                                   @RequestParam("start") int start,
                                                   @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<? extends Object> auditInfo = tableService.getRiceAudit1(params1,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //获取未审核表1-2各表数据分页
    @PostMapping("/getRiceTableAudit12")
    public ArrayList<HashMap<String,Object> > getRiceTableAudit12(@RequestParam("params1") String params1,
                                                          @RequestParam("start") int start,
                                                          @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        ArrayList<HashMap<String,Object> > auditInfo = tableService.getRiceTableAudit12(params1,start,pageSize,year);
        return auditInfo;
    };
    //搜索未审核表各表数据分页
    @PostMapping("/searchRiceAudit1")
    public pageBean<? extends Object> searchRiceAudit1(@RequestParam("params1") String params1,
                                                    @RequestParam("month") String month,
                                                    @RequestParam("riceTableName") String riceTableName,
                                                    @RequestParam("start") int start,
                                                    @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<? extends Object> auditInfo = tableService.searchRiceAudit1(params1,month,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //搜索未审核表1-2数据分页
    @PostMapping("/searchRiceAudit12")
    public ArrayList<HashMap<String,Object> > searchRiceAudit12(@RequestParam("params1") String params1,
                                                       @RequestParam("month") String month,
                                                       @RequestParam("riceTableName") String riceTableName,
                                                       @RequestParam("start") int start,
                                                       @RequestParam("pageSize") int pageSize){
        //System.out.println(params1+","+start+","+pageSize);
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        ArrayList<HashMap<String,Object> > auditInfo = tableService.searchRiceAudit12(params1,month,riceTableName,start,pageSize,year);
        return auditInfo;
    };
    //删除辅助审核表对应数据
    @PostMapping("/deleteRecord")
    public String deleteRecord(@RequestParam("rowId") int rowId,
                               @RequestParam("rowName") String rowName,
                               @RequestParam("rowTime") String rowTime){
        int deleteData=tableService.deleteRecord(rowId,rowName,rowTime);
        //System.out.println(deleteData);
        return "success";
    }
    //一季稻生育期监测点个数
    @GetMapping("/getPeriod")
    public List<HashMap> getPeriod(){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_04_04> info =tableService.getPeriod(year);
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
    //显示退还记录
    @PostMapping("/getBackInfo")
    public pageBean<table_record_back> getBackInfo( @RequestParam("start") int start,
                                                @RequestParam("pageSize") int pageSize){
        //获取本年度时间
        Calendar date =  Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<table_record_back> backInfo = tableService.getBackInfo(year,start,pageSize);
        //System.out.println(backInfo);
        return backInfo;
    }
    //退回记录查询
    @PostMapping("/getAllBackData")
    public pageBean<table_record_back> getAllBackData(@RequestParam("params1") String params1,
                                                      @RequestParam("params2") String params2,
                                                      @RequestParam("start") int start,
                                                      @RequestParam("pageSize") int pageSize){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        pageBean<table_record_back> backInfo = tableService.getAllBackData(params1,params2,year,start,pageSize);
        //System.out.println(backInfo);
        return backInfo;
    }
    //获取各表未上报县数据
    @GetMapping("/getNoSumbitData")
    public List<HashMap<String,Object>> getNoSumbitData(){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<HashMap<String,Object>> noSumbitInfo = tableService.getNoSumbitData(year);
        return noSumbitInfo;
    }

    //处理未上报县数据
    @GetMapping("/getNoReportData")
    public List<HashMap<String, Object>> getNoReportData(){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<crop_01_01_table_01_01> one11 = tableService.getRice11(year);
        List<crop_01_01_table_01_02> one12 = tableService.getRice12(year);
        List<crop_01_01_table_03_01> one31 = tableService.getRice31(year);
        List<crop_01_01_table_03_02> one32 = tableService.getRice32(year);
        List<crop_01_01_table_03_03> one33 = tableService.getRice33(year);
        List<crop_01_01_table_04_01> one41 = tableService.getRice41(year);
        List<crop_01_01_table_04_02> one42 = tableService.getRice42(year);
        List<crop_01_01_table_04_03> one43 = tableService.getRice43(year);
        List<crop_01_01_table_04_04> one44 = tableService.getRice44(year);
        List<crop_01_01_table_05_01> one51 = tableService.getRice51(year);
        List<crop_01_01_table_06_01> one61 = tableService.getRice61(year);
        List<crop_01_01_table_06_02> one62 = tableService.getRice62(year);
        List<crop_01_01_table_06_03> one63 = tableService.getRice63(year);
        List<crop_01_01_table_06_04> one64 = tableService.getRice64(year);
        List<crop_01_01_table_06_05> one65 = tableService.getRice65(year);
        List<crop_01_01_table_07_01> one71 = tableService.getRice71(year);
        List<crop_01_01_table_07_02> one72 = tableService.getRice72(year);
        List<crop_01_01_table_07_03> one73 = tableService.getRice73(year);
        List<crop_01_01_table_08_01> one81 = tableService.getRice81(year);
        List<crop_01_01_table_09_03> one93 = tableService.getRice93(year);
        List<crop_01_01_table_09_04> one94 = tableService.getRice94(year);
        List<crop_01_01_table_09_05> one95 = tableService.getRice95(year);
        List<crop_01_01_table_09_06> one96 = tableService.getRice96(year);
        List<crop_01_01_table_09_07> one97 = tableService.getRice97(year);
        List<crop_01_01_table_10_01> one101 = tableService.getRice101(year);
        List<sum_select_list> selectInfo = tableService.getCounty1();
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
        if(one11.size()==0){
            noCounty.add(0,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one11.size();i++){
                temp.add(i,one11.get(i).getField_1());
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
        if(one12.size()==0){
            noCounty.add(1,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one12.size();i++){
                temp.add(i,one12.get(i).getCounty());
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
        if(one31.size()==0){
            noCounty.add(2,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one31.size();i++){
                temp.add(i,one31.get(i).getField_1());
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
        if(one32.size()==0){
            noCounty.add(3,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one32.size();i++){
                temp.add(i,one32.get(i).getField_1());
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
        if(one33.size()==0){
            noCounty.add(4,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one33.size();i++){
                temp.add(i,one33.get(i).getField_1());
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
        if(one41.size()==0){
            noCounty.add(5,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one41.size();i++){
                temp.add(i,one41.get(i).getField_1());
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
            noCounty.add(5,temp);
            ////System.out.println(noCounty);
        };
        if(one42.size()==0){
            noCounty.add(6,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one42.size();i++){
                temp.add(i,one42.get(i).getField_1());
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
        if(one43.size()==0){
            noCounty.add(7,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one43.size();i++){
                temp.add(i,one43.get(i).getField_1());
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
        if(one44.size()==0){
            noCounty.add(8,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one44.size();i++){
                temp.add(i,one44.get(i).getField_1());
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
            noCounty.add(8,temp);
        };
        if(one51.size()==0){
            noCounty.add(9,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one51.size();i++){
                temp.add(i,one51.get(i).getField_1());
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
        if(one61.size()==0){
            noCounty.add(10,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one61.size();i++){
                temp.add(i,one61.get(i).getField_1());
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
        if(one62.size()==0){
            noCounty.add(11,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one62.size();i++){
                temp.add(i,one62.get(i).getField_1());
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
        if(one63.size()==0){
            noCounty.add(12,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one63.size();i++){
                temp.add(i,one63.get(i).getField_1());
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
        if(one64.size()==0){
            noCounty.add(13,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one64.size();i++){
                temp.add(i,one64.get(i).getField_1());
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
        if(one65.size()==0){
            noCounty.add(14,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one65.size();i++){
                temp.add(i,one65.get(i).getField_1());
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
        if(one71.size()==0){
            noCounty.add(15,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one71.size();i++){
                temp.add(i,one71.get(i).getField_1());
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
        if(one72.size()==0){
            noCounty.add(16,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one72.size();i++){
                temp.add(i,one72.get(i).getField_1());
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
        if(one73.size()==0){
            noCounty.add(17,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one73.size();i++){
                temp.add(i,one73.get(i).getField_1());
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
        if(one81.size()==0){
            noCounty.add(18,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one81.size();i++){
                temp.add(i,one81.get(i).getField_1());
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
        if(one93.size()==0){
            noCounty.add(19,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one93.size();i++){
                temp.add(i,one93.get(i).getField_1());
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
        if(one94.size()==0){
            noCounty.add(20,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one94.size();i++){
                temp.add(i,one94.get(i).getField_1());
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
        if(one95.size()==0){
            noCounty.add(21,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one95.size();i++){
                temp.add(i,one95.get(i).getField_1());
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
        if(one96.size()==0){
            noCounty.add(22,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one96.size();i++){
                temp.add(i,one96.get(i).getField_1());
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
        if(one97.size()==0){
            noCounty.add(23,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one97.size();i++){
                temp.add(i,one97.get(i).getField_1());
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
        if(one101.size()==0){
            noCounty.add(24,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one101.size();i++){
                temp.add(i,one101.get(i).getField_1());
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
        //存表名
        List<table_second_name> tableName = tableService.getTabName();
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
            if(countyNumber.get(i).equals("30")){
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
    //删除审核表
    @GetMapping("/deleteAudit")
    public String deleteAudit(){
        int auditInfo = tableService.deleteAudit();
        //System.out.println(tableList);
        return "suceess";
    }
    //获取各表中所有县未审核数据县名和个数
    @GetMapping("/getAllCountyNoAuditNumber")
    public List<String> getAllCountyNoAuditNumber(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //一季稻
        List<crop_01_01_table_01_01> one11 = tableService.getAuditRiceTable11(year);
        List<crop_01_01_table_01_02> one12 = tableService.getAuditRiceTable12(year);
        List<crop_01_01_table_03_01> one31 = tableService.getAuditRiceTable31(year);
        List<crop_01_01_table_03_02> one32 = tableService.getAuditRiceTable32(year);
        List<crop_01_01_table_03_03> one33 = tableService.getAuditRiceTable33(year);
        List<crop_01_01_table_04_01> one41 = tableService.getAuditRiceTable41(year);
        List<crop_01_01_table_04_02> one42 = tableService.getAuditRiceTable42(year);
        List<crop_01_01_table_04_03> one43 = tableService.getAuditRiceTable43(year);
        List<crop_01_01_table_04_04> one44 = tableService.getAuditRiceTable44(year);
        List<crop_01_01_table_05_01> one51 = tableService.getAuditRiceTable51(year);
        List<crop_01_01_table_06_01> one61 = tableService.getAuditRiceTable61(year);
        List<crop_01_01_table_06_02> one62 = tableService.getAuditRiceTable62(year);
        List<crop_01_01_table_06_03> one63 = tableService.getAuditRiceTable63(year);
        List<crop_01_01_table_06_04> one64 = tableService.getAuditRiceTable64(year);
        List<crop_01_01_table_06_05> one65 = tableService.getAuditRiceTable65(year);
        List<crop_01_01_table_07_01> one71 = tableService.getAuditRiceTable71(year);
        List<crop_01_01_table_07_02> one72 = tableService.getAuditRiceTable72(year);
        List<crop_01_01_table_07_03> one73 = tableService.getAuditRiceTable73(year);
        List<crop_01_01_table_08_01> one81 = tableService.getAuditRiceTable81(year);
        List<crop_01_01_table_09_03> one93 = tableService.getAuditRiceTable93(year);
        List<crop_01_01_table_09_04> one94 = tableService.getAuditRiceTable94(year);
        List<crop_01_01_table_09_05> one95 = tableService.getAuditRiceTable95(year);
        List<crop_01_01_table_09_06> one96 = tableService.getAuditRiceTable96(year);
        List<crop_01_01_table_09_07> one97 = tableService.getAuditRiceTable97(year);
        List<crop_01_01_table_10_01> one101 = tableService.getAuditRiceTable101(year);
        List<String> auditInfo = new ArrayList<String>();
        for(int i=0;i<one11.size();i++){
            if(one11.get(i).getIn_use().equals("1")){
                auditInfo.add(one11.get(i).getField_1());
            }
        };
        for(int i=0;i<one12.size();i++){
            if(one12.get(i).getIn_use().equals("1")){
                auditInfo.add(one12.get(i).getCounty());
            }
        };
        for(int i=0;i<one31.size();i++){
            if(one31.get(i).getIn_use().equals("1")){
                auditInfo.add(one31.get(i).getField_1());
            }
        };
        for(int i=0;i<one32.size();i++){
            if(one32.get(i).getIn_use().equals("1")){
                auditInfo.add(one32.get(i).getField_1());
            }
        };
        for(int i=0;i<one33.size();i++){
            if(one33.get(i).getIn_use().equals("1")){
                auditInfo.add(one33.get(i).getField_1());
            }
        };
        for(int i=0;i<one41.size();i++){
            if(one41.get(i).getIn_use().equals("1")){
                auditInfo.add(one41.get(i).getField_1());
            }
        };
        for(int i=0;i<one42.size();i++){
            if(one42.get(i).getIn_use().equals("1")){
                auditInfo.add(one42.get(i).getField_1());
            }
        };
        for(int i=0;i<one43.size();i++){
            if(one43.get(i).getIn_use().equals("1")){
                auditInfo.add(one43.get(i).getField_1());
            }
        };
        for(int i=0;i<one44.size();i++){
            if(one44.get(i).getIn_use().equals("1")){
                auditInfo.add(one44.get(i).getField_1());
            }
        };
        for(int i=0;i<one51.size();i++){
            if(one51.get(i).getIn_use().equals("1")){
                auditInfo.add(one51.get(i).getField_1());
            }
        };
        for(int i=0;i<one61.size();i++){
            if(one61.get(i).getIn_use().equals("1")){
                auditInfo.add(one61.get(i).getField_1());
            }
        };
        for(int i=0;i<one62.size();i++){
            if(one62.get(i).getIn_use().equals("1")){
                auditInfo.add(one62.get(i).getField_1());
            }
        };
        for(int i=0;i<one63.size();i++){
            if(one63.get(i).getIn_use().equals("1")){
                auditInfo.add(one63.get(i).getField_1());
            }
        };
        for(int i=0;i<one64.size();i++){
            if(one64.get(i).getIn_use().equals("1")){
                auditInfo.add(one64.get(i).getField_1());
            }
        };
        for(int i=0;i<one65.size();i++){
            if(one65.get(i).getIn_use().equals("1")){
                auditInfo.add(one65.get(i).getField_1());
            }
        };
        for(int i=0;i<one71.size();i++){
            if(one71.get(i).getIn_use().equals("1")){
                auditInfo.add(one71.get(i).getField_1());
            }
        };
        for(int i=0;i<one72.size();i++){
            if(one72.get(i).getIn_use().equals("1")){
                auditInfo.add(one72.get(i).getField_1());
            }
        };
        for(int i=0;i<one73.size();i++){
            if(one73.get(i).getIn_use().equals("1")){
                auditInfo.add(one73.get(i).getField_1());
            }
        };
        for(int i=0;i<one81.size();i++){
            if(one81.get(i).getIn_use().equals("1")){
                auditInfo.add(one81.get(i).getField_1());
            }
        };
        for(int i=0;i<one93.size();i++){
            if(one93.get(i).getIn_use().equals("1")){
                auditInfo.add(one93.get(i).getField_1());
            }
        };
        for(int i=0;i<one94.size();i++){
            if(one94.get(i).getIn_use().equals("1")){
                auditInfo.add(one94.get(i).getField_1());
            }
        };
        for(int i=0;i<one95.size();i++){
            if(one95.get(i).getIn_use().equals("1")){
                auditInfo.add(one95.get(i).getField_1());
            }
        };
        for(int i=0;i<one96.size();i++){
            if(one96.get(i).getIn_use().equals("1")){
                auditInfo.add(one96.get(i).getField_1());
            }
        };
        for(int i=0;i<one97.size();i++){
            if(one97.get(i).getIn_use().equals("1")){
                auditInfo.add(one97.get(i).getField_1());
            }
        };
        for(int i=0;i<one101.size();i++){
            if(one101.get(i).getIn_use().equals("1")){
                auditInfo.add(one101.get(i).getField_1());
            }
        };
        return auditInfo;
    }

    //获取各表中未审核数据个数
    @GetMapping("/getDataNumber")
    public List<Integer> getDataNumber(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String currentYear = String.valueOf(yearTime);
        String year = String.valueOf(yearTime);
        //一季稻
        List<crop_01_01_table_01_01> one11 = tableService.getAuditRiceTable11(year);
        List<crop_01_01_table_01_02> one12 = tableService.getAuditRiceTable12(year);
        List<crop_01_01_table_03_01> one31 = tableService.getAuditRiceTable31(year);
        List<crop_01_01_table_03_02> one32 = tableService.getAuditRiceTable32(year);
        List<crop_01_01_table_03_03> one33 = tableService.getAuditRiceTable33(year);
        List<crop_01_01_table_04_01> one41 = tableService.getAuditRiceTable41(year);
        List<crop_01_01_table_04_02> one42 = tableService.getAuditRiceTable42(year);
        List<crop_01_01_table_04_03> one43 = tableService.getAuditRiceTable43(year);
        List<crop_01_01_table_04_04> one44 = tableService.getAuditRiceTable44(year);
        List<crop_01_01_table_05_01> one51 = tableService.getAuditRiceTable51(year);
        List<crop_01_01_table_06_01> one61 = tableService.getAuditRiceTable61(year);
        List<crop_01_01_table_06_02> one62 = tableService.getAuditRiceTable62(year);
        List<crop_01_01_table_06_03> one63 = tableService.getAuditRiceTable63(year);
        List<crop_01_01_table_06_04> one64 = tableService.getAuditRiceTable64(year);
        List<crop_01_01_table_06_05> one65 = tableService.getAuditRiceTable65(year);
        List<crop_01_01_table_07_01> one71 = tableService.getAuditRiceTable71(year);
        List<crop_01_01_table_07_02> one72 = tableService.getAuditRiceTable72(year);
        List<crop_01_01_table_07_03> one73 = tableService.getAuditRiceTable73(year);
        List<crop_01_01_table_08_01> one81 = tableService.getAuditRiceTable81(year);
        List<crop_01_01_table_09_03> one93 = tableService.getAuditRiceTable93(year);
        List<crop_01_01_table_09_04> one94 = tableService.getAuditRiceTable94(year);
        List<crop_01_01_table_09_05> one95 = tableService.getAuditRiceTable95(year);
        List<crop_01_01_table_09_06> one96 = tableService.getAuditRiceTable96(year);
        List<crop_01_01_table_09_07> one97 = tableService.getAuditRiceTable97(year);
        List<crop_01_01_table_10_01> one101 = tableService.getAuditRiceTable101(year);
        List<Integer> dataNumber = new ArrayList<Integer>();
        dataNumber.add(one11.size());
        dataNumber.add(one12.size());
        dataNumber.add(one31.size());
        dataNumber.add(one32.size());
        dataNumber.add(one33.size());
        dataNumber.add(one41.size());
        dataNumber.add(one42.size());
        dataNumber.add(one43.size());
        dataNumber.add(one44.size());
        dataNumber.add(one51.size());
        dataNumber.add(one61.size());
        dataNumber.add(one62.size());
        dataNumber.add(one63.size());
        dataNumber.add(one64.size());
        dataNumber.add(one65.size());
        dataNumber.add(one71.size());
        dataNumber.add(one72.size());
        dataNumber.add(one73.size());
        dataNumber.add(one81.size());
        dataNumber.add(one93.size());
        dataNumber.add(one94.size());
        dataNumber.add(one95.size());
        dataNumber.add(one96.size());
        dataNumber.add(one97.size());
        dataNumber.add(one101.size());
        return dataNumber;
    }
    //一季稻监测点删除
    @PostMapping("/deletePoint")
    public int deletePoint(@RequestParam("pointInfo")String pointInfo){
        //获取本年度时间
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        //
        crop_01_01_table_02_02 deleteInfo = JSONObject.parseObject(pointInfo , crop_01_01_table_02_02.class);
        int info = tableService.deletePoint(deleteInfo, year);

        return 1;
    }

    //处理各个监测县上报与未上报数据
    @GetMapping("/getSubmitData")
    public List<HashMap<String, Object>>  getSubmitData(){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<HashMap<String ,Object>> submitInfo = tableService.getSubmitData(year);
        return submitInfo;
    }

    //获取各个监测县上报与未上报具体表信息
    @GetMapping("/getSubmitInfo")
    public List<List<HashMap<String,Object>>>  getSubmitInfo(@RequestParam("countyName") String countyName){
        Calendar date = Calendar.getInstance();
        int yearTime = date.get(Calendar.YEAR);
        String year = String.valueOf(yearTime);
        List<List<HashMap<String,Object>>> submitInfo = new ArrayList<List<HashMap<String,Object>>>();
        if(countyName.equals("宣州区")|| countyName.equals("庐江县") || countyName.equals("怀宁县") || countyName.equals("枞阳县") || countyName.equals("贵池区") || countyName.equals("南陵县")){
            submitInfo = tableService.getSubmitTwoInfo(countyName,year);
        }else{
            submitInfo = tableService.getSubmitInfo(countyName,year);
        }
        System.out.println(submitInfo);
        return submitInfo;

    }

}
