package com.sha.model;

public class TradeSceneBO {

  private Integer commonCode;
  private String commonDesc;
  private Integer inUserType;
  private String inUserTypeDesc;
  private Integer inAccountType;
  private String inAccountTypeDesc;
  private Integer outUserType;
  private String outUserTypeDesc;
  private Integer outAccountType;
  private String outAccountTypeDesc;
  private Integer businessScene;
  private String businessSceneDesc;
  private Integer businessType;
  private String businessTypeDesc;
  private Integer subsetBusinessType;
  private String subsetBusinessTypeDesc;

  public Integer getCommonCode() {
    return commonCode;
  }

  public void setCommonCode(Integer commonCode) {
    this.commonCode = commonCode;
  }

  public String getCommonDesc() {
    return commonDesc;
  }

  public void setCommonDesc(String commonDesc) {
    this.commonDesc = commonDesc;
  }

  public Integer getInUserType() {
    return inUserType;
  }

  public void setInUserType(Integer inUserType) {
    this.inUserType = inUserType;
  }

  public String getInUserTypeDesc() {
    return inUserTypeDesc;
  }

  public void setInUserTypeDesc(String inUserTypeDesc) {
    this.inUserTypeDesc = inUserTypeDesc;
  }

  public Integer getInAccountType() {
    return inAccountType;
  }

  public void setInAccountType(Integer inAccountType) {
    this.inAccountType = inAccountType;
  }

  public String getInAccountTypeDesc() {
    return inAccountTypeDesc;
  }

  public void setInAccountTypeDesc(String inAccountTypeDesc) {
    this.inAccountTypeDesc = inAccountTypeDesc;
  }

  public Integer getOutUserType() {
    return outUserType;
  }

  public void setOutUserType(Integer outUserType) {
    this.outUserType = outUserType;
  }

  public String getOutUserTypeDesc() {
    return outUserTypeDesc;
  }

  public void setOutUserTypeDesc(String outUserTypeDesc) {
    this.outUserTypeDesc = outUserTypeDesc;
  }

  public Integer getOutAccountType() {
    return outAccountType;
  }

  public void setOutAccountType(Integer outAccountType) {
    this.outAccountType = outAccountType;
  }

  public String getOutAccountTypeDesc() {
    return outAccountTypeDesc;
  }

  public void setOutAccountTypeDesc(String outAccountTypeDesc) {
    this.outAccountTypeDesc = outAccountTypeDesc;
  }

  public Integer getBusinessScene() {
    return businessScene;
  }

  public void setBusinessScene(Integer businessScene) {
    this.businessScene = businessScene;
  }

  public String getBusinessSceneDesc() {
    return businessSceneDesc;
  }

  public void setBusinessSceneDesc(String businessSceneDesc) {
    this.businessSceneDesc = businessSceneDesc;
  }

  public Integer getBusinessType() {
    return businessType;
  }

  public void setBusinessType(Integer businessType) {
    this.businessType = businessType;
  }

  public String getBusinessTypeDesc() {
    return businessTypeDesc;
  }

  public void setBusinessTypeDesc(String businessTypeDesc) {
    this.businessTypeDesc = businessTypeDesc;
  }

  public Integer getSubsetBusinessType() {
    return subsetBusinessType;
  }

  public void setSubsetBusinessType(Integer subsetBusinessType) {
    this.subsetBusinessType = subsetBusinessType;
  }

  public String getSubsetBusinessTypeDesc() {
    return subsetBusinessTypeDesc;
  }

  public void setSubsetBusinessTypeDesc(String subsetBusinessTypeDesc) {
    this.subsetBusinessTypeDesc = subsetBusinessTypeDesc;
  }

  @Override
  public String toString() {
    return "TradeSceneBO{" +
        "commonCode=" + commonCode +
        ", commonDesc='" + commonDesc + '\'' +
        ", inUserType=" + inUserType +
        ", inUserTypeDesc='" + inUserTypeDesc + '\'' +
        ", inAccountType=" + inAccountType +
        ", inAccountTypeDesc='" + inAccountTypeDesc + '\'' +
        ", outUserType=" + outUserType +
        ", outUserTypeDesc='" + outUserTypeDesc + '\'' +
        ", outAccountType=" + outAccountType +
        ", outAccountTypeDesc='" + outAccountTypeDesc + '\'' +
        ", businessScene=" + businessScene +
        ", businessSceneDesc='" + businessSceneDesc + '\'' +
        ", businessType=" + businessType +
        ", businessTypeDesc='" + businessTypeDesc + '\'' +
        ", subsetBusinessType=" + subsetBusinessType +
        ", subsetBusinessTypeDesc='" + subsetBusinessTypeDesc + '\'' +
        '}';
  }
}
