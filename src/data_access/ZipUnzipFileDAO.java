/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_access;

import common.Validation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author GoldCandy
 */
public class ZipUnzipFileDAO {

    private static ZipUnzipFileDAO instance = null;
    private final Validation valid = new Validation();

    public static ZipUnzipFileDAO Instance() {
        if (instance == null) {
            synchronized (ZipUnzipFileDAO.class) {
                if (instance == null) {
                    instance = new ZipUnzipFileDAO();
                }
            }
        }
        return instance;
    }

    public boolean checkPath(String source, String destination) {
        File f = new File(source);
        if (f.getName().endsWith(".zip")) {
            if (!f.isFile()) {
                System.out.println("Source file not found");
                return false;
            }
        } else if (!f.isDirectory()) {
            System.out.println("Source path not found");
            return false;
        }
        f = new File(destination);
        if (!f.isDirectory()) {
            if (f.mkdir()) {
                return true;
            } else {
                System.out.println("Failed to create destination path");
                return false;
            }
        }
        return true;
    }

    public boolean zip(String source, String destination, String name) {
        if (!checkPath(source, destination)) {
            return false;
        }
        File f = new File(source);
        File[] files = f.listFiles();
        try {
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(destination + File.separator + name + ".zip"));
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    FileInputStream fis = new FileInputStream(file);
                    zipOut.putNextEntry(new ZipEntry(file.getName()));
                    //write to zip
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) >= 0) {
                        zipOut.write(buffer, 0, length);
                    }
                    fis.close();
                }
            }
            zipOut.close();
        } catch (IOException e) {
            System.out.println("File not found");;
        }
        return true;
    }

    public boolean compression() {
        String source = valid.inputString("Enter Source Folder");
        String destination = valid.inputString("Enter Destination Folder");
        String name = valid.inputString("Enter Name");
        if (!zip(source, destination, name)) {
            return false;
        }
        return true;
    }

    public boolean unzip(String source, String destination) {
        if (!checkPath(source, destination)) {
            return false;
        }
        File destDir = new File(destination);
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(source));
            ZipEntry zipEntry = zis.getNextEntry();
            byte[] buffer = new byte[1024];
            while (zipEntry != null) {
                System.out.println(zipEntry.getName());
                File newFile = new File(destDir + File.separator + zipEntry.getName());
                //make directory for sub directories in zip
                //Example: abc.zip -> abc -> abc.exe
                new File(newFile.getParent()).mkdir();
                FileOutputStream fos = new FileOutputStream(newFile);
                int length;
                while ((length = zis.read(buffer)) >= 0) {
                    fos.write(buffer, 0, length);
                }
                fos.close();
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException e) {
            System.out.println("File not found");
            return false;
        }
        return true;
    }

    public boolean extraction() {
        String source = valid.inputString("Enter Source File");
        String destination = valid.inputString("Enter Destination Folder");
        return unzip(source, destination);
    }
}
