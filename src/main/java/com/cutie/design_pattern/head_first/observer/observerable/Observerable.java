package com.cutie.design_pattern.head_first.observer.observerable;

import com.cutie.design_pattern.head_first.observer.observer.Observer;

/**
 * Created by Cutie on 2018/1/16.
 */
public interface Observerable {
    void registerObservers(Observer observer);
    void removeObservers(Observer observer);
    void notifyObservers();

}
