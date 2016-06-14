package com.shanlin.demo.response.entry;

/**
 * Created by Administrator on 2016/6/7.
 */
public class ResponseBaseEntry {

    private ResponseHeadEntry head;

    private long useTime = 0;

    public ResponseHeadEntry getHead() {
        return head;
    }

    public void setHead(ResponseHeadEntry head) {
        this.head = head;
    }

    public long getUseTime() {
        return useTime;
    }

    public void setUseTime(long useTime) {
        this.useTime = useTime;
    }
}
