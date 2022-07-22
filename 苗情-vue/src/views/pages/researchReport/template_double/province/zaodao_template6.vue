<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <!-- <el-breadcrumb-item><a href="/researchReport">研究报告</a></el-breadcrumb-item> -->
      <el-breadcrumb-item :to="{ name: '/tableTwoMain',params:{activeNum:3} }">研究报告</el-breadcrumb-item>
      <el-breadcrumb-item>新增报告</el-breadcrumb-item>
    </el-breadcrumb> 
    <!-- <router-link :to="{ name: '/tableTwoMain',params:{activeNum:3}}">
                    <el-button type="info"  style="float:right;margin:20px 0 0 0">返回</el-button>
    </router-link> -->
    <el-button type="success" style="float:right;margin:20px 0 0 0" @click="saveReport">报告上传</el-button>
    
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        <h1 style="width: 80%;margin: 0 auto;text-align: center;">
          <input ref="title" class="title" type="text" style="width:70%;background:none;font-size:25px;text-align: center;border:none;" :value="thisYear+title">
        </h1>
        <div>
          <h2>一、产量结果</h2>
          <!-- <h3>1．一季稻收割进度</h3> -->
          <!-- <p>
            &nbsp;&nbsp;截至9月24日，全省已收割 <u>{{reportData.data_1[0]}}</u> %，较去年同期<u>{{reportData.data_1[1]}}</u>个百分点，
            <input v-model="inputValues[0]" placeholder="手动输入当前天气对收获进度的评价" type="text" ref="input1" style="width:400px;background:none;border: none;border-bottom: 1px solid #000;font-size: 16px;outline: none;">。
            主体收割期将在<input v-model="inputValues[1]" type="text" ref="input2" style="width:200px;background:none;border: none;border-bottom: 1px solid #000;font-size: 16px;outline: none;">,
            预计收获完成期在<input v-model="inputValues[2]" type="text" ref="input3" style="width:200px;background:none;border: none;border-bottom: 1px solid #000;font-size: 16px;outline: none;">。
          </p> -->
          <!-- <h3>2．平均单产水平</h3> -->
          <p>
            &nbsp;&nbsp;据面上产量结构调查，平均亩有效穗 <u>{{reportData.data_2[0]}}</u>万，比去年<u>{{reportData.data_3[0]}}</u>万；
            每穗总粒数<u>{{reportData.data_2[1]}}</u>粒，比去年<u>{{reportData.data_3[1]}}</u>粒；
            结实率<u>{{reportData.data_2[2]}}</u> %，较去年<u>{{reportData.data_3[2]}}</u> 个百分点；
            千粒重<u>{{reportData.data_2[3]}}</u> 克，较去年<u>{{reportData.data_3[3]}}</u>克。
            产量结构三要素<input v-model="inputValues[0]" type="text" ref="input1" placeholder="输入增减态势" style="width:100px;background:none;border: none;border-bottom: 1px solid #000;font-size: 16px;outline: none;"> 。
            经加权平均八五折，实收亩产平均<u>{{reportData.data_2[4]}}</u>公斤/亩，较去年 <u>{{reportData.data_3[4]}}</u>公斤。
            

            <!-- 增 <input v-model="inputValues[4]" type="text" ref="input5" style="width:50px;background:none;border: none;border-bottom: 1px solid #000;font-size: 16px;outline: none;">减”态势， -->
          </p>
        </div>
        <div>
          <h2>二、结果分析</h2>
          <div>
            <textarea cols="10" rows="10" class="textarea" placeholder="输入介绍本季测产分气候、生产技术和科技手段三部分" v-model="textareas[0]"></textarea>
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
      title:'年度双季稻早稻测产结果',
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
        url:'/templateTwo',
        params:{templateTwoName:'全省双季稻早稻测产结果'},
        method:'get'
      }).then(res=>{
        console.log(res.data);
        this.reportData = res.data;
      })
    },

    saveReport(){
      var formData = new FormData();
      var fileName = this.$refs.title.value;
      var tempHtml = this.$refs.content;
      this.$confirm('确定上传？').then(_=>{
        this.$refs.title.setAttribute('value',this.thisYear+'年度双季稻早稻测产结果');
        this.$refs.title.setAttribute('disabled',true);
        this.$refs.input1.setAttribute('value',this.inputValues[0]);
        this.$refs.input1.setAttribute('disabled',true);
        // this.$refs.input2.setAttribute('value',this.inputValues[0]);
        // this.$refs.input2.setAttribute('disabled',true);
        // this.$refs.input3.setAttribute('value',this.inputValues[0]);
        // this.$refs.input3.setAttribute('disabled',true);
        // this.$refs.input4.setAttribute('value',this.inputValues[0]);
        // this.$refs.input4.setAttribute('disabled',true);
        // this.$refs.input5.setAttribute('value',this.inputValues[0]);
        // this.$refs.input5.setAttribute('disabled',true);
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
        var pStyle = `
          font-size: 20px;
          line-height: 30px;
          margin: 10px 0 10px 0;
        `
        var p = $('p');
        for(let i=0;i<p.length;i++){
          p[i].setAttribute('style',pStyle);
        }
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