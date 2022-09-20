package com.ricemonitoring.mapper;

import com.ricemonitoring.pojo.weatherinformation;
import com.ricemonitoring.pojo.weatheroption;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WeatherMapper {
    List<weatherinformation> getWeatherData();

    List<weatheroption> getselect();

    List<weatherinformation> getsearch(String cityType, String yearType, String monthType);

    List<weatherinformation> datesearch(String daterange1, String daterange2,String cityTypes);

    List<weatheroption> getselects();

    List<weatherinformation> echart1(String cityType, String yearType, String monthType);

    List<weatherinformation> echart2(String cityType, String yearType, String monthType);

    List<weatherinformation> echart3_one(String daterange1, String daterange2, String cityTypes);

    List<weatherinformation> echart3_two(String daterange3, String daterange4, String cityTypes);

    List<weatherinformation> echart3_three(String daterange5, String daterange6, String cityTypes);

    //echart5全省层面
    List<weatherinformation> echart5_quanshengjiwen(String daterange1, String daterange2);

    //echar5江淮地区
    List<weatherinformation> echart5_jianghuaijiwen(String daterange1, String daterange2);

    //echar5沿江地区
    List<weatherinformation> echart5_yanjiangjiwen(String daterange1, String daterange2);

    //echar5沿淮地区
    List<weatherinformation> echart5_yanhuaijiwen(String daterange1, String daterange2);
    void weatherAdd(weatherinformation weatherAdd);
}
