<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/researchReport">研究报告</a></el-breadcrumb-item>
      <el-breadcrumb-item>新增报告</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button type="success" style="float:right;margin:20px 0 0 0" @click="saveReport">报告上传</el-button>
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        <h1 style="width: 80%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;background:none;font-size:25px;text-align: center;border:none;" :value="thisYear+title" >
        </h1>
        <div>
          <h2>一、当前苗情</h2>
          <h3>1．栽插穴数、基本苗介绍:</h3>
          <p>
            &nbsp;&nbsp;<span style="font-weight:bold">中籼稻：</span>
            育苗移栽（人工移栽+机插秧+盘育抛栽）的平均亩栽 <u>{{reportData.data_1.muxue}}</u> 万穴，比去年 <u>{{reportData.data_1.sub_muxue}}</u> 万穴，
            基本苗 <u>{{reportData.data_1.jibenmiao}}</u>  万，比去年<u>{{reportData.data_1.sub_jibenmiao}}</u>  万。
            其中，人工移栽的平均亩栽  <u>{{reportData.data_1.datalist[0]}}</u>  万穴，亩基本苗 <u>{{reportData.data_1.datalist[1]}}</u> 万；
            机插的平均亩栽 <u>{{reportData.data_1.datalist[2]}}</u> 万穴，亩基本苗 <u>{{reportData.data_1.datalist[3]}}</u>  万；
            盘育抛栽的平均亩栽 <u>{{reportData.data_1.datalist[4]}}</u> 万穴，亩基本苗 <u>{{reportData.data_1.datalist[5]}}</u>万。
            直播的平均亩基本苗 <u>{{reportData.data_1.jibenmiao_zhibo}}</u> 万，比去年<u>{{reportData.data_1.jibenmiao_zhibo_sub}}</u>万亩    。
          </p>
          <p>
            &nbsp;&nbsp;<span style="font-weight:bold">中粳稻：</span>
            育苗移栽的平均亩栽 <u>{{reportData.data_2.muxue}}</u> 万穴，比去年 <u>{{reportData.data_2.sub_muxue}}</u> 万穴，
            基本苗 <u>{{reportData.data_2.jibenmiao}}</u>  万，比去年<u>{{reportData.data_2.sub_jibenmiao}}</u>  万。
            其中，人工移栽的平均亩栽  <u>{{reportData.data_2.datalist[0]}}</u>  万穴，亩基本苗 <u>{{reportData.data_2.datalist[1]}}</u> 万；
            机插的平均亩栽 <u>{{reportData.data_2.datalist[2]}}</u> 万穴，亩基本苗 <u>{{reportData.data_2.datalist[3]}}</u>  万；
            直播的平均亩基本苗 <u>{{reportData.data_2.jibenmiao_zhibo}}</u> 万，比去年<u>{{reportData.data_2.jibenmiao_zhibo_sub}}</u>万亩    。
          </p>
          <h3>2．叶龄、群体茎蘖数介绍</h3>
          <p>
            &nbsp;&nbsp;<span style="font-weight:bold">中籼稻：</span>移栽（人工移栽+机插秧+盘育抛栽）的主茎叶龄<u>{{reportData.data_3.yeling}}</u>叶，比去年<u>{{reportData.data_3.sub_yeling}}</u> 叶；
            平均亩茎蘖数 <u>{{reportData.data_3.jinye}}</u> 万，比去年<u>{{reportData.data_3.sub_jinye}}</u>  万。
            其中，人工栽插的主茎叶龄<u>{{reportData.data_3.datalist[0]}}</u>叶，平均亩茎蘖数<u>{{reportData.data_3.datalist[1]}}</u> 万；
            机械栽插主茎叶龄<u>{{reportData.data_3.datalist[2]}}</u>叶，平均亩茎蘖数<u>{{reportData.data_3.datalist[3]}}</u> 万；
            抛栽的主茎叶龄<u>{{reportData.data_3.datalist[4]}}</u>叶，平均亩茎蘖数<u>{{reportData.data_3.datalist[5]}}</u> 万。
            直播的主茎叶龄<u>{{reportData.data_3.yeling_zhibo}}</u>叶，比去年<u>{{reportData.data_3.yeling_zhibo_sub}}</u> 叶；
            平均亩茎蘖数 <u>{{reportData.data_3.jinye_zhibo}}</u>万，比去年<u>{{reportData.data_3.jinye_zhibo_sub}}</u>万。
          </p>
          <p>
            &nbsp;&nbsp;<span style="font-weight:bold">中粳稻：</span>移栽（人工移栽+机插秧+盘育抛栽）的主茎叶龄<u>{{reportData.data_5.yeling}}</u>叶，比去年<u>{{reportData.data_4.sub_yeling}}</u> 叶；
            平均亩茎蘖数 <u>{{reportData.data_4.jinye}}</u> 万，比去年<u>{{reportData.data_4.sub_jinye}}</u>  万。
            其中，人工栽插的主茎叶龄<u>{{reportData.data_4.datalist[0]}}</u>叶，平均亩茎蘖数<u>{{reportData.data_4.datalist[1]}}</u> 万；
            机械栽插主茎叶龄<u>{{reportData.data_4.datalist[2]}}</u>叶，平均亩茎蘖数<u>{{reportData.data_4.datalist[3]}}</u> 万；
            直播的主茎叶龄<u>{{reportData.data_4.yeling_zhibo}}</u>叶，比去年<u>{{reportData.data_4.yeling_zhibo_sub}}</u> 叶；
            平均亩茎蘖数 <u>{{reportData.data_4.jinye_zhibo}}</u>万，比去年<u>{{reportData.data_4.jinye_zhibo_sub}}</u>万。
          </p>
          <h3>3．面上苗情</h3>
          <p>
            据农情调度，全省一季稻面积<input v-model="inputValues[0]" type="text" ref="input1" style="width:50px;background:none;border: none;border-bottom: 1px solid #000;font-size: 18px;outline: none;">万亩，
            较去年<input v-model="inputValues[1]" type="text" ref="input2" style="width:60px;border: none;border-bottom: 1px solid #000;font-size: 18px;outline: none;">  万亩，
            其中30个苗情监测县 <u>{{reportData.data_5.area}}</u> 万亩，较去年<u>{{reportData.data_5.subNum}}</u>万亩。
            当前一类苗占 <u>{{reportData.data_5.yileimiao}}</u>%，二类苗占<u>{{reportData.data_5.erleimiao}}</u>%，三类苗占<u>{{reportData.data_5.sanleimiao}}</u>%。
            一、二类苗占<u>{{reportData.yiAnder}}</u> %，比去年<u>{{reportData.yiAndErSub}}</u>   个百分点，
            <span>苗情长势:</span> <input v-model="inputValues[2]" placeholder="请输入对当前苗情长势的评价" type="text" ref="input3" style="width:71%;height:30px;background:none;border: none;border-bottom: 1px solid #000;font-size: 18px;outline: none;">
          </p>

        </div>
        <div>
          <h2>二、主要问题</h2>
          <div>
            <textarea cols="30" rows="10" class="textarea" placeholder="请输入当前生产中遇到的问题" v-model="textareas[0]"></textarea>
          </div>
        </div>
        <div>
          <h2>三、田管意见</h2>
          <div>
            <textarea cols="30" rows="10" class="textarea" placeholder="请输入对当前问题的对应及下一阶段田管措施" v-model="textareas[1]"></textarea>
          </div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import  axios  from 'axios';
