package pohodnik.model;

public class Hike {
    private int id = 0;
    private String name;

    public Hike(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hike(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
