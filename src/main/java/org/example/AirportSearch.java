package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AirportSearch {
    private final List<Airport> airports;

    public AirportSearch(int searchColumn, String airportsFilePath) {
        airports = new ArrayList<>();
        loadAirports(searchColumn, airportsFilePath);
    }

    // Загрузка аэропортов из файла по заданному столбцу
    private void loadAirports(int searchColumn, String airportsFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(airportsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0]); //
                String name = parts[searchColumn-1].replaceAll("\"", "");
                airports.add(new Airport(number, name));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Поиск аэропортов по префиксу
    public List<SearchResult> outNumbers(String prefix) {
        long startTime = System.currentTimeMillis();
        List<SearchResult> searchResultList = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        for (Airport airport : airports) {
            if (airport.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                results.add(airport.getLineNumber());
            }
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        searchResultList.add(new SearchResult(prefix, results, executionTime));
        return searchResultList;
    }
}
