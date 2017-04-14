package com.github.bpazy.message;

import com.github.bpazy.util.Decoder;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class PrivateMessage {
    private String QQ;
    private String encodedText;

    public PrivateMessage(String QQ, String encodedText) {
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

    public String getText() {
        return Decoder.silentDecode(encodedText);
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "QQ='" + QQ + '\'' +
                ", encodedText='" + encodedText + '\'' +
                ", decodedText='" + getText() + '\'' +
                '}';
    }
}
