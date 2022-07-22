<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <router-link :to="{path:'/researchReport'}">
        <el-breadcrumb-item>研究报告</el-breadcrumb-item>
      </router-link>
    </el-breadcrumb>
    <el-button type="success" style="float:right;margin:20px 0 0 0" @click="saveReport">报告上传</el-button>
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        <h1 style="width: 80%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;font-size:25px;background:none;text-align: center;border:none;" :value="thisYear+title" >
        </h1>
        <div>
          <h2>一、播栽情况</h2>
          <h3>1．播栽期介绍:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              全县（市、区）早稻育秧（含工厂化育秧）最早播种期在<u>{{reportData.datas_1[0]}}</u>左右，多数播种期集中在
              <input v-model="inputValues[0]" placeholder="手动输入播栽期变化的评价" type="text" class="input" style="width:400px;">。
              <input v-model="inputValues[1]" placeholder="手动输入介绍移栽天气及进度" type="text" class="input" style="width:400px;">。
              预计<u>{{reportData.datas_1[1]}}</u>完成移栽。直播早稻播种期时间集中在
              <input v-model="inputValues[2]" placeholder="手动输入介绍" type="text" class="input" style="width:400px;">。
          </p>
          <h3>2．栽培方式介绍:</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            全县（市、区）早稻育秧栽插<u>{{reportData.datas_2[0]}}</u>万亩左右，占<u>{{reportData.datas_2[1]}}</u>%,较去年<u>{{reportData.datas_2[2]}}</u>万亩；
            其中，移栽 <u>{{reportData.datas_2[3]}}</u>        万亩，抛栽  <u>{{reportData.datas_2[4]}}</u>     万亩，机插秧 <u>{{reportData.datas_2[5]}}</u>      万亩。
            撒直播  <u>{{reportData.datas_2[6]}}</u>    万亩，占<u>{{reportData.datas_2[7]}}</u>    %，较去年<u>{{reportData.datas_2[8]}}</u> 万亩。
            <input v-model="inputValues[3]" placeholder="手动输入对栽培方式面积变化的评价" type="text" class="input" style="width:400px;">。
            抛栽、机插秧和撒直播三种种植方式共  <u>{{reportData.datas_2[9]}}</u>     万亩，占总面积 <u>{{reportData.datas_2[10]}}</u>   %，
            比去年<u>{{reportData.datas_2[11]}}</u>  万亩。
          </p>
          <h3>3.3．种植面积介绍</h3>
          <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              全县（市、区）今年早稻种植面积<u>{{reportData.datas_3[0]}}</u>万亩，比去年<u>{{reportData.datas_3[1]}}</u>万亩，
              <input v-model="inputValues[4]" placeholder="手动输入对种植面积变化的评价" type="text" class="input" style="width:300px;">。
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
      title:'年度双季早稻播栽期苗情',
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
        params:{templateName:'早稻播栽期苗情',account:''},
        method:'get'
      }).then(res=>{
        // console.log(res.data);
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
        this.$refs.title.setAttribute('value',this.thisYear+'年度双季早稻播栽期苗情');
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
        // formData.append("userId",localStorage.getItem("ms_username"));
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