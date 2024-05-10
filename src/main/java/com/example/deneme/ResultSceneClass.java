package com.example.deneme;

public class ResultSceneClass {

    private String expectedOutput;
    private String runOutput;
    private String result;

    public ResultSceneClass(String expectedOutput, String runOutput, String result) {
        this.expectedOutput = expectedOutput;
        this.runOutput = runOutput;
        this.result = result;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public String getRunOutput() {
        return runOutput;
    }

    public String getResult() {
        return result;
    }

}
