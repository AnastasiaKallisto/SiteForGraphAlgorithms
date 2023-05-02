package graph.alghoritms.dto.response;

import graph.alghoritms.model.exact.ExactEdge;
import lombok.Getter;

@Getter
public class ExactEdgeInfo {
    private int a;
    private int b;
    private int weight;

    public ExactEdgeInfo(ExactEdge edge) {
        a = edge.getA();
        b = edge.getB();
        this.weight = edge.getWeight();
    }
}
