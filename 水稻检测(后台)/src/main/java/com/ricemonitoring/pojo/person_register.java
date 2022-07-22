package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class person_register {
    private int id;
    private String name;
    private String phone;
    private String password;
    private String crop;
    private String plot;
    private String access;
    private String apply_date;
    private String audit;

}
