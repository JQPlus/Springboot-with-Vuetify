import { login, logout, getInfo } from '@/axios/login'
//在cookies中操作
import { getToken, setToken, removeToken } from '@/utils/localToken'

const user = {
    state: {
        token: getToken(),
        roles: "",
        EID: "",
        userName: "",
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_EID: (state, EID) => {
            state.EID = EID
        },
        SET_USERNAME: (state, userName) => {
            state.userName = userName
        }
    },

    actions: {
        // 登录
        //模块内部的actions方法参数为{ state, commit, rootState }=context,这里由于ES6参数解构，是简写
        login({ commit }, loginInfo) {
            //Promise，简单说就是一个容器，里面保存着某个未来才会结束的事件（通常是一个异步操作）的结果
            return new Promise((resolve, reject) => {
                //通过login，api发送请求到后端
                login(loginInfo).then(response => {
                    //设置token保存在本地
                    setToken(response.token)
                    //执行mutations中的方法，将token保存在state中
                    commit('SET_TOKEN', response.token)
                    commit('SET_ROLES', response.roles)
                    commit('SET_EID', response.EID)
                    commit('SET_USERNAME', response.userName)
                    //resolve函数的作用是，将Promise对象的状态从“未完成”变为“成功”
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 获取用户信息
        // 记得再次去commit mutations，否则页面刷新后，存储的信息会被摧毁
        getInfo({ commit }) {
            return new Promise((resolve, reject) => {
                getInfo().then(response => {
                    if (response.token) {
                        commit('SET_TOKEN', response.token)
                        commit('SET_ROLES', response.roles)
                        commit('SET_EID', response.EID)
                        commit('SET_USERNAME', response.userName)
                    } else {
                        reject("Please login again")
                    }
                    resolve(response)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 登出
        logout({ commit }) {
            return new Promise((resolve, reject) => {
                logout().then(() => {
                    commit('SET_TOKEN', '')
                    commit('SET_ROLES', '')
                    commit('SET_EID', '')
                    commit('SET_USERNAME', '')
                    removeToken()
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 前端 登出
        resetToken({ commit }) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                commit('SET_ROLES', '')
                commit('SET_EID', '')
                commit('SET_USERNAME', '')
                removeToken()
                resolve()
            })
        }
    },
}

export default user
