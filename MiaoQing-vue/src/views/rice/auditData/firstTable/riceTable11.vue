<template>
    <div class="totalRange">
          <div class="tableRange">
            <div class="tableStyle">
                    <el-row>
                      <div style="text-align:center;font-size:16px;color:black;margin-bottom:10px">
                        <span>{{tableLabel}}</span>
                      </div>
                    </el-row>
                    <el-row>
                      <div style="height:100px;margin-bottom:10px">
                        <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                            <el-table-column align="center" prop="field_1" width="100%" label="县（市、区）" />
                            <el-table-column align="center" prop="field_2" width="100%" label="全县总面积（万亩）" />
                            <el-table-column align="center" prop="field_3" width="100%" label="地区类型" />
                            <el-table-column align="center" prop="field_4" width="100%" label="稻作类型" />
                            <el-table-column align="center" width="100%" label="栽植方式（万亩）">
                              <el-table-column align="center" prop="field_5_1_1"  label="人工移栽" />
                              <el-table-column align="center" prop="field_5_1_2"  label="占比" />
                              <el-table-column align="center" prop="field_5_2_1"  label="机械插秧" />
                              <el-table-column align="center" prop="field_5_2_2"  label="占比" />
                              <el-table-column align="center" prop="field_5_3_1"  label="盘育抛栽" />
                              <el-table-column align="center" prop="field_5_3_2"  label="占比" />
                              <el-table-column align="center" prop="field_5_4_1"  label="无盘旱育抛栽" />
                              <el-table-column align="center" prop="field_5_4_2"  label="占比" />
                            </el-table-column>
                        </el-table>
                      </div>
                    </el-row>
                  <el-row>
                    <div style="height:100px;margin-bottom:10px">
                    <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="栽植方式（万亩）">
                        <el-table-column align="center" prop="field_5_5_1"  label="撒直播（旱）" />
                        <el-table-column align="center" prop="field_5_5_2"  label="占比" />
                        <el-table-column align="center" prop="field_5_6_1"  label="撒直播（水）" />
                        <el-table-column align="center" prop="field_5_6_2"  label="占比" />
                        <el-table-column align="center" prop="field_5_7_1"  label="机条播（旱）" />
                        <el-table-column align="center" prop="field_5_7_2"  label="占比" />
                        <el-table-column align="center" prop="field_5_8_1"  label="机条播（水）" />
                        <el-table-column align="center" prop="field_5_8_2"  label="占比" />
                      </el-table-column>
                      <el-table-column align="center" label="前茬口类型（万亩）">
                        <el-table-column align="center" prop="field_6_1_1"  label="水稻" />
                        <el-table-column align="center" prop="field_6_1_2"  label="占比" />
                        <el-table-column align="center" prop="field_6_2_1"  label="小麦" />
                        <el-table-column align="center" prop="field_6_2_2"  label="占比" />
                    </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                  <el-row>
                    <div style="height:100px;margin-bottom:10px">
                    <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                      <el-table-column align="center" label="前茬口类型（万亩）">
                        <el-table-column align="center" prop="field_6_3_1"  label="油菜" />
                        <el-table-column align="center" prop="field_6_3_2"  label="占比" />
                        <el-table-column align="center" prop="field_6_4_1"  label="空闲田" />
                        <el-table-column align="center" prop="field_6_4_2"  label="占比" />
                        <el-table-column align="center" prop="field_6_5_1"  label="绿肥" />
                        <el-table-column align="center" prop="field_6_5_2"  label="占比" />
                        <el-table-column align="center" prop="field_6_6_1"  label="蔬菜等经作" />
                        <el-table-column align="center" prop="field_6_6_2"  label="占比" />
                        <el-table-column align="center" prop="field_6_7_1"  label="其他" />
                        <el-table-column align="center" prop="field_6_7_2"  label="占比" />
                    </el-table-column>
                    </el-table>
                    </div>
                  </el-row>
                 
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
  props: ['parent_msg'],
  data() {
    return {       
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产栽插概况调查表',
      tableName:'crop_01_01_table_01_01',
      //退回原因对话框
      dialogVisible:false,
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
      this.getTableData();
    },
    //表头跨列隐藏
    headerStyle({row,column,rowIndex,columnIndex}){
            return{background:'#CDBE70',color:'green',fontSize:'8px',height:'0',padding:'1px',}
    },
    //获取表数据
    getTableData(){
       const _this = this;
       //console.log(this.parentMessage);
       var auditId=this.parentMessage;
       axios({
          url: '/getAuditRiceTable11',
          method:'get',
       }).then(res=>{
         var temp=res.data;
         var tempData=[];
         var temp1='';
         for(var i=0;i<temp.length;i++){
           if(temp[i].id == auditId){
             temp1 = temp[i];
           }
         }
         tempData[0] = temp1;
          _this.tableData=tempData;
          //console.log(_this.tableData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
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