import { saveAs } from 'file-saver';
export default {
  data(){
    return{
      //后台获取的报告数据
      reportData:{},
      month:'',
      day:'',
      thisYear:'',
      //几个input框的值
      title:'年度一季稻分孽期苗情',
      inputValues:['','','','','',''],
      textareas:['','']
    }
  },
  mounted(){
      this.getData();
  },
  methods:{
    getData(){
       var date = new Date();
      this.thisYear = date.getFullYear();
      axios({
        url:'/template',
        params:{templateName:'一季稻分孽期苗情'},
        method:'get'
      }).then(res=>{
        //console.log(res.data);
        this.reportData = res.data;
      })
    },
    initTable(id,xAxis,data1,data2){
      this.$nextTick(()=>{
        var myChart = this.$echarts.init(this.$refs[id]);
        var option;
        var colors = ['#5470C6', '#EE6666'];
        option = {
            color: colors,

            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            // grid: {
            //     right: '10%'
            // },
            toolbox: {
                feature: {
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['面积','增减百分比']
            },
            xAxis: [
                {
                    type: 'category',
                    axisTick: {
                        alignWithLabel: true
                    },
                    data: xAxis
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '面积',
                    position: 'left',
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: colors[0]
                        }
                    },
                    axisLabel: {
                        formatter: '{value} 万亩'
                    }
                },
                {
                    type: 'value',
                    name: '增减百分比',
                    min: 0,
                    max: 25,
                    position: 'right',
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: colors[1]
                        }
                    },
                    axisLabel: {
                        formatter: '{value} %'
                    }
                }
            ],
            series: [
                {
                    name: '面积',
                    type: 'bar',
                    data: data1
                },
                {
                    name: '增减百分比',
                    type: 'line',
                    yAxisIndex: 1,
                    data: data2
                }
            ]
        };
        option && myChart.setOption(option);
      })
    },
    saveReport(){
      var formData = new FormData();
      var fileName = this.$refs.title.value;
      var tempHtml = this.$refs.content;
      this.$confirm('确定上传？').then(_=>{
        this.$refs.title.setAttribute('value',this.thisYear+'年度一季稻分孽期苗情');
        this.$refs.title.setAttribute('disabled',true);
        this.$refs.input1.setAttribute('disabled',true);
        this.$refs.input2.setAttribute('disabled',true);
        this.$refs.input3.setAttribute('disabled',true);
        this.$refs.input1.setAttribute('value',this.inputValues[0]);
        this.$refs.input2.setAttribute('value',this.inputValues[1]);
        this.$refs.input3.setAttribute('value',this.inputValues[2]);
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
        var textareas = $('.textarea');
        for(let i=0;i<textareas.length;i++){
          textareas[i].innerHTML = this.textareas[i];
          textareas[i].setAttribute('disabled',true);
          textareas[i].setAttribute('style',inputStyle);
        }
        var html  = this.$refs.content;
        //console.log(html);
        //将canvas转化为图片
        var canvas = html.getElementsByTagName('canvas');
        for(let i=0;i<canvas.length;i++){
          var img=this.convertCanvasToImage(canvas[i]);
          this.$refs['table_01_01_'+(i+1)+'_pic'].append(img);
          this.$refs['table_01_01_'+(i+1)].style.display =  'none';
        }
        formData.append("crop",localStorage.getItem("crop"));
        formData.append("fileName",fileName);
        formData.append("html",html.innerHTML);
        formData.append("userId",sessionStorage.getItem("ms_username"));
         console.log(sessionStorage.getItem("ms_username"));
        axios({
          url:'/saveReport',
          method:'post',
          data:formData
        }).then(res=>{
          this.$message({message:'上传成功！',type:'success'});
          this.$router.replace('/researchReport');
        })
      }).catch(_=>{
        this.$message('取消上传');
      })
    },
    //从 canvas 提取图片 image  
    convertCanvasToImage(canvas) {  
        //新Image对象，可以理解为DOM  
        var image = new Image();  
        // canvas.toDataURL 返回的是一串Base64编码的URL
        // 指定格式 PNG  
        image.setAttribute("width",'1000px');
        image.src = canvas.toDataURL("image/png");  
        return image;  
    } 


  }
};
</script>

<style scoped>
.all {
  width: 75%;
  height:auto;
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
.content p u{
  text-decoration: underline;
  margin: 0 5px 0 5px;
  cursor: pointer;
}
.content input{
  width: 98%;
  border: none;
  outline: none;
  border-bottom: 1px solid rgb(26, 1, 1);
  height: 30px;
  line-height: 30px;
  font-size: 20px;
  padding-left: 30px;
  margin: 0 0 10px 0 ;
}
.content textarea {
  width: 98%;
  height: 80px;
  border: none;
  outline: none;
  border-bottom: 1px solid rgb(19, 1, 1);
  line-height: 30px;
  font-size: 20px;
  padding-left: 30px;
  margin: 0 0 10px 0 ;
}
input::-webkit-input-placeholder,textarea::-webkit-input-placeholder { /* WebKit browsers*/
  color:red;
}
</style>