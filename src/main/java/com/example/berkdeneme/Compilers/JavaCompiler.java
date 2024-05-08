package com.example.berkdeneme.Compilers;

import java.io.File;

public class JavaCompiler extends Compiler {
    public static final String COMPILER_PATH = "javac";
    public static final String RUN_COMMAND = "java";

    public JavaCompiler(File workingDirectory) {
        super(workingDirectory);
    }
}
