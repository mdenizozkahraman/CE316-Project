package com.example.deneme;


public class Config {

    private static Config instance;
    public Language SELECTEDLANGUAGE = Language.C;
    public String COMPILERPATH;
    public String COMPILERINTERPRETERARGS;
    public String RUNCOMMAND;
    public String EXPECTEDOUTCOME;
    public String RUNCOMMANDARGS;

    private Config(){

    }
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

}
