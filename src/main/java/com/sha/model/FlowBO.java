package com.sha.model;

import java.math.BigDecimal;

public class FlowBO {

  private Integer subsetBusinessType;
  private BigDecimal amount;

  public Integer getSubsetBusinessType() {
    return subsetBusinessType;
  }

  public void setSubsetBusinessType(Integer subsetBusinessType) {
    this.subsetBusinessType = subsetBusinessType;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
