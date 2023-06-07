package com.adityamlk.codelibrary.algorithm.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyMergeSortTest {

    private final MyMergeSort<Integer> myMergeSort = new MyMergeSort<>();

    private ArrayList<Integer> myArrayList;

    private ArrayList<Integer> defaultArrayList;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void doSortOneHundredEntriesTest() {
        final int valueCount = 100;
        myArrayList = new ArrayList<>(valueCount);
        defaultArrayList = new ArrayList<>(valueCount);

        populateLists(valueCount);

        sortLists(valueCount);

        assertThat("Sorted result is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void doSortOneThousandEntriesTest() {
        final int valueCount = 1000;
        myArrayList = new ArrayList<>(valueCount);
        defaultArrayList = new ArrayList<>(valueCount);

        populateLists(valueCount);

        sortLists(valueCount);

        assertThat("Sorted result is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void doSortTenThousandEntriesTest() {
        final int valueCount = 10000;
        myArrayList = new ArrayList<>(valueCount);
        defaultArrayList = new ArrayList<>(valueCount);

        populateLists(valueCount);

        sortLists(valueCount);

        assertThat("Sorted result is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void doSortTwentyThousandEntriesTest() {
        final int valueCount = 20000;
        myArrayList = new ArrayList<>(valueCount);
        defaultArrayList = new ArrayList<>(valueCount);

        populateLists(valueCount);

        sortLists(valueCount);

        assertThat("Sorted result is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void doSortThirtyThousandEntriesTest() {
        final int valueCount = 30000;
        myArrayList = new ArrayList<>(valueCount);
        defaultArrayList = new ArrayList<>(valueCount);

        populateLists(valueCount);

        sortLists(valueCount);

        assertThat("Sorted result is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    private void populateLists(final int valueCount) {
        final Random random = new Random();
        final List<Integer> valuesToInsert = random.ints(valueCount).boxed().toList();

        myArrayList.addAll(valuesToInsert);
        defaultArrayList.addAll(valuesToInsert);
    }

    private void sortLists(final int valueCount) {
        final long sortMyStartTime = System.nanoTime();

        myArrayList = (ArrayList<Integer>) myMergeSort.doSort(myArrayList);

        final long sortMyStopTime = System.nanoTime();

        final long sortDefaultStartTime = System.nanoTime();

        Collections.sort(defaultArrayList);

        final long sortDefaultStopTime = System.nanoTime();

        log.info(
                "Time to sort {} values with my merge sort: {} vs default sort: {}.",
                valueCount,
                (sortMyStopTime - sortMyStartTime) / (double) valueCount,
                (sortDefaultStopTime - sortDefaultStartTime) / (double) valueCount);
    }
}
