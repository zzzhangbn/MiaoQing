<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id == ''|| column_id == null">
            <router-link :to="{name:'/tableTwoMain',params: {activeNum: 2}}">
                <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <template v-if="column_id != ''&& column_id != null">
            <router-link to='/traceBackData'>
                <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <div class ="table-s" id="containner_div" style="width: 100%;height: 70%;">

            <table  id="sum_tab"  style="width: 100%;overflow-x: auto;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr style="background-color: #6E8B3D;color:white;">
                    <td colspan="14">监测县(市、区)水稻双季稻测产表</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 10%;" >县（市、区）</td>
                    <td style="width: 10%;">地区类型</td>
                    <td style="width: 10%;">稻作类型</td>
                    <td style="width: 15%;">全县面上平均水平</td>
                    <td style="width: 8%;">人工移栽</td>
                    <td style="width: 8%;">机械插秧</td>
                    <td style="width: 8%;">盘育抛秧</td>
                    <td style="width: 8%;">无盘旱育抛秧</td>
                    <td style="width: 8%;">撒直播</td>
                    <td style="width: 8%;">机条播</td>
                </tr>
                <!--<template v-for="(item,index) in data">-->
                <tr>
                    <td><el-input v-model="data.field_1" disabled></el-input></td>
                    <td><el-input v-model="data.field_2" disabled></el-input></td>
                    <td><el-input v-model="data.field_3" disabled></el-input></td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                    <td>
                        <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                    </td>
                </tr>
                <!--</template>-->
            </table>
        </div>

        <!--字段详情-->
        <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="50%" :before-close="handleClose">
            <table style="width: 90%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
                <template v-if="tableFlag == '全县面上平均水平'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >总面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_4_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_4_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_4_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_4_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_4_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_4_2!='' && data.field_4_3!='' && data.field_4_4!='' && data.field_4_5!=''
                             && data.field_4_2!=null && data.field_4_3!=null && data.field_4_4!=null && data.field_4_5!=null">
                                <span class="span">{{(Number(data.field_4_2) * Number(data.field_4_3) * Number(data.field_4_4) * Number(data.field_4_5) *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_4_2=='' || data.field_4_3=='' || data.field_4_4=='' || data.field_4_5==''
                              || data.field_4_2==null || data.field_4_3==null || data.field_4_4==null || data.field_4_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '人工移栽'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_5_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_5_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_5_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_5_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_5_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_5_2!='' && data.field_5_3!='' && data.field_5_4!='' && data.field_5_5!=''
                             && data.field_5_2!=null && data.field_5_3!=null && data.field_5_4!=null && data.field_5_5!=null">
                                <span class="span">{{(Number(data.field_5_2) * Number(data.field_5_3) * Number(data.field_5_4) * Number(data.field_5_5)  *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_5_2=='' || data.field_5_3=='' || data.field_5_4=='' || data.field_5_5==''
                             || data.field_5_2==null || data.field_5_3==null || data.field_5_4==null || data.field_5_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '机械插秧'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_6_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_6_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_6_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_6_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_6_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_6_2!='' && data.field_6_3!='' && data.field_6_4!='' && data.field_6_5!='' &&
                             data.field_6_2!=null && data.field_6_3!=null && data.field_6_4!=null && data.field_6_5!=null">
                                <span class="span">{{(Number(data.field_6_2) * Number(data.field_6_3) * Number(data.field_6_4) * Number(data.field_6_5)  *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_6_2=='' || data.field_6_3=='' || data.field_6_4=='' || data.field_6_5=='' ||
                                data.field_6_2==null || data.field_6_3==null || data.field_6_4==null || data.field_6_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '盘育抛秧'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_7_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_7_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_7_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_7_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_7_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_7_2!='' && data.field_7_3!='' && data.field_7_4!='' && data.field_7_5!='' &&
                             data.field_7_2!=null && data.field_7_3!=null && data.field_7_4!=null && data.field_7_5!=null">
                                <span class="span">{{(Number(data.field_7_2) * Number(data.field_7_3) * Number(data.field_7_4) * Number(data.field_7_5)  *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_7_2=='' || data.field_7_3=='' || data.field_7_4=='' || data.field_7_5=='' ||
                             data.field_7_2==null || data.field_7_3==null || data.field_7_4==null || data.field_7_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '无盘旱育抛秧'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_8_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_8_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_8_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_8_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_8_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_8_2!='' && data.field_8_3!='' && data.field_8_4!='' && data.field_8_5!='' &&
                             data.field_8_2!=null && data.field_8_3!=null && data.field_8_4!=null && data.field_8_5!=null">
                                <span class="span">{{(Number(data.field_8_2) * Number(data.field_8_3) * Number(data.field_8_4) * Number(data.field_8_5)  *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_8_2=='' || data.field_8_3=='' || data.field_8_4=='' || data.field_8_5=='' ||
                             data.field_8_2==null || data.field_8_3==null || data.field_8_4==null || data.field_8_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '撒直播'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_9_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_9_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_9_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_9_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_9_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_9_2!='' && data.field_9_3!='' && data.field_9_4!='' && data.field_9_5!='' &&
                             data.field_9_2!=null && data.field_9_3!=null && data.field_9_4!=null && data.field_9_5!=null ">
                                <span class="span">{{(Number(data.field_9_2) * Number(data.field_9_3) * Number(data.field_9_4) * Number(data.field_9_5) *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_9_2=='' || data.field_9_3=='' || data.field_9_4=='' || data.field_9_5==''||
                             data.field_9_2==null || data.field_9_3==null || data.field_9_4==null || data.field_9_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
                <template v-if="tableFlag == '机条播'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >面积（万亩）</td>
                        <td >亩有效穗（万）</td>
                        <td >每穗粒数（粒）</td>
                        <td >结实率（%）</td>
                        <td >千粒重（克）</td>
                        <td >八五折亩理论产量（公斤）</td>
                    </tr>
                    <tr>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_10_1" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_10_2" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_10_3" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_10_4" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 17%;">
                            <input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data.field_10_5" @blur.prevent="checkFirstFieldInput($event)" />
                        </td>
                        <td style="width: 15%">
                            <template v-if=" data.field_10_2!='' && data.field_10_3!='' && data.field_10_4!='' && data.field_10_5!='' &&
                              data.field_10_2!=null && data.field_10_3!=null && data.field_10_4!=null && data.field_10_5!=null">
                                <span class="span">{{(Number(data.field_10_2) * Number(data.field_10_3) * Number(data.field_10_4) * Number(data.field_10_5) *0.0001*0.85).toFixed(2)}}</span>
                            </template>
                            <template v-if=" data.field_10_2=='' || data.field_10_3=='' || data.field_10_4=='' || data.field_10_5==''||
                             data.field_10_2==null || data.field_10_3==null || data.field_10_4==null || data.field_10_5==null">
                                <span style="text-align: center;display:block;" class="span">暂无</span>
                            </template>
                        </td>
                    </tr>
                </template>
            </table>
            <span slot="footer" class="dialog-footer">
            <el-button @click="cancelOp">确 定</el-button>
                <!--<el-button type="primary" @click="getInputInfo">暂 存</el-button>-->
                <!-- <el-button @click="clearAllInputs(tableFlag)">清空</el-button> -->
          </span>
        </el-dialog>
    </div>
</template>
<script src="../../../public/js/Vue.min.js"></script>
<script>
    import axios from 'axios'
    export default {
        data() {
            return {
                tableName:'',
                column_id:'',
                tableId:'',
                autoList:[], //存储自动获取内容
                // temList:'',
                listArray:[],//存储下拉框列表
                selectedID:'',//存储选择了哪一个下拉框
                sumDataList:[],//保存所有表头的输入
                dataList:[],//保存二级表头的输入
                headerList:[],//一级表头
                secondList:[],//存储二级表头内容
                secondHeaderList:[],//存储有跨列的字段名
                isOpen:false,//控制表格的前段和后端展开
                tdWidth:'',//一级表的宽度
                tdSecondWidth:'',//二级表的宽度
                tdSecondFieldWidth:'',//二级表头的输入框宽度
                data:[],
                detialDialogVisible:false,
                tableFlag:'',//保存当前点击的字段名
                fieldColIndex:0,//存储二级字段所在列位置
                fieldRowIndex:0,//存储二级字段所在行位置
                testValue:0,
                getField_1_1:'',//第一行的‘县（市、区）’
                fieldName:'',//下拉框所属的字段名称
            }
        },

        mounted() {
            // this.initData();
            // this.getTableData();
        },

        methods:{
            //初始化数据
            initData() {
                //this.getHeaderList();
                //this.getTableData();
            },
            //获取表数据
            getTableData(people,column_id){
                const _this = this;
                axios({
                    url: '/getAllDataTwo0704',
                    method:'get',
                    params:{
                        people:people,
                        column_id:column_id,
                        beginYear:0,
                        endYear:0
                    }
                }).then(res=>{
                    //console.log(res);
                    _this.data=res.data[0];
                    //console.log(_this.data);
                }).catch(err=>{
                    return this.$message.error("查询数据失败！");
                });
            },
            //检查input框的输入是否符合要求
            checkFirstFieldInput(e){
                var strValue = e.target.value;
                if(!isNaN(strValue)){
                }else{
                    // e.target.parentNode.nextSibling.innerHTML = "X";
                    e.target.value = "";
                    alert("请输入数字！");
                }
            },

            handleClose(done) {
                var inputs = document.getElementsByClassName("secondFieldDetail");
                if(inputs.length < this.secondList.length){
                    this.$confirm('还未录入完成，确认退出？')
                        .then(_ => {
                            //不保存当前数据
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }
                else if(inputs.length == this.secondList.length){
                    this.$confirm('录入数据是否保存？')
                        .then(_ => {
                            //保存当前数据
                            this.dataList = [];
                            var tem = "";
                            tem += this.fieldRowIndex+"_"+this.fieldColIndex;
                            localStorage.setItem(tem,this.dataList);
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }else{
                    this.$confirm('确认关闭？')
                        .then(_ => {
                            //不保存当前数据
                            // this.clearAllInputs();
                            done();
                        })
                        .catch(_ => {});
                }

            },

            //关闭按钮
            cancelOp(){
                console.log(this.tableFlag)
                if(this.tableFlag == '全县面上平均水平'){
                    var spans = document.getElementsByClassName("span");
                    //console.log(this.data.field_4_6);
                    //console.log(spans);
                    if(this.data.field_4_6 == null){
                        this.data.field_4_6 = spans[0].innerHTML;
                    }
                    console.log(this.data.field_4_6);
                }

                if(this.tableFlag == '人工移栽'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_5_6 == null){
                        this.data.field_5_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_5_6);
                }
                if(this.tableFlag == '机械插秧'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_6_6 == null){
                        this.data.field_6_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_6_6);
                }
                if(this.tableFlag == '盘育抛秧'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_7_6 == null){
                        this.data.field_7_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_7_6);
                }
                if(this.tableFlag == '无盘旱育抛秧'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_8_6 == null){
                        this.data.field_8_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_8_6);
                }
                if(this.tableFlag == '撒直播'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_9_6 == null){
                        this.data.field_9_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_9_6);
                }
                if(this.tableFlag == '机条播'){
                    var spans = document.getElementsByClassName("span");
                    if(this.data.field_10_6 == null){
                        this.data.field_10_6 = spans[0].innerHTML;
                    }
                    //console.log(this.data.field_10_6);
                }
                this.detialDialogVisible = false;
            },


            //修改数据退还部分状态
            fixDataBackState(tableName,id){
                axios({
                    url:'/fixDataBackState',
                    method:'post',
                    params:{
                        tableName:tableName,
                        tableId:id
                    }
                }).then(

                ).catch(
                    _=>{
                        return this.$message.error("数据修改失败！");
                    }
                );
            },
            //保存
            getFormList(flag){
                this.$confirm("是否上报？").then(_=>{
                    var date = this.getNowTime();
                    this.data.modification_time = date;
                    for(var i = 0 ;i < this.data.length ; ++i){
                        this.data[i].modification_time = date;
                        this.data[i].in_use = flag;
                    }

                    var str = JSON.stringify(this.data)
                    this.saveInputData(str);
                    if(this.column_id != '' && this.tableName != ''){
                        this.fixDataBackState(this.tableName,this.column_id);
                    }
                }).catch(_=>{
                    return this.$message.info("您取消了操作！");
                });

            },

            //生成保存函数
            saveInputData(str){
                axios({
                    url:'/getInputDataTableTwo0704',
                    method:'post',
                    params:{
                        data:str
                    }
                }).then(res=>{
                    // //console.log(res);
                    this.$message.success("操作成功！");
                }).catch(err=>{
                    return this.$message.error("数据保存失败！");
                })

            },

            //填写详细信息
            fillDetailInfo(e){
                var temData = localStorage.getItem('1_4');
                var Index = e.target.parentNode.cellIndex;
                this.fieldColIndex = Index;//把当前点击的字段所在列保存
                this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
                this.fieldRowIndex = this.fieldRowIndex-2;
                //console.log(this.fieldRowIndex);
                var getTdInnerHTML="";
                getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
                this.tableFlag = getTdInnerHTML;

                // this.secondChooseField(getTdInnerHTML);
                this.secondList = [];
                for(var i = 0 ; i < this.headerList.length ; ++i){
                    if(this.headerList[i].column_name == getTdInnerHTML){
                        this.secondList = this.headerList[i].children;
                    }
                }
                this.tdSecondWidth = 100/this.secondHeaderList.length;
                this.tdSecondFieldWidth = 100/this.secondList.length;
                this.detialDialogVisible = true;
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
                // if(hh < 10){
                //     dateTime = yy + '-' + mm + '-' + dd + ' ' + '0' + hh + ':' + mf + ':' + ss
                // }else{
                dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
                // }
                return dateTime
            },

        },
        created(){
            var people = localStorage.getItem('ms_username');
            //console.log(people)
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            this.getTableData(people,this.column_id);

        },
    }
</script>
<!-- <script>Vue.config.productionTip=false</script> -->
<style>
    .demo-image__placeholder{

    }
    .el-dialog{
        top: 15%;
        left: 4%;
    }
    table tr, table tr td { border:1px solid lightgray; }
</style>