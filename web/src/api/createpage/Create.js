import request from "@/utils/request";

export function fetchList(id) {
  return request({
    method: 'GET',
    url: '/getQuestionList',
    id,
  })
}

export function fetchOutLine(id) {
  return request({
    method: 'GET',
    url: '/getQuestionnaireOutline',
    id,
  })
}

export function saveOne(id, data) {
  return request({
    method: 'POST',
    url: '/saveOneQuestion?questionnaireId=${id}',
    data,
  })
}

export function saveTitle(params = {}) {
  return request({
    method: 'POST',
    url: '/saveQuestionnaireOutline',
    params,
  })
}

export function saveList(params = {}) {
  return request({
    method: 'POST',
    url: '/saveQuestionnaire',
    params
  })
}

export function deleteQ(id) {
  return request({
    method: 'GET',
    url: '/deleteQuestionnaire',
    id,
  })
}

export function releaseQ(id) {
  return request({
    method: 'POST',
    url: '/releaseQuestionnaire?questionnaireId=${id}',
  })
}

export default class Create {
}
