package com.demo.model;

import java.util.Date;

public class BankNode {
    private String nodeCode;

    private String nodeState;

    private String bankNo;

    private String cityCode;

    private String nodeName;

    private String hqName;

    private String hqNodeCode;

    private Integer superCodeFlag;

    private Integer bankType;

    private Date createTime;

    private Date updateTime;

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode == null ? null : nodeCode.trim();
    }

    public String getNodeState() {
        return nodeState;
    }

    public void setNodeState(String nodeState) {
        this.nodeState = nodeState == null ? null : nodeState.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getHqName() {
        return hqName;
    }

    public void setHqName(String hqName) {
        this.hqName = hqName == null ? null : hqName.trim();
    }

    public String getHqNodeCode() {
        return hqNodeCode;
    }

    public void setHqNodeCode(String hqNodeCode) {
        this.hqNodeCode = hqNodeCode == null ? null : hqNodeCode.trim();
    }

    public Integer getSuperCodeFlag() {
        return superCodeFlag;
    }

    public void setSuperCodeFlag(Integer superCodeFlag) {
        this.superCodeFlag = superCodeFlag;
    }

    public Integer getBankType() {
        return bankType;
    }

    public void setBankType(Integer bankType) {
        this.bankType = bankType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
