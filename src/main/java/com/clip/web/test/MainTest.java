package com.clip.web.test;

import org.jsoup.Jsoup;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";

        System.out.println(Jsoup.parse(html));
    }
}
