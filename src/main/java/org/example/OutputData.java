package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class OutputData {
    private long initTime;
    private List<SearchResult> result;

    public OutputData(long initTime, List<SearchResult> result) {
        this.initTime = initTime;
        this.result = result;
    }

    // Запись результатов поиска в JSON файл
    public void writeResultsToJsonFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
