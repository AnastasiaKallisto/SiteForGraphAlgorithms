package graph.alghoritms.database;

import graph.alghoritms.model.exact.ExactGraph;
import graph.alghoritms.model.interval.IntervalGraph;

import java.util.List;

public class Database {
    private static Database instance;
    public ExactGraph exactGraph = null;
    public ExactGraph exactPrimGraph = null;
    public ExactGraph exactKruskalGraph = null;

    public IntervalGraph intervalGraph;
    public IntervalGraph intervalGraphToShow;
    public List<IntervalGraph> intervalGraphsPrim;
    public List<IntervalGraph> intervalGraphsKruskal;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void clearExact(){
        exactGraph = null;
        exactPrimGraph = null;
        exactKruskalGraph = null;
    }

    public void clearInterval(){
        intervalGraph = null;
        intervalGraphsPrim = null;
        intervalGraphsKruskal = null;
        intervalGraphToShow = null;
    }
}
