package com.starvey.controller;

import org.casbin.casdoor.config.CasdoorConfiguration;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.service.CasdoorUserService;
import org.casbin.casdoor.util.http.CasdoorResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yu yaodong
 */
@RestController
public class UserController {

    @Resource
    private CasdoorConfiguration casdoorConfiguration;

    @Resource
    private CasdoorUserService casdoorUserService;

    @GetMapping("getUser")
    public CasdoorUser getUser(String name) throws Exception {
        return casdoorUserService.getUser(name);
    }

    @GetMapping("getUsers")
    public List<CasdoorUser> getUsers() throws Exception {
        return Arrays.asList(casdoorUserService.getUsers());
    }

    @PostMapping("addUser")
    public CasdoorResponse addUser(String name) throws IOException {
        CasdoorUser user = new CasdoorUser();
        user.setOwner(casdoorConfiguration.getOrganizationName());
        user.setName(name);
        user.setDisplayName(name);
        return casdoorUserService.addUser(user);
    }

    @PostMapping("updateUser")
    public CasdoorResponse updateUser(CasdoorUser user) throws IOException {
        return casdoorUserService.updateUser(user);
    }

    @DeleteMapping("deleteUser")
    public CasdoorResponse deleteUser(String name) throws Exception {
        CasdoorUser user = casdoorUserService.getUser(name);
        return casdoorUserService.deleteUser(user);
    }
}
