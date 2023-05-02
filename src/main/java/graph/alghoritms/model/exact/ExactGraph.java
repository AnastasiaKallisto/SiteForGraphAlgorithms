package graph.alghoritms.model.exact;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ExactGraph {
    private List<ExactEdge> edges;
    private Set<Integer> vertices;

    public ExactGraph(int n) {
        vertices = new HashSet<>();
        Set<ExactEdge> edgeSet = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            vertices.add(i);
        }
        int quantityOfActedVertices = 2;
        int firstNumber, secondNumber;
        edgeSet.add(new ExactEdge(1, 2, (int) (Math.random() * 100 + 1)));
        // создаем дерево
        while (quantityOfActedVertices < n) {
            // задейств. вершина
            firstNumber = (int) Math.floor(Math.random() * quantityOfActedVertices) + 1;
            // новая вершина
            secondNumber = ++quantityOfActedVertices;
            edgeSet.add(new ExactEdge(firstNumber, secondNumber, (int) (Math.random() * 100 + 1)));
        }
        //усложним граф
        int randomQuantity = (int) (Math.random() * 2 * n) + 1;
        for (int i = 1; i < randomQuantity; i++) {
            firstNumber = (int) Math.floor(Math.random() * n) + 1;
            secondNumber = (int) Math.floor(Math.random() * n) + 1;
            if (firstNumber != secondNumber) {
                edgeSet.add(new ExactEdge(firstNumber, secondNumber, (int) (Math.random() * 100 + 1)));
            }
        }
        edges = new ArrayList<>(edgeSet);
    }

    public ExactGraph (Set<Integer> vertices, List<ExactEdge> edges){
        this.edges = edges;
        this.vertices = vertices;
    }
}
