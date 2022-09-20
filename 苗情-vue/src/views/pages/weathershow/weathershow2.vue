<template>
  <div class="zuida_box">
    <div class="tianqichajian_box" align="center">
      <iframe
        width="1000"
        height="100"
        frameborder="0"
        scrolling="no"
        hspace="0"
        src="https://i.tianqi.com/?c=code&a=getcode&id=39&num=5&icon=1&num=5&site=14"
      ></iframe>
    </div>
    <div class="select_box" align="center">
      <el-select v-model="cityType" clearable placeholder="城市">
        <el-option
          v-for="item in cities"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <el-select
        v-model="yearType"
        clearable
        collapse-tags
        style="margin-left: 20px"
        placeholder="年份"
      >
        <el-option
          v-for="item in years"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <el-select
        v-model="monthType"
        clearable
        collapse-tags
        style="margin-left: 20px"
        placeholder="月份"
      >
        <el-option
          v-for="item in months"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
      <span class="chaxun">
        <el-button type="primary" @click="searchInfo">查询</el-button>
      </span>
      <div class="date_select">
        <span class="chengshixuanze">
          <el-select v-model="cityTypes" clearable placeholder="城市">
            <el-option
              v-for="item in citiess"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </span>
        <span class="date_range">
          <el-date-picker
            v-model="daterange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="yyyy-MM-dd"
          >
            >
          </el-date-picker>
        </span>
        <span class="datechaxun">
          <el-button type="primary" @click="datesearchInfo">查询</el-button>
        </span>
      </div>

      <div class="history_weather_show">
        <el-table
          stripe="true"
          :data="weatherData"
          style="width: 100% height:100%"
          :header-cell-style="{ background: '#eef1f6', color: '#606266' }"
        >
          <el-table-column prop="dates" label="日期" width="120" align="center">
          </el-table-column>
          <el-table-column prop="weathers" label="天气状况" width="120" align="center">
          </el-table-column>
          <el-table-column
            prop="highest_temperature"
            label="最高温度(℃)"
            width="120"
            align="center"
          >
          <template slot-scope="scope">
              <div class="specialColor">{{scope.row.highest_temperature}}</div>
            </template>
          </el-table-column>
          <el-table-column
            prop="minimum_temperature"
            label="最低温度（℃）"
            width="120"
            align="center"
          >
          </el-table-column>
          <el-table-column prop="wind_direction" label="风向" width="120" align="center">
          </el-table-column>
          <el-table-column prop="humidity" label="湿度" width="120" align="center">
          </el-table-column>
          <el-table-column prop="ultraviolet_rays" label="紫外线" width="120" align="center">
            <template slot-scope="scope">
              <span
                v-if="scope.row.ultraviolet_rays == '很强'"
                style="color: red"
                >很强</span
              >
              <span v-if="scope.row.ultraviolet_rays == '强'" style="color: red"
                >强</span
              >
              <span
                v-if="scope.row.ultraviolet_rays == '中'"
                style="color: rgb(84, 195, 26)"
                >中</span
              >
              <span
                v-if="scope.row.ultraviolet_rays == '弱'"
                style="color: black"
                >弱</span
              >
              <span
                v-if="scope.row.ultraviolet_rays == '很弱'"
                style="color: black"
                >很弱</span
              >
            </template>
          </el-table-column>
          <el-table-column prop="air_quality" label="空气质量指数" width="120" align="center">
            <template slot-scope="scope">
              <span
                v-if="scope.row.air_quality == '优'"
                style="color: rgb(84, 195, 26)"
                >优</span
              >
              <span v-if="scope.row.air_quality == '良'" style="color: #dec674"
                >良</span
              >
              <span
                v-if="scope.row.air_quality == '轻度污染'"
                style="color: #FFA500"
                >轻度污染</span
              >
              <span
                v-if="scope.row.air_quality == '中度污染'"
                style="color: red"
                >中度污染</span
              >
              <span
                v-if="scope.row.air_quality == '重度污染'"
                style="color: #FF00FF"
                >重度污染</span
              >
              <span
                v-if="scope.row.air_quality == '严重污染'"
                style="color: #FF4500"
                >严重污染</span
              >
            </template>
          </el-table-column>
          <el-table-column prop="pm" label="PM2.5" width="120" align="center">
          </el-table-column>
          <el-table-column
            prop="sunlight_time"
            label="日照时长（h）"
            width="120"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="precipitations"
            label="降水量（mm）"
            width="120"
            align="center"
          >
          </el-table-column>
        </el-table>
      </div>
      <div class="echart1">
        <div class="qiwen">
          <span
            ><el-select v-model="cityType" placeholder="请选择">
              <el-option
                v-for="item in cities"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-select v-model="yearType" placeholder="请选择">
              <el-option
                v-for="item in years"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-select v-model="monthType" placeholder="请选择">
              <el-option
                v-for="item in months"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <span class="chaxun">
              <el-button type="primary" @click="echart1">echart1查询</el-button>
            </span>
          </span>
        </div>
        <div id="echart1" style="width: 1200px; height: 550px"></div>
      </div>
      <div class="echart2">
        <div class="jiangshui">
          <span
            ><el-select v-model="cityType" placeholder="请选择">
              <el-option
                v-for="item in cities"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-select v-model="yearType" placeholder="请选择">
              <el-option
                v-for="item in years"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-select v-model="monthType" placeholder="请选择">
              <el-option
                v-for="item in months"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <span class="chaxun">
              <el-button type="primary" @click="echart2">echart2查询</el-button>
            </span>
          </span>
        </div>
        <div id="echart2" style="width: 1200px; height: 550px"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import * as echarts from "echarts";
