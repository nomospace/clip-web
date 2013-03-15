package com.clip.web.service;

import com.clip.core.bean.ReturnBean;
import com.clip.web.dao.StatusDao;
import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.weibo.TokenUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StatusService {
    @Resource
    private StatusDao statusDao;

    public Boolean addStatus(List statuses) {
        return statusDao.addStatus(statuses);
    }

    public JSONObject updateStatus(String type, String status) {
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken();
        Boolean success = false;
        if (type.equals(CoreConstants.SINA_WEIBO)) {
            Timeline tm = new Timeline();
            tm.client.setToken(token);
            try {
                Status statusData = tm.UpdateStatus(status);
                System.out.println(statusData.toString());
                success = true;
            } catch (WeiboException e) {
                e.printStackTrace();
            }
        } else if (type.equals(CoreConstants.QQ_WEIBO)) {
        }
        JSONObject resultJson = JSONObject.fromObject(success);
        JSONObject dataJson = resultJson.optJSONObject("data");
        JSONObject result = new ReturnBean(true, null, dataJson).toJSONObject();
        return result;
    }

}
