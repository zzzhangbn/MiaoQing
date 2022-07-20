package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: wangweiwen
 * Time: 9:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class table_dictionary {
    private int id;
    private String name;
    private String field_name;
    private String field_data_name;
    private String spanContent;
    private String table_name;
    private int in_use;
}
