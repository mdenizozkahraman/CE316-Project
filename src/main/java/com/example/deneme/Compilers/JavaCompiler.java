package com.example.deneme.Compilers;
import java.io.File;
public class JavaCompiler extends Compiler {
    public static final String COMPILER_PATH = "javac";
    public static final   String ARGS = "Test.java";
    public static final String RUN_COMMAND = "java Test";
    public JavaCompiler(File workingDirectory) {
        super(workingDirectory);
    }
}
