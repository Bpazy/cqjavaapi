package com.github.bpazy.cqjavaapi.sender;

import com.github.bpazy.cqjavaapi.util.Encoder;
import com.google.common.base.Joiner;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;

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
            byte[] send = (flag + " " + new String(Encoder.encode(msg), "UTF8")).getBytes("UTF8");
            return new DatagramPacket(send, send.length, InetAddress.getByName("127.0.0.1"), 11235);
        } catch (UnsupportedEncodingException | UnknownHostException e) {
            logger.error("Encode error or Host name error", e);
        }
        throw new Error("Encode error or Host name error");
    }

    @SneakyThrows
    private void sendMsgPacket(String flag, String msg) {
        DatagramPacket packet = buildMsgPacket(flag, msg);
        server.send(packet);
    }

    @SneakyThrows
    private void sendCmdPacket(String... args) {
        DatagramPacket packet = buildCmdPacket(args);
        server.send(packet);

    }

    private DatagramPacket buildCmdPacket(String... args) {
        try {
            byte[] sentBytes = blankJoiner.join(args).getBytes(StandardCharsets.UTF_8);
            return new DatagramPacket(sentBytes, sentBytes.length, InetAddress.getByName("127.0.0.1"), 11235);
        } catch (UnknownHostException e) {
            logger.error("Encode error or Host name error", e);
        }
        throw new Error("Encode error or Host name error");
    }

    public void sendPrivateMsg(String qq, String msg) {
        sendMsgPacket("PrivateMessage " + qq, msg);
    }

    public void sendGroupMsg(String groupMsg, String msg) {
        sendMsgPacket("GroupMessage " + groupMsg, msg);
    }

    public void sendDiscussMsg(String discussID, String msg) {
        sendMsgPacket("DiscussMessage " + discussID, msg);
    }

    /**
     * @param duration 单位秒
     */
    public void sendGroupBan(String groupID, String qq, long duration) {
        sendCmdPacket("GroupBan", groupID, qq, duration + "");
    }
}
