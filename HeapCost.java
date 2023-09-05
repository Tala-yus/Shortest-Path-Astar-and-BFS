package com.example.aidemo;


public class HeapCost implements Comparable<HeapCost> {

    private City city;
    private double cost;

    public HeapCost(City city, double cost) {
        super();
        this.city = city;
        this.cost = cost;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void calculateCost(double roadDistance, double airDistance) {

        this.cost = roadDistance + airDistance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "HeapCost [city=" + city + ", cost=" + cost + "]";
    }

    @Override
    public int compareTo(HeapCost o) {
        if (this.cost < o.cost) {
            return -1;
        } else if (this.cost > o.cost) {
            return 1;
        } else
            return 0;
    }

}
