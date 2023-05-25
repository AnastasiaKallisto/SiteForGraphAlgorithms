package graph.alghoritms.service;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.utils.Task;
import graph.alghoritms.model.interval.algorithms.utils.TaskStructure;

import java.util.*;

public class IntervalServiceUtils {
    public static List<IntervalGraph> getAllDecisionsWithRepeating(Task task){
        List<TaskStructure> nextTasksStructures = task.getNextTasksStructures();
        List<Task> nextTasks = new ArrayList<>();
        ArrayList<IntervalGraph> allDecisionsWithRepeating = new ArrayList<>();
        do {
            nextTasks.clear();
            for (TaskStructure taskStructure : nextTasksStructures) {
                nextTasks.add(new Task(taskStructure));
            }
            nextTasksStructures.clear();
            for (Task t : nextTasks) {
                if (t.getMinSpanningTree() != null) {
                    allDecisionsWithRepeating.add(t.getMinSpanningTree());
                } else {
                    nextTasksStructures.addAll(t.getNextTasksStructures());
                }
            }
        } while (nextTasks.size() != 0);
        // последняя итерация
        for (Task t : nextTasks) {
            allDecisionsWithRepeating.add(t.getMinSpanningTree());
        }
        return allDecisionsWithRepeating;
    }

    /*
     * Получаем все решения методом getDecisions
     * С помощью HashMap (отображение граф-вероятность)
     * Складываем одинаковые (сравниваем наборы ребер) решения в одно
     * И складываем у них вероятности
     * А затем формируем множество HashSet графов
     * в которых мы восстановим оригинальные веса рёбер
     * и установим им правильную посчитанную вероятность
     * */
    public static List<IntervalGraph> getDecisionsWithoutRepeating(IntervalGraph originalGraph, List<IntervalGraph> graphs) {
        HashMap<IntervalGraph, Double> graphHashMap = new HashMap<>();
        for (IntervalGraph graph : graphs) {
            if (graphHashMap.containsKey(graph)) {
                // складываем вероятности
                graphHashMap.put(graph, graphHashMap.get(graph) + graph.getProbability());
            } else {
                graphHashMap.put(graph, graph.getProbability());
            }
        }
        Set<IntervalGraph> keySet = new HashSet<>();
        for (IntervalGraph graph : graphHashMap.keySet()) {
            // восстанавливаем оригинальные веса у рёбер
            List<IntervalEdge> originalEdges = getEdgesWithOriginalWeights(
                    originalGraph.getEdges(), graph.getEdges());
            IntervalGraph g = new IntervalGraph(
                    graph.getId(), originalEdges, graph.getVertices());
            g.setProbability(graphHashMap.get(graph));
            keySet.add(g);
        }
        return new ArrayList<>(keySet);
    }

    public static IntervalEdge getEdgeWithOriginalWeight(
            IntervalEdge edge,
            List<IntervalEdge> edgesWithOriginalWeights) {
        for (IntervalEdge e : edgesWithOriginalWeights) {
            if (e.equals(edge))
                return e;
        }
        return edge;
    }

    public static List<IntervalEdge> getEdgesWithOriginalWeights(
            List<IntervalEdge> originalEdges,
            List<IntervalEdge> edges) {
        if (originalEdges.size() == 0 || edges.size() == 0) {
            return edges;
        }
        List<IntervalEdge> answer = new ArrayList<>(edges.size());
        for (IntervalEdge e : edges) {
            answer.add(getEdgeWithOriginalWeight(e, originalEdges));
        }
        return answer;
    }
}
