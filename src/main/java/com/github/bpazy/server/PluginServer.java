package com.github.bpazy.server;

import com.github.bpazy.message.*;
import com.github.bpazy.service.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class PluginServer {
    private static final Logger logger = LoggerFactory.getLogger(PluginServer.class);
    private List<MessageHandler> messageHandlers = new ArrayList<>();

    public void start(int port) {
        heartbeatThread();
        messageThread(port);
    }

    private void messageThread(int port) {
        new Thread(() -> {
            try {
                DatagramSocket server = new DatagramSocket(port);
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                while (true) {
                    server.receive(packet);
                    String msg = buildMessage(packet);
                    logger.info("Got message: {}", msg);
                    dispatchMessage(msg);
                }
            } catch (SocketException e) {
                logger.error("Server start failed", e);
            } catch (IOException e) {
                logger.error("server.receive failed", e);
            }
        }).start();
    }

    private void heartbeatThread() {
        new Thread(() -> {

        }).start();
    }

    protected String buildMessage(DatagramPacket packet) {
        byte[] data = packet.getData();
        data = Arrays.copyOf(data, packet.getLength());
        return new String(data);
    }

    protected void dispatchMessage(String msg) {
        String[] msgs = msg.split(" ");
        switch (msgs[0]) {
            case "ServerHello":
                break;
            case "PrivateMessage":
                messageHandlers.forEach(h -> h.privateMessage(new PrivateMessage(msgs[1], msgs[2])));
                break;
            case "GroupMessage":
                messageHandlers.forEach(h -> h.groupMessage(new GroupMessage(msgs[1], msgs[2], msgs[3])));
                break;
            case "DiscussMessage":
                messageHandlers.forEach(h -> h.discussMessage(new DiscussMessage(msgs[1], msgs[2], msgs[3])));
                break;
            case "GroupMemberDecrease":
                messageHandlers.forEach(h -> h.groupMemberDecrease(new GroupMemberDecrease(msgs[1], msgs[2], msgs[3])));
                break;
            case "GroupMemberIncrease":
                messageHandlers.forEach(h -> h.groupMemberIncrease(new GroupMemberIncrease(msgs[1], msgs[2], msgs[3])));
                break;
        }
    }

    public void addMessageHandler(MessageHandler handler) {
        messageHandlers.add(handler);
    }
}
