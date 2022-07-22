<template>
  
    <div class="totalRange">
        <div class="tableRange">
          <div class="searchBox">            
            <el-row>
              <el-col :span="6">
                <template>
                  <el-select v-model="city" clearable  style="width:160px; margin-left: 20px;" placeholder="市">
                    <el-option
                      v-for="item in citys"
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
                </template>
              </el-col>
            <el-col :span="4">
              <el-button type="info" @click="reSet">重置</el-button>
              <el-button type="info" @click="searchInfo">查询</el-button>
            </el-col>
            <el-col :span="3">
              <!-- <el-button  type="info" plain @click="downloadAndPrint">下载打印</el-button> -->
            </el-col>
            </el-row>
          </div>
          <div class="tableStyle">
            <el-table :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" style="width: 100%" :row-style="{height:'30px'}" :cell-style="{padding:'0'}" row-key="id" stripe>
              <el-table-column :label="tableLabel" align="center">
                <el-table-column align="center" fixed type="index" :index="countMethod" label="序号" />
                <el-table-column align="center" prop="field_1" width="100%" label="监测点代码" />
                <el-table-column align="center" label="地址">
                  <el-table-column align="center" prop="field_2_1" width="100%" label="市" />
                  <el-table-column align="center" prop="field_2_2" width="120%" label="县（市、区）"/>
                  <el-table-column align="center" prop="field_2_3" width="100%" label="镇（乡）" />
                  <el-table-column align="center" prop="field_2_4" width="100%" label="村名"/>
                  <el-table-column align="center" prop="field_2_5" width="100%" label="户主" />
                </el-table-column>
                <el-table-column align="center" label="监测田块">
                  <el-table-column align="center" prop="field_3_1" width="120%" label="种植面积（亩）" />
                  <el-table-column align="center" prop="field_3_2" width="100%" label="地区类型"/>
                  <el-table-column align="center" prop="field_3_3" width="100%" label="土壤类型" />
                  <el-table-column align="center" prop="field_3_4" width="100%" label="前茬类型"/>
                  <el-table-column align="center" prop="field_3_5" width="120%" label="水管方式" />
                  <el-table-column align="center" prop="field_3_6" width="100%" label="全氮(g/Kg)" />
                  <el-table-column align="center" prop="field_3_7" width="120%" label="速效磷(g/Kg)"/>
                  <el-table-column align="center" prop="field_3_8" width="120%" label="速效钾(g/Kg)" />
                  <el-table-column align="center" prop="field_3_9" width="120%" label="有机质(g/Kg)"/>
                  <el-table-column align="center" prop="field_3_10" width="150%" label="秸秆还田量(kg/亩)" />
                </el-table-column>
                <el-table-column align="center" label="品种（品系）">
                  <el-table-column align="center" prop="field_4_1" width="100%" label="名称" />
                  <el-table-column align="center" prop="field_4_2" width="100%" label="稻作类型"/>
                  <el-table-column align="center" prop="field_4_3" width="100%" label="品系类型" />
                </el-table-column>
                <el-table-column align="center" label="田间信息">
                  <el-table-column align="center" prop="field_5_1" width="100%" label="栽植方式" />
                  <el-table-column align="center" prop="field_5_2" width="100%" label="播期"/>
                  <el-table-column align="center" prop="field_5_3" width="120%" label="移栽秧龄（天）" />
                  <el-table-column align="center" prop="field_5_4" width="120%" label="移栽叶龄（叶）"/>
                  <el-table-column align="center" prop="field_5_5" width="130%" label="基本苗（万/亩）" />
                  <el-table-column align="center" prop="field_5_6" width="100%" label="收获日期" />
                  <el-table-column align="center" prop="field_5_7" width="120%" label="亩有效穗(万)"/>
                  <el-table-column align="center" prop="field_5_8" width="120%" label="穗总粒数(个)" />
                  <el-table-column align="center" prop="field_5_9" width="100%" label="结实率(％)"/>
                  <el-table-column align="center" prop="field_5_10" width="100%" label="千粒重(g)" />
                  <el-table-column align="center" prop="field_5_11" width="130%" label="理论产量(kg/亩)"/>
                  <el-table-column align="center" prop="field_5_12" width="130%" label="实收产量(kg/亩)" />
                </el-table-column>
                <el-table-column align="center" width="100" prop="in_use" :formatter="auditState" label="审核状态">
                
                </el-table-column>
                <el-table-column align="center" width="150px" label="操作">
                  <template slot-scope="scope">                 
                       <span width='50px'><el-button type="danger" plain size="mini" @click="reasonDialog(scope.$index,scope.row)"  v-if="scope.row.in_use === '1'">退还</el-button> </span>
                       <span width='50px' margin-left='20px'> <el-button type="info" plain size="mini" @click="confirmRecord(scope.row)"  v-if="scope.row.in_use === '1'">通过</el-button> </span>
                     
                  </template>
                </el-table-column>
              </el-table-column>
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
            <el-dialog title="退回原因:" :visible.sync="dialogVisible" width="30%">
              <el-form :model='reasonForm'>
                <el-form-item label='' label-width="10px">
                  <el-input v-model="reasonForm.reason" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="returnData">确 定</el-button>
              </div>
            </el-dialog>
          </div>
          
        </div>
     </div>
  
</template>


<script>
import axios from 'axios'
export default {
  name: 'riceTableTwo22',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {         
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中总数据
      TotalData:[],
      //表中分页数据
      tableData: [],
      //表名
      tableLabel:'2021年度水稻监测点汇总表',
      tableName:'crop_01_02_table_02_02',
      //数据有误输入原因
      reasonForm: {reason:''},
      //县的option
      countys:[],
      county: '',
      //市option
      citys:[],
      city: '',
      //系统时间
      systemTime:'',
      //退回原因对话框
      dialogVisible:false,
      //暂存退回记录
      rowRecord:[],
      //分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,//数据个数
      //接收父组件传过来参数
      parentMessage:this.parent_msg,
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
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
         if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex === 1){
          return {height:'0'}
        }
           //return {background:'#eee',color:'#606266'};
    },
    //表头颜色
    tableRowClassName({row,rowIndex}){
        if(rowIndex === 6){
          return '.header-row';
        }
        return '';
    },
    //获取表数据
    getTableData(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo22',
          method:'get',
          // params:{
          //   tabName:table
          // }
       }).then(res=>{
           _this.tableData=res.data;
           _this.dataNum = _this.tableData.length;
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
          var cityData = [];
          for(var i=0; i<optionData.length; ++i){
              if(optionData[i].caption === "县（市、区）"){
                countyData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              if(optionData[i].caption === "市"){
                cityData.push({"label":optionData[i].list,"value":optionData[i].list});
              };
              // if(optionData[i].caption === "稻作类型"){
              //   riceData.push({"label":optionData[i].list,"value":optionData[i].list});
              // };
          };
          _this.countys = countyData;
          _this.citys = cityData;
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
      var returnTableName = this.tableName;//退回表名
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
      this.city='';
      this.county='';
      this.getTableData();
    },
    //搜索
    searchInfo(){
      const _this = this;
      ////console.log(this.county,this.city)
      axios({
        url:'/getSeekInfoTwo22',
        method:'post',
        params:{county:this.county,
                city:this.city,
        }
      }).then(res=>{
            _this.tableData = res.data;
            _this.dataNum = _this.tableData.length;
            //console.log(_this.tableData);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
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
    width:1525px;
    height: auto;
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
    height: 400px;
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



