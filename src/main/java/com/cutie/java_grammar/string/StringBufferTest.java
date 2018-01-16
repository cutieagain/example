package com.cutie.java_grammar.string;

/**
 * Created by Cutie on 2018/1/16.
 */
public class StringBufferTest {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("abcdefgh");
        //d e f/insert start from c (index 2)
        char[] chars ={'a','b','c','d','e','f','g','h'};
        stringBuffer.insert(2, chars, 4, 3);
        System.out.println(stringBuffer);

        String string = String.valueOf(stringBuffer);
        System.out.println(string);
    }
}
