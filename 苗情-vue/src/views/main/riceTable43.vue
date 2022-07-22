<template>
  
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <YearPicker v-model="startYear"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker v-model="endYear"/>
                  <!-- <el-button   style="margin-right: 20px" @click="getDataComparison()">查询对比</el-button> -->
                  <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
                    <el-button type="info" plain>返回</el-button>
                  </router-link>
                </div>
              </el-row>
            </div>
            <div class="tableStyle">
               <el-row>
                  <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
                    <span>{{tableLabel}}</span>
                  </div>
                </el-row>
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" fixed prop="field_1"  label="县（市、区）" />
                  <el-table-column align="center" fixed prop="field_2"  label="监测地块" />
                  <el-table-column align="center" fixed prop="field_3"  label="地区类型" />
                  <el-table-column align="center" prop="field_4"  label="稻作类型" />
                  <el-table-column align="center" prop="field_5"  label="品系类型" />
                  <el-table-column align="center" prop="field_6"  label="品种名称" />
                  <el-table-column align="center" prop="field_7"  label="栽培方式" />
                  <el-table-column align="center" prop="field_8"  label="目标产量(kg/亩)" />
                  <el-table-column align="center" prop="field_9"  label="基本苗（万/亩）" />
                  <el-table-column align="center" label="拔节期">
                    <el-table-column align="center" prop="field_10_1"  label="测定日期" />
                    <el-table-column align="center" prop="field_10_2"  label="株高（cm）" />
                    <el-table-column align="center" prop="field_10_3"  label="总茎蘖数（万/亩）" />
                    <el-table-column align="center" prop="field_10_4"  label="平均单茎绿叶数" />
                    <el-table-column align="center" prop="field_10_5"  label="倒4与倒3叶色比" />
                    <el-table-column align="center" prop="field_10_6"  label="1叶分蘖个数" />
                    <el-table-column align="center" prop="field_10_7"  label="2叶及以上分蘖个数" />
                    <el-table-column align="center" prop="field_10_8"  label="3叶及以上分蘖个数" />
                  </el-table-column>
                  <el-table-column align="center" label="孕穗期（幼穗分化1-2期）">
                    <el-table-column align="center" prop="field_11_1"  label="测定日期" />
                    <el-table-column align="center" prop="field_11_2"  label="株高（cm）" />
                    <el-table-column align="center" prop="field_11_3"  label="总茎蘖数（万/亩）" />
                    <el-table-column align="center" prop="field_11_4"  label="平均单茎绿叶数" />
                    <el-table-column align="center" prop="field_11_5"  label="倒4与倒3叶色比" />
                  </el-table-column>
                  <el-table-column align="center" label="孕穗期（幼穗分化3-4期）">
                    <el-table-column align="center" prop="field_12_1"  label="测定日期" />
                    <el-table-column align="center" prop="field_12_2"  label="株高（cm）" />
                    <el-table-column align="center" prop="field_12_3"  label="总茎蘖数（万/亩）" />
                    <el-table-column align="center" prop="field_12_4"  label="平均单茎绿叶数" />
                    <el-table-column align="center" prop="field_12_5"  label="倒4与倒3叶色比" />
                  </el-table-column>
              </el-table>
              <el-pagination class="fy"
                      layout="prev,pager, next, total"
                      @current-change="handleCurrentChange"
                      :current-page="currentPage"
                      :page-size="pagesize"
                      :total="dataNum"
                      background
                      >
              </el-pagination>
              
            </div>
          </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
import YearPicker from './YearPicker'
export default {
  inject: ['reload'],
  data() {
    return {         
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'水稻监测点茎蘖数叶面积构成表',
      tableName:'crop_01_01_table_04_03',
      //数据有误输入原因
      reasonForm: {reason:''},
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
      //稻作类型option
      riceTypes:[],
      riceType: '',
      //品系类型option
      categorys:[],
      category: '',
      //栽植方式option
      plantWays:[],
      plantWay: '',
      //系统时间
      systemTime:'',
      //退回原因对话框
      dialogVisible:false,
      //暂存退回记录
      rowRecord:[],
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      startYear:2019,
      endYear:2031,
    };
  },
  created() {
    this.initData();
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
  },
  methods: {
    //初始化数据
    initData() {
      var people = localStorage.getItem('ms_username');
      var point = this.$route.query.point;
      this.getTableData(people,point);
    }, 
    //切换tabs
    handleClick(tab,event){
     
    }, 
    //获取时间
    //获取系统时间
    getMyTime(){
      var myDate = new Date();
     
      var sep1 = "-";
      var sep2 = ":";
      var month = myDate.getMonth() + 1;
      var day = myDate.getDate();
      var hour = myDate.getHours();
      var minute = myDate.getMinutes();
      var second = myDate.getSeconds();
      if (month >= 1 && month <= 9) {
            month = "0" + month;
      };
      if (day >= 0 && day <= 9) {
            day = "0" + day;
      };
      if(hour >= 0 && hour <= 9) {
          hour = "0" + hour;
      };
      if(minute >= 0 && minute <= 9) {
          minute = "0" + minute;
      };
      if(second >= 0 && second <= 9) {
          second = "0" + second;
      };
      var myTime = myDate.getFullYear() + sep1 + month + sep1 + day
            + " " + hour + sep2 + minute + sep2 + second;
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==1 || rowIndex==0){
            // return {background:'#eee',color:'#606266'};
            return{height:'0',padding:'1px',fontSize:'5px',background:'#6E8B3D',color:'#FFFFFF'};
        };
    },
    //表头颜色
    tableRowClassName({row,rowIndex}){
        if(rowIndex === 6){
          return '.header-row';
        }
        return '';
    },
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取表数据
    getTableData(people,point){
      var column_id = '';
      axios({
        url:'/getUserAllData0403',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          monitor_location:point,
          startYear:this.startYear,
          endYear:this.endYear
        }
      }).then(res=>{
         this.tableData=res.data;
          this.dataNum = this.tableData.length;
      
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },


    //分页
    handleCurrentChange:function(currentPage){
      this.currentPage = currentPage;
    },
    //分页后序号连续处理
    countMethod(index){
       return (this.currentPage-1)*this.pagesize + index +1;
    },
  }
};
</script>

<style scoped>
  /* .dashWrap{
      flex:1;
      width:1525px;
      overflow-y:auto;
      display:flex;
      flex-direction: column;
  } */
  .totalRange{
    width:100%;
    height: 100%;
  }
  .totalRange .tableRange{
    width:99%;
    margin: 0 auto;
  }
  .searchBox{
    width: 100%;
    height: 50px;
  }
  .tableStyle{
    width: 100%;
    height: 90%;
    margin-top: 5px;
  }
  .otherStyle{
    width: 100%;
    height: 70px;
    margin-top: 260px;
  }
  .el-table .header-row{
    background: '#FF0000';
    
  }
  .fy{
    text-align: left;
    margin-top: 10px;
  }
</style>



