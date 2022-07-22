package com.ricemonitoring.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class table_audit_2 {
    private int id;
    private String county;
    private String table_name;
    private int record_id;
    private String time;
    private String record_people;
    private String in_use;
}
