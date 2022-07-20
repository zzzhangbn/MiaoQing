package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class monitoring_point {
    private int id;
    private String code;
    private String province;
    private String city;
    private String area;
    private String areaCate;
    private String crop;
    private int in_use;
}
