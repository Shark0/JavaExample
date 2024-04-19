package com.shark.example.data.string.dirty_world;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DirtyWordTreeNodeDto {
    private String charValue;
    private String dirtyWord;
    private Map<String, DirtyWordTreeNodeDto> childMap;

    public DirtyWordTreeNodeDto(String charValue) {
        this.charValue = charValue;
        childMap = new HashMap<>();
    }
}
