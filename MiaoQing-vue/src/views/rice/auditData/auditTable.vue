<template>
      <div class="totalRange">
        <div class="secondRange">
          <el-tabs v-model="activeName" >
            <el-tab-pane label="单季稻数据审核" name="1">
              <span slot="label">单季稻数据审核<el-badge v-if="dataTotal != 0" style="margin-left: 10px;" :value="dataTotal" /></span>
              <el-tabs v-model="activeTable"  @tab-click="handleChangeTable">
                <el-tab-pane label="监测县(市、区)水稻栽插概况调查表"  name="1">
                  <span slot="label">监测县(市、区)水稻栽插概况调查表<el-badge v-if="pointData[0]!=0" style="margin-left: 10px;" :value="pointData[0]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                    <el-row>
                      <el-col :span="4">
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
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel1}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData1" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount1" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" fixed="left" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="100%"  label="全县总面积（万亩）" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <!-- <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" /> -->
                          <el-table-column align="center"   label="栽植方式（万亩）">
                            <el-table-column align="center" label="人工移栽">
                              <el-table-column align="center" prop="field_5_1_1"  label="" />
                              <el-table-column align="center" prop="field_5_1_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机械插秧">
                              <el-table-column align="center" prop="field_5_2_1"  label="" />
                              <el-table-column align="center" prop="field_5_2_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="盘育抛栽">
                              <el-table-column align="center" prop="field_5_3_1"  label="" />
                              <el-table-column align="center" prop="field_5_3_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="无盘旱育抛栽">
                              <el-table-column align="center" prop="field_5_4_1"  label="" />
                              <el-table-column align="center" prop="field_5_4_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="撒直播（旱）">
                              <el-table-column align="center" prop="field_5_5_1"  label="" />
                              <el-table-column align="center" prop="field_5_5_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="撒直播（水）">
                              <el-table-column align="center" prop="field_5_6_1"  label="" />
                              <el-table-column align="center" prop="field_5_6_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机条播（旱）">
                              <el-table-column align="center" prop="field_5_7_1"  label="" />
                              <el-table-column align="center" prop="field_5_7_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机条播（水）">
                              <el-table-column align="center" prop="field_5_8_1"  label="" />
                              <el-table-column align="center" prop="field_5_8_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小计">
                              <el-table-column align="center" prop="field_5_9_1"  label="" />
                              <el-table-column align="center" prop="field_5_9_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"  label="前茬口类型（万亩）">
                            <el-table-column align="center" label="水稻">
                              <el-table-column align="center" prop="field_6_1_1"  label="" />
                              <el-table-column align="center" prop="field_6_1_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小麦">
                              <el-table-column align="center" prop="field_6_2_1"  label="" />
                              <el-table-column align="center" prop="field_6_2_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="油菜">
                              <el-table-column align="center" prop="field_6_3_1"  label="" />
                              <el-table-column align="center" prop="field_6_3_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="空闲田">
                              <el-table-column align="center" prop="field_6_4_1"  label="" />
                              <el-table-column align="center" prop="field_6_4_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="绿肥">
                              <el-table-column align="center" prop="field_6_5_1"  label="" />
                              <el-table-column align="center" prop="field_6_5_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="蔬菜等经作">
                              <el-table-column align="center" prop="field_6_6_1"  label="" />
                              <el-table-column align="center" prop="field_6_6_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="其他">
                              <el-table-column align="center" prop="field_6_7_1"  label="" />
                              <el-table-column align="center" prop="field_6_7_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小计">
                              <el-table-column align="center" prop="field_6_8_1"  label="" />
                              <el-table-column align="center" prop="field_6_8_2" width="82%" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage1"
                              :current-page="currentPage1"
                              :page-size="pagesize1"
                              :total="dataNum1"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible1" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible1 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack1"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack1 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻生产主推品种调查表" name="2">
                  <span slot="label">监测县(市、区)水稻生产主推品种调查表<el-badge v-if="pointData[1]!=0" style="margin-left: 10px;" :value="pointData[1]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                    <el-row>
                      <el-col :span="4">
                        <template>
                        <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                        </template>
                      </el-col>
                      <el-col :span="4">
                        <el-button type="info" @click="reSet1">重置</el-button>
                        <el-button type="info" @click="searchInfo12">查询</el-button>
                      </el-col>
                    </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel12}}</span>
                          </div>
                      </el-row>
                      <el-table ref="tableExpand" highlight-current-row :data="tableData12.slice((currentPage12-1)*pagesize12,currentPage12*pagesize12)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}" :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="100%" :index="indexCount12" label="序号" />
                          <el-table-column align="center" prop="county" width="350%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="area"  label="全县总面积（万亩）" />
                          <el-table-column align="center" width="350%"  label="主推品种">
                              <template slot-scope="scope">
                                <el-button type="text" @click="toogleExpand(scope.row)">查看品种详情</el-button>
                              </template>
                          </el-table-column>
                          <el-table-column type="expand" width="1">
                            <template slot-scope="props">
                                <!-- <el-form label-position="left"  class="demo-table-expand">
                                  <el-form-item v-if="props.row.number != 0" label="需上报县：">
                                    <span style="width:100%">
                                      <span v-for="item in props.row.county_name">{{item}}、</span>
                                    </span> 
                                  </el-form-item>
                                  <el-form-item v-if="props.row.id != 0">
                                    
                                  </el-form-item>
                                  
                                </el-form> -->
                                <el-table :data="varietyInfo" border stripe :header-cell-style="{background:'#6E8B3D',color:'#FFFFFF',height:'0',padding:'1px',fontSize:'5px'}" :cell-style="{padding:'0',fontSize:'1px'}" :row-style="{height: '10px'}">
                                      <el-table-column align="center" fixed type="index" label="序号" />
                                      <el-table-column align="center" prop="pin_zhong_name" label="品系" />
                                      <el-table-column align="center" prop="area_data" label="面积" />
                                      <el-table-column align="center" prop="area_percent" label="占比" />
                                </el-table>
                              </template>
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="400px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage12"
                              :current-page="currentPage12"
                              :page-size="pagesize12"
                              :total="dataNum12"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible12" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible12 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack12"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack12 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻播栽进度调查表" name="3">
                  <span slot="label">监测县(市、区)水稻播栽进度调查表<el-badge v-if="pointData[2]!=0" style="margin-left: 10px;" :value="pointData[2]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo31">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel31}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData31" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount31" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left"  label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%"  label="全县总面积（万亩）" />
                          <el-table-column align="center" label="开始播种日期">
                              <el-table-column align="center" prop="field_4_1" width="100%" label="育秧" />
                              <el-table-column align="center" prop="field_4_2" width="100%" label="直播" />
                          </el-table-column>
                          <el-table-column align="center"   label="秧田面积（万亩）">
                            <el-table-column align="center" prop="field_5"  label="" />
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_6_1"  label="" />
                              <el-table-column align="center" prop="field_6_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"   label="折合大田面积（万亩）">
                            <el-table-column align="center" label="">
                              <el-table-column align="center" prop="field_7_1"  label="" />
                              <el-table-column align="center" prop="field_7_2"   label="" />  
                          </el-table-column>
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_8_1"  label="" />
                              <el-table-column align="center" prop="field_8_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度1">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_9_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_9_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_9_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_9_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_9_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_9_6" width="90%" label="占比" />
                          </el-table-column>  
                          <el-table-column align="center" label="播种期进度2">
                            <el-table-column align="center" prop="field_10_1" width="90%"  label="日期" />
                            <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_10_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_10_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_10_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_10_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_10_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_10_6" width="90%" label="占比" />
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度3">
                            <el-table-column align="center" prop="field_11_1" width="110%" label="日期" />
                            <el-table-column align="center" width="180px" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_11_2_1"  label="" />
                              <el-table-column align="center" prop="field_11_2_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_11_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_11_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_11_5"  label="小计" />
                            <el-table-column align="center" prop="field_11_6"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center" label="播种期进度4">
                            <el-table-column align="center" prop="field_12_1" width="110%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_12_3" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_12_4"  label="小计" />
                            <el-table-column align="center" prop="field_12_5"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage31"
                              :current-page="currentPage31"
                              :page-size="pagesize31"
                              :total="dataNum31"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible31" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible31 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack31"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack31 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（移栽稻）" name="4">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（移栽稻）<el-badge v-if="pointData[3]!=0" style="margin-left: 10px;" :value="pointData[3]" /></span>
                   <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo32">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel32}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData32" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount32" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left" label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable  label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="130%" label="全县总面积（万亩）" />
                          <el-table-column align="center"  label="进程1（移栽后7天）">
                            <el-table-column align="center" prop="field_4_1" width="110%"  label="日期" />
                            <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_4_2_1" label="" />
                              <el-table-column align="center" prop="field_4_2_2" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="二类苗（万亩）">
                              <el-table-column align="center" prop="field_4_3_1" label="" />
                              <el-table-column align="center" prop="field_4_3_2" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="三类苗（万亩）">
                              <el-table-column align="center" prop="field_4_4_1" label="" />
                              <el-table-column align="center" prop="field_4_4_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column  align="center"  label="进程2（移栽后14天）">
                            <el-table-column align="center" prop="field_5_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程3（移栽后21天）">
                            <el-table-column align="center" prop="field_6_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程4（移栽后28天）">
                            <el-table-column align="center" prop="field_7_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程5（移栽后35天）">
                            <el-table-column align="center" prop="field_8_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程6（移栽后42天）">
                            <el-table-column align="center" prop="field_9_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程7（移栽后52天）">
                            <el-table-column align="center" prop="field_10_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程8（移栽后62天）">
                            <el-table-column align="center" prop="field_11_1" width="110%"  label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage32"
                              :current-page="currentPage32"
                              :page-size="pagesize32"
                              :total="dataNum32"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible32" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible32 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack32"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack32 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（直播稻）" name="5">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（直播稻）<el-badge v-if="pointData[4]!=0" style="margin-left: 10px;" :value="pointData[4]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo33">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel33}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData33" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount33" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="140%"  label="全县总面积（万亩）" />
                          
                          <el-table-column align="center" label="进程1（大田直播后20天）">
                            <el-table-column align="center" prop="field_4_1" width="110%" label="日期" />
                              <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_4_2_1" label="" />
                              <el-table-column align="center" prop="field_4_2_2" label="" />  
                            </el-table-column>
                            <el-table-column align="center" label="二类苗（万亩）">
                              <el-table-column align="center" prop="field_4_3_1" label="" />
                              <el-table-column align="center" prop="field_4_3_2" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="三类苗（万亩）">
                              <el-table-column align="center" prop="field_4_4_1" label="" />
                              <el-table-column align="center" prop="field_4_4_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="进程2（大田直播后28天）">
                              <el-table-column align="center" prop="field_5_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程3（大田直播后35天）">
                            `<el-table-column align="center" prop="field_6_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程4（大田直播后42天）">
                              <el-table-column align="center" prop="field_7_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程5（大田直播后49天）">
                            <el-table-column align="center" prop="field_8_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程6（大田直播后56天）">
                              <el-table-column align="center" prop="field_9_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程7（大田直播后66天）">
                            <el-table-column align="center" prop="field_10_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程8（大田直播后76天）">
                              <el-table-column align="center" prop="field_11_1" width="110%" label="日期" />
                              <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_11_2_1" label="" />
                              <el-table-column align="center" prop="field_11_2_1" label="" />  
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
                          <el-table-column align="center" label="进程9（大田直播后86天）">
                            <el-table-column align="center" prop="field_12_1" width="130%" label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage33"
                              :current-page="currentPage33"
                              :page-size="pagesize33"
                              :total="dataNum33"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible33" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible33 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack33"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack33 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)中稻生育进度调查表（移栽稻）" name="6">
                  <span slot="label">监测县(市、区)中稻生育进度调查表（移栽稻）<el-badge v-if="pointData[5]!=0" style="margin-left: 10px;" :value="pointData[5]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo41">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel41}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData41" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount41" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6"  label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" label="移栽期">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_8_2"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_8_3"  label="基本苗（万/亩）" />
                            <el-table-column align="center" prop="field_8_4"  label="亩穴数（万）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程1（移栽后7天）">
                            <el-table-column align="center" prop="field_9_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_9_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_9_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程2（移栽后14天）">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_10_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_10_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程3（移栽后21天）">
                            <el-table-column align="center" prop="field_11_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_11_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_11_4"  label="茎蘖数（万/亩）" />
                          </el-table-column> 
                          <el-table-column align="center" label="进程4（移栽后28天）">
                            <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_12_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_12_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程5（移栽后35天）">
                            <el-table-column align="center" prop="field_13_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_13_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_13_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程6（移栽后42天）">
                            <el-table-column align="center" prop="field_14_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_14_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_14_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程7（移栽后52天）">
                            <el-table-column align="center" prop="field_15_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_15_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_15_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_15_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程8（移栽后62天）">
                            <el-table-column align="center" prop="field_16_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_16_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_16_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_16_4" label="穗数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗中期进程9移栽后72天）">
                            <el-table-column align="center" prop="field_17_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_17_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_17_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_17_4" label="穗数（万/亩）" />
                          </el-table-column>      
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage41"
                              :current-page="currentPage41"
                              :page-size="pagesize41"
                              :total="dataNum41"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible41" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible41 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack41"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack41 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)直播稻生育进度调查表（直播稻）" name="7">
                  <span slot="label">监测县(市、区)直播稻生育进度调查表（直播稻）<el-badge v-if="pointData[6]!=0" style="margin-left: 10px;" :value="pointData[6]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo42">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel42}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData42" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount42" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6"  label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" label="进程1（大田直播后20天）">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_8_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_8_4"  label="基本苗（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程2（大田直播后28天）">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_9_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_9_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程3（大田直播后35天）">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_10_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_10_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程4（大田直播后42天）">
                            <el-table-column align="center" prop="field_11_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_11_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_11_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程5（大田直播后49天）">
                            <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_12_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_12_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程6（大田直播后56天）">
                            <el-table-column align="center" prop="field_13_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_13_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_13_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程7（大田直播后66天）">
                            <el-table-column align="center" prop="field_14_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_14_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_14_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程8（大田直播后76天）">
                            <el-table-column align="center" prop="field_15_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_15_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_15_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_15_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程9（大田直播后86天）">
                            <el-table-column align="center" prop="field_16_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_16_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_16_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_16_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage42"
                              :current-page="currentPage42"
                              :page-size="pagesize42"
                              :total="dataNum42"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible42" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible42 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack42"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack42 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点茎蘖数叶面积构成表" name="8">
                  <span slot="label">水稻监测点茎蘖数叶面积构成表<el-badge v-if="pointData[7]!=0" style="margin-left: 10px;" :value="pointData[7]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo43">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel43}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData43" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount43" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" sortable label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" prop="field_8" width="120%" label="目标产量(kg/亩)" />
                          <el-table-column align="center" prop="field_9" width="120%" label="基本苗（万/亩）" />
                          <el-table-column align="center" label="拔节期">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="测定日期" />
                            <el-table-column align="center" prop="field_10_2"  label="株高（cm）" />
                            <el-table-column align="center" prop="field_10_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_10_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_10_5"  label="倒4与倒3叶色比" />
                            <el-table-column align="center" prop="field_10_6"  label="1叶分蘖个数" />
                            <el-table-column align="center" prop="field_10_7"  label="2叶及以上分蘖个数" />
                            <el-table-column align="center" prop="field_10_8"  label="3叶及以上分蘖个数" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期（幼穗分化1-2期）">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="测定日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="株高（cm）" />
                            <el-table-column align="center" prop="field_11_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_11_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_11_5"  label="倒4与倒3叶色比" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期（幼穗分化3-4期）">
                            <el-table-column align="center" prop="field_12_1" width="90%"  label="测定日期" />
                            <el-table-column align="center" prop="field_12_2"  label="株高（cm）" />
                            <el-table-column align="center" prop="field_12_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_12_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_12_5"  label="倒4与倒3叶色比" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage43"
                              :current-page="currentPage43"
                              :page-size="pagesize43"
                              :total="dataNum43"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible43" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible43 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack43"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack43 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点苗情生育期统计表" name="9">
                  <span slot="label">水稻监测点苗情生育期统计表<el-badge v-if="pointData[8]!=0" style="margin-left: 10px;" :value="pointData[8]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo44">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel44}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData44" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount44" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" prop="field_8" width="100%" label="播种期（月/日）" />
                          <el-table-column align="center" prop="field_9" width="100%" label="出苗期（月/日）" />
                          <el-table-column align="center" prop="field_10" width="100%" label="移栽期（月/日）" />
                          <el-table-column align="center" prop="field_fennie" width="130%" label="分蘖期（月/日）" />
                          <el-table-column align="center" prop="field_11" width="100%" label="够苗期（月/日）" />
                          <el-table-column align="center" prop="field_12" width="120%" label="有效分蘗临界叶龄期（月/日）" />
                          <el-table-column align="center" prop="field_13" width="100%" label="拔节期（月/日）" />
                          <el-table-column align="center" prop="field_14" width="100%" label="孕穗期（月/日）" />
                          <el-table-column align="center" prop="field_15" width="100%" label="抽穗期（月/日）" />
                          <el-table-column align="center" prop="field_16" width="100%" label="齐穗期（月/日）" />
                          <el-table-column align="center" prop="field_17" width="100%" label="成熟期（月/日）" />
                          <el-table-column align="center" prop="field_18" width="100%" label="全生育期（日）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage44"
                              :current-page="currentPage44"
                              :page-size="pagesize44"
                              :total="dataNum44"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible44" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible44 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack44"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack44 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="叶片含氮测定仪导出数据表" name="10">
                  <span slot="label">叶片含氮测定仪导出数据表<el-badge v-if="pointData[9]!=0" style="margin-left: 10px;" :value="pointData[9]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo51">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel51}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData51" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount51" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="够苗期">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_7_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_7_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_7_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_7_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_7_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_7_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_7_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_7_9" label="目标产量" />
                            <el-table-column align="center" prop="field_7_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="拔节期">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_8_2" width="120%"  label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_8_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_8_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_8_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_8_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_8_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_8_8" width="120%"  label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_8_9" label="目标产量" />
                            <el-table-column align="center" prop="field_8_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期1">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_9_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_9_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_9_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_9_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_9_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_9_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_9_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_9_9" label="目标产量" />
                            <el-table-column align="center" prop="field_9_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期2">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_10_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_10_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_10_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_10_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_10_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_10_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_10_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_10_9" label="目标产量" />
                            <el-table-column align="center" prop="field_10_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="抽穗期">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_11_2" width="120%"  label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_11_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_11_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_11_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_11_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_11_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_11_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_11_9" label="目标产量" />
                            <el-table-column align="center" prop="field_11_10" label="施肥量" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage51"
                              :current-page="currentPage51"
                              :page-size="pagesize51"
                              :total="dataNum51"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible51" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible51 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack51"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack51 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点水分管理记录表" name="11">
                  <span slot="label">水稻监测点水分管理记录表<el-badge v-if="pointData[10]!=0" style="margin-left: 10px;" :value="pointData[10]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo61">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel61}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData61" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount61" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable  label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="135%"  label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="时期1">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期2">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期3">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期4">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期5">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期6">
                            <el-table-column align="center" prop="field_12_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期7">
                            <el-table-column align="center" prop="field_13_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期8">
                            <el-table-column align="center" prop="field_14_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage61"
                              :current-page="currentPage61"
                              :page-size="pagesize61"
                              :total="dataNum61"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible61" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible61 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack61"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack61 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点化除管理记录表" name="12">
                  <span slot="label">水稻监测点化除管理记录表<el-badge v-if="pointData[11]!=0" style="margin-left: 10px;" :value="pointData[11]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo62">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel62}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData62" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount62" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3"  width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4"  width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="化除1-播栽前">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_7_4" width="125%" label="方法" />
                            <el-table-column align="center" prop="field_7_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化除2-播栽后或出苗后">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_8_4" width="125%" label="方法" />
                            <el-table-column align="center" prop="field_8_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化除3播栽后或出苗后">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_9_4"  label="方法" />
                            <el-table-column align="center" prop="field_9_5" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage62"
                              :current-page="currentPage62"
                              :page-size="pagesize62"
                              :total="dataNum62"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible62" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible62 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack62"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack62 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点病虫害防治记录表" name="13">
                  <span slot="label">水稻监测点病虫害防治记录表<el-badge v-if="pointData[12]!=0" style="margin-left: 10px;" :value="pointData[12]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo63">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel63}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData63" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount63" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" fixed="left" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="病虫害防治1">
                            <el-table-column align="center" prop="field_7_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_7_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_7_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_7_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_7_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_7_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治2">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_8_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_8_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_8_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_8_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_8_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治3">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_9_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_9_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_9_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_9_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_9_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治4">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_10_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_10_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_10_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_10_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_10_6" width="140%" label="生育进程" />
                          </el-table-column> 
                          <el-table-column align="center" label="病虫害防治5">
                            <el-table-column align="center" prop="field_11_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_11_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_11_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_11_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_11_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_11_6" width="140%" label="生育进程" />
                          </el-table-column>       
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage63"
                              :current-page="currentPage63"
                              :page-size="pagesize63"
                              :total="dataNum63"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible63" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible63 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack63"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack63 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点施肥管理记录表" name="14">
                  <span slot="label">水稻监测点施肥管理记录表<el-badge v-if="pointData[13]!=0" style="margin-left: 10px;" :value="pointData[13]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo64">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel64}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData64" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount64" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="100%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="基肥施肥（以折纯计kg/亩）1">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_7_2"  label="N" />
                            <el-table-column align="center" prop="field_7_3"  label="P" />
                            <el-table-column align="center" prop="field_7_4"  label="K" />
                            <el-table-column align="center" prop="field_7_5" width="100%" label="方法" />
                            <el-table-column align="center" prop="field_7_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="分蘖肥施肥（以折纯计kg/亩）2">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_8_2"  label="N" />
                            <el-table-column align="center" prop="field_8_3"  label="P" />
                            <el-table-column align="center" prop="field_8_4"  label="K" />
                            <el-table-column align="center" prop="field_8_5" width="100%" label="方法" />
                            <el-table-column align="center" prop="field_8_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）3">
                            <el-table-column align="center" prop="field_9_1" width="90%"  label="时间" />
                            <el-table-column align="center" prop="field_9_2"  label="N" />
                            <el-table-column align="center" prop="field_9_3"  label="P" />
                            <el-table-column align="center" prop="field_9_4"  label="K" />
                            <el-table-column align="center" prop="field_9_5" width="100%"  label="方法" />
                            <el-table-column align="center" prop="field_9_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）4">
                            <el-table-column align="center" prop="field_10_1" width="90%"  label="时间" />
                            <el-table-column align="center" prop="field_10_2"  label="N" />
                            <el-table-column align="center" prop="field_10_3"  label="P" />
                            <el-table-column align="center" prop="field_10_4"  label="K" />
                            <el-table-column align="center" prop="field_10_5" width="100%"  label="方法" />
                            <el-table-column align="center" prop="field_10_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="抽穗叶面肥施肥（以折纯计kg/亩）4">
                            <el-table-column align="center" prop="field_11_1"  label="时间" />
                            <el-table-column align="center" prop="field_11_2"  label="N" />
                            <el-table-column align="center" prop="field_11_3"  label="P" />
                            <el-table-column align="center" prop="field_11_4"  label="K" />
                            <el-table-column align="center" prop="field_11_5"  label="方法" />
                            <el-table-column align="center" prop="field_11_6" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage64"
                              :current-page="currentPage64"
                              :page-size="pagesize64"
                              :total="dataNum64"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible64" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible64 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack64"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack64 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点化学调节剂使用记录表" name="15">
                  <span slot="label">水稻监测点化学调节剂使用记录表<el-badge v-if="pointData[14]!=0" style="margin-left: 10px;" :value="pointData[14]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo65">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel65}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData65" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount65" label="序号" />
                          <el-table-column align="center" fixed="left" prop="field_1" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="化调剂1">
                            <el-table-column align="center" prop="field_7_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_7_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_7_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_7_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化调剂2">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_8_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_8_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_8_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化调剂3">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_9_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_9_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_9_5" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage65"
                              :current-page="currentPage65"
                              :page-size="pagesize65"
                              :total="dataNum65"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible65" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible65 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack65"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack65 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻单季稻苗情监测预测产表" name="16">
                  <span slot="label">监测县(市、区)水稻单季稻苗情监测预测产表<el-badge v-if="pointData[15]!=0" style="margin-left: 10px;" :value="pointData[15]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo71">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel71}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData71" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount71" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3"  label="总面积(万亩)" />
                          <el-table-column align="center" label="7月31日前抽穗扬花的中稻">
                            <el-table-column align="center" prop="field_4_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_4_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_4_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="8月1-10日抽穗扬花的中稻">
                            <el-table-column align="center" prop="field_5_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_5_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_5_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_5_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_5_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_5_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column><el-table-column align="center" label="8月11-15日抽穗扬花的中稻">
                            <el-table-column align="center" prop="field_6_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_6_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_6_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="在8月16日-20日抽穗扬花的中稻">
                            <el-table-column align="center" prop="field_7_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_7_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_7_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="在9月上旬抽穗扬花的中稻">
                            <el-table-column align="center" prop="field_9_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_9_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_9_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_9_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_9_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_9_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage71"
                              :current-page="currentPage71"
                              :page-size="pagesize71"
                              :total="dataNum71"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible71" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible71 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack71"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack71 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻单季稻收获进度调查表" name="17">
                  <span slot="label">监测县(市、区)水稻单季稻收获进度调查表<el-badge v-if="pointData[16]!=0" style="margin-left: 10px;" :value="pointData[16]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo72">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel72}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData72" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount72" label="序号" />
                          <el-table-column align="center" fixed="left" prop="field_1" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="140%" label="总面积(万亩)" />
                          <el-table-column align="center" label="收获进度1（9月30日）">
                            <el-table-column align="center" prop="field_4_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_4_2_1" label="" />
                              <el-table-column align="center"  prop="field_4_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度2（10月10日）">
                            <el-table-column align="center" prop="field_5_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_5_2_1" label="" />
                              <el-table-column align="center"  prop="field_5_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度3（10月20日）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_6_2_1" label="" />
                              <el-table-column align="center"  prop="field_6_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度4（10月30日）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_7_2_1" label="" />
                              <el-table-column align="center"  prop="field_7_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度5（11月10日）">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_8_2_1" label="" />
                              <el-table-column align="center"  prop="field_8_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度6（11月20日）">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_9_2_1" label="" />
                              <el-table-column align="center"  prop="field_9_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="单季稻实测产">
                              <el-table-column align="center" prop="field_10_1" width="120%" label="面积(万亩）" />
                              <el-table-column align="center" prop="field_10_2" width="120%" label="亩有效穗（万）" />
                              <el-table-column align="center" prop="field_10_3" width="120%" label="穗总粒数（个）" />
                              <el-table-column align="center" prop="field_10_4" width="120%" label="结实率（％）" />
                              <el-table-column align="center" prop="field_10_5" width="120%" label="千粒重（g）" />
                              <el-table-column align="center" prop="field_10_6" width="170%" label="八五折理论产量（kg/亩）" />
                            </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage72"
                              :current-page="currentPage72"
                              :page-size="pagesize72"
                              :total="dataNum72"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible72" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible72 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack72"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack72 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻单季稻测产表" name="18">
                  <span slot="label">监测县(市、区)水稻单季稻测产表<el-badge v-if="pointData[17]!=0" style="margin-left: 10px;" :value="pointData[17]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo73">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel73}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData73" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount73" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable  label="地区类型" />
                          <el-table-column align="center" label="全县面上平均水平">
                            <el-table-column align="center" prop="field_3_1"  label="总面积（万亩）" />
                            <el-table-column align="center" prop="field_3_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_3_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_3_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_3_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_3_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="人工移栽">
                            <el-table-column align="center" prop="field_4_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_4_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_4_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="机械插秧">
                            <el-table-column align="center" prop="field_5_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_5_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_5_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_5_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_5_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_5_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="盘育抛秧">
                            <el-table-column align="center" prop="field_6_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_6_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_6_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="无盘旱育抛秧">
                            <el-table-column align="center" prop="field_7_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_7_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_7_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="撒直播">
                            <el-table-column align="center" prop="field_8_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_8_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_8_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_8_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_8_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_8_6" width="120%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="机条播">
                            <el-table-column align="center" prop="field_9_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_9_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_9_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_9_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_9_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_9_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage73"
                              :current-page="currentPage73"
                              :page-size="pagesize73"
                              :total="dataNum73"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible73" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible73 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack73"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack73 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻生产成本效益统计表" name="19">
                  <span slot="label">监测县(市、区)水稻生产成本效益统计表<el-badge v-if="pointData[18]!=0" style="margin-left: 10px;" :value="pointData[18]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo81">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel81}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData81" border :header-cell-style="headerStyle81" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount81" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2"  label="地区类型" />
                          <el-table-column align="center" prop="field_3"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4"  label="品种类型" />
                          <el-table-column align="center" prop="field_5" width="110%" label="监测田块" />
                          <el-table-column align="center" prop="field_6"  label="代表面积（万亩）" />
                          <el-table-column align="center" prop="field_7"  label="栽植方式" />
                          <el-table-column align="center" prop="field_8"  label="水稻亩产值（元）" />
                          <el-table-column align="center" prop="field_9" width="140%" label="其中主产品产值（公斤/元）" />
                          <el-table-column align="center" prop="field_10" width="140%" label="副产品产值（公斤/元）" />
                          <el-table-column align="center" label="劳动用工">
                            <el-table-column align="center" label="育秧" >
                              <el-table-column align="center" prop="field_11_1_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_1_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="耕作" >
                              <el-table-column align="center" prop="field_11_2_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_2_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="移栽" >
                              <el-table-column align="center" prop="field_11_3_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_3_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="直播" >
                              <el-table-column align="center" prop="field_11_4_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_4_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="大田管理" >
                              <el-table-column align="center" prop="field_11_5_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_5_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="小计" >
                              <el-table-column align="center" prop="field_11_6_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_6_2" width="160%" label="价格（元/亩）" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="物质费用">
                            <el-table-column align="center" prop="field_12_1" width="120%" label="种子（元/亩）" />
                            <el-table-column align="center" label="化肥">
                              <el-table-column align="center" prop="field_12_2_1"  label="种类" />
                              <el-table-column align="center" prop="field_12_2_2" width="130%" label="施用量（kg/亩）" />
                              <el-table-column align="center" prop="field_12_2_3" width="130%" label="价格（元/kg）" />
                              <el-table-column align="center" prop="field_12_2_4" width="130%" label="小计（元/亩）" />
                            </el-table-column>
                              <el-table-column align="center" prop="field_12_3" width="120%" label="有机肥（元/亩）" />
                              <el-table-column align="center" prop="field_12_4" width="120%" label="农药（元/亩）" />
                              <el-table-column align="center" prop="field_12_5" width="120%" label="灌溉（元/亩）" />
                              <el-table-column align="center" prop="field_12_6" width="140%" label="机械耕作（元/亩）" />
                              <el-table-column align="center" prop="field_12_7" width="165%" label="机械插秧/直播（元/亩）" />
                              <el-table-column align="center" prop="field_12_8" width="140%" label="机械收获（元/亩）" />
                              <el-table-column align="center" prop="field_12_9" width="120%" label="其他（元/亩）" />
                              <el-table-column align="center" prop="field_12_10" width="120%" label="小计（元/亩）" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_13"  label="地租（元/亩）" />
                          <el-table-column align="center" prop="field_14" label="其他费用（元/亩）" />
                          <el-table-column align="center" prop="field_15"  label="亩总成本（元）" />
                          <el-table-column align="center" prop="field_16"  label="亩收益（元）" />        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage81"
                              :current-page="currentPage81"
                              :page-size="pagesize81"
                              :total="dataNum81"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible81" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible81 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack81"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack81 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻倒伏灾害表" name="20">
                  <span slot="label">监测县(市、区)水稻倒伏灾害表<el-badge v-if="pointData[19]!=0" style="margin-left: 10px;" :value="pointData[19]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo93">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel93}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData93" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount93" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" sortable width="120%" label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_6" width="170%" label="倒伏时间" />
                          <el-table-column align="center" prop="field_7"  label="倒伏面积（万亩）" />
                          <el-table-column align="center" prop="field_8"  label="占比（%）" />
                          <el-table-column align="center" prop="field_9"  label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_10"  label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_11"  label="产量损失（%）" />
                          <el-table-column align="center" prop="field_12"  label="倒伏程度（级别）" />
                          <el-table-column align="center" prop="field_13"  label="主要品种" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage93"
                              :current-page="currentPage93"
                              :page-size="pagesize93"
                              :total="dataNum93"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible93" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible93 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack93"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack93 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻低温冷害表" name="21">
                  <span slot="label">监测县(市、区)水稻低温冷害表<el-badge v-if="pointData[20]!=0" style="margin-left: 10px;" :value="pointData[20]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo94">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel94}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData94" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount94" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="110%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5"  label="低温冷害" />
                          <el-table-column align="center" prop="field_6" width="100%" label="低温冷害发生时间" />
                          <el-table-column align="center" prop="field_7" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_8" width="110%" label="冷害总面积（万亩）" />
                          <el-table-column align="center" label="冷害级别（万亩）" >
                            <el-table-column align="center" prop="field_9_1"  label="1级" />
                            <el-table-column align="center" prop="field_9_2"  label="2级" />
                            <el-table-column align="center" prop="field_9_3"  label="3级" />
                            <el-table-column align="center" prop="field_9_4"  label="4级" />
                            <el-table-column align="center" prop="field_9_5"  label="5级" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_10" label="主要品种名称" />
                          <el-table-column align="center" prop="field_11"  label="占比（%）" />
                          <el-table-column align="center" prop="field_12" width="120%" label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_13" width="120%" label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_14"  label="产量损失（%）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage94"
                              :current-page="currentPage94"
                              :page-size="pagesize94"
                              :total="dataNum94"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible94" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible94 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack94"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack94 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻高温热害表" name="22">
                  <span slot="label">监测县(市、区)水稻高温热害表<el-badge v-if="pointData[21]!=0" style="margin-left: 10px;" :value="pointData[21]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo95">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel95}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData95" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount95" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5"  label="高温热害" />
                          <el-table-column align="center" prop="field_6" width="100%"  label="高温热害发生时间" />
                          <el-table-column align="center" prop="field_7" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_8" width="110%" label="热害总面积（万亩）" />
                          <el-table-column align="center" label="热害级别（万亩）" >
                            <el-table-column align="center" prop="field_9_1"  label="1级" />
                            <el-table-column align="center" prop="field_9_2"  label="2级" />
                            <el-table-column align="center" prop="field_9_3"  label="3级" />
                            <el-table-column align="center" prop="field_9_4"  label="4级" />
                            <el-table-column align="center" prop="field_9_5"  label="5级" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_10"  label="主要品种名称" />
                          <el-table-column align="center" prop="field_11"  label="占比（%）" />
                          <el-table-column align="center" prop="field_12" width="110%" label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_13" width="110%" label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_14"  label="产量损失（%）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage95"
                              :current-page="currentPage95"
                              :page-size="pagesize95"
                              :total="dataNum95"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible95" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible95 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack95"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack95 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻涝害调查表" name="23">
                  <span slot="label">监测县(市、区)水稻涝害调查表<el-badge v-if="pointData[22]!=0" style="margin-left: 10px;" :value="pointData[22]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo96">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel96}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData96" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount96" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable  label="品系类型" />
                          <el-table-column align="center" label="受灾统计表（万亩）">
                            <el-table-column align="center" prop="field_5_1" width="90%" label="填报日期" />
                            <el-table-column align="center" prop="field_5_2" width="120%" label="生育阶段与灾害症状" />
                            <el-table-column align="center" prop="field_5_3"  label="受灾面积" />
                            <el-table-column align="center" prop="field_5_4"  label="其中：成灾面积" />
                            <el-table-column align="center" prop="field_5_5"  label="其中：绝收面积" />
                            <el-table-column align="center" prop="field_5_6"  label="需补改种面积" />
                            <el-table-column align="center" prop="field_5_7"  label="已补改种面积" />
                          </el-table-column>
                          <el-table-column align="center" label="受灾统计表（万亩）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="填报日期" />
                            <el-table-column align="center" prop="field_6_2" width="120%" label="生育阶段与灾害症状" />
                            <el-table-column align="center" prop="field_6_3"  label="受灾面积" />
                            <el-table-column align="center" prop="field_6_4"  label="其中：成灾面积" />
                            <el-table-column align="center" prop="field_6_5"  label="其中：绝收面积" />
                            <el-table-column align="center" prop="field_6_6"  label="需补改种面积" />
                            <el-table-column align="center" prop="field_6_7"  label="已补改种面积" />
                          </el-table-column>
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage96"
                              :current-page="currentPage96"
                              :page-size="pagesize96"
                              :total="dataNum96"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible96" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible96 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack96"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack96 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点洪涝灾害表" name="24">
                  <span slot="label">水稻监测点洪涝灾害表<el-badge v-if="pointData[23]!=0" style="margin-left: 10px;" :value="pointData[23]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo97">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel97}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData97" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount97" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%"  label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="140%" label="品种名称" />
                          <el-table-column align="center" prop="field_5" width="120%" label="栽植方式" />
                          <el-table-column align="center" prop="field_6" width="90%" label="淹水日期" />
                          <el-table-column align="center" prop="field_7" width="90%" label="退水日期" />
                          <el-table-column align="center" prop="field_8" label="生育阶段" />
                          <el-table-column align="center" prop="field_9" width="100%" label="面积（亩）" />
                          <el-table-column align="center" prop="field_10" label="淹水高度" />
                          <el-table-column align="center" prop="field_11" label="淹水天数">
                            <el-table-column align="center" prop='field_11_1' label="" />
                            <el-table-column align="center" prop='field_11_2' label="其中没顶" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_12" label="程度" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage97"
                              :current-page="currentPage97"
                              :page-size="pagesize97"
                              :total="dataNum97"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible97" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible97 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack97"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack97 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点灌浆速率调查表" name="25">
                  <span slot="label">水稻监测点灌浆速率调查表<el-badge v-if="pointData[24]!=0" style="margin-left: 10px;" :value="pointData[24]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName1"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet1">重置</el-button>
                          <el-button type="info" @click="searchInfo101">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabel101}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableData101" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCount101" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="140%" label="品种名称" />
                          <el-table-column align="center" prop="field_5" width="130%" sortable label="栽植方式" />
                          <el-table-column align="center" label="灌浆速率测定1（扬花）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_6_2" width="100%" label="千粒重（克）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定2（扬花+7天）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_7_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定3（扬花+14天）">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_8_3" width="160%"  label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定4（扬花+21天）">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_9_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定5（扬花+28天）">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_10_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定6（扬花+35天）">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_11_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePage101"
                              :current-page="currentPage101"
                              :page-size="pagesize101"
                              :total="dataNum101"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisible101" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason1" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisible101 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBack101"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBack101 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="双季稻数据审核" name="2">
              <span slot="label">双季稻数据审核<el-badge v-if="dataTotal2 != 0" style="margin-left: 10px;" :value="dataTotal2" /></span>
              <el-tabs v-model="activeTableTwo"  @tab-click="handleChangeTableTwo">
                <el-tab-pane label="监测县(市、区)水稻栽插概况调查表"  name="1">
                  <span slot="label">监测县(市、区)水稻栽插概况调查表<el-badge v-if="pointData2[0]!=0" style="margin-left: 10px;" :value="pointData2[0]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                    <el-row>
                      <el-col :span="4">
                        <template>
                        <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                        </template>
                      </el-col>
                      <el-col :span="4">
                        <el-button type="info" @click="reSet2">重置</el-button>
                        <el-button type="info" @click="searchInfoTwo11">查询</el-button>
                      </el-col>
                    </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo11}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo11" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo11" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" fixed="left" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="100%"  label="全县总面积（万亩）" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <!-- <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" /> -->
                          <el-table-column align="center"   label="栽植方式（万亩）">
                            <el-table-column align="center" label="人工移栽">
                              <el-table-column align="center" prop="field_5_1_1"  label="" />
                              <el-table-column align="center" prop="field_5_1_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机械插秧">
                              <el-table-column align="center" prop="field_5_2_1"  label="" />
                              <el-table-column align="center" prop="field_5_2_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="盘育抛栽">
                              <el-table-column align="center" prop="field_5_3_1"  label="" />
                              <el-table-column align="center" prop="field_5_3_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="无盘旱育抛栽">
                              <el-table-column align="center" prop="field_5_4_1"  label="" />
                              <el-table-column align="center" prop="field_5_4_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="撒直播（旱）">
                              <el-table-column align="center" prop="field_5_5_1"  label="" />
                              <el-table-column align="center" prop="field_5_5_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="撒直播（水）">
                              <el-table-column align="center" prop="field_5_6_1"  label="" />
                              <el-table-column align="center" prop="field_5_6_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机条播（旱）">
                              <el-table-column align="center" prop="field_5_7_1"  label="" />
                              <el-table-column align="center" prop="field_5_7_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="机条播（水）">
                              <el-table-column align="center" prop="field_5_8_1"  label="" />
                              <el-table-column align="center" prop="field_5_8_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小计">
                              <el-table-column align="center" prop="field_5_9_1"  label="" />
                              <el-table-column align="center" prop="field_5_9_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"  label="前茬口类型（万亩）">
                            <el-table-column align="center" label="水稻">
                              <el-table-column align="center" prop="field_6_1_1"  label="" />
                              <el-table-column align="center" prop="field_6_1_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小麦">
                              <el-table-column align="center" prop="field_6_2_1"  label="" />
                              <el-table-column align="center" prop="field_6_2_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="油菜">
                              <el-table-column align="center" prop="field_6_3_1"  label="" />
                              <el-table-column align="center" prop="field_6_3_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="空闲田">
                              <el-table-column align="center" prop="field_6_4_1"  label="" />
                              <el-table-column align="center" prop="field_6_4_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="绿肥">
                              <el-table-column align="center" prop="field_6_5_1"  label="" />
                              <el-table-column align="center" prop="field_6_5_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="蔬菜等经作">
                              <el-table-column align="center" prop="field_6_6_1"  label="" />
                              <el-table-column align="center" prop="field_6_6_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="其他">
                              <el-table-column align="center" prop="field_6_7_1"  label="" />
                              <el-table-column align="center" prop="field_6_7_2" width="82%" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="小计">
                              <el-table-column align="center" prop="field_6_8_1"  label="" />
                              <el-table-column align="center" prop="field_6_8_2" width="82%" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo11"
                              :current-page="currentPageTwo11"
                              :page-size="pagesizeTwo11"
                              :total="dataNumTwo11"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo11" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo11 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo11"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo11 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻生产主推品种调查表" name="2">
                  <span slot="label">监测县(市、区)水稻生产主推品种调查表<el-badge v-if="pointData2[1]!=0" style="margin-left: 10px;" :value="pointData2[1]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                    <el-row>
                      <el-col :span="4">
                        <template>
                        <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                        </template>
                      </el-col>
                      <el-col :span="4">
                        <el-button type="info" @click="reSet2">重置</el-button>
                        <el-button type="info" @click="searchInfoTwo12">查询</el-button>
                      </el-col>
                    </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo12}}</span>
                          </div>
                      </el-row>
                      <el-table ref="tableExpand2" highlight-current-row :data="tableDataTwo12.slice((currentPageTwo12-1)*pagesizeTwo12,currentPageTwo12*pagesizeTwo12)" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}" :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="100%" :index="indexCountTwo12" label="序号" />
                          <el-table-column align="center" prop="county" width="350%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="area"  label="全县总面积（万亩）" />
                          <el-table-column align="center" width="350%"  label="主推品种">
                              <template slot-scope="scope">
                                <el-button type="text" @click="toogleExpand2(scope.row)">查看品种详情</el-button>
                              </template>
                          </el-table-column>
                          <el-table-column type="expand" width="1">
                            <template slot-scope="props">
                                <!-- <el-form label-position="left"  class="demo-table-expand">
                                  <el-form-item v-if="props.row.number != 0" label="需上报县：">
                                    <span style="width:100%">
                                      <span v-for="item in props.row.county_name">{{item}}、</span>
                                    </span> 
                                  </el-form-item>
                                  <el-form-item v-if="props.row.id != 0">
                                    
                                  </el-form-item>
                                  
                                </el-form> -->
                                <el-table :data="varietyInfo2" border stripe :header-cell-style="{background:'#6E8B3D',color:'#FFFFFF',height:'0',padding:'1px',fontSize:'5px'}" :cell-style="{padding:'0',fontSize:'1px'}" :row-style="{height: '10px'}">
                                      <el-table-column align="center" fixed type="index" label="序号" />
                                      <el-table-column align="center" prop="pin_zhong_name" label="品系" />
                                      <el-table-column align="center" prop="area_data" label="面积" />
                                      <el-table-column align="center" prop="area_percent" label="占比" />
                                </el-table>
                              </template>
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="400px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo12"
                              :current-page="currentPageTwo12"
                              :page-size="pagesizeTwo12"
                              :total="dataNumTwo12"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo12" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo12 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo12"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo12 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻播栽进度调查表（早稻）" name="3">
                  <span slot="label">监测县(市、区)水稻播栽进度调查表（早稻）<el-badge v-if="pointData2[2]!=0" style="margin-left: 10px;" :value="pointData2[2]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo31">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo31}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo31" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo31" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left"  label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="100%"  label="全县总面积（万亩）" />
                          <el-table-column align="center" label="开始播种日期">
                              <el-table-column align="center" prop="field_5_1" width="100%" label="育秧" />
                              <el-table-column align="center" prop="field_5_2" width="100%" label="直播" />
                          </el-table-column>
                          <el-table-column align="center"   label="秧田面积（万亩）">
                            <el-table-column align="center" prop="field_6"  label="" />
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_7_1"  label="" />
                              <el-table-column align="center" prop="field_7_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"   label="折合大田面积（万亩）">
                            <el-table-column align="center" label="">
                              <el-table-column align="center" prop="field_8_1"  label="" />
                              <el-table-column align="center" prop="field_8_2"   label="" />  
                          </el-table-column>
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_9_1"  label="" />
                              <el-table-column align="center" prop="field_9_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度1">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_10_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_10_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_10_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_10_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_10_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_10_6" width="90%" label="占比" />
                          </el-table-column>  
                          <el-table-column align="center" label="播种期进度2">
                            <el-table-column align="center" prop="field_11_1" width="90%"  label="日期" />
                            <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_11_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_11_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_11_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_11_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_11_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_11_6" width="90%" label="占比" />
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度3">
                            <el-table-column align="center" prop="field_12_1" width="110%" label="日期" />
                            <el-table-column align="center" width="180px" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_12_2_1"  label="" />
                              <el-table-column align="center" prop="field_12_2_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_12_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_12_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_12_5"  label="小计" />
                            <el-table-column align="center" prop="field_12_6"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center" label="播种期进度4">
                            <el-table-column align="center" prop="field_13_1" width="110%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_13_3" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_13_4"  label="小计" />
                            <el-table-column align="center" prop="field_13_5"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo31"
                              :current-page="currentPageTwo31"
                              :page-size="pagesizeTwo31"
                              :total="dataNumTwo31"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo31" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo31 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo31"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo31 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻播栽进度调查表（晚稻）" name="4">
                  <span slot="label">监测县(市、区)水稻播栽进度调查表（晚稻）<el-badge v-if="pointData2[3]!=0" style="margin-left: 10px;" :value="pointData2[3]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo32">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo32}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo32" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo31" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left"  label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="100%"  label="全县总面积（万亩）" />
                          <el-table-column align="center" label="开始播种日期">
                              <el-table-column align="center" prop="field_5_1" width="100%" label="育秧" />
                              <el-table-column align="center" prop="field_5_2" width="100%" label="直播" />
                          </el-table-column>
                          <el-table-column align="center"   label="秧田面积（万亩）">
                            <el-table-column align="center" prop="field_6"  label="" />
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_7_1"  label="" />
                              <el-table-column align="center" prop="field_7_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center"   label="折合大田面积（万亩）">
                            <el-table-column align="center" label="">
                              <el-table-column align="center" prop="field_8_1"  label="" />
                              <el-table-column align="center" prop="field_8_2"   label="" />  
                          </el-table-column>
                            <el-table-column align="center" label="其中集中育秧">
                              <el-table-column align="center" prop="field_9_1"  label="" />
                              <el-table-column align="center" prop="field_9_2"  label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度1">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" width="180%" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_10_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_10_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_10_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_10_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_10_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_10_6" width="90%" label="占比" />
                          </el-table-column>  
                          <el-table-column align="center" label="播种期进度2">
                            <el-table-column align="center" prop="field_11_1" width="90%"  label="日期" />
                            <el-table-column align="center"  label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_11_2_1" width="90%" label="" />
                              <el-table-column align="center" prop="field_11_2_2" width="90%" label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_11_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_11_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_11_5" width="90%" label="小计" />
                            <el-table-column align="center" prop="field_11_6" width="90%" label="占比" />
                          </el-table-column>
                          <el-table-column align="center" label="播种期进度3">
                            <el-table-column align="center" prop="field_12_1" width="110%" label="日期" />
                            <el-table-column align="center" width="180px" label="育秧已播种面积（万亩）" >
                              <el-table-column align="center" prop="field_12_2_1"  label="" />
                              <el-table-column align="center" prop="field_12_2_2"  label="" />
                            </el-table-column>
                            <el-table-column align="center" prop="field_12_3" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_12_4" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_12_5"  label="小计" />
                            <el-table-column align="center" prop="field_12_6"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center" label="播种期进度4">
                            <el-table-column align="center" prop="field_13_1" width="110%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="150%" label="已栽插面积（万亩）" />
                            <el-table-column align="center" prop="field_13_3" width="180%" label="直播已播种面积（万亩）" />
                            <el-table-column align="center" prop="field_13_4"  label="小计" />
                            <el-table-column align="center" prop="field_13_5"  label="占比" />
                          </el-table-column> 
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo32"
                              :current-page="currentPageTwo32"
                              :page-size="pagesizeTwo32"
                              :total="dataNumTwo32"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo32" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo32 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo32"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo32 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（移栽早稻）" name="5">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（移栽早稻）<el-badge v-if="pointData2[4]!=0" style="margin-left: 10px;" :value="pointData2[4]" /></span>
                   <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo33">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo33}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo33" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo32" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left" label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable  label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%" label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="130%" label="全县总面积（万亩）" />
                          <el-table-column align="center"  label="进程1（移栽后7天）">
                            <el-table-column align="center" prop="field_5_1" width="110%"  label="日期" />
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
                          <el-table-column  align="center"  label="进程2（移栽后14天）">
                            <el-table-column align="center" prop="field_6_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程3（移栽后21天）">
                            <el-table-column align="center" prop="field_7_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程4（移栽后28天）">
                            <el-table-column align="center" prop="field_8_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程5（移栽后35天）">
                            <el-table-column align="center" prop="field_9_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程6（移栽后42天）">
                            <el-table-column align="center" prop="field_10_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程7（移栽后52天）">
                            <el-table-column align="center" prop="field_11_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程8（移栽后62天）">
                            <el-table-column align="center" prop="field_12_1" width="110%"  label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo33"
                              :current-page="currentPageTwo33"
                              :page-size="pagesizeTwo33"
                              :total="dataNumTwo33"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo33" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo33 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo33"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo33 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（直播早稻）" name="6">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（直播早稻）<el-badge v-if="pointData2[5]!=0" style="margin-left: 10px;" :value="pointData2[5]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo34">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo34}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo34" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo33" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="140%"  label="全县总面积（万亩）" />
                          
                          <el-table-column align="center" label="进程1（大田直播后20天）">
                            <el-table-column align="center" prop="field_5_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程2（大田直播后28天）">
                              <el-table-column align="center" prop="field_6_1" width="110%" label="日期" />
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
                            `<el-table-column align="center" prop="field_7_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程4（大田直播后42天）">
                              <el-table-column align="center" prop="field_8_1" width="110%"  label="日期" />
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
                            <el-table-column align="center" prop="field_9_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程6（大田直播后56天）">
                              <el-table-column align="center" prop="field_10_1" width="110%" label="日期" />
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
                            <el-table-column align="center" prop="field_11_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程8（大田直播后76天）">
                              <el-table-column align="center" prop="field_12_1" width="110%" label="日期" />
                              <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_12_2_1" label="" />
                              <el-table-column align="center" prop="field_12_2_1" label="" />  
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
                            <el-table-column align="center" prop="field_13_1" width="130%" label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo34"
                              :current-page="currentPageTwo34"
                              :page-size="pagesizeTwo34"
                              :total="dataNumTwo34"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo34" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo34 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo34"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo34 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（移栽晚稻）" name="7">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（移栽晚稻）<el-badge v-if="pointData2[6]!=0" style="margin-left: 10px;" :value="pointData2[6]" /></span>
                   <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo35">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo35}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo35" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo35" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" sortable fixed="left" label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable  label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="130%" label="全县总面积（万亩）" />
                          <el-table-column align="center"  label="进程1（移栽后7天）">
                            <el-table-column align="center" prop="field_4_1" width="110%"  label="日期" />
                            <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_4_2_1" label="" />
                              <el-table-column align="center" prop="field_4_2_2" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="二类苗（万亩）">
                              <el-table-column align="center" prop="field_4_3_1" label="" />
                              <el-table-column align="center" prop="field_4_3_2" label="" />
                            </el-table-column>
                            <el-table-column align="center" label="三类苗（万亩）">
                              <el-table-column align="center" prop="field_4_4_1" label="" />
                              <el-table-column align="center" prop="field_4_4_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column  align="center"  label="进程2（移栽后14天）">
                            <el-table-column align="center" prop="field_5_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程3（移栽后21天）">
                            <el-table-column align="center" prop="field_6_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程4（移栽后28天）">
                            <el-table-column align="center" prop="field_7_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程5（移栽后35天）">
                            <el-table-column align="center" prop="field_8_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程6（移栽后42天）">
                            <el-table-column align="center" prop="field_9_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程7（移栽后52天）">
                            <el-table-column align="center" prop="field_10_1" width="110%"  label="日期" />
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
                          <el-table-column align="center" label="进程8（移栽后62天）">
                            <el-table-column align="center" prop="field_11_1" width="110%"  label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo35"
                              :current-page="currentPageTwo35"
                              :page-size="pagesizeTwo35"
                              :total="dataNumTwo35"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo35" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo35 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo35"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo35 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻苗情评价动态列表（直播晚稻）" name="8">
                  <span slot="label">监测县(市、区)水稻苗情评价动态列表（直播晚稻）<el-badge v-if="pointData2[7]!=0" style="margin-left: 10px;" :value="pointData2[7]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo36">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo36}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo36" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo36" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="100%"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="140%"  label="全县总面积（万亩）" />
                          
                          <el-table-column align="center" label="进程1（大田直播后20天）">
                            <el-table-column align="center" prop="field_5_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程2（大田直播后28天）">
                              <el-table-column align="center" prop="field_6_1" width="110%" label="日期" />
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
                            `<el-table-column align="center" prop="field_7_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程4（大田直播后42天）">
                              <el-table-column align="center" prop="field_8_1" width="110%"  label="日期" />
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
                            <el-table-column align="center" prop="field_9_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程6（大田直播后56天）">
                              <el-table-column align="center" prop="field_10_1" width="110%" label="日期" />
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
                            <el-table-column align="center" prop="field_11_1" width="110%" label="日期" />
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
                          <el-table-column align="center" label="进程8（大田直播后76天）">
                              <el-table-column align="center" prop="field_12_1" width="110%" label="日期" />
                              <el-table-column align="center" label="一类苗（万亩）">
                              <el-table-column align="center" prop="field_12_2_1" label="" />
                              <el-table-column align="center" prop="field_12_2_1" label="" />  
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
                            <el-table-column align="center" prop="field_13_1" width="130%" label="日期" />
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
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo36"
                              :current-page="currentPageTwo36"
                              :page-size="pagesizeTwo36"
                              :total="dataNumTwo36"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo36" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo36 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo36"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo36 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)中稻生育进度调查表（移栽稻）" name="9">
                  <span slot="label">监测县(市、区)中稻生育进度调查表（移栽稻）<el-badge v-if="pointData2[8]!=0" style="margin-left: 10px;" :value="pointData2[8]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo41">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo41}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo41" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo41" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6" width="150%" label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" label="移栽期">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_8_2"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_8_3"  label="基本苗（万/亩）" />
                            <el-table-column align="center" prop="field_8_4"  label="亩穴数（万）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程1（移栽后7天）">
                            <el-table-column align="center" prop="field_9_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_9_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_9_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程2（移栽后14天）">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_10_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_10_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程3（移栽后21天）">
                            <el-table-column align="center" prop="field_11_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_11_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_11_4"  label="茎蘖数（万/亩）" />
                          </el-table-column> 
                          <el-table-column align="center" label="进程4（移栽后28天）">
                            <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_12_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_12_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程5（移栽后35天）">
                            <el-table-column align="center" prop="field_13_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_13_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_13_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程6（移栽后42天）">
                            <el-table-column align="center" prop="field_14_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_14_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_14_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程7（移栽后52天）">
                            <el-table-column align="center" prop="field_15_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_15_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_15_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_15_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>     
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo41"
                              :current-page="currentPageTwo41"
                              :page-size="pagesizeTwo41"
                              :total="dataNumTwo41"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo41" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo41 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo41"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo41 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)直播稻生育进度调查表（直播稻）" name="10">
                  <span slot="label">监测县(市、区)直播稻生育进度调查表（直播稻）<el-badge v-if="pointData2[9]!=0" style="margin-left: 10px;" :value="pointData2[9]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo42">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo42}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo42" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo42" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6"  label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" label="进程1（大田直播后20天）">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_8_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_8_4"  label="基本苗（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程2（大田直播后28天）">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_9_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_9_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程3（大田直播后35天）">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_10_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_10_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程4（大田直播后42天）">
                            <el-table-column align="center" prop="field_11_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_11_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_11_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程5（大田直播后49天）">
                            <el-table-column align="center" prop="field_12_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_12_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_12_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程6（大田直播后56天）">
                            <el-table-column align="center" prop="field_13_1" width="100%"  label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_13_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_13_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="进程7（大田直播后66天）">
                            <el-table-column align="center" prop="field_14_1" width="100%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="140%" label="生育进程" />
                            <el-table-column align="center" prop="field_14_3"  label="叶龄（叶）" />
                            <el-table-column align="center" prop="field_14_4"  label="茎蘖数（万/亩）" />
                          </el-table-column>     
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo42"
                              :current-page="currentPageTwo42"
                              :page-size="pagesizeTwo42"
                              :total="dataNumTwo42"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo42" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo42 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo42"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo42 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点茎蘖数叶面积构成表" name="11">
                  <span slot="label">水稻监测点茎蘖数叶面积构成表<el-badge v-if="pointData2[10]!=0" style="margin-left: 10px;" :value="pointData2[10]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo43">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo43}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo43" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo43" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" sortable label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" prop="field_8" width="120%" label="目标产量(kg/亩)" />
                          <el-table-column align="center" prop="field_9" width="120%" label="基本苗（万/亩）" />
                          <el-table-column align="center" label="拔节期">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="测定日期" />
                            <el-table-column align="center" prop="field_10_2"  label="株高（cm）" />
                            <el-table-column align="center" prop="field_10_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_10_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_10_5"  label="倒4与倒3叶色比" />
                            <el-table-column align="center" prop="field_10_6"  label="1叶分蘖个数" />
                            <el-table-column align="center" prop="field_10_7"  label="2叶及以上分蘖个数" />
                            <el-table-column align="center" prop="field_10_8"  label="3叶及以上分蘖个数" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期（幼穗分化1-2期）">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="测定日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="株高（cm）" />
                            <el-table-column align="center" prop="field_11_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_11_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_11_5"  label="倒4与倒3叶色比" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期（幼穗分化3-4期）">
                            <el-table-column align="center" prop="field_12_1" width="90%"  label="测定日期" />
                            <el-table-column align="center" prop="field_12_2"  label="株高（cm）" />
                            <el-table-column align="center" prop="field_12_3"  label="总茎蘖数（万/亩）" />
                            <el-table-column align="center" prop="field_12_4"  label="平均单茎绿叶数" />
                            <el-table-column align="center" prop="field_12_5"  label="倒4与倒3叶色比" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo43"
                              :current-page="currentPageTwo43"
                              :page-size="pagesizeTwo43"
                              :total="dataNumTwo43"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo43" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo43 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo43"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo43 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点苗情生育期统计表" name="12">
                  <span slot="label">水稻监测点苗情生育期统计表<el-badge v-if="pointData2[11]!=0" style="margin-left: 10px;" :value="pointData2[11]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo44">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo44}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo44" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo44" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_6" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_7" width="120%" sortable label="栽培方式" />
                          <el-table-column align="center" prop="field_8" width="100%" label="播种期（月/日）" />
                          <el-table-column align="center" prop="field_9" width="100%" label="出苗期（月/日）" />
                          <el-table-column align="center" prop="field_10" width="100%" label="移栽期（月/日）" />
                          <el-table-column align="center" prop="field_fennie" width="100%" label="分蘖期（月/日）" />
                          <el-table-column align="center" prop="field_11" width="100%" label="够苗期（月/日）" />
                          <el-table-column align="center" prop="field_12" width="120%" label="有效分蘗临界叶龄期（月/日）" />
                          <el-table-column align="center" prop="field_13" width="100%" label="拔节期（月/日）" />
                          <el-table-column align="center" prop="field_14" width="100%" label="孕穗期（月/日）" />
                          <el-table-column align="center" prop="field_15" width="100%" label="抽穗期（月/日）" />
                          <el-table-column align="center" prop="field_16" width="100%" label="齐穗期（月/日）" />
                          <el-table-column align="center" prop="field_17" width="100%" label="成熟期（月/日）" />
                          <el-table-column align="center" prop="field_18" width="100%" label="全生育期（日）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo44"
                              :current-page="currentPageTwo44"
                              :page-size="pagesizeTwo44"
                              :total="dataNumTwo44"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo44" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo44 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo44"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo44 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="叶片含氮测定仪导出数据表" name="13">
                  <span slot="label">叶片含氮测定仪导出数据表<el-badge v-if="pointData2[12]!=0" style="margin-left: 10px;" :value="pointData2[12]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo51">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo51}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo51" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo51" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="够苗期">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_7_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_7_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_7_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_7_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_7_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_7_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_7_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_7_9" label="目标产量" />
                            <el-table-column align="center" prop="field_7_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="拔节期">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_8_2" width="120%"  label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_8_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_8_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_8_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_8_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_8_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_8_8" width="120%"  label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_8_9" label="目标产量" />
                            <el-table-column align="center" prop="field_8_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期1">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_9_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_9_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_9_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_9_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_9_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_9_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_9_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_9_9" label="目标产量" />
                            <el-table-column align="center" prop="field_9_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗期2">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_10_2" width="120%" label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_10_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_10_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_10_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_10_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_10_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_10_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_10_9" label="目标产量" />
                            <el-table-column align="center" prop="field_10_10" label="施肥量" />
                          </el-table-column>
                          <el-table-column align="center" label="抽穗期">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_11_2" width="120%"  label="叶绿素（SPAD）" />
                            <el-table-column align="center" prop="field_11_3" width="120%" label="氮含量（mg/g）" />
                            <el-table-column align="center" prop="field_11_4"  label="叶面温度（℃）" />
                            <el-table-column align="center" prop="field_11_5"  label="叶面湿度（%RH）" />
                            <el-table-column align="center" prop="field_11_6"  label="植物名称" />
                            <el-table-column align="center" prop="field_11_7" width="120%" label="化肥中氮含量（%）" />
                            <el-table-column align="center" prop="field_11_8" width="120%" label="化肥中氮的利用率（%）" />
                            <el-table-column align="center" prop="field_11_9" label="目标产量" />
                            <el-table-column align="center" prop="field_11_10" label="施肥量" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo51"
                              :current-page="currentPageTwo51"
                              :page-size="pagesizeTwo51"
                              :total="dataNumTwo51"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo51" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo51 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo51"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo51 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点水分管理记录表" name="14">
                  <span slot="label">水稻监测点水分管理记录表<el-badge v-if="pointData2[13]!=0" style="margin-left: 10px;" :value="pointData2[13]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo61">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo61}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo61" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo61" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable  label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="135%"  label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="时期1">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期2">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期3">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期4">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期5">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期6">
                            <el-table-column align="center" prop="field_12_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <el-table-column align="center" label="时期7">
                            <el-table-column align="center" prop="field_13_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_13_2" width="100%" label="深度（cm）" />
                          </el-table-column>
                          <!-- <el-table-column align="center" label="时期8">
                            <el-table-column align="center" prop="field_14_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_14_2" width="100%" label="深度（cm）" />
                          </el-table-column> -->
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo61"
                              :current-page="currentPageTwo61"
                              :page-size="pagesizeTwo61"
                              :total="dataNumTwo61"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo61" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo61 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo61"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo61 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点化除管理记录表" name="15">
                  <span slot="label">水稻监测点化除管理记录表<el-badge v-if="pointData2[14]!=0" style="margin-left: 10px;" :value="pointData2[14]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo62">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo62}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo62" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo62" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3"  width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4"  width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="化除1-播栽前">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_7_4" width="125%" label="方法" />
                            <el-table-column align="center" prop="field_7_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化除2-播栽后或出苗后">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_8_4" width="125%" label="方法" />
                            <el-table-column align="center" prop="field_8_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化除3播栽后或出苗后">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="110%" label="除草剂名称" />
                            <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_9_4"  label="方法" />
                            <el-table-column align="center" prop="field_9_5" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo62"
                              :current-page="currentPageTwo62"
                              :page-size="pagesizeTwo62"
                              :total="dataNumTwo62"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo62" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo62 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo62"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo62 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点病虫害防治记录表" name="16">
                  <span slot="label">水稻监测点病虫害防治记录表<el-badge v-if="pointData2[15]!=0" style="margin-left: 10px;" :value="pointData2[15]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo63">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo63}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo63" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo63" label="序号" />
                          <el-table-column align="center" prop="field_1" width="120%" fixed="left" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="病虫害防治1">
                            <el-table-column align="center" prop="field_7_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_7_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_7_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_7_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_7_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_7_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治2">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_8_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_8_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_8_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_8_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_8_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治3">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_9_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_9_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_9_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_9_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_9_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="病虫害防治4">
                            <el-table-column align="center" prop="field_10_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_10_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_10_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_10_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_10_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_10_6" width="140%" label="生育进程" />
                          </el-table-column> 
                          <el-table-column align="center" label="病虫害防治5">
                            <el-table-column align="center" prop="field_11_1" width="100%" label="病虫害种类" />
                            <el-table-column align="center" prop="field_11_2" width="90%" label="使用时间" />
                            <el-table-column align="center" prop="field_11_3"  label="用药种类" />
                            <el-table-column align="center" prop="field_11_4" width="105%" label="用药量(ml/亩)" />
                            <el-table-column align="center" prop="field_11_5"  label="用药方法" />
                            <el-table-column align="center" prop="field_11_6" width="140%" label="生育进程" />
                          </el-table-column>       
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo63"
                              :current-page="currentPageTwo63"
                              :page-size="pagesizeTwo63"
                              :total="dataNumTwo63"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo63" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo63 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo63"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo63 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点施肥管理记录表" name="17">
                  <span slot="label">水稻监测点施肥管理记录表<el-badge v-if="pointData2[16]!=0" style="margin-left: 10px;" :value="pointData2[16]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo64">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo64}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo64" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo64" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="基肥施肥（以折纯计kg/亩）1">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_7_2"  label="N" />
                            <el-table-column align="center" prop="field_7_3"  label="P" />
                            <el-table-column align="center" prop="field_7_4"  label="K" />
                            <el-table-column align="center" prop="field_7_5" width="100%" label="方法" />
                            <el-table-column align="center" prop="field_7_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="分蘖肥施肥（以折纯计kg/亩）2">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="时间" />
                            <el-table-column align="center" prop="field_8_2"  label="N" />
                            <el-table-column align="center" prop="field_8_3"  label="P" />
                            <el-table-column align="center" prop="field_8_4"  label="K" />
                            <el-table-column align="center" prop="field_8_5" width="100%" label="方法" />
                            <el-table-column align="center" prop="field_8_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）3">
                            <el-table-column align="center" prop="field_9_1" width="90%"  label="时间" />
                            <el-table-column align="center" prop="field_9_2"  label="N" />
                            <el-table-column align="center" prop="field_9_3"  label="P" />
                            <el-table-column align="center" prop="field_9_4"  label="K" />
                            <el-table-column align="center" prop="field_9_5" width="100%"  label="方法" />
                            <el-table-column align="center" prop="field_9_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="孕穗肥施肥（以折纯计kg/亩）4">
                            <el-table-column align="center" prop="field_10_1" width="90%"  label="时间" />
                            <el-table-column align="center" prop="field_10_2"  label="N" />
                            <el-table-column align="center" prop="field_10_3"  label="P" />
                            <el-table-column align="center" prop="field_10_4"  label="K" />
                            <el-table-column align="center" prop="field_10_5" width="100%"  label="方法" />
                            <el-table-column align="center" prop="field_10_6" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="抽穗叶面肥施肥（以折纯计kg/亩）4">
                            <el-table-column align="center" prop="field_11_1"  label="时间" />
                            <el-table-column align="center" prop="field_11_2"  label="N" />
                            <el-table-column align="center" prop="field_11_3"  label="P" />
                            <el-table-column align="center" prop="field_11_4"  label="K" />
                            <el-table-column align="center" prop="field_11_5"  label="方法" />
                            <el-table-column align="center" prop="field_11_6" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo64"
                              :current-page="currentPageTwo64"
                              :page-size="pagesizeTwo64"
                              :total="dataNumTwo64"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo64" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo64 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo64"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo64 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点化学调节剂使用记录表" name="18">
                  <span slot="label">水稻监测点化学调节剂使用记录表<el-badge v-if="pointData2[17]!=0" style="margin-left: 10px;" :value="pointData2[17]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo65">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo65}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo65" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo65" label="序号" />
                          <el-table-column align="center" fixed="left" prop="field_1" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="120%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="120%" sortable label="栽植方式" />
                          <el-table-column align="center" label="化调剂1">
                            <el-table-column align="center" prop="field_7_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_7_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_7_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_7_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_7_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化调剂2">
                            <el-table-column align="center" prop="field_8_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_8_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_8_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_8_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_8_5" width="140%" label="生育进程" />
                          </el-table-column>
                          <el-table-column align="center" label="化调剂3">
                            <el-table-column align="center" prop="field_9_1" width="100%" label="使用时间" />
                            <el-table-column align="center" prop="field_9_2" width="130%" label="化调剂名称" />
                            <el-table-column align="center" prop="field_9_3"  label="亩用量/kg" />
                            <el-table-column align="center" prop="field_9_4" width="110%" label="使用方法" />
                            <el-table-column align="center" prop="field_9_5" width="140%" label="生育进程" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo65"
                              :current-page="currentPageTwo65"
                              :page-size="pagesizeTwo65"
                              :total="dataNumTwo65"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo65" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo65 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo65"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo65 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻双季稻苗情监测预测产表" name="19">
                  <span slot="label">监测县(市、区)水稻双季稻苗情监测预测产表<el-badge v-if="pointData2[18]!=0" style="margin-left: 10px;" :value="pointData2[18]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo71">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo71}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo71" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo71" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="140%" label="早稻总面积（万亩）" />
                          <el-table-column align="center" label="早稻6月20日前已抽穗扬花">
                            <el-table-column align="center" prop="field_3_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_3_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_3_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_3_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_3_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_3_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="早稻6月20日~30日抽穗扬花">
                            <el-table-column align="center" prop="field_4_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_4_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_4_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_5" width="140%" label="晚稻总面积（万亩）" />
                          <el-table-column align="center" label="晚稻9月10日前已抽穗扬花">
                            <el-table-column align="center" prop="field_6_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_6_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_6_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="晚稻9月10日以后抽穗扬花">
                            <el-table-column align="center" prop="field_7_1"  label="面积(万亩）" />
                            <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_7_3"  label="穗总粒数（个）" />
                            <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_7_6"  label="八五折理论产量（kg/亩）" />
                          </el-table-column>  
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo71"
                              :current-page="currentPageTwo71"
                              :page-size="pagesizeTwo71"
                              :total="dataNumTwo71"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo71" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo71 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo71"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo71 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻双季稻收获进度调查表（早稻）" name="20">
                  <span slot="label">监测县(市、区)水稻双季稻收获进度调查表（早稻）<el-badge v-if="pointData2[19]!=0" style="margin-left: 10px;" :value="pointData2[19]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo72">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo72}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo72" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo72" label="序号" />
                          <el-table-column align="center" fixed="left" prop="field_1" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="140%" label="总面积(万亩)" />
                          <el-table-column align="center" label="收获进度1（7月15日）">
                            <el-table-column align="center" prop="field_4_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_4_2_1" label="" />
                              <el-table-column align="center"  prop="field_4_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度2（7月25日）">
                            <el-table-column align="center" prop="field_5_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_5_2_1" label="" />
                              <el-table-column align="center"  prop="field_5_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度3（8月5日）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_6_2_1" label="" />
                              <el-table-column align="center"  prop="field_6_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="早稻实测产">
                              <el-table-column align="center" prop="field_7_1" width="120%" label="面积(万亩）" />
                              <el-table-column align="center" prop="field_7_2" width="120%" label="亩有效穗（万）" />
                              <el-table-column align="center" prop="field_7_3" width="120%" label="穗总粒数（个）" />
                              <el-table-column align="center" prop="field_7_4" width="120%" label="结实率（％）" />
                              <el-table-column align="center" prop="field_7_5" width="120%" label="千粒重（g）" />
                              <el-table-column align="center" prop="field_7_6" width="170%" label="八五折理论产量（kg/亩）" />
                            </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo72"
                              :current-page="currentPageTwo72"
                              :page-size="pagesizeTwo72"
                              :total="dataNumTwo72"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo72" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo72 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo72"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo72 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻双季稻收获进度调查表（晚稻）" name="21">
                  <span slot="label">监测县(市、区)水稻双季稻收获进度调查表（晚稻）<el-badge v-if="pointData2[20]!=0" style="margin-left: 10px;" :value="pointData2[20]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo73">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo73}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo73" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo73" label="序号" />
                          <el-table-column align="center" fixed="left" prop="field_1" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="140%" label="总面积(万亩)" />
                          <el-table-column align="center" label="收获进度1（11月5日）">
                            <el-table-column align="center" prop="field_4_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_4_2_1" label="" />
                              <el-table-column align="center"  prop="field_4_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度2（11月15日）">
                            <el-table-column align="center" prop="field_5_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_5_2_1" label="" />
                              <el-table-column align="center"  prop="field_5_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度3（11月25日）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_6_2_1" label="" />
                              <el-table-column align="center"  prop="field_6_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="收获进度4（11月30日）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" label="收获面积（万亩）">
                              <el-table-column align="center"  prop="field_7_2_1" label="" />
                              <el-table-column align="center"  prop="field_7_2_2" label="" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="晚稻实测产">
                              <el-table-column align="center" prop="field_8_1" width="120%" label="面积(万亩）" />
                              <el-table-column align="center" prop="field_8_2" width="120%" label="亩有效穗（万）" />
                              <el-table-column align="center" prop="field_8_3" width="120%" label="穗总粒数（个）" />
                              <el-table-column align="center" prop="field_8_4" width="120%" label="结实率（％）" />
                              <el-table-column align="center" prop="field_8_5" width="120%" label="千粒重（g）" />
                              <el-table-column align="center" prop="field_8_6" width="170%" label="八五折理论产量（kg/亩）" />
                            </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo73"
                              :current-page="currentPageTwo73"
                              :page-size="pagesizeTwo73"
                              :total="dataNumTwo73"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo73" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo73 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo73"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo73 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻双季稻测产表" name="22">
                  <span slot="label">监测县(市、区)水稻双季稻测产表<el-badge v-if="pointData2[21]!=0" style="margin-left: 10px;" :value="pointData2[21]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo74">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo74}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo74" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo74" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable  label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable  label="稻作类型" />
                          <el-table-column align="center" label="全县面上平均水平">
                            <el-table-column align="center" prop="field_4_1"  label="总面积（万亩）" />
                            <el-table-column align="center" prop="field_4_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_4_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_4_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_4_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_4_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="人工移栽">
                            <el-table-column align="center" prop="field_5_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_5_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_5_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_5_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_5_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_5_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="机械插秧">
                            <el-table-column align="center" prop="field_6_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_6_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_6_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_6_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_6_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_6_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="盘育抛秧">
                            <el-table-column align="center" prop="field_7_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_7_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_7_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_7_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_7_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_7_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="无盘旱育抛秧">
                            <el-table-column align="center" prop="field_8_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_8_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_8_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_8_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_8_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_8_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="撒直播">
                            <el-table-column align="center" prop="field_9_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_9_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_9_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_9_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_9_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_9_6" width="120%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>
                          <el-table-column align="center" label="机条播">
                            <el-table-column align="center" prop="field_10_1"  label="面积（万亩）" />
                            <el-table-column align="center" prop="field_10_2"  label="亩有效穗（万）" />
                            <el-table-column align="center" prop="field_10_3"  label="每穗粒数（粒）" />
                            <el-table-column align="center" prop="field_10_4"  label="结实率（％）" />
                            <el-table-column align="center" prop="field_10_5"  label="千粒重（g）" />
                            <el-table-column align="center" prop="field_10_6" width="110%" label="八五折理论产量（kg/亩）" />
                          </el-table-column>        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo74"
                              :current-page="currentPageTwo74"
                              :page-size="pagesizeTwo74"
                              :total="dataNumTwo74"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo74" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo74 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo74"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo74 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻生产成本效益统计表（早稻）" name="23">
                  <span slot="label">监测县(市、区)水稻生产成本效益统计表（早稻）<el-badge v-if="pointData2[22]!=0" style="margin-left: 10px;" :value="pointData2[22]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo81">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo81}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo81" border :header-cell-style="headerStyle81" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo81" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2"  label="地区类型" />
                          <el-table-column align="center" prop="field_3"  label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="105%"  label="品种类型" />
                          <el-table-column align="center" prop="field_5" width="110%" label="监测田块" />
                          <el-table-column align="center" prop="field_6"  label="代表面积（万亩）" />
                          <el-table-column align="center" prop="field_7"  label="栽植方式" />
                          <el-table-column align="center" prop="field_8"  label="水稻亩产值（元）" />
                          <el-table-column align="center" prop="field_9" width="140%" label="其中主产品产值（公斤/元）" />
                          <el-table-column align="center" prop="field_10" width="140%" label="副产品产值（公斤/元）" />
                          <el-table-column align="center" label="劳动用工">
                            <el-table-column align="center" label="育秧" >
                              <el-table-column align="center" prop="field_11_1_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_1_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="耕作" >
                              <el-table-column align="center" prop="field_11_2_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_2_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="移栽" >
                              <el-table-column align="center" prop="field_11_3_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_3_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="直播" >
                              <el-table-column align="center" prop="field_11_4_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_4_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="大田管理" >
                              <el-table-column align="center" prop="field_11_5_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_5_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="小计" >
                              <el-table-column align="center" prop="field_11_6_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_11_6_2" width="160%" label="价格（元/亩）" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="物质费用">
                            <el-table-column align="center" prop="field_12_1" width="120%" label="种子（元/亩）" />
                            <el-table-column align="center" label="化肥">
                              <el-table-column align="center" prop="field_12_2_1"  label="种类" />
                              <el-table-column align="center" prop="field_12_2_2" width="130%" label="施用量（kg/亩）" />
                              <el-table-column align="center" prop="field_12_2_3" width="130%" label="价格（元/kg）" />
                              <el-table-column align="center" prop="field_12_2_4" width="130%" label="小计（元/亩）" />
                            </el-table-column>
                              <el-table-column align="center" prop="field_12_3" width="120%" label="有机肥（元/亩）" />
                              <el-table-column align="center" prop="field_12_4" width="120%" label="农药（元/亩）" />
                              <el-table-column align="center" prop="field_12_5" width="120%" label="灌溉（元/亩）" />
                              <el-table-column align="center" prop="field_12_6" width="140%" label="机械耕作（元/亩）" />
                              <el-table-column align="center" prop="field_12_7" width="165%" label="机械插秧/直播（元/亩）" />
                              <el-table-column align="center" prop="field_12_8" width="140%" label="机械收获（元/亩）" />
                              <el-table-column align="center" prop="field_12_9" width="120%" label="其他（元/亩）" />
                              <el-table-column align="center" prop="field_12_10" width="120%" label="小计（元/亩）" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_13"  label="地租（元/亩）" />
                          <el-table-column align="center" prop="field_14" label="其他费用（元/亩）" />
                          <el-table-column align="center" prop="field_15"  label="亩总成本（元）" />
                          <el-table-column align="center" prop="field_16"  label="亩收益（元）" />        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo81"
                              :current-page="currentPageTwo81"
                              :page-size="pagesizeTwo81"
                              :total="dataNumTwo81"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo81" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo81 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo81"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo81 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻生产成本效益统计表（晚稻）" name="24">
                  <span slot="label">监测县(市、区)水稻生产成本效益统计表（晚稻）<el-badge v-if="pointData2[23]!=0" style="margin-left: 10px;" :value="pointData2[23]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo82">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo82}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo82" border :header-cell-style="headerStyle81" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo82" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2"  label="地区类型" />
                          <!-- <el-table-column align="center" prop="field_3"  label="稻作类型" /> -->
                          <el-table-column align="center" prop="field_3" width="105%"  label="品种类型" />
                          <el-table-column align="center" prop="field_4" width="110%" label="监测田块" />
                          <el-table-column align="center" prop="field_5"  label="代表面积（万亩）" />
                          <el-table-column align="center" prop="field_6"  label="栽植方式" />
                          <el-table-column align="center" prop="field_7"  label="水稻亩产值（元）" />
                          <el-table-column align="center" prop="field_8" width="140%" label="其中主产品产值（公斤/元）" />
                          <el-table-column align="center" prop="field_9" width="140%" label="副产品产值（公斤/元）" />
                          <el-table-column align="center" label="劳动用工">
                            <el-table-column align="center" label="育秧" >
                              <el-table-column align="center" prop="field_10_1_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_1_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="耕作" >
                              <el-table-column align="center" prop="field_10_2_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_2_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="移栽" >
                              <el-table-column align="center" prop="field_10_3_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_3_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="直播" >
                              <el-table-column align="center" prop="field_10_4_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_4_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="大田管理" >
                              <el-table-column align="center" prop="field_10_5_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_5_2" width="160%" label="单位用工价格（元/个）" />
                            </el-table-column>
                            <el-table-column align="center" label="小计" >
                              <el-table-column align="center" prop="field_10_6_1" width="110%" label="数量（个/亩）" />
                              <el-table-column align="center" prop="field_10_6_2" width="160%" label="价格（元/亩）" />
                            </el-table-column>
                          </el-table-column>
                          <el-table-column align="center" label="物质费用">
                            <el-table-column align="center" prop="field_11_1" width="120%" label="种子（元/亩）" />
                            <el-table-column align="center" label="化肥">
                              <el-table-column align="center" prop="field_11_2_1"  label="种类" />
                              <el-table-column align="center" prop="field_11_2_2" width="130%" label="施用量（kg/亩）" />
                              <el-table-column align="center" prop="field_11_2_3" width="130%" label="价格（元/kg）" />
                              <el-table-column align="center" prop="field_11_2_4" width="130%" label="小计（元/亩）" />
                            </el-table-column>
                              <el-table-column align="center" prop="field_11_3" width="120%" label="有机肥（元/亩）" />
                              <el-table-column align="center" prop="field_11_4" width="120%" label="农药（元/亩）" />
                              <el-table-column align="center" prop="field_11_5" width="120%" label="灌溉（元/亩）" />
                              <el-table-column align="center" prop="field_11_6" width="140%" label="机械耕作（元/亩）" />
                              <el-table-column align="center" prop="field_11_7" width="165%" label="机械插秧/直播（元/亩）" />
                              <el-table-column align="center" prop="field_11_8" width="140%" label="机械收获（元/亩）" />
                              <el-table-column align="center" prop="field_11_9" width="120%" label="其他（元/亩）" />
                              <el-table-column align="center" prop="field_11_10" width="120%" label="小计（元/亩）" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_12"  label="地租（元/亩）" />
                          <el-table-column align="center" prop="field_13" label="其他费用（元/亩）" />
                          <el-table-column align="center" prop="field_14"  label="亩总成本（元）" />
                          <el-table-column align="center" prop="field_15"  label="亩收益（元）" />        
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo82"
                              :current-page="currentPageTwo82"
                              :page-size="pagesizeTwo82"
                              :total="dataNumTwo82"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo82" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo82 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo82"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo82 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻倒伏灾害表" name="25">
                  <span slot="label">监测县(市、区)水稻倒伏灾害表<el-badge v-if="pointData2[24]!=0" style="margin-left: 10px;" :value="pointData2[24]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo93">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo93}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo93" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo93" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" sortable width="120%" label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_6" width="170%" label="倒伏时间" />
                          <el-table-column align="center" prop="field_7"  label="倒伏面积（万亩）" />
                          <el-table-column align="center" prop="field_8"  label="占比（%）" />
                          <el-table-column align="center" prop="field_9"  label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_10"  label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_11"  label="产量损失（%）" />
                          <el-table-column align="center" prop="field_12"  label="倒伏程度（级别）" />
                          <el-table-column align="center" prop="field_13"  label="主要品种" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo93"
                              :current-page="currentPageTwo93"
                              :page-size="pagesizeTwo93"
                              :total="dataNumTwo93"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo93" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo93 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo93"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo93 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻低温冷害表" name="26">
                  <span slot="label">监测县(市、区)水稻低温冷害表<el-badge v-if="pointData2[25]!=0" style="margin-left: 10px;" :value="pointData2[25]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo94">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo94}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo94" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo94" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="110%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5"  label="低温冷害" />
                          <el-table-column align="center" prop="field_6" width="100%" label="低温冷害发生时间" />
                          <el-table-column align="center" prop="field_7" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_8" width="110%" label="冷害总面积（万亩）" />
                          <el-table-column align="center" label="冷害级别（万亩）" >
                            <el-table-column align="center" prop="field_9_1"  label="1级" />
                            <el-table-column align="center" prop="field_9_2"  label="2级" />
                            <el-table-column align="center" prop="field_9_3"  label="3级" />
                            <el-table-column align="center" prop="field_9_4"  label="4级" />
                            <el-table-column align="center" prop="field_9_5"  label="5级" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_10" label="主要品种名称" />
                          <el-table-column align="center" prop="field_11"  label="占比（%）" />
                          <el-table-column align="center" prop="field_12" width="120%" label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_13" width="120%" label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_14"  label="产量损失（%）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo94"
                              :current-page="currentPageTwo94"
                              :page-size="pagesizeTwo94"
                              :total="dataNumTwo94"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo94" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo94 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo94"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo94 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻高温热害表" name="27">
                  <span slot="label">监测县(市、区)水稻高温热害表<el-badge v-if="pointData2[26]!=0" style="margin-left: 10px;" :value="pointData2[26]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo95">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo95}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo95" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo95" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable label="品系类型" />
                          <el-table-column align="center" prop="field_5"  label="高温热害" />
                          <el-table-column align="center" prop="field_6" width="100%"  label="高温热害发生时间" />
                          <el-table-column align="center" prop="field_7" width="110%" label="全县种植面积（万亩）" />
                          <el-table-column align="center" prop="field_8" width="110%" label="热害总面积（万亩）" />
                          <el-table-column align="center" label="热害级别（万亩）" >
                            <el-table-column align="center" prop="field_9_1"  label="1级" />
                            <el-table-column align="center" prop="field_9_2"  label="2级" />
                            <el-table-column align="center" prop="field_9_3"  label="3级" />
                            <el-table-column align="center" prop="field_9_4"  label="4级" />
                            <el-table-column align="center" prop="field_9_5"  label="5级" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_10"  label="主要品种名称" />
                          <el-table-column align="center" prop="field_11"  label="占比（%）" />
                          <el-table-column align="center" prop="field_12" width="110%" label="其中：成灾面积（万亩）" />
                          <el-table-column align="center" prop="field_13" width="110%" label="其中：绝收面积（万亩）" />
                          <el-table-column align="center" prop="field_14"  label="产量损失（%）" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo95"
                              :current-page="currentPageTwo95"
                              :page-size="pagesizeTwo95"
                              :total="dataNumTwo95"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo95" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo95 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo95"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo95 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="监测县(市、区)水稻涝害调查表" name="28">
                  <span slot="label">监测县(市、区)水稻涝害调查表<el-badge v-if="pointData2[27]!=0" style="margin-left: 10px;" :value="pointData2[27]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo96">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo96}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo96" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo96" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="稻作类型" />
                          <el-table-column align="center" prop="field_4" width="120%" sortable  label="品系类型" />
                          <el-table-column align="center" label="受灾统计表（万亩）">
                            <el-table-column align="center" prop="field_5_1" width="90%" label="填报日期" />
                            <el-table-column align="center" prop="field_5_2" width="120%" label="生育阶段与灾害症状" />
                            <el-table-column align="center" prop="field_5_3"  label="受灾面积" />
                            <el-table-column align="center" prop="field_5_4"  label="其中：成灾面积" />
                            <el-table-column align="center" prop="field_5_5"  label="其中：绝收面积" />
                            <el-table-column align="center" prop="field_5_6"  label="需补改种面积" />
                            <el-table-column align="center" prop="field_5_7"  label="已补改种面积" />
                          </el-table-column>
                          <el-table-column align="center" label="受灾统计表（万亩）">
                            <el-table-column align="center" prop="field_6_1" width="90%" label="填报日期" />
                            <el-table-column align="center" prop="field_6_2" width="120%" label="生育阶段与灾害症状" />
                            <el-table-column align="center" prop="field_6_3"  label="受灾面积" />
                            <el-table-column align="center" prop="field_6_4"  label="其中：成灾面积" />
                            <el-table-column align="center" prop="field_6_5"  label="其中：绝收面积" />
                            <el-table-column align="center" prop="field_6_6"  label="需补改种面积" />
                            <el-table-column align="center" prop="field_6_7"  label="已补改种面积" />
                          </el-table-column>
                          <el-table-column align="center" label="受灾统计表（万亩）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="填报日期" />
                            <el-table-column align="center" prop="field_7_2" width="120%" label="生育阶段与灾害症状" />
                            <el-table-column align="center" prop="field_7_3"  label="受灾面积" />
                            <el-table-column align="center" prop="field_7_4"  label="其中：成灾面积" />
                            <el-table-column align="center" prop="field_7_5"  label="其中：绝收面积" />
                            <el-table-column align="center" prop="field_7_6"  label="需补改种面积" />
                            <el-table-column align="center" prop="field_7_7"  label="已补改种面积" />
                          </el-table-column>
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo96"
                              :current-page="currentPageTwo96"
                              :page-size="pagesizeTwo96"
                              :total="dataNumTwo96"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo96" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo96 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo96"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo96 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点洪涝灾害表" name="29">
                  <span slot="label">水稻监测点洪涝灾害表<el-badge v-if="pointData2[28]!=0" style="margin-left: 10px;" :value="pointData2[28]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo97">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo97}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo97" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo97" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="120%"  label="监测地块" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="140%" label="品种名称" />
                          <el-table-column align="center" prop="field_5" width="120%" label="栽植方式" />
                          <el-table-column align="center" prop="field_6" width="90%" label="淹水日期" />
                          <el-table-column align="center" prop="field_7" width="90%" label="退水日期" />
                          <el-table-column align="center" prop="field_8" label="生育阶段" />
                          <el-table-column align="center" prop="field_9" width="100%" label="面积（亩）" />
                          <el-table-column align="center" prop="field_10" label="淹水高度" />
                          <el-table-column align="center" prop="field_11" label="淹水天数">
                            <el-table-column align="center" prop='field_11_1' label="" />
                            <el-table-column align="center" prop='field_11_2' label="其中没顶" />
                          </el-table-column>
                          <el-table-column align="center" prop="field_12" label="程度" />
                          
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo97"
                              :current-page="currentPageTwo97"
                              :page-size="pagesizeTwo97"
                              :total="dataNumTwo97"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo97" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo97 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo97"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo97 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点灌浆速率调查表（早稻）" name="30">
                  <span slot="label">水稻监测点灌浆速率调查表（早稻）<el-badge v-if="pointData2[29]!=0" style="margin-left: 10px;" :value="pointData2[29]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo101">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo101}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo101" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo101" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="140%" label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="140%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="130%" sortable label="栽培方式" />
                          <el-table-column align="center" label="灌浆速率测定1（扬花）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="100%" label="千粒重（克）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定2（扬花+7天）">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_8_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定3（扬花+14天）">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_9_3" width="160%"  label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定4（扬花+21天）">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_10_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定5（扬花+28天）">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_11_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定6（扬花+35天）">
                            <el-table-column align="center" prop="field_12_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_12_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo101"
                              :current-page="currentPageTwo101"
                              :page-size="pagesizeTwo101"
                              :total="dataNumTwo101"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo101" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo101 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo101"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo101 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
                <el-tab-pane label="水稻监测点灌浆速率调查表（晚稻）" name="31">
                  <span slot="label">水稻监测点灌浆速率调查表（晚稻）<el-badge v-if="pointData2[30]!=0" style="margin-left: 10px;" :value="pointData2[30]" /></span>
                  <div class="tableRange">
                    <div class="searchBox">            
                      <el-row>
                        <el-col :span="4">
                          <template>
                          <el-input placeholder="请输入县名" style="width:220px; margin-left: 0px;" v-model="countyName2"> </el-input>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <template>
                            <el-select v-model="month" clearable placeholder="请选择月份" style="width:180px; margin-left: 10px;">
                              <el-option v-for="item in months" :key="item.value" :label="item.label" :value="item.value"></el-option>
                            </el-select>
                          </template>
                        </el-col>
                        <el-col :span="4">
                          <el-button type="info" @click="reSet2">重置</el-button>
                          <el-button type="info" @click="searchInfoTwo102">查询</el-button>
                        </el-col>
                      </el-row>
                    </div>
                    <div class="tableStyle">
                      <el-row>
                          <div style="text-align:center;font-size:15px;font-weight:bolder;color:black;margin-bottom:5px">
                            <span>{{tableLabelTwo102}}</span>
                          </div>
                      </el-row>
                      <el-table :data="tableDataTwo102" border :header-cell-style="headerStyle" :cell-style="{padding:'0',fontSize:'1px'}"  :row-style="{height: '10px'}"  row-key="id" stripe>
                          <el-table-column align="center" fixed type="index" width="64px" :index="indexCountTwo102" label="序号" />
                          <el-table-column align="center" prop="field_1" fixed="left" width="120%" sortable label="县（市、区）" />
                          <el-table-column align="center" prop="field_2" width="110%" label="监测点代码" />
                          <el-table-column align="center" prop="field_3" width="120%" sortable label="地区类型" />
                          <el-table-column align="center" prop="field_4" width="140%" label="稻作类型" />
                          <el-table-column align="center" prop="field_5" width="140%" label="品种名称" />
                          <el-table-column align="center" prop="field_6" width="130%" sortable label="栽植方式" />
                          <el-table-column align="center" label="灌浆速率测定1（扬花）">
                            <el-table-column align="center" prop="field_7_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_7_2" width="100%" label="千粒重（克）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定2（扬花+7天）">
                            <el-table-column align="center" prop="field_8_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_8_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_8_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定3（扬花+14天）">
                            <el-table-column align="center" prop="field_9_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_9_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_9_3" width="160%"  label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定4（扬花+21天）">
                            <el-table-column align="center" prop="field_10_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_10_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_10_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定5（扬花+28天）">
                            <el-table-column align="center" prop="field_11_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_11_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_11_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center" label="灌浆速率测定6（扬花+35天）">
                            <el-table-column align="center" prop="field_12_1" width="90%" label="日期" />
                            <el-table-column align="center" prop="field_12_2" width="100%" label="千粒重（克）" />
                            <el-table-column align="center" prop="field_12_3" width="160%" label="灌浆速率（克/千粒•天）" />
                          </el-table-column>
                          <el-table-column align="center"  fixed="right" width="200px" label="操作">
                            <template slot-scope="scope">
                              <el-popover placement="top" width="200" :ref="`popover-${scope.$index}`"><p>是否确认通过?</p>
                                <div style="text-align: right; margin: 10px">
                                  <el-button size="mini" type="text" @click="closePopover(scope.$index)">取 消</el-button>
                                  <el-button type="primary" size="mini" @click="confirmPass2(scope.$index,scope.row)">确 定</el-button>
                                </div>
                                <el-button slot="reference" size="mini"   type="primary" style="margin-right:10px;">确  定</el-button>
                              </el-popover>
                              <el-button type="danger" size="mini" @click="backRevision2(scope.$index,scope.row)">退回</el-button>
                            </template>
                          </el-table-column>
                        
                      </el-table>
                      <el-pagination class="fy"
                              layout="prev,pager, next, total"
                              @current-change="handlePageTwo102"
                              :current-page="currentPageTwo102"
                              :page-size="pagesizeTwo102"
                              :total="dataNumTwo102"
                              background
                              >
                      </el-pagination>
                      <el-dialog title="退回原因:" :visible.sync="dialogVisibleTwo102" width="40%">
                        <el-input type="textarea" :rows="4" v-model="reason2" auto-complete="off"></el-input>                
                        <div slot="footer" class="dialog-footer">
                          <el-button @click="dialogVisibleTwo102 = false">取 消</el-button>
                          <el-popover placement="top" width="200" v-model="visibleBackTwo102"><p>是否确认退回?</p>
                            <div style="text-align: right; margin: 10px">
                              <el-button size="mini" type="text" @click="visibleBackTwo102 = false">取 消</el-button>
                              <el-button type="primary" size="mini" @click="confirmBack2()">确 定</el-button>
                            </div>
                            <el-button slot="reference" type="primary" >确  定</el-button>
                          </el-popover>
                          <!-- <el-button type="primary" @click="returnData">确 定</el-button> -->
                        </div>
                      </el-dialog>
                    </div>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <el-tab-pane label="退回记录查看" name="3">
              <div class="tableRange">
                <div class="searchBox">            
                <el-row>
                  <el-col :span="4">
                    <template>
                      <el-input placeholder="请输入表名" style="width:220px"  v-model="params1"> </el-input>
                    </template>
                  </el-col>
                  <el-col :span="4">
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
      </div>
  
</template>


<script>
import axios from 'axios'
import moment from 'moment'
export default {
  data() { 
    return { 
      //获取本年度
      systemYear:'',
      //系统时间
      systemTime:'',
      //激活el-tabs
      activeName:'1',
      activeTable:'1',
      activeTableTwo:'1',
      //暂存表英文名
      tableEngishName:'',
      tableEngishName2:'',
      //存各表未审核数据个数
      pointData:[],
      dataTotal:'',//单季稻
      pointData2:[],
      dataTotal2:'',//双季稻
      //一季稻表名
      tableLabel1:'',
      tableLabel12:'',
      tableLabel31:'',
      tableLabel32:'',
      tableLabel33:'',
      tableLabel41:'',
      tableLabel42:'',
      tableLabel43:'',
      tableLabel44:'',
      tableLabel51:'',
      tableLabel61:'',
      tableLabel62:'',
      tableLabel63:'',
      tableLabel64:'',
      tableLabel65:'',
      tableLabel71:'',
      tableLabel72:'',
      tableLabel73:'',
      tableLabel81:'',
      tableLabel93:'',
      tableLabel94:'',
      tableLabel95:'',
      tableLabel96:'',
      tableLabel97:'',
      tableLabel101:'',
      //双季稻表名
      tableLabelTwo11:'',
      tableLabelTwo12:'',
      tableLabelTwo31:'',
      tableLabelTwo32:'',
      tableLabelTwo33:'',
      tableLabelTwo34:'',
      tableLabelTwo35:'',
      tableLabelTwo36:'',
      tableLabelTwo41:'',
      tableLabelTwo42:'',
      tableLabelTwo43:'',
      tableLabelTwo44:'',
      tableLabelTwo51:'',
      tableLabelTwo61:'',
      tableLabelTwo62:'',
      tableLabelTwo63:'',
      tableLabelTwo64:'',
      tableLabelTwo65:'',
      tableLabelTwo71:'',
      tableLabelTwo72:'',
      tableLabelTwo73:'',
      tableLabelTwo74:'',
      tableLabelTwo81:'',
      tableLabelTwo82:'',
      tableLabelTwo93:'',
      tableLabelTwo94:'',
      tableLabelTwo95:'',
      tableLabelTwo96:'',
      tableLabelTwo97:'',
      tableLabelTwo101:'',
      tableLabelTwo102:'',
      //表数据
      //一季稻
      tableData1:[],
      tableData12:[],
      tableData31:[],
      tableData32:[],
      tableData33:[],
      tableData41:[],
      tableData42:[],
      tableData43:[],
      tableData44:[],
      tableData51:[],
      tableData61:[],
      tableData62:[],
      tableData63:[],
      tableData64:[],
      tableData65:[],
      tableData71:[],
      tableData72:[],
      tableData73:[],
      tableData81:[],
      tableData93:[],
      tableData94:[],
      tableData95:[],
      tableData96:[],
      tableData97:[],
      tableData101:[],
      //双季稻
      tableDataTwo11:[],
      tableDataTwo12:[],
      tableDataTwo31:[],
      tableDataTwo32:[],
      tableDataTwo33:[],
      tableDataTwo34:[],
      tableDataTwo35:[],
      tableDataTwo36:[],
      tableDataTwo41:[],
      tableDataTwo42:[],
      tableDataTwo43:[],
      tableDataTwo44:[],
      tableDataTwo51:[],
      tableDataTwo61:[],
      tableDataTwo62:[],
      tableDataTwo63:[],
      tableDataTwo64:[],
      tableDataTwo65:[],
      tableDataTwo66:[],
      tableDataTwo71:[],
      tableDataTwo72:[],
      tableDataTwo73:[],
      tableDataTwo74:[],
      tableDataTwo81:[],
      tableDataTwo82:[],
      tableDataTwo93:[],
      tableDataTwo94:[],
      tableDataTwo95:[],
      tableDataTwo96:[],
      tableDataTwo97:[],
      tableDataTwo101:[],
      tableDataTwo102:[],
      //时间搜索
      month:'',
      months:[{value: '01',label: '1月'},
              {value: '02',label: '2月'},
              {value: '03',label: '3月'},
              {value: '04',label: '4月'},
              {value: '05',label: '5月'},
              {value: '06',label: '6月'},
              {value: '07',label: '7月'},
              {value: '08',label: '8月'},
              {value: '09',label: '9月'},
              {value: '10',label: '10月'},
              {value: '11',label: '11月'},
              {value: '12',label: '12月'}],
      //审核搜索
      //一季稻
      countyName1:'',
      countyName12:'',
      //双季稻
      countyName2:'',
      countyNameTwo12:'',
      //退回原因对话框
      //一季稻
      dialogVisible1:false,
      dialogVisible12:false,
      dialogVisible31:false,
      dialogVisible32:false,
      dialogVisible33:false,
      dialogVisible41:false,
      dialogVisible42:false,
      dialogVisible43:false,
      dialogVisible44:false,
      dialogVisible51:false,
      dialogVisible61:false,
      dialogVisible62:false,
      dialogVisible63:false,
      dialogVisible64:false,
      dialogVisible65:false,
      dialogVisible71:false,
      dialogVisible72:false,
      dialogVisible73:false,
      dialogVisible81:false,
      dialogVisible93:false,
      dialogVisible94:false,
      dialogVisible95:false,
      dialogVisible96:false,
      dialogVisible97:false,
      dialogVisible101:false,
      //双季稻
      dialogVisibleTwo11:false,
      dialogVisibleTwo12:false,
      dialogVisibleTwo31:false,
      dialogVisibleTwo32:false,
      dialogVisibleTwo33:false,
      dialogVisibleTwo34:false,
      dialogVisibleTwo35:false,
      dialogVisibleTwo36:false,
      dialogVisibleTwo41:false,
      dialogVisibleTwo42:false,
      dialogVisibleTwo43:false,
      dialogVisibleTwo44:false,
      dialogVisibleTwo51:false,
      dialogVisibleTwo61:false,
      dialogVisibleTwo62:false,
      dialogVisibleTwo63:false,
      dialogVisibleTwo64:false,
      dialogVisibleTwo65:false,
      dialogVisibleTwo71:false,
      dialogVisibleTwo72:false,
      dialogVisibleTwo73:false,
      dialogVisibleTwo74:false,
      dialogVisibleTwo81:false,
      dialogVisibleTwo82:false,
      dialogVisibleTwo93:false,
      dialogVisibleTwo94:false,
      dialogVisibleTwo95:false,
      dialogVisibleTwo96:false,
      dialogVisibleTwo97:false,
      dialogVisibleTwo101:false,
      dialogVisibleTwo102:false,
      //popover对话框
      //通过
      visiblePass1:false,
      visiblePass12:false,
      visiblePass31:false,
      visiblePass32:false,
      visiblePass33:false,
      visiblePass41:false,
      visiblePass42:false,
      visiblePass43:false,
      visiblePass44:false,
      visiblePass51:false,
      visiblePass61:false,
      visiblePass62:false,
      visiblePass63:false,
      visiblePass64:false,
      visiblePass65:false,
      visiblePass71:false,
      visiblePass72:false,
      visiblePass73:false,
      visiblePass81:false,
      visiblePass93:false,
      visiblePass94:false,
      visiblePass95:false,
      visiblePass96:false,
      visiblePass97:false,
      visiblePass101:false,
      //退回
      //一季稻
      visibleBack1:false,
      visibleBack12:false,
      visibleBack31:false,
      visibleBack32:false,
      visibleBack33:false,
      visibleBack41:false,
      visibleBack42:false,
      visibleBack43:false,
      visibleBack44:false,
      visibleBack51:false,
      visibleBack61:false,
      visibleBack62:false,
      visibleBack63:false,
      visibleBack64:false,
      visibleBack65:false,
      visibleBack71:false,
      visibleBack72:false,
      visibleBack73:false,
      visibleBack81:false,
      visibleBack93:false,
      visibleBack94:false,
      visibleBack95:false,
      visibleBack96:false,
      visibleBack97:false,
      visibleBack101:false,
      //双季稻
      visibleBackTwo11:false,
      visibleBackTwo12:false,
      visibleBackTwo31:false,
      visibleBackTwo32:false,
      visibleBackTwo33:false,
      visibleBackTwo34:false,
      visibleBackTwo35:false,
      visibleBackTwo36:false,
      visibleBackTwo41:false,
      visibleBackTwo42:false,
      visibleBackTwo43:false,
      visibleBackTwo44:false,
      visibleBackTwo51:false,
      visibleBackTwo61:false,
      visibleBackTwo62:false,
      visibleBackTwo63:false,
      visibleBackTwo64:false,
      visibleBackTwo65:false,
      visibleBackTwo66:false,
      visibleBackTwo71:false,
      visibleBackTwo72:false,
      visibleBackTwo73:false,
      visibleBackTwo74:false,
      visibleBackTwo81:false,
      visibleBackTwo82:false,
      visibleBackTwo93:false,
      visibleBackTwo94:false,
      visibleBackTwo95:false,
      visibleBackTwo96:false,
      visibleBackTwo97:false,
      visibleBackTwo101:false,
      visibleBackTwo102:false,
      //双季稻
      //审核时暂存整条记录
      //一季稻
      recordData:'',
      //双季稻
      recordData2:'',
      //一季稻退回原因
      reason1:'',
      //双季稻退回原因
      reason2:'',
      //分页
      //一季稻
      pagesize1:15, //每页的数据条数
      pagesize12:15,
      pagesize31:15, 
      pagesize32:15,
      pagesize33:15,  
      pagesize41:15, 
      pagesize42:15,
      pagesize43:15,  
      pagesize44:15, 
      pagesize51:15,
      pagesize61:15,  
      pagesize62:15, 
      pagesize63:15,
      pagesize64:15,  
      pagesize65:15, 
      pagesize71:15,
      pagesize72:15,  
      pagesize73:15, 
      pagesize81:15,
      pagesize93:15,  
      pagesize94:15, 
      pagesize95:15,
      pagesize96:15,  
      pagesize97:15, 
      pagesize101:15,
      //默认开始页面
      currentPage1:1,
      currentPage12:1,
      currentPage31:1,
      currentPage32:1,
      currentPage33:1,
      currentPage41:1,
      currentPage42:1,
      currentPage43:1,
      currentPage44:1,
      currentPage51:1,
      currentPage61:1,
      currentPage62:1,
      currentPage63:1,
      currentPage64:1,
      currentPage65:1,
      currentPage71:1,
      currentPage72:1,
      currentPage73:1,
      currentPage81:1,
      currentPage93:1,
      currentPage94:1,
      currentPage95:1,
      currentPage96:1,
      currentPage97:1,
      currentPage101:1,
      //数据个数
      dataNum1:0,
      dataNum12:0,
      dataNum31:0,
      dataNum32:0,
      dataNum33:0,
      dataNum41:0,
      dataNum42:0,
      dataNum43:0,
      dataNum44:0,
      dataNum51:0,
      dataNum61:0,
      dataNum62:0,
      dataNum63:0,
      dataNum64:0,
      dataNum65:0,
      dataNum71:0,
      dataNum72:0,
      dataNum73:0,
      dataNum81:0,
      dataNum93:0,
      dataNum94:0,
      dataNum95:0,
      dataNum96:0,
      dataNum97:0,
      dataNum101:0,
      //双季稻
      pagesizeTwo11:15,  //每页的数据条数
      pagesizeTwo12:15, 
      pagesizeTwo31:15, 
      pagesizeTwo32:15, 
      pagesizeTwo33:15, 
      pagesizeTwo34:15, 
      pagesizeTwo35:15, 
      pagesizeTwo36:15, 
      pagesizeTwo41:15, 
      pagesizeTwo42:15, 
      pagesizeTwo43:15, 
      pagesizeTwo44:15, 
      pagesizeTwo51:15, 
      pagesizeTwo61:15, 
      pagesizeTwo62:15, 
      pagesizeTwo63:15, 
      pagesizeTwo64:15, 
      pagesizeTwo65:15, 
      pagesizeTwo71:15, 
      pagesizeTwo72:15, 
      pagesizeTwo73:15, 
      pagesizeTwo74:15, 
      pagesizeTwo81:15, 
      pagesizeTwo82:15, 
      pagesizeTwo93:15, 
      pagesizeTwo94:15, 
      pagesizeTwo95:15, 
      pagesizeTwo96:15, 
      pagesizeTwo97:15, 
      pagesizeTwo101:15, 
      pagesizeTwo102:15, 
      //默认开始页面
      currentPageTwo11:1,
      currentPageTwo12:1,
      currentPageTwo31:1,
      currentPageTwo32:1,
      currentPageTwo33:1,
      currentPageTwo34:1,
      currentPageTwo35:1,
      currentPageTwo36:1,
      currentPageTwo41:1,
      currentPageTwo42:1,
      currentPageTwo43:1,
      currentPageTwo44:1,
      currentPageTwo51:1,
      currentPageTwo61:1,
      currentPageTwo62:1,
      currentPageTwo63:1,
      currentPageTwo64:1,
      currentPageTwo65:1,
      currentPageTwo66:1,
      currentPageTwo71:1,
      currentPageTwo72:1,
      currentPageTwo73:1,
      currentPageTwo74:1,
      currentPageTwo81:1,
      currentPageTwo82:1,
      currentPageTwo93:1,
      currentPageTwo94:1,
      currentPageTwo95:1,
      currentPageTwo96:1,
      currentPageTwo97:1,
      currentPageTwo101:1,
      currentPageTwo102:1,
      //数据个数
      dataNumTwo11:0,
      dataNumTwo12:0,
      dataNumTwo31:0,
      dataNumTwo32:0,
      dataNumTwo33:0,
      dataNumTwo34:0,
      dataNumTwo35:0,
      dataNumTwo36:0,
      dataNumTwo41:0,
      dataNumTwo42:0,
      dataNumTwo43:0,
      dataNumTwo44:0,
      dataNumTwo51:0,
      dataNumTwo61:0,
      dataNumTwo62:0,
      dataNumTwo63:0,
      dataNumTwo64:0,
      dataNumTwo65:0,
      dataNumTwo71:0,
      dataNumTwo72:0,
      dataNumTwo73:0,
      dataNumTwo74:0,
      dataNumTwo81:0,
      dataNumTwo82:0,
      dataNumTwo93:0,
      dataNumTwo94:0,
      dataNumTwo95:0,
      dataNumTwo96:0,
      dataNumTwo97:0,
      dataNumTwo101:0,
      dataNumTwo102:0,
      
      //暂存表1-2品种
      varietyInfo:[],
      varietyInfo2:[],
      //退回记录显示
      tableBackData:[],
      params1:'',//搜索条件1
      params2:'',//搜索条件2
      //退回记录分页
      pagesize:15,  //每页的数据条数
      currentPage:1,//默认开始页面
      dataNum:0,//数据个数
      
    };
  },
  created() {
    this.initData();
  },
  methods: {
    //初始化数据
    initData() {
      this.getMyYear();
      //一季稻
      this.tableLabel1= this.systemYear+"年度监测县(市、区)水稻生产栽插概况调查表";
      this.tableLabel12= this.systemYear+"年度监测县(市、区)水稻生产主推品种调查表";
      this.tableLabel31= this.systemYear+"年度监测县(市、区)水稻播栽进度调查表";
      this.tableLabel32= this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（移栽稻）";
      this.tableLabel33= this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（直播稻）";
      this.tableLabel41= this.systemYear+"年度监测县(市、区)中稻生育进度调查表（移栽稻）";
      this.tableLabel42= this.systemYear+"年度监测县(市、区)直播稻生育进度调查表（直播稻）";
      this.tableLabel43= this.systemYear+"年度水稻监测点茎蘖数叶面积构成表";
      this.tableLabel44= this.systemYear+"年度水稻监测点苗情生育期统计表";
      this.tableLabel51= this.systemYear+"年度叶片含氮测定仪导出数据表";
      this.tableLabel61= this.systemYear+"年度水稻监测点水分管理记录表";
      this.tableLabel62= this.systemYear+"年度水稻监测点化除管理记录表";
      this.tableLabel63= this.systemYear+"年度水稻监测点病虫害防治记录表";
      this.tableLabel64= this.systemYear+"年度水稻监测点施肥管理记录表";
      this.tableLabel65= this.systemYear+"年度水稻监测点化学调节剂使用记录表";
      this.tableLabel71= this.systemYear+"年度监测县(市、区)水稻单季稻苗情监测预测产表";
      this.tableLabel72= this.systemYear+"年度监测县(市、区)水稻单季稻收获进度调查表";
      this.tableLabel73= this.systemYear+"年度监测县(市、区)水稻单季稻测产表";
      this.tableLabel81= this.systemYear+"年度监测县(市、区)水稻生产成本效益统计表";
      this.tableLabel93= this.systemYear+"年度监测县(市、区)水稻倒伏灾害表";
      this.tableLabel94= this.systemYear+"年度监测县(市、区)水稻低温冷害表";
      this.tableLabel95= this.systemYear+"年度监测县(市、区)水稻高温热害表";
      this.tableLabel96= this.systemYear+"年度监测县(市、区)水稻涝害调查表";
      this.tableLabel97= this.systemYear+"年度水稻监测点洪涝灾害表";
      this.tableLabel101= this.systemYear+"年度水稻监测点灌浆速率调查表";
      //双季稻
      this.tableLabelTwo11 = this.systemYear+"年度监测县(市、区)水稻栽插概况调查表";
      this.tableLabelTwo12 = this.systemYear+"年度监测县(市、区)水稻生产主推品种调查表";
      this.tableLabelTwo31 = this.systemYear+"年度监测县(市、区)水稻播栽进度调查表（早稻）";
      this.tableLabelTwo32 = this.systemYear+"年度监测县(市、区)水稻播栽进度调查表（晚稻）";
      this.tableLabelTwo33 = this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（移栽早稻）";
      this.tableLabelTwo34 = this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（直播早稻）";
      this.tableLabelTwo35 = this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（移栽晚稻）";
      this.tableLabelTwo36 = this.systemYear+"年度监测县(市、区)水稻苗情评价动态列表（直播晚稻）";
      this.tableLabelTwo41 = this.systemYear+"年度监测县(市、区)双季稻生育进度调查表（移栽稻）";
      this.tableLabelTwo42 = this.systemYear+"年度监测县(市、区)双季稻生育进度调查表（直播稻）";
      this.tableLabelTwo43 = this.systemYear+"年度水稻监测点茎蘖数叶面积构成表";
      this.tableLabelTwo44 = this.systemYear+"年度水稻监测点苗情生育期统计表";
      this.tableLabelTwo51 = this.systemYear+"年度叶片含氮测定仪导出数据表";
      this.tableLabelTwo61 = this.systemYear+"年度水稻监测点水分管理记录表";
      this.tableLabelTwo62 = this.systemYear+"年度水稻监测点化除管理记录表";
      this.tableLabelTwo63 = this.systemYear+"年度水稻监测点病虫害防治记录表";
      this.tableLabelTwo64 = this.systemYear+"年度水稻监测点施肥管理记录表";
      this.tableLabelTwo65 = this.systemYear+"年度水稻监测点化学调节剂使用记录表";
      this.tableLabelTwo71 = this.systemYear+"年度监测县(市、区)水稻双季稻苗情监测预测产表";
      this.tableLabelTwo72 = this.systemYear+"年度监测县(市、区)水稻双季稻收获进度调查（早稻）";
      this.tableLabelTwo73 = this.systemYear+"年度监测县(市、区)水稻双季稻收获进度调查（晚稻）";
      this.tableLabelTwo74 = this.systemYear+"年度监测县(市、区)水稻双季稻测产表";
      this.tableLabelTwo81 = this.systemYear+"年度监测县(市、区)水稻生产成本效益统计表（早稻）";
      this.tableLabelTwo82 = this.systemYear+"年度监测县(市、区)水稻生产成本效益统计表（晚稻）";
      this.tableLabelTwo93 = this.systemYear+"年度监测县(市、区)水稻倒伏灾害表";
      this.tableLabelTwo94 = this.systemYear+"年度监测县(市、区)水稻低温冷害表";
      this.tableLabelTwo95 = this.systemYear+"年度监测县(市、区)高温热害表";
      this.tableLabelTwo96 = this.systemYear+"年度监测县(市、区)水稻涝害调查表";
      this.tableLabelTwo97 = this.systemYear+"年度水稻监测点洪涝灾害表";
      this.tableLabelTwo101 = this.systemYear+"年度水稻监测点灌浆速率调查表（早稻）";
      this.tableLabelTwo102 = this.systemYear+"年度水稻监测点灌浆速率调查表（晚稻）";
      this.getDataNumber();
      this.getDataNumber2();
      this.getRiceData1();
      this.getRiceDataTwo11();
      this.getDataBackListInfo();
    }, 
    
    //获取年度时间
    getMyYear(){
      var myDate = new Date();
      this.systemYear = myDate.getFullYear();
      ////console.log(this.systemYear);
    }, 
    //获取时间
    //获取系统时间
    getMyTime(){
      var myDate = new Date();
      ////console.log(myDate);
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
      ////console.log(myTime);
      this.systemTime = myTime;
    }, 
    //表头
    headerStyle({row,column,rowIndex,columnIndex}){
     // ////console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 2){ 
           return {height:'0',padding:'0px',fontSize:'2px', display:"none"};
        };
        // if(rowIndex === 0){
        //   return{height:'0',padding:'0px',fontSize:'2px', background:'#6E8B3D',color:'#FFFFFF',}
        // }
        if(rowIndex==0 || rowIndex==1){
            return{height:'0',padding:'0px',fontSize:'2px',background:'#CDBE70',color:'green'};
        };
           
    },
    headerStyle81({row,column,rowIndex,columnIndex}){
     // ////console.log('row:',row,'column:', column, 'rowIndex:',rowIndex, 'columnIndex:',columnIndex );
        if(rowIndex === 3){ 
           return {height:'0',padding:'0px',fontSize:'2px', display:"none"};
        };
        // if(rowIndex === 0){
        //   return{height:'0',padding:'0px',fontSize:'2px', background:'#6E8B3D',color:'#FFFFFF',}
        // }
        if(rowIndex==0 || rowIndex==1 || rowIndex == 2){
            return{height:'0',padding:'0px',fontSize:'2px',background:'#CDBE70',color:'green'};
        };
           
    },
    headerStyle3({row,column,rowIndex,columnIndex}){
         if(rowIndex === 0){ 
           return {background:'#6E8B3D',color:'#FFFFFF',fontSize:'19px',height:'45px',padding:'1px'};
        }else{
            return{background:'#CDBE70',color:'green',fontSize:'16px',height:'20px',padding:'1px'}
        };
    },
    //显示表数据个数
    getDataNumber(){
      const _this = this;
      axios({
        url:'/getDataNumber',
        method:'get',
      }).then(res=>{
          _this.pointData = res.data;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getDataNumber2(){
      const _this = this;
      axios({
        url:'/getDataNumber2',
        method:'get',
      }).then(res=>{
          _this.pointData2 = res.data;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    
    getTotalNumber(){
      var num = 0;
      for(var i=0;i<this.pointData.length;i++){
        num = num + this.pointData[i];
      };
      this.dataTotal = num;
      console.log(this.dataTotal);
    },
    getTotalNumber2(){
      var num = 0;
      for(var i=0;i<this.pointData2.length;i++){
        num = num + this.pointData2[i];
      };
      this.dataTotal2 = num;
      console.log(this.dataTotal2);
    },

    //切换tabs
    handleChangeTable(tab,event){
      ////console.log(tab,event);
      switch(tab.name){
                case '1':
                  this.getRiceData1();
                   break;
                case '2':
                  this.getRiceData12();
                   break;
                case '3':
                  this.getRiceData31();
                   break;
                case '4':
                  this.getRiceData32();
                   break;
                case '5':
                  this.getRiceData33();
                   break;
                case '6':
                  this.getRiceData41();
                   break;
                case '7':
                  this.getRiceData42();
                   break;
                case '8':
                  this.getRiceData43();
                   break;
                case '9':
                  this.getRiceData44();
                   break;
                case '10':
                  this.getRiceData51();
                   break;
                case '11':
                  this.getRiceData61();
                   break;
                case '12':
                  this.getRiceData62();
                   break;
                case '13':
                  this.getRiceData63();
                   break;
                case '14':
                  this.getRiceData64();
                   break;
                case '15':
                  this.getRiceData65();
                   break;
                case '16':
                  this.getRiceData71();
                   break;
                case '17':
                  this.getRiceData72();
                   break;
                case '18':
                  this.getRiceData73();
                   break;
                case '19':
                  this.getRiceData81();
                   break;
                case '20':
                  this.getRiceData93();
                   break;
                case '21':
                  this.getRiceData94();
                   break;
                case '22':
                  this.getRiceData95();
                   break;
                case '23':
                  this.getRiceData96();
                   break;
                case '24':
                  this.getRiceData97();
                   break;
                case '25':
                  this.getRiceData101();
                   break;
            }
    },
    //切换tabs
    handleChangeTableTwo(tab,event){
      //console.log("双季稻",tab,event);
      switch(tab.name){
                case '1':
                  this.getRiceDataTwo11();
                   break;
                case '2':
                  this.getRiceDataTwo12();
                   break;
                case '3':
                  this.getRiceDataTwo31();
                   break;
                case '4':
                  this.getRiceDataTwo32();
                   break;
                case '5':
                  this.getRiceDataTwo33();
                   break;
                case '6':
                  this.getRiceDataTwo34();
                   break;
                case '7':
                  this.getRiceDataTwo35();
                   break;
                case '8':
                  this.getRiceDataTwo36();
                   break;
                case '9':
                  this.getRiceDataTwo41();
                   break;
                case '10':
                  this.getRiceDataTwo42();
                   break;
                case '11':
                  this.getRiceDataTwo43();
                   break;
                case '12':
                  this.getRiceDataTwo44();
                   break;
                case '13':
                  this.getRiceDataTwo51();
                   break;
                case '14':
                  this.getRiceDataTwo61();
                   break;
                case '15':
                  this.getRiceDataTwo62();
                   break;
                case '16':
                  this.getRiceDataTwo63();
                   break;
                case '17':
                  this.getRiceDataTwo64();
                   break;
                case '18':
                  this.getRiceDataTwo65();
                   break;
                case '19':
                  this.getRiceDataTwo71();
                   break;
                case '20':
                  this.getRiceDataTwo72();
                   break;
                case '21':
                  this.getRiceDataTwo73();
                   break;
                case '22':
                  this.getRiceDataTwo74();
                   break;
                case '23':
                  this.getRiceDataTwo81();
                   break;
                case '24':
                  this.getRiceDataTwo82();
                   break;
                case '25':
                  this.getRiceDataTwo93();
                   break;
                case '26':
                  this.getRiceDataTwo94();
                   break;
                case '27':
                  this.getRiceDataTwo95();
                   break;
                case '28':
                  this.getRiceDataTwo96();
                   break;
                case '29':
                  this.getRiceDataTwo97();
                   break;
                case '30':
                  this.getRiceDataTwo101();
                   break;
                case '31':
                  this.getRiceDataTwo102();
                   break;
            }
    },

    //获取一季稻数据
    //表1-1
    getRiceData1(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_01_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage1,
                pageSize:this.pagesize1
        }
      }).then(res=>{
          _this.tableData1 = res.data.lists;
          _this.dataNum1 = res.data.totalCount;
          _this.pointData[0] = res.data.totalCount;
          _this.getTotalNumber();
          ////console.log(_this.tableData1);
          ////console.log(_this.dataNum1);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData12(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_01_02";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceTableAudit12',
        method:'post',
        params:{params1:noParams,
                start:this.currentPage12,
                pageSize:this.pagesize12
        }
      }).then(res=>{
          _this.tableData12 = res.data;
          _this.dataNum12 = _this.tableData12.length;
          _this.pointData[1] = _this.tableData12.length;
          _this.getTotalNumber();
          console.log(_this.tableData12);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData31(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_03_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage31,
                pageSize:this.pagesize31
        }
      }).then(res=>{
            _this.tableData31 = res.data.lists;
            _this.dataNum31 = res.data.totalCount;
          _this.pointData[2] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData31);
            ////console.log(_this.dataNum31);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData32(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_03_02";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage31,
                pageSize:this.pagesize31
        }
      }).then(res=>{
            _this.tableData32 = res.data.lists;
            _this.dataNum32 = res.data.totalCount;
          _this.pointData[3] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData31);
            ////console.log(_this.dataNum31);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData33(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_03_03";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage33,
                pageSize:this.pagesize33
        }
      }).then(res=>{
            _this.tableData33 = res.data.lists;
            _this.dataNum33 = res.data.totalCount;
            _this.pointData[4] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData33);
            ////console.log(_this.dataNum33);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData41(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_04_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage41,
                pageSize:this.pagesize41
        }
      }).then(res=>{
            _this.tableData41 = res.data.lists;
            _this.dataNum41 = res.data.totalCount;
          _this.pointData[5] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData42(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_04_02";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage42,
                pageSize:this.pagesize42
        }
      }).then(res=>{
            _this.tableData42 = res.data.lists;
            _this.dataNum42 = res.data.totalCount;
          _this.pointData[6] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData43(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_04_03";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage43,
                pageSize:this.pagesize43
        }
      }).then(res=>{
            _this.tableData43 = res.data.lists;
            _this.dataNum43 = res.data.totalCount;
          _this.pointData[7] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData44(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_04_04";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage44,
                pageSize:this.pagesize44
        }
      }).then(res=>{
            console.log(res.data)
            _this.tableData44 = res.data.lists;
            _this.dataNum44 = res.data.totalCount;
          _this.pointData[8] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData51(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_05_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage51,
                pageSize:this.pagesize51
        }
      }).then(res=>{
            _this.tableData51 = res.data.lists;
            _this.dataNum51 = res.data.totalCount;
          _this.pointData[9] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData61(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_06_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage61,
                pageSize:this.pagesize61
        }
      }).then(res=>{
            _this.tableData61 = res.data.lists;
            _this.dataNum61 = res.data.totalCount;
          _this.pointData[10] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData62(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_06_02";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage62,
                pageSize:this.pagesize62
        }
      }).then(res=>{
            _this.tableData62 = res.data.lists;
            _this.dataNum62 = res.data.totalCount;
          _this.pointData[11] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData63(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_06_03";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage63,
                pageSize:this.pagesize63
        }
      }).then(res=>{
            _this.tableData63 = res.data.lists;
            _this.dataNum63 = res.data.totalCount;
          _this.pointData[12] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData64(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_06_04";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage64,
                pageSize:this.pagesize64
        }
      }).then(res=>{
            _this.tableData64 = res.data.lists;
            _this.dataNum64 = res.data.totalCount;
          _this.pointData[13] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData65(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_06_05";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage65,
                pageSize:this.pagesize65
        }
      }).then(res=>{
            _this.tableData65 = res.data.lists;
            _this.dataNum65 = res.data.totalCount;
          _this.pointData[14] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData71(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_07_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage71,
                pageSize:this.pagesize71
        }
      }).then(res=>{
            _this.tableData71 = res.data.lists;
            _this.dataNum71 = res.data.totalCount;
          _this.pointData[15] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData72(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_07_02";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage72,
                pageSize:this.pagesize72
        }
      }).then(res=>{
            _this.tableData72 = res.data.lists;
            _this.dataNum72 = res.data.totalCount;
          _this.pointData[16] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData73(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_07_03";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage73,
                pageSize:this.pagesize73
        }
      }).then(res=>{
            _this.tableData73 = res.data.lists;
            _this.dataNum73 = res.data.totalCount;
          _this.pointData[17] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData81(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_08_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage81,
                pageSize:this.pagesize81
        }
      }).then(res=>{
            _this.tableData81 = res.data.lists;
            _this.dataNum81 = res.data.totalCount;
          _this.pointData[18] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData93(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_09_03";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage93,
                pageSize:this.pagesize93
        }
      }).then(res=>{
            _this.tableData93 = res.data.lists;
            _this.dataNum93 = res.data.totalCount;
          _this.pointData[19] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData94(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_09_04";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage94,
                pageSize:this.pagesize94
        }
      }).then(res=>{
            _this.tableData94 = res.data.lists;
            _this.dataNum94 = res.data.totalCount;
          _this.pointData[20] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData95(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_09_05";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage95,
                pageSize:this.pagesize95
        }
      }).then(res=>{
            _this.tableData95 = res.data.lists;
            _this.dataNum95 = res.data.totalCount;
          _this.pointData[21] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData96(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_09_06";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage96,
                pageSize:this.pagesize96
        }
      }).then(res=>{
            _this.tableData96 = res.data.lists;
            _this.dataNum96 = res.data.totalCount;
          _this.pointData[22] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData97(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_09_07";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage97,
                pageSize:this.pagesize97
        }
      }).then(res=>{
            _this.tableData97 = res.data.lists;
            _this.dataNum97 = res.data.totalCount;
          _this.pointData[23] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceData101(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_01_table_10_01";
      this.tableEngishName=riceName;
      axios({
        url:'/getRiceAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPage101,
                pageSize:this.pagesize101
        }
      }).then(res=>{
            _this.tableData101 = res.data.lists;
            _this.dataNum101 = res.data.totalCount;
          _this.pointData[24] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    
    
    //搜索
    searchInfo1(){
      const _this = this;
      ////console.log("1111111");
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage1,
                pageSize:this.pagesize1
        }
      }).then(res=>{
            _this.tableData1 = res.data.lists;
            _this.dataNum1 = res.data.totalCount;
          _this.pointData[0] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData1);
            ////console.log(_this.dataNum1);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo12(){
      const _this = this;
      axios({
        url:'/searchRiceAudit12',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage12,
                pageSize:this.pagesize12
        }
      }).then(res=>{
            _this.tableData12 = res.data;
            _this.dataNum12 = _this.tableData12.length;
          _this.pointData[1] = _this.dataNum12;
          _this.getTotalNumber();
            ////console.log(_this.tableData12);
            ////console.log(_this.dataNum12);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo31(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage31,
                pageSize:this.pagesize31
        }
      }).then(res=>{
            _this.tableData31 = res.data.lists;
            _this.dataNum31 = res.data.totalCount;
          _this.pointData[2] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData31);
            ////console.log(_this.dataNum31);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo32(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage31,
                pageSize:this.pagesize31
        }
      }).then(res=>{
            _this.tableData32 = res.data.lists;
            _this.dataNum32 = res.data.totalCount;
          _this.pointData[3] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData32);
            ////console.log(_this.dataNum32);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo33(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage33,
                pageSize:this.pagesize33
        }
      }).then(res=>{
            _this.tableData33 = res.data.lists;
            _this.dataNum33 = res.data.totalCount;
          _this.pointData[4] = res.data.totalCount;
          _this.getTotalNumber();
            ////console.log(_this.tableData33);
            ////console.log(_this.dataNum33);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo41(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage41,
                pageSize:this.pagesize41
        }
      }).then(res=>{
            _this.tableData41 = res.data.lists;
            _this.dataNum41 = res.data.totalCount;
          _this.pointData[5] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo42(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage42,
                pageSize:this.pagesize42
        }
      }).then(res=>{
            _this.tableData42 = res.data.lists;
            _this.dataNum42 = res.data.totalCount;
          _this.pointData[6] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo43(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage43,
                pageSize:this.pagesize43
        }
      }).then(res=>{
            _this.tableData43 = res.data.lists;
            _this.dataNum43 = res.data.totalCount;
          _this.pointData[7] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo44(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage44,
                pageSize:this.pagesize44
        }
      }).then(res=>{
            _this.tableData44 = res.data.lists;
            _this.dataNum44 = res.data.totalCount;
          _this.pointData[8] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo51(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage51,
                pageSize:this.pagesize51
        }
      }).then(res=>{
            _this.tableData51 = res.data.lists;
            _this.dataNum51 = res.data.totalCount;
          _this.pointData[9] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo61(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage61,
                pageSize:this.pagesize61
        }
      }).then(res=>{
            _this.tableData61 = res.data.lists;
            _this.dataNum61 = res.data.totalCount;
          _this.pointData[10] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo62(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage62,
                pageSize:this.pagesize62
        }
      }).then(res=>{
            _this.tableData62 = res.data.lists;
            _this.dataNum62 = res.data.totalCount;
          _this.pointData[11] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo63(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage63,
                pageSize:this.pagesize63
        }
      }).then(res=>{
            _this.tableData63 = res.data.lists;
            _this.dataNum63 = res.data.totalCount;
          _this.pointData[12] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo64(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage64,
                pageSize:this.pagesize64
        }
      }).then(res=>{
            _this.tableData64 = res.data.lists;
            _this.dataNum64 = res.data.totalCount;
          _this.pointData[13] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo65(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage65,
                pageSize:this.pagesize65
        }
      }).then(res=>{
            _this.tableData65 = res.data.lists;
            _this.dataNum65 = res.data.totalCount;
          _this.pointData[14] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo71(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage71,
                pageSize:this.pagesize71
        }
      }).then(res=>{
            _this.tableData71 = res.data.lists;
            _this.dataNum71 = res.data.totalCount;
          _this.pointData[15] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo72(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage72,
                pageSize:this.pagesize72
        }
      }).then(res=>{
            _this.tableData72 = res.data.lists;
            _this.dataNum72 = res.data.totalCount;
          _this.pointData[16] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo73(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage73,
                pageSize:this.pagesize73
        }
      }).then(res=>{
            _this.tableData73 = res.data.lists;
            _this.dataNum73 = res.data.totalCount;
          _this.pointData[17] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo81(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage81,
                pageSize:this.pagesize81
        }
      }).then(res=>{
            _this.tableData81 = res.data.lists;
            _this.dataNum81 = res.data.totalCount;
          _this.pointData[18] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo93(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage93,
                pageSize:this.pagesize93
        }
      }).then(res=>{
            _this.tableData93 = res.data.lists;
            _this.dataNum93 = res.data.totalCount;
          _this.pointData[19] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo94(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage94,
                pageSize:this.pagesize94
        }
      }).then(res=>{
            _this.tableData94 = res.data.lists;
            _this.dataNum94 = res.data.totalCount;
          _this.pointData[20] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo95(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage95,
                pageSize:this.pagesize95
        }
      }).then(res=>{
            _this.tableData95 = res.data.lists;
            _this.dataNum95 = res.data.totalCount;
          _this.pointData[21] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo96(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage96,
                pageSize:this.pagesize96
        }
      }).then(res=>{
            _this.tableData96 = res.data.lists;
            _this.dataNum96 = res.data.totalCount;
          _this.pointData[22] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo97(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage97,
                pageSize:this.pagesize97
        }
      }).then(res=>{
            _this.tableData97 = res.data.lists;
            _this.dataNum97 = res.data.totalCount;
          _this.pointData[23] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfo101(){
      const _this = this;
      axios({
        url:'/searchRiceAudit1',
        method:'post',
        params:{params1:this.countyName1,
                month:this.month,
                riceTableName:this.tableEngishName,
                start:this.currentPage101,
                pageSize:this.pagesize101
        }
      }).then(res=>{
            _this.tableData101 = res.data.lists;
            _this.dataNum101 = res.data.totalCount;
          _this.pointData[24] = res.data.totalCount;
          _this.getTotalNumber();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },

    //重置
    reSet1(){
      this.countyName1="";
      this.month=""; 
      switch(this.tableEngishName){
        case "crop_01_01_table_01_01":
          this.getRiceData1();
          break;
        case "crop_01_01_table_01_02":
          this.getRiceData12();
          break;
        case "crop_01_01_table_03_01":
          this.getRiceData31();
          break;
        case "crop_01_01_table_03_02":
          this.getRiceData32();
          break;
        case "crop_01_01_table_03_03":
          this.getRiceData33();
          break;
        case "crop_01_01_table_04_01":
          this.getRiceData41();
          break;
        case "crop_01_01_table_04_02":
          this.getRiceData42();
          break;
        case "crop_01_01_table_04_03":
          this.getRiceData43();
          break;
        case "crop_01_01_table_04_04":
          this.getRiceData44();
          break;
        case "crop_01_01_table_05_01":
          this.getRiceData51();
          break;
        case "crop_01_01_table_06_01":
          this.getRiceData61();
          break;
        case "crop_01_01_table_06_02":
          this.getRiceData62();
          break;
        case "crop_01_01_table_06_03":
          this.getRiceData63();
          break;
        case "crop_01_01_table_06_04":
          this.getRiceData64();
          break;
        case "crop_01_01_table_06_05":
          this.getRiceData65();
          break;
        case "crop_01_01_table_07_01":
          this.getRiceData71();
          break;
        case "crop_01_01_table_07_02":
          this.getRiceData72();
          break;
        case "crop_01_01_table_07_03":
          this.getRiceData73();
          break;
        case "crop_01_01_table_08_01":
          this.getRiceData81();
          break;
        case "crop_01_01_table_09_03":
          this.getRiceData93();
          break;
        case "crop_01_01_table_09_04":
          this.getRiceData94();
          break;
        case "crop_01_01_table_09_05":
          this.getRiceData95();
          break;
        case "crop_01_01_table_09_06":
          this.getRiceData96();
          break;
        case "crop_01_01_table_09_07":
          this.getRiceData97();
          break;
        case "crop_01_01_table_10_01":
          this.getRiceData101();
          break;
      };
      // if(this.tableEngishName === "crop_01_01_table_01_01"){
      //   this.getRiceData1();
      // }else if(this.tableEngishName === "crop_01_01_table_03_01"){
      //   this.getRiceData31();
      // }else if(this.tableEngishName === "crop_01_01_table_03_02"){
      //   this.getRiceData32();
      // };
    },

    //通过
    confirmPass(index,row){
      ////console.log(row);
      const _this=this;
      // this.visiblePass1=true;
      // this.visiblePass31=true;
      // this.visiblePass32=true;
      var returnTableName = this.tableEngishName;
      if(returnTableName ==="crop_01_01_table_01_02"){
        var recordId = 1;
        var recordCounty=row.county;
      }else{
        var recordId = row.id;
        var recordCounty=row.field_1;
      }
      ////console.log(returnTableName,recordId,recordCounty);
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId}
      }).then(res=>{
        if(_this.tableEngishName === "crop_01_01_table_01_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo1();
          }else{
           _this.getRiceData1();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_01_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo12();
          }else{
           _this.getRiceData12();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_03_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo31();
          }else{
           _this.getRiceData31();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_03_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo32();
          }else{
           _this.getRiceData32();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_03_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo33();
          }else{
           _this.getRiceData33();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo41();
          }else{
           _this.getRiceData41();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo42();
          }else{
           _this.getRiceData42();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo43();
          }else{
           _this.getRiceData43();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo44();
          }else{
           _this.getRiceData44();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_05_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo51();
          }else{
           _this.getRiceData51();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo61();
          }else{
           _this.getRiceData61();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo62();
          }else{
           _this.getRiceData62();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo63();
          }else{
           _this.getRiceData63();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo64();
          }else{
           _this.getRiceData64();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_05"){
          if(_this.countyName1 !=""){
            _this.searchInfo65();
          }else{
           _this.getRiceData65();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo71();
          }else{
           _this.getRiceData71();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo72();
          }else{
           _this.getRiceData72();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo73();
          }else{
           _this.getRiceData73();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_08_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo81();
          }else{
           _this.getRiceData81();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo93();
          }else{
           _this.getRiceData93();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo94();
          }else{
           _this.getRiceData94();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_05"){
          if(_this.countyName1 !=""){
            _this.searchInfo95();
          }else{
           _this.getRiceData95();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_06"){
          if(_this.countyName1 !=""){
            _this.searchInfo96();
          }else{
           _this.getRiceData96();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_07"){
          if(_this.countyName1 !=""){
            _this.searchInfo97();
          }else{
           _this.getRiceData97();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_10_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo101();
          }else{
           _this.getRiceData101();
          };
        };
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },
    //退回按钮
    backRevision(index,row){
      this.recordData=row;
      switch(this.tableEngishName){
        case "crop_01_01_table_01_01":
          this.dialogVisible1=true;
          break;
        case "crop_01_01_table_01_02":
          this.dialogVisible12=true;
          break;
        case "crop_01_01_table_03_01":
          this.dialogVisible31=true;
          break;
        case "crop_01_01_table_03_02":
          this.dialogVisible32=true;
          break;
        case "crop_01_01_table_03_03":
          this.dialogVisible33=true;
          break;
        case "crop_01_01_table_04_01":
          this.dialogVisible41=true;
          break;
        case "crop_01_01_table_04_02":
          this.dialogVisible42=true;
          break;
        case "crop_01_01_table_04_03":
          this.dialogVisible43=true;
          break;
        case "crop_01_01_table_04_04":
          this.dialogVisible44=true;
          break;
        case "crop_01_01_table_05_01":
          this.dialogVisible51=true;
          break;
        case "crop_01_01_table_06_01":
          this.dialogVisible61=true;
          break;
        case "crop_01_01_table_06_02":
          this.dialogVisible62=true;
          break;
        case "crop_01_01_table_06_03":
          this.dialogVisible63=true;
          break;
        case "crop_01_01_table_06_04":
          this.dialogVisible64=true;
          break;
        case "crop_01_01_table_06_05":
          this.dialogVisible65=true;
          break;
        case "crop_01_01_table_07_01":
          this.dialogVisible71=true;
          break;
        case "crop_01_01_table_07_02":
          this.dialogVisible72=true;
          break;
        case "crop_01_01_table_07_03":
          this.dialogVisible73=true;
          break;
        case "crop_01_01_table_08_01":
          this.dialogVisible81=true;
          break;
        case "crop_01_01_table_09_03":
          this.dialogVisible93=true;
          break;
        case "crop_01_01_table_09_04":
          this.dialogVisible94=true;
          break;
        case "crop_01_01_table_09_05":
          this.dialogVisible95=true;
          break;
        case "crop_01_01_table_09_06":
          this.dialogVisible96=true;
          break;
        case "crop_01_01_table_09_07":
          this.dialogVisible97=true;
          break;
        case "crop_01_01_table_10_01":
          this.dialogVisible101=true;
          break;
        
      };
      
    },
    //确认退回
    confirmBack(){
      const _this=this;
      this.visibleBack1=false;
      this.visibleBack12=false;
      this.visibleBack31=false;
      this.visibleBack32=false;
      this.visibleBack33=false;
      this.visibleBack41=false;
      this.visibleBack42=false;
      this.visibleBack43=false;
      this.visibleBack44=false;
      this.visibleBack51=false;
      this.visibleBack61=false;
      this.visibleBack62=false;
      this.visibleBack63=false;
      this.visibleBack64=false;
      this.visibleBack65=false;
      this.visibleBack71=false;
      this.visibleBack72=false;
      this.visibleBack73=false;
      this.visibleBack81=false;
      this.visibleBack93=false;
      this.visibleBack94=false;
      this.visibleBack95=false;
      this.visibleBack96=false;
      this.visibleBack97=false;
      this.visibleBack101=false;

      this.dialogVisible1=false;
      this.dialogVisible12=false;
      this.dialogVisible31=false;
      this.dialogVisible32=false;
      this.dialogVisible33=false;
      this.dialogVisible41=false;
      this.dialogVisible42=false;
      this.dialogVisible43=false;
      this.dialogVisible44=false;
      this.dialogVisible51=false;
      this.dialogVisible61=false;
      this.dialogVisible62=false;
      this.dialogVisible63=false;
      this.dialogVisible64=false;
      this.dialogVisible65=false;
      this.dialogVisible71=false;
      this.dialogVisible72=false;
      this.dialogVisible73=false;
      this.dialogVisible81=false;
      this.dialogVisible93=false;
      this.dialogVisible94=false;
      this.dialogVisible95=false;
      this.dialogVisible96=false;
      this.dialogVisible97=false;
      this.dialogVisible101=false;
      //console.log(this.recordData);
      this.getMyTime();
      var recordTime = this.systemTime;
      var returnTableName = this.tableEngishName;
      if(returnTableName === "crop_01_01_table_01_02"){
        var recordCounty=this.recordData.county;
        var recordId = 1;
      }else{ 
        var recordCounty=this.recordData.field_1;
        var recordId = this.recordData.id;
      }
      var recordPeople = this.recordData.people;
      var recordReason = this.reason1;
      //将原因显示置空
      this.reason1='';
      ////console.log(recordTime,recordId,returnTableName,recordPeople,recordCounty,recordReason);
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
        if(_this.tableEngishName === "crop_01_01_table_01_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo1();
          }else{
           _this.getRiceData1();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_01_02"){
        if(_this.countyName1 !=""){
          _this.searchInfo12();
        }else{
          _this.getRiceData12();
        };
        }else if(_this.tableEngishName === "crop_01_01_table_03_01"){
        if(_this.countyName1 !=""){
          _this.searchInfo31();
        }else{
          _this.getRiceData31();
        };
        }else if(_this.tableEngishName === "crop_01_01_table_03_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo32();
          }else{
           _this.getRiceData32();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_03_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo33();
          }else{
           _this.getRiceData33();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo41();
          }else{
           _this.getRiceData41();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo42();
          }else{
           _this.getRiceData42();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo43();
          }else{
           _this.getRiceData43();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_04_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo44();
          }else{
           _this.getRiceData44();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_05_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo51();
          }else{
           _this.getRiceData51();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo61();
          }else{
           _this.getRiceData61();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo62();
          }else{
           _this.getRiceData62();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo63();
          }else{
           _this.getRiceData63();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo64();
          }else{
           _this.getRiceData64();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_06_05"){
          if(_this.countyName1 !=""){
            _this.searchInfo65();
          }else{
           _this.getRiceData65();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo71();
          }else{
           _this.getRiceData71();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_02"){
          if(_this.countyName1 !=""){
            _this.searchInfo72();
          }else{
           _this.getRiceData72();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_07_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo73();
          }else{
           _this.getRiceData73();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_08_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo81();
          }else{
           _this.getRiceData81();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_03"){
          if(_this.countyName1 !=""){
            _this.searchInfo93();
          }else{
           _this.getRiceData93();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_04"){
          if(_this.countyName1 !=""){
            _this.searchInfo94();
          }else{
           _this.getRiceData94();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_05"){
          if(_this.countyName1 !=""){
            _this.searchInfo95();
          }else{
           _this.getRiceData95();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_06"){
          if(_this.countyName1 !=""){
            _this.searchInfo96();
          }else{
           _this.getRiceData96();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_09_07"){
          if(_this.countyName1 !=""){
            _this.searchInfo97();
          }else{
           _this.getRiceData97();
          };
        }else if(_this.tableEngishName === "crop_01_01_table_10_01"){
          if(_this.countyName1 !=""){
            _this.searchInfo101();
          }else{
           _this.getRiceData101();
          };
        };
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    toogleExpand(row) {
      ////console.log(row);
      this.varietyInfo = row.variety;
      //////console.log(this.varietyInfo);
      let $table = this.$refs.tableExpand;
      this.tableData12.map((item) => {
          if (row.id != item.id) {
            //////console.log(item);
          $table.toggleRowExpansion(item, false)
        }
      })
      $table.toggleRowExpansion(row)
    },

    
    //获取双季稻数据
    //双季稻点击行展开，查看详情
    toogleExpand2(row) {
      //console.log(row);
      this.varietyInfo2 = row.variety;
      //////console.log(row.varietyInfo2);
      let $table = this.$refs.tableExpand2;
      this.tableDataTwo12.map((item) => {
          if (row.id != item.id) {
          $table.toggleRowExpansion(item, false)
        }
      })
      $table.toggleRowExpansion(row)
    },
    //表1-1
    getRiceDataTwo11(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_01_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo11,
                pageSize:this.pagesizeTwo11
        }
      }).then(res=>{
          _this.tableDataTwo11 = res.data.lists;
          _this.dataNumTwo11 = res.data.totalCount;
          _this.pointData2[0] = res.data.totalCount;
          _this.getTotalNumber2();
          //console.log(_this.tableDataTwo11);
          //_this.getRiceData31();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo12(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_01_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTableAuditTwo12',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo12,
                pageSize:this.pagesizeTwo12
        }
      }).then(res=>{
          _this.tableDataTwo12 = res.data;
          _this.dataNumTwo12 = _this.tableDataTwo12.length;
          _this.pointData2[1] =  _this.dataNumTwo12;
          _this.getTotalNumber2();
          //console.log(_this.tableDataTwo12);
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo31(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo31,
                pageSize:this.pagesizeTwo31
        }
      }).then(res=>{
          _this.tableDataTwo31 = res.data.lists;
          _this.dataNumTwo31 = res.data.totalCount;
          _this.pointData2[2] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo32(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo32,
                pageSize:this.pagesizeTwo32
        }
      }).then(res=>{
          _this.tableDataTwo32 = res.data.lists;
          _this.dataNumTwo32 = res.data.totalCount;
          _this.pointData2[3] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo33(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_03";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo33,
                pageSize:this.pagesizeTwo33
        }
      }).then(res=>{
          _this.tableDataTwo33 = res.data.lists;
          _this.dataNumTwo33 = res.data.totalCount; 
          _this.pointData2[4] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo34(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_04";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo34,
                pageSize:this.pagesizeTwo34
        }
      }).then(res=>{
          _this.tableDataTwo34 = res.data.lists;
          _this.dataNumTwo34 = res.data.totalCount; 
          _this.pointData2[5] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo35(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_05";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo35,
                pageSize:this.pagesizeTwo35
        }
      }).then(res=>{
          _this.tableDataTwo35 = res.data.lists;
          _this.dataNumTwo35 = res.data.totalCount; 
          _this.pointData2[6] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo36(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_03_06";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo36,
                pageSize:this.pagesizeTwo36
        }
      }).then(res=>{
          _this.tableDataTwo36 = res.data.lists;
          _this.dataNumTwo36 = res.data.totalCount; 
          _this.pointData2[7] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo41(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_04_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo41,
                pageSize:this.pagesizeTwo41
        }
      }).then(res=>{
          _this.tableDataTwo41 = res.data.lists;
          _this.dataNumTwo41 = res.data.totalCount;
          _this.pointData2[8] = res.data.totalCount; 
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo42(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_04_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo42,
                pageSize:this.pagesizeTwo42
        }
      }).then(res=>{
          _this.tableDataTwo42 = res.data.lists;
          _this.dataNumTwo42 = res.data.totalCount; 
          _this.pointData2[9] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo43(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_04_03";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo43,
                pageSize:this.pagesizeTwo43
        }
      }).then(res=>{
          _this.tableDataTwo43 = res.data.lists;
          _this.dataNumTwo43 = res.data.totalCount; 
          _this.pointData2[10] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo44(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_04_04";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo44,
                pageSize:this.pagesizeTwo44
        }
      }).then(res=>{
          _this.tableDataTwo44 = res.data.lists;
          _this.dataNumTwo44 = res.data.totalCount; 
          _this.pointData2[11] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo51(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_05_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo51,
                pageSize:this.pagesizeTwo51
        }
      }).then(res=>{
          _this.tableDataTwo51 = res.data.lists;
          _this.dataNumTwo51 = res.data.totalCount; 
          _this.pointData2[12] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo61(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_06_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo61,
                pageSize:this.pagesizeTwo61
        }
      }).then(res=>{
          _this.tableDataTwo61 = res.data.lists;
          _this.dataNumTwo61 = res.data.totalCount; 
          _this.pointData2[13] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo62(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_06_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo62,
                pageSize:this.pagesizeTwo62
        }
      }).then(res=>{
          _this.tableDataTwo62 = res.data.lists;
          _this.dataNumTwo62 = res.data.totalCount; 
          _this.pointData2[14] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo63(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_06_03";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo63,
                pageSize:this.pagesizeTwo63
        }
      }).then(res=>{
          _this.tableDataTwo63 = res.data.lists;
          _this.dataNumTwo63 = res.data.totalCount; 
          _this.pointData2[15] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo64(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_06_04";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo64,
                pageSize:this.pagesizeTwo64
        }
      }).then(res=>{
          _this.tableDataTwo64 = res.data.lists;
          _this.dataNumTwo64 = res.data.totalCount;
          _this.pointData2[16] = res.data.totalCount; 
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo65(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_06_05";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo65,
                pageSize:this.pagesizeTwo65
        }
      }).then(res=>{
          _this.tableDataTwo65 = res.data.lists;
          _this.dataNumTwo65 = res.data.totalCount; 
          _this.pointData2[17] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo71(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_07_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo71,
                pageSize:this.pagesizeTwo71
        }
      }).then(res=>{
          _this.tableDataTwo71 = res.data.lists;
          _this.dataNumTwo71 = res.data.totalCount; 
          _this.pointData2[18] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo72(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_07_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo72,
                pageSize:this.pagesizeTwo72
        }
      }).then(res=>{
          _this.tableDataTwo72 = res.data.lists;
          _this.dataNumTwo72 = res.data.totalCount; 
          _this.pointData2[19] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo73(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_07_03";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo73,
                pageSize:this.pagesizeTwo73
        }
      }).then(res=>{
          _this.tableDataTwo73 = res.data.lists;
          _this.dataNumTwo73 = res.data.totalCount; 
          _this.pointData2[20] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo74(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_07_04";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo74,
                pageSize:this.pagesizeTwo74
        }
      }).then(res=>{
          _this.tableDataTwo74 = res.data.lists;
          _this.dataNumTwo74 = res.data.totalCount; 
          _this.pointData2[21] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo81(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_08_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo81,
                pageSize:this.pagesizeTwo81
        }
      }).then(res=>{
          _this.tableDataTwo81 = res.data.lists;
          _this.dataNumTwo81 = res.data.totalCount; 
          _this.pointData2[22] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo82(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_08_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo82,
                pageSize:this.pagesizeTwo82
        }
      }).then(res=>{
          _this.tableDataTwo82 = res.data.lists;
          _this.dataNumTwo82 = res.data.totalCount; 
          _this.pointData2[23] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo93(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_09_03";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo93,
                pageSize:this.pagesizeTwo93
        }
      }).then(res=>{
          _this.tableDataTwo93 = res.data.lists;
          _this.dataNumTwo93 = res.data.totalCount; 
          _this.pointData2[24] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo94(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_09_04";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo94,
                pageSize:this.pagesizeTwo94
        }
      }).then(res=>{
          _this.tableDataTwo94 = res.data.lists;
          _this.dataNumTwo94 = res.data.totalCount; 
          _this.pointData2[25] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo95(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_09_05";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo95,
                pageSize:this.pagesizeTwo95
        }
      }).then(res=>{
          _this.tableDataTwo95 = res.data.lists;
          _this.dataNumTwo95 = res.data.totalCount; 
          _this.pointData2[26] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo96(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_09_06";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo96,
                pageSize:this.pagesizeTwo96
        }
      }).then(res=>{
          _this.tableDataTwo96 = res.data.lists;
          _this.dataNumTwo96 = res.data.totalCount; 
          _this.pointData2[27] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo97(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_09_07";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo97,
                pageSize:this.pagesizeTwo97
        }
      }).then(res=>{
          _this.tableDataTwo97 = res.data.lists;
          _this.dataNumTwo97 = res.data.totalCount; 
          _this.pointData2[28] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo101(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_10_01";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo101,
                pageSize:this.pagesizeTwo101
        }
      }).then(res=>{
          _this.tableDataTwo101 = res.data.lists;
          _this.dataNumTwo101 = res.data.totalCount; 
          _this.pointData2[29] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    getRiceDataTwo102(){
      const _this = this;
      var noParams='';
      var riceName="crop_01_02_table_10_02";
      this.tableEngishName2=riceName;
      axios({
        url:'/getRiceTwoAudit1',
        method:'post',
        params:{params1:noParams,
                riceTableName:riceName,
                start:this.currentPageTwo102,
                pageSize:this.pagesizeTwo102
        }
      }).then(res=>{
          _this.tableDataTwo102 = res.data.lists;
          _this.dataNumTwo102 = res.data.totalCount; 
          _this.pointData2[30] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },

    //搜索
    searchInfoTwo11(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo11,
                pageSize:this.pagesizeTwo11
        }
      }).then(res=>{
            _this.tableDataTwo11 = res.data.lists;
            _this.dataNumTwo11 = res.data.totalCount;
          _this.pointData2[0] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo12(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo12',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo12,
                pageSize:this.pagesizeTwo12
        }
      }).then(res=>{
            _this.tableDataTwo12 = res.data;
            _this.dataNumTwo12 = res.data.length;
          _this.pointData2[1] = _this.dataNumTwo12;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo31(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo31,
                pageSize:this.pagesizeTwo31
        }
      }).then(res=>{
            _this.tableDataTwo31 = res.data.lists;
            _this.dataNumTwo31 = res.data.totalCount;
            _this.pointData2[2] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo32(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo32,
                pageSize:this.pagesizeTwo32
        }
      }).then(res=>{
            _this.tableDataTwo32 = res.data.lists;
            _this.dataNumTwo32 = res.data.totalCount;
            _this.pointData2[3] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo33(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo33,
                pageSize:this.pagesizeTwo33
        }
      }).then(res=>{
            _this.tableDataTwo33 = res.data.lists;
            _this.dataNumTwo33 = res.data.totalCount;
            _this.pointData2[4] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo34(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo34,
                pageSize:this.pagesizeTwo34
        }
      }).then(res=>{
            _this.tableDataTwo34 = res.data.lists;
            _this.dataNumTwo34 = res.data.totalCount;
            _this.pointData2[5] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo35(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo35,
                pageSize:this.pagesizeTwo35
        }
      }).then(res=>{
            _this.tableDataTwo35 = res.data.lists;
            _this.dataNumTwo35 = res.data.totalCount;
            _this.pointData2[6] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo36(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo36,
                pageSize:this.pagesizeTwo36
        }
      }).then(res=>{
            _this.tableDataTwo36 = res.data.lists;
            _this.dataNumTwo36 = res.data.totalCount;
            _this.pointData2[7] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo41(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo41,
                pageSize:this.pagesizeTwo41
        }
      }).then(res=>{
            _this.tableDataTwo41 = res.data.lists;
            _this.dataNumTwo41 = res.data.totalCount;
            _this.pointData2[8] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo42(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo42,
                pageSize:this.pagesizeTwo42
        }
      }).then(res=>{
            _this.tableDataTwo42 = res.data.lists;
            _this.dataNumTwo42 = res.data.totalCount;
            _this.pointData2[9] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo43(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo43,
                pageSize:this.pagesizeTwo43
        }
      }).then(res=>{
            _this.tableDataTwo43 = res.data.lists;
            _this.dataNumTwo43 = res.data.totalCount;
            _this.pointData2[10] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo44(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo44,
                pageSize:this.pagesizeTwo44
        }
      }).then(res=>{
            _this.tableDataTwo44 = res.data.lists;
            _this.dataNumTwo44 = res.data.totalCount;
            _this.pointData2[11] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo51(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo51,
                pageSize:this.pagesizeTwo51
        }
      }).then(res=>{
            _this.tableDataTwo51 = res.data.lists;
            _this.dataNumTwo51 = res.data.totalCount;
            _this.pointData2[12] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo61(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo61,
                pageSize:this.pagesizeTwo61
        }
      }).then(res=>{
            _this.tableDataTwo61 = res.data.lists;
            _this.dataNumTwo61 = res.data.totalCount;
            _this.pointData2[13] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo62(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo62,
                pageSize:this.pagesizeTwo62
        }
      }).then(res=>{
            _this.tableDataTwo62 = res.data.lists;
            _this.dataNumTwo62 = res.data.totalCount;
            _this.pointData2[14] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo63(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo63,
                pageSize:this.pagesizeTwo63
        }
      }).then(res=>{
            _this.tableDataTwo63 = res.data.lists;
            _this.dataNumTwo63 = res.data.totalCount;
            _this.pointData2[15] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo64(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo64,
                pageSize:this.pagesizeTwo64
        }
      }).then(res=>{
            _this.tableDataTwo64 = res.data.lists;
            _this.dataNumTwo64 = res.data.totalCount;
            _this.pointData2[16] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo65(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo65,
                pageSize:this.pagesizeTwo65
        }
      }).then(res=>{
            _this.tableDataTwo65 = res.data.lists;
            _this.dataNumTwo65 = res.data.totalCount;
            _this.pointData2[17] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo71(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo71,
                pageSize:this.pagesizeTwo71
        }
      }).then(res=>{
            _this.tableDataTwo71 = res.data.lists;
            _this.dataNumTwo71 = res.data.totalCount;
            _this.pointData2[18] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo72(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo72,
                pageSize:this.pagesizeTwo72
        }
      }).then(res=>{
            _this.tableDataTwo72 = res.data.lists;
            _this.dataNumTwo72 = res.data.totalCount;
            _this.pointData2[19] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo73(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo73,
                pageSize:this.pagesizeTwo73
        }
      }).then(res=>{
            _this.tableDataTwo73 = res.data.lists;
            _this.dataNumTwo73 = res.data.totalCount;
            _this.pointData2[20] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo74(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo74,
                pageSize:this.pagesizeTwo74
        }
      }).then(res=>{
            _this.tableDataTwo74 = res.data.lists;
            _this.dataNumTwo74 = res.data.totalCount;
            _this.pointData2[21] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo81(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo81,
                pageSize:this.pagesizeTwo81
        }
      }).then(res=>{
            _this.tableDataTwo81 = res.data.lists;
            _this.dataNumTwo81 = res.data.totalCount;
            _this.pointData2[22] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo82(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo82,
                pageSize:this.pagesizeTwo82
        }
      }).then(res=>{
            _this.tableDataTwo82 = res.data.lists;
            _this.dataNumTwo82 = res.data.totalCount;
            _this.pointData2[23] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo93(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo93,
                pageSize:this.pagesizeTwo93
        }
      }).then(res=>{
            _this.tableDataTwo93 = res.data.lists;
            _this.dataNumTwo93 = res.data.totalCount;
            _this.pointData2[24] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo94(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo94,
                pageSize:this.pagesizeTwo94
        }
      }).then(res=>{
            _this.tableDataTwo94 = res.data.lists;
            _this.dataNumTwo94 = res.data.totalCount;
            _this.pointData2[25] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo95(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo95,
                pageSize:this.pagesizeTwo95
        }
      }).then(res=>{
            _this.tableDataTwo95 = res.data.lists;
            _this.dataNumTwo95 = res.data.totalCount;
            _this.pointData2[26] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo96(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo96,
                pageSize:this.pagesizeTwo96
        }
      }).then(res=>{
            _this.tableDataTwo96 = res.data.lists;
            _this.dataNumTwo96 = res.data.totalCount;
            _this.pointData2[27] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo97(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo97,
                pageSize:this.pagesizeTwo97
        }
      }).then(res=>{
            _this.tableDataTwo97 = res.data.lists;
            _this.dataNumTwo97 = res.data.totalCount;
            _this.pointData2[28] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo101(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo101,
                pageSize:this.pagesizeTwo101
        }
      }).then(res=>{
            _this.tableDataTwo101 = res.data.lists;
            _this.dataNumTwo101 = res.data.totalCount;
            _this.pointData2[29] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    searchInfoTwo102(){
      const _this = this;
      axios({
        url:'/searchRiceAuditTwo1',
        method:'post',
        params:{params1:this.countyName2,
                month:this.month,
                riceTableName:this.tableEngishName2,
                start:this.currentPageTwo102,
                pageSize:this.pagesizeTwo102
        }
      }).then(res=>{
            _this.tableDataTwo102 = res.data.lists;
            _this.dataNumTwo102 = res.data.totalCount;
            _this.pointData2[30] = res.data.totalCount;
          _this.getTotalNumber2();
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },
    //重置
    reSet2(){
      this.countyName2="";
      this.month="";
      switch(this.tableEngishName2){
        case "crop_01_02_table_01_01":
          this.getRiceDataTwo11();
          break;
        case "crop_01_02_table_01_02":
          this.getRiceDataTwo12();
          break;
        case "crop_01_02_table_03_01":
          this.getRiceDataTwo31();
          break;
        case "crop_01_02_table_03_02":
          this.getRiceDataTwo32();
          break;
        case "crop_01_02_table_03_03":
          this.getRiceDataTwo33();
          break;
        case "crop_01_02_table_03_04":
          this.getRiceDataTwo34();
          break;
        case "crop_01_02_table_03_05":
          this.getRiceDataTwo35();
          break;
        case "crop_01_02_table_03_06":
          this.getRiceDataTwo36();
          break;
        case "crop_01_02_table_04_01":
          this.getRiceDataTwo41();
          break;
        case "crop_01_02_table_04_02":
          this.getRiceDataTwo42();
          break;
        case "crop_01_02_table_04_03":
          this.getRiceDataTwo43();
          break;
        case "crop_01_02_table_04_04":
          this.getRiceDataTwo44();
          break;
        case "crop_01_02_table_05_01":
          this.getRiceDataTwo51();
          break;
        case "crop_01_02_table_06_01":
          this.getRiceDataTwo61();
          break;
        case "crop_01_02_table_06_02":
          this.getRiceDataTwo62();
          break;
        case "crop_01_02_table_06_03":
          this.getRiceDataTwo63();
          break;
        case "crop_01_02_table_06_04":
          this.getRiceDataTwo64();
          break;
        case "crop_01_02_table_06_05":
          this.getRiceDataTwo65();
          break;
        case "crop_01_02_table_07_01":
          this.getRiceDataTwo71();
          break;
        case "crop_01_02_table_07_02":
          this.getRiceDataTwo72();
          break;
        case "crop_01_02_table_07_03":
          this.getRiceDataTwo73();
          break;
        case "crop_01_02_table_07_04":
          this.getRiceDataTwo74();
          break;
        case "crop_01_02_table_08_01":
          this.getRiceDataTwo81();
          break;
        case "crop_01_02_table_08_02":
          this.getRiceDataTwo82();
          break;
        case "crop_01_02_table_09_03":
          this.getRiceDataTwo93();
          break;
        case "crop_01_02_table_09_04":
          this.getRiceDataTwo94();
          break;
        case "crop_01_02_table_09_05":
          this.getRiceDataTwo95();
          break;
        case "crop_01_02_table_09_06":
          this.getRiceDataTwo96();
          break;
        case "crop_01_02_table_09_07":
          this.getRiceDataTwo97();
          break;
        case "crop_01_02_table_10_01":
          this.getRiceDataTwo101();
          break;
        case "crop_01_02_table_10_02":
          this.getRiceDataTwo102();
          break;
      };
    },
    //通过
    confirmPass2(index,row){
      ////console.log(row);
      const _this=this;
      var returnTableName = this.tableEngishName2;
      if(returnTableName==="crop_01_02_table_01_02"){
        var recordId = 1;
        var recordCounty=row.county;
      }else{  
        var recordId = row.id;
        var recordCounty=row.field_1;
      }
      ////console.log(returnTableName,recordId,recordCounty);
      axios({
        url:'/confirmPass',
        method:'post',
        params:{returnTableName: returnTableName,
                recordCounty:recordCounty,
                recordId:recordId}
      }).then(res=>{
        if(_this.tableEngishName2 === "crop_01_02_table_01_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo11();
          }else{
           _this.getRiceDataTwo11();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_01_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo12();
          }else{
           _this.getRiceDataTwo12();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo31();
          }else{
           _this.getRiceDataTwo31();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo32();
          }else{
           _this.getRiceDataTwo32();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo33();
          }else{
           _this.getRiceDataTwo33();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo34();
          }else{
           _this.getRiceDataTwo34();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo35();
          }else{
           _this.getRiceDataTwo35();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_06"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo36();
          }else{
           _this.getRiceDataTwo36();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo41();
          }else{
           _this.getRiceDataTwo41();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo42();
          }else{
           _this.getRiceDataTwo42();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo43();
          }else{
           _this.getRiceDataTwo43();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo44();
          }else{
           _this.getRiceDataTwo44();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_05_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo51();
          }else{
           _this.getRiceDataTwo51();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo61();
          }else{
           _this.getRiceDataTwo61();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo62();
          }else{
           _this.getRiceDataTwo62();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo63();
          }else{
           _this.getRiceDataTwo63();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo64();
          }else{
           _this.getRiceDataTwo64();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo65();
          }else{
           _this.getRiceDataTwo65();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo71();
          }else{
           _this.getRiceDataTwo71();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo72();
          }else{
           _this.getRiceDataTwo72();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo73();
          }else{
           _this.getRiceDataTwo73();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_08_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo81();
          }else{
           _this.getRiceDataTwo81();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_08_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo82();
          }else{
           _this.getRiceDataTwo82();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo93();
          }else{
           _this.getRiceDataTwo93();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo94();
          }else{
           _this.getRiceDataTwo94();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo95();
          }else{
           _this.getRiceDataTwo95();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_06"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo96();
          }else{
           _this.getRiceDataTwo96();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_07"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo97();
          }else{
           _this.getRiceDataTwo97();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_10_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo101();
          }else{
           _this.getRiceDataTwo101();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_10_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo102();
          }else{
           _this.getRiceDataTwo102();
          };
        };
      }).catch(err=>{
        return this.$message.error("查询数据失败！")
      });
    },
    //退回按钮
    backRevision2(index,row){
      this.recordData2=row;
      switch(this.tableEngishName2){
        case "crop_01_02_table_01_01":
          this.dialogVisibleTwo11=true;
          break;
        case "crop_01_02_table_01_02":
          this.dialogVisibleTwo12=true;
          break;
        case "crop_01_02_table_03_01":
          this.dialogVisibleTwo31=true;
          break;
        case "crop_01_02_table_03_02":
          this.dialogVisibleTwo32=true;
          break;
        case "crop_01_02_table_03_03":
          this.dialogVisibleTwo33=true;
          break;
        case "crop_01_02_table_03_04":
          this.dialogVisibleTwo34=true;
          break;
        case "crop_01_02_table_03_05":
          this.dialogVisibleTwo35=true;
          break;
        case "crop_01_02_table_03_06":
          this.dialogVisibleTwo36=true;
          break;
        case "crop_01_02_table_04_01":
          this.dialogVisibleTwo41=true;
          break;
        case "crop_01_02_table_04_02":
          this.dialogVisibleTwo42=true;
          break;
        case "crop_01_02_table_04_03":
          this.dialogVisibleTwo43=true;
          break;
        case "crop_01_02_table_04_04":
          this.dialogVisibleTwo44=true;
          break;
        case "crop_01_02_table_05_01":
          this.dialogVisibleTwo51=true;
          break;
        case "crop_01_02_table_06_01":
          this.dialogVisibleTwo61=true;
          break;
        case "crop_01_02_table_06_02":
          this.dialogVisibleTwo62=true;
          break;
        case "crop_01_02_table_06_03":
          this.dialogVisibleTwo63=true;
          break;
        case "crop_01_02_table_06_04":
          this.dialogVisibleTwo64=true;
          break;
        case "crop_01_02_table_06_05":
          this.dialogVisibleTwo65=true;
          break;
        case "crop_01_02_table_07_01":
          this.dialogVisibleTwo71=true;
          break;
        case "crop_01_02_table_07_02":
          this.dialogVisibleTwo72=true;
          break;
        case "crop_01_02_table_07_03":
          this.dialogVisibleTwo73=true;
          break;
        case "crop_01_02_table_07_04":
          this.dialogVisibleTwo74=true;
          break;
        case "crop_01_02_table_08_01":
          this.dialogVisibleTwo81=true;
          break;
        case "crop_01_02_table_08_02":
          this.dialogVisibleTwo82=true;
          break;
        case "crop_01_02_table_09_03":
          this.dialogVisibleTwo93=true;
          break;
        case "crop_01_02_table_09_04":
          this.dialogVisibleTwo94=true;
          break;
        case "crop_01_02_table_09_05":
          this.dialogVisibleTwo95=true;
          break;
        case "crop_01_02_table_09_06":
          this.dialogVisibleTwo96=true;
          break;
        case "crop_01_02_table_09_07":
          this.dialogVisibleTwo97=true;
          break;
        case "crop_01_02_table_10_01":
          this.dialogVisibleTwo101=true;
          break;
        case "crop_01_02_table_10_02":
          this.dialogVisibleTwo102=true;
          break;
      };
      
    },
    //确认退回
    confirmBack2(){
      const _this=this;
      this.visibleBackTwo11=false;
      this.visibleBackTwo12=false;
      this.visibleBackTwo31=false;
      this.visibleBackTwo32=false;
      this.visibleBackTwo33=false;
      this.visibleBackTwo34=false;
      this.visibleBackTwo35=false;
      this.visibleBackTwo36=false;
      this.visibleBackTwo41=false;
      this.visibleBackTwo42=false;
      this.visibleBackTwo43=false;
      this.visibleBackTwo44=false;
      this.visibleBackTwo51=false;
      this.visibleBackTwo61=false;
      this.visibleBackTwo62=false;
      this.visibleBackTwo63=false;
      this.visibleBackTwo64=false;
      this.visibleBackTwo65=false;
      this.visibleBackTwo71=false;
      this.visibleBackTwo72=false;
      this.visibleBackTwo73=false;
      this.visibleBackTwo74=false;
      this.visibleBackTwo81=false;
      this.visibleBackTwo82=false;
      this.visibleBackTwo93=false;
      this.visibleBackTwo94=false;
      this.visibleBackTwo95=false;
      this.visibleBackTwo96=false;
      this.visibleBackTwo97=false;
      this.visibleBackTwo101=false;
      this.visibleBackTwo102=false;

      this.dialogVisibleTwo11=false;
      this.dialogVisibleTwo12=false;
      this.dialogVisibleTwo31=false;
      this.dialogVisibleTwo32=false;
      this.dialogVisibleTwo33=false;
      this.dialogVisibleTwo34=false;
      this.dialogVisibleTwo35=false;
      this.dialogVisibleTwo36=false;
      this.dialogVisibleTwo41=false;
      this.dialogVisibleTwo42=false;
      this.dialogVisibleTwo43=false;
      this.dialogVisibleTwo44=false;
      this.dialogVisibleTwo51=false;
      this.dialogVisibleTwo61=false;
      this.dialogVisibleTwo62=false;
      this.dialogVisibleTwo63=false;
      this.dialogVisibleTwo64=false;
      this.dialogVisibleTwo65=false;
      this.dialogVisibleTwo71=false;
      this.dialogVisibleTwo72=false;
      this.dialogVisibleTwo73=false;
      this.dialogVisibleTwo74=false;
      this.dialogVisibleTwo81=false;
      this.dialogVisibleTwo82=false;
      this.dialogVisibleTwo93=false;
      this.dialogVisibleTwo94=false;
      this.dialogVisibleTwo95=false;
      this.dialogVisibleTwo96=false;
      this.dialogVisibleTwo97=false;
      this.dialogVisibleTwo101=false;
      this.dialogVisibleTwo102=false;

      ////console.log(this.recordData2);
      this.getMyTime();
      var recordTime = this.systemTime;
      var returnTableName = this.tableEngishName2;
      var recordPeople = this.recordData2.people;
      if(returnTableName === "crop_01_02_table_01_02"){
        var recordCounty=this.recordData2.county;
        var recordId = 1;
      }else{   
        var recordCounty=this.recordData2.field_1;
        var recordId = this.recordData2.id;
      }
      var recordReason = this.reason2;
      //将原因显示置空
      this.reason2='';
      ////console.log(recordTime,recordId,returnTableName,recordPeople,recordCounty,recordReason);
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
        if(_this.tableEngishName2 === "crop_01_02_table_01_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo11();
          }else{
           _this.getRiceDataTwo11();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_01_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo12();
          }else{
           _this.getRiceDataTwo12();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo31();
          }else{
           _this.getRiceDataTwo31();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo32();
          }else{
           _this.getRiceDataTwo32();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo33();
          }else{
           _this.getRiceDataTwo33();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo34();
          }else{
           _this.getRiceDataTwo34();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo35();
          }else{
           _this.getRiceDataTwo35();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_03_06"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo36();
          }else{
           _this.getRiceDataTwo36();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo41();
          }else{
           _this.getRiceDataTwo41();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo42();
          }else{
           _this.getRiceDataTwo42();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo43();
          }else{
           _this.getRiceDataTwo43();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_04_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo44();
          }else{
           _this.getRiceDataTwo44();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_05_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo51();
          }else{
           _this.getRiceDataTwo51();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo61();
          }else{
           _this.getRiceDataTwo61();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo62();
          }else{
           _this.getRiceDataTwo62();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo63();
          }else{
           _this.getRiceDataTwo63();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo64();
          }else{
           _this.getRiceDataTwo64();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_06_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo65();
          }else{
           _this.getRiceDataTwo65();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo71();
          }else{
           _this.getRiceDataTwo71();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo72();
          }else{
           _this.getRiceDataTwo72();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_07_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo73();
          }else{
           _this.getRiceDataTwo73();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_08_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo81();
          }else{
           _this.getRiceDataTwo81();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_08_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo82();
          }else{
           _this.getRiceDataTwo82();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_03"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo93();
          }else{
           _this.getRiceDataTwo93();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_04"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo94();
          }else{
           _this.getRiceDataTwo94();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_05"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo95();
          }else{
           _this.getRiceDataTwo95();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_06"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo96();
          }else{
           _this.getRiceDataTwo96();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_09_07"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo97();
          }else{
           _this.getRiceDataTwo97();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_10_01"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo101();
          }else{
           _this.getRiceDataTwo101();
          };
        }else if(_this.tableEngishName2 === "crop_01_02_table_10_02"){
          if(_this.countyName2 !=""){
            _this.searchInfoTwo102();
          }else{
           _this.getRiceDataTwo102();
          };
        };
       }).catch(err=>{
         return this.$message.error("查询数据失败！");
       });
    },



    //关闭popover
    closePopover(index){
      this.$refs[`popover-${index}`].doClose();
    },
    //分页后序号连续处理
    //一季稻
    indexCount1(index){
       return (this.currentPage1-1)*this.pagesize1 + index +1;
    }, 
    indexCount12(index){
       return (this.currentPage12-1)*this.pagesize12 + index +1;
    }, 
    indexCount31(index){
       return (this.currentPage31-1)*this.pagesize31 + index +1;
    }, 
    indexCount32(index){
       return (this.currentPage32-1)*this.pagesize32 + index +1;
    },
    indexCount33(index){
       return (this.currentPage33-1)*this.pagesize33 + index +1;
    },
    indexCount41(index){
       return (this.currentPage41-1)*this.pagesize41 + index +1;
    },
    indexCount42(index){
       return (this.currentPage42-1)*this.pagesize42 + index +1;
    },
    indexCount43(index){
       return (this.currentPage43-1)*this.pagesize43 + index +1;
    },
    indexCount44(index){
       return (this.currentPage44-1)*this.pagesize44 + index +1;
    },
    indexCount51(index){
       return (this.currentPage51-1)*this.pagesize51 + index +1;
    },
    indexCount61(index){
       return (this.currentPage61-1)*this.pagesize61 + index +1;
    },
    indexCount62(index){
       return (this.currentPage62-1)*this.pagesize62 + index +1;
    },
    indexCount63(index){
       return (this.currentPage63-1)*this.pagesize63 + index +1;
    },
    indexCount64(index){
       return (this.currentPage64-1)*this.pagesize64 + index +1;
    },
    indexCount65(index){
       return (this.currentPage65-1)*this.pagesize65 + index +1;
    },
    indexCount71(index){
       return (this.currentPage71-1)*this.pagesize71 + index +1;
    },
    indexCount72(index){
       return (this.currentPage72-1)*this.pagesize72 + index +1;
    },
    indexCount73(index){
       return (this.currentPage73-1)*this.pagesize73 + index +1;
    },
    indexCount81(index){
       return (this.currentPage81-1)*this.pagesize81 + index +1;
    },
    indexCount93(index){
       return (this.currentPage93-1)*this.pagesize93 + index +1;
    },
    indexCount94(index){
       return (this.currentPage94-1)*this.pagesize94 + index +1;
    },
    indexCount95(index){
       return (this.currentPage95-1)*this.pagesize95 + index +1;
    },
    indexCount96(index){
       return (this.currentPage96-1)*this.pagesize96 + index +1;
    },
    indexCount97(index){
       return (this.currentPage97-1)*this.pagesize97 + index +1;
    },
    indexCount101(index){
       return (this.currentPage101-1)*this.pagesize101 + index +1;
    },
    //双季稻
    //分页后序号连续处理
    indexCountTwo11(index){
       return (this.currentPageTwo11-1)*this.pagesizeTwo11 + index +1;
    }, 
    indexCountTwo12(index){
       return (this.currentPageTwo12-1)*this.pagesizeTwo12 + index +1;
    }, 
    indexCountTwo31(index){
       return (this.currentPageTwo31-1)*this.pagesizeTwo31 + index +1;
    }, 
    indexCountTwo32(index){
       return (this.currentPageTwo32-1)*this.pagesizeTwo32 + index +1;
    }, 
    indexCountTwo33(index){
       return (this.currentPageTwo33-1)*this.pagesizeTwo33 + index +1;
    }, 
    indexCountTwo34(index){
       return (this.currentPageTwo34-1)*this.pagesizeTwo34 + index +1;
    }, 
    indexCountTwo35(index){
       return (this.currentPageTwo35-1)*this.pagesizeTwo35 + index +1;
    }, 
    indexCountTwo36(index){
       return (this.currentPageTwo36-1)*this.pagesizeTwo36 + index +1;
    }, 
    indexCountTwo41(index){
       return (this.currentPageTwo41-1)*this.pagesizeTwo41 + index +1;
    }, 
    indexCountTwo42(index){
       return (this.currentPageTwo42-1)*this.pagesizeTwo42 + index +1;
    }, 
    indexCountTwo43(index){
       return (this.currentPageTwo43-1)*this.pagesizeTwo43 + index +1;
    }, 
    indexCountTwo44(index){
       return (this.currentPageTwo44-1)*this.pagesizeTwo44 + index +1;
    }, 
    indexCountTwo51(index){
       return (this.currentPageTwo51-1)*this.pagesizeTwo51 + index +1;
    }, 
    indexCountTwo61(index){
       return (this.currentPageTwo61-1)*this.pagesizeTwo61 + index +1;
    }, 
    indexCountTwo62(index){
       return (this.currentPageTwo62-1)*this.pagesizeTwo62 + index +1;
    }, 
    indexCountTwo63(index){
       return (this.currentPageTwo63-1)*this.pagesizeTwo63 + index +1;
    }, 
    indexCountTwo64(index){
       return (this.currentPageTwo64-1)*this.pagesizeTwo64 + index +1;
    }, 
    indexCountTwo65(index){
       return (this.currentPageTwo65-1)*this.pagesizeTwo65 + index +1;
    }, 
    indexCountTwo71(index){
       return (this.currentPageTwo71-1)*this.pagesizeTwo71 + index +1;
    }, 
    indexCountTwo72(index){
       return (this.currentPageTwo72-1)*this.pagesizeTwo72 + index +1;
    }, 
    indexCountTwo73(index){
       return (this.currentPageTwo73-1)*this.pagesizeTwo73 + index +1;
    }, 
    indexCountTwo74(index){
       return (this.currentPageTwo74-1)*this.pagesizeTwo74 + index +1;
    }, 
    indexCountTwo81(index){
       return (this.currentPageTwo81-1)*this.pagesizeTwo81 + index +1;
    }, 
    indexCountTwo82(index){
       return (this.currentPageTwo82-1)*this.pagesizeTwo82 + index +1;
    }, 
    indexCountTwo93(index){
       return (this.currentPageTwo93-1)*this.pagesizeTwo93 + index +1;
    }, 
    indexCountTwo94(index){
       return (this.currentPageTwo94-1)*this.pagesizeTwo94 + index +1;
    }, 
    indexCountTwo95(index){
       return (this.currentPageTwo95-1)*this.pagesizeTwo95 + index +1;
    }, 
    indexCountTwo96(index){
       return (this.currentPageTwo96-1)*this.pagesizeTwo96 + index +1;
    }, 
    indexCountTwo97(index){
       return (this.currentPageTwo97-1)*this.pagesizeTwo97 + index +1;
    }, 
    indexCountTwo101(index){
       return (this.currentPageTwo101-1)*this.pagesizeTwo101 + index +1;
    }, 
    indexCountTwo102(index){
       return (this.currentPageTwo102-1)*this.pagesizeTwo102 + index +1;
    }, 
    //一季稻分页
    handlePage1(currentPage1){
      this.currentPage1 = currentPage1;
      if(this.countyName1!=""){
        this.searchInfo1();
      }else{
        this.getRiceData1();
      };
    },
    handlePage12(currentPage12){
      this.currentPage12 = currentPage12;
      // if(this.countyName1!=""){
      //   this.searchInfo12();
      // }else{
      //   this.getRiceData12();
      // };
    },
    handlePage31(currentPage31){
      this.currentPage31 = currentPage31;
      if(this.countyName1!=""){
        this.searchInfo31();
      }else{
        this.getRiceData31();
      };
    },
    handlePage32(currentPage32){
      this.currentPage32 = currentPage32;
      if(this.countyName1!=""){
        this.searchInfo32();
      }else{
        this.getRiceData32();
      };
    },
    handlePage33(currentPage33){
      this.currentPage33 = currentPage33;
      if(this.countyName1!=""){
        this.searchInfo33();
      }else{
        this.getRiceData33();
      };
    },
    handlePage41(currentPage41){
      this.currentPage41 = currentPage41;
      if(this.countyName1!=""){
        this.searchInfo41();
      }else{
        this.getRiceData41();
      };
    },
    handlePage42(currentPage42){
      this.currentPage42 = currentPage42;
      if(this.countyName1!=""){
        this.searchInfo42();
      }else{
        this.getRiceData42();
      };
    },
    handlePage43(currentPage43){
      this.currentPage43 = currentPage43;
      if(this.countyName1!=""){
        this.searchInfo43();
      }else{
        this.getRiceData43();
      };
    },
    handlePage44(currentPage44){
      this.currentPage44 = currentPage44;
      if(this.countyName1!=""){
        this.searchInfo44();
      }else{
        this.getRiceData44();
      };
    },
    handlePage51(currentPage51){
      this.currentPage51 = currentPage51;
      if(this.countyName1!=""){
        this.searchInfo51();
      }else{
        this.getRiceData51();
      };
    },
    handlePage61(currentPage61){
      this.currentPage61 = currentPage61;
      if(this.countyName1!=""){
        this.searchInfo61();
      }else{
        this.getRiceData61();
      };
    },
    handlePage62(currentPage62){
      this.currentPage62 = currentPage62;
      if(this.countyName1!=""){
        this.searchInfo62();
      }else{
        this.getRiceData62();
      };
    },
    handlePage63(currentPage63){
      this.currentPage63 = currentPage63;
      if(this.countyName1!=""){
        this.searchInfo63();
      }else{
        this.getRiceData63();
      };
    },
    handlePage64(currentPage64){
      this.currentPage64 = currentPage64;
      if(this.countyName1!=""){
        this.searchInfo64();
      }else{
        this.getRiceData64();
      };
    },
    handlePage65(currentPage65){
      this.currentPage65 = currentPage65;
      if(this.countyName1!=""){
        this.searchInfo65();
      }else{
        this.getRiceData65();
      };
    },
    handlePage71(currentPage71){
      this.currentPage71 = currentPage71;
      if(this.countyName1!=""){
        this.searchInfo71();
      }else{
        this.getRiceData71();
      };
    },
    handlePage72(currentPage72){
      this.currentPage72 = currentPage72;
      if(this.countyName1!=""){
        this.searchInfo72();
      }else{
        this.getRiceData72();
      };
    },
    handlePage73(currentPage73){
      this.currentPage73 = currentPage73;
      if(this.countyName1!=""){
        this.searchInfo73();
      }else{
        this.getRiceData73();
      };
    },
    handlePage81(currentPage81){
      this.currentPage81 = currentPage81;
      if(this.countyName1!=""){
        this.searchInfo81();
      }else{
        this.getRiceData81();
      };
    },
    handlePage93(currentPage93){
      this.currentPage93 = currentPage93;
      if(this.countyName1!=""){
        this.searchInfo93();
      }else{
        this.getRiceData93();
      };
    },
    handlePage94(currentPage94){
      this.currentPage94 = currentPage94;
      if(this.countyName1!=""){
        this.searchInfo94();
      }else{
        this.getRiceData94();
      };
    },
    handlePage95(currentPage95){
      this.currentPage95 = currentPage95;
      if(this.countyName1!=""){
        this.searchInfo95();
      }else{
        this.getRiceData95();
      };
    },
    handlePage96(currentPage96){
      this.currentPage96 = currentPage96;
      if(this.countyName1!=""){
        this.searchInfo96();
      }else{
        this.getRiceData96();
      };
    },
    handlePage97(currentPage97){
      this.currentPage97 = currentPage97;
      if(this.countyName1!=""){
        this.searchInfo97();
      }else{
        this.getRiceData97();
      };
    },
    handlePage101(currentPage101){
      this.currentPage101 = currentPage101;
      if(this.countyName1!=""){
        this.searchInfo101();
      }else{
        this.getRiceData101();
      };
    },
    //双季稻分页
    handlePageTwo11(currentPageTwo11){
      this.currentPageTwo11 = currentPageTwo11;
      if(this.countyName2!=""){
        this.searchInfoTwo11();
      }else{
        this.getRiceDataTwo11();
      };
    },
    handlePageTwo12(currentPageTwo12){
      this.currentPageTwo12 = currentPageTwo12;
      // if(this.countyName2!=""){
      //   this.searchInfoTwo12();
      // }else{
      //   this.getRiceDataTwo12();
      // };
    },
    handlePageTwo31(currentPageTwo31){
      this.currentPageTwo31 = currentPageTwo31;
      if(this.countyName2!=""){
        this.searchInfoTwo31();
      }else{
        this.getRiceDataTwo31();
      };
    },
    handlePageTwo32(currentPageTwo32){
      this.currentPageTwo32 = currentPageTwo32;
      if(this.countyName2!=""){
        this.searchInfoTwo32();
      }else{
        this.getRiceDataTwo32();
      };
    },
    handlePageTwo33(currentPageTwo33){
      this.currentPageTwo33 = currentPageTwo33;
      if(this.countyName2!=""){
        this.searchInfoTwo33();
      }else{
        this.getRiceDataTwo33();
      };
    },
    handlePageTwo34(currentPageTwo34){
      this.currentPageTwo34 = currentPageTwo34;
      if(this.countyName2!=""){
        this.searchInfoTwo34();
      }else{
        this.getRiceDataTwo34();
      };
    },
    handlePageTwo35(currentPageTwo35){
      this.currentPageTwo35 = currentPageTwo35;
      if(this.countyName2!=""){
        this.searchInfoTwo35();
      }else{
        this.getRiceDataTwo35();
      };
    },
    handlePageTwo36(currentPageTwo36){
      this.currentPageTwo36 = currentPageTwo36;
      if(this.countyName2!=""){
        this.searchInfoTwo36();
      }else{
        this.getRiceDataTwo36();
      };
    },
    handlePageTwo41(currentPageTwo41){
      this.currentPageTwo41 = currentPageTwo41;
      if(this.countyName2!=""){
        this.searchInfoTwo41();
      }else{
        this.getRiceDataTwo41();
      };
    },
    handlePageTwo42(currentPageTwo42){
      this.currentPageTwo42 = currentPageTwo42;
      if(this.countyName2!=""){
        this.searchInfoTwo42();
      }else{
        this.getRiceDataTwo42();
      };
    },
    handlePageTwo43(currentPageTwo43){
      this.currentPageTwo43 = currentPageTwo43;
      if(this.countyName2!=""){
        this.searchInfoTwo43();
      }else{
        this.getRiceDataTwo43();
      };
    },
    handlePageTwo44(currentPageTwo44){
      this.currentPageTwo44 = currentPageTwo44;
      if(this.countyName2!=""){
        this.searchInfoTwo44();
      }else{
        this.getRiceDataTwo44();
      };
    },
    handlePageTwo51(currentPageTwo51){
      this.currentPageTwo51 = currentPageTwo51;
      if(this.countyName2!=""){
        this.searchInfoTwo51();
      }else{
        this.getRiceDataTwo51();
      };
    },
    handlePageTwo61(currentPageTwo61){
      this.currentPageTwo61 = currentPageTwo61;
      if(this.countyName2!=""){
        this.searchInfoTwo61();
      }else{
        this.getRiceDataTwo61();
      };
    },
    handlePageTwo62(currentPageTwo62){
      this.currentPageTwo62 = currentPageTwo62;
      if(this.countyName2!=""){
        this.searchInfoTwo62();
      }else{
        this.getRiceDataTwo62();
      };
    },
    handlePageTwo63(currentPageTwo63){
      this.currentPageTwo63 = currentPageTwo63;
      if(this.countyName2!=""){
        this.searchInfoTwo63();
      }else{
        this.getRiceDataTwo63();
      };
    },
    handlePageTwo64(currentPageTwo64){
      this.currentPageTwo64 = currentPageTwo64;
      if(this.countyName2!=""){
        this.searchInfoTwo64();
      }else{
        this.getRiceDataTwo64();
      };
    },
    handlePageTwo65(currentPageTwo65){
      this.currentPageTwo65 = currentPageTwo65;
      if(this.countyName2!=""){
        this.searchInfoTwo65();
      }else{
        this.getRiceDataTwo65();
      };
    },
    handlePageTwo71(currentPageTwo71){
      this.currentPageTwo71 = currentPageTwo71;
      if(this.countyName2!=""){
        this.searchInfoTwo71();
      }else{
        this.getRiceDataTwo71();
      };
    },
    handlePageTwo72(currentPageTwo72){
      this.currentPageTwo72 = currentPageTwo72;
      if(this.countyName2!=""){
        this.searchInfoTwo72();
      }else{
        this.getRiceDataTwo72();
      };
    },
    handlePageTwo73(currentPageTwo73){
      this.currentPageTwo73 = currentPageTwo73;
      if(this.countyName2!=""){
        this.searchInfoTwo73();
      }else{
        this.getRiceDataTwo73();
      };
    },
    handlePageTwo74(currentPageTwo74){
      this.currentPageTwo74 = currentPageTwo74;
      if(this.countyName2!=""){
        this.searchInfoTwo74();
      }else{
        this.getRiceDataTwo74();
      };
    },
    handlePageTwo81(currentPageTwo81){
      this.currentPageTwo81 = currentPageTwo81;
      if(this.countyName2!=""){
        this.searchInfoTwo81();
      }else{
        this.getRiceDataTwo81();
      };
    },
    handlePageTwo82(currentPageTwo82){
      this.currentPageTwo82 = currentPageTwo82;
      if(this.countyName2!=""){
        this.searchInfoTwo82();
      }else{
        this.getRiceDataTwo82();
      };
    },
    handlePageTwo93(currentPageTwo93){
      this.currentPageTwo93 = currentPageTwo93;
      if(this.countyName2!=""){
        this.searchInfoTwo93();
      }else{
        this.getRiceDataTwo93();
      };
    },
    handlePageTwo94(currentPageTwo94){
      this.currentPageTwo94 = currentPageTwo94;
      if(this.countyName2!=""){
        this.searchInfoTwo94();
      }else{
        this.getRiceDataTwo94();
      };
    },
    handlePageTwo95(currentPageTwo95){
      this.currentPageTwo95 = currentPageTwo95;
      if(this.countyName2!=""){
        this.searchInfoTwo95();
      }else{
        this.getRiceDataTwo95();
      };
    },
    handlePageTwo96(currentPageTwo96){
      this.currentPageTwo96 = currentPageTwo96;
      if(this.countyName2!=""){
        this.searchInfoTwo96();
      }else{
        this.getRiceDataTwo96();
      };
    },
    handlePageTwo97(currentPageTwo97){
      this.currentPageTwo97 = currentPageTwo97;
      if(this.countyName2!=""){
        this.searchInfoTwo97();
      }else{
        this.getRiceDataTwo97();
      };
    },
    handlePageTwo101(currentPageTwo101){
      this.currentPageTwo101 = currentPageTwo101;
      if(this.countyName2!=""){
        this.searchInfoTwo101();
      }else{
        this.getRiceDataTwo101();
      };
    },
    handlePageTwo102(currentPageTwo102){
      this.currentPageTwo102 = currentPageTwo102;
      if(this.countyName2!=""){
        this.searchInfoTwo102();
      }else{
        this.getRiceDataTwo102();
      };
    },


//删除审核表
    deleteAudit(){
      axios({
            url:'/deleteAudit',
            method:'get',
        }).then(res=>{
            // return this.$message.success("退还数据获取失败！");
        }).catch(err=>{
            //return this.$message.error("退还数据获取失败！");
        })
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
    //退回记录分页
    handleCurrentChange(currentPage){
      this.currentPage = currentPage;
      if(this.params2!="" || this.params1!=""){
        this.getDataBackListInfo();
      }else{
        this.searchInfo();
      };
      ////console.log(this.currentPage);
    },
    //分页后序号连续处理
    //退回记录
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
            ////console.log(res.data);
            _this.tableBackData = res.data.lists;
            _this.dataNum = res.data.totalCount;
            ////console.log(_this.dataNum)
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
      ////console.log(this.params1);
      ////console.log(this.params2);
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
            ////console.log(_this.tableBackData);
            ////console.log(_this.dataNum);
          
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
  .totalRange .secondRange{
    margin-left: 10px;
    width: 98%;
    height: 100%;
  }
  .button{
    position: absolute;right: 60px;top: 105px;
    color:black;
    font-weight: 600;
    font-size: 14px;
  }
 .secondRange .tableRange{
    width:99%;
    margin: 0 auto;
  }
  .searchBox{
    width: 100%;
    height: 40px;
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
/deep/ .el-icon-arrow-left:before {
    content: "\E600";
    color: red;
}
/deep/ .el-icon-arrow-right:before {
    content: "\E604";
    color: red;
}


</style>



