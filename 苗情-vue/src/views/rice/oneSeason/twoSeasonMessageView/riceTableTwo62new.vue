<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="11">
                <template>

                </template>
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
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle"  :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
              
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed/>
                <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" fixed/>
                <el-table-column align="center" prop="field_3" width="100%" label="地区类型" fixed/>
                <el-table-column align="center" prop="field_4" width="100%" label="稻作类型" fixed/>
                <el-table-column align="center" prop="field_5" width="100%" label="品种名称" fixed/>
                <el-table-column align="center" prop="field_6" width="100%" label="栽植方式" />
                <el-table-column align="center" label="化除1-播栽前">
                  <el-table-column align="center" prop="field_7_1" width="100%" label="日期" />
                  <el-table-column align="center" prop="field_7_2" width="150%" label="除草剂名称" />
                  <el-table-column align="center" prop="field_7_3" width="120%" label="亩用量/kg" />
                  <el-table-column align="center" prop="field_7_4" width="120%" label="方法" />
                  <el-table-column align="center" prop="field_7_5" width="150%" label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化除2-播栽后或出苗后">
                  <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                  <el-table-column align="center" prop="field_8_2" width="150%" label="除草剂名称" />
                  <el-table-column align="center" prop="field_8_3" width="120%" label="亩用量/kg" />
                  <el-table-column align="center" prop="field_8_4" width="120%" label="方法" />
                  <el-table-column align="center" prop="field_8_5" width="150%" label="生育进程" />
                </el-table-column>
                <el-table-column align="center" label="化除3播栽后或出苗后">
                  <el-table-column align="center" prop="field_9_1" width="100%" label="日期" />
                  <el-table-column align="center" prop="field_9_2" width="150%" label="除草剂名称" />
                  <el-table-column align="center" prop="field_9_3" width="120%" label="亩用量/kg" />
                  <el-table-column align="center" prop="field_9_4" width="120%" label="方法" />
                  <el-table-column align="center" prop="field_9_5" width="150%" label="生育进程" />
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
      tableName:'crop_01_02_table_06_02',
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
       let date = new Date();
      let y = date.getFullYear();
      this.years=y;
      this.tableLabel= this.years+"年度水稻监测点化除管理记录表";
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
        var temp = sessionStorage.getItem("ms_username")
       axios({
          url: '/getRiceTableTwo62new',
          method:'post',
          params:{
            areaType:this.areas,
            temp:temp,
            year:this.years
           }
       }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_3*1);
            };
            //各品种面积总和
            //console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;
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
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":sums[0],"field_7_4":'--',"field_7_5":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":sums[1],"field_8_4":'--',"field_8_5":'--',
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[2],"field_9_4":'--',"field_9_5":'--',
                                "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
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
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;
          _this.categorys = categoryData;
          _this.plantWays = plantData;

       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
       });
    },
    
    //重置
    reSet(){
      this.areaType='';
      this.county='';
      this.riceType='';
      this.plantWay='';
      this.getTableData();
    },
    //搜索
    searchInfo(){ 
      const _this = this;
      axios({
        url:'/getSeekInfoTwo62',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                category:this.category,
                plantWay:this.plantWay,
                year:this.years,
        }
      }).then(res=>{
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存合计平均值
            var total =[];//存和
            
            var num1=0;var num2=0;var num3=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_7_3 === ''){
                totalTableData[i].field_7_3 = 0;
              }
              num1 = _this.add(num1*1+totalTableData[i].field_7_3*1);

              if(totalTableData[i].field_8_3 === ''){
                totalTableData[i].field_8_3 = 0;
              }
              num2 = _this.add(num2*1+totalTableData[i].field_8_3*1);

              if(totalTableData[i].field_9_3 === ''){
                totalTableData[i].field_9_3 = 0;
              }
              num3 = _this.add(num3*1+totalTableData[i].field_9_3*1);
            };
            //各品种面积总和
            //console.log('1111');
            total[0]=num1;total[1]=num2;total[2]=num3;
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
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',
                                "field_7_1":'--',"field_7_2":'--',"field_7_3":sums[0],"field_7_4":'--',"field_7_5":'--',
                                "field_8_1":'--',"field_8_2":'--',"field_8_3":sums[1],"field_8_4":'--',"field_8_5":'--',
                                "field_9_1":'--',"field_9_2":'--',"field_9_3":sums[2],"field_9_4":'--',"field_9_5":'--',
                                "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
          _this.tableData = totalTableData;
          //console.log(_this.tableData);
       }).catch(err=>{
         ////return this.$message.error("查询数据失败！");
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
      const th1 = [[this.tableLabel, '', '', '', '', '',  '', '', '', '', '', '', '','', '', '', '', '', '', '', ''],
                    ['县（市、区）', '监测点代码', '地区类型', '稻作类型', '品种名称', '栽植方式', 
                    '化除1-播栽前', '', '', '', '', 
                    '化除2-播栽后或出苗后', '', '', '', '', 
                    '化除3播栽后或出苗后', '', '', '', '',]] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['', '', '', '', '', '', 
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',
                    '日期', '除草剂名称', '亩用量/kg', '方法', '生育进程',]] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                    'field_7_1', 'field_7_2', 'field_7_3', 'field_7_4', 'field_7_5', 
                    'field_8_1', 'field_8_2', 'field_8_3', 'field_8_4', 'field_8_5', 
                    'field_9_1', 'field_9_2', 'field_9_3', 'field_9_4', 'field_9_5'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:U1",
        "A2:A3",
        "B2:B3",
        "C2:C3",
        "D2:D3",
        "E2:E3",
        "F2:F3",
        "G2:K2",
        "L2:P2",
        "Q2:U2"
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
      this.$router.push({path:'/tableTwoMain',query:{area:diqu,city:xian,year:nian}});
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
         ////return this.$message.error("查询数据失败！");
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



