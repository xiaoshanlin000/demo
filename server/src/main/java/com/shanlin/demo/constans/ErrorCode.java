package com.shanlin.demo.constans;

import com.shanlin.demo.response.entry.ResponseBaseEntry;
import com.shanlin.demo.response.entry.ResponseHeadEntry;

/**
 * Created by Administrator on 2016/6/7.
 */
public enum ErrorCode {

    SUCCESS("0", "成功"),
    ALREADY_REGISTERED("1", "该用户已经注册"),
    NOT_SUPPORT("2", "url未支持"),
    PARAMETER_ERROR("3", "请求参数错误"),
    SERVICE_ERROR("4", "服务器异常"),
    TOKEN_ERROR("5", "token 错误"),
    USERNAME_OR_PASSWORD_ERROR("6", "用户名或者密码错误"),
    TOKEN_TIME_OUT("7", "token 已过期"),;

    private String resultCode;
    private String resultMsg;

    ErrorCode(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public ResponseBaseEntry responseBaseEntry() {
        ResponseBaseEntry entry = new ResponseBaseEntry();
        entry.setHead(responseHeadEntry());
        return entry;
    }

    public ResponseHeadEntry responseHeadEntry() {
        ResponseHeadEntry head = new ResponseHeadEntry();
        head.setResultCode(getResultCode());
        head.setResultMsg(getResultMsg());
        return head;
    }
}
