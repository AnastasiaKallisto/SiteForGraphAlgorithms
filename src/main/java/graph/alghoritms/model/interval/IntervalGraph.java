package graph.alghoritms.model.interval;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class IntervalGraph {
    @Setter
    private int id;
    private List<IntervalEdge> edges;
    private Set<Integer> vertices;
    @Setter
    private double probability;

    public IntervalGraph() {
        id = 0;
        vertices = new HashSet<>();
        edges = new ArrayList<>();
        probability = 1;
    }

    public IntervalGraph(int n) {
        id = 0;
        vertices = new HashSet<>();
        edges = new ArrayList<>();
        probability = 1;
        for (int i = 1; i <= n; i++) {
            vertices.add(i);
        }
        int quantityOfActedVertices = 2;
        int firstNumber, secondNumber;
        int start = (int) (Math.random() * 100 + 1);
        int end = start + (int) (Math.random() * 100 + 1);
        edges.add(new IntervalEdge(1, 2, start, end));
        // создаем дерево
        while (quantityOfActedVertices < n) {
            firstNumber = (int) Math.floor(Math.random() * quantityOfActedVertices) + 1; // задейств. вершина
            secondNumber = ++quantityOfActedVertices; // новая вершина
            start = (int) (Math.random() * 100 + 1);
            end = start + (int) (Math.random() * 100 + 1);
            edges.add(new IntervalEdge(firstNumber, secondNumber, start, end));
        }
        //усложним граф
        int randomQuantity = (int) (Math.random() * 2 * n) + 1;
        for (int i = 1; i < randomQuantity; i++) {
            firstNumber = (int) Math.floor(Math.random() * n) + 1;
            secondNumber = (int) Math.floor(Math.random() * n) + 1;
            if (firstNumber != secondNumber) {
                start = (int) (Math.random() * 100 + 1);
                end = start + (int) (Math.random() * 100 + 1);
                IntervalEdge edge = new IntervalEdge(firstNumber, secondNumber, start, end);
                if (!edges.contains(edge)) {
                    edges.add(edge);
                }
            }
        }
    }

    public IntervalGraph(final IntervalGraph graph) {
        this.id = graph.id;
        this.edges = new ArrayList<>(graph.getEdges());
        this.vertices = new HashSet<>(graph.getVertices());
        this.probability = graph.probability;
    }

    public IntervalGraph(int id, List<IntervalEdge> edges, Set<Integer> vertices) {
        this.id = id;
        this.edges = new ArrayList<>(edges);
        this.vertices = new HashSet<>(vertices);
        this.probability = 0;
    }

    public void addEdge(IntervalEdge edge) {
        if (!edges.contains(edge)) {
            edges.add(edge);
            vertices.add(edge.getA());
            vertices.add(edge.getB());
        }
    }

    public void addEdge(int a, int b, int startWeight, int endWeight) {
        IntervalEdge edge = new IntervalEdge(a,b,startWeight, endWeight);
        if (!edges.contains(edge)) {
            edges.add(edge);
            vertices.add(edge.getA());
            vertices.add(edge.getB());
        }
    }

    public void addVertex(Integer vertex) {
        vertices.add(vertex);
    }

    @Override
    public String toString() {
        return "\n\nIntervalGraph{" +
                "\nid=" + id +
                ", \nprobability=" + probability +
                ", \nedges=" + edges +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntervalGraph)) return false;
        IntervalGraph that = (IntervalGraph) o;
        return new HashSet<>(edges).equals(new HashSet<>(that.edges)) && vertices.equals(that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(edges), vertices);
    }

    public int compareTo(IntervalGraph g){
        if (this.probability > g.probability)
            return 1;
        if (this.probability < g.probability)
            return -1;
        return 0;
    }

    public int reverseCompareTo(IntervalGraph g){
        return -compareTo(g);
    }



}