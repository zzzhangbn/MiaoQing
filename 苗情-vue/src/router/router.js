import Vue from 'vue';
import Router from 'vue-router';
import adminSystemRouter from '@/views/admin/system/router'
import pagesRouter from '@/views/pages/router'
import riceRouter from '@/views/rice/router'
// import { import } from 'mathjs';
import xrouter from '@/views/main/router';

Vue.use(Router);
export default new Router({
    mode:'hash',
    // routes
    routes: [
        ...adminSystemRouter,
        ...pagesRouter,
        ...riceRouter,
        ...xrouter,
        {
            path:'/',
            redirect:'/index',
            component: resolve => require(['@/components/layout/Index.vue'], resolve),
            children:[
                    {
                        path:'/index',
                        component:resolve => require(['../views/pages/index.vue'], resolve),
                    },

                    

            ]
        },
        {
            path:'/',
            redirect:'/home',
            component:resolve=>require(['@/components/layout/Home.vue'],resolve),
            meta:{title:'自述文件'},
            children:[
                
                    {
                        path: '/crop_01_01_table_01_01',
                        // redirect:'/table1',
                        component: resolve => require(['../views/main/table1.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻栽插概况调查表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_01_02',
                        component: resolve => require(['../views/main/table2.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻生产主推品种调查表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_02_01',
                        component: resolve => require(['../views/main/table3.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点建档卡',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_02_02',
                        component: resolve => require(['../views/main/table4.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点汇总表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_03_01',
                        component: resolve => require(['../views/main/table5.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻播栽进度调查表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_03_02',
                        component: resolve => require(['../views/main/table0302.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(移栽)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_03_03',
                        component: resolve => require(['../views/main/table0303.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(直播)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_04_01',
                        component: resolve => require(['../views/main/table0401.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)中稻生育进度调查表（移栽稻）',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_04_02',
                        component: resolve => require(['../views/main/table0402.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)直播稻生育进度调查表（直播稻）',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_04_03',
                        component: resolve => require(['../views/main/table0403.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点茎蘖数叶面积构成表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_01_table_04_04',
                        component: resolve => require(['../views/main/table0404.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点苗情生育期统计表',
                            requireAuth:true    
                    }                   
                    },
                    {
                        path: '/crop_01_01_table_08_01',
                        component: resolve => require(['../views/main/table0801.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻生产成本效益统计表',
                            requireAuth:true    
                    }                   
                    },
                    // {
                    //     path: '/crop_01_02_table_08_02',
                    //     component: resolve => require(['../views/main/tableTwo0802.vue'], resolve),
                    //     meta: { 
                    //         title: '年度监测县(市、区)水稻生产成本效益统计表',
                    //         requireAuth:true    
                    // }                   
                    // },
                    // {
                    //     path: '/crop_01_02_table_08_01',
                    //     component: resolve => require(['../views/main/tableTwo0801.vue'], resolve),
                    //     meta: { 
                    //         title: '年度监测县(市、区)水稻生产成本效益统计表',
                    //         requireAuth:true    
                    // }                   
                    // },
                    {
                        path: '/crop_01_02_table_04_04',
                        component: resolve => require(['../views/main/tableTwo0404.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点苗情生育期统计表',
                            requireAuth:true    
                    }                   
                    },
                    {
                        path: '/crop_01_02_table_04_03',
                        component: resolve => require(['../views/main/tableTwo0403.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点茎蘖数叶面积构成表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_04_02',
                        component: resolve => require(['../views/main/tableTwo0402.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)直播稻生育进度调查表（直播稻）',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_04_01',
                        component: resolve => require(['../views/main/tableTwo0401.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)中稻生育进度调查表（移栽稻）',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_06',
                        component: resolve => require(['../views/main/tableTwo0306.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻-直播)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_05',
                        component: resolve => require(['../views/main/tableTwo0305.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻-移栽)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_04',
                        component: resolve => require(['../views/main/tableTwo0304.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(早稻-直播)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_03',
                        component: resolve => require(['../views/main/tableTwo0303.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(早稻-移栽)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_02',
                        component: resolve => require(['../views/main/tableTwo0302.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_03_01',
                        component: resolve => require(['../views/main/tableTwo5.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻播栽进度调查表(早稻)',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_02_02',
                        component: resolve => require(['../views/main/tableTwo4.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点汇总表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_02_01',
                        component: resolve => require(['../views/main/tableTwo3.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点建档卡',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_01_02',
                        component: resolve => require(['../views/main/tableTwo2.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻生产主推品种调查表',
                            requireAuth:true    
                    }
                    },
                    {
                        path: '/crop_01_02_table_01_01',
                        component: resolve => require(['../views/main/tableTwo1.vue'], resolve),
                        meta: { 
                            title: '年度监测县(市、区)水稻栽插概况调查表',
                            requireAuth:true    
                    },
                    
                    },
                    {
                        path: '/crop_01_01_table_05_01',
                        component: resolve => require(['../views/main/table0501.vue'], resolve),
                        meta: {
                            title: '叶片含氮测定仪导出数据',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_06_01',
                        component: resolve => require(['../views/main/table0601.vue'], resolve),
                        meta: { 
                            title: '年度水稻监测点水分管理记录表',
                            requireAuth:true    
                        }
                    },
                    {
                        path: '/crop_01_01_table_06_02',
                        component: resolve => require(['../views/main/table0602.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点化除管理记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_06_03',
                        component: resolve => require(['../views/main/table0603.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点病虫害防治记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_06_04',
                        component: resolve => require(['../views/main/table0604.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点施肥管理记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_06_05',
                        component: resolve => require(['../views/main/table0605.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点化学调节剂使用记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_07_01',
                        component: resolve => require(['../views/main/table0701.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻单季稻苗情监测预测产表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_07_02',
                        component: resolve => require(['../views/main/table0702.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻单季稻收获进度调查',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_07_03',
                        component: resolve => require(['../views/main/table0703.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻单季稻测产表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_01',
                        component: resolve => require(['../views/main/table0901.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)气象资料列表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_02',
                        component: resolve => require(['../views/main/table0902.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)抽穗期前、后各15天气象资料',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_03',
                        component: resolve => require(['../views/main/table0903.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻倒伏灾害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_04',
                        component: resolve => require(['../views/main/table0904.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻低温冷害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_05',
                        component: resolve => require(['../views/main/table0905.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻高温热害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_06',
                        component: resolve => require(['../views/main/table0906.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻涝害调查表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_09_07',
                        component: resolve => require(['../views/main/table0907.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点洪涝灾害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_01_table_10_01',
                        component: resolve => require(['../views/main/table1001.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点灌浆速率调查表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_05_01',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0501.vue'], resolve),
                        meta: {
                            title: '叶片含氮测定仪导出数据',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_06_01',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0601.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点水分管理记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_06_02',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0602.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点化除管理记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_06_03',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0603.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点病虫害防治记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_06_04',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0604.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点施肥管理记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_06_05',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0605.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点化学调节剂使用记录表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_07_01',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0701.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻双季稻苗情监测预测产表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_07_02',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0702.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻双季稻收获进度调查（早稻）',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_07_03',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0703.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻双季稻收获进度调查（晚稻）',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_07_04',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0704.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻双季稻测产表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_01',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0901.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)气象资料列表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_02',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0902.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)抽穗期前、后各15天气象资料',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_03',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0903.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻倒伏灾害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_04',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0904.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻低温冷害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_05',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0905.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)高温热害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_06',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0906.vue'], resolve),
                        meta: {
                            title: '年度监测县(市、区)水稻涝害调查表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_09_07',
                        component: resolve => require(['../views/main/twoSeason/tabletwo0907.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点洪涝灾害表',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/crop_01_02_table_10_01',
                        component: resolve => require(['../views/main/twoSeason/tabletwo1001.vue'], resolve),
                        meta: {
                            title: '年度水稻监测点灌浆速率调查表（早稻）',
                            requireAuth:true
                        }
                    },
                    {
                        path: '/traceBackData',
                        component: resolve => require(['../views/main/traceBackData.vue'], resolve),
                        meta: {
                            title: '数据退还',
                            requireAuth:true
                        }
                    }
                
            ]
        },
        // {
        //     path:'/',
        //     redirect:'/table2',
        //     component: resolve => require(['@/components/layout/Home.vue'], resolve),
        //     children:[
        //             {
        //                 path:'/table2',
        //                 component:resolve => require(['@/views/main/table2.vue'], resolve),
        //             },
        //     ]
        // },
        
        {
            path:'/login',
            component:resolve => require(['../views/login/login.vue'], resolve),
        },
        
    ]
})
