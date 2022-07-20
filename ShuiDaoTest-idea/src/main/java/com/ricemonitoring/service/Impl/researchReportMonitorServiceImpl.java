package com.ricemonitoring.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.ricemonitoring.mapper.ConditionsAnalysisMapper;
import com.ricemonitoring.mapper.researchReportMapper;
import com.ricemonitoring.mapper.researchReportMonitorMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.researchReportMonitorService;
import com.ricemonitoring.util.quickSort;
import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class researchReportMonitorServiceImpl implements researchReportMonitorService {
    @Autowired
    researchReportMonitorMapper researchReportMonitorMapper;

    @Autowired
    ConditionsAnalysisMapper conditionsAnalysisMapper;

    @Autowired
    com.ricemonitoring.util.quickSort quickSort;

    @Override
    public void upload(String realName, String fileName, String crop, String userId) {
        //1.生成文件在线阅读的url
        String url = "http://139.224.41.123:8080/file/"+realName;
        //获取当前的时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        System.out.println("uplode"+date);
        //数据存储到数据库中
        researchReportMonitorMapper.upload(fileName,crop,date,url,userId);

    }

    @Override
    public List<research_report> getFiles(String crop, String year,String people) {
//        String area1 = researchReportMonitorMapper.getArea(country);
        return researchReportMonitorMapper.getFiles(crop,year,people);
    }

    @Override
    public Map<String, Object> yiZaiQiTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //全县种植总面积
        List<String> totalAreas = researchReportMonitorMapper.getTotalArea(thisYear,area1);
        Double totalArea = 0.0;
        if(totalAreas.size()>0){
            for (String area : totalAreas) {
                if(area!=null){
                    totalArea += Double.valueOf(area);
                }
            }
        }
        //1.一季稻种植茬口类型与面积比较以及种植类型与面积比较，对应表1-1，根据年份查出数据
        String[] years = {thisYear,lastYear};
        List<crop_01_01_table_01_01> items = new ArrayList<>();
        for (String year : years) {
            crop_01_01_table_01_01 crop_01_01_table_01_01 =  researchReportMonitorMapper.getStubbleArea(year,area1);
            items.add(crop_01_01_table_01_01);
        }
        //茬口数据
        List<String> stubbleList = new ArrayList<>();//各茬口亩数
        List<String> stubblePercents = new ArrayList<>();//各茬口占比
        List<String> stubbleList2 = new ArrayList<>();//比去年增加减少的亩数
        //种植方式数据
        List<String> cateList = new ArrayList<>();
        List<String> catePercents = new ArrayList<>();
        List<String> cateList2 = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.0");
        //获得今年去年的数据
        crop_01_01_table_01_01 item_2021 = items.get(0);
        crop_01_01_table_01_01 item_2020 = items.get(1);

        //本年度一季稻种植总面积
        if (item_2021 == null){
            map.put("totalArea_1_1","0.00");
            if (item_2020 == null){
                map.put("totalAreaSub_1_1","增加0.00");
            }else {
                map.put("totalAreaSub_1_1","减少"+item_2020.getField_2());
            }
        }else {
            if (item_2021.getField_2() == null){
                map.put("totalArea_1_1","0.00");
                if (item_2020 == null){
                    map.put("totalAreaSub_1_1","增加0.00");
                }else {
                    if (item_2020.getField_2() == null){
                        map.put("totalAreaSub_1_1","增加0.00");
                    }else {
                        map.put("totalAreaSub_1_1","减少"+item_2020.getField_2());
                    }
                }
            }else {
                map.put("totalArea_1_1",item_2021.getField_2());
                if (item_2020 == null){
                    map.put("totalAreaSub_1_1","增加"+item_2021.getField_2());
                }else {
                    map.put("totalAreaSub_1_1","减少"+item_2020.getField_2());
                    if(Double.valueOf(item_2021.getField_2())-Double.valueOf(item_2020.getField_2())>0){
                        map.put("totalAreaSub_1_1","增加"+df.format(Double.valueOf(item_2021.getField_2())-Double.valueOf(item_2020.getField_2())));
                    }else {
                        map.put("totalAreaSub_1_1","减少"+df.format(Double.valueOf(item_2020.getField_2())-Double.valueOf(item_2021.getField_2())));
                    }
                }
            }
        }

        //第一段解析出各个茬口的面积
        for(int j =1;j<7;j++){
                if(item_2021 ==null){//2021的数据为空，无记录
                    stubbleList.add("0.00");
                    stubblePercents.add("0.00");
                    if(item_2020 ==null){//2020的数据为空，无记录
                        stubbleList2.add("0.00");
                    }else{//2020的数据不为空，有记录
                        Method m2 = item_2020.getClass().getMethod("getField_6_" + j + "_1");
                        String data2 = (String) m2.invoke(item_2020);
                        if(data2 == null){
                            stubbleList2.add("0.00");
                        }else{
                            stubbleList2.add("减少"+df.format(Double.valueOf(data2)));
                        }
                    }
                }else{//2021年的数据不为空，有记录
                    Method m1 = item_2021.getClass().getMethod("getField_6_" + j + "_1");
                    String data1 = (String) m1.invoke(item_2021);
                    if(data1 == null){
                        stubbleList.add("0.00");
                        stubblePercents.add("0.00");
                        if(item_2020 ==null){//2020的数据为空，无记录
                            stubbleList2.add("0.00");
                        }else{//2020的数据不为空，有记录
                            Method m2 = item_2020.getClass().getMethod("getField_6_" + j + "_1");
                            String data2 = (String) m2.invoke(item_2020);
                            if(data2 == null){
                                stubbleList2.add("0.00");
                            }else{
                                stubbleList2.add("减少"+df.format(Double.valueOf(data2)));
                            }
                        }

                    }else{
                        stubbleList.add(df.format(Double.valueOf(data1)));
                        stubblePercents.add(df.format((Double.valueOf(data1) / Double.valueOf(item_2021.getField_2())) * 100));
                        if(item_2020 ==null){//2020的数据为空，无记录
                            stubbleList2.add("增加"+df.format(Double.valueOf(data1)));
                        }else{//2020的数据不为空，有记录
                            Method m2 = item_2020.getClass().getMethod("getField_6_" + j + "_1");
                            String data2 = (String) m2.invoke(item_2020);
                            if(data2 == null){
                                stubbleList2.add("增加"+df.format(Double.valueOf(data1)));
                            }else{
                                if (Double.valueOf(data2)>Double.valueOf(data1)){
                                    stubbleList2.add("减少"+df.format(Double.valueOf(data2)-Double.valueOf(data1)));
                                }else {
                                    stubbleList2.add("增加"+df.format(Double.valueOf(data1)-Double.valueOf(data2)));
                                }
                            }
                        }
                    }
                }
        }

        //第二段解析出种植类型的占比
        for(int k =1;k<9;k++){
            if(item_2021 ==null){//2021的数据为空，无记录
                cateList.add("0.00");
                catePercents.add("0.00");
                if(item_2020 ==null){//2020的数据为空，无记录
                    cateList2.add("0.00");
                }else{//2020的数据不为空，有记录
                    Method m2 = item_2020.getClass().getMethod("getField_5_" + k + "_1");
                    String data2 = (String) m2.invoke(item_2020);
                    if(data2 == null){
                        cateList2.add("0.00");
                    }else{
                        cateList2.add("减少100");
                    }
                }
            }else{//2021年的数据不为空，有记录
                Method m1 = item_2021.getClass().getMethod("getField_5_" + k + "_1");
                String data1 = (String) m1.invoke(item_2021);
                if(data1 == null){
                    cateList.add("0.00");
                    catePercents.add("0.00");
                    if(item_2020 ==null){//2020的数据为空，无记录
                        cateList2.add("0.00");
                    }else{//2020的数据不为空，有记录
                        Method m2 = item_2020.getClass().getMethod("getField_5_" + k + "_1");
                        String data2 = (String) m2.invoke(item_2020);
                        if(data2 == null){
                            cateList2.add("0.00");
                        }else{
                            cateList2.add("减少100");
                        }
                    }
                }else{
                    cateList.add(df.format(Double.valueOf(data1)));
                    catePercents.add(df.format((Double.valueOf(data1) / Double.valueOf(item_2021.getField_2())) * 100));
                    if(item_2020 ==null){//2020的数据为空，无记录
                        cateList2.add("增加100");
                    }else{//2020的数据不为空，有记录
                        Method m2 = item_2020.getClass().getMethod("getField_5_" + k + "_1");
                        String data2 = (String) m2.invoke(item_2020);
                        if(data2 == null  || Double.valueOf(data2) == 0.0  ){
                            cateList2.add("增加100");
                        }else{
                            if(Double.valueOf(data1) - Double.valueOf(data2) > 0){
                                cateList2.add("增加" + df.format((Double.valueOf(data1) - Double.valueOf(data2)) / Double.valueOf(data2) * 100));
                            }else{
                                cateList2.add("减少" + df.format((Double.valueOf(data2) - Double.valueOf(data1)) / Double.valueOf(data2) * 100));
                            }
                        }
                    }
                }
            }
        }
//        第一段各茬口数据
        map.put("stubbleList_1_1",stubbleList);
        map.put("stubblePercents_1_1",stubblePercents);
        map.put("stubbleList2_1_1",stubbleList2);
//        第二段各种植类型数据
        map.put("cateList_1_1",cateList);//亩数
        map.put("catePercent_1_1",catePercents);//占比
        map.put("cateList2_1_1",cateList2);//比去年增加或者减少的百分比


        //################################################
        //2.主导品种介绍 对应表1-2
        research_template data_1_2 = new research_template();
        //获取总面积
        Double totalArea_2_1 = 0.0;
        List<crop_01_01_table_01_02> area_table_01_02s  = conditionsAnalysisMapper.getTotalArea_2_1(thisYear,area1);
        for (crop_01_01_table_01_02 area_table_01_02 : area_table_01_02s) {
            if (area_table_01_02==null||area_table_01_02.getCounty_area()==null){
                continue;
            }
            totalArea_2_1+=Double.valueOf(area_table_01_02.getCounty_area());
        }
        //获取今年前10 的品种
        List<crop_01_01_table_01_02> crop_01_01_table_01_02s_now = conditionsAnalysisMapper.crop_01_01_table_01_02Analysis_1(area1, thisYear);
        quickSort.quickSort(crop_01_01_table_01_02s_now);
        Collections.reverse(crop_01_01_table_01_02s_now);
        if (crop_01_01_table_01_02s_now.size()>10){
            crop_01_01_table_01_02s_now = crop_01_01_table_01_02s_now.subList(0,10);
        }
        //获取去年前十的品种
        List<crop_01_01_table_01_02> crop_01_01_table_01_02s_front = conditionsAnalysisMapper.crop_01_01_table_01_02Analysis_1(area1, lastYear);
        quickSort.quickSort(crop_01_01_table_01_02s_front);
        Collections.reverse(crop_01_01_table_01_02s_front);
        if (crop_01_01_table_01_02s_front.size()>10){
            crop_01_01_table_01_02s_front = crop_01_01_table_01_02s_front.subList(0,10);
        }
        //计算今年前十品种总面积
        Double area_01_02_now = 0.0;
        for (crop_01_01_table_01_02 item : crop_01_01_table_01_02s_now) {
            if (item==null||item.getArea_data()==null){
                continue;
            }
            area_01_02_now+=Double.valueOf(item.getArea_data());
        }
        //计算去年的总面积
        Double area_1_2_front = 0.0;
        for (crop_01_01_table_01_02 item : crop_01_01_table_01_02s_front) {
            if (item==null||item.getArea_data()==null){
                continue;
            }
            area_1_2_front+=Double.valueOf(item.getArea_data());
        }
        //主导品种占地面积比去年增加或者减少
        if (area_01_02_now>area_1_2_front){
            data_1_2.setSubPercent("增加"+df.format(area_01_02_now-area_1_2_front));
        }else {
            data_1_2.setSubPercent("减少"+df.format(area_1_2_front-area_01_02_now));
        }
        //共计总面积
        data_1_2.setArea(df.format(area_01_02_now));

        if (totalArea_2_1==0.0){
            totalArea_2_1=1.0;
        }
        //面积占比
        data_1_2.setPercent(df.format(area_01_02_now/totalArea_2_1*100));

        map.put("data_1_2",data_1_2);
        map.put("cate_1_2",crop_01_01_table_01_02s_now);




        //###############################################################
        //3.播栽期插栽进度 表3-1


        List<String> areas = new ArrayList<>();
//        String area2 = researchReportMonitorMapper.getArea(country);
        for (String year : years) {
            String area = researchReportMonitorMapper.table_3_1(year,area1);
            if(area!=null&&!area.equals("")){
                areas.add(area);
            }
        }
        String area_2021 = null;
        String area_2020 = null;
        String areaSubPercent = null;
        if(areas.size()>1){
            for(int i=0;i<1;i++){
                //2021和2020年一级稻播栽面积
                area_2021 = areas.get(i);
                area_2020 = areas.get(i+1);
                if(Double.valueOf(area_2021)-Double.valueOf(area_2020)>0){
                    areaSubPercent ="增加"+df.format(((Double.valueOf(area_2021)-Double.valueOf(area_2020))/Double.valueOf(area_2020))*100);
                }else {
                    areaSubPercent = "减少"+df.format(((Double.valueOf(area_2020)-Double.valueOf(area_2021))/Double.valueOf(area_2020))*100);
                }
            }
            String areaPercent = df.format((Double.valueOf(area_2021)/totalArea)*100);
            map.put("table_3_1_area",area_2021);
            map.put("areaPercent_3_1",areaPercent);
            map.put("table_3_1_areaSubPercent",areaSubPercent);
        }
        else{
//            map.put("table_3_1_area",areas.get(0));
            map.put("table_3_1_area","0.00");
            map.put("areaPercent_3_1","0.00");
            map.put("table_3_1_areaSubPercent","增加0.00");
        }



        //######################苗情长势 对应表4-1和4-2
        String[] cropCates = {"中籼稻","中粳稻"};
        String[] tableNames = {"crop_01_01_table_04_01","crop_01_01_table_04_02"};
        List<crop_01_01_table_04_01> list_table_4_1 = new ArrayList<>();
//        String area3 = researchReportMonitorMapper.getArea(country);
        for (String cropCate : cropCates) {
            for (String tableName : tableNames) {
                crop_01_01_table_04_01 item = researchReportMonitorMapper.table_4_1(cropCate,tableName,thisYear,area1);
                list_table_4_1.add(item);
            }
        }
        map.put("list_4_1",list_table_4_1);


        return map;
    }

    @Override
    public Map<String, Object> formatHtmlStyle(String html) throws Exception {
        // 实现图片和样式处理的方法
        JSONArray picsArr = new JSONArray();

        // 缩小图片
        Document doc = Jsoup.parse(html);

        Elements elementsP = doc.getElementsByTag("p");
        for (int i = 0; i < elementsP.size(); i++) {
            Element element = elementsP.get(i);
            boolean hasImg = false;
            Elements elementsChildren = element.children();
            for (int j = 0; j < elementsChildren.size(); j++) {
                Element elementChild = elementsChildren.get(j);
                if (elementChild.nodeName().equals("img")) {
                    hasImg = true;
                    break;
                }
            }
            if (hasImg) {
                element.attr("style", "text-align: center ;");
            } else {
                element.attr("style",
                        "font-family: FangSong_GB2312 ;font-size:18px;text-indent: 2em ;line-height:34px;text-align:justify;");
            }

        }

        Elements elements = doc.getElementsByTag("img");
//            for (int i = 0; i < elements.size(); i++) {
//                Element element = elements.get(i);
//                String src = element.attr("src");
//                JSONObject picjo = new JSONObject();
//                picjo.put("index", i);
//                // 将网络图片转为 base64 格式
//                picjo.put("src", CommonUtils.urlToBase64(src));
//                picsArr.add(picjo);
//                element.attr("src", "" + i);
//            }
        Map<String, Object> map = new HashMap<>();
        map.put("html",doc.body().html());
        map.put("pics",picsArr);
        return map;
    }

    @Override
    public void saveReport(Map<String, Object> map) {
        //获取当前的时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        System.out.println("saveReport"+date);
        map.put("date",date);
        String url = "http://139.224.41.123";
        map.put("url",url);
        researchReportMonitorMapper.saveReport(map);

    }

    @Override
    public Map<String, Object> chouHuiQiTemplate_1(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMonitorMapper.table_7_1(thisYear,area1);
        //2.解析生育进程的数据
        Double totalArea = 0.0;
        Double area_1 = 0.0; //2021年8月20日前总面积
        Double area_2 = 0.0;//2020年8月20日前总面积
        Double area_3 = 0.0; //2021年8月20日-9月总面积
        Double area_4 = 0.0;//2020年8月20日-9月总面积
        DecimalFormat df = new DecimalFormat("0.0");
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_3()==null||item.getField_3().equals("")){
                continue;
            }
            //计算出全县总面积
            if (item.getModification_time().toString().contains(thisYear)) {
                totalArea += Double.valueOf(df.format(Double.valueOf(item.getField_3())));
            }
            //计算出8月20日前的面积
            for (int i = 4; i < 8; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);

                    if (area==null||area.equals("")){
                        break;
                    }
                    area_1 += Double.valueOf(df.format(Double.valueOf(area)));

                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_2 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }
            //计算出8月20日-9月的总面积
            for (int i = 8; i < 10; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_3 += Double.valueOf(df.format(Double.valueOf(area)));
                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_4 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }

        }
        //8月20日前的数据
        research_template data_1 = new research_template();
        data_1.setNum(df.format(area_1));
        data_1.setPercent(df.format(area_1 / totalArea*100));
        if (area_2==0.0){
            data_1.setSubPercent("增加0.0");
        }else{
            if (area_1 - area_2>0){
                data_1.setSubPercent("增加"+df.format(((area_1 - area_2) / area_2)*100));
            }else{
                data_1.setSubPercent("减少"+df.format(((area_2 - area_1) / area_2)*100));
            }
        }
        map.put("data_1", data_1);


        //8月20日-9月的数据
        research_template data_2 = new research_template();
        data_2.setNum(df.format(area_3));
        data_2.setPercent(df.format(area_3 / totalArea*100));
        if (area_4==0.0){
            data_2.setSubPercent("增加0.00");
        }else{
            if (area_3 - area_4>0){
                data_2.setSubPercent("增加"+df.format(((area_3 - area_4) / area_4)*100));
            }else{
                data_2.setSubPercent("减少"+df.format(((area_4 - area_3) )/ area_4*100));
            }
        }

        map.put("data_2", data_2);
        //3.解析出不同时段抽穗的产量水平
        //7月31号前
        Double area_5 = 0.0; //面积
        Double yxh_1 = 0.0;  //有笑橞
        Double zls_1 = 0.0;
        Double jsl_1 = 0.0;
        Double qlz_1 = 0.0;
        Double cl_2021_1 = 0.0;
        Double cl_2020_1 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getModification_time().toString().contains(thisYear)) {
                for (int i = 1; i < 7; i++) {
                    Method m1 = item.getClass().getMethod("getField_4_" + i);
                    String invoke1 = (String) m1.invoke(item);
                    if(invoke1==null||invoke1.equals("")){
                        break;
                    }
                    Double data = Double.valueOf(invoke1);
                    switch (i) {
                        case 1:
                            area_5 += data;
                            break;
                        case 2:
                            yxh_1 += data;
                            break;
                        case 3:
                            zls_1 += data;
                            break;
                        case 4:
                            jsl_1 += data;
                            break;
                        case 5:
                            qlz_1 += data;
                            break;
                        case 6:
                            cl_2021_1 += data;
                            break;
                    }
                }
            }
            if (item.getModification_time().toString().contains(lastYear)) {
                if (item.getField_4_6()==null||item.getField_4_6().equals("")){
                    continue;
                }
                cl_2020_1 += Double.valueOf(item.getField_4_6());
            }
        }
        research_template data_3 = new research_template();
        data_3.setArea(df.format(area_5));
        data_3.setPercent(df.format(area_5/totalArea*100));
        data_3.setYxh(df.format(yxh_1));
        data_3.setZls(df.format(zls_1));
        data_3.setJsl(df.format(jsl_1));
        data_3.setQlz(df.format(qlz_1));
        data_3.setChanliang_1(df.format(cl_2021_1));
        data_3.setChanliang_2(df.format(cl_2020_1));
        if (cl_2020_1==0.0){
            data_3.setPercent("增加0.0");
        }else{
            if(cl_2021_1-cl_2020_1>0){
                data_3.setSubPercent("增加"+df.format(((cl_2021_1-cl_2020_1)/cl_2020_1)*100));
            }else{
                data_3.setSubPercent("减少"+df.format(((cl_2020_1-cl_2021_1)/cl_2020_1)*100));
            }
        }

        map.put("data_3",data_3);
        //八月上旬
        Double area_6 =0.0;
        Double jsl_2 = 0.0;
        int i=1;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if(item.getModification_time().toString().contains(thisYear)){
                for (int k = 5;k<7;k++){
                    Method m1 = item.getClass().getMethod("getField_"+k+"_1");
                    String invoke1 = (String) m1.invoke(item);
                    if (invoke1==null||invoke1.equals("")){
                        break;
                    }
                    Double data1 = Double.valueOf(invoke1);
                    area_6+=data1;
                    Method m2 = item.getClass().getMethod("getField_"+k+"_4");
                    Double data2 = Double.valueOf((String) m2.invoke(item));
                    jsl_2+=data2;
                    ++i;
                }
            }
        }
        research_template data_4 = new research_template();
        data_4.setArea(df.format(area_6));
        data_4.setJsl(df.format(jsl_2/i));
        data_4.setPercent(df.format(area_6/totalArea*100));
        map.put("data_4",data_4);
        //八月下旬
        Double area_7 =0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            for(int k =7;k<9;k++){
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_"+k+"_" + 1);
                    String invoke = (String) m1.invoke(item);
                    if(invoke==null||invoke.equals("")){
                        break;
                    }
                    Double data = Double.valueOf((String) m1.invoke(item));
                    if(data==null||data.equals(0.0)){
                        break;
                    }
                    area_7+=data;
                }
            }

        }
        research_template data_5 = new research_template();
        data_5.setArea(df.format(area_7));
        data_5.setPercent(df.format(area_7/totalArea*100));
        map.put("data_5",data_5);
        //9月上旬
        Double area_8 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_9_1()==null||item.getField_9_1().equals("")){
                continue;
            }
            if(item.getModification_time().toString().contains(thisYear)){
                area_8+=Double.valueOf(item.getField_9_1());
            }
        }
        research_template data_6 = new research_template();
        data_6.setArea(area_8.toString());
        data_6.setPercent(df.format(area_8 / totalArea * 100));
        map.put("data_6",data_6);


        //叶龄、群体茎蘖数
        List<Double> data_7 = new ArrayList<>();
        List<Double> data_8 = new ArrayList<>();
        //中籼稻数据
        Double jinye_2021 = 0.0;
        Double jinye_2020 = 0.0;
        Double yeling_2021 = 0.0;
        Double yeling_2020 =0.0;
        Double jinye_rgyz =0.0;
        Double yeling_rgyz =0.0;
        Double jinye_jc =0.0;
        Double yeling_jc =0.0;
        Double jinye_pz =0.0;
        Double yeling_pz =0.0;
        //中粳稻数据
        Double jinye_2021_2 = 0.0;
        Double jinye_2020_2 = 0.0;
        Double yeling_2021_2 = 0.0;
        Double yeling_2020_2 =0.0;
        Double jinye_rgyz_2 =0.0;
        Double yeling_rgyz_2 =0.0;
        Double jinye_jc_2 =0.0;
        Double yeling_jc_2 =0.0;
        Double jinye_pz_2 =0.0;
        Double yeling_pz_2 =0.0;

        List<crop_01_01_table_04_01> items_4_1 =  researchReportMonitorMapper.table_4_1_2(thisYear,area1);
        List<crop_01_01_table_04_02> items_4_2 =  researchReportMonitorMapper.table_4_2(thisYear,area1);
        //抛栽数据
        for (crop_01_01_table_04_01 item : items_4_1) {
            if (item.getField_4()==null||item.getField_3()==null){
                continue;
            }
            if (item.getModification_time().toString().contains(thisYear)){
                //2021年数据
                if(item.getField_4().contains("中籼稻")){
                    //中籼稻的总体的叶龄以及茎蘖数
                    List<Double> datas1 = caculate(item);
                    if (datas1.size()>=1){
                        jinye_2021+=datas1.get(0);
                        yeling_2021+=datas1.get(1);
                    }
                    if(item.getField_7().equals("人工移栽")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_rgyz += datas2.get(0);
                            yeling_rgyz += datas2.get(1);
                        }

                    }else if (item.getField_7().equals("盘育抛栽")||item.getField_7().equals("无盘旱育抛栽")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_pz += datas2.get(0);
                            yeling_pz += datas2.get(1);
                        }

                    }else if(item.getField_7().equals("机械插秧")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_jc += datas2.get(0);
                            yeling_jc += datas2.get(1);
                        }
                    }
                }else if(item.getField_4().contains("中粳稻")){
                    //中籼稻的总体的叶龄以及茎蘖数
                    List<Double> datas1 = caculate(item);
                    if (datas1.size()>=1) {
                        jinye_2021_2 += datas1.get(0);
                        yeling_2021_2 += datas1.get(1);
                    }
                    if(item.getField_7().equals("人工移栽")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_rgyz_2 += datas2.get(0);
                            yeling_rgyz_2 += datas2.get(1);
                        }

                    }else if (item.getField_7().equals("盘育抛栽")||item.getField_7().equals("无盘旱育抛栽")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_pz_2 += datas2.get(0);
                            yeling_pz_2 += datas2.get(1);
                        }

                    }else if(item.getField_7().equals("机械插秧")){
                        List<Double> datas2 = caculate(item);
                        if (datas2.size()>=1) {
                            jinye_jc_2 += datas2.get(0);
                            yeling_jc_2 += datas2.get(1);
                        }
                    }

                }
            }else if(item.getModification_time().toString().contains(lastYear)){
                //2020年数据
                if(item.getField_4().contains("中籼稻")){
                    List<Double> datas =caculate(item);
                    if (datas.size()>=1) {
                        jinye_2020 += datas.get(0);
                        yeling_2020 += datas.get(1);
                    }
                }else if(item.getField_4().contains("中粳稻")){
                    List<Double> datas =caculate(item);
                    if (datas.size()>=1) {
                        jinye_2020_2 += datas.get(0);
                        yeling_2020_2 += datas.get(1);
                    }
                }
            }
        }
        Collections.addAll(data_7,Double.valueOf(df.format(jinye_2021)),
                Double.valueOf(df.format(jinye_2021-jinye_2020)),
                Double.valueOf(df.format(yeling_2021)),
                Double.valueOf(df.format(yeling_2021-yeling_2020)),
                jinye_rgyz,yeling_rgyz,jinye_jc,yeling_jc,jinye_pz,yeling_pz);
        map.put("data_7",data_7);
        Collections.addAll(data_8,Double.valueOf(df.format(jinye_2021_2)),
                Double.valueOf(df.format(jinye_2021_2-jinye_2020_2)),
                Double.valueOf(df.format(yeling_2021_2)),
                Double.valueOf(df.format(yeling_2021_2-yeling_2020_2)),
                jinye_rgyz_2,yeling_rgyz_2,jinye_jc_2,yeling_jc_2,jinye_pz_2,yeling_pz_2);
        map.put("data_8",data_8);

        //直播数据
        List<Double> data_9 = new ArrayList<>();
        Double jinye_zhibo_2021_zxd =0.0;
        Double jinye_zhibo_2020_zxd =0.0;
        Double yeling_zhibo_2021_zxd =0.0;
        Double yeling_zhibo_2020_zxd =0.0;
        Double jinye_zhibo_2021_zjd =0.0;
        Double jinye_zhibo_2020_zjd =0.0;
        Double yeling_zhibo_2021_zjd =0.0;
        Double yeling_zhibo_2020_zjd =0.0;
        for (crop_01_01_table_04_02 item : items_4_2) {
            if(item.getField_4()==null||item.getField_3()==null){
                continue;
            }
            if (item.getModification_time().toString().contains(thisYear)){
                if (item.getField_4().equals("中籼稻")){
                    List<Double> datas = caculate_2(item);
                    if (datas.size()>=1) {
                        jinye_zhibo_2021_zxd += datas.get(0);
                        yeling_zhibo_2021_zxd += datas.get(1);
                    }
                }else{
                    List<Double> datas = caculate_2(item);
                    if (datas.size()>=1) {
                        jinye_zhibo_2021_zjd += datas.get(0);
                        yeling_zhibo_2021_zjd += datas.get(1);
                    }
                }
            }else if(item.getModification_time().toString().contains(lastYear)){
                if (item.getField_4().equals("中籼稻")){
                    List<Double> datas = caculate_2(item);
                    if (datas.size()>=1) {
                        jinye_zhibo_2020_zxd += datas.get(0);
                        yeling_zhibo_2020_zxd += datas.get(1);
                    }
                }else{
                    List<Double> datas = caculate_2(item);
                    if (datas.size()>=1) {
                        jinye_zhibo_2020_zjd += datas.get(0);
                        yeling_zhibo_2020_zjd += datas.get(1);
                    }
                }
            }
        }

        Collections.addAll(data_9,Double.valueOf(df.format(jinye_zhibo_2021_zxd)),Double.valueOf(df.format(jinye_zhibo_2021_zxd-jinye_zhibo_2020_zxd)),
                Double.valueOf(df.format(yeling_zhibo_2021_zxd)),Double.valueOf(df.format(yeling_zhibo_2021_zxd-yeling_zhibo_2020_zxd)),
                jinye_zhibo_2021_zjd,jinye_zhibo_2021_zjd-jinye_zhibo_2020_zjd,
                yeling_zhibo_2021_zjd,yeling_zhibo_2021_zjd-yeling_zhibo_2020_zjd);
        map.put("data_9",data_9);

        return map;

    }
    //计算叶龄以及茎蘖数
    public List<Double> caculate(crop_01_01_table_04_01 item) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLine = ft.parse("2021-07-30");
        List<Double> list = new ArrayList<>();
        for (int i = 9; i < 15; i++) {
            Method m1 = item.getClass().getMethod("getField_" + i + "_1");
            Date tempDate = (Date) m1.invoke(item);
            if (tempDate==null){
                break;
            }
//            Date date = ft.parse(tempDate);
            if (tempDate.after(dateLine)){
                //时间超过抽橞期，计算出前一个进程的叶龄以及茎蘖数，然后直接跳出循环
                if(i==9){
                    Method m2 = item.getClass().getMethod("getField_" + (i) + "_4");
                    Double data_yl =Double.valueOf( (String) m2.invoke(item));
                    Method m3 = item.getClass().getMethod("getField_" + (i) + "_3");
                    Double data_jys =Double.valueOf( (String) m3.invoke(item));
                    list.add(data_jys);
                    list.add(data_yl);
                }
                else{
                    Method m2 = item.getClass().getMethod("getField_" + (i-1) + "_4");
                    Double data_yl =Double.valueOf( (String) m2.invoke(item));
                    Method m3 = item.getClass().getMethod("getField_" + (i-1) + "_3");
                    Double data_jys =Double.valueOf( (String) m3.invoke(item));
                    list.add(data_jys);
                    list.add(data_yl);
                }

                break;
            }
        }
        return list;
    }
    //计算叶龄以及茎蘖数
    public List<Double> caculate_2(crop_01_01_table_04_02 item) throws ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date dateLine = ft.parse("2021-07-30");
        List<Double> list = new ArrayList<>();
        for (int i = 9; i < 15; i++) {
            Method m1 = item.getClass().getMethod("getField_" + i + "_1");
            Date tempDate = (Date) m1.invoke(item);
            if (tempDate==null){
                break;
            }
//            Date date = ft.parse(tempDate);
            if (tempDate.after(dateLine)){
                //时间超过抽橞期，计算出前一个进程的叶龄以及茎蘖数，然后直接跳出循环
                if(i==8){
                    Method m2 = item.getClass().getMethod("getField_" + (i) + "_4");
                    Double data_yl =Double.valueOf( (String) m2.invoke(item));
                    Method m3 = item.getClass().getMethod("getField_" + (i) + "_3");
                    Double data_jys =Double.valueOf( (String) m3.invoke(item));
                    list.add(data_jys);
                    list.add(data_yl);
                }else{
                    Method m2 = item.getClass().getMethod("getField_" + (i-1) + "_4");
                    Double data_yl =Double.valueOf( (String) m2.invoke(item));
                    Method m3 = item.getClass().getMethod("getField_" + (i-1) + "_3");
                    Double data_jys =Double.valueOf( (String) m3.invoke(item));
                    list.add(data_jys);
                    list.add(data_yl);
                }

                break;
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> chouHuiQiTemplate_2(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMonitorMapper.table_7_1(thisYear,area1);
        //2.解析生育进程的数据
        Double totalArea = 0.0;
        Double area_1 = 0.0; //2021年8月20日前总面积
        Double area_2 = 0.0;//2020年8月20日前总面积
        Double area_3 = 0.0; //2021年8月20日-9月总面积
        Double area_4 = 0.0;//2020年8月20日-9月总面积
        DecimalFormat df = new DecimalFormat("0.0");
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_3()==null||item.getField_3().equals("")){
                continue;
            }
            //计算出全县总面积
            if (item.getModification_time().toString().contains(thisYear)) {
                totalArea += Double.valueOf(df.format(Double.valueOf(item.getField_3())));
            }
            //计算出8月20日前的面积
            for (int i = 4; i < 8; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_1 += Double.valueOf(df.format(Double.valueOf(area)));
                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_2 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }
            //计算出8月20日-9月的总面积
            for (int i = 8; i < 10; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_3 += Double.valueOf(df.format(Double.valueOf(area)));
                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_4 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }

        }
        //8月20日前的数据
        research_template data_1 = new research_template();
        data_1.setNum(df.format(area_1));
        data_1.setPercent(df.format(area_1 / totalArea*100));
        if (area_2==0.0){
            data_1.setSubPercent("增加0.0");
        }else{
            if (area_1 - area_2>0){
                data_1.setSubPercent("增加"+df.format(((area_1 - area_2) / area_2)*100));
            }else{
                data_1.setSubPercent("减少"+df.format(((area_2 - area_1) / area_2)*100));
            }
        }
        map.put("data_1", data_1);
        //8月20日-9月的数据
        research_template data_2 = new research_template();
        data_2.setNum(df.format(area_3));
        data_2.setPercent(df.format(area_3 / totalArea*100));
        if (area_4==0.0){
            data_2.setSubPercent("增加0.00");
        }else{
            if (area_3 - area_4>0){
                data_2.setSubPercent("增加"+df.format(((area_3 - area_4) / area_4)*100));
            }else{
                data_2.setSubPercent("减少"+df.format(((area_4 - area_3) )/ area_4*100));
            }
        }

        map.put("data_2", data_2);
        //3.解析出不同时段抽穗的产量水平
        //7月31号前
        Double area_5 = 0.0; //面积
        Double yxh_1 = 0.0;  //有笑橞
        Double zls_1 = 0.0;
        Double jsl_1 = 0.0;
        Double qlz_1 = 0.0;
        Double cl_2021_1 = 0.0;
        Double cl_2020_1 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getModification_time().toString().contains(thisYear)) {
                for (int i = 1; i < 7; i++) {
                    Method m1 = item.getClass().getMethod("getField_4_" + i);
                    String invoke1 = (String) m1.invoke(item);
                    if(invoke1==null||invoke1.equals("")){
                        break;
                    }
                    Double data = Double.valueOf(invoke1);
                    switch (i) {
                        case 1:
                            area_5 += data;
                            break;
                        case 2:
                            yxh_1 += data;
                            break;
                        case 3:
                            zls_1 += data;
                            break;
                        case 4:
                            jsl_1 += data;
                            break;
                        case 5:
                            qlz_1 += data;
                            break;
                        case 6:
                            cl_2021_1 += data;
                            break;
                    }
                }
            }
            if (item.getModification_time().toString().contains(lastYear)) {
                if (item.getField_4_6()==null||item.getField_4_6().equals("")){
                    continue;
                }
                cl_2020_1 += Double.valueOf(item.getField_4_6());
            }
        }
        research_template data_3 = new research_template();
        data_3.setArea(df.format(area_5));
        data_3.setPercent(df.format(area_5/totalArea*100));
        data_3.setYxh(df.format(yxh_1));
        data_3.setZls(df.format(zls_1));
        data_3.setJsl(df.format(jsl_1));
        data_3.setQlz(df.format(qlz_1));
        data_3.setChanliang_1(df.format(cl_2021_1));
        data_3.setChanliang_2(df.format(cl_2020_1));
        if (cl_2020_1==0.0){
            data_3.setSubPercent("增加0.00");
        }else{
            if(cl_2021_1-cl_2020_1>0){
                data_3.setSubPercent("增加"+df.format(((cl_2021_1-cl_2020_1)/cl_2020_1)*100));
            }else{
                data_3.setSubPercent("减少"+df.format(((cl_2020_1-cl_2021_1)/cl_2020_1)*100));
            }
        }

        map.put("data_3",data_3);
        //八月上旬
        Double area_6 =0.0;
        Double yxh_2 = 0.0;  //有笑橞
        Double zls_2 = 0.0;
        Double jsl_2 = 0.0;
        Double qlz_2 = 0.0;
        Double cl_2021_2 = 0.0;
        Double cl_2020_2 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            for(int k =5;k<7;k++){
                if (item.getModification_time().toString().contains(thisYear)) {
                    for (int i = 1; i < 7; i++) {
                        Method m1 = item.getClass().getMethod("getField_"+k+"_" + i);
                        String invoke = (String) m1.invoke(item);
                        if (invoke==null||invoke.equals("")){
                            break;
                        }
                        Double data = Double.valueOf((String) m1.invoke(item));
                        switch (i) {
                            case 1:
                                area_6 += data;
                                break;
                            case 2:
                                yxh_2 += data;
                                break;
                            case 3:
                                zls_2 += data;
                                break;
                            case 4:
                                jsl_2 += data;
                                break;
                            case 5:
                                qlz_2 += data;
                                break;
                            case 6:
                                cl_2021_2 += data;
                                break;
                        }
                    }
                }else if (item.getModification_time().toString().contains(lastYear)) {
                    switch (k){
                        case 5:
                            if (item.getField_5_6()!=null){
                                cl_2020_2 += Double.valueOf(item.getField_5_6());
                            }
                            break;
                        case 6:
                            if (item.getField_6_6()!=null) {
                                cl_2020_2 += Double.valueOf(item.getField_6_6());
                            }
                            break;
                    }
                }
            }

        }
        research_template data_4 = new research_template();
        data_4.setArea(df.format(area_6));
        data_4.setPercent(df.format(area_6/totalArea*100));
        data_4.setYxh(df.format(yxh_2));
        data_4.setZls(df.format(zls_2));
        data_4.setJsl(df.format(jsl_2));
        data_4.setQlz(df.format(qlz_2));
        data_4.setChanliang_1(df.format(cl_2021_2));
        data_4.setChanliang_2(df.format(cl_2020_2));
        if (cl_2020_2==0.0){
            data_4.setSubPercent("增加0.00");
        }else{
            if(cl_2021_2-cl_2020_2>0){
                data_4.setSubPercent("增加"+df.format((cl_2021_2-cl_2020_2)/cl_2020_2*100));
            }else{
                data_4.setSubPercent("减少"+df.format((cl_2020_2-cl_2021_2)/cl_2020_2*100));
            }
        }

        map.put("data_4",data_4);
        //八月下旬
        Double area_7 =0.0;
        Double jsl_3 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if(item.getModification_time().toString().contains(thisYear)){
                for (int k = 5;k<7;k++){
                    Method m1 = item.getClass().getMethod("getField_"+k+"_1");
                    String invoke = (String) m1.invoke(item);
                    Method m2 = item.getClass().getMethod("getField_"+k+"_4");
                    Object invoke1 = m2.invoke(item);
                    if (invoke==null||invoke.equals("")||invoke1==null||invoke1.equals("")){
                        break;
                    }
                    Double data1 = Double.valueOf((String) m1.invoke(item));
                    Double data2 = Double.valueOf((String) m2.invoke(item));
                    area_7+=data1;
                    jsl_3+=data2;
                }
            }
        }
        research_template data_5 = new research_template();
        data_5.setArea(df.format(area_7));
        data_5.setJsl(df.format(jsl_3));
        data_5.setPercent(df.format(area_7/totalArea*100));
        map.put("data_5",data_5);
        //9月上旬
        Double area_8 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_9_1()==null||item.getField_9_1().equals("")){
                continue;
            }
            if(item.getModification_time().toString().contains(thisYear)){
                area_8+=Double.valueOf(item.getField_9_1());
            }
        }
        research_template data_6 = new research_template();
        data_6.setArea(area_8.toString());
        data_6.setPercent(df.format(area_8 / totalArea * 100));
        map.put("data_6",data_6);

        return map;

    }

    @Override
    public Map<String, Object> chouHuiQiTemplate_3(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //1.7-1表格数据查询
        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMonitorMapper.table_7_1(thisYear,area1);
        //2.解析生育进程的数据
        Double totalArea = 0.0;
        Double area_1 = 0.0; //2021年8月20日前总面积
        Double area_2 = 0.0;//2020年8月20日前总面积
        Double area_3 = 0.0; //2021年8月20日-9月总面积
        Double area_4 = 0.0;//2020年8月20日-9月总面积
        DecimalFormat df = new DecimalFormat("0.0");
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_3()==null||item.getField_3().equals("")){
                continue;
            }
            //计算出全县总面积
            if (item.getModification_time().toString().contains(thisYear)) {
                totalArea += Double.valueOf(df.format(Double.valueOf(item.getField_3())));
            }
            //计算出8月20日前的面积
            for (int i = 4; i < 8; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_1 += Double.valueOf(df.format(Double.valueOf(area)));
                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_2 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }
            //计算出8月20日-9月的总面积
            for (int i = 8; i < 10; i++) {
                if (item.getModification_time().toString().contains(thisYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_3 += Double.valueOf(df.format(Double.valueOf(area)));
                } else if (item.getModification_time().toString().contains(lastYear)) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    String area = (String) m1.invoke(item);
                    if (area==null||area.equals("")){
                        break;
                    }
                    area_4 += Double.valueOf(df.format(Double.valueOf(area)));
                }
            }

        }
        //8月20日前的数据
        research_template data_1 = new research_template();
        data_1.setNum(df.format(area_1));
        data_1.setPercent(df.format(area_1 / totalArea*100));
        if (area_2==0.0){
            data_1.setSubPercent("增加0.00");
        }else{
            if (area_1 - area_2>0){
                data_1.setSubPercent("增加"+df.format(((area_1 - area_2) / area_2)*100));
            }else{
                data_1.setSubPercent("减少"+df.format(((area_2 - area_1) / area_2)*100));
            }
        }
        map.put("data_1", data_1);
        //8月20日-9月的数据
        research_template data_2 = new research_template();
        data_2.setNum(df.format(area_3));
        data_2.setPercent(df.format(area_3 / totalArea*100));
        if (area_4==0.0){
            data_2.setSubPercent("增加0.00");
        }else {
            if (area_3 - area_4 > 0) {
                data_2.setSubPercent("增加" + df.format(((area_3 - area_4) / area_4) * 100));
            } else {
                data_2.setSubPercent("减少" + df.format(((area_4 - area_1)) / area_4 * 100));
            }
        }
        map.put("data_2", data_2);
        //3.解析出不同时段抽穗的产量水平
        //7月31号前
        Double area_5 = 0.0; //面积
        Double yxh_1 = 0.0;  //有笑橞
        Double zls_1 = 0.0;
        Double jsl_1 = 0.0;
        Double qlz_1 = 0.0;
        Double cl_2021_1 = 0.0;
        Double cl_2020_1 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getModification_time().toString().contains(thisYear)) {
                for (int i = 1; i < 7; i++) {
                    Method m1 = item.getClass().getMethod("getField_4_" + i);
                    String invoke1 = (String) m1.invoke(item);
                    if(invoke1==null||invoke1.equals("")){
                        break;
                    }
                    Double data = Double.valueOf(invoke1);
                    switch (i) {
                        case 1:
                            area_5 += data;
                            break;
                        case 2:
                            yxh_1 += data;
                            break;
                        case 3:
                            zls_1 += data;
                            break;
                        case 4:
                            jsl_1 += data;
                            break;
                        case 5:
                            qlz_1 += data;
                            break;
                        case 6:
                            cl_2021_1 += data;
                            break;
                    }
                }
            }
            if (item.getModification_time().toString().contains(lastYear)) {
                if (item.getField_4_6()==null||item.getField_4_6().equals("")){
                    continue;
                }
                cl_2020_1 += Double.valueOf(item.getField_4_6());
            }
        }
        research_template data_3 = new research_template();
        data_3.setArea(df.format(area_5));
        data_3.setPercent(df.format(area_5/totalArea*100));
        data_3.setYxh(df.format(yxh_1));
        data_3.setZls(df.format(zls_1));
        data_3.setJsl(df.format(jsl_1));
        data_3.setQlz(df.format(qlz_1));
        data_3.setChanliang_1(df.format(cl_2021_1));
        data_3.setChanliang_2(df.format(cl_2020_1));
        if (cl_2020_1==0.0){
            data_3.setSubPercent("增加0.00");
        }else {
            if (cl_2021_1 - cl_2020_1 > 0) {
                data_3.setSubPercent("增加" + df.format(((cl_2021_1 - cl_2020_1) / cl_2020_1) * 100));
            } else {
                data_3.setSubPercent("减少" + df.format(((cl_2020_1 - cl_2021_1) / cl_2020_1) * 100));
            }
        }
        map.put("data_3",data_3);
        //八月上旬
        Double area_6 =0.0;
        Double yxh_2 = 0.0;  //有笑橞
        Double zls_2 = 0.0;
        Double jsl_2 = 0.0;
        Double qlz_2 = 0.0;
        Double cl_2021_2 = 0.0;
        Double cl_2020_2 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            for(int k =5;k<7;k++){
                if (item.getModification_time().toString().contains(thisYear)) {
                    for (int i = 1; i < 7; i++) {
                        Method m1 = item.getClass().getMethod("getField_"+k+"_" + i);
                        String invoke = (String) m1.invoke(item);
                        if (invoke==null||invoke.equals("")){
                            break;
                        }
                        Double data = Double.valueOf((String) m1.invoke(item));
                        switch (i) {
                            case 1:
                                area_6 += data;
                                break;
                            case 2:
                                yxh_2 += data;
                                break;
                            case 3:
                                zls_2 += data;
                                break;
                            case 4:
                                jsl_2 += data;
                                break;
                            case 5:
                                qlz_2 += data;
                                break;
                            case 6:
                                cl_2021_2 += data;
                                break;
                        }
                    }
                }else if (item.getModification_time().toString().contains(lastYear)) {
                    if (item.getField_5_6()!=null&&item.getField_6_6()!=null){
                        switch (k){
                            case 5:
                                cl_2020_2 += Double.valueOf(item.getField_5_6());
                                break;
                            case 6:
                                cl_2020_2 += Double.valueOf(item.getField_6_6());
                                break;
                        }
                    }
                }
            }

        }
        research_template data_4 = new research_template();
        data_4.setArea(df.format(area_6));
        data_4.setPercent(df.format(area_6/totalArea*100));
        data_4.setYxh(df.format(yxh_2));
        data_4.setZls(df.format(zls_2));
        data_4.setJsl(df.format(jsl_2));
        data_4.setQlz(df.format(qlz_2));
        data_4.setChanliang_1(df.format(cl_2021_2));
        data_4.setChanliang_2(df.format(cl_2020_2));
        if (cl_2020_2==0.0){
            data_4.setSubPercent("增加0.00");
        }else {
            if (cl_2021_2 - cl_2020_2 > 0) {
                data_4.setSubPercent("增加" + df.format((cl_2021_2 - cl_2020_2) / cl_2020_2 * 100));
            } else {
                data_4.setSubPercent("减少" + df.format((cl_2020_2 - cl_2021_2) / cl_2020_2 * 100));
            }
        }
        map.put("data_4",data_4);
        //八月下旬
        Double area_7 =0.0;
        Double yxh_3 = 0.0;  //有笑橞
        Double zls_3 = 0.0;
        Double jsl_3 = 0.0;
        Double qlz_3 = 0.0;
        Double cl_2021_3 = 0.0;
        Double cl_2020_3 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            for(int k =7;k<9;k++){
                if (item.getModification_time().toString().contains(thisYear)) {
                    for (int i = 1; i < 7; i++) {
                        Method m1 = item.getClass().getMethod("getField_"+k+"_" + i);
                        String invoke = (String) m1.invoke(item);
                        if (invoke==null||invoke.equals("")){
                            break;
                        }
                        Double data = Double.valueOf((String) m1.invoke(item));
                        switch (i) {
                            case 1:
                                area_7 += data;
                                break;
                            case 2:
                                yxh_3 += data;
                                break;
                            case 3:
                                zls_3 += data;
                                break;
                            case 4:
                                jsl_3 += data;
                                break;
                            case 5:
                                qlz_3 += data;
                                break;
                            case 6:
                                cl_2021_3 += data;
                                break;
                        }
                    }
                }else if (item.getModification_time().toString().contains(lastYear)) {
                    if (item.getField_7_6()!=null&&item.getField_8_6()!=null){
                        switch (k){
                            case 7:
                                cl_2020_3 += Double.valueOf(item.getField_7_6());
                            case 8:
                                cl_2020_3 += Double.valueOf(item.getField_8_6());
                        }
                    }
                }
            }

        }
        research_template data_5 = new research_template();
        data_5.setArea(df.format(area_7));
        data_5.setPercent(df.format(area_7/totalArea*100));
        data_5.setYxh(df.format(yxh_3));
        data_5.setZls(df.format(zls_3));
        data_5.setJsl(df.format(jsl_3));
        data_5.setQlz(df.format(qlz_3));
        data_5.setChanliang_1(df.format(cl_2021_3));
        data_5.setChanliang_2(df.format(cl_2020_3));
        if (cl_2020_3==0.0){
            data_5.setSubPercent("增加0.00");
        }else {
            if (cl_2021_3 - cl_2020_3 > 0) {
                data_5.setSubPercent("增加" + df.format((cl_2021_3 - cl_2020_3) / cl_2020_3 * 100));
            } else {
                data_5.setSubPercent("减少" + df.format((cl_2020_3 - cl_2021_3) / cl_2020_3 * 100));
            }
        }
        map.put("data_5",data_5);
        //9月上旬
        Double area_8 = 0.0;
        for (crop_01_01_table_07_01 item : items_7_1) {
            if (item.getField_9_1()==null||item.getField_9_1().equals("")){
                continue;
            }
            if(item.getModification_time().toString().contains(thisYear)){
                area_8+=Double.valueOf(item.getField_9_1());
            }
        }
        research_template data_6 = new research_template();
        data_6.setArea(area_8.toString());
        data_6.setPercent(df.format(area_8/totalArea*100));
        map.put("data_6",data_6);

        return map;
    }

    @Override
    public Map<String, Object> fenLieQiTemplate(String country) throws ParseException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        DecimalFormat df = new DecimalFormat("0.00");
        List<crop_01_01_table_04_01> items_4_1 = researchReportMonitorMapper.table_4_1_2(thisYear,area1);
        //数据初始化
        //1．栽插穴数、基本苗介绍
        //中籼稻
        Double muxue_zx_2021 = 0.0;
        Double muxue_zx_2020 = 0.0;
        Double jibenmiao_zx_2021 =0.0;
        Double jibenmiao_zx_2020 =0.0;
        Double muxue_zx_rg =0.0;
        Double jibenmao_zx_rg =0.0;
        Double muxue_zx_jc =0.0;
        Double jibenmao_zx_jc =0.0;
        Double muxue_zx_pz =0.0;
        Double jibenmao_zx_pz =0.0;
        //中粳稻
        Double muxue_zj_2021 = 0.0;
        Double muxue_zj_2020 = 0.0;
        Double jibenmiao_zj_2021 =0.0;
        Double jibenmiao_zj_2020 =0.0;
        Double muxue_zj_rg =0.0;
        Double jibenmao_zj_rg =0.0;
        Double muxue_zj_jc =0.0;
        Double jibenmao_zj_jc =0.0;
        Double muxue_zj_pz =0.0;
        Double jibenmao_zj_pz =0.0;
        //2.叶龄、群体茎蘖数介绍
        //中籼稻
        Double yeling_zx_2021 =0.0;
        Double jinye_zx_2021 =0.0;
        Double yeling_zx_2020 =0.0;
        Double jinye_zx_2020 =0.0;
        Double yeling_zx_rg = 0.0;
        Double jinye_zx_rg = 0.0;
        Double yeling_zx_jc = 0.0;
        Double jinye_zx_jc = 0.0;
        Double yeling_zx_pz = 0.0;
        Double jinye_zx_pz = 0.0;
        //中粳稻
        Double yeling_zj_2021 =0.0;
        Double jinye_zj_2021 =0.0;
        Double yeling_zj_2020 =0.0;
        Double jinye_zj_2020 =0.0;
        Double yeling_zj_rg = 0.0;
        Double jinye_zj_rg = 0.0;
        Double yeling_zj_jc = 0.0;
        Double jinye_zj_jc = 0.0;
        Double yeling_zj_pz = 0.0;
        Double jinye_zj_pz = 0.0;
        //表4-1数据解析
        for (crop_01_01_table_04_01 item : items_4_1) {
            if (item.getField_4()==null){
                continue;
            }
            switch (item.getField_4()){
                case "中籼稻":
                    if (item.getModification_time().toString().contains(thisYear)){
                        //第一段数据
                        String jibenmiao =item.getField_8_3();
                        String muxue = item.getField_8_4();
                        if (jibenmiao!=null&&!jibenmiao.equals("")){
                            jibenmiao_zx_2021+=Double.valueOf(jibenmiao);
                        }
                        if (muxue!=null&&!muxue.equals("")){
                            muxue_zx_2021+=Double.valueOf(muxue);
                        }
                        //第三段数据
                        List<Double> list = caculate(item);
                        if(list.size()>=1){
                            yeling_zx_2021+=list.get(0);
                            jinye_zx_2021+=list.get(1);
                        }
                        if(item.getField_7().equals("人工移栽")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zx_rg+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zx_rg+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zx_rg+=list1.get(0);
                                jinye_zx_rg+=list1.get(1);
                            }

                        }else if (item.getField_7().equals("盘育抛栽")||item.getField_7().equals("无盘旱育抛栽")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zx_pz+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zx_pz+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zx_pz+=list1.get(0);
                                jinye_zx_pz+=list1.get(1);
                            }
                        }else if(item.getField_7().equals("机械插秧")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zx_jc+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zx_jc+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zx_jc+=list1.get(0);
                                jinye_zx_jc+=list1.get(1);
                            }
                        }


                    }else if(item.getModification_time().toString().contains(lastYear)){
                        //第一段数据
                        String jibenmiao =item.getField_8_3();
                        String muxue = item.getField_8_4();
                        if (jibenmiao!=null&&!jibenmiao.equals("")){
                            jibenmiao_zx_2020+=Double.valueOf(jibenmiao);
                        }
                        if (muxue!=null&&!muxue.equals("")){
                            muxue_zx_2020+=Double.valueOf(muxue);
                        }
                        //第三段数据
                        List<Double> list = caculate(item);
                        if(list.size()>1){
                            yeling_zx_2020+=list.get(1);
                            jinye_zx_2020+=list.get(0);
                        }
                    }
                    break;
                case "中粳稻":
                    if (item.getModification_time().toString().contains(thisYear)){
                        //第一段数据
                        String jibenmiao =item.getField_8_3();
                        String muxue = item.getField_8_4();
                        if (jibenmiao!=null&&!jibenmiao.equals("")){
                            jibenmiao_zj_2021+=Double.valueOf(jibenmiao);
                        }
                        if (muxue!=null&&!muxue.equals("")){
                            muxue_zj_2021+=Double.valueOf(muxue);
                        }
                        //第三段数据
                        List<Double> list = caculate(item);
                        if(list.size()>=1){
                            yeling_zj_2021+=list.get(0);
                            jinye_zj_2021+=list.get(1);
                        }
                        if(item.getField_7().equals("人工移栽")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zj_rg+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zj_rg+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zj_rg+=list1.get(0);
                                jinye_zj_rg+=list1.get(1);
                            }

                        }else if (item.getField_7().equals("盘育抛栽")||item.getField_7().equals("无盘旱育抛栽")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zj_pz+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zj_pz+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zj_pz+=list1.get(0);
                                jinye_zj_pz+=list1.get(1);
                            }
                        }else if(item.getField_7().equals("机械插秧")){
                            //第一段数据
                            jibenmiao =item.getField_8_3();
                            muxue = item.getField_8_4();
                            if (jibenmiao!=null&&!jibenmiao.equals("")){
                                jibenmao_zj_jc+=Double.valueOf(jibenmiao);
                            }
                            if (muxue!=null&&!muxue.equals("")){
                                muxue_zj_jc+=Double.valueOf(muxue);
                            }
                            //第三段数据
                            List<Double> list1 = caculate(item);
                            if(list.size()>=1){
                                yeling_zj_jc+=list1.get(0);
                                jinye_zj_jc+=list1.get(1);
                            }
                        }


                    }else if(item.getModification_time().toString().contains(lastYear)){
                        //第一段数据
                        String jibenmiao =item.getField_8_3();
                        String muxue = item.getField_8_4();
                        if (jibenmiao!=null&&!jibenmiao.equals("")){
                            jibenmiao_zj_2020+=Double.valueOf(jibenmiao);
                        }
                        if (muxue!=null&&!muxue.equals("")){
                            muxue_zj_2020+=Double.valueOf(muxue);
                        }
                        //第三段数据
                        List<Double> list = caculate(item);
                        if(list.size()>1){
                            yeling_zj_2020+=list.get(1);
                            jinye_zj_2020+=list.get(0);
                        }
                    }
                    break;
            }

        }

        //中籼稻数据-第一段
        research_template data_1 = new research_template();
        data_1.setMuxue(df.format(muxue_zx_2021));

        if(muxue_zx_2021-muxue_zx_2020>0){
            data_1.setSub_muxue("增加"+df.format(muxue_zx_2021-muxue_zx_2020));
        }else {
            data_1.setSub_muxue("减少"+df.format(muxue_zx_2020-muxue_zx_2021));
        }

        data_1.setJibenmiao(df.format(jibenmiao_zx_2021));
        if(jibenmiao_zx_2021-jibenmiao_zx_2020>0){
            data_1.setSub_jibenmiao("增加"+df.format(jibenmiao_zx_2021-jibenmiao_zx_2020));
        }else {
            data_1.setSub_jibenmiao("减少"+df.format(jibenmiao_zx_2020-jibenmiao_zx_2021));
        }

        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1,df.format(muxue_zx_rg),df.format(jibenmao_zx_rg),
                df.format(muxue_zx_jc),df.format(jibenmao_zx_jc),df.format(muxue_zx_pz),df.format(jibenmao_zx_pz));
        data_1.setDatalist(list1);

        //中粳稻数据-第二段
        research_template data_2 = new research_template();
        data_2.setMuxue(df.format(muxue_zj_2021));
        if(muxue_zj_2021-muxue_zj_2020>0){
            data_2.setSub_muxue("增加"+df.format(muxue_zj_2021-muxue_zj_2020));
        }else {
            data_2.setSub_muxue("减少"+df.format(muxue_zj_2020-muxue_zj_2021));
        }
        data_2.setJibenmiao(df.format(jibenmiao_zj_2021));
        if(jibenmiao_zj_2021-jibenmiao_zj_2020>0){
            data_2.setSub_jibenmiao("增加"+df.format(jibenmiao_zj_2021-jibenmiao_zj_2020));
        }else {
            data_2.setSub_jibenmiao("减少"+df.format(jibenmiao_zj_2020-jibenmiao_zj_2021));
        }
        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2,df.format(muxue_zj_rg),df.format(jibenmao_zj_rg),
                df.format(muxue_zj_jc),df.format(jibenmao_zj_jc),df.format(muxue_zj_pz),df.format(jibenmao_zj_pz));
        data_2.setDatalist(list2);
        //中籼稻数据-第三段
        research_template data_3 = new research_template();
        data_3.setYeling(df.format(yeling_zx_2021));
        if(yeling_zx_2021>yeling_zx_2020){
            data_3.setSub_yeling("增加"+df.format(yeling_zx_2021-yeling_zx_2020));
        }else{
            data_3.setSub_yeling("减少"+df.format(yeling_zx_2020-yeling_zx_2021));
        }
        data_3.setJinye(df.format(jinye_zx_2021));
        if(jinye_zx_2021>jinye_zx_2020){
            data_3.setSub_jinye("增加"+df.format(jinye_zx_2021-jinye_zx_2020));
        }else{
            data_3.setSub_jinye("减少"+df.format(jinye_zx_2020-jinye_zx_2021));
        }
        List<String> list3 = new ArrayList<>();
        Collections.addAll(list3,df.format(yeling_zx_rg),df.format(jinye_zx_rg),df.format(yeling_zx_jc),
                df.format(jinye_zx_jc),df.format(yeling_zx_pz),df.format(jinye_zx_pz));
        data_3.setDatalist(list3);
        //中籼稻数据-第三段
        research_template data_4 = new research_template();
        data_4.setYeling(df.format(yeling_zj_2021));
        if(yeling_zj_2021>yeling_zj_2020){
            data_4.setSub_yeling("增加"+df.format(yeling_zj_2021-yeling_zj_2020));
        }else{
            data_4.setSub_yeling("减少"+df.format(yeling_zj_2020-yeling_zj_2021));
        }
        data_4.setJinye(df.format(jinye_zj_2021));
        if(jinye_zj_2021>jinye_zj_2020){
            data_4.setSub_jinye("增加"+df.format(jinye_zj_2021-jinye_zj_2020));
        }else{
            data_4.setSub_jinye("减少"+df.format(jinye_zj_2020-jinye_zj_2021));
        }
        List<String> list4 = new ArrayList<>();
        Collections.addAll(list4,df.format(yeling_zj_rg),df.format(jinye_zj_rg),df.format(yeling_zj_jc),
                df.format(jinye_zj_jc),df.format(yeling_zj_pz),df.format(jinye_zj_pz));
        data_4.setDatalist(list4);
        //表4-2数据解析
        List<crop_01_01_table_04_02> items_4_2 = researchReportMonitorMapper.table_4_2(thisYear,area1);
        //中籼稻数据
        Double jibenmiao_zhibo_zx_2021 =0.0;
        Double jibenmiao_zhibo_zx_2020 =0.0;
        Double yeling_zhibo_zx_2021 =0.0;
        Double yeling_zhibo_zx_2020 =0.0;
        Double jinye_zhibo_zx_2021 =0.0;
        Double jinye_zhibo_zx_2020 =0.0;
        //中粳稻数据
        Double jibenmiao_zhibo_zj_2021 =0.0;
        Double jibenmiao_zhibo_zj_2020 =0.0;
        Double yeling_zhibo_zj_2021 =0.0;
        Double yeling_zhibo_zj_2020 =0.0;
        Double jinye_zhibo_zj_2021 =0.0;
        Double jinye_zhibo_zj_2020 =0.0;

        data_1.setJibenmiao_zhibo(df.format(jibenmiao_zhibo_zx_2021));
        if(jibenmiao_zhibo_zx_2021>jibenmiao_zhibo_zx_2020){
            data_1.setJibenmiao_zhibo_sub("增加"+df.format(jibenmiao_zhibo_zx_2021-jibenmiao_zhibo_zx_2020));
        }else{
            data_1.setJibenmiao_zhibo_sub("减少"+df.format(jibenmiao_zhibo_zx_2020-jibenmiao_zhibo_zx_2021));
        }
        data_2.setJibenmiao_zhibo(df.format(jibenmiao_zhibo_zj_2021));
        if(jibenmiao_zhibo_zj_2021>jibenmiao_zhibo_zj_2020){
            data_2.setJibenmiao_zhibo_sub("增加"+df.format(jibenmiao_zhibo_zj_2021-jibenmiao_zhibo_zj_2020));
        }else{
            data_2.setJibenmiao_zhibo_sub("减少"+df.format(jibenmiao_zhibo_zj_2020-jibenmiao_zhibo_zj_2021));
        }
        data_3.setYeling_zhibo(df.format(yeling_zhibo_zx_2021));
        if(yeling_zhibo_zx_2021>yeling_zhibo_zx_2020){
            data_3.setYeling_zhibo_sub("增加"+df.format(yeling_zhibo_zx_2021-yeling_zhibo_zx_2020));
        }else{
            data_3.setYeling_zhibo_sub("减少"+df.format(yeling_zhibo_zx_2020-yeling_zhibo_zx_2021));
        }
        data_3.setJinye_zhibo(df.format(jinye_zhibo_zx_2021));
        if(jinye_zhibo_zx_2021>jinye_zhibo_zx_2020){
            data_3.setJinye_zhibo_sub("增加"+df.format(jinye_zhibo_zx_2021-jinye_zhibo_zx_2020));
        }else{
            data_3.setJinye_zhibo_sub("减少"+df.format(jinye_zhibo_zx_2020-jinye_zhibo_zx_2021));
        }
        data_4.setYeling_zhibo(df.format(yeling_zhibo_zj_2021));
        if(yeling_zhibo_zj_2021>yeling_zhibo_zj_2020){
            data_4.setYeling_zhibo_sub("增加"+df.format(yeling_zhibo_zj_2021-yeling_zhibo_zj_2020));
        }else{
            data_4.setYeling_zhibo_sub("减少"+df.format(yeling_zhibo_zj_2020-yeling_zhibo_zj_2021));
        }
        data_4.setJinye_zhibo(df.format(jinye_zhibo_zj_2021));
        if(jinye_zhibo_zj_2021>jinye_zhibo_zj_2020){
            data_4.setJinye_zhibo_sub("增加"+df.format(jinye_zhibo_zj_2021-jinye_zhibo_zj_2020));
        }else{
            data_4.setJinye_zhibo_sub("减少"+df.format(jinye_zhibo_zj_2020-jinye_zhibo_zj_2021));
        }
        for (crop_01_01_table_04_02 item : items_4_2) {
            if (item.getField_4()==null){
                continue;
            }
            switch (item.getField_4()){
                case "中籼稻":
                    if(item.getModification_time().toString().contains(thisYear)){
                        //第一段数据
                        String field_8_4 = item.getField_8_4();
                        if (field_8_4!=null&&!field_8_4.equals("")){
                            jibenmiao_zhibo_zx_2021+=Double.valueOf(field_8_4);
                        }
                        //第三段数据
                        List<Double> list = caculate_2(item);
                        if (list.size()>1){
                            yeling_zhibo_zx_2021+=list.get(1);
                            jinye_zhibo_zx_2021+=list.get(0);
                        }
                    }else if(item.getModification_time().toString().contains(lastYear)){
                        //第一段数据
                        String field_8_4 = item.getField_8_4();
                        if (field_8_4!=null&&!field_8_4.equals("")){
                            jibenmiao_zhibo_zx_2020+=Double.valueOf(field_8_4);
                        }
                        //第三段数据
                        List<Double> list = caculate_2(item);
                        if (list.size()>1){
                            yeling_zhibo_zx_2020+=list.get(1);
                            jinye_zhibo_zx_2020+=list.get(0);
                        }
                    }
                    break;
                case "中粳稻":
                    if(item.getModification_time().toString().contains(thisYear)){
                        //第一段数据
                        String field_8_4 = item.getField_8_4();
                        if (field_8_4!=null&&!field_8_4.equals("")){
                            jibenmiao_zhibo_zj_2021+=Double.valueOf(field_8_4);
                        }
                        //第三段数据
                        List<Double> list = caculate_2(item);
                        if (list.size()>1){
                            yeling_zhibo_zj_2021+=list.get(1);
                            jinye_zhibo_zj_2021+=list.get(0);
                        }
                    }else if(item.getModification_time().toString().contains(lastYear)){
                        //第一段数据
                        String field_8_4 = item.getField_8_4();
                        if (field_8_4!=null&&!field_8_4.equals("")){
                            jibenmiao_zhibo_zj_2020+=Double.valueOf(field_8_4);
                        }
                        //第三段数据
                        List<Double> list = caculate_2(item);
                        if (list.size()>1){
                            yeling_zhibo_zj_2020+=list.get(1);
                            jinye_zhibo_zj_2020+=list.get(0);
                        }
                    }
                    break;
            }

        }
        //表3-2数据
        Double totalArea_2021 = 0.0;
        Double totalArea_2020 =0.0;
        Double yileimiao_2021 = 0.0;
        Double erleimiao_2021 = 0.0;
        Double sanleimiao = 0.0;
        Double yileimiao_2020 = 0.0;
        Double erleimiao_2020 = 0.0;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date dateLine = sdf.parse("2021-07-30");
        List<crop_01_01_table_03_02> items_03_02 = researchReportMonitorMapper.table_3_2(thisYear,area1);

        for (crop_01_01_table_03_02 item : items_03_02) {
            if(item.getModification_time().toString().contains(thisYear)){
                if(item.getField_3()!=null&&!item.getField_3().equals("")){
                    //计算总面积
                    totalArea_2021+=Double.valueOf(item.getField_3());
                }else{
                    //县的面积没填直接跳过这条数据
                    continue;
                }
                for (int i = 4; i < 12; i++) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    Date tempDate = (Date) m1.invoke(item);
//                    Date now = sdf.parse(tempDate);
                    if (tempDate==null){
                        break;
                    }
                    if (tempDate.after(dateLine)){
                        //时间超过抽橞期，计算出前一个进程的叶龄以及茎蘖数，然后直接跳出循环
                        if(i==4){
                            Method m2 = item.getClass().getMethod("getField_" + (i) + "_2_1");
                            Method m3 = item.getClass().getMethod("getField_" + (i) + "_3_1");
                            Method m4 = item.getClass().getMethod("getField_" + (i) + "_4_1");
                            String invoke1 = (String) m2.invoke(item);
                            if(invoke1!=null&&!invoke1.equals("")){
                                yileimiao_2021+=Double.valueOf(invoke1);
                            }
                            String invoke2 = (String) m3.invoke(item);
                            if(invoke2!=null&&!invoke2.equals("")){
                                erleimiao_2021+=Double.valueOf(invoke2);
                            }
                            String invoke3 = (String) m4.invoke(item);
                            if(invoke3!=null&&!invoke3.equals("")){
                                sanleimiao+=Double.valueOf(invoke3);
                            }
                        }else{
                            Method m2 = item.getClass().getMethod("getField_" + (i) + "_2_1");
                            Method m3 = item.getClass().getMethod("getField_" + (i) + "_3_1");
                            Method m4 = item.getClass().getMethod("getField_" + (i) + "_4_1");
                            String invoke1 = (String) m2.invoke(item);
                            if(invoke1!=null&&!invoke1.equals("")){
                                yileimiao_2021+=Double.valueOf(invoke1);
                            }
                            String invoke2 = (String) m3.invoke(item);
                            if(invoke2!=null&&!invoke2.equals("")){
                                erleimiao_2021+=Double.valueOf(invoke2);
                            }
                            String invoke3 = (String) m4.invoke(item);
                            if(invoke3!=null&&!invoke3.equals("")){
                                sanleimiao+=Double.valueOf(invoke3);
                            }
                        }

                        break;
                    }
                }
            }else if(item.getModification_time().toString().contains(lastYear)){
                if(item.getField_3()!=null&&!item.getField_3().equals("")){
                    //计算总面积
                    totalArea_2020+=Double.valueOf(item.getField_3());
                }else{
                    //县的面积没填直接跳过这条数据
                    continue;
                }
                for (int i = 4; i < 12; i++) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_1");
                    Date tempDate = (Date) m1.invoke(item);
//                    Date now = sdf.parse(tempDate);
                    if (tempDate==null){
                        break;
                    }
                    if (tempDate.after(dateLine)){
                        //时间超过抽橞期，计算出前一个进程的叶龄以及茎蘖数，然后直接跳出循环
                        if(i==4){
                            Method m2 = item.getClass().getMethod("getField_" + (i) + "_2_1");
                            Method m3 = item.getClass().getMethod("getField_" + (i) + "_3_1");
                            String invoke1 = (String) m2.invoke(item);
                            if(invoke1!=null&&!invoke1.equals("")){
                                yileimiao_2020+=Double.valueOf(invoke1);
                            }
                            String invoke2 = (String) m3.invoke(item);
                            if(invoke2!=null&&!invoke2.equals("")){
                                erleimiao_2020+=Double.valueOf(invoke2);
                            }
                        }else{
                            Method m2 = item.getClass().getMethod("getField_" + (i) + "_2_1");
                            Method m3 = item.getClass().getMethod("getField_" + (i) + "_3_1");
                            String invoke1 = (String) m2.invoke(item);
                            if(invoke1!=null&&!invoke1.equals("")){
                                yileimiao_2020+=Double.valueOf(invoke1);
                            }
                            String invoke2 = (String) m3.invoke(item);
                            if(invoke2!=null&&!invoke2.equals("")){
                                erleimiao_2020+=Double.valueOf(invoke2);
                            }
                        }

                        break;
                    }
                }
            }

        }
        research_template data_5 = new research_template();
        data_5.setArea(df.format(totalArea_2021));
        if (totalArea_2021>totalArea_2020){
            data_5.setSubNum("增加"+df.format(totalArea_2021-totalArea_2020));
        }else{
            data_5.setSubNum("减少"+df.format(totalArea_2020-totalArea_2021));
        }
        data_5.setYileimiao(df.format(yileimiao_2021/totalArea_2021*100));

        data_5.setErleimiao(df.format(erleimiao_2021/totalArea_2021*100));
        data_5.setSanleimiao(df.format(sanleimiao/totalArea_2021*100));
        String yiAnder = df.format((yileimiao_2021+erleimiao_2021)/totalArea_2021*100);
        String yiAndErSub = "";
        if (yileimiao_2020+erleimiao_2020!=0){
            yiAndErSub = df.format((yileimiao_2021+erleimiao_2021-yileimiao_2020-erleimiao_2020)/(yileimiao_2020+erleimiao_2020)*100);
        }else {
            yiAndErSub = "增加0.00";
        }
        map.put("data_1",data_1);
        map.put("data_2",data_2);
        map.put("data_3",data_3);
        map.put("data_4",data_4);
        map.put("data_5",data_5);
        map.put("yiAnder",yiAnder);
        map.put("yiAndErSub",yiAndErSub);
        return map;
    }

    @Override
    public Map<String, Object> guanJiangQiTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        List<crop_01_01_table_07_01> items_7_1 = researchReportMonitorMapper.table_7_1(thisYear,area1);
        Double totalArea = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        //1.第一段数据
        research_template data_1 = new research_template();
        Double area_1 =0.0;
        Double area_2 =0.0;//9月上旬数据
        Double area_2_front =0.0;
        //2.第二段数据 7月31日前
        research_template data_2 = new research_template();
        Double area_3 =0.0;
        Double yxh_1 =0.0;
        Double zls_1 = 0.0;
        Double jsl_1 = 0.0;
        Double qlz_1 =0.0;
        Double chanliang_1 =0.0;
        Double chanliang_1_front = 0.0;
        //3.第三段数据 8月上旬
        research_template data_3 = new research_template();
        Double area_4 =0.0;
        Double yxh_2 =0.0;
        Double zls_2 = 0.0;
        Double jsl_2 = 0.0;
        Double qlz_2 =0.0;
        Double chanliang_2 =0.0;
        Double chanliang_2_front = 0.0;
        //4.第四段数据 8月中旬
        research_template data_4 = new research_template();
        Double area_5 =0.0;
        Double yxh_3 =0.0;
        Double zls_3 = 0.0;
        Double jsl_3 = 0.0;
        Double qlz_3 =0.0;
        Double chanliang_3 =0.0;
        Double chanliang_3_front = 0.0;
        //5.第五段数据 8月下旬
        research_template data_5 = new research_template();
        Double area_6 =0.0;
        Double yxh_4 =0.0;
        Double zls_4 = 0.0;
        Double jsl_4 = 0.0;
        Double qlz_4 =0.0;
        Double chanliang_4 =0.0;
        Double chanliang_4_front = 0.0;


