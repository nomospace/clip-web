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
 * ���@ResponseBody ����ֵ���룬ͬʱ�ֲ��ƻ�@RequestBodyע��Ĺ��ܡ�
 * <p>
 * Spring mvc�У�������ע���@ResponseBody�ķ���ֵ������ʵ��HttpMessageConverter&lt;T&gt;�ӿ�������
 * ʵ��HttpMessageConverter��ʵ������ݷ���T��������Ӧ��ְ��
 * �統TΪStringʱ������StringHttpMessageConverter������
 * ��TΪBufferedImageʱ������BufferedImageHttpMessageConverter������
 * ��TΪObjectʱ������MappingJacksonHttpMessageConverter������
 * ����
 * </p>
 * <p>
 * ������StringHttpMessageConverter�У����ص�Content-Typeͷ��û�и�������chartset=***��
 * ������ĳЩ���������ʾutf-8ʱ����"?"�����롣
 * </p>
 * <p>
 * һ�ֽ������ķ�ʽ�ǽ�StringHttpMessageConverter�����ã�
 * supportedMediaTypes����Ϊtext/plain;charset=utf-8��
 * ��ᵼ��ʹ��@RequestBody��ʱ�����415(UNSUPPORTED_MEDIA_TYPE)����
 * ��Ϊ���ַ�ʽ������ֻ����mediaType��text/plain�������
 * </p>
 * <p>
 * �����ԭ����StringHttpMessageConverterͷ�ϸ���;chartset=utf-8���������ǲ�ǿ��text/plain
 * </p>
 * <p>
 * <b>���ʹ��:</b><br/>
 * ��spring-mvc�����ļ��н�ԭ����&lt;mvc:annotation-driven/&gt;�ĳ����±�ǩ��<br/>
 * &lt;mvc:annotation-driven&gt;<br/>
 * &lt;mvc:message-converters register-defaults="false"&gt;<br/>
 * &lt;bean class="����"/&gt;<br/>
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
     * ǿ��@ResponseBodyͷΪcharset=utf-8
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
     * ǿ��@RequestBodyΪutf-8����
     *
     * @param clazz        clazz
     * @param inputMessage inputMessage
     * @return ����RequestBody
     * @throws java.io.IOException
     */
    @Override
    protected String readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException {
        return FileCopyUtils.copyToString(new InputStreamReader(inputMessage.getBody(), UTF8_CHAR_SET));
    }

    /**
     * <p/>
     * �����л�ȡ�ı����ȵ����õĳ���DEFAULT_CHARSET������ISO-8859-1����ġ�
     * �������д�˷������ᵼ�������ĵ�����ı����ض�
     * <p/>
     *
     * @param s           context
     * @param contentType mediaType
     * @return �ı�byte����
     */
    @Override
    protected Long getContentLength(String s, MediaType contentType) {
        return (long) s.getBytes(UTF8_CHAR_SET).length;
    }

}
