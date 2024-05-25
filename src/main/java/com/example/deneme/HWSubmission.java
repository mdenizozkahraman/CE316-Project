package com.example.deneme;

public class HWSubmission {
    private final String id;
    private final String output;
    private final String result;
    private final int status;
    private final String error;
    private final String expected;

    public HWSubmission(String id, String output, String result, int status, String error, String expected) {
        this.id = id;
        this.output = output;
        this.result = result;
        this.status = status;
        this.error = error;
        this.expected = expected;
    }

    public String getExpected() {
        return expected;
    }
}
