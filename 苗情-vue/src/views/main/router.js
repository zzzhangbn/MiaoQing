export default [
    {
        path: '/',
        component: resolve => require(['../../components/layout/Home.vue'], resolve),
        meta: { title: '自述文件' },
        children: [
            {
                name: '/tableTwoMain',
                path: '/tableTwoMain',
                component: resolve => require(['./tableTwoMain.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/tablemain',
                component: resolve => require(['./tablemain.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            }, {
                path: '/crop_01_01_table_01_01',
                component: resolve => require(['./table1.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_01_02',
                component: resolve => require(['./table2.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_02_01',
                component: resolve => require(['./table3.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_02_02',
                component: resolve => require(['./table4.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_03_01',
                component: resolve => require(['./table5.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_03_02',
                component: resolve => require(['./table0302.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_03_03',
                component: resolve => require(['./table0303.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_04_01',
                component: resolve => require(['./table0401.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_04_02',
                component: resolve => require(['./table0402.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_04_03',
                component: resolve => require(['./table0403.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_04_04',
                name: '/crop_01_01_table_04_04',
                component: resolve => require(['./table0404.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_01_table_08_01',
                component: resolve => require(['./table0801.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_02_table_08_02',
                component: resolve => require(['../main/twoSeason/tableTwo0802.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻生产成本效益统计表',
                    requireAuth:true    
            }                   
            },
            {
                path: '/crop_01_02_table_04_04',
                component: resolve => require(['../main/twoSeason/tableTwo0404.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻生产成本效益统计表',
                    requireAuth:true    
            }                   
            },
            {
                path: '/tableTwo0801',
                name: '/tableTwo0801',
                component: resolve => require(['../main/twoSeason/tableTwo0801.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻生产成本效益统计表',
                    requireAuth:true    
            }                   
            },
            {
                path: '/tableTwo0802',
                name: '/tableTwo0802',
                component: resolve => require(['../main/twoSeason/tableTwo0802.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻生产成本效益统计表',
                    requireAuth:true    
            }                   
            },
            {
                name: '/tableTwo0404',
                path: '/tableTwo0404',
                component: resolve => require(['../main/twoSeason/tableTwo0404.vue'], resolve),
                meta: { 
                    title: '年度水稻监测点苗情生育期统计表',
                    requireAuth:true    
            }                   
            },
            {
                path: '/crop_01_02_table_04_03',
                component: resolve => require(['../main/twoSeason/tableTwo0403.vue'], resolve),
                meta: { 
                    title: '年度水稻监测点茎蘖数叶面积构成表',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_04_02',
                component: resolve => require(['../main/twoSeason/tableTwo0402.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)直播稻生育进度调查表（直播稻）',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_04_01',
                component: resolve => require(['../main/twoSeason/tableTwo0401.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)中稻生育进度调查表（移栽稻）',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_06',
                component: resolve => require(['../main/twoSeason/tableTwo0306.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻-直播)',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_05',
                component: resolve => require(['../main/twoSeason/tableTwo0305.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻-移栽)',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_04',
                component: resolve => require(['../main/twoSeason/tableTwo0304.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻苗情评价动态列表(早稻-直播)',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_03',
                component: resolve => require(['../main/twoSeason/tableTwo0303.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻苗情评价动态列表(早稻-移栽)',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_02',
                component: resolve => require(['../main/twoSeason/tableTwo0302.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻苗情评价动态列表(晚稻)',
                    requireAuth:true    
            }
            },
            {
                path: '/crop_01_02_table_03_01',
                component: resolve => require(['../main/twoSeason/tableTwo0301.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻播栽进度调查表(早稻)',
                    requireAuth:true    
            }
            },
            {
                name: '/crop_01_02_table_01_01',
                path: '/crop_01_02_table_01_01',
                component: resolve => require(['../main/twoSeason/tableTwo0101.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻栽插概况调查表',
                    requireAuth:true    
            }
            },
            {
                name: '/crop_01_02_table_01_02',
                path: '/crop_01_02_table_01_02',
                component: resolve => require(['../main/twoSeason/tableTwo0102.vue'], resolve),
                meta: { 
                    title: '年度监测县(市、区)水稻生产主推品种调查表',
                    requireAuth:true    
            }
            },
            {
                path: '/processTwo0401',
                name: '/processTwo0401',
                component: resolve => require(['../main/twoSeason/processTwo0401.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0402',
                name: '/processTwo0402',
                component: resolve => require(['../main/twoSeason/processTwo0402.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0403',
                name: '/processTwo0403',
                component: resolve => require(['../main/twoSeason/processTwo0403.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0301',
                name: '/processTwo0301',
                component: resolve => require(['../main/twoSeason/processTwo0301.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0302',
                name: '/processTwo0302',
                component: resolve => require(['../main/twoSeason/processTwo0302.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0303',
                name: '/processTwo0303',
                component: resolve => require(['../main/twoSeason/processTwo0303.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0304',
                name: '/processTwo0304',
                component: resolve => require(['../main/twoSeason/processTwo0304.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0305',
                name: '/processTwo0305',
                component: resolve => require(['../main/twoSeason/processTwo0305.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/processTwo0306',
                name: '/processTwo0306',
                component: resolve => require(['../main/twoSeason/processTwo0306.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },


            {
                path: '/traceBackData',
                component: resolve => require(['./traceBackData.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/index',
                component: resolve => require(['./index.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/index1',
                component: resolve => require(['./index1.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/process0401',
                name: '/process0401',
                component: resolve => require(['./process0401.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/process0402',
                name: '/process0402',
                component: resolve => require(['./process0402.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/process0403',
                name: '/process0403',
                component: resolve => require(['./process0403.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/process0301',
                name: '/process0301',
                component: resolve => require(['./process0301.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/process0302',
                name: '/process0302',
                component: resolve => require(['./process0302.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/process0303',
                name: '/process0303',
                component: resolve => require(['./process0303.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_01',
                name: '/crop_01_01_table_09_01',
                component: resolve => require(['./table0901.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_02',
                name: '/crop_01_01_table_09_02',
                component: resolve => require(['./table0902.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_03',
                name: '/crop_01_01_table_09_03',
                component: resolve => require(['./table0903.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_04',
                name: '/crop_01_01_table_09_04',
                component: resolve => require(['./table0904.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_05',
                name: '/crop_01_01_table_09_05',
                component: resolve => require(['./table0905.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_06',
                name: '/crop_01_01_table_09_06',
                component: resolve => require(['./table0906.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_01_table_09_07',
                name: '/crop_01_01_table_09_07',
                component: resolve => require(['./table0907.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_01',
                name: '/crop_01_02_table_09_01',
                component: resolve => require(['./tabletwo0901.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_02',
                name: '/crop_01_02_table_09_02',
                component: resolve => require(['./tabletwo0902.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_03',
                name: '/crop_01_02_table_09_03',
                component: resolve => require(['./tabletwo0903.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_04',
                name: '/crop_01_02_table_09_04',
                component: resolve => require(['./tabletwo0904.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_05',
                name: '/crop_01_02_table_09_05',
                component: resolve => require(['./tabletwo0905.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_06',
                name: '/crop_01_02_table_09_06',
                component: resolve => require(['./tabletwo0906.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_07',
                name: '/crop_01_02_table_09_07',
                component: resolve => require(['./tabletwo0907.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/tablemain',
                path: '/tablemain',
                component: resolve => require(['./tablemain.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0501',
                path: '/process0501',
                component: resolve => require(['./process0501.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0601',
                path: '/process0601',
                component: resolve => require(['./process0601.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0602',
                path: '/process0602',
                component: resolve => require(['./process0602.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0603',
                path: '/process0603',
                component: resolve => require(['./process0603.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0604',
                path: '/process0604',
                component: resolve => require(['./process0604.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0605',
                path: '/process0605',
                component: resolve => require(['./process0605.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0701',
                path: '/process0701',
                component: resolve => require(['./process0701.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0702',
                path: '/process0702',
                component: resolve => require(['./process0702.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process0703',
                path: '/process0703',
                component: resolve => require(['./process0703.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process1001',
                path: '/process1001',
                component: resolve => require(['./process1001.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/table0201',
                path: '/table0201',
                component: resolve => require(['./table0201.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/table0602',
                path: '/table0602',
                component: resolve => require(['./table0602.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/table0801',
                path: '/table0801',
                component: resolve => require(['./table0801.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/table0907',
                path: '/table0907',
                component: resolve => require(['./table0907.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/main',
                path: '/main',
                component: resolve => require(['./main.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/0riceTable0501',
                path: '/0riceTable0501',
                component: resolve => require(['./riceTable51.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable61',
                path: '/0riceTable61',
                component: resolve => require(['./riceTable61.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable65',
                path: '/0riceTable65',
                component: resolve => require(['./riceTable65.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable62',
                path: '/0riceTable62',
                component: resolve => require(['./riceTable62.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable63',
                path: '/0riceTable63',
                component: resolve => require(['./riceTable63.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable64',
                path: '/0riceTable64',
                component: resolve => require(['./riceTable64.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable71',
                path: '/0riceTable71',
                component: resolve => require(['./riceTable71.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable72',
                path: '/0riceTable72',
                component: resolve => require(['./riceTable72.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable73',
                path: '/0riceTable73',
                component: resolve => require(['./riceTable73.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable81',
                path: '/0riceTable81',
                component: resolve => require(['./riceTable81.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo81',
                path: '/0riceTableTwo81',
                component: resolve => require(['../main/twoSeason/riceTableTwo81.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo82',
                path: '/0riceTableTwo82',
                component: resolve => require(['../main/twoSeason/riceTableTwo82.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable91',
                path: '/0riceTable91',
                component: resolve => require(['./riceTable91.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable92',
                path: '/0riceTable92',
                component: resolve => require(['./riceTable92.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable93',
                path: '/0riceTable93',
                component: resolve => require(['./riceTable93.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable94',
                path: '/0riceTable94',
                component: resolve => require(['./riceTable94.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable95',
                path: '/0riceTable95',
                component: resolve => require(['./riceTable95.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable96',
                path: '/0riceTable96',
                component: resolve => require(['./riceTable96.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable97',
                path: '/0riceTable97',
                component: resolve => require(['./riceTable97.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable101',
                path: '/0riceTable101',
                component: resolve => require(['./riceTable101.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable11',
                path: '/0riceTable11',
                component: resolve => require(['./riceTable11.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable12',
                path: '/0riceTable12',
                component: resolve => require(['./riceTable12.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable31',
                path: '/0riceTable31',
                component: resolve => require(['./riceTable31.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable32',
                path: '/0riceTable32',
                component: resolve => require(['./riceTable32.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable33',
                path: '/0riceTable33',
                component: resolve => require(['./riceTable33.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable41',
                path: '/0riceTable41',
                component: resolve => require(['./riceTable41.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable42',
                path: '/0riceTable42',
                component: resolve => require(['./riceTable42.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable43',
                path: '/0riceTable43',
                component: resolve => require(['./riceTable43.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable44',
                path: '/0riceTable44',
                component: resolve => require(['./riceTable44.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable51',
                path: '/0riceTable51',
                component: resolve => require(['./riceTable51.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo41',
                path: '/0riceTableTwo41',
                component: resolve => require(['../main/twoSeason/riceTableTwo41.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo42',
                path: '/0riceTableTwo42',
                component: resolve => require(['../main/twoSeason/riceTableTwo42.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            // {
            //     name: '/riceTableTwo42new',
            //     path: '/riceTableTwo42new',
            //     component: resolve => require(['../rice/twoSeasonMessageView/riceTableTwo42.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            // {
            //     name: '/riceTableTwo42new',
            //     path: '/riceTableTwo42new',
            //     component: resolve => require(['../main/twoSeasonMessageView/riceTableTwo42new.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            {
                name: '/0riceTableTwo43',
                path: '/0riceTableTwo43',
                component: resolve => require(['../main/twoSeason/riceTableTwo43.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo44',
                path: '/0riceTableTwo44',
                component: resolve => require(['../main/twoSeason/riceTableTwo44.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo31',
                path: '/0riceTableTwo31',
                component: resolve => require(['../main/twoSeason/riceTableTwo31.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo32',
                path: '/0riceTableTwo32',
                component: resolve => require(['../main/twoSeason/riceTableTwo32.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo33',
                path: '/0riceTableTwo33',
                component: resolve => require(['../main/twoSeason/riceTableTwo33.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            }
            ,
            {
                name: '/0riceTableTwo34',
                path: '/0riceTableTwo34',
                component: resolve => require(['../main/twoSeason/riceTableTwo34.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo35',
                path: '/0riceTableTwo35',
                component: resolve => require(['../main/twoSeason/riceTableTwo35.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo36',
                path: '/0riceTableTwo36',
                component: resolve => require(['../main/twoSeason/riceTableTwo36.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTableTwo11',
                path: '/0riceTableTwo11',
                component: resolve => require(['../main/twoSeason/riceTableTwo11.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            // {
            //     name: '/0riceTableTwo11new',
            //     path: '/0riceTableTwo11new',
            //     component: resolve => require(['../main/twoSeasonMessageView/riceTableTwo11new.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            {
                name: '/0riceTableTwo12',
                path: '/0riceTableTwo12',
                component: resolve => require(['../main/twoSeason/riceTableTwo12.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
             /*****************************二季稻******添加开始***************************/
             {
                name: '/process020501',
                path: '/process020501',
                component: resolve => require(['./process020501.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020601',
                path: '/process020601',
                component: resolve => require(['./process020601.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020602',
                path: '/process020602',
                component: resolve => require(['./process020602.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020603',
                path: '/process020603',
                component: resolve => require(['./process020603.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020604',
                path: '/process020604',
                component: resolve => require(['./process020604.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020605',
                path: '/process020605',
                component: resolve => require(['./process020605.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020701',
                path: '/process020701',
                component: resolve => require(['./process020701.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020702',
                path: '/process020702',
                component: resolve => require(['./process020702.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/process020703',
                path: '/process020703',
                component: resolve => require(['./process020703.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_07_04',
                path: '/crop_01_02_table_07_04',
                component: resolve => require(['./tabletwo0704.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                path: '/crop_01_02_table_09_03',
                name: '/crop_01_02_table_09_03',
                component: resolve => require(['./tabletwo0903.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_04',
                name: '/crop_01_02_table_09_04',
                component: resolve => require(['./tabletwo0904.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_05',
                name: '/crop_01_02_table_09_05',
                component: resolve => require(['./tabletwo0905.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_06',
                name: '/crop_01_02_table_09_06',
                component: resolve => require(['./tabletwo0906.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_09_07',
                name: '/crop_01_02_table_09_07',
                component: resolve => require(['./tabletwo0907.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/process021001',
                path: '/process021001',
                component: resolve => require(['./process021001.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },{
                name: '/process021002',
                path: '/process021002',
                component: resolve => require(['./process021002.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            /*****************************二季稻*******添加结束******************************/
            /*****************************二季稻******历史开始***************************/
            {
                name: '/0riceTable0251',
                path: '/0riceTable0251',
                component: resolve => require(['./riceTable0251.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0261',
                path: '/0riceTable0261',
                component: resolve => require(['./riceTable0261.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0262',
                path: '/0riceTable0262',
                component: resolve => require(['./riceTable0262.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0263',
                path: '/0riceTable0263',
                component: resolve => require(['./riceTable0263.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0264',
                path: '/0riceTable0264',
                component: resolve => require(['./riceTable0264.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0265',
                path: '/0riceTable0265',
                component: resolve => require(['./riceTable0265.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0271',
                path: '/0riceTable0271',
                component: resolve => require(['./riceTable0271.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0272',
                path: '/0riceTable0272',
                component: resolve => require(['./riceTable0272.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0273',
                path: '/0riceTable0273',
                component: resolve => require(['./riceTable0273.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },{
                name: '/0riceTable0274',
                path: '/0riceTable0274',
                component: resolve => require(['./riceTable0274.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0291',
                path: '/0riceTable0291',
                component: resolve => require(['./riceTable0291.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0292',
                path: '/0riceTable0292',
                component: resolve => require(['./riceTable0292.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0293',
                path: '/0riceTable0293',
                component: resolve => require(['./riceTable0293.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0294',
                path: '/0riceTable0294',
                component: resolve => require(['./riceTable0294.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0295',
                path: '/0riceTable0295',
                component: resolve => require(['./riceTable0295.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0296',
                path: '/0riceTable0296',
                component: resolve => require(['./riceTable0296.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable0297',
                path: '/0riceTable0297',
                component: resolve => require(['./riceTable0297.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/0riceTable02101',
               path: '/0riceTable02101',
                component: resolve => require(['./riceTable02101.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            }, {
                name: '/0riceTable02102',
                path: '/0riceTable02102',
                component: resolve => require(['./riceTable02102.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            }, 
            /*****************************二季稻******历史结束***************************/

            /*****************************yijidao研究报告开始***************************/
            // {
            //     name: '/template11',
            //     path: '/template11',
            //     component: resolve => require(['./template11.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },

            // {
            //     name: '/template21',
            //     path: '/template21',
            //     component: resolve => require(['./template21.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },

            // {
            //     name: '/template3-11',
            //     path: '/template3-11',
            //     component: resolve => require(['./template3-11.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            
            // {
            //     name: '/template3-21',
            //     path: '/template3-21',
            //     component: resolve => require(['./template3-21.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },

            // {
            //     name: '/template3-31',
            //     path: 'template3-31',
            //     component: resolve => require(['./template3-31.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            
            // {
            //     name: '/template41',
            //     path: '/template41',
            //     component: resolve => require(['./template41.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            
            // {
            //     name: '/template51',
            //     path: '/template51',
            //     component: resolve => require(['./template51.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            
            {
                name: '/viewTemplateResearch',
                path: '/viewTemplateResearch',
                component: resolve => require(['./viewTemplateResearch.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/viewTemplateResearchTwo',
                path: '/viewTemplateResearchTwo',
                component: resolve => require(['./viewTemplateResearchTwo.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            // {
            //     name: '/templateZao_36',
            //     path: '/templateZao_36',
            //     component: resolve => require(['./templateZao_36.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },
            
            // {
            //     name: '/templateWan_54',
            //     path: '/templateWan_54',
            //     component: resolve => require(['./templateWan_54.vue'], resolve),
            //     meta: { requiresAuth: true,title:'2' }

            // },

            {
                path: '/crop_01_02_table_06_01',
                name: '/crop_01_02_table_06_01',
                component: resolve => require(['./tabletwo0601.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_06_02',
                name: '/crop_01_02_table_06_02',
                component: resolve => require(['./tabletwo0602.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_06_03',
                name: '/crop_01_02_table_06_03',
                component: resolve => require(['./tabletwo0603.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_06_04',
                name: '/crop_01_02_table_06_04',
                component: resolve => require(['./tabletwo0604.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_06_05',
                name: '/crop_01_02_table_06_05',
                component: resolve => require(['./tabletwo0605.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path: '/crop_01_02_table_05_01',
                name: '/crop_01_02_table_05_01',
                component: resolve => require(['./tabletwo0501.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/crop_01_02_table_07_01',
                path: '/crop_01_02_table_07_01',
                component: resolve => require(['./tabletwo0701.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_07_02',
                path: '/crop_01_02_table_07_02',
                component: resolve => require(['./tabletwo0702.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_07_03',
                path: '/crop_01_02_table_07_03',
                component: resolve => require(['./tabletwo0703.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_10_01',
                path: '/crop_01_02_table_10_01',
                component: resolve => require(['./tabletwo1001.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_10_02',
                path: '/crop_01_02_table_10_02',
                component: resolve => require(['./tabletwo1002.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },
            {
                name: '/crop_01_02_table_07_03',
                path: '/crop_01_02_table_07_03',
                component: resolve => require(['./tabletwo0703.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }

            },

            // 新增监测点信息查看路由
            {
                path:'/riceTable41_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable41_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable43_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable43_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable44_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable44_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable61_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable61_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable62_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable62_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable63_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable63_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable64_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable64_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable65_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable65_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable97_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable97_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable101_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable101_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable51_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable51_MessageView.vue'], resolve),
            },
            {
                path:'/riceTable81_MessageView',
                component: resolve => require(['../main/oneSeasonMessageView/riceTable81_MessageView.vue'], resolve),
            }






        ]
    }
]
