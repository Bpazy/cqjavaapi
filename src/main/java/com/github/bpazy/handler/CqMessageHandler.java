package com.github.bpazy.handler;

import com.github.bpazy.message.*;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class CqMessageHandler implements MessageHandler {
    @Override
    public boolean privateMessage(PrivateMessage msg) {
        return false;
    }

    @Override
    public boolean groupMessage(GroupMessage msg) {
        return false;
    }

    @Override
    public boolean discussMessage(DiscussMessage msg) {
        return false;
    }

    @Override
    public boolean groupMemberDecrease(GroupMemberDecrease msg) {
        return false;
    }

    @Override
    public boolean groupMemberIncrease(GroupMemberIncrease msg) {
        return false;
    }

    @Override
    public boolean groupAdmin(GroupAdmin msg) {
        return false;
    }
}
