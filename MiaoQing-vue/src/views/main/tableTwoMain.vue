<template>
    <div class="login_box">
        <div class="next_step_box">
            <div class="first_con">
                <div class="stepbox">
                    <span class="stepspan activestep">
                        <div class="step1" @click="intoStep(0)">
                            1.监测点信息管理
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 1 ||activeNum == 2||activeNum == 3||activeNum == 4?'activestep':''">
                        <div class="step2" @click="intoStep(1)">
                            2.监测点信息填报
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 2||activeNum == 3||activeNum == 4?'activestep':''">
                        <div class="step2" @click="intoStep(2)">
                            3.监测点信息查看
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 3||activeNum == 4?'activestep':''">
                        <div class="step2" @click="intoStep(3)">
                            4.全县信息填报
                        </div>
                    </span>
                    <span class="stepspan" :class="activeNum == 4?'activestep':''">
                        <div class="step3" @click="intoStep(4)">
                            5.监测报告上传
                        </div>                       
                    </span>
                </div>
                <div class="stepcontent">
                    <!--第一步-->
                    <div v-show="activeNum==0" class="pad15" >
                        <div>
                            <!--<router-link  to="/table0201" >-->
                                <el-button type="success" icon="el-icon-plus"
                                           style="float: right;margin-bottom: 20px;margin-right: 10px"
                                           @click="AddPoint">
                                    申请
                                </el-button>
                            <!--</router-link>-->
                        </div>
                        <div style="margin-top: 30px">
                            <el-tabs >
                                <el-tab-pane >
                                    <span slot="label"><i class="el-icon-s-operation"></i> 当年监测点</span>
                                    <el-table
                                            border
                                            :data="pointData"
                                            style="width: 100%" height="480">
                                        <el-table-column
                                                label="监测点"
                                                style="text-align: center"
                                                min-width="33%">
                                            <template slot-scope="scope">
                                                <el-popover trigger="hover" placement="top">
                                                    <p >监测点名: {{ scope.row.field_1 }}</p>
                                                    <p>县市区: {{ scope.row.field_3_3 }}</p>
                                                    <div slot="reference" class="name-wrapper">
                                                        <el-tag size="medium" style="background: rgba(63, 117, 1, 0.1);color: black">{{ scope.row.field_1 }}</el-tag>
                                                    </div>
                                                </el-popover>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                label="监测年份"
                                                min-width="33%">
                                            <template slot-scope="scope">
                                                <i class="el-icon-time"></i>
                                                <span style="margin-left: 10px">{{scope.row.field_2 }}</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="操作"
                                                         min-width="40%">
                                            <template slot-scope="scope">
                                                <el-button
                                                        size="mini"
                                                        type="success"
                                                        @click="handleCheck(scope.$index, scope.row,1)">查看
                                                </el-button>
                                                <el-button
                                                        size="mini"
                                                        type="danger"
                                                        @click="handleCheck(scope.$index, scope.row,2)">更新
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
                                <el-tab-pane >
                                    <span slot="label"><i class="el-icon-s-operation"></i> 历史监测点</span>
                                    <el-table
                                            border
                                            :data="historyPointData"
                                            style="width: 100%" height="480">
                                        <el-table-column
                                                label="监测点"
                                                style="text-align: center"
                                                min-width="33%">
                                            <template slot-scope="scope">
                                                <el-popover trigger="hover" placement="top">
                                                    <p >监测点名: {{ scope.row.field_1 }}</p>
                                                    <p>县市区: {{ scope.row.field_3_3 }}</p>
                                                    <div slot="reference" class="name-wrapper">
                                                        <el-tag size="medium" style="background: rgba(63, 117, 1, 0.1);color: black">{{ scope.row.field_1 }}</el-tag>
                                                    </div>
                                                </el-popover>
                                            </template>
                                        </el-table-column>
                                        <el-table-column
                                                label="监测年份"
                                                min-width="33%">
                                            <template slot-scope="scope">
                                                <i class="el-icon-time"></i>
                                                <span style="margin-left: 10px">{{scope.row.field_2 }}</span>
                                            </template>
                                        </el-table-column>
                                        <el-table-column label="操作"
                                                         min-width="40%">
                                            <template slot-scope="scope">
                                                <el-button
                                                        size="mini"
                                                        type="success"
                                                        @click="handleCheckHistory(scope.$index, scope.row,1)">查看
                                                </el-button>
                                                <template v-if="scope.row.field_2 == lastYear && !allHistoryPointDataL.includes(scope.row.field_1)">
                                                    <el-button
                                                        size="mini"
                                                        type="info"
                                                        @click="isContinuetoUse(scope.$index, scope.row)">延用
                                                    </el-button>
                                                </template>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
                            </el-tabs>
                        </div>
                        <div style="margin-top: 20px;text-align:center">
                            <el-button type="success" @click="NextStep()" >下一步</el-button>
                            <router-link  to="/main">
                                <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                            </router-link>
                        </div>
                    </div>
                    
                    <!--第二步-->
                    <div v-show="activeNum==1"  class="pad15" >
                        <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                            <template v-for="(item,index) in pointData">
                                <el-tab-pane :label=pointData[index].field_1 :name=index :key="item.index">
                                    <template v-if="item.field_6_1 == '无盘旱育抛栽' || item.field_6_1 =='人工移栽'|| item.field_6_1 =='机械插秧' || item.field_6_1 =='盘育抛栽'">
                                        <div>
                                            <div>
                                                <el-tabs tab-position="top">
                                                    <el-tab-pane label="进程表">
                                                        <el-table
                                                                border
                                                                :data="tableData1"
                                                                stripe
                                                                style="width: 100%" height="480">
                                                            <el-table-column
                                                                    label="序号"
                                                                    min-width="10%">
                                                                <template slot-scope="scope" >
                                                                    <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column
                                                                    label="表名"
                                                                    min-width="50%">
                                                                <template slot-scope="scope" >
                                                                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column label="操作" min-width="30%">
                                                                <template slot-scope="scope" >
                                                                    <el-button
                                                                            size="mini"
                                                                            type="success"
                                                                            @click="handleAddPoint(scope.$index, scope.row,item.field_1)">添加
                                                                    </el-button>
                                                                    <el-button
                                                                            size="mini"
                                                                            type="warning"
                                                                            @click="handleHistoryPointData(scope.$index, scope.row,item.field_1)">查看历史
                                                                    </el-button>
                                                                </template>
                                                            </el-table-column>
                                                        </el-table>
                                                    </el-tab-pane>
                                                    <el-tab-pane label="记录表">
                                                        <el-table
                                                                border
                                                                :data="tableData3"
                                                                stripe
                                                                style="width: 100%" height="480">
                                                            <el-table-column
                                                                    label="序号"
                                                                    min-width="10%">
                                                                <template slot-scope="scope" >
                                                                    <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column
                                                                    label="表名"
                                                                    min-width="50%">
                                                                <template slot-scope="scope" >
                                                                    <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                                </template>
                                                            </el-table-column>
                                                            <el-table-column label="操作" min-width="30%">
                                                                <template slot-scope="scope">
                                                                    <el-button
                                                                            size="mini"
                                                                            type="success"
                                                                            @click="handleAddPoint(scope.$index, scope.row,item.field_1)">添加
                                                                    </el-button>
                                                                    <el-button
                                                                            size="mini"
                                                                            type="warning"
                                                                            @click="handleHistoryPointData(scope.$index, scope.row,item.field_1)">查看历史
                                                                    </el-button>
                                                                </template>
                                                            </el-table-column>
                                                        </el-table>
                                                    </el-tab-pane>
                                                </el-tabs>
                                            </div>
                                            <!-- <div style="margin-top: 350px;text-align:center">
                                                <el-button type="success" @click="prevStep2()" >返回上一步</el-button>
                                                <el-button type="success" @click="NextStep2()">下一步</el-button>
                                                <router-link  to="/main">
                                                    <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                                                </router-link>
                                            </div> -->
                                        </div>
                                    </template>
                                    <template v-if="item.field_6_1=='撒直播（水）' || item.field_6_1=='撒直播（旱）'||
                                            item.field_6_1=='机条播（水）' || item.field_6_1=='机条播（旱）'">
                                        <div>
                                            <div>
                                                <div style="position: relative">
                                                    <el-tabs tab-position="top">
                                                        <el-tab-pane >
                                                            <span slot="label"><i class="el-icon-s-operation"></i> 进程表</span>
                                                            <el-table
                                                                    border
                                                                    :data="tableData2"
                                                                    stripe
                                                                    style="width: 100%" height="480">
                                                                <el-table-column
                                                                        label="序号"
                                                                        min-width="10%">
                                                                    <template slot-scope="scope" >
                                                                        <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                                    </template>
                                                                </el-table-column>
                                                                <el-table-column
                                                                        label="表名"
                                                                        min-width="50%">
                                                                    <template slot-scope="scope" >
                                                                        <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                                    </template>
                                                                </el-table-column>
                                                                <!-- <el-table-column
                                                                        label="时间"
                                                                        min-width="20%">
                                                                    <template slot-scope="scope" >
                                                                        <i class="el-icon-time"></i>
                                                                        <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                                                    </template>
                                                                </el-table-column> -->
                                                                <el-table-column label="操作" min-width="30%">
                                                                    <template slot-scope="scope">
                                                                        <el-button
                                                                                size="mini"
                                                                                type="success"
                                                                                @click="handleAddPoint(scope.$index, scope.row,item.field_1)">添加
                                                                        </el-button>
                                                                        <el-button
                                                                                size="mini"
                                                                                type="warning"
                                                                                @click="handleHistoryPointData(scope.$index, scope.row,item.field_1)">查看历史
                                                                        </el-button>
                                                                    </template>
                                                                </el-table-column>
                                                            </el-table>
                                                        </el-tab-pane>
                                                        <el-tab-pane >
                                                            <span slot="label"><i class="el-icon-s-order"></i> 记录表</span>
                                                            <el-table
                                                                    border
                                                                    :data="tableData3"
                                                                    stripe
                                                                    style="width: 100%" height="480">
                                                                <el-table-column
                                                                        label="序号"
                                                                        min-width="10%">
                                                                    <template slot-scope="scope" >
                                                                        <span style="margin-left: 10px">{{ scope.row.num }}</span>
                                                                    </template>
                                                                </el-table-column>
                                                                <el-table-column
                                                                        label="表名"
                                                                        min-width="50%">
                                                                    <template slot-scope="scope" >
                                                                        <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                                                    </template>
                                                                </el-table-column>
                                                                <!-- <el-table-column
                                                                        label="时间"
                                                                        min-width="20%">
                                                                    <template slot-scope="scope" >
                                                                        <i class="el-icon-time"></i>
                                                                        <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                                                    </template>
                                                                </el-table-column> -->
                                                                <el-table-column label="操作" min-width="30%">
                                                                    <template slot-scope="scope">
                                                                        <el-button
                                                                                size="mini"
                                                                                type="success"
                                                                                @click="handleAddPoint(scope.$index, scope.row,item.field_1)">添加
                                                                        </el-button>
                                                                        <el-button
                                                                                size="mini"
                                                                                type="warning"
                                                                                @click="handleHistoryPointData(scope.$index, scope.row,item.field_1)">查看历史
                                                                        </el-button>
                                                                    </template>
                                                                </el-table-column>
                                                            </el-table>
                                                        </el-tab-pane>
                                                    </el-tabs>
                                                </div>
                                                <!-- <div style="margin-top: 350px;text-align:center" >
                                                    <el-button type="success" @click="prevStep2()">返回上一步</el-button>
                                                    <el-button type="success" @click="NextStep2()">下一步</el-button>
                                                    <router-link  to="/main">
                                                        <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                                                    </router-link>
                                                </div> -->
                                            </div>
                                        </div>
                                    </template>
                                </el-tab-pane>
                            </template>
                        </el-tabs>
                    	
                    	<!-- 底部按钮 -->
                    	<div style="margin-top: 20px;text-align:center">
                    	    <el-button type="success" @click="prevStep2()">返回上一步</el-button>
                    		<el-button type="success" @click="NextStep2()">下一步</el-button>
                    	    <router-link  to="/main">
                    	        <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                    	    </router-link>
                    	</div>
                    </div>
                    
                    <!--第三步页面填写完成等待评估-->
                    <div class="pad15" v-show="activeNum==3">
                        <div>
                            <el-tabs tab-position="top" >
                                <el-tab-pane >
                                    <span slot="label"><i class="el-icon-s-operation"></i> 进程表</span>
                                    <el-table
                                            border
                                            :data="tableData4"
                                            stripe
                                            style="width: 100%" height="480">
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
                                                min-width="50%">
                                            <template slot-scope="scope" >
                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                            </template>
                                        </el-table-column>
                                        <!-- <el-table-column
                                                label="时间"
                                                min-width="20%">
                                            <template slot-scope="scope" >
                                                <i class="el-icon-time"></i>
                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                            </template>
                                        </el-table-column> -->
                                        <el-table-column label="操作" min-width="30%">
                                            <template slot-scope="scope">
                                                <el-button
                                                        size="mini"
                                                        type="success"
                                                        @click="handleAdd(scope.$index, scope.row)">添加
                                                </el-button>
                                                <el-button
                                                        size="mini"
                                                        type="warning"
                                                        @click="handleHistoryData(scope.$index, scope.row)">查看历史
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
                                <el-tab-pane >
                                    <span slot="label"><i class="el-icon-s-operation"></i> 记录表</span>
                                    <el-table
                                            border
                                            :data="tableData6"
                                            stripe
                                            style="width: 100%" height="480">
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
                                                min-width="50%">
                                            <template slot-scope="scope" >
                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                            </template>
                                        </el-table-column>
                                        <!-- <el-table-column
                                                label="时间"
                                                min-width="20%">
                                            <template slot-scope="scope" >
                                                <i class="el-icon-time"></i>
                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                            </template>
                                        </el-table-column> -->
                                        <el-table-column label="操作" min-width="30%">
                                            <template slot-scope="scope">
                                                <el-button
                                                        size="mini"
                                                        type="success"
                                                        @click="handleAdd(scope.$index, scope.row)">添加
                                                </el-button>
                                                <el-button
                                                        size="mini"
                                                        type="warning"
                                                        @click="handleHistoryData(scope.$index, scope.row)">查看历史
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
                                <el-tab-pane >
                                    <span slot="label"><i class="el-icon-s-operation"></i> 气象及灾害表</span>
                                    <el-table
                                            border
                                            :data="tableData5"
                                            stripe
                                            style="width: 100%" height="480">
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
                                                min-width="50%">
                                            <template slot-scope="scope" >
                                                <span style="margin-left: 10px">{{ scope.row.name }}</span>
                                            </template>
                                        </el-table-column>
                                        <!-- <el-table-column
                                                label="时间"
                                                min-width="20%">
                                            <template slot-scope="scope" >
                                                <i class="el-icon-time"></i>
                                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                                            </template>
                                        </el-table-column> -->
                                        <el-table-column label="操作" min-width="30%">
                                            <template slot-scope="scope">
                                                <template v-if="scope.row.name != '气象资料列表' && scope.row.name != '抽穗期前、后各15天气象资料' ">
                                                    <el-button
                                                            size="mini"
                                                            type="success"
                                                            @click="handleAdd(scope.$index, scope.row)">添加
                                                    </el-button>
                                                </template>
                                                <el-button
                                                        size="mini"
                                                        type="warning"
                                                        @click="handleHistoryData(scope.$index, scope.row)">查看历史
                                                </el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                                </el-tab-pane>
                            </el-tabs>
                        </div>
                    	
                    	<!-- 底部按钮 -->
                        <div style="margin-top: 20px;text-align:center">
                            <el-button type="success" @click="prevStep2()">返回上一步</el-button>
                    		<el-button type="success" @click="NextStep2()">下一步</el-button>
                            <router-link  to="/main">
                                <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                            </router-link>
                        </div>
                    </div>
					
                    <!--第四步增加报告-->
                    <div v-show="activeNum==4"  class="pad15">
                        <div>
                            <el-tabs tab-position="top" >
                                    <!-- <el-breadcrumb style="font-size:18px;margin:20px 0 20px 0px;">
                                        <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
                                        <el-breadcrumb-item>研究报告</el-breadcrumb-item>
                                    </el-breadcrumb> -->
                                    <el-select v-model="year" placeholder="请选择报告年份" @change="changeYear">
                                        <el-option label="2022" value="2022"></el-option>
                                        <el-option label="2021" value="2021"></el-option>
                                        <el-option label="2020" value="2020"></el-option>
                                        <el-option label="2019" value="2019"></el-option>
                                    </el-select>
                                    <el-button style="float:right" type="success" @click="centerDialogVisible = true">
                                        新增报告
                                    </el-button>
                                    <el-table
                                       :data="fileList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                                       style="width: 100%;" height="480">
                                        <el-table-column
                                                label="序号"
                                                type="index"
                                                width="100"
                                                align="center">
                                        </el-table-column>
                                                <el-table-column
                                                label="报告名称"
                                                prop="name">
                                                </el-table-column>
                                                <el-table-column
                                                label="作物类型"
                                                prop="crop">
                                                </el-table-column>
                                                <el-table-column
                                                label="报告年份"
                                                prop="modification_time">
                                        </el-table-column>
                                        <el-table-column align="right">
                                            <template slot="header">
                                                <el-input
                                                v-model="search"
                                                size="mini"
                                                placeholder="输入关键字搜索"/>
                                            </template>
                                            <template slot-scope="scope">
                                                <el-button
                                                size="mini"
                                                @click="viewReport(scope.row)">预览</el-button>
                                            </template>
                                        </el-table-column>
                                    </el-table>
                    
                                    <!-- <el-tab-pane>
                                        
                                    </el-tab-pane> -->
                             </el-tabs>
                             
                        </div>
						
						<!-- 底部按钮 -->
                    	<div style="margin-top: 20px; text-align:center">
                            <el-button type="success" @click="prevStep2()">返回上一步</el-button>
                            <router-link  to="/main">
                                <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                            </router-link>
                        </div>
                                  
                    </div>
                    <!--第三步新增-->
                    <div v-show="activeNum==2" class="pad15" >
                        <el-tabs v-model="activeNameq" @tab-click="handleClick">
                                        <el-tab-pane label="进程表" name="1">
                                        <div class="tableRange"><!--028ffb30b233-->
                                            <el-table :data="tableData7" @cell-click="handleCell2" :header-cell-style="{background:'#67c23a',color:'#FFFFFF',fontSize:'14px'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'12px',color:'#000000',cursor:'pointer'}" row-key="id" stripe>
                                                <el-table-column align="center" prop="table_01" label="双季稻生育进度" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_02" label="茎蘖数面积" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_03" label="苗情生育期" >
                                                </el-table-column>
                                            </el-table>
                                        </div>
                                        <!-- <div class="otherStyle">
                                           
                                            <el-collapse  accordion>
                                            <el-collapse-item style="">
                                                <template slot="title">
                                                <span style="margin-left:15px;font-size:15px;font-weight:bold"><el-button type="info" plain>表格数据导出Excel</el-button></span>
                                                </template>
                                                <div class="checkBox">
                                                <el-checkbox style="margin-left:15px" :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                                                <div style="margin:15px 0;margin-left:15px">
                                                    <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
                                                    <el-checkbox v-for="city in cities" :label="city" :key="city" style="width:250px; margin-left: 10px;">{{city}}</el-checkbox>
                                                    </el-checkbox-group> 
                                                </div>
                                                </div>
                                                <div style="margin-top:10px;margin-left:20px">
                                                <el-button type="info" @click="getExcel"> 导出 Excel </el-button>
                                                </div>
                                            </el-collapse-item>
                                            </el-collapse>
                                            
                              
                                        </div> -->
                                        </el-tab-pane>
                                        
                                        <el-tab-pane label="记录表" name="2">
                                        <div class="tableRange">
                                            <el-table :data="tableData8" @cell-click="handleCell" :header-cell-style="{background:'#67c23a',color:'#FFFFFF',fontSize:'14px'}" :row-style="{height: '30px'}" :cell-style="{padding:'0',fontSize:'12px',color:'#000000',cursor:'pointer'}" row-key="id" stripe>
                                                <el-table-column align="center" prop="table_01" label="水分管理" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_02" label="点化除管理" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_03" label="病虫害防治">
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_04" label="施肥管理" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_05" label="化学调节剂" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_06" width="190%" label="洪涝灾害" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_07" label="灌浆速率（早稻）" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_08" label="灌浆速率（晚稻）" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_12" label="叶片含氮测定" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_10" label="生产成本(早稻)" >
                                                </el-table-column>
                                                <el-table-column align="center" prop="table_11" label="生产成本(晚稻)" >
                                                </el-table-column>
                                            </el-table>
                                        </div>
                                        <!-- <div class="otherStyle">
                                            <el-collapse  accordion>
                                            <el-collapse-item style="">
                                                <template slot="title">
                                                <span style="margin-left:15px;font-size:15px;font-weight:bold"><el-button type="info" plain>表格数据导出Excel</el-button></span>
                                                </template>
                                                <div class="checkBox">
                                                <el-checkbox style="margin-left:15px" :indeterminate="isIndeterminate2" v-model="checkAll2" @change="handleCheckAllChange2">全选</el-checkbox>
                                                <div style="margin:15px 0;margin-left:15px">
                                                    <el-checkbox-group v-model="checkedCities2" @change="handleCheckedCitiesChange2">
                                                    <el-checkbox v-for="city2 in cities2" :label="city2" :key="city2" style="width:250px; margin-left: 10px;">{{city2}}</el-checkbox>
                                                    </el-checkbox-group> 
                                                </div>
                                                </div>
                                                <div style="margin-top:10px;margin-left:20px">
                                                <el-button type="info" @click="getExcel2"> 导出 Excel </el-button>
                                                </div>
                                            </el-collapse-item>
                                            </el-collapse>
                                            
                                        </div> -->
                                        </el-tab-pane>
                        </el-tabs>
                        <div style="margin-top: 20px;text-align:center">
                            <el-button type="success" @click="NextStep()" >下一步</el-button>
                            <router-link  to="/main">
                                <el-button type="success" style="margin-left: 10px">返回首页</el-button>
                            </router-link>
                        </div>
                    </div>


                    
                   


                    <!-- <div class="otherStyle">
                                    <el-collapse  accordion>
                                    <el-collapse-item style="">
                                        <template slot="title">
                                        <span style="margin-left:15px;font-size:15px;font-weight:bold"><el-button type="info" plain>表格数据导出Excel</el-button></span>
                                        </template>
                                        <div class="checkBox">
                                        <el-checkbox style="margin-left:15px" :indeterminate="isIndeterminate2" v-model="checkAll2" @change="handleCheckAllChange2">全选</el-checkbox>
                                        <div style="margin:15px 0;margin-left:15px">
                                            <el-checkbox-group v-model="checkedCities2" @change="handleCheckedCitiesChange2">
                                            <el-checkbox v-for="city2 in cities2" :label="city2" :key="city2" style="width:250px; margin-left: 10px;">{{city2}}</el-checkbox>
                                            </el-checkbox-group> 
                                        </div>
                                        </div>
                                        <div style="margin-top:10px;margin-left:20px">
                                        <el-button type="info" @click="getExcel2"> 导出 Excel </el-button>
                                        </div>
                                    </el-collapse-item>
                                    </el-collapse>
                                   
                    </div> -->




				</div>
            </div>
        </div>
          <!-- 模态框---报告模板选择框 -->
        <el-dialog
                title="请选择一个报告模板"
                :visible.sync="centerDialogVisible"
                width="45%"
                center>
            <el-collapse>
                <el-collapse-item title="早稻模板" name="1">
                    <div>
                        <el-radio-group v-model="checkedTemplate">
                            <el-radio label="zaodao_template1" border>双季早稻栽播期苗情</el-radio>
                            <el-radio label="zaodao_template2" border>早稻分蘖期苗情</el-radio>
                            <el-radio label="zaodao_template3" border>双季早稻分蘖孕穗期苗情</el-radio>
                        </el-radio-group>
                    </div>
                    <div style="margin:20px 0 0 0">
                        <el-radio-group v-model="checkedTemplate">
                            <el-radio label="zaodao_template4" border>早稻孕穗－抽穗期苗情</el-radio>
                            <el-radio label="zaodao_template5" border>早稻预测产结果</el-radio>
                            <el-radio label="zaodao_template6" border>双季稻早稻测产结果</el-radio> 
                        </el-radio-group>
                    </div>
                </el-collapse-item>
                <el-collapse-item title="晚稻模板" name="2">
                    <div style="margin:20px 0 0 0">
                        <el-radio-group v-model="checkedTemplate">
                                <el-radio label="wandao_template1" border>双季晚稻分蘖期苗情</el-radio>
                                <el-radio label="wandao_template2" border>双季晚稻分蘖－孕穗期苗情</el-radio>
                                <el-radio label="wandao_template3" border>双季晚稻灌浆期预估产</el-radio>
                        </el-radio-group>
                    </div>
                    <div style="margin:20px 0 0 0">
                        <el-radio-group v-model="checkedTemplate">
                                <el-radio label="wandao_template4" border>双季晚稻测产结果</el-radio>
                        </el-radio-group>
                    </div>
                </el-collapse-item>
            </el-collapse>
            
            <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addReport">确 定</el-button>
            </span>
        </el-dialog>
        <!--模态框-->
        <el-dialog
                title="" :visible.sync="dialogTableVisible0201"
                :before-close="handleClose"
                class="verifyform"
                width="70%">
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
                    <td colspan="2">
                        <el-date-picker
                            v-model="data.field_2"
                            value-format=“yyyy-MM-dd”
                            type="year" style="width: 100%;"
                            :disabled="true"
                            placeholder="选择年">
                        </el-date-picker>
                    </td>
                </tr>
                <tr>
                    <td rowspan="2">地址</td>
                    <td>市</td>
                    <td>县（市、区</td>
                    <td>镇（乡）</td>
                    <td>村名</td>
                    <td colspan="2"><el-input v-model="data.field_3_4" :disabled="flag==1"></el-input></td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_3_1" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_3_2" :disabled="true"></el-input></td>
                    <td><el-input v-model="data.field_3_3" :disabled="flag==1"></el-input></td>
                    <td>户主</td>
                    <td colspan="2"><el-input v-model="data.field_3_5" :disabled="flag==1"></el-input></td>
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
                    <td><el-input v-model="data.field_4_1"  :disabled="flag==1" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                    <td>
                        <template v-if="flag==1">
                            <el-input v-model="data.field_4_2" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <el-select v-model="data.field_4_2" clearable placeholder="请选择">
                                <el-option
                                        v-for="(item,index) in listArray3"
                                        :key="index"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                        <!--<el-input v-model="data.field_4_2"  :disabled="true"></el-input>-->
                    </td>
                    <td><el-input v-model="data.field_4_3" :disabled="flag==1"></el-input></td>
                    <td>
                        <template v-if="flag==1">
                            <el-input v-model="data.field_4_4" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <!--<select class="select" id="select_4" >-->
                                <!--<option value="noValue">&#45;&#45;请选择&#45;&#45;</option>-->
                            <!--</select>-->
                            <el-select v-model="data.field_4_4" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in listArray4"
                                        :key="item.list"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                    </td>
                    <td colspan="2">
                        <template v-if="flag==1">
                            <el-input v-model="data.field_4_5" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <!--<select class="select" id="select_5" >-->
                                <!--<option value="noValue">&#45;&#45;请选择&#45;&#45;</option>-->
                            <!--</select>-->
                            <el-select v-model="data.field_4_5" clearable placeholder="请选择">
                                <el-option
                                        v-for="item in listArray5"
                                        :key="item.list"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                    </td>
                </tr>
                <tr>
                    <td>全氮（g/KG）</td>
                    <td>速效磷(g/Kg)</td>
                    <td>速效钾(g/Kg)</td>
                    <td>有机质(g/Kg)</td>
                    <td colspan="2">秸秆还田量(kg/亩)</td>
                </tr>
                <tr>
                    <td><el-input v-model="data.field_4_6" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_4_7" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_4_8" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_4_9" :disabled="flag==1"></el-input></td>
                    <td colspan="2"><el-input v-model="data.field_4_10"  :disabled="flag==1" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                </tr>
                <tr>
                    <td rowspan="2">品种、品系信息</td>
                    <td colspan="2">名称</td>
                    <td colspan="2">稻作类型</td>
                    <td colspan="2">品系类型</td>
                </tr>
                <tr>
                    <td colspan="2"><el-input v-model="data.field_5_1" :disabled="flag==1"></el-input></td>
                    <td colspan="2">
                        <template v-if="flag==1">
                            <el-input v-model="data.field_5_2" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <el-select v-model="data.field_5_2" clearable placeholder="请选择">
                                <el-option
                                        v-for="(item,index) in listArray6"
                                        :key="index"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                        <!-- <el-input v-model="data.field_5_2"  :disabled="true"></el-input> -->
                    </td>
                    <td colspan="2">
                        <template v-if="flag==1">
                            <el-input v-model="data.field_5_3" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <el-select v-model="data.field_5_3" clearable placeholder="请选择">
                                <el-option
                                        v-for="(item,index) in listArray7"
                                        :key="index"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                        <!-- <el-input v-model="data.field_5_3"  :disabled="true"></el-input> -->
                    </td>
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
                    <td >
                        <template v-if="flag==1">
                            <el-input v-model="data.field_6_1" :disabled="flag==1"></el-input>
                        </template>
                        <template v-if="flag==2">
                            <el-select v-model="data.field_6_1" clearable placeholder="请选择">
                                <el-option
                                        v-for="(item,index) in listArray8"
                                        :key="index"
                                        :label="item.list"
                                        :value="item.list">
                                </el-option>
                            </el-select>
                        </template>
                        <!-- <el-input v-model="data.field_6_1"  :disabled="true"></el-input> -->
                    </td>
                    <!--<td>&lt;!&ndash; <el-input v-model="data.field_3_4_2"></el-input> &ndash;&gt;-->
                    <td>
                        <el-date-picker
                            v-model="data.field_6_2"
                            type="date"
                            :disabled="flag==1"
                            value-format=“yyyy-MM-dd”
                            placeholder="选择日期">
                        </el-date-picker>
                    </td>
                    <!-- <td ><el-input v-model="data.field_3_4_2" :disabled="true"></el-input></td> -->
                    <td><el-input v-model="data.field_6_3"  :disabled="flag==1"></el-input></td>
                    <td> <el-input v-model="data.field_6_4"  :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_5"  :disabled="flag==1"></el-input></td>
                    <td>
                        <el-date-picker
                        v-model="data.field_6_6"
                        type="date"
                        :disabled="flag==1"
                        value-format=“yyyy-MM-dd”
                        placeholder="选择日期">
                        </el-date-picker>
                    </td>
                    <!-- <td colspan="2"><el-input v-model="data.field_3_4_6" :disabled="true"></el-input></td> -->
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
                    <td><el-input v-model="data.field_6_7" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_8" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_9" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_10" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_11" :disabled="flag==1"></el-input></td>
                    <td><el-input v-model="data.field_6_12" :disabled="flag==1"></el-input></td>
                </tr>
            </table>
            <div style="text-align: center">
                <template v-if="flag==2">
                    <el-button style="display: inline-block;margin-top: 50px ;" @click="getFormList('1')">提交</el-button>
                </template>
            </div>
        </el-dialog>
        <el-dialog
                title="监测点申请"
                :visible.sync="dialogInputTableVisible0201"
                :before-close="handleClose"
                width="60%">
                <el-form  label-width="100px" >
                    <el-row>
                        <el-col :span="9">
                            <el-form-item label="监测年份" >
                                <el-date-picker
                                        v-model="data.field_2"
                                        value-format=“yyyy-MM-dd”
                                        :disabled="true"
                                        type="year" style="width: 100%;"
                                        placeholder="选择年">
                                </el-date-picker>
                                <!-- <el-input v-model="data.field_2" :disabled="true"></el-input> -->
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="9">
                            <el-form-item label="市">
                                <el-input v-model="data.field_3_1" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col offset=3 :span="9">
                            <el-form-item label="县（市、区）">
                                <el-input v-model="data.field_3_2" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="9">
                            <el-form-item label="镇（乡）">
                                <el-input v-model="data.field_3_3"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col offset=3 :span="9">
                            <el-form-item label="村名">
                                <el-input v-model="data.field_3_4"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="9">
                             <el-form-item label="户主">
                                <el-input v-model="data.field_3_5"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col offset=3 :span="9">
                            <el-form-item label="品种名称">
                                <!--<el-input v-model="data.field_3_3_1"></el-input>-->
                                <el-select
                                        v-model="data.field_5_1"
                                        multiple
                                        filterable
                                        remote
                                        allow-create
                                        reserve-keyword
                                        placeholder="请输入关键词"
                                        :remote-method="remoteMethod"
                                        :loading="loading">
                                    <el-option
                                            v-for="(item,index) in options"
                                            :key="index"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="地区类型">
                                <el-select v-model="data.field_4_2" clearable placeholder="请选择">
                                    <el-option
                                            v-for="item in listArray3"
                                            :key="item.list"
                                            :label="item.list"
                                            :value="item.list">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="稻作类型">
                                <el-select v-model="data.field_5_2" clearable placeholder="请选择">
                                    <el-option
                                            v-for="item in listArray6"
                                            :key="item.list"
                                            :label="item.list"
                                            :value="item.list">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="品系类型">
                                <el-select v-model="data.field_5_3" clearable placeholder="请选择">
                                    <el-option
                                            v-for="item in listArray7"
                                            :key="item.list"
                                            :label="item.list"
                                            :value="item.list">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="栽植方式">
                                <el-select v-model="data.field_6_1" clearable placeholder="请选择">
                                    <el-option
                                            v-for="item in listArray8"
                                            :key="item.list"
                                            :label="item.list"
                                            :value="item.list">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <div style="text-align: center">
                    <el-button style="display: inline-block;margin-top: 50px ;" @click="getFormList('1')">提交申请</el-button>
                </div>
        </el-dialog>
    </div>
