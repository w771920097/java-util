package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class writeFile {

  public static void main(String[] args) {
    String filePath = "D:\\aworkspace\\statsd\\stats.js"; // 读取的文件夹路径
    readForTemplate(filePath);
  }

  public static void readForTemplate(String temp) {
    // dlg.txt
    try {
      // 输出的文件夹路径
       FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop\\project\\project.text",true);
       BufferedWriter bw = new BufferedWriter(fw);
      try 
//      (
//          BufferedReader bufReader =
//          new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8")))// 数据流读取文件
      {
//        while ((temp = bufReader.readLine()) != null) {
////          temp = temp.replace("\"", "\\\"").replace("\t", "\\t").replace("    ", "\\t");
////          temp = temp + "\\n";
////          temp = "\t\tsb.append(\"" + temp + "\");";
//          if(temp != null && temp.contains("//")) {
//            temp = "//" + 翻译.tran(temp.replaceAll("//", ""));
//          }
//          System.out.println(temp);
//          bw.write(temp);
//          bw.newLine(); 
//        }
//        bufReader.close();
        bw.write(temp);
        bw.newLine(); 
         bw.close();
         fw.close();
      }catch (Exception e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      
    }

  }


}
