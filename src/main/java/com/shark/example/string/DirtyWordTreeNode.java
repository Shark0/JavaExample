package com.shark.example.string;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DirtyWordTreeNode {
    private String charValue;
    private String dirtyWord;
    private Map<String, DirtyWordTreeNode> childMap;

    public DirtyWordTreeNode(String charValue) {
        this.charValue = charValue;
        childMap = new HashMap<>();
    }
}
