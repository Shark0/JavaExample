package com.shark.example.algorithm.graph;

import com.google.gson.Gson;
import com.shark.example.algorithm.graph.pojo.GraphResultDto;
import com.shark.example.algorithm.graph.pojo.NodeDto;

import java.util.*;

public class Dijkstra {

    public GraphResultDto start(Map<Integer, List<NodeDto>> graph, int startNode) {
        PriorityQueue<NodeDto> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new NodeDto(startNode, 0));
        int[] distanceResult = new int[graph.keySet().size()];
        Arrays.fill(distanceResult, Integer.MAX_VALUE);
        distanceResult[startNode] = 0;

        int[] predecessorResult = new int[graph.keySet().size()];
        Arrays.fill(predecessorResult, -1);
        Set<Integer> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            NodeDto currentNode = priorityQueue.poll();
            int currentVertex = currentNode.getVertex();
            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            for (NodeDto neighborNode : graph.get(currentVertex)) {
                int newDistance = distanceResult[currentVertex] + neighborNode.getDistance();
                int currentDistance = distanceResult[neighborNode.getVertex()];
                if (newDistance < currentDistance) {
                    System.out.println("currentVertex = " + currentVertex + ", current neighbor = " + neighborNode.getVertex() +
                            ", current distance: " + currentDistance + ", new distance = " + newDistance);
                    distanceResult[neighborNode.getVertex()] = newDistance;
                    predecessorResult[neighborNode.getVertex()] = currentVertex;
                    priorityQueue.offer(new NodeDto(neighborNode.getVertex(), newDistance));
                }
            }
        }

        return new GraphResultDto(distanceResult, predecessorResult);
    }

    public static void main(String[] argv) {
        int numVertices = 6;

        //init graph
        Map<Integer, List<NodeDto>> graph = new HashMap<>();
        graph.put(0, List.of(new NodeDto(1, 5), new NodeDto(2, 1)));
        graph.put(1, List.of(new NodeDto(0, 5), new NodeDto(3, 3)));
        graph.put(2, List.of(new NodeDto(0, 1), new NodeDto(4, 2), new NodeDto(5, 4)));
        graph.put(3, List.of(new NodeDto(1, 3), new NodeDto(4, 1)));
        graph.put(4, List.of(new NodeDto(3, 1), new NodeDto(2, 2)));
        graph.put(5, List.of(new NodeDto(2, 4)));

        System.out.println(new Gson().toJson(graph));

        GraphResultDto result = new Dijkstra().start(graph, 0);
        for (int i = 0; i < numVertices; i++) {
            System.out.println("最短路徑到節點 " + i + ": " + result.getDistance()[i] + ", 前驅節點: " + result.getPredecessor()[i]);
        }
    }
}
