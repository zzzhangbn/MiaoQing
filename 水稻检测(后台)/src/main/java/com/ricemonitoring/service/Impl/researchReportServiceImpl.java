package com.ricemonitoring.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.ricemonitoring.mapper.ConditionsAnalysisMapper;
import com.ricemonitoring.mapper.researchReportMapper;
import com.ricemonitoring.pojo.*;
import com.ricemonitoring.service.researchReportService;
import com.ricemonitoring.util.quickSort;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 16:27
 */
@Service
public class researchReportServiceImpl implements researchReportService {
    @Autowired
    researchReportMapper researchReportMapper;

    @Autowired
    ConditionsAnalysisMapper conditionsAnalysisMapper;

    @Autowired
    quickSort quickSort;

    /*
    文件上传
    param:
        realName:服务器中文件的名字
        fileName:用户输入的研究报告名称
        crop:作物类型
        userId:上传用户id
     */
    @Override
    public void upload(String realName, String fileName, String crop, String userId) {
        //1.生成文件在线阅读的url
        String url = "http://139.224.41.123:8080/file/"+realName;
        //获取当前的时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        //数据存储到数据库中
        researchReportMapper.upload(fileName,crop,date,url,userId);

    }
    /*
     条件查询获取对应的研究报告信息
      */
    @Override
    public List<research_report> getFiles(String crop, String year,String account,String code_name) {
        if(code_name.equals("全省层面")){
            String A="340824202101";
            return researchReportMapper.getFiles(crop,year,A);
        }
        String area=researchReportMapper.getaccount(code_name);
        String tump = researchReportMapper.getaccount2(area);
//        System.out.println(account);
        if(tump==null){
            if(account.equals("340824202101")){
//                System.out.println(122);
                return researchReportMapper.getFiles(crop,year,account);
            }
        }
        account=tump;
//        System.out.println(account);
//        System.out.println(1111);
        return researchReportMapper.getFiles(crop,year,account);
    }

    @Override
    public String getaccount(String code_name) {
        return null;
    }


