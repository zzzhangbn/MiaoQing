package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class picture {
    private int id;
    private String picturename;
    private String pictureurl;
}
