package com.example.berkdeneme.Compilers;

import java.io.File;

public class CCompiler extends Compiler {
    public static String compiler = "gcc";
    public static String arguments = "-o main";
    public static String running_command = "./main";

    public CCompiler(File workingDirectory) {
        super(workingDirectory);
    }

}