//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        int year = Integer.parseInt(yearFormat.format(new Date()));
        int recordSize = items_7_1.size();
        for (crop_01_01_table_07_01 item : items_7_1) {
            //数据为空跳过这条元素
            if (item==null||item.getField_3()==null){
                recordSize--;
                continue;
            }
            if(item.getModification_time().toString().contains(thisYear)){
                totalArea+=Double.valueOf(item.getField_3());
                //本年信息
                for (int i = 4; i <10 ; i++) {
                    //外循环处理的是记录的每个进程的数据
                    for (int j = 1; j < 7; j++) {
                        //内循环处理的是每一个进程的指标数据
                        Method m1 = item.getClass().getMethod("getField_" + i + "_"+j);
                        String invoke = (String) m1.invoke(item);
                        if(invoke!=null&&!invoke.equals("")){
                            Double tempData = Double.valueOf(invoke);
                            switch (j){
                                case 1:
                                    //面积数据
                                    if(i==4){
                                        //第二段数据
                                        area_3+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        area_4+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        area_5+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        area_6+=tempData;
                                    }else {
                                        //第六端数据
                                        area_2+=tempData;
                                    }
                                    if(4<=i&&i<9){
                                        //第一段数据
                                        area_1+=tempData;
                                    }else
                                        break;
                                case 2:
                                    //有效蕙数据
                                    if(i==4){
                                        //第二段数据
                                        yxh_1+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        yxh_2+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        yxh_3+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        yxh_4+=tempData;
                                    }
                                    break;
                                case 3:
                                    //总粒数数据
                                    if(i==4){
                                        //第二段数据
                                        zls_1+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        zls_2+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        zls_3+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        zls_4+=tempData;
                                    }
                                    break;
                                case 4:
                                    //结实率数据
                                    if(i==4){
                                        //第二段数据
                                        jsl_1+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        jsl_2+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        jsl_3+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        jsl_4+=tempData;
                                    }
                                    break;
                                case 5:
                                    //千粒重数据
                                    if(i==4){
                                        //第二段数据
                                        qlz_1+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        qlz_2+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        qlz_3+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        qlz_4+=tempData;
                                    }
                                    break;
                                case 6:
                                    //理论产量数据
                                    if(i==4){
                                        //第二段数据
                                        chanliang_1+=tempData;
                                    }else if(i==5){
                                        //第三段数据
                                        chanliang_2+=tempData;
                                    }else if(i>=6&&i<8){
                                        //第四段数据
                                        chanliang_3+=tempData;
                                    }else if(i==8){
                                        //第五段数据
                                        chanliang_4+=tempData;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }else if (item.getModification_time().toString().contains(lastYear)){
                //去年9月上旬面积数据
                String field_9_1 = item.getField_9_1();
                if (field_9_1!=null&&!field_9_1.equals("")){
                    area_2_front+=Double.valueOf(field_9_1);
                }
                //计算出每个进程的产量数据-对应2-5段的往年产量数据
                for (int i = 4; i < 10; i++) {
                    Method m1 = item.getClass().getMethod("getField_" + i + "_6");
                    String invoke = (String) m1.invoke(item);
                    if (invoke!=null&&!invoke.equals("")){
                        Double tempData = Double .valueOf(invoke);
                        if(i==4){
                            //第二段数据
                            chanliang_1_front+=tempData;
                        }else if(i==5){
                            //第三段数据
                            chanliang_2_front+=tempData;
                        }else if(6<=i&&i<8){
                            //第四段数据
                            chanliang_3_front+=tempData;
                        }else if(i==8){
                            //第五段数据
                            chanliang_4_front+=tempData;
                        }
                    }
                }
            }

        }
        //########################第一段数据
        if(totalArea!=0.0){
            data_1.setPercent(df.format(area_1/totalArea));
            data_1.setPercent_2(df.format(area_2/totalArea));
        }else{
            data_1.setPercent("0.00");
            data_1.setPercent_2("0.00");
        }
        data_1.setArea(df.format(area_1));
        data_1.setArea_2(df.format(area_2));
        if (area_2_front!=0.0&&area_2!=0.0){
            //数据正常时
            if(area_2-area_2_front>0){
                data_1.setSubPercent("增加"+df.format((area_2-area_2_front)/area_2_front));
            }else{
                data_1.setSubPercent("减少"+df.format((area_2_front-area_2)/area_2_front));
            }
        }else {
            data_1.setSubPercent("增加0.00");
        }
        //#####################第二段数据
        if (recordSize==0){
            recordSize=1;
        }
        data_2.setArea(df.format(area_3));
        data_2.setYxh(df.format(yxh_1/recordSize));
        data_2.setJsl(df.format(jsl_1/recordSize));
        data_2.setZls(df.format(zls_1/recordSize));
        data_2.setQlz(df.format(qlz_1/recordSize));
        data_2.setChanliang_1(df.format(chanliang_1));
        data_2.setChanliang_2(df.format(chanliang_1_front));
        if(chanliang_1_front!=0.0){
            if(chanliang_1-chanliang_1_front>0){
                data_2.setSubPercent("增加"+df.format((chanliang_1-chanliang_1_front)/chanliang_1_front));
            }else{
                data_2.setSubPercent("减少"+df.format((chanliang_1_front-chanliang_1)/chanliang_1_front));
            }
        }else{
            data_2.setSubPercent("增加0.00");
        }
        //#######################第三段数据
        data_3.setArea(df.format(area_4));
        data_3.setYxh(df.format(yxh_2/recordSize));
        data_3.setJsl(df.format(jsl_2/recordSize));
        data_3.setZls(df.format(zls_2/recordSize));
        data_3.setQlz(df.format(qlz_2/recordSize));
        data_3.setChanliang_1(df.format(chanliang_2/recordSize));
        data_3.setChanliang_2(df.format(chanliang_2_front/recordSize));
        if(chanliang_2_front!=0.0){
            if(chanliang_2-chanliang_2_front>0){
                data_3.setSubPercent("增加"+df.format((chanliang_2-chanliang_2_front)/chanliang_2_front));
            }else{
                data_3.setSubPercent("减少"+df.format((chanliang_2_front-chanliang_2)/chanliang_2_front));
            }
        }else{
            data_3.setSubPercent("增加0.00");
        }
        //#######################第四段数据
        data_4.setArea(df.format(area_5));
        data_4.setYxh(df.format(yxh_3/recordSize));
        data_4.setJsl(df.format(jsl_3/recordSize));
        data_4.setZls(df.format(zls_3/recordSize));
        data_4.setQlz(df.format(qlz_3/recordSize));
        data_4.setChanliang_1(df.format(chanliang_3/recordSize));
        data_4.setChanliang_2(df.format(chanliang_3_front/recordSize));
        if(chanliang_3_front!=0.0){
            if(chanliang_3-chanliang_3_front>0){
                data_4.setSubPercent("增加"+df.format((chanliang_3-chanliang_3_front)/chanliang_3_front));
            }else{
                data_4.setSubPercent("减少"+df.format((chanliang_3_front-chanliang_3)/chanliang_3_front));
            }
        }else{
            data_4.setSubPercent("增加0.00");
        }
        //##############第五段数据
        data_5.setArea(df.format(area_6));
        data_5.setYxh(df.format(yxh_4/recordSize));
        data_5.setJsl(df.format(jsl_4/recordSize));
        data_5.setZls(df.format(zls_4/recordSize));
        data_5.setQlz(df.format(qlz_4/recordSize));
        data_5.setChanliang_1(df.format(chanliang_4/recordSize));
        data_5.setChanliang_2(df.format(chanliang_4_front/recordSize));
        if(chanliang_4_front!=0.0){
            if(chanliang_4-chanliang_4_front>0){
                data_5.setSubPercent("增加"+df.format((chanliang_4-chanliang_4_front)/chanliang_4_front));
            }else{
                data_5.setSubPercent("减少"+df.format((chanliang_4_front-chanliang_4)/chanliang_4_front));
            }
        }else{
            data_5.setSubPercent("增加0.00");
        }


        map.put("data_1",data_1);
        map.put("data_2",data_2);
        map.put("data_3",data_3);
        map.put("data_4",data_4);
        map.put("data_5",data_5);

        return map;
    }

    @Override
    public Map<String, Object> yuCeChanTemplate(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //7-2表格数据
        List<crop_01_01_table_07_02> items_7_2 = researchReportMonitorMapper.table_7_2(thisYear,area1);
        Double totalArea =0.0;
        Double area = 0.0;
        Double area_front =0.0;

        if(items_7_2.size()>0){
            for (crop_01_01_table_07_02 item : items_7_2) {
                String modification_time = item.getModification_time();
                if(modification_time==null||item.getField_3()==null){
                    //时间未填则说明数据并未填完成
                    continue;
                }
                if (modification_time.contains(thisYear)){
                    //本年数据
                    totalArea+=Double.valueOf(item.getField_3());
                    String field_4_2_1 = item.getField_4_2_1();
                    if (field_4_2_1!=null){
                        area+=Double.valueOf(field_4_2_1);
                    }
                }else if(modification_time.contains(lastYear)){
                    //往年数据
                    String field_4_2_1 = item.getField_4_2_1();
                    if (field_4_2_1!=null){
                        area_front+=Double.valueOf(field_4_2_1);
                    }
                }
            }
        }
        List<String> data_1 = new ArrayList<>();
//
        String subPercent = "";
        if(area_front!=0){
            if (area>area_front){
                subPercent = "增加"+df.format((area-area_front)/area_front);
            }else {
                subPercent = "减少"+df.format((area_front-area)/area_front);
            }
        }else{
            subPercent = "增加0.00";
        }
        if (totalArea==0.0){
            totalArea=1.0;
        }
        Collections.addAll(data_1,df.format(area/totalArea),subPercent);
        map.put("data_1",data_1);

        //表7-3数据
        crop_01_01_table_07_03 item_now = researchReportMonitorMapper.table_7_3(thisYear,area1);
        crop_01_01_table_07_03 item_front = researchReportMonitorMapper.table_7_3(lastYear,area1);
        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();
        if(item_now!=null&&item_front!=null){
            for (int i = 2; i < 6; i++) {
                Method m1 = item_now.getClass().getMethod("getField_3_" + i);
                Method m2 = item_front.getClass().getMethod("getField_3_" + i);
                String invoke1 = (String) m1.invoke(item_now);
                String invoke2 = (String) m2.invoke(item_front);
                if (invoke1!=null){
                    data_2.add(invoke1);
                }else {
                    data_2.add("0.00");
                }
                if(invoke1!=null&&invoke2!=null){
                    if (Double.valueOf(invoke1)>Double.valueOf(invoke2)){
                        data_3.add("增加"+df.format((Double.valueOf(invoke1)-Double.valueOf(invoke2))));
                    }else{
                        data_3.add("减少"+df.format((Double.valueOf(invoke2)-Double.valueOf(invoke1))));
                    }
                }else {
                    data_3.add("增加0.00");
                }
            }

            if (item_now.getField_3_6()!=null){
                data_3.add(df.format(Double.valueOf(item_now.getField_3_6())));
            }else{
                data_3.add("0.00");
            }

            if (item_front.getField_3_6()!=null){
                data_3.add(df.format(Double.valueOf(item_front.getField_3_6())));
            }else{
                data_3.add("0.00");
            }

        }else{
            for (int i = 0; i < 5; i++) {
                data_2.add("0.00");
                data_3.add("增加0.00");
            }
            data_3.add("0.00");
        }

        map.put("data_2",data_2);
        map.put("data_3",data_3);

        return map;
    }

    @Override
    public Map<String, Object> ceChanTemplateTwoZao(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        crop_01_02_table_07_02 item_now = researchReportMonitorMapper.tableTwo_7_2(thisYear,area1);
        crop_01_02_table_07_02 item_front = researchReportMonitorMapper.tableTwo_7_2(lastYear,area1);
        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();

        for (int i=2;i<7;i++){
            if(item_now == null){
                data_2.add("0.00");
                if(item_front == null){
                    data_3.add("增加0.00");
                }else{
                    Method m2 = item_front.getClass().getMethod("getField_7_" + i);
                    String invoke2 = (String) m2.invoke(item_front);
                    if(invoke2 == null){
                        data_3.add("0.00");
                    }else{
                        data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                    }
                }
            }else{//有今年的数据，但invoke1的数据不一定有
                Method m1 = item_now.getClass().getMethod("getField_7_"+i);
                String invoke1 = (String) m1.invoke(item_now);
                Method m2 = item_front.getClass().getMethod("getField_7_" + i);
                String invoke2 = (String) m2.invoke(item_front);
                if(invoke1 == null ){
                    data_2.add("0.00");
                    if(item_front == null){
                        data_3.add("0.00");
                    }else{
                        if (invoke2 == null){
                            data_3.add("增加0.00");
                        }else{
                            data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                        }
                    }
                }else{
                    if (invoke1!=null){
                        data_2.add(df.format(Double.valueOf(invoke1)));
                        if(item_front == null){
                            data_3.add("增加"+df.format(Double.valueOf(invoke2)));
                        }else{
                            if(invoke2 == null){
                                data_3.add("增加"+df.format(Double.valueOf(invoke1)));
                            }else{
                                if (Double.valueOf(invoke1)>Double.valueOf(invoke2)){
                                        data_3.add("增加"+df.format((Double.valueOf(invoke1)-Double.valueOf(invoke2))));
                                }else{
                                    data_3.add("减少"+df.format((Double.valueOf(invoke2)-Double.valueOf(invoke1))));
                                }
                            }
                        }
                    }else {
                        data_2.add("0.00");
                        if(item_front == null){
                            data_3.add("0.00");
                        }else{
                            if(invoke2 == null){
                                data_3.add("0.00");
                            }else{
                                data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                            }
                        }
                    }
                }
            }
        }

        map.put("data_2",data_2);
        map.put("data_3",data_3);

        return map;
    }

    @Override
    public Map<String, Object> ceChanTemplateTwoWan(String country) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        String area1 = researchReportMonitorMapper.getArea(country);
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        crop_01_02_table_07_03 item_now = researchReportMonitorMapper.tableTwo_7_3(thisYear,area1);
        crop_01_02_table_07_03 item_front = researchReportMonitorMapper.tableTwo_7_3(lastYear,area1);

        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();

        for (int i=2;i<7;i++){
            if(item_now == null){
                data_2.add("0.00");
                if(item_front == null){
                    data_3.add("增加0.00");
                }else{
                    Method m2 = item_front.getClass().getMethod("getField_8_" + i);
                    String invoke2 = (String) m2.invoke(item_front);
                    if(invoke2 == null){
                        data_3.add("0.00");
                    }else{
                        data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                    }
                }
            }else{//有今年的数据，但invoke1的数据不一定有
                Method m1 = item_now.getClass().getMethod("getField_8_"+i);
                String invoke1 = (String) m1.invoke(item_now);
                Method m2 = item_front.getClass().getMethod("getField_8_" + i);
                String invoke2 = (String) m2.invoke(item_front);
                if(invoke1 == null ){
                    data_2.add("0.00");
                    if(item_front == null){
                        data_3.add("0.00");
                    }else{
                        if (invoke2 == null){
                            data_3.add("增加0.00");
                        }else{
                            data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                        }
                    }
                }else{
                    if (invoke1!=null){
                        data_2.add(df.format(Double.valueOf(invoke1)));
                        if(item_front == null){
                            data_3.add("增加"+df.format(Double.valueOf(invoke2)));
                        }else{
                            if(invoke2 == null){
                                data_3.add("增加"+df.format(Double.valueOf(invoke1)));
                            }else{
                                if (Double.valueOf(invoke1)>Double.valueOf(invoke2)){
                                    data_3.add("增加"+df.format((Double.valueOf(invoke1)-Double.valueOf(invoke2))));
                                }else{
                                    data_3.add("减少"+df.format((Double.valueOf(invoke2)-Double.valueOf(invoke1))));
                                }
                            }
                        }
                    }else {
                        data_2.add("0.00");
                        if(item_front == null){
                            data_3.add("0.00");
                        }else{
                            if(invoke2 == null){
                                data_3.add("0.00");
                            }else{
                                data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                            }
                        }
                    }
                }
            }
        }

        map.put("data_2",data_2);
        map.put("data_3",data_3);

        return map;
    }

    @Override
    public Map<String, Object> ceChanTemplateTwoZao_Sheng() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        crop_01_02_table_07_02 item_now = researchReportMonitorMapper.tableTwoZao_7_2(thisYear);
        crop_01_02_table_07_02 item_front = researchReportMonitorMapper.tableTwoZao_7_2(lastYear);

        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();

        for (int i=2;i<7;i++){
            if(item_now == null){
                data_2.add("0.00");
                if(item_front == null){
                    data_3.add("增加0.00");
                }else{
                    Method m2 = item_front.getClass().getMethod("getField_7_" + i);
                    String invoke2 = (String) m2.invoke(item_front);
                    if(invoke2 == null){
                        data_3.add("0.00");
                    }else{
                        data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                    }
                }
            }else{//有今年的数据，但invoke1的数据不一定有
                Method m1 = item_now.getClass().getMethod("getField_7_"+i);
                String invoke1 = (String) m1.invoke(item_now);
                Method m2 = item_front.getClass().getMethod("getField_7_" + i);
                String invoke2 = (String) m2.invoke(item_front);
                if(invoke1 == null ){
                    data_2.add("0.00");
                    if(item_front == null){
                        data_3.add("0.00");
                    }else{
                        if (invoke2 == null){
                            data_3.add("增加0.00");
                        }else{
                            data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                        }
                    }
                }else{
                    if (invoke1!=null){
                        data_2.add(df.format(Double.valueOf(invoke1)));
                        if(item_front == null){
                            data_3.add("增加"+df.format(Double.valueOf(invoke2)));
                        }else{
                            if(invoke2 == null){
                                data_3.add("增加"+df.format(Double.valueOf(invoke1)));
                            }else{
                                if (Double.valueOf(invoke1)>Double.valueOf(invoke2)){
                                    data_3.add("增加"+df.format((Double.valueOf(invoke1)-Double.valueOf(invoke2))));
                                }else{
                                    data_3.add("减少"+df.format((Double.valueOf(invoke2)-Double.valueOf(invoke1))));
                                }
                            }
                        }
                    }else {
                        data_2.add("0.00");
                        if(item_front == null){
                            data_3.add("0.00");
                        }else{
                            if(invoke2 == null){
                                data_3.add("0.00");
                            }else{
                                data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                            }
                        }
                    }
                }
            }
        }
        map.put("data_2",data_2);
        map.put("data_3",data_3);

        return map;
    }

    @Override
    public Map<String, Object> ceChanTemplateTwoWan_Sheng() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        crop_01_02_table_07_03 item_now = researchReportMonitorMapper.tableTwoWan_7_3(thisYear);
        crop_01_02_table_07_03 item_front = researchReportMonitorMapper.tableTwoWan_7_3(lastYear);

        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();

        for (int i=2;i<7;i++){
            if(item_now == null){
                data_2.add("0.00");
                if(item_front == null){
                    data_3.add("增加0.00");
                }else{
                    Method m2 = item_front.getClass().getMethod("getField_8_" + i);
                    String invoke2 = (String) m2.invoke(item_front);
                    if(invoke2 == null){
                        data_3.add("0.00");
                    }else{
                        data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                    }
                }
            }else{//有今年的数据，但invoke1的数据不一定有
                Method m1 = item_now.getClass().getMethod("getField_8_"+i);
                String invoke1 = (String) m1.invoke(item_now);
                Method m2 = item_front.getClass().getMethod("getField_8_" + i);
                String invoke2 = (String) m2.invoke(item_front);
                if(invoke1 == null ){
                    data_2.add("0.00");
                    if(item_front == null){
                        data_3.add("0.00");
                    }else{
                        if (invoke2 == null){
                            data_3.add("增加0.00");
                        }else{
                            data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                        }
                    }
                }else{
                    if (invoke1!=null){
                        data_2.add(df.format(Double.valueOf(invoke1)));
                        if(item_front == null){
                            data_3.add("增加"+df.format(Double.valueOf(invoke2)));
                        }else{
                            if(invoke2 == null){
                                data_3.add("增加"+df.format(Double.valueOf(invoke1)));
                            }else{
                                if (Double.valueOf(invoke1)>Double.valueOf(invoke2)){
                                    data_3.add("增加"+df.format((Double.valueOf(invoke1)-Double.valueOf(invoke2))));
                                }else{
                                    data_3.add("减少"+df.format((Double.valueOf(invoke2)-Double.valueOf(invoke1))));
                                }
                            }
                        }
                    }else {
                        data_2.add("0.00");
                        if(item_front == null){
                            data_3.add("0.00");
                        }else{
                            if(invoke2 == null){
                                data_3.add("0.00");
                            }else{
                                data_3.add("减少"+df.format(Double.valueOf(invoke2)));
                            }
                        }
                    }
                }
            }
        }

        map.put("data_2",data_2);
        map.put("data_3",data_3);

//六个监测县（区）双季晚稻产量结构表
        String[] peoples ={"0722202101","1702202101","0822202101","0124202101","0223202101","1802202101"};

        List<List<String>> data_5 = new ArrayList<List<String>>();
        List<List<String>> data_6 = new ArrayList<List<String>>();
        List<Double> totalValues = new ArrayList<>();
        List<Double> totalValues_front = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            totalValues.add(0.00);
            totalValues_front.add(0.00);
        }
//        每个监测县年度数据
        for (String people : peoples) {
            crop_01_02_table_07_03 item_now_wanDao = researchReportMonitorMapper.tableTwoWanDao_7_3(thisYear,people);
            crop_01_02_table_07_03 item_front_wanDao = researchReportMonitorMapper.tableTwoWanDao_7_3(lastYear,people);
            List<String> temp=new ArrayList<>();
            List<String> temp2=new ArrayList<>();

//今年的数据
                if (item_now_wanDao != null) {
                    for (int j = 1; j < 7; j++) {
                        Method m3 = item_now_wanDao.getClass().getMethod("getField_8_" + j);
                        String invoke3 = (String) m3.invoke(item_now_wanDao);
                        if (invoke3 != null) {
                            temp.add(invoke3);
                        } else {
                            temp.add("0.00");
                        }
                    }
                    data_5.add(temp);
                }
                else {
                    List<String> temp1 = new ArrayList<String> () ;
                    for (int i = 1; i < 7; i++) {
                        temp1.add("0.00");
                    }
                    data_5.add(temp1);
                }
//                去年的数据
            if (item_front_wanDao != null) {
                for (int j = 1; j < 7; j++) {
                    Method m4 = item_front_wanDao.getClass().getMethod("getField_8_" + j);
                    String invoke4 = (String) m4.invoke(item_front_wanDao);
                    if (invoke4 != null) {
                        temp2.add(invoke4);
                    } else {
                        temp2.add("0.00");
                    }
                }
                data_6.add(temp2);
            }
            else {
                List<String> temp3 = new ArrayList<String> () ;
                for (int i = 1; i < 7; i++) {
                    temp3.add("0.00");
                }
                data_6.add(temp3);
            }


        }

//年度各监测县每类之和
        for (int m =0;m<data_5.size();m++) {
            for (int n = 0; n < totalValues.size(); n++) {
                totalValues.set(n,totalValues.get(n)+Double.valueOf(data_5.get(m).get(n)));
            }
        }
        for (int m =0;m<data_6.size();m++) {
            for (int n = 0; n < totalValues_front.size(); n++) {
                totalValues_front.set(n,totalValues_front.get(n)+Double.valueOf(data_6.get(m).get(n)));
            }
        }


//年度平均
        List<String> avgValues = new ArrayList<>();
        for(int i=0;i<totalValues.size();i++) {
            avgValues.add(String.valueOf(String.format("%.2f", totalValues.get(i)/data_5.size())));
        }

        List<String> avgValues_front = new ArrayList<>();
        for(int i=0;i<totalValues_front.size();i++){
            avgValues_front.add(String.valueOf(String.format("%.2f", totalValues_front.get(i)/data_6.size())));
        }

//        比上年度+-
        List<String> diffValues=new ArrayList<>();
        for (int i=0;i<6;i++){
            Double currentValue=Double.valueOf(avgValues.get(i));
            Double frontValue=Double.valueOf(avgValues_front.get(i));
            if (currentValue>frontValue){
                diffValues.add("+"+String.valueOf(String.format("%.2f",currentValue-frontValue)));
            }
            else
            {
                diffValues.add(String.valueOf(String.format("%.2f",(currentValue-frontValue))));
            }
        }

        data_5.add(avgValues);
        data_6.add(avgValues_front);
        data_6.add(diffValues);
        map.put("data_5",data_5);
        map.put("data_6",data_6);

        return map;
    }
}
