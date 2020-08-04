
const routes = [
    {
        path: '/',
        name: 'Dashboard',
        component: () => import(/* webpackChunkName: "about" */ '../views/Dashboard.vue')
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/notebook',
        name: 'notebook',
        component: () => import(/* webpackChunkName: "about" */ '../views/notebook/index.vue')
    },

]
export default routes