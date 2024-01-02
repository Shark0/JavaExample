package com.shark.example.algorithm.graph.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodeDto implements Comparable<NodeDto>{
    private int vertex;
    private int distance;

    @Override
    public int compareTo(NodeDto other) {
        return Integer.compare(this.distance, other.distance);
    }
}
