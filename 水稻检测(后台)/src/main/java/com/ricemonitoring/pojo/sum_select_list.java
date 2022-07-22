package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sum_select_list {
    private int id;
    private String caption;
    private String list;
    private String area_code;
    private String rice_type;
    private String in_use;

}
