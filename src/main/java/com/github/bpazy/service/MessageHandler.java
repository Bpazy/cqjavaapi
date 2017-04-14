package com.github.bpazy.service;

import com.github.bpazy.message.*;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public interface MessageHandler {

    boolean privateMessage(PrivateMessage msg);

    boolean groupMessage(GroupMessage msg);

    boolean discussMessage(DiscussMessage msg);

    boolean groupMemberDecrease(GroupMemberDecrease msg);

    boolean groupMemberIncrease(GroupMemberIncrease msg);
}
