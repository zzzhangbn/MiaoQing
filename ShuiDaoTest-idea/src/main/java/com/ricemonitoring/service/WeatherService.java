package com.ricemonitoring.service;

import com.ricemonitoring.pojo.weatherinformation;
import com.ricemonitoring.pojo.weatheroption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface WeatherService {

    //天气数据展示
    List<weatherinformation> getWeatherData();
    //下拉框选择
    List<weatheroption> getselect();
    //搜索
    List<weatherinformation> getsearch(String cityType, String yearType, String monthType);

    List<weatherinformation> datesearch(String daterange1, String daterange2, String cityTypes);
    //日期前下拉框选择
    List<weatheroption> getselects();
    //echart1
    List<weatherinformation> echart1(String cityType, String yearType, String monthType);
    //echart2
    List<weatherinformation> echart2(String cityType, String yearType, String monthType);
    //echart3的所选日期的当年数据
    List<weatherinformation> echart3_all(String daterange1, String daterange2, String cityTypes, String daterange3, String daterange4, String daterange5, String daterange6);
    //echart5积温
    Map<String,Integer> echart5_jiwensearch(String daterange1, String daterange2);
}
