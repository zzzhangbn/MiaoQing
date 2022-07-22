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
                    <el-table-column align="center" prop="field_2"  label="地区类型" />
                    <el-table-column align="center" prop="field_3"  label="稻作类型" />
                    <el-table-column align="center" prop="field_4"  label="全县总面积（万亩）" />
                    <el-table-column align="center" label="播种日期">
                        <el-table-column align="center" prop="field_5_1"  label="育秧" />
                        <el-table-column align="center" prop="field_5_2"  label="直播" />
                    </el-table-column>
                    <el-table-column align="center"  label="秧田面积（万亩）">
                      <el-table-column align="center" prop="field_6"  label="" />
                      <el-table-column align="center" label="其中集中育秧">
                        <el-table-column align="center" prop="field_7_1"  label="" />
                        <el-table-column align="center" prop="field_7_2"  label="" />
                      </el-table-column>
                    </el-table-column>
                    <el-table-column align="center"  label="折合大田面积（万亩）">
                      <el-table-column align="center" label="">
                        <el-table-column align="center" prop="field_8_1"  label="" />
                        <el-table-column align="center" prop="field_8_2"   label="" />  
                      </el-table-column>
                      <el-table-column align="center" label="其中集中育秧">
                        <el-table-column align="center" prop="field_9_1"  label="" />
                        <el-table-column align="center" prop="field_9_2"  label="" />
                      </el-table-column>
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="播种期进度1">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_10_2_1"  label="" />
                      <el-table-column align="center" prop="field_10_2_2"  label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_10_3"  label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_10_4"  label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_10_5"  label="小计" />
                    <el-table-column align="center" prop="field_10_6"  label="占比" />
                  </el-table-column>  
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="播种期进度2">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_11_2_1"  label="" />
                      <el-table-column align="center" prop="field_11_2_2"  label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_11_3"  label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_11_4"  label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_11_5"  label="小计" />
                    <el-table-column align="center" prop="field_11_6"  label="占比" />
                  </el-table-column> 
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="播种期进度3">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_12_2_1"  label="" />
                      <el-table-column align="center" prop="field_12_2_2"  label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_12_3"  label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_12_4"  label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_12_5"  label="小计" />
                    <el-table-column align="center" prop="field_12_6"  label="占比" />
                  </el-table-column> 
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="播种期进度3">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                      <el-table-column align="center" prop="field_12_2_1"  label="" />
                      <el-table-column align="center" prop="field_12_2_2"  label="" />
                    </el-table-column>
                    <el-table-column align="center" prop="field_12_3"  label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_12_4"  label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_12_5"  label="小计" />
                    <el-table-column align="center" prop="field_12_6"  label="占比" />
                  </el-table-column> 
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="播种期进度4">
                    <el-table-column align="center" prop="field_13_1"  label="日期" />                  
                    <el-table-column align="center" prop="field_13_2"  label="已栽插面积（万亩）" />
                    <el-table-column align="center" prop="field_13_3"  label="直播已播种面积（万亩）" />
                    <el-table-column align="center" prop="field_13_4"  label="小计" />
                    <el-table-column align="center" prop="field_13_5"  label="占比" />
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
  name: 'riceTableTwo31',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {        
      activeName:'1',     
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻播栽进度调查表（早稻）',
      tableName:'crop_01_02_table_03_01',
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
          url: '/getAuditRiceTableTwo31',
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



