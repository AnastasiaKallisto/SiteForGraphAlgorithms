import graph.alghoritms.model.interval.IntervalGraph;
import graph.alghoritms.model.interval.algorithms.IntervalKruskalAlghoritm;
import graph.alghoritms.model.interval.algorithms.IntervalPrimAlghoritm;
import graph.alghoritms.model.interval.algorithms.utils.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntervalGraphTests {
    private static IntervalGraph graph1;
    private static IntervalGraph graph2;
    private static IntervalGraph graph3;
    private static IntervalGraph graph4;

    @BeforeAll
    public static void setUp() {
        graph1 = new IntervalGraph();
        graph1.addVertex(1);
        graph1.addVertex(2);
        graph1.addVertex(3);
        graph1.addEdge(1, 2, 5, 15);
        graph1.addEdge(1, 3, 10, 20);
        graph1.addEdge(2, 3, 14, 25);

        graph2 = new IntervalGraph();
        graph2.addVertex(1);
        graph2.addVertex(2);
        graph2.addVertex(3);
        graph2.addVertex(4);
        graph2.addEdge(1, 2, 10, 20);
        graph2.addEdge(1, 4, 4, 20);
        graph2.addEdge(1, 3, 13, 30);
        graph2.addEdge(3, 4, 6, 25);
        graph2.addEdge(2, 3, 5, 15);

        graph3 = new IntervalGraph();
        graph3.addVertex(1);
        graph3.addVertex(2);
        graph3.addVertex(3);
        graph3.addVertex(4);
        graph3.addVertex(5);
        graph3.addVertex(6);
        graph3.addEdge(1, 2, 66, 153);
        graph3.addEdge(1, 3, 14, 50);
        graph3.addEdge(2, 4, 83, 106);
        graph3.addEdge(3, 4, 83, 154);
        graph3.addEdge(3, 5, 93, 104);
        graph3.addEdge(3, 6, 96, 124);
        graph3.addEdge(4, 5, 47, 99);
        graph3.addEdge(4, 6, 62, 70);
        graph3.addEdge(5, 6, 63, 125);

        graph4 = new IntervalGraph(8);
        System.out.println("Quantity of edges generated:  " + graph4.getEdges().size());
    }

    @Test
    public void testCountProbabilityKruskal1() {
//        long time = System.nanoTime();
        Task component = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph1.getEdges());
//        long timeAfterTask = System.nanoTime();
        ArrayList<IntervalGraph> allDecisions = component.getDecisions();
//        long timeAfterAll = System.nanoTime();
        double probabilitySum = 0;
        for (IntervalGraph graph : allDecisions) {
            probabilitySum += graph.getProbability();
        }
//        System.out.println(allDecisions);
//        System.out.println(time);
//        System.out.println(timeAfterTask);
//        System.out.println("Время работы с тасками в миллисекундах: " + (timeAfterTask - time));
//        System.out.println("Время работы с десижионами в милисекундах: " + (timeAfterAll - time));
//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");

//        System.out.println(component.getDecisionsWithoutRepeating(graph1));
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityPrim1() {
        Task component = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph1.getEdges());
        ArrayList<IntervalGraph> allDecisions = component.getDecisions();
        double probabilitySum = 0;
        for (IntervalGraph graph : allDecisions) {
            probabilitySum += graph.getProbability();
        }
//        System.out.println(allDecisions);

//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");

//        System.out.println(component.getDecisionsWithoutRepeating(graph1));
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityKruskal2() {
//        System.out.println("Оригинальный граф перед Краскалом:");
//        System.out.println(graph2);
        Task component = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph2.getEdges());
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph2));
        double probabilitySum = 0;
        for (IntervalGraph graph : allDecisions) {
            probabilitySum += graph.getProbability();
        }
//        System.out.println("Оригинальный граф после Краскала:");
//        System.out.println(graph2);
//        System.out.println("ВСЕ РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ, их кол-во " + allDecisions.size());
        allDecisions.sort(IntervalGraph::reverseCompareTo);
//        System.out.println(allDecisions);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityPrim2() {
//        System.out.println("Оригинальный граф перед Примом:");
//        System.out.println(graph2);
        Task component = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph2.getEdges());
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph2));
        double probabilitySum = 0;
        for (IntervalGraph graph : allDecisions) {
            probabilitySum += graph.getProbability();
        }

//        System.out.println("Оригинальный граф после Прима:");
//        System.out.println(graph2);
//
//        System.out.println("ВСЕ РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ, их кол-во " + allDecisions.size());
        allDecisions.sort(IntervalGraph::reverseCompareTo);

