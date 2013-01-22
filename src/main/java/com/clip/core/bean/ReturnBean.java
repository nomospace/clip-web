package com.clip.core.bean;

import com.clip.web.utils.JsonDateValueProcessor;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.Date;

public class ReturnBean {
    public boolean success = false;
    public String message = "";
    public Object data;

    public ReturnBean() {
    }

    public ReturnBean(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ReturnBean(boolean b, String s) {
        this.success = b;
        this.message = s;
    }

    @Override
    public String toString() {
        return this.toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        return JSONObject.fromObject(this, jsonConfig);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
