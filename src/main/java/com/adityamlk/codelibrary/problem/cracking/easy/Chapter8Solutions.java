package com.adityamlk.codelibrary.problem.cracking.easy;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Covers problems from Chapter 8: Recursion and Dynamic Programming.
 */
@Log4j2
public class Chapter8Solutions {

    /*
     * Problem 1: Count the number of ways to get to top of the steps by taking 1, 2, or 3 steps at a time.
     */

    /**
     * Calculates the number of ways to climb up to the top of the steps provided assuming hops of 1, 2, or 3 at each
     * step. Uses recursion and memoization to track the previous steps and re-use them instead of drilling down again.
     * <p>
     * Time Complexity: O(N); need to process the sub-steps once, and then use cache to get their value. Alternative
     * solution of recursion only will lead to runtime of O(3^N) since entire tree needs to be traversed and there are
     * three paths.
     * <p>
     * Space Complexity: O(N); due to recursive solution and memoization, need to track step data and maintain the call
     * stack for the sub-steps.
     *
     * @param numSteps Number of steps to climb.
     * @return Number of ways to climb to the top of the steps using 1, 2, or 3 hops.
     */
    public static int getWaysToClimbSteps(@NonNull final Integer numSteps) {
        if (0 >= numSteps) {
            return 0;
        } else {
            return getWaysToClimbSteps(numSteps, new Integer[numSteps + 1]);
        }
    }

    /*
     * Uses recursion and memoization to determine different ways to climb steps. Base case is whether number of steps
     * is negative, then returns 0, or 0, then returns 1. Otherwise, each step count is the sum of n-1 step, n-2 step,
     * and n-3 step. Uses memoization to track the results at each step so repeat visits are minimized to array lookups.
     */
    private static int getWaysToClimbSteps(final Integer stepNumber, final Integer[] memo) {
        if (0 > stepNumber) {
            return 0;
        } else if (0 == stepNumber) {
            return 1;
        } else {
            if (null == memo[stepNumber]) {
                memo[stepNumber] =
                        getWaysToClimbSteps(stepNumber - 1, memo)
                                + getWaysToClimbSteps(stepNumber - 2, memo)
                                + getWaysToClimbSteps(stepNumber - 3, memo);
            }

            return memo[stepNumber];
        }
    }

    /*
     * Problem 2: Find a path for a robot that only moves right or down from top left to bottom right of a grid.
     */

    /**
     * Identifies the path a robot can take from the top-left cell of a grid to the bottom-right cell while only moving
     * either right or down. Ignores cells if they are blocked, already visited, or out of bounds of the grid. Uses
     * recursion and memoization to track the visited or blocked cells to traverse the grid in DFS manner. Tracks the
     * path using a list and populates it as the stack returns with successful path.
     * <p>
     * Time Complexity: O(R*C) where R is the number of rows and C is the number of columns; need to process the
     * cells once at most due to the cache. Alternative solution of recursion only will lead to runtime of O(2^(R+C))
     * since paths can overlap on the right and down movement for the majority of the grid.
     * <p>
     * Space Complexity: O(R*C); due to recursive solution and memoization, need to track path data and maintain the call
     * stack for the cells on the paths.
     *
     * @param grid R*C grid to traverse with some cells filled in.
     * @return String path with the coordinates visited on a successful path.
     */
    public static List<String> findPathForRobot(final boolean[][] grid) {
        final List<String> path = Lists.newArrayList();

        if (findPathForRobotDFS(0, 0, grid, path)) {
            return path;
        } else {
            return null;
        }
    }

    /*
     * Generates path using right and down movements via DFS. Makes recursive call to the right or down if the cell is
     * viable and tracks the path if the path is viable.
     */
    private static boolean findPathForRobotDFS(final int colPos, final int rowPos, final boolean[][] grid, final List<String> path) {
        grid[colPos][rowPos] = true;

        log.info("At the cell ({},{}).", colPos, rowPos);

        final int numRows = grid.length;
        final int numCols = grid[0].length;

        if (colPos == numCols - 1 && rowPos == numRows - 1) {
            log.info("Reached the end of the grid at ({},{})!", colPos, rowPos);
            path.add(String.format("(%s,%s)", colPos, rowPos));
            return true;
        }

        final int right = colPos + 1;
        final int down = rowPos + 1;

        if (right < numCols && !grid[right][rowPos]) {
            if (findPathForRobotDFS(right, rowPos, grid, path)) {
                path.add(String.format("(%s,%s)", colPos, rowPos));
                return true;
            }
        }

        if (down < numRows && !grid[colPos][down]) {
            if (findPathForRobotDFS(colPos, down, grid, path)) {
                path.add(String.format("(%s,%s)", colPos, rowPos));
                return true;
            }
        }

        return false;
    }
}
