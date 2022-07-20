<template>
  <div class="all">
    <el-breadcrumb separator="/" style="margin:20px 0 0 0;font-size:20px;width:300px;display:inline-block;">
      <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
      <!-- <el-breadcrumb-item><a href="/researchReport">研究报告</a></el-breadcrumb-item> -->
      <el-breadcrumb-item :to="{ path: '/tableTwoMain' }">研究报告</el-breadcrumb-item>
      <el-breadcrumb-item>报告预览</el-breadcrumb-item>
    </el-breadcrumb>
    <router-link :to="{ name: '/tablemain',params:{activeNum:3}}">
                    <el-button type="success"  style="float:right;margin:20px 0 0 0">返回</el-button>
    </router-link>
    <el-button type="success" style="float:right;margin:20px 10px 0 0" @click="exportWord">报告下载</el-button>
    <el-card style="margin:20px 0 0 0;cursor:pointer;">
      <div class="content" ref="content">
        
      </div>
    </el-card>
  </div>
</template>

<script>
import  axios  from 'axios';
import { saveAs } from 'file-saver';
require('../../../public/js/jquery.wordexport')
export default {
  data(){
    return{
    }
  },
  mounted(){
      this.init();
  },
  methods:{
    init(){
      var html = sessionStorage.getItem("html");
      sessionStorage.removeItem('activeIndex');
    //    sessionStorage.setItem('activeIndex','/researchReport');
      // // 新增下边1行
      sessionStorage.setItem('activeIndex','/tableTwoMain');
      console.log(html);
      this.$refs.content.innerHTML = html;
    },

    //导出为word
    exportWord(){
      var html = $('.content')[0];
      var inputs = html.getElementsByTagName('input');
      var textareas = html.getElementsByTagName('textarea');
      var title = html.getElementsByClassName("title")[0].value;
      var pStyle = `
        outline: none;
        border-bottom: 1px solid #000;
        height: 30px;
        line-height: 30px;
        font-size: 20px;
        padding-left: 30px;
        margin: 0 0 10px 0 ;
        `;
      for(let k=0;k<3;k++){
        for(let i=0;i<inputs.length;i++){
          var value = inputs[i].defaultValue;
          if(inputs[i].className=='title'){
            var node = document.createElement('h1');
            node.innerHTML  = value;
            inputs[i].parentNode.replaceChild(node,inputs[i]);
          }else {
            var node = document.createElement('p');
            node.innerHTML  = value;
            node.setAttribute("style",pStyle);
            inputs[i].parentNode.replaceChild(node,inputs[i]);
          }
        }
        for(let j=0;j<textareas.length;j++){
            var value = textareas[j].defaultValue;
            var node = document.createElement('p');
            node.innerHTML  = value;
            node.setAttribute("style",pStyle);
            textareas[j].parentNode.replaceChild(node,textareas[j]);
        }
      }
      this.$confirm('确定下载？',{
      }).then(_=>{
        $('.content').wordExport(title);
        location.reload();
      })

    },
    // 把image 转换为 canvas对象  
    convertImageToCanvas(image) {  
        // 创建canvas DOM元素，并设置其宽高和图片一样   
        var canvas = document.createElement("canvas");  
        canvas.width = image.width;  
        canvas.height = image.height;  
        // 坐标(0,0) 表示从此处开始绘制，相当于偏移。  
        canvas.getContext("2d").drawImage(image, 0, 0);    
        return canvas;  
    }



  }
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
</style>