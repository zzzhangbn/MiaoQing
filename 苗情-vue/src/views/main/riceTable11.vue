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
                  <YearPicker  v-model="startYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                  <el-button   style="margin-right: 20px" @click="getDataComparison('栽植方式')">查询对比</el-button>
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
              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'4px'}" :row-class-name="tableRowClassName" :row-style="{height: '30px'}"  row-key="id" stripe>
                <!-- <el-table-column width="100%" :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" width="64px" :index="countMethod" label="序号" />
                  <el-table-column align="center" fixed prop="field_1" width="100%" label="县（市、区）" />
                  <el-table-column align="center" fixed prop="field_2" width="100%" label="全县总面积（万亩）" />
                  <el-table-column align="center" prop="field_3" width="100%" label="地区类型" />
                  <!-- <el-table-column align="center" prop="field_4" width="100%" label="稻作类型" /> -->
                  <el-table-column align="center"  width="100%" label="栽植方式（万亩）">
                    <el-table-column align="center" label="人工移栽"  width="100%" >
                      <el-table-column align="center" prop="field_5_1_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_1_2" width="60%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="机械插秧">
                      <el-table-column align="center" prop="field_5_2_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_2_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="盘育抛栽">
                      <el-table-column align="center" prop="field_5_3_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_3_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="无盘旱育抛栽">
                      <el-table-column align="center" prop="field_5_4_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_4_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="撒直播（旱）">
                      <el-table-column align="center" prop="field_5_5_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_5_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="撒直播（水）">
                      <el-table-column align="center" prop="field_5_6_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_6_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="机条播（旱）">
                      <el-table-column align="center" prop="field_5_7_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_7_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="机条播（水）">
                      <el-table-column align="center" prop="field_5_8_1" width="90%" label="" />
                      <el-table-column align="center" prop="field_5_8_2" width="67%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="小计">
                      <el-table-column align="center" prop="field_5_9_1" width="85%" label="" />
                      <el-table-column align="center" prop="field_5_9_2" width="70%" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" width="100%" label="前茬口类型（万亩）">
                    <el-table-column align="center" label="水稻">
                      <el-table-column align="center" prop="field_6_1_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_1_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="小麦">
                      <el-table-column align="center" prop="field_6_2_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_2_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="油菜">
                      <el-table-column align="center" prop="field_6_3_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_3_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="空闲田">
                      <el-table-column align="center" prop="field_6_4_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_4_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="绿肥">
                      <el-table-column align="center" prop="field_6_5_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_5_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="蔬菜等经作">
                      <el-table-column align="center" prop="field_6_6_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_6_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="其他">
                      <el-table-column align="center" prop="field_6_7_1" width="100%" label="" />
                      <el-table-column align="center" prop="field_6_7_2" width="82%" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="小计">
                      <el-table-column align="center" prop="field_6_8_1" width="85%" label="" />
                      <el-table-column align="center" prop="field_6_8_2" width="82%" label="" />
                    </el-table-column>
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
import echarts from 'echarts'
import moment from "moment"
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
      tableLabel:'监测县(市、区)水稻生产栽插概况调查表',
      tableName:'crop_01_01_table_01_01',
      //退回原因对话框
      dialogVisible:false,
      //暂存退回记录
      rowRecord:[],
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      userSelectedGap:'本年度',//用户选择对比年份
      selectList:[{
        value:'1',
        label:'前一年'
      },{
        value:'2',
        label:'前两年'
      }],
      flag:0,//查询数据所在的年份
      dialogTableVisibleDataComparison:false,//
      temSeries:[],
      option:{},
      open: false,
      startYear:'2019',
      endYear:'2022',
    };
  },
  created() {
    if(this.userSelectedGap == 1 || this.userSelectedGap == 2){
      this.flag = this.userSelectedGap;
    }
        var people = localStorage.getItem('ms_username'); 
        this.getTableData(people,this.startYear,this.endYear,); 
        sessionStorage.removeItem("path");
        sessionStorage.setItem("path","/dataHome1");
  },

  methods: {
    //改变开始年份
    changeYear1(){
      //console.log(this.startYear);
    },
    //改变结束年份
    changeYear2(){
      //console.log(this.endYear);
    },
    //获取数据对比
    getDataComparison(title){
        this.flag = this.userSelectedGap;
        var people = localStorage.getItem('ms_username');
        this.getTableData(people,this.startYear,this.endYear,title);
      
    },
    //tab标签头切换
    changeSubject(e){
      //console.log(e);
      var title = '';
      if(e == 1){
        title = '栽植方式';
      }
      if(e == 2){
        title = '前茬口类型';
      }
      this.userSelectedGap = '本年度';
      this.flag = 0;
      var people = localStorage.getItem('ms_username'); 
      let myChart = echarts.init(document.getElementById('chart_example1'));
      if(myChart != null && myChart != '' && myChart != undefined){
          myChart.dispose();//销毁原图
      }
      this.getTableData(people,this.startYear,this.endYear,title);
    },
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==1 || rowIndex==0){
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
    getTableData(people,startYear,endYear,columnName){
      if(startYear == ''){
        startYear = 0;
      }
      if(endYear == ''){
        endYear = 0;
      }
      var column_id = '';
      axios({
        url:'/checkUserAllKnownData1',
        method:'post',
        params:{
          UserID:people,
          column_id:column_id,
          startYear:startYear,
          endYear:endYear,
        }
      }).then(res=>{
        this.tableData = [];
        for(var i = 0 ; i < res.data.length ; ++i){
          this.tableData.push(res.data[i]);
        }
        //console.log(this.tableData);
        this.dataNum = this.tableData.length;
          this.temSeries = [];
          var field = "";
          for(var i = 0 ; i < this.tableData.length ; ++i){
              var obj = new Object;
              var index = 1;
              var tem = [];
              if(columnName == '栽植方式'){
                  while(index < 9){
                    field = "field_5_"+index+"_1";
                    if(this.tableData[i][field] != null && this.tableData[i][field] != '填写正确！'){
                      tem.push(this.tableData[i][field]);
                  }
                  index ++;
                }
              }
              if(columnName == '前茬口类型'){
                while(index < 8){
                    field = "field_6_"+index+"_1";
                    if(this.tableData[i][field] != null && this.tableData[i][field] != '填写正确！'){
                      tem.push(this.tableData[i][field]);
                  }
                  index ++;
                }
              }
                obj.data = tem;
                obj.type = 'line';
                var myDate = this.tableData[i].modification_time;
                //console.log(myDate.slice(0,4));
                if(myDate.slice(0,4).length>0){
                  obj.name = myDate.slice(0,4);
                }
                this.temSeries.push(obj);
          }
          var series = [];
          for(var i = 0 ; i < this.temSeries.length ; ++i){
            series.push(this.temSeries[i]);
          }
              //将数据读入相应的数组中
              let myChart = echarts.init(document.getElementById('chart_example1'));
              if(myChart != null && myChart != '' && myChart != undefined){
                myChart.dispose();//销毁原图
              }
              myChart = echarts.init(document.getElementById('chart_example1'));
              this.option.series = series;
              this.option.xAxis =  {
              type: 'category',
              data: ['人工移栽', '机械插秧', '盘育抛栽', '无盘旱育抛栽', '撒直播（旱）', '撒直播（水）', '机条播（旱）','机条播（水）'],
              axisLabel:{interval:0,rotate:'45'}};
              this.option.yAxis = {type: 'value',name:'各栽植方式数量（万亩）'};
              this.option.tooltip = {trigger: 'axis'};
              // if(this.flag == 0){
              //   this.option.legend =  {data: ['本年度']};         
              // }
              // if(this.flag == 1){
              //   this.option.legend =  {data: ['本年度','上年度']};   
              // }
              // if(this.flag == 2){
              //   this.option.legend =  {data: ['本年度','上年度','前年度']};   
              // }
              
              myChart.setOption(this.option);
      
            //建议加上以下这一行代码，不加的效果图如下（当浏览器窗口缩小的时候）。超过了div的界限（红色边框）
            window.addEventListener('resize',function() {myChart.resize()});
      
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

  },
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



