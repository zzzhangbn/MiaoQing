<template>
  <div style="margin-left: 1%">
    <div style="width: 100%; margin-top: 25px">
      <el-steps :active="currentStep" finish-status="success">
        <el-step
          v-for="(item, index) of stepTitle"
          :key="index"
          :title="item"
          onmouseover="this.style.cursor='pointer'"
          @click.native="handleStep(index)"
        />
      </el-steps>
    </div>
    <div style="width: 89%; border: #c4c4c4 1.3px solid; margin-top: 30px">
      <div
        style="
          background-color: white;
          width: 170px;
          height: 30px;
          margin-top: -1px;
          border-width: 0 1px 1px 1px;
          border-style: solid;
          border-color: #c4c4c4;
          margin-left: 4%;
          text-align: center;
          line-height: 20px;
          font-family: YouYuan;
          color: #006666;
          font-size: 25px;
        "
      >
        <template v-if="currentStep != 0">
          进程<span
            style="
              font-family: YouYuan;
              font-size: 30px;
              font-style: oblique;
              color: #006666;
              font-weight: 10px;
              margin-right: 7px;
            "
            >{{ currentStep }}</span
          >
        </template>
        <!-- <template v-if="currentStep == 9">
                进程<span style="font-family: YouYuan;font-size: 30px;font-style:oblique;color: #006666;font-weight: 10px;margin-right: 7px;">{{currentStep}}</span>
            </template> -->
        <template v-if="currentStep == 0">
          <span
            style="
              font-family: YouYuan;
              font-size: 30px;
              font-style: oblique;
              color: #006666;
              font-weight: 10px;
              margin-right: 7px;
            "
            >移栽期</span
          >
        </template>
      </div>
      <div
        style="
          width: 170px;
          height: 1.3px;
          background-color: #c1ffc1;
          margin-top: -0px;
          margin-left: 4%;
        "
      ></div>

      <div style="margin-left: 1%; margin-top: 15px; margin-bottom: 1%">
        <i class="el-icon-s-flag" style="color: #8b2323"></i
        ><span style="color: #5c5c5c; margin-left: 10px; font-size: 17px"
          >年度监测县(市、区)中稻生育进度调查表（移栽稻）</span
        >
      </div>
      <el-card
        style="
          width: 98%;
          margin-left: 1%;
          box-shadow: 0 0 7px #ccffcc;
          margin-bottom: 10px;
        "
      >
        <!-- <template v-if="currentStep <= tab4CurrentStep"> -->
        <el-form :inline="true" ref="form" label-width="180px">
          <template v-if="tab4DataList.length > 0">
            <template v-if="currentStep == 0">
              <el-form-item label="日期">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-date-picker
                    v-model="tab4DataList[0][tab4_1]"
                    align="right"
                    type="date"
                    readonly
                  >
                  </el-date-picker>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-date-picker
                    v-model="tab4DataList[0][tab4_1]"
                    align="right"
                    type="date"
                  >
                  </el-date-picker>
                </template>
              </el-form-item>
              <el-form-item label="叶龄（叶）">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_2]"
                    readonly
                  ></el-input>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_2]"
                    oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                  ></el-input>
                </template>
              </el-form-item>
              <el-form-item label="基本苗（万亩）">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_3]"
                    readonly
                  ></el-input>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_3]" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                  ></el-input>
                </template>
              </el-form-item>
              <el-form-item label="亩穴数（万）">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_4]" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                    readonly
                  ></el-input>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_4]"
                  ></el-input>
                </template>
              </el-form-item>
            </template>
            <template
              v-if="currentStep == 2 ||currentStep == 1 ||currentStep == 3 ||currentStep == 4 ||currentStep == 5 ||currentStep == 6 ||currentStep == 7 ||currentStep == 8 ||currentStep == 9">
              <el-form-item label="日期">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-date-picker
                    v-model="tab4DataList[0][tab4_1]"
                    align="right"
                    type="date"
                    readonly
                  >
                  </el-date-picker>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-date-picker
                    v-model="tab4DataList[0][tab4_1]"
                    align="right"
                    type="date"
                  >
                  </el-date-picker>
                </template>
              </el-form-item>
              <el-form-item label="生育进程">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_2]"
                    readonly
                  ></el-input>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_2]"
                  ></el-input>
                </template>
              </el-form-item>
              <el-form-item label="叶龄（叶）">
                <template v-if="currentStep < tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_3]"
                    readonly
                  ></el-input>
                </template>
                <template v-else="currentStep >= tab4CurrentStep">
                  <el-input
                    style="width: 220px"
                    v-model="tab4DataList[0][tab4_3]" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                    replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"></el-input>
                </template>
              </el-form-item>
              <template v-if="currentStep == 8 || currentStep == 9">
                <el-form-item label="穗数(万/亩)">
                  <template v-if="currentStep < tab4CurrentStep">
                    <el-input
                      style="width: 220px"
                      v-model="tab4DataList[0][tab4_4]"
                      readonly
                    ></el-input>
                  </template>
                  <template v-else="currentStep >= tab4CurrentStep">
                    <el-input
                      style="width: 220px"
                      v-model="tab4DataList[0][tab4_4]" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                    ></el-input>
                  </template>
                </el-form-item>
              </template>
              <template
                v-if="currentStep == 2 || currentStep == 1 || currentStep == 3 || currentStep == 4 || currentStep == 5 ||currentStep == 6 ||currentStep == 7">
                <el-form-item label="茎蘖数(万/亩)">
                  <template v-if="currentStep < tab4CurrentStep">
                    <el-input
                      style="width: 220px"
                      v-model="tab4DataList[0][tab4_4]"
                      readonly
                    ></el-input>
                  </template>
                  <template v-else="currentStep >= tab4CurrentStep">
                    <el-input
                      style="width: 220px"
                      v-model="tab4DataList[0][tab4_4]" oninput="value=value.replace(/[^\d.]/g, '').replace(/\.{2,}/g, '.').replace('.', '$#$').
                      replace(/\./g, '').replace('$#$', '.').replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3').replace(/^\./g, '')"
                    ></el-input>
                  </template>
                </el-form-item>
              </template>
            </template>
          </template>
        </el-form>
        <!-- </template> -->
      </el-card>
    </div>
    <div style="margin-top: 2.3%; float: right; margin-right: 17%">
      <template v-if="currentStep >= tab4CurrentStep">
        <el-button @click="getFormList($event)">一键上报</el-button>
      </template>
      <router-link to="/tablemain">
        <el-button style="display: inline-block; margin-left: 10px"
          >返回</el-button
        >
      </router-link>
    </div>
  </div>
