package com.cutie.multi_thread.ThreadLoginServlet;

/**
 * Created by cutie on 2017/11/5.
 */
public class ALogin extends Thread{
    @Override
    public void run() {
        ThreadLoginServlet.post("a","aaaa");
    }
}
