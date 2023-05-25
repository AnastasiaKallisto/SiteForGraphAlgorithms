package graph.alghoritms.model.interval.algorithms.utils;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.IntervalGraphAlghoritm;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TaskStructure {
    private double probability;
    private IntervalGraphAlghoritm algorithm;
    private IntervalGraph graph;
    private List<IntervalEdge> availableEdges;
}
