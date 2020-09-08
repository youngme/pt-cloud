package com.bin.cloud.auth.server.utils;

import java.util.Random;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/7/14 08:08
 */
public class CommonConstants {

    public static final String UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE = "application/json";

    //生成随机用户名，数字和字母组成,
    public static String getRandomName() {

         String val = "";
         Random random = new Random();

         //参数length，表示生成几位随机数
         for(int i = 0; i < 15; i++) {

                 String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                 //输出字母还是数字
                 if( "char".equalsIgnoreCase(charOrNum) ) {
                         //输出是大写字母还是小写字母
                         int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                         val += (char)(random.nextInt(26) + temp);
                     } else if( "num".equalsIgnoreCase(charOrNum) ) {
                         val += String.valueOf(random.nextInt(10));
                     }
            }
         return val;
    }

}
