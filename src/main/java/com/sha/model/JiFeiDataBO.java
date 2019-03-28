package com.sha.model;

import java.math.BigDecimal;

public class JiFeiDataBO {

  private String 计费来源;
  private Long 订单号;
  private String 费用名称;
  private BigDecimal 结算金额;
  private Integer 订单日期;

  public String get计费来源() {
    return 计费来源;
  }

  public void set计费来源(String 计费来源) {
    this.计费来源 = 计费来源;
  }

  public Long get订单号() {
    return 订单号;
  }

  public void set订单号(Long 订单号) {
    this.订单号 = 订单号;
  }

  public String get费用名称() {
    return 费用名称;
  }

  public void set费用名称(String 费用名称) {
    this.费用名称 = 费用名称;
  }

  public BigDecimal get结算金额() {
    return 结算金额;
  }

  public void set结算金额(BigDecimal 结算金额) {
    this.结算金额 = 结算金额;
  }

  public Integer get订单日期() {
    return 订单日期;
  }

  public void set订单日期(Integer 订单日期) {
    this.订单日期 = 订单日期;
  }

  @Override
  public String toString() {
    return "A{" +
        "计费来源='" + 计费来源 + '\'' +
        ", 订单号=" + 订单号 +
        ", 费用名称='" + 费用名称 + '\'' +
        ", 结算金额=" + 结算金额 +
        ", 订单日期=" + 订单日期 +
        '}';
  }

}
