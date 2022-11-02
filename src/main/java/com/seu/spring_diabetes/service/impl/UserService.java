package com.seu.spring_diabetes.service.impl;

import com.seu.spring_diabetes.common.Const;
import com.seu.spring_diabetes.common.ResponseCode;
import com.seu.spring_diabetes.dao.UserMapper;
import com.seu.spring_diabetes.pojo.User;
import com.seu.spring_diabetes.service.IUserService;
import com.seu.spring_diabetes.utils.DateUtil;
import com.seu.spring_diabetes.utils.MD5Utils;
import com.seu.spring_diabetes.utils.ServerResponse;
import com.seu.spring_diabetes.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ServerResponse loginLogic(String username, String password){

        //step1:用户名密码非空判断
        if (StringUtils.isBlank(username)){//null length=0 含空格 tab制表符

            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),
                    ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        if (StringUtils.isBlank(password)){

            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),
                    ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        //step2:查看用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if (count == 0){
            //用户名不存在
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EXISTS.getCode(),
                    ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        }

        //step3:根据用户名和密码查询
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.getMD5Code(password));
        if(user==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_CORRECT.getCode(),ResponseCode.PASSWORD_NOT_CORRECT.getMsg());
        }
        user.setPassword(null);

        //step4:返回结果
        return ServerResponse.createServerResponseBySuccess(convert(user));
    }

    private UserVO convert(User user){
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setQuestion(user.getQuestion());
        userVO.setPhone(user.getPhone());
        userVO.setCreateTime(DateUtil.date2String(user.getCreateTime()));
        userVO.setUpdateTime(DateUtil.date2String(user.getUpdateTime()));
        return userVO;
    }

    @Override
    public ServerResponse registerLogic(User user){
        //step1:参数非空校验
        if (user == null){
            return ServerResponse.createServerResponseByFail(ResponseCode.ARGS_NOT_EMPTY.getCode(),
                    ResponseCode.ARGS_NOT_EMPTY.getMsg());
        }
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        String phone = user.getPhone();
        String question = user.getQuestion();
        String answer = user.getAnswer();

        if (StringUtils.isBlank(username)){
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_NOT_EMPTY.getCode(),
                    ResponseCode.USERNAME_NOT_EMPTY.getMsg());
        }

        if (StringUtils.isBlank(password)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(),
                    ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }

        if (StringUtils.isBlank(email)){
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_NOT_EMPTY.getCode(),
                    ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }

        if (StringUtils.isBlank(phone)){
            return ServerResponse.createServerResponseByFail(ResponseCode.PHONE_NOT_EMPTY.getCode(),
                    ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }

        if (StringUtils.isBlank(question)){
            return ServerResponse.createServerResponseByFail(ResponseCode.QUESTION_NOT_EMPTY.getCode(),
                    ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }
        if (StringUtils.isBlank(answer)){
            return ServerResponse.createServerResponseByFail(ResponseCode.ANSWER_NOT_EMPTY.getCode(),
                    ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }


        //step2:判断用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if (count > 0) {
            //用户名存在
            return ServerResponse.createServerResponseByFail(ResponseCode.USERNAME_EXISTS.getCode(),
                    ResponseCode.USERNAME_EXISTS.getMsg());
        }

        //step3:判断邮箱是否存在
        Integer count_email = userMapper.findByEmail(email);
        if (count_email > 0) {
            //邮箱存在
            return ServerResponse.createServerResponseByFail(ResponseCode.EMAIL_EXISTS.getCode(),
                    ResponseCode.EMAIL_EXISTS.getMsg());
        }
        //step4:注册-对密码加密-MD5
        user.setPassword(MD5Utils.getMD5Code(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(Const.NORMAL_USER);
        Integer result = userMapper.insert(user);
        if (result==0){
            return ServerResponse.createServerResponseByFail(ResponseCode.REGISTER_FAIL.getCode(),
                    ResponseCode.REGISTER_FAIL.getMsg());
        }

        //step5:返回结果
        return ServerResponse.createServerResponseBySuccess();
    }
}
