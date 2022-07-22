<template>
    <div class="demo-image__placeholder">
        <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button>
        <router-link to='/traceBackData'>
          <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 1.4%;margin-bottom: 1%;">返回</el-button>
        </router-link>
        <div id="containner_div" style="width: 100%;height: 70%;">
        <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
            <tr>
                <td colspan="7">年度水稻监测点建档卡</td>
            </tr>
            <tr>
                <td></td>
                <td>监测点代码：</td>
                <td colspan="2">
                  <el-input v-model="data.field_1" readonly></el-input>
                </td>
                <td>监测年份：</td>
                <td colspan="2"><el-date-picker
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
                <td colspan="2"><el-input v-model="data.field_3_4"></el-input></td>
                
            </tr>
            <tr>
                <td><!--市-->
                    <el-input v-model="data.field_3_1" ></el-input>
                </td>
                <td>
                    <el-input v-model="data.field_3_2" ></el-input>
                </td>
                <td><el-input v-model="data.field_3_3"></el-input></td>
                <td>户主</td>
                <td colspan="2"><el-input v-model="data.field_3_5"></el-input></td>
    
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
                <td><el-input v-model="data.field_4_1"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                <td>
                    <el-input v-model="data.field_4_2" ></el-input>
                </td>
                <td><el-input v-model="data.field_4_3"></el-input></td>
                <td>
                  <!-- v-model="data.field_4_4" -->
                    <!-- <select class="select" id="select_4">
                        <option value="noValue">--请选择--</option>
                    </select> -->
                    <el-select v-model="data.field_4_4" placeholder="请选择" @focus="getSelectContent4">
                    <el-option
                        v-for="item in qianChaTypeData"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
                  </el-select>
                </td>
                <td colspan="2">
                  <!-- <select class="select" id="select_5" v-model="data.field_4_5">
                    <option value="noValue">--请选择--</option>

                </select> -->
                  <el-select v-model="data.field_4_5" placeholder="请选择" @focus="getSelectContent5">
                    <el-option
                        v-for="item in shuiGuanTypeData"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
                  </el-select>
                    <!-- <select class="select" id="select_5">
                        <option value="noValue">--请选择--</option>
                    </select> -->
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
             
                <td><el-input v-model="data.field_4_6"></el-input></td>
                <td><el-input v-model="data.field_4_7"></el-input></td>
                <td><el-input v-model="data.field_4_8"></el-input></td>
                <td><el-input v-model="data.field_4_9"></el-input></td>
                <td colspan="2"><el-input v-model="data.field_4_10" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
    
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
    
                <td colspan="2"><el-input v-model="data.field_5_1"></el-input></td>
    
                <td colspan="2">
                  <!-- v-model="data.field_5_2" -->
                    <!-- <select class="select" id="select_6" >
                        <option value="noValue">--请选择--</option>
     
                    </select> -->
                    <el-select v-model="data.field_5_2" placeholder="请选择" @focus="getSelectContent6">
                    <el-option
                        v-for="item in daoZuoType"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
                  </el-select>
                </td>
    
                <td colspan="2">
                  <!-- v-model="data.field_5_3" -->
                    <!-- <select class="select" id="select_7" >
                        <option value="noValue">--请选择--</option>
    
                    </select> -->
                    <el-select v-model="data.field_5_3" placeholder="请选择" @focus="getSelectContent7">
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
                  <!-- v-model="data.field_6_1" -->
                    <!-- <select class="select" id="select_8" >
                        <option value="noValue">--请选择--</option>
    
                    </select> -->
                    <el-select v-model="data.field_6_1" placeholder="请选择" @focus="getSelectContent8">
                    <el-option
                        v-for="item in zaiZhiType"
                        :key="item.id"
                        :label="item.list"
                        :value="item.list">
                    </el-option>
                  </el-select>
                </td>
                <td>
                  <!-- <el-input v-model="data.field_22"></el-input> -->
                  <el-date-picker
                        v-model="data.field_6_2"
                        type="date"
                        placeholder="选择日期">
                        </el-date-picker>
                </td>
                <td><el-input v-model="data.field_6_3" @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                <td> <el-input v-model="data.field_6_4"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                <td><el-input v-model="data.field_6_5"  @blur.prevent="checkFirstFieldInput($event)"></el-input></td>
                <td>
                    <el-date-picker
                        v-model="data.field_6_6"
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
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_7"></el-input></td>
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_8"></el-input></td>
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_9"></el-input></td>
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_10"></el-input></td>
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_11"></el-input></td>
                <td><el-input @blur.prevent="checkFirstFieldInput($event)" v-model="data.field_6_12"></el-input></td>
            </tr>
        </table>
        </div>
    </div>
    </template>
    <script>
        import axios from 'axios'
    export default{
        data(){
            return{
                listArray:[],
                tableName:'',
                monitorList:[],//监测点代码列表
                qianChaTypeData:[],
                shuiGuanTypeData:[],
                daoZuoType:[],
                pinXiType:[],
                zaiZhiType:[],
                column_id:[],
                data:{
                    field_1:'测试数据_1',
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
                this.$confirm('是否上报？').then(_ => {
                var people = localStorage.getItem('ms_username');
                this.data.modification_time = this.getNowTime();
                this.data.in_use = flag;
                this.saveInputData();
                if(this.column_id != '' && this.tableName != ''){
                this.fixDataBackState(this.tableName,this.column_id);
                //直接跳转
                this.$router.push('/traceBackData');
              }}).catch(_=>{
                return this.$message.info("您取消了操作！");
              })
                // this.insertMonitorInfoToTableTwo0101(this.data.field_3_2,this.data.field_4_2,people);
                // this.insertMonitorInfoToTableTwo0102(this.data.field_3_2,people);
                // this.insertMonitorInfoToTableTwo0301(this.data.field_3_2,this.data.field_4_2,this.data.field_5_2,people);
                // this.insertMonitorInfoToTableTwo0302(this.data.field_3_2,this.data.field_4_2,this.data.field_5_2,people);
                // this.insertMonitorInfoToTableTwo0303(this.data.field_3_2,this.data.field_4_2,this.data.field_5_2,people);
                // this.insertMonitorInfoToTableTwo0304(this.data.field_3_2,this.data.field_4_2,this.data.field_5_2,people);
                // this.insertMonitorInfoToTableTwo0305(this.data.field_3_2,this.data.field_4_2,people);
                // this.insertMonitorInfoToTableTwo0306(this.data.field_3_2,this.data.field_4_2,this.data.field_5_2,people);
                // this.insertMonitorInfoToTableTwo0401(this.data.field_3_2,this.data.field_1,this.data.field_4_2,this.data.field_5_2,this.data.field_5_3,this.data.field_18,this.data.field_6_1,people);
                // this.insertMonitorInfoToTableTwo0402(this.data.field_3_2,this.data.field_1,this.data.field_4_2,this.data.field_5_2,this.data.field_5_3,this.data.field_18,this.data.field_6_1,people);
                // this.insertMonitorInfoToTableTwo0403(this.data.field_3_2,this.data.field_1,this.data.field_4_2,this.data.field_5_2,this.data.field_5_3,this.data.field_18,this.data.field_6_1,this.data.field_4_1,people);
                // this.insertMonitorInfoToTableTwo0404(this.data.field_3_2,this.data.field_1,this.data.field_4_2,this.data.field_5_2,this.data.field_5_3,this.data.field_18,this.data.field_6_1,people);

                //console.log(this.data);
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
                    url:'/getSituationInputDataTableTwo3',
                    method:'post',
                    params:{
                      data:this.data
                    }
                  }).then(res=>{
                    //console.log(res);
                    this.$message.success("操作成功！");
                  }).catch(err=>{
                    return this.$message.error("数据保存失败！");
                  })
                },
    
            //把监测点的信息插入到表1-1 中
            insertMonitorInfoToTableTwo0101(area,areaCate,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0101',
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
              insertMonitorInfoToTableTwo0102(area,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0102',
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
            insertMonitorInfoToTableTwo0301(area,areaCate,daoZuoType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0301',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:daoZuoType,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-1监测点信息更新失败！");
              });
            },
          //把监测点的信息插入到表3-2 中
            insertMonitorInfoToTableTwo0302(area,areaCate,daoZuoType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0302',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:daoZuoType,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-2监测点信息更新失败！");
              });
            },
            //把监测点的信息插入到表3-3 中
            insertMonitorInfoToTableTwo0303(area,areaCate,daoZuoType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0303',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:daoZuoType,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-3监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-4 中
            insertMonitorInfoToTableTwo0304(area,areaCate,daoZuoType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0304',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:daoZuoType,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-4监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-5 中
            insertMonitorInfoToTableTwo0305(area,areaCate,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0305',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-5监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表3-6 中
            insertMonitorInfoToTableTwo0306(area,areaCate,daoZuoType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0306',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  daoZuoType:daoZuoType,
                  people:people
                }
              }).then(res=>{
                //console.log("表3-6监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表3-6监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-1 中
            insertMonitorInfoToTableTwo0401(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0401',
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
            insertMonitorInfoToTableTwo0402(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0402',
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
            insertMonitorInfoToTableTwo0403(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,jiBenMiao,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0403',
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
                //console.log("表4-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表4-3监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表4-4 中
            insertMonitorInfoToTableTwo0404(area,MonitorBlock,areaCate,daoZuoType,pinXiType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0404',
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
            insertMonitorInfoToTableTwo0501(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0501',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表5-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表5-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-1 中
          insertMonitorInfoToTableTwo0601(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0601',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表6-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-1监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-2 中
          insertMonitorInfoToTableTwo0602(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0602',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表6-2监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-2监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-3 中
          insertMonitorInfoToTableTwo0603(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0603',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表6-3监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-3监测点信息更新失败！");
              });
            },

          //把监测点的信息插入到表6-4 中
          insertMonitorInfoToTableTwo0604(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0604',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表6-4监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-4监测点信息更新失败！");
              });
            },
            
          //把监测点的信息插入到表6-5 中
          insertMonitorInfoToTableTwo0605(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0605',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
                  zaiZhiType:zaiZhiType,
                }
              }).then(res=>{
                //console.log("表6-5监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表6-5监测点信息更新失败！");
              });
            },
    
            //把监测点的信息插入到表7-1 中
            insertMonitorInfoToTableTwo0701(area,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0701',
                method:'post',
                params:{
                  area:area,
                  people:people
                }
              }).then(res=>{
                //console.log("表7-1监测点信息更新成功！");
              }).catch(err=>{
                return this.$message.error("表7-1监测点信息更新失败！");
              });
            },

            //把监测点的信息插入到表7-2 中
            insertMonitorInfoToTableTwo0702(area,areaCate,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0702',
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
            insertMonitorInfoToTableTwo0703(area,areaCate,people){
              axios({
                url:'/insertMonitorInfoToTableTwo0703',
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

          //把监测点的信息插入到表10-1 中
          insertMonitorInfoToTableTwo1001(area,MonitorBlock,areaCate,daoZuoType,pinZhongType,zaiZhiType,people){
              axios({
                url:'/insertMonitorInfoToTableTwo1001',
                method:'post',
                params:{
                  area:area,
                  areaCate:areaCate,
                  people:people,
                  MonitorBlock:MonitorBlock,
                  daoZuoType:daoZuoType,
                  pinZhongType:pinZhongType,
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
                  this.data.field_4_4 = text;
    
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
                this.qianChaTypeData = res.data;
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
                this.shuiGuanTypeData = res.data;}
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
          getMonitorCodeTwo(people){
            axios({
              url:'/getMonitorCodeTwo',
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
                this.data.field_3_1 = this.monitorList[i].city;
                this.data.field_3_2 = this.monitorList[i].area;
                this.data.field_4_2 = this.monitorList[i].areaCate;
              }
            }
    
          },
         //获取该监测点详细数据
        getUserAllData0201(people,column_id){
            const _this = this;
            axios({
                url:'/getUserAllDataTwo0201',
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
        },
        created(){
            var people = localStorage.getItem('ms_username');
            this.data.people = people;
            if(this.$route.query.column_id != null && this.$route.query.column_id != undefined && this.$route.query.column_id != ''){
              this.column_id = this.$route.query.column_id;
              this.tableName = this.$route.query.record_table;
              this.getUserAllData0201(people,this.column_id);
            }
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