<template>
<div class="demo-image__placeholder">
    <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button>
    <router-link to='/traceBackData'>
          <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
    </router-link>
    <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 2.4%;margin-bottom: 1%;" @click="getFormList('0')">暂存</el-button> -->
    <div id="containner_div" style="width: 100%;height: 70%;">
    <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
        <tr >
            <td colspan="7">年度水稻监测点建档卡</td>
        </tr>
        <tr>
            <td></td>
            <td>监测点代码：</td>
            <td colspan="2">
              <el-input v-model="data.field_1" readonly></el-input>
            </td>
            <td>监测年份：</td>
            <td colspan="2">
            <el-date-picker
                v-model="data.field_2"
                type="year" style="width: 100%;"
                placeholder="选择年">
              </el-date-picker></td>
        </tr>
        <tr>
            <td rowspan="2">地址</td>
            <td>市

            </td>
            <td>县（市、区）

            </td>
            <td>镇（乡）</td>
            <td>村名</td>
            <td colspan="2"><el-input v-model="data.field_3"></el-input></td>
            
        </tr>
        <tr>
            <td><!--市-->

                <el-input v-model="data.field_4" ></el-input>
            </td>
            <td>
                <el-input v-model="data.field_5" ></el-input>
            </td>
            <td><el-input v-model="data.field_6"></el-input></td>
            <td>户主</td>
            <td colspan="2"><el-input v-model="data.field_7"></el-input></td>

        </tr>
        <tr>
            <td rowspan="4">监测田块</td>
            <td>种植面积（亩）</td>
            <td>地区类型
            </td>
            <td>土壤类型</td>
            <td>前茬类型
            </td>
            <td colspan="2">水管方式
            </td>

        </tr>
        <tr>
            <td><el-input v-model="data.field_8"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
            <td>
                <el-input v-model="data.field_9" ></el-input>
            </td>
            <td><el-input v-model="data.field_10"></el-input></td>
            <td>
            <el-select v-model="data.field_11" placeholder="请选择" @focus="getSelectContent4">
                    <el-option
                        v-for="item in qianChaTypeData"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
            </el-select>
              <!-- <select class="select" id="select_4">
                      <option value="noValue">--请选择--</option>
              </select> -->

               
            </td>
            <td colspan="2">
                <!-- <select class="select" id="select_5">
                    <option value="noValue">--请选择--</option>

                </select> -->
                <el-select v-model="data.field_12" placeholder="请选择" @focus="getSelectContent5">
                    <el-option
                        v-for="item in shuiGuanTypeData"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
            </el-select>
            </td>
        </tr>
        <tr>
            <td>全氮（g/KG）</td>
            <td>速效磷
                (g/Kg)</td>
            <td>速效钾
                (g/Kg)</td>
            <td>有机质
                (g/Kg)</td>
            <td colspan="2">秸秆还田量
                (kg/亩)</td>
        </tr>
        <tr>
         
            <td><el-input v-model="data.field_13"></el-input></td>
            <td><el-input v-model="data.field_14"></el-input></td>
            <td><el-input v-model="data.field_15"></el-input></td>
            <td><el-input v-model="data.field_16"></el-input></td>
            <td colspan="2"><el-input v-model="data.field_17" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>

        </tr>
        <tr>
            <td rowspan="2">品种、品系信息</td>
            <td colspan="2">名称</td>

            <td colspan="2">
                稻作类型
            </td>

            <td colspan="2">
                品系类型
            </td>
        </tr>
        <tr>

            <td colspan="2"><el-input v-model="data.field_18"></el-input></td>

            <td colspan="2">
                <!-- <select class="select" id="select_6">
                    <option value="noValue">--请选择--</option>
 
                </select> -->
                <el-select v-model="data.field_19" placeholder="请选择" @focus="getSelectContent6">
                    <el-option
                        v-for="item in daoZuoType"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
            </el-select>
            </td>

            <td colspan="2">
                <!-- <select class="select" id="select_7">
                    <option value="noValue">--请选择--</option>

                </select> -->
                 <el-select v-model="data.field_20" placeholder="请选择" @focus="getSelectContent7">
                    <el-option
                        v-for="item in pinXiType"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
            </el-select>
            </td>
 
        </tr>
        <tr>
            <td rowspan="4">田间信息</td>
            <td>栽植方式
            </td>
            <td>播期</td>
            <td>移栽秧龄（天）</td>
            <td>移栽叶龄（叶）</td>
            <td>基本苗（万/亩）</td>
            <td>收获日期</td>
        </tr>
        <tr>
            <td>
                <!-- <select class="select" id="select_8">
                    <option value="noValue">--请选择--</option>
                </select> -->
                <el-select v-model="data.field_21" placeholder="请选择" @focus="getSelectContent8">
                    <el-option
                        v-for="item in zaiZhiType"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
            </el-select>
            </td>
            <td>
              <el-date-picker
                    v-model="data.field_22"
                    type="date"
                    placeholder="选择日期">
                    </el-date-picker>
            </td>
            <td><el-input v-model="data.field_23" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
            <td> <el-input v-model="data.field_24"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
            <td><el-input v-model="data.field_25"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
            <td>
                <el-date-picker
                    v-model="data.field_26"
                    type="date"
                    placeholder="选择日期">
                    </el-date-picker>
            </td>
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
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_27"></el-input></td>
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_28"></el-input></td>
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_29"></el-input></td>
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_30"></el-input></td>
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_31"></el-input></td>
            <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_32"></el-input></td>
        </tr>
    </table>
    </div>
