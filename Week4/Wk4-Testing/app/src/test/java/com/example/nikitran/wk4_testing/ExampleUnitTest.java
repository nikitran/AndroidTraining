package com.example.nikitran.wk4_testing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMyAddition(){
        MACMath mm = new MACMath();
        int r = mm.addEm(3, 5);

        assertEquals(8, r);
    }

    @Test
    public void testMyMultiplication(){
        MACMath mm = new MACMath();
        int r = mm.multEm(3, 5);

        assertEquals(15, r);
    }
}