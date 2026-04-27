import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void add() {
        assertEquals (4, Calculator.add (2, 2));
    }

    @Test
    public void multiply() {
        assertAll (() -> assertEquals (4, Calculator.multiply (2, 2)),
                () -> assertEquals (-4, Calculator.multiply (2, -2)),
                () -> assertEquals (4, Calculator.multiply (-2, -2)),
                () -> assertEquals (0, Calculator.multiply (1, 0))
        );
    }

    @Test
    public void divide() {
        assertAll (() -> assertEquals (1, Calculator.divide (3, 3)),
                () -> assertEquals (-4, Calculator.divide (12, -3)),
                () -> assertEquals (4, Calculator.divide (12, 3))
        );
    }

    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator ();
        assertThrows (ArithmeticException.class, () -> calc.divide (10, 0));
    }
}