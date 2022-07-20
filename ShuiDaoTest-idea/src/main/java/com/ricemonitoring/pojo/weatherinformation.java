package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class weatherinformation {
    //序号
    private int id;
    //城市
    private String citys;
    //日期
    private String dates;
    //天气状况
    private String weathers;
    //实时温度
    private String realtime_temperature;
    //最高温度
    private String highest_temperature;
    //最低温度
    private String minimum_temperature;
    //风向
    private String wind_direction;
    //年份
    private String years;
    //月份
    private String months;
    //湿度
    private String humidity;
    //紫外线
    private String ultraviolet_rays;
    //空气质量
    private String air_quality;
    //PM
    private String pm;
    //日照时长
    private String sunlight_time;
    //降水量
    private String precipitations;
}
