let routePrefix = process.env.VUE_APP_ROUTE_PREFIX

const routes = [
    {
        path: routePrefix + '/',
        name: 'dashboard',
        component: () => import('../views/dashboard.vue')
    },
    {
        path: routePrefix + '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/about.vue')
    },
    {
        path: routePrefix + '/notebook',
        name: 'notebook',
        component: () => import('../views/notebook/index.vue')
    },
    {
        path: routePrefix + '/file',
        name: 'file',
        component: () => import('../views/file.vue')
    },


]
export default routes