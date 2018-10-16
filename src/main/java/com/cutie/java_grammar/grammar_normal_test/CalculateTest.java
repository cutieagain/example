package com.cutie.java_grammar.grammar_normal_test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cutie on 2018/3/13.
 */
public class CalculateTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-" + 9);
        String ctime = formatter.format(new Date());
        System.out.println(ctime);

        formatter = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(formatter.parse(ctime));

        Double db = 6.00;
        int it = db.intValue();
        System.out.println(it);
    }
}
