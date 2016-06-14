package com.shanlin.demo.request.handler.handlerImpl;

import com.shanlin.demo.constans.ErrorCode;
import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.LogoutRequestEntry;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/6/10.
 */
@Component
public class LogoutHandler extends CheckTokenHandler<LogoutRequestEntry> {

    @Override
    public ResponseBaseEntry handleMessage(LogoutRequestEntry entry) throws ResponseException {
        return ErrorCode.SUCCESS.responseBaseEntry();
    }
}
