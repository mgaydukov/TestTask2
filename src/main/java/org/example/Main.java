package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // Проверка аргументов командной строки
        if (args.length != 8 || !args[0].equals("--data") || !args[2].equals("--indexed-column-id")
                || !args[4].equals("--input-file") || !args[6].equals("--output-file")) {
            System.out.println("Usage: java -Xmx7m -jar airports-search.jar --data /home/test/airports.csv --indexed-column-id 3 " +
                    "--input-file /temp/input1.txt --output-file /temp/result1.json");
            System.exit(1);
        }

        // Инициализация параметров
        String airportsFilePath = args[1];
        int indexedColumnId = Integer.parseInt(args[3]);
        String inputFilePath = args[5];
        String outputFilePath = args[7];

        AirportSearch airportSearch = new AirportSearch(indexedColumnId, airportsFilePath);
        long endTime = System.currentTimeMillis();
        long initTime = endTime - startTime; // Время от начала запуска программы до готовности к выполнению первого поиска

        // Поиск аэропортов
        List<SearchResult> searchResultsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                searchResultsList.addAll(airportSearch.outNumbers(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Запись результатов в JSON файл
        OutputData outputData =  new OutputData(initTime, searchResultsList);
        outputData.writeResultsToJsonFile(outputFilePath);
    }
}