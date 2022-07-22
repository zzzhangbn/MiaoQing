package com.ricemonitoring.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_02_01{

      /**
     * 主键id
     */
      private int id;

      /**
     * 监测点代码：
     */
      private String field_1;

      /**
     * 监测年份：
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_2;

      /**
     * 地址-市
     */
      private String field_3_1;

      /**
     * 地址-县（市、区）
     */
      private String field_3_2;

      /**
     * 地址-镇（乡）
     */
      private String field_3_3;

      /**
     * 地址-村名
     */
      private String field_3_4;

      /**
     * 地址-户主
     */
      private String field_3_5;

      /**
     * 监测田块-种植面积（亩）
     */
      private String field_4_1;

      /**
     * 监测田块-地区类型
     */
      private String field_4_2;

      /**
     * 监测田块-土壤类型
     */
      private String field_4_3;

      /**
     * 监测田块-前茬类型
     */
      private String field_4_4;

      /**
     * 监测田块-水管方式
     */
      private String field_4_5;

      /**
     * 监测田块-N(g/Kg)
     */
      private String field_4_6;

      /**
     * 监测田块-P(g/Kg)
     */
      private String field_4_7;

      /**
     * 监测田块-K(g/Kg)
     */
      private String field_4_8;

      /**
     * 监测田块-有机质(g/Kg)
     */
      private String field_4_9;

      /**
     * 监测田块-秸秆还田量
     */
      private String field_4_10;

      /**
     * 品种（品系）-名称
     */
      private String field_5_1;

      /**
     * 品种（品系）-稻作类型
     */
      private String field_5_2;

      /**
     * 品种（品系）-品系类型
     */
      private String field_5_3;

      /**
     * 田间信息-栽种方式
     */
      private String field_6_1;

      /**
     * 田间信息-播期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_6_2;

      /**
     * 田间信息-移栽秧龄（天）
     */
      private String field_6_3;

      /**
     * 田间信息-移栽叶龄（叶）
     */
      private String field_6_4;

      /**
     * 田间信息-基本苗（万/亩）
     */
      private String field_6_5;

      /**
     * 田间信息-收获日期
     */
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
      private Date field_6_6;

      /**
     * 田间信息-亩有效穗(万)
     */
      private String field_6_7;

      /**
     * 田间信息-穗总粒数(个)
     */
      private String field_6_8;

      /**
     * 田间信息-结实率(％)
     */
      private String field_6_9;

      /**
     * 田间信息-千粒重(g)
     */
      private String field_6_10;

      /**
     * 田间信息-理论产量(kg/亩)
     */
      private String field_6_11;

      /**
     * 田间信息-实收产量(kg/亩)
     */
      private String field_6_12;

      /**
     * 修改时间
     */
      private String modification_time;

      /**
     * 修改人
     */
      private String people;

      /**
     * 状态
     */
      private String in_use;

        /**
         * 状态
         */
        private String continue_use;


}
