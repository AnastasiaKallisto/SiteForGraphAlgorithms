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
    private ArrayList<Task> nextTasks;
    // null если не дошли до листа дерева задач,
    // иначе - это мин.остовное дерево (одно из)
    private IntervalGraph minSpanningTree;

    /**
     * @param probability - вероятность подзадачи
     * @param algorithm - тип алгоритма (Прим, Краскал)
     * @param graph - граф, для которого нужно вычислить варианты MST
     * @param availableEdges - доступные для добавления рёбра
     * Вызов этого конструктора полностью задает всё дерево задач.
     * **/
    public Task(double probability,
                IntervalGraphAlghoritm algorithm,
                IntervalGraph graph,
                List<IntervalEdge> availableEdges) {
        this.nextTasks = new ArrayList<>();
        // Получим множество следующих потенциальных ребер, множество Q
        // Также внутри этой функции удаляются из списка все рёбра,
        // которые могли бы дать цикл
        List<IntervalEdge> Q = algorithm.getQ(graph, availableEdges);

        if (Q.size() != 0) {
            /*
            Найдем минимальную правую границу среди правых границ рёбер из Q
            Найдем все границы для элементов разбиений весов рёбер из Q
            Найдем все элементы разбиений весов для рёбер из Q
            И рассмотрим все потенциальные рёбра в качестве минимальных
            Чтобы сформировать подзадачи
             */
            int minRightBorder = getMinRightBorder(Q);
            List<Integer> bordersForPartitionOfQ = Partition.getBordersForPartitionOfQ(Q);
            List<Interval> partitionForQ = Partition.getPartitionForQ(bordersForPartitionOfQ);
            List<IntervalEdge> helpQ = new ArrayList<>(Q);
            // потому что иначе Q сортируется и это портит проход итератором по Q

            for (IntervalEdge edge: Q) {
                /* Для расчёта вероятности
                Сформируем вспомогательный граф с этим ребром,
                чтобы отправить его в следующие задачи
                Сформируем разбиение этого ребра
                и посчитаем вероятность выбрать именно это ребро из Q
                Это будет вероятность, которую мы отправим в подзадачу
                 */
                List<Interval> partitionOfEdgeWeight = Partition.getPartitionFor_e_q(
                        edge, bordersForPartitionOfQ, partitionForQ);
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
                List<IntervalEdge> Q1 = IntervalGraphAlghoritm.cutEdges(cutEdge, helpQ);
                List<IntervalEdge> helpListOfAvailableEdges = new ArrayList<>(availableEdges);
                helpListOfAvailableEdges.removeAll(helpQ);
                helpListOfAvailableEdges.addAll(Q1);
                helpListOfAvailableEdges.remove(edge);
                // создаем новую задачу со своим новым начальным графом
                // и новым множеством доступных ребер
                nextTasks.add(new Task(
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
    public ArrayList<IntervalGraph> getDecisions() {
        ArrayList<IntervalGraph> graphs = new ArrayList<>();
        if (minSpanningTree != null) {
            graphs.add(minSpanningTree);
        } else {
            for (Task task : nextTasks) {
                graphs.addAll(task.getDecisions());
            }
        }
        graphs.sort(IntervalGraph::reverseCompareTo);
        for (int i = 0; i < graphs.size(); i++){
            graphs.get(i).setId(i);
        }
        return graphs;
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
    public Set<IntervalGraph> getDecisionsWithoutRepeating(IntervalGraph originalGraph){
        ArrayList<IntervalGraph> graphs = getDecisions();
        HashMap<IntervalGraph, Double> graphHashMap = new HashMap<>();
        for(IntervalGraph graph: graphs){
            if (graphHashMap.containsKey(graph)){
                // складываем вероятности
                graphHashMap.put(graph, graphHashMap.get(graph)+graph.getProbability());
            } else {
                graphHashMap.put(graph, graph.getProbability());
            }
        }
        Set<IntervalGraph> keySet = new HashSet<>();
        for (IntervalGraph graph: graphHashMap.keySet()){
            // восстанавливаем оригинальные веса у рёбер
            List<IntervalEdge> originalEdges = getEdgesWithOriginalWeights(
                    originalGraph.getEdges(), graph.getEdges());
            IntervalGraph g = new IntervalGraph(
                    graph.getId(), originalEdges, graph.getVertices());
            g.setProbability(graphHashMap.get(graph));
            keySet.add(g);
        }
        return keySet;
    }

    public IntervalEdge getEdgeWithOriginalWeight(
            IntervalEdge edge,
            List<IntervalEdge> edgesWithOriginalWeights){
        for (IntervalEdge e: edgesWithOriginalWeights){
            if (e.equals(edge))
                return e;
        }
        return edge;
    }

    public List<IntervalEdge> getEdgesWithOriginalWeights(
            List<IntervalEdge> originalEdges,
            List<IntervalEdge> edges){
        if (originalEdges.size() == 0 || edges.size() == 0){
            return edges;
        }
        List<IntervalEdge> answer = new ArrayList<>(edges.size());
        for (IntervalEdge e: edges){
            answer.add(getEdgeWithOriginalWeight(e, originalEdges));
        }
        return answer;
    }

    public static int getMinRightBorder(List<IntervalEdge> edges){
        int minRightBorder = edges.get(0).getEndWeight();
        for (IntervalEdge edge : edges){
            int w = edge.getEndWeight();
            if (minRightBorder > w)
                minRightBorder = w;
        }
        return minRightBorder;
    }
}
