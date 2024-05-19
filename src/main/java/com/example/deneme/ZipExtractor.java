package com.example.deneme;

import java.io.*;
import java.util.zip.*;

public class ZipExtractor {

    public static void unzip(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static void extractAllZipsInDirectory(String zipDirPath, String outputDirPath) {
        File zipDir = new File(zipDirPath);
        File[] zipFiles = zipDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

        if (zipFiles == null) {
            System.out.println("No ZIP files found in the directory.");
            return;
        }

        for (File zipFile : zipFiles) {
            String studentId = zipFile.getName().replace(".zip", "");
            String studentOutputDir = outputDirPath + File.separator + studentId;
            try {
                unzip(zipFile.getAbsolutePath(), studentOutputDir);
                System.out.println("Extracted: " + zipFile.getName());
            } catch (IOException e) {
                System.out.println("Failed to extract: " + zipFile.getName());
                e.printStackTrace();
            }
        }
    }
}
