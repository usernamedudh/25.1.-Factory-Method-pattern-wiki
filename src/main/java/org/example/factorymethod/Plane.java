package org.example.factorymethod;

public class Plane implements Transport {
    @Override
    public void move() {
        System.out.println("The plane is flying in the sky.");
    }
}
