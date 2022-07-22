<template>
    <div class="demo-image__placeholder">
        <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;" @click="getFormList('1')">提交</el-button> -->
        <div id="containner_div" style="width: 100%;height: 70%;overflow-x : auto;max-width: 1300px;">
            <!-- <el-row style="text-align: center;font-size: large;margin-top: 1%;color: grey; ">2021年度水稻监测点汇总表</el-row> -->
            <table style="width: 100%;border: 1px solid lightgray;text-align: center;line-height: 40px;overflow-x: auto ;" border="1" cellspacing="0">
                <tr >
                    <td colspan="31" style="background-color: #6E8B3D;color: #FFFFFF;">年度水稻监测点汇总表</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td rowspan="2" style="width:100px">监测点代码</td>
                    <td colspan="5" >地址</td>
                    <td colspan="10" >监测田块</td>
                    <td colspan="3" >品种（品系）</td>
                    <td colspan="12">田间信息</td>
                </tr>
                <tr style="background-color: #CDBE70;color: green;">
                    <td style="width: 4%;">
                        市
                    </td>
                    <td style="width: 4%;">
                        县（市、区）
                    </td>
                    <td style="width: 4%;">
                        镇（乡）
                    </td>
                    <td style="width: 4%;">
                        村名
                    </td>
                    <td>
                        户主
                    </td>
                    <td>
                        种植面积（亩）
                    </td>
                    <td>
                        地区类型
                    </td>
                    <td>
                        土壤类型
                    </td>
                    <td>
                        前茬类型
                    </td>
                    <td>
                        水管方式
                    </td>
                    <td>
                        全氮(g/Kg)
                    </td>
                    <td>
                        速效磷(g/Kg)
                    </td>
                    <td>
                        速效钾(g/Kg)
                    </td>
                    <td>
                        有机质(g/Kg)
                    </td>
                    <td>
                        秸秆还田量(kg/亩)
                    </td>
                    <td>
                        名称
                    </td>
                    <td>
                        稻作类型
                    </td>
                    <td>
                        品系类型
                    </td>
                    <td>
                        栽植方式
                    </td>
                    <td>
                        播期
                    </td>
                    <td>
                        移栽秧龄（天）
                    </td>
                    <td>
                        移栽叶龄（叶）
                    </td>
                    <td>
                        基本苗（万/亩）
                    </td>
                    <td>
                        收获日期
                    </td>
                    <td>
                        亩有效穗(万)
                    </td>
                    <td>
                        穗总粒数(个)
                    </td>
                    <td>
                        结实率(％)
                    </td>
                    <td>
                        千粒重(g)
                    </td>
                    <td>
                        理论产量(kg/亩)
                    </td>
                    <td>
                        实收产量(kg/亩)
                    </td>
                </tr>
                <template v-for="(item,index) in data">
                    <tr>
                        <td style="width: 18%;"><input v-model="data[index].field_1" style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" disabled></input></td>
                        <td style="width: 8%;"><input
                            style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_2_1" disabled></input></td>
                        <td style="width: 8%;"> <input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_2_2" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_2_3" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_2_4" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_2_5" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_1" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_2" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_3" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_4" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_5" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_6" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_7" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_8" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_9" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_3_10" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_4_1" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_4_2" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_4_3" disabled></input></td>
                        <td style="width: 8%;"><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_1" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_2" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_3" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_4" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_5" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_6" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_7" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_8" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_9" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_10" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_11" disabled></input></td>
                        <td><input style="width: 100px;
                            background-color: #fff;
                            border-radius: 4px;
                            border: 1px solid #dcdfe6;
                            box-sizing: border-box;
                            color: #606266;
                            display: inline-block;
                            font-size: inherit;
                            height: 40px;
                            line-height: 40px;" v-model="data[index].field_5_12" disabled></input></td>
                    </tr>
                </template>
    
        </table>    
    </div>

        <!--字段详情-->
        <el-dialog :title="currentFieldTitile" :visible.sync="detialDialogVisible" width="90%" :before-close="handleClose">
            <table style="width: 100%;border: 1px solid white;text-align: center;line-height: 40px;" border="1" cellspacing="0">
                <template v-if="currentFieldTitile == '地址'">
                    <tr>
                        <td>市</td>
                        <td>县（市、区）</td>
                        <td>镇（乡）</td>
                        <td>村名</td>
                        <td>户主</td>
                    </tr>
                    <tr>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                    </tr>
                </template>
                <template v-if="currentFieldTitile == '监测田块'">
                    <tr>
                        <td>种植面积（亩）</td>
                        <td>地区类型</td>
                        <td>土壤类型</td>
                        <td>前茬类型</td>
                        <td>水管方式</td>
                        <td>全氮(g/Kg)</td>
                        <td>速效磷(g/Kg)</td>
                        <td>速效钾(g/Kg)</td>
                        <td>有机质(g/Kg)</td>
                        <td>秸秆还田量(kg/亩)</td>
                    </tr>
                    <tr>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                    </tr>
                </template>
                <template v-if="currentFieldTitile == '品种（品系）'">
                    <tr>
                        <td>名称</td>
                        <td>稻作类型</td>
                        <td>品系类型</td>
                    </tr>
                    <tr>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                    </tr>
                </template>
                <template v-if="currentFieldTitile == '田间信息'">
                    <tr>
                        <td>栽植方式</td>
                        <td>播期</td>
                        <td>移栽秧龄（天）</td>
                        <td>移栽叶龄（叶）</td>
                        <td>基本苗（万/亩）</td>
                        <td>收获日期</td>
                        <td>亩有效穗(万)</td>
                        <td>穗总粒数(个)</td>
                        <td>结实率(％)</td>
                        <td>千粒重(g)</td>
                        <td>理论产量(kg/亩)</td>
                        <td>实收产量(kg/亩)</td>
                    </tr>
                    <tr>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                        <td><el-input class="secondFieldDetail"></el-input></td>
                    </tr>
                </template>
            </table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="cancelOp">关 闭</el-button>
                <el-button type="primary" @click="getInputInfo">暂 存</el-button>
              </span>
        </el-dialog>
    </div>
