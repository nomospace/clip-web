package com.clip.web.utils.i18n;

import com.clip.web.utils.SysProperties;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * 国际化资源工具
 * <p>默认是当前系统环境的语言，可以通过<code>setLocale</code>来设定语言</p>
 */
public class MultiSystemI18NSupport {

    private ReloadableResourceBundleMessageSource messageSource;

    private Locale locale = new Locale(SysProperties.SYS_LANG, SysProperties.SYS_COUNTRY);

    public ReloadableResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * 获取资源文件中的消息，不带参数
     *
     * @param code 资源文件中的键
     * @return 字符串消息
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, this.locale);
    }

    /**
     * 获取资源文件中的消息，带参数
     *
     * @param code 资源文件中的键
     * @param args 字符串数组，将替换资源文件中{0} {1} ...占位符<br/>
     *             <b>注：args通常是一些数，不应该有消息体，否则无法满足国际化要求</b>
     * @return 占位符替换处理后的消息
     */
    public String getMessage(String code, String... args) {
        try {
            return messageSource.getMessage(code, args, this.locale);
        } catch (NoSuchMessageException ex) {
//            ex.printStackTrace();
            return "";
        }
    }

    public MultiSystemI18NSupport(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MultiSystemI18NSupport(ReloadableResourceBundleMessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public MultiSystemI18NSupport() {
    }

}
