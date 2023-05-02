package graph.alghoritms.dto.response;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class IntervalGraphDtoResponse {
    private int id;
    private ArrayList<IntervalEdgeInfo> edges;
    private Set<Integer> vertices;
    private double probability;
    private double minWeight;
    private double maxWeight;

    public IntervalGraphDtoResponse(IntervalGraph graph, int id) {
        this.id = id;
        minWeight = 0;
        maxWeight = 0;
        if (graph == null){
            edges = new ArrayList<>();
            vertices = new HashSet<>();
            probability = 1;
        } else {
            vertices = graph.getVertices();
            int n = graph.getEdges().size();
            edges = new ArrayList<>(n);
            List<IntervalEdge> graphEdges = graph.getEdges();
            for (int i = 0; i < n; i++) {
                IntervalEdgeInfo edge = new IntervalEdgeInfo(graphEdges.get(i));
                edges.add(edge);
                minWeight += edge.getStartWeight();
                maxWeight += edge.getEndWeight();
            }
            probability = graph.getProbability();
        }
    }

    public IntervalGraphDtoResponse(int id,
                                    Set<Integer> vertices,
                                    List<IntervalEdgeInfo> edges,
                                    int minWeight,
                                    int maxWeight,
                                    double probability) {
        this.id = id;
        this.vertices = vertices;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.edges = new ArrayList<>(edges);
        this.probability = probability;
    }
}
