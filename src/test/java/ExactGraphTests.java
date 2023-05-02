import graph.alghoritms.model.exact.ExactGraph;
import graph.alghoritms.model.exact.ExactGraphAlgorithms;
import org.junit.jupiter.api.Test;

public class ExactGraphTests {
    @Test
    public void testCountProbabilityPrim1() {
        ExactGraph g = new ExactGraph(10);
        ExactGraphAlgorithms.returnMSTKruskal(g);
        ExactGraphAlgorithms.returnMSTPrim(g);
    }
}
