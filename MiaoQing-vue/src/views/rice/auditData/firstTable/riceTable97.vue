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
              <div style="height:100px;margin-bottom:20px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" fixed type="index" width="50%" :index="countMethod" label="序号" />
                    <el-table-column align="center" prop="field_1"  label="县（市、区）" />
                    <el-table-column align="center" prop="field_2"  label="监测地块" />
                    <el-table-column align="center" prop="field_3"  label="地区类型" />
                    <el-table-column align="center" prop="field_4"  label="品种名称" />
                    <el-table-column align="center" prop="field_5"  label="栽植方式" />
                    <el-table-column align="center" prop="field_6"  label="淹水日期" />
                    <el-table-column align="center" prop="field_7"  label="退水日期" />
                </el-table>
              </div>
            </el-row>
             <el-row>
              <div style="height:100px;">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    
                <el-table-column align="center" prop="field_8"  label="生育阶段" />
                <el-table-column align="center" prop="field_9"  label="面积（亩）" />
                <el-table-column align="center" prop="field_10"  label="淹水高度" />
                <el-table-column align="center" prop="field_11"  label="淹水天数">
                  <el-table-column align="center" prop='field_11_1'  label="" />
                  <el-table-column align="center" prop='field_11_2'  label="其中没顶" />
                </el-table-column>
                <el-table-column align="center" prop="field_12"  label="程度" />
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
  name: 'riceTable97',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {        
      activeName:'1',     
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度水稻监测点洪涝灾害表',
      tableName:'crop_01_01_table_09_07',
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
       var auditId=this.parentMessage;
       const _this = this;
       axios({
          url: '/getAuditRiceTable97',
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



