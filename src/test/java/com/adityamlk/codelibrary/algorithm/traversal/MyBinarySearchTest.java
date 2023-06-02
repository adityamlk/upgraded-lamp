package com.adityamlk.codelibrary.algorithm.traversal;

import com.google.common.collect.ImmutableList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyBinarySearchTest {

    private static final List<Integer> SORTED_LIST =
            ImmutableList.of(8, 23, 26, 31, 35, 38, 41, 44, 48, 56, 77, 88, 89, 91, 99);

    private final MyBinarySearch myBinarySearch = new MyBinarySearch();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void doesContainValueLowValueNoMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(1, SORTED_LIST);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesContainValueHighValueNoMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(101, SORTED_LIST);

        assertThat("Result does not match.", result, is(false));
    }

    @Test
    public void doesContainValueFirstIndexMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(8, SORTED_LIST);

        assertThat("Result does not match.", result, is(true));
    }

    @Test
    public void doesContainValueMiddleIndexMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(44, SORTED_LIST);

        assertThat("Result does not match.", result, is(true));
    }

    @Test
    public void doesContainValueLastIndexMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(99, SORTED_LIST);

        assertThat("Result does not match.", result, is(true));
    }

    @Test
    public void doesContainValueLowIndexMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(26, SORTED_LIST);

        assertThat("Result does not match.", result, is(true));
    }

    @Test
    public void doesContainValueHighIndexMatchTest() {
        final boolean result = myBinarySearch.doesContainValue(89, SORTED_LIST);

        assertThat("Result does not match.", result, is(true));
    }
}
