package com.example.deneme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

public class ZipExtractor {

    public List<String> extract(String directoryPath) {
        List<String> extractedFolders = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] zipFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));
        byte[] buffer = new byte[1024];

        if (zipFiles != null) {
            for (File zipFile : zipFiles) {
                try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
                    ZipEntry zipEntry = zipInputStream.getNextEntry();

                    while (zipEntry != null) {
                        File outputFile = new File(directory, zipEntry.getName());

                        if (zipEntry.isDirectory()) {
                            if (!outputFile.exists()) {
                                outputFile.mkdirs();
                            }
                            if (!extractedFolders.contains(outputFile.getName())) {
                                extractedFolders.add(outputFile.getName());
                            }
                        } else {
                            File parentDir = outputFile.getParentFile();
                            if (!parentDir.exists()) {
                                parentDir.mkdirs();
                            }
                            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                                int length;
                                while ((length = zipInputStream.read(buffer)) > 0) {
                                    fileOutputStream.write(buffer, 0, length);
                                }
                            }
                            // Add parent directory to the list of extracted folders if not already present
                            if (!extractedFolders.contains(parentDir.getName())) {
                                extractedFolders.add(parentDir.getName());
                            }
                        }
                        zipEntry = zipInputStream.getNextEntry();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (extractedFolders.isEmpty()) {
            System.err.println("No folders found in the extracted zip files.");
        } else {
            System.out.println("Extracted folders: " + extractedFolders);
        }

        return extractedFolders;
    }
}
