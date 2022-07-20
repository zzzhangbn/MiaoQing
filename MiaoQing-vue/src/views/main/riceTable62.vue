<template>
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col >
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <YearPicker  v-model="startYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison()">查询对比</el-button>
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
                <el-table-column align="center" prop="field_3"  width="80%" label="地区类型" />
                <el-table-column align="center" prop="field_4"  width="80%" label="稻作类型" />
                <el-table-column align="center" prop="field_5"  label="品种名称" />
                <el-table-column align="center" prop="field_6"  label="栽植方式" />
                <el-table-column align="center" label="化除1-播栽前">
                  <el-table-column align="center" prop="field_7_1"  label="日期" />
                  <el-table-column align="center" prop="field_7_2"  label="除草剂名称" />
                  <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_7_4"  label="方法" />
                  <el-table-column align="center" prop="field_7_5"  label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化除2-播栽后或出苗后">
                  <el-table-column align="center" prop="field_8_1"  label="日期" />
                  <el-table-column align="center" prop="field_8_2"  label="除草剂名称" />
                  <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_8_4"  label="方法" />
                  <el-table-column align="center" prop="field_8_5"  label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化除3播栽后或出苗后">
                  <el-table-column align="center" prop="field_9_1"  label="日期" />
                  <el-table-column align="center" prop="field_9_2"  label="除草剂名称" />
                  <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_9_4"  label="方法" />
                  <el-table-column align="center" prop="field_9_5"  label="生育进程" />
                </el-table-column>
              <!-- </el-table-column> -->
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
      tableLabel:'水稻监测点化除管理记录表',
      tableName:'crop_01_01_table_06_02',
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
      //this.getHeaderList();
      this.people = localStorage.getItem('ms_username');
      this.point = this.$route.query.point;
      this.getTableData(this.people,this.point,this.startYear,this.endYear);
      //this.getCountySelection();
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
    }, 
      //获取数据对比
      getDataComparison(){
        if(this.startYear != '' && this.endYear != ''){
          this.getTableData(this.people,this.point,this.startYear,this.endYear);
        }
        
      // this.getTableData(people,this.startYear,this.endYear,title);
    
  },
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex === 0){
          return{background:'#6E8B3D',color:'#FFFFFF',}
        }
        if(rowIndex==1 || rowIndex==2){
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
    //获取表数据
    getTableData(people,point,startYear,endYear){
      if(startYear == ''){
        startYear = 0;
      }
      if(endYear == ''){
        endYear = 0;
      }
      axios({
        url:'/getAllData0602',
        method:'get',
        params:{
          people:people,
          point:point,
          column_id:'',
          beginYear:startYear,
          endYear:endYear
        }
      }).then(res=>{
          this.tableData=res.data;
          //console.log(this.tableData);
          this.dataNum - this.tableData.length;
          this.temSeries = [];
          var field = "";
          for(var i = 0 ; i < this.tableData.length ; ++i){
              var obj = new Object;
              var index = 7;
              var tem = [];
                while(index < 10){
                    field = "field_"+index+"_3";
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
          //console.log(this.temSeries);
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
              data: ['化除1-播栽前', '化除2-播栽后或出苗后', '化除3播栽后或出苗后'],
              axisLabel:{interval:0}};
              this.option.yAxis = {type: 'value',name:'除草剂亩用量/kg'};
              this.option.tooltip = {trigger: 'axis'};
              myChart.setOption(this.option);
      
            //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
            window.addEventListener('resize',function() {myChart.resize()});
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },

    // //分页
    // handleCurrentChange:function(currentPage){
    //   this.currentPage = currentPage;
    //   //console.log(this.currentPage);
    // },
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



