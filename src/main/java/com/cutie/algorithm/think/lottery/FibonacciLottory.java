package com.cutie.algorithm.think.lottery;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by cutie on 2018/3/31.
 */
public class FibonacciLottory {
    public static void main(String[] args) {
        new FibonacciLottory().listWithMultiple();
    }




    private static int listFibonacci(int a){
        if(1==a || 2==a){
            return 1;
        }
        return listFibonacci(a-1) + listFibonacci(a-2);
    }

    private void listWithMultiple(){
        /*for (int i = 1; i < 100; i++) {
            System.out.print(listFibonacci(i) + " ");
        }*/
        DecimalFormat decimalFormat =new DecimalFormat("#.00");
        double multiple = 1.68*1.68;
        double initValue = 4;

        double[] array = new double[100];
        array[0] = initValue;
        array[1] = initValue;
        for (int i = 2; i < 100; i++) {
            array[i] = array[i-1]+array[i-2];
        }
        System.out.println("multiple：" + multiple);
        System.out.print("第1次投入：" + decimalFormat.format(new BigDecimal(initValue*2).doubleValue()) + " ");
        System.out.print("成本：" + decimalFormat.format(new BigDecimal(initValue*2).doubleValue()) + " ");
        System.out.println("最低利润：" + decimalFormat.format(new BigDecimal(initValue*2 * (multiple-1)).doubleValue()));
        for (int i = 2; i < 20; i++) {
            double result = array[i-1] + array[i-2];
            double profit = array[i] * (multiple-1);
            System.out.print("第"+ i +"次投入：" + decimalFormat.format(new BigDecimal(result).doubleValue()) + " ");
            System.out.print("成本：" + decimalFormat.format(new BigDecimal(result*2).doubleValue()) + " ");
            System.out.println("最低利润：" + decimalFormat.format(new BigDecimal(profit).doubleValue()));
        }
    }
}
