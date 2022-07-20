<template>
    <div class="demo-image__placeholder">
        <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
        <template v-if="column_id == ''|| column_id == null">
            <router-link :to="{name:'/tablemain',params: {activeNum: 1}}">
                <el-button style="display: inline-block; margin-left: 10px;float:right ;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <template v-if="column_id != ''">
            <router-link to='/traceBackData'>
                <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
            </router-link>
        </template>
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
        <!-- <span style="font-size: 20px;color:gray;margin-left: 41%;">{{tableId}}</span> -->
        <div class ="table-s" id="containner_div" style="width: 100%;height: 70%;">

            <table  id="sum_tab"  style="width: 100%;overflow-x: auto;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <tr >
                    <td colspan="14">水稻监测点水分管理记录表</td>
                </tr>
                <tr >
                    <td style="width: 10%;" >县（市、区）</td>
                    <td style="width: 10%;">监测点代码</td>
                    <td style="width: 10%;">地区类型</td>
                    <td style="width: 10%;">稻作类型</td>
                    <td style="width: 10%;">品种名称</td>
                    <td style="width: 10%;">栽植方式</td>
                    <td style="width: 5%;">时期1</td>
                    <td style="width: 5%;">时期2</td>
                    <td style="width: 5%;">时期3</td>
                    <td style="width: 5%;">时期4</td>
                    <td style="width: 5%;">时期5</td>
                    <td style="width: 5%;">时期6</td>
                    <td style="width: 5%;">时期7</td>
                    <td style="width: 5%;">时期8</td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td><el-input v-model="item.field_1" disabled></el-input></td>
                        <td><el-input v-model="item.field_2" disabled></el-input></td>
                        <td><el-input v-model="item.field_3" disabled></el-input></td>
                        <td><el-input v-model="item.field_4" disabled></el-input></td>
                        <td><el-input v-model="item.field_5" disabled></el-input></td>
                        <td><el-input v-model="item.field_6" disabled></el-input></td>
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
                        <td>
                            <span onmouseover="this.style.color='green';this.style.cursor='pointer'" onmouseout="this.style.color='black';" v-on:click="fillDetailInfo($event)" >填写</span>
                        </td>
                    </tr>
                </template>
            </table>

        </div>
        

        <!--字段详情-->
        <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="40%" :before-close="handleClose">
            <table style="width: 90%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
                <template v-if="tableFlag == '时期1'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td style="width: 50%;">日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td style="width: 50%;"><el-date-picker v-model="data[fieldRowIndex].field_8_1" type="date" placeholder="选择日期" ></el-date-picker></td>-->
                        <td><el-date-picker
                                value-format=“yyyy-MM-dd”
                                v-model="data[fieldRowIndex].field_8_1"
                                type="date"
                                placeholder="选择日期">
                        </el-date-picker></td>
                        <td style="width: 50%;"><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期2'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_1" /></td>-->
                        <td style="width: 50%;"><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_8_1" type="date" placeholder="选择日期" ></el-date-picker></td>
                        <td style="width: 50%;"><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期3'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_9_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期4'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_10_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_10_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_10_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期5'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_11_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_11_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_11_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期6'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_12_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_12_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_12_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期7'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_13_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_13_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_13_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
                <template v-if="tableFlag == '时期8'">
                    <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
                        <td >日期</td>
                        <td >深度（cm）</td>
                    </tr>
                    <tr>
                        <!--<td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_14_1" /></td>-->
                        <td><el-date-picker value-format=“yyyy-MM-dd” v-model="data[fieldRowIndex].field_14_1" type="date" placeholder="选择日期" style="width: 100%"></el-date-picker></td>
                        <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_14_2" @blur.prevent="checkFirstFieldInput($event)"/></td>
                    </tr>
                </template>
            </table>
            <span slot="footer" class="dialog-footer">
            <el-button @click="cancelOp">关 闭</el-button>
            <!--<el-button type="primary" @click="getInputInfo">暂 存</el-button>-->
                <!-- <el-button @click="clearAllInputs(tableFlag)">清空</el-button> -->
          </span>
        </el-dialog>
    </div>
