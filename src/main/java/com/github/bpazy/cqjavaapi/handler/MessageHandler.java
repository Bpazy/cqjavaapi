package com.github.bpazy.cqjavaapi.handler;

import com.github.bpazy.cqjavaapi.message.*;

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

    boolean groupAdmin(GroupAdmin msg);
}
