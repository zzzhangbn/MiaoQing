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
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'4px'}" :row-style="{height: '30px'}"  row-key="id" stripe>
              <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed/>
                <el-table-column align="center" prop="field_2" width="100%" label="地区类型" fixed/>
                <el-table-column align="center" prop="field_3" width="100%" label="稻作类型" fixed/>
                <el-table-column align="center" prop="field_4" width="100%" label="全县总面积（万亩）" fixed/>                
                <el-table-column align="center" label="进程1（大田直播后20天）">
                  <el-table-column align="center" prop="field_5_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_5_2_1" label="" />
                    <el-table-column align="center" prop="field_5_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_5_3_1" label="" />
                    <el-table-column align="center" prop="field_5_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_5_4_1" label="" />
                    <el-table-column align="center" prop="field_5_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程2（大田直播后28天）">
                  <el-table-column align="center" prop="field_6_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_6_2_1" label="" />
                    <el-table-column align="center" prop="field_6_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_6_3_1" label="" />
                    <el-table-column align="center" prop="field_6_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_6_4_1" label="" />
                    <el-table-column align="center" prop="field_6_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程3（大田直播后35天）">
                  <el-table-column align="center" prop="field_7_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_7_2_1" label="" />
                    <el-table-column align="center" prop="field_7_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_7_3_1" label="" />
                    <el-table-column align="center" prop="field_7_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_7_4_1" label="" />
                    <el-table-column align="center" prop="field_7_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程4（大田直播后42天）">
                  <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_8_2_1" label="" />
                    <el-table-column align="center" prop="field_8_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_8_3_1" label="" />
                    <el-table-column align="center" prop="field_8_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_8_4_1" label="" />
                    <el-table-column align="center" prop="field_8_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程5（大田直播后49天）">
                  <el-table-column align="center" prop="field_9_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_9_2_1" label="" />
                    <el-table-column align="center" prop="field_9_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_9_3_1" label="" />
                    <el-table-column align="center" prop="field_9_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_9_4_1" label="" />
                    <el-table-column align="center" prop="field_9_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程6（大田直播后56天）">
                  <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_10_2_1" label="" />
                    <el-table-column align="center" prop="field_10_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_10_3_1" label="" />
                    <el-table-column align="center" prop="field_10_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_10_4_1" label="" />
                    <el-table-column align="center" prop="field_10_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程7（大田直播后66天）">
                  <el-table-column align="center" prop="field_11_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_11_2_1" label="" />
                    <el-table-column align="center" prop="field_11_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_11_3_1" label="" />
                    <el-table-column align="center" prop="field_11_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_11_4_1" label="" />
                    <el-table-column align="center" prop="field_11_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程8（大田直播后76天）">
                  <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_12_2_1" label="" />
                    <el-table-column align="center" prop="field_12_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_12_3_1" label="" />
                    <el-table-column align="center" prop="field_12_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_12_4_1" label="" />
                    <el-table-column align="center" prop="field_12_4_2" label="" />
                  </el-table-column>
                </el-table-column>
                <el-table-column align="center" label="进程9（大田直播后86天）">
                  <el-table-column align="center" prop="field_13_1" width="100%" label="日期" />
                  <el-table-column align="center" label="一类苗（万亩）">
                    <el-table-column align="center" prop="field_13_2_1" label="" />
                    <el-table-column align="center" prop="field_13_2_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="二类苗（万亩）">
                    <el-table-column align="center" prop="field_13_3_1" label="" />
                    <el-table-column align="center" prop="field_13_3_2" label="" />
                  </el-table-column>
                  <el-table-column align="center" label="三类苗（万亩）">
                    <el-table-column align="center" prop="field_13_4_1" label="" />
                    <el-table-column align="center" prop="field_13_4_2" label="" />
                  </el-table-column>
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
      tableName:'crop_01_02_table_03_04',
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
      this.tableLabel= this.years+"年度监测县(市、区)水稻苗情评价动态列表（早稻-直播稻）";
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
          url: '/getRiceTableTwo34',
         method:'post',
          params:{
            areaType:this.areas,
            county:this.citys,
            year:this.years
           }
       }).then(res=>{
          //_this.tableData=res.data;

          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              //console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_10_4_1*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_11_2_1*1);
              
              if(totalTableData[i].field_11_3_1 === ''){
                totalTableData[i].field_11_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_11_3_1*1);

              if(totalTableData[i].field_11_4_1 === ''){
                totalTableData[i].field_11_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_11_4_1*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_12_2_1*1);
              
              if(totalTableData[i].field_12_3_1 === ''){
                totalTableData[i].field_12_3_1 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_12_3_1*1);

              if(totalTableData[i].field_12_4_1 === ''){
                totalTableData[i].field_12_4_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_12_4_1*1);

              if(totalTableData[i].field_13_2_1 === ''){
                totalTableData[i].field_13_2_1 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_13_2_1*1);
              
              if(totalTableData[i].field_13_3_1 === ''){
                totalTableData[i].field_13_3_1 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_13_3_1*1);

              if(totalTableData[i].field_13_4_1 === ''){
                totalTableData[i].field_13_4_1 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_13_4_1*1);

              
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;total[21]=num23;total[22]=num24;total[23]=num25;total[24]=num26;total[25]=num27;total[26]=num28;
            //求占比
            for(var i=0; i<total.length; i++){
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
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,
                      "field_5_1":'--',"field_5_2_1":total[0],"field_5_2_2":sums[0],"field_5_3_1":total[1],"field_5_3_2":sums[1],"field_5_4_1":total[2],"field_5_4_2":sums[2],
                      "field_6_1":'--',"field_6_2_1":total[3],"field_6_2_2":sums[3],"field_6_3_1":total[4],"field_6_3_2":sums[4],"field_6_4_1":total[5],"field_6_4_2":sums[5],
                      "field_7_1":'--',"field_7_2_1":total[6],"field_7_2_2":sums[6],"field_7_3_1":total[7],"field_7_3_2":sums[7],"field_7_4_1":total[8],"field_7_4_2":sums[8],
                      "field_8_1":'--',"field_8_2_1":total[9],"field_8_2_2":sums[9],"field_8_3_1":total[10],"field_8_3_2":sums[10],"field_8_4_1":total[11],"field_8_4_2":sums[11],
                      "field_9_1":'--',"field_9_2_1":total[12],"field_9_2_2":sums[12],"field_9_3_1":total[13],"field_9_3_2":sums[13],"field_9_4_1":total[14],"field_9_4_2":sums[14],
                      "field_10_1":'--',"field_10_2_1":total[15],"field_10_2_2":sums[15],"field_10_3_1":total[16],"field_10_3_2":sums[16],"field_10_4_1":total[17],"field_10_4_2":sums[17],
                      "field_11_1":'--',"field_11_2_1":total[18],"field_11_2_2":sums[18], "field_11_3_1":total[19],"field_11_3_2":sums[19],"field_11_4_1":total[20],"field_11_4_2":sums[20],
                      "field_12_1":'--',"field_12_2_1":total[21],"field_12_2_2":sums[21], "field_12_3_1":total[22],"field_12_3_2":sums[22],"field_12_4_1":total[23],"field_12_4_2":sums[23],
                      "field_13_1":'--',"field_13_2_1":total[24],"field_13_2_2":sums[24], "field_13_3_1":total[25],"field_13_3_2":sums[25],"field_13_4_1":total[26],"field_13_4_2":sums[26],
                      "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
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
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfoTwo34',
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
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_4 === ''){
                totalTableData[i].field_4 = 0;
              }
              //console.log(num1);
              num1 = _this.add(num1*1+totalTableData[i].field_4*1);

              if(totalTableData[i].field_5_2_1 === ''){
                totalTableData[i].field_5_2_1 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_2_1*1);

              if(totalTableData[i].field_5_3_1 === ''){
                totalTableData[i].field_5_3_1 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_3_1*1);

              if(totalTableData[i].field_5_4_1 === ''){
                totalTableData[i].field_5_4_1 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_4_1*1);

              if(totalTableData[i].field_6_2_1 === ''){
                totalTableData[i].field_6_2_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_6_2_1*1);

              if(totalTableData[i].field_6_3_1 === ''){
                totalTableData[i].field_6_3_1 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3_1*1);

              if(totalTableData[i].field_6_4_1 === ''){
                totalTableData[i].field_6_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4_1*1);

              if(totalTableData[i].field_7_2_1 === ''){
                totalTableData[i].field_7_2_1 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_7_2_1*1);

              if(totalTableData[i].field_7_3_1 === ''){
                totalTableData[i].field_7_3_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_7_3_1*1);

              if(totalTableData[i].field_7_4_1 === ''){
                totalTableData[i].field_7_4_1 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_7_4_1*1);

              if(totalTableData[i].field_8_2_1 === ''){
                totalTableData[i].field_8_2_1 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_8_2_1*1);

              if(totalTableData[i].field_8_3_1 === ''){
                totalTableData[i].field_8_3_1 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_8_3_1*1);

              if(totalTableData[i].field_8_4_1 === ''){
                totalTableData[i].field_8_4_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_8_4_1*1);

              if(totalTableData[i].field_9_2_1 === ''){
                totalTableData[i].field_9_2_1 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_9_2_1*1);

              if(totalTableData[i].field_9_3_1 === ''){
                totalTableData[i].field_9_3_1 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_9_3_1*1);

              if(totalTableData[i].field_9_4_1 === ''){
                totalTableData[i].field_9_4_1 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_9_4_1*1);

              if(totalTableData[i].field_10_2_1 === ''){
                totalTableData[i].field_10_2_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_10_2_1*1);

              if(totalTableData[i].field_10_3_1 === ''){
                totalTableData[i].field_10_3_1 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_10_3_1*1);

              if(totalTableData[i].field_10_4_1 === ''){
                totalTableData[i].field_10_4_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_10_4_1*1);

              if(totalTableData[i].field_11_2_1 === ''){
                totalTableData[i].field_11_2_1 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_11_2_1*1);
              
              if(totalTableData[i].field_11_3_1 === ''){
                totalTableData[i].field_11_3_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_11_3_1*1);

              if(totalTableData[i].field_11_4_1 === ''){
                totalTableData[i].field_11_4_1 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_11_4_1*1);

              if(totalTableData[i].field_12_2_1 === ''){
                totalTableData[i].field_12_2_1 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_12_2_1*1);
              
              if(totalTableData[i].field_12_3_1 === ''){
                totalTableData[i].field_12_3_1 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_12_3_1*1);

              if(totalTableData[i].field_12_4_1 === ''){
                totalTableData[i].field_12_4_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_12_4_1*1);

              if(totalTableData[i].field_13_2_1 === ''){
                totalTableData[i].field_13_2_1 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_13_2_1*1);
              
              if(totalTableData[i].field_13_3_1 === ''){
                totalTableData[i].field_13_3_1 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_13_3_1*1);

              if(totalTableData[i].field_13_4_1 === ''){
                totalTableData[i].field_13_4_1 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_13_4_1*1);

              
            };
            //各品种面积总和
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;total[5]=num7;total[6]=num8;total[7]=num9;total[8]=num10;total[9]=num11;
            total[10]=num12;total[11]=num13;total[12]=num14;total[13]=num15;total[14]=num16;total[15]=num17;total[16]=num18;total[17]=num19;total[18]=num20;
            total[19]=num21;total[20]=num22;total[21]=num23;total[22]=num24;total[23]=num25;total[24]=num26;total[25]=num27;total[26]=num28;
            //求占比
            for(var i=0; i<total.length; i++){
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
            //辅助数组
            var temp=[];
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":num1,
                      "field_5_1":'--',"field_5_2_1":total[0],"field_5_2_2":sums[0],"field_5_3_1":total[1],"field_5_3_2":sums[1],"field_5_4_1":total[2],"field_5_4_2":sums[2],
                      "field_6_1":'--',"field_6_2_1":total[3],"field_6_2_2":sums[3],"field_6_3_1":total[4],"field_6_3_2":sums[4],"field_6_4_1":total[5],"field_6_4_2":sums[5],
                      "field_7_1":'--',"field_7_2_1":total[6],"field_7_2_2":sums[6],"field_7_3_1":total[7],"field_7_3_2":sums[7],"field_7_4_1":total[8],"field_7_4_2":sums[8],
                      "field_8_1":'--',"field_8_2_1":total[9],"field_8_2_2":sums[9],"field_8_3_1":total[10],"field_8_3_2":sums[10],"field_8_4_1":total[11],"field_8_4_2":sums[11],
                      "field_9_1":'--',"field_9_2_1":total[12],"field_9_2_2":sums[12],"field_9_3_1":total[13],"field_9_3_2":sums[13],"field_9_4_1":total[14],"field_9_4_2":sums[14],
                      "field_10_1":'--',"field_10_2_1":total[15],"field_10_2_2":sums[15],"field_10_3_1":total[16],"field_10_3_2":sums[16],"field_10_4_1":total[17],"field_10_4_2":sums[17],
                      "field_11_1":'--',"field_11_2_1":total[18],"field_11_2_2":sums[18], "field_11_3_1":total[19],"field_11_3_2":sums[19],"field_11_4_1":total[20],"field_11_4_2":sums[20],
                      "field_12_1":'--',"field_12_2_1":total[21],"field_12_2_2":sums[21], "field_12_3_1":total[22],"field_12_3_2":sums[22],"field_12_4_1":total[23],"field_12_4_2":sums[23],
                      "field_13_1":'--',"field_13_2_1":total[24],"field_13_2_2":sums[24], "field_13_3_1":total[25],"field_13_3_2":sums[25],"field_13_4_1":total[26],"field_13_4_2":sums[26],
                      "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
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
      const th1 = [[this.tableLabel, '', '', '','','','','','','','', '','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',],
                    ['县（市、区）', '地区类型', '稻作类型', '全县总面积（万亩）','进程1（移栽后20天）','','','','','','', '进程2（移栽后28天）','','','','','','',
                    '进程3（移栽后35天）','','','','','','','进程4（移栽后42天）','','','','','','','进程5（移栽后49天）','','','','','','','进程6（移栽后56天）','','','','','','','进程7（移栽后66天）','','','','','','',
                    '进程8（移栽后76天）','','','','','','','进程9（移栽后86天）','','','','','','',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['','', '', '','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）',''
      ,'日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','',
      '日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','','日期','一类苗（万亩）','','二类苗（万亩）','','三类苗（万亩）','' ]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4','field_5_1', 'field_5_2_1', 'field_5_2_2', 'field_5_3_1', 'field_5_3_2', 'field_5_4_1', 'field_5_4_2', 'field_6_1', 'field_6_2_1', 'field_6_2_2', 'field_6_3_1', 'field_6_3_2', 'field_6_4_1', 'field_6_4_2',
      'field_7_1', 'field_7_2_1', 'field_7_2_2', 'field_7_3_1', 'field_7_3_2', 'field_7_4_1', 'field_7_4_2','field_8_1', 'field_8_2_1', 'field_8_2_2', 'field_8_3_1', 'field_8_3_2', 'field_8_4_1', 'field_8_4_2',
      'field_9_1', 'field_9_2_1', 'field_9_2_2', 'field_9_3_1', 'field_9_3_2', 'field_9_4_1', 'field_9_4_2', 'field_10_1', 'field_10_2_1', 'field_10_2_2', 'field_10_3_1', 'field_10_3_2', 'field_10_4_1', 'field_10_4_2',
      'field_11_1', 'field_11_2_1', 'field_11_2_2', 'field_11_3_1', 'field_11_3_2', 'field_11_4_1', 'field_11_4_2','field_12_1', 'field_12_2_1', 'field_12_2_2', 'field_12_3_1', 'field_12_3_2', 'field_12_4_1', 'field_12_4_2',
      'field_13_1', 'field_13_2_1', 'field_13_2_2', 'field_13_3_1', 'field_13_3_2', 'field_13_4_1', 'field_13_4_2'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:BO1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:K2",
        "L2:R2",
        "S2:Y2",
        "Z2:AF2",
        "AG2:AM2",
        "AN2:AT2",
        "AU2:BA2",
        "BB2:BH2",
        "BI2:BO2",
        "F3:G3",
        "H3:I3",
        "J3:K3",
        "M3:N3",
        "O3:P3",
        "Q3:R3",
        "T3:U3",
        "V3:W3",
        "X3:Y3",
        "AA3:AB3",
        "AC3:AD3",
        "AE3:AF3",
        "AH3:AI3",
        "AJ3:AK3",
        "AL3:AM3",
        "AO3:AP3",
        "AQ3:AR3",
        "AS3:AT3",
        "AV3:AW3",
        "AX3:AY3",
        "AZ3:BA3",
        "BC3:BD3",
        "BE3:BF3",
        "BG3:BH3",
        "BJ3:BK3",
        "BL3:BM3",
        "BN3:BO3",
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



