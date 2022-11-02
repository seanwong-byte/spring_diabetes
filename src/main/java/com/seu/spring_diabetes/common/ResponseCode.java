package com.seu.spring_diabetes.common;

public enum ResponseCode {
    USERNAME_NOT_EMPTY(1,"用户名不能为空"),
    PASSWORD_NOT_EMPTY(2,"密码不能为空"),
    USERNAME_NOT_EXISTS(3,"用户名不存在"),
    PASSWORD_NOT_CORRECT(4,"密码错误"),
    ARGS_NOT_EMPTY(5,"参数不能为空"),
    USERNAME_EXISTS(6,"用户名已存在"),
    EMAIL_EXISTS(7,"邮箱已存在"),
    QUESTION_NOT_EMPTY(8,"密保问题不能为空"),
    ANSWER_NOT_EMPTY(9,"密保答案不能为空"),
    EMAIL_NOT_EMPTY(10,"邮箱不能为空"),
    PHONE_NOT_EMPTY(11,"手机不能为空"),
    REGISTER_FAIL(12,"注册失败");


    private int code;
    private String msg;

   ResponseCode(int code , String msg){
        this.code=code;
        this.msg=msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
