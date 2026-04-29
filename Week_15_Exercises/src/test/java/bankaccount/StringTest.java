package bankaccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTest {

    @Test
    void equals() {
        String strOne = "hello";
        String strTwo = "hello";
        assertEquals(strOne, strTwo);
    }
}
