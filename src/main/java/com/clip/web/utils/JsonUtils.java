package com.clip.web.utils;

import com.clip.core.bean.ReturnBean;
import com.clip.web.bean.enums.ServiceType;
import com.clip.web.exception.ErrorInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Json工具，用于统一运维平台逻辑上的Json的转化以及常用Json的创建
 */
public class JsonUtils {

    private static JsonConfig emptyConfig = new JsonConfig();

    private static Gson engine = new Gson();

    /**
     * 将子系统服务端返回的json转化成供前端使用的json格式
     * <p>
     * 在有错误的情况下，这个过程经过了按照规范文档中描述的错误消息的转换，<br/>
     * 在有错误消息的情况下自动转换成success:false，message:error message<br/>
     * 且message错误消息是根据国际化转化后的消息结果
     * </p>
     * <p>
     * 在没有错误的情况下success:true，message:null<br/>
     * data 为frontJsonObject中键为datakey的那一层<br/>
     * 当dataKey为null的时候，data就是整个frontJsonObject
     * </p>
     *
     * @param backendJsonObject 要转化的来自子系统服务端的json对象
     * @param dataKey           data中的内容将以frontJsonObject.dataKey来替换，<br/>
     *                          dataKey是null的将以frontJsonObject来替换
     * @param serviceType       子系统服务类型
     * @return 返回{success:boolean,message:string,data:object} 的形式
     */
    public static JSONObject convertToFrontJson(JSONObject backendJsonObject, String dataKey, ServiceType serviceType) {
        if (backendJsonObject == null) {
            return null;
        }
        if (backendJsonObject.containsKey("rcode") && backendJsonObject.optLong("rcode") != 0) {
            return ServiceUtils.parseErrorInfo(backendJsonObject, serviceType).toJSONObject();
        } else {
            ReturnBean returnBean = new ReturnBean(true, null);
            if (dataKey == null) {
                returnBean.setData(backendJsonObject);
            } else {
                returnBean.setData(backendJsonObject.opt(dataKey));
            }
            return returnBean.toJSONObject();
        }
    }

    /**
     * 方便建造一个符合标准服务子系统后台返回的错误对象
     *
     * @param rcode       错误码（应为负数）
     * @param rdesc       错误描述
     * @param contextInfo 错误提示信息（用于填充错误资源文件中的占位符）
     * @return {
     *         rcode:num,
     *         rdesc:string,
     *         contextInfo:[string,string....]
     *         }
     */
    public static JSONObject createBackendErrorJson(
            long rcode, String rdesc, String... contextInfo) {
        JSONObject errorBean = new JSONObject();
        if (rcode > 0) {
            rcode = rcode * -1L;
        }
        errorBean.put("rcode", rcode);
        errorBean.put("rdesc", rdesc);
        if (contextInfo != null && contextInfo.length > 0) {
            JSONArray contextNode = new JSONArray();
            Collections.addAll(contextNode, contextInfo);
            errorBean.put("contextInfo", contextNode);
        }
        return errorBean;
    }

    /**
     * 根据错误码，ServiceType，contextInfo（可选）来创建一个标准的前端错误Json
     * <p>
     * 该方法是通过先createBackendErrorJson，再convertToFrontJson
     * </p>
     *
     * @param rcode              错误码（负数）
     * @param serviceTypeForI18n 服务类型
     * @param contextInfo        错误消息占位符（用于替换国际化资源文件）
     * @return 返回{success:false,message:string,data:null} 的形式
     */
    public static JSONObject createFrontendErrorJson(
            long rcode, ServiceType serviceTypeForI18n, String... contextInfo
    ) {
        if (rcode > 0) {
            rcode = rcode * -1L;
        }
        return convertToFrontJson(
                createBackendErrorJson(rcode, null, contextInfo),
                null, serviceTypeForI18n
        );
    }

    /**
     * 创建一个标准的前台json对象
     *
     * @param success 是否成功
     * @param message 消息
     * @param data    数据
     * @return 返回{success:boolean,message:string,data:object} 的形式
     */
    public static JSONObject createFrontendJson(
            boolean success, String message, Object data
    ) {
        return new ReturnBean(success, message, data).toJSONObject();
    }

    /**
     * 获取{success:boolean,message:string,data:object} 中data的部分
     *
     * @param frontendJson 完整的如 {success:boolean,message:string,data:object} 的格式
     * @return JsonObject
     * @see JsonUtils#getFrontendData(Class, net.sf.json.JSONObject)
     */
    public static JSONObject getFrontendDataObject(JSONObject frontendJson) {
        return getFrontendData(JSONObject.class, frontendJson);
    }

    /**
     * 获取{success:boolean,message:string,data:object} 中data的部分
     *
     * @param frontendJson 完整的如 {success:boolean,message:string,data:object} 的格式
     * @return JsonArray
     * @see JsonUtils#getFrontendData(Class, net.sf.json.JSONObject)
     */
    public static JSONArray getFrontendDataArray(JSONObject frontendJson) {
        return getFrontendData(JSONArray.class, frontendJson);
    }

