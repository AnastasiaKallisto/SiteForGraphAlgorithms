package graph.alghoritms.dao;

import graph.alghoritms.model.interval.IntervalGraph;

import java.util.List;

public interface IntervalGraphDao {
    IntervalGraph getIntervalGraph();

    IntervalGraph getIntervalGraphToShow();

    void setIntervalGraph(IntervalGraph intervalGraph);

    List<IntervalGraph> getIntervalGraphsPrim();

    void setIntervalGraphsPrim(List<IntervalGraph> intervalGraphsPrim);

    List<IntervalGraph> getIntervalGraphsKruskal();

    void setIntervalGraphsKruskal(List<IntervalGraph> intervalGraphsKruskal);

    void clearInterval();

    void setIntervalGraphToShow(IntervalGraph graph);
}
