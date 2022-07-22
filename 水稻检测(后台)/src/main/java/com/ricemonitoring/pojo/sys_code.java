package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 8:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class sys_code {
    private int id;
    private String code;
    private String code_name;
    private String kind;
}
