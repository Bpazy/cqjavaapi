package com.github.bpazy.message;

/**
 * Created by Ziyuan
 * on 2017/4/14
 */
public class GroupMemberDecrease {
    private String groupID;
    private String QQ;
    private String operatedQQ;

    public GroupMemberDecrease(String groupID, String QQ, String operatedQQ) {
        this.groupID = groupID;
        this.QQ = QQ;
        this.operatedQQ = operatedQQ;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getOperatedQQ() {
        return operatedQQ;
    }

    public void setOperatedQQ(String operatedQQ) {
        this.operatedQQ = operatedQQ;
    }

    @Override
    public String toString() {
        return "GroupMemberDecrease{" +
                "groupID='" + groupID + '\'' +
                ", QQ='" + QQ + '\'' +
                ", operatedQQ='" + operatedQQ + '\'' +
                '}';
    }
}
