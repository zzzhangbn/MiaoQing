<template>
  
    <div class="totalRange">
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
                <el-table-column align="center" label="化调剂1">
                  <el-table-column align="center" prop="field_7_1"  label="使用时间" />
                  <el-table-column align="center" prop="field_7_2"  label="化调剂名称" />
                  <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_7_4"  label="使用方法" />
                  <el-table-column align="center" prop="field_7_5"  label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化调剂2">
                  <el-table-column align="center" prop="field_8_1"  label="使用时间" />
                  <el-table-column align="center" prop="field_8_2"  label="化调剂名称" />
                  <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_8_4"  label="使用方法" />
                  <el-table-column align="center" prop="field_8_5"  label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化调剂3">
                  <el-table-column align="center" prop="field_9_1"  label="使用时间" />
                  <el-table-column align="center" prop="field_9_2"  label="化调剂名称" />
                  <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                  <el-table-column align="center" prop="field_9_4"  label="使用方法" />
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
      tableLabel:'水稻监测点化学调节剂使用记录表',
      tableName:'crop_01_01_table_06_05',
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
      this.getTableData(this.people,this.point);
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
      const _this = this;
      axios({
        url:'/getAllData0605',
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
        this.series = [];
        for(var i = 0; i< this.tableData.length;i++){
          var data = this.tableData[i];
          var myMap = {};
          var perData = [];
          myMap.name=data.modification_time.substring(0,4);
          myMap.type='line';
          for(var j=7;j<10;j++){
            var column = "field_"+j+"_3";
            perData.push(data[column]);
          }
          myMap.data=perData;
          this.series.push(myMap);
        }
        //console.log(this.series);

        var myChart = echarts.init(document.getElementById('main'));
        //console.log(myChart);
        if(myChart != null && myChart != '' && myChart != undefined){
          myChart.dispose();//销毁原图
        }
        // chartDom = document.getElementById('main');
        myChart = echarts.init(document.getElementById('main'));

        var option = {
          title: {
            text: '折线图堆叠'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['2017', '2018', '2019', '2020', '2021']
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
            data: ['化调剂1', '化调剂2', '化调剂3']
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
    initEchart(){
      this.getTableData(this.people,this.point);
      //console.log(this.series);
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



