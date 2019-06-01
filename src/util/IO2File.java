package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class IO2File {
  public static void main(String[] args) throws IOException {

    File f = new File("C:\\Users\\n-340\\Desktop\\test\\out.txt");
    f.createNewFile();
    FileOutputStream fileOutputStream = new FileOutputStream(f, true);
    PrintStream printStream = new PrintStream(fileOutputStream);
    System.setOut(printStream);
    System.out.println("默认输出到控制台的这一句，输出到了文件 out.txt");
  }

}
