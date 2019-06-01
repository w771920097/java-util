package util;

import java.io.File;

public class 生成文件夹 {

  public static void main(String[] args) {
    File file = new File("E:\\cloud-monitor-20181108-wsh\\cloud-monitor-web\\src\\main\\webapp\\res\\export");
    if (!file.exists()){
      file.mkdirs();
    }

  }

}
