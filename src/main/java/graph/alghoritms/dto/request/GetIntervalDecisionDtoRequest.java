package graph.alghoritms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetIntervalDecisionDtoRequest {
    private final int graphId;
    private final int resultNumber;
    private final int typeOfAlgorithm;
}
