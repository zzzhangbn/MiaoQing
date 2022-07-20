<template>
      <div class="totalRange">
        <el-tabs v-model="activeName">
          <el-tab-pane label="单季稻监测点审核" name="1">
            <div class="tableRange">
              <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                    <el-table-column align="center" width="80%" label="监测点申请审核表">
                      <el-table-column align="center" fixed type="index" width="80%"  label="序号" >
                      </el-table-column>
                      <el-table-column align="center" prop="field_4" label="县（市、区）" >
                      </el-table-column>
                      <el-table-column align="center" prop="modification_time" label="申请时间" >
                      </el-table-column>
                      <el-table-column align="center" label="审核状态">
                        <template >
                          <span>未审核</span>
                        </template>
                      </el-table-column>
                      <el-table-column align="center" width="200px" label="操作">
                      <template slot-scope="scope">                 
                          <span><el-button type="danger" @click="audit(scope.$index,scope.row)" >审核</el-button> </span>
                          </template>
                      </el-table-column>
                    </el-table-column>
              </el-table>
              <el-dialog title="监测点审核" :visible.sync="dialogVisible" :append-to-body="true" width="75%">
                  <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
                      <tr style="height:40px">
                          <td colspan="7"><span style="text-align:center;font-weight:bold;font-size:16px">{{currentYear}}年度水稻监测点建档卡</span></td>
                      </tr>
                      <tr style="height:30px">
                          <td></td>
                          <td>监测点代码：</td>
                          <td colspan="2" class="tdClass">
                            <!-- <el-input v-model="data.field_1" readonly></el-input> -->
                            <span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data.field_1}}</span>
                          </td>
                          <td>监测年份：</td>
                          <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data.field_2}}</span></td>
                      </tr>
                      <tr style="height:30px">
                          <td rowspan="2">地址</td>
                          <td >市</td>
                          <td>县（市、区）</td>
                          <td>镇（乡）</td>
                          <td>村名</td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_6}}</span></td>
                          
                      </tr>
                      <tr style="height:30px">
                          <!-- <td></td> -->
                          <td><!--市--><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_3}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_4}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_5}}</span></td>
                          <td>户主</td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_7}}</span></td>

                      </tr>
                      <tr style="height:30px">
                          <td rowspan="4">监测田块</td>
                          <td>种植面积（亩）</td>
                          <td>地区类型</td>
                          <td>土壤类型</td>
                          <td>前茬类型</td>
                          <td colspan="2">水管方式</td>

                      </tr>
                      <tr style="height:30px">
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_8}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_9}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_10}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_11}}</span></td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_12}}</span></td>
                      </tr>
                      <tr style="height:30px">
                          <td>全氮（g/KG）</td>
                          <td>速效磷(g/Kg)</td>
                          <td>速效钾(g/Kg)</td>
                          <td>有机质(g/Kg)</td>
                          <td colspan="2">秸秆还田量(kg/亩)</td>
                      </tr>
                      <tr style="height:30px">
                      
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_13}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_14}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_15}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_16}}</span></td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_17}}</span></td>

                      </tr>
                      <tr style="height:30px">
                          <td rowspan="2">品种、品系信息</td>
                          <td colspan="2">名称</td>
                          <td colspan="2"> 稻作类型</td>
                          <td colspan="2">品系类型</td>
                      </tr>
                      <tr style="height:30px">
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_18}}</span></td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_19}}</span></td>
                          <td colspan="2"><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_20}}</span></td>
              
                      </tr>
                      <tr style="height:30px">
                          <td rowspan="4">田间信息</td>
                          <td>栽植方式</td>
                          <td>播期</td>
                          <td>移栽秧龄（天）</td>
                          <td>移栽叶龄（叶）</td>
                          <td>基本苗（万/亩）</td>
                          <td>收获日期</td>
                      </tr>
                      <tr style="height:30px">
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_21}}</span></td>
                          <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data.field_22}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_23}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_24}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_25}}</span></td>
                          <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data.field_26}}</span></td>
                      </tr>
                      <tr style="height:30px">
                          <td>亩有效穗(万)</td>
                          <td>穗总粒数 (个)</td>
                          <td>结实率(％)</td>
                          <td>千粒重(g)</td>
                          <td>理论产量(kg/亩)</td>
                          <td>实收产量(kg/亩)</td>
                      </tr>
                      <tr style="height:30px">          
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_27}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_28}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_29}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_30}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_31}}</span></td>
                          <td><span style="text-align:center;color:#030303;font-weight:bold"> {{data.field_32}}</span></td>
                      </tr>
                  </table>
                  <div style="height:80px;margin-top:30px;font-size:16px">
                    <span>退还原因：</span><br/>
                    <!-- <span style="margin-left:40px"><el-select style="width:200px" v-model="returnReason" multiple collapse-tags @change="selectInfo" placeholder="常 见 退 还 原 因">
                      <el-option v-for="item in returnReasons" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                      </el-select></span> -->
                    <textarea id="reason" rows="4" style="width:99%" warp="virtual"></textarea>
                  </div>
                <div slot="footer" class="dialog-footer">
                  <el-row>
                    <el-button @click="dialogVisible = false" style="margin-right:10px">取  消</el-button>
                    <el-popover placement="top" width="200" v-model="visible2"><p>是否退回?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visible2 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="noPass()">确 定</el-button>
                        </div>
                        <el-button slot="reference" style="margin-right:10px" >退 回</el-button>
                    </el-popover>
                    <el-popover placement="top" width="200" v-model="visible1"><p>是否确认通过?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visible1 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="pass()">确 定</el-button>
                        </div>
                        <el-button slot="reference" type="primary" >通  过</el-button>
                    </el-popover>
                  </el-row>
                  </div>
              </el-dialog>
            </div>
          </el-tab-pane>
          <el-tab-pane label="双季稻监测点审核" name="2">
            <div class="tableRange">
              <el-table :data="tableData2" border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                    <el-table-column align="center" width="80%" label="监测点申请审核表">
                      <el-table-column align="center" fixed type="index" width="80%"  label="序号" >
                      </el-table-column>
                      <el-table-column align="center" prop="field_3_2" label="县（市、区）" >
                      </el-table-column>
                      <el-table-column align="center" prop="modification_time" label="申请时间" >
                      </el-table-column>
                      <el-table-column align="center" label="审核状态">
                        <template >
                          <span>未审核</span>
                        </template>
                      </el-table-column>
                      <el-table-column align="center" width="200px" label="操作">
                      <template slot-scope="scope">                 
                          <span><el-button type="danger" @click="audit2(scope.$index,scope.row)" >审核</el-button> </span>
                          </template>
                      </el-table-column>
                    </el-table-column>
              </el-table>
              <el-dialog title="监测点审核" :visible.sync="dialogVisible2" :append-to-body="true" width="75%">
                <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
                    <tr style="height:40px">
                        <td colspan="7"><span style="text-align:center;font-weight:bold;font-size:16px">{{currentYear}}年度水稻监测点建档卡</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td></td>
                        <td>监测点代码：</td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_1}}</span></td>
                        <td>监测年份：</td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_2}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td rowspan="2">地址</td>
                        <td>市</td>
                        <td>县（市、区）</td>
                        <td>镇（乡）</td>
                        <td>村名</td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_3_4}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_3_1}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_3_2}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_3_3}}</span></td>
                        <td>户主</td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_3_5}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td rowspan="4">监测田块</td>
                        <td>种植面积（亩）</td>
                        <td>地区类型</td>
                        <td>土壤类型</td>
                        <td>前茬类型</td>
                        <td colspan="2">水管方式</td>
                    </tr>
                    <tr style="height:30px">
                      <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_1}}</span></td>
                      <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_2}}</span></td>
                      <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_3}}</span></td>
                      <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_4}}</span></td>
                      <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_5}}</span></td>
                    </tr>
                    <tr style="height:30px">
                      <td>全氮（g/KG）</td>
                      <td>速效磷(g/Kg)</td>
                      <td>速效钾(g/Kg)</td>
                      <td>有机质(g/Kg)</td>
                      <td colspan="2">秸秆还田量(kg/亩)</td>
                    </tr>
                    <tr style="height:30px">
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_6}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_7}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_8}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_9}}</span></td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_4_10}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td rowspan="2">品种、品系信息</td>
                        <td colspan="2">名称</td>
                        <td colspan="2">稻作类型</td>
                        <td colspan="2">品系类型</td>
                    </tr>
                    <tr style="height:30px">
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_5_1}}</span></td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_5_2}}</span></td>
                        <td colspan="2"><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_5_3}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td rowspan="4">田间信息</td>
                        <td>栽植方式</td>
                        <td>播期</td>
                        <td>移栽秧龄（天）</td>
                        <td>移栽叶龄（叶）</td>
                        <td>基本苗（万/亩）</td>
                        <td>收获日期</td>
                    </tr>
                    <tr style="height:30px">
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_1}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_2}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_3}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_4}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_5}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_6}}</span></td>
                    </tr>
                    <tr style="height:30px">
                        <td>亩有效穗(万)</td>
                        <td>穗总粒数 (个)</td>
                        <td>结实率(％)</td>
                        <td>千粒重(g)</td>
                        <td>理论产量(kg/亩)</td>
                        <td>实收产量(kg/亩)</td>
                    </tr>
                    <tr style="height:30px">          
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_7}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_8}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_9}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_10}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_11}}</span></td>
                        <td><span style="text-align:center;color:#0F0F0F;font-weight:bold"> {{data2.field_6_12}}</span></td>
                    </tr>
                </table>
                <div style="height:80px;margin-top:30px;font-size:16px">
                    <span>退还原因：</span><br/>
                    <!-- <span style="margin-left:40px"><el-select style="width:200px" v-model="returnReason2" multiple collapse-tags @change="selectInfo2" placeholder="常 见 退 还 原 因">
                      <el-option v-for="item in returnReasons2" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                      </el-select></span> -->
                    <textarea id="reason2" rows="4" style="width:99%" warp="virtual"></textarea>
                </div>
                <div slot="footer" class="dialog-footer">
                  <el-row>
                    <el-button @click="dialogVisible2 = false" style="margin-right:10px">取  消</el-button>
                    <el-popover placement="top" width="200" v-model="visibleTwo2"><p>是否确认退回?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visibleTwo2 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="noPass2()">确 定</el-button>
                        </div>
                        <el-button slot="reference" style="margin-right:10px">退 回</el-button>
                      </el-popover>
                      <el-popover placement="top" width="200" v-model="visibleTwo1"><p>是否确认通过?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visibleTwo1 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="pass2()">确 定</el-button>
                        </div>
                        <el-button slot="reference" type="primary" >通  过</el-button>
                      </el-popover>
                    <!-- <el-button @click="noPass2()">退回</el-button>
                    <el-button type="primary" @click="pass2()">通  过</el-button> -->
                  </el-row>
                  </div>
              </el-dialog>
            </div>
          </el-tab-pane>
        </el-tabs>
        <div class="button">
          <el-button type="success" @click="returnPage">返  回</el-button>
        </div>  
      </div>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
