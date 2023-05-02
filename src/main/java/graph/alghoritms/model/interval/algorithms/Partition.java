package graph.alghoritms.model.interval.algorithms;

import graph.alghoritms.model.interval.Interval;
import graph.alghoritms.model.interval.IntervalEdge;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    /**
     * @param Q - множество рёбер без обрезанных весов
     * @return - отсортированный список всех границ кусочков
     */
    public static List<Integer> getBordersForPartitionOfQ(List<IntervalEdge> Q) {
        List<Integer> answer = new ArrayList<>();
        int a;
        for (IntervalEdge edge : Q) {
            a = edge.getStartWeight();
            if (!answer.contains(a)) {
                answer.add(a);
            }
            a = edge.getEndWeight();
            if (!answer.contains(a)) {
                answer.add(a);
            }
        }
        answer.sort(Integer::compareTo);
        return answer;
    }

    /**
     * @param e_q                    - ребро (с обрезанным весом)
     * @param bordersForPartitionOfQ - список возможных границ кусочков без повторений
     * @param partitionForQ          - все элементы разбиения весов,
     *                              которые только встречаются среди ребер из Q
     * @return - список элементов разбиения веса ребра e_q
     */
    public static List<Interval> getPartitionFor_e_q(IntervalEdge e_q,
                                                     List<Integer> bordersForPartitionOfQ,
                                                     List<Interval> partitionForQ) {
        List<Interval> answer = new ArrayList<>();
        int startIndex = bordersForPartitionOfQ.indexOf(e_q.getStartWeight());
        int endIndex = bordersForPartitionOfQ.indexOf(e_q.getEndWeight());
        for (int i = startIndex; i < endIndex; i++) {
            answer.add(partitionForQ.get(i));
        }
        return answer;
    }

    /**
     * @param bordersForPartitionOfQ - список возможных границ
     *                               кусочков без повторений, отсортирован
     * @return - все элементы разбиения весов,
     * которые только встречаются среди ребер из Q
     */
    public static List<Interval> getPartitionForQ(
            List<Integer> bordersForPartitionOfQ) {
        List<Interval> answer = new ArrayList<>();
        for (int i = 0; i < bordersForPartitionOfQ.size() - 1; i++) {
            answer.add(new Interval(
                    bordersForPartitionOfQ.get(i),
                    bordersForPartitionOfQ.get(i + 1)));
        }
        return answer;
    }
}
