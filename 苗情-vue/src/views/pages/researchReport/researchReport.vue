<template>
  <div class="all">
    <el-card>
        <el-breadcrumb style="font-size:18px;margin:20px 0 20px 0px;">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>研究报告</el-breadcrumb-item>
        </el-breadcrumb>
      <!-- <el-button style="margin:20px 0 0 10px" @click="dialogAddFile=true">上传报告</el-button> -->
      <!-- 条件检索 -->
      <el-select v-model="year" placeholder="请选择报告年份" @change="changeYear">
        <el-option label="2022" value="2022"></el-option>
        <el-option label="2021" value="2021"></el-option>
        <el-option label="2020" value="2020"></el-option>
        <el-option label="2019" value="2019"></el-option>
        <el-option label="2018" value="2018"></el-option>
      </el-select>
      <el-select v-model="code_name" placeholder="地区" @change="changeYear">
        <el-option label="全省层面" value="全省层面"></el-option>
        <el-option label="肥东县" value="肥东县"></el-option>
        <el-option label="肥西县" value="肥西县"></el-option>
        <el-option label="巢湖市" value="巢湖市"></el-option>
        <el-option label="湾沚区" value="湾沚区"></el-option>
        <el-option label="无为市" value="无为市"></el-option>
        <el-option label="东至县" value="东至县"></el-option>
        <el-option label="天长市" value="天长市"></el-option>
        <el-option label="定远县" value="定远县"></el-option>
        <el-option label="全椒县" value="全椒县"></el-option>
        <el-option label="来安县" value="来安县"></el-option>
        <el-option label="明光市" value="明光市"></el-option>
        <el-option label="桐城市" value="桐城市"></el-option>
        <el-option label="太湖县" value="太湖县"></el-option>
        <el-option label="望江县" value="望江县"></el-option>
        <el-option label="泾县" value="泾县"></el-option>
        <el-option label="舒城县" value="舒城县"></el-option>
        <el-option label="裕安区" value="裕安区"></el-option>
        <el-option label="霍山县" value="霍山县"></el-option>
        <el-option label="当涂县" value="当涂县"></el-option>
        <el-option label="颍上县" value="颍上县"></el-option>
        <el-option label="宿松县" value="宿松县"></el-option>
        <el-option label="广德市" value="广德市"></el-option>
        <el-option label="郎溪县" value="郎溪县"></el-option>
        <el-option label="休宁县" value="休宁县"></el-option>
        <el-option label="义安区" value="义安区"></el-option>
        <el-option label="怀远县" value="怀远县"></el-option>
        <el-option label="五河县" value="五河县"></el-option>
        <el-option label="凤台县" value="凤台县"></el-option>
        <el-option label="望江县" value="望江县"></el-option>
        <el-option label="寿县" value="寿县"></el-option>
        <el-option label="霍邱县" value="霍邱县"></el-option>
        <el-option label="宣州区" value="宣州区"></el-option>
        <el-option label="庐江县" value="庐江县"></el-option>
        <el-option label="怀宁县" value="怀宁县"></el-option>
        <el-option label="枞阳县" value="枞阳县"></el-option>
        <el-option label="贵池区" value="贵池区"></el-option>
        <el-option label="南陵县" value="南陵县"></el-option>
      </el-select>
      <el-button style="float:right" type="success" @click="centerDialogVisible = true">新增报告</el-button>
      <!-- 文件上传弹出框 -->
      <!-- <el-dialog :visible.sync="dialogAddFile" width="500px" style="padding:0;" @close="resetAdd">
            报告名称：<el-input v-model="fileName" placeholder="请输入报告名称"></el-input>
            <div class="add-file-right" style="height:70px;margin-left:100px;margin-top:15px;">
                <div class="add-file-right-img" style="margin-left:70px;">上传文件：</div>
                <input type="file" ref="clearFile" @change="getFile($event)" multiple="multiplt" style="margin-left:50px;" accept=".docx,.doc,.pdf">
                <span class="add-file-right-more" style="margin-left:50px;">支持扩展名：.doc .docx .pdf </span>
            </div>
            <div class="add-file-list">
                <ul>
                    <li v-for="(item, index) in fileArr" :key="index"><a >{{item.name}}</a></li>
                </ul>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitAddFile" size="small">开始上传</el-button>
                <el-button @click="resetAdd" size="small">全部删除</el-button>
            </div>
      </el-dialog> -->
      <!-- 报告模板选择框 -->
      <el-dialog
        title="请选择一个报告模板"
        :visible.sync="centerDialogVisible"
        width="50%"
        center>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="单季稻报告模板" name="1">
            <div>
              <el-radio-group v-model="checkedTemplate">
                <el-radio label="template1" border>单季稻栽播期苗情</el-radio>
                <el-radio label="template2" border>单季稻分孽期苗情</el-radio>
              </el-radio-group>
            </div>
            <div style="margin:20px 0 0 0">
              <el-radio-group v-model="checkedTemplate">
                <el-radio label="template3-1" border>单季稻抽穗期苗情-1</el-radio>
                <el-radio label="template3-2" border>单季稻抽穗期苗情-2</el-radio>
                <el-radio label="template3-3" border>单季稻抽穗期苗情-3</el-radio>
              </el-radio-group>
            </div>
            <div style="margin:20px 0 0 0">
              <el-radio-group v-model="checkedTemplate">
                <el-radio label="template4" border>单季稻抽穗－灌浆期苗情</el-radio>
                <el-radio label="template5" border>单季稻预测产情况</el-radio>
              </el-radio-group>
            </div>
          </el-collapse-item>
          <el-collapse-item title="双季-早稻报告模板" name="2">
                    <div>
                        <el-radio-group v-model="checkedTemplate">
                            <el-radio label="/province/zaodao_template1" border>双季早稻栽播期苗情</el-radio>
                            <el-radio label="/province/zaodao_template2" border>早稻分蘖期苗情</el-radio>
                            <el-radio label="/province/zaodao_template3" border>早稻分蘖－孕穗初期苗情</el-radio>
                        </el-radio-group>
                    </div>
                    <div style="margin:20px 0 0 0">
                        <el-radio-group v-model="checkedTemplate">
                            <el-radio label="/province/zaodao_template4" border>早稻孕穗－抽穗期苗情</el-radio>
                            <el-radio label="/province/zaodao_template5" border>早稻预测产结果</el-radio>
                            <el-radio label="/province/zaodao_template6" border>早稻测产结果</el-radio> 
                        </el-radio-group>
                    </div>
          </el-collapse-item>
          <el-collapse-item title="双季-晚稻报告模板" name="3">
              <div style="margin:20px 0 0 0">
                  <el-radio-group v-model="checkedTemplate">
                          <el-radio label="/province/wandao_template1" border>双季晚稻分蘖期苗情</el-radio>
                          <el-radio label="/province/wandao_template2" border>双季晚稻分蘖－孕穗期苗情</el-radio>
                          <el-radio label="/province/wandao_template3" border>双季晚稻灌浆期预估产</el-radio>                        
                  </el-radio-group>
              </div>
               <div style="margin:20px 0 0 0">
                   <el-radio-group v-model="checkedTemplate">
                      <el-radio label="/province/wandao_template4" border>双季晚稻测产结果</el-radio>
                   </el-radio-group>
               </div>
          </el-collapse-item>
        </el-collapse>
        
        <span slot="footer" class="dialog-footer">
          <el-button @click="centerDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addReport">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 展示表格 -->
      <el-table
        :data="fileList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%;" height="500">
        <el-table-column
          label="序号"
          type="index"
          width="100"
          align="center">
        </el-table-column>
        <el-table-column
          label="报告名称"
          prop="name">
        </el-table-column>
        <el-table-column
          label="作物类型"
          prop="crop">
        </el-table-column>
        <el-table-column
          label="报告年份"
          prop="modification_time">
        </el-table-column>
        <el-table-column align="right">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索"/>
          </template>
          <template slot-scope="scope">
            <!-- <el-button
              size="mini"
              @click="preView(scope.row)">下载</el-button> -->
            <el-button
            size="mini"
            @click="viewReport(scope.row)">预览</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 文件在线预览 -->
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
      return {
        //文件上传弹出框‘
        dialogAddFile:false,
        //前台文件数组
        fileName:'',
        //后台传来的文件list
        fileList:[],
         search: '',
        //文件在线预览的url
        url:'',
        //条件检索
        year:'',
        // 模板选择弹出框
        centerDialogVisible:false,
        checkedTemplate: 'template1',
        activeName:'1',//折叠面板参数,
        code_name:''//县名
        

      }
    },
    mounted(){
      this.getFiles();
    },
    methods: {
        //前台获取到文件
        getFile(event){
           var file = event.target.files;
           for(var i = 0;i<file.length;i++){
            //    上传类型判断
               var imgName = file[i].name;
                var idx = imgName.lastIndexOf(".");  
                if (idx != -1){
                    var ext = imgName.substr(idx+1).toUpperCase();   
                    ext = ext.toLowerCase( ); 
                     if (ext!='pdf' && ext!='doc' && ext!='docx'){
                       
                    }else{
                          this.fileArr.push(file[i]);
                    }   
                }else{

                }
           }
       },
       //文件提交后台
       submitAddFile(){
           if(0 == this.fileArr.length){
             this.$message({
               type: 'info',
               message: '请选择要上传的文件'
             });
             return;
           }
            var formData = new FormData();
            formData.append("crop",localStorage.getItem("crop"));
            formData.append("fileName",this.fileName)
            formData.append("userId",localStorage.getItem("ms_username"));
            for(var i=0;i<this.fileArr.length;i++){
                formData.append('files',this.fileArr[i]);
            }
          axios({
              url:'/upload',
              data:formData,
              method:'post',
              headers:{'Content-Type': 'multipart/form-data'}
          }).then(res=>{
              this.$message("文件上传成功！");
              this.dialogAddFile = false;
          }).catch(error=>{
            this.$message("文件上传出错，请重新上传！");
          })
        },
        //前台文件清除
        resetAdd(){
            this.fileArr = [];
        },
        //获取到所有的研究报告列表
        getFiles(){
          var account  = sessionStorage.getItem("ms_username");
          console.log(this.code_name);
          axios({
            url:'/getFiles',
            method:'get',
            params:{crop:'',year:this.year,account:account,code_name:this.code_name}
          }).then(res=>{
            var data = res.data;
            data.forEach(item => {
              switch(item.crop.slice(0,7)){
                case 'crop_01':
                  item.crop = '水稻';
                  break;
              };
              item.modification_time = item.modification_time.slice(0,4);
            });
            this.fileList = data;
          }).catch(error=>{
            this.$message("获取数据失败！")
          })
        },
        //按年分检索
        changeYear(){
          this.getFiles();
        },
        addReport(){
          // //console.log(this.checkedTemplate);
          this.$router.replace(this.checkedTemplate);
        },
        viewReport(row){
          sessionStorage.setItem("html",row.html);
          this.$router.replace('/viewTemplate');
        }
        
     
    },
};
</script>

<style scoped>
.all {
  width: 80%;
  margin: 0 auto;
  height:700px;
}
</style>