</template>
  <script>
import axios from "axios";
export default {
  data() {
    return {
      currentStep: 0, //当前进程数
      maxStep: 0, //最大进程数
      tab4CurrentStep: 0, //表4    4-1当前进程，最大是10，进程1之前先判断 ‘移栽期’填过没有

      tab4DataList: [], //保存表4 - 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的数据
      tab4_1: "", //表4的字段绑定
      tab4_2: "",
      tab4_3: "",
      tab4_4: "",
      // 步骤标题
      stepTitle: ["移栽期","进程1","进程2","进程3","进程4","进程5","进程6","进程7", "进程8","孕穗中期进程9"],
      point:'',
    };
  },
  methods: {
    //上报
    getFormList(e) {
      this.$confirm("是否上报？").then(_=>{
        var date = this.getNowTime();
        for (var i = 0; i < this.tab4DataList.length; ++i) {
          this.tab4DataList[i].modification_time = date;
        }
        var list = this.tab4DataList;
        var str = "";
        for (var i = 0; i < list.length; ++i) {
          str += JSON.stringify(list[i]) + "#";
        }
        //console.log(this.tab4_1+":"+this.tab4_2+":"+this.tab4_3+":"+this.tab4_4);
        if(this.tab4DataList[0][this.tab4_1]!=null && this.tab4DataList[0][this.tab4_2]!=''&& this.tab4DataList[0][this.tab4_2]!=null && 
          this.tab4DataList[0][this.tab4_3]!= ''&& this.tab4DataList[0][this.tab4_3]!= null && this.tab4DataList[0][this.tab4_4]!=''&& 
          this.tab4DataList[0][this.tab4_4]!=null){
          this.saveInputData(str);
        }else{
          this.$message.warning("您还有数据未填！");
        }
        
        }).catch(_=>{
          return this.$message.info("您取消了操作！");
        });
      
    },
    //生成保存函数
    saveInputData(list) {
      axios({
        url: "/getSituationInputDataTable0401",
        method: "post",
        params: {
          data: list,
        },
      })
        .then((res) => {
          //console.log(res);
          this.$message.success("操作成功！");
          this.getTab4CurrentStep(this.people, this.point);
        })
        .catch((err) => {
          return this.$message.error("数据保存失败！");
        });
    },
    //限制输入数据
    checkFieldInput(e){
      
    },
    // 获取当前时间
    getNowTime: function () {
      let dateTime;
      let yy = new Date().getFullYear();
      let mm = new Date().getMonth() + 1;
      let dd = new Date().getDate();
      let hh = new Date().getHours();
      let mf =
        new Date().getMinutes() < 10
          ? "0" + new Date().getMinutes()
          : new Date().getMinutes();
      let ss =
        new Date().getSeconds() < 10
          ? "0" + new Date().getSeconds()
          : new Date().getSeconds();
      if (hh < 10) {
        dateTime =
          yy + "-" + mm + "-" + dd + " " + "0" + hh + ":" + mf + ":" + ss;
      } else {
        dateTime = yy + "-" + mm + "-" + dd + " " + hh + ":" + mf + ":" + ss;
      }
      return dateTime;
    },
    handleStep(index) {
      //如果index > currentStep ，不允许跳转
      if (index > this.maxStep) {
        this.currentStep = this.maxStep;
        return this.$message.info("该进程尚未开启！");
      }
      if (index <= this.maxStep) {
        this.currentStep = index;
        //修改各表对应的进程下的字段绑定
        //4-1
        if (this.currentStep <= this.tab4CurrentStep) {
          // var gap = 8 + this.tab4CurrentStep;
          var gap4 = 8 + index; //表4-1 的进程绑定 OK
          this.tab4_1 = "field_" + gap4 + "_1";
          this.tab4_2 = "field_" + gap4 + "_2";
          this.tab4_3 = "field_" + gap4 + "_3";
          this.tab4_4 = "field_" + gap4 + "_4";
        }
      }
    },

    //获取表4 - 年度监测县(市、区)中稻生育进度调查表（移栽稻） 的当前进程
    getTab4CurrentStep(people, point) {
      axios({
        url: "/getUserAllData0401",
        method: "post",
        params: {
          people: people,
          column_id: "",
          monitor_location: point,
          startYear:0,
          endYear:0
        },
      })
        .then((res) => {
          this.tab4DataList = [];
          this.tab4CurrentStep = 0;
          for (var i = 0; i < res.data.length; ++i) {
            this.tab4DataList.push(res.data[i]);
          }
          this.tab4DataList = res.data;
          if (this.tab4DataList.length > 0) {
            for (var j = 0; j < 10; ++j) {
              var start = 8 + j;
              var str = "field_" + start + "_1";
              if (
                this.tab4DataList[0][str] != null &&
                this.tab4DataList[0][str] != ""
              ) {
                this.tab4CurrentStep += 1;
              } else {
                break;
              }
            }
            var gap = 8 + this.tab4CurrentStep;
            this.tab4_1 = "field_" + gap + "_1";
            this.tab4_2 = "field_" + gap + "_2";
            this.tab4_3 = "field_" + gap + "_3";
            this.tab4_4 = "field_" + gap + "_4";
            if (this.tab4CurrentStep > this.currentStep) {
                this.currentStep = this.tab4CurrentStep;
            }
            if (this.tab4CurrentStep == 10) {
                this.currentStep = this.tab4CurrentStep - 1;
                gap = gap - 1;
                this.tab4_1 = "field_" + gap + "_1";
                this.tab4_2 = "field_" + gap + "_2";
                this.tab4_3 = "field_" + gap + "_3";
                this.tab4_4 = "field_" + gap + "_4";
            }
            this.maxStep = this.currentStep;
            //console.log(this.tab4CurrentStep);
          }

        })
        .catch((err) => {
          return this.$message.error("获取中稻进度失败！");
        });
    },
  },
  created() {
    this.people = localStorage.getItem("ms_username");
    this.point = this.$route.params.point;
    this.getTab4CurrentStep(this.people, this.point);
  },
};
</script>
  <style>
</style>