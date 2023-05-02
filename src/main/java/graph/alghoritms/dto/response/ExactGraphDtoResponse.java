package graph.alghoritms.dto.response;

import graph.alghoritms.model.exact.ExactEdge;
import graph.alghoritms.model.exact.ExactGraph;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ExactGraphDtoResponse {
    private List<ExactEdgeInfo> edges;
    private Set<Integer> vertices;

    public ExactGraphDtoResponse(ExactGraph graph) {
        if (graph == null){
            edges = new ArrayList<>();
            vertices = new HashSet<>();
        } else {
            vertices = graph.getVertices();
            int n = graph.getEdges().size();
            edges = new ArrayList<>(n);
            List<ExactEdge> graphEdges = graph.getEdges();
            for (int i = 0; i < n; i++) {
                edges.add(new ExactEdgeInfo(graphEdges.get(i)));
            }
        }
    }
}
