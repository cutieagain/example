package com.cutie.algorithm.think.lottery;

import java.util.Random;

/**
 * Created by cutie on 2018/4/7.
 * 爬取 http://www.310win.com/vote/jclq_112.html 上的
 */
public class BasketballLottery {

    public static void main(String[] args) {
        //主要是计算能承受几次黑脸情况

        //投入钱，中奖拿出重新投入；不中奖倍投
        int winCount = 0;
        for (int j = 0; j < 100; j++) {
            double totalBonus = 0;
            for (int i = 0; i < 36; i++) {
                double bonus = getBonus();
                totalBonus += bonus;
                System.out.println("getBonus() = " + bonus + ",totalBonus = " + totalBonus);
            }
            winCount += totalBonus > 0 ? 1 : 0;
        }
        System.out.println("winCount = " + winCount);
    }

    public static double getBonus(){
        double totalInput = 0;
        double boundary = 100;
        double totalBonus = 0;
        int inputCount = 20;

        double ratio = 1.68 * 1.68;

        for (int i = 1; i <= inputCount; i++) {
            //每次投入
            double currentInput = getCurrentInput(i);

            if(currentInput > 10240){
                System.out.println("如果超出承受范围，gg = " + getCurrentInput(i+1));
//                i = 1;
//                totalBonus = 0;
//                totalInput = 0;
                break;
            }

            totalInput += currentInput;
//            System.out.println("currentInput = " + currentInput);
            //如果中奖，从 initInput 开始 or //如果超出承受范围，gg
            if (isGetBonus()){
                totalBonus += currentInput * (ratio - 1);
//                System.out.println("win : currentInput = " + currentInput + ",totalBonus = " + totalBonus + ",totalInput = " + totalInput);
                return totalBonus;
            }else{
                //如果没中奖，则增加金额，总获奖金额减去投入的金额
                totalBonus -= currentInput;
            }
//            System.out.println("lose : currentInput = " + currentInput + ",totalBonus = " + totalBonus + ",totalInput = " + totalInput);

        }
        return totalBonus;
    }

    // 2 4 12 36 108 324
    // 2 4 8 16 32 64 128
    private static double getCurrentInput(int count){
        double initInput = 20;
        double fiducialValue = 2;
        if (count == 1){
            return initInput;
        }
        if (count == 2){
            return initInput * 2;
        }
        return getCurrentInput(count - 1) * fiducialValue;
//        return getCurrentInput(count - 1) * 2 + 100;
    }

    private static boolean isGetBonus(){
        return new Random().nextInt(10) < 4;
    }

}
