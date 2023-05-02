package graph.alghoritms.model.exact;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Getter
public class ExactEdge {
    private ArrayList<Integer> vertices;
    private int weight;

    public ExactEdge(Integer a, Integer b, int weight) {
        vertices = new ArrayList<>();
        vertices.add(a);
        vertices.add(b);
        this.weight = weight;
    }

    public Integer getA() {
        return vertices.get(0);
    }

    public Integer getB() {
        return vertices.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExactEdge exactEdge = (ExactEdge) o;
        return new HashSet<>(vertices).equals(new HashSet<>(exactEdge.vertices));
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(vertices));
    }

    public int compareTo(ExactEdge exactEdge) {
        return Integer.compare(this.getWeight(), exactEdge.getWeight());
    }
}
