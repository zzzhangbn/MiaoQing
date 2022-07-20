package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class conditions_analysis {
    private int id;
    private String column_name;
    private String column_code;
    private String second_name;
    private String sub_cate;
}
