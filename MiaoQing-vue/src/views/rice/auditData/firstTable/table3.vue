<template>
    <div class="totalRange">
          <div class="tableRange">
            <div class="tableStyle">
              <el-table :data="tableData" border :header-cell-style="headerStyle" :row-class-name="tableRowClassName" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'4px'}" row-key="id" stripe>
                <el-table-column :label="tableLabel" align="center">
                  <el-table-column align="center" fixed type="index" label="序号" />
                  <el-table-column align="center" prop="field_1" label="县（市、区）" />
                  <el-table-column align="center" prop="field_2" label="全县总面积（万亩）" />
                  <el-table-column align="center" prop="field_3" label="地区类型" />
                  <el-table-column align="center" prop="field_4" label="稻作类型" />
                  <!-- <el-table-column align="center" prop="modification_time" label="上报时间" /> -->
                  <el-table-column align="center" width="150px" label="操作">
                    <template slot-scope="scope">                 
                        <span width='50px'><el-button type="danger" plain size="mini" @click="reasonDialog(scope.$index,scope.row)" v-if="scope.row.in_use === '1'">审核</el-button> </span>
                        <!-- <span width='50px' margin-left='20px'> <el-button type="info" plain size="mini" @click="confirmRecord(scope.row)"  v-if="scope.row.in_use === '1'">通过</el-button> </span>  -->
                    </template>
                  </el-table-column>
                  
                </el-table-column>
              </el-table>
              <el-dialog title="审核" :visible.sync="dialogVisible" :append-to-body="true" width="60%">
                  <el-row>
                    <div style="height:100px">
                    <el-table :data="showAuditRecord" border :header-cell-style="{height:'0',padding:'1px',fontSize:'8px',background:'#9BCD9B',color:'#FFFFFF'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="栽植方式（万亩）">
                        <el-table-column align="center" prop="field_5_1_1" label="人工移栽" />
                        <el-table-column align="center" prop="field_5_1_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_2_1" label="机械插秧" />
                        <el-table-column align="center" prop="field_5_2_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_3_1" label="盘育抛栽" />
                        <el-table-column align="center" prop="field_5_3_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_4_1" label="无盘旱育抛栽" />
                        <el-table-column align="center" prop="field_5_4_2" width="85%" label="占比" />
                      </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="height:100px">
                    <el-table :data="showAuditRecord" border :header-cell-style="{height:'0',padding:'1px',fontSize:'8px',background:'#9BCD9B',color:'#FFFFFF'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="栽植方式（万亩）">
                        <el-table-column align="center" prop="field_5_5_1" label="撒直播（旱）" />
                        <el-table-column align="center" prop="field_5_5_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_6_1" label="撒直播（水）" />
                        <el-table-column align="center" prop="field_5_6_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_7_1" label="机条播（旱）" />
                        <el-table-column align="center" prop="field_5_7_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_5_8_1" label="机条播（水）" />
                        <el-table-column align="center" prop="field_5_8_2" width="85%" label="占比" />
                      </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="height:100px">
                    <el-table :data="showAuditRecord" border :header-cell-style="{height:'0',padding:'1px',fontSize:'8px',background:'#9BCD9B',color:'#FFFFFF'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="前茬口类型（万亩）">
                        <el-table-column align="center" prop="field_6_1_1" label="水稻" />
                        <el-table-column align="center" prop="field_6_1_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_6_2_1" label="小麦" />
                        <el-table-column align="center" prop="field_6_2_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_6_3_1" label="油菜" />
                        <el-table-column align="center" prop="field_6_3_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_6_4_1" label="空闲田" />
                        <el-table-column align="center" prop="field_6_4_2" width="85%" label="占比" />
                    </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="height:100px">
                    <el-table :data="showAuditRecord" border :header-cell-style="{height:'0',padding:'1px',fontSize:'8px',background:'#9BCD9B',color:'#FFFFFF'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="前茬口类型（万亩）">
                        <el-table-column align="center" prop="field_6_5_1" label="绿肥" />
                        <el-table-column align="center" prop="field_6_5_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_6_6_1" label="蔬菜等经作" />
                        <el-table-column align="center" prop="field_6_6_2" width="85%" label="占比" />
                        <el-table-column align="center" prop="field_6_7_1" label="其他" />
                        <el-table-column align="center" prop="field_6_7_2" width="85%" label="占比" />
                    </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="height:80px">
                      <el-form :model='reasonForm'>
                        <el-form-item label='审核退还原因：' label-width="75px">
                          <el-input type="textarea" :rows="3" v-model="reasonForm.reason" auto-complete="off"></el-input>
                        </el-form-item>
                      </el-form>
                    </div>
                  </el-row>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取  消</el-button>
                  <el-button @click="returnData()">退  回</el-button>
                  <el-button type="primary" @click="confirmRecord()">通  过</el-button>
                </div>
            </el-dialog>
            </div>
          </div>
     </div>
  
</template>


<script>
import axios from 'axios'
import * as math from 'mathjs'
export default {
  name: 'riceTable11',
  inject: ['reload'],
  data() {
    return {       
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产栽插概况调查表',
      tableName:'crop_01_01_table_01_01',
      //数据有误输入原因
      reasonForm: {reason:''},
      //系统时间
      systemTime:'',
      //退回原因对话框
      dialogVisible:false,
      //暂存退回记录
      rowRecord:[],
      //展示审核记录
      showAuditRecord:[],

    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.getTableData();
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
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
    }, 
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
         if(rowIndex === 3){ 
           return {display:"none"};
        };
        if(rowIndex==1 || rowIndex==2){
            // return {background:'#eee',color:'#606266'};
            return{height:'0',padding:'1px',fontSize:'5px'};
        };
           
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
          url: '/getAuditRiceTable11',
          method:'get',
       }).then(res=>{
          _this.tableData=res.data;
          //console.log(_this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //退还对话框
    reasonDialog(index,row){
      this.dialogVisible=true;
      var temp=[row];
      //console.log(row);
      //console.log(temp);
      this.rowRecord = row;
      this.showAuditRecord = temp;
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
    confirmRecord(){
      const _this=this;
      this.dialogVisible = false;
      var returnTableName = this.tableName;
      var recordId = this.rowRecord.id;
      //console.log(recordId);
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
      this.getTableData();
    },
  },
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



