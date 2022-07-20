<template>
  
    <div class="totalRange">
      <el-tabs v-model="activeName">
        <!-- <el-tab-pane label="受灾统计1-2" name="1"> -->
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
                  <el-table-column align="center" fixed type="index" :index="countMethod"  label="序号" />
                  <el-table-column align="center" prop="field_1"  label="县（市、区）" fixed=""/>
                  <el-table-column align="center" prop="field_2"  label="地区类型" fixed=""/>
                  <el-table-column align="center" prop="field_3"  label="稻作类型" />
                  <el-table-column align="center" prop="field_4"  label="品系类型" />
                  <el-table-column align="center" label="受灾统计表（万亩）">
                    <el-table-column align="center" prop="field_5_1"  label="填报日期" />
                    <el-table-column align="center" prop="field_5_2"  label="生育阶段与灾害症状" />
                    <el-table-column align="center" prop="field_5_3"  label="受灾面积" />
                    <el-table-column align="center" prop="field_5_4"  label="其中：成灾面积" />
                    <el-table-column align="center" prop="field_5_5"  label="其中：绝收面积" />
                    <el-table-column align="center" prop="field_5_6"  label="需补改种面积" />
                    <el-table-column align="center" prop="field_5_7"  label="已补改种面积" />
                  </el-table-column>
                  <el-table-column align="center" label="受灾统计表（万亩）">
                    <el-table-column align="center" prop="field_5_1"  label="填报日期" />
                    <el-table-column align="center" prop="field_5_2"  label="生育进程、阶段" />
                    <el-table-column align="center" prop="field_5_3"  label="受灾面积" />
                    <el-table-column align="center" prop="field_5_4"  label="其中：成灾面积" />
                    <el-table-column align="center" prop="field_5_5"  label="其中：绝收面积" />
                    <el-table-column align="center" prop="field_5_6"  label="需补改种面积" />
                    <el-table-column align="center" prop="field_5_7"  label="已补改种面积" />
                  </el-table-column>
                  <el-table-column align="center" label="受灾统计表（万亩）">
                    <el-table-column align="center" prop="field_5_1"  label="填报日期" />
                    <el-table-column align="center" prop="field_5_2"  label="生育进程、阶段" />
                    <el-table-column align="center" prop="field_5_3"  label="受灾面积" />
                    <el-table-column align="center" prop="field_5_4"  label="其中：成灾面积" />
                    <el-table-column align="center" prop="field_5_5"  label="其中：绝收面积" />
                    <el-table-column align="center" prop="field_5_6"  label="需补改种面积" />
                    <el-table-column align="center" prop="field_5_7"  label="已补改种面积" />
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
        <!-- <el-tab-pane label="受灾统计3" name="2"> -->
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
      tableLabel:'监测县(市、区)水稻涝害调查表',
      tableName:'crop_01_01_table_09_06',
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
      beginYear:'2021',
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
        url:'/getAllData0906',
        method:'get',
        params:{
          people:people,
          column_id:'',
          beginYear:beginYear,
          endYear:endYear

        }
      }).then(res=>{
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
        
            _this.dataNum = totalTableData.length ;
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
          //品系数据
          var categoryData = [];
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
          };
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;
          _this.categorys = categoryData;
          //完整获取稻作类型
          _this.getCountySelection1();

       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取完整稻作类型下拉框内容
    getCountySelection1(){
      const _this = this;
      axios({
          url: '/getCounty2',
          method:'get',
       }).then(res=>{
          var optionData=[];
          optionData=res.data;
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "稻作类型"){
                _this.riceTypes.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };
          //console.log(_this.riceTypes);
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
      this.areaType='';
      this.county='';
      this.riceType='';
      this.category='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfo96',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                category:this.category,
                year:years,
        }
      }).then(res=>{
         
         // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;var num2=0;var num3=0;var num4=0;var num5=0;var num6=0;var num7=0;var num8=0;var num9=0;var num10=0;
            var num11=0;var num12=0;var num13=0;var num14=0;var num15=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_5_3 === ''){
                totalTableData[i].field_5_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_5_3*1);

              if(totalTableData[i].field_5_4 === ''){
                totalTableData[i].field_5_4 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_5_4*1);

              if(totalTableData[i].field_5_5 === ''){
                totalTableData[i].field_5_5 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_5_5*1);

              if(totalTableData[i].field_5_6 === ''){
                totalTableData[i].field_5_6 = 0;
              }
              num4 = _this.add(num4*1+totalTableData[i].field_5_6*1);

              if(totalTableData[i].field_5_7 === ''){
                totalTableData[i].ffield_5_7 = 0;
              }
              num5 = _this.add(num5*1+totalTableData[i].field_5_7*1);

              if(totalTableData[i].field_6_3 === ''){
                totalTableData[i].field_6_3 = 0;
              }
              num6 = _this.add(num6*1+totalTableData[i].field_6_3*1);

              if(totalTableData[i].field_6_4 === ''){
                totalTableData[i].ffield_6_4 = 0;
              }
              num7 = _this.add(num7*1+totalTableData[i].field_6_4*1);

              if(totalTableData[i].field_6_5 === ''){
                totalTableData[i].field_6_5 = 0;
              }
              num8 = _this.add(num8*1+totalTableData[i].field_6_5*1);

              if(totalTableData[i].ffield_6_6 === ''){
                totalTableData[i].field_6_6 = 0;
              }
              num9 = _this.add(num9*1+totalTableData[i].field_6_6*1);

              if(totalTableData[i].field_6_7 === ''){
                totalTableData[i].field_6_7 = 0;
              }
              num10 = _this.add(num10*1+totalTableData[i].field_6_7*1);

              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num11 = _this.add(num11*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_7_4 === ''){
                totalTableData[i].field_7_4 = 0;
              }
              num12 = _this.add(num12*1+totalTableData[i].field_7_4*1);

              if(totalTableData[i].field_7_5 === ''){
                totalTableData[i].field_7_5 = 0;
              }
              num13 = _this.add(num13*1+totalTableData[i].field_7_5*1);

              if(totalTableData[i].field_7_6 === ''){
                totalTableData[i].field_7_6 = 0;
              }
              num14 = _this.add(num14*1+totalTableData[i].field_7_6*1);

              if(totalTableData[i].field_7_7 === ''){
                totalTableData[i].field_7_7 = 0;
              }
              num15 = _this.add(num15*1+totalTableData[i].field_7_7*1);
            };
            //各品种面积总和
            //console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;total[3]=num4;total[4]=num5;total[5]=num6;total[6]=num7;total[7]=num8;total[8]=num9;total[9]=num10;
            total[10]=num11;total[11]=num12;total[12]=num13;total[13]=num14;total[14]=num15;
        
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',
                                "field_5_1":'--',"field_5_2":'--',"field_5_3":total[0],"field_5_4":total[1],"field_5_5":total[2],"field_5_6":total[3],"field_5_7":total[4],
                                "field_6_1":'--',"field_6_2":'--',"field_6_3":total[5],"field_6_4":total[6],"field_6_5":total[7],"field_6_6":total[8],"field_6_7":total[9],
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":total[10],"field_7_4":total[11],"field_7_5":total[12],"field_7_6":total[13],"field_7_7":total[14],
                                
                                "in_use":'--'})
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



