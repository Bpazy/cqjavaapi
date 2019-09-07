package com.github.bpazy.cqjavaapi.server;

import com.github.bpazy.cqjavaapi.handler.MessageHandler;
import com.github.bpazy.cqjavaapi.message.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class CqServer {
    private static final Logger logger = LoggerFactory.getLogger(CqServer.class);
    private static final int HEARTBEAT_INTERVAL = 1000 * 5;

    private List<MessageHandler> messageHandlers = new ArrayList<>();

    public void listenAndServe(int port) {
        heartbeatThread(port);
        receiveMessageThread(port);
    }

    private void receiveMessageThread(int port) {
        new Thread(() -> {
            try (DatagramSocket server = new DatagramSocket(port)) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                while (true) {
                    server.receive(packet);
                    String msg = buildMessage(packet);
                    // if is not heartbeat message
                    if (!"ServerHello".equals(msg)) {
                        logger.debug("Got message: {}", msg);
                    }
                    dispatchMessage(msg);
                }
            } catch (SocketException e) {
                logger.error("Server listenAndServe failed", e);
            } catch (IOException e) {
                logger.error("server.receive failed", e);
            }
        }).start();
    }

    private void heartbeatThread(int port) {
        new Thread(() -> {
            try (DatagramSocket heartbeatSocket = new DatagramSocket()) {
                DatagramPacket heartbeatPacket = buildHeartbeatPacket("ClientHello " + port);
                while (true) {
                    heartbeatSocket.send(heartbeatPacket);
                    Thread.sleep(HEARTBEAT_INTERVAL);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private DatagramPacket buildHeartbeatPacket(String msg) throws UnknownHostException {
        byte[] bytes = msg.getBytes();
        return new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 11235);
    }

    private String buildMessage(DatagramPacket packet) {
        byte[] data = packet.getData();
        data = Arrays.copyOf(data, packet.getLength());
        return new String(data);
    }

    protected void dispatchMessage(String msg) {
        String[] args = msg.split(" ");
        switch (args[0]) {
            case "ServerHello":
                break;
            case "PrivateMessage":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.privateMessage(new PrivateMessage(args[1], args[2]));
                    if (finish) break;
                }
                break;
            case "GroupMessage":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.groupMessage(new GroupMessage(args[1], args[2], args[3]));
                    if (finish) break;
                }
                break;
            case "DiscussMessage":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.discussMessage(new DiscussMessage(args[1], args[2], args[3]));
                    if (finish) break;
                }
                break;
            case "GroupMemberDecrease":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.groupMemberDecrease(new GroupMemberDecrease(args[1], args[2], args[3]));
                    if (finish) break;
                }
                break;
            case "GroupMemberIncrease":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.groupMemberIncrease(new GroupMemberIncrease(args[1], args[2], args[3]));
                    if (finish) break;
                }
                break;
            case "GroupAdmin":
                for (MessageHandler handler : messageHandlers) {
                    boolean finish = handler.groupAdmin(new GroupAdmin(args[1], args[2], args[3]));
                    if (finish) break;
                }
                break;
            default:
                logger.warn("Unknown event, {}", msg);
        }
    }

    public void addMessageHandler(MessageHandler handler) {
        messageHandlers.add(handler);
    }
}
