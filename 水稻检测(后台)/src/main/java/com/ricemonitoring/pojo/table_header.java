package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class table_header {
    private int id;
    private String column_id;
    private String column_name;
    private String father_id;
    private String second_name;
    private String content_limit;
    private String formula;
    private String total;
    private String field_type;
    private int is_null;
    private int in_use;
}
