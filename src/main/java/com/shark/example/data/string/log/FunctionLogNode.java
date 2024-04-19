package com.shark.example.data.string.log;

import lombok.Data;

@Data
public class FunctionLogNode {
    private String code;
    private int logLineIndex;
    private int spaceCount;
    private String functionName;
}
