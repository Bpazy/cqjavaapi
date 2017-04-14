package com.github.bpazy.message;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class DiscussMessage {
    private String discussID;
    private String QQ;
    private String encodedText;

    public DiscussMessage(String discussID, String QQ, String encodedText) {
        this.discussID = discussID;
        this.QQ = QQ;
        this.encodedText = encodedText;
    }

    public String getDiscussID() {
        return discussID;
    }

    public void setDiscussID(String discussID) {
        this.discussID = discussID;
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
}