</template>

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
                fold1:false,
                fold2:false,
                fold3:false,
                fold4:false,
                fold5:false,
                fold6:false,
                fold7:false,
                fold8:false,
            }
        },

        mounted() {
            // this.initData();
            //this.getTableData();
        },

        methods:{
            // foldOrNot(e,tag){
            //     if(tag == "时期1"){
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold1 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold1 = true;
            //         }
            //     }
            //     if(tag == "时期2"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold2 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold2 = true;
            //         }
            //     }
            //     if(tag == "时期3"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold3 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold3 = true;
            //         }
            //     }
            //     if(tag == "时期4"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold4 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold4 = true;
            //         }
            //     }
            //     if(tag == "时期5"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold5 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold5 = true;
            //         }
            //     }
            //     if(tag == "时期6"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold6 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold6 = true;
            //         }
            //     }
            //     if(tag == "时期7"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold7 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold7 = true;
            //         }
            //     }
            //     if(tag == "时期8"){
            //
            //         if(e.target.className == "el-icon-caret-bottom"){
            //             e.target.className = "el-icon-caret-top";
            //             this.fold8 = false;
            //         }else{
            //             e.target.className = "el-icon-caret-bottom";
            //             this.fold8 = true;
            //         }
            //     }
            // },

            //初始化数据
            initData() {
                //this.getHeaderList();
                this.getTableData();
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
            //获取表数据
            getTableData(people,column_id,point){
                const _this = this;
                axios({
                    url: '/getAllData0601',
                    method:'get',
                    params:{
                        people:people,
                        column_id:column_id,
                        point:point,
                        beginYear:0,
                        endYear:0
                    }
                }).then(res=>{
                    //console.log(res);
                    _this.data=res.data;
                    //console.log(_this.autoList);
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

            //取消按钮
            cancelOp(){
                this.detialDialogVisible = false;
            },


            //保存
            getFormList(flag){
                this.$confirm("是否上报？").then(_=>{
                var date = this.getNowTime();
                            //this.data.modification_time = date;
                            for(var i = 0 ;i < this.data.length ; ++i){
                                this.data[i].modification_time = date;
                                if (this.data[i].field_7_1 != null){
                                    this.data[i].field_7_1 =this.data[i].field_7_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_8_1 != null){
                                    this.data[i].field_8_1 =this.data[i].field_8_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_9_1 != null){
                                    this.data[i].field_9_1 =this.data[i].field_9_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_10_1 != null){
                                    this.data[i].field_10_1 =this.data[i].field_10_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_11_1 != null){
                                    this.data[i].field_11_1 =this.data[i].field_11_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_12_1 != null){
                                    this.data[i].field_12_1 =this.data[i].field_12_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_13_1 != null){
                                    this.data[i].field_13_1 =this.data[i].field_13_1.toString().replace('“','').replace('”','');
                                }
                                if (this.data[i].field_14_1 != null){
                                    this.data[i].field_14_1 =this.data[i].field_14_1.toString().replace('“','').replace('”','');
                                }
                                // if (this.data[i].field_7_1 != null){
                                //     this.data[i].field_7_1 = this.data[i].field_7_1.replace('“','').replace('”','')
                                // }
                                // if (this.data[i].field_8_1 != null){
                                //     this.data[i].field_8_1 = this.data[i].field_8_1.replace('“','').replace('”','')
                                // }
                                // if (this.data[i].field_9_1 != null){
                                //     this.data[i].field_9_1 = this.data[i].field_9_1.replace('“','').replace('”','')
                                // }
                                this.data[i].in_use = flag;
                            }
                            var list = [];
                            for(var i = 0 ; i < this.data.length ; ++i){
                                list.push(this.data[i]);
                            }
                            var str = JSON.stringify(list[0]);
                            var str = '';
                            for(var i = 0 ; i < list.length ; ++i){
                                str += JSON.stringify(list[i]) + "#";
                            }
                            this.data = list;
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
                    url:'/getInputDataTable0601',
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
                // this.secondList = [];
                // for(var i = 0 ; i < this.headerList.length ; ++i){
                //     if(this.headerList[i].column_name == getTdInnerHTML){
                //         this.secondList = this.headerList[i].children;
                //     }
                // }
                // //console.log(this.headerList);
                // //console.log(this.secondHeaderList);
                // //console.log(this.secondList);
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
                dateTime = yy + '-' + mm + '-' + dd + ' ' + hh + ':' + mf + ':' + ss
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
            var point = this.$route.query.point;
            this.point = point;
            if(this.point == null || this.point == '' || this.point == undefined){
                this.point = '';
            }
            this.getTableData(people,this.column_id,this.point);
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