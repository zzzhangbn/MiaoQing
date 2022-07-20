package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 9:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class research_template {
    private String num;
    private String percent;
    private String percent_2;
    private String subPercent;
    private String subNum;
    private String area;
    private String area_2;
    private String yxh;
    private String zls;
    private String jsl;
    private String qlz;
    private String chanliang_1;
    private String chanliang_2;
    private String yileimiao;
    private String erleimiao;
    private String sanleimiao;
    private String muxue;
    private String sub_muxue;
    private String jibenmiao;
    private String sub_jibenmiao;
    private String jibenmiao_zhibo;
    private String jibenmiao_zhibo_sub;
    private String yeling;
    private String sub_yeling;
    private String yeling_zhibo;
    private String yeling_zhibo_sub;
    private String jinye_zhibo;
    private String jinye_zhibo_sub;
    private String jinye;
    private String sub_jinye;
    private List<String> datalist;




}
