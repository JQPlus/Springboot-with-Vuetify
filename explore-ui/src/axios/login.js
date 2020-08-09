// import request from '@/axios/interception'

export function login(loginInfo) {
  // return request({
  //   url: '/login/login',
  //   method: 'post',
  //   data: loginInfo
  // })
  return this.$http.post(this.$url.login, loginInfo)
}

export function verify() {
  // return request({
  //   url: '/login/verify',
  //   method: 'post',
  // })
  return this.$http.get(this.$url.verify)
}

export function logout() {
  // return request({
  //   url: '/login/logout',
  //   method: 'post'
  // })
  return this.$http.get(this.$url.logout)
}

