package com.github.bpazy.cqjavaapi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class Decoder {
    private final static Base64.Decoder decoder = Base64.getDecoder();
    private static final Logger logger = LoggerFactory.getLogger(Decoder.class);

    public static String decode(String encodedStr) throws UnsupportedEncodingException {
        return new String(decoder.decode(encodedStr), "GB18030");
    }

    public static String silentDecode(String encodedStr) {
        try {
            return decode(encodedStr);
        } catch (UnsupportedEncodingException e) {
            logger.warn("对{}的GB18030解码失败", encodedStr);
        }
        return encodedStr;
    }
}
