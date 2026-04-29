package bankaccount;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayTest {

    @Test
    void AtLeastTwenty() {
        int[] values = {20, 25, 30, 42};
        assertTrue(Arrays.stream(values).allMatch(value -> value >= 20));
    }
}
