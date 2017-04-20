package com.github.bpazy.cqjavaapi.message;

import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/15
 */
@Data
public class GroupAdmin {
    private String groupID;
    private String subType; // 1被取消管理员 2被设置管理员
    private String beingOperateQQ;

    public GroupAdmin(String groupID, String subType, String beingOperateQQ) {
        this.groupID = groupID;
        this.subType = subType;
        this.beingOperateQQ = beingOperateQQ;
    }
}
