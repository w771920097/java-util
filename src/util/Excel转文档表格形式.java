package util;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Excel转文档表格形式 
 * 1.最大列数从第一行获取，第一行必须是最大列数
 * 2.使用公式的单元格需自行改成文本格式
 * 3.目前只支持.xls格式(点击另存为.xls格式就行)
 * @author wangsihong@hztianque.com   
 * @date 2019年3月4日 上午11:16:03 
 */
public class Excel转文档表格形式 {

  public static void main(String[] args) {
    try {
      readExcel("C:\\Users\\Administrator\\Desktop\\发布平台状态码.xls");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  
  public static Boolean readExcel(String filePath) throws Exception {
      HSSFWorkbook workbook = null;
      //.xlsx格式
//      Workbook wb = null;   

      // 封装输入流,封装sheet里面的内容
      InputStream inputStream = null;
      try {
          inputStream = new FileInputStream(filePath);
          workbook = new HSSFWorkbook(inputStream);
//          wb = WorkbookFactory.create(inputStream);   

      } catch (Exception e) {
          throw new Exception("封装输入流出错", e);
      } finally {
          try {
              if (null != inputStream) {
                  inputStream.close();
              }
          } catch (Exception e) {
              throw new Exception("读取Excel文件出错", e);
          }
      }
      // 循环工作表
      for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
          HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
          if (hssfSheet == null || hssfSheet.getRow(0) == null) {
              continue;
          }
          // 循环行
          int cellsNum =  hssfSheet.getRow(0).getPhysicalNumberOfCells();
          for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
              HSSFRow hssfRow = hssfSheet.getRow(rowNum);
              if (hssfRow == null) {
                  continue;
              }

              // 第二行的时候加上
              // | ------------ | ------------ | ------------ |
              if(rowNum == 1) {
                System.out.print("| ");
                for (int i = 0; i < cellsNum; i++) {
                  System.out.print("------------ | ");
                }
                System.out.println("");
              }
              System.out.print("| ");
              // 循环单元格
              for (int i = 0; i < cellsNum; i++) {
                Cell cell = hssfRow.getCell(i);
                if(cell != null) {
                  System.out.print(cell.toString()  + " | ");
                }else {
                  System.out.print("     | ");
                }
              }
              System.out.println("");
          }
      }

      try {
          workbook.close();
      } catch (Exception e) {
          throw new Exception("转换Excel文件出错", e);
      }
      return true;
  }

}
