package hiking;

public class Calc {
    private Calc() {
        throw new IllegalStateException("Calc class");
    }
    public static int add(int x, int y) {
        return x+y;
    }
}
