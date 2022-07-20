<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">

          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <YearPicker  v-model="beginYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getTableData(people)">查询对比</el-button>
                    <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
                        <el-button type="info" plain>返回</el-button>
                    </router-link>
                </div>
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
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <!-- <el-table-column :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                  <el-table-column align="center" prop="field_2"  label="地区类型" />
                  <el-table-column align="center" prop="field_3"  label="稻作类型" />
                  <el-table-column align="center" label="全县面上平均水平">
                    <el-table-column align="center" prop="field_4_1"  label="总面积（万亩）" />
                    <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_4_3"  label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                    <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                    <el-table-column align="center" prop="field_4_6"  label="亩理论产量（kg/亩）" />
                  </el-table-column>
                <el-table-column align="center" label="人工移栽">
                  <el-table-column align="center" prop="field_5_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_5_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_5_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_5_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_5_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_5_6"  label="亩理论产量（kg/亩）" />
                </el-table-column>
                <el-table-column align="center" label="机械插秧">
                  <el-table-column align="center" prop="field_6_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_6_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_6_6"  label="亩理论产量（kg/亩）" />
                </el-table-column>
                <el-table-column align="center" label="盘育抛秧">
                  <el-table-column align="center" prop="field_7_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_7_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_7_6"  label="亩理论产量（kg/亩）" />
                </el-table-column>
                <el-table-column align="center" label="无盘旱育抛秧">
                  <el-table-column align="center" prop="field_8_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_8_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_8_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_8_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_8_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_8_6"  label="亩理论产量（kg/亩）" />
                </el-table-column>
                <el-table-column align="center" label="撒直播">
                  <el-table-column align="center" prop="field_9_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_9_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_9_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_9_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_9_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_9_6"  label="亩理论产量（kg/亩）" />
                </el-table-column>
                <el-table-column align="center" label="机条播">
                  <el-table-column align="center" prop="field_10_1"  label="面积（万亩）" />
                  <el-table-column align="center" prop="field_10_2"  label="亩有效穗（万）" />
                  <el-table-column align="center" prop="field_10_3"  label="每穗粒数（粒）" />
                  <el-table-column align="center" prop="field_10_4"  label="结实率（％）" />
                  <el-table-column align="center" prop="field_10_5"  label="千粒重（g）" />
                  <el-table-column align="center" prop="field_10_6"  label="亩理论产量（kg/亩）" />
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

      </el-tabs>
      <div id="main" style="width: 80%;height:400px;margin: 40px auto"></div>
     </div>
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
import * as echarts from 'echarts';
import YearPicker from './YearPicker'
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
      tableLabel:'监测县(市、区)水稻单季稻测产表',
      tableName:'crop_01_02_table_07_04',
      //数据有误输入原因
      reasonForm: {reason:''},
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
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
      beginYear:2021,
      endYear:2022,
      series:[],
      people: ''
    };
  },
  created() {
    this.initData();
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
    this.initEchart();
  },
  methods: {
    //改变开始年份
    changeYear1(){
      //console.log(this.beginYear);
    },
    //改变结束年份
    changeYear2(){
      //console.log(this.endYear);
    },
    //初始化数据
    initData() {
      this.people = localStorage.getItem('ms_username');

      this.getTableData(this.people,this.beginYear,this.endYear);
    }, 
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 3){ 
           return {display:"none"};
        }
        if(rowIndex === 0){
          return{background:'#6E8B3D',color:'#FFFFFF',}
        }
        if(rowIndex==1 || rowIndex==2){
            return{height:'0',padding:'1px',fontSize:'5px',background:'#6E8B3D',color:'#FFFFFF'};
        }
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
    getTableData(people){
      axios({
        url:'/getAllDataTwo0704',
        method:'get',
        params:{
          people:people,
          column_id:'',
          endYear:this.endYear,
          beginYear:this.beginYear
        }
      }).then(res=>{
          this.tableData=res.data;
          //console.log(res.data);
          this.series = [];
          this.dataNum = this.tableData.length;
          for(var i = 0; i< this.tableData.length;i++){
            var data = this.tableData[i];
            var myMap = {};
            var perData = [];
            myMap.name=data.modification_time.substring(0,4);
            myMap.type='line';
            for(var j=4;j<11;j++){
              var column = "field_"+j+"_6";
              perData.push(data[column]);
            }
            myMap.data=perData;
            this.series.push(myMap);
          }
        //console.log(this.series);

        var myChart = echarts.init(document.getElementById('main'));
        if(myChart != null && myChart != '' && myChart != undefined){
          myChart.dispose();//销毁原图
        }
        myChart = echarts.init(document.getElementById('main'));
        var option = {
          title: {
            text: '亩理论产量（kg/亩）'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['2018', '2019', '2020', '2021','2022']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['人工移栽', '机械插秧', '盘育抛秧', '无盘旱育抛秧', '撒直播', '机条播']
          },
          yAxis: {
            type: 'value'
          },
          series: this.series,
        };
        myChart.setOption(option);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //分页
    handleCurrentChange:function(currentPage){
      this.currentPage = currentPage;
      //console.log(this.currentPage);
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



