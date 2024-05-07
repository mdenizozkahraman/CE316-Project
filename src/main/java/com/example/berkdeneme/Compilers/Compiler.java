package com.example.berkdeneme.Compilers;

import com.example.berkdeneme.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public abstract class Compiler {
    protected File workingDir;

    public Compiler(File workingDirectory) {
        this.workingDir = workingDirectory;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public Result compile_run(String pathOrCommand, String args) throws Exception {                                      // Same function for compiling and running

        Process process = Runtime.getRuntime().exec(pathOrCommand + " " + args, null, workingDir);
        process.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();


        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.destroy();

        return new Result(output.toString());
    }

}


