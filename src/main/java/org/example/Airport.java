package org.example;

class Airport {
    private final String name;
    private final int lineNumber;

    public Airport(int lineNumber, String name) {
        this.lineNumber = lineNumber;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
