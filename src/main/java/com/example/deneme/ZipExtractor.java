package com.example.deneme;

import java.io.*;
import java.util.zip.*;

public class ZipExtractor {

    private boolean zipExists = false;

    public void extract(String directoryPath) {

        File directory = new File(directoryPath);
        File[] zipFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));
        byte[] buffer = new byte[1024];
        if (zipFiles != null) {
            for (File zipFile : zipFiles) {
                try {
                    ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
                    ZipEntry zipEntry = zipInputStream.getNextEntry();
                    while (zipEntry != null) {
                        File outputFile = new File(directory, zipEntry.getName());
                        if (zipEntry.isDirectory()) {
                            if (!outputFile.exists()) {
                                outputFile.mkdirs();
                            }
                        } else {
                            File parentDir = outputFile.getParentFile();
                            if (!parentDir.exists()) {
                                parentDir.mkdirs();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                            int length;
                            while ((length = zipInputStream.read(buffer)) > 0) {
                                fileOutputStream.write(buffer, 0, length);
                            }
                            fileOutputStream.close();
                        }
                        zipEntry = zipInputStream.getNextEntry();
                    }
                    zipInputStream.closeEntry();
                    zipInputStream.close();
                    this.zipExists = true;

                } catch (IOException e) {
                    e.getMessage();
                }
            }
        } else {
            this.zipExists = false;
        }

    }
}
