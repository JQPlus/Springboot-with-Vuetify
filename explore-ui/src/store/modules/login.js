import { login, logout, verify } from '@/axios/login'
//在cookies中操作
import { getToken, setToken, removeToken } from '@/utils/localToken'

const user = {
    state: {
        token: getToken(),
        roles: "",
        account: "",
        userName: "",
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_ACCOUNT: (state, account) => {
            state.account = account
        },
        SET_USERNAME: (state, userName) => {
            state.userName = userName
        }
    },

    actions: {
        login({ commit }, loginInfo) {
            return new Promise((resolve, reject) => {
                login(loginInfo).then(response => {
                    setToken(response.token)
                    commit('SET_TOKEN', response.token)
                    commit('SET_ROLES', response.roles)
                    commit('SET_ACCOUNT', response.account)
                    commit('SET_USERNAME', response.userName)
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        verify({ commit }) {
            return new Promise((resolve, reject) => {
                verify().then(response => {
                    if (response.token) {
                        commit('SET_TOKEN', response.token)
                        commit('SET_ROLES', response.roles)
                        commit('SET_ACCOUNT', response.account)
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

        logout({ commit }) {
            return new Promise((resolve, reject) => {
                logout().then(() => {
                    commit('SET_TOKEN', '')
                    commit('SET_ROLES', '')
                    commit('SET_ACCOUNT', '')
                    commit('SET_USERNAME', '')
                    removeToken()
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        resetToken({ commit }) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '')
                commit('SET_ROLES', '')
                commit('SET_ACCOUNT', '')
                commit('SET_USERNAME', '')
                removeToken()
                resolve()
            })
        }
    },
}

export default user
