package com.ricemonitoring.service.Impl;

import com.ricemonitoring.mapper.WeatherMapper;
import com.ricemonitoring.pojo.weatherinformation;
import com.ricemonitoring.pojo.weatheroption;
import com.ricemonitoring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherMapper weatherMapper;

    @Override
    public List<weatherinformation> getWeatherData() {
        return weatherMapper.getWeatherData();
    }

    @Override
    public List<weatheroption> getselect() {
        return weatherMapper.getselect();
    }

    @Override
    public List<weatheroption> getselects() {
        return weatherMapper.getselects();
    }

    @Override
    public List<weatherinformation> getsearch(String cityType, String yearType, String monthType) {
        return weatherMapper.getsearch(cityType, yearType, monthType);
    }

    @Override
    public List<weatherinformation> datesearch(String daterange1, String daterange2, String cityTypes) {
        return weatherMapper.datesearch(daterange1, daterange2, cityTypes);
    }

    @Override
    public List<weatherinformation> echart1(String cityType, String yearType, String monthType) {
//        List <weatherinformation> item=weatherMapper.echart1(cityType,yearType,monthType);
//        //System.out.println(item);

        return weatherMapper.echart1(cityType, yearType, monthType);
    }

    @Override
    public List<weatherinformation> echart2(String cityType, String yearType, String monthType){
        return weatherMapper.echart2(cityType, yearType, monthType);
    }

    @Override
    public List<weatherinformation> echart3_all(String daterange1, String daterange2, String cityTypes,String daterange3, String daterange4,String daterange5, String daterange6) {
        List<weatherinformation> weatherinformationList1 = weatherMapper.echart3_one(daterange1, daterange2, cityTypes);
        List<weatherinformation> weatherinformationList2 = weatherMapper.echart3_two(daterange3, daterange4, cityTypes);
        List<weatherinformation> weatherinformationList3 = weatherMapper.echart3_three(daterange5, daterange6, cityTypes);
        System.out.println(weatherinformationList1);
        System.out.println("*********************************************************");
        System.out.println(weatherinformationList2);
        System.out.println("*********************************************************");
        System.out.println(weatherinformationList3);
        System.out.println("*********************************************************");
        //System.out.println(weatherinformationList1.get(0).getRealtime_temperature());

        /**
         * 当前年份数据计算 LinkedHashMap保证map.put有序
         */
        Map<String, Object> map1 = new LinkedHashMap<>();
        //日平均气温（已改为活动积温）
        float sum_realtime_temperature1 = 0;
        float realtime_temperature_mean1;
        for(int i = 0;i<weatherinformationList1.size();i++){
            sum_realtime_temperature1 += Integer.parseInt(weatherinformationList1.get(i).getRealtime_temperature());
        }
        //平均气温经过下面一步
        realtime_temperature_mean1 = sum_realtime_temperature1/weatherinformationList1.size();
        //System.out.println(realtime_temperature_mean)
        map1.put("sum_realtime_temperature1",sum_realtime_temperature1);

        //有效积温
        float sum_jiwen1 = 0;
        List<Integer> jiwen1=new ArrayList<>();
        for (int i = 0;i<weatherinformationList1.size();i++){
            if(Integer.parseInt(weatherinformationList1.get(i).getRealtime_temperature())-10<0){
                continue;
            }
            jiwen1.add(Integer.parseInt(weatherinformationList1.get(i).getRealtime_temperature())-10);
        }
        //System.out.println("dsa##############");
        //System.out.println(jiwen1);
        if (jiwen1==null){
            sum_jiwen1 = 0;
        }
        else{
            for(int i = 0;i<jiwen1.size();i++){
                sum_jiwen1 += jiwen1.get(i);
            };
        }
        //System.out.println(sum_jiwen1);
//        for(int i = 0;i<weatherinformationList1.size();i++){
//            sum_jiwen1 += Integer.parseInt(weatherinformationList1.get(i).getRealtime_temperature())-10;
//        }
        map1.put("sum_jiwen1",sum_jiwen1);

        //总降雨量
        float sum_precipitations1 = 0;
        for(int i = 0;i<weatherinformationList1.size();i++){
            sum_precipitations1 += Double.parseDouble(weatherinformationList1.get(i).getPrecipitations());
        }
        map1.put("sum_precipitations1",sum_precipitations1);

        //降雨日数
        float jiangyuday1 = 0;
        for(int i = 0;i<weatherinformationList1.size();i++){
            if(Double.parseDouble(weatherinformationList1.get(i).getPrecipitations())>0){
                jiangyuday1++;
            }
        }
        map1.put("jiangyuday1",jiangyuday1);

        //总日照时数
        float sum_rizhaoshishu1 = 0;
        for(int i = 0;i<weatherinformationList1.size();i++){
            sum_rizhaoshishu1 += Integer.parseInt(weatherinformationList1.get(i).getSunlight_time());
        }
        map1.put("sum_rizhaoshishu1",sum_rizhaoshishu1);

        //晴好天气
        float qinghaoday1 = 0;
        for(int i = 0;i<weatherinformationList1.size();i++){
            if(Double.parseDouble(weatherinformationList1.get(i).getPrecipitations())==0){
                qinghaoday1++;
            }
        }
        map1.put("qinghaoday1",qinghaoday1);
        //平均湿度
        float sum_humidity1 = 0;
        float humidity_mean1;
        for(int i = 0;i<weatherinformationList1.size();i++){
            sum_humidity1 += Double.parseDouble(weatherinformationList1.get(i).getHumidity());
        }
        humidity_mean1 = sum_humidity1/weatherinformationList1.size();
        String humidity_mean11 = String.format("%.2f", humidity_mean1); //保留两位小数
        humidity_mean1 = Float.parseFloat(humidity_mean11); //String转Float
        //System.out.println(realtime_temperature_mean)
        map1.put("humidity_mean1",humidity_mean1);
        //平均PM2.5值
        float sum_pm1 = 0;
        float pm_mean1;
        for(int i = 0;i<weatherinformationList1.size();i++){
            sum_pm1 += Double.parseDouble(weatherinformationList1.get(i).getPm());
        }
        pm_mean1 = sum_pm1/weatherinformationList1.size();
        //System.out.println(realtime_temperature_mean)
        map1.put("pm_mean1",pm_mean1);

        //将当年所有数据放在一个List中
        List<weatherinformation> result1 = new ArrayList(map1.values());
        System.out.println(result1);

        /**
         * 当前的上一年年份数据计算 LinkedHashMap保证map.put有序
         */
        Map<String, Object> map2 = new LinkedHashMap<>();
        //日平均气温（已经改为活动积温）
        float sum_realtime_temperature2 = 0;
        float realtime_temperature_mean2;
        for(int i = 0;i<weatherinformationList2.size();i++){
            sum_realtime_temperature2 += Integer.parseInt(weatherinformationList2.get(i).getRealtime_temperature());
        }
        realtime_temperature_mean2 = sum_realtime_temperature2/weatherinformationList2.size();
        //System.out.println(realtime_temperature_mean)
        map2.put("sum_realtime_temperature2",sum_realtime_temperature2);

        //有效积温
        float sum_jiwen2 = 0;
        List<Integer> jiwen2=new ArrayList<>();
        for(int i = 0;i<weatherinformationList2.size();i++) {
            if(Integer.parseInt(weatherinformationList2.get(i).getRealtime_temperature())-10<0){
                continue;
            }
            jiwen2.add(Integer.parseInt(weatherinformationList2.get(i).getRealtime_temperature())-10);
        }
        if (jiwen2==null){
            sum_jiwen2 = 0;
        }
        else{
            for(int i = 0;i<jiwen2.size();i++){
                sum_jiwen2 += jiwen2.get(i);
            };
        }
        map2.put("sum_jiwen2",sum_jiwen2);

        //总降雨量
        float sum_precipitations2 = 0;
        for(int i = 0;i<weatherinformationList2.size();i++){
            sum_precipitations2 += Double.parseDouble(weatherinformationList2.get(i).getPrecipitations());
        }
        map2.put("sum_precipitations2",sum_precipitations2);

        //降雨日数
        float jiangyuday2 = 0;
        for(int i = 0;i<weatherinformationList2.size();i++){
            if(Double.parseDouble(weatherinformationList2.get(i).getPrecipitations())>0){
                jiangyuday2++;
            }
        }
        map2.put("jiangyuday2",jiangyuday2);

        //总日照时数
        float sum_rizhaoshishu2 = 0;
        for(int i = 0;i<weatherinformationList2.size();i++){
            sum_rizhaoshishu2 += Integer.parseInt(weatherinformationList2.get(i).getSunlight_time());
        }
        map2.put("sum_rizhaoshishu2",sum_rizhaoshishu2);

        //晴好天气
        float qinghaoday2 = 0;
        for(int i = 0;i<weatherinformationList2.size();i++){
            if(Double.parseDouble(weatherinformationList2.get(i).getPrecipitations())==0){
                qinghaoday2++;
            }
        }
        map2.put("qinghaoday2",qinghaoday2);
        //平均湿度
        float sum_humidity2 = 0;
        float humidity_mean2;
        for(int i = 0;i<weatherinformationList2.size();i++){
            sum_humidity2 += Double.parseDouble(weatherinformationList2.get(i).getHumidity());
        }
        humidity_mean2 = sum_humidity2/weatherinformationList2.size();
        String humidity_mean22 = String.format("%.2f", humidity_mean2); //保留两位小数
        humidity_mean2 = Float.parseFloat(humidity_mean22); //String转Float
        //System.out.println(realtime_temperature_mean)
        map2.put("humidity_mean2",humidity_mean2);
        //平均PM2.5值
        float sum_pm2 = 0;
        float pm_mean2;
        for(int i = 0;i<weatherinformationList2.size();i++){
            sum_pm2 += Double.parseDouble(weatherinformationList2.get(i).getPm());
        }
        pm_mean2 = sum_pm2/weatherinformationList2.size();
        //System.out.println(realtime_temperature_mean)
        map2.put("pm_mean2",pm_mean2);

        //将当年所有数据放在一个List中
        List<weatherinformation> result2 = new ArrayList(map2.values());
        System.out.println(result2);

        /**
         * 当前年份的上上一年数据计算 LinkedHashMap保证map.put有序
         */
        Map<String, Object> map3 = new LinkedHashMap<>();
        //日平均气温
        float sum_realtime_temperature3 = 0;
        float realtime_temperature_mean3;
        for(int i = 0;i<weatherinformationList3.size();i++){
            sum_realtime_temperature3 += Integer.parseInt(weatherinformationList3.get(i).getRealtime_temperature());
        }
        realtime_temperature_mean3 = sum_realtime_temperature3/weatherinformationList3.size();
        //System.out.println(realtime_temperature_mean)
        map3.put("sum_realtime_temperature3",sum_realtime_temperature3);

        //有效积温
        float sum_jiwen3 = 0;
        List<Integer> jiwen3=new ArrayList<>();
        for(int i = 0;i<weatherinformationList3.size();i++) {
            if(Integer.parseInt(weatherinformationList3.get(i).getRealtime_temperature())-10<0){
                continue;
            }
            jiwen3.add(Integer.parseInt(weatherinformationList3.get(i).getRealtime_temperature())-10);
        }
        if (jiwen3==null){
            sum_jiwen3 = 0;
        }
        else{
            for(int i = 0;i<jiwen3.size();i++){
                sum_jiwen3 += jiwen3.get(i);
            };
        }
        map3.put("sum_jiwen3",sum_jiwen3);

        //总降雨量
        float sum_precipitations3 = 0;
        for(int i = 0;i<weatherinformationList3.size();i++){
            sum_precipitations3 += Double.parseDouble(weatherinformationList3.get(i).getPrecipitations());
        }
        map3.put("sum_precipitations3",sum_precipitations3);

        //降雨日数
        float jiangyuday3 = 0;
        for(int i = 0;i<weatherinformationList3.size();i++){
            if(Double.parseDouble(weatherinformationList3.get(i).getPrecipitations())>0){
                jiangyuday3++;
            }
        }
        map3.put("jiangyuday3",jiangyuday3);

        //总日照时数
        float sum_rizhaoshishu3 = 0;
        for(int i = 0;i<weatherinformationList3.size();i++){
            sum_rizhaoshishu3 += Integer.parseInt(weatherinformationList3.get(i).getSunlight_time());
        }
        map3.put("sum_rizhaoshishu3",sum_rizhaoshishu3);

        //晴好天气
        float qinghaoday3 = 0;
        for(int i = 0;i<weatherinformationList3.size();i++){
            if(Double.parseDouble(weatherinformationList3.get(i).getPrecipitations())==0){
                qinghaoday3++;
            }
        }
        map3.put("qinghaoday3",qinghaoday3);
        //平均湿度
        float sum_humidity3 = 0;
        float humidity_mean3;
        for(int i = 0;i<weatherinformationList3.size();i++){
            sum_humidity3 += Double.parseDouble(weatherinformationList3.get(i).getHumidity());
        }
        humidity_mean3 = sum_humidity3/weatherinformationList3.size();
        String humidity_mean33 = String.format("%.2f", humidity_mean3); //保留两位小数
        humidity_mean3 = Float.parseFloat(humidity_mean33); //String转Float
        //System.out.println(realtime_temperature_mean)
        map3.put("humidity_mean3",humidity_mean3);
        //平均PM2.5值
        float sum_pm3 = 0;
        float pm_mean3;
        for(int i = 0;i<weatherinformationList3.size();i++){
            sum_pm3 += Double.parseDouble(weatherinformationList3.get(i).getPm());
        }
        pm_mean3 = sum_pm3/weatherinformationList3.size();
        //System.out.println(realtime_temperature_mean)
        map3.put("pm_mean3",pm_mean3);

        //将当年所有数据放在一个List中
        List<weatherinformation> result3 = new ArrayList(map3.values());
        System.out.println(result3);

        /**
         * 当前年份三年的平均数据计算 LinkedHashMap保证map.put有序
         */
        Map<String, Object> map4 = new LinkedHashMap<>();
        //日平均温度(活动积温)
//        float threeyears_realtime_temperature_mean = (float) Math.round(((realtime_temperature_mean1 + realtime_temperature_mean2 + realtime_temperature_mean3)/3)*100)/100;
        float sum_allhuodongjiwen = (float) Math.round(((sum_realtime_temperature1 + sum_realtime_temperature2 + sum_realtime_temperature3)/3)*100)/100;
        map4.put("sum_allhuodongjiwen",sum_allhuodongjiwen);
        //近三年平均有效积温
        float threeyears_jiwen_mean = (float) Math.round(((sum_jiwen1 +sum_jiwen2+sum_jiwen3)/3)*100)/100;
        map4.put("threeyears_jiwen_mean",threeyears_jiwen_mean);
        //近三年平均总降雨量
        float threeyears_sum_precipitations = (float) Math.round(((sum_precipitations1+sum_precipitations2+sum_precipitations3)/3)*100)/100;
        map4.put("threeyears_sum_precipitions",threeyears_sum_precipitations);
        //近三年平均降雨日数
        float threeyears_jiangyuday_mean = (float)Math.round(((jiangyuday1+jiangyuday2+jiangyuday3)/3)*100)/100;
        map4.put("threeyears_jiangyuday_mean",threeyears_jiangyuday_mean);
        //近三年平均总日照时数
        float threeyears_sum_rizhaoshishu_mean = (float) Math.round(((sum_rizhaoshishu1+sum_rizhaoshishu2+sum_rizhaoshishu3)/3)*100)/100;
        map4.put("threeyears_sum_rizhaoshishu_mean",threeyears_sum_rizhaoshishu_mean);
        //近三年平均晴好天气
        float threeyears_qinghaoday_mean = (float) Math.round(((qinghaoday1+qinghaoday2+qinghaoday3)/3)*100)/100;
        map4.put("threeyears_qinghaoday_mean",threeyears_qinghaoday_mean);
        //近三年平均湿度
        float threeyears_humidity_mean = (float) Math.round(((humidity_mean1+humidity_mean2+humidity_mean3)/3)*100)/100;
        map4.put("threeyears_humidity_mean",threeyears_humidity_mean);
        //近三年平均PM2.5值
        float threeyears_pm_mean = (float) Math.round(((pm_mean1+pm_mean2+pm_mean3)/3)*100)/100;
        map4.put("threeyears_pm_mean",threeyears_pm_mean);

        //将当前年份三年的平均所有数据放在一个List中
        List<weatherinformation> result4 = new ArrayList(map4.values());
        System.out.println(result4);


        List<weatherinformation> weatherinformationListAll = new ArrayList<>();
        weatherinformationListAll.addAll(result1);
        weatherinformationListAll.addAll(result2);
        weatherinformationListAll.addAll(result3);
        weatherinformationListAll.addAll(result4);
        //System.out.println(cityTypes);
        System.out.println(weatherinformationListAll);
        return weatherinformationListAll;
    }

    @Override
    public Map<String,Integer> echart5_jiwensearch(String daterange1, String daterange2) {
        //全省数据处理
        List<weatherinformation> echart5_quanshengjiwen = weatherMapper.echart5_quanshengjiwen(daterange1, daterange2);
        //全省活动积温
        int sum_quansheng_huodongjiwen = 0;
        for (weatherinformation i:echart5_quanshengjiwen) {
            sum_quansheng_huodongjiwen = sum_quansheng_huodongjiwen +Integer.parseInt(i.getRealtime_temperature());
            System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_quansheng_huodongjiwen = sum_quansheng_huodongjiwen/36;
        System.out.println("全省活动积温:"+mean_sum_quansheng_huodongjiwen);
        //全省有效积温
        int sum_quansheng_youxiaojiwen = 0;
        for (weatherinformation i:echart5_quanshengjiwen) {
            sum_quansheng_youxiaojiwen = sum_quansheng_youxiaojiwen +(Integer.parseInt(i.getRealtime_temperature())-10);
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_quansheng_youxiaojiwen = sum_quansheng_youxiaojiwen/36;
        System.out.println("全省有效积温:"+mean_sum_quansheng_youxiaojiwen);

        //江淮地区数据处理
        List<weatherinformation> echart5_jianghuaijiwen = weatherMapper.echart5_jianghuaijiwen(daterange1,daterange2);
        //江淮地区活动积温
        int sum_jianghuai_huodongjiwen = 0;
        for (weatherinformation i:echart5_jianghuaijiwen) {
            sum_jianghuai_huodongjiwen = sum_jianghuai_huodongjiwen +Integer.parseInt(i.getRealtime_temperature());
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_jianghuai_huodongjiwen = sum_jianghuai_huodongjiwen/20;
        System.out.println("江淮地区活动积温:"+mean_sum_jianghuai_huodongjiwen);
        //江淮地区有效积温
        int sum_jianghuai_youxiaojiwen = 0;
        for (weatherinformation i:echart5_jianghuaijiwen) {
            sum_jianghuai_youxiaojiwen = sum_jianghuai_youxiaojiwen +(Integer.parseInt(i.getRealtime_temperature())-10);
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_jianghuai_youxiaojiwen = sum_jianghuai_youxiaojiwen/20;
        System.out.println("江淮地区有效积温:"+mean_sum_jianghuai_youxiaojiwen);

        //沿江地区数据处理
        List<weatherinformation> echart5_yanjiangjiwen = weatherMapper.echart5_yanjiangjiwen(daterange1,daterange2);
        //江淮地区活动积温
        int sum_yanjiang_huodongjiwen = 0;
        for (weatherinformation i:echart5_yanjiangjiwen) {
            sum_yanjiang_huodongjiwen = sum_yanjiang_huodongjiwen +Integer.parseInt(i.getRealtime_temperature());
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_yanjiang_huodongjiwen = sum_yanjiang_huodongjiwen/13;
        System.out.println("沿江地区活动积温:"+mean_sum_yanjiang_huodongjiwen);
        //沿江地区有效积温
        int sum_yanjiang_youxiaojiwen = 0;
        for (weatherinformation i:echart5_yanjiangjiwen) {
            sum_yanjiang_youxiaojiwen = sum_yanjiang_youxiaojiwen +(Integer.parseInt(i.getRealtime_temperature())-10);
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_yanjiang_youxiaojiwen = sum_yanjiang_youxiaojiwen/13;
        System.out.println("沿江地区有效积温:"+mean_sum_yanjiang_youxiaojiwen);

        //沿淮地区数据处理
        List<weatherinformation> echart5_yanhuaijiwen = weatherMapper.echart5_yanhuaijiwen(daterange1,daterange2);
        //江淮地区活动积温
        int sum_yanhuai_huodongjiwen = 0;
        for (weatherinformation i:echart5_yanhuaijiwen) {
            sum_yanhuai_huodongjiwen = sum_yanhuai_huodongjiwen +Integer.parseInt(i.getRealtime_temperature());
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_yanhuai_huodongjiwen = sum_yanhuai_huodongjiwen/14;
        System.out.println("沿淮地区活动积温:"+mean_sum_yanhuai_huodongjiwen);
        //沿淮地区有效积温
        int sum_yanhuai_youxiaojiwen = 0;
        for (weatherinformation i:echart5_yanhuaijiwen) {
            sum_yanhuai_youxiaojiwen = sum_yanhuai_youxiaojiwen +(Integer.parseInt(i.getRealtime_temperature())-10);
            //System.out.println(i.getRealtime_temperature());
        }
        int mean_sum_yanhuai_youxiaojiwen = sum_yanhuai_youxiaojiwen/14;
        System.out.println("沿淮地区有效积温:"+mean_sum_yanhuai_youxiaojiwen);

        //汇总数据
        Map<String, Integer> jiwen_map = new LinkedHashMap<>();
        jiwen_map.put("quansheng_huodongjiwen",mean_sum_quansheng_huodongjiwen);
        jiwen_map.put("quansheng_youxiaojiwen",mean_sum_quansheng_youxiaojiwen);
        jiwen_map.put("jianghuai_huodongjiwen",mean_sum_jianghuai_huodongjiwen);
        jiwen_map.put("jianghuai_youxiaojiwen",mean_sum_jianghuai_youxiaojiwen);
        jiwen_map.put("yanjiang_huodongjiwen",mean_sum_yanjiang_huodongjiwen);
        jiwen_map.put("yanjiang_youxiaojiwen",mean_sum_yanjiang_youxiaojiwen);
        jiwen_map.put("yanhuai_huodongjiwen",mean_sum_yanhuai_huodongjiwen);
        jiwen_map.put("yanhuai_youxiaojiwen",mean_sum_yanhuai_youxiaojiwen);
        System.out.println(jiwen_map);
        return jiwen_map;

    }

    @Override
    public Map<String, Object> weatherAdd(weatherinformation weatherAdd) {
        Map<String,Object> map = new HashMap<>();
        String str = weatherAdd.getDates();
        String years = "";
        String months = "";
        years = str.substring(0,4);
        months = str.substring(5,7);
        if (months.charAt(0) == '0'){
            months = months.substring(1,2);
        }
        years += "年";
        months += "月";
        weatherAdd.setYears(years);
        weatherAdd.setMonths(months);
        weatherMapper.weatherAdd(weatherAdd);
        map.put("token", 200);
        return map;
    }

}




