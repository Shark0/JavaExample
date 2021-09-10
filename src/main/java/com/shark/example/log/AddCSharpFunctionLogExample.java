package com.shark.example.log;

import com.shark.example.util.StringUtil;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AddCSharpFunctionLogExample {

    private static final String PROJECT_PATH = "D:\\HW\\Fish\\Source_Code\\GameServer\\JCBYServer2.0";
    private static final String CLASS_FILE_EXTENSION = ".cs";
    private static final String LOG_FUNCTION_START = "MyLog.All(\"";
    private static final String LOG_FUNCTION_END = "\");";

    public static void main(String argv[]) {
        File file = new File(PROJECT_PATH);
        addLog(file);
    }

    private static void addLog(File file) {
        if(file.isDirectory() && !(file.getName().equals("bin") || file.getName().equals("obj"))) {
            File childFileArray[] = file.listFiles();
            for(File childFile: childFileArray) {
                addLog(childFile);
            }
            return;
        }
        if(!isClassFile(file)) {
            return;
        }
        List<FunctionLogNode> functionLogNodeList = parserClassFile(file);
        if(functionLogNodeList.isEmpty()) {
            return;
        }
        addFunctionNodeLog(file, functionLogNodeList);
    }

    private static boolean isClassFile(File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.length() - CLASS_FILE_EXTENSION.length()).equalsIgnoreCase(CLASS_FILE_EXTENSION);
    }

    private static List<FunctionLogNode> parserClassFile(File classFile) {
        List functionNodeList = new ArrayList();
        List<String> codeLineList;
        try {
            codeLineList = Files.readAllLines(classFile.toPath());
            for(int i = 0; i < codeLineList.size(); i ++) {
                try {
                    FunctionLogNode functionLogNode = generateFunctionNode(codeLineList, i);
                    if(functionLogNode != null) {
                        functionNodeList.add(functionLogNode);
                    }
                } catch (Exception e) {
                    System.out.println("fileAbsolutePath: " + classFile.getAbsolutePath());
                    System.out.println("i: " + i + ", code: " + codeLineList.get(i).trim());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return functionNodeList;
    }

    private static FunctionLogNode generateFunctionNode(List<String> codeLineList , int index) {
        String code = codeLineList.get(index);
        String trimCode = code.trim();
        if(StringUtil.isEmpty(trimCode) ||
                !trimCode.matches("[\\w\\s]+\\w+\\(.*\\)")) {
            return null;
        }

        String[] subCodeArray = code.split(" ");
        for(String subCode: subCodeArray) {
            if(subCode.contains("(")) {
                String functionName = subCode.split("\\(")[0];
                FunctionLogNode functionLogNode = new FunctionLogNode();
                functionLogNode.setCode(code);
                functionLogNode.setFunctionName(functionName);
                int spaceIndex = 0;
                while (code.substring(spaceIndex, spaceIndex + 1).equalsIgnoreCase(" ")) {
                    spaceIndex = spaceIndex + 1;
                }
                functionLogNode.setSpaceCount(spaceIndex);
                int curlyBracketIndex = 0;
                while (!codeLineList.get(index + curlyBracketIndex).trim().equalsIgnoreCase("{")) {
                    curlyBracketIndex = curlyBracketIndex + 1;
                }
                int logIndex = index + curlyBracketIndex + 1;
                functionLogNode.setLogLineIndex(logIndex);
                return functionLogNode;
            }
        }
        return null;
    }

    private static void addFunctionNodeLog(File classFile, List<FunctionLogNode> functionLogNodeList) {
        String className = classFile.getName();
//        System.out.println("fileAbsolutePath: " + classFile.getAbsolutePath());
        List<String> lines;
        try {
            lines = Files.readAllLines(classFile.toPath());
            for(int i = 0; i < functionLogNodeList.size(); i ++) {
                FunctionLogNode node = functionLogNodeList.get(i);
//                System.out.println("code: " + node.getCode());
//                System.out.println("functionName: " + node.getFunctionName());
                StringBuilder logStringBuilder = new StringBuilder();
                for(int x = 0; x < node.getSpaceCount() + 4; x ++) {
                    logStringBuilder.append(" ");
                }
                logStringBuilder.append(LOG_FUNCTION_START).append(className).append("\", \"")
                        .append(node.getFunctionName()).append("()").append(LOG_FUNCTION_END);
                String logCode = logStringBuilder.toString();
                lines.add(node.getLogLineIndex() + i, logCode);
            }
            Files.write(classFile.toPath(), lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
