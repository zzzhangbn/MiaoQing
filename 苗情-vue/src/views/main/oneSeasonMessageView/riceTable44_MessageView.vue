<template>
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="12">
              </el-col>
            <el-col :span="4">
            </el-col>
            <el-col :span="4">
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

            <el-table :data="totalTableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
              <!-- <el-table-column :label="tableLabel" align="center"> -->
                <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="105%" label="县（市、区）" fixed />
                <el-table-column align="center" prop="field_2" width="110%" label="监测地块" fixed />
                <el-table-column align="center" prop="field_3" width="100%" label="地区类型" />
                <el-table-column align="center" prop="field_4" width="100%" label="稻作类型" />
                <el-table-column align="center" prop="field_5"  label="品系类型" />
                <el-table-column align="center" prop="field_6" width="150%" label="品种名称" />
                <el-table-column align="center" prop="field_7"  label="栽培方式" />
                <el-table-column align="center" prop="field_8" width="130%" label="播种期（月/日）" />
                <el-table-column align="center" prop="field_9" width="130%" label="出苗期（月/日）" />
                <el-table-column align="center" prop="field_10" width="130%" label="移栽期（月/日）" />
                <el-table-column align="center" prop="field_fennie" width="130%" label="分蘖期（月/日）" />
                <el-table-column align="center" prop="field_11" width="130%" label="够苗期（月/日）" />
                <el-table-column align="center" prop="field_12" width="220%" label="有效分蘗临界叶龄期（月/日）" />
                <el-table-column align="center" prop="field_13" width="130%" label="拔节期（月/日）" />
                <el-table-column align="center" prop="field_14" width="130%" label="孕穗期（月/日）" />
                <el-table-column align="center" prop="field_15" width="130%" label="抽穗期（月/日）" />
                <el-table-column align="center" prop="field_16" width="130%" label="齐穗期（月/日）" />
                <el-table-column align="center" prop="field_17" width="130%" label="成熟期（月/日）" />
                <el-table-column align="center" prop="field_18" width="135%" label="全生育期（日）" />
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
        <router-link :to="{ name: '/tablemain',params:{activeNum:2}}">
                    <el-button type="success"  style="float:right;margin:20px 0 0 0">返回</el-button>
        </router-link>
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
      totalTableData:[],
      //表名
      tableLabel:'',
      tableName:'crop_01_01_table_04_04',
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
     this.years = myDate.getFullYear();
     console.log(this.years)
    this.tableLabel= this.years+"年度水稻监测点苗情生育期统计表";
    //console.log(this.areas,this.citys,this.years);
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
        if(rowIndex === 0){
          return{height:'0',padding:'2px',fontSize:'7px',background:'#6E8B3D',color:'#FFFFFF',}
        }
        if(rowIndex==1 ){
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
    getTableData(){
       const _this = this;
       var temp = localStorage.getItem("ms_username")
       axios({
          url: '/getRiceTable44_Message',
          method:'post',
          params:{
             areaType : this.areas,
            area:temp,
            year:this.years
           }
       }).then(res=>{
         console.log(res.data);
          //_this.tableData=res.data;
          var totalTableData=res.data;
          if(totalTableData.length != 0){
            var sums = [];//存占比
            var total =[];//存各面积和
            var dataLength=totalTableData.length;

            //num1为总面积
            var num1=0;
            //合计行计算
            for(var i=0; i<totalTableData.length; i++){
              if(totalTableData[i].field_18 === ''){
                totalTableData[i].field_18 = 0;
              }else if(this.tableData[i].field_7=="撒直播（水）" || this.tableData[i].field_7=="撒直播（旱）"){
              this.tableData[i].field_10="--"
            }
              num1 = _this.add(num1*1+totalTableData[i].field_18*1);
            };
            //各品种面积总和
            //console.log('num1');
            total[0]=num1;
            //console.log(total);
            if(total[0] === 0){
              sums[0]='--';
            }else{
              sums[0] = (Number(total[0])/Number(dataLength)).toFixed(2);
            }
            //console.log(sums);      
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":'--',"field_10":'--',"field_fennie":'--',
                                "field_11":'--',"field_12":'--',"field_13":'--',"field_14":'--',"field_15":'--',"field_16":'--',"field_17":'--',"field_18":sums[0],               
                                "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
          _this.tableData = totalTableData;
        //   console.log(_this.tableData);
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
      const _this = this;
      axios({
        url:'/getSeekInfo44',
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
            var sums = [];//存占比
            var total =[];//存各面积和
            var dataLength=totalTableData.length;
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
              sums[0] = (Number(total[0])/Number(dataLength)).toFixed(2);
            }
            //console.log(sums);      
            //辅助数组
            totalTableData.push({"field_1":'合计',"field_2":'--',"field_3":'--',"field_4":'--',"field_5":'--',"field_6":'--',"field_7":'--',"field_8":'--',"field_9":'--',"field_10":'--',"field_fennie":'--',
                                "field_11":'--',"field_12":'--',"field_13":'--',"field_14":'--',"field_15":'--',"field_16":'--',"field_17":'--',"field_18":sums[0],               
                                "in_use":'--'})
            _this.dataNum = totalTableData.length - 1;
          }else{
            _this.dataNum = 0;
          } 
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
    //导出excel
    // 导出Excel点击方法
    handleDownload() {
      // this.downloadLoading = true // 加载遮罩层开启
      //console.log("11111");
      const {export_json_to_excel} = require('../../rice/excel/Export2Excel2'); //引入文件
      //console.log("2222")
      const th1 = [[this.tableLabel, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
                    ] //对应表格一级输出的title
      const header = []//三级
      const th2 =  [['县（市、区）', '监测田块', '地区类型', '稻作类型', '品系类型', '品种名称',
                      '栽培方式', '播种期（月/日）', '出苗期（月/日）', '移栽期（月/日）', '分蘖期（月/日）',
                      '够苗期（月/日', '有效分蘗临界叶龄期（月/日）', '拔节期（月/日）', '孕穗期（月/日）', 
                      '抽穗期（月/日）', '齐穗期（月/日）', '成熟期（月/日）', '全生育期/天']] //对应表格三级输出的title
      const filterVal = ['field_1', 'field_2', 'field_3', 'field_4', 'field_5', 'field_6', 
                        'field_7', 'field_8', 'field_9', 'field_10', 'field_fennie','field_11', 'field_12',
                        'field_13', 'field_14', 'field_15', 'field_16', 'field_17', 'field_18'] // 对应表格tableData输出的数据
      //console.log("th1:"+th1,"th2:"+th2,"filterVal:"+filterVal);
      //进行所有表头的单元格合并，建议一行一行来，不然容易整乱
      const merges = [
        "A1:AL1",
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



