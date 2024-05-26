package com.example.deneme.Compilers;
import com.example.deneme.Result;
import java.io.File;
public class PythonInterpreter extends Compiler {
    public static final String COMPILER_PATH = "python";
    public static final String ARGS = "PythonTest.py";

    public PythonInterpreter(File workingDirectory) {
        super(workingDirectory);
    }
    @Override
    public Result compile(String path, String args) throws Exception {
        return super.compile(path, args);
    }
}