</template>
<script>
    import axios from 'axios'
    export default{
        data(){
            return{
                detialDialogVisible:false,//对话框隐藏与否
                currentFieldTitile:'',//保存当前点击了哪一个字段下的‘填写’
                data:[],
            }
        },
        methods:{
            getFormList(flag){},
            //把表2-1的建档卡内容读取至表2-2中
            getMonitorCardContent(people){
                axios({
                    url:'/getMonitorCardContent',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    //console.log(res.data);
                }).catch(err=>{
                    return this.$message.error("获取内容失败！");
                });
            },

            //读取自己2-2表的内容
            getUserAllData0202(people){
                axios({
                    url:'/getUserAllData0202',
                    method:'post',
                    params:{
                        people:people
                    }
                }).then(res=>{
                    this.data = res.data;
                    //console.log(this.data);
                }).catch(err=>{
                    return this.$message.error("获取表2-2内容失败！");
                });
            },
            //关闭dialog
            cancelOp(){
                this.detialDialogVisible = false;
            },
            //数据暂存
            getInputInfo(){
                //暂存数据
                this.detialDialogVisible = false;
            },
            handleClose(done) {
            var inputs = document.getElementsByClassName("secondFieldDetail");
            if(inputs.length < this.secondList.length){
                this.$confirm('还未录入完成，确认退出？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});          
            }
            else if(inputs.length == this.secondList.length){
            this.$confirm('录入数据是否保存？')
                .then(_ => {
                    done();
                })
                .catch(_ => {}); 
            }else{
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {}); 
            }
        },
            //填写详细信息
            fillDetailInfo(e){
                //console.log(e);
                var Index = e.target.parentNode.cellIndex+1;
            //   this.fieldColIndex = Index;//把当前点击的字段所在列保存
            //   this.fieldRowIndex = e.target.parentNode.parentNode.rowIndex;//把当前点击字段所在行保存
              var getTdInnerHTML="";    
              getTdInnerHTML = e.target.parentNode.parentNode.parentNode.rows[1].childNodes[Index].innerHTML;
              //console.log(getTdInnerHTML);
              this.currentFieldTitile = getTdInnerHTML;
                this.detialDialogVisible = true;
            },
        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.getMonitorCardContent(people);
            this.getUserAllData0202(people);
        },
    }
</script>
<style>
.el-dialog{
   top: 15%;
   left: 4%;
 }
</style>