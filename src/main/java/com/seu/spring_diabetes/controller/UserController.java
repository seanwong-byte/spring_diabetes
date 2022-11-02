package com.seu.spring_diabetes.controller;

import com.seu.spring_diabetes.common.Const;
import com.seu.spring_diabetes.pojo.User;
import com.seu.spring_diabetes.service.IUserService;
import com.seu.spring_diabetes.utils.ServerResponse;
import com.seu.spring_diabetes.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.standard.expression.GreaterOrEqualToExpression;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/")
public class UserController {

    @Autowired
    IUserService userService;


    //登录login.do?username-xxx&password=xxx
//    @RequestMapping(value={"/login.do","/login"})
//    public User login(@RequestParam("username") String username,
//                      @RequestParam("password") String password){
//        User userInfo = new User();
//        userInfo.setId(1);
//        userInfo.setUsername(username);
//        userInfo.setPassword(password);
//
//        return userInfo;
//
//    }

    //restful登录 http://localhost:8080/loginRestful/zhangsan/123
    @RequestMapping(value="/restful/login/")
    public User loginRestful(@PathVariable("username") String username,
                             @PathVariable("password") String password){
        User userInfo = new User();
        userInfo.setId(1);
        userInfo.setUsername(username);
        userInfo.setPassword(password);

        return userInfo;

    }

    //自定义参数测试
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @RequestMapping("/test")
    public String test(){
        return jdbcUrl;
    }

    @RequestMapping(value = "user/login.do/")
    public ServerResponse<UserVO> login(String username,
                                        String password, HttpSession session){

        ServerResponse serverResponse = userService.loginLogic(username,password);
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;


    }

    @RequestMapping(value = "user/register.do/")
    public ServerResponse<User> register(User user){

        ServerResponse serverResponse = userService.registerLogic(user);
        return serverResponse;


    }


}
