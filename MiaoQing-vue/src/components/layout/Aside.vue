<template>
    <div class="aside" style="width:300px;">
        <div class="logo" style="height:60px;background:#002140;font-size:0;">
            <img src="../../../public/img/logo.svg" alt=""><span class="cai">苗情监测平台</span>
        </div>
        <el-menu
            class="el-menu-vertical-demo"
            :collapse="isCollapse"
            background-color="#242f42"
            text-color="#fff"
            router
        >
        <!-- active-text-color="#ffd04b" -->
        <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <!-- 水稻一级菜单 -->
                        <template slot="title">
                           <span class="firstmenu" slot="title">{{ item.title }}</span>
                        </template>
                        <!-- 有二级菜单 -->
                        <template v-for="subItem in item.subs">
                            <template v-if="subItem.childs">
                                <el-submenu :index="subItem.index" :key="subItem.index">
                                     <template slot="title">
                                            <span class="firstmenu" slot="title">{{ subItem.title }}</span>
                                    </template>
                                    <!-- 三级菜单 -->
                                    <template v-for="childItem in subItem.childs">                                       
                                            <el-submenu v-if="childItem.grandchilds" :index="childItem.index" :key="childItem.index">
                                                <template slot="title">
                                                    <span class="firstmenu" slot="title">{{childItem.title}}</span>
                                                </template>
                                                <!-- 四级菜单 -->
                                                <el-menu-item v-for="(fourItem,i) in childItem.grandchilds" :key="i" :index="fourItem.index">
                                                    <span style="font-size:1px" slot="title">{{ fourItem.title }}</span>
                                                </el-menu-item>
                                            </el-submenu>
                                            <el-menu-item v-else :index="childItem.index" :key="childItem.index">
                                                <span class="firstmenu" slot="title">{{ childItem.title }}</span>
                                            </el-menu-item>
                                    </template>
                                </el-submenu>
                            </template>
                            <template v-else>
                                <el-menu-item :index="subItem.index" :key="subItem.index">
                                        <span class="firstmenu" slot="title">{{ subItem.title }}</span>
                                    </el-menu-item>
                            </template>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <span class="firstmenu" slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
        </div>
</template>
<script>

