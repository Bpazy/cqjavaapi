package com.github.bpazy.cqjavaapi.command.group;


import com.github.bpazy.cqjavaapi.message.GroupMessage;

/**
 * Created by Ziyuan
 * on 2017/4/19
 */
public abstract class GroupCommand {
    public abstract String cmd();

    public boolean response(GroupMessage msg, String[] args) {
        String[] split = msg.getText().split(" ");
        return split[0].equals("/" + cmd()) && doSomething(msg, args);
    }

    public abstract boolean doSomething(GroupMessage msg, String[] args);
}
