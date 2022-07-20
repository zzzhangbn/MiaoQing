package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 20:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class research_report {
    private int id;
    private String name;
    private  String crop;
    private String url;
    private String html;
    /**
     * 修改时间
     */
    private LocalDate modification_time;

    /**
     * 修改人
     */
    private String people;

    /**
     * 状态
     */
    private String in_use;

}
