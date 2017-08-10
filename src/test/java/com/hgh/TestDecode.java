package com.hgh;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/7/18.
 */
public class TestDecode {

    public static void main(String[] args) throws Exception{

      /*  String string = new String("手表".getBytes("gb2312"));
        String encode = URLEncoder.encode(string);
        System.out.println(encode);
        String encode1 = URLDecoder.decode(new String("%CA%D6%B1%ED".getBytes("GB2312")));
        System.out.println(encode1);*/

      /*for (int i=0;i<=100000;i++){
          int i1 = RandomUtils.nextInt(0, 29);
          System.out.println(i1);
      }*/

      /*String[] a = new String[]{"1","2"};
      System.out.println(a[0]);*/
      /*  int i = 1956 / 7;
        System.out.println(i);*/

   /* String a = "abc\b\bd\b\bghi";

        TestDecode.stringReplace2(a);*/


        /*String s = TestDecode.stringReplace1(a);
      System.out.println(s);
      String a  =" \'a \'";
     System.out.println(a);*/

        String a = new String ("abc\n\\\'\"");
        System.out.println(a.length());

    }


    public static String stringReplace1(String str) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        int index = str.indexOf('\b');
        if (index == -1) {
            return str;
        }
        return stringReplace1(index <= 1 ? str.substring(index + 1)
                : str.substring(0, index - 1) + str.substring(index + 1));
    }

   /* public static String stringReplace2(String str) {

        int i = str.indexOf("\b");
    }*/

    public static String stringReplace2(String str) {
        if (StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '\b') {
                sb.append(str.charAt(i));
                count++;
            } else {
                if (count > 0) {
                    sb.deleteCharAt(count - 1);
                    count--;
                }
            }
        }

        return sb.toString();
    }

}
