package org.matmed.messengerclient.common;

import com.alibaba.fastjson.JSONObject;

public class Response {
    public static final int OK = 0;
    public static final int ERROR = 1;


    private int status;
    private int code; //In case error status
    private JSONObject body;
    private String type;
    public Response()
    {

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
