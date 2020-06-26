package hiking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @Test
    void add() {
        assertEquals(6, Calc.add(2,3));
    }
}
