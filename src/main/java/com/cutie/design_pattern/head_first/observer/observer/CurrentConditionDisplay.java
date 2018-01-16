package com.cutie.design_pattern.head_first.observer.observer;

import com.cutie.design_pattern.head_first.observer.DisplayElement;

/**
 * Created by Cutie on 2018/1/16.
 */
public class CurrentConditionDisplay implements Observer,DisplayElement {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionDisplay temperature:" + temperature + "," +
                "humidity:" + humidity + "," +
                "pressure:" + pressure);
    }
}
