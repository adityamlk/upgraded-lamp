package com.adityamlk.codelibrary.problem.cracking.easy;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Chapter8SolutionsTest {

    /*
     * Problem 1: Count the number of ways to get to top of the steps by taking 1, 2, or 3 steps at a time.
     */

    @Test
    public void getWaysToClimbStepsFirstTest() {
        final int resultReturned = Chapter8Solutions.getWaysToClimbSteps(3);

        assertThat("Result is incorrect.", resultReturned, is(4));
    }

    @Test
    public void getWaysToClimbStepsSecondTest() {
        final int resultReturned = Chapter8Solutions.getWaysToClimbSteps(4);

        assertThat("Result is incorrect.", resultReturned, is(7));
    }

    @Test
    public void getWaysToClimbStepsThirdTest() {
        final int resultReturned = Chapter8Solutions.getWaysToClimbSteps(5);

        assertThat("Result is incorrect.", resultReturned, is(13));
    }

    /*
     * Problem 2: Find a path for a robot that only moves right or down from top left to bottom right of a grid.
     */

    @Test
    public void findPathForRobotFirstTest() {
        final boolean[][] grid = new boolean[5][5];
        grid[1][0] = true;
        grid[1][1] = true;
        grid[3][2] = true;
        grid[4][3] = true;

        final List<String> resultReturned = Chapter8Solutions.findPathForRobot(grid);

        assertNotNull(resultReturned, "Result is incorrect.");
        assertThat("Result is incorrect.", resultReturned.size(), is(9));
    }
}
