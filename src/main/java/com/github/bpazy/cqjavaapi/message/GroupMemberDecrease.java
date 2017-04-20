package com.github.bpazy.cqjavaapi.message;

import lombok.Data;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
@Data
public class GroupMemberDecrease {
    private String groupID;
    private String QQ;
    private String operatedQQ;

    public GroupMemberDecrease(String groupID, String QQ, String operatedQQ) {
        this.groupID = groupID;
        this.QQ = QQ;
        this.operatedQQ = operatedQQ;
    }
}
