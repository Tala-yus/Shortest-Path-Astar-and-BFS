package com.example.aidemo;

public class City {

    private String name;
    private City parent;
    private double x;
    private double y;

    public City(String name, double x, double y) {
        super();
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public City getParent() {
        return parent;
    }

    public void setParent(City parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City [name=" + name + "]";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
