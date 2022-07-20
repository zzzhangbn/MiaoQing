<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="9">
                <template>
                  <el-select v-model="areaType" clearable  placeholder="地区类型" style="width:160px">
                    <el-option
                      v-for="item in areaTypes"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                  <el-select v-model="county" clearable  style="width:160px; margin-left: 20px;" placeholder="县（市、区）">
                    <el-option
                      v-for="item in countys"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                  <el-select v-model="riceType" clearable  style="width:160px; margin-left: 20px;" placeholder="稻作类型">
                    <el-option
                      v-for="item in riceTypes"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                </template>
              </el-col>
            <el-col :span="4">
              <el-button type="info" @click="reSet">重置</el-button>
              <el-button type="info" @click="searchInfo">查询</el-button>
            </el-col>
            <el-col :span="3">
              <!-- <el-button  type="info" plain @click="downloadAndPrint">下载打印</el-button> -->
                    <el-button  type="info" plain @click="handleDownload">全部数据导出Excel</el-button>
            </el-col>
            </el-row>
          </div>
          <div class="tableStyle">
            <el-row>
                  <div style="text-align:center;font-size:17px;font-weight:bolder;color:black;margin-bottom:10px">
                    <span>{{tableLabel}}</span>
                  </div>
                </el-row>

            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'4px'}" :row-style="{height: '30px'}" row-key="id" stripe>
              <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed/>
                <el-table-column align="center" prop="field_2" width="100%" label="地区类型" fixed/>
                <el-table-column align="center" prop="field_3" width="100%" label="稻作类型" />
                <el-table-column align="center" prop="field_4" width="100%" label="全县总面积（万亩）" />
                <el-table-column align="center" label="开始播种日期">
                    <el-table-column align="center" prop="field_5_1" width="100%" label="育秧" />
                    <el-table-column align="center" prop="field_5_2" width="100%" label="直播" />
                </el-table-column>
                <el-table-column align="center"  width="100%" label="秧田面积（万亩）">
                  <el-table-column align="center" prop="field_6" width="100%" label="" />
                  <el-table-column align="center" label="其中集中育秧">
                    <el-table-column align="center" prop="field_7_1" width="100%" label="" />
                    <el-table-column align="center" prop="field_7_2" width="100%" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center"  width="100%" label="折合大田面积（万亩）">
                  <el-table-column align="center" label="">
                    <el-table-column align="center" prop="field_8_1" width="100%" label="" />
                    <el-table-column align="center" prop="field_8_2"  width="100%" label="" />  
                </el-table-column>
                  <el-table-column align="center" label="其中集中育秧">
                    <el-table-column align="center" prop="field_9_1" width="100%" label="" />
                    <el-table-column align="center" prop="field_9_2" width="100%" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="播种期进度1">
                  <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                  <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                    <el-table-column align="center" prop="field_10_2_1" width="90%" label="" />
                    <el-table-column align="center" prop="field_10_2_2" width="90%" label="" />
                  </el-table-column>
                  <el-table-column align="center" prop="field_10_3" width="150%" label="已栽插面积（万亩）" />
                  <el-table-column align="center" prop="field_10_4" width="180%" label="直播已播种面积（万亩）" />
                  <el-table-column align="center" prop="field_10_5" width="100%" label="小计" />
                  <el-table-column align="center" prop="field_10_6" width="100%" label="占比" />
                </el-table-column>  
                <el-table-column align="center" label="播种期进度2">
                  <el-table-column align="center" prop="field_11_1" width="100%" label="日期" />
                  <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                    <el-table-column align="center" prop="field_11_2_1" width="90%" label="" />
                    <el-table-column align="center" prop="field_11_2_2" width="90%" label="" />
                  </el-table-column>
                  <el-table-column align="center" prop="field_11_3" width="150%" label="已栽插面积（万亩）" />
                  <el-table-column align="center" prop="field_11_4" width="180%" label="直播已播种面积（万亩）" />
                  <el-table-column align="center" prop="field_11_5" width="100%" label="小计" />
                  <el-table-column align="center" prop="field_11_6" width="100%" label="占比" />
                </el-table-column> 
                <el-table-column align="center" label="播种期进度3">
                  <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                  <el-table-column align="center" width="180px" label="育秧已播种面积（万亩）" >
                    <el-table-column align="center" prop="field_12_2_1" width="90%" label="" />
                    <el-table-column align="center" prop="field_12_2_2" width="90%" label="" />
                  </el-table-column>
                  <el-table-column align="center" prop="field_12_3" width="150%" label="已栽插面积（万亩）" />
                  <el-table-column align="center" prop="field_12_4" width="180%" label="直播已播种面积（万亩）" />
                  <el-table-column align="center" prop="field_12_5" width="100%" label="小计" />
                  <el-table-column align="center" prop="field_12_6" width="100%" label="占比" />
                </el-table-column>   
                <el-table-column align="center"  fixed="right" width="120px" label="操作" v-if="years == currentyear">
                  <template slot-scope="scope">
                    <el-button type="danger" size="mini" v-if="scope.row.in_use == 3" @click="backRevision(scope.$index,scope.row)" >退回修改</el-button>
                  </template>
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
            <el-dialog title="退回原因:" :visible.sync="dialogVisible" width="40%">
              <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-popover placement="top" width="200" v-model="visibleBack"><p>是否确认退回修改?</p>
                  <div style="text-align: right; margin: 10px">
                    <el-button size="mini" type="text" @click="visibleBack = false">取 消</el-button>
                    <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                  </div>
                  <el-button slot="reference" type="primary" >确  定</el-button>
                </el-popover>
              </div>
            </el-dialog>
          </div>
        </div>
      <div class="button">
        <el-button type="success" @click="returnPage">返  回</el-button>
      </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
