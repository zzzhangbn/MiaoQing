package com.ricemonitoring.service;

import com.ricemonitoring.pojo.research_report;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 16:26
 */
public interface researchReportService {
    void upload(String realName, String fileName, String crop, String userId);

    List<research_report> getFiles(String crop, String year,String account,String code_name);
    String getaccount(String code_name);

    Map<String, Object> yizaiqiTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> formatHtmlStyle(String html) throws Exception;

    void saveReport(Map<String, Object> map);

    Map<String, Object> chouHuiQiTemplate_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException;

    Map<String, Object> chouHuiQiTemplate_2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> chouHuiQiTemplate_3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> fenLieQiTemplate() throws ParseException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    Map<String, Object> guanJiangQiTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> yuCeChanTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> zaodao_bozaiqiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> zaodao_fenLieQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> zaodao_chouHuiQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> zaodao_yuChanTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> wandao_fenleiqiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> wandao_yunhuiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> wandao_yuGuChanTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    Map<String, Object> zaodao_yunHuiQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
