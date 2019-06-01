package generate;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * ����ת�� NameTransformation
 * 
 * @author wangsihong@hztianque.com
 * @date 2018��10��25�� ����7:24:22
 *
 */
public class Util {

  /**
   * ����ĸ��д
   * 
   * @param srcStr
   * @return
   */
  public static String toUpperCaseFirstOne(String srcStr) {
    return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
  }

  /**
   * ����ĸСд
   * 
   * @param srcStr
   * @return
   */
  public static String toLowerCaseFirstOne(String srcStr) {
    return srcStr.substring(0, 1).toLowerCase() + srcStr.substring(1);
  }

  /**
   * replaceUnderlineAndfirstToUpper
   * 
   * @Description: �滻�»��߲���������һ����ĸΪ��д
   * @param source
   * @return String ��������
   */
  public static String replaceUnderlineAndfirstToUpper(String source) {
    String[] str = source.split("_");
    if (str.length < 1) {
      return source;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(str[0]);
    for (int i = 1; i < str.length; i++) {
      sb.append(toUpperCaseFirstOne(str[i]));
    }
    return sb.toString();
  }

  public static void generateFiles(String str, String path) {
    FileOutputStream fos  = null;
    BufferedWriter bw = null;
    try {
      // ������ļ���·��
      // C:\\Users\\n-340\\Desktop\\test.txt
      fos  = new FileOutputStream(path);
      bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
      bw.write(str);
      bw.flush();
//      bw.append(str);// �����е��ļ�������ַ���
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (bw != null) {
        try {
          bw.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fos  != null) {
        try {
          fos .close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    String str = "usertest";
    System.out.println(">> " + replaceUnderlineAndfirstToUpper(str));
  }

}
