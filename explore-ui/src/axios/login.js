import request from '@/axios/interception'

export function login(loginInfo) {
  return request({
    url: '/login/login',
    method: 'post',
    data: loginInfo
  })
}

export function getInfo() {
  return request({
    url: '/login/verify',
    method: 'post',
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

