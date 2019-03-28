package com.sha.model;

import java.math.BigDecimal;
import java.util.List;

public class AnalysisSettleResultBO {

  /**
   * 场景名称
   */
  private String sceneName;
  /**
   * 交易场景
   */
  private Integer tradeCode;
  /**
   * 流入账户类型
   */
  private String inAccountTypeDesc;
  /**
   * 流入用户类型
   */
  private String inUserTypeDesc;
  /**
   * 流出用户类型
   */
  private String outUserTypeDesc;
  /**
   * 流出账户类型
   */
  private String outAccountTypeDesc;
  /**
   * 金额
   */
  private BigDecimal amount;
  /**
   * 差额
   */
  private BigDecimal differentAmount;

  private List<AnalysisSettleResultDetailBO> detailBOS;

  @Override
  public String toString() {
    return "AnalysisSettleResultBO{" +
        "sceneName='" + sceneName + '\'' +
        ", tradeCode=" + tradeCode +
        ", inAccountTypeDesc='" + inAccountTypeDesc + '\'' +
        ", inUserTypeDesc='" + inUserTypeDesc + '\'' +
        ", outUserTypeDesc='" + outUserTypeDesc + '\'' +
        ", outAccountTypeDesc='" + outAccountTypeDesc + '\'' +
        ", amount=" + amount +
        ", differentAmount=" + differentAmount +
        ", detailBOS=" + detailBOS +
        '}';
  }

  public String getSceneName() {
    return sceneName;
  }

  public void setSceneName(String sceneName) {
    this.sceneName = sceneName;
  }

  public Integer getTradeCode() {
    return tradeCode;
  }

  public void setTradeCode(Integer tradeCode) {
    this.tradeCode = tradeCode;
  }

  public String getInAccountTypeDesc() {
    return inAccountTypeDesc;
  }

  public void setInAccountTypeDesc(String inAccountTypeDesc) {
    this.inAccountTypeDesc = inAccountTypeDesc;
  }

  public String getInUserTypeDesc() {
    return inUserTypeDesc;
  }

  public void setInUserTypeDesc(String inUserTypeDesc) {
    this.inUserTypeDesc = inUserTypeDesc;
  }

  public String getOutUserTypeDesc() {
    return outUserTypeDesc;
  }

  public void setOutUserTypeDesc(String outUserTypeDesc) {
    this.outUserTypeDesc = outUserTypeDesc;
  }

  public String getOutAccountTypeDesc() {
    return outAccountTypeDesc;
  }

  public void setOutAccountTypeDesc(String outAccountTypeDesc) {
    this.outAccountTypeDesc = outAccountTypeDesc;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getDifferentAmount() {
    return differentAmount;
  }

  public void setDifferentAmount(BigDecimal differentAmount) {
    this.differentAmount = differentAmount;
  }

  public List<AnalysisSettleResultDetailBO> getDetailBOS() {
    return detailBOS;
  }

  public void setDetailBOS(List<AnalysisSettleResultDetailBO> detailBOS) {
    this.detailBOS = detailBOS;
  }
}
