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
                    <el-table-column align="center"  label="县（市、区）" >
                      <el-table-column align="center"  prop="field_1_1" label="" />
                      <el-table-column align="center"  prop="field_1_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="抽穗期前15天">
                      <el-table-column align="center" prop="field_2_1"  label="日平均气温（℃）" />
                      <el-table-column align="center" prop="field_2_2"  label="日最高气温（℃）" />
                      <el-table-column align="center" prop="field_2_3"  label="降雨量（mm）" />
                      <el-table-column align="center" prop="field_2_4"  label="日照时数（h）" />
                      <el-table-column align="center" prop="field_2_5"  label="晴好天气天数（天）" />
                      <el-table-column align="center" prop="field_2_6"  label="阴雨天气天数（天）" />
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="抽穗期后15天">
                      <el-table-column align="center" prop="field_3_1"  label="日平均气温（℃）" />
                      <el-table-column align="center" prop="field_3_2"  label="日最高气温（℃）" />
                      <el-table-column align="center" prop="field_3_3"  label="降雨量（mm）" />
                      <el-table-column align="center" prop="field_3_4"  label="日照时数（h）" />
                      <el-table-column align="center" prop="field_3_5"  label="晴好天气天数（天）" />
                      <el-table-column align="center" prop="field_3_6"  label="阴雨天气天数（天）" />
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
  name: 'riceTable92',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {         
      activeName:'1',    
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)抽穗期前、后各15天气象资料表',
      tableName:'crop_01_01_table_09_02',
      //退回原因对话框
      dialogVisible:false,
      //年度
      currentYear:'',   //本年度
      previousYear:'',   //上年度 
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
      this.getYear();
      this.getTableData();
    }, 
    //获取年度时间
    getYear(){
      var myDate = new Date();
      this.currentYear = myDate.getFullYear();
      this.previousYear = parseInt(this.currentYear) - 1;
     
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
       //console.log(this.currentYear,this.previousYear);
       axios({
          url: '/getAuditRiceTable92',
          method:'post',
          params:{currentYear:this.currentYear,
                  previousYear:this.currentYear,}
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



