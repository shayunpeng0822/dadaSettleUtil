package com.sha.model;

import java.math.BigDecimal;
import java.util.List;

public class AnalysisSettleResultDetailBO {

  /**
   * 计费来源
   */
  private String jiFeiSource;
  /**
   * 费用编码
   */
  private Integer jiFeiCode;
  /**
   * 费用类型
   */
  private String jiFeiStr;
  /**
   * 金额
   */
  private BigDecimal amount;

  /**
   * 差额
   */
  private BigDecimal differentAmount;

  private List<AnalysisSettleResultBO> settleResultBOS;

  @Override
  public String toString() {
    return "AnalysisSettleResultDetailBO{" +
        "jiFeiSource='" + jiFeiSource + '\'' +
        ", jiFeiCode=" + jiFeiCode +
        ", jiFeiStr='" + jiFeiStr + '\'' +
        ", amount=" + amount +
        ", settleResultBOS=" + settleResultBOS +
        '}';
  }

  public BigDecimal getDifferentAmount() {
    return differentAmount;
  }

  public void setDifferentAmount(BigDecimal differentAmount) {
    this.differentAmount = differentAmount;
  }

  public List<AnalysisSettleResultBO> getSettleResultBOS() {
    return settleResultBOS;
  }

  public void setSettleResultBOS(List<AnalysisSettleResultBO> settleResultBOS) {
    this.settleResultBOS = settleResultBOS;
  }

  public String getJiFeiSource() {
    return jiFeiSource;
  }

  public void setJiFeiSource(String jiFeiSource) {
    this.jiFeiSource = jiFeiSource;
  }

  public Integer getJiFeiCode() {
    return jiFeiCode;
  }

  public void setJiFeiCode(Integer jiFeiCode) {
    this.jiFeiCode = jiFeiCode;
  }

  public String getJiFeiStr() {
    return jiFeiStr;
  }

  public void setJiFeiStr(String jiFeiStr) {
    this.jiFeiStr = jiFeiStr;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
