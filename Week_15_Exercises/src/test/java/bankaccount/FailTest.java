package bankaccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.assertTrue;


// 4.
// 
// If you have three
//  
// JUnit
//  
// test methods written in the same testing class and the first one fails its
// 
// assertions, will he other methods still be executed? (try it)

@TestMethodOrder(OrderAnnotation.class)
class FailTest {

    //fail
    // @Test
    // @Order(1)
    // void test1() {
    //     assertTrue(false, "FAIL ON PURPOSE TO THROW ERROR");
    // }

    //See if these pass afterwards
    @Test
    @Order(2)
    void test2() {
        assertTrue(true);
    }


    @Test
    @Order(3)
    void test3() {
        assertTrue(true);
    }
}

//Output (we can see that 3 tests ran even though 1 failed)

// [ERROR] Failures: 
// [ERROR]   FailTest.firstTestFails:17 FAIL ON PURPOSE TO THROW ERROR ==> expected: <true> but was: <false>
// [ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0
// [ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.1.2:test (default-test) on project week15-exercises: There are test failures.
// [ERROR] 
// [ERROR] Please refer to /var/home/b/Documents/GitHub/Principles-of-Programming-Concepts/Week_15_Exercises/target/surefire-reports for the individual test results.
// [ERROR] Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
// [ERROR] -> [Help 1]
// [ERROR] 
// [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
// [ERROR] Re-run Maven using the -X switch to enable full debug logging.
// [ERROR] 
// [ERROR] For more information about the errors and possible solutions, please read the following articles:
// [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
