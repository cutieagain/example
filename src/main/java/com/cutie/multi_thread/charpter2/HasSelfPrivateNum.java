package com.cutie.multi_thread.charpter2;

/**
 * Created by cutie on 2017/11/6.
 */
public class HasSelfPrivateNum {
    public void addT(String username){
        int num = 0;
        try {
            if(username.equals("a")){
                num = 100;
                System.out.println("a set over");
                Thread.sleep(1000L);
            }else{
                num=200;
                System.out.println("b set over");
            }
            System.out.println(username +" num=" + num);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

    }

}
