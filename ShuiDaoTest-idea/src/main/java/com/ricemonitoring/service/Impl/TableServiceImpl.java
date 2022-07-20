package com.ricemonitoring.service.Impl;


import com.ricemonitoring.mapper.TableMapper;
import com.ricemonitoring.mapper.TableTwoMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.TableService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    TableMapper tableMapper;

    @Autowired
    TableTwoMapper tableTwoMapper;

    @Override
    public List<sum_select_list> getCounty1() {

        return tableMapper.getCounty1();
    }


    @Override
    public List<sum_select_list> getCitys() {
        return tableMapper.getCitys();
    }

    @Override
    public String returnTable12(String recordCounty, String year) {
        return tableMapper.returnTable12(recordCounty,year);
    }
    @Override
    public String returnTableTwo12(String recordCounty, String year) {
        return tableMapper.returnTableTwo12(recordCounty,year);
    }

    @Override
    public String returnRecord(String returnTableName, int recordId) {
        return tableMapper.returnRecord(returnTableName,recordId);
    }

    @Override
    public String recordAdd(String returnTableName,String recordCounty, int recordId, String recordReason, String recordTime, String recordPeople) {
        return tableMapper.recordAdd(returnTableName,recordCounty,recordId,recordReason,recordTime,recordPeople);
    }

    @Override
    public String confirmTable12(String recordCounty, String year) {
        return tableMapper.confirmTable12(recordCounty,year);
    }
    @Override
    public String confirmTableTwo12(String recordCounty, String year) {
        return tableMapper.confirmTableTwo12(recordCounty,year);
    }

    @Override
    public String confirmPass(String returnTableName, int recordId) {
        return tableMapper.confirmPass(returnTableName,recordId);
    }
    //监测点审核
    @Override
    public List<crop_01_01_table_02_01> getPointData(String year) {
        return tableMapper.getPointData(year);
    }

    @Override
    public int noPassPoint(String recordId) {
        return tableMapper.noPassPoint(recordId);
    }
    @Override
    public int PassPoint(String pointCode,String recordId) {
        //System.out.println(recordId+"——" +pointCode);
        return tableMapper.PassPoint(pointCode,recordId);
    }
    @Override
    public List<crop_01_01_table_02_01> getRiceTable21(String year, String county) {
        return tableMapper.getRiceTable21(year,county);
    }

    @Override
    public int insertInfoToRiceTable0202(crop_01_01_table_02_01 insertInfo) {
        return tableMapper.insertInfoToRiceTable0202(insertInfo);
    }
    @Override
    public int updateInfoToRiceTable0202(crop_01_01_table_02_01 insertInfo) {
        return tableMapper.updateInfoToRiceTable0202(insertInfo);
    }


    @Override
    public List<crop_01_01_table_01_01> getRiceTable11(String county, String areaType,String year) {
        return tableMapper.getRiceTable11(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_01_01> getAuditRiceTable11(String year) {
        return tableMapper.getAuditRiceTable11(year);
    }
    @Override
    public List<crop_01_01_table_01_01> getSeekInfo11(String county, String areaType, String riceType,String year) {
        return tableMapper.getSeekInfo11(county,areaType,riceType,year);
    }

    //分页数据获取
    @Override
    public pageBean<crop_01_01_table_01_02> getPageRiceTable12(String county, String areaType, String year, int start, int pageSize) {
        HashMap<String,Object> map =new HashMap<String, Object>();
        pageBean<crop_01_01_table_01_02> pageBean = new pageBean<crop_01_01_table_01_02>();
        //封装当前页数
        pageBean.setCurrPage(start);
        //每页显示的数据
        pageBean.setPageSize(pageSize);
//        //封装总记录数
//        int totalCount = (getRiceTable93(county,areaType,year)).size();
//        pageBean.setTotalCount(totalCount);
//        //封装总页数
//        double tc = totalCount;
//        Double num = Math.ceil(tc/pageSize);
//        pageBean.setTotalPage(num.intValue());

        map.put("county",county);
        map.put("areaType",areaType);
        map.put("year",year);
        map.put("start",(start-1)*pageSize);
        map.put("size",pageBean.getPageSize());
        //封装每页显示的数据
        List<crop_01_01_table_01_02> lists = tableMapper.getPageRiceTable12(map);
        pageBean.setLists(lists);
        //System.out.println(pageBean);
        return pageBean;
    }
    @Override
    public List<crop_01_01_table_01_02> getRiceTable12(String county, String areaType,String year) {
        return tableMapper.getRiceTable12(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_01_02> getAuditRiceTable12(String year) {
        return tableMapper.getAuditRiceTable12(year);
    }
    @Override
    public List<crop_01_01_table_01_02> getAssist12(String auditCounty,String year) {
        return tableMapper.getAssist12(auditCounty,year);
    }
    @Override
    public List<crop_01_01_table_01_02> getSeekInfo12(String county, String areaType, String riceType,String year) {
        return tableMapper.getSeekInfo12(county,areaType,riceType,year);
    }

    @Override
    public List<crop_01_01_table_02_02> getRiceTable22(String county, String areaType,String year) {
        return tableMapper.getRiceTable22(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_02_02> getAuditRiceTable22(String year) {
        return tableMapper.getAuditRiceTable22(year);
    }
    @Override
    public List<crop_01_01_table_02_02> getSeekInfo22(String county, String city,String year) {
        return tableMapper.getSeekInfo22(county,city,year);
    }

    @Override
    public List<crop_01_01_table_03_01> getRiceTable31(String county, String areaType,String year) {
        return tableMapper.getRiceTable31(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_03_01> getAuditRiceTable31(String year) {
        return tableMapper.getAuditRiceTable31(year);
    }
    @Override
    public List<crop_01_01_table_03_01> getSeekInfo31(String county, String areaType, String riceType,String year) {
        return tableMapper.getSeekInfo31(county,areaType,riceType,year);
    }

    @Override
    public List<crop_01_01_table_03_02> getRiceTable32(String county, String areaType,String year) {
        return tableMapper.getRiceTable32(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_03_02> getAuditRiceTable32(String year) {
        return tableMapper.getAuditRiceTable32(year);
    }
    @Override
    public List<crop_01_01_table_03_02> getSeekInfo32(String county, String areaType, String riceType,String year) {
        return tableMapper.getSeekInfo32(county,areaType,riceType,year);
    }

    @Override
    public List<crop_01_01_table_03_03> getRiceTable33(String county, String areaType,String year) {
        return tableMapper.getRiceTable33(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_03_03> getAuditRiceTable33(String year) {
        return tableMapper.getAuditRiceTable33(year);
    }
    @Override
    public List<crop_01_01_table_03_03>getSeekInfo33(String county, String areaType, String riceType,String year) {
        return tableMapper.getSeekInfo33(county,areaType,riceType,year);
    }

    @Override
    public List<crop_01_01_table_04_01> getRiceTable41(String county, String areaType,String year) {
        return tableMapper.getRiceTable41(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_04_01> getAuditRiceTable41(String year) {
        return tableMapper.getAuditRiceTable41(year);
    }
    @Override
    public List<crop_01_01_table_04_01> getSeekInfo41(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo41(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_04_02> getRiceTable42(String county, String areaType,String year) {
        return tableMapper.getRiceTable42(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_04_02> getAuditRiceTable42(String year) {
        return tableMapper.getAuditRiceTable42(year);
    }
    @Override
    public List<crop_01_01_table_04_02> getSeekInfo42(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo42(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_04_03> getRiceTable43(String county, String areaType,String year) {
        return tableMapper.getRiceTable43(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_04_03> getAuditRiceTable43(String year) {
        return tableMapper.getAuditRiceTable43(year);
    }
    @Override
    public List<crop_01_01_table_04_03>getSeekInfo43(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo43(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_04_04> getRiceTable44(String county, String areaType,String year) {
        return tableMapper.getRiceTable44(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_04_04> getAuditRiceTable44(String year) {
        return tableMapper.getAuditRiceTable44(year);
    }
    @Override
    public List<crop_01_01_table_04_04>getSeekInfo44(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo44(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_05_01> getRiceTable51(String county, String areaType,String year) {
        return tableMapper.getRiceTable51(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_05_01> getAuditRiceTable51(String year) {
        return tableMapper.getAuditRiceTable51(year);
    }
    @Override
    public List<crop_01_01_table_05_01> getSeekInfo51(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo51(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_06_01> getRiceTable61(String county, String areaType,String year) {
        return tableMapper.getRiceTable61(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_06_01> getAuditRiceTable61(String year) {
        return tableMapper.getAuditRiceTable61(year);
    }
    @Override
    public List<crop_01_01_table_06_01> getSeekInfo61(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo61(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_06_02> getRiceTable62(String county, String areaType,String year) {
        return tableMapper.getRiceTable62(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_06_02> getAuditRiceTable62(String year) {
        return tableMapper.getAuditRiceTable62(year);
    }
    @Override
    public List<crop_01_01_table_06_02> getSeekInfo62(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo62(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_06_03> getRiceTable63(String county, String areaType,String year) {
        return tableMapper.getRiceTable63(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_06_03> getAuditRiceTable63(String year) {
        return tableMapper.getAuditRiceTable63(year);
    }
    @Override
    public List<crop_01_01_table_06_03>getSeekInfo63(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo63(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_06_04> getRiceTable64(String county, String areaType,String year) {
        return tableMapper.getRiceTable64(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_06_04> getAuditRiceTable64(String year) {
        return tableMapper.getAuditRiceTable64(year);
    }
    @Override
    public List<crop_01_01_table_06_04>getSeekInfo64(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo64(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_06_05> getRiceTable65(String county, String areaType,String year) {
        return tableMapper.getRiceTable65(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_06_05> getAuditRiceTable65(String year) {
        return tableMapper.getAuditRiceTable65(year);
    }
    @Override
    public List<crop_01_01_table_06_05>getSeekInfo65(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo65(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_07_01> getRiceTable71(String county, String areaType,String year) {
        return tableMapper.getRiceTable71(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_07_01> getAuditRiceTable71(String year) {
        return tableMapper.getAuditRiceTable71(year);
    }
    @Override
    public List<crop_01_01_table_07_01> getSeekInfo71(String county, String areaType,String year) {
        return tableMapper.getSeekInfo71(county,areaType,year);
    }

    @Override
    public List<crop_01_01_table_07_02> getRiceTable72(String county, String areaType,String year) {
        return tableMapper.getRiceTable72(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_07_02> getAuditRiceTable72(String year) {
        return tableMapper.getAuditRiceTable72(year);
    }
    @Override
    public List<crop_01_01_table_07_02> getSeekInfo72(String county, String areaType,String year) {
        return tableMapper.getSeekInfo72(county,areaType,year);
    }

    @Override
    public List<crop_01_01_table_07_03> getRiceTable73(String county, String areaType,String year) {
        return tableMapper.getRiceTable73(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_07_03> getAuditRiceTable73(String year) {
        return tableMapper.getAuditRiceTable73(year);
    }
    @Override
    public List<crop_01_01_table_07_03>getSeekInfo73(String county, String areaType,String year) {
        return tableMapper.getSeekInfo73(county,areaType,year);
    }

    //8-1第二版
    @Override
    public List<crop_01_01_table_08_01> attainRiceTable81(String county, String areaType, String year) {
        List<crop_01_01_table_08_01> attain81 = tableMapper.getRiceTable81(county, areaType, year);
        //System.out.println(attain81);
        List<sum_select_list> selectInfo = tableMapper.getCounty1();
        ArrayList<String> countyInfo = new ArrayList<String>();
        ArrayList<String> plantWay = new ArrayList<String>();
        List<crop_01_01_table_08_01> total81 = new ArrayList<crop_01_01_table_08_01>();
        //System.out.println(selectInfo);
        for (int i = 0; i < selectInfo.size(); i++) {
            if (selectInfo.get(i).getCaption().equals("县（市、区）")) {
                countyInfo.add(selectInfo.get(i).getList());
            }
            ;
            if (selectInfo.get(i).getCaption().equals("栽植方式")) {
                plantWay.add(selectInfo.get(i).getList());
            }
            ;
        }
        ;
        //System.out.println(countyInfo + ";" + plantWay);
        ;
        if (attain81.size() != 0) {
            //先做最后的合计数据
            for (int i = 0; i < plantWay.size(); i++) {
                BigDecimal num1 = new BigDecimal("0");
                BigDecimal num2 = new BigDecimal("0");
                BigDecimal num3 = new BigDecimal("0");
                BigDecimal num4 = new BigDecimal("0");
                BigDecimal num5 = new BigDecimal("0");
                BigDecimal num6 = new BigDecimal("0");
                BigDecimal num7 = new BigDecimal("0");
                BigDecimal num8 = new BigDecimal("0");
                BigDecimal num9 = new BigDecimal("0");
                BigDecimal num10 = new BigDecimal("0");
                BigDecimal num11 = new BigDecimal("0");
                BigDecimal num12 = new BigDecimal("0");
                BigDecimal num13 = new BigDecimal("0");
                BigDecimal num14 = new BigDecimal("0");
                BigDecimal num15 = new BigDecimal("0");
                BigDecimal num16 = new BigDecimal("0");
                BigDecimal num17 = new BigDecimal("0");
                BigDecimal num18 = new BigDecimal("0");
                BigDecimal num19 = new BigDecimal("0");
                BigDecimal num20 = new BigDecimal("0");
                BigDecimal num21 = new BigDecimal("0");
                BigDecimal num22 = new BigDecimal("0");
                BigDecimal num23 = new BigDecimal("0");
                BigDecimal num24 = new BigDecimal("0");
                BigDecimal num25 = new BigDecimal("0");
                BigDecimal num26 = new BigDecimal("0");
                BigDecimal num27 = new BigDecimal("0");
                BigDecimal num28 = new BigDecimal("0");
                BigDecimal num29 = new BigDecimal("0");
                BigDecimal num30 = new BigDecimal("0");
                BigDecimal num31 = new BigDecimal("0");
                BigDecimal num32 = new BigDecimal("0");
                int tableLength = 0;
                for (int j = 0; j < attain81.size(); j++) {
                    if (plantWay.get(i).equals(attain81.get(j).getField_7())) {
                        if (attain81.get(j).getField_6() == null || attain81.get(j).getField_6().length() == 0) {
                            attain81.get(j).setField_6("0");
                        }
                        ;
                        BigDecimal field_6 = new BigDecimal((attain81.get(j).getField_6()));
                        num1 = num1.add(field_6);
                        //
                        if (attain81.get(j).getField_8() == null || attain81.get(j).getField_8().length() == 0) {
                            attain81.get(j).setField_8("0");
                        }
                        ;
                        BigDecimal field_8 = new BigDecimal((attain81.get(j).getField_8()));
                        num2 = num2.add(field_8);
                        //
                        if (attain81.get(j).getField_9() == null || attain81.get(j).getField_9().length() == 0) {
                            attain81.get(j).setField_9("0");
                        }
                        ;
                        BigDecimal field_9 = new BigDecimal((attain81.get(j).getField_9()));
                        num3 = num3.add(field_9);
                        //
                        if (attain81.get(j).getField_10() == null || attain81.get(j).getField_10().length() == 0) {
                            attain81.get(j).setField_10("0");
                        }
                        ;
                        BigDecimal field_10 = new BigDecimal((attain81.get(j).getField_10()));
                        num4 = num4.add(field_10);
                        //
                        if (attain81.get(j).getField_11_1_1() == null || attain81.get(j).getField_11_1_1().length() == 0) {
                            attain81.get(j).setField_11_1_1("0");
                        }
                        ;
                        BigDecimal field_11_1_1 = new BigDecimal((attain81.get(j).getField_11_1_1()));
                        num5 = num5.add(field_11_1_1);
                        //
                        if (attain81.get(j).getField_11_1_2() == null || attain81.get(j).getField_11_1_2().length() == 0) {
                            attain81.get(j).setField_11_1_2("0");
                        }
                        ;
                        BigDecimal field_11_1_2 = new BigDecimal((attain81.get(j).getField_11_1_2()));
                        num6 = num6.add(field_11_1_2);
                        //
                        if (attain81.get(j).getField_11_2_1() == null || attain81.get(j).getField_11_2_1().length() == 0) {
                            attain81.get(j).setField_11_2_1("0");
                        }
                        ;
                        BigDecimal field_11_2_1 = new BigDecimal((attain81.get(j).getField_11_2_1()));
                        num7 = num7.add(field_11_2_1);
                        //
                        if (attain81.get(j).getField_11_2_2() == null || attain81.get(j).getField_11_2_2().length() == 0) {
                            attain81.get(j).setField_11_2_2("0");
                        }
                        ;
                        BigDecimal field_11_2_2 = new BigDecimal((attain81.get(j).getField_11_2_2()));
                        num8 = num8.add(field_11_2_2);
                        //
                        if (attain81.get(j).getField_11_3_1() == null || attain81.get(j).getField_11_3_1().length() == 0) {
                            attain81.get(j).setField_11_3_1("0");
                        }
                        ;
                        BigDecimal field_11_3_1 = new BigDecimal((attain81.get(j).getField_11_3_1()));
                        num9 = num9.add(field_11_3_1);
                        //
                        if (attain81.get(j).getField_11_3_2() == null || attain81.get(j).getField_11_3_2().length() == 0) {
                            attain81.get(j).setField_11_3_2("0");
                        }
                        ;
                        BigDecimal field_11_3_2 = new BigDecimal((attain81.get(j).getField_11_3_2()));
                        num10 = num10.add(field_11_3_2);
                        //
                        if (attain81.get(j).getField_11_4_1() == null || attain81.get(j).getField_11_4_1().length() == 0) {
                            attain81.get(j).setField_11_4_1("0");
                        }
                        ;
                        BigDecimal field_11_4_1 = new BigDecimal((attain81.get(j).getField_11_4_1()));
                        num11 = num11.add(field_11_4_1);
                        //
                        if (attain81.get(j).getField_11_4_2() == null || attain81.get(j).getField_11_4_2().length() == 0) {
                            attain81.get(j).setField_11_4_2("0");
                        }
                        ;
                        BigDecimal field_11_4_2 = new BigDecimal((attain81.get(j).getField_11_4_2()));
                        num12 = num12.add(field_11_4_2);
                        //
                        if (attain81.get(j).getField_11_5_1() == null || attain81.get(j).getField_11_5_1().length() == 0) {
                            attain81.get(j).setField_11_5_1("0");
                        }
                        ;
                        BigDecimal field_11_5_1 = new BigDecimal((attain81.get(j).getField_11_5_1()));
                        num13 = num13.add(field_11_5_1);
                        //
                        if (attain81.get(j).getField_11_5_2() == null || attain81.get(j).getField_11_5_2().length() == 0) {
                            attain81.get(j).setField_11_5_2("0");
                        }
                        ;
                        BigDecimal field_11_5_2 = new BigDecimal((attain81.get(j).getField_11_5_2()));
                        num14 = num14.add(field_11_5_2);
                        //
                        if (attain81.get(j).getField_11_6_1() == null || attain81.get(j).getField_11_6_1().length() == 0) {
                            attain81.get(j).setField_11_6_1("0");
                        }
                        ;
                        BigDecimal field_11_6_1 = new BigDecimal((attain81.get(j).getField_11_6_1()));
                        num15 = num15.add(field_11_6_1);
                        //
                        if (attain81.get(j).getField_11_6_2() == null || attain81.get(j).getField_11_6_2().length() == 0) {
                            attain81.get(j).setField_11_6_2("0");
                        }
                        ;
                        BigDecimal field_11_6_2 = new BigDecimal((attain81.get(j).getField_11_6_2()));
                        num16 = num16.add(field_11_6_2);
                        //
                        if (attain81.get(j).getField_12_1() == null || attain81.get(j).getField_12_1().length() == 0) {
                            attain81.get(j).setField_12_1("0");
                        }
                        ;
                        BigDecimal field_12_1 = new BigDecimal((attain81.get(j).getField_12_1()));
                        num17 = num17.add(field_12_1);
                        //
                        if (attain81.get(j).getField_12_2_2() == null || attain81.get(j).getField_12_2_2().length() == 0) {
                            attain81.get(j).setField_12_2_2("0");
                        }
                        ;
                        BigDecimal field_12_2_2 = new BigDecimal((attain81.get(j).getField_12_2_2()));
                        num18 = num18.add(field_12_2_2);
                        //
                        if (attain81.get(j).getField_12_2_3() == null || attain81.get(j).getField_12_2_3().length() == 0) {
                            attain81.get(j).setField_12_2_3("0");
                        }
                        ;
                        BigDecimal field_12_2_3 = new BigDecimal((attain81.get(j).getField_12_2_3()));
                        num19 = num19.add(field_12_2_3);
                        //
                        if (attain81.get(j).getField_12_2_4() == null || attain81.get(j).getField_12_2_4().length() == 0) {
                            attain81.get(j).setField_12_2_4("0");
                        }
                        ;
                        BigDecimal field_12_2_4 = new BigDecimal((attain81.get(j).getField_12_2_4()));
                        num20 = num20.add(field_12_2_4);
                        //
                        if (attain81.get(j).getField_12_3() == null || attain81.get(j).getField_12_3().length() == 0) {
                            attain81.get(j).setField_12_3("0");
                        }
                        ;
                        BigDecimal field_12_3 = new BigDecimal((attain81.get(j).getField_12_3()));
                        num21 = num21.add(field_12_3);
                        //
                        if (attain81.get(j).getField_12_4() == null || attain81.get(j).getField_12_4().length() == 0) {
                            attain81.get(j).setField_12_4("0");
                        }
                        ;
                        BigDecimal field_12_4 = new BigDecimal((attain81.get(j).getField_12_4()));
                        num22 = num22.add(field_12_4);
                        //
                        if (attain81.get(j).getField_12_5() == null || attain81.get(j).getField_12_5().length() == 0) {
                            attain81.get(j).setField_12_5("0");
                        }
                        ;
                        BigDecimal field_12_5 = new BigDecimal((attain81.get(j).getField_12_5()));
                        num23 = num23.add(field_12_5);
                        //
                        if (attain81.get(j).getField_12_6() == null || attain81.get(j).getField_12_6().length() == 0) {
                            attain81.get(j).setField_12_6("0");
                        }
                        ;
                        BigDecimal field_12_6 = new BigDecimal((attain81.get(j).getField_12_6()));
                        num24 = num24.add(field_12_6);
                        //
                        if (attain81.get(j).getField_12_7() == null || attain81.get(j).getField_12_7().length() == 0) {
                            attain81.get(j).setField_12_7("0");
                        }
                        ;
                        BigDecimal field_12_7 = new BigDecimal((attain81.get(j).getField_12_7()));
                        num25 = num25.add(field_12_7);
                        //
                        if (attain81.get(j).getField_12_8() == null || attain81.get(j).getField_12_8().length() == 0) {
                            attain81.get(j).setField_12_8("0");
                        }
                        ;
                        BigDecimal field_12_8 = new BigDecimal((attain81.get(j).getField_12_8()));
                        num26 = num26.add(field_12_8);
                        //
                        if (attain81.get(j).getField_12_9() == null || attain81.get(j).getField_12_9().length() == 0) {
                            attain81.get(j).setField_12_9("0");
                        }
                        ;
                        BigDecimal field_12_9 = new BigDecimal((attain81.get(j).getField_12_9()));
                        num27 = num27.add(field_12_9);
                        //
                        if (attain81.get(j).getField_12_10() == null || attain81.get(j).getField_12_10().length() == 0) {
                            attain81.get(j).setField_12_10("0");
                        }
                        ;
                        BigDecimal field_12_10 = new BigDecimal((attain81.get(j).getField_12_10()));
                        num28 = num28.add(field_12_10);
                        //
                        if (attain81.get(j).getField_13() == null || attain81.get(j).getField_13().length() == 0) {
                            attain81.get(j).setField_13("0");
                        }
                        ;
                        BigDecimal field_13 = new BigDecimal((attain81.get(j).getField_13()));
                        num29 = num29.add(field_13);
                        //
                        if (attain81.get(j).getField_14() == null || attain81.get(j).getField_14().length() == 0) {
                            attain81.get(j).setField_14("0");
                        }
                        ;
                        BigDecimal field_14 = new BigDecimal((attain81.get(j).getField_14()));
                        num30 = num30.add(field_14);
                        //
                        if (attain81.get(j).getField_15() == null || attain81.get(j).getField_15().length() == 0) {
                            attain81.get(j).setField_15("0");
                        }
                        ;
                        BigDecimal field_15 = new BigDecimal((attain81.get(j).getField_15()));
                        num31 = num31.add(field_15);
                        //
                        if (attain81.get(j).getField_16() == null || attain81.get(j).getField_16().length() == 0) {
                            attain81.get(j).setField_16("0");
                        }
                        ;
                        BigDecimal field_16 = new BigDecimal((attain81.get(j).getField_16()));
                        num32 = num32.add(field_16);
                        //计算该种植方式有多少个县
                        ++tableLength;
                    }
                    ;
                }
                ;
                BigDecimal length = new BigDecimal(tableLength);
                //System.out.println("length:" + length);
                List<BigDecimal> total = new ArrayList<BigDecimal>();
                total.add(num1);
                total.add(num2);
                total.add(num3);
                total.add(num4);
                total.add(num5);
                total.add(num6);
                total.add(num7);
                total.add(num8);
                total.add(num9);
                total.add(num10);
                total.add(num11);
                total.add(num12);
                total.add(num13);
                total.add(num14);
                total.add(num15);
                total.add(num16);
                total.add(num17);
                total.add(num18);
                total.add(num19);
                total.add(num20);
                total.add(num21);
                total.add(num22);
                total.add(num23);
                total.add(num24);
                total.add(num25);
                total.add(num26);
                total.add(num27);
                total.add(num28);
                total.add(num29);
                total.add(num30);
                total.add(num31);
                total.add(num32);
                List<BigDecimal> meanValue = new ArrayList<BigDecimal>();
                for (int k = 0; k < total.size(); k++) {
                    BigDecimal tempNum = total.get(k);
                    meanValue.add(tempNum.multiply(length).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                //System.out.println(meanValue);
                crop_01_01_table_08_01 info = new crop_01_01_table_08_01();
                info.setField_1("合计");
                info.setField_2("");
                info.setField_3("");
                info.setField_4("");
                info.setField_5("");
                info.setField_6(meanValue.get(0).toString());
                info.setField_7(plantWay.get(i));
                info.setField_8(meanValue.get(1).toString());
                info.setField_9(meanValue.get(2).toString());
                info.setField_10(meanValue.get(3).toString());
                info.setField_11_1_1(meanValue.get(4).toString());
                info.setField_11_1_2(meanValue.get(5).toString());
                info.setField_11_2_1(meanValue.get(6).toString());
                info.setField_11_2_2(meanValue.get(7).toString());
                info.setField_11_3_1(meanValue.get(8).toString());
                info.setField_11_3_2(meanValue.get(9).toString());
                info.setField_11_4_1(meanValue.get(10).toString());
                info.setField_11_4_2(meanValue.get(11).toString());
                info.setField_11_5_1(meanValue.get(12).toString());
                info.setField_11_5_2(meanValue.get(13).toString());
                info.setField_11_6_1(meanValue.get(14).toString());
                info.setField_11_6_2(meanValue.get(15).toString());
                info.setField_12_1(meanValue.get(16).toString());
                info.setField_12_2_2(meanValue.get(17).toString());
                info.setField_12_2_3(meanValue.get(18).toString());
                info.setField_12_2_4(meanValue.get(19).toString());
                info.setField_12_2_4(meanValue.get(20).toString());
                info.setField_12_3(meanValue.get(21).toString());
                info.setField_12_4(meanValue.get(22).toString());
                info.setField_12_5(meanValue.get(23).toString());
                info.setField_12_6(meanValue.get(24).toString());
                info.setField_12_7(meanValue.get(25).toString());
                info.setField_12_9(meanValue.get(26).toString());
                info.setField_12_10(meanValue.get(27).toString());
                info.setField_13(meanValue.get(28).toString());
                info.setField_14(meanValue.get(29).toString());
                info.setField_15(meanValue.get(30).toString());
                info.setField_16(meanValue.get(31).toString());
                info.setIn_use("");
                ////System.out.println(info);
                attain81.add(info);
            }
            ;
            //for循环取出县名，不包括其中的总计
            ArrayList<String> countyArray = new ArrayList<>();
            for (int i = 0; i < attain81.size() - 1; i++) {
                if (attain81.get(i).getField_1() != attain81.get(i + 1).getField_1()) {
                    countyArray.add(attain81.get(i).getField_1());
                }
                ;
            }
            ;
            //添加合计
            countyArray.add(attain81.get((attain81.size() - 1)).getField_1());
            //再做各个县的小计
            List<ArrayList<crop_01_01_table_08_01>> eachCounty = new ArrayList<ArrayList<crop_01_01_table_08_01>>();
            //存各自县数据
            for (int i = 0; i < countyArray.size(); ++i) {
                ArrayList<crop_01_01_table_08_01> selfInfo = new ArrayList<crop_01_01_table_08_01>();
                for (int j = 0; j < attain81.size(); j++) {
                    if (countyArray.get(i).equals(attain81.get(j).getField_1())) {
                        selfInfo.add(attain81.get(j));
                    }
                }
                ;
                eachCounty.add(selfInfo);
            }
            ;
            //System.out.println(eachCounty);
            for (int i = 0; i < eachCounty.size(); i++) {
                BigDecimal num1 = new BigDecimal("0");
                BigDecimal num2 = new BigDecimal("0");
                BigDecimal num3 = new BigDecimal("0");
                BigDecimal num4 = new BigDecimal("0");
                BigDecimal num5 = new BigDecimal("0");
                BigDecimal num6 = new BigDecimal("0");
                BigDecimal num7 = new BigDecimal("0");
                BigDecimal num8 = new BigDecimal("0");
                BigDecimal num9 = new BigDecimal("0");
                BigDecimal num10 = new BigDecimal("0");
                BigDecimal num11 = new BigDecimal("0");
                BigDecimal num12 = new BigDecimal("0");
                BigDecimal num13 = new BigDecimal("0");
                BigDecimal num14 = new BigDecimal("0");
                BigDecimal num15 = new BigDecimal("0");
                BigDecimal num16 = new BigDecimal("0");
                BigDecimal num17 = new BigDecimal("0");
                BigDecimal num18 = new BigDecimal("0");
                BigDecimal num19 = new BigDecimal("0");
                BigDecimal num20 = new BigDecimal("0");
                BigDecimal num21 = new BigDecimal("0");
                BigDecimal num22 = new BigDecimal("0");
                BigDecimal num23 = new BigDecimal("0");
                BigDecimal num24 = new BigDecimal("0");
                BigDecimal num25 = new BigDecimal("0");
                BigDecimal num26 = new BigDecimal("0");
                BigDecimal num27 = new BigDecimal("0");
                BigDecimal num28 = new BigDecimal("0");
                BigDecimal num29 = new BigDecimal("0");
                BigDecimal num30 = new BigDecimal("0");
                BigDecimal num31 = new BigDecimal("0");
                BigDecimal num32 = new BigDecimal("0");
                for (int j = 0; j < eachCounty.get(i).size(); j++) {
                    if (eachCounty.get(i).get(j).getField_6() == null || eachCounty.get(i).get(j).getField_6().length() == 0) {
                        eachCounty.get(i).get(j).setField_6("0");
                    }
                    ;
                    BigDecimal field_6 = new BigDecimal((eachCounty.get(i).get(j).getField_6()));
                    num1 = num1.add(field_6);
                    //
                    if (eachCounty.get(i).get(j).getField_8() == null || eachCounty.get(i).get(j).getField_8().length() == 0) {
                        eachCounty.get(i).get(j).setField_8("0");
                    }
                    ;
                    BigDecimal field_8 = new BigDecimal((eachCounty.get(i).get(j).getField_8()));
                    num2 = num2.add(field_6.multiply(field_8));
                    //
                    if (eachCounty.get(i).get(j).getField_9() == null || eachCounty.get(i).get(j).getField_9().length() == 0) {
                        eachCounty.get(i).get(j).setField_9("0");
                    }
                    ;
                    BigDecimal field_9 = new BigDecimal((eachCounty.get(i).get(j).getField_9()));
                    num3 = num3.add(field_6.multiply(field_9));
                    //
                    if (eachCounty.get(i).get(j).getField_10() == null || eachCounty.get(i).get(j).getField_10().length() == 0) {
                        eachCounty.get(i).get(j).setField_10("0");
                    }
                    ;
                    BigDecimal field_10 = new BigDecimal((eachCounty.get(i).get(j).getField_10()));
                    num4 = num4.add(field_6.multiply(field_10));
                    //
                    if (eachCounty.get(i).get(j).getField_11_1_1() == null || eachCounty.get(i).get(j).getField_11_1_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_1_1("0");
                    }
                    ;
                    BigDecimal field_11_1_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_1_1()));
                    num5 = num5.add(field_6.multiply(field_11_1_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_1_2() == null || eachCounty.get(i).get(j).getField_11_1_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_1_2("0");
                    }
                    ;
                    BigDecimal field_11_1_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_1_2()));
                    num6 = num6.add(field_6.multiply(field_11_1_2));
                    //
                    if (eachCounty.get(i).get(j).getField_11_2_1() == null || eachCounty.get(i).get(j).getField_11_2_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_2_1("0");
                    }
                    ;
                    BigDecimal field_11_2_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_2_1()));
                    num7 = num7.add(field_6.multiply(field_11_2_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_2_2() == null || eachCounty.get(i).get(j).getField_11_2_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_2_2("0");
                    }
                    ;
                    BigDecimal field_11_2_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_2_2()));
                    num8 = num8.add(field_6.multiply(field_11_2_2));
                    //
                    if (eachCounty.get(i).get(j).getField_11_3_1() == null || eachCounty.get(i).get(j).getField_11_3_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_3_1("0");
                    }
                    ;
                    BigDecimal field_11_3_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_3_1()));
                    num9 = num9.add(field_6.multiply(field_11_3_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_3_2() == null || eachCounty.get(i).get(j).getField_11_3_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_3_2("0");
                    }
                    ;
                    BigDecimal field_11_3_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_3_2()));
                    num10 = num10.add(field_6.multiply(field_11_3_2));
                    //
                    if (eachCounty.get(i).get(j).getField_11_4_1() == null || eachCounty.get(i).get(j).getField_11_4_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_4_1("0");
                    }
                    ;
                    BigDecimal field_11_4_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_4_1()));
                    num11 = num11.add(field_6.multiply(field_11_4_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_4_2() == null || eachCounty.get(i).get(j).getField_11_4_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_4_2("0");
                    }
                    ;
                    BigDecimal field_11_4_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_4_2()));
                    num12 = num12.add(field_6.multiply(field_11_4_2));
                    //
                    if (eachCounty.get(i).get(j).getField_11_5_1() == null || eachCounty.get(i).get(j).getField_11_5_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_5_1("0");
                    }
                    ;
                    BigDecimal field_11_5_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_5_1()));
                    num13 = num13.add(field_6.multiply(field_11_5_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_5_2() == null || eachCounty.get(i).get(j).getField_11_5_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_5_2("0");
                    }
                    ;
                    BigDecimal field_11_5_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_5_2()));
                    num14 = num14.add(field_6.multiply(field_11_5_2));
                    //
                    if (eachCounty.get(i).get(j).getField_11_6_1() == null || eachCounty.get(i).get(j).getField_11_6_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_6_1("0");
                    }
                    ;
                    BigDecimal field_11_6_1 = new BigDecimal((eachCounty.get(i).get(j).getField_11_6_1()));
                    num15 = num15.add(field_6.multiply(field_11_6_1));
                    //
                    if (eachCounty.get(i).get(j).getField_11_6_2() == null || eachCounty.get(i).get(j).getField_11_6_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_11_6_2("0");
                    }
                    ;
                    BigDecimal field_11_6_2 = new BigDecimal((eachCounty.get(i).get(j).getField_11_6_2()));
                    num16 = num16.add(field_6.multiply(field_11_6_2));
                    //
                    if (eachCounty.get(i).get(j).getField_12_1() == null || eachCounty.get(i).get(j).getField_12_1().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_1("0");
                    }
                    ;
                    BigDecimal field_12_1 = new BigDecimal((eachCounty.get(i).get(j).getField_12_1()));
                    num17 = num17.add(field_6.multiply(field_12_1));
                    //
                    if (eachCounty.get(i).get(j).getField_12_2_2() == null || eachCounty.get(i).get(j).getField_12_2_2().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_2_2("0");
                    }
                    ;
                    BigDecimal field_12_2_2 = new BigDecimal((eachCounty.get(i).get(j).getField_12_2_2()));
                    num18 = num18.add(field_6.multiply(field_12_2_2));
                    //
                    if (eachCounty.get(i).get(j).getField_12_2_3() == null || eachCounty.get(i).get(j).getField_12_2_3().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_2_3("0");
                    }
                    ;
                    BigDecimal field_12_2_3 = new BigDecimal((eachCounty.get(i).get(j).getField_12_2_3()));
                    num19 = num19.add(field_6.multiply(field_12_2_3));
                    //
                    if (eachCounty.get(i).get(j).getField_12_2_4() == null || eachCounty.get(i).get(j).getField_12_2_4().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_2_4("0");
                    }
                    ;
                    BigDecimal field_12_2_4 = new BigDecimal((eachCounty.get(i).get(j).getField_12_2_4()));
                    num20 = num20.add(field_6.multiply(field_12_2_4));
                    //
                    if (eachCounty.get(i).get(j).getField_12_3() == null || eachCounty.get(i).get(j).getField_12_3().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_3("0");
                    }
                    ;
                    BigDecimal field_12_3 = new BigDecimal((eachCounty.get(i).get(j).getField_12_3()));
                    num21 = num21.add(field_6.multiply(field_12_3));
                    //
                    if (eachCounty.get(i).get(j).getField_12_4() == null || eachCounty.get(i).get(j).getField_12_4().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_4("0");
                    }
                    ;
                    BigDecimal field_12_4 = new BigDecimal((eachCounty.get(i).get(j).getField_12_4()));
                    num22 = num22.add(field_6.multiply(field_12_4));
                    //
                    if (eachCounty.get(i).get(j).getField_12_5() == null || eachCounty.get(i).get(j).getField_12_5().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_5("0");
                    }
                    ;
                    BigDecimal field_12_5 = new BigDecimal((eachCounty.get(i).get(j).getField_12_5()));
                    num23 = num23.add(field_6.multiply(field_12_5));
                    //
                    if (eachCounty.get(i).get(j).getField_12_6() == null || eachCounty.get(i).get(j).getField_12_6().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_6("0");
                    }
                    ;
                    BigDecimal field_12_6 = new BigDecimal((eachCounty.get(i).get(j).getField_12_6()));
                    num24 = num24.add(field_6.multiply(field_12_6));
                    //
                    if (eachCounty.get(i).get(j).getField_12_7() == null || eachCounty.get(i).get(j).getField_12_7().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_7("0");
                    }
                    ;
                    BigDecimal field_12_7 = new BigDecimal((eachCounty.get(i).get(j).getField_12_7()));
                    num25 = num25.add(field_6.multiply(field_12_7));
                    //
                    if (eachCounty.get(i).get(j).getField_12_8() == null || eachCounty.get(i).get(j).getField_12_8().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_8("0");
                    }
                    ;
                    BigDecimal field_12_8 = new BigDecimal((eachCounty.get(i).get(j).getField_12_8()));
                    num26 = num26.add(field_6.multiply(field_12_8));
                    //
                    if (eachCounty.get(i).get(j).getField_12_9() == null || eachCounty.get(i).get(j).getField_12_9().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_9("0");
                    }
                    ;
                    BigDecimal field_12_9 = new BigDecimal((eachCounty.get(i).get(j).getField_12_9()));
                    num27 = num27.add(field_6.multiply(field_12_9));
                    //
                    if (eachCounty.get(i).get(j).getField_12_10() == null || eachCounty.get(i).get(j).getField_12_10().length() == 0) {
                        eachCounty.get(i).get(j).setField_12_10("0");
                    }
                    ;
                    BigDecimal field_12_10 = new BigDecimal((eachCounty.get(i).get(j).getField_12_10()));
                    num28 = num28.add(field_6.multiply(field_12_10));
                    //
                    if (eachCounty.get(i).get(j).getField_13() == null || eachCounty.get(i).get(j).getField_13().length() == 0) {
                        eachCounty.get(i).get(j).setField_13("0");
                    }
                    ;
                    BigDecimal field_13 = new BigDecimal((eachCounty.get(i).get(j).getField_13()));
                    num29 = num29.add(field_6.multiply(field_13));
                    //
                    if (eachCounty.get(i).get(j).getField_14() == null || eachCounty.get(i).get(j).getField_14().length() == 0) {
                        eachCounty.get(i).get(j).setField_14("0");
                    }
                    ;
                    BigDecimal field_14 = new BigDecimal((eachCounty.get(i).get(j).getField_14()));
                    num30 = num30.add(field_6.multiply(field_14));
                    //
                    if (eachCounty.get(i).get(j).getField_15() == null || eachCounty.get(i).get(j).getField_15().length() == 0) {
                        eachCounty.get(i).get(j).setField_15("0");
                    }
                    ;
                    BigDecimal field_15 = new BigDecimal((eachCounty.get(i).get(j).getField_15()));
                    num31 = num31.add(field_6.multiply(field_15));
                    //
                    if (eachCounty.get(i).get(j).getField_16() == null || eachCounty.get(i).get(j).getField_16().length() == 0) {
                        eachCounty.get(i).get(j).setField_16("0");
                    }
                    ;
                    BigDecimal field_16 = new BigDecimal((eachCounty.get(i).get(j).getField_16()));
                    num32 = num32.add(field_6.multiply(field_16));
                }
                ;
                List<BigDecimal> eachSum = new ArrayList<BigDecimal>();
                eachSum.add(num1);
                eachSum.add(num2.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num3.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num4.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num5.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num6.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num7.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num8.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num9.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num10.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num11.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num12.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num13.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num14.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num15.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num16.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num17.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num18.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num19.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num20.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num21.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num22.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num23.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num24.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num25.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num26.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num27.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num28.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num29.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num30.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num31.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                eachSum.add(num32.divide(num1,2,BigDecimal.ROUND_HALF_UP));
                crop_01_01_table_08_01 infoData = new crop_01_01_table_08_01();
                infoData.setField_1(eachCounty.get(i).get(0).getField_1());
                infoData.setField_2("--");
                infoData.setField_3("--");
                infoData.setField_4("--");
                infoData.setField_5("--");
                infoData.setField_6(eachSum.get(0).toString());
                infoData.setField_7("小计");
                infoData.setField_8(eachSum.get(1).toString());
                infoData.setField_9(eachSum.get(2).toString());
                infoData.setField_10(eachSum.get(3).toString());
                infoData.setField_11_1_1(eachSum.get(4).toString());
                infoData.setField_11_1_2(eachSum.get(5).toString());
                infoData.setField_11_2_1(eachSum.get(6).toString());
                infoData.setField_11_2_2(eachSum.get(7).toString());
                infoData.setField_11_3_1(eachSum.get(8).toString());
                infoData.setField_11_3_2(eachSum.get(9).toString());
                infoData.setField_11_4_1(eachSum.get(10).toString());
                infoData.setField_11_4_2(eachSum.get(11).toString());
                infoData.setField_11_5_1(eachSum.get(12).toString());
                infoData.setField_11_5_2(eachSum.get(13).toString());
                infoData.setField_11_6_1(eachSum.get(14).toString());
                infoData.setField_11_6_2(eachSum.get(15).toString());
                infoData.setField_12_1(eachSum.get(16).toString());
                infoData.setField_12_2_2(eachSum.get(17).toString());
                infoData.setField_12_2_3(eachSum.get(18).toString());
                infoData.setField_12_2_4(eachSum.get(19).toString());
                infoData.setField_12_2_4(eachSum.get(20).toString());
                infoData.setField_12_3(eachSum.get(21).toString());
                infoData.setField_12_4(eachSum.get(22).toString());
                infoData.setField_12_5(eachSum.get(23).toString());
                infoData.setField_12_6(eachSum.get(24).toString());
                infoData.setField_12_7(eachSum.get(25).toString());
                infoData.setField_12_9(eachSum.get(26).toString());
                infoData.setField_12_10(eachSum.get(27).toString());
                infoData.setField_13(eachSum.get(28).toString());
                infoData.setField_14(eachSum.get(29).toString());
                infoData.setField_15(eachSum.get(30).toString());
                infoData.setField_16(eachSum.get(31).toString());
                infoData.setIn_use("--");
                eachCounty.get(i).add(infoData);
            }
            ;
            //System.out.println(eachCounty);
            for (int i = 0; i < eachCounty.size(); i++) {
                for (int j = 0; j < eachCounty.get(i).size(); j++) {
                    total81.add(eachCounty.get(i).get(j));
                }
                ;
            }
            ;
            //System.out.println(total81);
        };
        return total81;

    }
    @Override
    public List<crop_01_01_table_08_01> getRiceTable81(String county, String areaType,String year) {
        return tableMapper.getRiceTable81(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_08_01> getAuditRiceTable81(String year) {
        return tableMapper.getAuditRiceTable81(year);
    }
    @Override
    public List<crop_01_01_table_08_01> getSeekInfo81(String county, String areaType, String riceType, String category, String plantWay,String year) {
        return tableMapper.getSeekInfo81(county,areaType,riceType,category,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_09_01> getRiceTable91(String county, String areaType,String year) {
        return tableMapper.getRiceTable91(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_01> getAuditRiceTable91(String year) {
        return tableMapper.getAuditRiceTable91(year);
    }
    @Override
    public List<crop_01_01_table_09_01> getSeekInfo91(String county,String year) {
        return tableMapper.getSeekInfo91(county,year);
    }

    @Override
    public List<crop_01_01_table_09_02> getRiceTable92(String currentYear,String previousYear,String county, String areaType,String year) {
        return tableMapper.getRiceTable92(currentYear,previousYear,county,areaType,year);
    }
    @Override
    public String[] getCurrentCounty(String county,String year) {

        List<crop_01_01_table_09_02> list92 = tableMapper.getCurrentCounty(year,county);
        String[] data1 = new String[list92.size()];
        for(int i=0; i<list92.size();i++){
            data1[i] = list92.get(i).getField_1_1();
        }
        return data1;
    }
    //审核
    @Override
    public List<crop_01_01_table_09_02> getAuditRiceTable92(String currentYear,String year) {
        return tableMapper.getAuditRiceTable92(currentYear,year);
    }
    @Override
    public List<crop_01_01_table_09_02> getSeekInfo92(String county,String currentYear,String previousYear,String year) {
        return tableMapper.getSeekInfo92(county,currentYear,previousYear,year);
    }

    //分页数据获取
    @Override
    public pageBean<crop_01_01_table_09_03> getPageRiceTable93(String county, String areaType, String year, int start, int pageSize) {
        HashMap<String,Object> map =new HashMap<String, Object>();
        pageBean<crop_01_01_table_09_03> pageBean = new pageBean<crop_01_01_table_09_03>();
        //封装当前页数
        pageBean.setCurrPage(start);
        //每页显示的数据
        pageBean.setPageSize(pageSize);
//        //封装总记录数
//        int totalCount = (getRiceTable93(county,areaType,year)).size();
//        pageBean.setTotalCount(totalCount);
//        //封装总页数
//        double tc = totalCount;
//        Double num = Math.ceil(tc/pageSize);
//        pageBean.setTotalPage(num.intValue());

        map.put("county",county);
        map.put("areaType",areaType);
        map.put("year",year);
        map.put("start",(start-1)*pageSize);
        map.put("size",pageBean.getPageSize());
        //封装每页显示的数据
        List<crop_01_01_table_09_03> lists = tableMapper.getPageRiceTable93(map);
        pageBean.setLists(lists);
        return pageBean;
    }


    @Override
    public List<crop_01_01_table_09_03> getRiceTable93(String county, String areaType,String year) {
        return tableMapper.getRiceTable93(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_03> getAuditRiceTable93(String year) {
        return tableMapper.getAuditRiceTable93(year);
    }
    @Override
    public List<crop_01_01_table_09_03>getSeekInfo93(String county, String areaType, String riceType, String category,String year) {
        return tableMapper.getSeekInfo93(county,areaType,riceType,category,year);
    }

    @Override
    public List<crop_01_01_table_09_04> getRiceTable94(String county, String areaType,String year) {
        return tableMapper.getRiceTable94(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_04> getAuditRiceTable94(String year) {
        return tableMapper.getAuditRiceTable94(year);
    }
    @Override
    public List<crop_01_01_table_09_04>getSeekInfo94(String county, String areaType, String riceType, String category,String year) {
        return tableMapper.getSeekInfo94(county,areaType,riceType,category,year);
    }

    @Override
    public List<crop_01_01_table_09_05> getRiceTable95(String county, String areaType,String year) {
        return tableMapper.getRiceTable95(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_05> getAuditRiceTable95(String year) {
        return tableMapper.getAuditRiceTable95(year);
    }
    @Override
    public List<crop_01_01_table_09_05>getSeekInfo95(String county, String areaType, String riceType, String category,String year) {
        return tableMapper.getSeekInfo95(county,areaType,riceType,category,year);
    }

    @Override
    public List<crop_01_01_table_09_06> getRiceTable96(String county, String areaType,String year) {
        return tableMapper.getRiceTable96(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_06> getAuditRiceTable96(String year) {
        return tableMapper.getAuditRiceTable96(year);
    }
    @Override
    public List<crop_01_01_table_09_06>getSeekInfo96(String county, String areaType, String riceType, String category,String year) {
        return tableMapper.getSeekInfo96(county,areaType,riceType,category,year);
    }

    @Override
    public List<crop_01_01_table_09_07> getRiceTable97(String county, String areaType,String year) {
        return tableMapper.getRiceTable97(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_09_07> getAuditRiceTable97(String year) {
        return tableMapper.getAuditRiceTable97(year);
    }
    @Override
    public List<crop_01_01_table_09_07>getSeekInfo97(String county, String areaType, String plantWay,String year) {
        return tableMapper.getSeekInfo97(county,areaType,plantWay,year);
    }

    @Override
    public List<crop_01_01_table_10_01> getRiceTable101(String county, String areaType,String year) {
        return tableMapper.getRiceTable101(county,areaType,year);
    }
    //审核
    @Override
    public List<crop_01_01_table_10_01> getAuditRiceTable101(String year) {
        return tableMapper.getAuditRiceTable101(year);
    }
    @Override
    public List<crop_01_01_table_10_01>getSeekInfo101(String county, String areaType, String plantWay,String year) {
        return tableMapper.getSeekInfo101(county,areaType,plantWay,year);
    }

    @Override
    public int getAuditData(String city, String name,String chinese, int dataId, String times,String auditPeople) {
        return tableMapper.getAuditData(city,name,chinese,dataId,times,auditPeople);
    }

    @Override
    public int deleteRepeatData() {
        return tableMapper.deleteRepeatData();
    }

    @Override
    public List<table_audit> getNoAuditAllData() {
        return tableMapper.getNoAuditAllData();
    }

    @Override
    public pageBean<table_audit> getNoAuditInfo(String params1, int start, int pageSize) {
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<table_audit> backInfo = new pageBean<table_audit>();
        //封装当前页
        backInfo.setCurrPage(start);
        //封装页码
        backInfo.setPageSize(pageSize);
        //封装总记录数
        int total = (tableMapper.getNoAuditAllInfo(params1).size());
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
        List<table_audit> lists= tableMapper.getNoAuditAllDataMap(map);
        backInfo.setLists(lists);
        return backInfo;
        //return tableMapper.getNoAuditAllData();
    }

    @Override
    public int deleteRecord(int rowId, String rowName, String rowTime) {
        return tableMapper.deleteRecord(rowId,rowName,rowTime);
    }

    @Override
    public List<sum_select_list> getCountyInfo() {
        return tableMapper.getCountyInfo();
    }

    @Override
    public pageBean<table_record_back> getBackInfo(String year, int start, int pageSize) {
        HashMap<String,Object> map = new HashMap<String, Object>();
        pageBean<table_record_back> backInfo = new pageBean<table_record_back>();
        //封装当前页和页码
        backInfo.setCurrPage(start);
        backInfo.setPageSize(pageSize);
        //封装总记录数和总页码
        int total = (tableMapper.getBackInfoAll(year)).size();
        backInfo.setTotalCount(total);
        double totalCount=total;
        Double num=Math.ceil(totalCount/pageSize);
        backInfo.setTotalPage(num.intValue());
        //传参
        map.put("year",year);
        map.put("start",(start-1)*pageSize);
        map.put("size",backInfo.getPageSize());
        //封装每页显示的数据
        List<table_record_back> lists= tableMapper.getBackInfo(map);
        backInfo.setLists(lists);
        return backInfo;
    }

    @Override
    public pageBean<table_record_back> getAllBackData(String params1, String params2, String year, int start, int pageSize) {
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<table_record_back> backInfo = new pageBean<table_record_back>();
        //封装当前页
        backInfo.setCurrPage(start);
        //封装页码
        backInfo.setPageSize(pageSize);
        //封装总记录数
        int total = (tableMapper.getBackSeeKInfo(params1,params2,year).size());
        backInfo.setTotalCount(total);
        //封装总页码
        double totalCount=total;
        Double num=Math.ceil(totalCount/pageSize);
        backInfo.setTotalPage(num.intValue());
        //传参
        map.put("params1",params1);
        map.put("params2",params2);
        map.put("year",year);
        map.put("start",(start-1)*pageSize);
        map.put("size",backInfo.getPageSize());
        //封装每页显示的数据
        List<table_record_back> lists= tableMapper.getAllBackData(map);
        backInfo.setLists(lists);
        return backInfo;
    }

    @Override
    public List<crop_01_01_table_04_04> getPeriod(String year) {
        return tableMapper.getPeriod(year);
    }

    //获取未审核各表数据
    @Override
    public pageBean<? extends Object> getRiceAudit1(String params1,String riceTableName, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<? extends Object> info= new pageBean<>();
        if(riceTableName.equals("crop_01_01_table_01_01")){
            pageBean<crop_01_01_table_01_01> TableInfo = new pageBean<crop_01_01_table_01_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable11(year).size());
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
            List<crop_01_01_table_01_01> lists= tableMapper.getRiceAudit11(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }
//        else if(riceTableName.equals("crop_01_01_table_01_02")){
//            pageBean<crop_01_01_table_01_02> TableInfo = new pageBean<crop_01_01_table_01_02>();
//            //封装当前页
//            TableInfo.setCurrPage(start);
//            //封装页码
//            //封装总记录数
//            int total = (tableMapper.getAuditRiceTable12(year).size());
//            TableInfo.setPageSize(pageSize);
//            TableInfo.setTotalCount(total);
//            //封装总页码
//            double totalCount=total;
//            Double num=Math.ceil(totalCount/pageSize);
//            TableInfo.setTotalPage(num.intValue());
//            //传参
//            map.put("params1",params1);
//            map.put("year",year);
//            map.put("start",(start-1)*pageSize);
//            map.put("size",TableInfo.getPageSize());
//            List<crop_01_01_table_01_02> lists= tableMapper.getRiceAudit12(map);
//            TableInfo.setLists(lists);
//            info = TableInfo;
//             // return TableInfo;
//        }
        else if(riceTableName.equals("crop_01_01_table_03_01")){
            pageBean<crop_01_01_table_03_01> TableInfo = new pageBean<crop_01_01_table_03_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable31(year).size());
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
            List<crop_01_01_table_03_01> lists= tableMapper.getRiceAudit31(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_03_02")){
            pageBean<crop_01_01_table_03_02> TableInfo = new pageBean<crop_01_01_table_03_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable32(year).size());
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
            List<crop_01_01_table_03_02> lists= tableMapper.getRiceAudit32(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_03_03")){
            pageBean<crop_01_01_table_03_03> TableInfo = new pageBean<crop_01_01_table_03_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable33(year).size());
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
            List<crop_01_01_table_03_03> lists= tableMapper.getRiceAudit33(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_01")){
            pageBean<crop_01_01_table_04_01> TableInfo = new pageBean<crop_01_01_table_04_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable41(year).size());
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
            List<crop_01_01_table_04_01> lists= tableMapper.getRiceAudit41(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_02")){
            pageBean<crop_01_01_table_04_02> TableInfo = new pageBean<crop_01_01_table_04_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable42(year).size());
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
            List<crop_01_01_table_04_02> lists= tableMapper.getRiceAudit42(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_03")){
            pageBean<crop_01_01_table_04_03> TableInfo = new pageBean<crop_01_01_table_04_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable43(year).size());
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
            List<crop_01_01_table_04_03> lists= tableMapper.getRiceAudit43(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_04")){
            pageBean<crop_01_01_table_04_04> TableInfo = new pageBean<crop_01_01_table_04_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable44(year).size());
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
            List<crop_01_01_table_04_04> lists= tableMapper.getRiceAudit44(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_05_01")){
            pageBean<crop_01_01_table_05_01> TableInfo = new pageBean<crop_01_01_table_05_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable51(year).size());
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
            List<crop_01_01_table_05_01> lists= tableMapper.getRiceAudit51(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_01")){
            pageBean<crop_01_01_table_06_01> TableInfo = new pageBean<crop_01_01_table_06_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable61(year).size());
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
            List<crop_01_01_table_06_01> lists= tableMapper.getRiceAudit61(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_02")){
            pageBean<crop_01_01_table_06_02> TableInfo = new pageBean<crop_01_01_table_06_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable62(year).size());
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
            List<crop_01_01_table_06_02> lists= tableMapper.getRiceAudit62(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_03")){
            pageBean<crop_01_01_table_06_03> TableInfo = new pageBean<crop_01_01_table_06_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable63(year).size());
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
            List<crop_01_01_table_06_03> lists= tableMapper.getRiceAudit63(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_04")){
            pageBean<crop_01_01_table_06_04> TableInfo = new pageBean<crop_01_01_table_06_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable64(year).size());
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
            List<crop_01_01_table_06_04> lists= tableMapper.getRiceAudit64(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_05")){
            pageBean<crop_01_01_table_06_05> TableInfo = new pageBean<crop_01_01_table_06_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable65(year).size());
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
            List<crop_01_01_table_06_05> lists= tableMapper.getRiceAudit65(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_01")){
            pageBean<crop_01_01_table_07_01> TableInfo = new pageBean<crop_01_01_table_07_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable71(year).size());
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
            List<crop_01_01_table_07_01> lists= tableMapper.getRiceAudit71(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_02")){
            pageBean<crop_01_01_table_07_02> TableInfo = new pageBean<crop_01_01_table_07_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable72(year).size());
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
            List<crop_01_01_table_07_02> lists= tableMapper.getRiceAudit72(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_03")){
            pageBean<crop_01_01_table_07_03> TableInfo = new pageBean<crop_01_01_table_07_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable73(year).size());
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
            List<crop_01_01_table_07_03> lists= tableMapper.getRiceAudit73(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_08_01")){
            pageBean<crop_01_01_table_08_01> TableInfo = new pageBean<crop_01_01_table_08_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable81(year).size());
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
            List<crop_01_01_table_08_01> lists= tableMapper.getRiceAudit81(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_03")){
            pageBean<crop_01_01_table_09_03> TableInfo = new pageBean<crop_01_01_table_09_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable93(year).size());
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
            List<crop_01_01_table_09_03> lists= tableMapper.getRiceAudit93(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_04")){
            pageBean<crop_01_01_table_09_04> TableInfo = new pageBean<crop_01_01_table_09_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable94(year).size());
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
            List<crop_01_01_table_09_04> lists= tableMapper.getRiceAudit94(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_05")){
            pageBean<crop_01_01_table_09_05> TableInfo = new pageBean<crop_01_01_table_09_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable95(year).size());
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
            List<crop_01_01_table_09_05> lists= tableMapper.getRiceAudit95(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_06")){
            pageBean<crop_01_01_table_09_06> TableInfo = new pageBean<crop_01_01_table_09_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable96(year).size());
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
            List<crop_01_01_table_09_06> lists= tableMapper.getRiceAudit96(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_07")){
            pageBean<crop_01_01_table_09_07> TableInfo = new pageBean<crop_01_01_table_09_07>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable97(year).size());
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
            List<crop_01_01_table_09_07> lists= tableMapper.getRiceAudit97(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_10_01")){
            pageBean<crop_01_01_table_10_01> TableInfo = new pageBean<crop_01_01_table_10_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getAuditRiceTable101(year).size());
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
            List<crop_01_01_table_10_01> lists= tableMapper.getRiceAudit101(map);
            TableInfo.setLists(lists);
            info = TableInfo;
             // return TableInfo;
        };
        //System.out.println("info1:"+info);
        return info;
    };
    //获取未审核表1-2数据
    @Override
    public ArrayList<HashMap<String,Object> > getRiceTableAudit12(String params1, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        //处理表1-2数据
        //传参
        System.out.println(params1+";"+year);
        map.put("params1", params1);
        map.put("year", year);
        //取出表1-2所有值
        List<crop_01_01_table_01_02> lists = tableMapper.getRiceAudit12(map);
        System.out.println(lists);
        ////封装总记录数
        List<crop_01_01_table_01_02> list2 = tableMapper.getAuditRiceTable12(year);
        System.out.println(list2);
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
        System.out.println("temp:"+temp);

        return temp;
    }
    //搜索未审核各表数据
    @Override
    public pageBean<? extends Object> searchRiceAudit1(String params1,String month, String riceTableName, int start, int pageSize, String year) {
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        pageBean<? extends Object> info= new pageBean<>();
        StringBuilder Str = new StringBuilder();
        if(month != null || month !=""){
            Str.append(year).append("-").append(month);
        }else{
            Str.append(year);
        };
        String time = Str.toString();
        //System.out.println(time);

        if(riceTableName.equals("crop_01_01_table_01_01")){
            pageBean<crop_01_01_table_01_01> TableInfo = new pageBean<crop_01_01_table_01_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit11(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_01_01> lists= tableMapper.searchRiceAudit11(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_01_02")){
            pageBean<crop_01_01_table_01_02> TableInfo = new pageBean<crop_01_01_table_01_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit12(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_01_02> lists= tableMapper.searchRiceAudit12(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_03_01")){
            pageBean<crop_01_01_table_03_01> TableInfo = new pageBean<crop_01_01_table_03_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit31(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_03_01> lists= tableMapper.searchRiceAudit31(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_03_02")){
            pageBean<crop_01_01_table_03_02> TableInfo = new pageBean<crop_01_01_table_03_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit32(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_03_02> lists= tableMapper.searchRiceAudit32(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_03_03")){
            pageBean<crop_01_01_table_03_03> TableInfo = new pageBean<crop_01_01_table_03_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit33(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_03_03> lists= tableMapper.searchRiceAudit33(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_01")){
            pageBean<crop_01_01_table_04_01> TableInfo = new pageBean<crop_01_01_table_04_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit41(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_04_01> lists= tableMapper.searchRiceAudit41(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_02")){
            pageBean<crop_01_01_table_04_02> TableInfo = new pageBean<crop_01_01_table_04_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit42(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_04_02> lists= tableMapper.searchRiceAudit42(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_03")){
            pageBean<crop_01_01_table_04_03> TableInfo = new pageBean<crop_01_01_table_04_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit43(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_04_03> lists= tableMapper.searchRiceAudit43(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_04_04")){
            pageBean<crop_01_01_table_04_04> TableInfo = new pageBean<crop_01_01_table_04_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit44(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_04_04> lists= tableMapper.searchRiceAudit44(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_05_01")){
            pageBean<crop_01_01_table_05_01> TableInfo = new pageBean<crop_01_01_table_05_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit51(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_05_01> lists= tableMapper.searchRiceAudit51(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_01")){
            pageBean<crop_01_01_table_06_01> TableInfo = new pageBean<crop_01_01_table_06_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit61(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_06_01> lists= tableMapper.searchRiceAudit61(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_02")){
            pageBean<crop_01_01_table_06_02> TableInfo = new pageBean<crop_01_01_table_06_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit62(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_06_02> lists= tableMapper.searchRiceAudit62(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_03")){
            pageBean<crop_01_01_table_06_03> TableInfo = new pageBean<crop_01_01_table_06_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit63(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_06_03> lists= tableMapper.searchRiceAudit63(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_04")){
            pageBean<crop_01_01_table_06_04> TableInfo = new pageBean<crop_01_01_table_06_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit64(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_06_04> lists= tableMapper.searchRiceAudit64(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_06_05")){
            pageBean<crop_01_01_table_06_05> TableInfo = new pageBean<crop_01_01_table_06_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit65(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_06_05> lists= tableMapper.searchRiceAudit65(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_01")){
            pageBean<crop_01_01_table_07_01> TableInfo = new pageBean<crop_01_01_table_07_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit71(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_07_01> lists= tableMapper.searchRiceAudit71(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_02")){
            pageBean<crop_01_01_table_07_02> TableInfo = new pageBean<crop_01_01_table_07_02>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit72(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_07_02> lists= tableMapper.searchRiceAudit72(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_07_03")){
            pageBean<crop_01_01_table_07_03> TableInfo = new pageBean<crop_01_01_table_07_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit73(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_07_03> lists= tableMapper.searchRiceAudit73(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_08_01")){
            pageBean<crop_01_01_table_08_01> TableInfo = new pageBean<crop_01_01_table_08_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit81(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_08_01> lists= tableMapper.searchRiceAudit81(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_03")){
            pageBean<crop_01_01_table_09_03> TableInfo = new pageBean<crop_01_01_table_09_03>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit93(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_09_03> lists= tableMapper.searchRiceAudit93(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_04")){
            pageBean<crop_01_01_table_09_04> TableInfo = new pageBean<crop_01_01_table_09_04>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit94(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_09_04> lists= tableMapper.searchRiceAudit94(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_05")){
            pageBean<crop_01_01_table_09_05> TableInfo = new pageBean<crop_01_01_table_09_05>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit95(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_09_05> lists= tableMapper.searchRiceAudit95(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_06")){
            pageBean<crop_01_01_table_09_06> TableInfo = new pageBean<crop_01_01_table_09_06>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit96(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_09_06> lists= tableMapper.searchRiceAudit96(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_09_07")){
            pageBean<crop_01_01_table_09_07> TableInfo = new pageBean<crop_01_01_table_09_07>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit97(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_09_07> lists= tableMapper.searchRiceAudit97(map);
            TableInfo.setLists(lists);
            info = TableInfo;
            // return TableInfo;
        }else if(riceTableName.equals("crop_01_01_table_10_01")){
            pageBean<crop_01_01_table_10_01> TableInfo = new pageBean<crop_01_01_table_10_01>();
            //封装当前页
            TableInfo.setCurrPage(start);
            //封装页码
            //封装总记录数
            int total = (tableMapper.getSearchRiceAudit101(params1,time).size());
            TableInfo.setPageSize(pageSize);
            TableInfo.setTotalCount(total);
            //封装总页码
            double totalCount=total;
            Double num=Math.ceil(totalCount/pageSize);
            TableInfo.setTotalPage(num.intValue());
            //传参
            map.put("params1",params1);
            map.put("time",time);
            map.put("start",(start-1)*pageSize);
            map.put("size",TableInfo.getPageSize());
            List<crop_01_01_table_10_01> lists= tableMapper.searchRiceAudit101(map);
            TableInfo.setLists(lists);
            info = TableInfo;
        };
        return info;
    };
    //搜索未审核表1-2数据
    @Override
    public ArrayList<HashMap<String,Object> > searchRiceAudit12(String params1,String month, String riceTableName, int start, int pageSize, String year) {

//        StringBuilder Str = new StringBuilder();
//        if(month != null || month !=""){
//            Str.append(year).append("-").append(month);
//        }else{
//            Str.append(year);
//        };
//        String time = Str.toString();
//        //System.out.println(time);
        //封装每页显示的数据
        HashMap<String ,Object> map = new HashMap<String ,Object>();
        //处理表1-2数据
        //传参
        map.put("params1", params1);
        map.put("year", year);
        //取出表1-2所有值
        List<crop_01_01_table_01_02> lists = tableMapper.searchRiceAudit12(map);
        //System.out.println(lists);
        ////封装总记录数
        List<crop_01_01_table_01_02> list2 = tableMapper.getSearchRiceAudit12(params1,year);
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
    //获取各表未上报县数据
    @Override
    public List<HashMap<String,Object>> getNoSumbitData(String year){
        List<sum_select_list> selectInfo = tableMapper.getCounty1();
        ArrayList<String> countyInfo = new ArrayList<String>();
        //System.out.println(selectInfo);
        for(int i=0;i<selectInfo.size();i++){
            if(selectInfo.get(i).getCaption().equals("县（市、区）") ){
                countyInfo.add(selectInfo.get(i).getList());
            };
        };
        List<crop_01_01_table_03_01> one31 = tableMapper.getRice31(year);
        List<crop_01_01_table_03_02> one32 = tableMapper.getRice32(year);
        List<crop_01_01_table_03_03> one33 = tableMapper.getRice33(year);
        List<crop_01_01_table_04_01> one41 = tableMapper.getRice41(year);
        List<crop_01_01_table_04_02> one42 = tableMapper.getRice42(year);
        List<crop_01_01_table_04_03> one43 = tableMapper.getRice43(year);
        List<crop_01_01_table_04_04> one44 = tableMapper.getRice44(year);
        List<crop_01_01_table_07_01> one71 = tableMapper.getRice71(year);
        List<crop_01_01_table_07_02> one72 = tableMapper.getRice72(year);
        List<crop_01_01_table_07_03> one73 = tableMapper.getRice73(year);
        List<crop_01_01_table_08_01> one81 = tableMapper.getRice81(year);

        ArrayList<ArrayList> noCounty =new ArrayList<ArrayList>();
        Map<String, Integer> map=new HashMap<String, Integer>();
        if(one31.size()==0){
            noCounty.add(0,countyInfo);
        }else{
            ArrayList<String> temp = new ArrayList<String>();
            for (int i=0;i<one31.size();i++){
                if(!one31.get(i).getField_9_1().equals(null))
                temp.add(i,one31.get(i).getField_1());
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
        //存表名
        ArrayList<String> countyNumber =new ArrayList<String>();
        for(int i=0;i<noCounty.size();i++){
            String number = String.valueOf(noCounty.get(i).size());
            countyNumber.add(i,number);
        }
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
                map1.put("number",countyNumber.get(i));
                map1.put("county_name",noCounty.get(i));
                reportInfo.add(map1);
            }
        };

        return null;
    }




    //    处理未上报数据
    @Override
    public List<crop_01_01_table_01_01> getRice11(String year) {
        return tableMapper.getRice11(year);
    }
    @Override
    public List<crop_01_01_table_01_02> getRice12(String year) {
        return tableMapper.getRice12(year);
    }
    @Override
    public List<crop_01_01_table_03_01> getRice31(String year) {
        return tableMapper.getRice31(year);
    }
    @Override
    public List<crop_01_01_table_03_02> getRice32(String year) {
        return tableMapper.getRice32(year);
    }
    @Override
    public List<crop_01_01_table_03_03> getRice33(String year) {
        return tableMapper.getRice33(year);
    }
    @Override
    public List<crop_01_01_table_04_01> getRice41(String year) {
        return tableMapper.getRice41(year);
    }
    @Override
    public List<crop_01_01_table_04_02> getRice42(String year) {
        return tableMapper.getRice42(year);
    }
    @Override
    public List<crop_01_01_table_04_03> getRice43(String year) {
        return tableMapper.getRice43(year);
    }
    @Override
    public List<crop_01_01_table_04_04> getRice44(String year) {
        return tableMapper.getRice44(year);
    }
    @Override
    public List<crop_01_01_table_05_01> getRice51(String year) {
        return tableMapper.getRice51(year);
    }
    @Override
    public List<crop_01_01_table_06_01> getRice61(String year) {
        return tableMapper.getRice61(year);
    }
    @Override
    public List<crop_01_01_table_06_02> getRice62(String year) {
        return tableMapper.getRice62(year);
    }
    @Override
    public List<crop_01_01_table_06_03> getRice63(String year) {
        return tableMapper.getRice63(year);
    }
    @Override
    public List<crop_01_01_table_06_04> getRice64(String year) {
        return tableMapper.getRice64(year);
    }
    @Override
    public List<crop_01_01_table_06_05> getRice65(String year) {
        return tableMapper.getRice65(year);
    }
    @Override
    public List<crop_01_01_table_07_01> getRice71(String year) {
        return tableMapper.getRice71(year);
    }
    @Override
    public List<crop_01_01_table_07_02> getRice72(String year) {
        return tableMapper.getRice72(year);
    }
    @Override
    public List<crop_01_01_table_07_03> getRice73(String year) {
        return tableMapper.getRice73(year);
    }
    @Override
    public List<crop_01_01_table_08_01> getRice81(String year) {
        return tableMapper.getRice81(year);
    }
    @Override
    public List<crop_01_01_table_09_01> getRice91(String year) {
        return tableMapper.getRice91(year);
    }
    @Override
    public List<crop_01_01_table_09_02> getRice92(String year) {
        return tableMapper.getRice92(year);
    }
    @Override
    public List<crop_01_01_table_09_03> getRice93(String year) {
        return tableMapper.getRice93(year);
    }
    @Override
    public List<crop_01_01_table_09_04> getRice94(String year) {
        return tableMapper.getRice94(year);
    }
    @Override
    public List<crop_01_01_table_09_05> getRice95(String year) {
        return tableMapper.getRice95(year);
    }
    @Override
    public List<crop_01_01_table_09_06> getRice96(String year) {
        return tableMapper.getRice96(year);
    }
    @Override
    public List<crop_01_01_table_09_07> getRice97(String year) {
        return tableMapper.getRice97(year);
    }
    @Override
    public List<crop_01_01_table_10_01> getRice101(String year) {
        return tableMapper.getRice101(year);
    }

    @Override
    public List<table_second_name> getTabName() {
        return tableMapper.getTabName();
    }
    //删除审核表
    @Override
    public int deleteAudit() {
        int audit1= tableMapper.deleteAudit();
        int audit2= tableMapper.deleteAudit2();
        return audit1;
    }

    //删除监测点
    @Override
    public int deletePoint(crop_01_01_table_02_02 deleteInfo,String year){
        System.out.println(deleteInfo);
        String pointCode = deleteInfo.getField_1();
        String people = deleteInfo.getPeople();
        int deleteId = deleteInfo.getId();
//        //删除表2-1信息
//        int info1 = deleteTable21(pointCode,people,deleteId);
//        //删除表2-2信息
//        int info2 = deleteTable22(pointCode,people,deleteId);
//        //删除其他表中含监测点的信息
        //删除监测点信息
        List<String> tableName = new ArrayList<>();
        tableName.add("crop_01_01_table_02_01");
        tableName.add("crop_01_01_table_02_02");
        tableName.add("crop_01_01_table_04_01");
        tableName.add("crop_01_01_table_04_02");
        tableName.add("crop_01_01_table_04_03");
        tableName.add("crop_01_01_table_04_04");
        tableName.add("crop_01_01_table_05_01");
        tableName.add("crop_01_01_table_06_01");
        tableName.add("crop_01_01_table_06_02");
        tableName.add("crop_01_01_table_06_03");
        tableName.add("crop_01_01_table_06_04");
        tableName.add("crop_01_01_table_06_05");
        tableName.add("crop_01_01_table_09_07");
        tableName.add("crop_01_01_table_10_01");
        int info1 = 1;
        int info2 = 1;
        for(int i=0;i<2;i++){
            //表2对应监测点代码为field_1
            String name = tableName.get(i);
            tableMapper.deleteTable1(name,pointCode,people,deleteId,year);
            System.out.println(name);
        }
        for(int i=2;i<tableName.size();i++){
            //二类表对应监测点代码为field_2
            String name = tableName.get(i);
            tableMapper.deleteTable2(name,pointCode,people,deleteId,year);
            System.out.println(name);
        }
        //tableMapper.deleteTable3(pointCode,people,deleteId,year);
        return 1;
    }

    //处理各监测点上传与未上传表
    @Override
    public List<HashMap<String,Object>> getSubmitData(String year) {
        List<sum_select_list> countyName = tableMapper.getCountyName();
        List<String> singleCounty = new ArrayList<>();
        List<String> doubleCounty = new ArrayList<>();
        for(int i=0;i<countyName.size();i++){
            if(countyName.get(i).getRice_type().equals("one")){
                singleCounty.add(countyName.get(i).getList());
            }else{
                doubleCounty.add(countyName.get(i).getList());
            }
        };
        System.out.println(doubleCounty.size());
        System.out.println(singleCounty.size());
        //一季稻表
        List<crop_01_01_table_01_01> one11 = tableMapper.getRice11(year);
        List<crop_01_01_table_01_02> one12 = tableMapper.getRice12(year);
        List<crop_01_01_table_03_01> one31 = tableMapper.getRice31(year);
        List<crop_01_01_table_03_02> one32 = tableMapper.getRice32(year);
        List<crop_01_01_table_03_03> one33 = tableMapper.getRice33(year);
        List<crop_01_01_table_04_01> one41 = tableMapper.getRice41(year);
        List<crop_01_01_table_04_02> one42 = tableMapper.getRice42(year);
        List<crop_01_01_table_04_03> one43 = tableMapper.getRice43(year);
        List<crop_01_01_table_04_04> one44 = tableMapper.getRice44(year);
        List<crop_01_01_table_05_01> one51 = tableMapper.getRice51(year);
        List<crop_01_01_table_06_01> one61 = tableMapper.getRice61(year);
        List<crop_01_01_table_06_02> one62 = tableMapper.getRice62(year);
        List<crop_01_01_table_06_03> one63 = tableMapper.getRice63(year);
        List<crop_01_01_table_06_04> one64 = tableMapper.getRice64(year);
        List<crop_01_01_table_06_05> one65 = tableMapper.getRice65(year);
        List<crop_01_01_table_07_01> one71 = tableMapper.getRice71(year);
        List<crop_01_01_table_07_02> one72 = tableMapper.getRice72(year);
        List<crop_01_01_table_07_03> one73 = tableMapper.getRice73(year);
        List<crop_01_01_table_08_01> one81 = tableMapper.getRice81(year);
        List<crop_01_01_table_09_03> one93 = tableMapper.getRice93(year);
        List<crop_01_01_table_09_04> one94 = tableMapper.getRice94(year);
        List<crop_01_01_table_09_05> one95 = tableMapper.getRice95(year);
        List<crop_01_01_table_09_06> one96 = tableMapper.getRice96(year);
        List<crop_01_01_table_09_07> one97 = tableMapper.getRice97(year);
        List<crop_01_01_table_10_01> one101 = tableMapper.getRice101(year);

        //双季稻表
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
        System.out.println(two11.size());
        //存各县上报表个数与未上报表个数
        ArrayList<HashMap<String, Object>> tableNum = new ArrayList<HashMap<String, Object>>();
        //计算县对应上报与未上报表个数
        for(int i=0;i<singleCounty.size();i++){
            int num = 0;
            for(int j=0;j<one11.size();j++){
                if(singleCounty.get(i).equals(one11.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one12.size();j++){
                if(singleCounty.get(i).equals(one12.get(j).getCounty())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one31.size();j++){
                if(singleCounty.get(i).equals(one31.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one32.size();j++){
                if(singleCounty.get(i).equals(one32.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one33.size();j++){
                if(singleCounty.get(i).equals(one33.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one41.size();j++){
                if(singleCounty.get(i).equals(one41.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one42.size();j++){
                if(singleCounty.get(i).equals(one42.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one43.size();j++){
                if(singleCounty.get(i).equals(one43.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one44.size();j++){
                if(singleCounty.get(i).equals(one44.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one51.size();j++){
                if(singleCounty.get(i).equals(one51.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one61.size();j++){
                if(singleCounty.get(i).equals(one61.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one62.size();j++){
                if(singleCounty.get(i).equals(one62.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one63.size();j++){
                if(singleCounty.get(i).equals(one63.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one64.size();j++){
                if(singleCounty.get(i).equals(one64.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one65.size();j++){
                if(singleCounty.get(i).equals(one65.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one71.size();j++){
                if(singleCounty.get(i).equals(one71.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one72.size();j++){
                if(singleCounty.get(i).equals(one72.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one73.size();j++){
                if(singleCounty.get(i).equals(one73.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one81.size();j++){
                if(singleCounty.get(i).equals(one81.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one93.size();j++){
                if(singleCounty.get(i).equals(one93.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one94.size();j++){
                if(singleCounty.get(i).equals(one94.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one95.size();j++){
                if(singleCounty.get(i).equals(one95.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one96.size();j++){
                if(singleCounty.get(i).equals(one96.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one97.size();j++){
                if(singleCounty.get(i).equals(one97.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            for(int j=0;j<one101.size();j++){
                if(singleCounty.get(i).equals(one101.get(j).getField_1())){
                    num ++;
                    break;
                }
            }
            int other = 25 - num;
            HashMap<String, Object> map = new HashMap<>();
            map.put("county_name",singleCounty.get(i));
            map.put("sumbitNum",num);
            map.put("nosumbitNum",other);
            tableNum.add(map);
        };
        //双季稻
        for(int i=0;i<doubleCounty.size();i++){
            int num=0;
            for(int j=0;j<two11.size();j++){
                if(doubleCounty.get(i).equals(two11.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two12.size();j++){
                if(doubleCounty.get(i).equals(two12.get(j).getCounty())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two31.size();j++){
                if(doubleCounty.get(i).equals(two31.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two32.size();j++){
                if(doubleCounty.get(i).equals(two32.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two33.size();j++){
                if(doubleCounty.get(i).equals(two33.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two34.size();j++){
                if(doubleCounty.get(i).equals(two34.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two34.size();j++){
                if(doubleCounty.get(i).equals(two34.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two35.size();j++){
                if(doubleCounty.get(i).equals(two35.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two36.size();j++){
                if(doubleCounty.get(i).equals(two36.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two41.size();j++){
                if(doubleCounty.get(i).equals(two41.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two42.size();j++){
                if(doubleCounty.get(i).equals(two42.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two43.size();j++){
                if(doubleCounty.get(i).equals(two43.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two44.size();j++){
                if(doubleCounty.get(i).equals(two44.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two51.size();j++){
                if(doubleCounty.get(i).equals(two51.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two61.size();j++){
                if(doubleCounty.get(i).equals(two61.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two62.size();j++){
                if(doubleCounty.get(i).equals(two62.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two63.size();j++){
                if(doubleCounty.get(i).equals(two63.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two64.size();j++){
                if(doubleCounty.get(i).equals(two64.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two65.size();j++){
                if(doubleCounty.get(i).equals(two65.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two71.size();j++){
                if(doubleCounty.get(i).equals(two71.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two72.size();j++){
                if(doubleCounty.get(i).equals(two72.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two73.size();j++){
                if(doubleCounty.get(i).equals(two73.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two81.size();j++){
                if(doubleCounty.get(i).equals(two81.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two82.size();j++){
                if(doubleCounty.get(i).equals(two82.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two93.size();j++){
                if(doubleCounty.get(i).equals(two93.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two94.size();j++){
                if(doubleCounty.get(i).equals(two94.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two95.size();j++){
                if(doubleCounty.get(i).equals(two95.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two96.size();j++){
                if(doubleCounty.get(i).equals(two96.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two97.size();j++){
                if(doubleCounty.get(i).equals(two97.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two101.size();j++){
                if(doubleCounty.get(i).equals(two101.get(j).getField_1())){
                    num++;
                    break;
                }
            }
            for(int j=0;j<two102.size();j++){
                if(doubleCounty.get(i).equals(two102.get(j).getField_1())){
                    num++;
                    break;
                }
            }

            int other = 31 - num;
            HashMap<String, Object> map = new HashMap<>();
            map.put("county_name",doubleCounty.get(i));
            map.put("sumbitNum",num);
            map.put("nosumbitNum",other);
            tableNum.add(map);
        }
        return tableNum;
    }

    //获取各个监测县上报与未上报具体表信息
    @Override
    public List<List<HashMap<String,Object>>> getSubmitInfo(String countyName,String year) {
        //一季稻表
        List<crop_01_01_table_01_01> one11 = tableMapper.getTable11(countyName,year);
        List<crop_01_01_table_01_02> one12 = tableMapper.getTable12(countyName,year);
        List<crop_01_01_table_03_01> one31 = tableMapper.getTable31(countyName,year);
        List<crop_01_01_table_03_02> one32 = tableMapper.getTable32(countyName,year);
        List<crop_01_01_table_03_03> one33 = tableMapper.getTable33(countyName,year);
        List<crop_01_01_table_04_01> one41 = tableMapper.getTable41(countyName,year);
        List<crop_01_01_table_04_02> one42 = tableMapper.getTable42(countyName,year);
        List<crop_01_01_table_04_03> one43 = tableMapper.getTable43(countyName,year);
        List<crop_01_01_table_04_04> one44 = tableMapper.getTable44(countyName,year);
        List<crop_01_01_table_05_01> one51 = tableMapper.getTable51(countyName,year);
        List<crop_01_01_table_06_01> one61 = tableMapper.getTable61(countyName,year);
        List<crop_01_01_table_06_02> one62 = tableMapper.getTable62(countyName,year);
        List<crop_01_01_table_06_03> one63 = tableMapper.getTable63(countyName,year);
        List<crop_01_01_table_06_04> one64 = tableMapper.getTable64(countyName,year);
        List<crop_01_01_table_06_05> one65 = tableMapper.getTable65(countyName,year);
        List<crop_01_01_table_07_01> one71 = tableMapper.getTable71(countyName,year);
        List<crop_01_01_table_07_02> one72 = tableMapper.getTable72(countyName,year);
        List<crop_01_01_table_07_03> one73 = tableMapper.getTable73(countyName,year);
        List<crop_01_01_table_08_01> one81 = tableMapper.getTable81(countyName,year);
        List<crop_01_01_table_09_03> one93 = tableMapper.getTable93(countyName,year);
        List<crop_01_01_table_09_04> one94 = tableMapper.getTable94(countyName,year);
        List<crop_01_01_table_09_05> one95 = tableMapper.getTable95(countyName,year);
        List<crop_01_01_table_09_06> one96 = tableMapper.getTable96(countyName,year);
        List<crop_01_01_table_09_07> one97 = tableMapper.getTable97(countyName,year);
        List<crop_01_01_table_10_01> one101 = tableMapper.getTable101(countyName,year);
        ArrayList<String> sumbitList = new ArrayList<>();
        ArrayList<String> noSumbitList = new ArrayList<>();
        if(one11.size()!=0){
            sumbitList.add("监测县(市、区)水稻栽插概况调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻栽插概况调查表");
        }
        if(one12.size()!=0){
            sumbitList.add("监测县(市、区)水稻生产主推品种调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻生产主推品种调查表");
        }
        if(one31.size()!=0){
            sumbitList.add("监测县(市、区)水稻播栽进度调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻播栽进度调查表");
        }
        if(one32.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽稻）");
        }
        if(one33.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播稻）");
        }
        if(one41.size()!=0){
            sumbitList.add("监测县(市、区)中稻生育进度调查表（移栽稻）");
        }else{
            noSumbitList.add("监测县(市、区)中稻生育进度调查表（移栽稻）");
        }
        if(one42.size()!=0){
            sumbitList.add("监测县(市、区)直播稻生育进度调查表（直播稻）");
        }else{
            noSumbitList.add("监测县(市、区)直播稻生育进度调查表（直播稻）");
        }
        if(one43.size()!=0){
            sumbitList.add("水稻监测点茎蘖数叶面积构成表");
        }else{
            noSumbitList.add("水稻监测点茎蘖数叶面积构成表");
        }
        if(one44.size()!=0){
            sumbitList.add("水稻监测点苗情生育期统计表");
        }else{
            noSumbitList.add("水稻监测点苗情生育期统计表");
        }
        if(one51.size()!=0){
            sumbitList.add("叶片含氮测定仪导出数据");
        }else{
            noSumbitList.add("叶片含氮测定仪导出数据");
        }
        if(one61.size()!=0){
            sumbitList.add("水稻监测点水分管理记录表");
        }else{
            noSumbitList.add("水稻监测点水分管理记录表");
        }
        if(one62.size()!=0){
            sumbitList.add("水稻监测点化除管理记录表");
        }else{
            noSumbitList.add("水稻监测点化除管理记录表");
        }
        if(one63.size()!=0){
            sumbitList.add("水稻监测点病虫害防治记录表");
        }else{
            noSumbitList.add("水稻监测点病虫害防治记录表");
        }
        if(one64.size()!=0){
            sumbitList.add("水稻监测点施肥管理记录表");
        }else{
            noSumbitList.add("水稻监测点施肥管理记录表");
        }
        if(one65.size()!=0){
            sumbitList.add("水稻监测点化学调节剂使用记录表");
        }else{
            noSumbitList.add("水稻监测点化学调节剂使用记录表");
        }
        if(one71.size()!=0){
            sumbitList.add("监测县(市、区)水稻单季稻苗情监测预测产表");
        }else{
            noSumbitList.add("监测县(市、区)水稻单季稻苗情监测预测产表");
        }
        if(one72.size()!=0){
            sumbitList.add("监测县(市、区)水稻单季稻收获进度调查");
        }else{
            noSumbitList.add("监测县(市、区)水稻单季稻收获进度调查");
        }
        if(one73.size()!=0){
            sumbitList.add("监测县(市、区)水稻单季稻测产表");
        }else{
            noSumbitList.add("监测县(市、区)水稻单季稻测产表");
        }
        if(one81.size()!=0){
            sumbitList.add("监测县(市、区)水稻生产成本效益统计表");
        }else{
            noSumbitList.add("监测县(市、区)水稻生产成本效益统计表");
        }
        if(one93.size()!=0){
            sumbitList.add("监测县(市、区)水稻倒伏灾害表");
        }else{
            noSumbitList.add("监测县(市、区)水稻倒伏灾害表");
        }
        if(one94.size()!=0){
            sumbitList.add("监测县(市、区)水稻低温冷害表");
        }else{
            noSumbitList.add("监测县(市、区)水稻低温冷害表");
        }
        if(one95.size()!=0){
            sumbitList.add("监测县(市、区)水稻高温热害表");
        }else{
            noSumbitList.add("监测县(市、区)水稻高温热害表");
        }
        if(one96.size()!=0){
            sumbitList.add("监测县(市、区)水稻涝害调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻涝害调查表");
        }
        if(one97.size()!=0){
            sumbitList.add("水稻监测点洪涝灾害表");
        }else{
            noSumbitList.add("水稻监测点洪涝灾害表");
        }
        if(one101.size()!=0){
            sumbitList.add("水稻监测点灌浆速率调查表");
        }else{
            noSumbitList.add("水稻监测点灌浆速率调查表");
        };
        List<HashMap<String,Object>> subInfo = new ArrayList<>();
        for(int i=0;i<sumbitList.size();){
            HashMap<String, Object> map = new HashMap<>();
            if(sumbitList.size()%2 != 1){
                map.put("sumbitTable",sumbitList.get(i));
                map.put("sumbitTable2",sumbitList.get(i+1));
                subInfo.add(map);
            }else{
                if(i != (sumbitList.size()-1)){
                    map.put("sumbitTable",sumbitList.get(i));
                    map.put("sumbitTable2",sumbitList.get(i+1));
                    subInfo.add(map);
                }else{
                    map.put("sumbitTable",sumbitList.get(i));
                    subInfo.add(map);
                }
            };
            i=i+2;
        }
        List<HashMap<String,Object>> noSubInfo = new ArrayList<>();
        for(int i=0;i<noSumbitList.size();){
            HashMap<String, Object> map = new HashMap<>();
            if(noSumbitList.size()%2 != 1){
                map.put("nosumbitTable",noSumbitList.get(i));
                map.put("nosumbitTable2",noSumbitList.get(i+1));
                noSubInfo.add(map);
            }else{
                if(i != (noSumbitList.size()-1)){
                    map.put("nosumbitTable",noSumbitList.get(i));
                    map.put("nosumbitTable2",noSumbitList.get(i+1));
                    noSubInfo.add(map);
                }else{
                    map.put("nosumbitTable",noSumbitList.get(i));
                    noSubInfo.add(map);
                }
            };
            i=i+2;
        }


        List<List<HashMap<String,Object>>> tableNum = new ArrayList<List<HashMap<String,Object>>>();
        tableNum.add(subInfo);
        tableNum.add(noSubInfo);

        return tableNum;
    }

    //双季稻
    @Override
    public List<List<HashMap<String,Object>>> getSubmitTwoInfo(String countyName,String year) {
        //双季稻表
        List<crop_01_02_table_01_01> two11 = tableTwoMapper.getTableTwo11(countyName,year);
        List<crop_01_02_table_01_02> two12 = tableTwoMapper.getTableTwo12(countyName,year);
        List<crop_01_02_table_03_01> two31 = tableTwoMapper.getTableTwo31(countyName,year);
        List<crop_01_02_table_03_02> two32 = tableTwoMapper.getTableTwo32(countyName,year);
        List<crop_01_02_table_03_03> two33 = tableTwoMapper.getTableTwo33(countyName,year);
        List<crop_01_02_table_03_04> two34 = tableTwoMapper.getTableTwo34(countyName,year);
        List<crop_01_02_table_03_05> two35 = tableTwoMapper.getTableTwo35(countyName,year);
        List<crop_01_02_table_03_06> two36 = tableTwoMapper.getTableTwo36(countyName,year);
        List<crop_01_02_table_04_01> two41 = tableTwoMapper.getTableTwo41(countyName,year);
        List<crop_01_02_table_04_02> two42 = tableTwoMapper.getTableTwo42(countyName,year);
        List<crop_01_02_table_04_03> two43 = tableTwoMapper.getTableTwo43(countyName,year);
        List<crop_01_02_table_04_04> two44 = tableTwoMapper.getTableTwo44(countyName,year);
        List<crop_01_02_table_05_01> two51 = tableTwoMapper.getTableTwo51(countyName,year);
        List<crop_01_02_table_06_01> two61 = tableTwoMapper.getTableTwo61(countyName,year);
        List<crop_01_02_table_06_02> two62 = tableTwoMapper.getTableTwo62(countyName,year);
        List<crop_01_02_table_06_03> two63 = tableTwoMapper.getTableTwo63(countyName,year);
        List<crop_01_02_table_06_04> two64 = tableTwoMapper.getTableTwo64(countyName,year);
        List<crop_01_02_table_06_05> two65 = tableTwoMapper.getTableTwo65(countyName,year);
        List<crop_01_02_table_07_01> two71 = tableTwoMapper.getTableTwo71(countyName,year);
        List<crop_01_02_table_07_02> two72 = tableTwoMapper.getTableTwo72(countyName,year);
        List<crop_01_02_table_07_03> two73 = tableTwoMapper.getTableTwo73(countyName,year);
        List<crop_01_02_table_07_04> two74 = tableTwoMapper.getTableTwo74(countyName,year);
        List<crop_01_02_table_08_01> two81 = tableTwoMapper.getTableTwo81(countyName,year);
        List<crop_01_02_table_08_02> two82 = tableTwoMapper.getTableTwo82(countyName,year);
        List<crop_01_02_table_09_03> two93 = tableTwoMapper.getTableTwo93(countyName,year);
        List<crop_01_02_table_09_04> two94 = tableTwoMapper.getTableTwo94(countyName,year);
        List<crop_01_02_table_09_05> two95 = tableTwoMapper.getTableTwo95(countyName,year);
        List<crop_01_02_table_09_06> two96 = tableTwoMapper.getTableTwo96(countyName,year);
        List<crop_01_02_table_09_07> two97 = tableTwoMapper.getTableTwo97(countyName,year);
        List<crop_01_02_table_10_01> two101 = tableTwoMapper.getTableTwo101(countyName,year);
        List<crop_01_02_table_10_02> two102 = tableTwoMapper.getTableTwo102(countyName,year);
        ArrayList<String> sumbitList = new ArrayList<>();
        ArrayList<String> noSumbitList = new ArrayList<>();
        if(two11.size()!=0){
            sumbitList.add("监测县(市、区)水稻栽插概况调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻栽插概况调查表");
        };
        if(two12.size()!=0){
            sumbitList.add("监测县(市、区)水稻生产主推品种调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻生产主推品种调查表");
        };
        if(two31.size()!=0){
            sumbitList.add("监测县(市、区)水稻播栽进度调查表（早稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻播栽进度调查表（早稻）");
        };
        if(two32.size()!=0){
            sumbitList.add("监测县(市、区)水稻播栽进度调查表（晚稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻播栽进度调查表（晚稻）");
        };
        if(two33.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽早稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽早稻）");
        };
        if(two34.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播早稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播早稻）");
        };
        if(two35.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽晚稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（移栽晚稻）");
        };
        if(two36.size()!=0){
            sumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播晚稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻苗情评价动态列表（直播晚稻）");
        };
        if(two41.size()!=0){
            sumbitList.add("监测县(市、区)双季稻生育进度调查表（移栽稻）");
        }else{
            noSumbitList.add("监测县(市、区)双季稻生育进度调查表（移栽稻）");
        };
        if(two42.size()!=0){
            sumbitList.add("监测县(市、区)双季稻生育进度调查表（直播稻）");
        }else{
            noSumbitList.add("监测县(市、区)双季稻生育进度调查表（直播稻）");
        };
        if(two43.size()!=0){
            sumbitList.add("水稻监测点茎蘖数叶面积构成表");
        }else{
            noSumbitList.add("水稻监测点茎蘖数叶面积构成表");
        };
        if(two44.size()!=0){
            sumbitList.add("水稻监测点苗情生育期统计表");
        }else{
            noSumbitList.add("水稻监测点苗情生育期统计表");
        };
        if(two51.size()!=0){
            sumbitList.add("叶片含氮测定仪导出数据");
        }else{
            noSumbitList.add("叶片含氮测定仪导出数据");
        };
        if(two61.size()!=0){
            sumbitList.add("水稻监测点水分管理记录表");
        }else{
            noSumbitList.add("水稻监测点水分管理记录表");
        };
        if(two62.size()!=0){
            sumbitList.add("水稻监测点化除管理记录表");
        }else{
            noSumbitList.add("水稻监测点化除管理记录表");
        };
        if(two63.size()!=0){
            sumbitList.add("水稻监测点病虫害防治记录表");
        }else{
            noSumbitList.add("水稻监测点病虫害防治记录表");
        };
        if(two64.size()!=0){
            sumbitList.add("水稻监测点施肥管理记录表");
        }else{
            noSumbitList.add("水稻监测点施肥管理记录表");
        };
        if(two65.size()!=0){
            sumbitList.add("水稻监测点化学调节剂使用记录表");
        }else{
            noSumbitList.add("水稻监测点化学调节剂使用记录表");
        };
        if(two71.size()!=0){
            sumbitList.add("监测县(市、区)水稻双季稻苗情监测预测产表");
        }else{
            noSumbitList.add("监测县(市、区)水稻双季稻苗情监测预测产表");
        };
        if(two72.size()!=0){
            sumbitList.add("监测县(市、区)水稻双季稻收获进度调查（早稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻双季稻收获进度调查（早稻）");
        };
        if(two73.size()!=0){
            sumbitList.add("监测县(市、区)水稻双季稻收获进度调查（晚稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻双季稻收获进度调查（晚稻）");
        };
        if(two74.size()!=0){
            sumbitList.add("监测县(市、区)水稻双季稻测产表");
        }else{
            noSumbitList.add("监测县(市、区)水稻双季稻测产表");
        };
        if(two81.size()!=0){
            sumbitList.add("监测县(市、区)水稻生产成本效益统计表（早稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻生产成本效益统计表（早稻）");
        };
        if(two82.size()!=0){
            sumbitList.add("监测县(市、区)水稻生产成本效益统计表（晚稻）");
        }else{
            noSumbitList.add("监测县(市、区)水稻生产成本效益统计表（晚稻）");
        };
        if(two93.size()!=0){
            sumbitList.add("监测县(市、区)水稻倒伏灾害表");
        }else{
            noSumbitList.add("监测县(市、区)水稻倒伏灾害表");
        };
        if(two94.size()!=0){
            sumbitList.add("监测县(市、区)水稻低温冷害表");
        }else{
            noSumbitList.add("监测县(市、区)水稻低温冷害表");
        };
        if(two95.size()!=0){
            sumbitList.add("监测县(市、区)高温热害表");
        }else{
            noSumbitList.add("监测县(市、区)高温热害表");
        };
        if(two96.size()!=0){
            sumbitList.add("监测县(市、区)水稻涝害调查表");
        }else{
            noSumbitList.add("监测县(市、区)水稻涝害调查表");
        };
        if(two97.size()!=0){
            sumbitList.add("水稻监测点洪涝灾害表");
        }else{
            noSumbitList.add("水稻监测点洪涝灾害表");
        };
        if(two101.size()!=0){
            sumbitList.add("水稻监测点灌浆速率调查表（早稻）");
        }else{
            noSumbitList.add("水稻监测点灌浆速率调查表（早稻）");
        };
        if(two102.size()!=0){
            sumbitList.add("水稻监测点灌浆速率调查表（晚稻）");
        }else{
            noSumbitList.add("水稻监测点灌浆速率调查表（晚稻）");
        };

        List<HashMap<String,Object>> subInfo = new ArrayList<>();
        for(int i=0;i<sumbitList.size();){
            HashMap<String, Object> map = new HashMap<>();
            if(sumbitList.size()%2 != 1){
                map.put("sumbitTable",sumbitList.get(i));
                map.put("sumbitTable2",sumbitList.get(i+1));
                subInfo.add(map);
            }else{
                if(i != (sumbitList.size()-1)){
                    map.put("sumbitTable",sumbitList.get(i));
                    map.put("sumbitTable2",sumbitList.get(i+1));
                    subInfo.add(map);
                }else{
                    map.put("sumbitTable",sumbitList.get(i));
                    subInfo.add(map);
                }
            };
            i=i+2;
        }
        List<HashMap<String,Object>> noSubInfo = new ArrayList<>();
        for(int i=0;i<noSumbitList.size();){
            HashMap<String, Object> map = new HashMap<>();
            if(noSumbitList.size()%2 != 1){
                map.put("nosumbitTable",noSumbitList.get(i));
                map.put("nosumbitTable2",noSumbitList.get(i+1));
                noSubInfo.add(map);
            }else{
                if(i != (noSumbitList.size()-1)){
                    map.put("nosumbitTable",noSumbitList.get(i));
                    map.put("nosumbitTable2",noSumbitList.get(i+1));
                    noSubInfo.add(map);
                }else{
                    map.put("nosumbitTable",noSumbitList.get(i));
                    noSubInfo.add(map);
                }
            };
            i=i+2;
        }

        List<List<HashMap<String,Object>>> tableNum = new ArrayList<List<HashMap<String,Object>>>();
        tableNum.add(subInfo);
        tableNum.add(noSubInfo);
        return tableNum;
    }

//    增加单季稻监测点信息查看部分
    @Override
    public List<crop_01_01_table_04_01> getRiceTable41_Message(String area,String areaType, String year) {
        return tableMapper.getRiceTable41_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_04_03> getRiceTable43_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable43_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_04_04> getRiceTable44_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable44_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_06_01> getRiceTable61_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable61_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_06_02> getRiceTable62_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable62_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_06_03> getRiceTable63_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable63_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_06_04> getRiceTable64_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable64_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_06_05> getRiceTable65_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable65_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_09_07> getRiceTable97_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable97_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_10_01> getRiceTable101_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable101_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_05_01> getRiceTable51_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable51_Message(area,areaType,year);
    }
    @Override
    public List<crop_01_01_table_08_01> getRiceTable81_Message(String area, String areaType,String year) {
        return tableMapper.getRiceTable81_Message(area,areaType,year);
    }


}
