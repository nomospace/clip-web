package com.clip.web.utils;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 解决@ResponseBody 返回值乱码，同时又不破坏@RequestBody注解的功能。
 * <p>
 * Spring mvc中，对于有注解的@ResponseBody的返回值，都由实现HttpMessageConverter&lt;T&gt;接口来处理。
 * 实现HttpMessageConverter的实现类根据泛型T来处理相应的职责，
 * 如当T为String时，就由StringHttpMessageConverter来处理，
 * 当T为BufferedImage时，就由BufferedImageHttpMessageConverter来处理，
 * 当T为Object时，就由MappingJacksonHttpMessageConverter来处理，
 * ……
 * </p>
 * <p>
 * 但是在StringHttpMessageConverter中，返回的Content-Type头中没有附带参数chartset=***，
 * 导致在某些浏览器中显示utf-8时出现"?"的乱码。
 * </p>
 * <p>
 * 一种解决乱码的方式是将StringHttpMessageConverter类设置，
 * supportedMediaTypes设置为text/plain;charset=utf-8，
 * 这会导致使用@RequestBody的时候出现415(UNSUPPORTED_MEDIA_TYPE)错误，
 * 因为这种方式声明了只允许mediaType是text/plain的情况。
 * </p>
 * <p>
 * 此类的原理即在StringHttpMessageConverter头上附带;chartset=utf-8参数，但是不强制text/plain
 * </p>
 * <p>
 * <b>如何使用:</b><br/>
 * 在spring-mvc配置文件中将原来的&lt;mvc:annotation-driven/&gt;改成以下标签：<br/>
 * &lt;mvc:annotation-driven&gt;<br/>
 * &lt;mvc:message-converters register-defaults="false"&gt;<br/>
 * &lt;bean class="此类"/&gt;<br/>
 * &lt;/mvc:message-converters&gt;<br/>
 * &lt;/mvc:annotation-driven&gt;
 * </p>
 *
 * @author Daniel
 * @see org.springframework.http.converter.StringHttpMessageConverter
 * @see org.springframework.http.converter.HttpMessageConverter
 */
public class Utf8CharsetHttpMessageConverter extends StringHttpMessageConverter {

    public static final Charset UTF8_CHAR_SET = Charset.forName("utf-8");

    /**
     * 强制@ResponseBody头为charset=utf-8
     *
     * @param s             context
     * @param outputMessage outputMessage
     * @throws java.io.IOException
     */
    @Override
    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        MediaType mediaType = outputMessage.getHeaders().getContentType();
        MediaType mediaTypeForceCharset = new MediaType(
                mediaType.getType(), mediaType.getSubtype(), UTF8_CHAR_SET
        );
        outputMessage.getHeaders().setContentType(mediaTypeForceCharset);
        super.writeInternal(s, outputMessage);
    }

    /**
     * 强制@RequestBody为utf-8编码
     *
     * @param clazz        clazz
     * @param inputMessage inputMessage
     * @return 返回RequestBody
     * @throws java.io.IOException
     */
    @Override
    protected String readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException {
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), UTF8_CHAR_SET));
    }

    /**
     * <p/>
     * 父类中获取文本长度的是用的常数DEFAULT_CHARSET，根据ISO-8859-1计算的。
     * 如果不重写此方法，会导致有中文的情况文本被截断
     * <p/>
     *
     * @param s           context
     * @param contentType mediaType
     * @return 文本byte长度
     */
    @Override
    protected Long getContentLength(String s, MediaType contentType) {
        return (long) s.getBytes(UTF8_CHAR_SET).length;
    }

}
