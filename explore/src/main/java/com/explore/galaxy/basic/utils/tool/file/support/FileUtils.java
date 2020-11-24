package com.explore.galaxy.basic.utils.tool.file.support;

public class FileUtils {
    public static String getTmpDir() {
        String osName = System.getProperties().getProperty("os.name");
        String dir = "";
        if (osName.toLowerCase().contains("windows")) {
            dir = System.getProperty("java.io.tmpdir");
        } else {
            dir = System.getProperty("java.io.tmpdir") + "/";
        }
        return dir;
    }
}
