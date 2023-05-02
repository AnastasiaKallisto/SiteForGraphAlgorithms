package graph.alghoritms.daoimpl;

import graph.alghoritms.dao.ExactGraphDao;
import graph.alghoritms.database.Database;
import graph.alghoritms.model.exact.ExactGraph;

public class ExactGraphDaoImpl implements ExactGraphDao {
    private Database database;

    public ExactGraphDaoImpl() {
        database = Database.getInstance();
    }

    @Override
    public ExactGraph getExactGraph() {
        return database.exactGraph;
    }

    @Override
    public void setExactGraph(ExactGraph exactGraph) {
        database.exactGraph = exactGraph;
    }

    @Override
    public ExactGraph getExactPrimGraph() {
        return database.exactPrimGraph;
    }

    @Override
    public void setExactPrimGraph(ExactGraph exactPrimGraph) {
        database.exactPrimGraph = exactPrimGraph;
    }

    @Override
    public ExactGraph getExactKruskalGraph() {
        return database.exactKruskalGraph;
    }

    @Override
    public void setExactKruskalGraph(ExactGraph exactKruskalGraph) {
        database.exactKruskalGraph = exactKruskalGraph;
    }

    @Override
    public void clearExact() {
        database.clearExact();
    }
}
