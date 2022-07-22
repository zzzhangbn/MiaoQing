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
          <input ref="title" class="title" type="text" style="width:70%;font-size:25px;background:none;text-align: center;border:none;font-weight:bold" :value="thisYear+title" >
        </h1>
        <h1 style="width: 50%;margin: 0 auto;text-align: center;font-size:23px;">一季稻生育进程及高温热害的影响</h1>
        <div>
          <h2>一、当前苗情</h2>
          <h3>1．生育进程</h3>
          <p style="font-size: 20px;line-height: 30px;">
            &nbsp;&nbsp;据农情调度，全省一季稻面积<input v-model="inputValues[0]" type="text" ref="input1" style="width:40px;border: none;border-bottom: 1px solid #000;font-size: 20px;outline: none;">万亩，
            与去年相比<input type="text" v-model="inputValues[1]" ref="input2" style="width:100px;border: none;border-bottom: 1px solid #000;font-size: 20px;outline: none;">万亩。
            8月20日前全省处在抽穗扬花至灌浆期的一季稻 <u>{{reportData.data_1.num}}</u> 万亩左右，占总面积 <u>{{reportData.data_1.percent}}</u> %，比去年 <u>{{reportData.data_1.subPercent}}</u> 个百分点；
            预计8月下旬至9月上中旬抽穗扬花的<u>{{reportData.data_2.num}}</u> 万亩左右，占总面积 <u>{{reportData.data_2.percent}}</u> %，比去年 <u>{{reportData.data_2.subPercent}}</u> 个百分点；
          </p>
          <h3>2．不同时段抽穗的产量水平</h3>
          <p style="font-size: 20px;line-height: 30px;">
            &nbsp;&nbsp;其中，7月31日前抽穗扬花的，约 <u>{{reportData.data_3.area}}</u> 万亩左右，占 <u>{{reportData.data_3.percent}}</u>%，
            <input type="text" style="width:98%;" ref="input3" placeholder="请输入分布地区、前茬类型及当前天气对抽穗扬花的评价" v-model="inputValues[2]">
            &nbsp;&nbsp;据调查，平均亩有效穗<u>{{reportData.data_3.yxh}}</u> 万，每穗总粒数 <u>{{reportData.data_3.qls}}</u>粒，结实率 <u>{{reportData.data_3.jsl}}</u>%，
            千粒重按 <u>{{reportData.data_3.qlz}}</u> 克计，理论亩产 <u>{{reportData.data_3.chanliang_1}}</u>公斤，比去年同期（<u>{{reportData.data_3.chanliang_2}}</u>公斤）<u>{{reportData.data_3.subPercent}}</u> %
          </p>
          <p style="font-size: 20px;line-height: 30px;">
            &nbsp;&nbsp;8月上旬抽穗扬花的中籼稻，全省约 <u>{{reportData.data_4.area}}</u> 万亩，占 <u>{{reportData.data_4.percent}} </u>%，据初步调查，结实率 <u>{{reportData.data_4.jsl}}</u> %。
            <input type="text" placeholder="输入分布地区、前茬类型及当前天气对抽穗扬花的评价" ref="input4" v-model="inputValues[3]">
          </p>
          <p style="font-size: 20px;line-height: 30px;">
            &nbsp;&nbsp;8月下旬抽穗扬花的中籼稻，全省约 <u>{{reportData.data_5.area}}</u> 万亩，占 <u>{{reportData.data_5.percent}} </u>%。
            <input type="text" placeholder="手动输入分布地区、前茬类型及当前天气对抽穗扬花的评价" ref="input5" v-model="inputValues[4]">
          </p>
          <p style="font-size: 20px;line-height: 30px;">
            &nbsp;&nbsp;9月上中旬抽穗扬花的中籼稻，全省约 <u>{{reportData.data_6.area}}</u> 万亩，占 <u>{{reportData.data_6.percent}} </u>%。
            <input type="text" placeholder="手动输入分布地区、前茬类型及当前天气对抽穗扬花的评价" ref="input6" v-model="inputValues[5]">
          </p>
        </div>
        <div>
          <h2>二、当前预估产分析</h2>
          <div>
            <textarea cols="30" rows="10" class="textarea" placeholder="输入本生产季天气状况对水稻生产的影响，及当前生产中遇到的问题" v-model="textareas[0]"></textarea>
          </div>
        </div>
        <div>
          <h2>三、田管意见</h2>
          <div>
            <textarea cols="30" rows="10" class="textarea"  placeholder="请输入对当前问题的对应及下一阶段田管措施" v-model="textareas[1]"></textarea>
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
      title:'年度一季稻抽穗期苗情（二）',
      inputValues:['','','','','','',''],
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
        method:'get',
        params:{templateName:'一季稻抽穗期苗情-2'}
      }).then(res=>{
        //console.log(res.data);
        this.reportData = res.data;
      })
    },
    saveReport(){
      var formData = new FormData();
      var fileName = this.$refs.title.value;
      var tempHtml = this.$refs.content;
      this.$confirm('确定上传？').then(_=>{
       this.$refs.title.setAttribute('value',this.thisYear+'年度一季稻抽穗期苗情（二）');
        this.$refs.title.setAttribute('disabled',true);
        this.$refs.input1.setAttribute('value',this.inputValues[0]);
        this.$refs.input1.setAttribute('disabled',true);
        this.$refs.input2.setAttribute('value',this.inputValues[1]);
        this.$refs.input2.setAttribute('disabled',true);
        this.$refs.input3.setAttribute('value',this.inputValues[2]);
        this.$refs.input3.setAttribute('disabled',true);
        this.$refs.input4.setAttribute('value',this.inputValues[3]);
        this.$refs.input4.setAttribute('disabled',true);
        this.$refs.input5.setAttribute('value',this.inputValues[4]);
        this.$refs.input5.setAttribute('disabled',true);
        this.$refs.input6.setAttribute('value',this.inputValues[5]);
        this.$refs.input6.setAttribute('disabled',true);

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
  padding-left: 10px;
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