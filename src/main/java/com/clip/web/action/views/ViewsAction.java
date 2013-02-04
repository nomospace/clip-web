package com.clip.web.action.views;

import com.clip.web.utils.CoreConstants;
import com.clip.web.utils.Oauth4Sina;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;
import weibo4j.http.AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("viewsAction")
public class ViewsAction {
    private static Log logger = LogFactory.getLog(ViewsAction.class);
    private static Oauth4Sina oauth4Sina = new Oauth4Sina();
    private static AccessToken weiboToken;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        HttpSession session = request.getSession();
        if (weiboToken == null) {
            String code = (String) session.getAttribute(CoreConstants.SINA_WEIBO_CODE);
            if (code != null) {
                try {
                    weiboToken = oauth4Sina.getAccessTokenByCode(code);
                    mav.addObject("weiboToken", weiboToken);
                    System.out.println(weiboToken);
                } catch (WeiboException e) {
                    if (401 == e.getStatusCode()) {
                        logger.info("Unable to get the access token.");
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            mav.addObject("weiboToken", weiboToken);
        }
        return mav;
    }

    @RequestMapping("/404")
    public String to400(HttpServletResponse response) {
        response.setStatus(404);
        return "common/404";
    }

    @RequestMapping("/500")
    public String to500(HttpServletResponse response) {
        response.setStatus(500);
        return "common/500";
    }

    @RequestMapping("/403")
    public String to403(HttpServletResponse response) {
        response.setStatus(403);
        return "common/403";
    }

}
