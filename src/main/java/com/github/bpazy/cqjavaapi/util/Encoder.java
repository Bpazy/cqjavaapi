package com.github.bpazy.cqjavaapi.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class Encoder {
    public static byte[] encode(String msg) throws UnsupportedEncodingException {
        return Base64.getEncoder().encode(msg.getBytes("GB18030"));
    }
}
