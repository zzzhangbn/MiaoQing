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
                    <el-table-column align="center" prop="field_1" label="县（市、区）" />
                    <el-table-column align="center" prop="field_2" width="100%" label="全县总面积（万亩）" />
                    <el-table-column align="center" label="主推品种">
                      <el-table-column align="center" prop="field_3_1"  label="品种1" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_2_1" label="" />
                        <el-table-column align="center" prop="field_3_2_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_3"  label="品种2" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_4_1" label="" />
                        <el-table-column align="center" prop="field_3_4_2" label="" />
                      </el-table-column>
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    <el-table-column align="center" label="主推品种">
                      <el-table-column align="center" prop="field_3_5"  label="品种3" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_6_1" label="" />
                        <el-table-column align="center" prop="field_3_6_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_7"  label="品种4" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_8_1" label="" />
                        <el-table-column align="center" prop="field_3_8_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_9"  label="品种5" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_10_1" label="" />
                        <el-table-column align="center" prop="field_3_10_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_11"  label="品种6" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_12_1" label="" />
                        <el-table-column align="center" prop="field_3_12_2" label="" />
                      </el-table-column>
                    </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                    
                    <el-table-column align="center" label="主推品种">
                      <el-table-column align="center" prop="field_3_13"  label="品种7" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_14_1" label="" />
                        <el-table-column align="center" prop="field_3_14_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_15"  label="品种8" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_16_1" label="" />
                        <el-table-column align="center" prop="field_3_16_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_17"  label="品种9" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_18_1" label="" />
                        <el-table-column align="center" prop="field_3_18_2" label="" />
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_19"  label="品种10" />
                      <el-table-column align="center" label="面积（万亩）">
                        <el-table-column align="center" prop="field_3_20_1" label="" />
                        <el-table-column align="center" prop="field_3_20_2" label="" />
                      </el-table-column>
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
  name: 'riceTableTwo12',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {        
      activeName:'1',     
      // 表格组件 列表数据对象
      //表中分页数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻生产主推品种调查表',
      tableName:'crop_01_01_table_01_02',
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
     if(rowIndex === 2){ 
           return {display:"none"};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'8px',height:'0',padding:'1px',}
        };
    },
    //获取表数据
    getTableData(){
       const _this = this;
       var auditId=this.parentMessage;
       axios({
          url: '/getAuditRiceTable12',
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



