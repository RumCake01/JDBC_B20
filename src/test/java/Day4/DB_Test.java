package Day4;

import org.junit.jupiter.api.*;

public class DB_Test {

    // what can we use if we want to run certain code before all test only once
    //@BeforeAll
    @BeforeAll
    public static void setUp(){
        System.out.println("Before all is running!!");
    }

    // what can we use if we want to run certain code before each test
    //@BeforeEach
    @BeforeEach
    public void beforeEachMethod(){
        System.out.println("Before Each is running!!!");

    }
    // what can we use if we want to run certain code before all test only once
    // @AfterAll
    @AfterAll
    public static void tearDown(){
        System.out.println(" @AfterAll  is running!!");
    }
    // what can we use if we want to run certain code before each test
    //@AfterEach
    @AfterEach
    public void afterEachMethod() {
        System.out.println(" @AfterEach  is running!!!");
    }


    @Test
    public void test1(){

        System.out.println("Test 1 is running");
        Assertions.assertEquals(9,3*3);

    }

    @Test
    public void test2(){
        System.out.println("Test 2 is running");
    }
}
