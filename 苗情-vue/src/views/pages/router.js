export default [
    {
       path:'/',
       redirect:'/index',
       component: resolve => require(['@/components/layout/Index.vue'], resolve),
       children:[
            {
                path:'/index',
                component:resolve => require(['./index.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/riceAnalysis',
                component:resolve => require(['./conditionsAnalysis/riceAnalysis.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/doubleRiceAnalysis',
                component:resolve => require(['./conditionsAnalysis/doubleRiceAnalysis.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/accountAllocation',
                component:resolve => require(['./accountAllocation.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/pestDatabase',
                component:resolve => require(['./pestDatabase.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/riceHome',
                component:resolve => require(['../rice/riceHome.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/researchReport',
                component:resolve => require(['./researchReport/researchReport.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
	        {
                path:'/weathershow',
                component:resolve => require(['./weathershow/weathershow.vue'], resolve),
            },
            {
                path:'/template1',
                component:resolve => require(['./researchReport/template/template1.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template2',
                component:resolve => require(['./researchReport/template/template2.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template3-1',
                component:resolve => require(['./researchReport/template/template3-1.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template3-2',
                component:resolve => require(['./researchReport/template/template3-2.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template3-3',
                component:resolve => require(['./researchReport/template/template3-3.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template4',
                component:resolve => require(['./researchReport/template/template4.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/template5',
                component:resolve => require(['./researchReport/template/template5.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/viewTemplate',
                component:resolve => require(['./researchReport/template/viewTemplate.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path:'/weathershow',
                component:resolve => require(['./weathershow/weathershow.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template1',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template1.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template2',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template2.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template3',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template3.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template4',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template4.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template5',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template5.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/zaodao_template6',
                component: resolve => require(['./researchReport/template_double/province/zaodao_template6.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/wandao_template1',
                component: resolve => require(['./researchReport/template_double/province/wandao_template1.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/wandao_template2',
                component: resolve => require(['./researchReport/template_double/province/wandao_template2.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/wandao_template3',
                component: resolve => require(['./researchReport/template_double/province/wandao_template3.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            {
                path: '/province/wandao_template4',
                component: resolve => require(['./researchReport/template_double/province/wandao_template4.vue'], resolve),
                meta: { requiresAuth: true,title:'1' }
            },
            

       ]
    },
    {
        path: '/',
        component: resolve => require(['../../components/layout/Home.vue'], resolve),
        meta: { title: '自述文件' },
        children: [
            {
                name: '/zaodao_template1',
                path: '/zaodao_template1',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template1.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/zaodao_template2',
                path: '/zaodao_template2',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template2.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/zaodao_template3',
                path: '/zaodao_template3',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template3.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/zaodao_template4',
                path: '/zaodao_template4',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template4.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/zaodao_template5',
                path: '/zaodao_template5',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template5.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/zaodao_template6',
                path: '/zaodao_template6',
                component: resolve => require(['./researchReport/template_double/county/zaodao_template6.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/wandao_template1',
                path: '/wandao_template1',
                component: resolve => require(['./researchReport/template_double/county/wandao_template1.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/wandao_template2',
                path: '/wandao_template2',
                component: resolve => require(['./researchReport/template_double/county/wandao_template2.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/wandao_template3',
                path: '/wandao_template3',
                component: resolve => require(['./researchReport/template_double/county/wandao_template3.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                name: '/wandao_template4',
                path: '/wandao_template4',
                component: resolve => require(['./researchReport/template_double/county/wandao_template4.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template11',
                component:resolve => require(['./researchReport/template/county/template11.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template21',
                component:resolve => require(['./researchReport/template/county/template21.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template3-11',
                component:resolve => require(['./researchReport/template/county/template3-11.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template3-21',
                component:resolve => require(['./researchReport/template/county/template3-21.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template3-31',
                component:resolve => require(['./researchReport/template/county/template3-31.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template41',
                component:resolve => require(['./researchReport/template/county/template41.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
            {
                path:'/template51',
                component:resolve => require(['./researchReport/template/county/template51.vue'], resolve),
                meta: { requiresAuth: true,title:'2' }
            },
        ]
    },
    {
        path:'/login',
        component:resolve => require(['../login/login.vue'], resolve),
    },
    {
        path:'/401',
        component:resolve => require(['../login/401.vue'], resolve),
    },
    {
        path:'/changePwd',
        component:resolve => require(['../login/changePwd.vue'], resolve),
    },
    {
        path:'/test',
        component:resolve => require(['./test.vue'], resolve),
    }
]