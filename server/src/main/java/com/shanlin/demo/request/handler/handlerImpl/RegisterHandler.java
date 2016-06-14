package com.shanlin.demo.request.handler.handlerImpl;

import com.shanlin.demo.database.entry.User;
import com.shanlin.demo.database.repository.UserRepository;
import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.RegisterRequestEntry;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.shanlin.demo.constans.ErrorCode.ALREADY_REGISTERED;
import static com.shanlin.demo.constans.ErrorCode.SUCCESS;

/**
 * Created by Administrator on 2016/6/7.
 */
@Component
public class RegisterHandler extends AbstractHandler<RegisterRequestEntry>{

    @Autowired
    protected UserRepository userRepository;
    @Override
    public ResponseBaseEntry handleMessage(RegisterRequestEntry entry) throws ResponseException {
        User user = userRepository.findByUsername(entry.getHead().getUsername());
        if (user != null){
            return  ALREADY_REGISTERED.responseBaseEntry();
        }
        user = new User();
        user.setPassword(entry.getPassword());
        user.setUsername(entry.getHead().getUsername());
        userRepository.saveAndFlush(user);
        return SUCCESS.responseBaseEntry();
    }
}
