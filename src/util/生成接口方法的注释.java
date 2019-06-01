package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class 生成接口方法的注释 {
  
  public static final String javaPath = "E:\\tqKeeper\\src\\main\\java\\com\\tianque\\tqkeeper";
  //"E:\\tqKeeper\\src\\main\\java\\com\\tianque\\tqkeeper";
  //"C:\\Users\\n-340\\Desktop\\test"
  public static final String xx = "";
  private static final String FileName = null;
  private static final String outPath = "C:\\Users\\n-340\\Desktop\\test\\test.txt";
  
  
  public static void main(String[] args) {
    System.out.println("------------------start----------------------");
//    addNotes(javaPath);
    List<String> folder = 遍历文件夹.traverseFolder2(javaPath);
    for (String path : folder) {
      System.out.println("path>>" + path);
      addNotes(path);
    }
    System.out.println("----------------end------------------");
  }
  
  private static void findFile(String javaPath){

    String[] dirs = getDirNameList(javaPath);
    for (String dir : dirs) {
      String path = javaPath + "\\" + dir;
     System.out.println("path>>" + path); 
     findFile(javaPath);
    }
    
  }
  
  private static void addNotes(String javaPath) {
    String[] strs = getFileNameList(javaPath);
    strs = getInterfaceList(strs,javaPath);
    String temp = null;
    BufferedReader bufReader = null;
    String beforeTemp = null;
    try {  
      for (String javaName : strs) {
        String filePath = javaPath + "\\" + javaName;
        javaName = javaName.substring(0,javaName.indexOf("."));
        System.out.println("javaName>>"+ javaName);
          bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));//数据流读取文件
          
          StringBuilder strb = new StringBuilder();
          String bigTemp = null;
          while ((bigTemp = bufReader.readLine()) != null) {
            temp = bigTemp.trim();
            
            //非方法代码
            if(!temp.endsWith(";") || temp.startsWith("import") 
                || temp.startsWith("package") || temp.contains("=")){
              beforeTemp = bigTemp;
              strb.append(bigTemp).append("\n");
              continue;
            }
            
            //已经有注释的方法
            if(null != beforeTemp && beforeTemp.trim().equals("*/")){
              beforeTemp = bigTemp;
              strb.append(bigTemp).append("\n");
              continue;
            }
            
            System.out.println("没有注释的方法>>" + temp);
            
            String[] items = temp.split(" ");
            String methodName = null;
            List<String> params = new ArrayList<String>();
            String returnStr = null;
            for (int i = 1; i < items.length; i++) {
              if(items[i].contains("(")){
                returnStr = items[i - 1];
                methodName = items[i].substring(0, items[i].indexOf("("));
              }
              if(items[i].endsWith(",")){
                String param = items[i].substring(0, items[i].indexOf(","));
                params.add(param);
              }
              if(items[i].contains(")")){
                String param = items[i].substring(0, items[i].indexOf(")"));
                params.add(param);
              }
//              System.out.println("item>>" + i + "  " + items[i]);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("  /**").append("\n")
            .append("   * ").append(methodName).append("\n");
            for (String param : params) {
              sb.append("   * @param ").append(param).append("\n");
            }
            sb.append("   * @return ").append(returnStr).append("\n")
            .append("   */").append("\n");

            sb.append("  ").append(temp).append("\n");
//            System.out.println(sb.toString());
            strb.append(sb.toString());
            beforeTemp = bigTemp;
          }
          writeFile(filePath,strb.toString());
      }
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      if(null != bufReader){
        try {
          bufReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    
  }
  
  
  

  public static String[] getInterfaceList(String[] fileList, String javaPath){
    BufferedReader bufReader = null;
    String temp = null;
    List<String> newFileList = new ArrayList<String>();
    try {  
      for (String FileName : fileList) {
        String filePath = javaPath + "\\" + FileName;
        String className = FileName.substring(0,FileName.indexOf("."));
          bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));//数据流读取文件
          while ((temp = bufReader.readLine()) != null) {
            String[] items = temp.split(" ");
            boolean isInterface = false;
            for (int i = 1; i < items.length; i++) {
              if(items[i].equals(className) && items[i-1].equals("interface")){
//                System.out.println("interface FileName>>" + FileName);
                newFileList.add(FileName);
                isInterface = true;
              }
            }
            if (isInterface == true){
              break;
            }
          }
      }
    }catch(Exception e){
      e.printStackTrace();
    }finally{
      if(null != bufReader){
        try {
          bufReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return (String[]) newFileList.toArray(new String[0]);
  }


  public static String[] getFileNameList(String path) {
    File file = new File(path);
    String[] fileName = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                    if (name.endsWith(".java")) {
                            return true;
                    }
                    return false;
            }
    });
    return fileName;
}
  
  public static String[] getDirNameList(String path) {
    File file = new File(path);
    String[] dirs = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                    if (null != dir && !name.contains(".")) {
                            return true;
                    }
                    return false;
            }
    });
    return dirs;
}
  
  
  /**
   * 将字符串追加到文件已有内容后面
   * 
   * @param fileFullPath 文件完整地址：D:/test.txt
   * @param content 需要写入的
   */
  public static void writeFile(String fileFullPath,String content) {
      FileOutputStream fos = null;
      try {
          //true不覆盖已有内容
          fos = new FileOutputStream(fileFullPath);  
          OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileFullPath),"utf-8");   
          BufferedWriter writer=new BufferedWriter(write);          
          writer.write(content);      
          writer.close();   
          //写入
//          PrintWriter pw = new PrintWriter(fos);
//          fos.write(content.getBytes());
//          pw.write(content);
//          pw.flush();
//          pw.close();
                     
      } catch (IOException e) {
          e.printStackTrace();
      }finally{
          if(fos != null){
              try {
                  fos.flush();
                  fos.close(); 
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
  }

}
