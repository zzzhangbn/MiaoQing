<template>
    <div class="demo-image__placeholder">
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
              <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
             </router-link>
          </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;" @click="getFormList('1')">提交</el-button>
        <div id="containner_div" style="width: 100%;height: 70%;">
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="16" style="background-color: #6E8B3D;color: #FFFFFF;">年度监测县(市、区)双季稻生育进度调查表（直播稻）</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 4%;">县（市、区）</td>
                    <td style="width: 4%;">监测田块</td>
                    <td style="width: 4%;">地区类型</td>
                    <td style="width: 4%;">稻作类型</td>
                    <td style="width: 4%;">品系类型</td>
                    <td style="width: 4%;">品种名称</td>
                    <td>栽培方式(直播)</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_1" readonly></el-input>
                        </td>
                        <td style="width: 8.5%;">
                            <el-input v-model="data[index].field_2" readonly></el-input>
                        </td>
                        <td style="width: 7.6%;">
                            <el-input v-model="data[index].field_3" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_4" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_5" readonly></el-input>
                        </td>
                        <td style="width: 6%;">
                            <el-input v-model="data[index].field_6" readonly></el-input>
                        </td>
                        <td style="width: 7.8%;">
                            <el-input v-model="data[index].field_7" readonly></el-input><!--固定为 直播-->
                        </td>
                    </tr>
                </template>
            </table>

            <div style="width: 100%;height: 10px;"></div>

            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <template v-for="(item,index) in data">
                    <tr>
                        <template v-if="data[index].field_8_1 == null || data[index].field_8_1 == '' || data[index].field_8_2 == null || data[index].field_8_2 == '' || data[index].field_8_3 == null
                        || data[index].field_8_3 == '' || data[index].field_8_4 == null || data[index].field_8_4 == ''">
                            <td style="color:red">进程1（大田直播后20天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程1')"></i></td>
                        </template>
                        <template v-if="data[index].field_8_1 != null && data[index].field_8_1 != '' && data[index].field_8_2 != null && data[index].field_8_2 != '' && data[index].field_8_3 != null
                        && data[index].field_8_3 != '' || data[index].field_8_4 != null && data[index].field_8_4 != ''">
                            <td style="color: green;">进程1（大田直播后20天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程1')"></i></td>
                        </template>
                        <template v-if="data[index].field_9_1 == null || data[index].field_9_1 == '' || data[index].field_9_2 == null || data[index].field_9_2 == '' || data[index].field_9_3 == null
                        || data[index].field_9_3 == '' || data[index].field_9_4 == null || data[index].field_9_4 == ''">
                            <td style="color: red;">进程2（大田直播后25天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程2')"></i></td>
                        </template>
                        <template v-if="data[index].field_9_1 != null && data[index].field_9_1 != '' && data[index].field_9_2 != null && data[index].field_9_2 != '' && data[index].field_9_3 != null
                        && data[index].field_9_3 != '' || data[index].field_9_4 != null && data[index].field_9_4 != ''">
                            <td style="color: green;">进程2（大田直播后25天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程2')"></i></td>
                        </template>
                        <template v-if="data[index].field_10_1 == null || data[index].field_10_1 == '' || data[index].field_10_2 == null || data[index].field_10_2 == '' || data[index].field_10_3 == null
                        || data[index].field_10_3 == '' || data[index].field_10_4 == null || data[index].field_10_4 == ''">
                            <td style="color: red;">进程3（大田直播后30天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程3')"></i></td>
                        </template>
                        <template v-if="data[index].field_10_1 != null && data[index].field_10_1 != '' && data[index].field_10_2 != null && data[index].field_10_2 != '' && data[index].field_10_3 != null
                        && data[index].field_10_3 != '' || data[index].field_10_4 != null && data[index].field_10_4 != ''">
                            <td style="color: green;">进程3（大田直播后30天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程3')"></i></td>
                        </template>
                        <template v-if="data[index].field_11_1 == null || data[index].field_11_1 == '' || data[index].field_11_2 == null || data[index].field_11_2 == '' || data[index].field_11_3 == null
                        || data[index].field_11_3 == '' || data[index].field_11_4 == null || data[index].field_11_4 == ''">
                            <td style="color: red;">进程4（大田直播后35天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程4')"></i></td>
                        </template>
                        <template v-if="data[index].field_11_1 != null && data[index].field_11_1 != '' && data[index].field_11_2 != null && data[index].field_11_2 != '' && data[index].field_11_3 != null
                        && data[index].field_11_3 != '' || data[index].field_11_4 != null && data[index].field_11_4 != ''">
                            <td style="color: green;">进程4（大田直播后35天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程4')"></i></td>
                        </template>
                        <template v-if="data[index].field_12_1 == null || data[index].field_12_1 == '' || data[index].field_12_2 == null || data[index].field_12_2 == '' || data[index].field_12_3 == null
                        || data[index].field_12_3 == '' || data[index].field_12_4 == null || data[index].field_12_4 == ''">
                            <td style="color: red;">进程5（大田直播后40天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程5')"></i></td>
                        </template>
                        <template v-if="data[index].field_12_1 != null && data[index].field_12_1 != '' && data[index].field_12_2 != null && data[index].field_12_2 != '' && data[index].field_12_3 != null
                        && data[index].field_12_3 != '' || data[index].field_12_4 != null && data[index].field_12_4 != ''">
                            <td style="color: green;">进程5（大田直播后40天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程5')"></i></td>
                        </template>
                       <template v-if="data[index].field_13_1 == null || data[index].field_13_1 == '' || data[index].field_13_2 == null || data[index].field_13_2 == '' || data[index].field_13_3 == null
                       || data[index].field_13_3 == '' || data[index].field_13_4 == null || data[index].field_13_4 == ''">
                           <td style="color: red;">进程6（大田直播后50天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程6')"></i></td>
                        </template>
                       <template v-if="data[index].field_13_1 != null && data[index].field_13_1 != '' && data[index].field_13_2 != null && data[index].field_13_2 != '' && data[index].field_13_3 != null
                       && data[index].field_13_3 != '' || data[index].field_13_4 != null && data[index].field_13_4 != ''">
                           <td style="color: green;">进程6（大田直播后50天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程6')"></i></td>
                        </template>
                        <template v-if="data[index].field_14_1 == null || data[index].field_14_1 == '' || data[index].field_14_2 == null || data[index].field_14_2 == '' || data[index].field_14_3 == null
                        || data[index].field_14_3 == '' || data[index].field_14_4 == null || data[index].field_14_4 == ''">
                            <td style="color: red;">进程7（大田直播后60天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程7')"></i></td>
                        </template>
                        <template v-if="data[index].field_14_1 != null && data[index].field_14_1 != '' && data[index].field_14_2 != null && data[index].field_14_2 != '' && data[index].field_14_3 != null
                        && data[index].field_14_3 != '' || data[index].field_14_4 != null && data[index].field_14_4 != ''">
                            <td style="color: green;">进程7（大田直播后60天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程7')"></i></td>
                        </template>
                        <!-- <template v-if="data[index].field_15_1 == null || data[index].field_15_1 == '' || data[index].field_15_2 == null || data[index].field_15_2 == '' || data[index].field_15_3 == null
                        || data[index].field_15_3 == '' || data[index].field_15_4 == null || data[index].field_15_4 == ''">
                            <td style="color: red;">进程8（大田直播后76天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程8')"></i></td>
                        </template>
                        <template v-if="data[index].field_15_1 != null && data[index].field_15_1 != '' && data[index].field_15_2 != null && data[index].field_15_2 != '' && data[index].field_15_3 != null
                        && data[index].field_15_3 != '' || data[index].field_15_4 != null && data[index].field_15_4 != ''">
                            <td style="color: green;">进程8（大田直播后76天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程8')"></i></td>
                        </template>
                        <template v-if="data[index].field_16_1 == null || data[index].field_16_1 == '' || data[index].field_16_2 == null || data[index].field_16_2 == '' || data[index].field_16_3 == null
                        || data[index].field_16_3 == '' || data[index].field_16_4 == null || data[index].field_16_4 == ''">
                            <td style="color: red;">进程9（大田直播后86天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程9')"></i></td>
                        </template>
                        <template v-if="data[index].field_16_1 != null && data[index].field_16_1 != '' && data[index].field_16_2 != null && data[index].field_16_2 != '' && data[index].field_16_3 != null
                        && data[index].field_16_3 != '' || data[index].field_16_4 != null && data[index].field_16_4 != ''">
                            <td style="color: green;">进程9（大田直播后86天）<i class='el-icon-caret-top' @click="foldOrNot($event,'进程9')"></i></td>
                        </template> -->
                        
                    </tr>
                    <template v-if="fold2 == true || fold3 == true || fold4 == true || fold5 == true || fold6 == true || fold7 == true ">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>茎蘖数(万/亩)</td>
                        </tr>
                    </template>
                    <template v-if="fold1 == true ">
                        <tr style="background-color: #CDBE70;color: green;">
                            <td>日期</td>
                            <td>生育进程</td>
                            <td>叶龄（叶）</td>
                            <td>基本苗(万/亩)</td>
                        </tr>
                    </template>
                    <template v-if="fold1 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_8_1" :disabled="data[index].field_8_1 != null && data[index].field_8_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_8_2" :disabled="data[index].field_8_2 != null && data[index].field_8_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_8_3" :disabled="data[index].field_8_3 != null && data[index].field_8_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_8_4" :disabled="data[index].field_8_4 != null && data[index].field_8_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold2 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_9_1" :disabled="data[index].field_9_1 != null && data[index].field_9_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_9_2" :disabled="data[index].field_9_2 != null && data[index].field_9_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_9_3" :disabled="data[index].field_9_3 != null && data[index].field_9_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_9_4" :disabled="data[index].field_9_4 != null && data[index].field_9_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold3 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_10_1" :disabled="data[index].field_10_1 != null && data[index].field_10_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_10_2" :disabled="data[index].field_10_2 != null && data[index].field_10_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_10_3" :disabled="data[index].field_10_3 != null && data[index].field_10_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_10_4" :disabled="data[index].field_10_4 != null && data[index].field_10_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold4 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_11_1" :disabled="data[index].field_11_1 != null && data[index].field_11_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_11_2" :disabled="data[index].field_11_2 != null && data[index].field_11_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_11_3" :disabled="data[index].field_11_3 != null && data[index].field_11_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_11_4" :disabled="data[index].field_11_4 != null && data[index].field_11_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold5 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_12_1" :disabled="data[index].field_12_1 != null && data[index].field_12_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_12_2" :disabled="data[index].field_12_2 != null && data[index].field_12_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_12_3" :disabled="data[index].field_12_3 != null && data[index].field_12_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_12_4" :disabled="data[index].field_12_4 != null && data[index].field_12_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold6 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_13_1" :disabled="data[index].field_13_1 != null && data[index].field_13_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_13_2" :disabled="data[index].field_13_2 != null && data[index].field_13_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_13_3" :disabled="data[index].field_13_3 != null && data[index].field_13_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_13_4" :disabled="data[index].field_13_4 != null && data[index].field_13_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <template v-if="fold7 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_14_1" :disabled="data[index].field_14_1 != null && data[index].field_14_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_14_2" :disabled="data[index].field_14_2 != null && data[index].field_14_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_14_3" :disabled="data[index].field_14_3 != null && data[index].field_14_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_14_4" :disabled="data[index].field_14_4 != null && data[index].field_14_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template>
                    <!-- <template v-if="fold8 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_15_1" :disabled="data[index].field_15_1 != null && data[index].field_15_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_15_2" :disabled="data[index].field_15_2 != null && data[index].field_15_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_15_3" :disabled="data[index].field_15_3 != null && data[index].field_15_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_15_4" :disabled="data[index].field_15_4 != null && data[index].field_15_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template> -->
                    <!-- <template v-if="fold9 == true">
                        <tr>
                            <td>
                                <el-date-picker
                                        v-model="data[index].field_16_1" :disabled="data[index].field_16_1 != null && data[index].field_16_1 != '' && column_id == '' "
                                        type="date"
                                        placeholder="选择日期">
                                </el-date-picker>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_16_2" :disabled="data[index].field_16_2 != null && data[index].field_16_2 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_16_3" :disabled="data[index].field_16_3 != null && data[index].field_16_3 != '' && column_id == '' "></el-input>
                            </td>
                            <td>
                                <el-input  v-model="data[index].field_16_4" :disabled="data[index].field_16_4 != null && data[index].field_16_4 != '' && column_id == '' "></el-input>
                            </td>
                        </tr>
                    </template> -->
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
                daoZuoTypeData:[],//稻作类型
                pinXiTypeData:[],//品系类型
                zaiZhiTypeData:[],//栽植方式
                fieldRowIndex:0,
                detialDialogVisible:false,
                copyData:[],
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
                column_id:'',
                tableName:'',
                monitor_location:'',
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
            // if(tag == "进程8"){
                    
            //     if(e.target.className == "el-icon-caret-bottom"){
            //         e.target.className = "el-icon-caret-top";
            //         this.fold8 = false;
            //     }else{
            //         e.target.className = "el-icon-caret-bottom";
            //         this.fold8 = true;
            //     }
            // } 
            // if(tag == "进程9"){
                    
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold9 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold9 = true;
            //         }
            //     } 
                
                
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
            //生成保存函数
            saveInputData(list){
              axios({
                url:'/getSituationInputDataTableTwo0402',
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
            // //确定地区类型
            // getMonitorCardContentInto0402(people){
            //     axios({
            //         url:'/getMonitorCardContentIntoTwo0402',
            //         method:'post',
            //         params:{
            //             people:people
            //         }
            //     }).then(res=>{
                    
            //         //console.log(res.data);
            //     }).catch(err=>{
            //         return this.$message.error("更新表4-2数据失败！");
            //     });
            // },
            //读取表4-2相应的内容
            getUserAllData0402(people,column_id,monitor_location){
                axios({
                    url:'/getUserAllDataTwo0402',
                    method:'post',
                    params:{
                        people:people,
                        column_id:column_id,
                        monitor_location:monitor_location,
                        startYear:0,
                        endYear:0
                    }
                }).then(res=>{
                    this.data = res.data;
                    const json = JSON.stringify(res.data);
                    this.copyData = JSON.parse(json);
                    //console.log(this.data);
                }).catch(err=>{
                    return this.$message.error("获取表4-2数据失败！");
                });
            },
            //获取稻作类型数据
            brandKeyChange1:function(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"稻作类型",
                        riceType:'double'
                    },
                }).then(res=>{
    
                    this.daoZuoTypeData = res.data;
  
                }).catch(err=>{
                    return this.$message.error("获取稻作类型失败！");
                });
                },
            //获取品系类型数据
            brandKeyChange2:function(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"品系类型",
                        riceType:'double'
                    },
                }).then(res=>{
                    this.pinXiTypeData = res.data;
                }).catch(err=>{
                    return this.$message.error("获取品系类型失败！");
                });
                },
            //获取z栽植方式数据
            brandKeyChange3:function(){
                axios({
                    url:'/getSelectContent',
                    method:'post',
                    params:{
                        fieldName:"栽植方式",
                        riceType:'double'
                    },
                }).then(res=>{
                    this.zaiZhiTypeData = res.data;
                }).catch(err=>{
                    return this.$message.error("获取栽植方式失败！");
                });
                },
            //填写详细信息
            fillDetailInfo(e){
                var Index = e.target.parentNode.cellIndex;
              this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
             this.fieldRowIndex = this.fieldRowIndex-2; 
            //console.log(this.fieldRowIndex);
              var getTdInnerHTML="";
              getTdInnerHTML = getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
  
              this.tableFlag = getTdInnerHTML;
              //console.log(this.tableFlag);
              this.detialDialogVisible = true;
            },
            cancelOp(){
                this.detialDialogVisible = false;
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
            // this.getMonitorCardContentInto0402(people);
            this.getUserAllData0402(people,this.column_id,this.monitor_location);
        }
    }
</script>