<template>
        <div class="totalRange">
          <el-tabs v-model="activeName">
            <el-tab-pane label="栽植方式1" name="1">
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
                    <!-- <el-button  type="info" plain @click="getDataAnalyse">数据分析</el-button> -->
                    <el-button  type="info" plain @click="downloadAndPrint">导出</el-button>
                  </el-col>
                  </el-row>
                </div>
                <div class="tableStyle">
                  <el-table id="tableExecl" :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'4px'}" :row-style="{height: '30px'}"  row-key="id" stripe>
                    <el-table-column  :label="tableLabel" align="center">
                      <el-table-column align="center" fixed type="index" width="64px" :index="countMethod" label="序号" />
                      <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                      <el-table-column align="center" prop="field_2" width="100%" label="全县总面积（万亩）" />
                      <el-table-column align="center" prop="field_3"  label="地区类型" />
                      <el-table-column align="center" prop="field_4"  label="稻作类型" />
                      <el-table-column align="center"   label="栽植方式（万亩）">
                        <el-table-column align="center" label="人工移栽">
                          <el-table-column align="center" prop="field_5_1_1"  label="" />
                          <el-table-column align="center" prop="field_5_1_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="机械插秧">
                          <el-table-column align="center" prop="field_5_2_1"  label="" />
                          <el-table-column align="center" prop="field_5_2_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="盘育抛栽">
                          <el-table-column align="center" prop="field_5_3_1"  label="" />
                          <el-table-column align="center" prop="field_5_3_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="无盘旱育抛栽">
                          <el-table-column align="center" prop="field_5_4_1"  label="" />
                          <el-table-column align="center" prop="field_5_4_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="撒直播（旱）">
                          <el-table-column align="center" prop="field_5_5_1"  label="" />
                          <el-table-column align="center" prop="field_5_5_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="撒直播（水）">
                          <el-table-column align="center" prop="field_5_6_1"  label="" />
                          <el-table-column align="center" prop="field_5_6_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="机条播（旱）">
                          <el-table-column align="center" prop="field_5_7_1"  label="" />
                          <el-table-column align="center" prop="field_5_7_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="机条播（水）">
                          <el-table-column align="center" prop="field_5_8_1"  label="" />
                          <el-table-column align="center" prop="field_5_8_2"  label="" />
                        </el-table-column>
                        <el-table-column align="center" label="小计">
                          <el-table-column align="center" prop="field_5_9_1"  label="" />
                          <el-table-column align="center" prop="field_5_9_2"  label="" />
                        </el-table-column>
                      </el-table-column>
                      <el-table-column align="center"  label="前茬口类型（万亩）">
                          <el-table-column align="center" label="水稻">
                            <el-table-column align="center" prop="field_6_1_1"  label="" />
                            <el-table-column align="center" prop="field_6_1_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="小麦">
                            <el-table-column align="center" prop="field_6_2_1"  label="" />
                            <el-table-column align="center" prop="field_6_2_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="油菜">
                            <el-table-column align="center" prop="field_6_3_1"  label="" />
                            <el-table-column align="center" prop="field_6_3_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="空闲田">
                            <el-table-column align="center" prop="field_6_4_1"  label="" />
                            <el-table-column align="center" prop="field_6_4_2" width="82%" label="" />
                          </el-table-column>
                            <el-table-column align="center" label="绿肥">
                            <el-table-column align="center" prop="field_6_5_1"  label="" />
                            <el-table-column align="center" prop="field_6_5_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="蔬菜等经作">
                            <el-table-column align="center" prop="field_6_6_1"  label="" />
                            <el-table-column align="center" prop="field_6_6_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="其他">
                            <el-table-column align="center" prop="field_6_7_1"  label="" />
                            <el-table-column align="center" prop="field_6_7_2" width="82%" label="" />
                          </el-table-column>
                          <el-table-column align="center" label="小计">
                            <el-table-column align="center" prop="field_6_8_1"  label="" />
                            <el-table-column align="center" prop="field_6_8_2" width="82%" label="" />
                          </el-table-column>
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
            </el-tab-pane>
          </el-tabs>
          <div class="button">
            <el-button type="success" @click="returnPage">返  回</el-button>
          </div>
        </div>
