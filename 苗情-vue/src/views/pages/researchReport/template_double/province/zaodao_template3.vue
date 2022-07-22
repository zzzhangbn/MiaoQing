<template>
  <!--早稻分蘖期苗情报告  -->
  <div class="all">
    <el-breadcrumb
      separator="/"
      style="
        margin: 20px 0 0 0;
        font-size: 20px;
        width: 300px;
        display: inline-block;
      "
    >
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <router-link :to="{ path: '/researchReport' }">
        <el-breadcrumb-item>研究报告</el-breadcrumb-item>
      </router-link>
    </el-breadcrumb>
    <el-button
      type="success"
      style="float: right; margin: 20px 0 0 0"
      @click="saveReport"
      >报告上传</el-button
    >
    <el-card style="margin: 20px 0 0 0; cursor: pointer">
      <div class="content" ref="content">
        <h1 style="width: 80%; margin: 0 auto; text-align: center">
          <input
            ref="title"
            class="title"
            type="text"
            style="
              width: 70%;
              font-size: 25px;
              background: none;
              text-align: center;
              border: none;
            "
            :value="thisYear+title"
          />
        </h1>
        <div>
          <h2>一、当前苗情</h2>
          <h3>1．叶龄、群体茎蘖数介绍</h3>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 移抛栽田平均叶龄<u>{{
              reportData.datas_2[0]
            }}</u
            >叶，比去年<u>{{ reportData.sub_1[3] }}</u> 叶； 平均亩茎蘖数
            <u>{{ reportData.datas_2[1] }}</u> 万，比去年<u>{{
              reportData.sub_1[4]
            }}</u>
            万。其中， 人工移栽的叶龄
            <u>{{ reportData.datas_2[2] }}</u> 叶，亩茎蘖数<u>{{
              reportData.datas_2[3]
            }}</u>
            万； 抛栽的叶龄 <u>{{ reportData.datas_2[4] }}</u> 叶，亩茎蘖数
            <u>{{ reportData.datas_2[5] }}</u> 万； 机械栽插的叶龄
            <u>{{ reportData.datas_2[6] }}</u> 叶，亩茎蘖数
            <u>{{ reportData.datas_2[7] }}</u> 万。 直播田平均叶龄
            <u>{{ reportData.datas_2[8] }}</u> 叶, 比去年<u>{{
              reportData.sub_2[0]
            }}</u>
            叶； 平均亩茎蘖数 <u>{{ reportData.datas_2[9] }}</u> 万，比去年<u>{{
              reportData.sub_1[1]
            }}</u>
            万。
          </p>
          <h3>3.苗情介绍</h3>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 据6个监测县面上调查：当前一类苗
            <u>{{ reportData.datas_3[0] }}</u> 万亩，占
            <u>{{ reportData.percents[0] }}</u> %； 二类苗
            <u>{{ reportData.datas_3[1] }}</u> 万亩，占
            <u>{{ reportData.percents[1] }}</u> %； 三类苗
            <u>{{ reportData.datas_3[2] }}</u> 万亩，占
            <u>{{ reportData.percents[2] }}</u> %。 一、二类苗比例占
            <u>{{ reportData.percents[3] }}</u> %，比去年同期（
            <u>{{ reportData.percents[4] }}</u> %）
            <u>{{ reportData.percents[5] }}</u> 个百分点，4月份以来
            <input
              v-model="inputValues[0]"
              placeholder="手动输入播栽期变化的评价"
              type="text"
              class="input"
              style="width: 400px"
            />。
          </p>
        </div>
        <div>
          <h3 style="width: 80%; margin: 0 auto; text-align: center">
            6个监测县早稻分蘖期至孕穗初期苗情评价表
          </h3>
          <div class="table">
            <table border="1" cellspacing="0" cellpadding="10" width="100%">
              <thead>
                <tr class="firstHead">
                  <th colspan="1" rowspan="2">县（市、区）</th>
                  <th colspan="4">早稻面积</th>
                  <th colspan="4">一类苗</th>
                  <th colspan="4">二类苗</th>
                  <th colspan="4">三类苗</th>
                </tr>
                <tr class="twoHead">
                  <th colspan="2">上年度</th>
                  <th colspan="2">本年度</th>
                  <th colspan="2">上年度</th>
                  <th colspan="2">本年度</th>
                  <th colspan="2">上年度</th>
                  <th colspan="2">本年度</th>
                  <th colspan="2">上年度</th>
                  <th colspan="2">本年度</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="i in 6" :key="i">
                  <td align="center">庐江县</td>
                  <td colspan="2" align="center">{{reportData.datas_5[(i-1)*4]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_4[(i-1)*4]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_5[i*4-3]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_4[i*4-3]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_5[i*4-2]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_4[i*4-2]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_5[i*4-1]}}</td>
                  <td colspan="2" align="center">{{reportData.datas_4[i*4-1]}}</td>
                </tr>
                <tr>
                  <td align="center">合计</td>
                  <td colspan="2" align="center">{{heji[0]}}</td>
                  <td colspan="2" align="center">{{heji_2[0]}}</td>
                  <td colspan="2" align="center">{{heji[1]}}</td>
                  <td colspan="2" align="center">{{heji_2[1]}}</td>
                  <td colspan="2" align="center">{{heji[2]}}</td>
                  <td colspan="2" align="center">{{heji_2[2]}}</td>
                  <td colspan="2" align="center">{{heji[3]}}</td>
                  <td colspan="2" align="center">{{heji_2[3]}}</td>
                </tr>
                <tr>
                  <td align="center">所占比例</td>
                  <td colspan="2" align="center">--</td>
                  <td colspan="2" align="center">--</td>
                  <td colspan="2" align="center" v-for="i in 6" :key="i">{{bili[i-1]}}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div>
          <h2>二、主要问题</h2>
          <div>
            <textarea
              cols="30"
              rows="10"
              class="textarea"
              placeholder="请输入当前生产中遇到的问题"
              v-model="textareas[0]"
            ></textarea>
          </div>
        </div>
        <div>
          <h2>三、田管意见</h2>
          <div>
            <textarea
              cols="30"
              rows="10"
              class="textarea"
              placeholder="请输入对当前问题的对应及下一阶段田管措施"
              v-model="textareas[1]"
            ></textarea>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import { saveAs } from "file-saver";
