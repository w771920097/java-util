package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class 随机不重复数 {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    
    //网上方法获取不重复随机数
//    testA(100000);
    //自定义方法获取不重复随机数
    testB(100000);
}

//自定义方法获取不重复随机数
private static void testB(int sz){
    long startTime=System.currentTimeMillis(); //开始测试时间
    Random rd = new Random();
    int[] rds = new int[sz];//随机数数组
    int n = 0;//序号
    List<Integer> lst = new ArrayList<Integer>();//存放有序数字集合
    //获取随机数数组, 里面有重复数字
    while (n < rds.length) {
        lst.add(n);
        rds[n++] = (int)(rd.nextFloat() * sz);
    }
    //把随机数和有序集合进行匹对, 把随机数在集合出现的数字从集合中移除掉.
    for (int i = 0; i < rds.length; i++) {
        for (int j = 0; j < lst.size(); j++) {
            if (rds[i] == lst.get(j)) {
                lst.remove(j);
                break;
            }
        }
    }
    //把数组中重复的第二个数字用集合的第一个数字替换掉, 并移除掉数组的第一个数字
    for (int i = 0; i < rds.length; i++) {
        for (int j = 0; j < rds.length; j++) {
            if (i != j && rds[i] == rds[j]) {
                rds[j] = lst.get(0);
                lst.remove(0);
                break;
            }
        }
    }
    System.out.println("length" + rds.length);
    //得到的  rds  数组就是不重复的随机数组
    long endTime=System.currentTimeMillis(); //获取结束时间 
    System.out.println("自定义代码运行时间： "+(endTime-startTime)+"ms"); 
}
//网上方法获取不重复随机数
private static void testA(int sz){
    long startTime=System.currentTimeMillis(); //开始测试时间
    Random random = new Random();
    int a[] = new int[sz];
    for (int i = 0; i < a.length; i++) {
        a[i] = random.nextInt(sz);
        for (int j = 1; j < i; j++) {
            while (a[i] == a[j]) {//如果重复，退回去重新生成随机数
                i--;
            }
        }
    }
    System.out.println("length" + a.length);
    long endTime=System.currentTimeMillis(); //获取结束时间 
    System.out.println("网上思路代码运行时间： "+(endTime-startTime)+"ms"); 
}
}
