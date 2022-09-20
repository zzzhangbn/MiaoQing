import Vue from 'vue'
import Vuex from 'vuex'

import state from './state'
import getters from './getters'
import actions from './actions'
import mutations from './mutations'

Vue.use(Vuex)

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions,
    proxyTable: {
        '/apli': {
          target: 'http://pv.sohu.com',
          changeOrigin: true,
          pathRewrite: {
            '^/apli': '/apli' // 重写路径
          }
        }
      },
})
