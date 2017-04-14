package com.github.bpazy.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class PluginClient {
    public DatagramPacket buildPacket(String msg, int port) throws Exception {
        byte[] bytes = msg.getBytes();
        return new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), port);
    }

    public DatagramSocket buildClient() throws SocketException {
        return new DatagramSocket();
    }
}
