package graph.alghoritms.service;

import graph.alghoritms.dao.IntervalGraphDao;
import graph.alghoritms.daoimpl.IntervalGraphDaoImpl;
import graph.alghoritms.dto.response.GetIntervalDecisionsDtoResponse;
import graph.alghoritms.dto.response.IntervalGraphDtoResponse;
import graph.alghoritms.error.ErrorCode;
import graph.alghoritms.error.ServerException;
import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.IntervalKruskalAlghoritm;
import graph.alghoritms.model.interval.algorithms.IntervalPrimAlghoritm;
import graph.alghoritms.model.interval.algorithms.utils.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntervalGraphService {
    IntervalGraphDao dao = new IntervalGraphDaoImpl();

    public IntervalGraphDtoResponse getIntervalMainGraph() {
        IntervalGraph graph = dao.getIntervalGraph();
        return new IntervalGraphDtoResponse(graph, 0);
    }

    public IntervalGraphDtoResponse getIntervalMainGraphAndShow() {
        IntervalGraph graph = dao.getIntervalGraph();
        dao.setIntervalGraphToShow(graph);
        return new IntervalGraphDtoResponse(graph, 0);
    }

    public IntervalGraphDtoResponse getPrimGraphById(int i) { // приходит 1 - возвращаем 0-й элемент
        IntervalGraph graph = dao.getIntervalGraphsPrim().get(i - 1);
        dao.setIntervalGraphToShow(graph);
        return new IntervalGraphDtoResponse(graph, i - 1);
    }

    public IntervalGraphDtoResponse getKruskalGraphById(int i) {
        IntervalGraph graph = dao.getIntervalGraphsKruskal().get(i - 1);
        dao.setIntervalGraphToShow(graph);
        return new IntervalGraphDtoResponse(graph, i - 1);
    }

    public IntervalGraphDtoResponse generateGraph(int n) throws ServerException {
        if (n < 3)
            throw new ServerException(ErrorCode.INVALID_N);
        dao.clearInterval();
        IntervalGraph g = new IntervalGraph(n);
        dao.setIntervalGraph(g);
        dao.setIntervalGraphToShow(g);
        return new IntervalGraphDtoResponse(g, 0);
    }

    public GetIntervalDecisionsDtoResponse getIntervalPrimGraphs() {
        List<IntervalGraph> graphs = dao.getIntervalGraphsPrim();
        return new GetIntervalDecisionsDtoResponse(graphs);
    }

    public GetIntervalDecisionsDtoResponse runPrim() {
        IntervalGraph graph = dao.getIntervalGraph();
        if (graph == null)
            return new GetIntervalDecisionsDtoResponse(null);
//        System.out.println("======================================================");
//        long startTime = System.currentTimeMillis();
        Task task = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph.getEdges());
//        long endTime = System.currentTimeMillis();
//        System.out.println("Время выполнения алгоритма: " + (endTime - startTime));
        List<IntervalGraph> allDecisions = new ArrayList<>(task.getDecisionsWithoutRepeating(graph));
        dao.setIntervalGraphsPrim(allDecisions);
        return new GetIntervalDecisionsDtoResponse(allDecisions);
    }

    public GetIntervalDecisionsDtoResponse getIntervalKruskalGraphs() {
        List<IntervalGraph> graphs = dao.getIntervalGraphsKruskal();
        return new GetIntervalDecisionsDtoResponse(graphs);
    }

    public GetIntervalDecisionsDtoResponse runKruskal() {
        IntervalGraph graph = dao.getIntervalGraph();
        if (graph == null)
            return new GetIntervalDecisionsDtoResponse(null);
        Task task = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph.getEdges());
        List<IntervalGraph> allDecisions = new ArrayList<>(task.getDecisionsWithoutRepeating(graph));
        dao.setIntervalGraphsKruskal(allDecisions);
        return new GetIntervalDecisionsDtoResponse(allDecisions);
    }

    public Integer getQuantity() {
        IntervalGraph graph = dao.getIntervalGraph();
        if (graph == null)
            return 0;
        return dao.getIntervalGraph().getVertices().size();
    }

    public IntervalGraphDtoResponse getIntervalGraphToShow() {
        IntervalGraph graph = dao.getIntervalGraphToShow();
        if (graph != null) {
            return new IntervalGraphDtoResponse(graph, graph.getId());
        }
        return new IntervalGraphDtoResponse(graph, 0);
    }
}
