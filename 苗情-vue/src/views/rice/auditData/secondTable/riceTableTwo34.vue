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
                  <el-table-column align="center" label="进程1（大田直播后20天）">
                    <el-table-column align="center" prop="field_5_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_5_2_1" label="" />
                      <el-table-column align="center" prop="field_5_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_5_3_1" label="" />
                      <el-table-column align="center" prop="field_5_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_5_4_1" label="" />
                      <el-table-column align="center" prop="field_5_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="进程2（大田直播后28天）">
                    <el-table-column align="center" prop="field_6_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_6_2_1" label="" />
                      <el-table-column align="center" prop="field_6_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_6_3_1" label="" />
                      <el-table-column align="center" prop="field_6_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_6_4_1" label="" />
                      <el-table-column align="center" prop="field_6_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程3（大田直播后35天）">
                    <el-table-column align="center" prop="field_7_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_7_2_1" label="" />
                      <el-table-column align="center" prop="field_7_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_7_3_1" label="" />
                      <el-table-column align="center" prop="field_7_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_7_4_1" label="" />
                      <el-table-column align="center" prop="field_7_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="进程4（大田直播后42天）">
                    <el-table-column align="center" prop="field_8_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_8_2_1" label="" />
                      <el-table-column align="center" prop="field_8_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_8_3_1" label="" />
                      <el-table-column align="center" prop="field_8_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_8_4_1" label="" />
                      <el-table-column align="center" prop="field_8_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程5（大田直播后49天）">
                    <el-table-column align="center" prop="field_9_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_9_2_1" label="" />
                      <el-table-column align="center" prop="field_9_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_9_3_1" label="" />
                      <el-table-column align="center" prop="field_9_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_9_4_1" label="" />
                      <el-table-column align="center" prop="field_9_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="进程6（大田直播后56天）">
                    <el-table-column align="center" prop="field_10_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_10_2_1" label="" />
                      <el-table-column align="center" prop="field_10_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_10_3_1" label="" />
                      <el-table-column align="center" prop="field_10_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_10_4_1" label="" />
                      <el-table-column align="center" prop="field_10_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程7（大田直播后66天）">
                    <el-table-column align="center" prop="field_11_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_11_2_1" label="" />
                      <el-table-column align="center" prop="field_11_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_11_3_1" label="" />
                      <el-table-column align="center" prop="field_11_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_11_4_1" label="" />
                      <el-table-column align="center" prop="field_11_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                </el-table>
              </div>
            </el-row>
            <el-row>
              <div style="height:100px;margin-bottom:10px">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'8px'}" row-key="id" stripe >
                  <el-table-column align="center" label="进程8（大田直播后76天）">
                    <el-table-column align="center" prop="field_12_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_12_2_1" label="" />
                      <el-table-column align="center" prop="field_12_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_12_3_1" label="" />
                      <el-table-column align="center" prop="field_12_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_12_4_1" label="" />
                      <el-table-column align="center" prop="field_12_4_2" label="" />
                    </el-table-column>
                  </el-table-column>
                  <el-table-column align="center" label="进程9（大田直播后86天）">
                    <el-table-column align="center" prop="field_13_1"  label="日期" />
                    <el-table-column align="center" label="一类苗（万亩）">
                      <el-table-column align="center" prop="field_13_2_1" label="" />
                      <el-table-column align="center" prop="field_13_2_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="二类苗（万亩）">
                      <el-table-column align="center" prop="field_13_3_1" label="" />
                      <el-table-column align="center" prop="field_13_3_2" label="" />
                    </el-table-column>
                    <el-table-column align="center" label="三类苗（万亩）">
                      <el-table-column align="center" prop="field_13_4_1" label="" />
                      <el-table-column align="center" prop="field_13_4_2" label="" />
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
  name: 'riceTableTwo34',
  inject: ['reload'],
  props: ['parent_msg'],
  data() {
    return {          
      activeName:'1',   
      // 表格组件 列表数据对象
      //表中数据
      tableData: [],
      //表名
      tableLabel:'2021年度监测县(市、区)水稻苗情评价动态列表（早稻）',
      tableName:'crop_01_02_table_03_04',
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
          url: '/getAuditRiceTableTwo34',
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



