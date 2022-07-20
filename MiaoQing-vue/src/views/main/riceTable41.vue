<template>
  
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <YearPicker v-model="startYear"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker v-model="endYear"/>
                  <el-button   style="margin-right: 20px" @click="dataComparison();getDataComparison()">查询对比</el-button>
                  <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                  <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
                    <!-- <el-button onClick="javascript :history.back(-1);" type="info" plain>返回</el-button> -->
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
                  <el-table-column align="center" label="移栽期">
                    <el-table-column align="center" prop="field_8_1"  label="日期" />
                    <el-table-column align="center" prop="field_8_2"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_8_3"  label="基本苗（万/亩）" />
                    <el-table-column align="center" prop="field_8_4"  label="亩穴数（万）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程1（移栽后7天）">
                    <el-table-column align="center" prop="field_9_1"  label="日期" />
                    <el-table-column align="center" prop="field_9_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_9_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_9_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程2（移栽后14天）">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center" prop="field_10_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_10_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_10_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程3（移栽后21天）">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center" prop="field_11_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_11_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_11_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                   <el-table-column align="center" label="进程4（移栽后28天）">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center" prop="field_12_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_12_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_12_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程5（移栽后35天）">
                    <el-table-column align="center" prop="field_13_1"  label="日期" />
                    <el-table-column align="center" prop="field_13_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_13_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_13_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程6（移栽后42天）">
                    <el-table-column align="center" prop="field_14_1"  label="日期" />
                    <el-table-column align="center" prop="field_14_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_14_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_14_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程7（移栽后52天）">
                    <el-table-column align="center" prop="field_15_1"  label="日期" />
                    <el-table-column align="center" prop="field_15_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_15_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_15_4"  label="茎蘖数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="进程8（移栽后62天）">
                    <el-table-column align="center" prop="field_16_1"  label="日期" />
                    <el-table-column align="center" prop="field_16_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_16_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_16_4" label="穗数（万/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="孕穗中期进程9移栽后72天）">
                    <el-table-column align="center" prop="field_17_1"  label="日期" />
                    <el-table-column align="center" prop="field_17_2"  label="生育进程" />
                    <el-table-column align="center" prop="field_17_3"  label="叶龄（叶）" />
                    <el-table-column align="center" prop="field_17_4" label="穗数（万/亩）" />
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
      <div id="chart_example1" style="width: 99%;height: 500px;"></div>
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
      tableLabel:'监测县(市、区)中稻生育进度调查表（移栽稻）',
      tableName:'crop_01_01_table_04_01',
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
      startYear:2019,//开始年份
      endYear:2022,//结束年份
      point:'',
      people:'',
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
    back(){
      this.$router.go(-1);//返回上一层
    },
    //切换tabs
    handleClick(tab,event){
     
    }, 
    //获取数据对比
    getDataComparison(){
      // if(this.userSelectedGap == 1 || this.userSelectedGap == 2){
        this.people = localStorage.getItem('ms_username'); 
        this.getTableData(this.people,this.point,this.startYear,this.endYear);
      // }
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
        url:'/dataComparison_04_01',
        params:{userId,startYear:this.startYear,endYear:this.endYear,point:this.point},
        method:'get'
      }).then(res=>{
        var data = res.data;
        // //console.log(data);
        var datas_1 = data.datas_1;
        var datas_2 = data.datas_2;
        var chartDom = document.getElementById('chart_example1');
        if (chartDom.hasAttribute("_echarts_instance_"))
            chartDom.removeAttribute("_echarts_instance_");
        var myChart = this.$echarts.init(chartDom);
        var option;
        var series = new Array();
        for(let i=0;i<data.years.length;i++){
          var sery_1 = {
              name: '叶龄',
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
              data: datas_1.slice(i*7,(i+1)*7)
          }
          var sery_2 = {
              name: '茎蘖数',
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
              data: datas_2.slice(i*7,(i+1)*7)
          }
          series.push(sery_1);
          series.push(sery_2);
        }
        option = {
            title: {
              text:'各进程叶龄以及茎蘖数的变化比较',
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['叶龄','茎蘖数'],
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
                    data: ['进程1','进程2','进程3','进程4','进程5','进程6','进程7']
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
      })
      // }).catch(err=>{
      //   this.$message({message:'数据获取失败',type:'warning'});
      // })
    },
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
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取表数据
    getTableData(people,point,startYear,endYear){
      var column_id = '';
      const _this = this;
      axios({
        url:'/getUserAllData0401',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          monitor_location:point,
          startYear:startYear,
          endYear:endYear
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



