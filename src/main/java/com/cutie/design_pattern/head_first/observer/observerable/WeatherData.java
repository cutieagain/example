package com.cutie.design_pattern.head_first.observer.observerable;

import com.cutie.design_pattern.head_first.observer.observer.Observer;

import java.util.ArrayList;

/**
 * Created by Cutie on 2018/1/16.
 */
public class WeatherData implements Observerable {
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList();
    }

    @Override
    public void registerObservers(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObservers(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity =humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
