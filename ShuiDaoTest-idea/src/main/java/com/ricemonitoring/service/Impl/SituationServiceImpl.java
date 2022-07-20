package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.SituationMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.SituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class SituationServiceImpl implements SituationService {
    @Autowired
    SituationMapper situationMapper;
    //分页数据获取


    //3-2表数据对比分析
    @Override
    public Map<String, Object> dataComparison_03_02(String userId, int startYear, int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_01_table_03_02> items_03_02 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_01_table_03_02 item =  situationMapper.getData_03_02(userId,i);
            if (item!=null){
                items_03_02.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_01_table_03_02 item : items_03_02) {
            for (int i = 4; i < 12; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //表3-3数据对比分析
    @Override
    public Map<String, Object> dataComparison_03_03(String userId, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_01_table_03_03> items_03_03 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_01_table_03_03 item =  situationMapper.getData_03_03(userId,i);
            if (item!=null){
                items_03_03.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_01_table_03_03 item : items_03_03) {
            for (int i = 4; i < 13; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //查询该用户下所有监测点信息
    @Override
    public ArrayList<crop_01_02_table_02_01> getCurrentAccountMonitorInfo(String account){
        return situationMapper.getCurrentAccountMonitorInfo(account);
    }

    //二季稻：表3-3数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_03_03(String userId, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_03_03> items_03_03 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_03_03 item =  situationMapper.getData_two_03_03(userId,i);
            if (item!=null){
                items_03_03.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_02_table_03_03 item : items_03_03) {
            for (int i = 5; i < 12; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //二季稻：表3-4数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_03_04(String userId, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_03_04> items_03_04 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_03_04 item =  situationMapper.getData_two_03_04(userId,i);
            if (item!=null){
                items_03_04.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_02_table_03_04 item : items_03_04) {
            for (int i = 5; i < 14; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //二季稻：表3-5数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_03_05(String userId, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_03_05> items_03_05 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_03_05 item =  situationMapper.getData_two_03_05(userId,i);
            if (item!=null){
                items_03_05.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_02_table_03_05 item : items_03_05) {
            for (int i = 4; i < 11; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //二季稻：表3-5数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_03_06(String userId, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_03_06> items_03_06 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_03_06 item =  situationMapper.getData_two_03_06(userId,i);
            if (item!=null){
                items_03_06.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> datas_3 = new ArrayList<>();
        for (crop_01_02_table_03_06 item : items_03_06) {
            for (int i = 5; i < 14; i++) {
                //一类苗数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //二类苗数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3_1");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
                //三类苗数据
                Method m3 = item.getClass().getMethod("getField_"+i+"_4_1");
                String invoke3 = (String) m3.invoke(item);
                if (invoke3==null||invoke3.equals("")){
                    datas_3.add("0.00");
                }else{
                    datas_3.add(df.format(Double.valueOf(invoke3)));
                }

            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("datas_3",datas_3);
        return map;
    }

    //表4-1表数据对比分析
    @Override
    public Map<String, Object> dataComparison_04_01(String userId, int startYear,int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_01_table_04_01> items_04_01 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 16; i++) {
            sqlFields.add("sum(field_"+i+"_3) as field_"+i+"_3,sum(field_"+i+"_4) as field_"+i+"_4");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_01_table_04_01 item =  situationMapper.getData_04_01(userId,i,point,sqlFields);
            if (item!=null){
                items_04_01.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_01_table_04_01 item : items_04_01) {
            for (int i = 9; i < 16; i++) {
                //叶龄数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //茎蘖数数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_4");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    //二季稻：表4-1表数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_04_01(String userId, int startYear,int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_04_01> items_04_01 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 16; i++) {
            sqlFields.add("sum(field_"+i+"_3) as field_"+i+"_3,sum(field_"+i+"_4) as field_"+i+"_4");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_04_01 item =  situationMapper.getData_two_04_01(userId,i,point,sqlFields);
            if (item!=null){
                items_04_01.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_02_table_04_01 item : items_04_01) {
            for (int i = 9; i < 16; i++) {
                //叶龄数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //茎蘖数数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_4");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    //表4-2表数据对比分析
    @Override
    public Map<String, Object> dataComparison_04_02(String userId, int startYear,int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_01_table_04_02> items_04_02 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 17; i++) {
            sqlFields.add("sum(field_"+i+"_3) as field_"+i+"_3,sum(field_"+i+"_4) as field_"+i+"_4");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_01_table_04_02 item =  situationMapper.getData_04_02(userId,i,point,sqlFields);
            if (item!=null){
                items_04_02.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_01_table_04_02 item : items_04_02) {
            for (int i = 9; i < 17; i++) {
                //叶龄数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //茎蘖数数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_4");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    //二季稻：表4-2表数据对比分析
    @Override
    public Map<String, Object> dataComparison_two_04_02(String userId, int startYear,int endYear,String point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_04_02> items_04_02 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 17; i++) {
            sqlFields.add("sum(field_"+i+"_3) as field_"+i+"_3,sum(field_"+i+"_4) as field_"+i+"_4");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_04_02 item =  situationMapper.getData_two_04_02(userId,i,point,sqlFields);
            if (item!=null){
                items_04_02.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_02_table_04_02 item : items_04_02) {
            for (int i = 9; i < 17; i++) {
                //叶龄数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //茎蘖数数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_4");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    //表10-1表数据对比分析
    @Override
    public Map<String, Object> dataComparison_10_01(String userId, String point, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_01_table_10_01> items_10_01 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 7; i < 12; i++) {
            sqlFields.add("sum(field_"+i+"_2) as field_"+i+"_2,sum(field_"+i+"_3) as field_"+i+"_3");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_01_table_10_01 item =  situationMapper.getData_10_01(userId,point,i,sqlFields);
            if (item!=null){
                items_10_01.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_01_table_10_01 item : items_10_01) {
            for (int i = 7; i < 12; i++) {
                //千粒重数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //灌浆速率数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }
    //表10-1表数据对比分析
    @Override
    public Map<String, Object> dataTwoComparison_10_01(String userId, String point, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_10_01> items_10_01 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 8; i < 13; i++) {
            sqlFields.add("sum(field_"+i+"_2) as field_"+i+"_2,sum(field_"+i+"_3) as field_"+i+"_3");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_10_01 item =  situationMapper.getTwoData_10_01(userId,point,i,sqlFields);
            if (item!=null){
                items_10_01.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_02_table_10_01 item : items_10_01) {
            for (int i = 8; i < 13; i++) {
                //千粒重数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //灌浆速率数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }
    //表10-1表数据对比分析
    @Override
    public Map<String, Object> dataTwoComparison_10_02(String userId, String point, int startYear,int endYear) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //获取数据
        List<crop_01_02_table_10_02> items_10_01 = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
        //定义要查询的sql语句字段
        List<String> sqlFields = new ArrayList<>();
        for (int i = 8; i < 13; i++) {
            sqlFields.add("sum(field_"+i+"_2) as field_"+i+"_2,sum(field_"+i+"_3) as field_"+i+"_3");
        }
        for (int i=startYear;i<=endYear;i++) {
            //通过年份以及账户名查出所需的数据
            crop_01_02_table_10_02 item =  situationMapper.getTwoData_10_02(userId,point,i,sqlFields);
            if (item!=null){
                items_10_01.add(item);
                years.add(i);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        for (crop_01_02_table_10_02 item : items_10_01) {
            for (int i = 8; i < 13; i++) {
                //千粒重数据
                Method m1 = item.getClass().getMethod("getField_"+i+"_2");
                String invoke1 = (String) m1.invoke(item);
                if (invoke1==null||invoke1.equals("")){
                    datas_1.add("0.00");
                }else{
                    datas_1.add(df.format(Double.valueOf(invoke1)));
                }
                //灌浆速率数据
                Method m2 = item.getClass().getMethod("getField_"+i+"_3");
                String invoke2 = (String) m2.invoke(item);
                if (invoke2==null||invoke2.equals("")){
                    datas_2.add("0.00");
                }else{
                    datas_2.add(df.format(Double.valueOf(invoke2)));
                }
            }
        }
        //System.out.println(datas_1.size());
        map.put("years",years);
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }


    //查询该用户下的所有表1-2数据
    @Override
    public List<crop_01_01_table_01_02> getUserAllData(String people,String column_id,int flag,int currentYear){
        return situationMapper.getUserAllData(people,column_id,flag,currentYear);
    }

    //查询该用户下的所有表1-2数据，品系名对应
    @Override
    public List<crop_01_01_table_01_02> getByPinZhongName(String people,String pin_zhong_name,String column_id,int flag,int currentYear){
        return situationMapper.getByPinZhongName(people,pin_zhong_name,column_id,flag,currentYear);
    }

    //获取品种信息
    @Override
    public List<table_variety> getPinZhongInfo(String varieties){
        return situationMapper.getPinZhongInfo(varieties);
    }

    //查询该用户下的所有表1-1数据
    @Override
    public List<crop_01_01_table_01_01> getUserAllData01(String people){
        return situationMapper.getUserAllData01(people);
    }

//    //判断该用户是否首次登录表1-2
//    @Override
//    public List<crop_01_01_table_01_02> judgeUserFirstLoginTable2(String UserID){
//        return situationMapper.judgeUserFirstLoginTable2(UserID);
//    }

    //获取下拉框内容
    @Override
    public List<sum_select_list> getSelectContentList(String fieldName,String riceType){
        return situationMapper.getSelectContentList(fieldName,riceType);
    }
    //获取监测点代码
    @Override
    public List<monitoring_point> getMonitorCode(String UserID){
        return situationMapper.getMonitorCode(UserID);
    }

//    //查询该用户所属的县市区和地区类型
//    @Override
//    public List<crop_01_01_table_02_01> checkUserBelong(String people){
//        return situationMapper.checkUserBelong(people);
//    }

    //保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    @Override
    public int getInputDataTable1(List<crop_01_01_table_01_01> obj1){
        return situationMapper.getInputDataTable1(obj1);
    }

    //保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表 数据
    @Override
    public int getInputDataTable2(crop_01_01_table_01_02 obj1){
        return situationMapper.getInputDataTable2(obj1);
    }

    //保存 表3-1   数据
    @Override
    public int getInputDataTable5(List<crop_01_01_table_03_01> obj1){
        return situationMapper.getInputDataTable5(obj1);
    }

    //保存 表3-2   数据
    @Override
    public int getInputDataTable0302(List<crop_01_01_table_03_02> obj1){
        return situationMapper.getInputDataTable0302(obj1);
    }

    //保存 表3-3   数据
    @Override
    public int getInputDataTable0303(List<crop_01_01_table_03_03> obj1){
        return situationMapper.getInputDataTable0303(obj1);
    }

    //保存 表4-1   数据
    @Override
    public int getInputDataTable0401(List<crop_01_01_table_04_01> obj1){
        return situationMapper.getInputDataTable0401(obj1);
    }

    //保存 表4-2   数据
    @Override
    public int getInputDataTable0402(List<crop_01_01_table_04_02> obj1){
        return situationMapper.getInputDataTable0402(obj1);
    }

    //保存 表4-3   数据
    @Override
    public int getInputDataTable0403(List<crop_01_01_table_04_03> obj1){
        return situationMapper.getInputDataTable0403(obj1);
    }

    //保存 表4-4   数据
    @Override
    public int getInputDataTable0404(List<crop_01_01_table_04_04> obj1){
        return situationMapper.getInputDataTable0404(obj1);
    }

    //保存 表8-1   数据
    @Override
    public int getInputDataTable0801(crop_01_01_table_08_01 obj1){
        return situationMapper.getInputDataTable0801(obj1);
    }

    //保存 表2-1  2021年度水稻监测点建档卡 数据
    @Override
    public int getInputDataTable3(crop_01_01_table_02_01 obj1){
        return situationMapper.getInputDataTable3(obj1);
    }

    //保存监测点建档卡数据至表1-1中
    @Override
    public int insertMonitorInfoToTable0101(String area, String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0101(area,areaCate,people,time,currentYear);
    }


    //更新监测点建档卡数据至表1-1中
    @Override
    public int updateMonitorInfoToTable0101(String area, String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0101(area,areaCate,people,time,currentYear);
    }

    //查询表1-1 下该用户所有的数据数
    @Override
    public List<crop_01_01_table_01_01> checkUserAllKnownData1(String UserID,String column_id,int startYear,int endYear){

        return situationMapper.checkUserAllKnownData1(UserID,column_id,startYear,endYear);

    }

    //读取建档卡表2-1内容至表2-2中
    @Override
    public int getMonitorCardContent(String people){
        return situationMapper.getMonitorCardContent(people);
    }

    //读取建档卡内容至表3-2中
    @Override
    public int getMonitorCardContentInto0302(String people){
        return situationMapper.getMonitorCardContentInto0302(people);
    }

    //读取建档卡内容至表3-3中
    @Override
    public int getMonitorCardContentInto0303(String people){
        return situationMapper.getMonitorCardContentInto0303(people);
    }

    //读取建档卡内容至表3-1中
    @Override
    public int getMonitorCardContentInto0301(String people){
        return situationMapper.getMonitorCardContentInto0301(people);
    }

    //保存监测点建档卡数据至表1-2中
    @Override
    public int insertMonitorInfoToTable0102(String area,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0102(area,people,time,currentYear);
    }

    //读取该用户所有表2-2数据
    @Override
    public List<crop_01_01_table_02_02> getUserAllData0202(String people){
        return situationMapper.getUserAllData0202(people);
    }

    //查询该用户所有表2-1的数据
    @Override
    public List<crop_01_01_table_02_01> getUserAllData0201(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllData0201(people,column_id,startYear,endYear);
    }

    //查询该用户所有表2-1的数据
    @Override
    public List<crop_01_02_table_02_01> getUserAllDataTwo0201(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0201(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-1的数据
    @Override
    public List<crop_01_01_table_03_01> getUserAllData0301(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllData0301(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-2的数据
    @Override
    public List<crop_01_01_table_03_02> getUserAllData0302(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllData0302(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-3的数据
    @Override
    public List<crop_01_01_table_03_03> getUserAllData0303(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllData0303(people,column_id,startYear,endYear);
    }

    //查询该用户所有表4-1的数据
    @Override
    public List<crop_01_01_table_04_01> getUserAllData0401(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllData0401(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-2的数据
    @Override
    public List<crop_01_01_table_04_02> getUserAllData0402(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllData0402(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-3的数据
    @Override
    public List<crop_01_01_table_04_03> getUserAllData0403(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllData0403(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-4的数据
    @Override
    public List<crop_01_01_table_04_04> getUserAllData0404(String people,String column_id,String point,int startYear,int endYear){
        return situationMapper.getUserAllData0404(people,column_id,point,startYear,endYear);
    }

    //查询该用户所有表8-1的数据
    @Override
    public List<crop_01_01_table_08_01> getUserAllData0801(String people,String point, String column_id,int startYear,int endYear){
        return situationMapper.getUserAllData0801(people,point,column_id,startYear,endYear);
    }

    //保存监测点建档卡数据至表3-1中
    @Override
    public int insertMonitorInfoToTable0301(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0301(area,areaCate,people,time,currentYear);
    }

    //更新监测点建档卡数据至表3-1中
    @Override
    public int updateMonitorInfoToTable0301(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0301(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-2中
    @Override
    public int insertMonitorInfoToTable0302(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0302(area,areaCate,people,time,currentYear);
    }

    //更新监测点建档卡数据至表3-2中
    @Override
    public int updateMonitorInfoToTable0302(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0302(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-3中
    @Override
    public int insertMonitorInfoToTable0303(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0303(area,areaCate,people,time,currentYear);
    }

    //更新监测点建档卡数据至表3-3中
    @Override
    public int updateMonitorInfoToTable0303(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0303(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-1中
    @Override
    public int insertMonitorInfoToTable0701(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0701(area,areaCate,people,time,currentYear);
    }

    //gengxing 监测点建档卡数据至表7-1中
    @Override
    public int updateMonitorInfoToTable0701(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0701(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-2中
    @Override
    public int insertMonitorInfoToTable0702(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0702(area,areaCate,people,time,currentYear);
    }

    //更新监测点建档卡数据至表7-2中
    @Override
    public int updateMonitorInfoToTable0702(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0702(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-3中
    @Override
    public int insertMonitorInfoToTable0703(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0703(area,areaCate,people,time,currentYear);
    }

    //更新监测点建档卡数据至表7-3中
    @Override
    public int updateMonitorInfoToTable0703(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0703(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表8-1中
    @Override
    public int insertMonitorInfoToTable0801(String area,String MonitorBlock,String areaCate,
                                            String zaiZhiType,
                                            String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.insertMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //更新监测点建档卡数据至表8-1中
    @Override
    public int updateMonitorInfoToTable0801(String area,String MonitorBlock,String areaCate,
                                            String zaiZhiType,
                                            String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.updateMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //读取建档卡内容至表4-1中
    @Override
    public int insertMonitorInfoToTable0401(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表4-1中
    @Override
    public int updateMonitorInfoToTable0401(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表4-2中
    @Override
    public int insertMonitorInfoToTable0402(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表4-2中
    @Override
    public int updateMonitorInfoToTable0402(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表4-3中
    @Override
    public int insertMonitorInfoToTable0403(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time,currentYear);
    }

    //更新建档卡内容至表4-3中
    @Override
    public int updateMonitorInfoToTable0403(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time,currentYear);
    }

    //读取建档卡内容至表4-4中
    @Override
    public int insertMonitorInfoToTable0404(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表4-4中
    @Override
    public int updateMonitorInfoToTable0404(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinXiType,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表5-1中
    @Override
    public int insertMonitorInfoToTable0501(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表5-1中
    @Override
    public int updateMonitorInfoToTable0501(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表6-1中
    @Override
    public int insertMonitorInfoToTable0601(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表6-1中
    @Override
    public int updateMonitorInfoToTable0601(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表6-2中
    @Override
    public int insertMonitorInfoToTable0602(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表6-2中
    @Override
    public int updateMonitorInfoToTable0602(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表6-3中
    @Override
    public int insertMonitorInfoToTable0603(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表6-3中
    @Override
    public int updateMonitorInfoToTable0603(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表6-4中
    @Override
    public int insertMonitorInfoToTable0604(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表6-4中
    @Override
    public int updateMonitorInfoToTable0604(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表6-5中
    @Override
    public int insertMonitorInfoToTable0605(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表6-5中
    @Override
    public int updateMonitorInfoToTable0605(String area,String MonitorBlock,String areaCate,
                                            String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //读取建档卡内容至表10-1中
    @Override
    public int insertMonitorInfoToTable1001(String area,String MonitorBlock,String areaCate,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //更新建档卡内容至表10-1中
    @Override
    public int updateMonitorInfoToTable1001(String area,String MonitorBlock,String areaCate,
                                            String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //查询 表05-01 获取监测点代码
    @Override
    public List<crop_01_01_table_05_01> getAllData0501(String people,String point,String column_id,int startYear,int endYear) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        //System.out.println(people+"--"+point+"--"+column_id+"---"+startYear+"-----"+endYear);
        List<crop_01_01_table_05_01> crop_01_01_table_05_01s = this.situationMapper.getAllData0501(people,point,column_id,currentYear,startYear,endYear);
        //System.out.println(crop_01_01_table_05_01s);
        return crop_01_01_table_05_01s;
    }

    // 更新 表05-01 获取监测点代码
    @Override
    public int getInputDataTable0501(List<crop_01_01_table_05_01> obj1) {
        return situationMapper.getInputDataTable0501(obj1);
    }

    //查询 表01-06-01 获取监测点代码
    @Override
    public List<crop_01_01_table_06_01> getAllData0601(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_01_table_06_01> crop_01_01_table_06_01s = this.situationMapper.getAllData0601(people,point,column_id,startYear,endYear);

        return crop_01_01_table_06_01s;
    }

    //查询 表01-06-02 获取监测点代码
    @Override
    public List<crop_01_01_table_06_02> getAllData0602(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_01_table_06_02> crop_01_01_table_06_02s = this.situationMapper.getAllData0602(people,point,column_id,startYear,endYear);
        return crop_01_01_table_06_02s;
    }

    //查询 表01-06-03 获取监测点代码
    @Override
    public List<crop_01_01_table_06_03> getAllData0603(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_01_table_06_03> crop_01_01_table_06_03s = this.situationMapper.getAllData0603(people,point,column_id,startYear,endYear);
        return crop_01_01_table_06_03s;
    }


    //查询 表01-06-04 获取监测点代码
    @Override
    public List<crop_01_01_table_06_04> getAllData0604(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_06_04> crop_01_01_table_06_04s = this.situationMapper.getAllData0604(people,point,column_id,beginYear,endYear);
        return crop_01_01_table_06_04s;
    }

    //查询 表01-06-05 获取监测点代码
    @Override
    public List<crop_01_01_table_06_05> getAllData0605(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_06_05> crop_01_01_table_06_05s = this.situationMapper.getAllData0605(people,point,column_id,beginYear,endYear);
        return crop_01_01_table_06_05s;
    }

    //查询 表01-07-01
    @Override
    public List<crop_01_01_table_07_01> getAllData0701(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_07_01> crop_01_01_table_07_01s = this.situationMapper.getAllData0701(people,column_id,beginYear,endYear);
        return crop_01_01_table_07_01s;
    }

    //查询 表01-07-02
    @Override
    public List<crop_01_01_table_07_02> getAllData0702(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_07_02> crop_01_01_table_07_02s = this.situationMapper.getAllData0702(people,column_id,beginYear,endYear);
        return crop_01_01_table_07_02s;
    }

    //查询 表01-07-03
    @Override
    public List<crop_01_01_table_07_03> getAllData0703(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_07_03> crop_01_01_table_07_03s = this.situationMapper.getAllData0703(people,column_id,beginYear,endYear);
        return crop_01_01_table_07_03s;
    }


    //查询 表01-09-01
    @Override
    public List<crop_01_01_table_09_01> getAllData0901(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_01> crop_01_01_table_09_01s = this.situationMapper.getAllData0901(people,column_id,beginYear,endYear);
        return crop_01_01_table_09_01s;
    }

    //查询 表01-09-02
    @Override
    public List<crop_01_01_table_09_02> getAllData0902(String people,String year,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_02> crop_01_01_table_09_02s = this.situationMapper.getAllData0902(people,year,column_id,beginYear,endYear);
        return crop_01_01_table_09_02s;
    }

    //查询 表01-09-03
    @Override
    public List<crop_01_01_table_09_03> getAllData0903(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_03> crop_01_01_table_09_03s = this.situationMapper.getAllData0903(people,column_id,beginYear,endYear);
        return crop_01_01_table_09_03s;
    }

    //查询 表01-09-04
    @Override
    public List<crop_01_01_table_09_04> getAllData0904(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_04> crop_01_01_table_09_04s = this.situationMapper.getAllData0904(people,column_id,beginYear,endYear);
        return crop_01_01_table_09_04s;
    }

    //查询 表01-09-05
    @Override
    public List<crop_01_01_table_09_05> getAllData0905(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_05> crop_01_01_table_09_05s = this.situationMapper.getAllData0905(people,column_id,beginYear,endYear);
        return crop_01_01_table_09_05s;
    }

    //查询 表01-09-06
    @Override
    public List<crop_01_01_table_09_06> getAllData0906(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_06> crop_01_01_table_09_06s = this.situationMapper.getAllData0906(people,column_id,beginYear,endYear);
        return crop_01_01_table_09_06s;
    }

    //查询 01-表09-07
    @Override
    public List<crop_01_01_table_09_07> getAllData0907(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_09_07> crop_01_01_table_09_07s = this.situationMapper.getAllData0907(people,point,column_id,beginYear,endYear);
        return crop_01_01_table_09_07s;
    }

    //查询 表01-02-01 自动获取监测点信息
    @Override
    public List<crop_01_01_table_02_01> getAllData0201(String point) {
        return situationMapper.getAllData0201(point);
    }



    //查询 表01-02-01monitoring_point 获取监测点
    @Override
    public List<monitoring_point> getMonitoringPoint(String account) {
        return situationMapper.getMonitoringPoint(account);
    }

    //查询 表01-10-01 获取监测点代码
    @Override
    public List<crop_01_01_table_10_01> getAllData1001(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_01_table_10_01> crop_01_01_table_10_01s = this.situationMapper.getAllData1001(people,point,column_id,beginYear,endYear);
        return crop_01_01_table_10_01s;
    }

    //保存 表01-6-1
    @Override
    public int getInputDataTable0601(List<crop_01_01_table_06_01> obj1) {
        return situationMapper.getInputDataTable0601(obj1);
    }

    //保存 表01-6-2
    @Override
    public int getInputDataTable0602(List<crop_01_01_table_06_02> obj1) {
        return situationMapper.getInputDataTable0602(obj1);
    }

    //保存 表01-6-3
    @Override
    public int getInputDataTable0603(List<crop_01_01_table_06_03> obj1) {
        return situationMapper.getInputDataTable0603(obj1);
    }

    //保存 表01-6-4
    @Override
    public int getInputDataTable0604(List<crop_01_01_table_06_04> obj1) {
        return situationMapper.getInputDataTable0604(obj1);
    }

    //保存 表01-6-5
    @Override
    public int getInputDataTable0605(List<crop_01_01_table_06_05> obj1) {
        return situationMapper.getInputDataTable0605(obj1);
    }




    //保存 表01-07-02
    @Override
    public int getInputDataTable0702(crop_01_01_table_07_02 obj1) {
        return situationMapper.getInputDataTable0702(obj1);
    }

    //保存 表01-07-03
    @Override
    public int getInputDataTable0703(crop_01_01_table_07_03 obj1) {
        return situationMapper.getInputDataTable0703(obj1);
    }

    //保存 表01-07-01
    @Override
    public int getInputDataTable0701(crop_01_01_table_07_01 obj1) {
        return situationMapper.getInputDataTable0701(obj1);
    }

    //保存 表01-09-01
    @Override
    public int getInputDataTable0901(crop_01_01_table_09_01 obj1) {
        return situationMapper.getInputDataTable0901(obj1);
    }

    //保存 表01-09-02
    @Override
    public int getInputDataTable0902(crop_01_01_table_09_02 obj1) {
        return situationMapper.getInputDataTable0902(obj1);
    }

    //保存 表01-09-03
    @Override
    public int getInputDataTable0903(crop_01_01_table_09_03 obj1) {
        return situationMapper.getInputDataTable0903(obj1);
    }

    //保存 表01-09-04
    @Override
    public int getInputDataTable0904(crop_01_01_table_09_04 obj1) {
        return situationMapper.getInputDataTable0904(obj1);
    }

    //保存 表01-09-05
    @Override
    public int getInputDataTable0905(crop_01_01_table_09_05 obj1) {
        return situationMapper.getInputDataTable0905(obj1);
    }

    //保存 表01-09-06
    @Override
    public int getInputDataTable0906(crop_01_01_table_09_06 obj1) {
        return situationMapper.getInputDataTable0906(obj1);
    }


    //保存 表01-09-07
    @Override
    public int getInputDataTable0907(List<crop_01_01_table_09_07> obj1) {
        return situationMapper.getInputDataTable0907(obj1);
    }

    //保存 表01-10-01
    @Override
    public int getInputDataTable1001(List<crop_01_01_table_10_01> obj1) {
        return situationMapper.getInputDataTable1001(obj1);
    }


    //查询 表02-02-01  2021年度水稻监测点建档卡 数据
    @Override
    public List<crop_01_02_table_02_01> getAllDataTwo0201(String people,String point) {
        return situationMapper.getAllDataTwo0201(people,point);
    }

    /*******************************二季稻*********************************************/
    /*******************************查询开始*********************************************/
    //查询 表05-01 获取监测点代码
    @Override
    public List<crop_01_02_table_05_01> getAllDataTwo0501(String people,String point,String column_id,int startYear,int endYear) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        List<crop_01_02_table_05_01> crop_01_02_table_05_01s = this.situationMapper.getAllDataTwo0501(people,point,column_id,currentYear,startYear,endYear);
        return crop_01_02_table_05_01s;
    }


    //查询 表01-06-01 获取监测点代码
    @Override
    public List<crop_01_02_table_06_01> getAllDataTwo0601(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_02_table_06_01> crop_01_02_table_06_01s = this.situationMapper.getAllDataTwo0601(people,point,column_id,startYear,endYear);

        return crop_01_02_table_06_01s;
    }

    //查询 表01-06-02 获取监测点代码
    @Override
    public List<crop_01_02_table_06_02> getAllDataTwo0602(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_02_table_06_02> crop_01_02_table_06_02s = this.situationMapper.getAllDataTwo0602(people,point,column_id,startYear,endYear);
        return crop_01_02_table_06_02s;
    }

    //查询 表01-06-03 获取监测点代码
    @Override
    public List<crop_01_02_table_06_03> getAllDataTwo0603(String people,String point,String column_id,int startYear,int endYear) {
        List<crop_01_02_table_06_03> crop_01_01_table_06_03s = this.situationMapper.getAllDataTwo0603(people,point,column_id,startYear,endYear);
        return crop_01_01_table_06_03s;
    }


    //查询 表01-06-04 获取监测点代码
    @Override
    public List<crop_01_02_table_06_04> getAllDataTwo0604(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_06_04> crop_01_02_table_06_04s = this.situationMapper.getAllDataTwo0604(people,point,column_id,beginYear,endYear);
        return crop_01_02_table_06_04s;
    }

    //查询 表01-06-05 获取监测点代码
    @Override
    public List<crop_01_02_table_06_05> getAllDataTwo0605(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_06_05> crop_01_02_table_06_05s = this.situationMapper.getAllDataTwo0605(people,point,column_id,beginYear,endYear);
        return crop_01_02_table_06_05s;
    }

    //查询 表01-07-01
    @Override
    public List<crop_01_02_table_07_01> getAllDataTwo0701(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_07_01> crop_01_02_table_07_01s = this.situationMapper.getAllDataTwo0701(people,column_id,beginYear,endYear);
        return crop_01_02_table_07_01s;
    }

    //查询 表01-07-02
    @Override
    public List<crop_01_02_table_07_02> getAllDataTwo0702(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_07_02> crop_01_02_table_07_02s = this.situationMapper.getAllDataTwo0702(people,column_id,beginYear,endYear);
        return crop_01_02_table_07_02s;
    }

    //查询 表01-07-03
    @Override
    public List<crop_01_02_table_07_03> getAllDataTwo0703(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_07_03> crop_01_02_table_07_03s = this.situationMapper.getAllDataTwo0703(people,column_id,beginYear,endYear);
        return crop_01_02_table_07_03s;
    }

    //查询 表01-07-04
    @Override
    public List<crop_01_02_table_07_04> getAllDataTwo0704(String people, String column_id, int beginYear, int endYear) {
        List<crop_01_02_table_07_04> crop_01_02_table_07_04s = this.situationMapper.getAllDataTwo0704(people,column_id,beginYear,endYear);
        return crop_01_02_table_07_04s;
    }


    //查询 表01-09-01
    @Override
    public List<crop_01_02_table_09_01> getAllDataTwo0901(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_01> crop_01_02_table_09_01s = this.situationMapper.getAllDataTwo0901(people,column_id,beginYear,endYear);
        return crop_01_02_table_09_01s;
    }

    //查询 表01-09-02
    @Override
    public List<crop_01_02_table_09_02> getAllDataTwo0902(String people,String year,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_02> crop_01_02_table_09_02s = this.situationMapper.getAllDataTwo0902(people,year,column_id,beginYear,endYear);
        return crop_01_02_table_09_02s;
    }

    //查询 表01-09-03
    @Override
    public List<crop_01_02_table_09_03> getAllDataTwo0903(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_03> crop_01_02_table_09_03s = this.situationMapper.getAllDataTwo0903(people,column_id,beginYear,endYear);
        return crop_01_02_table_09_03s;
    }

    //查询 表01-09-04
    @Override
    public List<crop_01_02_table_09_04> getAllDataTwo0904(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_04> crop_01_02_table_09_04s = this.situationMapper.getAllDataTwo0904(people,column_id,beginYear,endYear);
        return crop_01_02_table_09_04s;
    }

    //查询 表01-09-05
    @Override
    public List<crop_01_02_table_09_05> getAllDataTwo0905(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_05> crop_01_02_table_09_05s = this.situationMapper.getAllDataTwo0905(people,column_id,beginYear,endYear);
        return crop_01_02_table_09_05s;
    }

    //查询 表01-09-06
    @Override
    public List<crop_01_02_table_09_06> getAllDataTwo0906(String people,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_06> crop_01_02_table_09_06s = this.situationMapper.getAllDataTwo0906(people,column_id,beginYear,endYear);
        return crop_01_02_table_09_06s;
    }

    //查询 02-表09-07
    @Override
    public List<crop_01_02_table_09_07> getAllDataTwo0907(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_09_07> crop_01_02_table_09_07s = this.situationMapper.getAllDataTwo0907(people,point,column_id,beginYear,endYear);
        return crop_01_02_table_09_07s;
    }

    //查询 表02-10-01 获取监测点代码
    @Override
    public List<crop_01_02_table_10_01> getAllDataTwo1001(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_10_01> crop_01_02_table_10_01s = this.situationMapper.getAllDataTwo1001(people,point,column_id,beginYear,endYear);

        return crop_01_02_table_10_01s;
    }

    //查询 表02-10-02 获取监测点代码
    @Override
    public List<crop_01_02_table_10_02> getAllDataTwo1002(String people,String point,String column_id,int beginYear,int endYear) {
        List<crop_01_02_table_10_02> crop_01_02_table_10_02s = this.situationMapper.getAllDataTwo1002(people,point,column_id,beginYear,endYear);

        return crop_01_02_table_10_02s;
    }
    /*******************************查询结束*********************************************/
    /*******************************保存开始*********************************************/
    //保存 表01-5-1
    @Override
    public int getInputDataTableTwo0501(List<crop_01_02_table_05_01> obj1) {
        return situationMapper.getInputDataTableTwo0501(obj1);
    }

    //保存 表01-6-1
    @Override
    public int getInputDataTableTwo0601(List<crop_01_02_table_06_01> obj1) {
        return situationMapper.getInputDataTableTwo0601(obj1);
    }

    //保存 表01-6-2
    @Override
    public int getInputDataTableTwo0602(List<crop_01_02_table_06_02> obj1) {
        return situationMapper.getInputDataTableTwo0602(obj1);
    }

    //保存 表01-6-3
    @Override
    public int getInputDataTableTwo0603(List<crop_01_02_table_06_03> obj1) {
        return situationMapper.getInputDataTableTwo0603(obj1);
    }

    //保存 表01-6-4
    @Override
    public int getInputDataTableTwo0604(List<crop_01_02_table_06_04> obj1) {
        return situationMapper.getInputDataTableTwo0604(obj1);
    }

    //保存 表01-6-5
    @Override
    public int getInputDataTableTwo0605(List<crop_01_02_table_06_05> obj1) {
        return situationMapper.getInputDataTableTwo0605(obj1);
    }

    //保存 表01-07-01
    @Override
    public int getInputDataTableTwo0701(crop_01_02_table_07_01 obj1) {
        return situationMapper.getInputDataTableTwo0701(obj1);
    }

    //保存 表01-07-02
    @Override
    public int getInputDataTableTwo0702(crop_01_02_table_07_02 obj1) {
        return situationMapper.getInputDataTableTwo0702(obj1);
    }

    //保存 表01-07-03
    @Override
    public int getInputDataTableTwo0703(crop_01_02_table_07_03 obj1) {
        return situationMapper.getInputDataTableTwo0703(obj1);
    }

    //保存 表01-07-03
    @Override
    public int getInputDataTableTwo0704(crop_01_02_table_07_04 obj1) {
        return situationMapper.getInputDataTableTwo0704(obj1);
    }

    //保存 表01-09-01
    @Override
    public int getInputDataTableTwo0901(crop_01_02_table_09_01 obj1) {
        return situationMapper.getInputDataTableTwo0901(obj1);
    }

    //保存 表01-09-02
    @Override
    public int getInputDataTableTwo0902(crop_01_02_table_09_02 obj1) {
        return situationMapper.getInputDataTableTwo0902(obj1);
    }

    //保存 表01-09-03
    @Override
    public int getInputDataTableTwo0903(crop_01_02_table_09_03 obj1) {
        return situationMapper.getInputDataTableTwo0903(obj1);
    }

    //保存 表01-09-04
    @Override
    public int getInputDataTableTwo0904(crop_01_02_table_09_04 obj1) {
        return situationMapper.getInputDataTableTwo0904(obj1);
    }

    //保存 表01-09-05
    @Override
    public int getInputDataTableTwo0905(crop_01_02_table_09_05 obj1) {
        return situationMapper.getInputDataTableTwo0905(obj1);
    }

    //保存 表01-09-06
    @Override
    public int getInputDataTableTwo0906(crop_01_02_table_09_06 obj1) {
        return situationMapper.getInputDataTableTwo0906(obj1);
    }

    //保存 表01-09-07
    @Override
    public int getInputDataTableTwo0907(crop_01_02_table_09_07 obj1) {
        return situationMapper.getInputDataTableTwo0907(obj1);
    }

    //保存 表01-10-01
    @Override
    public int getInputDataTableTwo1001(crop_01_02_table_10_01 obj1) {
        return situationMapper.getInputDataTableTwo1001(obj1);
    }

    //保存 表01-10-02
    @Override
    public int getInputDataTableTwo1002(crop_01_02_table_10_02 obj1) {
        return situationMapper.getInputDataTableTwo1002(obj1);
    }
    /*******************************保存结束*********************************************/
    /********************************监测点开始*************************************/
    @Override
    public List<crop_01_02_table_02_01> getAllDataPointTwo0201(String people, String myDate) {
        return situationMapper.getAllDataPointTwo0201(people,myDate);
    }

    @Override
    public List<crop_01_02_table_02_01> getAllDataHistoryPointTwo0201(String people, String myDate) {
        return situationMapper.getAllDataHistoryPointTwo0201(people,myDate);
    }

    //保存 表01-10-01
    @Override
    public int updateInputDataTable3(crop_01_01_table_02_01 obj1) {
        return situationMapper.updateInputDataTable3(obj1);
    }

    @Override
    public int updateInputDataTableTwo3(crop_01_02_table_02_01 obj1) {
        return situationMapper.updateInputDataTableTwo3(obj1);
    }
    /********************************监测点结束*************************************/
    /*******************************二季稻*********************************************/

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //查询该用户下的所有表1-2数据
    @Override
    public List<crop_01_02_table_01_02> getUserAllDataTwo(String people,String column_id,int flag,int currentYear){
        return situationMapper.getUserAllDataTwo(people,column_id,flag,currentYear);
    }

    //查询该用户下的所有表1-2数据，品系名对应
    @Override
    public List<crop_01_02_table_01_02> getTwoByPinZhongName(String people,String pin_zhong_name,String column_id,int flag,int currentYear){
        return situationMapper.getTwoByPinZhongName(people,pin_zhong_name,column_id,flag,currentYear);
    }

    //查询该用户下的所有表1-1数据
    @Override
    public List<crop_01_02_table_01_01> getUserAllDataTwo01(String people){
        return situationMapper.getUserAllDataTwo01(people);
    }


    //获取监测点代码
    @Override
    public List<monitoring_point> getMonitorCodeTwo(String UserID){
        return situationMapper.getMonitorCodeTwo(UserID);
    }


    //保存 表1-1  2021年度监测县(市、区)水稻栽插概况调查表 数据
    @Override
    public int getInputDataTableTwo1(List<crop_01_02_table_01_01> obj1){
        return situationMapper.getInputDataTableTwo1(obj1);
    }

    //保存 表1-2  2021年度监测县(市、区)水稻生产主推品种调查表 数据
    @Override
    public int getInputDataTableTwo2(crop_01_02_table_01_02 obj1){
        return situationMapper.getInputDataTableTwo2(obj1);
    }

    //保存 表3-1   数据
    @Override
    public int getInputDataTableTwo5(List<crop_01_02_table_03_01> obj1){
        return situationMapper.getInputDataTableTwo5(obj1);
    }

    //保存 表3-2   数据
    @Override
    public int getInputDataTableTwo0302(List<crop_01_02_table_03_02> obj1){
        return situationMapper.getInputDataTableTwo0302(obj1);
    }

    //保存 表3-3   数据
    @Override
    public int getInputDataTableTwo0303(List<crop_01_02_table_03_03> obj1){
        return situationMapper.getInputDataTableTwo0303(obj1);
    }

    //保存 表3-4   数据
    @Override
    public int getInputDataTableTwo0304(List<crop_01_02_table_03_04> obj1){
        return situationMapper.getInputDataTableTwo0304(obj1);
    }

    //保存 表3-5   数据
    @Override
    public int getInputDataTableTwo0305(List<crop_01_02_table_03_05> obj1){
        return situationMapper.getInputDataTableTwo0305(obj1);
    }

    //保存 表3-6   数据
    @Override
    public int getInputDataTableTwo0306(List<crop_01_02_table_03_06> obj1){
        return situationMapper.getInputDataTableTwo0306(obj1);
    }

    //保存 表4-1   数据
    @Override
    public int getInputDataTableTwo0401(List<crop_01_02_table_04_01> obj1){
        return situationMapper.getInputDataTableTwo0401(obj1);
    }

    //保存 表4-2   数据
    @Override
    public int getInputDataTableTwo0402(List<crop_01_02_table_04_02> obj1){
        return situationMapper.getInputDataTableTwo0402(obj1);
    }

    //保存 表4-3   数据
    @Override
    public int getInputDataTableTwo0403(List<crop_01_02_table_04_03> obj1){
        return situationMapper.getInputDataTableTwo0403(obj1);
    }

    //保存 表4-4   数据
    @Override
    public int getInputDataTableTwo0404(List<crop_01_02_table_04_04> obj1){
        return situationMapper.getInputDataTableTwo0404(obj1);
    }

    //保存 表8-1   数据
    @Override
    public int getInputDataTableTwo0801(crop_01_02_table_08_01 obj1){
        return situationMapper.getInputDataTableTwo0801(obj1);
    }

    //保存 表8-2   数据
    @Override
    public int getInputDataTableTwo0802(crop_01_02_table_08_02 obj1){
        return situationMapper.getInputDataTableTwo0802(obj1);
    }

    //保存 表2-1  2021年度水稻监测点建档卡 数据
    @Override
    public int getInputDataTableTwo3(crop_01_02_table_02_01 obj1){
        return situationMapper.getInputDataTableTwo3(obj1);
    }

    //保存监测点建档卡数据至表1-1中
    @Override
    public int insertMonitorInfoToTableTwo0101(String area, String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0101(area,areaCate,people,time,currentYear);
    }

    //修改监测点建档卡数据至表1-1中
    @Override
    public int updateMonitorInfoToTableTwo0101(String area, String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0101(area,areaCate,people,time,currentYear);
    }

    //查询表1-1 下该用户所有的数据数
    @Override
    public List<crop_01_02_table_01_01> checkUserAllKnownDataTwo1(String UserID,String column_id,int startYear,int endYear){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.checkUserAllKnownDataTwo1(UserID,column_id,startYear,endYear,currentYear);
    }

    //读取建档卡表2-1内容至表2-2中
    @Override
    public int getMonitorCardContentTwo(String people){
        return situationMapper.getMonitorCardContentTwo(people);
    }

    //读取建档卡内容至表3-2中
    @Override
    public int getMonitorCardContentIntoTwo0302(String people){
        return situationMapper.getMonitorCardContentIntoTwo0302(people);
    }

    //读取建档卡内容至表3-3中
    @Override
    public int getMonitorCardContentIntoTwo0303(String people){
        return situationMapper.getMonitorCardContentIntoTwo0303(people);
    }

    //读取建档卡内容至表3-1中
    @Override
    public int getMonitorCardContentIntoTwo0301(String people){
        return situationMapper.getMonitorCardContentIntoTwo0301(people);
    }

    //保存监测点建档卡数据至表1-2中
    @Override
    public int insertMonitorInfoToTableTwo0102(String area,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0102(area,people,time,currentYear);
    }

    //读取该用户所有表2-2数据
    @Override
    public List<crop_01_02_table_02_02> getUserAllDataTwo0202(String people){
        return situationMapper.getUserAllDataTwo0202(people);
    }

    //查询该用户所有表3-1的数据
    @Override
    public List<crop_01_02_table_03_01> getUserAllDataTwo0301(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0301(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-2的数据
    @Override
    public List<crop_01_02_table_03_02> getUserAllDataTwo0302(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0302(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-3的数据
    @Override
    public List<crop_01_02_table_03_03> getUserAllDataTwo0303(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0303(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-4的数据
    @Override
    public List<crop_01_02_table_03_04> getUserAllDataTwo0304(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0304(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-5的数据
    @Override
    public List<crop_01_02_table_03_05> getUserAllDataTwo0305(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0305(people,column_id,startYear,endYear);
    }

    //查询该用户所有表3-6的数据
    @Override
    public List<crop_01_02_table_03_06> getUserAllDataTwo0306(String people,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0306(people,column_id,startYear,endYear);
    }

    //查询该用户所有表4-1的数据
    @Override
    public List<crop_01_02_table_04_01> getUserAllDataTwo0401(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0401(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-2的数据
    @Override
    public List<crop_01_02_table_04_02> getUserAllDataTwo0402(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0402(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-2的数据新增
    @Override
    public List<crop_01_02_table_04_02> getUserAllDataTwo0402new(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0402new(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-3的数据
    @Override
    public List<crop_01_02_table_04_03> getUserAllDataTwo0403(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0403(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表4-4的数据
    @Override
    public List<crop_01_02_table_04_04> getUserAllDataTwo0404(String people,String column_id,String monitor_location,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0404(people,column_id,monitor_location,startYear,endYear);
    }

    //查询该用户所有表8-1的数据
    @Override
    public List<crop_01_02_table_08_01> getUserAllDataTwo0801(String people,String point,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0801(people,point,column_id,startYear,endYear);
    }

    //查询该用户所有表8-2的数据
    @Override
    public List<crop_01_02_table_08_02> getUserAllDataTwo0802(String people,String point,String column_id,int startYear,int endYear){
        return situationMapper.getUserAllDataTwo0802(people,point,column_id,startYear,endYear);
    }
//
//    //保存监测点建档卡数据至表3-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表3-2中
//    @Override
//    public int insertMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表3-3中
//    @Override
//    public int insertMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表3-4中
//    @Override
//    public int insertMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表3-5中
//    @Override
//    public int insertMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0305(area,areaCate,people,time);
//    }
//
//    //保存监测点建档卡数据至表3-6中
//    @Override
//    public int insertMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表5-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表6-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表6-2中
//    @Override
//    public int insertMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表6-3中
//    @Override
//    public int insertMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表6-4中
//    @Override
//    public int insertMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表6-5中
//    @Override
//    public int insertMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表7-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0701(String area,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0701(area,people,time);
//    }
//
//    //保存监测点建档卡数据至表7-2中
//    @Override
//    public int insertMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0702(area,areaCate,people,time);
//    }
//
//    //保存监测点建档卡数据至表7-3中
//    @Override
//    public int insertMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0703(area,areaCate,people,time);
//    }
//
//    //保存监测点建档卡数据至表7-4中
//    @Override
//    public int insertMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time);
//    }
//
//    //保存监测点建档卡数据至表9-3中
//    @Override
//    public int insertMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表9-4中
//    @Override
//    public int insertMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表9-5中
//    @Override
//    public int insertMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表9-6中
//    @Override
//    public int insertMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表9-7中
//    @Override
//    public int insertMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表10-1中
//    @Override
//    public int insertMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //保存监测点建档卡数据至表10-2中
//    @Override
//    public int insertMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time);
//    }
//
//
//    //保存监测点建档卡数据至表8-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0801(String area,String areaCate,
//                                               String zaiZhiType,
//                                               String people,String time){
//        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
//        for(int i = 0 ; i < 5 ; ++i){
//            zaiZhiType = zaiArr[i];
//            situationMapper.insertMonitorInfoToTableTwo0801(area,areaCate,zaiZhiType,people,time);
//        }
//        return 1;
//    }
//
//    //保存监测点建档卡数据至表8-2中
//    @Override
//    public int insertMonitorInfoToTableTwo0802(String area,String areaCate,
//                                               String zaiZhiType,
//                                               String people,String time){
//        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
//        for(int i = 0 ; i < 5 ; ++i){
//            zaiZhiType = zaiArr[i];
//            situationMapper.insertMonitorInfoToTableTwo0802(area,areaCate,zaiZhiType,people,time);
//        }
//        return 1;
//    }
//
//    //读取建档卡内容至表4-1中
//    @Override
//    public int insertMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
//                                               String daoZuoType,String pinXiType,
//                                               String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //读取建档卡内容至表4-2中
//    @Override
//    public int insertMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
//                                               String daoZuoType,String pinXiType,
//                                               String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }
//
//    //读取建档卡内容至表4-3中
//    @Override
//    public int insertMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
//                                               String daoZuoType,String pinXiType,
//                                               String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time);
//    }
//
//    //读取建档卡内容至表4-4中
//    @Override
//    public int insertMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
//                                               String daoZuoType,String pinXiType,
//                                               String pinZhongType,String zaiZhiType,String people,String time){
//        return situationMapper.insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time);
//    }


    //保存监测点建档卡数据至表3-1中
    @Override
    public int insertMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-1中
    @Override
    public int updateMonitorInfoToTableTwo0301(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-2中
    @Override
    public int insertMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-2中
    @Override
    public int updateMonitorInfoToTableTwo0302(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-3中
    @Override
    public int insertMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-3中
    @Override
    public int updateMonitorInfoToTableTwo0303(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-4中
    @Override
    public int insertMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-4中
    @Override
    public int updateMonitorInfoToTableTwo0304(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-5中
    @Override
    public int insertMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0305(area,areaCate,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-5中
    @Override
    public int updateMonitorInfoToTableTwo0305(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0305(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表3-6中
    @Override
    public int insertMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表3-6中
    @Override
    public int updateMonitorInfoToTableTwo0306(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存建档卡内容至表4-1中
    @Override
    public int insertMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改建档卡内容至表4-1中
    @Override
    public int updateMonitorInfoToTableTwo0401(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存建档卡内容至表4-2中
    @Override
    public int insertMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改建档卡内容至表4-2中
    @Override
    public int updateMonitorInfoToTableTwo0402(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存建档卡内容至表4-3中
    @Override
    public int insertMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time,currentYear);
    }

    //修改建档卡内容至表4-3中
    @Override
    public int updateMonitorInfoToTableTwo0403(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String jiBenMiao,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time,currentYear);
    }

    //保存建档卡内容至表4-4中
    @Override
    public int insertMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改建档卡内容至表4-4中
    @Override
    public int updateMonitorInfoToTableTwo0404(String area,String MonitorBlock,String areaCate,
                                               String daoZuoType,String pinXiType,
                                               String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表5-1中
    @Override
    public int insertMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表5-1中
    @Override
    public int updateMonitorInfoToTableTwo0501(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表6-1中
    @Override
    public int insertMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表6-1中
    @Override
    public int updateMonitorInfoToTableTwo0601(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表6-2中
    @Override
    public int insertMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表6-2中
    @Override
    public int updateMonitorInfoToTableTwo0602(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表6-3中
    @Override
    public int insertMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表6-3中
    @Override
    public int updateMonitorInfoToTableTwo0603(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表6-4中
    @Override
    public int insertMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表6-4中
    @Override
    public int updateMonitorInfoToTableTwo0604(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表6-5中
    @Override
    public int insertMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表6-5中
    @Override
    public int updateMonitorInfoToTableTwo0605(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-1中
    @Override
    public int insertMonitorInfoToTableTwo0701(String area,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0701(area,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-2中
    @Override
    public int insertMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0702(area,areaCate,people,time,currentYear);
    }

    //修改监测点建档卡数据至表7-2中
    @Override
    public int updateMonitorInfoToTableTwo0702(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0702(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-3中
    @Override
    public int insertMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0703(area,areaCate,people,time,currentYear);
    }

    //修改监测点建档卡数据至表7-3中
    @Override
    public int updateMonitorInfoToTableTwo0703(String area,String areaCate,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0703(area,areaCate,people,time,currentYear);
    }

    //保存监测点建档卡数据至表7-4中
    @Override
    public int insertMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表7-4中
    @Override
    public int updateMonitorInfoToTableTwo0704(String area,String areaCate,String daoZuoType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表8-1中
    @Override
    public int insertMonitorInfoToTableTwo0801(String area,String MonitorBlock,String areaCate,
                                               String zaiZhiType,
                                               String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.insertMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //修改监测点建档卡数据至表8-1中
    @Override
    public int updateMonitorInfoToTableTwo0801(String area,String MonitorBlock,String areaCate,
                                               String zaiZhiType,
                                               String people,String time){
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.updateMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //保存监测点建档卡数据至表8-2中
    @Override
    public int insertMonitorInfoToTableTwo0802(String area,String MonitorBlock,String areaCate,
                                               String zaiZhiType,
                                               String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.insertMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //修改监测点建档卡数据至表8-2中
    @Override
    public int updateMonitorInfoToTableTwo0802(String area,String MonitorBlock,String areaCate,
                                               String zaiZhiType,
                                               String people,String time){
        String[] zaiArr = {"人工移栽","机械插秧","抛秧","撒直播","机条播"};
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        for(int i = 0 ; i < 5 ; ++i){
            zaiZhiType = zaiArr[i];
            situationMapper.updateMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time,currentYear);
        }
        return 1;
    }

    //保存监测点建档卡数据至表9-3中
    @Override
    public int insertMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表9-4中
    @Override
    public int insertMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表9-5中
    @Override
    public int insertMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表9-6中
    @Override
    public int insertMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表9-7中
    @Override
    public int insertMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表9-3中
    @Override
    public int updateMonitorInfoToTableTwo0903(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表9-4中
    @Override
    public int updateMonitorInfoToTableTwo0904(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表9-5中
    @Override
    public int updateMonitorInfoToTableTwo0905(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表9-6中
    @Override
    public int updateMonitorInfoToTableTwo0906(String area,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表9-7中
    @Override
    public int updateMonitorInfoToTableTwo0907(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinXiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表10-1中
    @Override
    public int insertMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表10-1中
    @Override
    public int updateMonitorInfoToTableTwo1001(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //保存监测点建档卡数据至表10-2中
    @Override
    public int insertMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.insertMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }

    //修改监测点建档卡数据至表10-2中
    @Override
    public int updateMonitorInfoToTableTwo1002(String area,String MonitorBlock,String areaCate,String daoZuoType,String pinZhongType,String zaiZhiType,String people,String time){
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        return situationMapper.updateMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time,currentYear);
    }



    //获取数据退还列表
    @Override
    public List<table_record_back> getDataBackListInfo(String people){
        return situationMapper.getDataBackListInfo(people);
    }

    //修改数据退还状态
    @Override
    public int fixDataBackState(String tableName,String tableId){
        return situationMapper.fixDataBackState(tableName,tableId);
    }

    ///////////////////////////////////////////////////////////////////////////////
    @Override
    public List<crop_01_01_table_02_01> getAllDataPoint0201(String people, String myDate) {
        return situationMapper.getAllDataPoint0201(people,myDate);
    }

    @Override
    public List<crop_01_01_table_02_01> getAllDataHistoryPoint0201(String people, String myDate) {
        return situationMapper.getAllDataHistoryPoint0201(people,myDate);
    }

    //查询该用户所在的县
    @Override
    public List<sys_code> getAreaInfo(String people) {
        return situationMapper.getAreaInfo(people);
    }

    //查询所有品种
    @Override
    public List<table_variety> getAllVarieties() {
        return situationMapper.getAllVarieties();
    }

    @Override
    public List<person_login> getPerson(String people) {
        return situationMapper.getPerson(people);
    }
}
