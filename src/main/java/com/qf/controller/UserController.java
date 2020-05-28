package com.qf.controller;

import com.qf.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 54110 on 2019/11/22.
 */
@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/dealLogin")
    public String dealLogin(@RequestParam("username")String name,@RequestParam("password")String password){
    /*    TbSysUser byUserName = sysUserService.findByUserName(name);
        if (byUserName!=null){
            String password1 = byUserName.getPassword();
            if (password1.equals(password)){
                return "redirect:findAll";
            }
        }*/
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                return "redirect:findAll";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "login";
    }
}
