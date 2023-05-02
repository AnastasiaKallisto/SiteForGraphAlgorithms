package graph.alghoritms.model.interval.algorithms;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.utils.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IntervalPrimAlghoritm extends IntervalGraphAlghoritm {
    /**
     * @param graph          - уже составленная на данный момент часть дерева
     * @param availableEdges - все доступные для добавления рёбра
     * @return - список рёбер, которые имеют веса левее мин. правой границы (множество Q)
     */

    // Создадим пустой список рёбер needToBeRemoved,
    // которые нужно будет убрать из списка доступных (availableEdges)
    // Это будут рёбра, которые дают цикл, и, соответственно,
    // будут давать его во всех следующих компонентах решения,
    // поэтому можно убрать их сразу, чтобы они не мешали.

    // Находим все инцидентные графу (graph) рёбра incidentEdges,
    // среди них находим те, что дадут цикл
    // (обе вершины уже содержатся во множестве вершин графа)
    // и добавляем их в список needToBeRemoved

    // Удаляем рёбра из needToBeRemoved
    // из списка availableEdges и из списка incidentEdges
    // Если список incidentEdges оказался пуст,
    // значит, Q пустое, и возвращаем пустой список.
    // Если список не пуст, то
    // За первый проход по всем рёбрам incidentEdges
    // мы найдем минимальную правую границу minRightBorder
    // За второй проход по incidentEdges мы найдем все рёбра,
    // у которых минимальной значение веса меньше, чем minRightBorder,
    // иными слова - рёбра, имеющие веса левее минимальной правой границы.
    // То есть, множество Q.
    @Override
    public List<IntervalEdge> getQ(IntervalGraph graph, List<IntervalEdge> availableEdges) {
        List<IntervalEdge> needToBeRemoved = new ArrayList<>();
        if (graph.getVertices().size() == 0){
            graph.addVertex(1);
        }
        List<IntervalEdge> incidentEdges = searchIncidentEdgesForPrim(graph, availableEdges);
        for (IntervalEdge edge : incidentEdges) {
            if (graph.getVertices().containsAll(edge.getVertices())) {
                needToBeRemoved.add(edge);
            }
        }
        incidentEdges.removeAll(needToBeRemoved);
        availableEdges.removeAll(needToBeRemoved);
        if (incidentEdges.size() == 0) {
            return new ArrayList<>();
        }
        List<IntervalEdge> Q = new ArrayList<>();
        int minRightBorder = Task.getMinRightBorder(incidentEdges);
        for (IntervalEdge edge : incidentEdges) {
            if (edge.getStartWeight() < minRightBorder) {
                Q.add(edge);
            }
        }
        return Q;
    }

    /**
     * Функция, которая находит все рёбра,
     * инцидентные вершинам уще вычисленной части дерева
     *
     * @param graph          - уже вычисленная часть дерева
     * @param availableEdges - все доступные для добавления ребра
     * @return - список ребер, инцидентных с вершинами, содержащимися в graph
     */
    // Для получения множества всех инцидентных графу (graph) рёбер,
    // нужно получить номера всех его вершин - vertexNumbers.
    // Проверим каждое ребро из множества доступных(availableEdges):
    // если хоть одна его вершина есть в vertexNumbers,
    // значит, ребро можно добавить в ответ.
    public static List<IntervalEdge> searchIncidentEdgesForPrim(
            IntervalGraph graph,
            List<IntervalEdge> availableEdges) {
        List<IntervalEdge> incidentEdges = new ArrayList<>();
        Set<Integer> vertexNumbers = graph.getVertices();
        for (IntervalEdge edge : availableEdges) {
            if (vertexNumbers.contains(edge.getA())
                    || vertexNumbers.contains(edge.getB())) {
                incidentEdges.add(edge);
            }
        }
        return incidentEdges;
    }
}
