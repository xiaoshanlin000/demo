package com.shanlin.demo.request.handler.handlerImpl;

import com.shanlin.demo.constans.ErrorCode;
import com.shanlin.demo.database.entry.User;
import com.shanlin.demo.database.repository.UserRepository;
import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.request.handler.IHttpHandler;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Shanlin on 16/6/9.
 */
@Component
public abstract class AbstractHandler<T extends RequestBaseEntry>  implements IHttpHandler<T>{

    @Override
    public boolean checkRequest(RequestBaseEntry entry) throws ResponseException {
        if (entry == null) throw  new ResponseException(ErrorCode.PARAMETER_ERROR);
        if (entry.getHead() == null) throw  new ResponseException(ErrorCode.PARAMETER_ERROR);
        if (entry.getHead().getUsername() == null || entry.getHead().getUsername().length() == 0 ) throw new ResponseException(ErrorCode.PARAMETER_ERROR);
        return true;
    }

}
