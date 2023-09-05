package com.example.aidemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {
    static long execution =0;
    private HashMap<City, HashMap<City, Double>> heuristic = new HashMap<>();

    public AStar(HashMap<City, HashMap<City, Double>> heuristic) {
        super();
        this.heuristic = heuristic;
    }

    public ArrayList<City> findShortestPath(City source, City goal, Graph graph) {
        long start = System.currentTimeMillis();
        HashMap<City, Boolean> state = new HashMap<>(); // HashMap to determine the state of each city visited or not
        graph.setDistances(new HashMap<>()); // clear all the paths and distances
        PriorityQueue<HeapCost> heap = new PriorityQueue<>(); // Heap to save the cost of the path and keep the minimum at the root
        ArrayList<City> path = new ArrayList<>(); // to save path

        HeapCost hO = new HeapCost(source, 0); // object to save each city in the heap with its corresponding cost( g(n) + h(n) )
        hO.calculateCost(0, heuristic.get(source).get(goal)); // method to add g(n) + h(n)

        graph.getDistances().put(source, 0.0); //initialize the distances 0

        state.put(source, true); // put the state for the source (true) which means its visited

        heap.add(hO); //add to the heap

        while (!heap.isEmpty()) {

            HeapCost c = heap.poll(); //delete the minimum

            if (c.getCity() == goal) { // backtrace the path by tracking the previously assigned parents to each city
                City city = goal;
                while (city != source) {
                    path.add(city);
                    city = city.getParent();
                }
                path.add(source);
                Collections.reverse(path); // reverse
                long end = System.currentTimeMillis();
                execution = end - start;
                return path;
            }

            ArrayList<Edge> edges = graph.getEdges().get(c.getCity()); // get the neighbours for the city that got deleted from the heap

            for (int i = 0; i < edges.size(); i++) {
                City neighbour = edges.get(i).getGoal();
                double weight = edges.get(i).getDistance();

                if (state.get(neighbour) == null) { //if the city isn't visited
                    HeapCost node = new HeapCost(neighbour, 0); // new object to put in the heap

                    double distance = weight + graph.getDistances().get(c.getCity()); // add the distance between (the city and its neighbour)
                                                                                     // with the current distance that was reached so far
                    graph.getDistances().put(neighbour, distance); //update the distance that is reached so far with the end city

                    node.calculateCost(distance, heuristic.get(goal).get(neighbour)); // calculate the f(n) = g(n) + h(n) for the neighbour

                    heap.add(node); // add it to the heap

                    neighbour.setParent(c.getCity()); // set the parent for the neighbour

                    state.put(neighbour, true); // change the state for the neighbour to visited

                }

            }

        }

        return path;
    }

    public HashMap<City, HashMap<City, Double>> getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(HashMap<City, HashMap<City, Double>> heuristic) {
        this.heuristic = heuristic;
    }

//	public HashMap<City, Boolean> getState() {
//		return state;
//	}
//
//	public void setState(HashMap<City, Boolean> state) {
//		this.state = state;
//	}

}
