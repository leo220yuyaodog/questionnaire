package com.starvey.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.exception.CasdoorAuthException;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author Leo220yuyaodog
 */
@RestController
@Api(tags="Account")
public class AccountController {

    @Resource
    private CasdoorAuthService casdoorAuthService;

    @PostMapping("login")
    @ApiOperation("login")
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

    @PostMapping("logout")
    @ApiOperation("logout")
    public String logout(HttpSession session) {
        session.setAttribute("casdoorUser", null);
        return "OK";
    }
}
