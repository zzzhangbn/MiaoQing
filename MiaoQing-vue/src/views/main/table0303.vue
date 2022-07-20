<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id == ''|| column_id == null">
        <router-link :to="{name:'/tablemain',params: {activeNum: 2}}">
              <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
          </router-link>
      </template>
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
             </router-link>
          </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="59" style="background-color: #6E8B3D;color: #FFFFFF;">年度监测县(市、区)水稻苗情评价动态列表</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td >县（市、区）</td>
                    <td>地区类型</td>
                    <td>全县总面积（万亩）</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td>
                            <el-input v-model="data[index].field_1" :disabled="copyData[index].field_1 !=='' && copyData[index].field_1 !== null"></el-input>
                        </td>
                        <td>
                            <el-input v-model="data[index].field_2" :disabled="copyData[index].field_2 !=='' && copyData[index].field_2 !== null"></el-input>
                        </td>
                        <td>
                            <el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data[index].field_3" 
                            :disabled="copyData[index].field_3 !=='' && copyData[index].field_3 !== null && column_id == '' "></el-input>
                        </td>
                    </tr>
            </template>
            </table>
            <div style="width: 100%;height: 10px;"></div>
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <template v-for="(item,index) in data">
                    <tr>
                        <template v-if="data[index].field_4_1 == null || data[index].field_4_1 == '' || data[index].field_4_2_1 == null || data[index].field_4_2_1 == '' ||
                        data[index].field_4_2_2 == null || data[index].field_4_2_2 == '' || data[index].field_4_3_1 == null || data[index].field_4_3_1 == '' || data[index].field_4_3_2 == null || data[index].field_4_3_2==''
                        || data[index].field_4_4_1 == null || data[index].field_4_4_1 == '' || data[index].field_4_4_2 == null || data[index].field_4_4_2 == ''">
                            <td style="color: red;">进程1（大田直播后20天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程1')"></i></td>
                        </template>
                        <template v-if="data[index].field_4_1 != null && data[index].field_4_1 != '' && data[index].field_4_2_1 != null && data[index].field_4_2_1 != '' &&
                        data[index].field_4_2_2 != null && data[index].field_4_2_2 != '' && data[index].field_4_3_1 != null && data[index].field_4_3_1 != '' && data[index].field_4_3_2 != null && data[index].field_4_3_2!=''
                        && data[index].field_4_4_1 != null && data[index].field_4_4_1 != '' && data[index].field_4_4_2 != null && data[index].field_4_4_2 != ''">
                            <td style="color: green;">进程1（大田直播后20天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程1')"></i></td>
                        </template>
                        <template v-if="data[index].field_5_1 == null || data[index].field_5_1 == '' || data[index].field_5_2_1 == null || data[index].field_5_2_1 == '' ||
                        data[index].field_5_2_2 == null || data[index].field_5_2_2 == '' || data[index].field_5_3_1 == null || data[index].field_5_3_1 == '' || data[index].field_5_3_2 == null || data[index].field_5_3_2==''
                        || data[index].field_5_4_1 == null || data[index].field_5_4_1 == '' || data[index].field_5_4_2 == null || data[index].field_5_4_2 == ''">
                            <td style="color: red;">进程2（大田直播后28天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程2')"></i></td>
                        </template>
                        <template v-if="data[index].field_5_1 != null && data[index].field_5_1 != '' && data[index].field_5_2_1 != null && data[index].field_5_2_1 != '' &&
                        data[index].field_5_2_2 != null && data[index].field_5_2_2 != '' && data[index].field_5_3_1 != null && data[index].field_5_3_1 != '' && data[index].field_5_3_2 != null && data[index].field_5_3_2!=''
                        && data[index].field_5_4_1 != null && data[index].field_5_4_1 != '' && data[index].field_5_4_2 != null && data[index].field_5_4_2 != ''">
                            <td style="color: green;">进程2（大田直播后28天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程2')"></i></td>
                        </template>
                        <template v-if="data[index].field_6_1 == null || data[index].field_6_1 == '' || data[index].field_6_2_1 == null || data[index].field_6_2_1 == '' ||
                        data[index].field_6_2_2 == null || data[index].field_6_2_2 == '' || data[index].field_6_3_1 == null || data[index].field_6_3_1 == '' || data[index].field_6_3_2 == null || data[index].field_6_3_2==''
                        || data[index].field_6_4_1 == null || data[index].field_6_4_1 == '' || data[index].field_6_4_2 == null || data[index].field_6_4_2 == ''">
                            <td style="color: red;">进程3（大田直播后35天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程3')"></i></td>
                        </template>
                        <template v-if="data[index].field_6_1 != null && data[index].field_6_1 != '' && data[index].field_6_2_1 != null && data[index].field_6_2_1 != '' &&
                        data[index].field_6_2_2 != null && data[index].field_6_2_2 != '' && data[index].field_6_3_1 != null && data[index].field_6_3_1 != '' && data[index].field_6_3_2 != null && data[index].field_6_3_2!=''
                        && data[index].field_6_4_1 != null && data[index].field_6_4_1 != '' && data[index].field_6_4_2 != null && data[index].field_6_4_2 != ''">
                            <td style="color: green;">进程3（大田直播后35天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程3')"></i></td>
                        </template>
                        <template v-if="data[index].field_7_1 == null || data[index].field_7_1 == '' || data[index].field_7_2_1 == null || data[index].field_7_2_1 == '' ||
                        data[index].field_7_2_2 == null || data[index].field_7_2_2 == '' || data[index].field_7_3_1 == null || data[index].field_7_3_1 == '' || data[index].field_7_3_2 == null || data[index].field_7_3_2==''
                        || data[index].field_7_4_1 == null || data[index].field_7_4_1 == '' || data[index].field_7_4_2 == null || data[index].field_7_4_2 == ''">
                            <td style="color: red;">进程4（大田直播后42天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程4')"></i></td>
                        </template>
                        <template v-if="data[index].field_7_1 != null && data[index].field_7_1 != '' && data[index].field_7_2_1 != null && data[index].field_7_2_1 != '' &&
                        data[index].field_7_2_2 != null && data[index].field_7_2_2 != '' && data[index].field_7_3_1 != null && data[index].field_7_3_1 != '' && data[index].field_7_3_2 != null && data[index].field_7_3_2!=''
                        && data[index].field_7_4_1 != null && data[index].field_7_4_1 != '' && data[index].field_7_4_2 != null && data[index].field_7_4_2 != ''">
                            <td style="color: green;">进程4（大田直播后42天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程4')"></i></td>
                        </template>
                        
                        <template v-if="data[index].field_8_1 == null || data[index].field_8_1 == '' || data[index].field_8_2_1 == null || data[index].field_8_2_1 == '' ||
                        data[index].field_8_2_2 == null || data[index].field_8_2_2 == '' || data[index].field_8_3_1 == null || data[index].field_8_3_1 == '' || data[index].field_8_3_2 == null || data[index].field_8_3_2==''
                        || data[index].field_8_4_1 == null || data[index].field_8_4_1 == '' || data[index].field_8_4_2 == null || data[index].field_8_4_2 == ''">
                            <td style="color: red;">进程5（大田直播后49天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程5')"></i></td>
                        </template>
                        <template v-if="data[index].field_8_1 != null && data[index].field_8_1 != '' && data[index].field_8_2_1 != null && data[index].field_8_2_1 != '' &&
                        data[index].field_8_2_2 != null && data[index].field_8_2_2 != '' && data[index].field_8_3_1 != null && data[index].field_8_3_1 != '' && data[index].field_8_3_2 != null && data[index].field_8_3_2!=''
                        && data[index].field_8_4_1 != null && data[index].field_8_4_1 != '' && data[index].field_8_4_2 != null && data[index].field_8_4_2 != ''">
                            <td style="color: green;">进程5（大田直播后49天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程5')"></i></td>
                        </template>
                      
                        <template v-if="data[index].field_9_1 == null || data[index].field_9_1 == '' || data[index].field_9_2_1 == null || data[index].field_9_2_1 == '' ||
                        data[index].field_9_2_2 == null || data[index].field_9_2_2 == '' || data[index].field_9_3_1 == null || data[index].field_9_3_1 == '' || data[index].field_9_3_2 == null || data[index].field_9_3_2==''
                        || data[index].field_9_4_1 == null || data[index].field_9_4_1 == '' || data[index].field_9_4_2 == null || data[index].field_9_4_2 == ''">
                            <td style="color: red;">进程6（大田直播后56天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程6')"></i></td>
                        </template>
                        <template v-if="data[index].field_9_1 != null && data[index].field_9_1 != '' && data[index].field_9_2_1 != null && data[index].field_9_2_1 != '' &&
                        data[index].field_9_2_2 != null && data[index].field_9_2_2 != '' && data[index].field_9_3_1 != null && data[index].field_9_3_1 != '' && data[index].field_9_3_2 != null && data[index].field_9_3_2!=''
                        && data[index].field_9_4_1 != null && data[index].field_9_4_1 != '' && data[index].field_9_4_2 != null && data[index].field_9_4_2 != ''">
                            <td style="color: green;">进程6（大田直播后56天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程6')"></i></td>
                        </template>
                        
                        <template v-if="data[index].field_10_1 == null || data[index].field_10_1 == '' || data[index].field_10_2_1 == null || data[index].field_10_2_1 == '' ||
                        data[index].field_10_2_2 == null || data[index].field_10_2_2 == '' || data[index].field_10_3_1 == null || data[index].field_10_3_1 == '' || data[index].field_10_3_2 == null || data[index].field_10_3_2==''
                        || data[index].field_10_4_1 == null || data[index].field_10_4_1 == '' || data[index].field_10_4_2 == null || data[index].field_10_4_2 == ''">
                            <td style="color: red;">进程7（大田直播后66天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程7')"></i></td>
                        </template>
                        <template v-if="data[index].field_10_1 != null && data[index].field_10_1 != '' && data[index].field_10_2_1 != null && data[index].field_10_2_1 != '' &&
                        data[index].field_10_2_2 != null && data[index].field_10_2_2 != '' && data[index].field_10_3_1 != null && data[index].field_10_3_1 != '' && data[index].field_10_3_2 != null && data[index].field_10_3_2!=''
                        && data[index].field_10_4_1 != null && data[index].field_10_4_1 != '' && data[index].field_10_4_2 != null && data[index].field_10_4_2 != ''">
                            <td style="color: green;">进程7（大田直播后66天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程7')"></i></td>
                        </template>
                        
                        <template v-if="data[index].field_11_1 == null || data[index].field_11_1 == '' || data[index].field_11_2_1 == null || data[index].field_11_2_1 == '' ||
                        data[index].field_11_2_2 == null || data[index].field_11_2_2 == '' || data[index].field_11_3_1 == null || data[index].field_11_3_1 == '' || data[index].field_11_3_2 == null || data[index].field_11_3_2==''
                        || data[index].field_11_4_1 == null || data[index].field_11_4_1 == '' || data[index].field_11_4_2 == null || data[index].field_11_4_2 == ''">
                            <td style="color: red;">进程8（大田直播后76天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程8')"></i></td>
                        </template>
                        <template v-if="data[index].field_11_1 != null && data[index].field_11_1 != '' && data[index].field_11_2_1 != null && data[index].field_11_2_1 != '' &&
                        data[index].field_11_2_2 != null && data[index].field_11_2_2 != '' && data[index].field_11_3_1 != null && data[index].field_11_3_1 != '' && data[index].field_11_3_2 != null && data[index].field_11_3_2!=''
                        && data[index].field_11_4_1 != null && data[index].field_11_4_1 != '' && data[index].field_11_4_2 != null && data[index].field_11_4_2 != ''">
                            <td style="color: green;">进程8（大田直播后76天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程8')"></i></td>
                        </template>
                        
                        <template v-if="data[index].field_12_1 == null || data[index].field_12_1 == '' || data[index].field_12_2_1 == null || data[index].field_12_2_1 == '' ||
                        data[index].field_12_2_2 == null || data[index].field_12_2_2 == '' || data[index].field_12_3_1 == null || data[index].field_12_3_1 == '' || data[index].field_12_3_2 == null || data[index].field_12_3_2==''
                        || data[index].field_12_4_1 == null || data[index].field_12_4_1 == '' || data[index].field_12_4_2 == null || data[index].field_12_4_2 == ''">
                            <td style="color: red;">进程9（大田直播后86天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程9')"></i></td>
                        </template>
                        <template v-if="data[index].field_12_1 != null && data[index].field_12_1 != '' && data[index].field_12_2_1 != null && data[index].field_12_2_1 != '' &&
                        data[index].field_12_2_2 != null && data[index].field_12_2_2 != '' && data[index].field_12_3_1 != null && data[index].field_12_3_1 != '' && data[index].field_12_3_2 != null && data[index].field_12_3_2!=''
                        && data[index].field_12_4_1 != null && data[index].field_12_4_1 != '' && data[index].field_12_4_2 != null && data[index].field_12_4_2 != ''">
                            <td style="color: green;">进程9（大田直播后86天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程9')"></i></td>
                        </template>
                        
                    </tr>
                    <template v-if="fold1 == true||fold2 == true||fold3 == true||fold4 == true||fold5 == true||fold6 == true||fold7 == true||fold8 == true||fold9 == true">
                        <div style="width: 100%;height: 10px;"></div>
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td colspan="2">一类苗
                                （万亩）</td>
                            <td colspan="2">二类苗
                                （万亩）</td>
                            <td colspan="2">三类苗
                                （万亩）</td>
                        </tr>
                        <template v-if="fold1 == true">
                            <tr>
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_4_1" :disabled="copyData[index].field_4_1 !=='' && copyData[index].field_4_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_4_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程1')" 
                                    :disabled="copyData[index].field_4_2_1 !=='' && copyData[index].field_4_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_4_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_4_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程1')" 
                                    :disabled="copyData[index].field_4_3_1 !=='' && copyData[index].field_4_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_4_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_4_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程1')" 
                                    :disabled="copyData[index].field_4_4_1 !=='' && copyData[index].field_4_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_4_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold2 == true">
                            <tr> 
                                <td>
                                    <el-date-picker 
                                        v-model="data[index].field_5_1" :disabled="copyData[index].field_5_1 !=='' && copyData[index].field_5_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_5_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程2')" 
                                    :disabled="copyData[index].field_5_2_1 !=='' && copyData[index].field_5_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_5_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_5_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程2')" 
                                    :disabled="copyData[index].field_5_3_1 !=='' && copyData[index].field_5_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_5_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_5_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程2')" 
                                    :disabled="copyData[index].field_5_4_1 !=='' && copyData[index].field_5_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_5_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold3 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_6_1" :disabled="copyData[index].field_6_1 !=='' && copyData[index].field_6_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_6_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程3')" 
                                    :disabled="copyData[index].field_6_2_1 !=='' && copyData[index].field_6_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_6_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_6_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程3')" 
                                    :disabled="copyData[index].field_6_3_1 !=='' && copyData[index].field_6_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_6_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_6_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程3')" 
                                    :disabled="copyData[index].field_6_4_1 !=='' && copyData[index].field_6_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_6_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold4 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_7_1" :disabled="copyData[index].field_7_1 !=='' && copyData[index].field_7_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_7_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程4')" 
                                    :disabled="copyData[index].field_7_2_1 !=='' && copyData[index].field_7_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_7_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_7_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程4')"
                                     :disabled="copyData[index].field_7_3_1 !=='' && copyData[index].field_7_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_7_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_7_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程4')" 
                                    :disabled="copyData[index].field_7_4_1 !=='' && copyData[index].field_7_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_7_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold5 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_8_1" :disabled="copyData[index].field_8_1 !=='' && copyData[index].field_8_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_8_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程5')" 
                                    :disabled="copyData[index].field_8_2_1 !=='' && copyData[index].field_8_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_8_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_8_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程5')" 
                                    :disabled="copyData[index].field_8_3_1 !=='' && copyData[index].field_8_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_8_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_8_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程5')" 
                                    :disabled="copyData[index].field_8_4_1 !=='' && copyData[index].field_8_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_8_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold6 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_9_1" :disabled="copyData[index].field_9_1 !=='' && copyData[index].field_9_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_9_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程6')" 
                                    :disabled="copyData[index].field_9_2_1 !=='' && copyData[index].field_9_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_9_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_9_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程6')" 
                                    :disabled="copyData[index].field_9_3_1 !=='' && copyData[index].field_9_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_9_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_9_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程6')" 
                                    :disabled="copyData[index].field_9_4_1 !=='' && copyData[index].field_9_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_9_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold7 == true">
                            <tr> 
                                <td>
                                    <el-date-picker 
                                        v-model="data[index].field_10_1" :disabled="copyData[index].field_10_1 !=='' && copyData[index].field_10_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_10_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程7')" 
                                    :disabled="copyData[index].field_10_2_1 !=='' && copyData[index].field_10_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_10_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_10_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程7')" 
                                    :disabled="copyData[index].field_10_3_1 !=='' && copyData[index].field_10_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_10_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_10_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程7')" 
                                    :disabled="copyData[index].field_10_4_1 !=='' && copyData[index].field_10_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_10_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold8 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_11_1" :disabled="copyData[index].field_11_1 !=='' && copyData[index].field_11_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_11_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程8')" 
                                    :disabled="copyData[index].field_11_2_1 !=='' && copyData[index].field_11_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_11_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_11_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程8')" 
                                    :disabled="copyData[index].field_11_3_1 !=='' && copyData[index].field_11_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_11_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_11_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程8')" 
                                    :disabled="copyData[index].field_11_4_1 !=='' && copyData[index].field_11_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_11_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                        <template v-if="fold9 == true">
                            <tr> 
                                <td>
                                    <el-date-picker
                                        v-model="data[index].field_12_1" :disabled="copyData[index].field_12_1 !=='' && copyData[index].field_12_1 !== null && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                        </el-date-picker>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_12_2_1"  @blur.prevent="checkFirstFieldInput421($event,index,'进程9')" 
                                    :disabled="copyData[index].field_12_2_1 !=='' && copyData[index].field_12_2_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_12_2_2" ></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_12_3_1" @blur.prevent="checkFirstFieldInput431($event,index,'进程9')" 
                                    :disabled="copyData[index].field_12_3_1 !=='' && copyData[index].field_12_3_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_12_3_2"></el-input>
                                </td>
                                <td>
                                    <el-input v-model="data[index].field_12_4_1" @blur.prevent="checkFirstFieldInput441($event,index,'进程9')" 
                                    :disabled="copyData[index].field_12_4_1 !=='' && copyData[index].field_12_4_1 !== null && column_id == '' "></el-input>
                                </td>
                                <td>
                                    <el-input disabled v-model="data[index].field_12_4_2"></el-input>
                                </td>
                            </tr>
                        </template>
                    </template>
                </template>
            </table>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    export default{
        data(){
            return{
                data:[],
                fieldRowIndex:0,
                detialDialogVisible:false,
                copyData:[],
                column_id:'',
                tableName:'',
                tableFlag:'',
                fold1:false,
                fold2:false,
                fold3:false,
                fold4:false,
                fold5:false,
                fold6:false,
                fold7:false,
                fold8:false,
                fold9:false,
            }
        },
        methods:{
            foldOrNot(e,tag){
                if(tag == "进程1"){
                    
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold1 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold1 = true;
                }
            }
            if(tag == "进程2"){
                  
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold2 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold2 = true;
                }
            }
            if(tag == "进程3"){
                   
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold3 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold3 = true;
                }
            } 
            if(tag == "进程4"){
                   
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold4 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold4 = true;
                }
            }   
            if(tag == "进程5"){
                    
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold5 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold5 = true;
                }
            } 
            if(tag == "进程6"){
                    
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold6 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold6 = true;
                }
            } 
            if(tag == "进程7"){
                    
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold7 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold7 = true;
                }
            } 
            if(tag == "进程8"){
                    
                if(e.target.className == "el-icon-caret-bottom"){
                    e.target.className = "el-icon-caret-top";
                    this.fold8 = false;
                }else{
                    e.target.className = "el-icon-caret-bottom";
                    this.fold8 = true;
                }
            } 
            if(tag == "进程9"){
                    
                    if(e.target.className == "el-icon-caret-bottom"){
                        e.target.className = "el-icon-caret-top";
                        this.fold9 = false;
                    }else{
                        e.target.className = "el-icon-caret-bottom";
                        this.fold9 = true;
                    }
                } 
                
                
            },
            //上报
            getFormList(e){
                this.$confirm("是否上报？").then(_=>{
                    var date = this.getNowTime();
                    for(var i = 0 ;i < this.data.length ; ++i){
                        this.data[i].modification_time = date;
                    }
                    
                    
                    var list = this.data;
                    var str = '';
                    for(var i = 0 ; i < list.length ; ++i){
                        str += JSON.stringify(list[i]) + "#";
                    }
                    
                    this.saveInputData(str);
                    if(this.column_id != '' && this.tableName != ''){
                        this.fixDataBackState(this.tableName,this.column_id);
                    }
                }).catch(_=>{
                return this.$message.info("您取消了操作！");
                });
                                                            
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
            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTable0303',
                method:'post',
                params:{
                  data:list
                }
              }).then(res=>{
                //console.log(res);
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })

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
            cancelOp(){
                this.detialDialogVisible = false;
            },
            //查询该用户下数据
            getUserAllData0303(people,column_id){
                axios({
                    url:'/getUserAllData0303',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id,
                        startYear:0,
                        endYear:0
                    }
                }).then(res=>{
                    this.data = res.data;
                    const json=JSON.stringify(res.data);
                    this.copyData = JSON.parse(json);
                
                }).catch(err=>{
                    return this.$message.error("获取表2-2内容失败！");
                });
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
            //将建档卡2-1中已知内容插入到表3-3中
            getMonitorCardContentInto0303(people){
                axios({
                    url:'/getMonitorCardContentInto0303',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    //console.log(res);
                }).catch(err=>{
                    return this.$message.error("获取表2-2内容失败！");
                });
            },
            //填写详细信息
            fillDetailInfo(e){
              var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
             this.fieldRowIndex = this.fieldRowIndex-2; 
            
              var getTdInnerHTML="";
              getTdInnerHTML = getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
              //console.log(getTdInnerHTML);
              this.tableFlag = getTdInnerHTML;
              this.detialDialogVisible = true;
            },
            checkFirstFieldInput421(e,fieldRowIndex,tableFlag){
                //console.log(e);
                if(tableFlag == '进程1'){
                    this.data[fieldRowIndex].field_4_2_2 = (Math.round((this.data[fieldRowIndex].field_4_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(tableFlag == '进程2'){
                    this.data[fieldRowIndex].field_5_2_2 = (Math.round((this.data[fieldRowIndex].field_5_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程3'){
                    this.data[fieldRowIndex].field_6_2_2 = (Math.round((this.data[fieldRowIndex].field_6_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程4'){
                    this.data[fieldRowIndex].field_7_2_2 = (Math.round((this.data[fieldRowIndex].field_7_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程5'){
                    this.data[fieldRowIndex].field_8_2_2 = (Math.round((this.data[fieldRowIndex].field_8_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程6'){
                    this.data[fieldRowIndex].field_9_2_2 = (Math.round((this.data[fieldRowIndex].field_9_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程7'){
                    this.data[fieldRowIndex].field_10_2_2 = (Math.round((this.data[fieldRowIndex].field_10_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程8'){
                    this.data[fieldRowIndex].field_11_2_2 = (Math.round((this.data[fieldRowIndex].field_11_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程9'){
                    this.data[fieldRowIndex].field_12_2_2 = (Math.round((this.data[fieldRowIndex].field_12_2_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
            },
            checkFirstFieldInput431(e,fieldRowIndex,tableFlag){
                if(tableFlag == '进程1'){
                    this.data[fieldRowIndex].field_4_3_2 = (Math.round((this.data[fieldRowIndex].field_4_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(tableFlag == '进程2'){
                    this.data[fieldRowIndex].field_5_3_2 = (Math.round((this.data[fieldRowIndex].field_5_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程3'){
                    this.data[fieldRowIndex].field_6_3_2 = (Math.round((this.data[fieldRowIndex].field_6_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程4'){
                    this.data[fieldRowIndex].field_7_3_2 = (Math.round((this.data[fieldRowIndex].field_7_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程5'){
                    this.data[fieldRowIndex].field_8_3_2 = (Math.round((this.data[fieldRowIndex].field_8_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程6'){
                    this.data[fieldRowIndex].field_9_3_2 = (Math.round((this.data[fieldRowIndex].field_9_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程7'){
                    this.data[fieldRowIndex].field_10_3_2 = (Math.round((this.data[fieldRowIndex].field_10_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程8'){
                    this.data[fieldRowIndex].field_11_3_2 = (Math.round((this.data[fieldRowIndex].field_11_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程9'){
                    this.data[fieldRowIndex].field_12_3_2 = (Math.round((this.data[fieldRowIndex].field_12_3_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
            },
            checkFirstFieldInput441(e,fieldRowIndex,tableFlag){
                if(tableFlag == '进程1'){
                    this.data[fieldRowIndex].field_4_4_2 = (Math.round((this.data[fieldRowIndex].field_4_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                
                if(tableFlag == '进程2'){
                    this.data[fieldRowIndex].field_5_4_2 = (Math.round((this.data[fieldRowIndex].field_5_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程3'){
                    this.data[fieldRowIndex].field_6_4_2 = (Math.round((this.data[fieldRowIndex].field_6_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程4'){
                    this.data[fieldRowIndex].field_7_4_2 = (Math.round((this.data[fieldRowIndex].field_7_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程5'){
                    this.data[fieldRowIndex].field_8_4_2 = (Math.round((this.data[fieldRowIndex].field_8_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程6'){
                    this.data[fieldRowIndex].field_9_4_2 = (Math.round((this.data[fieldRowIndex].field_9_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程7'){
                    this.data[fieldRowIndex].field_10_4_2 = (Math.round((this.data[fieldRowIndex].field_10_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程8'){
                    this.data[fieldRowIndex].field_11_4_2 = (Math.round((this.data[fieldRowIndex].field_11_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
                if(tableFlag == '进程9'){
                    this.data[fieldRowIndex].field_12_4_2 = (Math.round((this.data[fieldRowIndex].field_12_4_1 /this.data[fieldRowIndex].field_3) * 100*100)/100).toFixed(2) + '%';
                }
            },
            close(){
                this.detialDialogVisible = false;
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
                this.column_id = this.$route.query.column_id;
                this.tableName = this.$route.query.record_table;
            }
            // this.getMonitorCardContentInto0303(people);
            this.getUserAllData0303(people,this.column_id);
        },
    }
</script>