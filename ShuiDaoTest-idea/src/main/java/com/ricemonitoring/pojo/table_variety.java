package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class table_variety {
    private int id;
    private String varieties;
    private String variety_name;
    private String in_use;
    private String varieties_id;
}
