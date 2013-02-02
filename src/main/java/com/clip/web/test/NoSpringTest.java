package com.clip.web.test;

import com.clip.web.exception.ErrorInfo;
import com.clip.web.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoSpringTest {
    @Test
    public void test() {
        JSONObject obj = JSONObject.fromObject("{array:[\"a\",\"b\"]}");
//           JSONObject obj=JSONObject.fromObject("{}");
        JSONArray array = obj.optJSONArray("array");
//           List<String> strs=JSONArray.toList(array,new String(),new JsonConfig());
//           List<String>  array=()obj.optJSONArray("array");
        System.out.println(array);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Long>>() {
        }.getType();
        List<Long> ids = gson.fromJson("[1,2]", type);
        List<Long> list = new ArrayList<Long>();
//           Collections.;
        System.out.println(ids);
        JSONArray jsonArray = JSONArray.fromObject("[1,2]");
        List<Long> list2 = null;
        try {
            list2 = JsonUtils.toLongList(jsonArray);
        } catch (ErrorInfo errorInfo) {
            errorInfo.printStackTrace();
        }
//        List<Long> list3 = JsonUtils.toList(jsonArray);
        System.out.println(list2);
//        System.out.println(list3);
    }

}