    /*
    一季稻栽播期苗情报告
     */
    @Override
    public Map<String, Object> yizaiqiTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //全省种植总面积
        List<String> totalAreas = researchReportMapper.getTotalArea(thisYear);
        Double totalArea = 0.0;
        if(totalAreas.size()>0){
            for (String area : totalAreas) {
                if(area!=null){
                    totalArea +=Double.valueOf(area);
                }
            }
        }
        System.out.println("总面积"+totalArea);
        //1.一季稻种植茬口类型与面积比较以及种植类型与面积比较，对应表1-1，根据年份查出数据
        String[] years = {thisYear,lastYear};
        List<crop_01_01_table_01_01> items = new ArrayList<>();
        for (String year : years) {
            crop_01_01_table_01_01 crop_01_01_table_01_01 =  researchReportMapper.getStubbleArea(year);
            items.add(crop_01_01_table_01_01);
        }
        //茬口数据
        List<String> stubbleList = new ArrayList<>();
        List<String> stubblePercents = new ArrayList<>();
        List<String> stubbleList2 = new ArrayList<>();
        //种植方式数据
        List<String> cateList = new ArrayList<>();
        List<String> catePercents = new ArrayList<>();
        List<String> cateList2 = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.0");
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
                } else {
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


        map.put("stubbleList_1_1",stubbleList);
        map.put("stubblePercents_1_1",stubblePercents);
        map.put("stubbleList2_1_1",stubbleList2);
        map.put("cateList_1_1",cateList);
        map.put("catePercent_1_1",catePercents);
        map.put("cateList2_1_1",cateList2);


        //################################################
        //2.主导品种介绍 对应表1-2
        research_template data_1_2 = new research_template();
        //获取总面积
        Double totalArea_2_1 = 0.0;
        List<crop_01_01_table_01_02> area_table_01_02s  = conditionsAnalysisMapper.getTotalArea_2_1(thisYear);
        for (crop_01_01_table_01_02 area_table_01_02 : area_table_01_02s) {
            if (area_table_01_02==null||area_table_01_02.getCounty_area()==null){
                continue;
            }
            totalArea_2_1+=Double.valueOf(area_table_01_02.getCounty_area());
        }
        //获取今年前10 的品种
        List<crop_01_01_table_01_02> crop_01_01_table_01_02s_now = conditionsAnalysisMapper.crop_01_01_table_01_02Analysis_1( "", thisYear);
        quickSort.quickSort(crop_01_01_table_01_02s_now);
        Collections.reverse(crop_01_01_table_01_02s_now);
        if (crop_01_01_table_01_02s_now.size()>10){
            crop_01_01_table_01_02s_now = crop_01_01_table_01_02s_now.subList(0,10);
        }
        //获取去年前十的品种
        List<crop_01_01_table_01_02> crop_01_01_table_01_02s_front = conditionsAnalysisMapper.crop_01_01_table_01_02Analysis_1( "", lastYear);
        quickSort.quickSort(crop_01_01_table_01_02s_front);
        Collections.reverse(crop_01_01_table_01_02s_front);
        if (crop_01_01_table_01_02s_front.size()>10){
            crop_01_01_table_01_02s_front = crop_01_01_table_01_02s_front.subList(0,10);
        }
        //计算去年前十品种面积的占比
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
            area_01_02_now+=Double.valueOf(item.getArea_data());
        }
        if (area_01_02_now>area_1_2_front){
            data_1_2.setSubPercent("增加"+df.format(area_01_02_now-area_1_2_front));
        }else{
            data_1_2.setSubPercent("减少"+df.format(area_1_2_front-area_01_02_now));
        }
        data_1_2.setArea(df.format(area_01_02_now));
        if (totalArea_2_1==0.0){
            totalArea_2_1=1.0;
        }
        data_1_2.setPercent(df.format(area_01_02_now/totalArea_2_1*100));
        map.put("data_1_2",data_1_2);
        map.put("cate_1_2",crop_01_01_table_01_02s_now);


        //###############################################################
        //3.播栽期插栽进度 表3-1
        List<String> areas = new ArrayList<>();
        for (String year : years) {
            String area = researchReportMapper.table_3_1(year);
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



        //######################苗情长势 对应表4-1和4-2
        String[] cropCates = {"中籼稻","中粳稻"};
        String[] tableNames = {"crop_01_01_table_04_01","crop_01_01_table_04_02"};
        List<crop_01_01_table_04_01> list_table_4_1 = new ArrayList<>();
        for (String cropCate : cropCates) {
            for (String tableName : tableNames) {
                crop_01_01_table_04_01 item = researchReportMapper.table_4_1(cropCate,tableName,thisYear);
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


    /*
    报告存储功能
     */
    @Override
    public void saveReport(Map<String, Object> map) {
        //获取当前的时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        map.put("date",date);
        String url = "http://139.224.41.123";
        map.put("url",url);
        researchReportMapper.saveReport(map);
    }
    /*
        抽橞期报告模板-1
     */
    @Override
    public Map<String, Object> chouHuiQiTemplate_1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Map<String, Object> map = new HashMap<>();


        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMapper.table_7_1();
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

        List<crop_01_01_table_04_01> items_4_1 =  researchReportMapper.table_4_1_2();
        List<crop_01_01_table_04_02> items_4_2 =  researchReportMapper.table_4_2();
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
        Collections.addAll(data_7,Double.valueOf(df.format(jinye_2021)),Double.valueOf(df.format(jinye_2021-jinye_2020)),
                Double.valueOf(df.format(yeling_2021)),Double.valueOf(df.format(yeling_2021-yeling_2020)),
                jinye_rgyz,yeling_rgyz,jinye_jc,yeling_jc,jinye_pz,yeling_pz);
        map.put("data_7",data_7);
        Collections.addAll(data_8,Double.valueOf(df.format(jinye_2021_2)),Double.valueOf(df.format(jinye_2021_2-jinye_2020_2)),
                Double.valueOf(df.format(yeling_2021_2)),Double.valueOf(df.format(yeling_2021_2-yeling_2020_2)),
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
            System.out.println(item);
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
    /*
            抽橞期报告模板-2
     */
    @Override
    public Map<String, Object> chouHuiQiTemplate_2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMapper.table_7_1();
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

    /*
    抽橞期报告模板
     */
    @Override
    public Map<String, Object> chouHuiQiTemplate_3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);
        //1.7-1表格数据查询
        //1.7-1表格数据查询
        List<crop_01_01_table_07_01> items_7_1 = researchReportMapper.table_7_1();
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
        System.out.println(area_4);
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
                                System.out.println(item);
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

    /*
    一季稻分孽期苗情
     */
    @Override
    public Map<String, Object> fenLieQiTemplate() throws ParseException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DecimalFormat df = new DecimalFormat("0.00");
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int previousYear = currentYear-1;
        String thisYear = String.valueOf(currentYear);
        String lastYear = String.valueOf(previousYear);

        List<crop_01_01_table_04_01> items_4_1 = researchReportMapper.table_4_1_2();
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
        List<crop_01_01_table_04_02> items_4_2 = researchReportMapper.table_4_2();
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
        List<crop_01_01_table_03_02> items_03_02 = researchReportMapper.table_3_2();

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
    /*
    一季稻抽穗－灌浆期苗情
     */
    @Override
    public Map<String, Object> guanJiangQiTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        List<crop_01_01_table_07_01> items_7_1 = researchReportMapper.table_7_1();
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


        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(yearFormat.format(new Date()));
        int recordSize = items_7_1.size();
        for (crop_01_01_table_07_01 item : items_7_1) {
            //数据为空跳过这条元素
            if (item==null||item.getField_3()==null){
                recordSize--;
                continue;
            }
            if(item.getModification_time().toString().contains(String.valueOf(year))){
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
            }else if (item.getModification_time().toString().contains(String.valueOf(year-1))){
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
    /*
    一季稻预测产情况 对应表7-2和7-3
     */
    @Override
    public Map<String, Object> yuCeChanTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        //7-2表格数据
        List<crop_01_01_table_07_02> items_7_2 = researchReportMapper.table_7_2();
        Double totalArea =0.0;
        Double area = 0.0;
        Double area_front =0.0;
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        DecimalFormat df = new DecimalFormat("0.00");
        int year = Integer.parseInt(yearFormat.format(new Date()));
        if(items_7_2.size()>0){
            for (crop_01_01_table_07_02 item : items_7_2) {
                String modification_time = item.getModification_time();
                if(modification_time==null||item.getField_3()==null){
                    //时间未填则说明数据并未填完成
                    continue;
                }
                if (modification_time.contains(String.valueOf(year))){
                    //本年数据
                    totalArea+=Double.valueOf(item.getField_3());
                    String field_4_2_1 = item.getField_4_2_1();
                    if (field_4_2_1!=null){
                        area+=Double.valueOf(field_4_2_1);
                    }
                }else if(modification_time.contains(String.valueOf(year-1))){
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
        crop_01_01_table_07_03 item_now = researchReportMapper.table_7_3(year);
        crop_01_01_table_07_03 item_front = researchReportMapper.table_7_3(year-1);
        List<String> data_3 = new ArrayList<>();
        List<String> data_2 = new ArrayList<>();
        if(item_now!=null&&item_front!=null){
            for (int i = 2; i < 6; i++) {
                Method m1 = item_now.getClass().getMethod("getField_4_" + i);
                Method m2 = item_front.getClass().getMethod("getField_4_" + i);
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
            if (item_front.getField_4_6()!=null){
                data_3.add(df.format(item_front.getField_4_6()));
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


    //#######################早稻模板开始#################################################
    /*
    早稻播栽期苗情
    对应表：1-1
     */
    @Override
    public Map<String, Object> zaodao_bozaiqiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        DateFormat dF = new SimpleDateFormat("yyyy-MM-dd");
        String year = dateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("0.00");
        //第一段内容 对应表3-1-1
        crop_01_02_table_03_01 item_03_01 = researchReportMapper.table_double_0301(account,year);
        List<String> datas_1 = new ArrayList<>();
        if (item_03_01!=null){
            String field_5_1 = dF.format(item_03_01.getField_5_1());
            if (field_5_1!=null){
                datas_1.add(field_5_1);
            }else{
                datas_1.add(year+"-03-20");
            }
            String field_11_1 = dF.format(item_03_01.getField_11_1());
            if (field_11_1!=null){
                datas_1.add(field_11_1);
            }else{
                datas_1.add(year+"-04-25");
            }
        }else{
            datas_1.add(year+"-03-20");
            datas_1.add(year+"-04-25");
        }
        map.put("datas_1",datas_1);
        //第二段内容 对应表3-2-1 和1-1

        List<String> datas_2 = new ArrayList<>();

        crop_01_02_table_03_01 item_03_01_front = researchReportMapper.table_double_0301(account,String.valueOf(Double.valueOf(year)-1));
        if (item_03_01!=null&&item_03_01_front!=null){
            datas_2.add(item_03_01.getField_7_1());
            datas_2.add(item_03_01.getField_7_2());
            double temp = Double.valueOf(item_03_01.getField_7_1()) - Double.valueOf(item_03_01_front.getField_7_1());
            if (temp>=0){
                datas_2.add("增加"+temp);
            }else{
                datas_2.add("减少"+temp);
            }
        }
        else if(item_03_01!=null){
            datas_2.add(item_03_01.getField_7_1());
            datas_2.add(item_03_01.getField_7_2());
            datas_2.add("增加0.00");
        }
        else{
            for (double i = 0; i < 3; i++) {
                if(i==0||i==1){
                    datas_2.add("0.00");
                }else{
                    datas_2.add("增加0.00");
                }
            }
        }
        String sql = "field_2,";
        for (int i = 1; i < 9; i++) {
            sql+="avg(field_5_"+i+"_1) as field_5_"+i+"_1,avg(field_5_"+i+"_2) as field_5_"+i+"_2";
            if (i!=8){
                sql+=",";
            }
        }
        crop_01_02_table_01_01 item_0101_now = researchReportMapper.table_double_1_1(account,year,sql);
        crop_01_02_table_01_01 item_0101_front = researchReportMapper.table_double_1_1(account,String.valueOf(Double.valueOf(year)-1),sql);
        double totalArea = 1;
        double totalArea_front = 1;
        double tempArea_now = 0;
        double tempArea_front = 0;
        double temp = 0; //撒直播数据
        //两年的数据都存在
        if(item_0101_now!=null&&item_0101_front!=null){
            try {
                totalArea = Double.valueOf(item_0101_now.getField_2());
            } catch (NumberFormatException e) {
                System.out.println("1-1表无数据");
            }

            try {
                totalArea_front=Double.valueOf(item_0101_front.getField_2());
            } catch (NumberFormatException e) {
                System.out.println("1-1表无数据");
            }
            //移栽数据
            String field_5_1_1 = item_0101_now.getField_5_1_1();
            if (field_5_1_1!=null){
                datas_2.add(field_5_1_1);
                tempArea_now+=Double.valueOf(field_5_1_1);
            }else{
                datas_2.add("0.00");
            }
            //抛栽数据
            String field_5_3_1 = item_0101_now.getField_5_3_1();
            String field_5_4_1 = item_0101_now.getField_5_4_1();
            if (field_5_3_1!=null&&field_5_4_1!=null){
                double tempDa = Double.valueOf(field_5_3_1)+Double.valueOf(field_5_4_1);
                datas_2.add(df.format(tempDa));
                tempArea_now+=tempDa;
            }
            else if (field_5_3_1!=null){
                datas_2.add(field_5_3_1);
                tempArea_now+=Double.valueOf(field_5_3_1);
            }
            else if(field_5_4_1!=null){
                datas_2.add(field_5_4_1);
                tempArea_now+=Double.valueOf(field_5_4_1);
            }
            else{
                datas_2.add("0.00");
            }
            //机插秧
            String field_5_2_1 = item_0101_now.getField_5_2_1();
            if (field_5_2_1!=null){
                datas_2.add(field_5_2_1);
                tempArea_now+=Double.valueOf(field_5_2_1);
            }else{
                datas_2.add("0.00");
            }
            //撒直播数据  当年数据
            String field_5_5_1 = item_0101_now.getField_5_5_1();
            String field_5_6_1 = item_0101_now.getField_5_6_1();
            if (field_5_5_1!=null&&field_5_6_1!=null){
                 temp= Double.valueOf(field_5_5_1)+Double.valueOf(field_5_6_1);
                datas_2.add(df.format(temp));
                tempArea_now+=temp;
            }
            else if (field_5_5_1!=null){
                temp= Double.valueOf(field_5_5_1);
                datas_2.add(field_5_5_1);
                tempArea_now+=Double.valueOf(field_5_5_1);
            }
            else if(field_5_6_1!=null){
                temp= Double.valueOf(field_5_6_1);
                datas_2.add(field_5_6_1);
                tempArea_now+=Double.valueOf(field_5_6_1);
            }
            else{
                datas_2.add("0.00");
            }
            datas_2.add(df.format(temp));
            datas_2.add(df.format(temp/totalArea*100));
            //往年撒直播数据
            String field_5_5_1_front = item_0101_front.getField_5_5_1();
            String field_5_6_1_front = item_0101_front.getField_5_6_1();
            double tempData_56front = 0;
            if (field_5_5_1_front!=null&&field_5_6_1_front!=null){
                tempData_56front= Double.valueOf(field_5_5_1_front)+Double.valueOf(field_5_6_1_front);
            }
            else if (field_5_5_1_front!=null){
                tempData_56front= Double.valueOf(field_5_5_1_front);
            }
            else if(field_5_6_1_front!=null){
                tempData_56front=Double.valueOf(field_5_6_1_front);
            }
            if (temp>=tempArea_front){
                datas_2.add("增加"+(temp-tempData_56front));
            }else{
                datas_2.add("减少"+(tempData_56front-temp));
            }
            for (int i = 1; i < 7; i++) {
                Method m1 = item_0101_front.getClass().getMethod("getField_5_" + i + "_1");
                String invoke = (String) m1.invoke(item_0101_front);
                if (invoke!=null){
                    tempArea_front+=Double.valueOf(invoke);
                }
            }
        }
        //仅存在当年数据
        else if(item_0101_now!=null){
            try {
                totalArea = Double.valueOf(item_0101_now.getField_2());
            } catch (NumberFormatException e) {
                System.out.println("1-1表无数据");
            }
            //移栽数据
            String field_5_1_1 = item_0101_now.getField_5_1_1();
            if (field_5_1_1!=null){
                datas_2.add(field_5_1_1);
                tempArea_now+=Double.valueOf(field_5_1_1);
            }else{
                datas_2.add("0.00");
            }
            //抛栽数据
            String field_5_3_1 = item_0101_now.getField_5_3_1();
            String field_5_4_1 = item_0101_now.getField_5_4_1();
            if (field_5_3_1!=null&&field_5_4_1!=null){
                double tempDa = Double.valueOf(field_5_3_1)+Double.valueOf(field_5_4_1);
                datas_2.add(df.format(tempDa));
                tempArea_now+=tempDa;
            }
            else if (field_5_3_1!=null){
                datas_2.add(field_5_3_1);
                tempArea_now+=Double.valueOf(field_5_3_1);
            }
            else if(field_5_4_1!=null){
                datas_2.add(field_5_4_1);
                tempArea_now+=Double.valueOf(field_5_4_1);
            }
            else{
                datas_2.add("0.00");
            }
            //机插秧
            String field_5_2_1 = item_0101_now.getField_5_2_1();
            if (field_5_2_1!=null){
                datas_2.add(field_5_2_1);
                tempArea_now+=Double.valueOf(field_5_2_1);
            }else{
                datas_2.add("0.00");
            }
            //撒直播数据  当年数据
            String field_5_5_1 = item_0101_now.getField_5_5_1();
            String field_5_6_1 = item_0101_now.getField_5_6_1();
            if (field_5_5_1!=null&&field_5_6_1!=null){
                temp= Double.valueOf(field_5_5_1)+Double.valueOf(field_5_6_1);
                datas_2.add(df.format(temp));
                tempArea_now+=temp;
            }
            else if (field_5_5_1!=null){
                temp= Double.valueOf(field_5_5_1);
                datas_2.add(field_5_5_1);
                tempArea_now+=Double.valueOf(field_5_5_1);
            }
            else if(field_5_6_1!=null){
                temp= Double.valueOf(field_5_6_1);
                datas_2.add(field_5_6_1);
                tempArea_now+=Double.valueOf(field_5_6_1);
            }
            else{
                datas_2.add("0.00");
            }
            datas_2.add(df.format(temp));
            datas_2.add(df.format(temp/totalArea*100));
            datas_2.add("增加"+df.format(temp));
        }
        else{
            for (int i = 0; i < 5; i++) {
                datas_2.add("0.00");
            }
            datas_2.add("增加0.00");
        }

        datas_2.add(df.format(tempArea_now));
        datas_2.add(df.format(tempArea_now/totalArea*100));
        double temp1 = tempArea_now-tempArea_front;
        if (temp1>=0){
            datas_2.add("增加"+df.format(temp1));
        }else{
            datas_2.add("减少"+df.format(-temp1));
        }
        map.put("datas_2",datas_2);

        List<String> datas_3 = new ArrayList<>();
        datas_3.add(df.format(totalArea));
        double temp2 = totalArea-totalArea_front;
        if (temp2>=0){
            datas_3.add("增加"+df.format(temp2));
        }else{
            datas_3.add("减少"+df.format(-temp2));
        }
        map.put("datas_3",datas_3);
        return map;
    }
    /*
    早稻分蘖期苗情
    对应表：4-1 4-2 3-2-1
     */
    @Override
    public Map<String, Object> zaodao_fenLieQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        //第一段数据
        //总的数据
        crop_01_02_table_04_01 item_0401_now = researchReportMapper.table_double_0401(account,year,null,null);
        crop_01_02_table_04_01 item_0401_front = researchReportMapper.table_double_0401(account,String.valueOf(Integer.parseInt(year)-1),null,null);
        List<String> datas_1 = new ArrayList<>();
        List<String> sub_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> sub_2 = new ArrayList<>();
        if (item_0401_now!=null){
            //第一段第一句数据
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                datas_1.add(field_8_3);
            }else{
                datas_1.add("0.00");
            }
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else{
                datas_1.add("0.00");
            }
            //第二段第一句数据
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else{
                datas_2.add("0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else{
                datas_2.add("0.00");
            }
        }else{
            datas_1.add("0.00");
            datas_1.add("0.00");
            datas_2.add("0.00");
            datas_2.add("0.00");
        }
        if (item_0401_now!=null&&item_0401_front!=null){
            double field_8_3 = Double.parseDouble(item_0401_now.getField_8_3());
            double field_8_4 = Double.parseDouble(item_0401_now.getField_8_4());
            double field_8_3_front = Double.parseDouble(item_0401_front.getField_8_3());
            double field_8_4_front = Double.parseDouble(item_0401_front.getField_8_4());
            if(field_8_3!=0&&field_8_3_front!=0){
                if(field_8_3>field_8_3_front){
                    sub_1.add("增加"+(field_8_3-field_8_3_front));
                }else{
                    sub_1.add("减少"+(field_8_3_front-field_8_3));
                }
            }
            if(field_8_4!=0&&field_8_4_front!=0){
                if(field_8_4>field_8_4_front){
                    sub_1.add("增加"+(field_8_4-field_8_4_front));
                }else{
                    sub_1.add("减少"+(field_8_4_front-field_8_4));
                }
            }
            double field_10_3 = Double.parseDouble(item_0401_now.getField_10_3());
            double field_10_4 = Double.parseDouble(item_0401_now.getField_10_4());
            double field_10_3_front = Double.parseDouble(item_0401_front.getField_10_3());
            double field_10_4_front = Double.parseDouble(item_0401_front.getField_10_4());
            if(field_10_3!=0&&field_10_3_front!=0){
                if(field_10_3>field_10_3_front){
                    sub_2.add("增加"+(field_10_3-field_10_3_front));
                }else{
                    sub_2.add("减少"+(field_10_3_front-field_10_3));
                }
            }
            if(field_10_4!=0&&field_10_4_front!=0){
                if(field_10_4>field_10_4_front){
                    sub_2.add("增加"+(field_10_4-field_10_4_front));
                }else{
                    sub_2.add("减少"+(field_10_4_front-field_10_4));
                }
            }
        }
        else if (item_0401_now!=null){
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
        }else {
            sub_1.add("增加0.00");
            sub_1.add("增加0.00");
            sub_2.add("增加0.00");
            sub_2.add("增加0.00");
        }
        //各种栽种方式的数据
        String[] culMethods = {"人工移栽","盘育抛栽","机械插秧"};
        crop_01_02_table_04_01 item = null;
        List<crop_01_02_table_04_01> items_0401 = new ArrayList<>();
        for (String culMethod : culMethods) {
            item =researchReportMapper.table_double_0401(account,year,culMethod,null);
            items_0401.add(item);
        }
        System.out.println(items_0401.size());
        for (crop_01_02_table_04_01 crop_01_02_table_04_01 : items_0401) {
            if (crop_01_02_table_04_01!=null){
                //第一段中间部分数据
                String field_8_3 = item_0401_now.getField_8_3();
                String field_8_4 = item_0401_now.getField_8_4();
                if (field_8_3!=null){
                    datas_1.add(field_8_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_8_4!=null){
                    datas_1.add(field_8_4);
                }else{
                    datas_1.add("0.00");
                }
                //第二段中间部分数据
                String field_10_3 = item_0401_now.getField_10_3();
                String field_10_4 = item_0401_now.getField_10_4();
                if (field_10_3!=null){
                    datas_2.add(field_10_3);
                }else{
                    datas_2.add("0.00");
                }
                if (field_10_4!=null){
                    datas_2.add(field_10_4);
                }else{
                    datas_2.add("0.00");
                }
            }else{
                datas_1.add("0.00");
                datas_1.add("0.00");
                datas_2.add("0.00");
                datas_2.add("0.00");
            }

        }
        //第一以及第二段后半部分数据
        crop_01_02_table_04_02 item_0402_now = researchReportMapper.table_double_0402(account,year,null);
        crop_01_02_table_04_02 item_0402_front = researchReportMapper.table_double_0402(account,String.valueOf(Integer.parseInt(year)-1),null);
        if (item_0402_now!=null&&item_0402_front!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();
            //去年数据
            String field_8_4_front = item_0402_front.getField_8_4();
            String field_10_3_front = item_0402_front.getField_10_3();
            String field_10_4_front = item_0402_front.getField_10_4();
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else {
                datas_1.add("0.00");
            }
            if (field_8_4!=null&&field_8_4_front!=null){
                double temp = Double.valueOf(field_8_4)-Double.valueOf(field_8_4_front);
                if (temp>=0){
                    sub_1.add("增加"+temp);
                }else{
                    sub_1.add("减少"+temp);
                }
            }
            else if(field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            //第二段数据
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_3!=null&&field_10_3_front!=null){
                double temp = Double.valueOf(field_10_3)-Double.valueOf(field_10_3_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_3!=null){
                sub_2.add("增加"+field_10_3);
            }else{
                sub_2.add("增加0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_4!=null&&field_10_4_front!=null){
                double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_4_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_4!=null){
                sub_2.add("增加"+field_10_4);
            }else{
                sub_2.add("增加0.00");
            }

        }
        else if(item_0402_now!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();

            datas_1.add(field_8_4);
            sub_1.add("增加"+field_8_4);

            datas_2.add(field_10_3);
            sub_2.add("增加"+field_10_3);
            datas_2.add(field_10_4);
            sub_2.add("增加"+field_10_4);

        }
        else{
            datas_1.add("0.00");
            sub_1.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");

        }
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("sub_1",sub_1);
        map.put("sub_2",sub_2);

        //第三段数据
        String sql2 = "";
        for (int i = 2; i < 5; i++) {
            sql2+="avg(field_11_"+i+"_1) as field_11_"+i+"_1";
            if (i!=4){
                sql2+=",";
            }
        }
        crop_01_02_table_03_03 item_0303 = researchReportMapper.table_double_0303(account,year,sql2);
        crop_01_02_table_03_03 item_0303_front = researchReportMapper.table_double_0303(account,String.valueOf(Integer.parseInt(year)-1),sql2);
        List<String> datas_3 = new ArrayList<>();
        List<String> percents = new ArrayList<>();
        double tempData = 0;
        double tempPercent =0;
        double tempPercen2 =0;
        //
        if(item_0303!=null){
            for (int i = 2; i < 5; i++) {
                Method m1 = item_0303.getClass().getMethod("getField_11_" + i + "_1");
                String invoke1 = (String) m1.invoke(item_0303);
                Method m2 = item_0303.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303);
                if (invoke1!=null){
                    datas_3.add(invoke1);
                    tempData+=Double.valueOf(invoke1);
                }else{
                    datas_3.add("0.00");
                }
                if(invoke2!=null){
                    percents.add(invoke2);
                    tempPercent+=Double.valueOf(invoke2);
                }else{
                    percents.add("0.00");
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                datas_3.add("0.00");
                percents.add("0.00");
            }
        }
        if(item_0303_front!=null) {
            for (int i = 2; i < 5; i++) {
                Method m2 = item_0303_front.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303_front);
                if (invoke2 != null) {
                    tempPercen2 += Double.valueOf(invoke2);
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");

        percents.add(df.format(tempPercent));
        percents.add(df.format(tempPercen2));
        double temp = tempPercent-tempPercen2;
        if(temp>=0){
            percents.add("增加"+temp);
        }else{
            percents.add("减少"+temp);
        }
        map.put("datas_3",datas_3);
        map.put("percents",percents);
        return map;
    }
    /*
    早稻分蘖－孕穗初期苗情
    对应表格4-1 4-2 3-2-1
     */
    @Override
    public Map<String, Object> zaodao_yunHuiQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        //第一段数据
        //总的数据
        crop_01_02_table_04_01 item_0401_now = researchReportMapper.table_double_0401(account,year,null,null);
        crop_01_02_table_04_01 item_0401_front = researchReportMapper.table_double_0401(account,String.valueOf(Integer.parseInt(year)-1),null,null);
        List<String> datas_1 = new ArrayList<>();
        List<String> sub_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> sub_2 = new ArrayList<>();
        if (item_0401_now!=null){
            //第一段第一句数据
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                datas_1.add(field_8_3);
            }else{
                datas_1.add("0.00");
            }
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else{
                datas_1.add("0.00");
            }
            //第二段第一句数据
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else{
                datas_2.add("0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else{
                datas_2.add("0.00");
            }
        }else{
            datas_1.add("0.00");
            datas_1.add("0.00");
            datas_2.add("0.00");
            datas_2.add("0.00");
        }
        if (item_0401_now!=null&&item_0401_front!=null){
            double field_8_3 = Double.parseDouble(item_0401_now.getField_8_3());
            double field_8_4 = Double.parseDouble(item_0401_now.getField_8_4());
            double field_8_3_front = Double.parseDouble(item_0401_front.getField_8_3());
            double field_8_4_front = Double.parseDouble(item_0401_front.getField_8_4());
            if(field_8_3!=0&&field_8_3_front!=0){
                if(field_8_3>field_8_3_front){
                    sub_1.add("增加"+(field_8_3-field_8_3_front));
                }else{
                    sub_1.add("减少"+(field_8_3_front-field_8_3));
                }
            }
            if(field_8_4!=0&&field_8_4_front!=0){
                if(field_8_4>field_8_4_front){
                    sub_1.add("增加"+(field_8_4-field_8_4_front));
                }else{
                    sub_1.add("减少"+(field_8_4_front-field_8_4));
                }
            }
            double field_10_3 = Double.parseDouble(item_0401_now.getField_10_3());
            double field_10_4 = Double.parseDouble(item_0401_now.getField_10_4());
            double field_10_3_front = Double.parseDouble(item_0401_front.getField_10_3());
            double field_10_4_front = Double.parseDouble(item_0401_front.getField_10_4());
            if(field_10_3!=0&&field_10_3_front!=0){
                if(field_10_3>field_10_3_front){
                    sub_2.add("增加"+(field_10_3-field_10_3_front));
                }else{
                    sub_2.add("减少"+(field_10_3_front-field_10_3));
                }
            }
            if(field_10_4!=0&&field_10_4_front!=0){
                if(field_10_4>field_10_4_front){
                    sub_2.add("增加"+(field_10_4-field_10_4_front));
                }else{
                    sub_2.add("减少"+(field_10_4_front-field_10_4));
                }
            }
        }
        else if (item_0401_now!=null){
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
        }else {
            sub_1.add("增加0.00");
            sub_1.add("增加0.00");
            sub_2.add("增加0.00");
            sub_2.add("增加0.00");
        }
        //各种栽种方式的数据
        String[] culMethods = {"人工移栽","盘育抛栽","机械插秧"};
        crop_01_02_table_04_01 item = null;
        List<crop_01_02_table_04_01> items_0401 = new ArrayList<>();
        for (String culMethod : culMethods) {
            item =researchReportMapper.table_double_0401(account,year,culMethod,null);
            items_0401.add(item);
        }
        System.out.println(items_0401.size());
        for (crop_01_02_table_04_01 crop_01_02_table_04_01 : items_0401) {
            if (crop_01_02_table_04_01!=null){
                //第一段中间部分数据
                String field_8_3 = item_0401_now.getField_8_3();
                String field_8_4 = item_0401_now.getField_8_4();
                if (field_8_3!=null){
                    datas_1.add(field_8_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_8_4!=null){
                    datas_1.add(field_8_4);
                }else{
                    datas_1.add("0.00");
                }
                //第二段中间部分数据
                String field_10_3 = item_0401_now.getField_10_3();
                String field_10_4 = item_0401_now.getField_10_4();
                if (field_10_3!=null){
                    datas_2.add(field_10_3);
                }else{
                    datas_2.add("0.00");
                }
                if (field_10_4!=null){
                    datas_2.add(field_10_4);
                }else{
                    datas_2.add("0.00");
                }
            }else{
                datas_1.add("0.00");
                datas_1.add("0.00");
                datas_2.add("0.00");
                datas_2.add("0.00");
            }

        }
        //第一以及第二段后半部分数据
        crop_01_02_table_04_02 item_0402_now = researchReportMapper.table_double_0402(account,year,null);
        crop_01_02_table_04_02 item_0402_front = researchReportMapper.table_double_0402(account,String.valueOf(Integer.parseInt(year)-1),null);
        if (item_0402_now!=null&&item_0402_front!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();
            //去年数据
            String field_8_4_front = item_0402_front.getField_8_4();
            String field_10_3_front = item_0402_front.getField_10_3();
            String field_10_4_front = item_0402_front.getField_10_4();
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else {
                datas_1.add("0.00");
            }
            if (field_8_4!=null&&field_8_4_front!=null){
                double temp = Double.valueOf(field_8_4)-Double.valueOf(field_8_4_front);
                if (temp>=0){
                    sub_1.add("增加"+temp);
                }else{
                    sub_1.add("减少"+temp);
                }
            }
            else if(field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            //第二段数据
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_3!=null&&field_10_3_front!=null){
                double temp = Double.valueOf(field_10_3)-Double.valueOf(field_10_3_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_3!=null){
                sub_2.add("增加"+field_10_3);
            }else{
                sub_2.add("增加0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_4!=null&&field_10_4_front!=null){
                double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_4_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_4!=null){
                sub_2.add("增加"+field_10_4);
            }else{
                sub_2.add("增加0.00");
            }

        }
        else if(item_0402_now!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();

            datas_1.add(field_8_4);
            sub_1.add("增加"+field_8_4);

            datas_2.add(field_10_3);
            sub_2.add("增加"+field_10_3);
            datas_2.add(field_10_4);
            sub_2.add("增加"+field_10_4);

        }
        else{
            datas_1.add("0.00");
            sub_1.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");

        }
        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("sub_1",sub_1);
        map.put("sub_2",sub_2);

        //第三段数据
        String sql2 = "sum(field_3) as field_3,";
        for (int i = 2; i < 5; i++) {
            sql2+="avg(field_11_"+i+"_1) as field_11_"+i+"_1";
            if (i!=4){
                sql2+=",";
            }
        }
        crop_01_02_table_03_03 item_0303 = researchReportMapper.table_double_0303(account,year,sql2);
        crop_01_02_table_03_03 item_0303_front = researchReportMapper.table_double_0303(account,String.valueOf(Integer.parseInt(year)-1),sql2);
        List<String> datas_3 = new ArrayList<>();
        List<String> percents = new ArrayList<>();
        double tempData = 0;
        double tempPercent =0;
        double tempPercen2 =0;
        double percent_1_front = 0;//一类苗往年占比
        double percent_2_front = 0;//一类苗往年占比
        //
        if(item_0303!=null){
            for (int i = 2; i < 5; i++) {
                Method m1 = item_0303.getClass().getMethod("getField_11_" + i + "_1");
                String invoke1 = (String) m1.invoke(item_0303);
                Method m2 = item_0303.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303);
                if (invoke1!=null){
                    datas_3.add(invoke1);
                    tempData+=Double.valueOf(invoke1);
                }else{
                    datas_3.add("0.00");
                }
                if(invoke2!=null){
                    percents.add(invoke2);
                    tempPercent+=Double.valueOf(invoke2);
                }else{
                    percents.add("0.00");
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                datas_3.add("0.00");
                percents.add("0.00");
            }
        }
        if(item_0303_front!=null) {
            for (int i = 2; i < 5; i++) {
                Method m2 = item_0303_front.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303_front);
                if (invoke2 != null) {
                    tempPercen2 += Double.valueOf(invoke2);
                    if (i==2){
                        percent_1_front = Double.valueOf(invoke2);
                    }
                    if (i==3){
                        percent_2_front = Double.valueOf(invoke2);
                    }
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");

        percents.add(df.format(tempPercent));
        percents.add(df.format(tempPercen2));
        double temp = tempPercent-tempPercen2;
        if(temp>=0){
            percents.add("增加"+temp);
        }else{
            percents.add("减少"+temp);
        }
        double temp2 = Double.valueOf(percents.get(0))-percent_1_front;
        double temp3 = Double.valueOf(percents.get(1))-percent_2_front;
        if(temp2>=0){
            percents.add("增加"+temp2);
        }else{
            percents.add("减少"+temp2);
        }
        if(temp3>=0){
            percents.add("增加"+temp3);
        }else{
            percents.add("减少"+temp3);
        }
        map.put("datas_3",datas_3);
        map.put("percents",percents);

        //省内表格数据
        List<String> datas_4 = new ArrayList<>();//表格本年度数据
        List<String> datas_5 = new ArrayList<>();//表格上年度数据
        String[] counties = {"0124202101","0722202101","0822202101","0223202101","1702202101","1802202101"};
        for (String county : counties) {
            crop_01_02_table_03_03 item1 = researchReportMapper.table_double_0303(county,year,sql2);
            crop_01_02_table_03_03 item_front = researchReportMapper.table_double_0303(county,String.valueOf(Integer.parseInt(year)-1),sql2);
            if (item1!=null){
                //面积数据
                String field_3 = item1.getField_3();
                if (field_3!=null){
                    datas_4.add(field_3);
                }else{
                    datas_4.add("0.00");
                }
                //一二三类苗数据
                for (int i = 2; i < 5; i++) {
                    Method m1 = item1.getClass().getMethod("getField_11_" + i + "_1");
                    String invoke = (String) m1.invoke(item1);
                    if (invoke!=null){
                        datas_4.add(invoke);
                    }else{
                        datas_4.add("0.00");
                    }
                }
            }else{
                for (int i = 0; i < 4; i++) {
                    datas_4.add("0.00");
                }
            }

            if (item_front!=null){
                //面积数据
                String field_3 = item_front.getField_3();
                if (field_3!=null){
                    datas_5.add(field_3);
                }else{
                    datas_5.add("0.00");
                }
                //一二三类苗数据
                for (int i = 2; i < 5; i++) {
                    Method m1 = item_front.getClass().getMethod("getField_11_" + i + "_1");
                    String invoke = (String) m1.invoke(item_front);
                    if (invoke!=null){
                        datas_5.add(invoke);
                    }else{
                        datas_5.add("0.00");
                    }
                }
            }else{
                for (int i = 0; i < 4; i++) {
                    datas_5.add("0.00");
                }
            }
        }
        map.put("datas_4",datas_4);
        map.put("datas_5",datas_5);
        return map;
    }
    /*
   早稻孕穗－抽穗期苗情
   对应表：4-1 4-2 3-2-1
    */
    @Override
    public Map<String, Object> zaodao_chouHuiQiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        //第一段数据
        //总的数据
        crop_01_02_table_04_01 item_0401_now = researchReportMapper.table_double_0401(account,year,null,null);
        crop_01_02_table_04_01 item_0401_front = researchReportMapper.table_double_0401(account,String.valueOf(Integer.parseInt(year)-1),null,null);
        List<String> datas_1 = new ArrayList<>();
        List<String> sub_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> sub_2 = new ArrayList<>();
        if (item_0401_now!=null){
            //第一段第一句数据
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                datas_1.add(field_8_3);
            }else{
                datas_1.add("0.00");
            }
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else{
                datas_1.add("0.00");
            }
            //第二段第一句数据
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else{
                datas_2.add("0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else{
                datas_2.add("0.00");
            }
        }else{
            datas_1.add("0.00");
            datas_1.add("0.00");
            datas_2.add("0.00");
            datas_2.add("0.00");
        }
        if (item_0401_now!=null&&item_0401_front!=null){
            double field_8_3 = Double.parseDouble(item_0401_now.getField_8_3());
            double field_8_4 = Double.parseDouble(item_0401_now.getField_8_4());
            double field_8_3_front = Double.parseDouble(item_0401_front.getField_8_3());
            double field_8_4_front = Double.parseDouble(item_0401_front.getField_8_4());
            if(field_8_3!=0&&field_8_3_front!=0){
                if(field_8_3>field_8_3_front){
                    sub_1.add("增加"+(field_8_3-field_8_3_front));
                }else{
                    sub_1.add("减少"+(field_8_3_front-field_8_3));
                }
            }
            if(field_8_4!=0&&field_8_4_front!=0){
                if(field_8_4>field_8_4_front){
                    sub_1.add("增加"+(field_8_4-field_8_4_front));
                }else{
                    sub_1.add("减少"+(field_8_4_front-field_8_4));
                }
            }
            double field_10_3 = Double.parseDouble(item_0401_now.getField_10_3());
            double field_10_4 = Double.parseDouble(item_0401_now.getField_10_4());
            double field_10_3_front = Double.parseDouble(item_0401_front.getField_10_3());
            double field_10_4_front = Double.parseDouble(item_0401_front.getField_10_4());
            if(field_10_3!=0&&field_10_3_front!=0){
                if(field_10_3>field_10_3_front){
                    sub_2.add("增加"+(field_10_3-field_10_3_front));
                }else{
                    sub_2.add("减少"+(field_10_3_front-field_10_3));
                }
            }
            if(field_10_4!=0&&field_10_4_front!=0){
                if(field_10_4>field_10_4_front){
                    sub_2.add("增加"+(field_10_4-field_10_4_front));
                }else{
                    sub_2.add("减少"+(field_10_4_front-field_10_4));
                }
            }
        }
        else if (item_0401_now!=null){
            String field_8_3 = item_0401_now.getField_8_3();
            String field_8_4 = item_0401_now.getField_8_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            String field_10_3 = item_0401_now.getField_10_3();
            String field_10_4 = item_0401_now.getField_10_4();
            if (field_8_3!=null){
                sub_1.add("增加"+field_8_3);
            }else{
                sub_1.add("增加0.00");
            }
            if (field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
        }else {
            sub_1.add("增加0.00");
            sub_1.add("增加0.00");
            sub_2.add("增加0.00");
            sub_2.add("增加0.00");
        }
        //各种栽种方式的数据
        String[] culMethods = {"人工移栽","盘育抛栽","机械插秧"};
        crop_01_02_table_04_01 item = null;
        List<crop_01_02_table_04_01> items_0401 = new ArrayList<>();
        for (String culMethod : culMethods) {
            item =researchReportMapper.table_double_0401(account,year,culMethod,null);
            items_0401.add(item);
        }
        System.out.println(items_0401.size());
        for (crop_01_02_table_04_01 crop_01_02_table_04_01 : items_0401) {
            if (crop_01_02_table_04_01!=null){
                //第一段中间部分数据
                String field_8_3 = item_0401_now.getField_8_3();
                String field_8_4 = item_0401_now.getField_8_4();
                if (field_8_3!=null){
                    datas_1.add(field_8_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_8_4!=null){
                    datas_1.add(field_8_4);
                }else{
                    datas_1.add("0.00");
                }
                //第二段中间部分数据
                String field_10_3 = item_0401_now.getField_10_3();
                String field_10_4 = item_0401_now.getField_10_4();
                if (field_10_3!=null){
                    datas_2.add(field_10_3);
                }else{
                    datas_2.add("0.00");
                }
                if (field_10_4!=null){
                    datas_2.add(field_10_4);
                }else{
                    datas_2.add("0.00");
                }
            }else{
                datas_1.add("0.00");
                datas_1.add("0.00");
                datas_2.add("0.00");
                datas_2.add("0.00");
            }

        }
        //第一以及第二段后半部分数据
        crop_01_02_table_04_02 item_0402_now = researchReportMapper.table_double_0402(account,year,null);
        crop_01_02_table_04_02 item_0402_front = researchReportMapper.table_double_0402(account,String.valueOf(Integer.parseInt(year)-1),null);
        if (item_0402_now!=null&&item_0402_front!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();
            //去年数据
            String field_8_4_front = item_0402_front.getField_8_4();
            String field_10_3_front = item_0402_front.getField_10_3();
            String field_10_4_front = item_0402_front.getField_10_4();
            if (field_8_4!=null){
                datas_1.add(field_8_4);
            }else {
                datas_1.add("0.00");
            }
            if (field_8_4!=null&&field_8_4_front!=null){
                double temp = Double.valueOf(field_8_4)-Double.valueOf(field_8_4_front);
                if (temp>=0){
                    sub_1.add("增加"+temp);
                }else{
                    sub_1.add("减少"+temp);
                }
            }
            else if(field_8_4!=null){
                sub_1.add("增加"+field_8_4);
            }else{
                sub_1.add("增加0.00");
            }
            //第二段数据
            if (field_10_3!=null){
                datas_2.add(field_10_3);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_3!=null&&field_10_3_front!=null){
                double temp = Double.valueOf(field_10_3)-Double.valueOf(field_10_3_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_3!=null){
                sub_2.add("增加"+field_10_3);
            }else{
                sub_2.add("增加0.00");
            }
            if (field_10_4!=null){
                datas_2.add(field_10_4);
            }else {
                datas_2.add("0.00");
            }
            if (field_10_4!=null&&field_10_4_front!=null){
                double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_4_front);
                if (temp>=0){
                    sub_2.add("增加"+temp);
                }else{
                    sub_2.add("减少"+temp);
                }
            }
            else if(field_10_4!=null){
                sub_2.add("增加"+field_10_4);
            }else{
                sub_2.add("增加0.00");
            }

        }
        else if(item_0402_now!=null){
            //当年数据
            String field_8_4 = item_0402_now.getField_8_4();
            String field_10_3 = item_0402_now.getField_10_3();
            String field_10_4 = item_0402_now.getField_10_4();

            datas_1.add(field_8_4);
            sub_1.add("增加"+field_8_4);

            datas_2.add(field_10_3);
            sub_2.add("增加"+field_10_3);
            datas_2.add(field_10_4);
            sub_2.add("增加"+field_10_4);

        }
        else{
            datas_1.add("0.00");
            sub_1.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");
            datas_2.add("0.00");
            sub_2.add("增加0.00");

        }
        map.put("datas_2",datas_2);
        map.put("sub_2",sub_2);

        //第三段数据
        String sql2 = "";
        for (int i = 2; i < 5; i++) {
            sql2+="avg(field_11_"+i+"_1) as field_11_"+i+"_1";
            if (i!=4){
                sql2+=",";
            }
        }
        crop_01_02_table_03_03 item_0303 = researchReportMapper.table_double_0303(account,year,sql2);
        crop_01_02_table_03_03 item_0303_front = researchReportMapper.table_double_0303(account,String.valueOf(Integer.parseInt(year)-1),sql2);
        List<String> datas_3 = new ArrayList<>();
        List<String> percents = new ArrayList<>();
        double tempData = 0;
        double tempPercent =0;
        double tempPercen2 =0;
        //
        if(item_0303!=null){
            for (int i = 2; i < 5; i++) {
                Method m1 = item_0303.getClass().getMethod("getField_11_" + i + "_1");
                String invoke1 = (String) m1.invoke(item_0303);
                Method m2 = item_0303.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303);
                if (invoke1!=null){
                    datas_3.add(invoke1);
                    tempData+=Double.valueOf(invoke1);
                }else{
                    datas_3.add("0.00");
                }
                if(invoke2!=null){
                    percents.add(invoke2);
                    tempPercent+=Double.valueOf(invoke2);
                }else{
                    percents.add("0.00");
                }
            }
        }else{
            for (int i = 0; i < 3; i++) {
                datas_3.add("0.00");
                percents.add("0.00");
            }
        }
        if(item_0303_front!=null) {
            for (int i = 2; i < 5; i++) {
                Method m2 = item_0303_front.getClass().getMethod("getField_11_" + i + "_2");
                String invoke2 = (String) m2.invoke(item_0303_front);
                if (invoke2 != null) {
                    tempPercen2 += Double.valueOf(invoke2);
                }
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");

        percents.add(df.format(tempPercent));
        percents.add(df.format(tempPercen2));
        double temp = tempPercent-tempPercen2;
        if(temp>=0){
            percents.add("增加"+temp);
        }else{
            percents.add("减少"+temp);
        }
        map.put("datas_3",datas_3);
        map.put("percents",percents);
        return map;
    }
    /*
       早稻预测产结果
       对应表：4-1 4-2 3-2-1
    */
    @Override
    public Map<String, Object> zaodao_yuChanTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("0.00");
        //查出数据
        //生成sql语句
        String sql = "sum(field_2) as field_2,";
        for (int i = 3; i < 5; i++) {
            sql+="sum(field_"+i+"_1)as field_"+i+"_1,avg(field_"+i+"_2) as field_"+i+"_2," +
                    "avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4," +
                    "avg(field_"+i+"_5) as field_"+i+"_5,avg(field_"+i+"_6) as field_"+i+"_6";
            if (i!=4){
                sql+=",";
            }
        }
        crop_01_02_table_07_01 item_0701 = researchReportMapper.table_double_0701(account,year,sql);
        List<String> datas_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        //据此估测数据
        double tuice_yxh =0;
        double tuice_zls =0;
        double tuice_jsl =0;
        double tuice_qlz =0;
        double tuice_ch =0;
        if (item_0701!=null){
            //第一段数据
            Double totalArea = Double.valueOf(item_0701.getField_2());
            Double field_3_1 = Double.valueOf(item_0701.getField_3_1());
            Double field_4_1 = Double.valueOf(item_0701.getField_4_1());
            if (field_3_1!=null&&field_4_1!=null&&totalArea!=null){
                datas_1.add(df.format((field_3_1+field_4_1)/totalArea));
            }else{
                datas_1.add("0.00");
            }
            if (field_3_1!=null&&totalArea!=null){
                datas_1.add(df.format(field_3_1/totalArea));
            }else{
                datas_1.add("0.00");
            }
            if (field_4_1!=null&&totalArea!=null){
                datas_1.add(df.format(field_4_1/totalArea));
            }else{
                datas_1.add("0.00");
            }

            //第二段数据
            for (int i = 3; i < 5; i++) {
                Method m1 = item_0701.getClass().getMethod("getField_"+i+"_1");
                String invoke1 = (String) m1.invoke(item_0701);
                Method m2 = item_0701.getClass().getMethod("getField_"+i+"_2");
                String invoke2 = (String) m2.invoke(item_0701);
                Method m3 = item_0701.getClass().getMethod("getField_"+i+"_3");
                String invoke3 = (String) m3.invoke(item_0701);
                Method m4 = item_0701.getClass().getMethod("getField_"+i+"_4");
                String invoke4 = (String) m4.invoke(item_0701);
                Method m5 = item_0701.getClass().getMethod("getField_"+i+"_4");
                String invoke5 = (String) m5.invoke(item_0701);
                Method m6 = item_0701.getClass().getMethod("getField_"+i+"_6");
                String invoke6 = (String) m6.invoke(item_0701);
                //有效穗
                if (invoke2!=null){
                    datas_2.add(invoke2);
                    tuice_yxh+=Double.valueOf(invoke2)*Double.valueOf(invoke1)/Double.valueOf(totalArea);
                }else{
                    datas_2.add("0");
                }
                //总粒数
                if (invoke3!=null){
                    datas_2.add(invoke3);
                    tuice_zls+=Double.valueOf(invoke3)*Double.valueOf(invoke1)/Double.valueOf(totalArea);
                }else{
                    datas_2.add("0");
                }
                //结实率
                if (invoke4!=null){
                    datas_2.add(invoke4);
                    tuice_jsl+=Double.valueOf(invoke4)*Double.valueOf(invoke1)/Double.valueOf(totalArea);
                }else{
                    datas_2.add("0");
                }
                //千粒重
                if (invoke5!=null){
                    datas_2.add(invoke5);
                    tuice_qlz+=Double.valueOf(invoke3)*Double.valueOf(invoke1)/Double.valueOf(totalArea);
                }else{
                    datas_2.add("0");
                }
                //理论产量
                if (invoke6!=null){
                    datas_2.add(df.format(Double.valueOf(invoke6)*0.85));
                    tuice_zls+=Double.valueOf(invoke3)*0.85*Double.valueOf(invoke1)/Double.valueOf(totalArea);
                }else{
                    datas_2.add("0");
                }

            }
        }
        //查出早稻的实际产量
        Double chanliang = researchReportMapper.table_double_0702(account,Integer.parseInt(year)-1);
        datas_2.add(df.format(tuice_yxh));
        datas_2.add(df.format(tuice_zls));
        datas_2.add(df.format(tuice_jsl));
        datas_2.add(df.format(tuice_qlz));
        datas_2.add(df.format(tuice_ch));
        System.out.println(chanliang);
        if (chanliang!=null){
            datas_2.add(df.format(chanliang));
            double temp = tuice_ch-chanliang;
            if (temp>=0){
                datas_2.add("增加"+df.format(temp));
                datas_2.add("增加"+df.format((temp)/chanliang*100));
            }else{
                datas_2.add("减少"+df.format(-temp));
                datas_2.add("减少"+df.format(-temp/chanliang*100));
            }
        }else{
            datas_2.add("0.00");
            datas_2.add(df.format(tuice_ch));
            datas_2.add("增加0.00");
        }

        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        return map;
    }
    /*
       双季晚稻分蘖期苗情
       对应表：4-1 4-2 3-2-2
    */
    @Override
    public Map<String, Object> wandao_fenleiqiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("0.00");
        //晚粳稻部分数据
        crop_01_02_table_04_01 item_0401 = researchReportMapper.table_double_0401(account,year,null,"晚粳稻");
        crop_01_02_table_04_01 item_0401_front = researchReportMapper.table_double_0401(account,
                String.valueOf(Integer.parseInt(year)-1),null,"晚粳稻");
        //育秧表4-1数据
        List<String> datas_1 = new ArrayList<>();
        List<String> sub_1 = new ArrayList<>();
        List<String> datas_2 = new ArrayList<>();
        List<String> sub_2 = new ArrayList<>();
        //两年数据都存在
        if (item_0401!=null&&item_0401_front!=null){
            //平均亩栽数据
            String field_8_3 = item_0401.getField_8_3();
            String field_8_3_front = item_0401_front.getField_8_3();
            if (field_8_3!=null&&field_8_3_front!=null){
                datas_1.add(field_8_3);
                double temp = Double.valueOf(field_8_3)-Double.valueOf(field_8_3_front);
                if (temp>=0){
                    sub_1.add("增加"+df.format(temp));
                }else{
                    sub_1.add("减少"+df.format(-temp));
                }

            }
            else if(field_8_3!=null){
                datas_1.add(field_8_3);
                sub_1.add("增加0.00");
            }
            else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //基本苗数据
            String field_8_4 = item_0401.getField_8_4();
            String field_8_4_front = item_0401_front.getField_8_4();
            if (field_8_4!=null&&field_8_4_front!=null){
                datas_1.add(field_8_4);
                double temp = Double.valueOf(field_8_4)-Double.valueOf(field_8_4_front);
                if (temp>=0){
                    sub_1.add("增加"+df.format(temp));
                }else{
                    sub_1.add("减少"+df.format(-temp));
                }
            }else if(field_8_4!=null){
                datas_1.add(field_8_4);
                sub_1.add("增加0.00");
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //茎蘖数数据
            String field_10_4 = item_0401.getField_10_4();
            String field_10_4_front = item_0401_front.getField_10_4();
            if (field_10_4!=null&&field_10_4_front!=null){
                datas_2.add(field_10_4);
                double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_4_front);
                if (temp>=0){
                    sub_2.add("增加"+df.format(temp));
                }else{
                    sub_2.add("减少"+df.format(-temp));
                }
            }else if(field_10_4!=null){
                datas_2.add(field_10_4);
                sub_2.add("增加0.00");
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
            //叶龄数据
            String field_10_3 = item_0401.getField_10_3();
            String field_10_3_front = item_0401_front.getField_10_3();
            if (field_10_3!=null&&field_10_3_front!=null){
                datas_2.add(field_10_3);
                double temp = Double.valueOf(field_10_3)-Double.valueOf(field_10_3_front);
                if (temp>=0){
                    sub_2.add("增加"+df.format(temp));
                }else{
                    sub_2.add("减少"+df.format(-temp));
                }
            }else if(field_10_3!=null){
                datas_2.add(field_10_3);
                sub_2.add("增加0.00");
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }

        }
        //只有当年数据存在
        else if (item_0401!=null){
            //平均亩栽数据
            String field_8_3 = item_0401.getField_8_3();
            if(field_8_3!=null){
                datas_1.add(field_8_3);
                sub_1.add("增加"+field_8_3);
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //基本苗数据
            String field_8_4 = item_0401.getField_8_4();
            if(field_8_4!=null){
                datas_1.add(field_8_4);
                sub_1.add("增加"+field_8_4);
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //茎蘖数数据
            String field_10_4 = item_0401.getField_10_4();
            if(field_10_4!=null){
                datas_2.add(field_10_4);
                sub_2.add("增加"+field_10_4);
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
            //叶龄数据
            String field_10_3 = item_0401.getField_10_3();
            if(field_10_3!=null){
                datas_2.add(field_10_3);
                sub_2.add("增加"+field_10_3);
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
        }
        //两年的数据都不存在
        else{
            for (int i = 0; i < 2; i++) {
                datas_1.add("0.00");
                datas_2.add("0.00");
                sub_1.add("增加0.00");
                sub_2.add("增加0.00");
            }
        }
        String[] culMethods = {"人工移栽","盘育抛栽"};
        for (String culMethod : culMethods) {
            crop_01_02_table_04_01 item = researchReportMapper.table_double_0401(account,year,culMethod,"晚粳稻");
            if (item!=null){
                //亩栽穴和基本苗数据
                String field_8_3 = item.getField_8_3();
                String field_8_4 = item.getField_8_4();
                if (field_8_3!=null){
                    datas_1.add(field_8_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_8_4!=null){
                    datas_1.add(field_8_4);
                }else{
                    datas_1.add("0.00");
                }
                String field_10_3 = item.getField_10_3();
                String field_10_4 = item.getField_10_4();
                if (field_10_3!=null){
                    datas_2.add(field_10_3);
                }else{
                    datas_2.add("0.00");
                }
                if (field_10_4!=null){
                    datas_2.add(field_10_4);
                }else{
                    datas_2.add("0.00");
                }
            }
            else{
                for (int i = 0; i < 2; i++) {
                    datas_1.add("0.00");
                    datas_2.add("0.00");
                }
            }
        }
        //晚籼稻部分数据
        crop_01_02_table_04_01 item_0401_wxd = researchReportMapper.table_double_0401(account,year,null,"晚籼稻");
        crop_01_02_table_04_01 item_0401_wxd_front = researchReportMapper.table_double_0401(account,
                String.valueOf(Integer.parseInt(year)-1),null,"晚籼稻");
        //两年数据都存在
        if (item_0401_wxd!=null&&item_0401_wxd_front!=null){
            //平均亩栽数据
            String field_8_3 = item_0401_wxd.getField_8_3();
            String field_8_3_front = item_0401_wxd_front.getField_8_3();
            if (field_8_3!=null&&field_8_3_front!=null){
                datas_1.add(field_8_3);
                double temp = Double.valueOf(field_8_3)-Double.valueOf(field_8_3_front);
                if (temp>=0){
                    sub_1.add("增加"+df.format(temp));
                }else{
                    sub_1.add("减少"+df.format(-temp));
                }

            }
            else if(field_8_3!=null){
                datas_1.add(field_8_3);
                sub_1.add("增加0.00");
            }
            else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //基本苗数据
            String field_8_4 = item_0401_wxd.getField_8_4();
            String field_8_4_front = item_0401_wxd_front.getField_8_4();
            if (field_8_4!=null&&field_8_4_front!=null){
                datas_1.add(field_8_4);
                double temp = Double.valueOf(field_8_4)-Double.valueOf(field_8_4_front);
                if (temp>=0){
                    sub_1.add("增加"+df.format(temp));
                }else{
                    sub_1.add("减少"+df.format(-temp));
                }
            }else if(field_8_4!=null){
                datas_1.add(field_8_4);
                sub_1.add("增加0.00");
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //茎蘖数数据
            String field_10_4 = item_0401_wxd.getField_10_4();
            String field_10_4_front = item_0401_wxd_front.getField_10_4();
            if (field_10_4!=null&&field_10_4_front!=null){
                datas_2.add(field_10_4);
                double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_4_front);
                if (temp>=0){
                    sub_2.add("增加"+df.format(temp));
                }else{
                    sub_2.add("减少"+df.format(-temp));
                }
            }else if(field_10_4!=null){
                datas_2.add(field_10_4);
                sub_2.add("增加0.00");
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
            //叶龄数据
            String field_10_3 = item_0401_wxd.getField_10_3();
            String field_10_3_front = item_0401_wxd_front.getField_10_3();
            if (field_10_3!=null&&field_10_3_front!=null){
                datas_2.add(field_10_3);
                double temp = Double.valueOf(field_10_3)-Double.valueOf(field_10_3_front);
                if (temp>=0){
                    sub_2.add("增加"+df.format(temp));
                }else{
                    sub_2.add("减少"+df.format(-temp));
                }
            }else if(field_10_3!=null){
                datas_2.add(field_10_3);
                sub_2.add("增加0.00");
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }

        }
        //只有当年数据存在
        else if (item_0401_wxd!=null){
            //平均亩栽数据
            String field_8_3 = item_0401_wxd.getField_8_3();
            if(field_8_3!=null){
                datas_1.add(field_8_3);
                sub_1.add("增加"+field_8_3);
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //基本苗数据
            String field_8_4 = item_0401_wxd.getField_8_4();
            if(field_8_4!=null){
                datas_1.add(field_8_4);
                sub_1.add("增加"+field_8_4);
            }else{
                datas_1.add("0.00");
                sub_1.add("增加0.00");
            }
            //茎蘖数数据
            String field_10_4 = item_0401_wxd.getField_10_4();
            if(field_10_4!=null){
                datas_2.add(field_10_4);
                sub_2.add("增加"+field_10_4);
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
            //叶龄数据
            String field_10_3 = item_0401_wxd.getField_10_3();
            if(field_10_3!=null){
                datas_2.add(field_10_3);
                sub_2.add("增加"+field_10_3);
            }else{
                datas_2.add("0.00");
                sub_2.add("增加0.00");
            }
        }
        //两年的数据都不存在
        else{
            for (int i = 0; i < 2; i++) {
                datas_1.add("0.00");
                datas_2.add("0.00");
                sub_1.add("增加0.00");
                sub_2.add("增加0.00");
            }
        }
        for (String culMethod : culMethods) {
            crop_01_02_table_04_01 item = researchReportMapper.table_double_0401(account,year,culMethod,"晚籼稻");
            if (item!=null){
                //亩栽穴和基本苗数据
                String field_8_3 = item.getField_8_3();
                String field_8_4 = item.getField_8_4();
                if (field_8_3!=null){
                    datas_1.add(field_8_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_8_4!=null){
                    datas_1.add(field_8_4);
                }else{
                    datas_1.add("0.00");
                }
                String field_10_3 = item.getField_10_3();
                String field_10_4 = item.getField_10_4();
                if (field_10_3!=null){
                    datas_2.add(field_10_3);
                }else{
                    datas_2.add("0.00");
                }
                if (field_10_4!=null){
                    datas_2.add(field_10_4);
                }else{
                    datas_2.add("0.00");
                }
            }
            else{
                for (int i = 0; i < 2; i++) {
                    datas_1.add("0.00");
                    datas_2.add("0.00");
                }
            }
        }
        map.put("datas_1",datas_1);
        map.put("sub_1",sub_1);
        map.put("datas_2",datas_2);
        map.put("sub_2",sub_2);
        List<String> datas_3 = new ArrayList<>();
        List<String> sub_3 = new ArrayList<>();
        //直播表数据4-2表
        String[] riceTypes = {"晚粳稻","晚籼稻"};
        for (String riceType : riceTypes) {
            crop_01_02_table_04_02 item_now = researchReportMapper.table_double_0402(account, year, riceType);
            crop_01_02_table_04_02 item_front = researchReportMapper.table_double_0402(account, String.valueOf(Integer.parseInt(year)), riceType);
            //数据都存在
            if (item_now!=null&&item_front!=null){
                String field_10_4 = item_now.getField_10_4();
                String field_10_41 = item_front.getField_10_4();
                String field_10_3 = item_now.getField_10_3();
                if (field_10_4!=null&&field_10_41!=null){
                    datas_3.add(field_10_4);
                    double temp = Double.valueOf(field_10_4)-Double.valueOf(field_10_41);
                    if (temp>=0){
                        sub_3.add("增加"+temp);
                    }else{
                        sub_3.add("减少"+(-temp));
                    }
                    datas_3.add(field_10_3);
                }
                else if (field_10_4!=null){
                    datas_3.add(field_10_4);
                    datas_3.add(field_10_3);
                    sub_3.add("增加"+field_10_4);
                }
                else{
                    datas_3.add("0.00");
                    datas_3.add("0.00");
                    sub_3.add("增加0.00");
                }
            }
            //只有当年的数据
            else if(item_now!=null){
                String field_10_4 = item_now.getField_10_4();
                String field_10_3 = item_now.getField_10_3();
                if (field_10_4!=null){
                    datas_3.add(field_10_4);
                    datas_3.add(field_10_3);
                    sub_3.add("增加"+field_10_4);
                }
                else{
                    datas_3.add("0.00");
                    datas_3.add("0.00");
                    sub_3.add("增加0.00");
                }
            }
            //数据都不存在
            else{
                datas_3.add("0.00");
                datas_3.add("0.00");
                sub_3.add("增加0.00");
            }
        }

        map.put("datas_3",datas_3);
        map.put("sub_3",sub_3);

        //第三段数据
        List<String> datas_4 = new ArrayList<>();
        List<String> sub_4 = new ArrayList<>();
        String sql2 = "field_3,sum(field_4) as field_4,";
        for (int i = 2; i < 5; i++) {
            sql2+="avg(field_11_"+i+"_1) as field_11_"+i+"_1,avg(field_11_"+i+"_2) as field_11_"+i+"_2";
            if (i!=4){
                sql2+=",";
            }
        }
        crop_01_02_table_03_04 item = researchReportMapper.table_double_0304(account,year,sql2);
        crop_01_02_table_03_04 item_front = researchReportMapper.table_double_0304(account,String.valueOf(Integer.parseInt(year)-1),sql2);
        //计算中间部分的数据
        double tempData = 0.0;
        double tempPercent = 0.0;
        double tempPercent2 = 0.0;
        if (item!=null){
            String field_4 = item.getField_4();
            datas_4.add(field_4);
            switch (item.getField_3()){
                case "晚粳稻":
                    datas_4.add("100");
                    datas_4.add("0");
                    break;
                case "晚籼稻":
                    datas_4.add("0");
                    datas_4.add("100");
                    break;
            }
            for (int i = 2; i < 5; i++) {
                Method m1 = item.getClass().getMethod("getField_11_" + i + "_1");
                Method m2 = item.getClass().getMethod("getField_11_" + i + "_2");
                String invoke1 = (String) m1.invoke(item);
                String invoke2 = (String) m2.invoke(item);
                if (invoke1!=null){
                    datas_4.add(invoke1);
                    tempData+=Double.valueOf(invoke1);
                }else{
                    datas_4.add("0.00");
                }
                if (invoke2!=null){
                    datas_4.add(invoke2);
                    tempPercent+=Double.valueOf(invoke2);
                }else{
                    datas_4.add("0.00");
                }
            }
        }else{
            for (int i = 0; i < 9; i++) {
                datas_4.add("0.00");
            }
            sub_4.add("增加0.00");
        }
        datas_4.add(String.valueOf(tempData));
        datas_4.add(String.valueOf(tempPercent));
        //计算与往年的数据差值
        if (item!=null&&item_front!=null){
            String field_4 = item.getField_4();
            String field_41 = item_front.getField_4();
            if (field_4!=null&&field_41!=null){
                double temp =  Double.valueOf(field_4)-Double.valueOf(field_41);
                if (temp>=0){
                    sub_4.add("增加"+df.format(temp));
                }else{
                    sub_4.add("减少"+df.format(-temp));
                }
            }
            else if(field_4!=null){
                sub_4.add("增加"+field_4);
            }else{
                sub_4.add("增加0.00");
            }
            for (int i = 2; i < 5; i++) {
                Method method = item_front.getClass().getMethod("getField_11_" + i + "_2");
                String invoke = (String) method.invoke(item_front);
                if (invoke != null) {
                    tempPercent2+=Double.valueOf(invoke);
                }
            }
        }
        else if(item!=null){
            String field_4 = item.getField_4();
            if(field_4!=null){
                sub_4.add("增加"+field_4);
            }else{
                sub_4.add("增加0.00");
            }
        }
        else{
            sub_4.add("增加0.00");
        }
        sub_4.add(df.format(tempPercent-tempPercent2));
        map.put("datas_4",datas_4);
        map.put("sub_4",sub_4);
        return map;
    }

    /*
       双季晚稻分蘖－孕穗期苗情
       对应表：4-1 4-2 3-2-2
    */

    @Override
    public Map<String, Object> wandao_yunhuiTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("0.00");
        //计算出平均叶龄和亩茎蘖数
        List<String> datas_1 = new ArrayList<>();
        crop_01_02_table_04_01 item_0401_1 = researchReportMapper.table_double_0401(account, year, null, null);
        if (item_0401_1!=null){
            String field_10_3 = item_0401_1.getField_10_3();
            String field_10_4 = item_0401_1.getField_10_4();
            if (field_10_3!=null){
                datas_1.add(field_10_3);
            }else{
                datas_1.add("0.00");
            }
            if (field_10_4!=null){
                datas_1.add(field_10_4);
            }else{
                datas_1.add("0.00");
            }
        }else{
            datas_1.add("0.00");
            datas_1.add("0.00");
        }
        //其中，人工移栽的叶龄
        String[] culMethods = {"人工移栽","盘育抛栽","机械插秧"};
        for (String culMethod : culMethods) {
            crop_01_02_table_04_01 item = researchReportMapper.table_double_0401(account, year, culMethod, null);
            if (item!=null){
                String field_10_3 = item_0401_1.getField_10_3();
                String field_10_4 = item_0401_1.getField_10_4();
                if (field_10_3!=null){
                    datas_1.add(field_10_3);
                }else{
                    datas_1.add("0.00");
                }
                if (field_10_4!=null){
                    datas_1.add(field_10_4);
                }else{
                    datas_1.add("0.00");
                }
            }else{
                datas_1.add("0.00");
                datas_1.add("0.00");
            }
        }
        //直播田平均叶龄
        crop_01_02_table_04_02 item_0402_1 = researchReportMapper.table_double_0402(account, year, null);
        if (item_0402_1!=null){
            String field_10_3 = item_0402_1.getField_10_3();
            String field_10_4 = item_0402_1.getField_10_4();
            if (field_10_3!=null){
                datas_1.add(field_10_3);
            }else{
                datas_1.add("0.00");
            }
            if (field_10_4!=null){
                datas_1.add(field_10_4);
            }else{
                datas_1.add("0.00");
            }
        }else{
            datas_1.add("0.00");
            datas_1.add("0.00");
        }
        map.put("datas_1",datas_1);
        //第二段数据
        List<String> datas_2 = new ArrayList<>();
        List<String> sub_2 = new ArrayList<>();
        String sql2 = "field_3,sum(field_4) as field_4,";
        for (int i = 2; i < 5; i++) {
            sql2+="avg(field_11_"+i+"_1) as field_11_"+i+"_1,avg(field_11_"+i+"_2) as field_11_"+i+"_2";
            if (i!=4){
                sql2+=",";
            }
        }
        crop_01_02_table_03_04 item = researchReportMapper.table_double_0304(account,year,sql2);
        crop_01_02_table_03_04 item_front = researchReportMapper.table_double_0304(account,String.valueOf(Integer.parseInt(year)-1),sql2);
        //计算中间部分的数据
        double tempData = 0.0;
        double tempPercent = 0.0;
        double tempPercent2 = 0.0;
        if (item!=null){
            String field_4 = item.getField_4();
            if (field_4!=null){
                datas_2.add(field_4);
            }else{
                datas_2.add("0.00");
            }
            for (int i = 2; i < 5; i++) {
                Method m1 = item.getClass().getMethod("getField_11_" + i + "_1");
                Method m2 = item.getClass().getMethod("getField_11_" + i + "_2");
                String invoke1 = (String) m1.invoke(item);
                String invoke2 = (String) m2.invoke(item);
                if (invoke1!=null){
                    datas_2.add(invoke1);
                    tempData+=Double.valueOf(invoke1);
                }else{
                    datas_2.add("0.00");
                }
                if (invoke2!=null){
                    datas_2.add(invoke2);
                    tempPercent+=Double.valueOf(invoke2);
                }else{
                    datas_2.add("0.00");
                }
            }
        }else{
            for (int i = 0; i < 7; i++) {
                datas_2.add("0.00");
            }
            sub_2.add("增加0.00");
        }
        datas_2.add(String.valueOf(tempData));
        datas_2.add(String.valueOf(tempPercent));
        //计算与往年的数据差值
        if (item!=null&&item_front!=null){
            String field_4 = item.getField_4();
            String field_41 = item_front.getField_4();
            if (field_4!=null&&field_41!=null){
                double temp =  Double.valueOf(field_4)-Double.valueOf(field_41);
                if (temp>=0){
                    sub_2.add("增加"+df.format(temp));
                }else{
                    sub_2.add("减少"+df.format(-temp));
                }
            }
            else if(field_4!=null){
                sub_2.add("增加"+field_4);
            }else{
                sub_2.add("增加0.00");
            }
            for (int i = 2; i < 5; i++) {
                Method method = item_front.getClass().getMethod("getField_11_" + i + "_2");
                String invoke = (String) method.invoke(item_front);
                if (invoke != null) {
                    tempPercent2+=Double.valueOf(invoke);
                }
            }
        }
        else if(item!=null){
            String field_4 = item.getField_4();
            if(field_4!=null){
                sub_2.add("增加"+field_4);
            }else{
                sub_2.add("增加0.00");
            }
        }
        else{
            sub_2.add("增加0.00");
        }
        sub_2.add(df.format(tempPercent-tempPercent2));

        map.put("datas_2",datas_2);
        map.put("sub_2",sub_2);
        return map;
    }
    /*
       双季晚稻灌浆期预估产
       对应表：7-1
    */
    @Override
    public Map<String, Object> wandao_yuGuChanTemplate(String account) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String year = dateFormat.format(new Date());
        DecimalFormat df = new DecimalFormat("0.00");


        //查出这两年的数据
        String sql = "sum(field_5) as field_5,";
        for (int i = 6; i < 8; i++) {
            sql+="sum(field_"+i+"_1)as field_"+i+"_1,avg(field_"+i+"_2) as field_"+i+"_2," +
                    "avg(field_"+i+"_3) as field_"+i+"_3,avg(field_"+i+"_4) as field_"+i+"_4," +
                    "avg(field_"+i+"_5) as field_"+i+"_5,avg(field_"+i+"_6) as field_"+i+"_6";
            if (i!=7){
                sql+=",";
            }
        }
        crop_01_02_table_07_01 item_now = researchReportMapper.table_double_0701(account, year, sql);
        crop_01_02_table_07_01 item_front = researchReportMapper.table_double_0701(account, String.valueOf(Integer.parseInt(year)), sql);
        List<String> datas_1 = new ArrayList<>();
        List<String> subs_1 = new ArrayList<>();
        //第一段数值 9月10日前的数据
        if (item_now!=null&&item_front!=null){
            double totalArea_wandao = 1;
            String field_5 = item_now.getField_5();
            if (field_5!=null){
                totalArea_wandao = Double.valueOf(field_5);
            }
            for (int i = 1; i < 7; i++) {
                //今年数据
                Method m1 = item_now.getClass().getMethod("getField_6_" + i + "");
                String invoke1 = (String) m1.invoke(item_now);
                //往年数据
                Method m2 = item_front.getClass().getMethod("getField_6_" + i + "");
                String invoke2 = (String) m2.invoke(item_front);
                //两年数据都存在的情况下
                if (invoke1!=null&&invoke2!=null){
                     if (i==1){
                         datas_1.add(invoke1);
                         datas_1.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                     }
                     else if(i==6){
                         datas_1.add(df.format(Double.valueOf(invoke1)*0.85));
                     }
                     else{
                         datas_1.add(invoke1);
                     }
                     if (i==6){
                         double temp = Double.valueOf(invoke1)-Double.valueOf(invoke2);
                         if (temp>=0){
                             subs_1.add("增加"+df.format(temp));
                             subs_1.add("增加"+df.format(temp/Double.valueOf(invoke2)*100));
                         }else{
                             subs_1.add("减少"+df.format(-temp));
                             subs_1.add("减少"+df.format(-temp/Double.valueOf(invoke2)*100));
                         }
                     }else{
                         double temp = Double.valueOf(invoke1)-Double.valueOf(invoke2);
                         if (temp>=0){
                             subs_1.add("增加"+df.format(temp));
                         }else{
                             subs_1.add("减少"+df.format(-temp));
                         }
                     }

                }
                //仅存在当年情况
                else if(invoke1!=null){
                    if (i==1){
                        datas_1.add(invoke1);
                        datas_1.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                    }
                    else if(i==6){
                        datas_1.add(df.format(Double.valueOf(invoke1)*0.85));
                    }
                    else{
                        datas_1.add(invoke1);
                    }
                    double temp = Double.valueOf(invoke1);
                    if (i==6){
                        if (temp>=0){
                            subs_1.add("增加"+df.format(temp));
                            subs_1.add("增加"+df.format(temp));
                        }else{
                            subs_1.add("减少"+df.format(-temp));
                            subs_1.add("减少"+df.format(-temp));
                        }
                    }else{
                        if (temp>=0){
                            subs_1.add("增加"+df.format(temp));
                        }else{
                            subs_1.add("减少"+df.format(-temp));
                        }
                    }
                }
                //两年数据都不存在
                else{
                    for (int j = 0; j < 7; j++) {
                        datas_1.add("0.00");
                        subs_1.add("增加0.00");
                    }
                }
            }
        }
        else if(item_now !=null){
            double totalArea_wandao = 1;
            String field_5 = item_now.getField_5();
            if (field_5!=null){
                totalArea_wandao = Double.valueOf(field_5);
            }
            for (int i = 1; i < 7; i++) {
                //今年数据
                Method m1 = item_now.getClass().getMethod("getField_6_" + i + "");
                String invoke1 = (String) m1.invoke(item_now);
                if(invoke1!=null){
                    if (i==1){
                        datas_1.add(invoke1);
                        datas_1.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                    }
                    else if(i==6){
                        datas_1.add(df.format(Double.valueOf(invoke1)*0.85));
                    }
                    else{
                        datas_1.add(invoke1);
                    }
                    double temp = Double.valueOf(invoke1);
                    if (i==6){
                        if (temp>=0){
                            subs_1.add("增加"+df.format(temp));
                            subs_1.add("增加"+df.format(temp));
                        }else{
                            subs_1.add("减少"+df.format(-temp));
                            subs_1.add("减少"+df.format(-temp));
                        }
                    }else{
                        if (temp>=0){
                            subs_1.add("增加"+df.format(temp));
                        }else{
                            subs_1.add("减少"+df.format(-temp));
                        }
                    }
                }
                //两年数据都不存在
                else{
                    for (int j = 0; j < 7; j++) {
                        datas_1.add("0.00");
                        subs_1.add("增加0.00");
                    }
                }
            }
        }
        else{
            for (int j = 0; j < 7; j++) {
                datas_1.add("0.00");
                subs_1.add("增加0.00");
            }
        }
        List<String> datas_2 = new ArrayList<>();
        List<String> subs_2 = new ArrayList<>();
        //第二段数据
        //9月10日后的数据
        if (item_now!=null&&item_front!=null){
            double totalArea_wandao = 1;
            String field_5 = item_now.getField_5();
            if (field_5!=null){
                totalArea_wandao = Double.valueOf(field_5);
            }
            for (int i = 1; i < 7; i++) {
                //今年数据
                Method m1 = item_now.getClass().getMethod("getField_7_" + i + "");
                String invoke1 = (String) m1.invoke(item_now);
                //往年数据
                Method m2 = item_front.getClass().getMethod("getField_7_" + i + "");
                String invoke2 = (String) m2.invoke(item_front);
                //两年数据都存在的情况下
                if (invoke1!=null&&invoke2!=null){
                    if (i==1){
                        datas_2.add(invoke1);
                        datas_2.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                    }
                    else if(i==6){
                        datas_2.add(df.format(Double.valueOf(invoke1)*0.85));
                    }
                    else{
                        datas_2.add(invoke1);
                    }
                    if (i==6){
                        double temp = Double.valueOf(invoke1)-Double.valueOf(invoke2);
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                            subs_2.add("增加"+df.format(temp/Double.valueOf(invoke2)*100));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                            subs_2.add("减少"+df.format(-temp/Double.valueOf(invoke2)*100));
                        }
                    }else{
                        double temp = Double.valueOf(invoke1)-Double.valueOf(invoke2);
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                        }
                    }

                }
                //仅存在当年情况
                else if(invoke1!=null){
                    if (i==1){
                        datas_2.add(invoke1);
                        datas_2.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                    }
                    else if(i==6){
                        datas_2.add(df.format(Double.valueOf(invoke1)*0.85));
                    }
                    else{
                        datas_2.add(invoke1);
                    }
                    double temp = Double.valueOf(invoke1);
                    if (i==6){
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                            subs_2.add("增加"+df.format(temp));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                            subs_2.add("减少"+df.format(-temp));
                        }
                    }else{
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                        }
                    }
                }
                //两年数据都不存在
                else{
                    for (int j = 0; j < 7; j++) {
                        datas_2.add("0.00");
                        subs_2.add("增加0.00");
                    }
                }
            }
        }
        else if(item_now !=null){
            double totalArea_wandao = 1;
            String field_5 = item_now.getField_5();
            if (field_5!=null){
                totalArea_wandao = Double.valueOf(field_5);
            }
            for (int i = 1; i < 7; i++) {
                //今年数据
                Method m1 = item_now.getClass().getMethod("getField_7_" + i + "");
                String invoke1 = (String) m1.invoke(item_now);
                if(invoke1!=null){
                    if (i==1){
                        datas_2.add(invoke1);
                        datas_2.add(df.format(Double.valueOf(invoke1)/totalArea_wandao));
                    }
                    else if(i==6){
                        datas_2.add(df.format(Double.valueOf(invoke1)*0.85));
                    }
                    else{
                        datas_2.add(invoke1);
                    }
                    double temp = Double.valueOf(invoke1);
                    if (i==6){
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                            subs_2.add("增加"+df.format(temp));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                            subs_2.add("减少"+df.format(-temp));
                        }
                    }else{
                        if (temp>=0){
                            subs_2.add("增加"+df.format(temp));
                        }else{
                            subs_2.add("减少"+df.format(-temp));
                        }
                    }
                }
                //两年数据都不存在
                else{
                    for (int j = 0; j < 7; j++) {
                        datas_2.add("0.00");
                        subs_2.add("增加0.00");
                    }
                }
            }
        }
        else{
            for (int j = 0; j < 7; j++) {
                datas_2.add("0.00");
                subs_2.add("增加0.00");
            }
        }


        map.put("datas_1",datas_1);
        map.put("datas_2",datas_2);
        map.put("subs_1",subs_1);
        map.put("subs_2",subs_2);

        return map;
    }




}
