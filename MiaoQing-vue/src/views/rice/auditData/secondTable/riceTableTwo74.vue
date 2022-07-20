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
                    <el-table-column align="center" prop="field_2" width="100%" label="地区类型" />
                    <el-table-column align="center" prop="field_3" width="100%" label="稻作类型" />
                    
                    <el-table-column align="center" label="全县面上平均水平">
                      <el-table-column align="center" prop="field_4_1" width="130%" label="总面积（万亩）" />
                      <el-table-column align="center" prop="field_4_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_4_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_4_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_4_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_4_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="人工移栽">
                      <el-table-column align="center" prop="field_5_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_5_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_5_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_5_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_5_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_5_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                    <el-table-column align="center" label="机械插秧">
                      <el-table-column align="center" prop="field_6_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_6_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_6_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_6_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_6_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_6_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
             <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="盘育抛秧">
                      <el-table-column align="center" prop="field_7_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_7_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_7_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_7_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_7_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_7_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                    <el-table-column align="center" label="无盘旱育抛秧">
                      <el-table-column align="center" prop="field_8_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_8_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_8_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_8_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_8_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_8_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
             <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="撒直播">
                      <el-table-column align="center" prop="field_9_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_9_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_9_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_9_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_9_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_9_6" width="160%" label="亩理论产量（kg/亩）" />
                    </el-table-column>
                    <el-table-column align="center" label="机条播">
                      <el-table-column align="center" prop="field_10_1" width="130%" label="面积（万亩）" />
                      <el-table-column align="center" prop="field_10_2" width="130%" label="亩有效穗（万）" />
                      <el-table-column align="center" prop="field_10_3" width="130%" label="每穗粒数（粒）" />
                      <el-table-column align="center" prop="field_10_4" width="120%" label="结实率（％）" />
                      <el-table-column align="center" prop="field_10_5" width="120%" label="千粒重（g）" />
                      <el-table-column align="center" prop="field_10_6" width="160%" label="亩理论产量（kg/亩）" />
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
  name: 'riceTableTwo74',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {          
      activeName:'1',       
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻双季稻测产表',
      tableName:'crop_01_02_table_07_04',
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
     // //console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 2){ 
           return {display:"none"};
        }else{
            // return {background:'#eee',color:'#606266'};
            return{background:'#CDBE70',color:'green',fontSize:'8px',height:'0',padding:'1px',}
        };
    },
    //获取表数据
    getTableData(){
       const _this = this;
       var auditId=this.parentMessage;
       axios({
          url: '/getAuditRiceTableTwo74',
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
  }
};
</script>

<style scoped>
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



