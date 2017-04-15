package com.github.bpazy.sender;

import com.github.bpazy.util.Encoder;
import com.google.common.base.Joiner;
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
    private static final Joiner blankJoiner = Joiner.on(" ").skipNulls();

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

    private DatagramPacket buildMsgPacket(String flag, String msg) {
        try {
            byte[] send = (flag + new String(Encoder.encode(msg), "UTF8")).getBytes("UTF8");
            return new DatagramPacket(send, send.length, InetAddress.getByName("127.0.0.1"), 11235);
        } catch (UnsupportedEncodingException | UnknownHostException e) {
            logger.error("Encode error or Host name error", e);
        }
        throw new Error("Encode error or Host name error");
    }

    private void sendMsgPacket(String flag, String msg) throws IOException {
        DatagramPacket packet = buildMsgPacket(flag + " ", msg);
        server.send(packet);
    }

    private void sendCmdPacket(String... args) throws IOException {
        DatagramPacket packet = buildCmdPacket(args);
        server.send(packet);

    }

    private DatagramPacket buildCmdPacket(String... args) {
        try {
            byte[] sentBytes = blankJoiner.join(args).getBytes("UTF8");
            return new DatagramPacket(sentBytes, sentBytes.length, InetAddress.getByName("127.0.0.1"), 11235);
        } catch (UnknownHostException | UnsupportedEncodingException e) {
            logger.error("Encode error or Host name error", e);
        }
        throw new Error("Encode error or Host name error");
    }

    public void sendPrivateMsg(String qq, String msg) throws IOException {
        sendMsgPacket("PrivateMessage " + qq, msg);
    }

    public void sendGroupMsg(String groupMsg, String msg) throws IOException {
        sendMsgPacket("GroupMessage " + groupMsg, msg);
    }

    public void sendDiscussMsg(String discussID, String msg) throws IOException {
        sendMsgPacket("DiscussMessage " + discussID, msg);
    }

    public void sendGroupBan(String groupID, String qq, String duration) throws IOException {
        sendCmdPacket("GroupBan", groupID, qq, duration);
    }
}
