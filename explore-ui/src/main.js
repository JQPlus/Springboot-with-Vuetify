import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify';
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
import axios from '@/axios/index.js'
import file from "@/axios/file.js"
import url from '@/axios/api.js'
import tools from "@/utils/tools.js"
import message from "@/components/message.vue"

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$url = url
Vue.prototype.$tools = tools
Vue.prototype.$message = message
Vue.prototype.$file = file

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
