package graph.alghoritms.model.interval.algorithms;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntervalKruskalAlghoritm extends IntervalGraphAlghoritm {

    //итоговая сложность N^3+N^3+N*logN=O(N^3)
    @Override
    public List<IntervalEdge> getQ(IntervalGraph graph, List<IntervalEdge> availableEdges) {
        /* Находим ребро с минимальной правой границей
         * Идем по порядку. Если встречаем ребро, которое дает цикл,
         * Вносим его в список тех, что нужно убрать
         * Если ребро не дает цикл - оно то, что нам нужно.
         * Когда нашли это ребро, можно удалить все ребра, что дают цикл, которые УЖЕ нашли
         * */
        List<IntervalEdge> needToBeRemoved = new ArrayList<>();
        List<IntervalEdge> answer = new ArrayList<>();
        availableEdges.sort(IntervalEdge::compareToRight);//быстрая сортировка: ожидаемая скорость = N*logN или N^2
        int minRightBorder = 0;
        // сложность цикла = N^3
        for (IntervalEdge edge : availableEdges) {
            // оцениваем сложность searchChain = N^2
            if (!searchChain(edge.getA(), edge.getB(), graph, new HashSet<>())) {
                minRightBorder = edge.getEndWeight();
                break;
            } else {
                needToBeRemoved.add(edge);
            }
        }
        availableEdges.removeAll(needToBeRemoved);
        // Найдем ВСЕ ребра с весами меньше, чем minRightBorder,
        // попутно убирая те, что дают цикл.
        // Получим множество Q
        //оценим сложность цикла N^3
        for (IntervalEdge edge : availableEdges) {
            //если левый вес меньше мин. правой границы
            if (edge.getStartWeight() < minRightBorder) {
                // добавим ребро, если нет цикла, иначе оно должно быть удалено
                if (!searchChain(edge.getA(), edge.getB(), graph, new HashSet<>())) {
                    answer.add(edge);
                } else {
                    needToBeRemoved.add(edge);
                }
            }
        }
        availableEdges.removeAll(needToBeRemoved);
        return answer;
    }

    //ищем цепочку из a в b.
    // Для этого рекурсивно будем смотреть непроверенные вершины, не придем ли мы из них в вершину b
    // по уже существующим ребрам дерева
    // возьмем худший случай за N^2
    private static boolean searchChain(Integer a, Integer b, IntervalGraph graph, Set<Integer> checkedVertices) {
        Set<Integer> needToBeCheckedSet = new HashSet<>();
        for (IntervalEdge edge : graph.getEdges()) {
            if (edge.getA().equals(a)) {
                if (edge.getB().equals(b)) {
                    return true;
                }
                if (!checkedVertices.contains(edge.getB())) {
                    needToBeCheckedSet.add(edge.getB());
                }
            } else {
                if (edge.getB().equals(a)) {
                    if (edge.getA().equals(b)) {
                        return true;
                    }
                    if (!checkedVertices.contains(edge.getA())) { // мы вторую уже проверяли?
                        needToBeCheckedSet.add(edge.getA()); // нет - надо проверить.
                    }
                }
            }
        }// сложность N
        checkedVertices.add(a);// из нее точно не попадаем в b
        for (Integer vertex : needToBeCheckedSet) {
            if (searchChain(vertex, b, graph, checkedVertices)) {
                return true;
            }
        }
        return false;
    }
}
