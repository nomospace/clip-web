package com.clip.web.action.views;

import com.clip.web.action.CommonAction;
import com.clip.web.model.User;
import com.clip.web.service.UserService;
import com.clip.web.utils.weibo.TokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import weibo4j.model.WeiboException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller("viewsAction")
public class ViewsAction extends CommonAction {
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(final HttpServletRequest request) throws WeiboException {
        ModelAndView mav = new ModelAndView("index");
        TokenUtils tokenUtils = new TokenUtils();
        String token = tokenUtils.getToken(request);
        mav.addObject("weiboToken", token);
        String tokenType = tokenUtils.getTokenType(request);
        if (token != null && tokenType != null) {
            User user = userService.getUser(tokenType, token);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", user);
                mav.addObject("user", user);
            } else {
                String uid = tokenUtils.getUid();
                userService.addUser(tokenType, token, uid);
            }
        }
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

    @RequestMapping("/xss")
    public String xss() {
        return "xss";
    }

    @RequestMapping("/image_xss")
    public ResponseEntity<byte[]> imageXss(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(401);
//        response.setContentType("image/gif");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "image/jpeg; charset=utf-8");
        responseHeaders.add("HTTP/1.1", "401 Unauthorized");


        OutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream(request.getSession()
                        .getServletContext().getRealPath("/WEB-INF/classes/baidu.gif")));
        byte[] data = new byte[1024];
        try {
            for (int i = inputStream.read(data); i > 0; i = inputStream
                    .read(data)) {
                outputStream.write(data, 0, i);
            }
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<byte[]>(data, responseHeaders, HttpStatus.UNAUTHORIZED);
//        response.sendRedirect("http://www.baidu.com/img/shouye_b5486898c692066bd2cbaeda86d74448.gif");
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
