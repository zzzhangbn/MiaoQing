<template>
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">
            <el-row>
              <div style="margin-top: 2.3%;float: right;margin-right: 10px;margin-bottom: 10px">
                <!-- <el-button  type="info" plain @click="downloadAndPrint" style="margin-right: 20px">下载打印</el-button> -->
                <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
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
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
              
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" fixed prop="field_1" width="110%" label="县（市、区）" />
                <el-table-column align="center" fixed prop="field_2"  label="监测地块" />
                <el-table-column align="center" prop="field_3"  label="地区类型" />
                <el-table-column align="center" prop="field_4"  label="稻作类型" />
                <el-table-column align="center" prop="field_5"  label="品系类型" />
                <el-table-column align="center" prop="field_6"  label="品种名称" />
                <el-table-column align="center" prop="field_7"  label="栽培方式" />
                <el-table-column align="center" prop="field_8" label="播种期（月/日）" />
                <el-table-column align="center" prop="field_9" label="出苗期（月/日）" />
                <el-table-column align="center" prop="field_10" label="移栽期（月/日）" />
                <el-table-column align="center" prop="field_fennie" label="分蘖期（月/日）" />
                <el-table-column align="center" prop="field_11" label="够苗期（月/日）" />
                <el-table-column align="center" prop="field_12"  label="有效分蘗临界叶龄期（月/日）" />
                <el-table-column align="center" prop="field_13" label="拔节期（月/日）" />
                <el-table-column align="center" prop="field_14" label="孕穗期（月/日）" />
                <el-table-column align="center" prop="field_15" label="抽穗期（月/日）" />
                <el-table-column align="center" prop="field_16" label="齐穗期（月/日）" />
                <el-table-column align="center" prop="field_17" label="成熟期（月/日）" />
                <el-table-column align="center" prop="field_18" label="全生育期（日）" />
             
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
export default {
  inject: ['reload'],
  data() {
    return {          
      activeName:'1',   
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'水稻监测点苗情生育期统计表',
      tableName:'crop_01_01_table_04_04',
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
      startYear:2019,
      endYear:2031,
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
      var people = localStorage.getItem('ms_username');
      var point = this.$route.query.point;
      this.getTableData(people,point);
    }, 
    //切换tabs
    handleClick(tab,event){
     
    }, 
    //获取时间
    //获取系统时间
    getMyTime(){
      var myDate = new Date();
    
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
    
      this.systemTime = myTime;
    },  
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
  
         if(rowIndex === 2){ 
           return {display:"none"};
        };
        if(rowIndex==1 || rowIndex==0){
            // return {background:'#eee',color:'#606266'};
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
    getTableData(people,point){
      var column_id = '';
      const _this = this;
      axios({
        url:'/getUserAllData0404',
        method:'post',
        params:{
          people:people,
          column_id:column_id,
          point:point,
          startYear:this.startYear,
          endYear:this.endYear
        }
      }).then(res=>{
          this.tableData=res.data;
          this.dataNum = this.tableData.length;
          var i=0;
          for(i; i<this.tableData.length; i++){
            console.log(i)
            console.log(this.tableData[i].field_7)
            if(this.tableData[i].field_7=="撒直播（水）"){
              this.tableData[i].field_10="--"
            }
          }
          
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
          //栽植数据
          var plantData = [];
     
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
              if(optionData[i].caption === "栽培方式"){
                plantData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
          };

          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;
          _this.categorys = categoryData;
          _this.plantWays = plantData;

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
 
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.rowRecord.id;
      var recordPeople = this.rowRecord.people;
      var recordReason = this.reasonForm.reason;
      var returnTableName = this.tableName;

      
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
    //数据分析图跳转
    getDataAnalyse(){
      //console.log("11111")
    },
    //重置
    reSet(){
      this.areaType='';
      this.county='';
      this.riceType='';
      this.plantWay='';
      this.category='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      var years = this.$route.query.year;
      const _this = this;
      axios({
        url:'/getSeekInfo44',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                category:this.category,
                plantWay:this.plantWay,
                year:years,
        }
      }).then(res=>{
        //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            //num1为总面积
            var num1=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_18 === ''){
                totalTableData[i].field_18 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_18*1);
            };
            //各品种面积总和
            //console.log('1111');
            total[0]=num1;
            //console.log(total);
            if(total[0] === 0){
              sums[0]='--';
            }else{
              sums[0] = (Number(total[i])/Number(dataLength)).toFixed(2);
            }
            //console.log(sums);      
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":'--',"field_10":'--', "field_fennie":'--',
                                "field_11":'--',"field_12":'--',"field_13":'--',"field_14":'--',"field_15":'--',"field_16":'--',"field_17":'--',"field_18":sums[0],               
                                "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
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



