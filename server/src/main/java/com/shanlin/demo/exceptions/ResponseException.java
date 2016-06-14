package com.shanlin.demo.exceptions;

import com.shanlin.demo.constans.ErrorCode;
import com.shanlin.demo.response.entry.ResponseBaseEntry;

/**
 * Created by Shanlin on 16/6/6.
 */
public class ResponseException extends Exception {

    private ErrorCode code;
    public ResponseException(ErrorCode code){
        this.code = code;
    }

    public ResponseBaseEntry responseBaseEntry(){
        return this.code.responseBaseEntry();
    }
}
