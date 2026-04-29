package bankaccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComputationsTest {

    @Test
    void fibonacciTest() {
        assertEquals(0, Computations.fibonacci(0));
        assertEquals(1, Computations.fibonacci(1));
        assertEquals(1, Computations.fibonacci(2));
        assertEquals(5, Computations.fibonacci(5));
        assertEquals(8, Computations.fibonacci(6));
        assertEquals(13, Computations.fibonacci(7));
    }

    @Test
    void fibonacciNegTest() {
        assertThrows(IllegalArgumentException.class, () -> Computations.fibonacci(-1));
    }

    @Test
    void primeTest() {
        assertFalse(Computations.isPrime(-7));
        assertFalse(Computations.isPrime(0));
        assertFalse(Computations.isPrime(1));
        assertTrue(Computations.isPrime(2));
        assertTrue(Computations.isPrime(3));
        assertTrue(Computations.isPrime(13));
        assertFalse(Computations.isPrime(9));
        assertFalse(Computations.isPrime(21));
    }

    @Test
    void evenOddTest() {
        assertTrue(Computations.isEven(4));
        assertFalse(Computations.isEven(5));
        assertTrue(Computations.isEven(-2));
        assertFalse(Computations.isEven(-3));
        assertTrue(Computations.isOdd(5));
        assertFalse(Computations.isOdd(4));
        assertTrue(Computations.isOdd(-3));
        assertFalse(Computations.isOdd(-2));
    }

    @Test
    void tempTest() {
        assertEquals(0.0, Computations.toCelsius(32.0), 0.0001);
        assertEquals(212.0, Computations.toFahrenheit(100.0), 0.0001);
        assertEquals(-40.0, Computations.toCelsius(-40.0), 0.0001);
        assertEquals(-40.0, Computations.toFahrenheit(-40.0), 0.0001);
    }
}
