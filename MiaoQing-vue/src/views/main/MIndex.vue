<template>
    <div class="login_box">
        <div >
            <div >
                <!--<img src="../../common/img/login_03.jpg" alt="">-->
                <div>
                    <el-header>
                        <el-row>
                            <el-col :span="12">
                                <div class="grid-content bg-purple">
                                    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top: 20px;" >
                                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                                        <el-breadcrumb-item :to="{ path: '/table1'}">苗情管理</el-breadcrumb-item>
                                        <el-breadcrumb-item>苗情录入</el-breadcrumb-item>
                                    </el-breadcrumb>
                                </div>
                            </el-col>
                            <el-col :span="12">
                                <div>
                                    <!-- 用户头像 -->
                                    <!--<div class="user-avator"><img style="width:40px;height:40px;" src="../assets/logo.svg"></div>-->
                                    <!-- 用户名下拉菜单 -->
                                    <el-dropdown class="user-name" trigger="click" >
                                        <span class="el-dropdown-link">
                                            1234564648<i class="el-icon-caret-bottom"></i>
                                        </span>
                                        <el-dropdown-menu slot="dropdown">
                                            <el-dropdown-item divided  >退出登录</el-dropdown-item>
                                        </el-dropdown-menu>
                                    </el-dropdown>
                                </div>
                            </el-col>
                        </el-row>
                    </el-header>
                </div>
            </div>
        </div>
        <div class="next_step_box">
            <div class="first_con">
                <div class="stepbox">
                    <span class="stepspan activestep">
                        <div class="step1" @click="intoStep(0)">
                            1.监测点信息管理
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 1 ||activeNum == 2?'activestep':''">
                        <div class="step2" @click="intoStep(1)">
                            2.监测点信息填报
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 2?'activestep':''">
                        <div class="step3" @click="intoStep(2)">
                            3.全县信息填报
                        </div>
                    </span>
                </div>
                <div class="stepcontent">
                    <!--第一步-->
                    <div v-show="activeNum==0" class="pad15" style="height: 500px">
                        <div>
                            <router-link  to="/table0201" >
                                <el-button type="primary" icon="el-icon-plus" style="float: right;margin-bottom: 20px;margin-right: 40px">
                                    新增
                                </el-button>
                            </router-link>
                        </div>
                        <el-table
                                border=""
                                :data="pointData"
                                style="width: 100%">
                            <el-table-column
                                    label="监测点"
                                    width="400px"
                                    style="text-align: center"
                                    min-width="33%">
                                <template slot-scope="scope">
                                    <el-popover trigger="hover" placement="top">
                                        <p >监测点名: {{ scope.row.field_1 }}</p>
                                        <p>县市区: {{ scope.row.field_5 }}</p>
                                        <div slot="reference" class="name-wrapper">
                                            <el-tag size="medium">{{ scope.row.field_1 }}</el-tag>
                                        </div>
                                    </el-popover>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="时间"
                                    width="400px"
                                    min-width="33%">
                                <template slot-scope="scope">
                                    <i class="el-icon-time"></i>
                                    <span style="margin-left: 10px">{{ scope.row.field_2}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column label="操作"
                                             min-width="40%">
                                <template slot-scope="scope">
                                    <el-button
                                            size="mini"
                                            @click="handleEdit(scope.$index, scope.row)">查看
                                    </el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div><el-button type="primary" @click="NextStep()" style="margin-top: 20px;margin-right: 40px">下一步</el-button></div>
                    </div>

                    <!--第二步-->
                    <div v-show="activeNum==1"  class="pad15" style="height: 600px">
                        <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                            <template v-for="(item,index) in pointData">
                                <el-tab-pane :label=pointData[index].field_1 :name=index>
                                    <template v-if="item.field_21 == '无盘旱育抛栽' || item.field_21 =='人工移栽'|| item.field_21 =='机械插秧' || item.field_21 =='盘育抛栽'">
                                        <div>
                                            <el-tabs tab-position="top" style="height: 200px;">
                                                <el-tab-pane label="进程表">
                                                    <el-table
                                                            border="true"
                                                            :data="tableData1"
                                                            stripe
                                                            style="width: 100%">
                                                        <el-table-column
                                                                label="序号"
                                                                min-width="10%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="表名"
                                                                min-width="40%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="时间"
                                                                min-width="20%">
                                                            <template slot-scope="scope" >
                                                                <i class="el-icon-time"></i>
                                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column label="操作" min-width="30%">
                                                            <template slot-scope="scope" >
                                                                    <el-button
                                                                            size="mini"
                                                                            type="primary"
                                                                            @click="handleAdd(scope.$index, scope.row,item.field_1)">添加
                                                                    </el-button>
                                                            </template>
                                                        </el-table-column>
                                                    </el-table>
                                                </el-tab-pane>
                                                <el-tab-pane label="记录表">
                                                    <el-table
                                                            border="true"
                                                            :data="tableData3"
                                                            stripe
                                                            style="width: 100%">
                                                        <el-table-column
                                                                label="序号"
                                                                min-width="10%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="表名"
                                                                min-width="40%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="时间"
                                                                min-width="20%">
                                                            <template slot-scope="scope" >
                                                                <i class="el-icon-time"></i>
                                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column label="操作" min-width="30%">
                                                            <template slot-scope="scope">
                                                                <el-button
                                                                        size="mini"
                                                                        type="primary"
                                                                        @click="handleAdd(scope.$index, scope.row,item.field_1)">新增</el-button>
                                                            </template>
                                                        </el-table-column>
                                                    </el-table>
                                                </el-tab-pane>
                                            </el-tabs>
                                            <div style="margin-top: 150px">
                                                <el-button type="primary" @click="prevStep2()">返回上一步</el-button>
                                                <el-button type="primary" @click="NextStep2()">下一步</el-button>
                                            </div>
                                        </div>
                                    </template>
                                    <template v-if="item.field_21=='撒直播'">
                                        <div>
                                            <div>
                                            <el-tabs tab-position="top" style="height: 300px;">
                                                <el-tab-pane >
                                                    <span slot="label"><i class="el-icon-s-operation"></i> 进程表</span>
                                                    <el-table
                                                            border="true"
                                                            :data="tableData2"
                                                            stripe
                                                            style="width: 100%">
                                                        <el-table-column
                                                                label="序号"
                                                                min-width="10%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="表名"
                                                                min-width="40%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="时间"
                                                                min-width="20%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.data }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column label="操作" min-width="30%">
                                                            <template slot-scope="scope">
                                                                <el-button
                                                                        size="mini"
                                                                        type="primary"
                                                                        @click="handleAdd(scope.$index, scope.row,item.field_1)">添加</el-button>
                                                            </template>
                                                        </el-table-column>
                                                    </el-table>
                                                </el-tab-pane>
                                                <el-tab-pane >
                                                    <span slot="label"><i class="el-icon-s-order"></i> 记录表</span>
                                                    <el-table
                                                            border="true"
                                                            :data="tableData4"
                                                            stripe
                                                            style="width: 100%">
                                                        <el-table-column
                                                                label="序号"
                                                                min-width="10%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="表名"
                                                                min-width="40%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column
                                                                label="时间"
                                                                min-width="20%">
                                                            <template slot-scope="scope" >
                                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                                            </template>
                                                        </el-table-column>
                                                        <el-table-column label="操作" min-width="30%">
                                                            <template slot-scope="scope">
                                                                <el-button
                                                                        size="mini"
                                                                        type="primary"
                                                                        @click="handleAdd(scope.$index, scope.row,item.field_1)">添加</el-button>
                                                            </template>
                                                        </el-table-column>
                                                    </el-table>
                                                </el-tab-pane>
                                            </el-tabs>
                                            <div style="margin-top: 200px">
                                                <el-button type="primary" @click="prevStep2()">返回上一步</el-button>
                                                <el-button type="primary" @click="NextStep2()">下一步</el-button>
                                            </div>
                                            </div>
                                        </div>
                                    </template>
                                </el-tab-pane>
                            </template>
                        </el-tabs>
                    </div>
                    <!--第三步页面填写完成等待评估-->
                    <div class="pad15" v-show="activeNum==2">
                        <el-tabs tab-position="top" style="height: 200px;">
                            <el-tab-pane >
                                <span slot="label"><i class="el-icon-s-operation"></i> 进程表</span>
                                <el-table
                                        border="true"
                                        :data="tableData4"
                                        stripe
                                        style="width: 100%">
                                    <el-table-column
                                            prop="num"
                                            label="序号"
                                            min-width="10%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="表名"
                                            min-width="40%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="时间"
                                            min-width="20%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="操作" min-width="30%">
                                        <template slot-scope="scope">
                                            <el-button
                                                    size="mini"
                                                    type="primary"
                                                    @click="handleAdd(scope.$index, scope.row,item.field_1)">添加</el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>
                            <el-tab-pane >
                                <span slot="label"><i class="el-icon-s-order"></i> 气象及灾害</span>
                                <el-table
                                        border="true"
                                        :data="tableData5"
                                        stripe
                                        style="width: 100%">
                                    <el-table-column
                                            label="序号"
                                            width="300"
                                            min-width="10%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="表名"
                                            width="300"
                                            min-width="40%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="时间"
                                            min-width="30%">
                                        <template slot-scope="scope" >
                                            <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                        </template>
                                    </el-table-column>
                                    <el-table-column label="操作"
                                                     min-width="20%">
                                        <template slot-scope="scope">
                                            <el-button
                                                    size="mini"
                                                    type="primary"
                                                    @click="handleAdd(scope.$index, scope.row,item.field_1)">添加</el-button>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </el-tab-pane>
                        </el-tabs>

                        <div style="margin-top: 150px">
                            <el-button type="primary" @click="prevStep2()">返回上一步</el-button>
                            <el-button type="primary" @click="NextStep2()">返回首页</el-button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--模态框-->
        <el-dialog
                title="" :visible.sync="dialogTableVisible0201"
                :before-close="handleClose"
                class="verifyform"
                width="60%">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
                <tr >
                    <td colspan="7" style="background-color: #e1f1ff">年度水稻监测点建档卡</td>
                </tr>
                <tr>
                    <td></td>
                    <td>监测点代码：</td>
                    <td colspan="2">
                        <el-input v-model="data.field_1" :disabled="true"></el-input>
                    </td>
                    <td>监测年份：</td>
                    <td colspan="2"><el-input v-model="data.field_2"  :disabled="true"></el-input></td>
                </tr>
                <tr>
                    <td rowspan="2">地址</td>
                    <td>市</td>
                    <td>县（市、区</td>
                    <td>镇（乡）</td>
                    <td>村名</td>
                    <td colspan="2"><el-input v-model="data.field_3"></el-input></td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_4" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_5" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_6" :disabled="true"></el-input></td>
                    <td>户主</td>
                    <td colspan="2"><el-input v-model="data.field_7" :disabled="true"></el-input></td>
                </tr>
                <tr>
                    <td rowspan="4">监测田块</td>
                    <td>种植面积（亩）</td>
                    <td>地区类型</td>
                    <td>土壤类型</td>
                    <td>前茬类型</td>
                    <td colspan="2">水管方式</td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_8"  :disabled="true" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                    <td><el-input v-model="data.field_9"  :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_10" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_11" :disabled="true"></el-input></td>
                    <td colspan="2"><el-input v-model="data.field_12" :disabled="true"></el-input></td>
                </tr>
                <tr>
                    <td>全氮（g/KG）</td>
                    <td>速效磷(g/Kg)</td>
                    <td>速效钾(g/Kg)</td>
                    <td>有机质(g/Kg)</td>
                    <td colspan="2">秸秆还田量(kg/亩)</td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_13" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_14" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_15" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_16" :disabled="true"></el-input></td>
                    <td colspan="2"><el-input v-model="data.field_17"  :disabled="true" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                </tr>
                <tr>
                    <td rowspan="2">品种、品系信息</td>
                    <td colspan="2">名称</td>
                    <td colspan="2">稻作类型</td>
                    <td colspan="2">品系类型</td>
                </tr>
                <tr>
                    <td colspan="2"><el-input v-model="data.field_18" :disabled="true"></el-input></td>
                    <td colspan="2"><el-input v-model="data.field_19" :disabled="true"></el-input></td>
                    <td colspan="2"><el-input v-model="data.field_20" :disabled="true"></el-input></td>
                </tr>
                <tr>
                    <td rowspan="4">田间信息</td>
                    <td>栽植方式</td>
                    <td>播期</td>
                    <td>移栽秧龄（天）</td>
                    <td>移栽叶龄（叶）</td>
                    <td>基本苗（万/亩）</td>
                    <td>收获日期</td>
                </tr>
                <tr>
                    <td ><el-input v-model="data.field_21" :disabled="true"></el-input></td>
                    <!--<td>&lt;!&ndash; <el-input v-model="data.field_22"></el-input> &ndash;&gt;-->
                    <!--<el-date-picker-->
                    <!--v-model="data.field_22"-->
                    <!--type="date"-->
                    <!--value-format=“yyyy-MM-dd”-->
                    <!--placeholder="选择日期">-->
                    <!--</el-date-picker>-->
                    <!--</td>-->
                    <td ><el-input v-model="data.field_18" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_23"  :disabled="true"></el-input></td>
                    <td> <el-input v-model="data.field_24"  :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_25"  :disabled="true"></el-input></td>
                    <!--<td>-->
                    <!--<el-date-picker-->
                    <!--v-model="data.field_26"-->
                    <!--type="date"-->
                    <!--value-format=“yyyy-MM-dd”-->
                    <!--placeholder="选择日期">-->
                    <!--</el-date-picker>-->
                    <!--</td>-->
                    <td colspan="2"><el-input v-model="data.field_26"></el-input></td>
                </tr>
                <tr>
                    <td>亩有效穗(万)</td>
                    <td>穗总粒数 (个)</td>
                    <td>结实率(％)</td>
                    <td>千粒重(g)</td>
                    <td>理论产量(kg/亩)</td>
                    <td>实收产量(kg/亩)</td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_27" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_28" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_29" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_30" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_31" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_32" :disabled="true"></el-input></td>
                </tr>
            </table>
        </el-dialog>
    </div>
</template>
<script>
    import axios from 'axios'
    export default {
        data(){
            return {
                data:[],
                activeName:0,
                sec: '',
                btntype: 'info',
                ifno: true,
                activeNum: 0,
                verify: false,
                dialogTableVisible2: false,
                dialogTableVisible1: false,
                sizeForm: {
                    name: '',
                    region: '',
                    city: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: '',
                },
                name:'first',
                valerrormsg: '',
                value: [],
                tableData5: [
                    {
                        date: '2021-08-02',
                        name: '气象资料列表',
                        num: '1',
                        type:'process0901'
                    }, {
                        date: '2021-08-04',
                        name: '水稻倒伏灾害表',
                        num: '2',
                        type:'process0901'

                    }, {
                        date: '2021-08-01',
                        name: '水稻低温冷害表',
                        num: '3',
                        type:'process0903'
                    }, {
                        date: '2021-08-03',
                        name: '水稻高温热害表',
                        num: '4',
                        type:'process0904'
                    }, {
                        date: '2021-08-03',
                        name: '水稻涝害调查表',
                        num: '5',
                        type:'process0905'
                    }, {
                        date: '2021-08-03',
                        name: '水稻监测点洪涝灾害表',
                        num: '6',
                        type:'process0906'
                    }, {
                        date: '2021-08-03',
                        name: '抽穗期前、后各15天气象资料',
                        num: '7',
                        type:'process0907'
                    }
                ],
                tableData1: [{
                    date: '2021-08-02',
                    name: '中稻生育进度调查表（移栽稻）',
                    num: '1',
                    type:'process0401'
                }, {
                    date: '2021-08-01',
                    name: '茎蘖数叶面积构成表',
                    num: '2',
                    type:'process0403'
                },{
                    date: '2021-08-03',
                    name: '苗情生育期统计表',
                    num: '3',
                    type:'process0404'
                }],
                tableData2: [{
                    date: '2021-08-04',
                    name: '生育进度调查表（直播稻）',
                    num: '1',
                    type:'process0402'
                }, {
                    date: '2021-08-01',
                    name: '茎蘖数叶面积构成表',
                    num: '2',
                    type:'process0403'
                },{
                    date: '2021-08-03',
                    name: '苗情生育期统计表',
                    num: '3',
                    type:'process0404'
                }],
                tableData3: [{
                    date: '2021-08-02',
                    name: '水分管理记录表',
                    len:8,
                    secondNameId:'crop_01_01_table_06_01',
                    num: '1',
                    type:'process0601'
                }, {
                    date: '2021-08-04',
                    name: '点化除管理记录表',
                    len:3,
                    secondNameId:'crop_01_01_table_06_02',
                    num: '2',
                    type:'process0602'
                }, {
                    date: '2021-08-01',
                    name: '病虫害防治记录表',
                    len:4,
                    secondNameId:'crop_01_01_table_06_03',
                    num: '3',
                    type:'process0603'
                }, {
                    date: '2021-08-03',
                    name: '施肥管理记录表',
                    len:5,
                    secondNameId:'crop_01_01_table_06_04',
                    num: '4',
                    type:'process0604'
                }, {
                    date: '2021-08-03',
                    name: '化学调节剂使用记录表',
                    len:5,
                    secondNameId:'crop_01_01_table_06_05',
                    num: '5',
                    type:'process0605'
                }],
                tableData4: [{
                    date: '2021-08-02',
                    name: '水稻栽插概况调查表',
                    num: '1',
                    type:'process0101'
                }, {
                    date: '2021-08-04',
                    name: '水稻生产主推品种调查表',
                    num: '2',
                    type:'process0102'
                }, {
                    date: '2021-08-01',
                    name: '水稻苗情评价动态列表',
                    num: '3',
                    type:'process0301'
                }, {
                    date: '2021-08-03',
                    name: '水稻生产进度调查表',
                    num: '4',
                    type:'process0302'
                }, {
                    date: '2021-08-03',
                    name: '水稻播栽进度调查表',
                    num: '5',
                    type:'process0303'
                }],
                pointData:[{
                    field_1:'1221',
                    field_2:'343',
                    field_3:'34343',
                },{
                    field_1:'676',
                    field_2:'edfdf',
                    field_3:'dfd',
                }],//共有几个监测点
                pointDetailData:[],//监测点对应表格的详细数据
                tableData0601:[],//表0601对应的数据
                dialogTableVisible0201:false,
            }
        },
        created(){
            this.getTableData();
        },
        methods:{
            //获取表数据
            getTableData(){
                const _this = this;
                axios({
                    url: 'http://localhost:8888/getAllDataPoint0201',
                    method:'get',
                    params:{
                        people:202119966486260
                    }
                }).then(res=>{
                 
                    _this.pointData=res.data;
                    // for (var i = 0; i< _this.pointData.length; i++){
                    //     var y = new Date(_this.pointData[i].field_2);
                    //     _this.pointData[i].field_2 = y.getFullYear().toString();
                    //     //console.log(_this.pointData[i].field_2)
                    // }

                }).catch(err=>{
                    return this.$message.error("查询数据失败！");
                });
            },
            intoStep(i){
                this.activeNum = i;
            },
            //下一步
            NextStep() {
                this.activeNum++
            },
            //第二个下一步
            NextStep2() {
                this.activeNum++
            },
            //前一步
            prevStep2() {
                this.activeNum--
            },
            toLogin(){
                this.$router.push('/')
            },
            onSubmit() {
                
                this.dialogTableVisible1 = false;
            },
            handleClick (tab, event) {
               
            },
            handleClose(done){
                this.$confirm('确定关闭吗').then(() => {
                    // function(done)，done 用于关闭 Dialog
                    done();
                }).catch(() => {
                });
            },
            handleAdd(index,row,point){
          
                var type = "/" + row.type;
                
                this.$router.push({name:type,params:{point: point}})
            }
        }
    }
</script>

<style>
    .next_step_box .pad15{
        padding: 15px;
        border: 1px solid #e5e5e5
    }
    .cityname{
        display: inline-block;
        width: 25%;

    }
    .stepcontent{
        background: #fff
    }
    .choosecity .el-select{
        width: 100%
    }
    .choosecity a{
        color:#2683f5;
    }
    .registerpart{
        line-height: 34px;
        font-size: 12px;
        display: flex;
        justify-content: space-between;
    }
    .stepbox{
        width: 100%;
        overflow: hidden;
        padding: 15px 0;
        text-align: center;
        display: flex;
        justify-content: space-between
    }
    .el-select{
        width: 100%
    }
    .first_con{
        width: 72%;
        margin: 0 auto
    }
    .stepbox span{
        overflow: hidden;
        display: inline-block;
    }
    .registerpart .el-checkbox__label{
        font-size: 12px
    }
    .login_box{
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        background: #fff;
        height: 100%;
    }
    .txt_part h1{
        font-size: 40px;
        font-weight: bold;
        text-shadow: 1px 1px 1px #fff;
        color: #f87a23;
        line-height: 50px
    }
    .txt_part p{
        font-size: 17px;
        font-weight: bold;
        text-shadow: 1px 1px 1px #fff;
        color: #f87a23;
        line-height: 60px
    }
    .txt_part div{
        margin-top: 25%
    }
    .txt_part{
        width: 55%;
        height: 100%;
        text-align: center
    }
    .login_info{
        width: 45%;
        background: #fff;
        height: 100%;
    }
/* 
    .step1{
        width: 100%;
        height: 100%;
        background: url(../../src/assets/1_03.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    } */
    /* .step2{
        width: 100%;
        height: 100%;
        background: url(../../src/assets/a2_04.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    } */
    /* .activestep .step2{
        background: url(../../src/assets/2_04.png) no-repeat;
        width: 100%;
        height: 100%;
        background-size: 100% 100%;
        cursor: pointer;
    } */
    /* .activestep .step3{
        width: 100%;
        height: 100%;
        background: url(../../src/assets/3_05.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    }
    .step3{
        width: 100%;
        height: 100%;
        background: url(../../src/assets/a3_05.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    } */
    .stepspan{
        width: 36%;
        height: 34px;
        display: inline-block;
        line-height: 34px;
        font-size: 14px;
        margin-left: -13px
    }
    .activestep{
        color: #fff;
    }
    .login_info h3{
        text-align: center;
        font-weight: bold;
        font-size: 18px;
        line-height: 30px;
        padding-top: 10px;
        padding-bottom: 5px
    }

    .login_contact .icon-dianhua{
        font-size: 45px;
        font-weight: bold
    }
    .login_contact span{
        display: flex
    }
    .block{
        height: 100%;
    }
    .login_footer{
        width: 100%;
        height: 100px;
    }
    .next_step_box{
        width: 100%;
        height: calc(100% - 200px);
        position: relative;
        background: #f4f5fa;
        overflow: auto;
        padding-bottom: 20px
    }
    .mail{
        font-size: 12px;
        line-height: 30px;
        color: #999;
    }
    .mail i{
        font-size: 12px
    }
    .login_head_box{
        display: flex;
        justify-content: space-between;
        width: 90%;
        margin: 0 auto;
        height: 60px;
        line-height: 60px;
    }
    .login_head_box img{
        width: 120px;
        height: 32px;
        display: block;
        margin-top: 14px
    }
    .login_head_box span{
        font-size: 12px;
        padding: 0 15px;
        color: #999
    }
    .login_head_box a{
        background: #028ffb;
        font-size: 13px;
        color: #fff;
        padding: 4px 20px;
        border-radius: 4px;
        border: 1px solid #028ffb;
        cursor: pointer;

    }
    .login_footer_box .a_title{
        color: #000;
        font-size: 13px;
        display: block;
        border-left: 4px solid #FF5722;
        padding: 1px 5px;
    }
    .login_footer_box a{
        font-size: 11px;
        color: #666;
        padding: 1px 7px;
    }
    .login_footer_box ul li{
        margin: 5px 0
    }
    .login_head_box a:hover{
        color: #028ffb;
        background: #fff
    }
    .login_footer_box ul {
        width: 33%
    }
    .el-carousel__button{
        display: none
    }
    .login_footer_box{
        width: 40%;
        margin: 0 auto;
        height: 90px;
        margin-top: 20px;
        display: flex;
        justify-content: space-between
    }
    .login_footer_box .wid50{
        width: 49%;
        height: 100%
    }
    .flexul{
        display: flex;
        justify-content: space-between;
    }
    .login_footer_box .wid50:first-child{
        border-right: 0.5px solid #e2e2e2
    }
    .sucess_msg i{
        font-size: 20px;
        margin: 0 15px;
    }
    .login_box .verifyform .el-dialog{
        width: 336px
    }
    .login_box .el-dialog__header{
        display: none
    }
    .closebtn{
        position: absolute;
        top: 8px;
        right: 13px;
        color: #bf0505;
        cursor: pointer;
    }
    .panel-body ul li{
        float: left;
        padding: 10px 60px;
    }
    .panel-body ul li:hover{
        color: #088cf5;
        cursor: pointer;
    }
    .demo-table-expand {
        font-size: 0;
    }
    .demo-table-expand label {
        width: 110px;
        color: #99a9bf;
    }
    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
    .user-avator{
        float:left;
        width:40px;
        height:40px;
        border-radius:50%;
        border:1px solid #ccc;
        overflow:hidden;
        margin-right:20px;
    }
</style>
