package com.github.bpazy.cqjavaapi.message;

import com.github.bpazy.cqjavaapi.util.Decoder;
import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
@Data
public class PrivateMessage {
    private String QQ;
    private String encodedText;

    public PrivateMessage(String QQ, String encodedText) {
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getText() {
        return Decoder.silentDecode(encodedText);
    }
}
