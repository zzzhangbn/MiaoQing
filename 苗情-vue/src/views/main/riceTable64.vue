<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <!-- <el-tab-pane label="基肥施肥-分蘖肥施肥" name="1"> -->
          <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                  <YearPicker  v-model="beginYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getTableData(people,point)">查询对比</el-button>
                  <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
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
                  <el-table-column align="center" prop="field_1"  label="县（市、区）" fixed=""/>
                  <el-table-column align="center" prop="field_2"  label="监测点代码" fixed=""/>
                  <el-table-column align="center" prop="field_3"  label="地区类型" />
                  <el-table-column align="center" prop="field_4"  label="稻作类型" />
                  <el-table-column align="center" prop="field_5"  label="品种名称" />
                  <el-table-column align="center" prop="field_6"  label="栽植方式" />
                  <el-table-column align="center" label="基肥施肥（以折纯计kg/亩）1">
                    <el-table-column align="center" prop="field_7_1"  label="时间" />
                    <el-table-column align="center" prop="field_7_2"  label="N" />
                    <el-table-column align="center" prop="field_7_3"  label="P" />
                    <el-table-column align="center" prop="field_7_4"  label="K" />
                    <el-table-column align="center" prop="field_7_5"  label="方法" />
                    <el-table-column align="center" prop="field_7_6"  label="生育进程" />
                  </el-table-column>
                  <el-table-column align="center" label="分蘖肥施肥（以折纯计kg/亩）2">
                    <el-table-column align="center" prop="field_8_1"  label="时间" />
                    <el-table-column align="center" prop="field_8_2"  label="N" />
                    <el-table-column align="center" prop="field_8_3"  label="P" />
                    <el-table-column align="center" prop="field_8_4"  label="K" />
                    <el-table-column align="center" prop="field_8_5"  label="方法" />
                    <el-table-column align="center" prop="field_8_6"  label="生育进程" />
                  </el-table-column>
                  <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）3">
                    <el-table-column align="center" prop="field_9_1"  label="时间" />
                    <el-table-column align="center" prop="field_9_2"  label="N" />
                    <el-table-column align="center" prop="field_9_3"  label="P" />
                    <el-table-column align="center" prop="field_9_4"  label="K" />
                    <el-table-column align="center" prop="field_9_5"  label="方法" />
                    <el-table-column align="center" prop="field_9_6"  label="生育进程" />
                  </el-table-column>
                  <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）4">
                    <el-table-column align="center" prop="field_10_1"  label="时间" />
                    <el-table-column align="center" prop="field_10_2"  label="N" />
                    <el-table-column align="center" prop="field_10_3"  label="P" />
                    <el-table-column align="center" prop="field_10_4"  label="K" />
                    <el-table-column align="center" prop="field_10_5"  label="方法" />
                    <el-table-column align="center" prop="field_10_6"  label="生育进程" />
                  </el-table-column>
                  <el-table-column align="center" label="抽穗叶面肥施肥（以折纯计kg/亩）5">
                    <el-table-column align="center" prop="field_11_1"  label="时间" />
                    <el-table-column align="center" prop="field_11_2"  label="N" />
                    <el-table-column align="center" prop="field_11_3"  label="P" />
                    <el-table-column align="center" prop="field_11_4"  label="K" />
                    <el-table-column align="center" prop="field_11_5"  label="方法" />
                    <el-table-column align="center" prop="field_11_6"  label="生育进程" />
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
        <!-- <el-tab-pane label="孕穗肥施肥1-2" name="2"> -->
        <!-- </el-tab-pane> -->
        <!-- <el-tab-pane label="抽穗叶面肥施肥" name="3"> -->
        <!-- </el-tab-pane> -->
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
      tableLabel:'水稻监测点施肥管理记录表',
      tableName:'crop_01_01_table_06_04',
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
      endYear:2022,
      series:[],
      people:'',
      point:'',
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
        var beginYear = this.beginYear;
        var endYear = this.endYear;
        this.getTableData(this.people,this.point,this.beginYear,this.endYear);
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
    getTableData(people,point){

       axios({
         url:'/getAllData0604',
         method:'get',
         params:{
           people:people,
           point:point,
           column_id:'',
           beginYear:this.beginYear,
           endYear:this.endYear
         }
       }).then(res=>{
          //_this.tableData=res.data;
         this.tableData=res.data;
         this.series = [];
         for(var i = 0; i< this.tableData.length;i++){
           var data = this.tableData[i];
           var myMapN = {};
           var myMapP = {};
           var myMapK = {};
           var perDataN = [];
           var perDataP = [];
           var perDataK = [];
           myMapN.name='N';
           myMapP.name='P';
           myMapK.name='K';
           myMapN.stack=data.modification_time.substring(0,4);
           myMapP.stack=data.modification_time.substring(0,4);
           myMapK.stack=data.modification_time.substring(0,4);
           myMapN.label=this.label;
           myMapP.label=this.label;
           myMapK.label=this.label;
           myMapN.type='bar';
           myMapP.type='bar';
           myMapK.type='bar';
           for(var k=7;k<12;k++){
             var columnN = "field_"+k+"_2";
             var columnP = "field_"+k+"_3";
             var columnK = "field_"+k+"_4";
             perDataN.push(data[columnN]);
             perDataP.push(data[columnP]);
             perDataK.push(data[columnK]);
           }
           myMapN.data=perDataN;
           myMapP.data=perDataP;
           myMapK.data=perDataK;
           this.series.push(myMapN);
           this.series.push(myMapP);
           this.series.push(myMapK);

         }
         //console.log(this.series);

         var myChart = echarts.init(document.getElementById('main'));
         if(myChart != null && myChart != '' && myChart != undefined){
           myChart.dispose();//销毁原图
         }

         myChart = echarts.init(document.getElementById('main'));
         var option = {
           tooltip: {
             trigger: 'axis',
             axisPointer: {            // 坐标轴指示器，坐标轴触发有效
               type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
             }
           },
           legend: {
             data: ['N', 'P', 'K']
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
               data: ['基肥施肥', '分蘖肥施肥', '孕穗肥施肥', '孕穗肥施肥', '抽穗叶面肥施肥']
             }
           ],
           yAxis: [
             {
               type: 'value'
             }
           ],
           series:this.series,
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
    background:#FF0000;
    
  }
  .fy{
    text-align: left;
    margin-top: 10px;
  }
</style>



