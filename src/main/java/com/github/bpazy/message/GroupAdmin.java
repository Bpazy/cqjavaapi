package com.github.bpazy.message;

/**
 * Created by Ziyuan
 * on 2017/4/15
 */
public class GroupAdmin {
    private String groupID;
    private String subType; // 1被取消管理员 2被设置管理员
    private String beingOperateQQ;

    public GroupAdmin(String groupID, String subType, String beingOperateQQ) {
        this.groupID = groupID;
        this.subType = subType;
        this.beingOperateQQ = beingOperateQQ;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getBeingOperateQQ() {
        return beingOperateQQ;
    }

    public void setBeingOperateQQ(String beingOperateQQ) {
        this.beingOperateQQ = beingOperateQQ;
    }

    @Override
    public String toString() {
        return "GroupAdmin{" +
                "groupID='" + groupID + '\'' +
                ", subType='" + subType + '\'' +
                ", beingOperateQQ='" + beingOperateQQ + '\'' +
                '}';
    }
}
