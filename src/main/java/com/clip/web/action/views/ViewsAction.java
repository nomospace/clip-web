package com.clip.web.action.views;

import com.clip.web.action.CommonAction;
import com.clip.web.model.User;
import com.clip.web.service.UserService;
import com.clip.web.utils.CoreConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller("viewsAction")
public class ViewsAction extends CommonAction {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
//        TokenUtils tokenUtils = new TokenUtils();
//        String token = tokenUtils.getToken(request);
//        String tokenType = tokenUtils.getTokenType(request);
//        if (token != null && tokenType != null) {
//            String uid = tokenUtils.getOauth2TokenUidByTypeAndToken(tokenType, token);
//            User user = userService.getUserByUidAndType(uid, tokenType);
//            if (uid != null) {
//                if (user != null) {
//                    userService.updateToken(user.getId(), tokenType, token);
//                } else {
//                    user = userService.addUser(uid, tokenType, token);
//                }
//                this.updateCurrentUserInSession(user, request);
//                this.addObject(request, mav);
//                mav.addObject("statuses", this.getUserTimeline(user.getId(), tokenType, token).getStatuses());
//            }
//        }
        this.addObject(request, mav);
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView home(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = this.index(request);
        mav.addObject("isLogin", false);
        mav.addObject("fromHome", true);
        return mav;
    }

    @RequestMapping("/setting")
    public ModelAndView settings(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("setting");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user == null) {
            mav = this.index(request);
        } else {
            mav.addObject("user", user);
        }
        return mav;
    }

    @RequestMapping("/user/{username}")
    public ModelAndView showName(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("user");
//        User user = userService.getUserByName(name);
//        if (user != null) {
//            String uid = user.getUid();
//            String token = user.getSina_weibo_token();
//            String tokenType = CoreConstants.SINA_WEIBO;
//            if (uid.isEmpty()) {
//                uid = user.getQq_weibo_uid();
//                token = user.getQq_weibo_token();
//                tokenType = CoreConstants.QQ_WEIBO;
//            }
//            this.addObject(request, mav);
//            mav.addObject("ouid", uid);
//            mav.addObject("ouser", user);
//            mav.addObject("statuses", this.getUserTimeline(user.getId(), tokenType, token).getStatuses());
//        } else {
//            mav = this.to404(response);
//        }
        return mav;
    }

    @RequestMapping("/404")
    public ModelAndView to404(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("404");
        this.addObject(request, mav);
        response.setStatus(404);
        return mav;
    }

    @RequestMapping("/500")
    public ModelAndView to500(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("500");
        this.addObject(request, mav);
        response.setStatus(500);
        return mav;
    }

    @RequestMapping("/403")
    public ModelAndView to403(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("403");
        this.addObject(request, mav);
        response.setStatus(403);
        return mav;
    }

}
