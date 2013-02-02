package com.clip.web.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.clip.core.bean.ReturnBean;
import com.clip.core.utils.http.BlockHttpClient;
import com.clip.core.utils.http.HttpCallBack;
import com.clip.web.exception.ErrorInfo;
import com.clip.web.bean.enums.ServiceType;
import com.clip.web.utils.i18n.MultiSystemI18NSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class ServiceUtils {
    private static Log logger = LogFactory.getLog(ServiceUtils.class);

    public static ErrorInfo parseErrorInfo(JSONObject resultJson, ServiceType serviceType) {
        try {
            if (resultJson != null && !JsonUtils.isBackendJsonSuccess(resultJson)) {
                //默认出错代码
                String rcode = resultJson.optString("rcode", null);
                rcode = Long.parseLong(rcode) * (-1) + "";
                Long rcodeNum = Long.parseLong(rcode);
                String rdesc = resultJson.optString("rdesc", null);
                Object contextInfo = resultJson.opt("contextInfo");
                List<String> contextList = new ArrayList<String>();
                if (contextInfo instanceof String) {
                    contextList.add((String) contextInfo);
                } else if (contextInfo instanceof JSONArray) {
                    JSONArray contextInfoJsonArray = ((JSONArray) contextInfo);
                    for (Object item : contextInfoJsonArray) {
                        if (item == null || item == JSONNull.getInstance()) {
                            contextList.add("null");
                        } else {
                            contextList.add(item.toString());
                        }
                    }
                }
                return createErrorInfo(rcodeNum, rdesc, contextList, serviceType);
            }
        } catch (Exception e) {
            logger.error("parseErrorInfo未知错误", e);
        }
        return new ErrorInfo(-10001l, null, ServiceType.Index, null);
    }

    public static ErrorInfo createErrorInfo(long rcode, String rdesc, List<String> contextList, ServiceType serviceType) {
        //自动转换rcode
        if (rcode < 0) {
            rcode = Math.abs(rcode);
        }
        ErrorInfo errorInfo = new ErrorInfo(rcode, rdesc, serviceType, contextList);
        //设定出错时的默认值
        setErrorInfoAsCodeNotFound(serviceType, rcode, errorInfo);
        MultiSystemI18NSupport i18NSupport = null;
        //根据错误代码寻找翻译器
        if (rcode < 10000 && rcode > 1000) {
            i18NSupport = that.serviceErrorMessageMapping.getI18NSupport(ServiceType.Common);
        } else {
            i18NSupport = that.serviceErrorMessageMapping.getI18NSupport(serviceType);
        }
        //处理context数组
        String[] contextArray = null;
        if (contextList != null && contextList.size() > 0) {
            contextArray = contextList.toArray(new String[contextList.size()]);
        }
        if (i18NSupport != null) {
            try {
                String tmpMsg = i18NSupport.getMessage(rcode + "", contextArray);
                if (StringUtils.isNotEmpty(tmpMsg)) {
                    errorInfo.setErrorMessage(tmpMsg);
                }
            } catch (Exception ignored) {
            }
        }
        return errorInfo;
    }

    private static void setErrorInfoAsCodeNotFound(ServiceType serviceType, long rcode, ErrorInfo errorInfo) {
        errorInfo.setCode(-10001l);
        String rMessage = that.serviceErrorMessageMapping.getI18NSupport(ServiceType.Index).getMessage(
                "10001", serviceType.toString(), rcode + ""
        );
        errorInfo.setDesc(rMessage);
        errorInfo.setErrorMessage(rMessage);
//        logger.info("未找到相应代码:" + rcode);
    }


    public static JSONObject queryWithoutArgs(String serviceUrl, String actionName) {
        Map param = ParamUtils.getRequiredArgs(actionName);
        return queryJson(serviceUrl, param, BlockHttpClient.PushType.GET);
    }


    private static ServiceUtils that;

    @Resource
    private ServiceErrorMessageMapping serviceErrorMessageMapping;

    /**
     * 的基础上将结果封装为JSONObject
     *
     * @param urlStr   请求的url
     * @param para     请求参数
     * @param pushType 请求类型
     * @return 将返回字符串转化成的json对象（后台格式）, 如有异常, 返回404后台错误对象
     */
    public static JSONObject queryJson(String urlStr, final Object para, final BlockHttpClient.PushType pushType) {
        String responseString = BlockHttpClient.callUrl(urlStr, para, pushType);
        if (responseString == null) {
            return JsonUtils.createBackendErrorJson(-1001, null, "404");
        }
        try {
            return JSONObject.fromObject(responseString);
        } catch (Exception e) {
            return JsonUtils.createBackendErrorJson(-1004, null, "子系统返回值");
        }
    }

    /**
     * 在{@link com.clip.core.utils.http.BlockHttpClient#callUrl(String, java.util.Map, com.clip.core.utils.http.BlockHttpClient.PushType, com.clip.core.utils.http.HttpCallBack)}
     * 的基础上将结果封装为JSONObject
     *
     * @param urlStr     请求的url
     * @param para       请求参数
     * @param pushType   请求类型
     * @param callBackFn 回调函数
     * @return 将返回字符串转化成的json对象（后台格式）, 如有异常, 返回404后台错误对象
     * @see com.clip.core.utils.http.HttpCallBack
     */
    public static JSONObject queryJson(String urlStr, final Map para, final BlockHttpClient.PushType pushType, HttpCallBack callBackFn) {
        return JSONObject.fromObject(BlockHttpClient.callUrl(urlStr, para, pushType, callBackFn));
    }

    @PostConstruct
    private void init() {
        that = this;
    }


    public static Map insertToMap(Map operateMap, String name, Object value) {
        if (value instanceof Collection) {
            if (operateMap != null && value != null && ((Collection) value).size() > 0) {
                operateMap.put(name, value);
            }
        } else {
            if (operateMap != null && value != null) {
                operateMap.put(name, value);
            }
        }
        return operateMap;
    }

//    public static Map insertAllToMap(Map operateMap, String name, Collection value){
//        if(operateMap!=null && value!=null && value.size()>0){
//            operateMap.putAll(name,value);
//        }
//        return operateMap;
//    }


    public static <T> List<T> convertJsonArrayToBeanList(Class<T[]> clazz, JSONArray jsonArray) {
        Gson gson = new Gson();
        try {
            return Arrays.asList(gson.fromJson(jsonArray.toString(), clazz));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static <T> T convertJsonToBean(Class<T> clazz, JSONObject jsonObject) {
        Gson gson = new Gson();
        if (jsonObject != null) {
            try {
                return gson.fromJson(jsonObject.toString(), clazz);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return null;
    }


}
