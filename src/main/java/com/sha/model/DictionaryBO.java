package com.sha.model;

public class DictionaryBO {

  private String 计费来源;
  private Integer 费用编码;
  private String 费用类型;
  private Integer 交易场景;

  public String get计费来源() {
    return 计费来源;
  }

  public void set计费来源(String 计费来源) {
    this.计费来源 = 计费来源;
  }

  public Integer get费用编码() {
    return 费用编码;
  }

  public void set费用编码(Integer 费用编码) {
    this.费用编码 = 费用编码;
  }

  public String get费用类型() {
    return 费用类型;
  }

  public void set费用类型(String 费用类型) {
    this.费用类型 = 费用类型;
  }

  public Integer get交易场景() {
    return 交易场景;
  }

  public void set交易场景(Integer 交易场景) {
    this.交易场景 = 交易场景;
  }

  @Override
  public String toString() {
    return "DictionaryBO{" +
        "计费来源='" + 计费来源 + '\'' +
        ", 费用编码=" + 费用编码 +
        ", 费用类型='" + 费用类型 + '\'' +
        ", 交易场景=" + 交易场景 +
        '}';
  }
}
