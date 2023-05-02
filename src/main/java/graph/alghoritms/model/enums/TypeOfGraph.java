package graph.alghoritms.model.enums;

public enum TypeOfGraph {
    EXACT(1, "EXACT"),
    INTERVAL(2, "INTERVAL");

    private int id;
    private String name;

    TypeOfGraph(int id, String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
