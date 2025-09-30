package org.example.factorymethod;

public class Client {
    public static void run() {
        TransportFactory carFactory = new CarFactory();
        Transport car = carFactory.createTransport();
        car.move();

        TransportFactory planeFactory = new PlaneFactory();
        Transport plane = planeFactory.createTransport();
        plane.move();
    }

    public static void main(String[] args) {
        run();
    }
}
