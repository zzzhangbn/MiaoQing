<template>
<div class="demo-image__placeholder">
  <router-link to="/tablemain">
        <el-button style="display: inline-block; margin-left: 10px"
          >返回</el-button>
      </router-link>
    <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">上报</el-button>
    <!-- <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 2.4%;margin-bottom: 1%;" @click="getFormList('0')">暂存</el-button> -->
    <div id="containner_div" style="width: 100%;height: 70%;">
    <table style="width: 100%;border: 1px solid lightgray;text-align: center;" border="1" cellspacing="0">
        <tr >
            <td colspan="7" style="background-color: #708074">年度水稻监测点建档卡</td>
        </tr>
        <tr>
            <td></td>
            <td>监测点代码：</td>
            <td colspan="2">
              <el-input v-model="data.field_1" readonly></el-input>
              <!-- <select id="monitorList" v-model="data.field_1" class="select" @change="onSelectedMonitor($event)">
                <option>--请选择--</option>
              </select> -->
            </td>
            <td>监测年份：</td>
            <td colspan="2"><el-date-picker
                v-model="data.field_2"
                value-format=“yyyy-MM-dd”
                type="year" style="width: 100%;"
                placeholder="选择年">
              </el-date-picker></td>
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
            <!-- <td></td> -->
            <td><!--市-->
                <select class="select" id="select_1">
                    <option value="noValue">--请选择--</option>
                </select>
            </td>
            <td>
                <select class="select" id="select_2">
                    <option value="noValue">--请选择--</option>
                </select>
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
                <!--<el-input v-model="data.field_9" ></el-input>-->
                <select class="select" id="select_3">
                    <option value="noValue">--请选择--</option>
                </select>
            </td>
            <td><el-input v-model="data.field_10"></el-input></td>
            <td>
                <select class="select" id="select_4">
                    <option value="noValue">--请选择--</option>
                </select>
            </td>
            <td colspan="2">
                <select class="select" id="select_5">
                    <option value="noValue">--请选择--</option>
                </select>
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
                <!-- <select class="select" id="select_6">
                    <option value="noValue">--请选择--</option>
                </select> -->
            </td>

            <td colspan="2">
                品系类型
                <!-- <select class="select" id="select_7">
                    <option value="noValue">--请选择--</option>

                </select> -->
            </td>
        </tr>
        <tr>

            <td colspan="2"><el-input v-model="data.field_18"></el-input></td>

            <td colspan="2">
                <select class="select" id="select_6">
                    <option value="noValue">--请选择--</option>
 
                </select>
            </td>
            <td colspan="2">
                <select class="select" id="select_7">
                    <option value="noValue">--请选择--</option>

                </select>
            </td>
 
        </tr>
        <tr>
            <td rowspan="4">田间信息</td>
            <td>栽植方式
                <!-- <select class="select" id="select_8">
                    <option value="noValue">--请选择--</option>
                </select> -->
            </td>
            <td>播期</td>
            <td>移栽秧龄（天）</td>
            <td>移栽叶龄（叶）</td>
            <td>基本苗（万/亩）</td>
            <td>收获日期</td>
        </tr>
        <tr>
            
            <td>
                <select class="select" id="select_8">
                    <option value="noValue">--请选择--</option>

                </select>
            </td>
            <td>
              <!-- <el-input v-model="data.field_22"></el-input> -->
              <el-date-picker
                    v-model="data.field_22"
                    type="date"
                    value-format=“yyyy-MM-dd”
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
                    value-format=“yyyy-MM-dd”
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
export default{
    data(){
        return{
            listArray:[],
            monitorList:[],//监测点代码列表
            data:{
                field_1:'自动生成',
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
          this.$confirm("是否上报？").then(_=>{
                this.getAllSelectContent();
                var people = localStorage.getItem('ms_username');
                this.data.modification_time = this.getNowTime();
                this.data.in_use = 0;
                this.saveInputData();
                if (this.data.field_22 != null){
                    this.data.field_22 = this.data.field_22.toString().replace('“','').replace('”','');
                }
                if (this.data.field_26 != null){
                    this.data.field_26 = this.data.field_26.toString().replace('“','').replace('”','');
                }if (this.data.field_2 != null){
                    this.data.field_2 = this.data.field_2.toString().replace('“','').replace('”','');
                }
          }).catch(_=>{
            return this.$message.info("您取消了操作！");
          });

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
            //console.log(res);
            this.$message.success("操作成功！");
            }).catch(err=>{
            return this.$message.error("数据保存失败！");
            })
        },

        //获取下拉框的所有选中内容，并保存到对象中
        getAllSelectContent(){
            var selected = document.getElementById("select_4");
            var  index = selected.selectedIndex;
            var  text = selected.options[index].text;
            this.data.field_11 = text;

            selected = document.getElementById("select_1");
            index = selected.selectedIndex;
            text = selected.options[index].text;
            this.data.field_4 = text;

            selected = document.getElementById("select_2");
            index = selected.selectedIndex;
            text = selected.options[index].text;
            this.data.field_5 = text;

            selected = document.getElementById("select_3");
            index = selected.selectedIndex;
            text = selected.options[index].text;
            this.data.field_9 = text;

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
                    this.listArray = res.data;
                    var select = document.getElementById("select_1");
                    for(var i = 0 ; i < this.listArray.length ; ++i){
                        select.options[i+1] = new Option(this.listArray[i].list);
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
                    this.listArray = res.data;
                    var select = document.getElementById("select_2");
                    for(var i = 0 ; i < this.listArray.length ; ++i){
                        select.options[i+1] = new Option(this.listArray[i].list);
                    }

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
                this.listArray = res.data;
                var select = document.getElementById("select_4");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                  select.options[i+1] = new Option(this.listArray[i].list);
                }

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
                this.listArray = res.data;
                var select = document.getElementById("select_5");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                  select.options[i+1] = new Option(this.listArray[i].list);
                }

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
                this.listArray = res.data;
                var select = document.getElementById("select_6");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                  select.options[i+1] = new Option(this.listArray[i].list);
                }

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
                this.listArray = res.data;
                var select = document.getElementById("select_7");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                  select.options[i+1] = new Option(this.listArray[i].list);
                }

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
                this.listArray = res.data;
                var select = document.getElementById("select_8");
                for(var i = 0 ; i < this.listArray.length ; ++i){
                  select.options[i+1] = new Option(this.listArray[i].list);
                }

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
                    this.listArray = res.data;
                    var select = document.getElementById("select_3");
                    for(var i = 0 ; i < this.listArray.length ; ++i){
                        select.options[i+1] = new Option(this.listArray[i].list);
                    }

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
        this.getSelectContent1();
        this.getSelectContent2();
        this.getSelectContent3();
        this.getSelectContent4();
        this.getSelectContent5();
        this.getSelectContent6();
        this.getSelectContent7();
        this.getSelectContent8();
        var people = localStorage.getItem('ms_username');
        this.data.people = people;
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