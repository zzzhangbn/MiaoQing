<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <!-- <el-tab-pane label="7月31日前/8月1-10日抽穗扬花的中稻" name="1"> -->
          <div class="tableRange">
            <div class="searchBox">
              <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                <YearPicker  v-model="beginYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                <el-button   style="margin-right: 20px" @click="getTableData(people,column_id)">查询对比</el-button>
                <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
                  <el-button type="info" plain>返回</el-button>
                </router-link>
              </div>
            </div>
            <div class="tableStyle">
              <el-row>
              </el-row>
              <el-row>
                <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                    <span>{{tableLabel}}</span>
                </div>
              </el-row>
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName"  :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <!-- <el-table-column :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="field_1"  label="县（市、区）" fixed=""/>
                  <el-table-column align="center" prop="field_2"  label="早稻总面积(万亩)" />
                  <el-table-column align="center" label="早稻6月20日前已抽穗扬花">
                    <el-table-column align="center" prop="field_3_1"  label="面积(万亩）" />
                    <el-table-column align="center" prop="field_3_2"  label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_3_3"  label="穗总粒数（个）" />
                    <el-table-column align="center" prop="field_3_4"  label="结实率（％）" />
                    <el-table-column align="center" prop="field_3_5"  label="千粒重（g）" />
                    <el-table-column align="center" prop="field_3_6"  label="理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="早稻6月20日~30日抽穗扬花">
                    <el-table-column align="center" prop="field_4_1"  label="面积(万亩）" />
                    <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_4_3"  label="穗总粒数（个）" />
                    <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                    <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                    <el-table-column align="center" prop="field_4_6"  label="理论产量（kg/亩）" />
                  </el-table-column>
                <el-table-column align="center" prop="field_5"  label="晚稻总面积(万亩)" />
                  <el-table-column align="center" label="晚稻9月10日前已抽穗扬花">
                    <el-table-column align="center" prop="field_6_1"  label="面积(万亩）" />
                    <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_6_3"  label="穗总粒数（个）" />
                    <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                    <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                    <el-table-column align="center" prop="field_6_6"  label="理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="晚稻9月10日以后抽穗扬花">
                    <el-table-column align="center" prop="field_7_1"  label="面积(万亩）" />
                    <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_7_3"  label="穗总粒数（个）" />
                    <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                    <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                    <el-table-column align="center" prop="field_7_6"  label="理论产量（kg/亩）" />
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
        <!-- <el-tab-pane label="8月11-15日/8月16日-20日抽穗扬花的中稻" name="2"> -->

        <!-- </el-tab-pane> -->
        <!-- <el-tab-pane label="8月21-31日/9月上旬~抽穗扬花的中稻" name="3"> -->

        <!-- </el-tab-pane> -->
      </el-tabs>
      <div id="main" style="width: 80%;height:400px;margin: 40px auto"></div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
import * as echarts from 'echarts'
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
      tableLabel:'监测县(市、区)水稻单季稻苗情监测预测产表',
      tableName:'crop_01_02_table_07_01',
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
      column_id:'',
      beginYear:2021,
      endYear:2022,
      series:[],
      people:'',
    };
  },
  created() {
    this.initData();
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
    this.initEchart()
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
      if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
                }
      this.getTableData(this.people,this.column_id);
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
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
      }
      if (day >= 0 && day <= 9) {
            day = "0" + day;
      }
      if(hour >= 0 && hour <= 9) {
          hour = "0" + hour;
      }
      if(minute >= 0 && minute <= 9) {
          minute = "0" + minute;
      }
      if(second >= 0 && second <= 9) {
          second = "0" + second;
      }
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
    getTableData(people,column_id){
      axios({
        url:'/getAllDataTwo0701',
        method:'get',
        params:{
          people:people,
          column_id:'',
          beginYear:this.beginYear,
          endYear:this.endYear
        }
      }).then(res=>{
        this.tableData=res.data;
        //console.log(res.data);
        this.dataNum = this.tableData.length;
        this.series = [];
        for(var i = 0; i< this.tableData.length;i++){
          var data = this.tableData[i];
          var myMap = {};
          var perData = [];
          myMap.name=data.modification_time.substring(0,4);
          myMap.type='line';
          for(var j=3;j<7;j++){
            if(j != 5){
              var column = "field_"+j+"_6";
            }

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
            text: '理论产量（kg/亩）'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['2018', '2019', '2020', '2021', '2022']
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
            data: ['时期1', '时期2', '时期3', '时期4', ]
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



