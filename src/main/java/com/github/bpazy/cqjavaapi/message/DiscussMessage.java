package com.github.bpazy.cqjavaapi.message;

import com.github.bpazy.cqjavaapi.util.Decoder;
import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
@Data
public class DiscussMessage {
    private String discussID;
    private String QQ;
    private String encodedText;

    public DiscussMessage(String discussID, String QQ, String encodedText) {
        this.discussID = discussID;
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getText() {
        return Decoder.silentDecode(encodedText);
    }
}
