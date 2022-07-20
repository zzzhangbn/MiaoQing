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
        <h1 style="width: 50%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;font-size:25px;background:none;text-align: center;border:none;" :value="thisYear+title" >
        </h1>
        <div>
          <h2>一、栽插进度及苗情</h2>
          <h3>1．一季稻种植茬口类型与面积比较:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            30个监测县统计，一季稻种植面积<u>{{reportData.totalArea_1_1}}</u>万亩，
            占全省一季稻计划播种面积的<u>15%</u>，比去年 <u>{{reportData.totalAreaSub_1_1}}</u>万亩 。
            茬口方面，水稻茬口 <u>{{reportData.stubbleList_1_1[0]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[0]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[0]}}</u>万亩；
            小麦茬口 <u>{{reportData.stubbleList_1_1[1]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[1]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[1]}}</u>万亩；
            油菜茬口 <u>{{reportData.stubbleList_1_1[2]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[2]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[2]}}</u>万亩；
            空闲田茬口 <u>{{reportData.stubbleList_1_1[3]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[3]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[3]}}</u>万亩；
            蔬菜经作等茬口 <u>{{reportData.stubbleList_1_1[4]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[4]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[4]}}</u>万亩；
            绿肥茬口 <u>{{reportData.stubbleList_1_1[5]}}</u>万亩，占 <u>{{reportData.stubblePercents_1_1[5]}}</u>%，比去年<u>{{reportData.stubbleList2_1_1[5]}}</u>万亩。    
          </p>
          <div ref="table_01_01_1" style="width:1000px;height:300px;"></div>
          <div ref="table_01_01_1_pic"></div>
          <div>
            <input type="text" class="input" placeholder="请输入对茬口面积变化的评价" v-model="inputValues[0]">
          </div>
          <h3>2. 种植类型与面积比较:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            据30个监测县统计，一季稻人工移栽约<u>{{reportData.cateList_1_1[0]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[0]}}</u>%,比去年<u>{{reportData.cateList2_1_1[0]}}</u>个百分点；
            机械插秧约<u>{{reportData.cateList_1_1[1]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[1]}}</u>%,比去年<u>{{reportData.cateList2_1_1[1]}}</u>个百分点；
            盘育抛栽约<u>{{reportData.cateList_1_1[2]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[2]}}</u>%,比去年<u>{{reportData.cateList2_1_1[2]}}</u>个百分点；
            无盘旱育抛栽约<u>{{reportData.cateList_1_1[3]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[3]}}</u>%,比去年<u>{{reportData.cateList2_1_1[3]}}</u>个百分点；
            撒直播(旱)约<u>{{reportData.cateList_1_1[4]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[4]}}</u>%,比去年<u>{{reportData.cateList2_1_1[4]}}</u>个百分点；
            撒直播(水)约<u>{{reportData.cateList_1_1[5]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[5]}}</u>%,比去年<u>{{reportData.cateList2_1_1[5]}}</u>个百分点；
            机条播(旱)约<u>{{reportData.cateList_1_1[6]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[6]}}</u>%,比去年<u>{{reportData.cateList2_1_1[6]}}</u>个百分点；
            机条播(水)约<u>{{reportData.cateList_1_1[7]}}</u>万亩，占 <u>{{reportData.catePercent_1_1[7]}}</u>%,比去年<u>{{reportData.cateList2_1_1[7]}}</u>个百分点； 
          </p>
          <div ref="table_01_01_2"  style="width:1000px;height:300px;"></div>
          <div ref="table_01_01_2_pic"></div>
          <div>
            <input type="text" class="input" placeholder="请输入对种植类型面积变化的评价" v-model="inputValues[1]">
          </div>
          <h3>3．主导品种介绍:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            种植面积较大的有<u v-for="(item,index) in reportData.cate_1_2" :key="index">{{item.pin_zhong_name}}</u>，共计<u>{{reportData.data_1_2.area}}</u>万亩
            ,占<u>{{reportData.data_1_2.percent}}</u>%，比去年 <u>{{reportData.data_1_2.subPercent}}</u>万亩。
          </p>
          <div>
            <input type="text" class="input" placeholder="请输入对主要品种面积变化的评价" v-model="inputValues[2]">
          </div>
          <h3>4．播栽期、栽插进度:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            截至 <u>{{month}}</u>月<u>{{day}}</u>日，30个监测县一季稻播栽约<u>{{reportData.table_3_1_area}}</u>万亩，占总面积的 <u>{{reportData.areaPercent_3_1}}</u> %，
            较去年<u>{{reportData.table_3_1_areaSubPercent}}</u>个百分点。
          </p>
          <div>
            <input type="text" class="input" placeholder="请输入对当前栽插进度的评价" v-model="inputValues[3]">
          </div>
          <h3>5．苗情长势介绍:</h3>
          <!-- <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            目前移栽的中籼稻叶龄<u>{{reportData.list_4_1[0].field_17_3}}</u>叶，茎蘖数 <u>{{reportData.list_4_1[0].field_17_4}}</u>万；
            中粳稻叶龄 <u>{{reportData.list_4_1[2].field_17_3}}</u> 叶，茎蘖数<u>{{reportData.list_4_1[2].field_17_3}}</u>万。
            直播的中籼稻叶龄<u>{{reportData.list_4_1[1].field_17_3}}</u>叶，茎蘖数<u>{{reportData.list_4_1[1].field_17_4}}</u>万；
            中粳稻叶龄<u>{{reportData.list_4_1[3].field_17_3}}</u>叶，茎蘖数<u>{{reportData.list_4_1[3].field_17_3}}</u>万。
          </p> -->
          <div>
            <input type="text" class="input" placeholder="请输入当前所处生育期，及天气对苗情长势的影响" v-model="inputValues[4]">
          </div>
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
      title:'年度一季稻栽播期苗情',
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
        params:{templateName:'一季稻栽播期期苗情'},
        method:'get'
      }).then(res=>{
        //console.log(res.data);
        this.reportData = res.data;
        var myDate  = new Date();
        this.month = myDate.getMonth();
        this.day = myDate.getDay();
        this.initTable('table_01_01_1',['水稻','小麦','油菜','空闲田','蔬菜等经作','绿肥'],res.data.stubbleList_1_1,res.data.stubblePercents_1_1);
        this.initTable('table_01_01_2',['人工移栽','机械插秧','盘育抛栽','无盘旱育抛栽','撒直播(旱)','撒直播(水)',' 机条播(旱)',' 机条播(水)'],
        res.data.cateList_1_1,res.data.catePercent_1_1)
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
                    data: data2,
                    smooth: true
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
        this.$refs.title.setAttribute('value',this.thisYear+'年度一季稻栽播期苗情');
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