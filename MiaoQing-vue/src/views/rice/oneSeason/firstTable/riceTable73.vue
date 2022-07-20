<template>
  
    <div class="totalRange">
      <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <el-col :span="6">
                  <template>
                    <el-select v-model="areaType" clearable  placeholder="地区类型" style="width:150px">
                      <el-option
                        v-for="item in areaTypes"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>

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
              <el-col :span="6">
                <el-button  type="info" plain @click="getDataAnalyse">数据分析</el-button>
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
                  <el-table-column align="center" fixed type="index" :index="countMethod" width="50%" label="序号" />
                  <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed/>
                  <el-table-column align="center" prop="field_2" width="100%" label="地区类型" fixed/>
                  <el-table-column align="center" label="全县面上平均水平">
                    <el-table-column align="center" prop="field_3_1" width="110%" label="总面积（万亩）" />
                    <el-table-column align="center" prop="field_3_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_3_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_3_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_3_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_3_6" width="150%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="人工移栽">
                    <el-table-column align="center" prop="field_4_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_4_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_4_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_4_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_4_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_4_6" width="150%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="机械插秧">
                    <el-table-column align="center" prop="field_5_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_5_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_5_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_5_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_5_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_5_6" width="140%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="盘育抛秧">
                    <el-table-column align="center" prop="field_6_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_6_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_6_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_6_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_6_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_6_6" width="140%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="无盘旱育抛秧">
                    <el-table-column align="center" prop="field_7_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_7_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_7_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_7_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_7_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_7_6" width="140%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="撒直播">
                    <el-table-column align="center" prop="field_8_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_8_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_8_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_8_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_8_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_8_6" width="140%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center" label="机条播">
                    <el-table-column align="center" prop="field_9_1" width="110%" label="面积（万亩）" />
                    <el-table-column align="center" prop="field_9_2" width="110%" label="亩有效穗（万）" />
                    <el-table-column align="center" prop="field_9_3" width="110%" label="每穗粒数（粒）" />
                    <el-table-column align="center" prop="field_9_4" width="100%" label="结实率（％）" />
                    <el-table-column align="center" prop="field_9_5" width="100%" label="千粒重（g）" />
                    <el-table-column align="center" prop="field_9_6" width="140%" label="八五折理论产量（kg/亩）" />
                  </el-table-column>
                  <el-table-column align="center"  fixed="right" width="120px" label="操作" v-if="years == currentyear">
                    <template slot-scope="scope">
                      <el-button type="danger" size="mini" v-if="scope.row.in_use == 3" @click="backRevision(scope.$index,scope.row)" >退回修改</el-button>
                    </template>
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
      tableName:'crop_01_01_table_07_03',
      //县的option
      countys:[],
      county: '',
      //地区类型option
      areaTypes:[],
      areaType: '',
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
      this.tableLabel= this.years+"年度监测县(市、区)水稻单季稻测产表";
      this.getTableData();
      this.getCountySelection();
    }, 
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex === 0){
            return{height:'0',padding:'1px',fontSize:'5px',background:'#6E8B3D',color:'#FFFFFF'};
        }
        if(rowIndex==1 ){
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
    getTableData(){ 
       const _this = this;
       axios({
          url: '/getRiceTable73',
          method:'post',
          params:{
            county:this.citys,
            areaType:this.areas,
            year:this.years
           }
       }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            var num41=0;var num42=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_3_1 === ''){
                totalTableData[i].field_3_1 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_3_1*1);

              if(totalTableData[i].field_3_2 === ''){
                totalTableData[i].field_3_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_3_2*1);

              if(totalTableData[i].field_3_3 === ''){
                totalTableData[i].field_3_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_3_3*1);

              if(totalTableData[i].field_3_4 === ''){
                totalTableData[i].field_3_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_3_4*1);

              if(totalTableData[i].field_3_5 === ''){
                totalTableData[i].field_3_5 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_5*1);

              if(totalTableData[i].field_3_6 === ''){
                totalTableData[i].field_3_6 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_6*1);

              if(totalTableData[i].field_4_1 === ''){
                totalTableData[i].field_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_4_1*1);

              if(totalTableData[i].field_4_2 === ''){
                totalTableData[i].field_4_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_4_2*1);

              if(totalTableData[i].field_4_3 === ''){
                totalTableData[i].field_4_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_4_3*1);

              if(totalTableData[i].field_4_4 === ''){
                totalTableData[i].field_4_4 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_4_4*1);

              if(totalTableData[i].field_4_5 === ''){
                totalTableData[i].field_4_5 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_4_5*1);

              if(totalTableData[i].field_4_6 === ''){
                totalTableData[i].field_4_6 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_4_6*1);

              if(totalTableData[i].field_5_1 === ''){
                totalTableData[i].field_5_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_5_1*1);

              if(totalTableData[i].field_5_2 === ''){
                totalTableData[i].field_5_2 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_5_2*1);

              if(totalTableData[i].field_5_3 === ''){
                totalTableData[i].field_5_3 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_5_3*1);

              if(totalTableData[i].field_5_4 === ''){
                totalTableData[i].field_5_4 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_5_4*1);

              if(totalTableData[i].field_5_5 === ''){
                totalTableData[i].field_5_5 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_5_5*1);

              if(totalTableData[i].field_5_6 === ''){
                totalTableData[i].field_5_6 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_5_6*1);

              if(totalTableData[i].field_6_1 === ''){
                totalTableData[i].field_6_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_6_1*1);

              if(totalTableData[i].field_6_2 === ''){
                totalTableData[i].field_6_2 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_6_2*1);
              
              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].field_6_4 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].field_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_6_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5=== ''){
                totalTableData[i].field_7_5 = 0;
              }
              num29 = _this.add(num29*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num30 = _this.add(num30*1+totalTableData[i].field_7_6*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num31 = _this.add(num31*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num32 = _this.add(num32*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num33 = _this.add(num33*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num34 = _this.add(num34*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_8_5 === ''){
                totalTableData[i].field_8_5 = 0;
              }
              num35 = _this.add(num35*1+totalTableData[i].field_8_5*1);

              if(totalTableData[i].field_8_6 === ''){
                totalTableData[i].field_8_6 = 0;
              }
              num36 = _this.add(num36*1+totalTableData[i].field_8_6*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num37 = _this.add(num37*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num38 = _this.add(num38*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num39 = _this.add(num39*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num40 = _this.add(num40*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num41 = _this.add(num41*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_9_6 === ''){
                totalTableData[i].field_9_6 = 0;
              }
              num42 = _this.add(num42*1+totalTableData[i].field_9_6*1);
            };
            //各品种面积总和
            //console.log('1111');
            sums2[0]=num1;sums2[1]=num7;sums2[2]=num13;sums2[3]=num19;sums2[4]=num25;sums2[5]=num31;sums2[6]=num37;
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num8;total[6]=num9;total[7]=num10;total[8]=num11;total[9]=num12;
            total[10]=num14;total[11]=num15;total[12]=num16;total[13]=num17;total[14]=num18;   total[15]=num20;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
            total[20]=num26;total[21]=num27;total[22]=num28;total[23]=num29;total[24]=num30;   total[25]=num32;total[26]=num33;total[27]=num34;total[28]=num35;total[29]=num36;
            total[30]=num38;total[31]=num39;total[32]=num40;total[33]=num41;total[34]=num42;
            //console.log(sums2);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(num1===0){
                total[i]='--';
                sums2[i]='--';
                sums1[i]='--';
              }else if(total[i] === 0){
                sums1[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            //console.log(sums1);
            for(var i=0;i<sums2.length;i++){
              if(sums2[i]===0){
                sums2[i] ='--';
              }
            }
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',
                                "field_3_1":sums2[0],"field_3_2":sums1[0],"field_3_3":sums1[1],"field_3_4":sums1[2],"field_3_5":sums1[3],"field_3_6":sums1[4],
                                "field_4_1":sums2[1],"field_4_2":sums1[5],"field_4_3":sums1[6],"field_4_4":sums1[7],"field_4_5":sums1[8],"field_4_6":sums1[9],
                                "field_5_1":sums2[2],"field_5_2":sums1[10],"field_5_3":sums1[11],"field_5_4":sums1[12],"field_5_5":sums1[13],"field_5_6":sums1[14],
                                "field_6_1":sums2[3],"field_6_2":sums1[15],"field_6_3":sums1[16],"field_6_4":sums1[17],"field_6_5":sums1[18],"field_6_6":sums1[19],
                                "field_7_1":sums2[4],"field_7_2":sums1[20],"field_7_3":sums1[21],"field_7_4":sums1[22],"field_7_5":sums1[23],"field_7_6":sums1[24],
                                "field_8_1":sums2[5],"field_8_2":sums1[25],"field_8_3":sums1[26],"field_8_4":sums1[27],"field_8_5":sums1[28],"field_8_6":sums1[29],
                                "field_9_1":sums2[6],"field_9_2":sums1[30],"field_9_3":sums1[31],"field_9_4":sums1[32],"field_9_5":sums1[33],"field_9_6":sums1[34],
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
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "地区类型"){
                areaData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys = countyData;
          _this.areaTypes = areaData;
       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
     
    //数据分析图跳转
    getDataAnalyse(){
      this.$router.replace('/riceAnalysis');
      sessionStorage.setItem("years",this.years);
    },
    //重置
    reSet(){
      this.areaType='';
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){ 
      const _this = this;
      axios({
        url:'/getSeekInfo73',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                year:this.years,
        }
      }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums1 = [];//存平均
            var sums2 = [];//存面积合计
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            var num41=0;var num42=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_3_1 === ''){
                totalTableData[i].field_3_1 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_3_1*1);

              if(totalTableData[i].field_3_2 === ''){
                totalTableData[i].field_3_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_3_2*1);

              if(totalTableData[i].field_3_3 === ''){
                totalTableData[i].field_3_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_3_3*1);

              if(totalTableData[i].field_3_4 === ''){
                totalTableData[i].field_3_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_3_4*1);

              if(totalTableData[i].field_3_5 === ''){
                totalTableData[i].field_3_5 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_5*1);

              if(totalTableData[i].field_3_6 === ''){
                totalTableData[i].field_3_6 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_6*1);

              if(totalTableData[i].field_4_1 === ''){
                totalTableData[i].field_4_1 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_4_1*1);

              if(totalTableData[i].field_4_2 === ''){
                totalTableData[i].field_4_2 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_4_2*1);

              if(totalTableData[i].field_4_3 === ''){
                totalTableData[i].field_4_3 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_4_3*1);

              if(totalTableData[i].field_4_4 === ''){
                totalTableData[i].field_4_4 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_4_4*1);

              if(totalTableData[i].field_4_5 === ''){
                totalTableData[i].field_4_5 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_4_5*1);

              if(totalTableData[i].field_4_6 === ''){
                totalTableData[i].field_4_6 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_4_6*1);

              if(totalTableData[i].field_5_1 === ''){
                totalTableData[i].field_5_1 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_5_1*1);

              if(totalTableData[i].field_5_2 === ''){
                totalTableData[i].field_5_2 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_5_2*1);

              if(totalTableData[i].field_5_3 === ''){
                totalTableData[i].field_5_3 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_5_3*1);

              if(totalTableData[i].field_5_4 === ''){
                totalTableData[i].field_5_4 = 0;
              }
              num16 = _this.add(num16*1+totalTableData[i].field_5_4*1);

              if(totalTableData[i].field_5_5 === ''){
                totalTableData[i].field_5_5 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_5_5*1);

              if(totalTableData[i].field_5_6 === ''){
                totalTableData[i].field_5_6 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_5_6*1);

              if(totalTableData[i].field_6_1 === ''){
                totalTableData[i].field_6_1 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_6_1*1);

              if(totalTableData[i].field_6_2 === ''){
                totalTableData[i].field_6_2 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_6_2*1);
              
              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].field_6_4 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].field_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_6_6*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5=== ''){
                totalTableData[i].field_7_5 = 0;
              }
              num29 = _this.add(num29*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num30 = _this.add(num30*1+totalTableData[i].field_7_6*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num31 = _this.add(num31*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num32 = _this.add(num32*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num33 = _this.add(num33*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].field_8_4 = 0;
              }
              num34 = _this.add(num34*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_8_5 === ''){
                totalTableData[i].field_8_5 = 0;
              }
              num35 = _this.add(num35*1+totalTableData[i].field_8_5*1);

              if(totalTableData[i].field_8_6 === ''){
                totalTableData[i].field_8_6 = 0;
              }
              num36 = _this.add(num36*1+totalTableData[i].field_8_6*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num37 = _this.add(num37*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num38 = _this.add(num38*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num39 = _this.add(num39*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num40 = _this.add(num40*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_9_5 === ''){
                totalTableData[i].field_9_5 = 0;
              }
              num41 = _this.add(num41*1+totalTableData[i].field_9_5*1);

              if(totalTableData[i].field_9_6 === ''){
                totalTableData[i].field_9_6 = 0;
              }
              num42 = _this.add(num42*1+totalTableData[i].field_9_6*1);
            };
            //各品种面积总和
            //console.log('1111');
            sums2[0]=num1;sums2[1]=num7;sums2[2]=num13;sums2[3]=num19;sums2[4]=num25;sums2[5]=num31;sums2[6]=num37;
            total[0]=num2;total[1]=num3;total[2]=num4;total[3]=num5;total[4]=num6;   total[5]=num8;total[6]=num9;total[7]=num10;total[8]=num11;total[9]=num12;
            total[10]=num14;total[11]=num15;total[12]=num16;total[13]=num17;total[14]=num18;   total[15]=num20;total[16]=num21;total[17]=num22;total[18]=num23;total[19]=num24;
            total[20]=num26;total[21]=num27;total[22]=num28;total[23]=num29;total[24]=num30;   total[25]=num32;total[26]=num33;total[27]=num34;total[28]=num35;total[29]=num36;
            total[30]=num38;total[31]=num39;total[32]=num40;total[33]=num41;total[34]=num42;
            //console.log(sums2);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(num1===0){
                total[i]='--';
                sums2[i]='--';
                sums1[i]='--';
              }else if(total[i] === 0){
                sums1[i]='--';
              }else{
                sums1[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            //console.log(sums1);
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',
                                "field_3_1":sums2[0],"field_3_2":sums1[0],"field_3_3":sums1[1],"field_3_4":sums1[2],"field_3_5":sums1[3],"field_3_6":sums1[4],
                                "field_4_1":sums2[1],"field_4_2":sums1[5],"field_4_3":sums1[6],"field_4_4":sums1[7],"field_4_5":sums1[8],"field_4_6":sums1[9],
                                "field_5_1":sums2[2],"field_5_2":sums1[10],"field_5_3":sums1[11],"field_5_4":sums1[12],"field_5_5":sums1[13],"field_5_6":sums1[14],
                                "field_6_1":sums2[3],"field_6_2":sums1[15],"field_6_3":sums1[16],"field_6_4":sums1[17],"field_6_5":sums1[18],"field_6_6":sums1[19],
                                "field_7_1":sums2[4],"field_7_2":sums1[20],"field_7_3":sums1[21],"field_7_4":sums1[22],"field_7_5":sums1[23],"field_7_6":sums1[24],
                                "field_8_1":sums2[5],"field_8_2":sums1[25],"field_8_3":sums1[26],"field_8_4":sums1[27],"field_8_5":sums1[28],"field_8_6":sums1[29],
                                "field_9_1":sums2[6],"field_9_2":sums1[30],"field_9_3":sums1[31],"field_9_4":sums1[32],"field_9_5":sums1[33],"field_9_6":sums1[34],
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
    //分页
    handleCurrentChange:function(currentPage){
      this.currentPage = currentPage;
      //console.log(this.currentPage);
    },
    //分页后序号连续处理
    countMethod(index){
       return (this.currentPage-1)*this.pagesize + index +1;
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
      const th1 = [[this.tableLabel, '','', '', '', '', '', '','', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '','', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',],
                    ['县（市、区）', '地区类型',
                     '全县面上平均水平', '', '', '', '', '',
                     '人工移栽', '', '', '', '', '',
                     '机械插秧', '', '', '', '', '',
                     '盘育抛秧', '', '', '', '', '',
                     '无盘旱育抛秧', '', '', '', '', '',
                     '撒直播', '', '', '', '', '',
                     '机条播', '', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',
                     '总面积（万亩）', '亩有效穗（万）', '每穗粒数（粒）', '结实率（%）', '千粒重（克）', '八五折理论产量（公斤）',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2',
                     'field_3_1', 'field_3_2', 'field_3_3', 'field_3_4', 'field_3_5', 'field_3_6',
                     'field_4_1', 'field_4_2', 'field_4_3', 'field_4_4', 'field_4_5', 'field_4_6',
                     'field_5_1', 'field_5_2', 'field_5_3', 'field_5_4', 'field_5_5', 'field_5_6',
                     'field_6_1', 'field_6_2', 'field_6_3', 'field_6_4', 'field_6_5', 'field_6_6',
                     'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 'field_7_6',
                     'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 'field_8_6',
                     'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5', 'field_9_6',] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AR1",
        "A2:A3",
        "B2:B3",
        "C2:H2",
        "I2:N2",
        "O2:T2",
        "U2:Z2",
        "AA2:AF2",
        "AG2:AL2",
        "AM2:AR2",
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