</template>
<script>
    import axios from 'axios'

    const cityOptions2 = ['水稻栽插概况调查表', '水稻生产主推品种调查表', '水稻监测点汇总表' ,'水稻播栽进度调查表（早稻）' ,'水稻播栽进度调查表（晚稻）' ,'水稻苗情评价动态列表（早稻-移栽稻）' ,
                      '水稻苗情评价动态列表（早稻-直播稻）' ,'水稻苗情评价动态列表（晚稻-移栽稻）' ,'水稻苗情评价动态列表（晚稻-直播稻）' ,'中稻生育进度调查表（移栽稻）' ,'直播稻生育进度调查表（直播稻）' ,
                      '水稻监测点茎蘖数叶面积构成表' ,'水稻监测点苗情生育期统计表' ,'叶片含氮测定仪导出数据' ,'水稻监测点水分管理记录表' ,'水稻监测点化除管理记录表' ,'水稻监测点病虫害防治记录表' ,
                      '水稻监测点施肥管理记录表' ,'水稻监测点化学调节剂使用记录表' ,'水稻双季稻苗情监测预测产表' ,'水稻双季稻收获进度调查（早稻）' ,'水稻双季稻收获进度调查（晚稻）' ,'水稻双季稻测产表' ,
                      '水稻生产成本效益统计表（早稻）' ,'水稻生产成本效益统计表（晚稻）' ,'气象资料列表' ,'抽穗期前、后各15天气象资料' ,'水稻倒伏灾害表' ,'水稻低温冷害表','水稻高温热害表' ,'水稻涝害调查表' ,
                      '水稻监测点洪涝灾害表', '水稻监测点灌浆速率调查表（早稻）', '水稻监测点灌浆速率调查表（晚稻）'];
    const cityOptions = ['水稻栽插概况调查表', '水稻生产主推品种调查表', '水稻监测点汇总表', '水稻播栽进度调查表','水稻苗情评价动态列表（育秧）','水稻苗情评价动态列表（直播）','中稻生育进度调查表（移栽稻）',
                     '直播稻生育进度调查表（直播稻）','水稻监测点茎蘖数叶面积构成表','水稻监测点苗情生育期统计表', '叶片含氮测定仪导出数据','水稻监测点水分管理记录表','水稻监测点化除管理记录表',
                     '水稻监测点病虫害防治记录表','水稻监测点施肥管理记录表','水稻监测点化学调节剂使用记录表','水稻单季稻苗情监测预测产表','水稻单季稻收获进度调查','水稻单季稻测产表','水稻生产成本效益统计表',
                     '气象资料列表','抽穗期前、后各15天气象资料','水稻倒伏灾害表','水稻低温冷害表','水稻高温热害表','水稻涝害调查表','水稻监测点洪涝灾害表','水稻监测点灌浆速率调查表'];


    export default {
        inject:['reload'],
        data(){
            return {
                 //一季稻导出选择
                checkAll: false,
                checkedCities: [],
                cities: cityOptions,
                isIndeterminate: true,
                //双季稻导出选择
                checkAll2: false,
                checkedCities2: [],
                cities2: cityOptions2,
                isIndeterminate2: true,
                
                 //暂存参数
                diqu:'',
                xian:'',
                nian:'',
                activeName:'1', 
                activeNameq:'1', 
      tableData8: [{table_01: '水分管理记录表', table_02: '点化除管理记录表',table_03: '病虫害防治记录表', table_04: '施肥管理记录表',
                    table_05: '化学调节剂使用记录表',table_06: '水稻监测点洪涝灾害表',table_07: '灌浆速率调查表（早稻）',table_08: '灌浆速率调查表（晚稻）',
                    table_12: '叶片含氮测定仪导出数据',table_10: '生产成本效益统计表(早稻)',table_11: '生产成本效益统计表(晚稻)'}, 
                 
                  ],
      tableData7: [{table_01: '双季稻生育进度调查表（直播稻）', table_02: '茎蘖数叶面积构成表',table_03: '苗情生育期统计表', table_04: '水分管理记录表',
                    table_05: '点化除管理记录表',table_06: '病虫害防治记录表',table_07: '施肥管理记录表',table_08: '化学调节剂使用记录表',
                    table_09: '水稻监测点洪涝灾害表',table_10: '灌浆速率调查表（早稻）',table_11: '灌浆速率调查表（晚稻）',table_12: '叶片含氮测定仪导出数据',table_13: '生产成本效益统计表(早稻)',
                    table_14: '生产成本效益统计表(晚稻)',}, 
                 
                  ], 
                data:{
                    id:'',
                    field_1:'自动生成',
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
                    continue_use:''
                },
                listArray:[],
                listArray1:[],
                listArray2:[],
                listArray3:[],
                listArray4:[],
                listArray5:[],
                listArray6:[],
                listArray7:[],
                listArray8:[],
                activeName:0,
                flag:0,
                sec: '',
                btntype: 'info',
                ifno: true,
                activeNum: 0,
                verify: false,
                dialogTableVisible2: false,
                dialogTableVisible1: false,
                dialogInputTableVisible0201:false,
                // 报告部分新增下边8行（只限代码部分，不含注释）
                // 模板选择弹出框
                centerDialogVisible:false,
                checkedTemplate: 'templateZao_36',
                //后台传来的文件list
                fileList:[],
                //文件上传弹出框‘
                dialogAddFile:false,
                //前台文件数组
                fileName:'',
                search: '',
                //文件在线预览的url
                url:'',
                //条件检索
                year:'',
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
                        name: '水稻倒伏灾害表',
                        num: '1',
                        type:'crop_01_02_table_09_03',
                        historyData:'riceTable0293'

                    }, {
                        name: '水稻低温冷害表',
                        num: '2',
                        type:'crop_01_02_table_09_04',
                        historyData:'riceTable0294'
                    }, {
                        name: '水稻高温热害表',
                        num: '3',
                        type:'crop_01_02_table_09_05',
                        historyData:'riceTable0295'
                    }, {
                        name: '水稻涝害调查表',
                        num: '4',
                        type:'crop_01_02_table_09_06',
                        historyData:'riceTable0296'
                    },{
                        name: '气象资料列表',
                        num: '5',
                        type:'crop_01_02_table_09_01',
                        historyData:'riceTable0291'
                    },{
                        name: '抽穗期前、后各15天气象资料',
                        num: '6',
                        type:'crop_01_02_table_09_02',
                        historyData:'riceTable0292'
                    }
                ],
                tableData1: [{
                    name: '双季稻生育进度调查表（移栽稻）',
                    num: '1',
                    type:'processTwo0401',
                    historyData:'riceTableTwo41'
                }, {
                    name: '茎蘖数叶面积构成表',
                    num: '2',
                    type:'processTwo0403',
                    historyData:'riceTableTwo43'
                },{
                    name: '苗情生育期统计表',
                    num: '3',
                    type:'tableTwo0404',
                    historyData:'riceTableTwo44'
                }],
                tableData2: [{
                    name: '双季稻生育进度调查表（直播稻）',
                    num: '1',
                    type:'processTwo0402',
                    historyData:'riceTableTwo42'
                }, {
                    name: '茎蘖数叶面积构成表',
                    num: '2',
                    type:'processTwo0403',
                    historyData:'riceTableTwo43'
                },{
                    name: '苗情生育期统计表',
                    num: '3',
                    type:'tableTwo0404',
                    historyData:'riceTableTwo44'
                }],
                tableData3: [{
                    name: '水分管理记录表',
                    secondNameId:'crop_01_02_table_06_01',
                    num: '1',
                    type:'process020601',
                    historyData:'riceTable0261'
                }, {
                    name: '点化除管理记录表',
                    secondNameId:'crop_01_02_table_06_02',
                    num: '2',
                    type:'process020602',
                    historyData:'riceTable0262'
                }, {
                    name: '病虫害防治记录表',
                    secondNameId:'crop_01_02_table_06_03',
                    num: '3',
                    type:'process020603',
                    historyData:'riceTable0263'
                }, {
                    name: '施肥管理记录表',
                    secondNameId:'crop_01_02_table_06_04',
                    num: '4',
                    type:'process020604',
                    historyData:'riceTable0264'
                }, {
                    name: '化学调节剂使用记录表',
                    secondNameId:'crop_01_02_table_06_05',
                    num: '5',
                    type:'process020605',
                    historyData:'riceTable0265'
                }, {
                    name: '水稻监测点洪涝灾害表',
                    num: '6',
                    type:'crop_01_02_table_09_07',
                    historyData:'riceTable0297'
                }, {
                    name: '灌浆速率调查表（早稻）',
                    num: '7',
                    type:'process021001',
                    historyData:'riceTable02101'
                }, {
                    name: '灌浆速率调查表（晚稻）',
                    num: '8',
                    type:'process021002',
                    historyData:'riceTable02102'
                }, {
                    name: '叶片含氮测定仪导出数据',
                    num: '9',
                    type:'process020501',
                    historyData:'riceTable0251'
                }
                , {
                    name: '生产成本效益统计表(早稻)',
                    num: '10',
                    type:'tableTwo0801',
                    historyData:'riceTableTwo81'
                }
                //  {
                //     name: '叶片含氮测定仪导出数据',
                //     num: '9',
                //     type:'process020501',
                //     historyData:'riceTable02051'
                // }
                , {
                    name: '生产成本效益统计表(晚稻)',
                    num: '11',
                    type:'tableTwo0802',
                    historyData:'riceTableTwo82'
                }
                ],
                tableData4: [{
                    name: '水稻播栽进度调查表（早稻）',
                    num: '1',
                    type:'processTwo0301',
                    historyData:'riceTableTwo31'
                }, {
                    name: '水稻播栽进度调查表（晚稻）',
                    num: '2',
                    type:'processTwo0302',
                    historyData:'riceTableTwo32'
                }, {
                    name: '水稻苗情评价动态列表（早稻移栽）',
                    num: '3',
                    type:'processTwo0303',
                    historyData:'riceTableTwo33'
                }, {
                    name: '水稻苗情评价动态列表（早稻直播）',
                    num: '4',
                    type:'processTwo0304',
                    historyData:'riceTableTwo34'
                }, {
                    name: '水稻苗情评价动态列表(晚稻移栽)',
                    num: '5',
                    type:'processTwo0305',
                    historyData:'riceTableTwo35'
                }, {
                    name: '水稻苗情评价动态列表(晚稻直播)',
                    num: '6',
                    type:'processTwo0306',
                    historyData:'riceTableTwo36'
                }],
                tableData6: [{
                    name: '水稻栽插概况调查表',
                    num: '1',
                    type:'crop_01_02_table_01_01',
                    historyData:'riceTableTwo11'
                }, {
                    name: '水稻生产主推品种调查表',
                    num: '2',
                    type:'crop_01_02_table_01_02',
                    historyData:'riceTableTwo12'
                }, {
                    name: '水稻双季稻苗情监测预测产表',
                    num: '3',
                    type:'process020701',
                    historyData:'riceTable0271'
                }, {
                    name: '水稻双季稻收获进度调查(早稻)',
                    num: '4',
                    type:'process020702',
                    historyData:'riceTable0272'
                }, {
                    name: '水稻双季稻收获进度调查(晚稻)',
                    num: '5',
                    type:'process020703',
                    historyData:'riceTable0273'
                }, {
                    name: '水稻双季稻测产表',
                    num: '6',
                    type:'crop_01_02_table_07_04',
                    // type:'process020703',
                    historyData:'riceTable0274'
                }],
                pointData:[],//共有几个监测点
                allHistoryPointDataL:[],//不相同的历史监测点
                historyPointData:[],//历史监测点
                pointDetailData:[],//监测点对应表格的详细数据
                tableData0601:[],//表0601对应的数据
                dialogTableVisible0201:false,
                people:'',
                states: [],
                options: [],
                list: [],
                loading: false,
                myYear:'',
                lastYear:'',
                historyPointDetailData:{field_1:'',
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
                    continue_use:''
                    }
            }
        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.people = localStorage.getItem('ms_username');
            var myDate = this.getNowTime();
            var ac = this.$route.params.activeNum;
            this.data.field_2 = myDate;
            //Console.log(this.data.field_2);
            if(this.$route.params.activeNum != null && this.$route.params.activeNum!=''){
                this.activeNum = this.$route.params.activeNum;
                //Console.log(this.activeNum)
            }
            this.myYear = new Date().getFullYear();
            this.lastYear = this.myYear-1;
            this.getTableData(this.people,this.myYear);
            this.getHistoryTableData(people,this.myYear);
        },
        // 新增以下3行代码
        mounted(){
            this.getFiles();
        },
        computed: {
            listSet() {
                return this.states.map(item => {
                    return { value: `${item.variety_name}`, label: `${item.variety_name}` };
                });
            }
        },
        filters: {
            formatTimer: function(value) {
            let date = new Date(value);
            let y = date.getFullYear();
            let MM = date.getMonth() + 1;
            MM = MM < 10 ? "0" + MM : MM;
            let d = date.getDate();
            d = d < 10 ? "0" + d : d;
            return y + "-" + MM + "-" + d ;
            }
        },
        methods:{
            //获取当年监测点数据
            getTableData(people,myDate){
                //Console.log(myDate);
                axios({
                    url: '/getAllDataPointTwo0201',
                    method:'post',
                    params:{
                        people:people,
                        myDate:myDate
                    }
                }).then(res=>{
                    //Console.log(res);
                    this.pointData=res.data;
                    for(let i=0;i < this.pointData.length; i++){
                        this.pointData[i].field_2 = this.pointData[i].field_2.substring(0,4);
                        this.allHistoryPointDataL.push(this.pointData[i].field_1);
                    }
                    //Console.log(this.pointData);
                    //Console.log(this.allHistoryPointDataL);

                }).catch(err=>{
                    // return this.$message.error("查询数据失败！");
                });
            },
            //双季稻导出进程表
            //表名多选框选择
            handleCheckAllChange(val) {
                this.checkedCities = val ? cityOptions : [];
                this.isIndeterminate = false;
            },
             //双季稻导出
                //表名多选框选择
                handleCheckAllChange2(val) {
                    this.checkedCities2 = val ? cityOptions2 : [];
                    this.isIndeterminate2 = false;
                },
                handleCheckedCitiesChange2(value) {
                let checkedCount2 = value.length;
                this.checkAll2 = checkedCount2 === this.cities2.length;
                this.isIndeterminate2 = checkedCount2 > 0 && checkedCount2 < this.cities2.length;
                },
           
            //获取监测点历史数据
            getHistoryTableData(people,myDate){
                axios({
                    url: '/getAllDataHistoryPointTwo0201',
                    method:'get',
                    params:{
                        people:people,
                        myDate:myDate
                    }
                }).then(res=>{
                    //Console.log(res);
                    this.historyPointData=res.data;
                    for(let i=0;i < this.historyPointData.length; i++){
                        this.historyPointData[i].field_2 = this.historyPointData[i].field_2.substring(0,4);
                    }
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
                //Console.log('submit!');
                this.dialogTableVisible1 = false;
            },
            handleClick (tab, event) {
                console.log(tab, event)
                console.log(111)
            },
            handleClose(done){
                this.$confirm('确定关闭吗').then(() => {
                    // function(done)，done 用于关闭 Dialog
                    this.reload();
                    done();
                }).catch(() => {
                });
            },
            //查看当年监测点
            handleCheck(index,row,flag){
                //Console.log(index);
                //Console.log(row);
                this.data = this.pointData[index];
                this.dialogTableVisible0201 = true;
                this.flag = flag;
                this.getSelectContent3();
                this.getSelectContent4();
                this.getSelectContent5();
                this.getSelectContent1();
                //this.getSelectContent2();
                this.getSelectContent6();
                this.getSelectContent7();
                this.getSelectContent8();
            },
            //查看历史监测点
            handleCheckHistory(index,row,flag){
                this.data = this.historyPointData[index];
                this.dialogTableVisible0201 = true;
                this.flag = flag;
                this.getSelectContent3();
                this.getSelectContent4();
                this.getSelectContent5();
                
            },
            isContinuetoUse(index,row,flag){

                this.$confirm("是否延用？").then(_=>{
                    var myDate = this.getNowTime();
                    this.historyPointDetailData.field_1 = this.historyPointData[index].field_1;
                    this.historyPointDetailData.field_2 = myDate;
                    this.historyPointDetailData.field_3_1 = this.historyPointData[index].field_3_1;
                    this.historyPointDetailData.field_3_2 = this.historyPointData[index].field_3_2;
                    this.historyPointDetailData.field_3_3 = this.historyPointData[index].field_3_3;
                    this.historyPointDetailData.field_3_4 = this.historyPointData[index].field_3_4;
                    this.historyPointDetailData.field_3_5 = this.historyPointData[index].field_3_5;
                    this.historyPointDetailData.field_4_2 = this.historyPointData[index].field_4_2;
                    this.historyPointDetailData.field_5_1 = this.historyPointData[index].field_5_1;
                    this.historyPointDetailData.field_5_2 = this.historyPointData[index].field_5_2;
                    this.historyPointDetailData.field_5_3 = this.historyPointData[index].field_5_3;
                    this.historyPointDetailData.field_6_1 = this.historyPointData[index].field_6_1;
                    this.historyPointDetailData.people = this.people;
                    this.historyPointDetailData.modification_time = this.getNowTime();
                    this.historyPointDetailData.in_use = 3;
                    //Console.log(this.historyPointDetailData);
                    //Console.log(this.data);
                    this.saveInputData(this.historyPointDetailData);

                }).catch(_=>{
                    return this.$message.info("您取消了操作！");
                });
            },
            //新增监测点表数据
            handleAddPoint(index,row,point){
                //Console.log(index);
                //Console.log(row);
                //Console.log(point);
                var type = "/" + row.type;
                //Console.log(type);
                this.$router.push({name:type,query:{point: point}})
            },
            //查看监测点表历史数据
            handleHistoryPointData(index,row,point){
                var type = "/0" + row.historyData;
                console.log(type);
                console.log(row);
                console.log(point);
                
                this.$router.push({name:type,query:{point: point}})
            },
            //新增县级表数据
            handleAdd(index,row){
                //Console.log(index);
                //Console.log(row);
                var type = "/" + row.type;
                //Console.log(type);
                this.$router.push(type)
            },
            //查看县级表历史数据
            handleHistoryData(index,row){
                var type = "/0" + row.historyData;
                this.$router.push(type)
            },
            formatTime(time) {
                var date = Date.parse(time);
                let yy = date.getFullYear()
                //Console.log(yy)
                let mm = date.getMonth() + 1 < 10 ? '0'+ (new Date().getMonth() + 1) :new Date().getMonth() + 1
                let dd = date.getDate() < 10 ? '0'+ new Date().getDate():new Date().getDate()
                var dateTime = yy.toString() + '-' + mm + '-' + dd
                //Console.log(dateTime)
                return dateTime
            },
            AddPoint(){
                this.data.field_5 = '';
                this.data.field_6 = '';
                this.data.field_7 = '';
                this.data.field_9 = '';
                this.data.field_18 = '';
                this.data.field_19 = '';
                this.data.field_20 = '';
                this.data.field_21 = '';
                this.getSelectContent1();
                //this.getSelectContent2();
                this.getSelectContent8();
                this.getSelectContent3();
                this.getSelectContent4();
                this.getSelectContent5();
                this.getSelectContent6();
                this.getSelectContent7();
                this.getAreaInfo(this.people);
                this.dialogInputTableVisible0201 = true;
            },
            //存储数据
            getFormList(flag){
                var field_5_1 = this.data.field_5_1.toString();
                //Console.log(this.data.field_3_3);
                //Console.log(this.data.field_3_4);
                //Console.log(field_5_1);
                if(this.data.field_3_3 == null || this.data.field_3_4 == null || this.data.field_3_5 == null || this.data.field_4_2 == null|| field_5_1 == null
                    || this.data.field_5_2 == null|| this.data.field_5_3 == null|| this.data.field_6_1 == null ||
                    this.data.field_3_3 == '' || this.data.field_3_4 == '' || this.data.field_3_5 == '' || this.data.field_4_2 == ''|| field_5_1 == ''
                    || this.data.field_5_2 == ''|| this.data.field_5_3 == ''|| this.data.field_6_1 == ''){
                    this.$alert('数据不能为空!', '错误提示', {
                        confirmButtonText: '确定',
                        callback: action => {
                        }
                    });
                }else {
                    this.$confirm("是否上报？").then(_=>{
                        var people = localStorage.getItem('ms_username');
                        this.data.people = people;
                        this.data.modification_time = this.getNowTime();
                        this.data.in_use = 1;
                        //Console.log(this.data.field_2);
                        // if(this.data.field_2 != null){
                        //     this.data.field_2 = moment(year).format("YYYY-MM-DD");
                        // };
                        // if (this.flag==2){
                        //     this.getAllSelectContent();
                        // }
                        this.data.field_5_1 = this.data.field_5_1.toString();
                        if (this.data.field_2 != null){
                            this.data.field_2 = this.data.field_2.toString().replace('“','').replace('”','');
                        }
                        //Console.log(this.data)
                        if (this.data.field_6_2 != null){
                            this.data.field_6_2 = this.data.field_6_2.toString().replace('“','').replace('”','');
                        }
                        if (this.data.field_6_6 != null){
                            this.data.field_6_6 = this.data.field_6_6.toString().replace('“','').replace('”','');
                        }if (this.data.field_2 != null){
                            this.data.field_2 = this.data.field_2.toString().replace('“','').replace('”','');
                        }
                        this.saveInputData(this.data);
                    }).catch(_=>{
                        return this.$message.info("您取消了操作！");
                    });
                }
            },
            //生成保存函数
            saveInputData(data){
                // Console.log(data);
                var url = 'getSituationInputDataTableTwo3';
                if (this.flag==2){
                     url = 'updateSituationInputDataTableTwo3';
                }
                axios({
                    url:'/'+url,
                    method:'post',
                    params:{
                        data:data
                    }
                }).then(res=>{
                    //Console.log(res);
                    this.$message.success("操作成功！");
                    this.dialogInputTableVisible0201=false;
                    this.reload();

                }).catch(err=>{
                    return this.$message.error("数据保存失败！");
                })
            },
            //获取 市 下拉框内容
            getSelectContent1(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"市",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray1 = res.data;
                        //Console.log(this.listArray);
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取县
            getAreaInfo(people){
                    axios({
                    url:'/getAreaInfo',
                    method:'get',
                    params:{
                        people:people,
                    }
                }).then(
                    res=>{
                        this.listArray2 = res.data;
                        //Console.log(this.listArray2);
                        this.data.field_3_2 = this.listArray2[0].code_name;
                        if(this.data.field_3_2 == '庐江县' ){
                            this.data.field_3_1 = '合肥市';
                        }
                        if(this.data.field_3_2 == '南陵县'){
                            this.data.field_3_1 = '芜湖市';
                        }
                        if(this.data.field_3_2 == '贵池区'){
                            this.data.field_3_1 = '池州市';
                        }
                        if(this.data.field_3_2 == '怀宁县'){
                            this.data.field_3_1 = '安庆市';
                        }
                        if(this.data.field_3_2 == '宣州区'){
                            this.data.field_3_1 = '宣城市';
                        }
                        if(this.data.field_3_2 == '枞阳县'){
                            this.data.field_3_1 = '铜陵市';
                        }
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取 县 下拉框内容
            getSelectContent2(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"县（市、区）",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray2 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },
            //获取 栽植方式 下拉框内容
            getSelectContent3(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"栽植方式",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray8 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },
            //获取 前茬类型 下拉框内容
            getSelectContent4(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"前茬类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray4 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取 水管方式 下拉框内容
            getSelectContent5(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"水管方式",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray5 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取 稻作类型 下拉框内容
            getSelectContent6(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"稻作类型",
                        riceType:'double'
                    }
                }).then(
                    res=>{
                        this.listArray6 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },

            //获取 品系类型 下拉框内容
            getSelectContent7(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"品系类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray7 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },


            //获取 地区类型 下拉框内容
            getSelectContent8(fieldName){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"地区类型",
                        riceType:'one'
                    }
                }).then(
                    res=>{
                        this.listArray3 = res.data;
                    }
                ).catch(err=>{
                    return this.$message.error("获取下拉框内容失败！");
                });
            },
            // 获取当前时间
            getNowTime: function () {
                let dateTime
                let yy = new Date().getFullYear()
                let mm = new Date().getMonth() + 1 < 10 ? '0'+ (new Date().getMonth() + 1) :new Date().getMonth() + 1
                let dd = new Date().getDate() < 10 ? '0'+ new Date().getDate():new Date().getDate()
                let hh = new Date().getHours()  < 10 ? '0'+ new Date().getHours() :new Date().getHours()
                let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes():new Date().getMinutes()
                let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds():new Date().getSeconds()
                dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                return dateTime
            },
            //获取下拉框的所有选中内容，并保存到对象中
            getAllSelectContent(){
                var selected = document.getElementById("select_4");
                var  index = selected.selectedIndex;
                var  text = selected.options[index].text;
                this.data.field_11 = text;

                selected = document.getElementById("select_3");
                index = selected.selectedIndex;
                text = selected.options[index].text;
                this.data.field_4_2 = text;
                //Console.log(this.data.field_4_2);

                selected = document.getElementById("select_5");
                index = selected.selectedIndex;
                text = selected.options[index].text;
                this.data.field_4_5 = text;

                selected = document.getElementById("select_6");
                index = selected.selectedIndex;
                text = selected.options[index].text;
                this.data.field_5_2 = text;

                selected = document.getElementById("select_7");
                index = selected.selectedIndex;
                text = selected.options[index].text;
                this.data.field_5_3 = text;

                selected = document.getElementById("select_8");
                index = selected.selectedIndex;
                text = selected.options[index].text;
                this.data.field_6_1 = text;
            },
            remoteMethod(query) {
                console.log(query);
                if (query !== '') {
                    this.loading = true;
                    axios({
                        url:'/getAllVarieties',
                        method:'get',
                    }).then((response) => {
                        //console.log(response);
                        this.states = response.data;
                        console.log(this.states);
                    });
                    setTimeout(() => {
                        this.loading = false;
                        // this.options = this.list.filter(item => {
                        this.options = this.listSet.filter(item => {
                            return item.label.toLowerCase()
                                .indexOf(query.toLowerCase()) > -1;
                        });
                        console.log(this.options);
                    }, 1000);
                } else {
                    this.options = [];
                }
            },
            // 新增以下7个方法
            //1.前台获取到文件
            getFile(event){
            var file = event.target.files;
            for(var i = 0;i<file.length;i++){
                //    上传类型判断
                var imgName = file[i].name;
                    var idx = imgName.lastIndexOf(".");  
                    if (idx != -1){
                        var ext = imgName.substr(idx+1).toUpperCase();   
                        ext = ext.toLowerCase( ); 
                        if (ext!='pdf' && ext!='doc' && ext!='docx'){
                       
                        }else{
                            this.fileArr.push(file[i]);
                        }   
                    }else{
                    }
                }
            },
            //2.文件提交后台
            submitAddFile(){
                if(0 == this.fileArr.length){
                    this.$message({
                    type: 'info',
                    message: '请选择要上传的文件'
                    });
                    return;
                }
                    var formData = new FormData();
                    formData.append("crop",localStorage.getItem("crop"));
                    formData.append("fileName",this.fileName)
                    formData.append("userId",localStorage.getItem("ms_username"));
                    for(var i=0;i<this.fileArr.length;i++){
                        formData.append('files',this.fileArr[i]);
                    }
                axios({
                    url:'/upload1',
                    data:formData,
                    method:'post',
                    headers:{'Content-Type': 'multipart/form-data'}
                }).then(res=>{
                    this.$message("文件上传成功！");
                    this.dialogAddFile = false;
                }).catch(error=>{
                    this.$message("文件上传出错，请重新上传！");
                })
            },
            //3.前台文件清除
            resetAdd(){
                this.fileArr = [];
            },
            //4.获取到所有的研究报告列表
            getFiles(){
                let account = sessionStorage.getItem("ms_username");
                axios({
                    url:'/getFiles',
                    method:'get',
                    params:{crop:'',year:this.year,account:account,code_name:''}
                }).then(res=>{
                    var data = res.data;
                    data.forEach(item => {
                    switch(item.crop.slice(0,7)){
                        case 'crop_01':
                            item.crop = '水稻';
                            break;
                    };
                    item.modification_time = item.modification_time.slice(0,4);
                    });
                    this.fileList = data;
                    
                }).catch(error=>{
                    this.$message("获取数据失败！")
                })
            },
            //5.按年分检索
            changeYear(){
            this.getFiles();
            },
            // 6.
            addReport(){
            // //Console.log(this.checkedTemplate);
            this.$router.replace(this.checkedTemplate);
            },
            // 7.
            viewReport(row){
            sessionStorage.setItem("html",row.html);
            this.$router.replace('/viewTemplateResearchTwo');
            },
            //新增------------------------------------------
              //进程表单元格跳转
            handleCell2(row,column,event,cell){
            var areas=this.diqu;
            var citys=this.xian;
            var years=this.nian;
            ////console.log(areas,citys);
            ////console.log(row);
            ////console.log(column);
            // console.log(years);
            if(column.label === "双季稻生育进度"){
                if(row.table_01 === '双季稻生育进度调查表（直播稻）'){
                // console.log(123);
                this.$router.push({path:'/riceTableTwo42new',query:{area:areas,city:citys,year:years}});
                };
               
            };
            if(column.label === "茎蘖数面积"){
                if(row.table_02 === '茎蘖数叶面积构成表'){
                this.$router.push({path:'/riceTableTwo43new',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "苗情生育期"){
                if(row.table_03 === '苗情生育期统计表'){
                this.$router.push({path:'/riceTableTwo44new',query:{area:areas,city:citys,year:years}});
                };
            };
  
            if(column.label === "叶片参数自动测定"){
                if(row.table_05 === '叶片含氮测定仪导出数据'){
                this.$router.push({path:'/riceTableTwo51',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "监测点田管记录"){
                if(row.table_06 === '水稻监测点水分管理记录表'){
                this.$router.push({path:'/riceTableTwo61',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_06 === '水稻监测点化除管理记录表'){
                this.$router.push({path:'/riceTableTwo62',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_06 === '水稻监测点病虫害防治记录表'){
                this.$router.push({path:'/riceTableTwo63',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_06 === '水稻监测点施肥管理记录表'){
                this.$router.push({path:'/riceTableTwo64',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_06 === '水稻监测点化学调节剂使用记录表'){
                this.$router.push({path:'/riceTableTwo65',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "测产相关进度监测"){
                if(row.table_07 === '监测县(市、区)水稻双季稻苗情监测预测产表'){
                this.$router.push({path:'/riceTableTwo71',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_07 === '监测县(市、区)水稻双季稻收获进度调查（早稻）'){
                this.$router.push({path:'/riceTableTwo72',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_07 === '监测县(市、区)水稻双季稻收获进度调查（晚稻）'){
                this.$router.push({path:'/riceTableTwo73',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_07 === '监测县(市、区)水稻双季稻测产表'){
                this.$router.push({path:'/riceTableTwo74',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "生产效益调查"){
                if(row.table_08 === '监测县(市、区)水稻生产成本效益统计表（早稻）'){
                this.$router.push({path:'/riceTableTwo81',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_08 === '监测县(市、区)水稻生产成本效益统计表（晚稻）'){
                this.$router.push({path:'/riceTableTwo82',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "气象及自然灾害调查"){
                if(row.table_09 === '监测县(市、区)气象资料列表'){
                this.$router.push({path:'/riceTableTwo91',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '监测县(市、区)抽穗期前、后各15天气象资料'){
                this.$router.push({path:'/riceTableTwo92',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '监测县(市、区)水稻倒伏灾害表'){
                this.$router.push({path:'/riceTableTwo93',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '监测县(市、区)水稻低温冷害表'){
                this.$router.push({path:'/riceTableTwo94',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '监测县(市、区)水稻高温热害表'){
                this.$router.push({path:'/riceTableTwo95',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '监测县(市、区)水稻涝害调查表'){
                this.$router.push({path:'/riceTableTwo96',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_09 === '水稻监测点洪涝灾害表'){
                this.$router.push({path:'/riceTableTwo97',query:{area:areas,city:citys,year:years}});
                };
            };
            if(column.label === "监测点灌浆速率调查表"){
                if(row.table_10 === '水稻监测点灌浆速率调查表（早稻）'){
                this.$router.push({path:'/riceTableTwo101',query:{area:areas,city:citys,year:years}});
                };
                if(row.table_10 === '水稻监测点灌浆速率调查表（晚稻）'){
                this.$router.push({path:'/riceTableTwo102',query:{area:areas,city:citys,year:years}});
                };
            };
            },
             //记录表单元格跳转
            handleCell(row,column,event,cell){
                var areas=this.diqu;
                var citys=this.xian;
                var years=this.nian;
                console.log(areas,citys,years);
                ////console.log(row);
                ////console.log(column);
                if(column.label == "水分管理"){
                    if(row.table_01 === '水分管理记录表'){
                    this.$router.push({path:'/riceTableTwo61new',query:{area:areas,city:citys,year:years}});
                    ////console.log('1111');
                    };
                
                };
                if(column.label === "点化除管理"){
                    if(row.table_02 === '点化除管理记录表'){
                        this.$router.push({path:'/riceTableTwo62new',query:{area:areas,city:citys,year:years}});
                    };
                };
                if(column.label === "病虫害防治"){
                    if(row.table_03 === '病虫害防治记录表'){
                    this.$router.push({path:'/riceTableTwo63new',query:{area:areas,city:citys,year:years}});
                    };

                };
                if(column.label === "施肥管理"){
                    if(row.table_04 === '施肥管理记录表'){
                        this.$router.push({path:'/riceTableTwo64new',query:{area:areas,city:citys,year:years}});
                    };
                  
                };
                if(column.label === "化学调节剂"){
                    if(row.table_05 === '化学调节剂使用记录表'){
                        this.$router.push({path:'/riceTableTwo65new',query:{area:areas,city:citys,year:years}});
                    };
                };
                if(column.label === "洪涝灾害"){
                    if(row.table_06 === '水稻监测点洪涝灾害表'){
                    this.$router.push({path:'/riceTableTwo97new',query:{area:areas,city:citys,year:years}});
                    };
                   
                };
                if(column.label === "灌浆速率（早稻）"){
                    if(row.table_07 === '灌浆速率调查表（早稻）'){
                    this.$router.push({path:'/riceTableTwo101new',query:{area:areas,city:citys,year:years}});
                    };
                   
                };
                if(column.label === "灌浆速率（晚稻）"){
                    if(row.table_08 === '灌浆速率调查表（晚稻）'){
                        this.$router.push({path:'/riceTableTwo102new',query:{area:areas,city:citys,year:years}});
                    };
                };
                if(column.label === "叶片含氮测定"){
                    if(row.table_12 === '叶片含氮测定仪导出数据'){
                    this.$router.push({path:'/riceTableTwo51new',query:{area:areas,city:citys,year:years}});
                    };
                   
                };
                if(column.label === "生产成本(早稻)"){
                    if(row.table_10 === '生产成本效益统计表(早稻)'){
                    this.$router.push({path:'/riceTableTwo81new',query:{area:areas,city:citys,year:years}});
                };
                };
                 if(column.label === "生产成本(晚稻)"){
                    if(row.table_11 === '生产成本效益统计表(晚稻)'){
                    this.$router.push({path:'/riceTableTwo82new',query:{area:areas,city:citys,year:years}});
                };
                };
            },

             //导出excel
            getExcel2(){
                ////console.log("选择："+this.checkedCities2);
                var tableOption=this.checkedCities2;
                for(var i=0; i<tableOption.length;i++){
                    var name=tableOption[i];
                    switch(name){
                    case "水稻栽插概况调查表":
                        this.handleDownloadTwo11();
                        break;
                    case "水稻生产主推品种调查表":
                        this.handleDownloadTwo12();
                        break;
                    case "水稻监测点汇总表":
                        this.handleDownloadTwo22();
                        break;
                    case "水稻播栽进度调查表（早稻）":
                        this.handleDownloadTwo31();
                        break;
                    case "水稻播栽进度调查表（晚稻）":
                        this.handleDownloadTwo33();
                        break;
                    case "水稻苗情评价动态列表（早稻-移栽稻）":
                        this.handleDownloadTwo33();
                        break;
                    case "水稻苗情评价动态列表（早稻-直播稻）":
                        this.handleDownloadTwo34();
                        break;
                    case "水稻苗情评价动态列表（晚稻-移栽稻）":
                        this.handleDownloadTwo35();
                        break;
                    case "水稻苗情评价动态列表（晚稻-直播稻）":
                        this.handleDownloadTwo36();
                        break;
                    case "中稻生育进度调查表（移栽稻）":
                        this.handleDownloadTwo41();
                        break;
                    case "直播稻生育进度调查表（直播稻）":
                        this.handleDownloadTwo42();
                        break;
                    case "水稻监测点茎蘖数叶面积构成表":
                        this.handleDownloadTwo43();
                        break;
                    case "水稻监测点苗情生育期统计表":
                        this.handleDownloadTwo44();
                        break;
                    case "叶片含氮测定仪导出数据":
                        this.handleDownloadTwo51();
                        break;
                    case "水稻监测点水分管理记录表":
                        this.handleDownloadTwo61();
                        break;
                    case "水稻监测点化除管理记录表":
                        this.handleDownloadTwo62();
                        break;
                    case "水稻监测点病虫害防治记录表":
                        this.handleDownloadTwo63();
                        break;
                    case "水稻监测点施肥管理记录表":
                        this.handleDownloadTwo64();
                        break;
                    case "水稻监测点化学调节剂使用记录表":
                        this.handleDownloadTwo65();
                        break;
                    case "水稻双季稻苗情监测预测产表":
                        this.handleDownloadTwo71();
                        break;
                    case "水稻双季稻收获进度调查（早稻）":
                        this.handleDownloadTwo72();
                        break;
                    case "水稻双季稻收获进度调查（晚稻）":
                        this.handleDownloadTwo73();
                        break;
                    case "水稻双季稻测产表":
                        this.handleDownloadTwo74();
                        break;
                    case "水稻生产成本效益统计表（早稻）":
                        this.handleDownloadTwo81();
                        break;
                    case "水稻生产成本效益统计表（晚稻）":
                        this.handleDownloadTwo82();
                        break;
                    case "气象资料列表":
                        this.handleDownloadTwo91();
                        break;
                    case "抽穗期前、后各15天气象资料":
                        this.handleDownloadTwo92();
                        break;
                    case "水稻倒伏灾害表":
                        this.handleDownloadTwo93();
                        break;
                    case "水稻低温冷害表":
                        this.handleDownloadTwo94();
                        break;
                    case "水稻高温热害表":
                        this.handleDownloadTwo95();
                        break;
                    case "水稻涝害调查表":
                        this.handleDownloadTwo96();
                        break;
                    case "水稻监测点洪涝灾害表":
                        this.handleDownloadTwo97();
                        break;
                    case "水稻监测点灌浆速率调查表（早稻）":
                        this.handleDownloadTwo101();
                        break;
                    case "水稻监测点灌浆速率调查表（晚稻）":
                        this.handleDownloadTwo102();
                        break;
                    }
                }
            },
                    //导出excel
            getExcel(){
            ////console.log(this.checkedCities);
            var tableOption=this.checkedCities;
            for(var i=0; i<tableOption.length;i++){
                var name=tableOption[i];
                switch(name){
                case "水稻栽插概况调查表":
                    this.handleDownload11();
                    break;
                case "水稻生产主推品种调查表":
                    this.handleDownload12();
                    break;
                case "水稻监测点汇总表":
                    this.handleDownload22();
                    break;
                case "水稻播栽进度调查表":
                    this.handleDownload31();
                    break;
                case "水稻苗情评价动态列表（育秧）":
                    this.handleDownload32();
                    break;
                case "水稻苗情评价动态列表（直播）":
                    this.handleDownload33();
                    break;
                case "中稻生育进度调查表（移栽稻）":
                    this.handleDownload41();
                    break;
                case "直播稻生育进度调查表（直播稻）":
                    this.handleDownload42();
                    break;
                case "水稻监测点茎蘖数叶面积构成表":
                    this.handleDownload43();
                    break;
                case "水稻监测点苗情生育期统计表":
                    this.handleDownload44();
                    break;
                case "叶片含氮测定仪导出数据":
                    this.handleDownload51();
                    break;
                case "水稻监测点水分管理记录表":
                    this.handleDownload61();
                    break;
                case "水稻监测点化除管理记录表":
                    this.handleDownload62();
                    break;
                case "水稻监测点病虫害防治记录表":
                    this.handleDownload63();
                    break;
                case "水稻监测点施肥管理记录表":
                    this.handleDownload64();
                    break;
                case "水稻监测点化学调节剂使用记录表":
                    this.handleDownload65();
                    break;
                case "水稻单季稻苗情监测预测产表":
                    this.handleDownload71();
                    break;
                case "水稻单季稻收获进度调查":
                    this.handleDownload72();
                    break;
                case "水稻单季稻测产表":
                    this.handleDownload73();
                    break;
                case "水稻生产成本效益统计表":
                    this.handleDownload81();
                    break;
                case "气象资料列表":
                    this.handleDownload91();
                    break;
                case "水稻监测点病虫抽穗期前、后各15天气象资料害防治记录表":
                    this.handleDownload92();
                    break;
                case "水稻倒伏灾害表":
                    this.handleDownload93();
                    break;
                case "水稻低温冷害表":
                    this.handleDownload94();
                    break;
                case "水稻高温热害表":
                    this.handleDownload95();
                    break;
                case "水稻涝害调查表":
                    this.handleDownload96();
                    break;
                case "水稻监测点洪涝灾害表":
                    this.handleDownload97();
                    break;
                case "水稻监测点灌浆速率调查表":
                    this.handleDownload101();
                    break;

                }
      }
    },
     }

    }
</script>

<style>
    .next_step_box .pad15{
        padding: 15px;
        border: 1px solid #e5e5e5
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
    .stepbox{
        width: 100%;
        overflow: hidden;
        padding: 15px 0;
        text-align: center;
        display: flex;
        justify-content: space-between
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

        width: 100%;
        background: #fff;

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
    .step1{
        width: 100%;
        height: 100%;
        background: url(../../assets/1_03.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    }
    .step2{
        width: 100%;
        height: 100%;
        background: url(../../assets/a2_04.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    }
    .activestep .step2{
        background: url(../../assets/2_04.png) no-repeat;
        width: 100%;
        height: 100%;
        background-size: 100% 100%;
        cursor: pointer;
    }
    .activestep .step3{
        width: 100%;
        height: 100%;
        background: url(../../assets/3_05.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    }
    .step3{
        width: 100%;
        height: 100%;
        background: url(../../assets/a3_05.png) no-repeat;
        background-size: 100% 100%;
        cursor: pointer;
    }
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

    .next_step_box{
        width: 100%;
        height: 100%;
        position: relative;
        background: #f4f5fa;
        overflow: auto;
        padding-bottom: 20px
    }

    .mail i{
        font-size: 12px
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

    .login_footer_box .wid50{
        width: 49%;
        height: 100%
    }

    .login_footer_box .wid50:first-child{
        border-right: 0.5px solid #e2e2e2
    }
    .sucess_msg i{
        font-size: 20px;
        margin: 0 15px;
    }
    /*.login_box .verifyform .el-dialog{*/
        /*width: 336px*/
    /*}*/
    /*.login_box .el-dialog__header{*/
        /*display: none*/
    /*}*/

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
    input:disabled,textarea:disabled{
        opacity: 1;
        -webkit-text-fill-color: black;
    }
</style>


