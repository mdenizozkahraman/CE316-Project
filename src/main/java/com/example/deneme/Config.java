package com.example.deneme;

enum Language{
    C,JAVA,PYTHON
}

public class Config {
    private static Config instance;
    public Language selectedLanguage = Language.C;
    public String compilerPath;
    public String assignmentPath;
    public String args;
    public String run;
    public String runArgs;
    public String expected;

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
}
