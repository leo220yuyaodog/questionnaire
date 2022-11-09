import * as config from "@/config"
export function getQuestionnaires(userId, pageNumber, pageSize) {
  return fetch(`${config.ServerUrl}/api/questionnaires?id=${userId}&pageNumber=${pageNumber}&pageSize=${pageSize}`, {
    method: "GET",
    credentials: "include"
  }).then(res => res.json())
}

export function addQuestionnaires(newQuestionnaire) {
  const questionnaire = Object.assign({}, newQuestionnaire)
  return fetch(`${config.ServerUrl}/api/questionnaire/add`, {
    method: "POST",
    credentials: "include",
    body: JSON.stringify(questionnaire),
    headers: {
      "Content-Type": "application/json"
    }
  }).then(res => res.json())
}

