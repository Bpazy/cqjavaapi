package com.github.bpazy.message;

import com.github.bpazy.util.Decoder;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class GroupMessage {
    private String groupID;
    private String QQ;
    private String encodedText;

    public GroupMessage(String groupID, String QQ, String encodedText) {
        this.groupID = groupID;
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
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
}
