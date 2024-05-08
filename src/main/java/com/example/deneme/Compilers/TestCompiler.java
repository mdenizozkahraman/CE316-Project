package com.example.deneme.Compilers;

import com.example.deneme.Result;


import java.io.File;

public class TestCompiler {
    public static void main(String[] args) {
        File workingDirectory = new File("src/main/java/com/example/deneme/Compilers/main.c");

        CCompiler compiler = new CCompiler(workingDirectory);

        try {
            Result compileResult = compiler.compile_run("gcc", "main.c " + CCompiler.arguments);

            if (compileResult != null) {
                System.out.println("Compilation successful.");

                Result runResult = compiler.compile_run(CCompiler.running_command, "");

                if (runResult != null) {
                    System.out.println("Output:");
                    System.out.println(runResult.getOutput());
                } else {
                    System.out.println("Execution failed.");
                }
            } else {
                System.out.println("Compilation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
