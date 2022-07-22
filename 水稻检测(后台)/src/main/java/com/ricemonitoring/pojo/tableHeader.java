package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tableHeader {
    private int id;
    private String column_id;
    private String column;
    private String father_id;
    private String table_name;
    private String use;
}
