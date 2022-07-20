package com.ricemonitoring.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 表10 -2   20xx年度水稻监测点灌浆速率调查表（晚稻）
 * </p>
 *
 * @author admin
 * @since 2021-07-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
    public class crop_01_02_table_10_02{

      /**
     * id,自增长
     */
      private int id;

      /**
     * 县（市、区）
     */
      private String field_1;

      /**
     * 监测点代码
     */
      private String field_2;

      /**
     * 地区类型
     */
      private String field_3;

      /**
     * 稻作类型
     */
      private String field_4;

      /**
     * 品种名称
     */
      private String field_5;

      /**
     * 种植方式
     */
      private String field_6;

      /**
     * 灌浆速率测定1（扬花）：日期
     */
      private String field_7_1;

      /**
     * 千粒重（克）
     */
      private String field_7_2;

      /**
     * 灌浆速率测定2（扬花+7天）：日期
     */
      private String field_8_1;

      /**
     * 千粒重（克）
     */
      private String field_8_2;

      /**
     * 灌浆速率（克/千粒•天）
     */
      private String field_8_3;

      /**
     * 灌浆速率测定3（扬花+14天）：日期
     */
      private String field_9_1;

      /**
     * 千粒重（克）
     */
      private String field_9_2;

      /**
     * 灌浆速率（克/千粒•天）
     */
      private String field_9_3;

      /**
     * 灌浆速率测定4（扬花+21天）：日期
     */
      private String field_10_1;

      /**
     * 千粒重（克）
     */
      private String field_10_2;

      /**
     * 灌浆速率（克/千粒•天）
     */
      private String field_10_3;

      /**
     * 灌浆速率测定5（扬花+28天）：日期
     */
      private String field_11_1;

      /**
     * 千粒重（克）
     */
      private String field_11_2;

      /**
     * 灌浆速率（克/千粒•天）
     */
      private String field_11_3;

      /**
     * 灌浆速率测定6（扬花+28天）：日期
     */
      private String field_12_1;

      /**
     * 千粒重（克）
     */
      private String field_12_2;

      /**
     * 灌浆速率（克/千粒•天）
     */
      private String field_12_3;

      /**
     * 修改时间
     */
      private String modification_time;

      /**
     * 修改人员
     */
      private String people;

      /**
     * 使用状态
     */
      private String in_use;


}
