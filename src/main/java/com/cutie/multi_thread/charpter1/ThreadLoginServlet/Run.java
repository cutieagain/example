package com.cutie.multi_thread.charpter1.ThreadLoginServlet;

/**
 * Created by cutie on 2017/11/5.
 */
public class Run {
    public static void main(String[] args) {
        ALogin aLogin = new ALogin();
        aLogin.start();
        BLogin bLogin = new BLogin();
        bLogin.start();
    }
}
