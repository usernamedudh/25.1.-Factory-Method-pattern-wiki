package org.example.factorymethod;

public class Car implements Transport {
    @Override
    public void move() {
        System.out.println("The car is driving on the road.");
    }
}
