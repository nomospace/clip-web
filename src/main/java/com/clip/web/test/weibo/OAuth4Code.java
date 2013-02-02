package com.clip.web.test.weibo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OAuth4Code {
    private static Log logger = LogFactory.getLog(OAuth4Code.class);

    public static void main(String[] args) throws WeiboException, IOException {
        Oauth oauth = new Oauth();
        BareBonesBrowserLaunch.openURL(oauth.authorize("code", args[0], args[1]));
        System.out.println(oauth.authorize("code", args[0], args[1]));
        System.out.print("Hit enter when it's done.[Enter]:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        logger.info("code: " + code);
        try {
            System.out.println(oauth.getAccessTokenByCode(code));
        } catch (WeiboException e) {
            if (401 == e.getStatusCode()) {
                logger.info("Unable to get the access token.");
            } else {
                e.printStackTrace();
            }
        }
    }

}
