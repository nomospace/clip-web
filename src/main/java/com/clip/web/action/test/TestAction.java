package com.clip.web.action.test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller("testAction")
public class TestAction {
    @RequestMapping("/xss")
    public String xss() {
        return "xss";
    }

    @RequestMapping("/image_xss")
    public ResponseEntity<byte[]> imageXss(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setStatus(401);
//        response.setContentType("image/gif");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "image/png; charset=utf-8");
        responseHeaders.add("HTTP/1.1", "401 Unauthorized");


        OutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream = new BufferedInputStream(
                new FileInputStream(request.getSession()
                        .getServletContext().getRealPath("/static/images/octocat-icon.png")));
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
    }

}
