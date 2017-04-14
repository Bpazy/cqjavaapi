package com.github.bpazy.sender;

import java.io.IOException;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class CqSilentSender {
    private CqSender sender = new CqSender();
    private static CqSilentSender INSTANCE = new CqSilentSender();

    public static CqSilentSender getDefaultSender() {
        return INSTANCE;
    }

    public void sendPrivateMsg(String qq, String msg) {
        try {
            sender.sendPrivateMsg(qq, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendGroupMsg(String groupMsg, String msg) {
        try {
            sender.sendGroupMsg(groupMsg, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDiscussMsg(String discussID, String msg) {
        try {
            sender.sendDiscussMsg(discussID, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
