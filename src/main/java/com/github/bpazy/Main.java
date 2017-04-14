package com.github.bpazy;

import com.github.bpazy.handler.CqMessageHandler;
import com.github.bpazy.message.DiscussMessage;
import com.github.bpazy.message.PrivateMessage;
import com.github.bpazy.sender.CqSilentSender;
import com.github.bpazy.server.CqServer;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class Main {
    public static void main(String[] args) {
        CqServer server = new CqServer();

        server.addMessageHandler(new CqMessageHandler() {
            @Override
            public boolean privateMessage(PrivateMessage msg) {
                System.out.println(msg.getText());
                return false;
            }

            @Override
            public boolean discussMessage(DiscussMessage msg) {
                if (msg.getText().equals("我爱你")) {
                    CqSilentSender.getDefaultSender().sendDiscussMsg(msg.getDiscussID(), "我也爱你");
                }
                return false;
            }
        });

        server.addMessageHandler(new CqMessageHandler() {
            @Override
            public boolean privateMessage(PrivateMessage msg) {
                System.out.println("2: " + msg.getText());
                return true;
            }

            @Override
            public boolean discussMessage(DiscussMessage msg) {
                if (msg.getText().equals("我爱你")) {
                    CqSilentSender.getDefaultSender().sendDiscussMsg(msg.getDiscussID(), "我恨你");
                }
                return true;
            }
        });
        server.listenAndServe(9999);
    }
}
