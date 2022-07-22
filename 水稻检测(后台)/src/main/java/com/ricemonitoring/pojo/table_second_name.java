package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class table_second_name {
    private int id;
    private String second_name_id;
    private String second_name;
    private String first_name_id;
    private int in_use;
    private String rice_type;

}