export default {
  inject: ['reload'],
  data() {
    return {         
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'',
      tableName:'crop_01_02_table_03_02',
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
      //稻作类型option
      riceTypes:[],
      riceType: '',
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,
      //暂存参数
      areas:'',
      citys:'',
      years:'',
      //退回原因对话框
      dialogVisible:false,
      visibleBack:false,
      //系统时间
      currentyear:'',
      systemTime:'',
      recordData:[],
      reason1:'',
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
    var myDate = new Date();
    this.currentyear = myDate.getFullYear();
      this.areas = this.$route.query.area;
      this.citys = this.$route.query.city;
      this.years = this.$route.query.year;
      this.tableLabel= this.years+"年度监测县(市、区)水稻播栽进度调查表（晚稻）";
      //this.getHeaderList();
      this.getTableData();
      this.getCountySelection();
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
      };
      if (day >= 0 && day <= 9) {
            day = "0" + day;
      };
      if(hour >= 0 && hour <= 9) {
          hour = "0" + hour;
      };
      if(minute >= 0 && minute <= 9) {
          minute = "0" + minute;
      };
      if(second >= 0 && second <= 9) {
          second = "0" + second;
      };
      var myTime = myDate.getFullYear() + sep1 + month + sep1 + day
            + " " + hour + sep2 + minute + sep2 + second;
      //console.log(myTime);
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==0 || rowIndex==1){
            return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF'};
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
    getTableData(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo32',
          method:'post',
          params:{
            areaType:this.areas,
            county:this.citys,
            year:this.years
           }
       }).then(res=>{
          //_this.tableData=res.data;
          ////console.log(_this.tableData);
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total1 =[];//存各面积和;无占比
            var total2 =[];//有占比
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_6 === ''){
                totalTableData[i].field_6 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_2_1*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_12_2_1*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_12_4*1);
            };
            //console.log(num1,num2,num6,num14);
            //各品种面积总和
            //无占比
            total1[0]=num2;total1[1]=num7;total1[2]=num8;total1[3]=num10;total1[4]=num11;total1[5]=num13;total1[6]=num14;total1[7]=num15;total1[8]=num16;
            total2[0]=num3;total2[1]=num4;total2[2]=num5;total2[3]=num6;total2[4]=num9;total2[5]=num12;//有占比
            //求占比          
            for(var i=0; i<6; i++){
              if(num1 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(total2[i] === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(i===1 || i===2){
                sums[i] = (Number(total2[i])/Number(num1)*100).toFixed(2)+"%";////总面积占比
              }else if(num2 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total2[i])/Number(num2)*100).toFixed(2)+"%";//秧田面积占比
              };
            };
            //求小计和
            var totalNum=[];
            var totalPer=[];
            totalNum[0] = (Number(total1[1])+Number(total1[2])).toFixed(2);
            totalNum[1] = (Number(total1[3])+Number(total1[4])).toFixed(2);
            totalNum[2]= (Number(total1[5])+Number(total1[6])).toFixed(2);
            for(var i=0; i<4; i++){
              if(num1 === 0){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else if(totalNum[i] === '0.00'){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else{
                totalPer[i] = (Number(totalNum[i])/Number(num1)*100).toFixed(2)+"%";
              }
            }
            //处理无占比数据为0时情况
            for(var i=0; i<9; i++){
              if(total1[i] === 0){
                total1[i] ='--'
              }
            }     
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,"field_5_1":'--',"field_5_2":'--',"field_6":total1[0],
                      "field_7_1":total2[0],"field_7_2":sums[0],"field_8_1":total2[1],"field_8_2":sums[1],"field_9_1":total2[2],"field_9_2":sums[2],
                      "field_10_1":'--',"field_10_2_1":total2[3],"field_10_2_2":sums[3],"field_10_3":total1[1],"field_10_4":total1[2],"field_10_5":totalNum[0],"field_10_6":totalPer[0],
                      "field_11_1":'--',"field_11_2_1":total2[4],"field_11_2_2":sums[4],"field_11_3":total1[3],"field_11_4":total1[4],"field_11_5":totalNum[1],"field_11_6":totalPer[1],
                      "field_12_1":'--',"field_12_2_1":total2[5],"field_12_2_2":sums[5],"field_12_3":total1[5],"field_12_4":total1[6],"field_12_5":totalNum[2],"field_12_6":totalPer[2],
                      "in_use":'--'})
            _this.dataNum = totalTableData.length-1;
          }else{
            _this.dataNum = 0;
          };
          _this.tableData = totalTableData;

          //console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty2',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          //地区数据
          var areaData = [];
          //稻作数据
          var riceData = [];
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "地区类型"){
                areaData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "稻作类型"){
                riceData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          // //console.log(countyData);
          // //console.log(areaData);
          // //console.log(riceData);
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;

       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //重置
    reSet(){
      this.areaType='';
      this.county='';
      this.riceType='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      const _this = this;
      axios({
        url:'/getSeekInfoTwo32',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                year:this.years,
        }
      }).then(res=>{
        var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total1 =[];//存各面积和;无占比
            var total2 =[];//有占比
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_6 === ''){
                totalTableData[i].field_6 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_11_2_1*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_12_2_1*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_12_4*1);
            };
            //console.log(num1,num2,num6,num14);
            //各品种面积总和
            //无占比
            total1[0]=num2;total1[1]=num7;total1[2]=num8;total1[3]=num10;total1[4]=num11;total1[5]=num13;total1[6]=num14;total1[7]=num15;total1[8]=num16;
            total2[0]=num3;total2[1]=num4;total2[2]=num5;total2[3]=num6;total2[4]=num9;total2[5]=num12;//有占比
            //求占比          
            for(var i=0; i<6; i++){
              if(num1 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(total2[i] === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else if(i===1 || i===2){
                sums[i] = (Number(total2[i])/Number(num1)*100).toFixed(2)+"%";////总面积占比
              }else if(num2 === 0){
                total2[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total2[i])/Number(num2)*100).toFixed(2)+"%";//秧田面积占比
              };
            };
            //求小计和
            var totalNum=[];
            var totalPer=[];
            totalNum[0] = (Number(total1[1])+Number(total1[2])).toFixed(2);
            totalNum[1] = (Number(total1[3])+Number(total1[4])).toFixed(2);
            totalNum[2]= (Number(total1[5])+Number(total1[6])).toFixed(2);
            for(var i=0; i<4; i++){
              if(num1 === 0){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else if(totalNum[i] === '0.00'){
                totalNum[i] = '--';
                totalPer[i] = '--';
              }else{
                totalPer[i] = (Number(totalNum[i])/Number(num1)*100).toFixed(2)+"%";
              }
            }
            //处理无占比数据为0时情况
            for(var i=0; i<9; i++){
              if(total1[i] === 0){
                total1[i] ='--'
              }
            }     
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,"field_5_1":'--',"field_5_2":'--',"field_6":total1[0],
                      "field_7_1":total2[0],"field_7_2":sums[0],"field_8_1":total2[1],"field_8_2":sums[1],"field_9_1":total2[2],"field_9_2":sums[2],
                      "field_10_1":'--',"field_10_2_1":total2[3],"field_10_2_2":sums[3],"field_10_3":total1[1],"field_10_4":total1[2],"field_10_5":totalNum[0],"field_10_6":totalPer[0],
                      "field_11_1":'--',"field_11_2_1":total2[4],"field_11_2_2":sums[4],"field_11_3":total1[3],"field_11_4":total1[4],"field_11_5":totalNum[1],"field_11_6":totalPer[1],
                      "field_12_1":'--',"field_12_2_1":total2[5],"field_12_2_2":sums[5],"field_12_3":total1[5],"field_12_4":total1[6],"field_12_5":totalNum[2],"field_12_6":totalPer[2],
                      "in_use":'--'})
            _this.dataNum = totalTableData.length-1;
          }else{
            _this.dataNum = 0;
          };
          _this.tableData = totalTableData;

       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
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
    //导出excel
    // 导出Excel点击方法
    handleDownload() {
      // this.downloadLoading = true // 加载遮罩层开启
      //console.log("11111");
      const {export_json_to_excel} = require('../../excel/Export2Excel2'); //引入文件
      //console.log("2222")
      const th1 = [[this.tableLabel, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ['县（市、区）', '地区类型', '稻作类型','全县总面积（万亩）', '开始播种日期', '', '秧田面积（万亩）', '', '', '折合大田面积（万亩）', '', '', '', '播栽期进度1', '', '', '', '', '', '', '播栽期进度2', '', '', '', '', '', '', '播栽期进度3', '', '', '', '', '', '', ]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '育秧', '直播', '', '其中集中育秧', '', '', '', '其中集中育秧', '', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比', '日期', '育秧已播种面积（万亩）', '', '已栽插面积（万亩）', '直播已播种面积（万亩）', '小计', '占比']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2', 'field_6', 'field_7_1', 'field_7_2', 'field_8_1', 'field_8_2', 'field_9_1', 'field_9_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3', 'field_10_4', 'field_10_5', 'field_10_6', 'field_11_1','field_11_2_1', 'field_11_2_2', 'field_11_3', 'field_11_4', 'field_11_5', 'field_11_6', 'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3', 'field_12_4', 'field_12_5', 'field_12_6'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AH1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:F2",
        "G2:I2",
        "J2:M2",
        "N2:T2",
        "U2:AA2",
        "AB2:AH2",
        "H3:I3",
        "J3:K3",
        "L3:M3",
        "O3:P3",
        "V3:W3",
        "AC3:AD3",
      ];
      const list = this.tableData;
      //console.log(list);
      const data = this.formatJson(filterVal, list);
      //console.log(data);
      export_json_to_excel(th1, th2, header, data, this.tableLabel, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    // 将JSON转换成map
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    //返回
    returnPage(){
      var diqu = this.areas;
      var xian = this.citys;
      var nian = this.years;
      //console.log(diqu,xian,nian)
      if(xian ==''){
        this.$router.push({path:'/dataHome',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='宣州区') || (xian=='庐江县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='怀宁县') || (xian=='枞阳县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else if((xian=='贵池区')|| (xian=='南陵县')){
        this.$router.push({path:'/dataHome2',query:{area:diqu,city:xian,year:nian}});
      }else{
        this.$router.push({path:'/dataHome1',query:{area:diqu,city:xian,year:nian}});
      };
    },
    //退回
    backRevision(index,row){
      this.recordData=row;
      this.dialogVisible=true;
    },
    confirmBack(){
      const _this=this;
      this.visibleBack=false;
      this.dialogVisible=false;
      this.getMyTime();
      var recordTime = this.systemTime;
      var returnTableName = this.tableName;
      var recordCounty=this.recordData.field_1;
      var recordId = this.recordData.id;
      var recordPeople = this.recordData.people;
      var recordReason = this.reason1;
      //将原因显示置空
      this.reason1='';
      ////console.log(recordTime,recordId,returnTableName,recordPeople,recordCounty,recordReason);
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId,
                recordReason:recordReason,
                recordTime:recordTime,
                recordPeople:recordPeople
                }
      }).then(res=>{
        _this.getTableData();
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
  }
};
</script>

<style scoped>
  
  .totalRange{
    width:100%;
    height: 100%;
  }
  .button{
    position: absolute;right: 60px;top: 105px;
    color:black;
    font-weight: 600;
    font-size: 14px;
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



