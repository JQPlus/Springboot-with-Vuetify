import basicRoutes from './routes'

let routePrefix = process.env.VUE_APP_ROUTE_PREFIX
const routes = [
    {
        path: routePrefix + '/',
        component: () => import('@/components/layout/index.vue'),
        children: []
    },
    {
        path: routePrefix + '/login',
        name: 'login',
        component: () => import('@/views/login.vue'),
    },
]
// add children
Array.prototype.push.apply(routes[0].children, basicRoutes)

export default routes