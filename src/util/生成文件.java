package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class 生成文件 {

  public static void main(String[] args) {
    String path = "C:\\Users\\n-340\\Desktop\\equipment\\test.txt";
    String str = "\n \tsabc";
    exportFile(path, str);
  }
  
  public static void exportFile(String path, String str){

    //输出的文件夹路径
      FileWriter fw = null;
      BufferedWriter bw = null;
      try {
        //fw = new FileWriter(path, true);// 往已有的文件上添加字符串 
        fw = new FileWriter(path);// 覆盖已有的文件
        bw = new BufferedWriter(fw);
        bw.append(str);
      } catch (IOException e) {
        e.printStackTrace();
      }finally{
        if (bw != null){
          try {
            bw.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        if (fw != null){
          try {
            fw.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
  }
}
