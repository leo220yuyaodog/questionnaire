import request from "@/utils/request";

export function checkAlready(id, ip) {
  return request({
    method: 'GET',
    url: '/fillin/checkAlreadySubmit',
    id,
    ip,
  })
}

export function getList(id) {
  return request({
    method: 'GET',
    url: '/fillin/getQuestionList',
    id,
  })
}

export function getOutline(id) {
  return request({
    method: 'GET',
    url: '/fillin/getQuestionnaireOutline',
    id,
  })
}

export function checkSubmit(id, list, ip) {
  return request({
    method: 'POST',
    url: '/fillin/submitAnswer?questionnaireId=${id}',
    list,
    ip,
  })
}

export default class fillin {
}
