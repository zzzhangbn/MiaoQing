export default [
    {
       path:'/',
       component: resolve => require(['@/components/layout/Home.vue'], resolve),
       children:[
            // {
            //     path: '/dashboard',
            //     component: resolve => require(['../views/main/Dashboard.vue'], resolve),
            //     //component: resolve => require(['../views/\rice\oneSeason\firstTable\table1.vue'], resolve),
            //     meta: { 
            //         title: '首页',
            //         requireAuth:true    
            // }
            // },
            {
                path:'/weathershow',
                component: resolve => require(['../pages/weathershow/weathershow.vue'], resolve),
            },
            {
                path:'/testExcel',
                component: resolve => require(['./oneSeason/firstTable/test.vue'], resolve),
            },
            {
                path:'/audit',
                component: resolve => require(['./auditData/auditTable.vue'], resolve),
            },
            // {
            //     path:'/riceHome',
            //     component: resolve => require(['./riceHome.vue'], resolve),
            // },
            // {
            //     path:'/audit',
            //     component: resolve => require(['./auditData/auditHome.vue'], resolve),
            // },
            {
                path:'/auditPoint',
                component: resolve => require(['./auditData/auditPoint.vue'], resolve),
            },
            // {
            //     path:'/submitHome',
            //     component: resolve => require(['./auditData/submitHome.vue'], resolve),
            // },
            {
                path:'/submitHome',
                component: resolve => require(['./auditData/noSumbit.vue'], resolve),
            },
            {
                path:'/submitDetailPage',
                component: resolve => require(['./auditData/submitDetailPage.vue'], resolve),
            },
            {
                path:'/dataHome',
                component: resolve => require(['./oneSeason/dataHome.vue'], resolve),
            },
            {
                path:'/dataHome1',
                component: resolve => require(['./oneSeason/dataHome1.vue'], resolve),
            },
            {
                path:'/dataHome2',
                component: resolve => require(['./oneSeason/dataHome2.vue'], resolve),
            },
            //单季稻
            {
                path:'/riceTable11',
                component: resolve => require(['./oneSeason/firstTable/riceTable11.vue'], resolve),
            },
            //单季稻
            {
                path:'/riceTable11',
                component: resolve => require(['./oneSeason/firstTable/riceTable11.vue'], resolve),
            },
            {
                path:'/riceTable12',
                component: resolve => require(['./oneSeason/firstTable/riceTable12.vue'], resolve),
            },
            {
                path:'/riceTable22',
                component: resolve => require(['./oneSeason/firstTable/riceTable22.vue'], resolve),
            },
            {
                path:'/riceTable31',
                component: resolve => require(['./oneSeason/firstTable/riceTable31.vue'], resolve),
            },
            {
                path:'/riceTable32',
                component: resolve => require(['./oneSeason/firstTable/riceTable32.vue'], resolve),
            },
            {
                path:'/riceTable33',
                component: resolve => require(['./oneSeason/firstTable/riceTable33.vue'], resolve),
            },
            {
                path:'/riceTable41',
                component: resolve => require(['./oneSeason/firstTable/riceTable41.vue'], resolve),
            },
            {
                path:'/riceTable42',
                component: resolve => require(['./oneSeason/firstTable/riceTable42.vue'], resolve),
            },
            {
                path:'/riceTable43',
                component: resolve => require(['./oneSeason/firstTable/riceTable43.vue'], resolve),
            },
            {
                path:'/riceTable44',
                component: resolve => require(['./oneSeason/firstTable/riceTable44.vue'], resolve),
            },
            {
                path:'/riceTable51',
                component: resolve => require(['./oneSeason/firstTable/riceTable51.vue'], resolve),
            },
            {
                path:'/riceTable61',
                component: resolve => require(['./oneSeason/firstTable/riceTable61.vue'], resolve),
            },
            {
                path:'/riceTable62',
                component: resolve => require(['./oneSeason/firstTable/riceTable62.vue'], resolve),
            },
            {
                path:'/riceTable63',
                component: resolve => require(['./oneSeason/firstTable/riceTable63.vue'], resolve),
            },
            {
                path:'/riceTable64',
                component: resolve => require(['./oneSeason/firstTable/riceTable64.vue'], resolve),
            },
            {
                path:'/riceTable65',
                component: resolve => require(['./oneSeason/firstTable/riceTable65.vue'], resolve),
            },
            {
                path:'/riceTable71',
                component: resolve => require(['./oneSeason/firstTable/riceTable71.vue'], resolve),
            },
            {
                path:'/riceTable72',
                component: resolve => require(['./oneSeason/firstTable/riceTable72.vue'], resolve),
            },
            {
                path:'/riceTable73',
                component: resolve => require(['./oneSeason/firstTable/riceTable73.vue'], resolve),
            },
            {
                path:'/riceTable81',
                component: resolve => require(['./oneSeason/firstTable/riceTable81.vue'], resolve),
            },
            {
                path:'/riceTable91',
                component: resolve => require(['./oneSeason/firstTable/riceTable91.vue'], resolve),
            },
            {
                path:'/riceTable92',
                component: resolve => require(['./oneSeason/firstTable/riceTable92.vue'], resolve),
            },
            {
                path:'/riceTable93',
                component: resolve => require(['./oneSeason/firstTable/riceTable93.vue'], resolve),
            },
            {
                path:'/riceTable94',
                component: resolve => require(['./oneSeason/firstTable/riceTable94.vue'], resolve),
            },
            {
                path:'/riceTable95',
                component: resolve => require(['./oneSeason/firstTable/riceTable95.vue'], resolve),
            },
            {
                path:'/riceTable96',
                component: resolve => require(['./oneSeason/firstTable/riceTable96.vue'], resolve),
            },
            {
                path:'/riceTable97',
                component: resolve => require(['./oneSeason/firstTable/riceTable97.vue'], resolve),
            },
            {
                path:'/riceTable101',
                component: resolve => require(['./oneSeason/firstTable/riceTable101.vue'], resolve),
            },

            //双季稻
            {
                path:'/riceTableTwo11',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo11.vue'], resolve),
            },
            {
                path:'/riceTableTwo12',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo12.vue'], resolve),
            },
            {
                path:'/riceTableTwo22',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo22.vue'], resolve),
            },
            {
                path:'/riceTableTwo31',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo31.vue'], resolve),
            },
            {
                path:'/riceTableTwo32',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo32.vue'], resolve),
            },
            {
                path:'/riceTableTwo33',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo33.vue'], resolve),
            },
            {
                path:'/riceTableTwo34',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo34.vue'], resolve),
            },
            {
                path:'/riceTableTwo35',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo35.vue'], resolve),
            },
            {
                path:'/riceTableTwo36',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo36.vue'], resolve),
            },
            {
                path:'/riceTableTwo41',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo41.vue'], resolve),
            },
            {
                path:'/riceTableTwo42',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo42.vue'], resolve),
            },
            {
                path:'/riceTableTwo42new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo42new.vue'], resolve),
            },
            
            {
                path:'/riceTableTwo43',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo43.vue'], resolve),
            },
            {
                path:'/riceTableTwo43new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo43new.vue'], resolve),
            },
            {
                path:'/riceTableTwo44',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo44.vue'], resolve),
            },
            {
                path:'/riceTableTwo44new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo44new.vue'], resolve),
            },
            {
                path:'/riceTableTwo51',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo51.vue'], resolve),
            },
            {
                path:'/riceTableTwo51new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo51new.vue'], resolve),
            },
            {
                path:'/riceTableTwo61',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo61.vue'], resolve),
            },
            {
                path:'/riceTableTwo61new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo61new.vue'], resolve),
            },
            {
                path:'/riceTableTwo62',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo62.vue'], resolve),
            },
            {
                path:'/riceTableTwo62new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo62new.vue'], resolve),
            },
            {
                path:'/riceTableTwo63',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo63.vue'], resolve),
            },
            {
                path:'/riceTableTwo63new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo63new.vue'], resolve),
            },
            {
                path:'/riceTableTwo64',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo64.vue'], resolve),
            },
            {
                path:'/riceTableTwo64new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo64new.vue'], resolve),
            },
            {
                path:'/riceTableTwo65',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo65.vue'], resolve),
            },
            {
                path:'/riceTableTwo65new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo65new.vue'], resolve),
            },
            {
                path:'/riceTableTwo71',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo71.vue'], resolve),
            },
            {
                path:'/riceTableTwo72',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo72.vue'], resolve),
            },
            {
                path:'/riceTableTwo73',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo73.vue'], resolve),
            },
            {
                path:'/riceTableTwo74',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo74.vue'], resolve),
            },
            {
                path:'/riceTableTwo81',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo81.vue'], resolve),
            },
            {
                path:'/riceTableTwo81new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo81new.vue'], resolve),
            },
            {
                path:'/riceTableTwo82',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo82.vue'], resolve),
            },
            {
                path:'/riceTableTwo82new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo82new.vue'], resolve),
            },
            {
                path:'/riceTableTwo91',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo91.vue'], resolve),
            },
            {
                path:'/riceTableTwo92',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo92.vue'], resolve),
            },
            {
                path:'/riceTableTwo93',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo93.vue'], resolve),
            },
            {
                path:'/riceTableTwo94',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo94.vue'], resolve),
            },
            {
                path:'/riceTableTwo95',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo95.vue'], resolve),
            },
            {
                path:'/riceTableTwo96',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo96.vue'], resolve),
            },
            {
                path:'/riceTableTwo97',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo97.vue'], resolve),
            },
            {
                path:'/riceTableTwo97new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo97new.vue'], resolve),
            },
            {
                path:'/riceTableTwo101',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo101.vue'], resolve),
            },
            {
                path:'/riceTableTwo101new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo101new.vue'], resolve),
            },
            {
                path:'/riceTableTwo102',
                component: resolve => require(['./oneSeason/secondTable/riceTableTwo102.vue'], resolve),
            },
            {
                path:'/riceTableTwo102new',
                component: resolve => require(['./oneSeason/twoSeasonMessageView/riceTableTwo102new.vue'], resolve),
            },



            //审核
            {
                path:'/riceAuditTable11',
                component: resolve => require(['./auditData/firstTable/riceTable11.vue'], resolve),
            },
            {
                path:'/riceAuditTable12',
                component: resolve => require(['./auditData/firstTable/riceTable12.vue'], resolve),
            },
            {
                path:'/riceAuditTable22',
                component: resolve => require(['./auditData/firstTable/riceTable22.vue'], resolve),
            },
            {
                path:'/riceAuditTable31',
                component: resolve => require(['./auditData/firstTable/riceTable31.vue'], resolve),
            },
            {
                path:'/riceAuditTable32',
                component: resolve => require(['./auditData/firstTable/riceTable32.vue'], resolve),
            },
            {
                path:'/riceAuditTable33',
                component: resolve => require(['./auditData/firstTable/riceTable33.vue'], resolve),
            },
            {
                path:'/riceAuditTable41',
                component: resolve => require(['./auditData/firstTable/riceTable41.vue'], resolve),
            },
            {
                path:'/riceAuditTable42',
                component: resolve => require(['./auditData/firstTable/riceTable42.vue'], resolve),
            },
            {
                path:'/riceAuditTable43',
                component: resolve => require(['./auditData/firstTable/riceTable43.vue'], resolve),
            },
            {
                path:'/riceAuditTable44',
                component: resolve => require(['./auditData/firstTable/riceTable44.vue'], resolve),
            },
            {
                path:'/riceAuditTable51',
                component: resolve => require(['./auditData/firstTable/riceTable51.vue'], resolve),
            },
            {
                path:'/riceAuditTable61',
                component: resolve => require(['./auditData/firstTable/riceTable61.vue'], resolve),
            },
            {
                path:'/riceAuditTable62',
                component: resolve => require(['./auditData/firstTable/riceTable62.vue'], resolve),
            },
            {
                path:'/riceAuditTable63',
                component: resolve => require(['./auditData/firstTable/riceTable63.vue'], resolve),
            },
            {
                path:'/riceAuditTable64',
                component: resolve => require(['./auditData/firstTable/riceTable64.vue'], resolve),
            },
            {
                path:'/riceAuditTable65',
                component: resolve => require(['./auditData/firstTable/riceTable65.vue'], resolve),
            },
            {
                path:'/riceAuditTable71',
                component: resolve => require(['./auditData/firstTable/riceTable71.vue'], resolve),
            },
            {
                path:'/riceAuditTable72',
                component: resolve => require(['./auditData/firstTable/riceTable72.vue'], resolve),
            },
            {
                path:'/riceAuditTable73',
                component: resolve => require(['./auditData/firstTable/riceTable73.vue'], resolve),
            },
            {
                path:'/riceAuditTable81',
                component: resolve => require(['./auditData/firstTable/riceTable81.vue'], resolve),
            },
            {
                path:'/riceAuditTable91',
                component: resolve => require(['./auditData/firstTable/riceTable91.vue'], resolve),
            },
            {
                path:'/riceAuditTable92',
                component: resolve => require(['./auditData/firstTable/riceTable92.vue'], resolve),
            },
            {
                path:'/riceAuditTable93',
                component: resolve => require(['./auditData/firstTable/riceTable93.vue'], resolve),
            },
            {
                path:'/riceAuditTable94',
                component: resolve => require(['./auditData/firstTable/riceTable94.vue'], resolve),
            },
            {
                path:'/riceAuditTable95',
                component: resolve => require(['./auditData/firstTable/riceTable95.vue'], resolve),
            },
            {
                path:'/riceAuditTable96',
                component: resolve => require(['./auditData/firstTable/riceTable96.vue'], resolve),
            },
            {
                path:'/riceAuditTable97',
                component: resolve => require(['./auditData/firstTable/riceTable97.vue'], resolve),
            },
            {
                path:'/riceAuditTable101',
                component: resolve => require(['./auditData/firstTable/riceTable101.vue'], resolve),
            },

            //双季稻
            {
                path:'/riceAuditTableTwo11',
                component: resolve => require(['./auditData/secondTable/riceTableTwo11.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo12',
                component: resolve => require(['./auditData/secondTable/riceTableTwo12.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo22',
                component: resolve => require(['./auditData/secondTable/riceTableTwo22.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo31',
                component: resolve => require(['./auditData/secondTable/riceTableTwo31.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo32',
                component: resolve => require(['./auditData/secondTable/riceTableTwo32.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo33',
                component: resolve => require(['./auditData/secondTable/riceTableTwo33.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo34',
                component: resolve => require(['./auditData/secondTable/riceTableTwo34.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo35',
                component: resolve => require(['./auditData/secondTable/riceTableTwo35.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo36',
                component: resolve => require(['./auditData/secondTable/riceTableTwo36.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo41',
                component: resolve => require(['./auditData/secondTable/riceTableTwo41.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo42',
                component: resolve => require(['./auditData/secondTable/riceTableTwo42.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo43',
                component: resolve => require(['./auditData/secondTable/riceTableTwo43.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo44',
                component: resolve => require(['./auditData/secondTable/riceTableTwo44.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo51',
                component: resolve => require(['./auditData/secondTable/riceTableTwo51.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo61',
                component: resolve => require(['./auditData/secondTable/riceTableTwo61.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo62',
                component: resolve => require(['./auditData/secondTable/riceTableTwo62.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo63',
                component: resolve => require(['./auditData/secondTable/riceTableTwo63.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo64',
                component: resolve => require(['./auditData/secondTable/riceTableTwo64.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo65',
                component: resolve => require(['./auditData/secondTable/riceTableTwo65.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo71',
                component: resolve => require(['./auditData/secondTable/riceTableTwo71.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo72',
                component: resolve => require(['./auditData/secondTable/riceTableTwo72.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo73',
                component: resolve => require(['./auditData/secondTable/riceTableTwo73.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo74',
                component: resolve => require(['./auditData/secondTable/riceTableTwo74.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo81',
                component: resolve => require(['./auditData/secondTable/riceTableTwo81.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo82',
                component: resolve => require(['./auditData/secondTable/riceTableTwo82.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo91',
                component: resolve => require(['./auditData/secondTable/riceTableTwo91.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo92',
                component: resolve => require(['./auditData/secondTable/riceTableTwo92.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo93',
                component: resolve => require(['./auditData/secondTable/riceTableTwo93.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo94',
                component: resolve => require(['./auditData/secondTable/riceTableTwo94.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo95',
                component: resolve => require(['./auditData/secondTable/riceTableTwo95.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo96',
                component: resolve => require(['./auditData/secondTable/riceTableTwo96.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo97',
                component: resolve => require(['./auditData/secondTable/riceTableTwo97.vue'], resolve),
            },
            {
                path:'/riceAuditTableTwo101',
                component: resolve => require(['./auditData/secondTable/riceTableTwo101.vue'], resolve),
            },
            {
                path:'/riceTableTwo102',
                component: resolve => require(['./auditData/secondTable/riceTableTwo102.vue'], resolve),
            },

            
            {
                path:'/test',
                component: resolve => require(['./oneSeason/firstTable/table1.vue'], resolve),
            },
        ]
    },
]