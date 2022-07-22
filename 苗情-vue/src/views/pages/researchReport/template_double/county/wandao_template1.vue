<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <router-link :to="{name:'/tableTwoMain',params: {activeNum: 3}}">
        <el-breadcrumb-item>研究报告</el-breadcrumb-item>
      </router-link>
    </el-breadcrumb>
    <!-- <el-button type="success" style="float:right;margin:20px 0 0 0" @click="saveReport">报告上传</el-button>
    <router-link :to="{name:'/tableTwoMain',params: {activeNum: 3}}">
      <el-button style="float:right;margin:20px 0 0 0" type="primary">返回</el-button>
    </router-link> -->
     <router-link :to="{ name: '/tableTwoMain',params:{activeNum:3}}">
                    <el-button type="success"  style="float:right;margin:20px 0 0 0">返回</el-button>
    </router-link>
    <el-button type="success" style="float:right;margin:20px 10px 0 0" @click="saveReport">报告上传</el-button>
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        <h1 style="width: 80%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;font-size:25px;background:none;text-align: center;border:none;" :value="thisYear+title" >
        </h1>
        <div>
          <h2>一、当前苗情</h2>
          <h3>1．移栽期评价:</h3>
          <input v-model="inputValues[0]" placeholder="手动输入移栽期评价" type="text" class="input" style="width:1000px;">。
          <h3>2．栽插苗情介绍:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              晚粳稻平均亩栽<u>{{reportData.datas_1[0]}}</u>万穴，比去年<u>{{reportData.sub_1[0]}}</u>万穴；
              亩基本苗约<u>{{reportData.datas_1[1]}}</u>万，比去年<u>{{reportData.sub_1[1]}}</u> 万。
              其中，育秧移栽的田块亩栽穴数约<u>{{reportData.datas_1[4]}}</u>万穴，亩栽基本苗<u>{{reportData.datas_1[5]}}</u> 万；
              盘育抛栽的亩抛穴数约<u>{{reportData.datas_1[6]}}</u>万穴，亩基本苗<u>{{reportData.datas_1[7]}}</u>万。
              晚籼杂交稻平均亩栽<u>{{reportData.datas_1[2]}}</u>万穴，比去年<u>{{reportData.sub_1[2]}}</u>万穴；
              亩基本苗<u>{{reportData.datas_1[3]}}</u>万，比去年<u>{{reportData.sub_1[3]}}</u> 万。
              其中，育秧移栽的亩栽穴数约 <u>{{reportData.datas_1[8]}}</u>    万穴，亩基本苗 <u>{{reportData.datas_1[9]}}</u>    万；
              盘育抛栽的亩抛穴数约 <u>{{reportData.datas_1[10]}}</u>    万穴，亩基本苗  <u>{{reportData.datas_1[11]}}</u>   万。
          </p>
          <h3>3．群体结构介绍:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            晚粳稻亩平均茎蘖数<u>{{reportData.datas_2[0]}}</u>     万，较去年<u>{{reportData.sub_2[0]}}</u> 万，
            主茎叶龄平均 <u>{{reportData.datas_2[1]}}</u>    叶，较去年<u>{{reportData.sub_2[1]}}</u>    叶。其中，
            人工移栽的   <u>{{reportData.datas_2[4]}}</u>  万，叶龄<u>{{reportData.datas_2[5]}}</u>     叶；
            盘育抛栽的  <u>{{reportData.datas_2[6]}}</u>   万，叶龄   <u>{{reportData.datas_2[7]}}</u>  叶；
            直播田块茎蘖数   <u>{{reportData.datas_3[0]}}</u>  万，较去年<u>{{reportData.sub_3[0]}}</u>  万，叶龄 <u>{{reportData.datas_3[1]}}</u>    叶。
            晚籼杂交稻亩平均茎蘖数 <u>{{reportData.datas_2[2]}}</u>    万，比去年<u>{{reportData.sub_2[2]}}</u>  万，
            主茎叶龄平均  <u>{{reportData.datas_2[3]}}</u>   叶，比去年<u>{{reportData.sub_2[3]}}</u>  叶。
            其中，人工移栽的  <u>{{reportData.datas_2[8]}}</u>   万，叶龄  <u>{{reportData.datas_2[9]}}</u>   叶；
            盘育抛栽的  <u>{{reportData.datas_2[10]}}</u>   万，叶龄 <u>{{reportData.datas_2[11]}}</u>    叶；
            直播田块茎蘖数   <u>{{reportData.datas_3[2]}}</u>  万，叶龄  <u>{{reportData.datas_3[3]}}</u>   叶，较去年<u>{{reportData.sub_3[1]}}</u>  叶。
          </p>
          <h3>4．种植面积介绍</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            全县（市、区）播栽面积约 <u>{{reportData.datas_4[0]}}</u>万亩，<u>{{reportData.sub_4[0]}}</u> 万亩，
            晚粳稻约占    <u>{{reportData.datas_4[1]}}</u> %，晚籼稻约占<u>{{reportData.datas_4[2]}}</u> %。
            据6个监测县面上调查，当前一类苗<u>{{reportData.datas_4[3]}}</u> 万亩，占<u>{{reportData.datas_4[4]}}</u> %；
            二类苗 <u>{{reportData.datas_4[5]}}</u>万亩，占 <u>{{reportData.datas_4[6]}}</u> %；
            三类苗   <u>{{reportData.datas_4[7]}}</u>  万亩，占  <u>{{reportData.datas_4[8]}}</u>   %。
            一、二类苗面积万亩 <u>{{reportData.datas_4[9]}}</u>    万亩，占比 <u>{{reportData.datas_4[10]}}</u>    %，
            比去年同期<u>{{reportData.datas_3[1]}}</u> 个百分点，总体苗情
              <input v-model="inputValues[1]" placeholder="手动输入苗情评价" type="text" class="input" style="width:1000px;">。
          </p>
        </div>
        <div>
          <h2>二、苗情分析</h2>
          <div>
            <textarea cols="30" rows="10" class="textarea" placeholder="请手动输入当前天气对苗情的影响" v-model="textareas[0]"></textarea>
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
      title:'年度双季晚稻分蘖期苗情',
      inputValues:['','','','','',''],
      textareas:['','']
    }
  },
  mounted(){
      this.getData();
  },
  methods:{
    getData(){
        let account  = sessionStorage.getItem("ms_username");
         var date = new Date();
      this.thisYear = date.getFullYear();
      axios({
        url:'/template_double',
        params:{templateName:'双季晚稻分蘖期苗情',account:account},
        method:'get'
      }).then(res=>{
        console.log(res.data);
        this.reportData = res.data;
        // var myDate  = new Date();
        // this.month = myDate.getMonth();
        // this.day = myDate.getDay();
        // this.initTable('table_01_01_1',['水稻','小麦','油菜','空闲田','蔬菜等经作','绿肥'],res.data.stubbleList_1_1,res.data.stubblePercents_1_1);
        // this.initTable('table_01_01_2',['人工移栽','机械插秧','盘育抛栽','无盘旱育抛栽','撒直播(旱)','撒直播(水)',' 机条播(旱)',' 机条播(水)'],
        // res.data.cateList_1_1,res.data.catePercent_1_1)
      })
    },
    saveReport(){
      var formData = new FormData();
      var fileName = this.$refs.title.value;
      var tempHtml = this.$refs.content;
      this.$confirm('确定上传？').then(_=>{
        this.$refs.title.setAttribute('value',this.thisYear+'年度双季晚稻分蘖期苗情');
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
        // var canvas = html.getElementsByTagName('canvas');
        // for(let i=0;i<canvas.length;i++){
        //   var img=this.convertCanvasToImage(canvas[i]);
        //   this.$refs['table_01_01_'+(i+1)+'_pic'].append(img);
        //   this.$refs['table_01_01_'+(i+1)].style.display =  'none';
        // }
        formData.append("crop",localStorage.getItem("crop"));
        formData.append("fileName",fileName);
        formData.append("html",html.innerHTML);
        formData.append("userId",localStorage.getItem("ms_username"));
        axios({
          url:'/saveReport',
          method:'post',
          data:formData
        }).then(res=>{
          this.$message({message:'上传成功！',type:'success'});
          this.$router.replace('/tableTwoMain');
        })
      }).catch(_=>{
        this.$message('取消上传');
      })
    },


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