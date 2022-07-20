<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <!-- <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item> -->
      <!-- <el-breadcrumb-item><a href="/researchReport">研究报告</a></el-breadcrumb-item> -->
      <el-breadcrumb-item :to="{ name: '/tablemain' ,params: {activeNum: 3}}">研究报告</el-breadcrumb-item>
      <el-breadcrumb-item>新增报告</el-breadcrumb-item>
    </el-breadcrumb>
     <router-link :to="{ name: '/tablemain',params:{activeNum:3}}">
                    <el-button type="success"  style="float:right;margin:20px 0 0 0">返回</el-button>
    </router-link>
    <el-button type="success" style="float:right;margin:20px 10px 0 0" @click="saveReport">报告上传</el-button>
    
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        <h1 style="width: 80%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;background:none;font-size:25px;text-align: center;border:none;" :value="thisYear+title">
        </h1>
        <div>
          <h2>一、不同阶段的预产结果</h2>
          <p>
            &nbsp;&nbsp;当前处在灌浆至成熟阶段的一季稻面积约<u>{{reportData.data_1.area}}</u>万亩，
            占总面积<u>{{reportData.data_1.percent}}</u>%左右；
            9月上旬以后抽穗扬花的约<u>{{reportData.data_1.area_2}}</u>万亩，
            占总面积<u>{{reportData.data_1.percent_2}}</u>%，
            较去年<u>{{reportData.data_1.subPercent}}</u>个百分点。
          </p>
          <p>
            &nbsp;&nbsp;据调查，7月31日前抽穗扬花的早熟中籼稻，全县（市、区）约<u>{{reportData.data_2.area}}</u>万亩，
            平均亩有效穗 <u>{{reportData.data_2.yxh}}</u>万，每穗总粒数<u>{{reportData.data_2.zls}}</u> 粒，
            结实率 <u>{{reportData.data_2.jsl}}</u>%，千粒重按<u>{{reportData.data_2.qlz}}</u> 克计，
            九折预估亩产<u>{{reportData.data_2.chanliang_1}}</u>公斤，
            较去年同期（ <u>{{reportData.data_2.chanliang_2}}</u>公斤）<u>{{reportData.data_2.subPercent}}</u>  %。
            <input type="text" class="input" placeholder="输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[0]">
          </p>
          <p>
            &nbsp;&nbsp;8月上旬抽穗扬花的中籼稻，全县（市、区）约<u>{{reportData.data_3.area}}</u>万亩，
            平均亩有效穗 <u>{{reportData.data_3.yxh}}</u>万，每穗总粒数<u>{{reportData.data_3.zls}}</u> 粒，
            结实率 <u>{{reportData.data_3.jsl}}</u>%，千粒重按<u>{{reportData.data_3.qlz}}</u> 克计，
            九折预估亩产<u>{{reportData.data_3.chanliang_1}}</u>公斤，
            较去年同期（ <u>{{reportData.data_3.chanliang_2}}</u>公斤）<u>{{reportData.data_3.subPercent}}</u>  %。
            <input type="text" class="input" placeholder="请输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[1]">
          </p>
          <p>
            &nbsp;&nbsp;8月中旬抽穗扬花的中籼稻，全县（市、区）约<u>{{reportData.data_4.area}}</u>万亩，
            平均亩有效穗 <u>{{reportData.data_4.yxh}}</u>万，每穗总粒数<u>{{reportData.data_4.zls}}</u> 粒，
            结实率 <u>{{reportData.data_4.jsl}}</u>%，千粒重按<u>{{reportData.data_4.qlz}}</u> 克计，
            九折预估亩产<u>{{reportData.data_4.chanliang_1}}</u>公斤，
            较去年同期（ <u>{{reportData.data_4.chanliang_2}}</u>公斤）<u>{{reportData.data_4.subPercent}}</u>  %。
            <input type="text" class="input" placeholder="请输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[2]">
          </p>
          <p>
            &nbsp;&nbsp;8月下旬抽穗扬花的中籼稻，全县（市、区）约<u>{{reportData.data_5.area}}</u>万亩，
            平均亩有效穗 <u>{{reportData.data_5.yxh}}</u>万，每穗总粒数<u>{{reportData.data_5.zls}}</u> 粒，
            结实率 <u>{{reportData.data_5.jsl}}</u>%，千粒重按<u>{{reportData.data_5.qlz}}</u> 克计，
            九折预估亩产<u>{{reportData.data_5.chanliang_1}}</u>公斤，
            较去年同期（ <u>{{reportData.data_5.chanliang_2}}</u>公斤）<u>{{reportData.data_5.subPercent}}</u>  %。
            <input type="text" class="input" placeholder="请输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[3]">
          </p>
          <p>
            9月上旬以后抽穗扬花的一季稻，全县（市、区）约<u>{{reportData.data_1.area2}}</u> 万亩，具体结实情况有待进一步监测。   
            <input type="text" class="input" placeholder="请输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[4]">
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
      title:'年度一季稻抽穗－灌浆期苗情',
      inputValues:['','','','','','','',''],
      textareas:['','']
    }
  },
  mounted(){
      this.getData();
  },
  methods:{
    getData(){
      let area = localStorage.getItem('area');
       var date = new Date();
      this.thisYear = date.getFullYear();
      axios({
        url:'/template1',
        params:{templateName:'一季稻抽穗－灌浆期苗情',country:area},
        method:'get'
      }).then(res=>{
        console.log(res.data);
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
        this.$refs.title.setAttribute('value',this.thisYear+'年度一季稻抽穗－灌浆期苗情');
        this.$refs.title.setAttribute('disabled',true);
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
        var inputs = $('.input');
        for(let i=0;i<inputs.length;i++){
          inputs[i].setAttribute('value',this.inputValues[i]);
          inputs[i].setAttribute('disabled',true);
          inputs[i].setAttribute('style',inputStyle);
        }
        var textareas = $('.textarea');
        for(let i=0;i<textareas.length;i++){
          textareas[i].innerHTML = this.textareas[i];
          textareas[i].setAttribute('disabled',true);
          textareas[i].setAttribute('style',inputStyle);
        }
        var html  = this.$refs.content;
        console.log(html);
        formData.append("crop",localStorage.getItem("crop"));
        formData.append("fileName",fileName);
        formData.append("html",html.innerHTML);
        formData.append("userId",localStorage.getItem("ms_username"));
        axios({
          url:'/saveReport1',
          method:'post',
          data:formData
        }).then(res=>{
          this.$message({message:'上传成功！',type:'success'});
          // this.$router.replace('/tablemain');
           this.$router.push({ name: '/tablemain',params:{activeNum:3}} );
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
  border-bottom: 1px solid #000;
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
  border-bottom: 1px solid #000;
  line-height: 30px;
  font-size: 20px;
  padding-left: 30px;
  margin: 0 0 10px 0 ;
}
input::-webkit-input-placeholder,textarea::-webkit-input-placeholder { /* WebKit browsers*/
  color:red;
}
</style>