export default {
  data() {
    return {
      //表中数据
      weatherData: [],
      //表名
      tableLabel: "历史天气表",
      tableName: "weatherinformation",
      //城市的option
      cities: [],
      cityType: "肥东县",
      //年份的option
      years: [],
      yearType: "2020年",
      //月份的option
      months: [],
      monthType: "6月",
      //日期选择
      daterange: "",
      //日期的城市选择
      citiess: [],
      cityTypes: "",

      //echart1
      echart1options: [
        {
          value: "选项1",
          label: "2020年12月",
        },
        {
          value: "选项2",
          label: "2020年11月",
        },
        {
          value: "选项3",
          label: "2020年10月",
        },
        {
          value: "选项4",
          label: "2020年9月",
        },
        {
          value: "选项5",
          label: "2020年8月",
        },
      ],
      echart1value: "",

      //echart2
      echart2options: [
        {
          value: "选项1",
          label: "2020年12月",
        },
        {
          value: "选项2",
          label: "2020年11月",
        },
        {
          value: "选项3",
          label: "2020年10月",
        },
        {
          value: "选项4",
          label: "2020年9月",
        },
        {
          value: "选项5",
          label: "2020年8月",
        },
      ],
      echart2value: "",
    };
  },
  created() {
    //this.initData();
    this.test();

  },
  methods: {
    test() {
      var that = this;
      axios({
        url: "https://i.tianqi.com/?c=code&a=getcode&id=39&num=5&icon=1&num=5&site=14",
        method:"get"
      }).then((res)=>{
        console(res);
      })
    }
    //初始化数据
    initData() {
      this.getTableData();
      this.getSelect();
      this.getSelects();
      // this.echart1();
      // this.echart2();
    },
    //获取表数据
    getTableData() {
      const _this = this;
      axios({
        url: "/getWeatherData",
        method: "get",
      })
        .then((res) => {
          _this.weatherData = res.data;
          //console.log(_this.weatherData);
        })
        .catch((err) => {
          return this.$message.error("查询数据失败");
        });
    },
    //获取下拉框内容
    getSelect() {
      const _this = this;
      axios({
        url: "/getselect",
        method: "get",
      })
        .then((res) => {
          var optionData = [];
          optionData = res.data;
          //城市
          var cityData = [];
          //年份
          var yearData = [];
          //月份
          var monthData = [];
          ////console.log(optionData);
          for (var i = 0; i < optionData.length; ++i) {
            if (optionData[i].option === "城市") {
              cityData.push({
                label: optionData[i].list,
                value: optionData[i].list,
              });
            }
            if (optionData[i].option === "年份") {
              yearData.push({
                label: optionData[i].list,
                value: optionData[i].list,
              });
            }
            if (optionData[i].option === "月份") {
              monthData.push({
                label: optionData[i].list,
                value: optionData[i].list,
              });
            }
          }
          _this.cities = cityData;
          _this.years = yearData;
          _this.months = monthData;
        })
        .catch((err) => {
          return this.$message.error("查询数据失败！");
        });
    },

    //搜索
    searchInfo() {
      const _this = this;
      axios({
        url: "/getsearch",
        method: "post",
        params: {
          cityType: this.cityType,
          yearType: this.yearType,
          monthType: this.monthType,
        },
      })
        .then((res) => {
          _this.weatherData = res.data;
          ////console.log(_this.tableData);
        })
        .catch((err) => {
          return this.$message.error("查询数据失败！");
        });
    },

    //日期前的下拉框
    getSelects() {
      const _this = this;
      axios({
        url: "/getselects",
        method: "get",
      })
        .then((res) => {
          var optionDatas = [];
          optionDatas = res.data;
          //城市
          var cityDatas = [];
          ////console.log(optionData);
          for (var i = 0; i < optionDatas.length; ++i) {
            if (optionDatas[i].option === "城市") {
              cityDatas.push({
                label: optionDatas[i].list,
                value: optionDatas[i].list,
              });
            }
          }
          _this.citiess = cityDatas;
        })
        .catch((err) => {
          return this.$message.error("查询数据失败！");
        });
    },

    //根据日期范围查询
    datesearchInfo() {
      const _this = this;
      axios({
        url: "/datesearch",
        method: "post",
        params: {
          cityTypes: this.cityTypes,
          daterange1: this.daterange[0],
          daterange2: this.daterange[1],
        },
      })
        .then((res) => {
          _this.weatherData = res.data;
          // //console.log(_this.daterange);
        })
        .catch((err) => {
          return this.$message.error("查询数据失败！");
        });
    },

    //echart1搜索
    echart1() {
      axios({
        url: "/echart1",
        method: "post",
        params: {
          cityType: this.cityType,
          yearType: this.yearType,
          monthType: this.monthType,
        },
      }).then((res) => {
        var echart1data = res.data;
        ////console.log(echart1data);
        if (echart1data == "") {
          this.$message({
            message: "该地区暂无数据上报，请重新选择",
            type: "warning",
          });
          return;
        }
        this.charts = echarts.init(document.getElementById("echart1"));
        this.charts.setOption({
          title: {
            text: "气温走势图",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["日平均气温", "最高温度", "最低温度"],
          },
          grid: {
            left: "3%",
            right: "8%",
            bottom: "33%",
            containLabel: true,
          },
          toolbox: {
            feature: {
              saveAsImage: {},
              magicType: { type: ["line", "bar"] },
              restore: {},
            },
          },
          xAxis: {
            type: "category",
            boundaryGap: true,
            axisLabel: {
              interval: 0,
              formatter: "{value} 号",
            },
            data: [
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "29",
              "30",
              "31",
            ],
          },
          yAxis: {
            type: "value",
            axisLabel: {
              formatter: "{value} °C",
            },
          },
          series: [
            {
              name: "日平均气温",
              type: "line",
              data: echart1data[0],
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
            },

            {
              name: "最高温度",
              type: "line",
              data: echart1data[1],
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
            },
            {
              name: "最低温度",
              type: "line",
              data: echart1data[2],
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
            },
          ],
        });
      });
    },

    //echart2搜索
    echart2() {
      axios({
        url: "/echart2",
        method: "post",
        params: {
          cityType: this.cityType,
          yearType: this.yearType,
          monthType: this.monthType,
        },
      }).then((res) => {
        var echart2data = res.data;
        //console.log(echart2data);
        if (echart2data == "") {
          this.$message({
            message: "该地区暂无数据上报，请重新选择",
            type: "warning",
          });
          return;
        }
        this.chartss = echarts.init(document.getElementById("echart2"));
        this.chartss.setOption({
          title: {
            text: "降雨量及日照时数图",
          },
          tooltip: {
            trigger: "axis",
          },
          legend: {
            data: ["降雨量", "日照时长"],
          },
          grid: {
            left: "3%",
            right: "8%",
            bottom: "33%",
            containLabel: true,
          },
          toolbox: {
            feature: {
              saveAsImage: {},
              magicType: { type: ["line", "bar"] },
              restore: {},
            },
          },
          xAxis: {
            type: "category",
            boundaryGap: true,
            data: [
              "1",
              "2",
              "3",
              "4",
              "5",
              "6",
              "7",
              "8",
              "9",
              "10",
              "11",
              "12",
              "13",
              "14",
              "15",
              "16",
              "17",
              "18",
              "19",
              "20",
              "21",
              "22",
              "23",
              "24",
              "25",
              "26",
              "27",
              "28",
              "29",
              "30",
              "31",
            ],
            axisLabel: {
              interval: 0,
              formatter: "{value} 号",
            },
          },
          yAxis: [
            {
              type: "value",
              name: "降水量",
              min: 0,
              max: 480,
              interval: 40,
              axisLabel: {
                formatter: "{value} ml",
              },
            },
            {
              type: "value",
              name: "日照时长",
              min: 0,
              max: 24,
              interval: 2,
              axisLabel: {
                formatter: "{value} 小时",
              },
            },
          ],
          series: [
            {
              name: "降雨量",
              data: echart2data[1],
              type: "bar",
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
            },
            {
              name: "日照时长",
              data: echart2data[0],
              type: "line",
              yAxisIndex: 1,
              markPoint: {
                data: [
                  { type: "max", name: "最大值" },
                  { type: "min", name: "最小值" },
                ],
              },
            },
          ],
        });
      });
    },
  },
  mounted() {
    this.echart1();
    this.echart2();
  },
};
</script>

<style lang="less" scoped>
.tianqichajian_box {
  position: relative;
  top: 20px;
}

.chaxun {
  position: relative;
  left: 10px;
}
.history_weather_show {
  position: relative;
  top: 40px;
}
.date_select {
  position: relative;
  top: 20px;
}
.chengshixuanze {
  position: relative;
  right: 10px;
}
// .date_range {
//   position: relative;
//   width:453px ;
// }
.el-date-editor--daterange.el-input,
.el-date-editor--daterange.el-input__inner,
.el-date-editor--timerange.el-input,
.el-date-editor--timerange.el-input__inner {
  width: 453px;
  left: 9px;
}
.datechaxun {
  position: relative;
  left: 21px;
}
.echart1 {
  position: relative;
  top: 80px;
}
.echart2 {
  position: relative;
  top: 10px;
}
</style>


<style>
/*有些不能在scoped设置*/
.el-table--striped
  .el-table__body
  tr.el-table__row--striped.el-table__row--striped.el-table__row--striped
  td {
  background-color: #c0bebe6e ; /*替换为你需要的颜色，觉得优先级不够就加!important*/
}
.specialColor{
    color:red;
  }
</style>

