package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.ConditionsAnalysisMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.ConditionsAnalysisService;
import com.ricemonitoring.util.CommonUtils;
import com.ricemonitoring.util.quickSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:50
 */
@Service
public class ConditionsAnalysisServiceImpl implements ConditionsAnalysisService {
    //注入mapper
    @Autowired
    ConditionsAnalysisMapper conditionsAnalysisMapper;


    //注入utils
    @Autowired
    CommonUtils commonUtils;

    @Autowired
    quickSort quickSort;
    /*************************************一季稻开始*********************************************/
    /*
    通过表名查出表中农情分析需要展示的字段
     */
    @Override
    public List<conditions_analysis> getFileds(String tableName) {
        //1.通过表名查出表对应的id
        String tableId = conditionsAnalysisMapper.getIdByTableName(tableName);
        //2.通过id查出表中需要展示的字段
        return conditionsAnalysisMapper.getFileds(tableId);
    }
    /*
    通过表id字段名以及城市名查出数据
     */
    @Override
    public List<Integer> getData(String tableId, String city, String columns) {
        //1.将columns字符串转化为数组
        String[] dataColumns = columns.split(",");
        //2.通过表的id以及字段名查出字段在数据库的字段名
        List<Integer> datas = new ArrayList<>();
        for (String dataColumn : dataColumns) {
            String filed = conditionsAnalysisMapper.getDataFileds(tableId,dataColumn);
            int data=Integer.parseInt(conditionsAnalysisMapper.getData(tableId,filed,city)) ;
            datas.add(data);
        }
        //3.查出数据
        return datas;
    }
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
    crop_01_01_table_01_02Analysis_1：通过控制县名查出各品系的占比
    crop_01_01_table_01_02Analysis_2：通过控制品系查出该品系在个县的占比
     */
    @Override
    public Map<String, Object> crop_01_01_table_01_02Analysis_1(String area,String years) throws Exception{
        Map<String, Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        //2.查出某一品种的各条记录
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(new Date());
        List<crop_01_01_table_01_02> items_01_02 = conditionsAnalysisMapper.crop_01_01_table_01_02Analysis_1(area,years);
        quickSort.quickSort(items_01_02);
        Collections.reverse(items_01_02);
        if (items_01_02.size()>=10){
            items_01_02 = items_01_02.subList(0,10);
        }
        Collections.reverse(items_01_02);
        List<String> cates = new ArrayList<>();
        List<String> datas = new ArrayList<>();
        for (crop_01_01_table_01_02 crop_01_01_table_01_02 : items_01_02) {
            if (crop_01_01_table_01_02.getPin_zhong_name()==null){
                continue;
            }
            cates.add(crop_01_01_table_01_02.getPin_zhong_name());
            datas.add(df.format(Double.valueOf(crop_01_01_table_01_02.getArea_data())));
        }
        map.put("cates",cates);
        map.put("datas",datas);
        return map;
    }

