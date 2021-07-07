package com.shark.example.string;

public class SplitExample {
    public static void main(String[] argv) {
        String path = "C:\\Users\\Shark%20Lin\\Documents\\HW\\LeSheng\\Bussiness\\business-api\\target\\business-api\\WEB-INF\\resource\\ipipfree.ipdb";
        String[] folders = path.split("\\\\");
        StringBuilder newPathStringBuilder = new StringBuilder();
        int i = 0;
        for(String folder: folders) {
            if(i != 0) {
                newPathStringBuilder.append("\\");
            }
            if (folder.contains("%20")) {
                folder = folder.replace("%20", " ");
                newPathStringBuilder.append("\'");
                newPathStringBuilder.append(folder);
                newPathStringBuilder.append("\'");
            } else {
                newPathStringBuilder.append(folder);
            }
            i ++;
        }
        String newPath =  newPathStringBuilder.toString();
        System.out.println("newPath: " + newPath);
    }
}