export default {
  data() { 
    return { 
      //表名
      tableName:'crop_01_01_table_02_01',
      tableName2:'crop_01_02_table_02_01',
      activeName:'1',
      tableData:[],
      tableData2:[],
      //系统时间
      systemTime:'',
      //退回原因对话框
      dialogVisible:false,
      dialogVisible2:false,
      //popover对话框
      visible1:false,
      visible2:false,
      visibleTwo1:false,
      visibleTwo2:false,
      //该审核数据对应的记录
      rowId:'',
      rowCounty:'',
      //返回审核表中的所有表名
      rowTableName:[],
      //建档卡
      listArray:[],
      monitorList:[],//监测点代码列表
      tempdata:'1111',
      data:{
          id:'',
          field_1:'',
          field_2:'',
          field_3:'',
          field_4:'',
          field_5:'',
          field_6:'',
          field_7:'',
          field_8:'',
          field_9:'',
          field_10:'',
          field_11:'',
          field_12:'',
          field_13:'',
          field_14:'',
          field_15:'',
          field_16:'',
          field_17:'',
          field_18:'',
          field_19:'',
          field_20:'',
          field_21:'',
          field_22:'',
          field_23:'',
          field_24:'',
          field_25:'',
          field_26:'',
          field_27:'',
          field_28:'',
          field_29:'',
          field_30:'',
          field_31:'',
          field_32:'',
          modification_time:'',
          people:'',
          in_use:'',
          continue_use:'',
          },
      data2:{
          id:'',
          field_1:'',
          field_2:'',
          field_3_1:'',
          field_3_2:'',
          field_3_3:'',
          field_3_4:'',
          field_3_5:'',
          field_4_1:'',
          field_4_2:'',
          field_4_3:'',
          field_4_4:'',
          field_4_5:'',
          field_4_6:'',
          field_4_7:'',
          field_4_8:'',
          field_4_9:'',
          field_4_10:'',
          field_5_1:'',
          field_5_2:'',
          field_5_3:'',
          field_6_1:'',
          field_6_2:'',
          field_6_3:'',
          field_6_4:'',
          field_6_5:'',
          field_6_6:'',
          field_6_7:'',
          field_6_8:'',
          field_6_9:'',
          field_6_10:'',
          field_6_11:'',
          field_6_12:'',
          modification_time:null,
          people:'',
          in_use:'',
          continue_use:'',
          },
      PassData:[],
      PassData2:[],
      //常见退还原因选择框
      returnReasons: [{
          value: '地区类型有误',
          label: '地区类型有误'
        }, {
          value: '稻作类型有误',
          label: '稻作类型有误'
        }, {
          value: '品系类型有误',
          label: '品系类型有误'
        }, {
          value: '品种名称有误',
          label: '品种名称有误'
        }, {
          value: '填报日期有误',
          label: '填报日期有误'
        },{
          value: '数据不全',
          label: '数据不全'
        }],
      returnReason: '',
      returnReasons2: [{
          value: '地区类型有误',
          label: '地区类型有误'
        }, {
          value: '稻作类型有误',
          label: '稻作类型有误'
        }, {
          value: '品系类型有误',
          label: '品系类型有误'
        }, {
          value: '品种名称有误',
          label: '品种名称有误'
        }, {
          value: '填报日期有误',
          label: '填报日期有误'
        },{
          value: '数据不全',
          label: '数据不全'
        }],
      returnReason2: '',
      //本年度时间
      currentYear:'',
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
      this.getTableData2();
    }, 
    //本年度
    getYear(){
      var myDate = new Date();
      this.currentYear = myDate.getFullYear();
    },
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
    
      // 获取当前时间
      getNowTime: function () {
              let dateTime
              let yy = new Date().getFullYear()
              let mm = new Date().getMonth() + 1
              let dd = new Date().getDate()
              let hh = new Date().getHours()
              let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes()
                :
                new Date().getMinutes()
              let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds()
                :
                new Date().getSeconds()
                if(hh < 10){
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                }else{
                  dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                }
              return dateTime
            },
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'19px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px'}
        };
    },
    //获取一季稻数据
    getTableData(){
       const _this = this;
       axios({
          url: '/getPointData',
          method:'get',
       }).then(res=>{
          _this.tableData=res.data;
          //console.log(res.data);
          //console.log(_this.tableData)   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取双季稻数据
    getTableData2(){
       const _this = this;
       axios({
          url: '/getPointData2',
          method:'get',
       }).then(res=>{
          _this.tableData2=res.data;
          //console.log(_this.tableData2)   
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    
    //获取一季稻数据
    getPassData(){
       const _this = this;
       axios({
          url: '/getRiceTable21',
          method:'post',
          params:{county:this.rowCounty}
       }).then(res=>{
          _this.PassData=res.data;
          //console.log(_this.PassData);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取双季稻数据
    getPassData2(){
       const _this = this;
       axios({
          url: '/getRiceTableTwo21',
          method:'post',
          params:{county:this.rowCounty}
       }).then(res=>{
          _this.PassData2=res.data;
          //console.log(_this.PassData2);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //审核一季稻
    audit(index,row){
      this.dialogVisible=true;
      console.log(row);
      this.rowId = row.id;
      //console.log(row.field_22);
      //处理日期格式
      var year = row.field_2;
      if(row.field_2 != null){
         row.field_2 = moment(year).format("YYYY");
      };
      if(row.field_22!=null){
          row.field_22 = moment(row.field_22).format("YYYY-MM-DD");
          //console.log(row.field_22);
      };
      if(row.field_26!=null){
          row.field_26 = moment(row.field_26).format("YYYY-MM-DD");
      }
      this.rowCounty=row.field_4;
      this.data=row;
      //console.log(this.data);
      this.getPassData();
    },
    //审核双季稻
    audit2(index,row){
      this.dialogVisible2=true;
      this.rowId = row.id;
      //console.log(row);
      this.rowCounty=row.field_3_2;
      var year = row.field_2;
      if(row.field_2!=null){
          row.field_2 = moment(year).format("YYYY");
      };
      if(row.field_6_2!=null){
          row.field_6_2 = moment(row.field_6_2).format("YYYY-MM-DD");
      };
      if(row.field_6_6!=null){
          row.field_6_6 = moment(row.field_6_6).format("YYYY-MM-DD");
      }
      this.data2=row;
      //console.log(this.data2);
      this.getPassData2();
    },
    //显示一季稻select框中内容
    selectInfo(value){
      //console.log(value);
      document.getElementById("reason").value=value;
    },
    //显示双季稻select框中内容
    selectInfo2(value){
      //console.log(value);
      document.getElementById("reason2").value=value;
    },
    // 退还一季稻记录确定
    returnData(){
      const _this=this;
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.data.id;
      var recordPeople = this.data.people;
      var recordReason = document.getElementById("reason").value;
      var returnTableName = this.tableName;
      //将原因显示置空
      this.returnReason='';
      document.getElementById("reason").value='';
        var recordCounty=this.data.field_4;
      //console.log(recordTime,recordId,recordPeople,recordReason,returnTableName);
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
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
    },
    // 退还双季稻记录确定
    returnData2(){
      const _this=this;
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.data2.id;
      var recordPeople = this.data2.people;
      var recordReason = document.getElementById("reason2").value;
      var returnTableName = this.tableName2;
      //将原因显示置空
      this.returnReason2='';
      document.getElementById("reason2").value='';
      var recordCounty=this.data2.field_3_2;
      //console.log(recordTime,recordId,recordPeople,recordReason);
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId,
                recordReason:recordReason,
                recordTime:recordTime,
                recordPeople:recordPeople
                }
      }).then(res=>{
          //console.log(res.data);
           _this.getTableData2();

       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });

    },
    // 退回一季稻记录确定
    noPass(){
      const _this=this;
      this.returnData();
      this.visible2=false;
      this.dialogVisible = false;
      axios({
        url:'/noPassPoint',
        menthod:'get',
        params:{recordId:this.rowId}
      }).then(res=>{
          //console.log(res.data);
           _this.getTableData();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    // 退回双季稻记录确定
    noPass2(){
      const _this=this;
      this.returnData2();
      this.visibleTwo2=false;
      this.dialogVisible2 = false;
      axios({
        url:'/noPassPoint2',
        menthod:'get',
        params:{recordId:this.rowId}
      }).then(res=>{
          //console.log(res.data);
           _this.getTableData2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //审核通过将数据插入到表2-2中
    insertInfoToTable0202(){
      this.getMyTime();
      this.data.modification_time=this.systemTime;
      this.data.in_use=3;
      console.log(this.data);
      var info=this.data;
      //console.log(this.data);
      //console.log(this.data.modification_time,this.rowCounty);
      axios({
        url:'/insertInfoToRiceTable0202',
        method:'post',
        params:{infoData:info,
                county:this.rowCounty}
      }).then(res=>{
        this.$message.success("操作成功！");
      }).catch(err=>{
        return this.$message.error("数据保存失败！");
      })
    },
    //审核通过将数据插入到表2-2中
    insertInfoToTableTwo0202(){
      this.getMyTime();
      this.data2.modification_time=this.systemTime;
      this.data2.in_use=3;
      var info=this.data2;
      //console.log(this.data2);
      //console.log(this.data2.modification_time,this.rowCounty);
      axios({
        url:'/insertInfoToRiceTableTwo0202',
        method:'post',
        params:{infoData:info,
                county:this.rowCounty}
      }).then(res=>{
        this.$message.success("操作成功！");
      }).catch(err=>{
        return this.$message.error("数据保存失败！");
      })
    },
    //数据一季稻记录审核通过
    pass(){
      var county = this.data.field_4;
      var pointCode='';
      var tabNumber= this.PassData.length;
      //console.log(tabNumber)
      var tabLength=0;
      if(parseInt(tabNumber) <9){
          tabLength = parseInt(tabNumber)+1;
          //console.log(tabLength);
          pointCode = county +"_" + '00'+tabLength.toString();
      }else if(9<=parseInt(tabNumber) && parseInt(tabNumber)<99){
          tabLength = parseInt(tabNumber)+1;
          pointCode = county +"_" + '0'+tabLength.toString();
      }else{
          tabLength = parseInt(tabNumber)+1;
          pointCode = county +"_" + tabLength.toString();
      };
      var time = this.getNowTime();
      //console.log(pointCode);
      if(this.data.field_1 == '自动生成'){
        this.data.field_1 = pointCode;
        var time = this.getNowTime();
        //插入监测点
        this.insertMonitorInfoToTable0101(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0102(this.data.field_4,this.data.people,time);
        this.insertMonitorInfoToTable0301(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0302(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0303(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0401(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0402(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0403(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.field_25,this.data.people,time);
        this.insertMonitorInfoToTable0404(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0501(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0601(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0602(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0603(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0604(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0605(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0701(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0702(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0703(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0801(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable1001(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0903(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0904(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0905(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0906(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0907(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,this.data.people,time);
      }else if(this.data.continue_use =='1'){
        var time = this.getNowTime();
        //插入监测点
        this.insertMonitorInfoToTable0101(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0102(this.data.field_4,this.data.people,time);
        this.insertMonitorInfoToTable0301(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0302(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0303(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0401(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0402(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0403(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.field_25,this.data.people,time);
        this.insertMonitorInfoToTable0404(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0501(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0601(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0602(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0603(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0604(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0605(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0701(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0702(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0703(this.data.field_4,this.data.field_9,this.data.people,time);
        this.insertMonitorInfoToTable0801(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable1001(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,this.data.people,time);
        this.insertMonitorInfoToTable0903(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0904(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0905(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0906(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.insertMonitorInfoToTable0907(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,this.data.people,time);
      }else{
        //更新监测点        
        this.updateMonitorInfoToTable0101(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0301(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0302(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0303(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0401(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0402(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0403(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.field_25,this.data.people,time);
        this.updateMonitorInfoToTable0404(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0501(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0601(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0602(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0603(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0604(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0605(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0701(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0702(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0703(this.data.field_4,this.data.field_9,this.data.people,time);
        this.updateMonitorInfoToTable0801(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable1001(this.data.field_4,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,this.data.people,time);
        this.updateMonitorInfoToTable0903(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.updateMonitorInfoToTable0904(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.updateMonitorInfoToTable0905(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.updateMonitorInfoToTable0906(this.data.field_4,this.data.field_9,this.data.field_19,this.data.field_20,this.data.people,time);
        this.updateMonitorInfoToTable0907(this.data.field_4,this.data.field_9,this.data.field_1,this.data.field_18,this.data.field_21,this.data.people,time);
      }
      //插入一季表2-2
      this.insertInfoToTable0202();
      const _this=this;
      this.visible1=false;
      this.dialogVisible = false;
      axios({
        url:'/PassPoint',
        method:'post',
        params:{recordId:this.rowId,
                county:this.rowCounty,
                pointCode:this.data.field_1}
      }).then(res=>{
        ////console.log(res.data);
        _this.getTableData();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },
    //数据双季稻记录审核通过
    pass2(){
      var county = this.data2.field_3_2;
      var pointCode='';
      var tabNumber= this.PassData2.length;
      //console.log(tabNumber)
      var tabLength=0;
      if(parseInt(tabNumber) <9){
          tabLength = parseInt(tabNumber)+1;
          //console.log(tabLength);
          pointCode = county +"_" + '00'+tabLength.toString();
      }else if(9<=parseInt(tabNumber) && parseInt(tabNumber)<99){
          tabLength = parseInt(tabNumber)+1;
          pointCode = county +"_" + '0'+tabLength.toString();
      }else{
          tabLength = parseInt(tabNumber)+1;
          pointCode = county +"_" + tabLength.toString();
      };
        var time = this.getNowTime();
      if(this.data2.field_1 == '自动生成'){
         this.data2.field_1 = pointCode;
         this.insertMonitorInfoToTableTwo0101(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0102(this.data2.field_3_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0301(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0302(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0303(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0304(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0305(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0306(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0401(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0402(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0403(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.field_6_11,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0404(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0501(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0601(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0602(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0603(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0604(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0605(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0701(this.data2.field_3_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0702(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0703(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0704(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0801(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0802(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0903(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0904(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0905(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0906(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0907(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo1001(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo1002(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
      }else if(this.data2.continue_use == '1'){
        this.insertMonitorInfoToTableTwo0101(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0102(this.data2.field_3_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0301(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0302(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0303(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0304(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0305(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0306(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0401(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0402(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0403(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.field_6_11,this.data2.people,time);
         this.insertMonitorInfoToTableTwo0404(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0501(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0601(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0602(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0603(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0604(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0605(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0701(this.data2.field_3_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0702(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0703(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0704(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0801(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0802(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0903(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0904(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0905(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0906(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.insertMonitorInfoToTableTwo0907(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo1001(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.insertMonitorInfoToTableTwo1002(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
      }else{
          this.updateMonitorInfoToTableTwo0101(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0301(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0302(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0303(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0304(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0305(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0306(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0401(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0402(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0403(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.field_6_11,this.data2.people,time);
         this.updateMonitorInfoToTableTwo0404(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0501(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0601(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0602(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0603(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0604(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0605(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          // this.updateMonitorInfoToTableTwo0701(this.data2.field_3_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0702(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0703(this.data2.field_3_2,this.data2.field_4_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0704(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0801(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0802(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0903(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0904(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0905(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0906(this.data2.field_3_2,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_3,this.data2.people,time);
          this.updateMonitorInfoToTableTwo0907(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo1001(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
          this.updateMonitorInfoToTableTwo1002(this.data2.field_3_2,this.data2.field_1,this.data2.field_4_2,this.data2.field_5_2,this.data2.field_5_1,this.data2.field_6_1,this.data2.people,time);
      }
      //插入雙季表2-2
      this.insertInfoToTableTwo0202();
     const _this=this;
     this.visibleTwo1=false;
      this.dialogVisible2 = false;
      axios({
        url:'/PassPoint2',
        method:'post',
        params:{recordId:this.rowId,
                county:this.rowCounty,
                pointCode:this.data2.field_1}
      }).then(res=>{
        ////console.log(res.data);
        _this.getTableData2();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },


    //把监测点的信息插入到表1-1 中
        insertMonitorInfoToTable0101(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0101',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time,
            }
          }).then(res=>{
            //console.log('表1-1监测点信息更新成功！');
          }).catch(err=>{
            return this.$message.error("表1-1监测点信息更新失败！");
          });
        },
    //把监测点的信息更新入到表1-1 中
      updateMonitorInfoToTable0101(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0101',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time,
            }
          }).then(res=>{
            //console.log('表1-1监测点信息更新成功！');
          }).catch(err=>{
            return this.$message.error("表1-1监测点信息更新失败！");
          });
        },        
          //把监测点的信息插入到表1-2 中
          insertMonitorInfoToTable0102(area,people,time){
          axios({
            url:'/insertMonitorInfoToTable0102',
            method:'post',
            params:{
              area:area,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log('表1-2监测点信息更新成功！');
          }).catch(err=>{
            return this.$message.error("表1-2监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表3-1 中
        insertMonitorInfoToTable0301(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0301',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-1监测点信息更新失败！");
          });
        },
        //把监测点的信息更新入到表3-1 中
        updateMonitorInfoToTable0301(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0301',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-1监测点信息更新失败！");
          });
        },        
      //把监测点的信息插入到表3-2 中
        insertMonitorInfoToTable0302(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0302',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-2监测点信息更新失败！");
          });
        },
      //把监测点的信息更新入到表3-2 中
        updateMonitorInfoToTable0302(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0302',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-2监测点信息更新失败！");
          });
        },        
        //把监测点的信息插入到表3-3 中
        insertMonitorInfoToTable0303(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0303',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-3监测点信息更新失败！");
          });
        },
        //把监测点的信息更新入到表3-3 中
        updateMonitorInfoToTable0303(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0303',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表3-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-3监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表7-1 中
        insertMonitorInfoToTable0701(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0701',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-1监测点信息更新失败！");
          });
        },


        //把监测点的信息更新入到表7-1 中
        updateMonitorInfoToTable0701(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0701',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表7-2 中
        insertMonitorInfoToTable0702(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0702',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-2监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表7-2 中
        updateMonitorInfoToTable0702(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0702',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-2监测点信息更新失败！");
          });
        },        

        //把监测点的信息插入到表7-3 中
        insertMonitorInfoToTable0703(area,areaCate,people,time){
          axios({
            url:'/insertMonitorInfoToTable0703',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表7-3 中
        updateMonitorInfoToTable0703(area,areaCate,people,time){
          axios({
            url:'/updateMonitorInfoToTable0703',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              time:time
            }
          }).then(res=>{
            //console.log("表7-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表8-1 中
        insertMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0801',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              MonitorBlock:MonitorBlock,
              people:people,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            console.log("表8-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表8-1监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表8-1 中
        updateMonitorInfoToTable0801(area,MonitorBlock,areaCate,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0801',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              MonitorBlock:MonitorBlock,
              people:people,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            console.log("表8-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表8-1监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表9-3 中
        insertMonitorInfoToTable0903(area,diQuType,daoZuoType,pinXiType,people,time){
          //console.log(time);
          var diQuType='';
          axios({
            url:'/insertMonitorInfoToTable0903',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

       //把监测点的信息更新入到表9-3 中
        updateMonitorInfoToTable0903(area,diQuType,daoZuoType,pinXiType,people,time){
          //console.log(time);
          axios({
            url:'/updateMonitorInfoToTable0903',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表9-4 中
        insertMonitorInfoToTable0904(area,diQuType,daoZuoType,pinXiType,people,time){
          var diQuType='';
          axios({
            url:'/insertMonitorInfoToTable0904',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表9-4 中
        updateMonitorInfoToTable0904(area,diQuType,daoZuoType,pinXiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0904',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表9-5 中
        insertMonitorInfoToTable0905(area,diQuType,daoZuoType,pinXiType,people,time){
          var diQuType='';
          axios({
            url:'/insertMonitorInfoToTable0905',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-5监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表9-5 中
        updateMonitorInfoToTable0905(area,diQuType,daoZuoType,pinXiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0905',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-5监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表9-6 中
        insertMonitorInfoToTable0906(area,diQuType,daoZuoType,pinXiType,people,time){
          var diQuType='';
          axios({
            url:'/insertMonitorInfoToTable0906',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-6监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表9-6 中
        updateMonitorInfoToTable0906(area,diQuType,daoZuoType,pinXiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0906',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-6监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表9-6监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表9-7 中
        updateMonitorInfoToTable0907(area,diQuType,MonitorBlock,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0907',
            method:'post',
            params:{
              area:area,
              diQuType:diQuType,
              MonitorBlock:MonitorBlock,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              people:people,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-6监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表9-7监测点信息更新失败！");
          });
        },        

        //把监测点的信息插入到表4-1 中
        insertMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0401',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-1监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表4-1 中
        updateMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0401',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-2 中
        insertMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0402',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-2监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表4-2 中
        updateMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0402',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-3 中
        insertMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time){
          axios({
            url:'/insertMonitorInfoToTable0403',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              jiBenMiao:jiBenMiao,
              time:time
            }
          }).then(res=>{
            //console.log("表4-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-2监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表4-3 中
        updateMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time){
          axios({
            url:'/updateMonitorInfoToTable0403',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              jiBenMiao:jiBenMiao,
              time:time
            }
          }).then(res=>{
            //console.log("表4-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-3监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表4-4 中
        insertMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0404',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-4监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表4-4 中
        updateMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0404',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinXiType:pinXiType,
              pinZhongType:pinZhongType,
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表4-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-4监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表5-1 中
        insertMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0501',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表5-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表5-1监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表5-1 中
        updateMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0501',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表5-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表5-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-1 中
        insertMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0601',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-1监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表6-1 中
        updateMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0601',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-2 中
        insertMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0602',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-2监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表6-2 中
        updateMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0602',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-3 中
        insertMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0603',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-3监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表6-3 中
        updateMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0603',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-4 中
        insertMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0604',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-4监测点信息更新失败！");
          });
        },

       //把监测点的信息更新入到表6-4 中
        updateMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0604',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-4监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-5 中
        insertMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0605',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-5监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-5监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表6-5 中
        updateMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable0605',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              daoZuoType:daoZuoType,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表6-5监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-5监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表9-7 中
        insertMonitorInfoToTable0907(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable0907',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              modification_time:time
            }
          }).then(res=>{
            //console.log("表9-7监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表9-7监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表10-1 中
        insertMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/insertMonitorInfoToTable1001',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表10-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表10-1监测点信息更新失败！");
          });
        },

        //把监测点的信息更新入到表10-1 中
        updateMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people,time){
          axios({
            url:'/updateMonitorInfoToTable1001',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people,
              MonitorBlock:MonitorBlock,
              pinZhongType:pinZhongType,//品种名称
              zaiZhiType:zaiZhiType,
              time:time
            }
          }).then(res=>{
            //console.log("表10-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表10-1监测点信息更新失败！");
          });
        },        

//双季稻插入
    //把监测点的信息插入到表1-1 中
            insertMonitorInfoToTableTwo0101(area,areaCate,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0101',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log('表1-1监测点信息更新成功！');
              }).catch(err=>{
                return this.$message.error("表1-1监测点信息更新失败！");
              });
            },
              //把监测点的信息插入到表1-2 中
              insertMonitorInfoToTableTwo0102(area,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0102',
                method:'post',
                params:{
                  area:area,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log('表1-2监测点信息更新成功！');
              }).catch(err=>{
                return this.$message.error("表1-2监测点信息更新失败！");
              });
            },
            //把监测点的信息插入到表3-1 中
            insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0301',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-1监测点信息更新失败！");
              });
            },
          //把监测点的信息插入到表3-2 中
            insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0302',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-2监测点信息更新失败！");
              });
            },
            //把监测点的信息插入到表3-3 中
            insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0303',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-3监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-4 中
            insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0304',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-5 中
            insertMonitorInfoToTableTwo0305(area,areaCate,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0305',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-5监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-6 中
            insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0306',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-6监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-6监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-1 中
            insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0401',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-1监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-2 中
            insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0402',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-2监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-3 中
            insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0403',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  jiBenMiao:jiBenMiao,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-3监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-4 中
            insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0404',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表5-1 中
            insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0501',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表5-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表5-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-1 中
          insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0601',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-2 中
          insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0602',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-2监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-3 中
          insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0603',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-3监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-4 中
          insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0604',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-4监测点信息更新失败！");
              });
            },
            
          //把监测点的信息插入到表6-5 中
          insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0605',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-5监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表7-1 中
            insertMonitorInfoToTableTwo0701(area,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0701',
                method:'post',
                params:{
                  area:area,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-1监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-2 中
            insertMonitorInfoToTableTwo0702(area,areaCate,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0702',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-2监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-3 中
            insertMonitorInfoToTableTwo0703(area,areaCate,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0703',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-3监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表8-1 中
            insertMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0801',
                method:'post',
                params:{
                  area:area,
                  MonitorBlock:MonitorBlock,
                  areaCate:areaCate,
                  people:people,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                console.log("二季稻：表8-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表8-1监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表8-2 中
            insertMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0802',
                method:'post',
                params:{
                  area:area,
                  MonitorBlock:MonitorBlock,
                  areaCate:areaCate,
                  people:people,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                console.log("二季稻：表8-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表8-2监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-4 中
            insertMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0704',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-3 中
            insertMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time){
              var areaCate='';
              axios({
                url:'/insertMonitorInfoToTableTwo0903',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-3监测点信息更新失败！");
              });
            },

             //把监测点的信息插入到表9-4 中
            insertMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time){
              var areaCate='';
              axios({
                url:'/insertMonitorInfoToTableTwo0904',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-5 中
            insertMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time){
              var areaCate='';
              axios({
                url:'/insertMonitorInfoToTableTwo0905',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-5监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-6 中
            insertMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time){
              var areaCate='';
              axios({
                url:'/insertMonitorInfoToTableTwo0906',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-6监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-7 中
            insertMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo0907',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  MonitorBlock:MonitorBlock,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-7监测点信息更新失败！");
              });
            },



          //把监测点的信息插入到表10-1 中
          insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo1001',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表10-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表10-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表10-2 中
          insertMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/insertMonitorInfoToTableTwo1002',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表10-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表10-2监测点信息更新失败！");
              });
            },

 //更新二季稻监测点
             updateMonitorInfoToTableTwo0101(area,areaCate,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0101',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log('表1-1监测点信息更新成功！');
              }).catch(err=>{
                return this.$message.error("表1-1监测点信息更新失败！");
              });
            },
              //把监测点的信息插入到表1-2 中
              updateMonitorInfoToTableTwo0102(area,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0102',
                method:'post',
                params:{
                  area:area,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log('表1-2监测点信息更新成功！');
              }).catch(err=>{
                return this.$message.error("表1-2监测点信息更新失败！");
              });
            },
            //把监测点的信息插入到表3-1 中
            updateMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0301',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-1监测点信息更新失败！");
              });
            },
          //把监测点的信息插入到表3-2 中
            updateMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0302',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-2监测点信息更新失败！");
              });
            },
            //把监测点的信息插入到表3-3 中
            updateMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0303',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-3监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-4 中
            updateMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0304',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-5 中
            updateMonitorInfoToTableTwo0305(area,areaCate,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0305',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-5监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-6 中
            updateMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0306',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:'',
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表3-6监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-6监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-1 中
            updateMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0401',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-1监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-2 中
            updateMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0402',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-2监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-3 中
            updateMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0403',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  jiBenMiao:jiBenMiao,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-3监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-4 中
            updateMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0404',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表4-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表5-1 中
            updateMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0501',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表5-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表5-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-1 中
          updateMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0601',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-2 中
          updateMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0602',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-2监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-3 中
          updateMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0603',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-3监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-4 中
          updateMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0604',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-4监测点信息更新失败！");
              });
            },
            
          //把监测点的信息插入到表6-5 中
          updateMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0605',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表6-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-5监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表7-1 中
            updateMonitorInfoToTableTwo0701(area,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0701',
                method:'post',
                params:{
                  area:area,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-1监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-2 中
            updateMonitorInfoToTableTwo0702(area,areaCate,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0702',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-2监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-3 中
            updateMonitorInfoToTableTwo0703(area,areaCate,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0703',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-3监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表8-1 中
            updateMonitorInfoToTableTwo0801(area,MonitorBlock,areaCate,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0801',
                method:'post',
                params:{
                  area:area,
                  MonitorBlock:MonitorBlock,
                  areaCate:areaCate,
                  people:people,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                console.log("二季稻：表8-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表8-1监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表8-2 中
            updateMonitorInfoToTableTwo0802(area,MonitorBlock,areaCate,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0802',
                method:'post',
                params:{
                  area:area,
                  MonitorBlock:MonitorBlock,
                  areaCate:areaCate,
                  people:people,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                console.log("二季稻：表8-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表8-2监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-4 中
            updateMonitorInfoToTableTwo0704(area,areaCate,daoZuoType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0704',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-3 中
            updateMonitorInfoToTableTwo0903(area,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0903',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-3监测点信息更新失败！");
              });
            },

             //把监测点的信息插入到表9-4 中
            updateMonitorInfoToTableTwo0904(area,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0904',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-5 中
            updateMonitorInfoToTableTwo0905(area,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0905',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-5监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-6 中
            updateMonitorInfoToTableTwo0906(area,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0906',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-6监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表9-7 中
            updateMonitorInfoToTableTwo0907(area,MonitorBlock,areaCate,daoZuoType,pinXiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo0907',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  daoZuoType:daoZuoType,
                  MonitorBlock:MonitorBlock,
                  pinXiType:pinXiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表7-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表9-7监测点信息更新失败！");
              });
            },



          //把监测点的信息插入到表10-1 中
          updateMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo1001',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表10-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表10-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表10-2 中
          updateMonitorInfoToTableTwo1002(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people,time){
              axios({
                url:'/updateMonitorInfoToTableTwo1002',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                  time:time
                }
              }).then(res=>{
                //console.log("表10-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表10-2监测点信息更新失败！");
              });
            }, 
  
    //返回
    returnPage(){
        this.$router.push("/riceHome");
    },
  },

};

</script>

<style scoped>
  .totalRange{
    width:100%;
    height: 100%;
  }
  .button{
    position: absolute;right: 60px;top: 105px;
    color:black;
    font-weight: 600;
    font-size: 14px;
  }
  .totalRange .tableRange{
    width:60%;
    margin: 0 auto;
  }
  .el-button--danger{
    border: 1px solid #000000;
    padding:2px;
    width: 70px;
    height: 30px;
  }
  .el-tabs_item{
    font-size: 18px !important;
  
  }
  .tdClass{
    text-align: center;
    color:rgb(214, 43, 43),
  }
</style>



