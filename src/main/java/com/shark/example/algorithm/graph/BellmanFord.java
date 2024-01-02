package com.shark.example.algorithm.graph;

import com.google.gson.Gson;
import com.shark.example.algorithm.graph.pojo.GraphResultDto;
import com.shark.example.algorithm.graph.pojo.NodeDto;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BellmanFord {

    public GraphResultDto start( Map<Integer, List<NodeDto>> graph, int startSource) throws Exception {
        int verticesCount = graph.keySet().size();
        Gson gson = new Gson();
        int[] distanceResult = new int[verticesCount];
        Arrays.fill(distanceResult, Integer.MAX_VALUE);
        distanceResult[startSource] = 0;
        int[] predecessorResult = new int[verticesCount];
        Arrays.fill(predecessorResult, -1);

        // 進行V-1次迭代
        for (int i = 1; i < verticesCount; i++) {
            for(Integer vertices: graph.keySet()) {
                if (distanceResult[vertices] == Integer.MAX_VALUE) {
                    continue;
                }
                for(NodeDto neighbor: graph.get(vertices)) {
                    int currentDestinationDistance = distanceResult[neighbor.getVertex()];
                    int newDestinationDistance = distanceResult[vertices] + neighbor.getDistance();
                    if (newDestinationDistance < currentDestinationDistance) {
                        System.out.println("vertices = " + vertices + ", neighbor = " + gson.toJson(neighbor) +
                                ", currentDestinationWeight = " + currentDestinationDistance + ", newDestinationWeight = " + newDestinationDistance);
                        distanceResult[neighbor.getVertex()] = newDestinationDistance;
                        predecessorResult[neighbor.getVertex()] = vertices;
                    }
                }
            }
        }

        for(Integer vertices: graph.keySet()) {
            for(NodeDto neighbor: graph.get(vertices)) {
                if (distanceResult[neighbor.getVertex()] != Integer.MAX_VALUE &&
                        (distanceResult[vertices] + neighbor.getDistance() < distanceResult[neighbor.getVertex()])) {
                    throw new Exception("圖中存在負權環");
                }
            }
        }

        return new GraphResultDto(distanceResult, predecessorResult);
    }

    public static void main(String[] argv) throws Exception {
        //init graph
        Map<Integer, List<NodeDto>> graph = new HashMap<>();
        graph.put(0, List.of(new NodeDto(1, -1), new NodeDto(2, 4)));
        graph.put(1, List.of(new NodeDto(2, 3), new NodeDto(3, 2), new NodeDto(4, 2)));
        graph.put(2, List.of());
        graph.put(3, List.of(new NodeDto(2, 5), new NodeDto(1, 1)));
        graph.put(4, List.of(new NodeDto(3, -3)));

        GraphResultDto resultDto = new BellmanFord().start(graph, 0);
        for (int i = 0; i < resultDto.getDistance().length; i++) {
            System.out.println("最短路徑到節點 " + i + ": " + resultDto.getDistance()[i] + ", 前驅節點: " + resultDto.getPredecessor()[i]);
        }
    }
}
