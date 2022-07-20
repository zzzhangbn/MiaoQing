<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <!-- <el-tab-pane label="天气1-4月" name="1"> -->
          <div class="tableRange">
            <div class="searchBox">
              <el-row>
                <el-col >
                  <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                    <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                    <YearPicker  v-model="beginYear" @input="changeYear1"/><span style="width: 10px;margin-right: -20px;color: gray;">~</span><YearPicker  v-model="endYear" @input="changeYear2"/>
                    <el-button   style="margin-right: 20px" @click="getDataComparison('')">查询对比</el-button>
                    <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
                      <el-button type="info" plain>返回</el-button>
                    </router-link>
                  </div>
                </el-col>
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
                  <el-table-column align="center" prop="field_1" label="县（市、区）" fixed=""/>
                  <el-table-column align="center" label="1月份">
                    <el-table-column align="center" prop="field_2_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_2_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_2_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_2_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="2月份">
                    <el-table-column align="center" prop="field_3_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_3_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_3_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_3_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="3月份">
                    <el-table-column align="center" prop="field_4_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_4_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_4_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_4_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="4月份">
                    <el-table-column align="center" prop="field_5_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_5_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_5_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_5_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="5月份">
                    <el-table-column align="center" prop="field_6_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_6_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_6_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_6_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="6月份">
                    <el-table-column align="center" prop="field_7_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_7_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_7_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_7_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="7月份">
                    <el-table-column align="center" prop="field_8_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_8_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_8_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_8_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="8月份">
                    <el-table-column align="center" prop="field_9_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_9_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_9_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_9_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="9月份">
                    <el-table-column align="center" prop="field_10_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_10_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_10_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_10_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="10月份">
                    <el-table-column align="center" prop="field_11_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_11_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_11_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_11_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="11月份">
                    <el-table-column align="center" prop="field_12_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_12_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_12_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_12_4" label="降雨量（mm）" />
                  </el-table-column>
                  <el-table-column align="center" label="12月份">
                    <el-table-column align="center" prop="field_13_1" label="平均温度（℃）" />
                    <el-table-column align="center" prop="field_13_2" label="有效积温（℃）" />
                    <el-table-column align="center" prop="field_13_3" label="光照时数（h）" />
                    <el-table-column align="center" prop="field_13_4" label="降雨量（mm）" />
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
        <!-- <el-tab-pane label="天气5-8月" name="2"> -->

        <!-- </el-tab-pane> -->
        <!-- <el-tab-pane label="天气9-12月" name="3"> -->

        <!-- </el-tab-pane> -->
      </el-tabs>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
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
      tableLabel:'监测县(市、区)气象资料表',
      tableName:'crop_01_01_table_09_01',
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
      beginYear:'2020',
      endYear:'2022',
      column_id:'',
      point:'',
      people:'',
    };
  },
  created() {
    this.initData();
    sessionStorage.removeItem("path");
    sessionStorage.setItem("path","/dataHome1");
  },
  methods: {
    //初始化数据
    initData() {
      this.people = localStorage.getItem('ms_username');
      this.getTableData(this.people,this.beginYear,this.endYear);
    },
    //获取数据对比
    getDataComparison(title){
      if(this.beginYear != '' && this.endYear != ''){
        this.getTableData(this.people,this.beginYear,this.endYear);
      }
    },
    //改变开始年份
    changeYear1(){
      if(this.beginYear != '' && this.endYear !=''){
        if(this.beginYear > this.endYear){
          this.beginYear = '';
          this.$message.warning("该年份不可选择！");
        }
      }
      // //console.log(this.beginYear);
    } ,
    //改变结束年份
    changeYear2(){
      var date = new Date();
      var currentYear = date .getFullYear();
      if(this.endYear > currentYear){
        this.endYear = currentYear;
        return this.$message.warning("不可选择该年份！");
      }
      if(this.beginYear > this.endYear){
        this.beginYear = '';
        return this.$message.warning("开始年份不可大于结束年份！");
      }
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
    getTableData(people,beginYear,endYear,title){
      const _this = this;
      axios({
        url:'/getAllData0901',
        method:'get',
        params:{
          people:people,
          column_id:'',
          beginYear:beginYear,
          endYear:endYear
        }
      }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            this.dataNum = totalTableData.length;
          }else{
            this.dataNum = 0;
          }; 
          this.tableData = totalTableData;
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
          ////console.log(optionData);
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          _this.countys = countyData;

       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    
    //审核状态
    auditState(row,column){
      if(row.in_use === '3'){
        return "通过";
      }else if(row.in_use === '1'){
        return "未审核";
      }else if(row.in_use === '2'){
        return "已退回";
      }else if(row.in_use === '--'){
        return "--";
      }
    },
    //退还对话框
    reasonDialog(index,row){
      this.dialogVisible=true;
      this.rowRecord = row;
    },
    // 退还记录确定
    returnData(){
      const _this=this;
      this.dialogVisible = false;
      //console.log(this.rowRecord);
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.rowRecord.id;
      var recordPeople = this.rowRecord.people;
      var recordReason = this.reasonForm.reason;
      var returnTableName = this.tableName;
      //console.log(recordTime,recordId,recordPeople,recordReason);
      
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordId:recordId,
                recordReason:recordReason,
                recordTime:recordTime,
                recordPeople:recordPeople
                }
      }).then(res=>{
          //console.log(res.data);
           _this.getTableData();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
       this.reload();
       this.reasonForm.reason='';
    },
    //数据记录审核通过
    confirmRecord(row){
      const _this=this;
      var returnTableName = this.tableName;
      var recordId = row.id;
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordId:recordId}
      }).then(res=>{
        //console.log(res.data);
        _this.getTableData();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
     
      this.reload();
    },
    //重置
    reSet(){
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfo91',
        method:'post',
        params:{county:this.county,
                year:years,
        }
      }).then(res=>{
         //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;var num16=0;var num17=0;var num18=0;var num19=0;var num20=0;
            var num21=0;var num22=0;var num23=0;var num24=0;var num25=0;var num26=0;var num27=0;var num28=0;var num29=0;var num30=0;
            var num31=0;var num32=0;var num33=0;var num34=0;var num35=0;var num36=0;var num37=0;var num38=0;var num39=0;var num40=0;
            var num41=0;var num42=0;var num43=0;var num44=0;var num45=0;var num46=0;var num47=0;var num48=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_2_1 === ''){
                totalTableData[i].field_2_1 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_2_1*1);

              if(totalTableData[i].field_2_2 === ''){
                totalTableData[i].field_2_2 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_2_2*1);

              if(totalTableData[i].field_2_3 === ''){
                totalTableData[i].field_2_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_2_3*1);

              if(totalTableData[i].field_2_4 === ''){
                totalTableData[i].field_2_4 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_2_4*1);

              if(totalTableData[i].field_3_1 === ''){
                totalTableData[i].field_3_1 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_3_1*1);

              if(totalTableData[i].field_3_2 === ''){
                totalTableData[i].field_3_2 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_3_2*1);

              if(totalTableData[i].field_3_3 === ''){
                totalTableData[i].field_3_3 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_3_3*1);

              if(totalTableData[i].field_3_4 === ''){
                totalTableData[i].field_3_4 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_3_4*1);

              if(totalTableData[i].field_4_1 === ''){
                totalTableData[i].field_4_1 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_4_1*1);

              if(totalTableData[i].field_4_2 === ''){
                totalTableData[i].field_4_2 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_4_2*1);

              if(totalTableData[i].field_4_3 === ''){
                totalTableData[i].field_4_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_4_3*1);

              if(totalTableData[i].field_4_4 === ''){
                totalTableData[i].field_4_4 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_4_4*1);

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

              if(totalTableData[i].field_6_1 === ''){
                totalTableData[i].field_6_1 = 0;
              }
              num17 = _this.add(num17*1+totalTableData[i].field_6_1*1);

              if(totalTableData[i].field_6_2 === ''){
                totalTableData[i].field_6_2 = 0;
              }
              num18 = _this.add(num18*1+totalTableData[i].field_6_2*1);

              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num19 = _this.add(num19*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].field_6_4 = 0;
              }
              num20 = _this.add(num20*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_7_1 === ''){
                totalTableData[i].field_7_1 = 0;
              }
              num21 = _this.add(num21*1+totalTableData[i].field_7_1*1);

              if(totalTableData[i].field_7_2 === ''){
                totalTableData[i].field_7_2 = 0;
              }
              num22 = _this.add(num22*1+totalTableData[i].field_7_2*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num23 = _this.add(num23*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num24 = _this.add(num24*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_8_1 === ''){
                totalTableData[i].field_8_1 = 0;
              }
              num25 = _this.add(num25*1+totalTableData[i].field_8_1*1);

              if(totalTableData[i].field_8_2 === ''){
                totalTableData[i].field_8_2 = 0;
              }
              num26 = _this.add(num26*1+totalTableData[i].field_8_2*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num27 = _this.add(num27*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_8_4 === ''){
                totalTableData[i].fifield_8_4 = 0;
              }
              num28 = _this.add(num28*1+totalTableData[i].field_8_4*1);

              if(totalTableData[i].field_9_1 === ''){
                totalTableData[i].field_9_1 = 0;
              }
              num29 = _this.add(num29*1+totalTableData[i].field_9_1*1);

              if(totalTableData[i].field_9_2 === ''){
                totalTableData[i].field_9_2 = 0;
              }
              num30 = _this.add(num30*1+totalTableData[i].field_9_2*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num31 = _this.add(num31*1+totalTableData[i].field_9_3*1);

              if(totalTableData[i].field_9_4 === ''){
                totalTableData[i].field_9_4 = 0;
              }
              num32 = _this.add(num32*1+totalTableData[i].field_9_4*1);

              if(totalTableData[i].field_10_1 === ''){
                totalTableData[i].field_10_1 = 0;
              }
              num33 = _this.add(num33*1+totalTableData[i].field_10_1*1);

              if(totalTableData[i].field_10_2 === ''){
                totalTableData[i].field_10_2 = 0;
              }
              num34 = _this.add(num34*1+totalTableData[i].field_10_2*1);

              if(totalTableData[i].field_10_3 === ''){
                totalTableData[i].field_10_3 = 0;
              }
              num35 = _this.add(num35*1+totalTableData[i].field_10_3*1);

              if(totalTableData[i].field_10_4 === ''){
                totalTableData[i].field_10_4 = 0;
              }
              num36 = _this.add(num36*1+totalTableData[i].field_10_4*1);

              if(totalTableData[i].field_11_1 === ''){
                totalTableData[i].field_11_1 = 0;
              }
              num37 = _this.add(num37*1+totalTableData[i].field_11_1*1);

              if(totalTableData[i].field_11_2 === ''){
                totalTableData[i].field_11_2 = 0;
              }
              num38 = _this.add(num38*1+totalTableData[i].field_11_2*1);

              if(totalTableData[i].field_11_3 === ''){
                totalTableData[i].field_11_3 = 0;
              }
              num39 = _this.add(num39*1+totalTableData[i].field_11_3*1);

              if(totalTableData[i].field_11_4 === ''){
                totalTableData[i].field_11_4 = 0;
              }
              num40 = _this.add(num40*1+totalTableData[i].field_11_4*1);

              if(totalTableData[i].field_12_1 === ''){
                totalTableData[i].field_12_1 = 0;
              }
              num41 = _this.add(num41*1+totalTableData[i].field_12_1*1);

              if(totalTableData[i].field_12_2 === ''){
                totalTableData[i].field_12_2 = 0;
              }
              num42 = _this.add(num42*1+totalTableData[i].field_12_2*1);

              if(totalTableData[i].field_12_3 === ''){
                totalTableData[i].field_12_3 = 0;
              }
              num43 = _this.add(num43*1+totalTableData[i].field_12_3*1);

              if(totalTableData[i].field_12_4 === ''){
                totalTableData[i].field_12_4 = 0;
              }
              num44 = _this.add(num44*1+totalTableData[i].field_12_4*1);

              if(totalTableData[i].field_13_1 === ''){
                totalTableData[i].field_13_1 = 0;
              }
              num45 = _this.add(num45*1+totalTableData[i].field_13_1*1);

              if(totalTableData[i].field_13_2 === ''){
                totalTableData[i].field_13_2 = 0;
              }
              num46 = _this.add(num46*1+totalTableData[i].field_13_2*1);

              if(totalTableData[i].field_13_3 === ''){
                totalTableData[i].field_13_3 = 0;
              }
              num47 = _this.add(num47*1+totalTableData[i].field_13_3*1);

              if(totalTableData[i].field_13_4 === ''){
                totalTableData[i].field_13_4 = 0;
              }
              num48 = _this.add(num48*1+totalTableData[i].field_13_4*1);
             
            };
            //各品种面积总和
            //console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;total[15]=num16;total[16]=num17;total[17]=num18;total[18]=num19;total[19]=num20;
            total[20]=num21;total[21]=num22;total[22]=num23;total[23]=num24;total[24]=num25;total[25]=num26;total[26]=num27;total[27]=num28;total[28]=num29;total[29]=num30;
            total[30]=num31;total[31]=num32;total[32]=num33;total[33]=num34;total[34]=num35;total[35]=num36;total[36]=num37;total[37]=num38;total[38]=num39;total[39]=num40;
            total[40]=num41;total[41]=num42;total[42]=num43;total[43]=num44;total[44]=num45;total[45]=num46;total[46]=num47;total[47]=num48;
            //console.log(total);
            var dataLength = totalTableData.length;
            for(var i=0; i<total.length; i++){
              if(total[i] === 0){
                sums[i]='--';
              }else{
                sums[i] = (Number(total[i])/Number(dataLength)).toFixed(2);
              }
            }
            //console.log(sums);
        
            //辅助数组
             totalTableData.push({"field_1":'合计',"field_2_1":sums[0],"field_2_2":sums[1],"field_2_3":sums[2],"field_2_4":sums[3],"field_3_1":sums[4],"field_3_2":sums[5],"field_3_3":sums[6],"field_3_4":sums[7],
                                "field_4_1":sums[8],"field_4_2":sums[9],"field_4_3":sums[10],"field_4_4":sums[10],"field_5_1":sums[12],"field_5_2":sums[13],"field_5_3":sums[14],"field_5_4":sums[15],
                                "field_6_1":sums[16],"field_6_2":sums[17],"field_6_3":sums[18],"field_6_4":sums[19],"field_7_1":sums[20],"field_7_2":sums[21],"field_7_3":sums[22],"field_7_4":sums[23],
                                "field_8_1":sums[24],"field_8_2":sums[25],"field_8_3":sums[26],"field_8_4":sums[27],"field_9_1":sums[28],"field_9_2":sums[29],"field_9_3":sums[30],"field_9_4":sums[31],
                                "field_10_1":sums[32],"field_10_2":sums[33],"field_10_3":sums[34],"field_10_4":sums[35],"field_11_1":sums[36],"field_11_2":sums[37],"field_11_3":sums[38],"field_11_4":sums[39],
                                "field_12_1":sums[40],"field_12_2":sums[41],"field_12_3":sums[45],"field_12_4":sums[43],"field_13_1":sums[44],"field_13_2":sums[45],"field_13_3":sums[46],"field_13_4":sums[47],
                                "in_use":'--'});
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          };
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



