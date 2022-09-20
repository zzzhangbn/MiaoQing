package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class login_record {
    private Integer id;
    private String account;
    private String logintime;
    private String loginip;
    private String loginname;
    private Integer status;
    private String password;
    private Integer currentpage;
    private Integer pagesize;
}
