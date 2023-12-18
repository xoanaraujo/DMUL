package xoanaraujo.examenuf1.model;

public class Room {
    private Integer id;
    private String name, path;

    public Room(Integer id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
