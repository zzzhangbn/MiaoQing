<template>
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <!-- <el-select style="width: 160px;float: left;margin-right: 10px;" v-model="userSelectedGap">
                    <el-option v-for="(item,index) in selectList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"></el-option>
                  </el-select> -->
                  <YearPicker v-model="startYear"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker v-model="endYear"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison();dataComparison()">查询对比</el-button>
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
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
                  <el-table-column align="center" label="进程1（移栽后7天）">
                    <el-table-column align="center" prop="field_4_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_4_2_1" label="" />
                      <el-table-column align="center" prop="field_4_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_4_3_1" label="" />
                      <el-table-column align="center" prop="field_4_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_4_4_1" label="" />
                      <el-table-column align="center" prop="field_4_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程2（移栽后14天）">
                    <el-table-column align="center" prop="field_5_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_5_2_1" label="" />
                      <el-table-column align="center" prop="field_5_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_5_3_1" label="" />
                      <el-table-column align="center" prop="field_5_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_5_4_1" label="" />
                      <el-table-column align="center" prop="field_5_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程3（移栽后21天）">
                    <el-table-column align="center" prop="field_6_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_6_2_1" label="" />
                      <el-table-column align="center" prop="field_6_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_6_3_1" label="" />
                      <el-table-column align="center" prop="field_6_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_6_4_1" label="" />
                      <el-table-column align="center" prop="field_6_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程4（移栽后28天）">
                    <el-table-column align="center" prop="field_7_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_7_2_1" label="" />
                      <el-table-column align="center" prop="field_7_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_7_3_1" label="" />
                      <el-table-column align="center" prop="field_7_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_7_4_1" label="" />
                      <el-table-column align="center" prop="field_7_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程5（移栽后35天）">
                    <el-table-column align="center" prop="field_8_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_8_2_1" label="" />
                      <el-table-column align="center" prop="field_8_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_8_3_1" label="" />
                      <el-table-column align="center" prop="field_8_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_8_4_1" label="" />
                      <el-table-column align="center" prop="field_8_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程6（移栽后42天）">
                    <el-table-column align="center" prop="field_9_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_9_2_1" label="" />
                      <el-table-column align="center" prop="field_9_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_9_3_1" label="" />
                      <el-table-column align="center" prop="field_9_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_9_4_1" label="" />
                      <el-table-column align="center" prop="field_9_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程7（移栽后52天）">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_10_2_1" label="" />
                      <el-table-column align="center" prop="field_10_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_10_3_1" label="" />
                      <el-table-column align="center" prop="field_10_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_10_4_1" label="" />
                      <el-table-column align="center" prop="field_10_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程8（移栽后62天）">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_11_2_1" label="" />
                      <el-table-column align="center" prop="field_11_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_11_3_1" label="" />
                      <el-table-column align="center" prop="field_11_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_11_4_1" label="" />
                      <el-table-column align="center" prop="field_11_4_2" label="" />
                    </el-table-column>
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
      <div id="chart_example1" style="width: 99%;height: 400px;"></div>
     </div>
</template>

<script>
import axios from 'axios'
import * as math from 'mathjs'
import echarts from 'echarts'
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
      tableLabel:'监测县(市、区)水稻苗情评价动态列表（移栽稻）',
      tableName:'crop_01_01_table_03_02',
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      flag:0,//0 为查询当前年份
      userSelectedGap:'本年度',
      selectList:[{
        value:'1',
        label:'前一年'
      },{
        value:'2',
        label:'前两年'
      }],
      temSeries:[],
      option:{},
      flag:0,//0 为查询当前年份
      temSeries:[],
      option:{},
      startYear:2019,//开始年份
      endYear:2022//结束年份
    };
  },
  created() {
    if(this.userSelectedGap == 1 || this.userSelectedGap == 2){
      this.flag = this.userSelectedGap;
    }
    var people = localStorage.getItem('ms_username');
    this.getTableData(people,this.startYear,this.endYear);
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
  },
  methods: {
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
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
    //获取数据对比
    getDataComparison(title){
      // if(this.userSelectedGap == 1 || this.userSelectedGap == 2){
        this.flag = this.userSelectedGap;
        var people = localStorage.getItem('ms_username'); 
        this.getTableData(people,this.startYear,this.endYear);
      // }
    },
    //获取表数据
    getTableData(people,startYear,endYear){
      var column_id = '';
      axios({
        url:'/getUserAllData0302',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          startYear:startYear,
          endYear:endYear
        }
      }).then(res=>{
          this.tableData=res.data;
          this.dataNum = res.data.length;
          // //console.log(this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //数据对比可视化分析
    dataComparison(){
      var userId = localStorage.getItem('ms_username'); 
      var flag = this.userSelectedGap;
      if(this.startYear>this.endYear||this.startYear>2021){
        this.$message({message:'你选择的年份有误，请重新选择！',type:'warning'});
        return;
      }
      axios({
        url:'/dataComparison_03_02',
        params:{userId,startYear:this.startYear,endYear:this.endYear},
        method:'get'
      }).then(res=>{
        var data = res.data;
        var datas_1 = data.datas_1;
        var datas_2 = data.datas_2;
        var datas_3 = data.datas_3;
        var chartDom = document.getElementById('chart_example1');
        if (chartDom.hasAttribute("_echarts_instance_"))
            chartDom.removeAttribute("_echarts_instance_");
        var myChart = echarts.init(chartDom);
        var option;
        var series = new Array();
        for(let i=0;i<data.years.length;i++){
          var sery_1 = {
              name: '一类苗',
              type: 'bar',
              stack: data.years[i],
              emphasis: {
                  focus: 'series'
              },
              label: {
                show: true,
                position: 'insideBottom',
                color: '#FFFFFF',
                formatter: function (params) {
                  let rs = "";
                  rs+=data.years[i]+"年"+"\n";
                  rs+=params.value;
                  return rs;
                }
              },
              data: datas_1.slice(i*8,(i+1)*8)
          }
          var sery_2 = {
              name: '二类苗',
              type: 'bar',
              stack: data.years[i],
              emphasis: {
                  focus: 'series'
              },
              label:{
                show:true,
              },
              data: datas_2.slice(i*8,(i+1)*8)
          }
          var sery_3 = {
              name: '三类苗',
              type: 'bar',
              stack: data.years[i],
              emphasis: {
                  focus: 'series'
              },
              label:{
                show:true,
              },
              data: datas_3.slice(i*8,(i+1)*8)
          }
          series.push(sery_1);
          series.push(sery_2);
          series.push(sery_3);
        }
        option = {
            title: {
              text:'一、二、三类苗占比',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['一类苗','二类苗','三类苗']
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
                    data: ['进程1','进程2','进程3','进程4','进程5','进程6','进程7','进程8']
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} 万亩'
                    }
                }
            ],
            color: ['#5470C6', '#91CC75', '#EE6666'],
            series:series
        };
        option && myChart.setOption(option);

      }).catch(err=>{
        this.$message({message:'数据获取失败',type:'warning'});
      })
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



