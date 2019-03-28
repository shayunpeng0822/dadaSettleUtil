package com.sha.tool;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sha.model.AnalysisSettleResultBO;
import com.sha.model.AnalysisSettleResultDetailBO;
import com.sha.model.DictionaryBO;
import com.sha.model.FlowBO;
import com.sha.model.JiFeiDataBO;
import com.sha.model.TradeSceneBO;
import com.sha.util.ExcelUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AnalysisSettleExcel {

  private static Gson gson = new Gson();
  private static SqlSession session;

  static {
    //mybatis的配置文件
    String resource = "config/mybatis.xml";
    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
    InputStream is = AnalysisSettleExcel.class.getClassLoader().getResourceAsStream(resource);
    //构建sqlSession的工厂
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
    //Reader reader = Resources.getResourceAsReader(resource);
    //构建sqlSession的工厂
    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    //创建能执行映射文件中sql的sqlSession
    session = sessionFactory.openSession();
  }

  public static void main(String[] args) throws Exception {
    //获取计费的金额数据
    Scanner scanner = new Scanner(System.in);
    System.out.print("输入文件一(结算金额数据)的文件地址:");
    String fileOne = scanner.next();
    List<List<String>> lists = ExcelUtil.readXlsx(fileOne);
    List<JiFeiDataBO> jiFeiDataBOS = generateObj(lists, new TypeToken<List<JiFeiDataBO>>() {
    }.getType());
    System.out.println("解析成功，excel中一共" + (jiFeiDataBOS.size() - 1) + "条记录");
    //获取字典数据
    System.out.print("输入文件二(字典数据)的文件地址:");
    String fileTwo = scanner.next();
    lists = ExcelUtil.readXlsx(fileTwo);
    List<DictionaryBO> dictionaryBOS = generateObj(lists, new TypeToken<List<DictionaryBO>>() {
    }.getType());
    System.out.println("解析成功，excel中一共" + (dictionaryBOS.size() - 1) + "条记录");
    //获取流水数据
    Map<String, Object> sqlParamMap = Maps.newHashMap();
    sqlParamMap.put("tableId", jiFeiDataBOS.get(0).get订单日期());
    sqlParamMap.put("businessId", jiFeiDataBOS.get(0).get订单号());
    List<FlowBO> flowBOS = session.selectList("getFlow", sqlParamMap);
    System.out.println("获取流水数据成功,流水条数：" + flowBOS.size());
    //获取交易场景和三级场景关系
    List<TradeSceneBO> tradeSceneBOS = session.selectList("getTradeScene");
    System.out.println("获取交易场景和三级场景关系,场景条数：" + tradeSceneBOS.size());
    Thread.sleep(500);
    System.out.println("开始分析数据。。。请耐心等待");
    //分析数据
    List<AnalysisSettleResultBO> resultBOS = AnalysisData(jiFeiDataBOS, dictionaryBOS, flowBOS,
        tradeSceneBOS);
    System.out.println("分析完成");
    System.out.println("准备生成excel文件");
    System.out.print("请输入想生成文件的路径:");
    String filePath = scanner.next();
    System.out.println("收到，开始生成文件....");
    generateExcel(resultBOS, filePath);
    System.out.println("生成excel文件成功");
    System.out.println("欢迎再次使用");
  }

  private static List<AnalysisSettleResultBO> AnalysisData(List<JiFeiDataBO> jiFeiDataBOS,
      List<DictionaryBO> dictionaryBOS,
      List<FlowBO> flowBOS, List<TradeSceneBO> tradeSceneBOS) {
    //jiFeiDataBOS设置字典项属性
    for (JiFeiDataBO jiFeiDataBO : jiFeiDataBOS) {
      for (DictionaryBO dictionaryBO : dictionaryBOS) {
        if (jiFeiDataBO.get计费来源().trim().equals(dictionaryBO.get计费来源().trim()) &&
            jiFeiDataBO.get费用名称().trim().equals(dictionaryBO.get费用类型().trim())) {
          jiFeiDataBO.set交易场景(dictionaryBO.get交易场景());
          jiFeiDataBO.set费用编码(dictionaryBO.get费用编码());
          jiFeiDataBO.set账户场景名称(dictionaryBO.get账户系统场景名称());
          break;
        }
      }
    }
    Map<Integer, List<JiFeiDataBO>> jiFeiDataMap = jiFeiDataBOS.stream()
        .collect(Collectors.groupingBy(JiFeiDataBO::get交易场景));

    List<AnalysisSettleResultBO> resultBOS = Lists.newArrayList();

    for (Map.Entry<Integer, List<JiFeiDataBO>> entry : jiFeiDataMap.entrySet()) {
      Integer tradeScene = entry.getKey();
      List<JiFeiDataBO> jifeiDatas = entry.getValue();
      TradeSceneBO tradeSceneBO = tradeSceneBOS.stream()
          .filter(bo -> tradeScene.equals(bo.getCommonCode())).findFirst().orElse(null);
      if (tradeSceneBO == null) {
        throw new RuntimeException("交易场景不存在：" + tradeScene);
      }
      BigDecimal flowMoney = flowBOS.stream()
          .filter(bo -> bo.getSubsetBusinessType().equals(tradeSceneBO.getSubsetBusinessType()))
          .map(FlowBO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
      BigDecimal settleMoney = jifeiDatas.stream().map(JiFeiDataBO::get结算金额)
          .reduce(BigDecimal.ZERO, BigDecimal::add);

      AnalysisSettleResultBO resultBO = new AnalysisSettleResultBO();
      resultBO.setSceneName(jifeiDatas.get(0).get账户场景名称());
      resultBO.setTradeCode(tradeScene);
      resultBO.setInAccountTypeDesc(tradeSceneBO.getInAccountTypeDesc());
      resultBO.setInUserTypeDesc(tradeSceneBO.getInUserTypeDesc());
      resultBO.setOutUserTypeDesc(tradeSceneBO.getOutUserTypeDesc());
      resultBO.setOutAccountTypeDesc(tradeSceneBO.getOutAccountTypeDesc());
      resultBO.setAmount(flowMoney);
      resultBO.setDifferentAmount(flowMoney.subtract(settleMoney));
      List<AnalysisSettleResultDetailBO> detailBOS = Lists.newArrayList();
      for (JiFeiDataBO jiFeiDataBO : jifeiDatas) {
        AnalysisSettleResultDetailBO detailBO = new AnalysisSettleResultDetailBO();
        detailBO.setJiFeiSource(jiFeiDataBO.get计费来源());
        detailBO.setJiFeiCode(jiFeiDataBO.get费用编码());
        detailBO.setJiFeiStr(jiFeiDataBO.get费用名称());
        detailBO.setAmount(jiFeiDataBO.get结算金额());
        detailBOS.add(detailBO);
      }
      resultBO.setDetailBOS(detailBOS);
      resultBOS.add(resultBO);
    }
    return resultBOS;
  }


  /**
   * 转换成对象
   */
  private static <T> T generateObj(List<List<String>> lists, Type type) {
    List<String> needGenerateList = Lists.newArrayList();
    List<String> fields = lists.get(0);
    for (int i = 1; i < lists.size(); i++) {
      List<String> list = lists.get(i);
      Map<String, Object> map = Maps.newHashMap();
      for (int k = 0; k < fields.size(); k++) {
        map.put(fields.get(k).trim(), list.get(k).trim());
      }
      System.out.println(map);
      needGenerateList.add(gson.toJson(map));
    }
    System.out.println(needGenerateList);
    return gson.fromJson(needGenerateList.toString(), type);
  }

  /**
   * 生成excel
   */
  private static void generateExcel(List<AnalysisSettleResultBO> resultBOS, String filePath)
      throws IOException {
    // 创建excel文件
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("对比数据详情");

    String[] titles = {"计费来源", "费用编码", "费用类型", "金额", "出账户", "入账户", "场景名称（账户系统）", "交易场景",
        "流出账户类型", "流入账户类型", "金额", "差额"};
    // 构建表头
    XSSFRow headRow = sheet.createRow(0);
    XSSFCell cell;
    //创建头部
    for (int i = 0; i < titles.length; i++) {
      cell = headRow.createCell(i);
      cell.setCellValue(titles[i]);
    }

    // 构建表数据
    int rowCount = 1, columnCount = 0;
    for (AnalysisSettleResultBO resultBO : resultBOS) {
      List<AnalysisSettleResultDetailBO> detailBOS = resultBO.getDetailBOS();
      boolean first = true;
      for (AnalysisSettleResultDetailBO detailBO : detailBOS) {
        XSSFRow row = sheet.createRow(rowCount);
        cell = row.createCell(columnCount++);
        cell.setCellValue(detailBO.getJiFeiSource());
        cell = row.createCell(columnCount++);
        cell.setCellValue(detailBO.getJiFeiCode());
        cell = row.createCell(columnCount++);
        cell.setCellValue(detailBO.getJiFeiStr());
        cell = row.createCell(columnCount++);
        cell.setCellValue(detailBO.getAmount().toString());
        cell = row.createCell(columnCount++);
        cell.setCellValue(resultBO.getOutUserTypeDesc());
        cell = row.createCell(columnCount++);
        cell.setCellValue(resultBO.getInUserTypeDesc());
        if (first) {
          cell = row.createCell(columnCount++);
          cell.setCellValue(resultBO.getSceneName());
          cell = row.createCell(columnCount++);
          cell.setCellValue(resultBO.getTradeCode());
          cell = row.createCell(columnCount++);
          cell.setCellValue(resultBO.getOutAccountTypeDesc());
          cell = row.createCell(columnCount++);
          cell.setCellValue(resultBO.getInAccountTypeDesc());
          cell = row.createCell(columnCount++);
          cell.setCellValue(resultBO.getAmount().toString());
          cell = row.createCell(columnCount);
          cell.setCellValue(resultBO.getDifferentAmount().toString());
          first = false;
        }
        columnCount = 0;
        rowCount++;
      }
      if (detailBOS.size() > 1) {
        sheet
            .addMergedRegion(new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 6, 6));
        sheet
            .addMergedRegion(new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 7, 7));
        sheet
            .addMergedRegion(new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 8, 8));
        sheet
            .addMergedRegion(new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 9, 9));
        sheet.addMergedRegion(
            new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 10, 10));
        sheet.addMergedRegion(
            new CellRangeAddress(rowCount - detailBOS.size(), rowCount - 1, 11, 11));
      }
    }
    File file = new File(filePath + File.separator + "分析结果.xlsx");
    FileOutputStream fout = new FileOutputStream(file);
    workbook.write(fout);
    fout.close();
  }
}