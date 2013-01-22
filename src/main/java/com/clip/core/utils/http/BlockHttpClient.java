package com.clip.core.utils.http;

import com.clip.core.utils.BeanTools;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class BlockHttpClient {

    public enum PushType {POST, GET}

    private static Log logger = LogFactory.getLog("BlockHttpClient");

    public static String callUrl(String urlStr, final Object requestObj, final PushType pushType) {
        Map para;
        if (!(requestObj instanceof Map)) {
            para = BeanTools.convertBeanToMap(requestObj);
        } else {
            para = (Map) requestObj;
        }
        // 把 long 和 int 的默认值转为 null
        JsonConfig config = new JsonConfig();
        config.registerDefaultValueProcessor(Integer.class, new DefaultIntAndLongValueProcessor());
        config.registerDefaultValueProcessor(Long.class, new DefaultIntAndLongValueProcessor());
        logger.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + ",http begin,url:" + urlStr + ",para:" + JSONObject.fromObject(para, config).toString());
        HttpURLConnection httpURLConnection = null;
        String paraStr = null;
        try {
            // 根据类型转换参数
            if (para != null) {
                if (pushType == PushType.POST) {
                    // post 方法
                    paraStr = JSONObject.fromObject(para, config).toString();
                } else {
                    // get 方法
                    StringBuffer params = new StringBuffer();
                    for (Iterator<?> iter = para.entrySet().iterator(); iter.hasNext(); ) {
                        Map.Entry<?, ?> element = (Map.Entry<?, ?>) iter.next();
                        if (element.getValue() != null) {
                            params.append(element.getKey().toString());
                            params.append("=");
                            params.append(URLEncoder.encode(element.getValue().toString(), "utf-8"));
                            params.append("&");
                        } else {
                            logger.info("para:" + element.getKey() + " is " + element.getValue());
                        }
                    }
                    if (params.length() > 0) {
                        params = params.deleteCharAt(params.length() - 1);
                    }
                    paraStr = params.toString();
                    if (urlStr.contains("?") && !paraStr.isEmpty()) {
                        urlStr += "&" + paraStr;
                    } else {
                        urlStr += "?" + paraStr;
                    }
                }
            }
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setUseCaches(false);
            if (pushType == PushType.POST) {
                httpURLConnection.setRequestMethod("POST");// 设置提交方法
            } else {
                httpURLConnection.setRequestMethod("GET");// 设置提交方法
            }
            httpURLConnection.setDoInput(true);
            httpURLConnection.setConnectTimeout(2 * 60 * 1000);// 连接超时时间
            httpURLConnection.setReadTimeout(20 * 60 * 1000);
            httpURLConnection.setInstanceFollowRedirects(true);
            if (pushType == PushType.POST) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type",
                        "application/json; charset=utf-8");
                httpURLConnection.connect();
                DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
                if (para != null) {
                    out.write(paraStr.getBytes("UTF-8"));
                }
                out.flush();
                out.close();
            }
            // 读取 post 之后的返回值
            BufferedReader in = new BufferedReader(
                    new InputStreamReader((InputStream) httpURLConnection.getInputStream(), "utf-8"));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            httpURLConnection.disconnect();// 断开连接
            logger.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + ",http finish,url:" + urlStr + ",para:" + JSONObject.fromObject(para, config).toString() + ",return:" + sb.toString());
            return sb.toString();
        } catch (Exception e) {
            logger.error("http error,url:" + urlStr + ",para:" + JSONObject.fromObject(para, config).toString(), e);
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return null;
    }


    public static String callUrl(final String urlStr, final Map para, final PushType pushType, HttpCallBack callBackFn) {
        String result = callUrl(urlStr, para, pushType);
        try {
            return callBackFn.doCallBack(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
