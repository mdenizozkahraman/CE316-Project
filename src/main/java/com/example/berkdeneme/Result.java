package com.example.berkdeneme;


public class Result {
    private final String output;
    private final int status;
    private final String error;

    public Result(String output, int status, String error) {
        this.output = output;
        this.status = status;
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}

