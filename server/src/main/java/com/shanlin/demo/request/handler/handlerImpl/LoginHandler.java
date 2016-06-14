package com.shanlin.demo.request.handler.handlerImpl;

import com.shanlin.demo.constans.ErrorCode;
import com.shanlin.demo.database.entry.User;
import com.shanlin.demo.database.repository.UserRepository;
import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.LoginRequestEntry;
import com.shanlin.demo.response.entry.LoginResponseEntry;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import com.shanlin.demo.utils.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/6/10.
 */
@Component
public class LoginHandler extends AbstractHandler<LoginRequestEntry> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseBaseEntry handleMessage(LoginRequestEntry entry) throws ResponseException {
        User user = userRepository.findByUsername(entry.getHead().getUsername());
        if (user == null) throw new ResponseException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        if (!user.getPassword().equals(entry.getPassword()))
            throw new ResponseException(ErrorCode.USERNAME_OR_PASSWORD_ERROR);
        String token = AppUtil.getToken(user.getUsername());
        user.setToken(token);
        user.setCreateTokenTime(AppUtil.currentTimeMillis());
        userRepository.save(user);
        LoginResponseEntry loginResponseEntry = new LoginResponseEntry();
        loginResponseEntry.setHead(ErrorCode.SUCCESS.responseHeadEntry());
        loginResponseEntry.setToken(token);
        return loginResponseEntry;
    }
}
