import request from './interception'
import axios from 'axios'

/**
 * @param {String} url 
 * @param {JSON} data json类型对象
 * @param {JSON} extend 如需修改，使用json格式添加参数
 * @description post请求 对应@PostMapping
 */
function post(url, data, extend = { timeout: 5000, responseType: 'json' }) {
    return request({
        url: url,
        method: 'post',
        // headers: { "Content-Type": 'application/json;chartset=UTF-8' },
        data: data,
        // 如果是用作blob二进制流生成excel，需修改为blob
        responseType: extend.responseType,
        timeout: extend.timeout
    })
}

/**
 * @description get请求 对应@GetMapping
 */
function get(url, params, extend = { timeout: 5000, responseType: 'json' }) {
    return request({
        url: url,
        method: 'get',
        params: params,
        responseType: extend.responseType,
        timeout: extend.timeout
    })
}

/**
 * @description delete请求，用作删除，对应 @DeleteMapping
 */
function del(url, data, extend = { timeout: 5000 }) {
    return request({
        url: url,
        method: 'delete',
        data: data,
        timeout: extend.timeout
    })
}

/**
 * @description put @PutMapping
 */
function put(url, data, extend = { timeout: 5000 }) {
    return request({
        url: url,
        method: 'put',
        data: data,
        timeout: extend.timeout
    })
}
/**
 * 
 * @description 该方法没有使用封装的request拦截器，通过blob二进制的形式下载excel数据
 */
function download(url, data, extend = { timeout: 9999999 }) {
    return axios({
        baseURL: process.env.VUE_APP_PROXYURL,
        url: url,
        method: 'post',
        data: data,
        responseType: 'blob',
        timeout: extend.timeout
    })
}


export default { post, get, del, put, download }