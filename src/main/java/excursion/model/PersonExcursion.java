package excursion.model;

public class PersonExcursion {
    private int id;
    private int personId;
    private int excursionId;

    public PersonExcursion(int id, int personId, int excursionId) {
        this.id = id;
        this.personId = personId;
        this.excursionId = excursionId;
    }

    public PersonExcursion(int personId, int excursionId) {
        this.personId = personId;
        this.excursionId = excursionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(int excursionId) {
        this.excursionId = excursionId;
    }
}
