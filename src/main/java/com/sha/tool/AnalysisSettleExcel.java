package com.sha.tool;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sha.model.DictionaryBO;
import com.sha.model.FlowBO;
import com.sha.model.JiFeiDataBO;
import com.sha.model.TradeSceneBO;
import com.sha.util.ExcelUtil;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
    System.out.println("解析成功，一共" + (jiFeiDataBOS.size() - 1) + "条记录");
    //获取字典数据
    System.out.print("输入文件二(字典数据)的文件地址:");
    String fileTwo = scanner.next();
    lists = ExcelUtil.readXlsx(fileTwo);
    List<DictionaryBO> dictionaryBOS = generateObj(lists, new TypeToken<List<DictionaryBO>>() {
    }.getType());
    System.out.println("解析成功，一共" + (dictionaryBOS.size() - 1) + "条记录");
    //获取流水数据
    Map<String, Object> sqlParamMap = Maps.newHashMap();
    sqlParamMap.put("tableId", jiFeiDataBOS.get(0).get订单日期());
    sqlParamMap.put("businessId", jiFeiDataBOS.get(0).get订单号());
    List<FlowBO> flowBOS = session.selectList("getFlow", sqlParamMap);
    System.out.println(flowBOS);

    System.out.print("想生成文件的路径:");
    scanner = new Scanner(System.in);
    ExcelUtil.generateExcelDemo(scanner.next());
  }

//  private static String formatFileUrl(String fileOne) {
//    if(fileOne.contains("\\")){
//
//    }
//  }

  private static <T> T generateObj(List<List<String>> lists, Type type) {
    List<String> needGenerateList = new ArrayList();
    List<String> fields = lists.get(0);
    for (int i = 1; i < lists.size(); i++) {
      List<String> list = lists.get(i);
      Map<String, Object> map = new HashMap<String, Object>();
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
   * db连接
   */
  private static void get() {
//    //mybatis的配置文件
//    String resource = "config/mybatis.xml";
//    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
//    InputStream is = AnalysisSettleExcel.class.getClassLoader().getResourceAsStream(resource);
//    //构建sqlSession的工厂
//    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
//    //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
//    //Reader reader = Resources.getResourceAsReader(resource);
//    //构建sqlSession的工厂
//    //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
//    //创建能执行映射文件中sql的sqlSession
//    SqlSession session = sessionFactory.openSession();
    /**
     * 映射sql的标识字符串，
     * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
     * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
     */
    //执行查询返回一个唯一user对象的sql

    List<TradeSceneBO> tradeSceneBOS = session.selectList("getTradeScene");
    System.out.println(gson.toJson(tradeSceneBOS));
    System.out.println(tradeSceneBOS.size());
  }
}
