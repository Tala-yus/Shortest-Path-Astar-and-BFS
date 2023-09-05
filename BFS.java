package com.example.aidemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    static long execution =0;

    public ArrayList<City> findPath(City source, City goal, Graph graph) {

        long start = System.currentTimeMillis();

        HashMap<City, Boolean> state = new HashMap<>();  // HashMap to determine the state of each city visited or not

        graph.setDistances(new HashMap<>()); // clear all the paths and distances

        Queue<City> queue = new LinkedList<>(); //queue to explore the graph level by level

        ArrayList<City> path = new ArrayList<>(); // to save the path


        queue.add(source); //add to first of the queue

        graph.getDistances().put(source, 0.0); //initialize the distances from source to source 0

        state.put(source, true); //put the state for the source (true) which means its visited

        while (!queue.isEmpty()) {

            City c = queue.remove();
            state.put(c, true);
            if (c == goal) { // backtrace the path by tracking the previously assigned parents to each city
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
            ArrayList<Edge> edges = graph.getEdges().get(c); // get the neighbours for the city that got deleted from the queue
            for (int i = 0; i < edges.size(); i++) {
                City neighbour = edges.get(i).getGoal();
                double weight = edges.get(i).getDistance();
                if (state.get(neighbour) == null && !queue.contains(neighbour)) { //if the city isn't visited

                    double distance = weight + graph.getDistances().get(c); // add the distance between (the city and its neighbour)
                                                                           // with the current distance that was reached so far
                    graph.getDistances().put(neighbour, distance);  //update the distance that is reached so far with the end city
                    queue.add(neighbour); //add to the queue
                    neighbour.setParent(c); // set the parent city for the neighbour
                    state.put(neighbour, true); // change the state to visited so we don't visit it again

                }

            }

        }

        return path;

    }

}
