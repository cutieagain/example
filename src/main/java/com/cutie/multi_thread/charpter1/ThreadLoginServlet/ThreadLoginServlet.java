package com.cutie.multi_thread.charpter1.ThreadLoginServlet;

/**
 * Created by cutie on 2017/11/5.
 */
public class ThreadLoginServlet {
    private static String usernameRef;
    private static String passwordRef;

    synchronized public static void post(String username, String password){

            usernameRef = username;

            passwordRef = password;
            System.out.println("name:"+passwordRef + ";password:" + passwordRef);

    }

}
