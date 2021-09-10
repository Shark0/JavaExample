package com.shark.example.log;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AddLuaFunctionLogExample {

    private static final String PROJECT_PATH = "D:\\HW\\Card\\2.4.0\\server\\misvr\\svr";
    private static final String LUA_FILE_EXTENSION = ".lua";
    private static final String LOG_FUNCTION_START = "Logger.log(\"";
    private static final String LOG_FUNCTION_END = "\")";

    public static void main(String argv[]) {
        File file = new File(PROJECT_PATH);
        addLog(file);
    }

    private static void addLog(File file) {
        if(file.isDirectory()) {
            File childFileArray[] = file.listFiles();
            for(File childFile: childFileArray) {
                addLog(childFile);
            }
            return;
        }
        if(!isLuaFile(file)) {
            return;
        }
        List<FunctionLogNode> functionLogNodeList = parserLuaFile(file);
        if(functionLogNodeList.isEmpty()) {
            return;
        }
        addFunctionNodeLog(file, functionLogNodeList);
    }

    private static boolean isLuaFile(File file) {
        String fileName = file.getName();
        return fileName.endsWith(LUA_FILE_EXTENSION);
    }

    private static List<FunctionLogNode> parserLuaFile(File classFile) {
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
//                    System.out.println("fileAbsolutePath: " + classFile.getAbsolutePath());
//                    System.out.println("i: " + i + ", code: " + codeLineList.get(i).trim());
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
        try {
            if(code.startsWith("function")) {
                return null;
            }
            String functionName = code.replace("function ", "");
            FunctionLogNode functionLogNode = new FunctionLogNode();
            functionLogNode.setCode(code);
            functionLogNode.setFunctionName(functionName);
            functionLogNode.setSpaceCount(0);
            int logIndex = index + 1;
            functionLogNode.setLogLineIndex(logIndex);
            return functionLogNode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void addFunctionNodeLog(File classFile, List<FunctionLogNode> functionLogNodeList) {
        String className = classFile.getName();
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
                logStringBuilder.append(LOG_FUNCTION_START).append(className).append(" ")
                        .append(node.getFunctionName()).append(LOG_FUNCTION_END);
                String logCode = logStringBuilder.toString();
                lines.add(node.getLogLineIndex() + i, logCode);
            }
            Files.write(classFile.toPath(), lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
