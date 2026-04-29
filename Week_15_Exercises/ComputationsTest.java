import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComputationsTest {

    @Test
    void fibonacciReturnsExpectedValues() {
        assertEquals(0, Computations.fibonacci(0));
        assertEquals(1, Computations.fibonacci(1));
        assertEquals(8, Computations.fibonacci(6));
    }

    @Test
    void fibonacciRejectsNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> Computations.fibonacci(-1));
    }

    @Test
    void primeChecksWork() {
        assertTrue(Computations.isPrime(2));
        assertTrue(Computations.isPrime(13));
        assertFalse(Computations.isPrime(1));
        assertFalse(Computations.isPrime(9));
    }

    @Test
    void evenAndOddChecksWork() {
        assertTrue(Computations.isEven(4));
        assertFalse(Computations.isEven(5));
        assertTrue(Computations.isOdd(5));
        assertFalse(Computations.isOdd(4));
    }

    @Test
    void temperatureConversionsWork() {
        assertEquals(0.0, Computations.toCelsius(32.0), 0.0001);
        assertEquals(212.0, Computations.toFahrenheit(100.0), 0.0001);
    }
}