package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class person_people {
    private String account;
    private String name;
    private String password;
    private String area;
    private String phone;
    private String crop;
    private String sub_crop;
    private String access;
    private String use_date;
    private int in_use;
}
