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
                        <div id="crop_01_01_table_01_02" style="width:1050px;height:500px;"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)水稻苗情评价动态列表（移栽稻）" name="2">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_13">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="area" placeholder="请选择地区" @change="change_3">
                            <el-option
                            v-for="item in cities"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_01_table_03_02" style="width:1050px;height:500px;"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)中稻生育进度调查表（移栽稻）" name="3">
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
                        <div id="crop_01_01_table_04_01" style="width:1050px;height:300px;margin-bottom:30px"></div>
                        <el-select v-model="area" placeholder="请选择地区" @change="change_5">
                            <el-option
                            v-for="item in cities"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_01_table_04_01_line" style="width:1050px;height:300px;"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度水稻监测点茎蘖数叶面积构成表" name="4">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_7">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="strainCategory" placeholder="请选择品系类别" @change="change_8" clearable>
                            <el-option
                            v-for="item in strainCategories"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="cultivationMethod" placeholder="请选择栽培方式" @change="change_9" clearable>
                            <el-option
                            v-for="item in cultivationMethods"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_01_table_04_03_line" style="width:1050px;height:300px;"></div>
                        <div id="crop_01_01_table_04_03_bar" style="width:1050px;height:300px;margin-top:30px"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)水稻单季稻苗情监测预测产表" name="5">
                    <div class="table_content">
                        <div id="crop_01_01_table_07_01" style="width:1050px;height:700px;margin-top:30px"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)水稻单季稻收获进度调查" name="6">
                    <div class="table_content">
                        <div id="crop_01_01_table_07_02_bar" style="width:1050px;height:500px;margin-top:30px"></div>
                    </div>
                </el-tab-pane>
                <el-tab-pane label="年度监测县(市、区)水稻单季稻测产表" name="7">
                    <div class="table_content">
                        <div id="crop_01_01_table_07_03_bar" style="width:1050px;height:700px;margin-top:30px"></div>
                    </div>
                </el-tab-pane>
                 <el-tab-pane label="年度水稻监测点灌浆速率调查表" name="8">
                    <div class="table_content">
                        <el-select v-model="areaCate" placeholder="请选择地区类型" @change="change_12">
                            <el-option
                            v-for="item in areaCates"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_01_table_10_02" style="width:1050px;height:300px;margin-top:30px"></div>
                        <el-select v-model="area" placeholder="请选择县市" @change="change_10">
                            <el-option
                            v-for="item in cities.slice(1,cities.length-1)"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <el-select v-model="monitoringPoint" placeholder="请选择监测点代码" @change="change_11">
                            <el-option
                            v-for="item in monitoringPoints"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                            </el-option>
                        </el-select>
                        <div id="crop_01_01_table_10_01" style="width:1050px;height:300px;margin-top:30px"></div>
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
            year:'',
            //表格选择名
            activeName:'1',
            //县市区选择器的option
            cities: [
                {value:'',label:'全省层面'},
                {value: '肥东县',label: '肥东县'}, 
                {value: '肥西县',label: '肥西县'}, 
                {value: '巢湖市',label: '巢湖市'}, 
                {value: '湾沚区',label: '湾沚区'}, 
                {value: '无为市',label: '无为市'}, 
                {value: '东至县',label: '东至县'}, 
                {value: '天长市',label: '天长市'}, 
                {value: '定远县',label: '定远县'}, 
                {value: '全椒县',label: '全椒县'}, 
                {value: '来安县',label: '来安县'}, 
                {value: '明光市',label: '明光市'}, 
                {value: '桐城市',label: '桐城市'}, 
                {value: '太湖县',label: '太湖县'}, 
                {value: '望江县',label: '望江县'}, 
                {value: '泾县',label: '泾县'}, 
                {value: '舒城县',label: '舒城县'}, 
                {value: '裕安区',label: '裕安区'}, 
                {value: '霍山县',label: '霍山县'}, 
                {value: '当涂县',label: '当涂县'}, 
                {value: '颍上县',label: '颍上县'}, 
                {value: '宿松县',label: '宿松县'}, 
                {value: '广德市',label: '广德市'}, 
                {value: '郎溪县',label: '郎溪县'}, 
                {value: '休宁县',label: '休宁县'}, 
                {value: '义安区',label: '义安区'}, 
                {value: '五河县',label: '五河县'}, 
                {value: '怀远县',label: '怀远县'}, 
                {value: '凤台县',label: '凤台县'}, 
                {value: '寿县',label: '寿县'}, 
                {value: '霍邱县',label: '霍邱县'}, 
            ],
            //crop_01_01_table_01_02Analysis
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
            //地区类型沿淮地区、江淮地区、沿江地区
            areaCates:[
                {value:'',label:'全省层面'},
                {value: '沿淮地区',label: '沿淮地区'},
                {value: '江淮地区',label: '江淮地区'},
                {value: '沿江地区',label: '沿江地区'}
            ],
            areaCate:'',
            //稻作类型 中籼稻、中粳稻
            riceCates:[
                {value: '中籼稻',label: '中籼稻'},
                {value: '中粳稻',label: '中粳稻'}
            ],
            riceCate:'',
            //品系类别 常规稻、二系杂交稻、三系杂交稻
            strainCategories:[
                {value: '常规稻',label: '常规稻'},
                {value: '二系杂交稻',label: '二系杂交稻'},
                {value: '三系杂交稻',label: '三系杂交稻'},
            ],
            strainCategory:'',
            //栽培方式 机插秧、人工栽插、抛秧
            /* cultivationMethods:[
                {value: '机插秧',label: '机插秧'},
                {value: '人工栽插',label: '人工栽插'},
                {value: '抛秧',label: '抛秧'},
                {value:'直播',label:'直播'}
            ], */
            cultivationMethods:[
                {value: '机械插秧',label: '机械插秧'},
                {value: '人工移栽',label: '人工移栽'},
                {value: '盘育抛栽',label: '盘育抛栽'},
                {value:'机条播（水）',label:'机条播（水）'},
                {value:'撒直播（水）',label:'撒直播（水）'},
            ],
            cultivationMethod:'',
            //监测点代码
            monitoringPoints:[],
            monitoringPoint:''
        }
    },
    mounted(){
        this.crop_01_01_table_01_02Analysis_1();
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }
        //   console.log(1111)
        //   console.log(this.year)
    },
    methods:{
        //tab_pane点击事件
        handleClick(tab, event) {
            this.area = '';
            this.areaCate = '';
            switch(tab.label){
                case '年度监测县(市、区)水稻生产主推品种调查表':
                   this.crop_01_01_table_01_02Analysis_1();
                   break;
                case '年度监测县(市、区)水稻苗情评价动态列表（移栽稻）':
                   this.crop_01_01_table_03_02Analysis_1();
                   break;
                case '年度监测县(市、区)中稻生育进度调查表（移栽稻）':
                    this.crop_01_01_table_04_01Analysis_1();
                    this.crop_01_01_table_04_01Analysis_2();
                    break;
                case '年度水稻监测点茎蘖数叶面积构成表':
                    this.crop_01_01_table_04_03Analysis_1();
                    this.crop_01_01_table_04_03Analysis_2();
                    break;
                case '年度监测县(市、区)水稻单季稻苗情监测预测产表':
                    this.crop_01_01_table_07_01Analysis_1();
                    break;
                case '年度监测县(市、区)水稻单季稻收获进度调查':
                    this.crop_01_01_table_07_02Analysis_1();
                    break;
                case '年度监测县(市、区)水稻单季稻测产表':
                    this.crop_01_01_table_07_03Analysis_1();
                    break;
                case '年度水稻监测点灌浆速率调查表':
                    this.getMonitoringCode('肥东县');
                    this.crop_01_01_table_10_01Analysis_2();
                    break;
                    
            }
        },
        /*
        表1-2  20xx年度监测县(市、区)水稻生产主推品种调查表
        crop_01_01_table_01_02Analysis_1：通过控制县名查出各品系的占比
        crop_01_01_table_01_02Analysis_2：通过控制品系查出该品系在个县的占比
        */
        crop_01_01_table_01_02Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
            axios({
                url:'/crop_01_01_table_01_02Analysis_1',
                method:'post',
                params:{area:this.area,year: this.year}
            }).then(res=>{
                var data = res.data;
                if(data.cates.length==0){
                    this.$message({message:'你选择地区暂无数据，请重新选择',type:'warning'});
                    return;
                }
                let Echart = this.$echarts.init(document.getElementById('crop_01_01_table_01_02'));
                Echart.setOption({
                        title: {
                            text: '水稻品种排名前十',
                            left: 'center'
                        },
                        color:['#66b1ff'],
                        tooltip:{
                            trigger: 'axis',
                            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'cross'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '5%',
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
                            data: data.cates,
                            axisLabel:{
                                fontSize:13
                            }
                        },
                        yAxis: {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 万亩',
                                fontSize:13
                            }
                        },
                        series: [{
                            data: data.datas,
                            type: 'bar',
                            barWidth:40
                        }]
                    })
            })
        },
        //通过调整县市来获取不同的数据
        change_1(val){
            this.area = val;
            this.crop_01_01_table_01_02Analysis_1();
        },
        //通过调整水稻品种来查看不同县市的占比
        change_2(val){
            this.variety = val;
            this.crop_01_01_table_01_02Analysis_2();
        },
        /*
        表3-2  年度监测县(市、区)水稻苗情评价动态列表（移栽稻）
        crop_01_01_table_03_02Analysis_1：通过控制县名查出一、二、三秒的数据
        */
       crop_01_01_table_03_02Analysis_1(){
                   var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
           axios({
               url:'/crop_01_01_table_03_02Analysis_1',
               method:'post',
               params:{area:this.area,areaCate:this.areaCate,year:this.year}
           }).then(res=>{
                // //console.log(res);
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                var echart = this.$echarts.init(document.getElementById("crop_01_01_table_03_02"));
                echart.setOption({
                    title:{
                        text:'各进程1、2、3类苗数据对比'
                    },
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
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data: ['一类苗', '二类苗', '三类苗'],
                        textStyle:{
                            fontSize:13
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: data.fields,
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '数量',
                            axisLabel: {
                                formatter: '{value} 万亩',
                                fontSize:13
                            }
                        }
                    ],
                    color: ['#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '一类苗',
                            type: 'bar',
                            data: data.datas1
                        },
                        {
                            name: '二类苗',
                            type: 'bar',
                            data: data.datas2
                        },
                        {
                            name: '三类苗',
                            type: 'bar',
                            data: data.datas3
                        }
                    ]
                })
           })
       },
       //通过调整县市来获取不同的数据
        change_3(val){
            this.area = val;
            this.areaCate = '';
            this.crop_01_01_table_03_02Analysis_1();
        },
        change_13(val){
            this.areaCate = val;
            this.area = '';
            this.crop_01_01_table_03_02Analysis_1();

        },
        /*
        表4-1  年度监测县(市、区)中稻生育进度调查表（移栽稻）
        crop_01_01_table_04_01Analysis_1：不同地区类型下不同进程中的平均叶龄和平均茎蘖数
        */
       crop_01_01_table_04_01Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
           axios({
               url:'/crop_01_01_table_04_01Analysis_1',
               method:'post',
               params:{areaCate:this.areaCate,riceCate:this.riceCate,cultivationMethod:this.cultivationMethod,year:this.year}
           }).then(res=>{
            //    //console.log(res);
               var data = res.data;
               if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               let echart = this.$echarts.init(document.getElementById('crop_01_01_table_04_01'));
               echart.setOption({
                   title:{
                       text:'某地区类型下的各进程叶龄以及茎蘖数对比'
                   },
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
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
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
                            data: data.fields,
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel: {
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '叶龄',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#5470C6'
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 叶',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '茎蘖数',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#91CC75'
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万/亩',
                                fontSize:13
                            }
                        },
                    ],
                    color: ['#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '叶龄',
                            type: 'bar',
                            data: data.datas1
                        },
                        {
                            name: '茎蘖数',
                            type: 'bar',
                            yAxisIndex:1,
                            data: data.datas2
                        },
                    ]
                })
               
           })
       },
       crop_01_01_table_04_01Analysis_2(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
           axios({
               url:'/crop_01_01_table_04_01Analysis_2',
               method:'post',
               params:{area:this.area,year:this.year}
           }).then(res=>{
            //    //console.log(res);
               var data = res.data;
               if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               let echart = this.$echarts.init(document.getElementById('crop_01_01_table_04_01_line'));
               echart.setOption({
                   title:{
                       text:'各进程叶龄以及茎蘖数对比'
                   },
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
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
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
                            data: data.fields,
                            axisPointer: {
                                type: 'shadow'
                            },
                            axisLabel: {
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '叶龄',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#91CC75'
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 叶',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '茎蘖数',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: '#EE6666'
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万/亩',
                                fontSize:13
                            }
                        },
                    ],
                    color: ['#5470C6' ,'#91CC75', '#EE6666' ],
                    series: [
                        {
                            name: '叶龄',
                            type: 'line',
                            smooth: true,
                            data: data.datas1
                        },
                        {
                            name: '茎蘖数',
                            type: 'line',
                            smooth: true,
                            yAxisIndex:1,
                            data: data.datas2
                        },
                    ]
                })
               
           })
       },
       //通过调整不同的地区类型来获取数据
       change_4(val){
            this.areaCate = val;
            this.crop_01_01_table_04_01Analysis_1();
        },
        change_4_1(val){
            this.riceCate = val;
            this.crop_01_01_table_04_01Analysis_1();
        },
        change_4_2(val){
            this.cultivationMethod = val;
            this.crop_01_01_table_04_01Analysis_1();
        },
        change_5(val){
            this.area = val;
            this.crop_01_01_table_04_01Analysis_2();
        },
        /*
        表4-3  年度水稻监测点茎蘖数叶面积构成表
        crop_01_01_table_04_03Analysis_1：通过地区类型求得平均值
        */
       crop_01_01_table_04_03Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
            var colors = ['#5470C6', '#91CC75', '#EE6666'];
           axios({
               url:'/crop_01_01_table_04_03Analysis_1',
               method:'post',
               params:{areaCate:this.areaCate,strainCategory:this.strainCategory,cultivationMethod:this.cultivationMethod,year:this.year}
           }).then(res=>{
            //    //console.log(res);
                var data = res.data;
                // //console.log(data);
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               let echart = this.$echarts.init(document.getElementById("crop_01_01_table_04_03_line"));
               echart.setOption({
                    title: {
                        text: '水稻茎蘖数叶面积'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#6a7985'
                            }
                        }
                    },
                    legend: {
                        data: ['株高','总茎蘖数(万/亩)','平均单茎绿叶数'],
                        top:13,
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
                    grid: {
                        top:'25%',
                        left: '3%',
                        right: '8%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: data.fields,
                            axisLabel: {
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '株高',
                            position: 'left',
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
                            name: '总茎蘖数(万/亩)',
                            position: 'right',
                            offset: 95,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[1]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万/亩',
                                fontSize:13
                            },
                            
                        },
                        {
                            type: 'value',
                            name: '平均单茎绿叶数',
                            position: 'right',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: colors[2]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 个',
                                fontSize:13
                            }
                        },
                    ],
                    color : ['#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '株高',
                            type: 'line',
                            data: data.datas1
                        },
                        {
                            name: '总茎蘖数(万/亩)',
                            type: 'line',
                            yAxisIndex: 1,
                            data: data.datas2
                        },
                        {
                            name: '平均单茎绿叶数',
                            type: 'line',
                            yAxisIndex: 2,
                            data: data.datas3
                        }
                    ]
               })
           })
       },
       change_7(val){
            this.areaCate = val;
            this.crop_01_01_table_04_03Analysis_1();
        },
        change_8(val){
            this.strainCategory = val;
            this.crop_01_01_table_04_03Analysis_1();
        },
        change_9(val){
            this.cultivationMethod = val;
            this.crop_01_01_table_04_03Analysis_1();
        },
        crop_01_01_table_04_03Analysis_2(){
            var year=sessionStorage.getItem('years');
            if(year==''||year==null)
            {
                this.year=2022
            }else{
                this.year=sessionStorage.getItem('years')
            }
            axios({
                url:'/crop_01_01_table_04_03Analysis_2',
                method:'post',
               params:{year:this.year}

            }).then(res=>{
                // //console.log(res);
                var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                let echarts = this.$echarts.init(document.getElementById('crop_01_01_table_04_03_bar'));
                echarts.setOption({
                    title: {
                        text: '分葉数',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['江淮地区', '沿江地区','沿淮地区']
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
                            data: ['1叶', '2叶', '3叶']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 叶'
                            },
                        },
                        
                    ],
                    series:[
                        {
                            name: '江淮地区',
                            type: 'bar',
                            data: data.datas1
                        },
                        {
                            name: '沿江地区',
                            type: 'bar',
                            data: data.datas2
                        },
                        {
                            name: '沿淮地区',
                            type: 'bar',
                            data: data.datas3
                        }
                    ]
                })
            })
        },
        
        /*
        表7-1：年度监测县(市、区)水稻单季稻苗情监测预测产表
        crop_01_01_table_07_01Analysis_1：柱状图展示某一地区类型下的面积、亩有效穗(万)、穗总粒数(个)、结实率(％)、千粒重(g)、理论产量(kg/亩)
        crop_01_01_table_04_03Analysis_2：不同地区类型下的分葉数
        */
        crop_01_01_table_07_01Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
            axios({
                url:'/crop_01_01_table_07_01Analysis_1',
                method:'post',
                params:{year:this.year}
            }).then(res=>{

                // //console.log(res);
                var data = res.data;
                console.log(data);
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                var datas = res.data.datas;
                var percents = res.data.percents;
                var len = datas.length/3;
                let barechart = this.$echarts.init(document.getElementById("crop_01_01_table_07_01"));
                var option;
                option = {
                    title:{
                       text:'三大地区类型预估产量同比以及环比'
                   },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            animation: false
                        }
                    },
                    legend: {
                        data: ['','全省平均','江淮地区', '沿江地区','沿淮地区','全省-同比','江淮地区-同比','沿江地区-同比','沿淮地区-同比',''],
                        top:25,
                    /*     left: 300
                    }, */
                    // axisPointer: {
                    //     link: {xAxisIndex: 'all'}
                        textStyle:{
                            fontSize:13
                        }
                    },

                    toolbox: {
                            show: true,
                            feature: {
                                dataView: {show: true, readOnly: false},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                    },
                    grid: [{
                        left: 50,
                        right: 50,
                        height: '35%'
                    }, {
                        left: 50,
                        right: 20,
                        top: '55%',
                        height: '35%'
                    }],
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            axisLine: {onZero: true},
                            data: ["","进程1","进程2","进程3","进程4","进程5","进程6",""]
                        },
                        {
                            gridIndex: 1,
                            type: 'category',
                            boundaryGap: false,
                            axisLine: {onZero: true},
                            data: ["","进程1","进程2","进程3","进程4","进程5","进程6",""],
                            position: 'bottom',
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            name: '产量(kg/亩)',
                            type: 'value',
                            axisLabel:{
                                fontSize:13
                            }
                        },
                        {
                            gridIndex: 1,
                            name: '同比增减(%)',
                            type: 'value',
                            inverse: false,
                            axisLabel: {
                                formatter: '{value} %'
                            }
                        }
                    ],
                    color: ['#5470C6', '#91CC75', '#EE6666','orange'],
                    series: [
                        {
                            name: '江淮地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(0,7)
                        },
                        {
                            name: '沿江地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(7,14)
                        },
                        {
                            name: '沿淮地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(14,21)
                        },
                        {
                            name: '全省平均',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(21,28)
                        },
                        {
                            name: '江淮地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(0,7)
                        },
                        {
                            name: '沿江地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(7,7*2)
                        },
                        {
                            name: '沿淮地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(7*2,7*3)
                        },
                        {
                            name: '全省平均-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(21,28)
                        }
                    ]
                };
                barechart.setOption(option);

            })
        },

        /*
        表7-1：年度监测县(市、区)水稻单季稻苗情监测预测产表
        crop_01_01_table_07_01Analysis_1：柱状图展示理论产量(kg/亩)
        */
       crop_01_01_table_07_02Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
            axios({
                url:'/crop_01_01_table_07_02Analysis_1',
                method:'post',
                params:{year:this.year}
            }).then(res=>{
                // //console.log(res);
                var data = res.data;
                //console.log(data);
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                var datas = data.datas;
                let barechart = this.$echarts.init(document.getElementById("crop_01_01_table_07_02_bar"));
                var option;
                option = {
                    title: {
                        text: '三大地区类型各进程的收获进度',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['全省','江淮地区', '沿江地区','沿淮地区'],
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
                            data: ['收获进度1','收获进度2','收获进度3','收获进度4','收获进度5','收获进度6'],
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} 万亩',
                                fontSize:13
                            }
                        },
                        {
                            type: 'value',
                            name: '全省',
                            position: 'right',
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    color: 'orange'
                                }
                            },
                            axisLabel: {
                                formatter: '{value} 万亩',
                                fontSize:13
                            }
                        },
                    ],
                    color: ['orange','#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '全省',
                            type: 'line',
                            yAxisIndex: 1,
                            /* data:[56.53,107.37,142.6,174.28,204.43,229.12] */
                            data:datas.slice(18,24)
                            
                        },
                        {
                            name: '江淮地区',
                            type: 'bar',
                            data: datas.slice(0,6),
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
                            name: '沿江地区',
                            type: 'bar',
                            data: datas.slice(6,12),
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
                            name: '沿淮地区',
                            type: 'bar',
                            data: datas.slice(12,18),
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
                    ]
                };
                barechart.setOption(option);

            })
        },
        /*
        表7-3：年度监测县(市、区)水稻单季稻测产表
        crop_01_01_table_07_02Analysis_1：柱状图展示三大地区类型下的理论产量(kg/亩)
        */
        crop_01_01_table_07_03Analysis_1(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
            axios({
                url:'/crop_01_01_table_07_03Analysis_1',
                method:'post',
                params:{year:this.year}
            }).then(res=>{
                // console.log(res,data);
                var data = res.data;
                // //console.log(res.data);
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
                var datas = res.data.datas;
                var percents = res.data.percents;
                console.log(datas)
                console.log(percents)
                data.fields.push("");
                let barechart = this.$echarts.init(document.getElementById("crop_01_01_table_07_03_bar"));
                var option;
                var timeData = data.fields;
                option = {
                    title:{
                       text:'三大地区类型各进程产量同比以及环比'
                   },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            animation: false
                        }
                    },
                    legend: {
                        data: ['江淮地区', '沿江地区','沿淮地区','江淮地区-同比','沿江地区-同比','沿淮地区-同比'],
                        top:30,
                        textStyle:{
                                fontSize:13
                            }
                    },
                    // axisPointer: {
                    //     link: {xAxisIndex: 'all'}
                    // },
                    toolbox: {
                            show: true,
                            feature: {
                                dataView: {show: true, readOnly: false},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                    },
                    grid: [{
                        left: 50,
                        right: 50,
                        height: '35%'
                    }, {
                        left: 50,
                        right: 50,
                        top: '55%',
                        height: '35%'
                    }],
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            axisLine: {onZero: true},
                            data: ["","全县平均水平","人工移栽","机械插秧","盘育抛秧","无盘旱育抛秧","撒直播","机条播",""],
                            axisLabel:{
                                fontSize:13
                            }
                        },
                        {
                            gridIndex: 1,
                            type: 'category',
                            boundaryGap: false,
                            axisLine: {onZero: true},
                            data: ["","全县平均水平","人工移栽","机械插秧","盘育抛秧","无盘旱育抛秧","撒直播","机条播",""],
                            position: 'bottom',
                            axisLabel:{
                                fontSize:13
                            }
                        }
                    ],
                    yAxis: [
                        {
                            name: '产量(kg/亩)',
                            type: 'value',
                            axisLabel:{
                                fontSize:13
                            }
                        },
                        {
                            gridIndex: 1,
                            name: '同比增减(%)',
                            type: 'value',
                            inverse: false,
                            axisLabel: {
                                formatter: '{value} %',
                                fontSize:13
                            }
                        }
                    ],
                    color: ['#5470C6', '#91CC75', '#EE6666'],
                    series: [
                        {
                            name: '江淮地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(0,8)
                        },
                        {
                            name: '沿江地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(8,16)
                        },
                        {
                            name: '沿淮地区',
                            type: 'bar',
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: datas.slice(16,24)
                        },
                        {
                            name: '江淮地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(0,8)
                        },
                        {
                            name: '沿江地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(8,16)
                        },
                        {
                            name: '沿淮地区-同比',
                            type: 'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            symbolSize: 8,
                            hoverAnimation: false,
                            data: percents.slice(16,24)
                        }
                    ]
                };
                barechart.setOption(option);

            })
        },

        /*
        表10-1：年度监测县(市、区)水稻单季稻测产表
        getMonitoringCode:通过县查出所有的监测点代码
        */
       getMonitoringCode(val){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
           axios({
               url:'/getMonitoringCode',
               method:'post',
               params:{area:val,year:this.year}
           }).then(res=>{
               var monitoringPoints = [];
               for (const point of res.data) {
                   var item = {value:point,label:point}
                    monitoringPoints.push(item);
               }
               this.monitoringPoints = monitoringPoints;
                this.crop_01_01_table_10_01Analysis_1('肥西县_001');
           })
       },
       crop_01_01_table_10_01Analysis_1(monitoring_code){
            var year=sessionStorage.getItem('years');
            if(year==''||year==null)
            {
                this.year=2022
            }else{
                this.year=sessionStorage.getItem('years')
            }
           axios({
               url:'/crop_01_01_table_10_01Analysis_1',
               method:'post',
               params:{monitoring_code:monitoring_code,year:this.year}
           }).then(res=>{
               var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               var chartDom = document.getElementById('crop_01_01_table_10_01');
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
                            data: res.data.datas,
                            barWidth:60,
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
                };

                option && myChart.setOption(option);

           })
       },
       crop_01_01_table_10_01Analysis_2(){
        var year=sessionStorage.getItem('years');
        if(year==''||year==null)
        {
            this.year=2022
        }else{
            this.year=sessionStorage.getItem('years')
        }
        // console.log("111")
        // console.log(this.year)
        // console.log("1-2")
        // console.log(this.year)
           axios({
               url:'/crop_01_01_table_10_01Analysis_2',
               method:'get',
               params:{areaCate:this.areaCate,year:this.year}
           }).then(res=>{
               var data = res.data;
                if(JSON.stringify(data) == "{}"){
                    this.$message({message:"你选择的条件下暂无数据！",type:'warning'});
                    return;
                }
               var chartDom = document.getElementById('crop_01_01_table_10_02');
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
           this.crop_01_01_table_10_01Analysis_1(val);
       },
       change_12(val){
           this.areaCate = val;
           this.crop_01_01_table_10_01Analysis_2();
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
    width: 75%;
    margin: 0 auto;
}
.table_content{
    width: 100%;
    height: 700px;
}
</style>