//        System.out.println(allDecisions);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityPrim4() {
        IntervalGraph graph4 = new IntervalGraph();
        graph4.addVertex(1);
        graph4.addVertex(2);
        graph4.addVertex(3);
        graph4.addVertex(4);
        graph4.addVertex(5);
        graph4.addVertex(6);
        graph4.addEdge(1, 2, 66, 153);
        graph4.addEdge(1, 3, 14, 50);
        graph4.addEdge(2, 4, 83, 106);
        graph4.addEdge(3, 4, 83, 154);
        graph4.addEdge(4, 5, 47, 99);
        graph4.addEdge(4, 6, 62, 70);

        long time = System.currentTimeMillis();
        Task component = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
//        System.out.println((double) (System.currentTimeMillis() - time)/1000);
        ArrayList<IntervalGraph> allDecisions = component.getDecisions();

        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }
//        System.out.println("С дублированием");
//        System.out.println(allDecisions);

//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");
        allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph4));

//        System.out.println(allDecisions);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityKruskal4() {
        IntervalGraph graph4 = new IntervalGraph();
        graph4.addVertex(1);
        graph4.addVertex(2);
        graph4.addVertex(3);
        graph4.addVertex(4);
        graph4.addVertex(5);
        graph4.addVertex(6);
        graph4.addEdge(1, 2, 66, 153);
        graph4.addEdge(1, 3, 14, 50);
        graph4.addEdge(2, 4, 83, 106);
        graph4.addEdge(3, 4, 83, 154);
        graph4.addEdge(4, 5, 47, 99);
        graph4.addEdge(4, 6, 62, 70);
//        long time = System.currentTimeMillis();
        Task component = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
//        System.out.println(System.currentTimeMillis() - time);
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph4));
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }

//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");
        allDecisions.sort(IntervalGraph::reverseCompareTo);
//        System.out.println(allDecisions);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityKruskal3() {
//        long time = System.currentTimeMillis();
        Task component = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph3.getEdges());
//        long timeAfterTask = System.currentTimeMillis();
        ArrayList<IntervalGraph> allDecisions = component.getDecisions();
//        long timeAfterAll = System.currentTimeMillis();
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }
        //System.out.println(allDecisions);
//        System.out.println("Время работы алгоритма тасков: " + (timeAfterTask - time));
//        System.out.println("Время работы алгоритма десижинов: " + (timeAfterAll - time));
//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");

//        System.out.println(component.getDecisionsWithoutRepeating(graph3));
//        System.out.println(probabilitySum);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityPrim3() {
//        System.out.println("Оригинальный граф перед Примом:");
//        System.out.println(graph3);
//        long time = System.currentTimeMillis();
        Task component = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph3.getEdges());
//        System.out.println((double) (System.currentTimeMillis() - time) / 1000);
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph3));
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }
//        System.out.println("Оригинальный граф после Прима:");
//        System.out.println(graph3);

//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");

//        System.out.println(allDecisions);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityWithRandomGraphKruskal() {
//        long time = System.currentTimeMillis();
        Task component = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
//        System.out.println(System.currentTimeMillis() - time);
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph4));
//        System.out.println("ВСЕ РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ, их кол-во " + allDecisions.size());
        allDecisions.sort(IntervalGraph::reverseCompareTo);
//        System.out.println(allDecisions);
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testCountProbabilityWithRandomGraphPrim() {
        Task component = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
        ArrayList<IntervalGraph> allDecisions = new ArrayList<>(component.getDecisionsWithoutRepeating(graph4));
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions) {
            probabilitySum += g.getProbability();
        }

//        System.out.println("РЕШЕНИЯ БЕЗ ДУБЛИРОВАНИЯ");

//        System.out.println(allDecisions);
//        System.out.println(probabilitySum);
        assertEquals(1, probabilitySum, 1e-6);
    }

    @Test
    public void testEqualDecisionsWithRandomGraphPrimAndKruskal() {
        Task component1 = new Task(
                1,
                new IntervalKruskalAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
        ArrayList<IntervalGraph> allDecisions1 = component1.getDecisions();
        Task component2 = new Task(
                1,
                new IntervalPrimAlghoritm(),
                new IntervalGraph(),
                graph4.getEdges());
        ArrayList<IntervalGraph> allDecisions2 = component2.getDecisions();
        double probabilitySum = 0;
        for (IntervalGraph g : allDecisions1) {
            probabilitySum += g.getProbability();
        }
        assertEquals(new HashSet<>(allDecisions1), new HashSet<>(allDecisions2));
    }


}
