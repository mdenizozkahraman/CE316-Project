package com.example.berkdeneme.Compilers;

import com.example.berkdeneme.Result;

import java.io.File;

public class TestCompiler {
    public static void main(String[] args) {
        File workingDirectory = new File("TestFiles");

        JavaCompiler javaCompiler = new JavaCompiler(workingDirectory);

        try {
            Result compileResult = javaCompiler.compile(JavaCompiler.COMPILER_PATH, "Test.java");


            System.out.println("Compile Output:");

            System.out.println(compileResult.getOutput());

            System.out.println("Compile Error:");
            System.out.println(compileResult.getError());

            System.out.println("Compile Exit Code: " + compileResult.getStatus());


            if (compileResult.getStatus() == 0) {

                Result runResult = javaCompiler.run(JavaCompiler.RUN_COMMAND, "Test");

                System.out.println("Run Output:");
                System.out.println(runResult.getOutput());

                System.out.println("Run Error:");
                System.out.println(runResult.getError());

                System.out.println("Run Exit Code: " + runResult.getStatus());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }



        PythonInterpreter pythonInterpreter = new PythonInterpreter(workingDirectory);

        try {
            Result runResult = pythonInterpreter.run(PythonInterpreter.COMPILER_PATH, PythonInterpreter.ARGS);

            System.out.println("Run Output:");
            System.out.println(runResult.getOutput());

            System.out.println("Run Error:");
            System.out.println(runResult.getError());

            System.out.println("Run Exit Code: " + runResult.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
