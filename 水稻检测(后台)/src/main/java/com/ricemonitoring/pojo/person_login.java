package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class person_login {
    private String account;
    private String password;
    private String newPassword;
    private String access;
    private String crop;
    private String area;
    private String sub_crop;
    private int in_use;
    private int fault;
}
