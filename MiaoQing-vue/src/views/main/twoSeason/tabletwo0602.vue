<template>
  <div class="demo-image__placeholder">
    <!-- <Child v-show="false"  @tableCode="tableCode"></Child>  -->
    <el-button style="display: inline-block; float: right;margin-top: 1%;margin-right: 5.4%;margin-bottom: 1%;" @click="getFormList('1')">提交</el-button>
    <!-- <span style="font-size: 20px;color:gray;margin-left: 41%;">{{tableId}}</span> -->
    <div id="containner_div" style="width: 100%;height: 70%;">

      <table id="sum_tab" style="width: 100%;overflow-x: auto;border: 1px solid lightgray;text-align: center;line-height: 40px;" border="1" cellspacing="0">
        <tr >
          <td colspan="9">2021年度水稻监测点化除管理记录表</td>
        </tr>
        <tr >
          <td style="width: 10%;">县（市、区）</td>
          <td style="width: 10%;">监测点代码</td>
          <td style="width: 10%;">地区类型</td>
          <td style="width: 10%;">稻作类型</td>
          <td style="width: 10%;">品种名称</td>
          <td style="width: 10%;">栽植方式</td>
          <td style="width: 13%;">化除1-播栽前</td>
          <td style="width: 13%;">化除2-播栽后或出苗后</td>
          <td style="width: 13%;">化除3-播栽后或出苗后</td>
        </tr>
        <template v-for="(item,index) in data">
          <tr>
            <td><el-input v-model="data[index].field_1" disabled></el-input></td>
            <td><el-input v-model="data[index].field_2" disabled></el-input></td>
            <td><el-input v-model="data[index].field_3" disabled></el-input></td>
            <td><el-input v-model="data[index].field_4" disabled></el-input></td>
            <td><el-input v-model="data[index].field_5" disabled></el-input></td>
            <td><el-input v-model="data[index].field_6" disabled></el-input></td>
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
    <!--<el-button @click="getNewRecord" style="margin-top: 10px;margin-left: 9px;">新增</el-button>-->

    <!--字段详情-->
    <el-dialog :title="tableFlag" :visible.sync="detialDialogVisible" width="50%" :before-close="handleClose">
      <table style="width: 90%;border: 1px solid lightgray;line-height: 40px;" border="1" cellspacing="0">
        <template v-if="tableFlag == '化除1-播栽前'">
          <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
            <td style="width: 10%">日期</td>
            <td style="width: 10%">除草剂名称</td>
            <td style="width: 10%">亩用量/kg</td>
            <td style="width: 10%">方法</td>
            <td style="width: 10%">生育进程</td>
          </tr>
          <tr>
            <td><el-date-picker
                    value-format=“yyyy-MM-dd”
                    v-model="data[fieldRowIndex].field_7_1"
                    type="date"
                    placeholder="选择日期" style="width: 100%">
            </el-date-picker></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_7_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
          </tr>
        </template>
        <template v-if="tableFlag == '化除2-播栽后或出苗后'">
          <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
            <td style="width: 10%">日期</td>
            <td style="width: 10%">除草剂名称</td>
            <td style="width: 10%">亩用量/kg</td>
            <td style="width: 10%">方法</td>
            <td style="width: 10%">生育进程</td>
          </tr>
          <tr>
            <td><el-date-picker
                    value-format=“yyyy-MM-dd”
                    v-model="data[fieldRowIndex].field_8_1"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_8_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
          </tr>
        </template>
        <template v-if="tableFlag == '化除3-播栽后或出苗后'">
          <tr style="text-align: center;font-size: 20px;line-height: 40px;color: gray;background-color: #DDDDDD;">
            <td style="width: 10%">日期</td>
            <td style="width: 10%">除草剂名称</td>
            <td style="width: 10%">亩用量/kg</td>
            <td style="width: 10%">方法</td>
            <td style="width: 10%">生育进程</td>
          </tr>
          <tr>
            <td><el-date-picker
                    value-format=“yyyy-MM-dd”
                    v-model="data[fieldRowIndex].field_9_1"
                    type="date"
                    placeholder="选择日期">
            </el-date-picker></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_2" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_3" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_4" @blur.prevent="checkFirstFieldInput($event)" /></td>
            <td><input style="background-color: #fff;border-radius: 4px;border: 1px solid #dcdfe6;width: 100%;box-sizing: border-box;color: #606266;outline: 0;padding: 0 15px;font-size: inherit;height: 40px;line-height: 40px;" class="secondFieldDetail" v-model="data[fieldRowIndex].field_9_5" @blur.prevent="checkFirstFieldInput($event)" /></td>
          </tr>
        </template>
      </table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancelOp">关 闭</el-button>
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
        tableId:'',
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
        data:
                {
                  field_1:'',
                  field_2:'',
                  field_3:'',
                  field_4:'',
                  field_5:'',
                  field_6:'',
                  field_7_1:'',
                  field_7_2:'',
                  field_7_3:'',
                  field_7_4:'',
                  field_7_5:'',
                  field_8_1:'',
                  field_8_2:'',
                  field_8_3:'',
                  field_8_4:'',
                  field_8_5:'',
                  field_9_1:'',
                  field_9_2:'',
                  field_9_3:'',
                  field_9_4:'',
                  field_9_5:'',
                  people:'202119966486260',//用户id
                  modification_time:'',//修改时间
                  in_use:'',//此数据状态
                }  ,
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
      //this.getTableData();
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

      //初始化数据
      initData() {
        //this.getHeaderList();
        //this.getTableData();
      },

      //获取表数据
      getTableData(people){
        const _this = this;
        axios({
          url: '/getAllDataTwo0601',
          method:'get',
          params:{
            people:people
          }
        }).then(res=>{
          //console.log(res);
          _this.data=res.data;
          //console.log(_this.autoList);
        }).catch(err=>{
          return this.$message.error("查询数据失败！");
        });
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
        var date = this.getNowTime();
        //this.data.modification_time = date;
        for(var i = 0 ;i < this.data.length ; ++i){
          this.data[i].modification_time = date;
          if (this.data[i].field_7_1 != null){
            this.data[i].field_7_1 = this.data[i].field_7_1.replace('“','').replace('”','')
          }
          if (this.data[i].field_8_1 != null){
            this.data[i].field_8_1 = this.data[i].field_8_1.replace('“','').replace('”','')
          }
          if (this.data[i].field_9_1 != null){
            this.data[i].field_9_1 = this.data[i].field_9_1.replace('“','').replace('”','')
          }
        }
        this.data.in_use = flag;


        var list = [];
        for(var i = 0 ; i < this.data.length ; ++i){
          list.push(this.data[i]);
        }


        var str = JSON.stringify(this.data[0]);
        //console.log(str)
        var str = '';
        for(var i = 0 ; i < list.length ; ++i){
          str += JSON.stringify(list[i]) + "#";
        }

        //console.log(str)
        //console.log(typeof str)

        this.data = list;
        this.saveInputData(str);

      },

      //生成保存函数
      saveInputData(str){
        axios({
          url:'/getInputDataTableTwo0602',
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
        //console.log(this.headerList);
        //console.log(this.secondHeaderList);
        //console.log(this.secondList);
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

      //判断当前用户是否在数据库中已经有填过数据
      checkUserFirstLogin(userID){
        axios({
          url:'/judgeUserFirstLoginTable1',
          method:'post',
          params:{
            UserID:userID
          }
        }).then(
                res=>{
                  var list = res.data;
                  //console.log(list);

                  if(list.length > 0){
                    //此人已经填写过
                    var selected1=list[0].field_1;
                    //console.log(selected1);
                    var select_1 = document.getElementById("select_1").options;
                    for(var i = 0 ; i < select_1.length ; ++i){
                      if(select_1[i].innerHTML == selected1){
                        select_1[i].selected = true;
                      }
                    }
                    document.getElementById("select_1").disabled = "true";

                    var selected2 = list[0].field_3;
                    //console.log(selected2);
                    var select_2 = document.getElementById("select_2").options;
                    for(var i = 0 ; i <select_2.length ; ++i){
                      if(select_2[i].innerHTML == selected2){
                        select_2[i].selected = true;
                      }
                    }
                    document.getElementById("select_2").disabled = "true";
                  }
                  if(list.length  == 0){
                    //此人尚未填写过

                  }
                  //console.log(list);

                }
        ).catch(err=>{
          return this.$message.error("用户登录次数判断失误！");
        });
      }

    },

    created(){

      //登录监测--判断当前用户是否在数据库中已经有填过数据，有填过的话，从数据库中读入县（市、区）、地区类型

      var people = localStorage.getItem('ms_username');
      //console.log(people)
      this.getTableData(people);

    },


    watch:{ //可以监听某个值的变化，如果变化则执行相应的函数

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