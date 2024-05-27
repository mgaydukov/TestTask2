package org.example;

import java.util.List;

class SearchResult {
    private String search;
    private List<Integer> result;
    private long time;

    public SearchResult(String search, List<Integer> result, long time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }
}