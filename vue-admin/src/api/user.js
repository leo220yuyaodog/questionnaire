import request from "@/utils/request"
import { ServerUrl } from "@/config"

export function login(data) {
  return request({
    url: "/vue-admin-template/user/login",
    method: "post",
    data
  })
}

export function getInfo(token) {
  return request({
    url: "/vue-admin-template/user/info",
    method: "get",
    params: { token }
  })
}

export function getAccount(token) {
  let query = ""
  if (token !== null) {
    query = `?accessToken=${token}`
  }
  return fetch(`${ServerUrl}/api/get-account${query}`, {
    method: "GET",
    credentials: "include"
  }).then((res) => res.json())
}

export function getUser() {
  return fetch(`${ServerUrl}/api/getUser`, {
    method: "GET",
    credentials: "include"
  }).then((res) => res.json())
}

export function logout() {
  return fetch(`${ServerUrl}/api/logout`, {
    method: "post",
    credentials: "include"
  }).then((res) => res.json())
}
