<template>
  
    <div class="totalRange">
      <div class="tableRange">
            <div class="searchBox">            
              <el-row>
                <el-col :span="4">
                  <template>
                    <el-select v-model="county" clearable  style="width:160px; margin-left: 20px;" placeholder="县（市、区）">
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

              <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle"  :row-style="{height:'30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <!-- <el-table-column width="100%" :label="tableLabel" align="center"> -->
                  <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                  <el-table-column align="center" prop="county" width="105%" label="县（市、区）" />
                  <el-table-column align="center" prop="county_area" label="全县总面积（万亩）" />
                  <el-table-column align="center" prop="pin_zhong_name" label="品种名" />
                  <el-table-column align="center" prop="area_data" label="面积（万亩）" />
                  <el-table-column align="center" prop="area_percent" label="面积占比" />
                  <el-table-column align="center"  fixed="right" width="120px" label="操作" v-if="years == currentyear">
                    <template slot-scope="scope">
                      <el-button type="danger" size="mini" v-if="scope.row.in_use == 3" @click="backRevision(scope.$index,scope.row)" >退回修改</el-button>
                    </template>
                  </el-table-column>  
                <!-- </el-table-column> -->
              </el-table>
              <el-pagination class="fy"
                      layout="prev, pager, next, total"
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
      //表中总数据
      TotalData:[],
      //表中分页数据
      tableData: [],
      //表名
      tableLabel:'',
      tableName:'crop_01_02_table_01_02',
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
      dataNum:0,//数据个数
      //暂存参数
      areas:'',
      citys:'',
      years:'',
      diqu:'',
      nian:'',
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

      this.diqu = this.$route.query.area;
      this.nian = this.$route.query.year;
      
      this.tableLabel= this.years+"年度监测县(市、区)水稻生产主推品种调查表";
      //this.getHeaderList();
      this.getTableData();
      this.getCountySelection();
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
    //处理小数点
    add(value) {
        const precision = 14
        return Number(math.format(value, precision))
    },
    //获取表数据
    getTableData(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo12',
          method:'post',
          params:{
            areaType:this.areas,
            county:this.citys,
            year:this.years
           }
       }).then(res=>{
          // _this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            _this.dataNum = totalTableData.length;
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
              // if(optionData[i].caption === "地区类型"){
              //   areaData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
              // if(optionData[i].caption === "稻作类型"){
              //   riceData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
          };
          _this.countys = countyData;
          _this.areaTypes = areaData;
          _this.riceTypes = riceData;

       }).catch(err=>{
         //return this.$message.error("查询数据失败！");
       });
    },
    
    //数据分析图跳转
    getDataAnalyse(){
      var areas=this.diqu;
      var years=this.nian;
      // this.$router.replace('/doubleRiceAnalysis');
      this.$router.push({path:'/doubleRiceAnalysis',query: {area:areas,year:years}});
    },
    //重置
    reSet(){
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      const _this = this;
      axios({
        url:'/getSeekInfoTwo12',
        method:'post',
        params:{county:this.county,
                areaType:this.areaType,
                riceType:this.riceType,
                year:this.years,
        }
      }).then(res=>{
          var totalTableData = res.data;
          if(totalTableData.length != 0){
            _this.dataNum = totalTableData.length;
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
    handleCurrentChange(currentPage){
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
      const th1 = [[this.tableLabel, '', '', '', '']] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['县（市、区）', '全县总面积（万亩）', '品种名', '面积（万亩）', '面积占比']] //对应表格三级输出的title
      const filterVal = ['county', 'county_area', 'pin_zhong_name', 'area_data', 'area_percent'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:E1",
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
      var recordCounty=this.recordData.county;
      //console.log(recordCounty);
      var recordId = 1;
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