export default {
    inject: ['reload'],
   name:'Aside',
   props:{
       isCollapse:{
           type:Boolean,
           default:true
       }
   },
   data () {
       return {
        //    isCollapse:,
           items: []
       }
   },
   methods:{
       //根据用户类型生成items
       generateItems(){
           var items = [];
           let access =localStorage.getItem('access');
           switch(access){
                case '2':
                    items=[
                        { icon: 'el-icon-star-on',index: 'dashboard1',title: '水稻农情',
                            subs:[
                                {icon:'el-icon-star-on',index:'dashboard2',title:'一季稻',
                                    childs:[
                                        {
                                        icon:'el-icon-star-on',index:'dashboard3',title:'全县生产概况',                                    
                                        grandchilds:
                                            [
                                                {index:'crop_01_01_table_01_01',title:'监测县(市、区)水稻栽插概况调查表'},
                                                {index:'crop_01_01_table_01_02',title:'监测县(市、区)水稻生产主推品种调查表'},
                                            ]
                                        },
                                        {icon:'el-icon-star-on',index:'dashboard4',title:'监测点建档卡',
                                        grandchilds:[
                                            {index:'crop_01_01_table_02_01',title:'水稻监测点建档卡'},
                                            {index:'crop_01_01_table_02_02',title:'水稻监测点汇总表'},
                                        ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard5',title:'全县面上生产调度',
                                            grandchilds:[
                                                 {index:'crop_01_01_table_03_01',title:'监测县(市、区)水稻播栽进度调查表'},
                                                {index:'crop_01_01_table_03_02',title:'监测县(市、区)水稻苗情评价动态列表(移栽)'},
                                                {index:'crop_01_01_table_03_03',title:'监测县(市、区)水稻苗情评价动态列表(直播)'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard6',title:'监测点生产进度调查',
                                            grandchilds:[
                                                {index:'crop_01_01_table_04_01',title:'监测县(市、区)中稻生育进度调查表（移栽稻）'},
                                                {index:'crop_01_01_table_04_02',title:'监测县(市、区)直播稻生育进度调查表（直播稻）'},
                                                {index:'crop_01_01_table_04_03',title:'水稻监测点茎蘖数叶面积构成表'},
                                                {index:'crop_01_01_table_04_04',title:'水稻监测点苗情生育期统计表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard7',title:'叶片参数自动测定',
                                            grandchilds:[
                                                {index: 'crop_01_01_table_05_01',title: '叶片含氮测定仪导出数据'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard8',title:'监测点田管记录',
                                            grandchilds:[
                                                 {index: 'crop_01_01_table_06_01',title: '水稻监测点水分管理记录表'},
                                                {index: 'crop_01_01_table_06_02',title: '水稻监测点化除管理记录表'},
                                                {index: 'crop_01_01_table_06_03',title: '水稻监测点病虫害防治记录表'},
                                                {index: 'crop_01_01_table_06_04',title: '水稻监测点施肥管理记录表'},
                                                {index: 'crop_01_01_table_06_05',title: '水稻监测点化学调节剂使用记录表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard9',title:'全县面上测产相关进度监测',
                                            grandchilds:[
                                                {index: 'crop_01_01_table_07_01',title: '监测县(市、区)水稻单季稻苗情监测预测产表'},
                                                {index: 'crop_01_01_table_07_02',title: '监测县(市、区)水稻单季稻收获进度调查'},
                                                {index: 'crop_01_01_table_07_03',title: '监测县(市、区)水稻单季稻测产表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard10',title:'全县生产效益调查',
                                            grandchilds:[
                                                 {index:'crop_01_01_table_08_01',title:'监测县(市、区)水稻生产成本效益统计表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard11',title:'全县气象及自然灾害调查',
                                            grandchilds:[
                                                {index: 'crop_01_01_table_09_01',title: '监测县(市、区)气象资料列表'},
                                                {index: 'crop_01_01_table_09_02',title: '年度监测县(市、区)抽穗期前、后各15天气象资料'},
                                                {index: 'crop_01_01_table_09_03',title: '监测县(市、区)水稻倒伏灾害表'},
                                                {index: 'crop_01_01_table_09_04',title: '监测县(市、区)水稻低温冷害表'},
                                                {index: 'crop_01_01_table_09_05',title: '监测县(市、区)水稻高温热害表'},
                                                {index: 'crop_01_01_table_09_06',title: '监测县(市、区)水稻涝害调查表'},
                                                {index: 'crop_01_01_table_09_07',title: '水稻监测点洪涝灾害表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard12',title:'监测点灌浆速率调查表',
                                            grandchilds:[
                                                {index: 'crop_01_01_table_10_01',title: '水稻监测点灌浆速率调查表'},
                                            ]
                                        }  
                                    ]
                            },
                            {icon:'el-icon-star-on',index:'dashboard01',title:'二季稻',
                                     childs:[
                                        {
                                        icon:'el-icon-star-on',index:'dashboard02',title:'全县生产概况',                                    
                                        grandchilds:
                                            [
                                                {index:'crop_01_02_table_01_01',title:'监测县(市、区)水稻栽插概况调查表'},
                                                {index:'crop_01_02_table_01_02',title:'监测县(市、区)水稻生产主推品种调查表'},
                                            ]
                                        },
                                        {icon:'el-icon-star-on',index:'dashboard03',title:'监测点建档卡',
                                        grandchilds:[
                                            {index:'crop_01_02_table_02_01',title:'水稻监测点建档卡'},
                                            {index:'crop_01_02_table_02_02',title:'水稻监测点汇总表'},
                                        ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard04',title:'全县面上生产调度',
                                            grandchilds:[
                                                 {index:'crop_01_02_table_03_01',title:'监测县(市、区)水稻播栽进度调查表(早稻)'},
                                                {index:'crop_01_02_table_03_02',title:'监测县(市、区)水稻苗情评价动态列表(晚稻)'},
                                                {index:'crop_01_02_table_03_03',title:'监测县(市、区)水稻苗情评价动态列表(早稻-移栽)'},
                                                {index:'crop_01_02_table_03_04',title:'监测县(市、区)水稻苗情评价动态列表(早稻-直播)'},
                                                {index:'crop_01_02_table_03_05',title:'监测县(市、区)水稻苗情评价动态列表(晚稻-移栽)'},
                                                {index:'crop_01_02_table_03_06',title:'监测县(市、区)水稻苗情评价动态列表(晚稻-直播)'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard05',title:'监测点生产进度调查',
                                            grandchilds:[
                                                {index:'crop_01_02_table_04_01',title:'监测县(市、区)中稻生育进度调查表（移栽稻）'},
                                                {index:'crop_01_02_table_04_02',title:'监测县(市、区)直播稻生育进度调查表（直播稻）'},
                                                {index:'crop_01_02_table_04_03',title:'水稻监测点茎蘖数叶面积构成表'},
                                                {index:'crop_01_02_table_04_04',title:'水稻监测点苗情生育期统计表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard06',title:'叶片参数自动测定',
                                            grandchilds:[
                                                  {index:'crop_01_02_table_05_01',title: '叶片含氮测定仪导出数据'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard07',title:'监测点田管记录',
                                            grandchilds:[
                                               {index: 'crop_01_02_table_06_01',title: '水稻监测点水分管理记录表'},
                                                {index: 'crop_01_02_table_06_02',title: '水稻监测点化除管理记录表'},
                                                {index: 'crop_01_02_table_06_03',title: '水稻监测点病虫害防治记录表'},
                                                {index: 'crop_01_02_table_06_04',title: '水稻监测点施肥管理记录表'},
                                                {index: 'crop_01_02_table_06_05',title: '水稻监测点化学调节剂使用记录表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard08',title:'全县面上测产相关进度监测',
                                            grandchilds:[
                                                 {index: 'crop_01_02_table_07_01',title: '监测县(市、区)水稻单季稻苗情监测预测产表'},
                                                {index: 'crop_01_02_table_07_02',title: '监测县(市、区)水稻双季稻收获进度调查（早稻）'},
                                                {index: 'crop_01_02_table_07_03',title: '监测县(市、区)水稻双季稻收获进度调查（晚稻）'},
                                                {index: 'crop_01_02_table_07_04',title: '监测县(市、区)水稻单季稻测产表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard09',title:'全县生产效益调查',
                                            grandchilds:[
                                                 {index:'crop_01_02_table_08_01',title:'监测县(市、区)水稻生产成本效益统计表(早稻)'},
                                                {index:'crop_01_02_table_08_02',title:'监测县(市、区)水稻生产成本效益统计表(晚稻)'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard010',title:'全县气象及自然灾害调查',
                                            grandchilds:[
                                                {index: 'crop_01_02_table_09_01',title: '监测县(市、区)气象资料列表'},
                                                {index: 'crop_01_02_table_09_02',title: ' 年度监测县(市、区)抽穗期前、后各15天气象资料'},
                                                {index: 'crop_01_02_table_09_03',title: '监测县(市、区)水稻倒伏灾害表'},
                                                {index: 'crop_01_02_table_09_04',title: '监测县(市、区)水稻低温冷害表'},
                                                {index: 'crop_01_02_table_09_05',title: '监测县(市、区)水稻高温热害表'},
                                                {index: 'crop_01_02_table_09_06',title: '监测县(市、区)水稻涝害调查表'},
                                                {index: 'crop_01_02_table_09_07',title: '水稻监测点洪涝灾害表'},
                                            ]
                                        },
                                        {
                                            icon:'el-icon-star-on',index:'dashboard011',title:'监测点灌浆速率调查表',
                                            grandchilds:[
                                                 {index: 'crop_01_02_table_10_01',title: '水稻监测点灌浆速率调查表'},
                                            ]
                                        }  
                                    ]
                            }
                            ],
                            // subs:[
                            //     {icon:'el-icon-star-on',index:'tabHome',title:'二季稻',childs:[{
                            //         icon:'el-icon-star-on',index:'tabHome',title:'全县生产概况',
                            //         grandchilds:
                            //         []
                            //         }]
                            //     }
                            // ]
                    },
                        { icon: 'el-icon-star-on',index: 'dashboard012',title: '数据退还',
                        subs:[
                            {icon:'el-icon-star-on',index:'/traceBackData',title:'退还列表'}
                        ]
                        
                    },
                    ];
                    break;
                case '1':
                    items=[
                        //{ icon: 'el-icon-star-on',index: 'dashboard',title: '首页'},
                        { icon: 'el-icon-star-on',index: 'dashboard',title: '水稻农情',
                        subs:[
                              { icon: 'el-icon-star-on',index: 'dashboard1',title: '一季稻',
                                childs:[{icon: 'el-icon-star-on',index: 'dashboard1-1',title: '全县生产概况',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable11',title: '监测县(市、区)水稻栽插概况调查表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable12',title: '监测县(市、区)水稻生产主推品种调查表'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-2',title: '监测点建档卡',
                                        grandchilds:[
                                                     {icon: 'el-icon-star-on',index: 'riceTable22',title: '水稻监测点汇总表'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-3',title: '全县面上生产调度',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable31',title: '监测县(市、区)水稻播栽进度调查表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable32',title: '监测县(市、区)水稻苗情评价动态列表（育秧）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable33',title: '监测县(市、区)水稻苗情评价动态列表（直播）'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-4',title: '监测点生产进度调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable41',title: '监测县(市、区)中稻生育进度调查表（移栽稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable42',title: '监测县(市、区)直播稻生育进度调查表（直播稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable43',title: '水稻监测点茎蘖数叶面积构成表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable44',title: '水稻监测点苗情生育期统计表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-5',title: '叶片参数自动测定',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable51',title: '叶片含氮测定仪导出数据'},
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-6',title: '监测点田管记录',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable61',title: '水稻监测点水分管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable62',title: '水稻监测点化除管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable63',title: '水稻监测点病虫害防治记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable64',title: '水稻监测点施肥管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable65',title: '水稻监测点化学调节剂使用记录表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-7',title: '全县面上测产相关进度监测',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable71',title: '监测县(市、区)水稻单季稻苗情监测预测产表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable72',title: '监测县(市、区)水稻单季稻收获进度调查'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable73',title: '监测县(市、区)水稻单季稻测产表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-8',title: '全县生产效益调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable81',title: '监测县(市、区)水稻生产成本效益统计表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-9',title: '全县气象及自然灾害调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable91',title: '监测县(市、区)气象资料列表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable92',title: '监测县(市、区)抽穗期前、后各15天气象资料'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable93',title: '监测县(市、区)水稻倒伏灾害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable94',title: '监测县(市、区)水稻低温冷害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable95',title: '监测县(市、区)水稻高温热害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable96',title: '监测县(市、区)水稻涝害调查表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTable97',title: '水稻监测点洪涝灾害表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard1-10',title: '监测点灌浆速率调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTable101',title: '水稻监测点灌浆速率调查表'}]},

                                        ]
                              },
                              { icon: 'el-icon-star-on',index: 'dashboard2',title: '双季稻',
                                childs:[{icon: 'el-icon-star-on',index: 'dashboard2-1',title: '全县生产概况',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo11',title: '监测县(市、区)水稻栽插概况调查表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo12',title: '监测县(市、区)水稻生产主推品种调查表'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-2',title: '监测点建档卡',
                                        grandchilds:[
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo22',title: '水稻监测点汇总表'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-3',title: '全县面上生产调度',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo31',title: '监测县(市、区)水稻播栽进度调查表（早稻)'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo32',title: '监测县(市、区)水稻播栽进度调查表（晚稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo33',title: '监测县(市、区)水稻苗情评价动态列表（早稻育秧）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo34',title: '监测县(市、区)水稻苗情评价动态列表（早稻直播）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo35',title: '监测县(市、区)水稻苗情评价动态列表（晚稻育秧）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo36',title: '监测县(市、区)水稻苗情评价动态列表（晚稻直播）'}]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-4',title: '监测点生产进度调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo41',title: '监测县(市、区)中稻生育进度调查表（移栽稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo42',title: '监测县(市、区)直播稻生育进度调查表（直播稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo43',title: '水稻监测点茎蘖数叶面积构成表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo44',title: '水稻监测点苗情生育期统计表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-5',title: '叶片参数自动测定',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo51',title: '叶片含氮测定仪导出数据'},
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-6',title: '监测点田管记录',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo61',title: '水稻监测点水分管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo62',title: '水稻监测点化除管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo63',title: '水稻监测点病虫害防治记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo64',title: '水稻监测点施肥管理记录表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo65',title: '水稻监测点化学调节剂使用记录表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-7',title: '全县面上测产相关进度监测',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo71',title: '监测县(市、区)水稻双季稻苗情监测预测产表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo72',title: '监测县(市、区)水稻双季稻收获进度调查（早稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo73',title: '监测县(市、区)水稻双季稻收获进度调查（晚稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo74',title: '监测县(市、区)水稻双季稻测产表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-8',title: '全县生产效益调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo81',title: '监测县(市、区)水稻生产成本效益统计表（早稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo82',title: '监测县(市、区)水稻生产成本效益统计表（晚稻）'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-9',title: '全县气象及自然灾害调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo91',title: '监测县(市、区)气象资料列表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo92',title: '监测县(市、区)抽穗期前、后各15天气象资料'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo93',title: '监测县(市、区)水稻倒伏灾害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo94',title: '监测县(市、区)水稻低温冷害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo95',title: '监测县(市、区)水稻高温热害表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo96',title: '监测县(市、区)水稻涝害调查表'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo97',title: '水稻监测点洪涝灾害表'}
                                                     ]},
                                        {icon: 'el-icon-star-on',index: 'dashboard2-10',title: '监测点灌浆速率调查',
                                        grandchilds:[{icon: 'el-icon-star-on',index: 'riceTableTwo101',title: '水稻监测点灌浆速率调查表（早稻）'},
                                                     {icon: 'el-icon-star-on',index: 'riceTableTwo102',title: '水稻监测点灌浆速率调查表（晚稻）'}
                                                     ]},

                                        ]
                              },
                          ]
                        //   subs:[
                        //       { icon: 'el-icon-star-on',index: 'dashboard',title: '田间基本情况'},
                        //       { icon: 'el-icon-star-on',index: 'dashboard',title: '田间生育情况'},
                        //       { icon: 'el-icon-star-on',index: 'dashboard',title: '主要气象灾害'},
                        //       { icon: 'el-icon-star-on',index: 'dashboard',title: '预测产和测产'},
                        //   ]
                        },
                        // { icon: 'el-icon-star-on',index: 'dashboard',title: '研究报告'},
                        // { icon: 'el-icon-star-on',index: 'dashboard',title: '苗情测控大屏'},
                    ];
                    break;
                case '3':
                    items=[
                        { icon: 'el-icon-star-on',index: 'person_review',title: '人员信息审核'},
                    ];
                    break;
            }
            //将生成好的菜单复制到data中
            this.items = items;
            this.reload();
       }

   },
   mounted(){
       this.generateItems();
   }
}
</script>

<style>
</style>


