package graph.alghoritms.model.interval.algorithms;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;

import java.util.ArrayList;
import java.util.List;

public abstract class IntervalGraphAlghoritm {
    /**
     * @param minEdge - выбранное ребро,
     *                при опр. раскладе оно может иметь мин. вес.
     *                Его вес уже подрезан справа по мин. правой границе
     * @param Q       - множество рёбер Q
     * @return - список ребер с подрезанными слева весами
     * теперь их минимальный вес >= мин. весу ребра minEdge
     */
    public static List<IntervalEdge> cutEdges(
            IntervalEdge minEdge, List<IntervalEdge> Q) {
        List<IntervalEdge> answer = new ArrayList<>();
        for (IntervalEdge edge : Q) {
            answer.add(new IntervalEdge(edge));
        }
        int leftBorder = minEdge.getStartWeight();
        for (IntervalEdge edge : answer) {
            if (edge.getStartWeight() < leftBorder) {
                edge.setStart(leftBorder);
            }
        }
        return answer;
    }

    /**
     * @param graph
     * @param availableEdges - ВСЕ доступные рёбра
     * @return Список ребер, которые могут быть добавлены в граф
     * Эти ребра не дают цикл при их добавлении в граф
     * и имеют веса левее минимальной правой границы
     * В процессе нахождения этих ребер изменяется список availableEdges
     * Из него также удаляется часть ребер, которые дают цикл на данном этапе
     * Раз они уже дают цикл, значит, давали бы и во всех следующих задачах
     * А значит, их можно сразу убрать.
     */
    public abstract List<IntervalEdge> getQ(
            IntervalGraph graph, List<IntervalEdge> availableEdges);
}
