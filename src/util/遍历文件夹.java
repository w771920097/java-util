package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class 遍历文件夹 {

  public static List<String> list = new ArrayList<String>(16);
  
  
  public static void main(String[] args) {
    traverseFolder2("E:\\tqKeeper\\src\\main\\java\\com\\tianque\\tqkeeper\\activemq");
  }
  
  /**
   * ��ȡ�ļ����µ������ļ�
   * @param path
   * @return List<File>    ��������
   */
  public static List<String> traverseFolder2(String path) {
    File file = new File(path);
    if (file.exists()) {
        File[] files = file.listFiles();
        if (null == files || files.length == 0) {
            System.out.println("�ļ����ǿյ�!");
            return null;
        } else {
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    //System.out.println("�ļ���:" + file2.getAbsolutePath());
                    traverseFolder2(file2.getAbsolutePath());
                    list.add(file2.getAbsolutePath());
                } else {
                   // System.out.println("�ļ�:" + file2.getAbsolutePath());
                }
            }
        }
    } else {
        System.out.println("�ļ�������!");
    }
    return list;
}
}
