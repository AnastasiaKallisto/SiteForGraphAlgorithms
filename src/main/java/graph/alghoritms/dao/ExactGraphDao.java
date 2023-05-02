package graph.alghoritms.dao;

import graph.alghoritms.model.exact.ExactGraph;

public interface ExactGraphDao {
    ExactGraph getExactGraph();

    void setExactGraph(ExactGraph exactGraph);

    ExactGraph getExactPrimGraph();

    void setExactPrimGraph(ExactGraph exactPrimGraph);

    ExactGraph getExactKruskalGraph();

    void setExactKruskalGraph(ExactGraph exactKruskalGraph);

    void clearExact();
}
