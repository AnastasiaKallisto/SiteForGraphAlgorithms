package graph.alghoritms.model.enums;

public enum TypeOfAlgorithm {
    PRIM(1, "PRIM"),
    KRUSKAL(2, "KRUSKAL"),
    NONE(0, "NONE");

    private int id;
    private String name;

    TypeOfAlgorithm(int id, String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