</template>


<script>
import axios from 'axios';
import * as math from 'mathjs';
import {saveAs} from "file-saver";
import XLSX from "xlsx";
export default {
  inject: ['reload'],
  data() {
    return {    
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产栽插概况调查表',
      tableName:'crop_01_01_table_01_01',
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
      //暂存参数
      areas:'',
      citys:'',
      years:'',
    };
  },
  created() {
    this.initData();

  },
  methods: {
    //初始化数据
    initData() {
      this.areas = this.$route.query.area;
      this.citys = this.$route.query.city;
      this.years = this.$route.query.year;
      //console.log(this.areas,this.citys,this.years);
      //this.getHeaderList();
      this.getTableData();
      this.getCountySelection();
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
            return{height:'0',padding:'1px',fontSize:'5px',background:'#CDBE70',color:'green'};
        };
           
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
          url: '/getRiceTable11',
          method:'post',
          params:{
            county:this.citys,
            areaType:this.areas,
            year:this.years
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_5_1_1 === ''){
                totalTableData[i].field_5_1_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_1_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_5_5_1 === ''){
                totalTableData[i].field_5_5_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_5_5_1*1);

              if(totalTableData[i].field_5_6_1 === ''){
                totalTableData[i].field_5_6_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_5_6_1*1);

              if(totalTableData[i].field_5_7_1 === ''){
                totalTableData[i].field_5_7_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_5_7_1*1);

              if(totalTableData[i].field_5_8_1 === ''){
                totalTableData[i].field_5_8_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_5_8_1*1);

              if(totalTableData[i].field_6_1_1 === ''){
                totalTableData[i].field_6_1_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_1_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_6_5_1 === ''){
                totalTableData[i].field_6_5_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_6_5_1*1);

              if(totalTableData[i].field_6_6_1 === ''){
                totalTableData[i].field_6_6_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_6_6_1*1);

              if(totalTableData[i].field_6_7_1 === ''){
                totalTableData[i].field_6_7_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_6_7_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;
            //求占比
            var totalNum1=0;//小计
            var totalNum2=0;//小计
            for(var i=0; i<8; i++){
              totalNum1 = total[i] + totalNum1;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            for(var i=8; i<total.length; i++){
              totalNum2 = total[i] + totalNum2;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //console.log(total.length,sums.length)
            //小计
            if(totalNum1 === num1){
              total[15] = '核对正确！';
              sums[15] = '100%';
            }else{
              total[15] = '错误！';
              sums[15] = ((totalNum1/num1)*100).toFixed(2)+'%';
            };
            if(totalNum2 === num1){
              total[16] = '核对正确！';
              sums[16] = '100%';
            }else{
              total[16] = '错误！';
              sums[16] = ((totalNum2/num1)*100).toFixed(2)+'%';
            };
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":num1,"field_3":'--',"field_4":'--',"field_5_1_1":total[0],"field_5_1_2":sums[0],
                      "field_5_2_1":total[1],"field_5_2_2":sums[1],"field_5_3_1":total[2],"field_5_3_2":sums[2],
                      "field_5_4_1":total[3],"field_5_4_2":sums[3],"field_5_5_1":total[4],"field_5_5_2":sums[4],
                      "field_5_6_1":total[5],"field_5_6_2":sums[5],"field_5_7_1":total[6],"field_5_7_2":sums[6],
                      "field_5_8_1":total[7],"field_5_8_2":sums[7],"field_5_9_1":totalNum1,"field_5_9_2":'100.00%',"field_6_1_1":total[8],"field_6_1_2":sums[8],
                      "field_6_2_1":total[9],"field_6_2_2":sums[9],"field_6_3_1":total[10],"field_6_3_2":sums[10],
                      "field_6_4_1":total[11],"field_6_4_2":sums[11],"field_6_5_1":total[12],"field_6_5_2":sums[12],
                      "field_6_6_1":total[13],"field_6_6_2":sums[13],"field_6_7_1":total[14],"field_6_7_2":sums[14],
                      "field_6_8_1":totalNum2,"field_6_8_2":'100.00%',"in_use":'--'})
            _this.dataNum = totalTableData.length-1;
          }else{
            _this.dataNum = 0;
          };
            _this.tableData = totalTableData;
            //console.log(_this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty1',
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
         return this.$message.error("查询数据失败！");
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
        url:'/getSeekInfo11',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                year:this.years,
        }
      }).then(res=>{
         _this.tableData=res.data;
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2 === ''){
                totalTableData[i].field_2 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2*1);

              if(totalTableData[i].field_5_1_1 === ''){
                totalTableData[i].field_5_1_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_1_1*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_5_5_1 === ''){
                totalTableData[i].field_5_5_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_5_5_1*1);

              if(totalTableData[i].field_5_6_1 === ''){
                totalTableData[i].field_5_6_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_5_6_1*1);

              if(totalTableData[i].field_5_7_1 === ''){
                totalTableData[i].field_5_7_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_5_7_1*1);

              if(totalTableData[i].field_5_8_1 === ''){
                totalTableData[i].field_5_8_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_5_8_1*1);

              if(totalTableData[i].field_6_1_1 === ''){
                totalTableData[i].field_6_1_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_1_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_6_5_1 === ''){
                totalTableData[i].field_6_5_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_6_5_1*1);

              if(totalTableData[i].field_6_6_1 === ''){
                totalTableData[i].field_6_6_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_6_6_1*1);

              if(totalTableData[i].field_6_7_1 === ''){
                totalTableData[i].field_6_7_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_6_7_1*1);
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;
            //求占比
            var totalNum1=0;//小计
            var totalNum2=0;//小计
            for(var i=0; i<8; i++){
              totalNum1 = total[i] + totalNum1;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            for(var i=8; i<total.length; i++){
              totalNum2 = total[i] + totalNum2;
              if(num1 === 0){
                total[i] = '--';
                sums[i] = '--';
              }else if(total[i] === 0){
                total[i] = '--';
                sums[i] = '--';
              }else{
                sums[i] = (Number(total[i])/Number(num1)*100).toFixed(2)+"%";
              };
            };
            //console.log(total.length,sums.length)
            //小计
            if(totalNum1 === num1){
              total[15] = '核对正确！';
              sums[15] = '100%';
            }else{
              total[15] = '错误！';
              sums[15] = ((totalNum1/num1)*100).toFixed(2)+'%';
            };
            if(totalNum2 === num1){
              total[16] = '核对正确！';
              sums[16] = '100%';
            }else{
              total[16] = '错误！';
              sums[16] = ((totalNum2/num1)*100).toFixed(2)+'%';
            };
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":num1,"field_3":'--',"field_4":'--',"field_5_1_1":total[0],"field_5_1_2":sums[0],
                      "field_5_2_1":total[1],"field_5_2_2":sums[1],"field_5_3_1":total[2],"field_5_3_2":sums[2],
                      "field_5_4_1":total[3],"field_5_4_2":sums[3],"field_5_5_1":total[4],"field_5_5_2":sums[4],
                      "field_5_6_1":total[5],"field_5_6_2":sums[5],"field_5_7_1":total[6],"field_5_7_2":sums[6],
                      "field_5_8_1":total[7],"field_5_8_2":sums[7],"field_5_9_1":totalNum1,"field_5_9_2":'100.00%',"field_6_1_1":total[8],"field_6_1_2":sums[8],
                      "field_6_2_1":total[9],"field_6_2_2":sums[9],"field_6_3_1":total[10],"field_6_3_2":sums[10],
                      "field_6_4_1":total[11],"field_6_4_2":sums[11],"field_6_5_1":total[12],"field_6_5_2":sums[12],
                      "field_6_6_1":total[13],"field_6_6_2":sums[13],"field_6_7_1":total[14],"field_6_7_2":sums[14],
                      "field_6_8_1":totalNum2,"field_6_8_2":'100.00%',"in_use":'--'})
            _this.dataNum = totalTableData.length-1;
          }else{
            _this.dataNum = 0;
          }
            _this.tableData = totalTableData;
            //console.log(_this.tableData);
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

  },
};
</script>

<style scoped>
  
  .totalRange{
    margin-top: 0px;
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



