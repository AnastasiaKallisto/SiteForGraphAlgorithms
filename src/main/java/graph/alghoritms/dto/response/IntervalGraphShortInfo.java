package graph.alghoritms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IntervalGraphShortInfo {
    private int id;
    private double probability;
    private int minWeight;
    private int maxWeight;
}
