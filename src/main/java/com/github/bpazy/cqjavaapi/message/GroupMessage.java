package com.github.bpazy.cqjavaapi.message;

import com.github.bpazy.cqjavaapi.util.Decoder;
import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
@Data
public class GroupMessage {
    private String groupID;
    private String QQ;
    private String encodedText;

    public GroupMessage(String groupID, String QQ, String encodedText) {
        this.groupID = groupID;
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getText() {
        return Decoder.silentDecode(encodedText);
    }
}
