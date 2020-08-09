import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
import routes from './basic.js'

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
