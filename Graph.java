package com.example.aidemo;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private HashMap<City, ArrayList<Edge>> edges;
    private HashMap<City, Double> distances;

    public Graph() {
        edges = new HashMap<>();
        distances = new HashMap<>();
    }

    public HashMap<City, Double> getDistances() {
        return distances;
    }

    public void setDistances(HashMap<City, Double> distances) {
        this.distances = distances;
    }

    public void addCity(City city) {
        edges.put(city, new ArrayList<>());

    }

    public void addEdge(City s, City d, double distance) {
        edges.get(s).add(new Edge(s, d, distance));
    }

    public HashMap<City, ArrayList<Edge>> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<City, ArrayList<Edge>> edges) {
        this.edges = edges;
    }

}

