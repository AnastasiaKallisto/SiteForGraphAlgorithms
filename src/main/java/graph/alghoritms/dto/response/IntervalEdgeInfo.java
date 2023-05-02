package graph.alghoritms.dto.response;

import graph.alghoritms.model.interval.IntervalEdge;
import lombok.Getter;

@Getter
public class IntervalEdgeInfo {
    private int a;
    private int b;
    private int startWeight;
    private int endWeight;

    public IntervalEdgeInfo(IntervalEdge edge) {
        a = edge.getA();
        b = edge.getB();
        startWeight = edge.getIntervalWeight().getStart();
        endWeight = edge.getIntervalWeight().getEnd();
    }
}
