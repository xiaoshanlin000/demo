package com.shanlin.demo.request.handler.handlerImpl;

import com.shanlin.demo.exceptions.ResponseException;
import com.shanlin.demo.request.entry.RequestBaseEntry;
import com.shanlin.demo.response.entry.ResponseBaseEntry;
import org.springframework.stereotype.Component;

import static com.shanlin.demo.constans.ErrorCode.NOT_SUPPORT;

/**
 * Created by Shanlin on 16/6/6.
 */
@Component
public class NotSupportHandler extends AbstractHandler {

    @Override
    public boolean checkRequest(RequestBaseEntry entry) throws ResponseException {
        return true;
    }

    @Override
    public ResponseBaseEntry handleMessage(RequestBaseEntry entry) {
        return NOT_SUPPORT.responseBaseEntry();
    }
}
