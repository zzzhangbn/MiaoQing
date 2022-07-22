<template>
  <div class="total">
      <div class="table">
        <el-card>
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="年度监测县(市、区)水稻生产主推品种调查表" name="1">
                    <div class="table_content">
                        <el-select v-model="area" placeholder="请选择地区" @change="change_1">
                            <el-option
                            v-for="item in cities"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_01_02" style="width:1000px;height:500px;"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)水稻苗情评价动态列表（晚稻）" name="2">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区" @change="change_3">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="area" placeholder="请选择地区" @change="change_3_1">
                            <el-option
                            v-for="item in cities"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_03_06" style="width:1000px;height:500px;"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)双季稻生育进度调查表（移栽稻）" name="3">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_4">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="riceCate" placeholder="请选择稻作类型" @change="change_4_1" clearable>
                            <el-option
                            v-for="item in riceCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="cultivationMethod" placeholder="请选择栽植方式" @change="change_4_2" clearable>
                            <el-option
                            v-for="item in cultivationMethods"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_04_01" style="width:1000px;height:500px;margin-bottom:30px"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度水稻监测点茎蘖数叶面积构成表" name="4">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_9">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="strainCategory" placeholder="请选择品系类别" @change="change_9_1" clearable>
                            <el-option
                            v-for="item in strainCategories"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="cultivationMethod" placeholder="请选择栽植方式" @change="change_9_2" clearable>
                            <el-option
                            v-for="item in cultivationMethods"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_04_03" style="width:1000px;height:500px;"></div>
                    </div>
                </el-tab-pane>
                 <el-tab-pane label="年度水稻监测点灌浆速率调查表（早稻）" name="5">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_12">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_10_02" style="width:1050px;height:300px;margin-top:30px"></div>
                        <el-select v-model="area" placeholder="请选择县市" @change="change_10" clearable>
                            <el-option
                            v-for="item in cities.slice(1,cities.length-1)"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="monitoringPoint" placeholder="请选择监测点代码" @change="change_11" clearable>
                            <el-option
                            v-for="item in monitoringPoints"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_02_table_10_01" style="width:1000px;height:400px;margin-top:30px"></div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </el-card>

      </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            //表格选择名
            activeName:'1',
            //县市区选择器的option
            cities: [
                {value:'',label:'全省层面'},
                {value:'宣州区',label:'宣州区'},
                {value:'庐江县',label:'庐江县'},
                {value:'怀宁县',label:'怀宁县'},
                {value:'枞阳县',label:'枞阳县'},
                {value:'贵池区',label:'贵池区'},
                {value:'南陵县',label:'南陵县'},
            ],
            //crop_01_02_table_01_02Analysis
            //水稻品种选项
            varieties:[
                {value: '水稻1号',label: '水稻1号'},
                {value: '水稻2号',label: '水稻2号'},
                {value: '水稻3号',label: '水稻3号'},
                {value: '水稻4号',label: '水稻4号'},
                {value: '水稻5号',label: '水稻5号'},
                {value: '水稻6号',label: '水稻6号'},
            ],
            variety:'水稻1号',
            area:'',
            areas:'',
            years:'',
            nian:'',
            // monitoring_code:'',
            //地区类型沿淮地区、江淮地区、沿江地区
            areaCates:[
                {value: '',label: '全省层面'},
                {value: '沿淮地区',label: '沿淮地区'},
                {value: '江淮地区',label: '江淮地区'},
                {value: '沿江地区',label: '沿江地区'}
            ],
            areaCate:'',
            //稻作类型 中籼稻、中粳稻
            riceCates:[
                {value: '中籼稻',label: '中籼稻'},
                {value: '中粳稻',label: '中粳稻'},
                {value: '早籼稻',label: '早 籼稻'},
            ],
            //riceCate:'中粳稻',
            riceCate:'',
            //品系类别 常规稻、二系杂交稻、三系杂交稻
            strainCategories:[
                {value: '常规稻',label: '常规稻'},
                {value: '二系杂交稻',label: '二系杂交稻'},
                {value: '三系杂交稻',label: '三系杂交稻'},
            ],
            //strainCategory:'二系杂交稻',
            strainCategory:'',
            //栽培方式 机插秧、人工栽插、抛秧
            cultivationMethods:[
                {value: '机械插秧',label: '机械插秧'},
                {value: '人工移栽',label: '人工移栽'},
                {value: '盘育抛栽',label: '盘育抛栽'},
                {value:'机条播（水）',label:'机条播（水）'},
                {value:'撒直播（水）',label:'撒直播（水）'},
            ],
            //cultivationMethod:'机插秧',
            cultivationMethod:'',
            //监测点代码
            monitoringPoints:[],
            monitoringPoint:''
        }
    },
    mounted(){
        this.crop_01_02_table_01_02Analysis_1();
    },
      created() {
    this.initData();
  },
    methods:{
        //初始化数据
        initData() {
            this.areas = this.$route.query.area;
            this.years = this.$route.query.year;
            this.monitoring_code = this.$route.query.monitoring_code
        },
        //tab_pane点击事件
        handleClick(tab, event) {
            switch(tab.label){
                case '年度监测县(市、区)水稻生产主推品种调查表':
                   this.crop_01_02_table_01_02Analysis_1();
                   break;
                case '年度监测县(市、区)水稻苗情评价动态列表（晚稻）':
                   this.crop_01_02_table_03_06Analysis_1();
                   break;
                case '年度监测县(市、区)双季稻生育进度调查表（移栽稻）':
                    this.crop_01_02_table_04_01Analysis_1();
                    break;
                case '年度水稻监测点茎蘖数叶面积构成表':
                    this.crop_01_02_table_04_03Analysis_1();
                    break;
                case '年度水稻监测点灌浆速率调查表（早稻）':
                    this.getMonitoringCode('宣州区');
                    // this.crop_01_02_table_10_01Analysis_1();
                    this.crop_01_02_table_10_01Analysis_2();
                    break;
                    
            }
        },
        /*
        表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
        crop_01_02_table_01_02Analysis_1：通过控制县名查出各品系的占比
        crop_01_02_table_01_02Analysis_2：通过控制品系查出该品系在个县的占比
        */
        crop_01_02_table_01_02Analysis_1(){
            axios({
                url:'/crop_01_02_table_01_02Analysis_1',
                method:'post',
                params:{area:this.area,year:this.years}
            }).then(res=>{
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                if(res.status===200){
                    let Echart = this.$echarts.init(document.getElementById('crop_01_02_table_01_02'));
                    Echart.setOption({
                        color:['#66b1ff'],
                        tooltip:{
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'cross'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data:  data.cates
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                    formatter: '{value} （万亩）'
                            }

                        },
                        series: [{
                            data: data.datas,
                            type: 'bar'
                        }]
                    })
                }
            })
        },
        //通过调整县市来获取不同的数据
        change_1(val){
            this.area = val;
            this.crop_01_02_table_01_02Analysis_1();
        },
        /*
        表3-2  年度监测县(市、区)水稻苗情评价动态列表（晚稻）
        crop_01_02_table_03_06Analysis_1：通过控制县名查出一、二、三类苗的数据
        */
       crop_01_02_table_03_06Analysis_1(){
           axios({
               url:'/crop_01_02_table_03_06Analysis_1',
               method:'post',
               params:{areaCate:this.areaCate,area:this.area,year:this.years}
           }).then(res=>{
               var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                let Echart = this.$echarts.init(document.getElementById('crop_01_02_table_03_06'));
                Echart.setOption({
                        title: {
                            text: '一二三类苗数据',
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['一类苗', '二类苗','三类苗'],
                            textStyle:{
                                fontSize:13
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        calculable: true,
                        xAxis: [
                            {
                                type: 'category',
                                data: data.fields,
                                axisLabel:{
                                    fontSize:13
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                axisLabel: {
                                formatter: '{value} (万/亩)',
                                fontSize:13
                            }
                            }
                            
                        ],
                         color: ['#5470C6', '#91CC75', '#EE6666'],
                        series: [
                            {
                                name: '一类苗',
                                type: 'bar',
                                data: data.datas1,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                /* markLine: {
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                } */
                            },
                            {
                                name: '二类苗',
                                type: 'bar',
                                data: data.datas2,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                /* markLine: {
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                } */
                            },
                            {
                                name: '三类苗',
                                type: 'bar',
                                data: data.datas3,
                                markPoint: {
                                    data: [
                                        {type: 'max', name: '最大值'},
                                        {type: 'min', name: '最小值'}
                                    ]
                                },
                                /* markLine: {
                                    data: [
                                        {type: 'average', name: '平均值'}
                                    ]
                                } */
                            }
                        ]
                })
           })
       },
       //通过调整县市来获取不同的数据
        change_3(val){
            this.areaCate = val;
            this.crop_01_02_table_03_06Analysis_1();
        },
        change_3_1(val){
            this.area = val;
            this.crop_01_02_table_03_06Analysis_1();
        },
        /*
        表4-1  年度监测县(市、区)中稻生育进度调查表（移栽稻）
        crop_01_02_table_04_01Analysis_1：不同地区类型下不同进程中的平均叶龄和平均茎蘖数
        */
       crop_01_02_table_04_01Analysis_1(){
           axios({
               url:'/crop_01_02_table_04_01Analysis_1',
               method:'post',
               params:{areaCate:this.areaCate,riceCate:this.riceCate,cultivationMethod:this.cultivationMethod,year:this.years}
           }).then(res=>{
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               let Echart = this.$echarts.init(document.getElementById('crop_01_02_table_04_01'));
               var option;
                var colors = ['#5470C6', '#91CC75', '#EE6666'];

                option = {
                    color: colors,

                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross'
                        }
                    },
                    grid: {
                        right: '10%'
                    },
                    toolbox: {
                        feature: {
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            magicType: {show: true, type: ['line', 'bar']},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['叶龄', '茎蘖数'],
                        textStyle:{
                            fontSize:13
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {
                                alignWithLabel: true
                            },
                            data: data.fields,
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '茎蘖数',
                            position: 'right',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[0]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} (万/亩)',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '叶龄',
                            position: 'left',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[1]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} （叶）',
                                fontSize:13
                            }
                        }
                    ],
                    series: [
                        {
                            name: '叶龄',
                            type: 'bar',
                            data: data.datas1
                        },
                        {
                            name: '茎蘖数',
                            type: 'bar',
                            yAxisIndex: 1,
                            data: data.datas2
                        }
                    ]
                };

                option && Echart.setOption(option);

           })
       },
       //通过调整不同的地区类型来获取数据
       change_4(val){
            this.areaCate = val;
            this.crop_01_02_table_04_01Analysis_1();
        },
        change_4_1(val){
            this.riceCate = val;
            this.crop_01_02_table_04_01Analysis_1();
        },
        change_4_2(val){
            this.cultivationMethod = val;
            this.crop_01_02_table_04_01Analysis_1();
        },
        /*
        表4-3  年度水稻监测点茎蘖数叶面积构成表
        crop_01_02_table_04_01Analysis_1：通过地区类型求得平均值
        */
       crop_01_02_table_04_03Analysis_1(){
            var colors = ['#5470C6', '#91CC75', '#EE6666'];
           axios({
               url:'/crop_01_02_table_04_03Analysis_1',
               method:'post',
               params:{ areaCate:this.areaCate,
                        strainCategory:this.strainCategory,
                        cultivationMethod:this.cultivationMethod,
                        year:this.years}
           }).then(res=>{
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               let echart = this.$echarts.init(document.getElementById("crop_01_02_table_04_03"));
               echart.setOption({
                     tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            crossStyle: {
                                color: '#999'
                            }
                        }
                    },
                    toolbox: {
                        feature: {
                            // dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    grid: {
                        right: '16%'
                    },
                    legend: {
                        data: ['株高', '总茎蘖数', '平均单茎绿叶数'],
                        textStyle:{
                            fontSize:13
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: data.fields,
                            axisLabel:{
                                fontSize:13
                            },
                            axisPointer: {
                                type: 'shadow'
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '株高',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[0]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} cm',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '总茎蘖数',
                            position:'right',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[1]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} (万/亩)',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '平均单茎绿叶数',
                            offset: 95,
                            position:'right',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[2]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 叶'
                            }
                        },
                    ],
                    color:['#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '株高',
                            type: 'bar',
                            data: data.datas1
                        },
                        {
                            name: '总茎蘖数',
                            type: 'bar',
                            yAxisIndex: 1,
                            data: data.datas2
                        },
                        {
                            name: '平均单茎绿叶数',
                            type: 'bar',
                            yAxisIndex: 2,
                            data: data.datas3
                        }
                    ]
               })
           })
       },
       change_9(val){
            this.areaCate = val;
            this.crop_01_02_table_04_03Analysis_1();
        },
        change_9_1(val){
            this.strainCategory = val;
            this.crop_01_02_table_04_03Analysis_1();
        },
        change_9_2(val){
            this.cultivationMethod = val;
            this.crop_01_02_table_04_03Analysis_1();
        },

        /*
        表10-1：年度监测县(市、区)水稻单季稻测产表
        getMonitoringCode:通过县查出所有的监测点代码
        */
       getMonitoringCode(val){
           axios({
               url:'/getMonitoringCode_2',
               method:'post',
               params:{area:val,year:this.years}
           }).then(res=>{
               var monitoringPoints = [];
               for (const point of res.data) {
                   var item = {value:point,label:point}
                    monitoringPoints.push(item);
               }
               this.monitoringPoints = monitoringPoints;
                this.crop_01_02_table_10_01Analysis_1('宣州区_001');
           })
       },
       crop_01_02_table_10_01Analysis_1(monitoring_code){
           axios({
               url:'/crop_01_02_table_10_01Analysis_1',
               method:'post',
            //    params:{monitoring_code:this.monitoring_code,year:this.years}
            params:{monitoring_code,year:this.years}
           }).then(res=>{
            //    console.log("这里是0102---1001--1")
            //    console.log(res)
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               var chartDom = document.getElementById('crop_01_02_table_10_01');
                var myChart = this.$echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '某监测点不同时期水稻监测点灌浆速率',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['灌浆速率'],
                        textStyle:{
                            fontSize:13
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    // xAxis: [
                    //     {
                    //         type: 'category',
                    //         data: data.dates,
                    //         axisLabel:{
                    //             fontSize:13
                    //         }
                    //     }
                    // ],
                    // yAxis: [
                    //     {
                    //         type: 'value',
                    //         axisLabel: {
                    //             fontSize:13
                    //         },
                    //     }
                    // ],
                    xAxis: [
                        {
                            type: 'category',
                            data: ['扬花+7天','扬花+14天','扬花+21天','扬花+28天','扬花+35天'],
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 克/千粒•天',
                                fontSize:13
                            }
                        }
                    ],
                    color:['#2bae85'],
                    series: [
                        {
                            name: '灌浆速率',
                            type: 'bar',
                            data: data.datas,
                            barWidth:60,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            // markLine: {
                            //     data: [
                            //         {type: 'average', name: '平均值'}
                            //     ]
                            // }
                        }
                    ]
                };

                option && myChart.setOption(option);

           })
       },
       crop_01_02_table_10_01Analysis_2(){
           axios({
               url:'/crop_01_02_table_10_01Analysis_2',
               method:'get',
               params:{areaCate:this.areaCate,year:this.years}
           }).then(res=>{
               var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }

               var chartDom = document.getElementById('crop_01_02_table_10_02');
                var myChart = this.$echarts.init(chartDom);
                var option;
                option = {
                    title: {
                        text: '某地区类型不同时期水稻监测点灌浆速率',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['千粒重','灌浆速率'],
                        textStyle:{
                            fontSize:13
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: ['扬花+7天','扬花+14天','扬花+21天','扬花+28天','扬花+35天'],
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 克',
                                fontSize:13
                            },
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#5470C6'
                                }
                            },
                        },
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 克/千粒•天',
                                fontSize:13
                            },
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#91CC75'
                                }
                            },
                        }
                    ],
                    color: ['#5470C6', '#91CC75'],
                    series: [
                        {
                            name: '千粒重',
                            type: 'bar',
                            data: res.data.datas_1,
                            barWidth:50,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            /* markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            } */
                        },
                        {
                            name: '灌浆速率',
                            type: 'bar',
                            data: res.data.datas_2,
                            barWidth:50,
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            yAxisIndex:1,
                            /* markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            } */
                        }
                    ]
                };

                option && myChart.setOption(option);

           })
       },
       change_10(val){
           this.getMonitoringCode(val)
       },
       change_11(val){
           this.crop_01_02_table_10_01Analysis_1(val);
       },
       change_12(val){
           this.areaCate = val;
           this.crop_01_02_table_10_01Analysis_2();
       }











    }

}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}
.total {
    width: 100%;
    height: auto;
}
.total .table{
    width: 70%;
    margin: 0 auto;
}
.table_content{
    width: 100%;
    height: 700px;
}
</style>