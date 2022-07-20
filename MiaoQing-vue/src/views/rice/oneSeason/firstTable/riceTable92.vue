<template>
  
    <div class="totalRange">
          <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <el-col :span="3">
                  <template>                 
                    <el-select v-model="county" clearable  style="width:150px; margin-left: 20px;" placeholder="县（市、区）">
                      <el-option
                        v-for="item in countys"
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

              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <!-- <el-table-column :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                  <el-table-column align="center" width="100%" label="县（市、区）" >
                    <el-table-column align="center" width="100%" prop="field_1_1" label="" />
                    <el-table-column align="center" width="100%" prop="field_1_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="抽穗期前15天">
                    <el-table-column align="center" prop="field_2_1"  label="日平均气温（℃）" />
                    <el-table-column align="center" prop="field_2_2"  label="日最高气温（℃）" />
                    <el-table-column align="center" prop="field_2_3"  label="降雨量（mm）" />
                    <el-table-column align="center" prop="field_2_4"  label="日照时数（h）" />
                    <el-table-column align="center" prop="field_2_5"  label="晴好天气天数（天）" />
                    <el-table-column align="center" prop="field_2_6"  label="阴雨天气天数（天）" />
                  </el-table-column>
                  <el-table-column align="center" label="抽穗期后15天">
                    <el-table-column align="center" prop="field_3_1"  label="日平均气温（℃）" />
                    <el-table-column align="center" prop="field_3_2"  label="日最高气温（℃）" />
                    <el-table-column align="center" prop="field_3_3"  label="降雨量（mm）" />
                    <el-table-column align="center" prop="field_3_4"  label="日照时数（h）" />
                    <el-table-column align="center" prop="field_3_5"  label="晴好天气天数（天）" />
                    <el-table-column align="center" prop="field_3_6"  label="阴雨天气天数（天）" />
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
      tableName:'crop_01_01_table_09_02',
      //数据有误输入原因
      reasonForm: {reason:''},
      //县的option
      countys:[],
      county: '',
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
      //年度
      currentYear:'',   //本年度
      previousYear:'',   //上年度 
      //暂存参数
      areas:'',
      citys:'',
      years:'',
      //本年度县
      currentCounty:[],
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
      this.currentYear = this.$route.query.year;
      this.previousYear = parseInt(this.$route.query.year) - 1;
      this.tableLabel= this.years+"年度监测县(市、区)抽穗期前、后各15天气象资料表";
      // this.getCurrentCounty();
      this.getCountySelection();
      //this.getYear();
      this.getTableData();
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
    }, 
    //获取年度时间
    getYear(){
      var myDate = new Date();
      this.currentYear = myDate.getFullYear();
      this.previousYear = parseInt(this.currentYear) - 1;
     
    },

    //获取系统时间
    getMyTime(){
      var myDate = new Date();
      ////console.log(myDate);
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
      ////console.log(myTime);
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==1 || rowIndex === 0){
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
    //获取下拉框内容
    getCountySelection(){
      const _this = this;
      axios({
          url: '/getCounty1',
          method:'get',
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          //县数据
          var countyData = [];
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys = countyData;
          //_this.getTableData();
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取当年具体县数据
    getCurrentCounty(){
      const _this = this;
      axios({
          url: '/getCurrentCounty',
          method:'post',
          params:{county:this.citys,
                  year:this.years}
       }).then(res=>{
         console.log(res.data);
          _this.currentCounty = res.data;
          
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //获取表数据
    getTableData(){ 
       this.getCurrentCounty();
       const _this = this;
       //console.log("时间："+this.currentYear,this.previousYear,this.years);
       axios({
          url: '/getRiceTable92',
          method:'post',
          params:{currentYear:this.currentYear,
                  previousYear:this.previousYear,
                  county:this.citys,
                  areaType:this.areas,
                  year:this.years}
       }).then(res=>{
         var tempData=res.data;
         console.log(res.data);
         var year = _this.currentYear;
         //处理合计
         if(tempData.length != 0){
           var total = [];
           var mean1 = [];
           var mean2 = [];
           var mean3 = [];
           var sumTemp=[];
           var dataLength1=0;
           var dataLength2=0;
           var yearTemp=tempData;
           var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
           var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
           var num21=0;var num22=0;var num23=0;var num24=0;
           for(var i=0; i<yearTemp.length; i++){
              if(yearTemp[i].field_1_2 == year){
                 dataLength1++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num1 = _this.add(num1*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num2 = _this.add(num2*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num3 = _this.add(num3*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num4 = _this.add(num4*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num5 = _this.add(num5*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num6 = _this.add(num6*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num7 = _this.add(num7*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num8 = _this.add(num8*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num9 = _this.add(num9*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num10 = _this.add(num10*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num11 = _this.add(num11*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num12 = _this.add(num12*1+yearTemp[i].field_3_6*1);
              }else{
                dataLength2++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num13 = _this.add(num13*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num14 = _this.add(num14*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num15 = _this.add(num15*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num16 = _this.add(num16*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num17 = _this.add(num17*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num18 = _this.add(num18*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num19 = _this.add(num19*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num20 = _this.add(num20*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num21 = _this.add(num21*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num22 = _this.add(num22*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num23 = _this.add(num23*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num24 = _this.add(num24*1+yearTemp[i].field_3_6*1);
              };
           };
           total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
           total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;total[19]=num20;
           total[20]=num21;total[21]=num22;total[22]=num23;total[23]=num24;
           ////console.log(total);
           for(var i=0; i<12; i++){
             mean1[i] = (Number(total[i])/Number(dataLength1)).toFixed(2);
           };
           for(var i=12; i<24; i++){
             mean2[i-12] = (Number(total[i])/Number(dataLength2)).toFixed(2);
           };
           for(var i=0; i<12; i++){
             mean3[i] = (_this.add(mean1[i]*1-mean2[i]*1)).toFixed(2);
           }
           sumTemp.push({"field_1_1":"合计","field_1_2":"本年度",
                        "field_2_1":mean1[0],"field_2_2":mean1[1],"field_2_3":mean1[2],"field_2_4":mean1[3],"field_2_5":mean1[4],"field_2_6":mean1[5],
                        "field_3_1":mean1[6],"field_3_2":mean1[7],"field_3_3":mean1[8],"field_3_4":mean1[9],"field_3_5":mean1[10],"field_3_6":mean1[11],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":"上年度",
                        "field_2_1":mean2[0],"field_2_2":mean2[1],"field_2_3":mean2[2],"field_2_4":mean2[3],"field_2_5":mean2[4],"field_2_6":mean2[5],
                        "field_3_1":mean2[6],"field_3_2":mean2[7],"field_3_3":mean2[8],"field_3_4":mean2[9],"field_3_5":mean2[10],"field_3_6":mean2[11],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":'较去年增减',
                        "field_2_1":mean3[0],"field_2_2":mean3[1],"field_2_3":mean3[2],"field_2_4":mean3[3],"field_2_5":mean3[4],"field_2_6":mean3[5],
                        "field_3_1":mean3[6],"field_3_2":mean3[7],"field_3_3":mean3[8],"field_3_4":mean3[9],"field_3_5":mean3[10],"field_3_6":mean3[11],
                        "in_use":'--'});
          ////console.log(sumTemp);
         };
         //数据计算处理
        //  console.log(_this.currentCounty)
         if(tempData.length != 0){
            //console.log(tempData);
            var secondTemp=[];
            for(var i=0; i<_this.currentCounty.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(_this.currentCounty[i] === tempData[j].field_1_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            //secondTemp.push(sumTemp);
            console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp.length === 2){
                  var county=thirdTemp[0].field_1_1;
                  var yearTemp=[];
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;
                  for(var i=0; i<2; i++){     //处理显示本年度和上年度位置信息
                      //console.log(thirdTemp[i].field_1_2);
                    if(thirdTemp[i].field_1_2 == year){ 
                        thirdTemp[i].field_1_2="本年度";
                        yearTemp[0]=thirdTemp[i];
                    }else{
                        thirdTemp[i].field_1_2="上年度";
                        yearTemp[1]=thirdTemp[i];
                    };
                  };
                  //console.log(yearTemp);
                  for(var i=0; i<2; i++){
                    if(yearTemp[i].field_2_1 === ''){
                       yearTemp[i].field_2_1=0;
                    };
                    if(yearTemp[i].field_2_2 === ''){
                       yearTemp[i].field_2_2=0;
                    };
                    if(yearTemp[i].field_2_3 === ''){
                       yearTemp[i].field_2_3=0;
                    };
                    if(yearTemp[i].field_2_4 === ''){
                       yearTemp[i].field_2_4=0;
                    };
                    if(yearTemp[i].field_2_5 === ''){
                       yearTemp[i].field_2_5=0;
                    };
                    if(yearTemp[i].field_2_6 === ''){
                       yearTemp[i].field_2_6=0;
                    };
                    if(yearTemp[i].field_3_1 === ''){
                       yearTemp[i].field_3_1=0;
                    };
                    if(yearTemp[i].field_3_2 === ''){
                       yearTemp[i].field_3_2=0;
                    };
                    if(yearTemp[i].field_3_3 === ''){
                       yearTemp[i].field_3_3=0;
                    };
                    if(yearTemp[i].field_3_4 === ''){
                       yearTemp[i].field_3_4=0;
                    };
                    if(yearTemp[i].field_3_5 === ''){
                       yearTemp[i].field_3_5=0;
                    };
                    if(yearTemp[i].field_3_6 === ''){
                       yearTemp[i].field_3_6=0;
                    };
                  }
                  num1 = _this.add(yearTemp[0].field_2_1*1 - yearTemp[1].field_2_1*1);
                  num2 = _this.add(yearTemp[0].field_2_2*1 - yearTemp[1].field_2_2*1);
                  num3 = _this.add(yearTemp[0].field_2_3*1 - yearTemp[1].field_2_3*1);
                  num4 = _this.add(yearTemp[0].field_2_4*1 - yearTemp[1].field_2_4*1);
                  num5 = _this.add(yearTemp[0].field_2_5*1 - yearTemp[1].field_2_5*1);
                  num6 = _this.add(yearTemp[0].field_2_6*1 - yearTemp[1].field_2_6*1);
                  num7 = _this.add(yearTemp[0].field_3_1*1 - yearTemp[1].field_3_1*1);
                  num8 = _this.add(yearTemp[0].field_3_2*1 - yearTemp[1].field_3_2*1);
                  num9 = _this.add(yearTemp[0].field_3_3*1 - yearTemp[1].field_3_3*1);
                  num10 = _this.add(yearTemp[0].field_3_4*1 - yearTemp[1].field_3_4*1);
                  num11 = _this.add(yearTemp[0].field_3_5*1 - yearTemp[1].field_3_5*1);
                  num12 = _this.add(yearTemp[0].field_3_6*1 - yearTemp[1].field_3_6*1);

                  yearTemp.push({"field_1_1":county,"field_1_2":'较去年增减',
                                  "field_2_1":num1,"field_2_2":num2,"field_2_3":num3,"field_2_4":num4,"field_2_5":num5,"field_2_6":num6,
                                  "field_3_1":num7,"field_3_2":num8,"field_3_3":num9,"field_3_4":num10,"field_3_5":num11,"field_3_6":num12,
                                  "in_use":'--'}); 
                  fourTemp = fourTemp.concat(yearTemp);
                  
              }
            };
            if(fourTemp.length != 0){
              fourTemp = fourTemp.concat(sumTemp);
            }
            tempData=fourTemp;
            
            _this.dataNum = tempData.length;
         }else{
           _this.dataNum = 0;
         };
          //console.log(fourTemp);
          _this.tableData = tempData;
          console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    
    //重置
    reSet(){
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){ 
      const _this = this;
      //console.log(this.currentYear,this.previousYear)
      axios({
        url:'/getSeekInfo92',
        method:'post',
        params:{county:this.county,
                currentYear:this.currentYear,
                previousYear:this.previousYear,
                year:this.years,}
      }).then(res=>{
         var tempData=res.data;
         //console.log(tempData);
         var year = _this.currentYear;
         //console.log(year);
         //处理合计
         if(tempData.length != 0){
           var total = [];
           var mean1 = [];
           var mean2 = [];
           var mean3 = [];
           var sumTemp=[];
           var dataLength1=0;
           var dataLength2=0;
           var yearTemp=tempData;
           var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
           var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
           var num21=0;var num22=0;var num23=0;var num24=0;
           for(var i=0; i<yearTemp.length; i++){
              if(yearTemp[i].field_1_2 == year){
                 dataLength1++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num1 = _this.add(num1*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num2 = _this.add(num2*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num3 = _this.add(num3*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num4 = _this.add(num4*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num5 = _this.add(num5*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num6 = _this.add(num6*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num7 = _this.add(num7*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num8 = _this.add(num8*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num9 = _this.add(num9*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num10 = _this.add(num10*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num11 = _this.add(num11*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num12 = _this.add(num12*1+yearTemp[i].field_3_6*1);
              }else{
                dataLength2++;
                if(yearTemp[i].field_2_1 === ''){
                    yearTemp[i].field_2_1=0;
                };
                num13 = _this.add(num13*1+yearTemp[i].field_2_1*1);
                if(yearTemp[i].field_2_2 === ''){
                    yearTemp[i].field_2_2=0;
                };
                num14 = _this.add(num14*1+yearTemp[i].field_2_2*1);
                if(yearTemp[i].field_2_3 === ''){
                    yearTemp[i].field_2_3=0;
                };
                num15 = _this.add(num15*1+yearTemp[i].field_2_3*1);
                if(yearTemp[i].field_2_4 === ''){
                    yearTemp[i].field_2_4=0;
                };
                num16 = _this.add(num16*1+yearTemp[i].field_2_4*1);
                if(yearTemp[i].field_2_5 === ''){
                    yearTemp[i].field_2_5=0;
                };
                num17 = _this.add(num17*1+yearTemp[i].field_2_5*1);
                if(yearTemp[i].field_2_6 === ''){
                    yearTemp[i].field_2_6=0;
                };
                num18 = _this.add(num18*1+yearTemp[i].field_2_6*1);
                if(yearTemp[i].field_3_1 === ''){
                    yearTemp[i].field_3_1=0;
                };
                num19 = _this.add(num19*1+yearTemp[i].field_3_1*1);
                if(yearTemp[i].field_3_2 === ''){
                    yearTemp[i].field_3_2=0;
                };
                num20 = _this.add(num20*1+yearTemp[i].field_3_2*1);
                if(yearTemp[i].field_3_3 === ''){
                    yearTemp[i].field_3_3=0;
                };
                num21 = _this.add(num21*1+yearTemp[i].field_3_3*1);
                if(yearTemp[i].field_3_4 === ''){
                    yearTemp[i].field_3_4=0;
                };
                num22 = _this.add(num22*1+yearTemp[i].field_3_4*1);
                if(yearTemp[i].field_3_5 === ''){
                    yearTemp[i].field_3_5=0;
                };
                num23 = _this.add(num23*1+yearTemp[i].field_3_5*1);
                if(yearTemp[i].field_3_6 === ''){
                    yearTemp[i].field_3_6=0;
                };
                num24 = _this.add(num24*1+yearTemp[i].field_3_6*1);
              };
           };
           total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
           total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;total[19]=num20;
           total[20]=num21;total[21]=num22;total[22]=num23;total[23]=num24;
           for(var i=0; i<12; i++){
             mean1[i] = (Number(total[i])/Number(dataLength1)).toFixed(2);
           };
           for(var i=12; i<24; i++){
             mean2[i-12] = (Number(total[i])/Number(dataLength2)).toFixed(2);
           };
           for(var i=0; i<12; i++){
             mean3[i] = (_this.add(mean1[i]*1-mean2[i]*1)).toFixed(2);
           }
           sumTemp.push({"field_1_1":"合计","field_1_2":"本年度",
                        "field_2_1":mean1[0],"field_2_2":mean1[1],"field_2_3":mean1[2],"field_2_4":mean1[3],"field_2_5":mean1[4],"field_2_6":mean1[5],
                        "field_3_1":mean1[6],"field_3_2":mean1[7],"field_3_3":mean1[8],"field_3_4":mean1[9],"field_3_5":mean1[10],"field_3_6":mean1[11],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":"上年度",
                        "field_2_1":mean2[0],"field_2_2":mean2[1],"field_2_3":mean2[2],"field_2_4":mean2[3],"field_2_5":mean2[4],"field_2_6":mean2[5],
                        "field_3_1":mean2[6],"field_3_2":mean2[7],"field_3_3":mean2[8],"field_3_4":mean2[9],"field_3_5":mean2[10],"field_3_6":mean2[11],
                        "in_use":'--'},
                        {"field_1_1":"合计","field_1_2":'较去年增减',
                        "field_2_1":mean3[0],"field_2_2":mean3[1],"field_2_3":mean3[2],"field_2_4":mean3[3],"field_2_5":mean3[4],"field_2_6":mean3[5],
                        "field_3_1":mean3[6],"field_3_2":mean3[7],"field_3_3":mean3[8],"field_3_4":mean3[9],"field_3_5":mean3[10],"field_3_6":mean3[11],
                        "in_use":'--'});
          //console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            var secondTemp=[];
            secondTemp.push(tempData);
            //secondTemp.push(sumTemp);
            //console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp.length ===2 ){
                  var county=thirdTemp[0].field_1_1;
                  var yearTemp=[];
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;
                  for(var i=0; i<2; i++){     //处理显示本年度和上年度位置信息
                      //console.log(thirdTemp[i].field_1_2);
                    if(thirdTemp[i].field_1_2 == year){ 
                        thirdTemp[i].field_1_2="本年度";
                        yearTemp[0]=thirdTemp[i];
                    }else{
                        thirdTemp[i].field_1_2="上年度";
                        yearTemp[1]=thirdTemp[i];
                    };
                  };
                  //console.log(yearTemp);
                  for(var i=0; i<2; i++){
                    if(yearTemp[i].field_2_1 === ''){
                       yearTemp[i].field_2_1=0;
                    };
                    if(yearTemp[i].field_2_2 === ''){
                       yearTemp[i].field_2_2=0;
                    };
                    if(yearTemp[i].field_2_3 === ''){
                       yearTemp[i].field_2_3=0;
                    };
                    if(yearTemp[i].field_2_4 === ''){
                       yearTemp[i].field_2_4=0;
                    };
                    if(yearTemp[i].field_2_5 === ''){
                       yearTemp[i].field_2_5=0;
                    };
                    if(yearTemp[i].field_2_6 === ''){
                       yearTemp[i].field_2_6=0;
                    };
                    if(yearTemp[i].field_3_1 === ''){
                       yearTemp[i].field_3_1=0;
                    };
                    if(yearTemp[i].field_3_2 === ''){
                       yearTemp[i].field_3_2=0;
                    };
                    if(yearTemp[i].field_3_3 === ''){
                       yearTemp[i].field_3_3=0;
                    };
                    if(yearTemp[i].field_3_4 === ''){
                       yearTemp[i].field_3_4=0;
                    };
                    if(yearTemp[i].field_3_5 === ''){
                       yearTemp[i].field_3_5=0;
                    };
                    if(yearTemp[i].field_3_6 === ''){
                       yearTemp[i].field_3_6=0;
                    };
                  }
                  num1 = _this.add(yearTemp[0].field_2_1*1 - yearTemp[1].field_2_1*1);
                  num2 = _this.add(yearTemp[0].field_2_2*1 - yearTemp[1].field_2_2*1);
                  num3 = _this.add(yearTemp[0].field_2_3*1 - yearTemp[1].field_2_3*1);
                  num4 = _this.add(yearTemp[0].field_2_4*1 - yearTemp[1].field_2_4*1);
                  num5 = _this.add(yearTemp[0].field_2_5*1 - yearTemp[1].field_2_5*1);
                  num6 = _this.add(yearTemp[0].field_2_6*1 - yearTemp[1].field_2_6*1);
                  num7 = _this.add(yearTemp[0].field_3_1*1 - yearTemp[1].field_3_1*1);
                  num8 = _this.add(yearTemp[0].field_3_2*1 - yearTemp[1].field_3_2*1);
                  num9 = _this.add(yearTemp[0].field_3_3*1 - yearTemp[1].field_3_3*1);
                  num10 = _this.add(yearTemp[0].field_3_4*1 - yearTemp[1].field_3_4*1);
                  num11 = _this.add(yearTemp[0].field_3_5*1 - yearTemp[1].field_3_5*1);
                  num12 = _this.add(yearTemp[0].field_3_6*1 - yearTemp[1].field_3_6*1);

                  yearTemp.push({"field_1_1":county,"field_1_2":'较去年增减',
                                  "field_2_1":num1,"field_2_2":num2,"field_2_3":num3,"field_2_4":num4,"field_2_5":num5,"field_2_6":num6,
                                  "field_3_1":num7,"field_3_2":num8,"field_3_3":num9,"field_3_4":num10,"field_3_5":num11,"field_3_6":num12,
                                  "in_use":'--'}); 
                  fourTemp = fourTemp.concat(yearTemp);
                  
              }
            };
            if(fourTemp.length != 0){
              fourTemp = fourTemp.concat(sumTemp);
            }
            tempData=fourTemp;
            _this.dataNum = tempData.length;
         }else{
           _this.dataNum = 0;
         };
          //console.log(fourTemp);
          _this.tableData = tempData;
          //console.log(_this.tableData);
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //分页
    handleCurrentChange:function(currentPage){
      this.currentPage = currentPage;
      ////console.log(this.currentPage);
    },
    //分页后序号连续处理
    countMethod(index){
       return (this.currentPage-1)*this.pagesize + index +1;
    },
    //导出excel
    // 导出Excel点击方法
    handleDownload() {
      // this.downloadLoading = true // 加载遮罩层开启
      const {export_json_to_excel} = require('../../excel/Export2Excel2'); //引入文件
      const th1 = [[this.tableLabel,  '', '','','','','','','','','','','',''],
                    ['县（市、区）', '', 
        '抽穗期前15天','','','','','',
        '抽穗期后15天','','','','','']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', 
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）',
        '日平均气温（℃）','日最高气温（℃）','降雨量（mm）','日照时数（h）','晴好天气天数（天）','阴雨天气天数（天）']] //对应表格三级输出的title
      const filterVal = ['field_1_1', 'field_1_2', 
        'field_2_1','field_2_2','field_2_3','field_2_4','field_2_5','field_2_6',
        'field_3_1','field_3_2','field_3_3','field_3_4','field_3_5','field_3_6'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:N1",
        "A2:B3",
        "C2:H2",
        "I2:N2",
      ];
      const list = this.tableData;
      const data = this.formatJson(filterVal, list);
      export_json_to_excel(th1, th2, header, data, this.tableLabel, merges);
      // this.downloadLoading = false // 加载遮罩层结束
    },
    // 将JSON转换成map
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]))
    },
    returnPage(){
      var diqu = this.areas;
      var xian = this.citys;
      var nian = this.years;
      ////console.log(diqu,xian,nian)
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



