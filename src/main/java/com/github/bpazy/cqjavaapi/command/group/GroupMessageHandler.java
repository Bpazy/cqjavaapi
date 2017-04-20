package com.github.bpazy.cqjavaapi.command.group;

import com.github.bpazy.cqjavaapi.handler.CqMessageHandler;
import com.github.bpazy.cqjavaapi.message.GroupMessage;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ziyuan
 * on 2017/4/19
 */
public class GroupMessageHandler extends CqMessageHandler {
    private List<GroupCommand> cmdList = new ArrayList<>();

    public void addCommandHandler(GroupCommand cmd) {
        cmdList.add(cmd);
    }

    @Override
    public boolean groupMessage(GroupMessage msg) {
        if (!checkGroupMessageIsCmd(msg)) {
            return false;
        }
        boolean result = false;
        for (GroupCommand cmd : cmdList) {
            String[] args = detachCommand(msg);
            result = result || cmd.response(msg, args);
        }
        return result;
    }

    private String[] detachCommand(GroupMessage msg) {
        String text = msg.getText();
        String[] split = text.split(" ");
        if (split.length == 0) {
            return new String[]{""};
        }
        return Arrays.copyOfRange(split, 1, split.length);
    }

    private boolean checkGroupMessageIsCmd(GroupMessage msg) {
        String text = msg.getText();
        if (!stringStartWithSlash(text)) {
            return false;
        }
        String[] split = text.split(" ");
        for (GroupCommand cmd : cmdList) {
            String cmdStr = "/" + cmd.cmd();
            boolean has = cmdStr.equals(split[0]);
            if (has) return true;
        }
        return false;
    }

    private boolean stringStartWithSlash(String text) {
        return !Strings.isNullOrEmpty(text) && text.charAt(0) == '/';
    }
}
