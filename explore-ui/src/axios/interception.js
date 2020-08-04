import axios from 'axios'
import { getToken } from '@/utils/localToken'
import store from '../store'


// 创建axios实例
const service = axios.create({
    // 请求api 的 base_url
    baseURL: process.env.VUE_APP_AXIOS_PREFIX,
    // 公共请求超时时间
    // timeout: 30000,
})

// request拦截器
service.interceptors.request.use(
    config => {
        if (store.getters.token) {
            config.headers['Authorization'] = getToken() // 给request请求添加自定义的header属性
        }
        return config
    },
    error => {
        // Do something with request error
        // console.log(error) // for debug
        Promise.reject(error)
    }
)

// response 拦截器
service.interceptors.response.use(
    response => {
        const res = response.data
        return res;
    }
)

export default service