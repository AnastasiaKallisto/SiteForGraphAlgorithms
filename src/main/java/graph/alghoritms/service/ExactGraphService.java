package graph.alghoritms.service;

import graph.alghoritms.dao.ExactGraphDao;
import graph.alghoritms.daoimpl.ExactGraphDaoImpl;
import graph.alghoritms.dto.response.ExactGraphDtoResponse;
import graph.alghoritms.model.exact.ExactGraph;
import graph.alghoritms.model.exact.ExactGraphAlgorithms;

public class ExactGraphService {
    ExactGraphDao dao = new ExactGraphDaoImpl();

    public ExactGraphDtoResponse getExactMainGraph() {
        ExactGraph graph = dao.getExactGraph();
        return new ExactGraphDtoResponse(graph);
    }

    public ExactGraphDtoResponse generateGraph(int n) {
        dao.clearExact();
        ExactGraph g = new ExactGraph(n);
        dao.setExactGraph(g);
        return new ExactGraphDtoResponse(g);
    }

    public ExactGraphDtoResponse getExactPrim() {
            ExactGraph graph = dao.getExactPrimGraph();
            return new ExactGraphDtoResponse(graph);
    }

    public ExactGraphDtoResponse runPrim() {
            ExactGraph graph = dao.getExactGraph();
            if (graph == null)
                return new ExactGraphDtoResponse(null);
            ExactGraph primGraph = new ExactGraph(graph.getVertices(), ExactGraphAlgorithms.returnMSTPrim(graph));
            dao.setExactPrimGraph(primGraph);
            return new ExactGraphDtoResponse(primGraph);
    }

    public ExactGraphDtoResponse getExactKruskal() {
            ExactGraph graph = dao.getExactKruskalGraph();
            return new ExactGraphDtoResponse(graph);
    }

    public ExactGraphDtoResponse runKruskal() {
            ExactGraph graph = dao.getExactGraph();
            if (graph == null)
                return new ExactGraphDtoResponse(null);
            ExactGraph kruskalGraph = new ExactGraph(graph.getVertices(), ExactGraphAlgorithms.returnMSTKruskal(graph));
            dao.setExactKruskalGraph(kruskalGraph);
            return new ExactGraphDtoResponse(kruskalGraph);
    }

    public int getQuantity() {
        ExactGraph graph = dao.getExactGraph();
        if (graph == null)
            return 0;
        return graph.getVertices().size();
    }
}
