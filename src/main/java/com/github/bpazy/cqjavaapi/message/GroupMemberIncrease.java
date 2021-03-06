package com.github.bpazy.cqjavaapi.message;

import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
@Data
public class GroupMemberIncrease {
    private String groupID;
    private String QQ;
    private String operatedQQ;

    public GroupMemberIncrease(String groupID, String QQ, String operatedQQ) {
        this.groupID = groupID;
        this.QQ = QQ;
        this.operatedQQ = operatedQQ;
    }
}
