package graph.alghoritms.model.interval.algorithms.utils;

import graph.alghoritms.model.interval.Interval;
import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.IntervalGraphAlghoritm;
import graph.alghoritms.model.interval.algorithms.Partition;
import graph.alghoritms.model.interval.algorithms.Probability;

import java.util.*;

public class Task {
    // варианты компонентов, в которые мы можем прийти отсюда
    private ArrayList<TaskStructure> nextTasks;
    // null если не дошли до листа дерева задач,
    // иначе - это мин.остовное дерево (одно из)
    private IntervalGraph minSpanningTree;

    /**
     * @param probability    - вероятность подзадачи
     * @param algorithm      - тип алгоритма (Прим, Краскал)
     * @param graph          - граф, для которого нужно вычислить варианты MST
     * @param availableEdges - доступные для добавления рёбра
     *                       Вызов этого конструктора полностью задает всё дерево задач.
     **/
    public Task(double probability,
                IntervalGraphAlghoritm algorithm,
                IntervalGraph graph,
                List<IntervalEdge> availableEdges) {
        this.nextTasks = new ArrayList<>();
        // Получим множество следующих потенциальных ребер, множество Q
        // Также внутри этой функции удаляются из списка все рёбра,
        // которые могли бы дать цикл
//        оцениваем сложность getQ = O(N^3)
        List<IntervalEdge> Q = algorithm.getQ(graph, availableEdges);

        if (Q.size() != 0) {
            /*
            Найдем минимальную правую границу среди правых границ рёбер из Q
            Найдем все границы для элементов разбиений весов рёбер из Q
            Найдем все элементы разбиений весов для рёбер из Q
            И рассмотрим все потенциальные рёбра в качестве минимальных
            Чтобы сформировать подзадачи
             */
            int minRightBorder = getMinRightBorder(Q);//сложность O(N)
            List<Integer> bordersForPartitionOfQ = Partition.getBordersForPartitionOfQ(Q);// сложность O(N*logN)
            List<Interval> partitionForQ = Partition.getPartitionForQ(bordersForPartitionOfQ);// сложность O(N)
            List<IntervalEdge> helpQ = new ArrayList<>(Q);
            // потому что иначе Q сортируется и это портит проход итератором по Q

            //сложность O(N^3*2^N)
            for (IntervalEdge edge : Q) {
                /* Для расчёта вероятности
                Сформируем вспомогательный граф с этим ребром,
                чтобы отправить его в следующие задачи
                Сформируем разбиение этого ребра
                и посчитаем вероятность выбрать именно это ребро из Q
                Это будет вероятность, которую мы отправим в подзадачу
                 */
                //сложность O(N)
                List<Interval> partitionOfEdgeWeight = Partition.getPartitionFor_e_q(
                        edge, bordersForPartitionOfQ, partitionForQ);
                //сложность O(N^2*2^N)
                double nextTaskProbability = getProbability(
                        probability, edge, partitionOfEdgeWeight, helpQ, minRightBorder);
                /*
                 * Для формирования подзадачи
                 * мы рассматриваем добавление ребра edge,
                 * Считая, что его точный вес оказался меньше остальных
                 * Подрежем вес ребра справа по минимальной правой границе
                 * Сформируем вспомогательный граф для подзадачи
                 * В который добавили это ребро с подрезанным весом
                 *
                 * Сформируем вспомогательный список доступных рёбер
                 * Из которого мы уберем это ребро
                 * А также мы уберем из него Q и добавим Q1 на основе Q,
                 * только с подрезанными слева весами
                 * на тот случай, если были рёбра, у которых левая граница веса меньше
                 * чем у текущего ребра
                 *
                 * сравнение рёбер на равенство идет только по вершинам,
                 * веса ребер при проверке на равенство значения не имеют
                 * мы удалили рёбра из Q и добавили их же, но уже с другими весами
                 * */
                IntervalEdge cutEdge = new IntervalEdge(edge);
                cutEdge.setEnd(minRightBorder);
                IntervalGraph helpGraph = new IntervalGraph(graph);
                helpGraph.addEdge(cutEdge);
                //сложность O(N)
                List<IntervalEdge> Q1 = IntervalGraphAlghoritm.cutEdges(cutEdge, helpQ);
                List<IntervalEdge> helpListOfAvailableEdges = new ArrayList<>(availableEdges);
                helpListOfAvailableEdges.removeAll(helpQ);
                helpListOfAvailableEdges.addAll(Q1);
                helpListOfAvailableEdges.remove(edge);
                // создаем новую задачу со своим новым начальным графом
                // и новым множеством доступных ребер
                nextTasks.add(new TaskStructure(
                        nextTaskProbability,
                        algorithm,
                        helpGraph,
                        helpListOfAvailableEdges));
            }
        }
        minSpanningTree = null;
        if (nextTasks.size() == 0) {
            minSpanningTree = new IntervalGraph(graph);
            minSpanningTree.setProbability(probability);
        }
    }

    public Task(TaskStructure taskStructure){
        this(taskStructure.getProbability(),
                taskStructure.getAlgorithm(),
                taskStructure.getGraph(),
                taskStructure.getAvailableEdges());
    }

    public ArrayList<TaskStructure> getNextTasksStructures(){
        return nextTasks;
    }

    //сложность O(N^2*2^N)
    public double getProbability(
            double probability,
            IntervalEdge edge,
            List<Interval> partitionFor_e_q,
            List<IntervalEdge> Q,
            int minRightBorder) {
        return Probability.countProbabilityOf_e_q(
                probability,
                edge,
                partitionFor_e_q,
                Q,
                minRightBorder);
    }

    /*
     * Формируем пустое множество деревьев, из них формируется ответ на задачу
     * Если в этой задаче задано MST, значит, это лист, добавляем дерево из листа
     * Если не задано, значит, закапываемся вглубь и получим решения из подзадач
     * Полученные решения могут дублироваться
     * (наборы ребер могут повторяться, хотя порядок и вероятность могут быть разными)
     * */

    //сложность O(N)
    public static int getMinRightBorder(List<IntervalEdge> edges) {
        int minRightBorder = edges.get(0).getEndWeight();
        for (IntervalEdge edge : edges) {
            int w = edge.getEndWeight();
            if (minRightBorder > w)
                minRightBorder = w;
        }
        return minRightBorder;
    }

    public IntervalGraph getMinSpanningTree(){
        return minSpanningTree;
    }
}
