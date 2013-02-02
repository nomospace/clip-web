package com.clip.core.utils.http;

import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;

public class DefaultIntAndLongValueProcessor implements DefaultValueProcessor {
    static Object defaultValue = JSONNull.getInstance();

    public Object getDefaultValue(Class aClass) {
        if (aClass != null && Integer.class.isAssignableFrom(aClass)) {
            return defaultValue;
        }
        return JSONNull.getInstance();
    }

}
