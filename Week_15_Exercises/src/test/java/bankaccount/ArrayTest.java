package bankaccount;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

// 2.

// Write a JUnit test assuming you have an array of int values and you only want the JUnit

// test to fail if any of the values are less than 20.
class ArrayTest {

    @Test
    void AtLeastTwenty() {
        int[] values = {20, 25, 30, 42};
        assertTrue(Arrays.stream(values).allMatch(value -> value >= 20));
    }
}
