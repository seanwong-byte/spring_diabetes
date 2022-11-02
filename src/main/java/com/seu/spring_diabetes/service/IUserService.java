package com.seu.spring_diabetes.service;

import com.seu.spring_diabetes.pojo.User;
import com.seu.spring_diabetes.utils.ServerResponse;

public interface IUserService {
    /***
     * 登录
     */
    public ServerResponse loginLogic (String username, String password);

    /***
     * 注册
     */
    public ServerResponse registerLogic (User user);

}
