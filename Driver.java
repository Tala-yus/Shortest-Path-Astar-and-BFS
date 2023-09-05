package com.example.aidemo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class Driver {

    static Graph graph = new Graph();
    static HashMap<City, HashMap<City, Double>> heuristic = new HashMap<>();
    static HashMap<String, City> cities = new HashMap<>();

    public static void main(String[] args) {

        System.out.println(fileReaderCities("Cities.csv"));
        System.out.println(fileReaderEdges("Roads.csv"));
        System.out.println(fileReaderAirDistance("AirDistance.csv"));

        City source = cities.get("Haifa");
        City des = cities.get("Ramallah");

        AStar astar = new AStar(heuristic);
        BFS bfs = new BFS();

        ArrayList<City> c = bfs.findPath(source, des, graph);
        System.out.println(graph.getDistances().get(des));
        System.out.println(c + "\n");

        ArrayList<City> c1 = astar.findShortestPath(source, des, graph);
        System.out.println(graph.getDistances().get(des));
        System.out.println(c1 + "\n");

    }

    public static String fileReaderCities(String path) {

        try {
            File myfile = new File(path);
            Scanner scanner = new Scanner(myfile);
            if (myfile.exists()) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.split(",");

                    if (!line.isEmpty()) {
                        City c = new City(tokens[0].trim(), Double.parseDouble(tokens[1]),
                                Double.parseDouble(tokens[2].trim()));
                        graph.addCity(c);
                        cities.put(tokens[0].trim(), c);
                    }
                }
            } else {
                return "Error: Reading File!";
            }
            return "File reading Successful!";
        } catch (Exception e) {
            return "Error : Reading File ";
        }

    }

    public static String fileReaderEdges(String path) {

        try {
            File myfile = new File(path);
            Scanner scanner = new Scanner(myfile);

            if (myfile.exists()) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty()) {
                        String[] tokens = line.trim().split(",");
                        if (isExist(graph, tokens[0].trim()) && isExist(graph, tokens[1].trim())) {
                            City source = cities.get(tokens[0].trim());
                            City dest = cities.get(tokens[1].trim());
                            Double weight = Double.parseDouble(tokens[2]);
                            graph.addEdge(source, dest, weight);
                        } else {
                            return "City Doesn't Exist";
                        }
                    }
                }
            } else {
                return "Error: Reading File!";
            }
            return "File reading Successful!";
        } catch (Exception e) {
            return "Error : Reading File ";
        }

    }

    public static boolean isExist(Graph hash, String s) {

        Set<City> list = hash.getEdges().keySet();
        List<City> cities = new ArrayList<>(list);
        boolean flag = false;

        for (int i = 0; i < cities.size(); i++) {

            if (cities.get(i).getName().equalsIgnoreCase(s)) {
                flag = true;
                break;

            }
        }

        return flag;
    }

    public static String fileReaderAirDistance(String path) {
        try {
            File myfile = new File(path);
            Scanner scanner = new Scanner(myfile);
            if (myfile.exists()) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty()) {
                        String tokens[] = line.split(",");
                        City source = cities.get(tokens[0].trim());
                        City destination = cities.get(tokens[1].trim());
                        Double weight = Double.parseDouble(tokens[2]);

                        if (heuristic.containsKey(source)) {

                            heuristic.get(source).put(destination, weight);

                        } else {

                            heuristic.put(source, new HashMap<>());
                            heuristic.get(source).put(source, 0.0);
                            heuristic.get(source).put(destination, weight);

                        }

                    }
                }
            } else {
                return "Error: Reading File!";
            }
            return "File reading Successful!";
        } catch (Exception e) {
            return "Error : Reading File ";
        }

    }



}
