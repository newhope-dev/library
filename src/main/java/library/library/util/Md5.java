package library.library.util;

import org.springframework.util.DigestUtils;



public class Md5 {

   public static String  getMd5(String password,String salt){

       String str=salt+password+salt;
       for(int i=0;i<3;i++){
           str= DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
       }
       return  str;
   }
}
