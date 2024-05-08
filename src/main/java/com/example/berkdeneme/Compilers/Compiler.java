package com.example.berkdeneme.Compilers;


import com.example.berkdeneme.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public abstract class Compiler {
    protected final File workingDirectory;

    public Compiler(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public Result compile(String path, String args) throws Exception {
        Process process = Runtime.getRuntime().exec(path + " " + args, null, workingDirectory);
        process.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder outputBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            outputBuilder.append(line).append("\n");
        }
        while ((line = errorReader.readLine()) != null) {
            errorBuilder.append(line).append("\n");
        }

        process.destroy();
        Result result = new Result(outputBuilder.toString(), process.exitValue(), errorBuilder.toString());
        return result;
    }

    public Result run(String command, String args) throws Exception {
        Process process = Runtime.getRuntime().exec(command + " " + args, null, workingDirectory);
        process.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        StringBuilder outputBuilder = new StringBuilder();
        StringBuilder errorBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            outputBuilder.append(line).append("\n");
        }
        while ((line = errorReader.readLine()) != null) {
            errorBuilder.append(line).append("\n");
        }
        process.destroy();
        Result result = new Result(outputBuilder.toString(), process.exitValue(), errorBuilder.toString());
        return result;
    }
    public File getWorkingDirectory() {
        return workingDirectory;
    }
}

