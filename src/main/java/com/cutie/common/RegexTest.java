package com.cutie.common;

/**
 * Created by Cutie on 2018/5/3.
 */
public class RegexTest {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String s = "^@帅o1\u001cN(帅-_ ^@491428950594";
//        String a = s.replaceAll("[^0-9a-zA-Z-_]", "");
        String a = s.replaceAll("[^\\w-]", "");

        System.out.println(a);
    }
}
