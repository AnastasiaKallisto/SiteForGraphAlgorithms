package graph.alghoritms.model.interval;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Getter
public class IntervalEdge {
    private ArrayList<Integer> vertices;
    private int startWeight;
    private int endWeight;

    public IntervalEdge(Integer a, Integer b, int start, int end) {
        vertices = new ArrayList<>();
        vertices.add(a);
        vertices.add(b);
        startWeight = start;
        endWeight = end;
    }

    public IntervalEdge(IntervalEdge edge) {
        vertices = new ArrayList<>();
        vertices.add(edge.getA());
        vertices.add(edge.getB());
        startWeight = edge.startWeight;
        endWeight = edge.endWeight;
    }

    public Interval getIntervalWeight(){
        return new Interval(startWeight, endWeight);
    }

    public void setStart(int s) {
        if (s < endWeight) {
            startWeight = s;
        }
    }

    public void setEnd(int e) {
        if (e > startWeight) {
            endWeight = e;
        }
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
        IntervalEdge that = (IntervalEdge) o;
        return vertices.containsAll(that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new HashSet<>(vertices));
    }

    public int compareToRight(IntervalEdge edge) {
        return Integer.compare(endWeight, edge.endWeight);
    }

    public int compareToLeft(IntervalEdge edge) {
        return Integer.compare(startWeight, edge.startWeight);
    }

    @Override
    public String toString() {
        return "e" + getA() + "_" + getB() +
                " [" + startWeight +
                ", " + endWeight +
                "]   ";
    }
}