export default {
  data() {
    return {
      //后台获取的报告数据
      reportData: {},
      month: "",
      day: "",
      thisYear:'',
      //几个input框的值
      title: "年度双季早稻分蘖－孕穗初期苗情",
      inputValues: ["", "", "", "", "", ""],
      textareas: ["", ""],
      //表格合计数据
      heji:[],
      heji_2:[],
      //表格比例数据
      bili:[],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    getData() {
      let account = sessionStorage.getItem("ms_username");
       var date = new Date();
      this.thisYear = date.getFullYear();
      axios({
        url: "/template_double",
        params: { templateName: "早稻分蘖－孕穗初期苗情", account: "" },
        method: "get",
      }).then((res) => {
        this.reportData = res.data;
        var heji = new Array();//今年合计
        var heji_2 = new Array();//往年合计
        var datas_4 = res.data.datas_4;
        var datas_5 = res.data.datas_5;
        //计算合计数据
        for(let i=1;i<5;i++){
            var temp1 = 0.00;
            var temp2 = 0.00;
            switch(i){
              case 1:
                for(let j=1;j<7;j++){
                  temp1=parseFloat(temp1)+parseFloat(datas_4[(j-1)*4]);
                  temp2=parseFloat(temp2)+parseFloat(datas_5[(j-1)*4]);
                }
                heji.push(temp1.toFixed(2));
                heji_2.push(temp2.toFixed(2));
                break;
              case 2:
                for(let j=1;j<7;j++){
                  temp1=parseFloat(temp1)+parseFloat(datas_4[j*4-3]);
                  temp2=parseFloat(temp2)+parseFloat(datas_5[j*4-3]);
                }
                heji.push(temp1.toFixed(2));
                heji_2.push(temp2.toFixed(2));
                break;
              case 3:
                for(let j=1;j<7;j++){
                  temp1=parseFloat(temp1)+parseFloat(datas_4[j*4-2]);
                  temp2=parseFloat(temp2)+parseFloat(datas_5[j*4-2]);
                }
                heji.push(temp1.toFixed(2));
                heji_2.push(temp2.toFixed(2));
                break;
              case 4:
                for(let j=1;j<7;j++){
                  temp1=parseFloat(temp1)+parseFloat(datas_4[j*4-1]);
                  temp2=parseFloat(temp2)+parseFloat(datas_5[j*4-1]);
                }
                heji.push(temp1.toFixed(2));
                heji_2.push(temp2.toFixed(2));
                break;
            }
        }
        this.heji = heji;
        this.heji_2 = heji_2;
        //计算比例
        var bili = new Array();
        for(let i=1;i<4;i++){
          var fenmu = this.heji[0];
          var fenmu_2= this.heji_2[0];
          if(fenmu==0){
            fenmu = 1.00;
          }
          if(fenmu_2==0){
            fenmu_2 = 1.00;
          }
          bili.push((this.heji[i]/fenmu).toFixed(2));
          bili.push((this.heji_2[i]/fenmu_2).toFixed(2));
        }
        this.bili = bili;
      });
    },
    initTable(id, xAxis, data1, data2) {
      this.$nextTick(() => {
        var myChart = this.$echarts.init(this.$refs[id]);
        var option;
        var colors = ["#5470C6", "#EE6666"];
        option = {
          color: colors,

          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "cross",
            },
          },
          // grid: {
          //     right: '10%'
          // },
          toolbox: {
            feature: {
              saveAsImage: { show: true },
            },
          },
          legend: {
            data: ["面积", "增减百分比"],
          },
          xAxis: [
            {
              type: "category",
              axisTick: {
                alignWithLabel: true,
              },
              data: xAxis,
            },
          ],
          yAxis: [
            {
              type: "value",
              name: "面积",
              position: "left",
              axisLine: {
                show: true,
                lineStyle: {
                  color: colors[0],
                },
              },
              axisLabel: {
                formatter: "{value} 万亩",
              },
            },
            {
              type: "value",
              name: "增减百分比",
              position: "right",
              axisLine: {
                show: true,
                lineStyle: {
                  color: colors[1],
                },
              },
              axisLabel: {
                formatter: "{value} %",
              },
            },
          ],
          series: [
            {
              name: "面积",
              type: "bar",
              data: data1,
            },
            {
              name: "增减百分比",
              type: "line",
              yAxisIndex: 1,
              data: data2,
              smooth: true,
            },
          ],
        };
        option && myChart.setOption(option);
      });
    },
    saveReport() {
      var formData = new FormData();
      var fileName = this.$refs.title.value;
      var tempHtml = this.$refs.content;
      this.$confirm("确定上传？")
        .then((_) => {
          this.$refs.title.setAttribute("value", this.thisYear+"年度双季早稻分蘖－孕穗初期苗情");
          this.$refs.title.setAttribute("disabled", true);
          var inputStyle = `
        width: 98%;
        border: none;
        outline: none;
        border-bottom: 1px solid #000;
        background:none;
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        padding-left: 30px;
        margin: 0 0 10px 0 ;
        `;
          var inputs = $(".input");
          for (let i = 0; i < inputs.length; i++) {
            inputs[i].setAttribute("value", this.inputValues[i]);
            inputs[i].setAttribute("disabled", true);
            inputs[i].setAttribute("style", inputStyle);
          }
          var textareas = $(".textarea");
          for (let i = 0; i < textareas.length; i++) {
            textareas[i].innerHTML = this.textareas[i];
            textareas[i].setAttribute("disabled", true);
            textareas[i].setAttribute("style", inputStyle);
          }
          var html = this.$refs.content;
          //将canvas转化为图片
          // var canvas = html.getElementsByTagName('canvas');
          // for(let i=0;i<canvas.length;i++){
          //   var img=this.convertCanvasToImage(canvas[i]);
          //   this.$refs['table_01_01_'+(i+1)+'_pic'].append(img);
          //   this.$refs['table_01_01_'+(i+1)].style.display =  'none';
          // }
          formData.append("crop", localStorage.getItem("crop"));
          formData.append("fileName", fileName);
          formData.append("html", html.innerHTML);
          // formData.append("userId", localStorage.getItem("ms_username"));
          formData.append("userId",sessionStorage.getItem("ms_username"));
         console.log(sessionStorage.getItem("ms_username"));
          axios({
            url: "/saveReport",
            method: "post",
            data: formData,
          }).then((res) => {
            this.$message({ message: "上传成功！", type: "success" });
            this.$router.replace("/researchReport");
          });
        })
        .catch((_) => {
          this.$message("取消上传");
        });
    },
    //从 canvas 提取图片 image
    convertCanvasToImage(canvas) {
      //新Image对象，可以理解为DOM
      var image = new Image();
      // canvas.toDataURL 返回的是一串Base64编码的URL
      // 指定格式 PNG
      image.setAttribute("width", "1000px");
      image.src = canvas.toDataURL("image/png");
      return image;
    },
  },
};
</script>

<style scoped>
.all {
  width: 75%;
  height: auto;
  margin: 0 auto;
}
.content {
  width: 100%;
  height: 100%;
}
.content h1 {
  width: 50%;
  margin: 0 auto;
  text-align: center;
}
.content h2 {
  width: 50%;
  margin: 10px 0 10px 0;
}
.content p {
  font-size: 20px;
  line-height: 30px;
  margin: 10px 0 10px 0;
}
.content p u {
  text-decoration: underline;
  margin: 0 5px 0 5px;
  cursor: pointer;
}
.content input {
  width: 98%;
  border: none;
  outline: none;
  border-bottom: 1px solid #000;
  height: 30px;
  line-height: 30px;
  font-size: 20px;
  padding-left: 30px;
  margin: 0 0 10px 0;
}
.content textarea {
  width: 98%;
  height: 80px;
  border: none;
  outline: none;
  border-bottom: 1px solid #000;
  line-height: 30px;
  font-size: 20px;
  padding-left: 30px;
  margin: 0 0 10px 0;
}
input::-webkit-input-placeholder,textarea::-webkit-input-placeholder { /* WebKit browsers*/
  color:red;
}
</style>