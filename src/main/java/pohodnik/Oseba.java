package pohodnik;

public class Oseba {
    private int id = 0;
    private String ime;
    private String priimek;

    public Oseba(String ime, String priimek, int id) {
        this.ime = ime;
        this.priimek = priimek;
        this.id = id;
    }

    public Oseba(String ime, String priimek) {
        this.ime = ime;
        this.priimek = priimek;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
