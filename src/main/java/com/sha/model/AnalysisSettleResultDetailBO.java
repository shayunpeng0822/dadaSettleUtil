package com.sha.model;

import java.math.BigDecimal;

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

  @Override
  public String toString() {
    return "AnalysisSettleResultDetailBO{" +
        "jiFeiSource='" + jiFeiSource + '\'' +
        ", jiFeiCode=" + jiFeiCode +
        ", jiFeiStr='" + jiFeiStr + '\'' +
        ", amount=" + amount +
        '}';
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
