package com.shark.example.sentence.grammar;

import com.shark.example.sentence.TokenEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GrammarTreeNode {
    private TokenEntity tokenEntity;
    private List<GrammarTreeNode> childList;
}
