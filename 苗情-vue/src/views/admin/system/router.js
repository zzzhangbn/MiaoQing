export default [
    {
       path:'/person_review',
       component: resolve => require(['../../../components/layout/Home.vue'], resolve),
       children:[
          {
              path: '',
              component: resolve => require(['./person_review/index.vue'], resolve),
              meta: { title: '人员信息审核' }
          }
       ]
    },
]
