package com.github.bpazy;

import com.github.bpazy.message.PrivateMessage;
import com.github.bpazy.server.PluginServer;
import com.github.bpazy.serviceimpl.CqMessageHandler;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class Main {
    public static void main(String[] args) {
        PluginServer server = new PluginServer();

        server.addMessageHandler(new CqMessageHandler() {
            @Override
            public boolean privateMessage(PrivateMessage msg) {
                return super.privateMessage(msg);
            }
        });
    }
}
