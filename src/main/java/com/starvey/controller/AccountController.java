package com.starvey.controller;

import com.starvey.common.Data;
import com.starvey.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.exception.CasdoorAuthException;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Leo220yuyaodog
 */
@RestController
@Api(tags="Account")
public class AccountController {

    @Resource
    private CasdoorAuthService casdoorAuthService;

    @PostMapping("signin")
    public Result signin(@RequestParam String code, @RequestParam String state, HttpSession session) {
        String token = "";
        CasdoorUser user = null;
        try {
            token = casdoorAuthService.getOAuthToken(code, state);
            user = casdoorAuthService.parseJwtToken(token);
        } catch (CasdoorAuthException e) {
            e.printStackTrace();
        }
        session.setAttribute("casdoorUser", user);

        return Result.success(200,"ok",new Data(token,user));
    }

    @PostMapping("logout")
    @ApiOperation("logout")
    public Result logout(HttpSession session) {
        session.removeAttribute("casdoorUser");
        return Result.success("ok","成功登出");
    }
}
