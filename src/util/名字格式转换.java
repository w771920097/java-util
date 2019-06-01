package util;

public class 名字格式转换 {

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
 * @Description:  �滻�»��߲���������һ����ĸΪ��д 
 * @param source
 * @return String    ��������
 */
public static String replaceUnderlineAndfirstToUpper(String source){
  String[] str = source.split("_");
  if (str.length < 1){
    return source;
  }
  StringBuilder sb = new StringBuilder();
  sb.append(str[0]);
  for (int i = 1; i < str.length; i++) {
    sb.append(toUpperCaseFirstOne(str[i]));
  }
  return sb.toString();
}
  
  public static void main(String[] args) {
    String str = "usertest";
    System.out.println(">> "+replaceUnderlineAndfirstToUpper(str));
  }

}
