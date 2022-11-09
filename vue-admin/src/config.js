import Sdk from "casdoor-js-sdk"

export let CasdoorSdk
export const ServerUrl = "http://localhost:8080"
export const AuthConfig = {
  serverUrl: "http://localhost:8000", // 本地
  // serverUrl: 'http://bit.windsparkle.top',
  clientId: "d523344d9ee6890afacf",
  appName: "Star-Survey",
  organizationName: "Star",
  redirectPath: "/callback"
}

export function initCasdoorSdk(config) {
  CasdoorSdk = new Sdk(config)
}

export function getSignupUrl() {
  return CasdoorSdk.getSignupUrl()
}

export function getSigninUrl() {
  return CasdoorSdk.getSigninUrl()
}

export function getUserProfileUrl(userName, account) {
  return CasdoorSdk.getUserProfileUrl(userName, account)
}

export function getMyProfileUrl(account) {
  return CasdoorSdk.getMyProfileUrl(account)
}

export function getMyResourcesUrl(account) {
  return CasdoorSdk.getMyProfileUrl(account).replace("/account?", "/resources?")
}

