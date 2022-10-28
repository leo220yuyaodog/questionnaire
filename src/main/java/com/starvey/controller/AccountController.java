package com.starvey.controller;

import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.exception.CasdoorAuthException;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author Leo220yuyaodog
 */
@RestController
public class AccountController {

    @Resource
    private CasdoorAuthService casdoorAuthService;

    @RequestMapping("login")
    public String login(){
       return "";
    }

    @RequestMapping("callback")
    public String callback(String code, String state, HttpSession session) {
        String token = "";
        CasdoorUser user = null;
        try {
            token = casdoorAuthService.getOAuthToken(code, state);
            user = casdoorAuthService.parseJwtToken(token);
        } catch (CasdoorAuthException e) {
            e.printStackTrace();
        }
        session.setAttribute("casdoorUser", user);
        return "OK";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.setAttribute("casdoorUser", null);
        return "OK";
    }
}
