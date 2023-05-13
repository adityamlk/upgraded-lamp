package com.adityamlk.codelibrary.problem.cracking.easy;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Chapter5SolutionsTest {

    /*
     * Insert one number into another using the specified indices.
     */

    @Test
    public void insertNumberFirstTest() {
        final int result = Chapter5Solutions.insertNumber(1 << 10, 19, 2, 6);

        assertThat("Result is incorrect.", result, is(1100));
    }
}
