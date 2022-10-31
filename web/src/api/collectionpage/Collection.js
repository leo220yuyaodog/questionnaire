import request from "@/utils/request";

export function fetch(id) {
  return request({
    method: 'GET',
    url: '/getQuestionValueList',
    id,
  })
}

export function fetchWrite(id) {
  return request({
    method: 'GET',
    url: '/getWriteValue',
    id,
  })
}

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

export function getQuestion(params = {}) {
  return request({
    method: 'GET',
    url: '/getQuestionnaires',
    params,
  })
}

export function goto(params = {}) {
  return request({
    method: 'GET',
    url: '/createQuestionnaire',
    params
  })
}

export function deleteQuestion(id) {
  return request({
    method: 'GET',
    url: '/deleteQuestionnaire',
    id,
  })
}

export function close(id) {
  return request({
    method: 'POST',
    url: '/closeQuestionnaire?questionnaireId=${id}',
  })
}

export default class Collection {
}
