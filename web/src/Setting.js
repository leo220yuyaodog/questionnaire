import Sdk from "casdoor-js-sdk";

export const serverUrl = `http://localhost:8080` // 后端地址
export let CasdoorSdk;

export function showMessage(type, text) {
    if (type === "success") {
    } else if (type === "error") {
    }
}

export function initCasdoorSdk(config) {
    CasdoorSdk = new Sdk(config);
}
export function getSignupUrl() {
    return CasdoorSdk.getSignupUrl();
}

export function getSigninUrl() {
    return CasdoorSdk.getSigninUrl();
}

export function getUserProfileUrl(userName, account) {
    return CasdoorSdk.getUserProfileUrl(userName, account);
}

export function getMyProfileUrl(account) {
    return CasdoorSdk.getMyProfileUrl(account);
}

export function signin() {
    return CasdoorSdk.signin(serverUrl).then((res) => {
        if (res.status === "ok") {
            showMessage("success", "login:Logged in successfully");
        }
        return res;
    });
}



export function goToLink(link) {
    window.location.href = link;
}
