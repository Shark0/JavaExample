package com.shark.example.algorithm.sentence.grammar;

import com.shark.example.algorithm.sentence.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Grammar {
    private List<List<String>> grammarList;
    private TokenType tokenType;
}
