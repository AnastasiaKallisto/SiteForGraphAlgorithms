package graph.alghoritms.dto.response;

import graph.alghoritms.model.interval.IntervalEdge;
import graph.alghoritms.model.interval.IntervalGraph;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetIntervalDecisionsDtoResponse {
    List<IntervalGraphShortInfo> decisions;

    public GetIntervalDecisionsDtoResponse(List<IntervalGraph> decisions) {
        if (decisions == null){
            this.decisions = new ArrayList<>();
        } else {
            int n = decisions.size();
            this.decisions = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                IntervalGraph decision = decisions.get(i);
                int minWeight = 0, maxWeight = 0;
                List<IntervalEdge> graphEdges = decision.getEdges();
                int m = graphEdges.size();
                ArrayList<IntervalEdgeInfo> edges = new ArrayList<>(m);
                for (int j = 0; j < m; j++) {
                    IntervalEdgeInfo e = new IntervalEdgeInfo(graphEdges.get(j));
                    edges.add(e);
                    minWeight+=e.getStartWeight();
                    maxWeight+=e.getEndWeight();
                }

                IntervalGraphShortInfo responseDecision =
                        new IntervalGraphShortInfo(
                                i+1,
                                decision.getProbability(),
                                minWeight,
                                maxWeight
                                );
                this.decisions.add(responseDecision);
            }
        }
    }
}
