package com.ricemonitoring.controller;

import com.ricemonitoring.pojo.weatherinformation;
import com.ricemonitoring.pojo.weatheroption;
import com.ricemonitoring.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
@SuppressWarnings("all")
@RestController
////解决跨域问题
@CrossOrigin
public class weatherController {
    @Autowired
    WeatherService weatherService;


    @GetMapping("/getWeatherData")
    public List<weatherinformation> getWeatherData() {
        List<weatherinformation> weatherinformationList = weatherService.getWeatherData();
        //System.out.println(weatherinformationList);
        return weatherinformationList;
    }

    //下拉框选择
    @GetMapping("/getselect")
    public List<weatheroption>getselect(){
        List<weatheroption> weatheroptionList = weatherService.getselect();
        return weatheroptionList;
    }
    //下拉框选择
    @GetMapping("/getselects")
    public List<weatheroption>getselects(){
        List<weatheroption> weatheroptionLists = weatherService.getselects();
        return weatheroptionLists;
    }
    //查询
    @PostMapping("/getsearch")
    public List<weatherinformation> getsearch(@RequestParam("cityType") String cityType,
                                              @RequestParam("yearType") String yearType,
                                              @RequestParam("monthType") String monthType){
        List<weatherinformation> searchlist = weatherService.getsearch(cityType, yearType, monthType);
        //System.out.println(searchlist);
        return searchlist;
    }

    //按日期范围查询
    @PostMapping("/datesearch")
    public List<weatherinformation> datesearch(@RequestParam("daterange1") String daterange1,
                                               @RequestParam("daterange2") String daterange2,
                                               @RequestParam("cityTypes") String cityTypes){
        System.out.println(daterange1);
        System.out.println(daterange2);
        List<weatherinformation> datesearchlist = weatherService.datesearch(daterange1,daterange2,cityTypes);
        return datesearchlist;

    }

    //echart1
    @PostMapping("/echart1")
    public List<Object> echart1(@RequestParam("cityType") String cityType,
                                @RequestParam("yearType") String yearType,
                                @RequestParam("monthType") String monthType){
        List<weatherinformation> echart1data = weatherService.echart1(cityType, yearType, monthType);
        List<String> realtime_temperature = new ArrayList<>();
        for (weatherinformation list : echart1data){
            try{
                Method m1=list.getClass().getMethod("getRealtime_temperature");
                String invoke1 = (String) m1.invoke(list);
                realtime_temperature.add(invoke1);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        List<String> highest_temperature = new ArrayList<>();
        for (weatherinformation list : echart1data){
            try{
                Method m2=list.getClass().getMethod("getHighest_temperature");
                String invoke2 = (String) m2.invoke(list);
                highest_temperature.add(invoke2);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        List<String> minimum_temperature = new ArrayList<>();
        for (weatherinformation list : echart1data){
            try{
                Method m3=list.getClass().getMethod("getMinimum_temperature");
                String invoke3 = (String) m3.invoke(list);
                minimum_temperature.add(invoke3);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        //System.out.println(realtime_temperature);
        //将字段名赋值到map中
        Map<String, Object> map = new HashMap<>();
        map.put("realtime_temperature",realtime_temperature);
        map.put("highest_temperature",highest_temperature);
        map.put("minimum_temperature",minimum_temperature);
        //System.out.println(map);
        List<Object> collect = map.values().stream().collect(Collectors.toList());
        //System.out.println(collect);
        return collect;
    }

    //echart2
    @PostMapping("/echart2")
    public List<Object> echart2(@RequestParam("cityType") String cityType,
                                @RequestParam("yearType") String yearType,
                                @RequestParam("monthType") String monthType){
        List<weatherinformation> echart2data = weatherService.echart2(cityType, yearType, monthType);
        List<String> sunlight_time = new ArrayList<>();
        for (weatherinformation list : echart2data){
            try{
                Method m1=list.getClass().getMethod("getSunlight_time");
                String invoke1 = (String) m1.invoke(list);
                sunlight_time.add(invoke1);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        List<String> precipitations = new ArrayList<>();
        for (weatherinformation list : echart2data){
            try{
                Method m2=list.getClass().getMethod("getPrecipitations");
                String invoke2 = (String) m2.invoke(list);
                precipitations.add(invoke2);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        //将字段名赋值到map中
        Map<String, Object> map = new HashMap<>();
        map.put("sunlight_time",sunlight_time);
        map.put("precipitations",precipitations);
        //System.out.println(map);
        List<Object> collect2 = map.values().stream().collect(Collectors.toList());
        //System.out.println(collect2);
        return collect2;
    }

    //echart3的所选日期的当年数据
    @PostMapping("/echart3_all")
    public List<weatherinformation> echart3_all(@RequestParam("daterange1") String daterange1,
                                                @RequestParam("daterange2") String daterange2,
                                                @RequestParam("cityTypes") String cityTypes,
                                                @RequestParam("daterange3") String daterange3,
                                                @RequestParam("daterange4") String daterange4,
                                                @RequestParam("daterange5") String daterange5,
                                                @RequestParam("daterange6") String daterange6){
        //System.out.println(daterange3);
        List<weatherinformation> echart3_alllist = weatherService.echart3_all(daterange1,daterange2,cityTypes,daterange3,daterange4,daterange5,daterange6);
        System.out.println(echart3_alllist);
        return echart3_alllist;

    }

    //按日期范围查询
    @PostMapping("/echart5")
    public Map<String,Integer> echart5_jiwensearch(@RequestParam("daterange1") String daterange1, @RequestParam("daterange2") String daterange2){
        System.out.println(daterange1);
        System.out.println(daterange2);
        Map<String,Integer> echart5_jiwensearchList = weatherService.echart5_jiwensearch(daterange1,daterange2);
        return echart5_jiwensearchList;

    }


}
