<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <!-- <el-tab-pane label="灌浆速率测定1-3" name="1"> -->
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                 <YearPicker v-model="beginYear"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker v-model="endYear"/>
                  <el-button   style="margin-right: 20px" @click="dataComparison()">查询对比</el-button>
                  <router-link :to="{name:'/tableTwoMain',params: {activeNum: 1}}">
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
                  <el-table-column align="center" prop="field_1" label="县（市、区）" fixed=""/>
                  <el-table-column align="center" prop="field_2" label="监测点代码" fixed=""/>
                  <el-table-column align="center" prop="field_3" label="地区类型" />
                  <el-table-column align="center" prop="field_4" label="品种名称" />
                  <el-table-column align="center" prop="field_5" label="栽植方式" />
                  <el-table-column align="center" label="灌浆速率测定1（扬花）">
                    <el-table-column align="center" prop="field_6_1" label="日期" />
                    <el-table-column align="center" prop="field_6_2"  label="千粒重（克）" />
                  </el-table-column>
                  <el-table-column align="center" label="灌浆速率测定2（扬花+7天）">
                    <el-table-column align="center" prop="field_7_1" label="日期" />
                    <el-table-column align="center" prop="field_7_2"  label="粒重（克）" />
                    <el-table-column align="center" prop="field_7_3"  label="灌浆速率（克/千粒•天）" />
                  </el-table-column>
                  <el-table-column align="center" label="灌浆速率测定3（扬花+14天）">
                    <el-table-column align="center" prop="field_8_1" label="日期" />
                    <el-table-column align="center" prop="field_8_2"  label="粒重（克）" />
                    <el-table-column align="center" prop="field_8_3"  label="灌浆速率（克/千粒•天）" />
                  </el-table-column>
                  <el-table-column align="center" label="灌浆速率测定4（扬花+21天）">
                    <el-table-column align="center" prop="field_9_1" label="日期" />
                    <el-table-column align="center" prop="field_9_2"  label="粒重（克）" />
                    <el-table-column align="center" prop="field_9_3"  label="灌浆速率（克/千粒•天）" />
                  </el-table-column>
                  <el-table-column align="center" label="灌浆速率测定5（扬花+28天）">
                    <el-table-column align="center" prop="field_10_1" label="日期" />
                    <el-table-column align="center" prop="field_10_2"  label="粒重（克）" />
                    <el-table-column align="center" prop="field_10_3"  label="灌浆速率（克/千粒•天）" />
                  </el-table-column>
                  <el-table-column align="center" label="灌浆速率测定6（扬花+35天）">
                    <el-table-column align="center" prop="field_11_1" label="日期" />
                    <el-table-column align="center" prop="field_11_2"  label="粒重（克）" />
                    <el-table-column align="center" prop="field_11_3"  label="灌浆速率（克/千粒•天）" />
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
        <!-- </el-tab-pane> -->
        <!-- <el-tab-pane label="灌浆速率测定4-6" name="2"> -->
        <!-- </el-tab-pane> -->
      </el-tabs>
      <div id="chart_example1" style="width: 99%;height: 500px;"></div>
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
      tableLabel:'水稻监测点灌浆速率调查表',
      tableName:'crop_01_02_table_10_01',
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
      beginYear:2021,
      endYear:2022,//结束年份
      people:'',
      point:'',
    };
  },
  created() {
    this.initData();
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
  },
  methods: {
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
      this.point = this.$route.query.point;
      this.getTableData(this.people,this.point,this.beginYear,this.endYear);
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
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
      const _this = this;
      axios({
        url:'/getAllDataTwo1001',
        method:'get',
        params:{
          people:people,
          point:point,
          column_id:'',
          beginYear:this.beginYear,
          endYear:this.endYear
        }
      }).then(res=>{
        this.tableData=res.data;
          this.dataNum = this.tableData.length;        
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
        //数据对比可视化分析
    dataComparison(){
      var userId = localStorage.getItem('ms_username'); 
      var flag = this.userSelectedGap;
      if(this.beginYear>this.endYear||this.beginYear>2021){
        this.$message({message:'你选择的年份有误，请重新选择！',type:'warning'});
        return;
      }
      axios({
        url:'/dataTwoComparison_10_01',
        params:{userId,point:this.point,startYear:this.beginYear,endYear:this.endYear},
        method:'get'
      }).then(res=>{
        var data = res.data;
        // //console.log(data);
        var datas_1 = data.datas_1;
        var datas_2 = data.datas_2;
        if(datas_1.length==0)
          this.$message({message:'暂无数据！',type:'warning'});
          // break;
          // return;
        var chartDom = document.getElementById('chart_example1');
        if (chartDom.hasAttribute("_echarts_instance_"))
            chartDom.removeAttribute("_echarts_instance_");
        var myChart = this.$echarts.init(chartDom);
        var option;
        var series = new Array();
        for(let i=0;i<data.years.length;i++){
          var sery_1 = {
              name: '千粒重',
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
              data: datas_1.slice(i*5,(i+1)*5)
          }
          var sery_2 = {
              name: '灌浆速率',
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
              data: datas_2.slice(i*5,(i+1)*5),
          }
          series.push(sery_1);
          series.push(sery_2);
        }
        //console.log(series);
        option = {
            title: {
              text:'各进程千粒重以及灌浆速率的变化比较',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['千粒重','灌浆速率'],
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                top:'15%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['进程1','进程2','进程3','进程4','进程5']
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} 克'
                    },
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: '#5470C6'
                        }
                    },
                },
            ],
            color: ['#5470C6', '#91CC75', '#EE6666'],
            series:series
        };
        option && myChart.setOption(option);
      // })
      }).catch(err=>{
        this.$message({message:'数据获取失败',type:'warning'});
      })
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



