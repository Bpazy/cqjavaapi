package com.github.bpazy.sender;

import com.github.bpazy.util.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class CqSender {
    private static final Logger logger = LoggerFactory.getLogger(CqSender.class);
    private static final CqSender INSTANCE = new CqSender();

    private DatagramSocket server;

    public CqSender() {
        try {
            server = new DatagramSocket();
        } catch (SocketException e) {
            logger.error("初始化CqSender失败", e);
        }
    }

    public static CqSender getDefaultSender() {
        return INSTANCE;
    }

    private DatagramPacket buildPacket(String flag, String msg) {
        try {
            byte[] send = (flag + new String(Encoder.encode(msg), "UTF8")).getBytes("UTF8");
            return new DatagramPacket(send, send.length, InetAddress.getByName("127.0.0.1"), 11235);
        } catch (UnsupportedEncodingException | UnknownHostException e) {
            logger.error("Encode error or Host name error", e);
        }
        throw new Error("Encode error or Host name error");
    }

    private void sendPacket(String flag, String msg) throws IOException {
        DatagramPacket packet = buildPacket(flag + " ", msg);
        server.send(packet);
    }

    public void sendPrivateMsg(String qq, String msg) throws IOException {
        sendPacket("PrivateMessage " + qq, msg);
    }

    public void sendGroupMsg(String groupMsg, String msg) throws IOException {
        sendPacket("GroupMessage " + groupMsg, msg);
    }

    public void sendDiscussMsg(String discussID, String msg) throws IOException {
        sendPacket("DiscussMessage " + discussID, msg);
    }
}
