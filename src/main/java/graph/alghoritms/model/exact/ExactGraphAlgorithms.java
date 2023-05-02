package graph.alghoritms.model.exact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExactGraphAlgorithms {

    public static ArrayList<ExactEdge> returnMSTPrim(ExactGraph graph) {
        ArrayList<ExactEdge> minSpanningTreePrim = new ArrayList<>();
        List<ExactEdge> availableEdges = new ArrayList<>(graph.getEdges());
        List<Integer> availableVertices = new ArrayList<>(graph.getVertices());
        Set<Integer> usedVertices = new HashSet<>();
        availableEdges.sort(ExactEdge::compareTo);
        Integer a = availableVertices.get(0);
        usedVertices.add(a);
        availableVertices.remove(a);
        ExactEdge curMinEdge = searchNotUsedIncidentEdgesForPrim(
                a, availableEdges, availableVertices).get(0);
        while (availableVertices.size() > 0) {
            for (Integer vertex : usedVertices) {
                List<ExactEdge> incidentEdgesForVertex =
                        searchNotUsedIncidentEdgesForPrim(vertex, availableEdges, availableVertices);
                if (incidentEdgesForVertex.size() > 0 && curMinEdge == null) {
                    curMinEdge = incidentEdgesForVertex.get(0);
                } else {
                    for (ExactEdge edge : incidentEdgesForVertex) {
                        if (curMinEdge.getWeight() > edge.getWeight()) {
                            curMinEdge = edge;
                        }
                    }
                }
            }
            availableVertices.removeAll(curMinEdge.getVertices());
            availableEdges.remove(curMinEdge);
            usedVertices.addAll(curMinEdge.getVertices());
            minSpanningTreePrim.add(curMinEdge);
            curMinEdge = null;
        }
        return minSpanningTreePrim;
    }


    public static ArrayList<ExactEdge> returnMSTKruskal(ExactGraph graph) {
        ArrayList<ExactEdge> minSpanningTreeKruskal = new ArrayList<>();
        List<ExactEdge> availableEdges = new ArrayList<>(graph.getEdges());
        availableEdges.sort(ExactEdge::compareTo);
        for (ExactEdge edge : availableEdges) {
            if (!searchChain(
                    edge.getA(),
                    edge.getB(),
                    minSpanningTreeKruskal,
                    new HashSet<>())) {
                minSpanningTreeKruskal.add(edge);
            }
        }
        return minSpanningTreeKruskal;
    }

    private static boolean searchChain(
            Integer a,
            Integer b,
            ArrayList<ExactEdge> graphEdges,
            Set<Integer> checkedVertices) {
        Set<Integer> needToBeCheckedSet = new HashSet<>();
        for (ExactEdge edge : graphEdges) {
            if (edge.getA().equals(a)) {
                if (edge.getB().equals(b)) {
                    return true;
                }
                if (!checkedVertices.contains(edge.getB())) {
                    needToBeCheckedSet.add(edge.getB());
                }
            }
            if (edge.getB().equals(a)) {
                if (edge.getA().equals(b)) {
                    return true;
                }
                if (!checkedVertices.contains(edge.getA())) {
                    needToBeCheckedSet.add(edge.getA());
                }
            }
        }
        checkedVertices.add(a);
        for (Integer vertex : needToBeCheckedSet) {
            if (searchChain(vertex, b, graphEdges, checkedVertices)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param a - вершина, для которой ищем инцидентные ребра
     * @param availableEdges - доступные рёбра, не содержащиеся в графе
     * @param availableVertices - доступные вершины, не содержащиеся в графе
     **/
    public static List<ExactEdge> searchNotUsedIncidentEdgesForPrim(
            Integer a,
            List<ExactEdge> availableEdges,
            List<Integer> availableVertices) {
        List<ExactEdge> notUsedIncidentEdges = new ArrayList<>();
        for (ExactEdge edge : availableEdges) {
            if ((edge.getVertices().contains(a)) &&
                    (availableVertices.contains(edge.getA())
                            || availableVertices.contains(edge.getB()))) {
                notUsedIncidentEdges.add(edge);
            }
        }
        return notUsedIncidentEdges;
    }

}
