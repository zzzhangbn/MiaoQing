<template>
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <YearPicker  v-model="startYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison('田间面积')">查询对比</el-button>
                  <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
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
                  <el-table-column align="center" fixed prop="field_2"  label="地区类型" />
                  <el-table-column align="center" fixed prop="field_3"  label="全县总面积（万亩）" />
                  <el-table-column align="center" label="开始播种日期">
                      <el-table-column align="center" prop="field_4_1"  label="育秧" />
                      <el-table-column align="center" prop="field_4_2"  label="直播" />
                  </el-table-column>
                  <el-table-column align="center"   label="秧田面积（万亩）">
                    <el-table-column align="center" prop="field_5"  label="" />
                    <el-table-column align="center" label="其中集中育秧">
                      <el-table-column align="center" prop="field_6_1"  label="" />
                      <el-table-column align="center" prop="field_6_2"  label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center"   label="折合大田面积（万亩）">
                    <el-table-column align="center" label="">
                      <el-table-column align="center" prop="field_7_1"  label="" />
                      <el-table-column align="center" prop="field_7_2"   label="" />  
                  </el-table-column>
                    <el-table-column align="center" label="其中集中育秧">
                      <el-table-column align="center" prop="field_8_1"  label="" />
                      <el-table-column align="center" prop="field_8_2"  label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="播种期进度1">
                    <el-table-column align="center" prop="field_9_1"  label="日期" />
                    <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_9_2_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_9_2_2" width="90%" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_9_3" width="150%" label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_9_4" width="180%" label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_9_5"  label="小计" />
                    <el-table-column align="center" prop="field_9_6"  label="占比" />
                  </el-table-column>  
                  <el-table-column align="center" label="播种期进度2">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_10_2_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_10_2_2" width="90%" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_10_3" width="150%" label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_10_4" width="180%" label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_10_5"  label="小计" />
                    <el-table-column align="center" prop="field_10_6"  label="占比" />
                  </el-table-column>
                  <el-table-column align="center" label="播种期进度3">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center" width="180px" label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_11_2_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_11_2_2" width="90%" label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_11_3" width="150%" label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_11_4" width="180%" label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_11_5"  label="小计" />
                    <el-table-column align="center" prop="field_11_6"  label="占比" />
                  </el-table-column> 
                  <el-table-column align="center" label="播种期进度4">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center" prop="field_12_2" width="150%" label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_12_3" width="180%" label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_12_4"  label="小计" />
                    <el-table-column align="center" prop="field_12_5"  label="占比" />
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
      <div id="chart_example1" style="width: 70%;height: 400px;margin-left: 10%;"></div>
     </div> 
</template>
<script>
import axios from 'axios'
import * as math from 'mathjs'
import YearPicker from './YearPicker'
import echarts from 'echarts'
export default {
  inject: ['reload'],
  components:{YearPicker},
  data() {
    return {          
      activeName:'1',   
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'监测县(市、区)水稻播栽进度调查表',
      tableName:'crop_01_01_table_03_01',
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      startYear:'2019',
      endYear:'2022',
      column_id:'',
      point:'',
      people:'',
      temSeries:[],
      option:{},
      series:[],
      label: {
        show: true,
        position: 'inside'
      },
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
      var date = new Date();
      var currentYear = date.getFullYear();
      // this.startYear = currentYear;
      // this.endYear = currentYear;
      this.people = localStorage.getItem('ms_username');
      this.getTableData(this.people,this.startYear,this.endYear);
    }, 
    //切换tabs
    handleClick(tab,event){
     
    }, 
      //改变开始年份
      changeYear1(){
        if(this.startYear != '' && this.endYear !=''){
          if(this.startYear > this.endYear){
            this.startYear = '';
            this.$message.warning("该年份不可选择！");
          }
        }
    },
    //获取数据对比
    getDataComparison(){
    // if(this.startYear != '' && this.endYear != ''){
          this.getTableData(this.people,this.startYear,this.endYear);
        // }
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
    getTableData(people,startYear,endYear){
      if(startYear == ''){
        startYear = 0;
      }
      if(endYear == ''){
        endYear = 0;
      }
      var column_id = '';
      axios({
        url:'/getUserAllData0301',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          startYear:startYear,
          endYear:endYear
        }
      }).then(res=>{
          this.tableData=res.data;
          this.dataNum = this.tableData.length;
          let myChart = echarts.init(document.getElementById('chart_example1'));
          // let myChart = echarts.init(document.getElementById('chart_example1'));
      if(myChart != null && myChart != '' && myChart != undefined){
          myChart.dispose();//销毁原图
      }
      myChart = echarts.init(document.getElementById('chart_example1'));
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['育秧已播种面积（万亩）', '已栽插面积（万亩）', '直播已播种面积（万亩）']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['播栽期进度1', '播栽期进度二', '播栽期进度三', '播栽期进度四']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            
        };
        option.series = this.series 
          this.series = [];
          for(var i = 0; i< this.tableData.length;i++){
            var data = this.tableData[i];
            var myMapYY = {};
            var myMapZC = {};
            var myMapZB = {};
            var perDataYY = [];
            var perDataZC = [];
            var perDataZB = [];
            myMapYY.name='育秧已播种面积';
            myMapZC.name='已栽插面积';
            myMapZB.name='直播已播种面积';
            myMapYY.stack=data.modification_time.substring(0,4);
            myMapZC.stack=data.modification_time.substring(0,4);
            myMapZB.stack=data.modification_time.substring(0,4);
            myMapYY.label=this.label;
            myMapZC.label=this.label;
            myMapZB.label=this.label;
            myMapYY.type='bar';
            myMapZC.type='bar';
            myMapZB.type='bar';
            for(var k=9;k<13;k++){
              var columnYY = "field_"+k+"_2_1";
              var columnZC = "field_"+k+"_3";
              var columnZB = "field_"+k+"_4";
              perDataYY.push(data[columnYY]);
              perDataZC.push(data[columnZC]);
              perDataZB.push(data[columnZB]);
            }
            myMapYY.data=perDataYY;
            myMapZC.data=perDataZC;
            myMapZB.data=perDataZB;
            this.series.push(myMapYY);
            this.series.push(myMapZC);
            this.series.push(myMapZB);
          
          }
              myChart.setOption(option);
      
          //   //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
            window.addEventListener('resize',function() {myChart.resize()});

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



