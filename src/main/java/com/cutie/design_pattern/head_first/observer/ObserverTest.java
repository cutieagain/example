package com.cutie.design_pattern.head_first.observer;

import com.cutie.design_pattern.head_first.observer.observer.CurrentConditionDisplay;
import com.cutie.design_pattern.head_first.observer.observer.Observer;
import com.cutie.design_pattern.head_first.observer.observerable.WeatherData;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Cutie on 2018/1/16.
 */
public class ObserverTest {
    public static void main(String[] args) {
        Observer currentConditionDisplay = new CurrentConditionDisplay();
        WeatherData weatherData = new WeatherData();
        weatherData.registerObservers(currentConditionDisplay);
        weatherData.setMeasurements(2.1F, 4.2F, 5.2F);
        weatherData.setMeasurements(21.1F, 4.3212F, 5321.2F);
    }
}
