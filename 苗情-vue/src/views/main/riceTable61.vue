<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName" @tab-click="changeSubject(activeName)">
        <!-- <el-tab-pane label="时期1-4" name="1"> -->
          <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <el-col >
                  <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                    <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                    <YearPicker  v-model="startYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison('时期1-4')">查询对比</el-button>
                    <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
                      <el-button type="info" plain>返回</el-button>
                    </router-link>
                  </div>
                </el-col>
              </el-row>
            </div>
            <div class="tableStyle">
              <el-row>
              </el-row>
              <el-row>
                <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                    <span>{{tableLabel}}</span>
                </div>
              </el-row>
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <!-- <el-table-column :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1"  label="县（市、区）" fixed=""/>
                  <el-table-column align="center" prop="field_2"  label="监测点代码" fixed=""/>
                  <el-table-column align="center" prop="field_3"  label="地区类型" />
                  <el-table-column align="center" prop="field_4"  label="稻作类型" />
                  <el-table-column align="center" prop="field_5"  label="品种名称" />
                  <el-table-column align="center" prop="field_6"  label="栽植方式" />
                  <el-table-column align="center" label="时期1">
                    <el-table-column align="center" prop="field_7_1"  label="日期" />
                    <el-table-column align="center" prop="field_7_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期2">
                    <el-table-column align="center" prop="field_8_1"  label="日期" />
                    <el-table-column align="center" prop="field_8_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期3">
                    <el-table-column align="center" prop="field_9_1"  label="日期" />
                    <el-table-column align="center" prop="field_9_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期4">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center" prop="field_10_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期5">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center" prop="field_11_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期6">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center" prop="field_12_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期7">
                    <el-table-column align="center" prop="field_13_1"  label="日期" />
                    <el-table-column align="center" prop="field_13_2"  label="深度（cm）" />
                  </el-table-column>
                  <el-table-column align="center" label="时期8">
                    <el-table-column align="center" prop="field_14_1"  label="日期" />
                    <el-table-column align="center" prop="field_14_2"  label="深度（cm）" />
                  </el-table-column>
                <!-- </el-table-column> -->
              </el-table>
              <el-pagination class="fy"
                      layout="prev,pager, next, total"
                      :current-page="currentPage"
                      :page-size="pagesize"
                      :total="dataNum"
                      background
                      >
              </el-pagination>
            </div>
          </div>
        <!-- </el-tab-pane> -->
        <!-- <el-tab-pane label="时期5-8" name="2"> -->
        <!-- </el-tab-pane> -->
      </el-tabs>
      <div id="chart_example1" style="width: 99%;height: 400px;"></div>
     </div>
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
import YearPicker from './YearPicker'
import echarts from 'echarts'
export default {
  inject: ['reload'],
  components: {
      YearPicker
    },
  data() {
    return {         
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'水稻监测点水分管理记录表',
      tableName:'crop_01_01_table_06_01',
      //系统时间
      systemTime:'',
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      startYear:'2021',
      endYear:'2022',
      column_id:'',
      point:'',
      people:'',
      temSeries:[],
      option:{},
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
      this.people = localStorage.getItem('ms_username');
      this.point = this.$route.query.point;
      this.getTableData(this.people,this.point,this.startYear,this.endYear);
    }, 
      //获取数据对比
      getDataComparison(title){
        if(this.startYear != '' && this.endYear != ''){
          this.getTableData(this.people,this.point,this.startYear,this.endYear);
        }
        
      // this.getTableData(people,this.startYear,this.endYear,title);
    
  },
      //改变开始年份
      changeYear1(){
        if(this.startYear != '' && this.endYear !=''){
          if(this.startYear > this.endYear){
            this.startYear = '';
            this.$message.warning("该年份不可选择！");
          }
        }
      // //console.log(this.startYear);
    },
    //改变结束年份
    changeYear2(){
      var date = new Date();
      var currentYear = date .getFullYear();
      if(this.endYear > currentYear){
        this.endYear = currentYear;
        return this.$message.warning("不可选择该年份！");
      }
      if(this.startYear > this.endYear){
        this.startYear = '';
        return this.$message.warning("开始年份不可大于结束年份！");
      }
    },
//tab标签头切换
changeSubject(e){
      //console.log(e);
      var title = '';
      if(e == 1){
        title = '时期1-4';
      }
      if(e == 2){
        title = '时期5-8';
      }
    
      var people = localStorage.getItem('ms_username'); 
      let myChart = echarts.init(document.getElementById('chart_example1'));
      if(myChart != null && myChart != '' && myChart != undefined){
          myChart.dispose();//销毁原图
      }
      this.getTableData(people,this.point,this.startYear,this.endYear,title);
    },
    //获取时间
    //获取系统时间
    getMyTime(){
      var myDate = new Date();
      //console.log(myDate);
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
      //console.log(myTime);
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex === 0){
          return{background:'#6E8B3D',color:'#FFFFFF',}
        }
        if(rowIndex==1 || rowIndex==2){
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
    //获取表数据
    getTableData(people,point,startYear,endYear,title){
      if(startYear == ''){
        startYear = 0;
      }
      if(endYear == ''){
        endYear = 0;
      }
      console.log(point)
      axios({
            url:'/getAllData0601',
            method:'get',
            params:{
              people:people,
              point:point,
              column_id:'',
              beginYear:startYear,
              endYear:endYear
            }
      }).then(res=>{
        console.log(res.data)
        this.tableData=res.data;
        this.dataNum = this.tableData.length;
          this.temSeries = [];
          var field = "";
          for(var i = 0 ; i < this.tableData.length ; ++i){
              var obj = new Object;
              var index = 7;
              var tem = [];
                while(index < 15){
                    field = "field_"+index+"_2";
                    if(this.tableData[i][field] != null && this.tableData[i][field] != ''){
                      tem.push(this.tableData[i][field]);
                  }
                  index ++;
                }
                obj.data = tem;
                obj.type = 'line';
                var myDate = this.tableData[i].modification_time;
                
                if(myDate.slice(0,4).length>0){
                  obj.name = myDate.slice(0,4);
                }
                this.temSeries.push(obj);
          }
              var series = [];
              for(var i = 0 ; i < this.temSeries.length ; ++i){
                series.push(this.temSeries[i]);
              }
              // //console.log(series);
              //将数据读入相应的数组中
              // //console.log(document.getElementById('chart_example1'));
              let myChart = echarts.init(document.getElementById('chart_example1'));
              if(myChart != null && myChart != '' && myChart != undefined){
                myChart.dispose();//销毁原图
              }
              myChart = echarts.init(document.getElementById('chart_example1'));
              this.option.series = series;
              this.option.xAxis =  {
              type: 'category',
              data: ['时期1', '时期2', '时期3', '时期4', '时期5', '时期6', '时期7','时期8'],
              axisLabel:{interval:0,rotate:'45'}};
              this.option.yAxis = {type: 'value',name:'水稻监测点深度（cm）'};
              this.option.tooltip = {trigger: 'axis'};
              myChart.setOption(this.option);
      
            //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
            window.addEventListener('resize',function() {myChart.resize()});
      }).catch(err=>{
        return this.$message.error("查询数据失败！");
      });
    },
    //分页后序号连续处理
    countMethod(index){
       return (this.currentPage-1)*this.pagesize + index +1;
    },
  }
};
</script>

<style scoped>
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



