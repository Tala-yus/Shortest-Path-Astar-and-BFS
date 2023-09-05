package com.example.aidemo;

public class Edge {

    private City source;
    private City goal;
    private double distance;

    public Edge(City source, City goal, double distance) {
        super();
        this.source = source;
        this.goal = goal;
        this.distance = distance;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getGoal() {
        return goal;
    }

    public void setGoal(City goal) {
        this.goal = goal;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
