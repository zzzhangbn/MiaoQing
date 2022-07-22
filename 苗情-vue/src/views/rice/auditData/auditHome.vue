<template>
      <div class="totalRange">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="一季稻数据审核" name="1">
            <div class="tableRange">
              <div class="searchBox">            
              <el-row>
                <el-col :span="8">
                  <template>
                   <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                  </template>
                </el-col>
                <el-col :span="4">
                  <el-button type="info" @click="reSet1">重置</el-button>
                  <el-button type="info" @click="searchInfo1">查询</el-button>
                </el-col>
              </el-row>
              </div>
              <div class="tableStyle">
                <el-table :data="tableData" border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                      <el-table-column align="center" width="80%" label="数据审核表">
                        <el-table-column align="center" fixed type="index" width="80%" :index="countMethod1"  label="序号" >
                        </el-table-column>
                        <el-table-column align="center" prop="county" label="县（市、区）" >
                        </el-table-column>
                        <el-table-column align="center" prop="time" label="上报时间" >
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
                <el-pagination class="fy"
                        layout="prev, pager, next, total"
                        @current-change="handleCurrentChange1"
                        :current-page="currentPage1"
                        :page-size="pagesize1"
                        :total="dataNum1"
                        background
                        >
                </el-pagination>
                <el-dialog title="审核" :visible.sync="dialogVisible" :append-to-body="true" width="90%">
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_01_01'">
                      <riceTable11 :parent_msg="parent_msg"></riceTable11>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_01_02'">
                      <riceTable12 :parent_msg="parent_msg"></riceTable12>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_02_02'">
                      <riceTable22 :parent_msg="parent_msg"></riceTable22>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_03_01'">
                      <riceTable31 :parent_msg="parent_msg"></riceTable31>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_03_02'">
                      <riceTable32 :parent_msg="parent_msg"></riceTable32>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_03_03'">
                      <riceTable33 :parent_msg="parent_msg"></riceTable33>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_04_01'">
                      <riceTable41 :parent_msg="parent_msg"></riceTable41>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_04_02'">
                      <riceTable42 :parent_msg="parent_msg"></riceTable42>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_04_03'">
                      <riceTable43 :parent_msg="parent_msg"></riceTable43>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_04_04'">
                      <riceTable44 :parent_msg="parent_msg"></riceTable44>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_05_01'">
                      <riceTable51 :parent_msg="parent_msg"></riceTable51>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_06_01'">
                      <riceTable61 :parent_msg="parent_msg"></riceTable61>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_06_02'">
                      <riceTable62 :parent_msg="parent_msg"></riceTable62>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_06_03'">
                      <riceTable63 :parent_msg="parent_msg"></riceTable63>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_06_04'">
                      <riceTable64 :parent_msg="parent_msg"></riceTable64>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_06_05'">
                      <riceTable65 :parent_msg="parent_msg"></riceTable65>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_07_01'">
                      <riceTable71 :parent_msg="parent_msg"></riceTable71>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_07_02'">
                      <riceTable72 :parent_msg="parent_msg"></riceTable72>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_07_03'">
                      <riceTable73 :parent_msg="parent_msg"></riceTable73>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_08_01'">
                      <riceTable81 :parent_msg="parent_msg"></riceTable81>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_01'">
                      <riceTable91 :parent_msg="parent_msg"></riceTable91>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_02'">
                      <riceTable92 :parent_msg="parent_msg"></riceTable92>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_03'">
                      <riceTable93 :parent_msg="parent_msg"></riceTable93>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_04'">
                      <riceTable94 :parent_msg="parent_msg"></riceTable94>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_05'">
                      <riceTable95 :parent_msg="parent_msg"></riceTable95>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_06'">
                      <riceTable96 :parent_msg="parent_msg"></riceTable96>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_09_07'">
                      <riceTable97 :parent_msg="parent_msg"></riceTable97>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_01_table_10_01'">
                      <riceTable101 :parent_msg="parent_msg"></riceTable101>
                    </div>
                    <div style="height:80px;margin-top:30px;font-size:16px">
                      <span >退还原因：</span>
                      <span style="margin-left:40px"><el-select style="width:200px" v-model="returnReason" multiple collapse-tags @change="selectInfo" placeholder="常 见 退 还 原 因">
                        <el-option v-for="item in returnReasons" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select></span><br/>
                      <textarea id="reason" rows="4" style="width:90%" warp="virtual"></textarea>
                    </div>
                  <div slot="footer" class="dialog-footer">
                    <el-row>
                      <el-button @click="dialogVisible = false" style="margin-right:10px">取  消</el-button>
                      <el-popover placement="top" width="200" v-model="visible2"><p>是否确认退回?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visible2 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="returnData()">确 定</el-button>
                        </div>
                        <el-button slot="reference" style="margin-right:10px" >退  回</el-button>
                      </el-popover>
                      <el-popover placement="top" width="200" v-model="visible1"><p>是否确认通过?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visible1 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="confirmRecord()">确 定</el-button>
                        </div>
                        <el-button slot="reference" type="primary" >通  过</el-button>
                      </el-popover>
                    </el-row>
                    </div>
                </el-dialog>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="双季稻数据审核" name="2">
            <div class="tableRange">
              <div class="searchBox">            
              <el-row>
                <el-col :span="8">
                  <template>
                   <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                  </template>
                </el-col>
                <el-col :span="4">
                  <el-button type="info" @click="reSet2">重置</el-button>
                  <el-button type="info" @click="searchInfo2">查询</el-button>
                </el-col>
              </el-row>
              </div>
              <div class="tableStyle">
                <el-table :data="tableData2" border :header-cell-style="headerStyle" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" row-key="id" stripe>
                      <el-table-column align="center" width="80%" label="数据审核表">
                        <el-table-column align="center" fixed type="index" width="80%" :index="countMethod2" label="序号" >
                        </el-table-column>
                        <el-table-column align="center" prop="county" label="县（市、区）" >
                        </el-table-column>
                        <el-table-column align="center" prop="time" label="上报时间" >
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
                <el-pagination class="fy"
                        layout="prev, pager, next, total"
                        @current-change="handleCurrentChange2"
                        :current-page="currentPage2"
                        :page-size="pagesize2"
                        :total="dataNum2"
                        background
                        >
                </el-pagination>
                <el-dialog title="审核" :visible.sync="dialogVisible2" :append-to-body="true" width="75%">
                    <!-- 双季稻组件 -->
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_01_01'">
                      <riceTableTwo11 :parent_msg="parent_msg"></riceTableTwo11>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_01_02'">
                      <riceTableTwo12 :parent_msg="parent_msg"></riceTableTwo12>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_02_02'">
                      <riceTableTwo22 :parent_msg="parent_msg"></riceTableTwo22>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_01'">
                      <riceTableTwo31 :parent_msg="parent_msg"></riceTableTwo31>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_02'">
                      <riceTableTwo32 :parent_msg="parent_msg"></riceTableTwo32>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_03'">
                      <riceTableTwo33 :parent_msg="parent_msg"></riceTableTwo33>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_04'">
                      <riceTableTwo34 :parent_msg="parent_msg"></riceTableTwo34>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_05'">
                      <riceTableTwo35 :parent_msg="parent_msg"></riceTableTwo35>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_03_06'">
                      <riceTableTwo36 :parent_msg="parent_msg"></riceTableTwo36>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_04_01'">
                      <riceTableTwo41 :parent_msg="parent_msg"></riceTableTwo41>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_04_02'">
                      <riceTableTwo42 :parent_msg="parent_msg"></riceTableTwo42>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_04_03'">
                      <riceTableTwo43 :parent_msg="parent_msg"></riceTableTwo43>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_04_04'">
                      <riceTableTwo44 :parent_msg="parent_msg"></riceTableTwo44>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_05_01'">
                      <riceTableTwo51 :parent_msg="parent_msg"></riceTableTwo51>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_06_01'">
                      <riceTableTwo61 :parent_msg="parent_msg"></riceTableTwo61>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_06_02'">
                      <riceTableTwo62 :parent_msg="parent_msg"></riceTableTwo62>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_06_03'">
                      <riceTableTwo63 :parent_msg="parent_msg"></riceTableTwo63>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_06_04'">
                      <riceTableTwo64 :parent_msg="parent_msg"></riceTableTwo64>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_06_05'">
                      <riceTableTwo65 :parent_msg="parent_msg"></riceTableTwo65>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_07_01'">
                      <riceTableTwo71 :parent_msg="parent_msg"></riceTableTwo71>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_07_02'">
                      <riceTableTwo72 :parent_msg="parent_msg"></riceTableTwo72>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_07_03'">
                      <riceTableTwo73 :parent_msg="parent_msg"></riceTableTwo73>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_07_04'">
                      <riceTableTwo74 :parent_msg="parent_msg"></riceTableTwo74>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_08_01'">
                      <riceTableTwo81 :parent_msg="parent_msg"></riceTableTwo81>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_08_02'">
                      <riceTableTwo82 :parent_msg="parent_msg"></riceTableTwo82>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_01'">
                      <riceTableTwo91 :parent_msg="parent_msg"></riceTableTwo91>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_02'">
                      <riceTableTwo92 :parent_msg="parent_msg"></riceTableTwo92>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_03'">
                      <riceTableTwo93 :parent_msg="parent_msg"></riceTableTwo93>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_04'">
                      <riceTableTwo94 :parent_msg="parent_msg"></riceTableTwo94>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_05'">
                      <riceTableTwo95 :parent_msg="parent_msg"></riceTableTwo95>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_06'">
                      <riceTableTwo96 :parent_msg="parent_msg"></riceTableTwo96>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_09_07'">
                      <riceTableTwo97 :parent_msg="parent_msg"></riceTableTwo97>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_10_01'">
                      <riceTableTwo101 :parent_msg="parent_msg"></riceTableTwo101>
                    </div>
                    <div style="height:100%" v-if="this.tableName=='crop_01_02_table_10_02'">
                      <riceTableTwo102 :parent_msg="parent_msg"></riceTableTwo102>
                    </div>

                    <div style="height:80px;margin-top:30px;font-size:16px">
                      <span>退还原因：</span>
                      <span style="margin-left:40px"><el-select style="width:200px" v-model="returnReason2" multiple collapse-tags @change="selectInfo2" placeholder="常 见 退 还 原 因">
                        <el-option v-for="item in returnReasons2" :key="item.value" :label="item.label" :value="item.value"> </el-option>
                        </el-select></span><br/>
                      <textarea id="reason2" rows="4" style="width:90%" warp="virtual"></textarea>
                      
                    </div>
                  <div slot="footer" class="dialog-footer">
                    <el-row>
                      <el-button @click="dialogVisible2 = false" style="margin-right:10px">取  消</el-button>
                      <el-popover placement="top" width="200" v-model="visibleTwo2"><p>是否确认退回?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visibleTwo2 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="returnData2()">确 定</el-button>
                        </div>
                        <el-button slot="reference" style="margin-right:10px">退  回</el-button>
                      </el-popover>
                      <el-popover placement="top" width="200" v-model="visibleTwo1"><p>是否确认通过?</p>
                        <div style="text-align: right; margin: 10px">
                          <el-button size="mini" type="text" @click="visibleTwo1 = false">取 消</el-button>
                          <el-button type="primary" size="mini" @click="confirmRecord2()">确 定</el-button>
                        </div>
                        <el-button slot="reference" type="primary" >通  过</el-button>
                      </el-popover>
                      <!-- <el-button @click="returnData2()">退  回</el-button>
                      <el-button type="primary" @click="confirmRecord2()">通  过</el-button> -->
                    </el-row>
                    </div>
                </el-dialog>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="退回记录查看" name="3">
            <div class="tableRange">
              <div class="searchBox">            
              <el-row>
                <el-col :span="6">
                  <template>
                    <el-input placeholder="请输入表名" style="width:220px"  v-model="params1"> </el-input>
                  </template>
                </el-col>
                <el-col :span="6">
                  <template>
                   <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="params2"> </el-input>
                  </template>
                </el-col>
                <el-col :span="4">
                  <el-button type="info" @click="reSet">重置</el-button>
                  <el-button type="info" @click="searchInfo">查询</el-button>
                </el-col>
              </el-row>
              </div>
              <div class="tableStyle">
                <el-table border :header-cell-style="headerStyle3" :row-style="{height: '35px'}" :cell-style="{color:'#000000',padding:'0px',fontSize:'14px'}" :data="tableBackData">
                  <el-table-column align="center" width="80%" label="数据退回记录表">
                    <el-table-column align="center" fixed type="index" width="100%" :index="countMethod" label="序号" />
                    <el-table-column prop="second_name" label="表名" sortable align="center" style="word-break:break-all; word-wrap:break-all;" >
                    </el-table-column>
                    <el-table-column prop="record_county" sortable  align="center"  width="160%"  label="县（市、区）">
                    </el-table-column>
                    <el-table-column prop="back_time"  align="center"  label="退回时间" width="180%" :formatter="dateFormat" >
                    </el-table-column>
                    <el-table-column prop="reason"  align="center"  style="word-break:break-all; word-wrap:break-all;" label="退回原因">
                    </el-table-column>
                  </el-table-column>
                </el-table>
                <el-pagination class="fy"
                        layout="prev, pager, next, total"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-size="pagesize"
                        :total="dataNum"
                        background
                        >
                </el-pagination>
              </div>
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
import riceTable11 from './firstTable/riceTable11.vue'
import riceTable12 from './firstTable/riceTable12.vue'
import riceTable22 from './firstTable/riceTable22.vue'
import riceTable31 from './firstTable/riceTable31.vue'
import riceTable32 from './firstTable/riceTable32.vue'
import riceTable33 from './firstTable/riceTable33.vue'
import riceTable41 from './firstTable/riceTable41.vue'
import riceTable42 from './firstTable/riceTable42.vue'
import riceTable43 from './firstTable/riceTable43.vue'
import riceTable44 from './firstTable/riceTable44.vue'
import riceTable51 from './firstTable/riceTable51.vue'
import riceTable61 from './firstTable/riceTable61.vue'
import riceTable62 from './firstTable/riceTable62.vue'
import riceTable63 from './firstTable/riceTable63.vue'
import riceTable64 from './firstTable/riceTable64.vue'
import riceTable65 from './firstTable/riceTable65.vue'
import riceTable71 from './firstTable/riceTable71.vue'
import riceTable72 from './firstTable/riceTable72.vue'
import riceTable73 from './firstTable/riceTable73.vue'
import riceTable81 from './firstTable/riceTable81.vue'
import riceTable91 from './firstTable/riceTable91.vue'
import riceTable92 from './firstTable/riceTable92.vue'
import riceTable93 from './firstTable/riceTable93.vue'
import riceTable94 from './firstTable/riceTable94.vue'
import riceTable95 from './firstTable/riceTable95.vue'
import riceTable96 from './firstTable/riceTable96.vue'
import riceTable97 from './firstTable/riceTable97.vue'
import riceTable101 from './firstTable/riceTable101.vue'
import riceTableTwo11 from './secondTable/riceTableTwo11.vue'
import riceTableTwo12 from './secondTable/riceTableTwo12.vue'
import riceTableTwo22 from './secondTable/riceTableTwo22.vue'
import riceTableTwo31 from './secondTable/riceTableTwo31.vue'
import riceTableTwo32 from './secondTable/riceTableTwo32.vue'
import riceTableTwo33 from './secondTable/riceTableTwo33.vue'
import riceTableTwo34 from './secondTable/riceTableTwo34.vue'
import riceTableTwo35 from './secondTable/riceTableTwo35.vue'
import riceTableTwo36 from './secondTable/riceTableTwo36.vue'
import riceTableTwo41 from './secondTable/riceTableTwo41.vue'
import riceTableTwo42 from './secondTable/riceTableTwo42.vue'
import riceTableTwo43 from './secondTable/riceTableTwo43.vue'
import riceTableTwo44 from './secondTable/riceTableTwo44.vue'
import riceTableTwo51 from './secondTable/riceTableTwo51.vue'
import riceTableTwo61 from './secondTable/riceTableTwo61.vue'
import riceTableTwo62 from './secondTable/riceTableTwo62.vue'
import riceTableTwo63 from './secondTable/riceTableTwo63.vue'
import riceTableTwo64 from './secondTable/riceTableTwo64.vue'
import riceTableTwo65 from './secondTable/riceTableTwo65.vue'
import riceTableTwo71 from './secondTable/riceTableTwo71.vue'
import riceTableTwo72 from './secondTable/riceTableTwo72.vue'
import riceTableTwo73 from './secondTable/riceTableTwo73.vue'
import riceTableTwo74 from './secondTable/riceTableTwo74.vue'
import riceTableTwo81 from './secondTable/riceTableTwo81.vue'
import riceTableTwo82 from './secondTable/riceTableTwo82.vue'
import riceTableTwo91 from './secondTable/riceTableTwo91.vue'
import riceTableTwo92 from './secondTable/riceTableTwo92.vue'
import riceTableTwo93 from './secondTable/riceTableTwo93.vue'
import riceTableTwo94 from './secondTable/riceTableTwo94.vue'
import riceTableTwo95 from './secondTable/riceTableTwo95.vue'
import riceTableTwo96 from './secondTable/riceTableTwo96.vue'
import riceTableTwo97 from './secondTable/riceTableTwo97.vue'
import riceTableTwo101 from './secondTable/riceTableTwo101.vue'
import riceTableTwo102 from './secondTable/riceTableTwo102.vue'
export default {
  //配置组件
  components:{
    riceTable11,riceTable12,riceTable22,riceTable31,riceTable32,riceTable33,riceTable41,riceTable42,riceTable43,riceTable44,riceTable51,riceTable61,riceTable62,riceTable63,
    riceTable64,riceTable65,riceTable71,riceTable72,riceTable73,riceTable81,riceTable91,riceTable92,riceTable93,riceTable94,riceTable95,riceTable96,riceTable97,riceTable101,

    riceTableTwo11,riceTableTwo12,riceTableTwo22,riceTableTwo31,riceTableTwo32,riceTableTwo33,riceTableTwo34,riceTableTwo35,riceTableTwo36,riceTableTwo41,riceTableTwo42,
    riceTableTwo43,riceTableTwo44,riceTableTwo51,riceTableTwo61,riceTableTwo62,riceTableTwo63,riceTableTwo64,riceTableTwo65,riceTableTwo71,riceTableTwo72,riceTableTwo73,
    riceTableTwo74,riceTableTwo81,riceTableTwo82,riceTableTwo91,riceTableTwo92,riceTableTwo93,riceTableTwo94,riceTableTwo95,riceTableTwo96,riceTableTwo97,riceTableTwo101,riceTableTwo102,
  },
  data() { 
    return { 
      activeName:'1',
      tableData:[],
      tableData2:[],
      //审核搜索
      countyName1:'',
      countyName2:'',
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
      rowRecordId:'',
      tableName:'',
      rowRecordPeople:'',
      rowTime:'',
      rowCounty:'',
      //返回审核表中的所有表名
      rowTableName:[],
      //往子组件传值
      parent_msg:'',
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
      returnReason: [],
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
      returnReason2:[],

      //退回记录显示
      tableBackData:[],
      params1:'',//搜索条件1
      params2:'',//搜索条件2
      //分页
      pagesize1:15,  //每页的数据条数
      currentPage1:1,//默认开始页面
      pagesize2:15,  //每页的数据条数
      currentPage2:1,//默认开始页面
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum1:0,//数据个数
      dataNum2:0,//数据个数
      dataNum:0,//数据个数

    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.getTableData();
      this.getTableData2();
      this.getDataBackListInfo();
    }, 
    //切换tabs
    handleClick(tab,event){
      //console.log(tab,event);
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
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'19px',height:'40px',padding:'1px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px',height:'35px',padding:'1px'}
        };
    },
    headerStyle3({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'19px',height:'45px',padding:'1px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px',height:'20px',padding:'1px'}
        };
    },
    //重置
    reSet1(){
      this.countyName1='';
      this.getTableData();
    },
    //重置
    reSet2(){
      this.countyName2='';
      this.getTableData2();
    },
    //获取一季稻数据
    getTableData(){
      const _this = this;
      var noParams='';
      axios({
        url:'/getNoAuditInfo',
        method:'post',
        params:{params1:noParams,
                start:this.currentPage1,
                pageSize:this.pagesize1
        }
      }).then(res=>{
            _this.tableData = res.data.lists;
            _this.dataNum1 = res.data.totalCount;
            //console.log(_this.tableData);
            //console.log(_this.dataNum1);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //获取双季稻数据
    getTableData2(){
      const _this = this;
      var noParams='';
      axios({
        url:'/getNoAuditInfo2',
        method:'post',
        params:{params1:noParams,
                start:this.currentPage2,
                pageSize:this.pagesize2
        }
      }).then(res=>{
            _this.tableData2 = res.data.lists;
            _this.dataNum2 = res.data.totalCount;
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    
    //一季稻审核搜索
    searchInfo1(){
      //console.log(this.countyName1);
      //console.log(this.currentPage1);
      //console.log(this.pagesize1);
      const _this = this;
      axios({
        url:'/getNoAuditInfo',
        method:'post',
        params:{params1:this.countyName1,
                start:this.currentPage1,
                pageSize:this.pagesize1
        }
      }).then(res=>{
            _this.tableData = res.data.lists;
            _this.dataNum1 = res.data.totalCount;
            //console.log(_this.tableData);
            //console.log(_this.dataNum1);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //双季稻审核搜索
    searchInfo2(){
      const _this = this;
      axios({
        url:'/getNoAuditInfo2',
        method:'post',
        params:{params1:this.countyName2,
                start:this.currentPage2,
                pageSize:this.pagesize2
        }
      }).then(res=>{
            _this.tableData2 = res.data.lists;
            _this.dataNum2 = res.data.totalCount;
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //审核一季稻
    audit(index,row){
      this.dialogVisible=true;
      //console.log(row);
      this.rowId=row.id;
      this.rowRecordId=row.record_id;
      this.rowRecordPeople=row.record_people;
      if(row.table_name != 'crop_01_01_table_01_02'){
        this.parent_msg=row.record_id;
      }else{
        this.parent_msg=row.county;
      }
      //获取表名
      this.tableName=row.table_name;
      this.rowTime=row.time;
      this.rowCounty=row.county;
      //console.log(this.rowRecordId,this.rowRecordPeople,this.tableName,this.parent_msg);
    },
    //审核双季稻
    audit2(index,row){
      this.dialogVisible2=true;
      //console.log(row);
      this.rowId=row.id;
      this.rowRecordId=row.record_id;
      this.rowRecordPeople=row.record_people
      if(row.table_name != 'crop_01_02_table_01_02'){
        this.parent_msg=row.record_id;
      }else{
        this.parent_msg=row.county;
      };
      //获取表名
      this.tableName=row.table_name;
      this.rowTime=row.time;
      this.rowCounty=row.county;
      //console.log(this.rowRecordId,this.rowRecordPeople,this.tableName);
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
    //删除一季稻对应辅助审核表中的数据
    deleteRecord(){
      //console.log(this.rowId,this.rowTime,this.tableName);
      axios({
        url:'/deleteRecord',
        method:'post',
        params:{
          rowId:this.rowId,
          rowName:this.tableName,
          rowTime:this.rowTime
        }
      }).then(res=>{
        
      }).catch(err=>{
        return this.$message.error("查询数据失败！");
      });
    },
    //删除双季稻对应辅助审核表中的数据
    deleteRecord2(){
      //console.log(this.rowId,this.rowTime,this.tableName);
      axios({
        url:'/deleteRecord2',
        method:'post',
        params:{
          rowId:this.rowId,
          rowName:this.tableName,
          rowTime:this.rowTime
        }
      }).then(res=>{
        
      }).catch(err=>{
        return this.$message.error("查询数据失败！");
      });
    },
    // 退还一季稻记录确定
    returnData(){
      this.deleteRecord();
      const _this=this;
      this.visible2=false;
      this.dialogVisible = false;
      //console.log(this.rowRecord);
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.rowRecordId;
      var recordPeople = this.rowRecordPeople;
      var returnTableName = this.tableName;
      var recordCounty=this.rowCounty;
      //获取原因
      var recordReason = document.getElementById("reason").value;
      // if(this.returnReason.length!=0){
      //     var recordReason = this.returnReason+','+document.getElementById("reason").value;
      // }else{
      //   var recordReason = document.getElementById("reason").value;
      // }
      //将原因显示置空
      this.returnReason=[];
      document.getElementById("reason").value='';
      //console.log(recordTime,recordId,recordPeople,recordReason);
      axios({
        url:'/returnRecord',
        menthod:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId,
                recordReason:recordReason,
                recordTime:recordTime,
                recordPeople:recordPeople,
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
      this.deleteRecord2();
      const _this=this;
      this.visibleTwo2=false;
      this.dialogVisible2 = false;
      //console.log(this.rowRecord);
      this.getMyTime();
      var recordTime = this.systemTime;
      var recordId = this.rowRecordId;
      var returnTableName = this.tableName;
      var recordPeople = this.rowRecordPeople;
      var recordCounty=this.rowCounty;
      //获取原因
      //if(this.returnReason.length!=0){
      //     var recordReason = this.returnReason2+','+document.getElementById("reason2").value;
      // }else{
      //   var recordReason = document.getElementById("reason2").value;
      // }
      var recordReason = document.getElementById("reason2").value;
      //将原因显示置空
      this.returnReason2=[];
      document.getElementById("reason2").value='';
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
    //数据一季稻记录审核通过
    confirmRecord(){
      this.deleteRecord();
      const _this=this;
      this.visible1=false;
      this.dialogVisible = false;
      var returnTableName = this.tableName;
      var recordId = this.rowRecordId;
      var recordCounty=this.rowCounty;
      //console.log(recordId);
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId}
      }).then(res=>{
        ////console.log(res.data);
        _this.getTableData();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },
    //数据双季稻记录审核通过
    confirmRecord2(){
      this.deleteRecord2();
      const _this=this;
      this.visibleTwo1=false;
      this.dialogVisible2 = false;
      var returnTableName = this.tableName;
      var recordId = this.rowRecordId;
      var recordCounty=this.rowCounty;
      //console.log(recordId);
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId}
      }).then(res=>{
        ////console.log(res.data);
        _this.getTableData2();
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },
    //返回
    returnPage(){
        this.$router.push("/riceHome");
    },
    //日期格式设置
    dateFormat:function(row,column){
    var date = row[column.property];
    if(date == undefined){return ''};
    return moment(date).format("YYYY-MM-DD HH:mm:ss");
    },
    //分页
    handleCurrentChange1(currentPage1){
      this.currentPage1 = currentPage1;
      if(this.countyName1!=""){
        this.searchInfo1();
      }else{
        this.getTableData();
      };
      //console.log(this.currentPage1);
    },
    handleCurrentChange2(currentPage2){
      this.currentPage2 = currentPage2;
      if(this.countyName1!=""){
        this.searchInfo2();
      }else{
        this.getTableData2();
      };
      //console.log(this.currentPage2);
    },
    handleCurrentChange(currentPage){
      this.currentPage = currentPage;
      if(this.params2!="" || this.params1!=""){
        this.getDataBackListInfo();
      }else{
        this.searchInfo();
      };
      //console.log(this.currentPage);
    },
    //分页后序号连续处理
    countMethod1(index){
       return (this.currentPage1-1)*this.pagesize1 + index +1;
    }, 
    //分页后序号连续处理
    countMethod2(index){
       return (this.currentPage2-1)*this.pagesize2 + index +1;
    }, 
    //分页后序号连续处理
    countMethod(index){
       return (this.currentPage-1)*this.pagesize + index +1;
    },  
    //退回记录显示
    //获取退还所有数据
    getDataBackListInfo(){
      const _this=this;
        axios({
            url:'/getBackInfo',
            method:'post',
            params:{start:this.currentPage,
                    pageSize:this.pagesize}
        }).then(res=>{
            //console.log(res.data);
            _this.tableBackData = res.data.lists;
            _this.dataNum = res.data.totalCount;
            //console.log(_this.dataNum)
        }).catch(err=>{
            return this.$message.error("退还数据获取失败！");
        })
    },
    //重置
    reSet(){
      this.params1='';
      this.params2='';
      this.getDataBackListInfo();
    },
    //搜索
    searchInfo(){
      const _this = this;
      //console.log(this.params1);
      //console.log(this.params2);
      axios({
        url:'/getAllBackData',
        method:'post',
        params:{params1:this.params1,
                params2:this.params2,
                start:this.currentPage,
                pageSize:this.pagesize
        }
      }).then(res=>{
            _this.tableBackData = res.data.lists;
            _this.dataNum = res.data.totalCount;
            //console.log(_this.tableBackData);
            //console.log(_this.dataNum);
          
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
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
    width:80%;
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
  .el-button--danger{
    border: 1px solid #000000;
    padding:2px;
    width: 70px;
    height: 30px;
  }
  .el-tabs_item{
    font-size: 18px !important;
  }
  /* .el-table .descending .sort-caret.descending {
    background: red;
  } */
  .caret-wrapper {
   background-color:black!important;
 }
 .el-table /deep/ .sort-caret.descending {
    border-top-color: #1e1e1f;
    bottom: 2px;
}
 .el-table /deep/ .sort-caret {
    width: 0;
    height: 0;
    border: 7px solid transparent;
    position: absolute;
    left: 7px;
}
 .el-table /deep/ .sort-caret.ascending {
    border-bottom-color: #121213;
    top: 2px;
}


</style>



