package com.clip.web.exception;

import com.clip.core.bean.ReturnBean;
import com.clip.web.bean.enums.ServiceType;
import net.sf.json.JSONObject;

import java.util.List;

public class ErrorInfo extends Exception {
    private Long code;
    private String desc;
    private List<String> contextInfo;
    private ServiceType serviceType;
    private String errorMessage;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getContextInfo() {
        return contextInfo;
    }

    public void setContextInfo(List<String> contextInfo) {
        this.contextInfo = contextInfo;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ErrorInfo(Long code, String desc, ServiceType serviceType) {
        this.code = code;
        this.desc = desc;
        this.serviceType = serviceType;
    }

    public ErrorInfo(Long code, String desc, ServiceType serviceType, List<String> contextInfo) {
        this.code = code;
        this.desc = desc;
        this.serviceType = serviceType;
        this.contextInfo = contextInfo;
    }

    public JSONObject toJSONObject() {
        return toJSONObject(null);
    }

    public JSONObject toJSONObject(Object data) {
        JSONObject errorInfo = JSONObject.fromObject(new ReturnBean(false, getErrorMessage(), data));
        errorInfo.put("rdesc", getDesc());
        errorInfo.put("rcode", getCode());
        errorInfo.put("type", getServiceType());
        errorInfo.put("contextInfo", getContextInfo());
        return errorInfo;
    }

}
