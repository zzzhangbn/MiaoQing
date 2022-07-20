<template>
  
    <div class="totalRange">
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
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                <el-table-column align="center" prop="field_2"  label="地区类型" />
                <el-table-column align="center" prop="field_3"  label="稻作类型" />
                <el-table-column align="center" prop="field_4"  label="品系类型" />
                <el-table-column align="center" prop="field_5" label="低温冷害" />
                <el-table-column align="center" prop="field_6"  label="低温冷害发生时间" />
                <el-table-column align="center" prop="field_7"  label="全县种植面积（万亩）" />
                <el-table-column align="center" prop="field_8"  label="冷害总面积（万亩）" />
                <el-table-column align="center" label="冷害级别（万亩）" >
                  <el-table-column align="center" prop="field_9_1"  label="1级" />
                  <el-table-column align="center" prop="field_9_2"  label="2级" />
                  <el-table-column align="center" prop="field_9_3"  label="3级" />
                  <el-table-column align="center" prop="field_9_4"  label="4级" />
                  <el-table-column align="center" prop="field_9_5"  label="5级" />
                </el-table-column>
                <el-table-column align="center" prop="field_10" label="主要品种名称" />
                <el-table-column align="center" prop="field_11"  label="占比（%）" />
                <el-table-column align="center" prop="field_12"  label="其中：成灾面积（万亩）" />
                <el-table-column align="center" prop="field_13"  label="其中：绝收面积（万亩）" />
                <el-table-column align="center" prop="field_14"  label="产量损失（%）" />
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
      tableLabel:'监测县(市、区)水稻低温冷害表',
      tableName:'crop_01_01_table_09_04',
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
        url:'/getAllData0904',
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
          _this.dataNum = totalTableData.length;
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
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
        url:'/getSeekInfo94',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                category:this.category,
                year:years,
        }
      }).then(res=>{
           //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
          
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



