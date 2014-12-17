package com.eric.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * NOTE: list file format must match "D:/MKS_BASE/PCEImportExport/ComponentBuild/archives.include" file name just can
 * contain one "."
 * 
 * */

public class CopyFileByList {
    public static final String DESTDIR = "D:/testdir";
    public static final String SPLITE = "/";
    public static final String LISTFILE = "C:/MKS/PCE-AP/dev/PersonUtil/src/com/eric/copyfile/lists.txt";
    public static final String ERROR_FILE_PATH = "C:/MKS/PCE-AP/dev/PersonUtil/src/com/eric/copyfile/errorlist.txt";
    public static final int BEGIN = 2;
    public static List<String> errorList = new ArrayList<String>();

    public static void main(String[] args) {
        File file = new File(LISTFILE);
        try {
            BufferedReader bw = new BufferedReader(new FileReader(file));
            String filepath;
            while ((filepath = bw.readLine()) != null) {
                StringBuilder destDir = new StringBuilder(DESTDIR);
                String[] filePathSplite = filepath.split(SPLITE);
                if (!filePathSplite[filePathSplite.length - 1].contains(".")) {
                    copyDirFile(filepath.trim(),DESTDIR);
                    continue;
                }
                genDirPath(destDir, filePathSplite);
                doCopyFile(filepath.trim(), destDir.toString().trim());
            }
            bw.close();
            System.out.println("********** generate error file*********");
            for (String listItem : errorList) {
                FileWriter fw = new FileWriter(ERROR_FILE_PATH);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(listItem);
                out.newLine();
                out.flush();
                out.close();
                fw.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void genDirPath(StringBuilder destDir, String[] filePathSplite) {
        // begin with i
        for (int i = BEGIN; i < filePathSplite.length; i++) {
            destDir.append(SPLITE + filePathSplite[i]);
            if (i < filePathSplite.length - 1) {
                new File(destDir.toString().trim()).mkdir();
            }
        }
    }

    public static void doCopyFile(String src, String dest) throws IOException {
        File srcFile = new File(src);
        File destFile = new File(dest);
        if (destFile.exists()) {
            boolean d = destFile.delete();

            if (d) {
                System.out.print("删除成功！");
            } else {
                System.out.print("删除失败！");
            }
        }
        FileInputStream input = new FileInputStream(srcFile);
        try {
            FileOutputStream output = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int n = 0;
                while (-1 != (n = input.read(buffer))) {
                    output.write(buffer, 0, n);
                }
                System.out.println("Copy Successful::" + dest);
            } finally {
                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ioe) {
                System.out.println("failed src file:" + src + " reason:" + ioe.getMessage());
            }
        }
    }

    public static boolean copyDirFile(String filepath,String destdir) throws FileNotFoundException, IOException {
        try {
            if (filepath == null || filepath.trim().equals("")) {
                return false;
            }
            String filePathTemp = filepath.trim();
            File file = new File(filepath.trim());
            if (file.isFile()) {
                StringBuilder dest = new StringBuilder(destdir);
                String[] filePathSplite = file.getAbsolutePath().replace("\\", SPLITE).split(SPLITE);
                genDirPath(dest, filePathSplite);
                doCopyFile(filepath.trim(), dest.toString().trim());
            } else {
                File[] filelist = file.listFiles();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filePathTemp.trim() + SPLITE + filelist[i].getName());
                    if (!readfile.isDirectory()) {
                        StringBuilder dest = new StringBuilder(destdir);
                        String[] filePathSplite = readfile.getAbsolutePath().replace("\\", SPLITE).split(SPLITE);
                        genDirPath(dest, filePathSplite);
                        if (!filepath.contains(".")) {
                            filepath = filepath.trim() + SPLITE + readfile.getName();
                        }
                        doCopyFile(filepath.trim(), dest.toString().trim());

                    } else if (readfile.isDirectory()) {
                        copyDirFile(filelist[i].getPath(),DESTDIR);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            errorList.add(e.getMessage());
            System.out.println("readfile() Exception:" + e.getMessage());
        }
        return true;
    }

}