    /**
     * 获取{success:boolean,message:string,data:object} 中data的部分
     *
     * @param frontendJson 完整的如 {success:boolean,message:string,data:object} 的格式
     * @return String
     * @see JsonUtils#getFrontendData(Class, net.sf.json.JSONObject)
     */
    public static String getFrontendDataString(JSONObject frontendJson) {
        return getFrontendData(String.class, frontendJson);
    }

    /**
     * 获取{success:boolean,message:string,data:object} 中data的部分
     * getFrontendData*都基于此方法
     *
     * @param frontendJson 完整的如 {success:boolean,message:string,data:object} 的格式
     * @return T
     */
    public static <T> T getFrontendData(Class<T> clazz, JSONObject frontendJson) {
        if (!JsonUtils.isFrontendJsonSuccess(frontendJson)) {
            return null;
        }
        return (T) frontendJson.opt(JsonUtils.KEY_DATA);
    }

    public static final String KEY_DATA = "data";

    /**
     * 将一个对象x包裹到<br/>
     * {<br/>
     * "data":x<br/>
     * }<br/>
     *
     * @param object 要被包裹的对象
     * @return 包裹完后的对象
     */
    public static JSONObject wrapByDataKey(Object object) {
        JSONObject result = new JSONObject();
        result.put(KEY_DATA, object);
        return result;
    }

    public static boolean isBackendJsonSuccess(JSONObject resultJson) {
        boolean result = false;
        if (resultJson != null) {
            if (!resultJson.containsKey("rcode") || (resultJson.containsKey("rcode") && resultJson.optString("rcode").equals("0"))) {
                result = true;
            }
        }
        return result;
    }

    public static boolean isFrontendJsonSuccess(JSONObject resultJson) {
        if (resultJson == null || !resultJson.containsKey("success")) {
            return false;
        }
        return resultJson.optBoolean("success", false);
    }


    public static JSONObject createEmptyDataArray(Long totalCount, String key) {
        JSONObject returnJSON = new JSONObject();
        returnJSON.put("totalCount", totalCount);
        if (key != null) {
            returnJSON.put(key, new JSONArray());
        } else {
            returnJSON.put("list", new JSONArray());
        }
        return returnJSON;
    }

    public static List<JSONObject> detachDataFromFrontJsonList(List<JSONObject> frontJsonList) {
        if (frontJsonList != null) {
            List<JSONObject> detachedList = new ArrayList<JSONObject>();
            for (JSONObject frontJson : frontJsonList) {
                if (isFrontendJsonSuccess(frontJson)) {
                    detachedList.add(getFrontendData(JSONObject.class, frontJson));
                }
            }
            return detachedList;
        }
        return null;
    }

//    public static List toList(JSONArray array,Object root){
//        List retVal=new ArrayList();
//        if(CollectionUtils.isNotEmpty(array)){
//            try {
//                retVal=JSONArray.toList(array,root,emptyConfig);
//            } catch (Exception e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//            return retVal;
//        }
//        return null;
//
//    }

    //    public static  List<T>  toList(JSONArray array){
//           List<?> retVal=null;
//           if(CollectionUtils.isNotEmpty(array)){
//               try {
//
//                   Type type= new TypeToken<ArrayList<T>>(){}.getType();
//                   retVal=engine.fromJson(array.toString(),type);
//               } catch (Exception e) {
//                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//               }
//           }
//        return retVal;
//       }
    public static List<Long> toLongList(JSONArray array) throws ErrorInfo {
        List<Long> retVal = new ArrayList();
        if (CollectionUtils.isNotEmpty(array)) {
            try {
                Type type = new TypeToken<ArrayList<Long>>() {
                }.getType();
                retVal = engine.fromJson(array.toString(), type);
            } catch (Exception e) {
                throw new ErrorInfo(-19995l, "contact js develop", ServiceType.Weibo);
            }
            if (retVal != null && retVal.size() > 0 && retVal.size() == array.size()) {
                return retVal;
            } else {
                throw ServiceUtils.createErrorInfo(-19995l, "contact js develop", null, ServiceType.Weibo);
            }
        }
        return null;
    }

//    public static List<String> toStringList(JSONArray array) throws ErrorInfo {
//        List<String> retVal = new ArrayList();
//        if (CollectionUtils.isNotEmpty(array)) {
//            try {
//                Type type = new TypeToken<ArrayList<String>>() {
//                }.getType();
//                retVal = engine.fromJson(array.toString(), type);
//            } catch (Exception e) {
//                throw new ErrorInfo(-19995l, "contact js develop", ServiceType.Weibo);
//            }
//            if (retVal != null && retVal.size() > 0 && retVal.size() == array.size()) {
//                return retVal;
//            } else {
//                throw new ErrorInfo(-19995l, "contact js develop", ServiceType.Weibo);
//            }
//        }
//        return null;
//    }

}
