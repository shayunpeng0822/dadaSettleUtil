package com.sha.util;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

  private static Gson gson = new Gson();

  public static List<List<String>> readXlsx(String path) throws Exception {
    InputStream is = new FileInputStream(path);
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    List<List<String>> result = new ArrayList<List<String>>();
    // 循环每一页，并处理当前循环页
    for (XSSFSheet xssfSheet : xssfWorkbook) {
      if (xssfSheet == null) {
        continue;
      }
      // 处理当前页，循环读取每一行
      for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
        XSSFRow xssfRow = xssfSheet.getRow(rowNum);
        int minColIx = xssfRow.getFirstCellNum();
        int maxColIx = xssfRow.getLastCellNum();
        List<String> rowList = new ArrayList<String>();
        for (int colIx = minColIx; colIx < maxColIx; colIx++) {
          XSSFCell cell = xssfRow.getCell(colIx);
          if (cell == null) {
            continue;
          }
          rowList.add(cell.toString());
        }
        result.add(rowList);
      }
    }
    return result;
  }

  public static void generateExcelDemo(String path) throws Exception {
    SXSSFWorkbook wb = new SXSSFWorkbook(1000);
    CellStyle headerStyle = wb.createCellStyle();
    headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
    Font font = wb.createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    headerStyle.setFont(font);
    Sheet sheet = wb.createSheet("第" + 1 + "页");
    Row headerRow = sheet.createRow(0);
    Map<String, Object> columnMap = new HashMap<String, Object>();
    columnMap.put("test", "hahah");
    createHeaderRow(headerRow, headerStyle, columnMap);
    mkdirFilePath("试验", wb, path);
  }

  public static String mkdirFilePath(String fileName, SXSSFWorkbook wb, String path)
      throws IOException {
    try {
      String absolutePath = path + File.separator + fileName + ".xlsx";
      System.out.println(absolutePath);
      wb.write(new FileOutputStream(absolutePath));
    } catch (IOException e) {
      throw e;
    }
    wb.dispose();
    return path;
  }

  /**
   * 创建头部标题
   */
  public static void createHeaderRow(Row row, CellStyle headerStyle,
      Map<String, Object> columnMap) {
    int index = 0;
    for (String key : columnMap.keySet()) {
      row.createCell(index).setCellValue(key);
      row.getCell(index).setCellStyle(headerStyle);
      index++;
    }
  }

}