</div>
</template>
<script>
    import axios from 'axios'
import { re } from 'mathjs';
import template1 from '../pages/researchReport/template/template1.vue';
export default{
  components: { template1 },
    data(){
        return{
            listArray:[],
            monitorList:[],//监测点代码列表
            column_id:'',
            tableName:'',
            qianChaTypeData:[],
            shuiGuanTypeData:[],
            daoZuoType:[],
            pinXiType:[],
            zaiZhiType:[],
            data:{
                field_1:'测试数据_1',
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
                modification_time:null,
                people:'',
                in_use:'',
            }
        }
    },
    methods:{
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
        //存储数据
        getFormList(flag){
            // this.getAllSelectContent();
            this.$confirm('是否上报？').then(_ => {
            var people = localStorage.getItem('ms_username');
            this.data.modification_time = this.getNowTime();
            this.saveInputData();
            if(this.column_id != '' && this.tableName != ''){
                this.fixDataBackState(this.tableName,this.column_id);
                //直接跳转
                this.$router.push('/traceBackData');
            }
            }).catch(_=>{
              return this.$message.info("您取消了操作！");
            })
            // this.insertMonitorInfoToTable0101(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0102(this.data.field_5,people);
            // this.insertMonitorInfoToTable0301(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0302(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0303(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0401(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0402(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0403(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,this.data.field_25,people);
            // this.insertMonitorInfoToTable0404(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_20,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0501(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0601(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0602(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0603(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0604(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0605(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_19,this.data.field_18,this.data.field_21,people);
            // this.insertMonitorInfoToTable0701(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0702(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable0703(this.data.field_5,this.data.field_9,people);
            // this.insertMonitorInfoToTable1001(this.data.field_5,this.data.field_1,this.data.field_9,this.data.field_18,this.data.field_21,people);
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
        saveInputData(){
              axios({
                url:'/getSituationInputDataTable3',
                method:'post',
                params:{
                  data:this.data
                }
              }).then(res=>{
                this.$message.success("操作成功！");
              }).catch(err=>{
                return this.$message.error("数据保存失败！");
              })
            },

        //把监测点的信息插入到表1-1 中
        insertMonitorInfoToTable0101(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0101',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log('表1-1监测点信息更新成功！');
          }).catch(err=>{
            return this.$message.error("表1-1监测点信息更新失败！");
          });
        },
          //把监测点的信息插入到表1-2 中
          insertMonitorInfoToTable0102(area,people){
          axios({
            url:'/insertMonitorInfoToTable0102',
            method:'post',
            params:{
              area:area,
              people:people
            }
          }).then(res=>{
            //console.log('表1-2监测点信息更新成功！');
          }).catch(err=>{
            return this.$message.error("表1-2监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表3-1 中
        insertMonitorInfoToTable0301(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0301',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表3-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-1监测点信息更新失败！");
          });
        },
      //把监测点的信息插入到表3-2 中
        insertMonitorInfoToTable0302(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0302',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表3-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-2监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表3-3 中
        insertMonitorInfoToTable0303(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0303',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表3-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表3-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表7-1 中
        insertMonitorInfoToTable0701(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0701',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表7-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表7-2 中
        insertMonitorInfoToTable0702(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0702',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表7-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表7-3 中
        insertMonitorInfoToTable0703(area,areaCate,people){
          axios({
            url:'/insertMonitorInfoToTable0703',
            method:'post',
            params:{
              area:area,
              areaCate:areaCate,
              people:people
            }
          }).then(res=>{
            //console.log("表7-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表7-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-1 中
        insertMonitorInfoToTable0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
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
              zaiZhiType:zaiZhiType
            }
          }).then(res=>{
            //console.log("表4-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-2 中
        insertMonitorInfoToTable0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
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
              zaiZhiType:zaiZhiType
            }
          }).then(res=>{
            //console.log("表4-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-3 中
        insertMonitorInfoToTable0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people){
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
              jiBenMiao:jiBenMiao
            }
          }).then(res=>{
            //console.log("表4-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表4-4 中
        insertMonitorInfoToTable0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表4-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表4-4监测点信息更新失败！");
          });
        },
        //把监测点的信息插入到表5-1 中
        insertMonitorInfoToTable0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表5-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表5-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-1 中
        insertMonitorInfoToTable0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表6-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-1监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-2 中
        insertMonitorInfoToTable0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表6-2监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-2监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-3 中
        insertMonitorInfoToTable0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表6-3监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-3监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-4 中
        insertMonitorInfoToTable0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表6-4监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-4监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表6-5 中
        insertMonitorInfoToTable0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表6-5监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表6-5监测点信息更新失败！");
          });
        },

        //把监测点的信息插入到表10-1 中
        insertMonitorInfoToTable1001(area,MonitorBlock,areaCate,pinZhongType,zaiZhiType,people){
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
            }
          }).then(res=>{
            //console.log("表10-1监测点信息更新成功！");
          }).catch(err=>{
            return this.$message.error("表10-1监测点信息更新失败！");
          });
        },

        //获取下拉框的所有选中内容，并保存到对象中
        getAllSelectContent(){
             var selected = document.getElementById("select_4");
             var  index = selected.selectedIndex;
             var  text = selected.options[index].text; 
              this.data.field_11 = text;

              selected = document.getElementById("select_5");
              index = selected.selectedIndex;
              text = selected.options[index].text; 
              this.data.field_12 = text;

              selected = document.getElementById("select_6");
              index = selected.selectedIndex;
              text = selected.options[index].text; 
              this.data.field_19 = text;

              selected = document.getElementById("select_7");
              index = selected.selectedIndex;
              text = selected.options[index].text; 
              this.data.field_20 = text;

              selected = document.getElementById("select_8");
              index = selected.selectedIndex;
              text = selected.options[index].text; 
              this.data.field_21 = text;
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
            this.qianChaTypeData = res.data
            
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
            this.shuiGuanTypeData = res.data;
            
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
            riceType:'one'
          }
        }).then(
          res=>{
            this.daoZuoType = res.data;
            
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
            this.pinXiType = res.data;
            
          }
        ).catch(err=>{
          return this.$message.error("获取下拉框内容失败！");
        });
      },

    //获取 栽植方式 下拉框内容
    getSelectContent8(fieldName){ 
        axios({
          url:'/getSelectContent',
          method:'post',
          params:{
            fieldName:"栽植方式",
            riceType:'one'
          }
        }).then(
          res=>{
           this.zaiZhiType = res.data;
            
          }
        ).catch(err=>{
          return this.$message.error("获取下拉框内容失败！");
        });
      },

      //获取监测点代码数据
      getMonitorCode(people){
        axios({
          url:'/getMonitorCode',
          method:'post',
          params:{
            UserID:people
          }
        }).then(res=>{
          //console.log(res.data);
          this.monitorList = res.data;
          var monitorList = document.getElementById("monitorList");
            for(var i = 0 ; i < this.monitorList.length ; ++i){
              monitorList.options[i+1] = new Option(this.monitorList[i].code);
            }
        }).catch(err=>{
          return this.$message.error('获取监测点失败！');
        });
      },

      //选择监测点
      onSelectedMonitor(e){
        for(var i = 0 ; i < this.monitorList.length ; ++i){
          if(this.monitorList[i].code == this.data.field_1){
            this.data.field_4 = this.monitorList[i].city;
            this.data.field_5 = this.monitorList[i].area;
            this.data.field_9 = this.monitorList[i].areaCate;
          }
        }

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
       //获取该监测点详细数据
        getUserAllData0201(people,column_id){
            const _this = this;
            axios({
                url:'/getUserAllData0201',
                method:'get',
                params:{
                    people:people,
                    column_id:column_id,
                    startYear:0,
                    endYear:0
                }
            }).then(res=>{
                _this.data = res.data;
                this.data = res.data[0];
            }).catch(err=>{
                return this.$message.error("获取表2-1内容失败！");
            });
        },
    },
    created(){
        // this.getSelectContent4();
        // this.getSelectContent5();
        // this.getSelectContent6();
        // this.getSelectContent7();
        // this.getSelectContent8();
        var people = localStorage.getItem('ms_username');
        this.data.people = people;
        if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
              this.column_id = this.$route.query.column_id;
              this.tableName = this.$route.query.record_table;
              this.getUserAllData0201(people,this.column_id);
        }
        
        // this.getMonitorCode(people);
    },
}
</script>
<style scoped>
    .select{
        width: 50%;
        height: 35px;
        text-align: center;
        border-radius: 4px;
    border: 1px solid #dcdfe6;
    }
table tr{
    line-height: 40px;
}
</style>