package com.ricemonitoring.service;

import com.ricemonitoring.pojo.research_report;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface researchReportMonitorService {
    void upload(String realName, String fileName, String crop, String userId);

    List<research_report> getFiles(String crop, String year,String people);

    Map<String, Object> yiZaiQiTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> formatHtmlStyle(String html) throws Exception;

    void saveReport(Map<String, Object> map);

    Map<String, Object> chouHuiQiTemplate_1(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException;

    Map<String, Object> chouHuiQiTemplate_2(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> chouHuiQiTemplate_3(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> fenLieQiTemplate(String country) throws ParseException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    Map<String, Object> guanJiangQiTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> yuCeChanTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;


    Map<String, Object> ceChanTemplateTwoZao(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> ceChanTemplateTwoWan(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> ceChanTemplateTwoZao_Sheng() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> ceChanTemplateTwoWan_Sheng() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

}
