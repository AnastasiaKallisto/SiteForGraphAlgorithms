package graph.alghoritms.daoimpl;

import graph.alghoritms.dao.IntervalGraphDao;
import graph.alghoritms.database.Database;
import graph.alghoritms.model.interval.IntervalGraph;

import java.util.List;

public class IntervalGraphDaoImpl implements IntervalGraphDao {
    private Database database;

    public IntervalGraphDaoImpl() {
        database = Database.getInstance();
    }

    @Override
    public IntervalGraph getIntervalGraph() {
        return database.intervalGraph;
    }

    @Override
    public IntervalGraph getIntervalGraphToShow() {
        return database.intervalGraphToShow;
    }

    @Override
    public void setIntervalGraph(IntervalGraph intervalGraph) {
        database.intervalGraph = intervalGraph;
    }

    @Override
    public List<IntervalGraph> getIntervalGraphsPrim() {
        return database.intervalGraphsPrim;
    }

    @Override
    public void setIntervalGraphsPrim(List<IntervalGraph> intervalGraphsPrim) {
        database.intervalGraphsPrim = intervalGraphsPrim;
    }

    @Override
    public List<IntervalGraph> getIntervalGraphsKruskal() {
        return database.intervalGraphsKruskal;
    }

    @Override
    public void setIntervalGraphsKruskal(List<IntervalGraph> intervalGraphsKruskal) {
        database.intervalGraphsKruskal = intervalGraphsKruskal;
    }

    @Override
    public void clearInterval() {
        database.clearInterval();
    }

    @Override
    public void setIntervalGraphToShow(IntervalGraph graph) {
        database.intervalGraphToShow = graph;
    }
}
