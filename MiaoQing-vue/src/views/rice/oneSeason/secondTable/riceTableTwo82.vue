<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="3">
                <template>
                  <!-- <el-select v-model="areaType" clearable  placeholder="地区类型" style="width:150px">
                    <el-option
                      v-for="item in areaTypes"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select> -->

                  <el-select v-model="county" clearable  style="width:150px; margin-left: 20px;" placeholder="县（市、区）">
                    <el-option
                      v-for="item in countys"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                  <!-- <el-select v-model="riceType" clearable  style="width:150px; margin-left: 20px;" placeholder="稻作类型">
                    <el-option
                      v-for="item in riceTypes"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>

                  <el-select v-model="category" clearable  style="width:150px; margin-left: 20px;" placeholder="品种类型">
                    <el-option
                      v-for="item in categorys"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select> -->

                  <!-- <el-select v-model="plantWay" clearable  style="width:150px; margin-left: 20px;" placeholder="栽植方式">
                    <el-option
                      v-for="item in plantWays"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select> -->
                  
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
                <el-table-column align="center" fixed prop="field_1" width="100%" label="县（市、区）" />
                <el-table-column align="center" fixed prop="field_2" width="100%" label="地区类型" />

                <el-table-column align="center" fixed prop="field_3" width="100%" label="品种类型" />
                <el-table-column align="center" fixed prop="field_4" width="110%" label="监测田块" />
                <el-table-column align="center" prop="field_5" width="140%" label="代表面积（万亩）" />
                <el-table-column align="center" prop="field_6" width="100%" label="栽植方式" />
                <el-table-column align="center" prop="field_7" width="140%" label="水稻亩产值（元）" />
                <el-table-column align="center" prop="field_8" width="200%" label="其中主产品产值（公斤/元）" />
                <el-table-column align="center" prop="field_9" width="170%" label="副产品产值（公斤/元）" />
                <el-table-column align="center" label="劳动用工">
                  <el-table-column align="center" label="育秧" >
                    <el-table-column align="center" prop="field_10_1_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_1_2" width="160%" label="单位用工价格（元/个）" />
                  </el-table-column>
                  <el-table-column align="center" label="耕作" >
                    <el-table-column align="center" prop="field_10_2_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_2_2" width="160%" label="单位用工价格（元/个）" />
                  </el-table-column>
                  <el-table-column align="center" label="移栽" >
                    <el-table-column align="center" prop="field_10_3_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_3_2" width="160%" label="单位用工价格（元/个）" />
                  </el-table-column>
                  <el-table-column align="center" label="直播" >
                    <el-table-column align="center" prop="field_10_4_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_4_2" width="160%" label="单位用工价格（元/个）" />
                  </el-table-column>
                  <el-table-column align="center" label="大田管理" >
                    <el-table-column align="center" prop="field_10_5_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_5_2" width="160%" label="单位用工价格（元/个）" />
                  </el-table-column>
                  <el-table-column align="center" label="小计" >
                    <el-table-column align="center" prop="field_10_6_1" width="130%" label="数量（个/亩）" />
                    <el-table-column align="center" prop="field_10_6_2" width="160%" label="价格（元/亩）" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="物质费用">
                  <el-table-column align="center" prop="field_11_1" width="130%" label="种子（元/亩）" />
                  <el-table-column align="center" label="化肥">
                    <el-table-column align="center" prop="field_11_2_1" width="120%" label="种类" />
                    <el-table-column align="center" prop="field_11_2_2" width="130%" label="施用量（kg/亩）" />
                    <el-table-column align="center" prop="field_11_2_3" width="120%" label="价格（元/kg）" />
                    <el-table-column align="center" prop="field_11_2_4" width="120%" label="小计（元/亩）" />
                  </el-table-column>
                    <el-table-column align="center" prop="field_11_3" width="130%" label="有机肥（元/亩）" />
                    <el-table-column align="center" prop="field_11_4" width="120%" label="农药（元/亩）" />
                    <el-table-column align="center" prop="field_11_5" width="120%" label="灌溉（元/亩）" />
                    <el-table-column align="center" prop="field_11_6" width="140%" label="机械耕作（元/亩）" />
                    <el-table-column align="center" prop="field_11_7" width="180%" label="机械插秧/直播（元/亩）" />
                    <el-table-column align="center" prop="field_11_8" width="150%" label="机械收获（元/亩）" />
                    <el-table-column align="center" prop="field_11_9" width="120%" label="其他（元/亩）" />
                    <el-table-column align="center" prop="field_11_10" width="120%" label="小计（元/亩）" />
                </el-table-column>
                <el-table-column align="center" prop="field_12" width="120%" label="地租（元/亩）" />
                <el-table-column align="center" prop="field_13" width="140%" label="其他费用（元/亩）" />
                <el-table-column align="center" prop="field_14" width="120%" label="亩总成本（元）" />
                <el-table-column align="center" prop="field_15" width="110%" label="亩收益（元）" />
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
      tableName:'crop_01_02_table_08_02',
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
      this.tableLabel= this.years+"年度监测县(市、区)水稻生产成本效益统计表（晚稻）";
      this.getCountySelection();
      this.getTableData();
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
         if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex==0 || rowIndex==1 || rowIndex ==2){
            return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF'};
        };
    },
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
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
          //品系数据
          var categoryData = [];
          //栽植数据
          var plantData = [];
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
               if(optionData[i].caption === "品系类型"){
                categoryData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "栽植方式"){
                plantData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          // //console.log(countyData);
          // //console.log(areaData);
          // //console.log(riceData);
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;
          _this.categorys = categoryData;
          _this.plantWays = plantData;

       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    //获取表数据
    getTableData(){ 
       const _this = this;
       axios({
          url: '/getRiceTableTwo82',
          method:'post',
          params:{
            areaType:this.areas,
            county:this.citys,
            year:this.years
           }
       }).then(res=>{
          var tempData=res.data;
          //console.log(res.data);
         //处理合计
         if(tempData.length !=0){
            var plantTemp=_this.plantWays;
            var totalTemp=tempData;
            var sumTemp=[];
            for(var j=0; j<plantTemp.length; j++){
              var sums=[];
              var total=[];
                var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                var num31=0;var num32=0;
              
                for(var i=0; i<totalTemp.length; i++){
                  if(plantTemp[j].value === tempData[i].field_6){
                      if(totalTemp[i].field_5 === ''){
                          totalTemp[i].field_5 = 0;
                        }
                      num1 = _this.add(num1*1+totalTemp[i].field_5*1);

                      if(totalTemp[i].field_7 === ''){
                          totalTemp[i].field_7 = 0;
                        }
                      num2 = _this.add(num2*1+totalTemp[i].field_7*1);

                      if(totalTemp[i].field_8 === ''){
                        totalTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+totalTemp[i].field_8*1);

                      if(totalTemp[i].field_9 === ''){
                        totalTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+totalTemp[i].field_9*1);

                      if(totalTemp[i].field_10_1_1 === ''){
                        totalTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+totalTemp[i].field_10_1_1*1);

                      if(totalTemp[i].field_10_1_2 === ''){
                        totalTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+totalTemp[i].field_10_1_2*1);

                      if(totalTemp[i].field_10_2_1 === ''){
                        totalTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+totalTemp[i].field_10_2_1*1);

                      if(totalTemp[i].field_10_2_2 === ''){
                        totalTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+totalTemp[i].field_10_2_2*1);

                      if(totalTemp[i].field_10_3_1 === ''){
                        totalTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+totalTemp[i].field_10_3_1*1);

                      if(totalTemp[i].field_10_3_2 === ''){
                        totalTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+totalTemp[i].field_10_3_2*1);

                      if(totalTemp[i].field_10_4_1 === ''){
                        totalTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+totalTemp[i].field_10_4_1*1);

                      if(totalTemp[i].field_10_4_2 === ''){
                        totalTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+totalTemp[i].field_10_4_2*1);

                      if(totalTemp[i].field_10_5_1 === ''){
                        totalTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+totalTemp[i].field_10_5_1*1);

                      if(totalTemp[i].field_10_5_2 === ''){
                        totalTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+totalTemp[i].field_10_5_2*1);

                      if(totalTemp[i].field_10_6_1 === ''){
                        totalTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+totalTemp[i].field_10_6_1*1);

                      if(totalTemp[i].field_10_6_2 === ''){
                        totalTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+totalTemp[i].field_10_6_2*1);

                      if(totalTemp[i].field_12_1 === ''){
                        totalTemp[i].field_12_1 = 0;
                      }
                      num17 = _this.add(num17*1+totalTemp[i].field_12_1*1);

                      if(totalTemp[i].field_11_2_2 === ''){
                        totalTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+totalTemp[i].field_11_2_2*1);

                      if(totalTemp[i].field_11_2_3 === ''){
                        totalTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+totalTemp[i].field_11_2_3*1);

                      if(totalTemp[i].field_11_2_4 === ''){
                        totalTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+totalTemp[i].field_11_2_4*1);
                      
                      if(totalTemp[i].field_11_3 === ''){
                        totalTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+totalTemp[i].field_11_3*1);

                      if(totalTemp[i].field_11_4 === ''){
                        totalTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+totalTemp[i].field_11_4*1);

                      if(totalTemp[i].field_11_5 === ''){
                        totalTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+totalTemp[i].field_11_5*1);

                      if(totalTemp[i].field_11_6 === ''){
                        totalTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+totalTemp[i].field_11_6*1);

                      if(totalTemp[i].field_11_7 === ''){
                        totalTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+totalTemp[i].field_11_7*1);

                      if(totalTemp[i].field_11_8 === ''){
                        totalTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+totalTemp[i].field_11_8*1);

                      if(totalTemp[i].field_11_9 === ''){
                        totalTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+totalTemp[i].field_11_9*1);

                      if(totalTemp[i].field_11_10 === ''){
                        totalTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+totalTemp[i].field_11_10*1);

                      if(totalTemp[i].field_12 === ''){
                        totalTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+totalTemp[i].field_12*1);

                      if(totalTemp[i].field_13 === ''){
                        totalTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+totalTemp[i].field_13*1);

                      if(totalTemp[i].field_14 === ''){
                        totalTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+totalTemp[i].field_14*1);

                      if(totalTemp[i].field_15 === ''){
                        totalTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+totalTemp[i].field_15*1);
                  };
                };

                if(num1 !=0){//用代表面积来判断栽植方式存在哪些
                    sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                    total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                    total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                    total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30;
                    sumTemp.push({"field_1":"合计","field_2":'--',"field_3":'--',"field_4":'--',"field_5":sums[0],"field_6":plantTemp[j].value,"field_7":total[0],"field_8":total[1],"field_9":total[2],
                                  "field_10_1_1":total[3],"field_10_1_2":total[4],"field_10_2_1":total[5],"field_10_2_2":total[6],"field_10_3_1":total[7],"field_10_3_2":total[8],
                                  "field_10_4_1":total[9],"field_10_4_2":total[10],"field_10_5_1":total[11],"field_10_5_2":total[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":total[13],"field_11_2_1":"--","field_11_2_2":total[14],"field_11_2_3":total[15],"field_11_2_4":sums[3],
                                  "field_11_3":total[16],"field_11_4":total[17],"field_11_5":total[18],"field_11_6":total[19],"field_11_7":total[20],"field_11_8":total[21],
                                  "field_11_9":total[22],"field_11_10":sums[4],"field_12":total[23],"field_13":total[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                };
               
            };
            //console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            var countyTemp=_this.countys;
            //console.log(countyTemp);
            var secondTemp=[];
            for(var i=0; i<countyTemp.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(countyTemp[i].value === tempData[j].field_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            secondTemp.push(sumTemp);
            //console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp !=''){
                  var county=thirdTemp[0].field_1;
                  var sums=[];
                  var total=[];
                  var mean=[];//存加权平均
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                  var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                  var num31=0;var num32=0;
                  for(var i=0; i<thirdTemp.length;i++){
                    if(thirdTemp[i].field_5 === ''){
                        thirdTemp[i].field_5 = 0;
                      }
                      num1 = _this.add(num1*1+thirdTemp[i].field_5*1);

                    if(thirdTemp[i].field_7 === ''){
                        thirdTemp[i].field_7 = 0;
                      }
                      num2 = _this.add(num2*1+thirdTemp[i].field_5*thirdTemp[i].field_7);

                      if(thirdTemp[i].field_8 === ''){
                        thirdTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+thirdTemp[i].field_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_9 === ''){
                        thirdTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+thirdTemp[i].field_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_1 === ''){
                        thirdTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+thirdTemp[i].field_10_1_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_2 === ''){
                        thirdTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+thirdTemp[i].field_10_1_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_1 === ''){
                        thirdTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+thirdTemp[i].field_10_2_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_2 === ''){
                        thirdTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+thirdTemp[i].field_10_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_1 === ''){
                        thirdTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+thirdTemp[i].field_10_3_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_2 === ''){
                        thirdTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+thirdTemp[i].field_10_3_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_1 === ''){
                        thirdTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+thirdTemp[i].field_10_4_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_2 === ''){
                        thirdTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+thirdTemp[i].field_10_4_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_1 === ''){
                        thirdTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+thirdTemp[i].field_10_5_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_2 === ''){
                        thirdTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+thirdTemp[i].field_10_5_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_6_1 === ''){
                        thirdTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+thirdTemp[i].field_10_6_1*1);

                      if(thirdTemp[i].field_10_6_2 === ''){
                        thirdTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+thirdTemp[i].field_10_6_2*1);

                      if(thirdTemp[i].field_11_1 === ''){
                        thirdTemp[i].field_11_1 = 0;
                      }
                      num17 = _this.add(num17*1+thirdTemp[i].field_11_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_2 === ''){
                        thirdTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+thirdTemp[i].field_11_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_3 === ''){
                        thirdTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+thirdTemp[i].field_11_2_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_4 === ''){
                        thirdTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+thirdTemp[i].field_11_2_4*1);
                      
                      if(thirdTemp[i].field_11_3 === ''){
                        thirdTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+thirdTemp[i].field_11_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_4 === ''){
                        thirdTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+thirdTemp[i].field_11_4*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_5 === ''){
                        thirdTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+thirdTemp[i].field_11_5*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_6 === ''){
                        thirdTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+thirdTemp[i].field_11_6*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_7 === ''){
                        thirdTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+thirdTemp[i].field_11_7*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_8 === ''){
                        thirdTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+thirdTemp[i].field_11_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_9 === ''){
                        thirdTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+thirdTemp[i].field_11_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_10 === ''){
                        thirdTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+thirdTemp[i].field_11_10*1);

                      if(thirdTemp[i].field_12 === ''){
                        thirdTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+thirdTemp[i].field_12*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_13 === ''){
                        thirdTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+thirdTemp[i].field_13*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_14 === ''){
                        thirdTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+thirdTemp[i].field_14*1);

                      if(thirdTemp[i].field_15 === ''){
                        thirdTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+thirdTemp[i].field_15*1);
                  };
                  sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                  total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                  total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                  total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30; 
                  for(var i=0; i<total.length; i++){
                      if(num1===0){
                        sums[i]=0;
                        mean[i]=0;
                      }else{
                        mean[i] = (Number(total[i])/Number(num1)).toFixed(2);
                      };
                  };
                  //console.log(total);
                  //console.log(mean);
                  thirdTemp.push({"field_1":county,"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'小计',"field_7":mean[0],"field_8":mean[1],"field_9":mean[2],
                                  "field_10_1_1":mean[3],"field_10_1_2":mean[4],"field_10_2_1":mean[5],"field_10_2_2":mean[6],"field_10_3_1":mean[7],"field_10_3_2":mean[8],
                                  "field_10_4_1":mean[9],"field_10_4_2":mean[10],"field_10_5_1":mean[11],"field_10_5_2":mean[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":mean[13],"field_11_2_1":"--","field_11_2_2":mean[14],"field_11_2_3":mean[15],"field_11_2_4":sums[3],
                                  "field_11_3":mean[16],"field_11_4":mean[17],"field_11_5":mean[18],"field_11_6":mean[19],"field_11_7":mean[20],"field_11_8":mean[21],
                                  "field_11_9":mean[22],"field_11_10":sums[4],"field_12":mean[23],"field_13":mean[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                  
                  fourTemp = fourTemp.concat(thirdTemp);
                  
              }
            };
            //fourTemp = fourTemp.concat(sumTemp);
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
    
    
    //重置
    reSet(){
      this.areaType='';
      this.county='';
      this.riceType='';
      this.category='';
      this.plantWay='';
      this.getTableData();
    },
    //搜索
    searchInfo(){ 
      const _this = this;
      axios({
        url:'/getSeekInfoTwo82',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                category:this.category,
                plantWay:this.plantWay,
                year:this.years,
        }
      }).then(res=>{
          var tempData=res.data;
         //处理合计
         if(tempData.length !=0){
            var plantTemp=_this.plantWays;
            var totalTemp=tempData;
            var sumTemp=[];
            for(var j=0; j<plantTemp.length; j++){
              var sums=[];
              var total=[];
                var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                var num31=0;var num32=0;
              
                for(var i=0; i<totalTemp.length; i++){
                  if(plantTemp[j].value === tempData[i].field_6){
                      if(totalTemp[i].field_5 === ''){
                          totalTemp[i].field_5 = 0;
                        }
                      num1 = _this.add(num1*1+totalTemp[i].field_5*1);

                      if(totalTemp[i].field_7 === ''){
                          totalTemp[i].field_7 = 0;
                        }
                      num2 = _this.add(num2*1+totalTemp[i].field_7*1);

                      if(totalTemp[i].field_8 === ''){
                        totalTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+totalTemp[i].field_8*1);

                      if(totalTemp[i].field_9 === ''){
                        totalTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+totalTemp[i].field_9*1);

                      if(totalTemp[i].field_10_1_1 === ''){
                        totalTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+totalTemp[i].field_10_1_1*1);

                      if(totalTemp[i].field_10_1_2 === ''){
                        totalTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+totalTemp[i].field_10_1_2*1);

                      if(totalTemp[i].field_10_2_1 === ''){
                        totalTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+totalTemp[i].field_10_2_1*1);

                      if(totalTemp[i].field_10_2_2 === ''){
                        totalTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+totalTemp[i].field_10_2_2*1);

                      if(totalTemp[i].field_10_3_1 === ''){
                        totalTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+totalTemp[i].field_10_3_1*1);

                      if(totalTemp[i].field_10_3_2 === ''){
                        totalTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+totalTemp[i].field_10_3_2*1);

                      if(totalTemp[i].field_10_4_1 === ''){
                        totalTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+totalTemp[i].field_10_4_1*1);

                      if(totalTemp[i].field_10_4_2 === ''){
                        totalTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+totalTemp[i].field_10_4_2*1);

                      if(totalTemp[i].field_10_5_1 === ''){
                        totalTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+totalTemp[i].field_10_5_1*1);

                      if(totalTemp[i].field_10_5_2 === ''){
                        totalTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+totalTemp[i].field_10_5_2*1);

                      if(totalTemp[i].field_10_6_1 === ''){
                        totalTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+totalTemp[i].field_10_6_1*1);

                      if(totalTemp[i].field_10_6_2 === ''){
                        totalTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+totalTemp[i].field_10_6_2*1);

                      if(totalTemp[i].field_12_1 === ''){
                        totalTemp[i].field_12_1 = 0;
                      }
                      num17 = _this.add(num17*1+totalTemp[i].field_12_1*1);

                      if(totalTemp[i].field_11_2_2 === ''){
                        totalTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+totalTemp[i].field_11_2_2*1);

                      if(totalTemp[i].field_11_2_3 === ''){
                        totalTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+totalTemp[i].field_11_2_3*1);

                      if(totalTemp[i].field_11_2_4 === ''){
                        totalTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+totalTemp[i].field_11_2_4*1);
                      
                      if(totalTemp[i].field_11_3 === ''){
                        totalTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+totalTemp[i].field_11_3*1);

                      if(totalTemp[i].field_11_4 === ''){
                        totalTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+totalTemp[i].field_11_4*1);

                      if(totalTemp[i].field_11_5 === ''){
                        totalTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+totalTemp[i].field_11_5*1);

                      if(totalTemp[i].field_11_6 === ''){
                        totalTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+totalTemp[i].field_11_6*1);

                      if(totalTemp[i].field_11_7 === ''){
                        totalTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+totalTemp[i].field_11_7*1);

                      if(totalTemp[i].field_11_8 === ''){
                        totalTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+totalTemp[i].field_11_8*1);

                      if(totalTemp[i].field_11_9 === ''){
                        totalTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+totalTemp[i].field_11_9*1);

                      if(totalTemp[i].field_11_10 === ''){
                        totalTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+totalTemp[i].field_11_10*1);

                      if(totalTemp[i].field_12 === ''){
                        totalTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+totalTemp[i].field_12*1);

                      if(totalTemp[i].field_13 === ''){
                        totalTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+totalTemp[i].field_13*1);

                      if(totalTemp[i].field_14 === ''){
                        totalTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+totalTemp[i].field_14*1);

                      if(totalTemp[i].field_15 === ''){
                        totalTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+totalTemp[i].field_15*1);
                  };
                };

                if(num1 !=0){//用代表面积来判断栽植方式存在哪些
                    sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                    total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                    total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                    total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30;
                    sumTemp.push({"field_1":"合计","field_2":'--',"field_3":'--',"field_4":'--',"field_5":sums[0],"field_6":plantTemp[j].value,"field_7":total[0],"field_8":total[1],"field_9":total[2],
                                  "field_10_1_1":total[3],"field_10_1_2":total[4],"field_10_2_1":total[5],"field_10_2_2":total[6],"field_10_3_1":total[7],"field_10_3_2":total[8],
                                  "field_10_4_1":total[9],"field_10_4_2":total[10],"field_10_5_1":total[11],"field_10_5_2":total[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":total[13],"field_11_2_1":"--","field_11_2_2":total[14],"field_11_2_3":total[15],"field_11_2_4":sums[3],
                                  "field_11_3":total[16],"field_11_4":total[17],"field_11_5":total[18],"field_11_6":total[19],"field_11_7":total[20],"field_11_8":total[21],
                                  "field_11_9":total[22],"field_11_10":sums[4],"field_12":total[23],"field_13":total[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                };
               
            };
            //console.log(sumTemp);
         };
         //数据计算处理
         if(tempData.length != 0){
            var countyTemp=_this.countys;
            //console.log(countyTemp);
            var secondTemp=[];
            for(var i=0; i<countyTemp.length; i++){           //获取各个县市独立地数据
              var temp=[];
              for(var j=0; j<tempData.length; j++){
                if(countyTemp[i].value === tempData[j].field_1){
                  temp.push(tempData[j]);
                };
              };
              secondTemp.push(temp);
            };
            secondTemp.push(sumTemp);
            //console.log(secondTemp);
            var thirdTemp=[];
            var fourTemp=[];
            for(var j=0; j<secondTemp.length; j++){
              var thirdTemp=secondTemp[j];
              if(thirdTemp !=''){
                  var county=thirdTemp[0].field_1;
                  var sums=[];
                  var total=[];
                  var mean=[];//存加权平均
                  var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
                  var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
                  var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
                  var num31=0;var num32=0;
                  for(var i=0; i<thirdTemp.length;i++){
                    if(thirdTemp[i].field_5 === ''){
                        thirdTemp[i].field_5 = 0;
                      }
                      num1 = _this.add(num1*1+thirdTemp[i].field_5*1);

                    if(thirdTemp[i].field_7 === ''){
                        thirdTemp[i].field_7 = 0;
                      }
                      num2 = _this.add(num2*1+thirdTemp[i].field_5*thirdTemp[i].field_7);

                      if(thirdTemp[i].field_8 === ''){
                        thirdTemp[i].field_8 = 0;
                      }
                      num3 = _this.add(num3*1+thirdTemp[i].field_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_9 === ''){
                        thirdTemp[i].field_9 = 0;
                      }
                      num4 = _this.add(num4*1+thirdTemp[i].field_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_1 === ''){
                        thirdTemp[i].field_10_1_1 = 0;
                      }
                      num5 = _this.add(num5*1+thirdTemp[i].field_10_1_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_1_2 === ''){
                        thirdTemp[i].field_10_1_2 = 0;
                      }
                      num6 = _this.add(num6*1+thirdTemp[i].field_10_1_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_1 === ''){
                        thirdTemp[i].field_10_2_1 = 0;
                      }
                      num7 = _this.add(num7*1+thirdTemp[i].field_10_2_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_2_2 === ''){
                        thirdTemp[i].field_10_2_2 = 0;
                      }
                      num8 = _this.add(num8*1+thirdTemp[i].field_10_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_1 === ''){
                        thirdTemp[i].field_10_3_1 = 0;
                      }
                      num9 = _this.add(num9*1+thirdTemp[i].field_10_3_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_3_2 === ''){
                        thirdTemp[i].field_10_3_2 = 0;
                      }
                      num10 = _this.add(num10*1+thirdTemp[i].field_10_3_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_1 === ''){
                        thirdTemp[i].field_10_4_1 = 0;
                      }
                      num11 = _this.add(num11*1+thirdTemp[i].field_10_4_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_4_2 === ''){
                        thirdTemp[i].field_10_4_2 = 0;
                      }
                      num12 = _this.add(num12*1+thirdTemp[i].field_10_4_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_1 === ''){
                        thirdTemp[i].field_10_5_1 = 0;
                      }
                      num13 = _this.add(num13*1+thirdTemp[i].field_10_5_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_5_2 === ''){
                        thirdTemp[i].field_10_5_2 = 0;
                      }
                      num14 = _this.add(num14*1+thirdTemp[i].field_10_5_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_10_6_1 === ''){
                        thirdTemp[i].field_10_6_1 = 0;
                      }
                      num15 = _this.add(num15*1+thirdTemp[i].field_10_6_1*1);

                      if(thirdTemp[i].field_10_6_2 === ''){
                        thirdTemp[i].field_10_6_2 = 0;
                      }
                      num16 = _this.add(num16*1+thirdTemp[i].field_10_6_2*1);

                      if(thirdTemp[i].field_11_1 === ''){
                        thirdTemp[i].field_11_1 = 0;
                      }
                      num17 = _this.add(num17*1+thirdTemp[i].field_11_1*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_2 === ''){
                        thirdTemp[i].field_11_2_2 = 0;
                      }
                      num18 = _this.add(num18*1+thirdTemp[i].field_11_2_2*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_3 === ''){
                        thirdTemp[i].field_11_2_3 = 0;
                      }
                      num19 = _this.add(num19*1+thirdTemp[i].field_11_2_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_2_4 === ''){
                        thirdTemp[i].field_11_2_4 = 0;
                      }
                      num20 = _this.add(num20*1+thirdTemp[i].field_11_2_4*1);
                      
                      if(thirdTemp[i].field_11_3 === ''){
                        thirdTemp[i].field_11_3 = 0;
                      }
                      num21 = _this.add(num21*1+thirdTemp[i].field_11_3*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_4 === ''){
                        thirdTemp[i].field_11_4 = 0;
                      }
                      num22 = _this.add(num22*1+thirdTemp[i].field_11_4*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_5 === ''){
                        thirdTemp[i].field_11_5 = 0;
                      }
                      num23 = _this.add(num23*1+thirdTemp[i].field_11_5*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_6 === ''){
                        thirdTemp[i].field_11_6 = 0;
                      }
                      num24 = _this.add(num24*1+thirdTemp[i].field_11_6*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_7 === ''){
                        thirdTemp[i].field_11_7 = 0;
                      }
                      num25 = _this.add(num25*1+thirdTemp[i].field_11_7*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_8 === ''){
                        thirdTemp[i].field_11_8 = 0;
                      }
                      num26 = _this.add(num26*1+thirdTemp[i].field_11_8*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_9 === ''){
                        thirdTemp[i].field_11_9 = 0;
                      }
                      num27 = _this.add(num27*1+thirdTemp[i].field_11_9*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_11_10 === ''){
                        thirdTemp[i].field_11_10 = 0;
                      }
                      num28 = _this.add(num28*1+thirdTemp[i].field_11_10*1);

                      if(thirdTemp[i].field_12 === ''){
                        thirdTemp[i].field_12 = 0;
                      }
                      num29 = _this.add(num29*1+thirdTemp[i].field_12*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_13 === ''){
                        thirdTemp[i].field_13 = 0;
                      }
                      num30 = _this.add(num30*1+thirdTemp[i].field_13*thirdTemp[i].field_5);

                      if(thirdTemp[i].field_14 === ''){
                        thirdTemp[i].field_14 = 0;
                      }
                      num31 = _this.add(num31*1+thirdTemp[i].field_14*1);

                      if(thirdTemp[i].field_15 === ''){
                        thirdTemp[i].field_15 = 0;
                      }
                      num32 = _this.add(num32*1+thirdTemp[i].field_15*1);
                  };
                  sums[0]=num1;sums[1]=num15;sums[2]=num16;sums[3]=num20;sums[4]=num28;sums[5]=num31;sums[6]=num32;
                  total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
                  total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num17;total[14]=num18;   total[15]=num19;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
                  total[20]=num25;total[21]=num26;total[22]=num27;total[23]=num29;total[24]=num30; 
                  for(var i=0; i<total.length; i++){
                      if(num1===0){
                        sums[i]=0;
                        mean[i]=0;
                      }else{
                        mean[i] = (Number(total[i])/Number(num1)).toFixed(2);
                      };
                  };
                  //console.log(total);
                  //console.log(mean);
                  thirdTemp.push({"field_1":county,"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'小计',"field_7":mean[0],"field_8":mean[1],"field_9":mean[2],
                                  "field_10_1_1":mean[3],"field_10_1_2":mean[4],"field_10_2_1":mean[5],"field_10_2_2":mean[6],"field_10_3_1":mean[7],"field_10_3_2":mean[8],
                                  "field_10_4_1":mean[9],"field_10_4_2":mean[10],"field_10_5_1":mean[11],"field_10_5_2":mean[12],"field_10_6_1":sums[1],"field_10_6_2":sums[2],
                                  "field_11_1":mean[13],"field_11_2_1":"--","field_11_2_2":mean[14],"field_11_2_3":mean[15],"field_11_2_4":sums[3],
                                  "field_11_3":mean[16],"field_11_4":mean[17],"field_11_5":mean[18],"field_11_6":mean[19],"field_11_7":mean[20],"field_11_8":mean[21],
                                  "field_11_9":mean[22],"field_11_10":sums[4],"field_12":mean[23],"field_13":mean[24],"field_14":sums[5],"field_15":sums[6],
                                  "in_use":'--'}); 
                  
                  fourTemp = fourTemp.concat(thirdTemp);
                  
              }
            };
            //fourTemp = fourTemp.concat(sumTemp);
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
      const th1 = [[this.tableLabel,'', '', '','','', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '', '','', '', '', ''],
                    ['县（市、区）', '地区类型', '稻作类型', '品种类型','监测田块',
                     '代表面积（万亩）', '栽植方式', '水稻亩产值（元）', '其中主产品产值（公斤/元）', '副产品产值（公斤/元）',
                    '劳动用工', '', '', '', '', '', '', '', '', '', '', '',
                    '物质费用', '', '', '', '', '', '', '', '', '', '', '', '',
                    '地租（元/亩）', '其他费用（元/亩）', '亩总成本（元）', '亩收益（元）'],
                    ['', '', '', '','','', '', '', '', '',
                    '育秧', '', '耕作', '', '移栽', '', '直播', '', '大田管理', '', '小计', '',
                    '种子（元/亩）', '化肥', '', '', '', '有机肥（元/亩）', '农药（元/亩）', '灌溉（元/亩）', 
                    '机械耕作（元/亩）', '机械插秧/直播（元/亩）', '机械收获（元/亩）', '其他（元/亩）', '小计（元/亩）',
                    '', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '','','', '', '', '', '',
                    '数量（个/亩）', '价格（元/亩）', '数量（个/亩）', '价格（元/亩）','数量（个/亩）', '价格（元/亩）','数量（个/亩）', '价格（元/亩）','数量（个/亩）', '价格（元/亩）','数量（个/亩）', '价格（元/亩）',
                    '', '种类', '施用量（kg/亩）', '价格(元/kg)', '小计（元/亩）', '', '', '', '', '', '', '', '','', '', '', '']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5','field_6', 'field_7', 'field_8', 'field_9', 'field_10',
                    'field_11_1_1', 'field_11_1_2', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 
                    'field_11_4_1', 'field_11_4_2', 'field_11_5_1', 'field_11_5_2', 'field_11_6_1', 'field_11_6_2',
                    'field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_2_3', 'field_12_2_4', 
                    'field_12_3', 'field_12_4', 'field_12_5','field_12_6', 'field_12_7', 'field_12_8', 'field_12_9', 'field_12_10',
                    'field_13', 'field_14', 'field_15', 'field_16'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AM1",
        "A2:A4",
        "B2:B4",
        "C2:C4",
        "D2:D4",
        "E2:E4",
        "F2:F4",
        "G2:G4",
        "H2:H4",
        "I2:I4",
        "J2:J4",
        "K2:V2",
        "W2:AI2",
        "AJ2:AJ4",
        "AK2:AK4",
        "AL2:AL4",
        "AM2:AM4",
        "K3:L3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "S3:T3",
        "U3:V3",
        "W3:W4",
        "X3:AA3",
        "AB3:AB4",
        "AC3:AC4",
        "AD3:AD4",
        "AE3:AE4",
        "AF3:AF4",
        "AG3:AG4",
        "AH3:AH4",
        "AI3:AI4",
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