    /*
        表3-2 年度监测县(市、区)水稻苗情评价动态列表（移栽稻）
        crop_01_01_table_03_02Analysis_1
     */
    @Override
    public Map<String, Object> crop_01_01_table_03_02Analysis_1(String area, String areaCate,String years) throws Exception{
        Map<String, Object> map = new HashMap<>();

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(new Date());

        //通过地区查出数据
        List<String> sqlFields = new ArrayList<>();
        for (int i = 4; i < 12; i++) {
            sqlFields.add("avg(field_"+i+"_2_1) as field_"+i+"_2_1,avg(field_"+i+"_3_1) as field_"+i+"_3_1,avg(field_"+i+"_4_1) as field_"+i+"_4_1");
        }
        crop_01_01_table_03_02 item = conditionsAnalysisMapper.crop_01_01_table_03_02Analysis_1(area,areaCate,sqlFields,years);
        if (item==null){
            return map;
        }
        if(item.getField_3()==null||item.getField_3().equals("")){
            return map;
        }
        List<String> datas1 = new ArrayList<>();
        List<String> datas2 = new ArrayList<>();
        List<String> datas3 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int k = 0;
        //浮点数小数位数限制
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i =4;i<12;i++){
            Method m1 = item.getClass().getMethod("getField_" + i + "_2_1");
            Method m2 = item.getClass().getMethod("getField_" + i + "_3_1");
            Method m3 = item.getClass().getMethod("getField_" + i + "_4_1");

            String tempData1 = (String) m1.invoke(item);
            String tempData2 = (String) m2.invoke(item);
            String tempData3 = (String) m3.invoke(item);
            if(tempData1==null||tempData1.equals("")||
                    tempData2==null||tempData2.equals("")||
                    tempData3==null||tempData3.equals("")){
                //如果有进程数据还未填写则终止循环
                break;
            }
            fields.add("进程"+(++k));
            datas1.add(df.format(Double.valueOf(tempData1)));
            datas2.add(df.format(Double.valueOf(tempData2)));
            datas3.add(df.format(Double.valueOf(tempData3)));

        }
        //将数据封装好传到前台
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("datas3",datas3);
        map.put("fields",fields);
        return map;
    }
    /*
    表4-1 年度监测县(市、区)中稻生育进度调查表（移栽稻）
    crop_01_01_table_04_01Analysis_1:不同地区类型下不同进程中的平均叶龄和平均茎蘖数
    crop_01_01_table_04_01Analysis_2:控制县市，稻作类型、品系类别、栽培方式来获取数据
        param:
            area:县市
            riceCata:水稻类别
            strainCategory:品系类别
            cultivationMethod:栽培方式
     */
    @Override
    public Map<String, Object> crop_01_01_table_04_01Analysis_1(String areaCate,String riceCate,String cultivationMethod,String years) throws Exception{
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(new Date());
        //根据地区类型查出数据
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 18; i++) {
            sqlFields.add("avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4");
        }
        crop_01_01_table_04_01 item = conditionsAnalysisMapper.crop_01_01_table_04_01Analysis_1(areaCate,riceCate,cultivationMethod,sqlFields,years);
        if(item.getField_9_3()==null){
            return map;
        }
        List<Double> datas1 = new ArrayList<>();
        List<Double> datas2 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int k = 0;
        //浮点数小数位数限制
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i =9;i<18;i++){
            Method m1 = item.getClass().getMethod("getField_" + i + "_3");
            Method m2 = item.getClass().getMethod("getField_" + i + "_4");
            String tempData1 = (String) m1.invoke(item);
            String tempData2 = (String) m2.invoke(item);
            if(tempData1==null||tempData1.equals("")||
                    tempData2==null||tempData2.equals("")){
                //如果有进程数据还未填写则终止循环
                break;
            }
            fields.add("进程"+(++k));
            datas1.add(Double.valueOf(df.format(Double.valueOf(tempData1))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(tempData2))));
        }
        //将数据封装好传到前台
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("fields",fields);
        return map;
    }

    @Override
    public Map<String, Object> crop_01_01_table_04_01Analysis_2(String area,String year) throws Exception{
        Map<String, Object> map = new HashMap<>();
        //获取数据
//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String year = yearFormat.format(new Date());
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 18; i++) {
            sqlFields.add("avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4");
        }
        crop_01_01_table_04_01 item  = conditionsAnalysisMapper.crop_01_01_table_04_01Analysis_2(area,sqlFields,year);
        if (item==null){
            return map;
        }
        List<Double> datas1 = new ArrayList<>();
        List<Double> datas2 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int k = 1;
        //浮点数小数位数限制
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i =9;i<18;i++){
            Method m1 = item.getClass().getMethod("getField_" + i + "_3");
            Method m2 = item.getClass().getMethod("getField_" + i + "_4");
            String tempData1 = (String) m1.invoke(item);
            String tempData2 = (String) m2.invoke(item);
            if(tempData1==null||tempData1.equals("")||
                    tempData2==null||tempData2.equals("")){
                //如果有进程数据还未填写则终止循环
                break;
            }
            fields.add("进程"+(++k));
            datas1.add(Double.valueOf(df.format(Double.valueOf(tempData1))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(tempData2))));
        }
        //将数据封装好传到前台
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("fields",fields);
        return map;
    }
    /*
    表4-3 20xx年度水稻监测点茎蘖数叶面积构成表
    crop_01_01_table_04_03Analysis_1：通过地区类型求得平均值
    crop_01_01_table_04_03Analysis_2：不同地区类型下的分葉数
     */
    @Override
    public Map<String, Object> crop_01_01_table_04_03Analysis_1(String areaCate, String strainCategory, String cultivationMethod,String years) throws Exception{
        Map<String, Object> map = new HashMap<>();
        //1.通过地区类型来获取平均株高、总茎蘖数、平均单茎绿叶数
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(new Date());
        List<String> sqlFields = new ArrayList<>();
        for (int i = 10; i < 13; i++) {
            sqlFields.add("avg(field_"+i+"_2) as field_"+i+"_2,avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4");
        }
        crop_01_01_table_04_03 item = conditionsAnalysisMapper.crop_01_01_table_04_03Analysis_1(areaCate,strainCategory,cultivationMethod,sqlFields,years);
        if (item==null){
            return map;
        }
        if (item.getField_10_2()==null){
            return map;
        }
        //定义两个临时变量用来存数据
        List<Double> datas1 = new ArrayList<>(); //株高数据
        List<Double> datas2 = new ArrayList<>(); //总茎蘖数
        List<Double> datas3 = new ArrayList<>(); //平均单茎绿叶数
        List<String> fields = new ArrayList<>();

        //解析出自己所需的数据
        DecimalFormat   df=new   DecimalFormat("0.00");
        for (int i = 10;i<13;i++){
            Method m1 =(Method)  item.getClass().getMethod(("get" + "Field_"+i+"_2"));
            Method m2 =(Method)  item.getClass().getMethod(("get" + "Field_"+i+"_3"));
            Method m3 =(Method)  item.getClass().getMethod(("get" + "Field_"+i+"_4"));
            String invoke1 =(String) m1.invoke(item);
            String invoke2 =(String) m2.invoke(item);
            String invoke3 =(String) m3.invoke(item);
            if(invoke1==null||invoke1.equals("")||
                    invoke2==null||invoke2.equals("")||
                    invoke3==null||invoke3.equals("")){
                //如果有进程数据还未填写则终止循环
                break;
            }
            datas1.add( Double.valueOf(df.format(Double.valueOf(invoke1))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(invoke2))));
            datas3.add(Double.valueOf(df.format(Double.valueOf(invoke3))));
            switch (i){
                case 10:
                    fields.add("拔节期");
                    break;
                case 11:
                    fields.add("幼穗分化1-2期");
                    break;
                case 12:
                    fields.add("幼穗分化3-4期");
                    break;
            }

        }
        //将数据封装好传到前台
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("datas3",datas3);
        map.put("fields",fields);
        return map;

    }

    @Override
    public Map<String, Object> crop_01_01_table_04_03Analysis_2(String year) {
        //定义地区类型
        String[] areaCates = {"江淮地区","沿江地区","沿淮地区"};
//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String year = yearFormat.format(new Date());
        //循环查出不同地区类型下的分葉数
        List<crop_01_01_table_04_03> lists = new ArrayList<>();
        for (String areaCate : areaCates) {
            crop_01_01_table_04_03 item = null;
            try {
                item = conditionsAnalysisMapper.crop_01_01_table_04_03Analysis_2(areaCate,year);
            } catch (Exception e) {
                item = null;
            }
            lists.add(item);
        }
        //解析出自己所需的数据
        List<Double> datas1 = new ArrayList<>(); //株高数据
        List<Double> datas2 = new ArrayList<>(); //总茎蘖数
        List<Double> datas3 = new ArrayList<>(); //平均单茎绿叶数
        DecimalFormat df = new DecimalFormat("0.00");
        for (crop_01_01_table_04_03 list : lists) {
            if (list==null||list.getField_10_6()==null){
                //数据为空跳出循环
                continue;
            }
            datas1.add(Double.valueOf(df.format(Double.valueOf(list.getField_10_6()))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(list.getField_10_7()))));
            datas3.add(Double.valueOf(df.format(Double.valueOf(list.getField_10_8()))));
        }
        //将数据封装好传到前台
        Map<String, Object> map = new HashMap<>();
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("datas3",datas3);
        return map;

    }



    /*
    表7-1：年度监测县(市、区)水稻单季稻苗情监测预测产表
    crop_01_01_table_07_01Analysis_1：柱状图展示某一地区类型下的面积、亩有效穗(万)、穗总粒数(个)、结实率(％)、千粒重(g)、理论产量(kg/亩)
     */
    @Override
    public Map<String, Object> crop_01_01_table_07_01Analysis_1(String Year) throws Exception{
        System.out.println(Year);
        //1.通过地区类型查出数据
        String[] areaCates = {"江淮地区","沿江地区","沿淮地区"};
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        //String thisYear = yearFormat.format(new Date());    //今年
        String thisYear = Year;    //今年
        String lastYear = String.valueOf(Integer.parseInt(thisYear)-1);  //去年
        //String[] years = {"2021","2022"};
        String[] years = {lastYear,thisYear};
        List<crop_01_01_table_07_01> lists = new ArrayList<>();
        for (String cate : areaCates) {
            for (String year : years) {
                crop_01_01_table_07_01 item = conditionsAnalysisMapper.crop_01_01_table_07_01Analysis_1(cate,year);
                lists.add(item);
            }
        }
        List<Double> temp1_list = new ArrayList<>(); //2021各地区产量数据集合
        List<Double> temp2_list = new ArrayList<>(); //2022各地区产量数据集合
        //柱状图的数据list
        List<Double> datas = new ArrayList<>();
        //折线图的数据---数据全部*100以展现成百分比的形式
        List<Double> pecents = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        fields.add("");
        String[] allFields = {"","","","","进程1","进程2","进程3","进程4","进程5","进程6"};
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i =0;i<lists.size();i++) {
            crop_01_01_table_07_01 item = lists.get(i);
            if(item==null||item.getField_4_6()==null){
                //该地区该年份数据不存在,直接补零
                if(i%2==0){
                    for(int l=0;l<7;l++){
                        pecents.add(0.0);
                    }
                }else{
                    for(int l=0;l<7;l++){
                        datas.add(0.0);
                    }
                }

                continue;
            }
            //System.out.println("时间："+item.getModification_time());
            //取出2021年的理论产量数据
            if (item.getModification_time().toString().contains(thisYear)){
                datas.add(0.0);
                for (int j = 4;j<10;j++){
                    Method m1 =(Method)  item.getClass().getMethod(("get" + "Field_"+j+"_6"));
                    String temp1 = (String) m1.invoke(item);
                    if(temp1==null){
                        //空数据则终止循环
                        datas.add(0.0);
//                        continue;
                    }else{
                        datas.add(Double.valueOf(df.format(Double.valueOf(temp1))));
                    }
//                    if(fields.size()<7){
//                        fields.add(allFields[j]);
//                    }
                }
                //取出2021年相较于2020年的产量变化比
            }else if(item.getModification_time().toString().contains(lastYear)){
                crop_01_01_table_07_01 item_2021 = lists.get((i+1));
                pecents.add(0.0);
                if (item_2021==null){
                    for (int j = 0; j < 6; j++) {
                        pecents.add(0.0);
                    }
                    continue;
                }else{
                    for (int  k = 4;k<10;k++){
                        Method m1 =(Method)  item.getClass().getMethod(("get" + "Field_"+k+"_6"));
                        String temp1 = (String) m1.invoke(item);
                        Method m2 =(Method)  item_2021.getClass().getMethod(("get" + "Field_"+k+"_6"));
                        String temp2 = (String) m2.invoke(item_2021);
                        if(temp1==null||temp2==null){
                            //空数据则终止循环
                            pecents.add(0.0);
//                        continue;
                        }else{
                            double pecent = (Double.valueOf(temp2) - Double.valueOf(temp1)) / Double.valueOf(temp1);
                            pecent*=100;
                            pecent = Double.valueOf(df.format(pecent));
                            pecents.add(pecent);

                            temp1_list.add(Double.valueOf(temp1));
                            temp2_list.add(Double.valueOf(temp2));
                        }

                    }
                }

            }
        }

        List<Double> datas1 = new ArrayList<>(); //江淮地区2021数据集合
        List<Double> datas2 = new ArrayList<>(); //沿江地区2021数据集合
        List<Double> datas3 = new ArrayList<>(); //沿淮地区2021数据集合
        List<Double> total2021 = new ArrayList<>(); //全省产量2021数据集合

        //分别循环将2021各地区的数据加入到对应的集合中
        for (int i = 0; i < 7; i++) {
            Double jianghuai = datas.get(i);
            datas1.add(jianghuai);
        }
        for (int j = 7; j < 14; j++) {
            Double yanjiang = datas.get(j);
            datas2.add(yanjiang);
        }
        for (int k = 14; k < 21; k++) {
            Double yanhuai = datas.get(k);
            datas3.add(yanhuai);
        }

        //循环将2021各地区数据转化为全省数据，加到datas中
        for (int a = 0; a < 7; a++) {
            Double sum = (datas1.get(a)+datas2.get(a)+datas3.get(a)); //三种地区数据相加除3得到全省平均
            total2021.add(sum);
            Double avgSum = sum/3;
            DecimalFormat df1 = new DecimalFormat("#.00"); //保留小数点后两位
            String df2 = df1.format(avgSum);
            Double df3 = Double.parseDouble(df2); //转为double类型
            datas.add(df3);
        }

        List<Double> temp1_list1 = new ArrayList<>(); //江淮地区2020数据集合
        List<Double> temp1_list2 = new ArrayList<>(); //沿江地区2020数据集合
        List<Double> temp1_list3 = new ArrayList<>(); //沿淮地区2020数据集合
        List<Double> total2020 = new ArrayList<>(); //全省2020数据集合

        //分别循环将2020各地区的数据加入到对应的集合中
        for (int i = 0; i < 6; i++) {
            Double jianghuai2020 = temp1_list.get(i);
            temp1_list1.add(jianghuai2020);
        }
        for (int j = 0; j < 6; j++) {
            Double yanjiang2020 = temp1_list.get(j);
            temp1_list2.add(yanjiang2020);
        }
        for (int k = 0; k < 6; k++) {
            Double yanhuai2020 = temp1_list.get(k);
            temp1_list3.add(yanhuai2020);
        }

        //循环将2020各地区数据转化为全省数据
        total2020.add(0.0);
        for (int b = 0; b < 6; b++) {
            Double sum2020 = (temp1_list1.get(b)+temp1_list2.get(b)+temp1_list3.get(b));
            total2020.add(sum2020);
        }

        //相除得到百分比,加到pecents中
        pecents.add(0.0);
        for (int i = 1; i < 7; i++) {
            double pecent_total = (total2021.get(i)-total2020.get(i))/total2020.get(i);
            pecent_total*=100;
            pecent_total=Double.valueOf(df.format(pecent_total));
            pecents.add(pecent_total);
        }

        Map<String, Object> map = new HashMap<>();

        map.put("datas",datas);
        map.put("percents",pecents);
        map.put("fields",fields);
        return map;
    }
    /*
    表7-2：年度监测县(市、区)水稻单季稻收获进度调查
    crop_01_01_table_07_02Analysis_1：柱状图理论产量(kg/亩)
     */
    @Override
    public Map<String, Object> crop_01_01_table_07_02Analysis_1(String years) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //1.通过地区类型查出数据0,2,4为往年数据，1，3，5为今年数据
        DecimalFormat df = new DecimalFormat("0.00");
        String[] areaCates = {"江淮地区","沿江地区","沿淮地区"};
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        //String thisYear = yearFormat.format(new Date());    //今年
        String thisYear = years;
        List<String> sqlFields = new ArrayList<>();
        for (int i = 4; i < 10; i++) {
            sqlFields.add("avg(field_"+i+"_2_1) as field_"+i+"_2_1");
        }
        List<crop_01_01_table_07_02> items_7_2 = new ArrayList<>();
        for (String areaCate : areaCates) {
            crop_01_01_table_07_02 item = conditionsAnalysisMapper.crop_01_01_table_07_02Analysis_1(areaCate, sqlFields, thisYear);
            items_7_2.add(item);
        }
        List<String> datas = new ArrayList<>();
        for (crop_01_01_table_07_02 item : items_7_2) {
            if (item==null||item.getField_3()==null){
                //该条数据为空则直接插0
                for (int i = 0; i < 6; i++) {
                    datas.add("0.00");
                }
                continue;
            }
            for (int i = 4; i < 10; i++) {
                Method m1 = item.getClass().getMethod("getField_"+i+"_2_1");
                String invoke = (String) m1.invoke(item);
                if (invoke==null||invoke.equals("")){
                    datas.add("0.00");
                }else{
                    datas.add(df.format(Double.valueOf(invoke)));
                }
            }
        }

        List<String> datas1 = new ArrayList<>();
        List<String> datas2 = new ArrayList<>();
        List<String> datas3 = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            String jianghuai = datas.get(i);
            datas1.add(jianghuai);
        }
        for (int j = 6; j < 12; j++) {
            String yanjiang = datas.get(j);
            datas2.add(yanjiang);
        }
        for (int k = 12; k < 18; k++) {
            String yanhuai = datas.get(k);
            datas3.add(yanhuai);
        }

        for (int a = 0; a < 6; a++) {
            double jh = Double.parseDouble(datas1.get(a));
            double yj = Double.parseDouble(datas2.get(a));
            double yh = Double.parseDouble(datas3.get(a));
            double sum = jh+yj+yh;
            String Sum = Double.toString(sum);
            datas.add(Sum);
        }

        Map<String, Object> map = new HashMap<>();

        map.put("datas",datas);
        return map;
    }
    /*
    表7-3：年度监测县(市、区)水稻单季稻测产表
    crop_01_01_table_07_03Analysis_1：柱状图显示不同栽种方式下的三个地区类型的理论产量
     */
    @Override
    public Map<String, Object> crop_01_01_table_07_03Analysis_1(String Year) throws Exception{
        //1.通过地区类型查出数据
        String[] areaCates = {"江淮地区","沿江地区","沿淮地区"};
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String thisYear = yearFormat.format(new Date());    //今年
        String thisYear = yearFormat.format(new Date());    //今年
        String lastYear = String.valueOf(Integer.parseInt(thisYear)-1);  //去年
        String[] years = {lastYear,thisYear};
        List<crop_01_01_table_07_03> items = new ArrayList<>();
        for (String cate : areaCates) {
            for (String year : years) {
                crop_01_01_table_07_03 item = conditionsAnalysisMapper.crop_01_01_table_07_03Analysis_1(cate,year);
                items.add(item);
            }
        }
        System.out.println(items);
        //柱状图的数据list
        List<Double> datas = new ArrayList<>();
        //折线图的数据---数据全部*100以展现成百分比的形式
        List<Double> percents = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        fields.add("");
        DecimalFormat df = new DecimalFormat("0.00");
        String[] allFields = {"全县平均水平","人工移栽","机械插秧","盘育抛秧","无盘旱育抛秧","撒直播","机条播"};
        //2.解析出自己所需的数据
        for (int i =0;i<items.size();i++) {
            crop_01_01_table_07_03 item = items.get(i);
            if(item==null||item.getField_3_6()==null){
                //该地区该年份数据不存在,直接补零
                if (i%2==0){
                    for(int l=0;l<8;l++){
                        percents.add(0.0);
                    }
                }else{
                    for(int l=0;l<8;l++){
                        datas.add(0.0);
                    }
                }
                continue;
            }
            //取出2021年的理论产量数据
            if (item.getModification_time().toString().contains(thisYear)){
                datas.add(0.0);
                for (int j = 3;j<10;j++){
                    Method m1 =(Method)  item.getClass().getMethod(("get" + "Field_"+j+"_6"));
                    String temp1 = (String) m1.invoke(item);
                    if(temp1==null||temp1.equals("")){
                        datas.add(0.0);
                        continue;
                    }
                    Double data = Double.valueOf(temp1);
                    data = Double.valueOf(df.format(data));
                    datas.add(data);
                }
                //取出2021年相较于2020年的产量变化比
            }else if(item.getModification_time().toString().contains(lastYear)){
                crop_01_01_table_07_03 item_2021 = items.get((i+1));
                System.out.println(item_2021);
                percents.add(0.0);
                if (item_2021==null){
                    for (int j = 0; j < 7; j++) {
                        percents.add(0.0);
                    }
                    continue;
                }else{
                    for (int  k = 3;k<10;k++){
                    Method m1 =(Method)  item.getClass().getMethod(("get" + "Field_"+k+"_6"));
                    String temp1 = (String) m1.invoke(item);
                    Method m2 =(Method)  item_2021.getClass().getMethod(("get" + "Field_"+k+"_6"));
                    String temp2 = (String) m2.invoke(item_2021);
                    System.out.println(temp1);
                    System.out.println(temp2);
                    if(temp1==null||temp1.equals("")||temp2==null||temp2.equals("")){
                        percents.add(0.0);
                        continue;
                    }
                    double percent = (Double.valueOf(temp2) - Double.valueOf(temp1)) / Double.valueOf(temp1);
                    percent*=100;
                    percent = Double.valueOf(df.format(percent));
                    System.out.println(percent);
                    percents.add(percent);
                }
                }
            }
        }
        Map<String, Object> map = new HashMap<>();


        map.put("datas",datas);
        map.put("percents",percents);
        map.put("fields",fields);
        return map;
    }
    /*
    表10-1：年度监测县(市、区)水稻单季稻测产表
    getMonitoringCode:通过县查出所有的监测点代码
     */
    @Override
    public Map<String, Object> crop_01_01_table_10_01Analysis_1(String monitoring_code,String year)throws Exception {
        Map<String, Object> map = new HashMap<>();
        //1.通过监测点代码查出数据
        crop_01_01_table_10_01 item = conditionsAnalysisMapper.crop_01_01_table_10_01Analysis_1(monitoring_code,year);
//        System.out.println(1111);
//        System.out.println(item);
//        System.out.println(1111);
        if (item==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        //2.解析出图的横纵坐标
        List<Double> datas = new ArrayList<>();
        for (int i =7;i<12;i++){
            //date为横坐标日期，data为数据
            Method m2 =(Method)  item.getClass().getMethod(("get" + "Field_"+i+"_3"));
            String data =(String) m2.invoke(item);
            if (data==null||data.equals("")){
                datas.add(Double.valueOf(df.format(0.00)));
                continue;
            }
            datas.add(Double.valueOf(df.format(Double.valueOf(data))));
        }
        //将数据封装好传到前台
        map.put("datas",datas);
        return map;
    }

    @Override
    public Map<String, Object> crop_01_01_table_10_01Analysis_2(String areaCate,String Year) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //生成sql语句
        List<String> sqlFields = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        //String thisYear = yearFormat.format(new Date());    //今年
        String thisYear = Year;    //今年
        for (int i = 7; i < 12; i++) {
            sqlFields.add("avg(field_"+i+"_2) as field_"+i+"_2,avg(field_"+i+"_3) as field_"+i+"_3");
        }
        String year = thisYear;
        crop_01_01_table_10_01 item = conditionsAnalysisMapper.crop_01_01_table_10_01Analysis_2(areaCate,sqlFields,year);
        System.out.println(1256577);
        System.out.println(item);
        if (item==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>(); //千粒重数据
        List<String> datas_2 = new ArrayList<>(); //灌浆速率数据
        for (int i = 7; i < 12; i++) {
            Method m1 = item.getClass().getMethod("getField_" + i + "_2");
            String invoke1 = (String) m1.invoke(item);
            if (invoke1==null||invoke1.equals("")){
                datas_1.add("0.00");
            }else{
                datas_1.add(df.format(Double.valueOf(invoke1)));
            }
            Method m2 = item.getClass().getMethod("getField_" + i + "_3");
            String invoke2 = (String) m2.invoke(item);
            if (invoke2==null||invoke2.equals("")){
                datas_2.add("0.00");
            }else{
                datas_2.add(df.format(Double.valueOf(invoke2)));
            }
        }
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    @Override
    public List<String> getMonitoringCode(String area,String years) {
//        System.out.println(years);
        return conditionsAnalysisMapper.getMonitoringCode(area,years);
    }


    /*************************************一季稻结束*********************************************/
    /*************************************二季稻开始*********************************************/
    /*
    表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
     */
    @Override
    public Map<String, Object> crop_01_02_table_01_02Analysis_1(String area,String year) throws Exception{
        Map<String, Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(area);
        //1.通过县名查出数据
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year1 = yearFormat.format(new Date());
        List<crop_01_01_table_01_02> items_01_02 = conditionsAnalysisMapper.crop_01_02_table_01_02Analysis_1(area,year);
        //快速排序一下
        quickSort.quickSort(items_01_02);
        Collections.reverse(items_01_02);
        //解析出横坐标
        if (items_01_02.size()>=10){
            items_01_02 = items_01_02.subList(0,10);
        }
        Collections.reverse(items_01_02);
        List<String> cates = new ArrayList<>();
        List<String> datas = new ArrayList<>();
        for (crop_01_01_table_01_02 crop_01_01_table_01_02 : items_01_02) {
            if (crop_01_01_table_01_02.getPin_zhong_name()==null){
                continue;
            }
            cates.add(crop_01_01_table_01_02.getPin_zhong_name());
            datas.add(df.format(Double.valueOf(crop_01_01_table_01_02.getArea_data())));
        }
        map.put("cates",cates);
        map.put("datas",datas);
        //将数据封装好传到前台
        return map;
    }
    /*
    表3-2-1  20xx年度监测县(市、区)水稻苗情评价动态列表（晚稻）
     */
    @Override
    public Map<String, Object> crop_01_02_table_03_06Analysis_1(String areaCate,String area,String year)throws Exception {
        //通过地区类型查出数据
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String year = yearFormat.format(new Date());
        //根据地区类型查出数据
        List<String> sqlFields = new ArrayList<>();
        for (int i = 5; i < 14; i++) {
            sqlFields.add("avg(field_"+i+"_2_1) as field_"+i+"_2_1,avg(field_"+i+"_3_1) as field_"+i+"_3_1,avg(field_"+i+"_4_1) as field_"+i+"_4_1");
        }
        crop_01_02_table_03_04 item = conditionsAnalysisMapper.crop_01_02_table_03_06Analysis_1(areaCate,area,sqlFields,year);
        if (item==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");

        List<Double> datas1 = new ArrayList<>();
        List<Double> datas2 = new ArrayList<>();
        List<Double> datas3 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int k =1;
        //解析数据
        for (int i = 5;i<14;i++){
            Method m1 = item.getClass().getMethod(("getField_" + i + "_2_1"));
            Method m2 = item.getClass().getMethod(("getField_" + i + "_3_1"));
            Method m3 = item.getClass().getMethod(("getField_" + i + "_4_1"));
            String data1 = (String) m1.invoke(item);
            String data2 = (String) m2.invoke(item);
            String data3 = (String) m3.invoke(item);
            if(data1==null||data1.equals("")||
                    data2==null||data2.equals("")||
                    data3==null||data3.equals("")){
                break;
            }
            fields.add("进程"+(k++));
            datas1.add(Double.valueOf(df.format(Double.valueOf(data1))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(data2))));
            datas3.add(Double.valueOf(df.format(Double.valueOf(data3))));

        }
        //将数据封装好传到前台
        map.put("fields",fields);
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("datas3",datas3);
        return map;
    }
    /*
    表4-1  20xx年度监测县(市、区)双季稻生育进度调查表（移栽稻）
     */
    @Override
    public Map<String, Object> crop_01_02_table_04_01Analysis_1(String areaCate,String riceCate,String cultivationMethod,String year) throws Exception{
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String year = yearFormat.format(new Date());
        //根据地区类型查出数据
        List<String> sqlFields = new ArrayList<>();
        for (int i = 9; i < 16; i++) {
            sqlFields.add("avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4");
        }
        //通过地区类型查出数据
        crop_01_02_table_04_01 item = conditionsAnalysisMapper.crop_01_02_table_04_01Analysis_1(areaCate,riceCate,cultivationMethod,sqlFields,year);
        if(item==null||item.getField_9_3()==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");

        List<Double> datas1 = new ArrayList<>();
        List<Double> datas2 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        int k = 1;
        //解析数据
        for (int i = 9;i<16;i++){
            Method m1 = item.getClass().getMethod(("getField_" + i + "_3"));
            Method m2 = item.getClass().getMethod(("getField_" + i + "_4"));
            String data1 = (String) m1.invoke(item);
            String data2 = (String) m2.invoke(item);
            if (data1==null||data1.equals("")||
                    data2==null||data2.equals("")){
                break;
            }
            fields.add("进程"+(k++));
            datas1.add(Double.valueOf(df.format(Double.valueOf(data1))));
            datas2.add(Double.valueOf(df.format(Double.valueOf(data2))));
        }
        //将数据封装好传到前台
        map.put("fields",fields);
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        return map;
    }
    /*
    表4-3  20xx年度水稻监测点茎蘖数叶面积构成表
     */
    @Override
    public Map<String, Object> crop_01_02_table_04_03Analysis_1(String areaCate, String strainCategory, String cultivationMethod,String year)throws Exception {
        Map<String, Object> map = new HashMap<>();
        //通过地区类型查出数据
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        String year = yearFormat.format(new Date());
        List<String> sqlFields = new ArrayList<>();
        for (int i = 10; i < 13; i++) {
            sqlFields.add("avg(field_"+i+"_2) as field_"+i+"_2,avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4");
        }
        crop_01_02_table_04_03 item = conditionsAnalysisMapper.crop_01_02_table_04_03Analysis_1(areaCate,strainCategory,cultivationMethod,sqlFields,year);
        if (item==null){
            return map;
        }
        if (item.getField_10_2()==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");

        List<String> datas1 = new ArrayList<>();
        List<String> datas2 = new ArrayList<>();
        List<String> datas3 = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        String[] allFields = {"拔节期","幼穗分化1-2期","幼穗分化3-4期"};
        //解析数据
        for (int i = 10;i<13;i++){
            Method m1 = item.getClass().getMethod(("getField_" + i + "_2"));
            Method m2 = item.getClass().getMethod(("getField_" + i + "_3"));
            Method m3 = item.getClass().getMethod(("getField_" + i + "_4"));
            String data1 = (String) m1.invoke(item);
            String data2 = (String) m2.invoke(item);
            String data3 = (String) m3.invoke(item);
            if(data1==null||data1.equals("")||
                    data2==null||data2.equals("")||
                    data3==null||data3.equals("")){
                break;
            }
            fields.add(allFields[i-10]);
            datas1.add(df.format(Double.valueOf(data1)));
            datas2.add(df.format(Double.valueOf(data2)));
            datas3.add(df.format(Double.valueOf(data3)));

        }
        //将数据封装好传到前台
        map.put("fields",fields);
        map.put("datas1",datas1);
        map.put("datas2",datas2);
        map.put("datas3",datas3);
        return map;
    }
    /*
    表10-1 20xx年度水稻监测点灌浆速率调查表（早稻）
     */
    @Override
    public Map<String, Object> crop_01_02_table_10_01Analysis_1(String monitoring_code,String year)throws Exception {
        Map<String, Object> map = new HashMap<>();
        //1.通过监测点代码查出数据
        crop_01_02_table_10_01 item = conditionsAnalysisMapper.crop_01_02_table_10_01Analysis_1(monitoring_code,year);
        System.out.println("双季稻表格10-01"+item);
        if (item==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        //2.解析出图的横纵坐标
        List<Double> datas = new ArrayList<>();
        for (int i =8;i<13;i++){
            //date为横坐标日期，data为数据
            Method m2 =(Method)  item.getClass().getMethod(("get" + "Field_"+i+"_3"));
            String data =(String) m2.invoke(item);
            if (data==null||data.equals("")){
                datas.add(Double.valueOf(df.format(0.00)));
                continue;
            }
            datas.add(Double.valueOf(df.format(Double.valueOf(data))));
        }
        //2.解析出图的横纵坐标
        map.put("datas",datas);
        return map;
    }

    @Override
    public Map<String, Object> crop_01_02_table_10_01Analysis_2(String areaCate, String year) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //生成sql语句
        List<String> sqlFields = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String thisYear = yearFormat.format(new Date());    //今年
        for (int i = 8; i < 12; i++) {
            sqlFields.add("avg(field_"+i+"_2) as field_"+i+"_2,avg(field_"+i+"_3) as field_"+i+"_3");
        }
//        String year = thisYear;
        crop_01_02_table_10_01 item = conditionsAnalysisMapper.crop_01_02_table_10_01Analysis_2(areaCate,sqlFields,year);
        if (item==null){
            return map;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> datas_1 = new ArrayList<>(); //千粒重数据
        List<String> datas_2 = new ArrayList<>(); //灌浆速率数据
        for (int i = 8; i < 12; i++) {
            Method m1 = item.getClass().getMethod("getField_" + i + "_2");
            String invoke1 = (String) m1.invoke(item);
            if (invoke1==null||invoke1.equals("")){
                datas_1.add("0.00");
            }else{
                datas_1.add(df.format(Double.valueOf(invoke1)));
            }
            Method m2 = item.getClass().getMethod("getField_" + i + "_3");
            String invoke2 = (String) m2.invoke(item);
            if (invoke2==null||invoke2.equals("")){
                datas_2.add("0.00");
            }else{
                datas_2.add(df.format(Double.valueOf(invoke2)));
            }
        }
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }

    @Override
    public List<String> getMonitoringCode_2(String area,String year) {
        return conditionsAnalysisMapper.getMonitoringCode_2(area,year);
    }


}
