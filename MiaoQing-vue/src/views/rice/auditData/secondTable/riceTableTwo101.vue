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
                     <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                    <el-table-column align="center" prop="field_2"  label="监测点代码" />
                    <el-table-column align="center" prop="field_3"  label="地区类型" />
                    <el-table-column align="center" prop="field_4"  label="稻作类型" />

                    <el-table-column align="center" prop="field_5"  label="品种名称" />
                    <el-table-column align="center" prop="field_6"  label="栽植方式" />
                    <el-table-column align="center" label="灌浆速率测定1（扬花）">
                      <el-table-column align="center" prop="field_7_1"  label="日期" />
                      <el-table-column align="center" prop="field_7_2"  label="千粒重（克）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="灌浆速率测定2（扬花+7天）">
                      <el-table-column align="center" prop="field_8_1"  label="日期" />
                      <el-table-column align="center" prop="field_8_2"  label="粒重（克）" />
                      <el-table-column align="center" prop="field_8_3"  label="灌浆速率（克/千粒•天）" />
                    </el-table-column>
                    <el-table-column align="center" label="灌浆速率测定3（扬花+14天）">
                      <el-table-column align="center" prop="field_9_1"  label="日期" />
                      <el-table-column align="center" prop="field_9_2"  label="粒重（克）" />
                      <el-table-column align="center" prop="field_9_3"  label="灌浆速率（克/千粒•天）" />
                    </el-table-column>
                    <el-table-column align="center" label="灌浆速率测定4（扬花+21天）">
                      <el-table-column align="center" prop="field_10_1"  label="日期" />
                      <el-table-column align="center" prop="field_10_2"  label="粒重（克）" />
                      <el-table-column align="center" prop="field_10_3"  label="灌浆速率（克/千粒•天）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                     <el-table-column align="center" label="灌浆速率测定5（扬花+28天）">
                        <el-table-column align="center" prop="field_11_1"  label="日期" />
                        <el-table-column align="center" prop="field_11_2"  label="粒重（克）" />
                        <el-table-column align="center" prop="field_11_3"  label="灌浆速率（克/千粒•天）" />
                      </el-table-column>
                      <el-table-column align="center" label="灌浆速率测定6（扬花+35天）">
                        <el-table-column align="center" prop="field_12_1"  label="日期" />
                        <el-table-column align="center" prop="field_12_2"  label="粒重（克）" />
                        <el-table-column align="center" prop="field_12_3"  label="灌浆速率（克/千粒•天）" />
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
  name: 'riceTableTwo101',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {         
      activeName:'1',        
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度水稻监测点灌浆速率调查表（早稻）',
      tableName:'crop_01_02_table_10_01',
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
          url: '/getAuditRiceTableTwo101',
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



