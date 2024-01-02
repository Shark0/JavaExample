package com.shark.example.algorithm.graph.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GraphResultDto {
    private int[] distance;
    private int[] predecessor;
}
