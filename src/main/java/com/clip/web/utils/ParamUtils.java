package com.clip.web.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数辅助制造工具
 */
public class ParamUtils {

    /**
     * 负责填充标准的请求参数，包含 action,client
     *
     * @param target     将在这个对象的基础上追加action和client参数，若对象为空，则实例化一个新的HashMap
     * @param actionName 请求名称
     * @return 填充完后的 Map 对象
     */
    public static Map fillRequiredArgs(Map target, String actionName) {
        if (target == null) {
            target = new HashMap();
        }
        target.put(CoreConstants.ACTION_KEY, actionName);
        target.put("client", SysProperties.SYS_UUID);
        return target;
    }

    /**
     * 负责创建新的 HashMap，包含 action,client
     *
     * @param actionName 请求名称
     * @return 创建完后的 Map 对象
     */
    public static Map getRequiredArgs(String actionName) {
        return fillRequiredArgs(null, actionName);
    }

}
