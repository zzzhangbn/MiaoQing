package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class table_record_back {
    private String record_table;
    private String record_county;
    private int record_id;
    private String reason;
    private String back_time;
    private String record_people;
    private int in_use;
    private String second_name;
}
