<template>
  <div style="margin-left: 1%;">
    <!-- <div style="width: 100%;height: 50px;background-color: cadetblue;">
      <el-button style="margin-top: 0.3%;margin-left: 1%;">一键保存</el-button>
    </div> -->

    <div>
      <!-- <div style="width: 100%;height: 80px;background-color: chocolate;line-height: 36px;">
        <div style="width: 19.2%;height: 100%;border-radius:5px 10px 0px 0px;background-color: cornsilk;border: darkblue 1px solid;display: inline-block;">年度监测县(市、区)水稻播栽进度调查表</div>
        <div style="width: 20%;height: 100%;border-radius:5px 10px 0px 0px;background-color: cornsilk;border: darkblue 1px solid;display: inline-block;">年度监测县(市、区)水稻苗情评价动态列表(移栽)</div>
        <div style="width: 20%;height: 100%;border-radius: 5px 10px 0px 0px;background-color: cornsilk;border: darkblue 1px solid;display: inline-block;">年度监测县(市、区)水稻苗情评价动态列表(直播)</div>
        <div style="width: 20%;height: 100%;border-radius: 5px 10px 0px 0px;background-color: cornsilk;border: darkblue 1px solid;display: inline-block;">年度监测县(市、区)中稻生育进度调查表（移栽稻）</div>
        <div style="width: 20%;height: 100%;border-radius: 5px 10px 0px 0px;background-color: cornsilk;border: darkblue 1px solid;display: inline-block;">年度监测县(市、区)中稻生育进度调查表（直播稻）</div>
      </div> -->
      <div style="width: 100%;height: 90px;margin-top: 25px;">
          <el-steps :space="200" :active="currentStep" finish-status="success">
            <el-step title="进程1"></el-step>
            <el-step title="进程2"></el-step>
            <el-step title="进程3"></el-step>
            <el-step title="进程4"></el-step>
            <el-step title="进程5"></el-step>
            <el-step title="进程6"></el-step>
            <el-step title="进程7"></el-step>
            <el-step title="进程8"></el-step>
            <el-step title="进程9"></el-step>
          </el-steps>

      </div>
      <div style="width: 100%;margin-top: 10px;">
       
        <div style="width: 100%;">
          <span>年度监测县(市、区)水稻播栽进度调查表</span>
          <el-form :inline="true" ref="form" :model="form" label-width="180px">
            <el-form-item label="日期" >
                <el-date-picker
                v-model="form.name"
                align="right"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="育秧已播种面积（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="已栽插面积（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="直播已播种面积（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="width: 100%;">
          <span>年度监测县(市、区)水稻苗情评价动态列表(移栽)</span>
          <el-form :inline="true" ref="form" :model="form" label-width="180px">
            <el-form-item label="日期" >
                <el-date-picker
                v-model="form.name"
                align="right"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="一类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="二类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="三类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="width: 100%;">
          <span>年度监测县(市、区)水稻苗情评价动态列表(直播)</span>
          <el-form :inline="true" ref="form" :model="form" label-width="180px">
            <el-form-item label="日期" >
                <el-date-picker
                v-model="form.name"
                align="right"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="一类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="二类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="三类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="width: 100%;">
          <span>年度监测县(市、区)中稻生育进度调查表（移栽稻）</span>
          <el-form :inline="true" ref="form" :model="form" label-width="180px">
            <el-form-item label="日期" >
                <el-date-picker
                v-model="form.name"
                align="right"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="一类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="二类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="三类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div style="width: 100%;">
          <span>年度监测县(市、区)中稻生育进度调查表（直播稻）</span>
          <el-form :inline="true" ref="form" :model="form" label-width="180px">
            <el-form-item label="日期" >
                <el-date-picker
                v-model="form.name"
                align="right"
                type="date"
                placeholder="选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="一类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="二类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
            <el-form-item label="三类苗（万亩）">
              <el-input style="width: 220px;"></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <div style="width: 100%;height: 40px;margin-top: 20px;">
        <el-button style="float: right;margin-right: 10%;">上报</el-button>
      </div>
    </div>
  </div>
</template>
<script>
    import axios from 'axios'
export default{
    data() {
    return {
      tab1CurrentStep:1,//表1当前进程
      tab2CurrentStep:1,//表2当前进程
      tab3CurrentStep:1,//表3当前进程
      tab4CurrentStep:1,//表4当前进程
      tab5CurrentStep:1,//表5当前进程
      currentTab:'tab1',//当前所在表格
      boZai_process:{//年度监测县(市、区)水稻播栽进度调查表

      },
      form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
    };
  },
  methods: {
    
  }
};
</script>
