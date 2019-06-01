package util;

import java.io.UnsupportedEncodingException;

public class  解码转码 {
  private final static String ENCODE = "UTF-8"; 
//  private final static String ENCODE = "GBK"; 
  /**
   * URL ����
   *
   * @return String
   */
  public static String getURLDecoderString(String str) {
      String result = "";
      if (null == str) {
          return "";
      }
      try {
          result = java.net.URLDecoder.decode(str, ENCODE);
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      return result;
  }
  /**
   * URL ת��
   *
   * @return String
   */
  public static String getURLEncoderString(String str) {
      String result = "";
      if (null == str) {
          return "";
      }
      try {
          result = java.net.URLEncoder.encode(str, ENCODE);
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      return result;
  }

  /**
   * 
   * @return void
   */
  public static void main(String[] args) {
//      String str = "%C3%A7%C2%94%C2%A8%C3%A6%C2%88%C2%B7%C3%A4%C2%B8%C2%AD%C3%A5%C2%BF%C2%832";
    String str = "%C3%A7%C2%94%C2%A8%C3%A6%C2%88%C2%B7%C3%A4%C2%B8%C2%AD%C3%A5%C2%BF%C2%83%C3%A4%C2%BD%C2%A0%C3%A4%C2%B8%C2%AA,";
      
      System.out.println(getURLEncoderString(str));

      System.out.println(getURLDecoderString(str));
      
      System.out.println(getURLDecoderString(getURLEncoderString(str)));

      System.out.println(getURLEncoderString(getURLEncoderString(str)));
      System.out.println(getURLDecoderString(getURLDecoderString(str)));
  }

}