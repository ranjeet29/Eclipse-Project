package com.android.uiautomator;
import java.io.File;
public class Utils {
    public static void deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            for (File child : children) {
                if (!child.getName().startsWith("."))
                    deleteRecursive(child);
            }
        }
        file.delete();
    }
}