package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SolutionTillTest {
    @Test
    public void testNormalCondition() {
        assertEquals(9, SolutionTill.solveSuperMarketQueue(new int[] { 2, 2, 3, 3, 4, 4 }, 2));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, SolutionTill.solveSuperMarketQueue(new int[] {}, 1));
    }

    @Test
    public void testSingleTillManyCustomers() {
        assertEquals(15, SolutionTill.solveSuperMarketQueue(new int[] { 1, 2, 3, 4, 5 }, 1));
    }

}