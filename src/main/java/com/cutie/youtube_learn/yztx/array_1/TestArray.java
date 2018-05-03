package com.cutie.youtube_learn.yztx.array_1;

/**
 * Created by Cutie on 2018/4/11.
 */
public class TestArray {
    public static void main(String[] args) {
        soutArray();
    }

    /**
     * 数组输出
     */
    private static void soutArray() {
        long[] array = new long[]{2, 3, 4};
        array[0] = 1;
        for (int i = 0; i < 3; i++) {
            System.out.println(array[i]);
        }
    }

}
