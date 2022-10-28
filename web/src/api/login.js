
import * as config from "@/Setting";

export function getAccount() {
  return fetch(`${config.serverUrl}/api/get-account`, {
    method: 'GET',
    credentials: 'include',
  }).then(res => {
    console.log(res)
    return res.json()
  });
}

export function logOut() {
  return fetch(`${config.serverUrl}/api/logout`, {
    method: 'POST',
    credentials: 'include',
  }).then((res => {
    return res.json()
  }));
}
