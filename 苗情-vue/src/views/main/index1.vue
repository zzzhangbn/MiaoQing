<template>
    <div style="margin-left: 1%;">
      <!-- <div style="width: 20%;height: 670px;display: inline-block;border-radius: 10px;border: #CCCCCC 2px dashed;border-style:dotted; ">
        <div style="margin-top: 10px;padding-left: 16px;
        width: 100%;
        height: 30px;
        color: black;
        font-size: 20px;"><i class="el-icon-setting"><span style="margin-left: 10px;">菜单栏</span></i></div>
        <div id="sideBar" style="width: 100%;height: 670px;">
            <el-button plain style="width: 100%;background-color: #F0FFF0;margin-top: 3%;" >人工录入</el-button>
            <el-button plain style="width: 100%;margin-left: 0px;margin-top: 15px;background-color: #F0FFF0;">数据退还</el-button>
            <el-button plain style="width: 100%;margin-left: 0px;margin-top: 15px;background-color: #F0FFF0;">历史数据</el-button>
        </div>
        
      </div> -->
      <div style="width: 100%;box-shadow: #9BCD9B 0px 0px 10px;height: 30px;display: inline-block;float: right;font-size: 15px;color: rgb(132, 132, 132);line-height: 2;
      padding-left: 1%;">当前位置：苗情监测<i class="el-icon-arrow-right"></i><i class="el-icon-arrow-right"></i>人工录入</div>
      
      <div style="width: 100%;height: 100%;float: right;display: inline-block;margin-top: 7px;">

        <el-card style="width: 48%;height: 200px;display: inline-block;float: left;margin-bottom: 5px;">
            <div style="font-size: 18px;width: 4%;margin-left: 4%;height: 40%;display: inline-block;background-color: #6EC6CA;box-shadow: cadetblue 0px 0px 10px;"><i class="el-icon-c-scale-to-original"></i><span style="">基本概况统计</span></div>
            <!-- <div style="font-size:10px;width: 95%;margin-top: 10px;">全县生产概况（水稻栽插概况调查表、水稻生产主推品种调查表）、监测点建档卡</div> -->
            <div style="width: 70%;height: 200px;float: right;margin-top: 4%;">
            <div style="width: 330px;height: 40px;text-align: center;line-height: 35px;display: inline-block;margin-left: -33%;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点建档卡</span>
                <!-- <el-button style="display: inline-block;" type="info" plain>查看</el-button>
                <el-button  style="display: inline-block;"  plain>新增</el-button> -->
            </div>
    
            <div style="width: 330px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;margin-left: -15%;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻栽插概况调查表</span>
                <!-- <el-button style="display: inline-block;" type="info" plain>查看</el-button>
                <el-button  style="display: inline-block;"  plain>新增</el-button> -->
            </div>
    
            <div style="width: 350px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;margin-left: -12%;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻生产主推品种调查表</span>
                <!-- <el-button style="display: inline-block;" type="info" plain>查看</el-button>
                <el-button  style="display: inline-block;"  plain>新增</el-button> -->
            </div>
            </div>
        </el-card>
      
        <el-card style="width: 51%;height: 325px;display: inline-block;float: right;margin-bottom: 5px;">
            <div style="font-size: 18px;width: 4%;margin-left: 4%;float: left;display: inline-block;margin-top: 7%;background-color: #6EC6CA;box-shadow: cadetblue 0px 0px 10px;"><i class="el-icon-c-scale-to-original"></i>生长进度统计</div>
            <!-- <div style="font-size:10px;">全县面上生产调度（水稻播栽进度调查表、水稻苗情评价动态列表）、监测点生产进度调查（中稻生育进度、水稻监测点茎蘖数叶面积、水稻苗情生育期）</div> -->
            <div  style="width: 70%;height: 500px;display: inline-block;">
    
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;margin-left: 10%;">
                <i class="el-icon-caret-right" @click="getList1($event)"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻苗情评价动态列表</span>
            </div>

            <template v-if="list1 == true">
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;margin-left: 10%;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻苗情评价动态列表（移栽）</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻苗情评价动态列表（直播）</span>
            </div>
            </template>
    
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;margin-left: 10%;">
                <i class="el-icon-caret-right" @click="getList2($event)"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)中稻生育进度调查表</span>
            </div>

            <template v-if="list2 == true">
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)中稻生育进度调查表（移栽稻）</span>
            </div>

            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻播栽进度调查表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)生育进度调查表（直播稻）</span>
            </div>
            </template>

            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点茎蘖数叶面积构成表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点苗情生育期统计表</span>
            </div>
    
            </div>
        </el-card>
  
        <el-card style="float: left;width: 48%;height: 500px;">
            <div style="font-size: 18px;width: 5%;margin-left: 4%;height: 40%;display: inline-block;float: left;margin-top: 20%;background-color: #6EC6CA;box-shadow: cadetblue 0px 0px 10px;"><i class="el-icon-c-scale-to-original"></i>监测点参数测定与记录</div>
            <!-- <div style="font-size:10px;">叶片参数、监测点田管、气象及自然灾害</div> -->
            <div  style="width: 70%;height: 500px;display: inline-block;">

            <div style="width: 160px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-caret-right" @click="getList3($event)"></i>
                <span style="margin-left: 10px;">水稻灾害及防治表</span>
                
            </div>

            <template v-if="list3 == true">
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点病虫害防治记录表</span>
            </div>

            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻倒伏灾害表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻低温冷害表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻高温热害表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻涝害调查表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点洪涝灾害表</span>
            </div>
            </template>

            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit" @click="openShuiDaoList"></i>
                <span style="margin-left: 10px;">叶片含氮测定仪导出数据</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点施肥管理记录表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点化学调节剂使用记录表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)气象资料列表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)抽穗期前、后各15天气象资料</span>
            </div>

            </div>
        </el-card>

        <el-card style="display: inline-block;width: 51%;height: 50%;float: right;">
            <div style="font-size: 18px;width: 6%;margin-left: 4%;height: 40%;display: inline-block;"><i class="el-icon-c-scale-to-original"></i>生产效益与测产进度：</div>
            <!-- <div style="font-size:10px;">全县面上测产相关进度监测（水稻苗情预测、水稻收获进度、水稻测产）、全县生产效益调查</div> -->
            <div  style="width: 70%;height: 300px;display: inline-block;">
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度水稻监测点洪涝灾害表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻单季稻苗情监测预测产表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻单季稻收获进度调查</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻单季稻测产表</span>
            </div>
    
            <div style="width: 390px;height: 40px;text-align: center;margin-top: 1%;line-height: 35px;display: inline-block;">
                <i class="el-icon-edit"></i>
                <span style="margin-left: 10px;">年度监测县(市、区)水稻生产成本效益统计表</span>
            </div>
    
            </div>
        </el-card>
    </div>
    </div>
  </template>
  <script>
      import axios from 'axios'
  export default{
      data() {
      return {
        list1:false,
        list2:false,
        list3:false,
        list4:false,
      };
    },
    methods: {
      test(){
      
      },
      getList1(e){
          if(this.list1 == true)
          {
            this.list1 = false;
            e.target.className = 'el-icon-caret-right';
        }
          else{
              this.list1 = true;
              e.target.className = 'el-icon-caret-bottom';
            } 
      },
      getList2(e){
          if(this.list2 == true)
          {
            this.list2 = false;
            e.target.className = 'el-icon-caret-right';
        }
          else{
              this.list2 = true;
              e.target.className = 'el-icon-caret-bottom';
            } 
      },
      getList3(e){
          if(this.list3 == true)
          {
            this.list3 = false;
            e.target.className = 'el-icon-caret-right';
        }
          else{
              this.list3 = true;
              e.target.className = 'el-icon-caret-bottom';
            } 
      },
      //打开水稻相关表格
      openShuiDaoList(){},
    }
  };
  </script>
  <style>
#sideBar{
    background-image: url('');
}
  </style>
  