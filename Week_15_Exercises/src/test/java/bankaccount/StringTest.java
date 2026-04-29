package bankaccount;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



// 3.

// Write a JUnit test assuming you have two String references named strOne an strTwo and you only want

// it to pass if the two Strings contain the same characters

class StringTest {

    @Test
    void equals() {
        String strOne = "hello";
        String strTwo = "hello";
        assertEquals(strOne, strTwo);
    }
}
