package com.cutie;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String s = "帅o1\u001cN(帅-_";
//        String a = s.replaceAll("[^0-9a-zA-Z-_]", "");
        String a = s.replaceAll("[^\\w-]", "");

        System.out.println(a);
    }
}
