package com.adityamlk.codelibrary.datastructure.tree;

import java.util.PriorityQueue;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyMinHeapTest {

    private MyMinHeap<Integer> myMinHeap;

    private PriorityQueue<Integer> defaultPriorityQueue;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        insertValues(1000, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        insertValues(10000, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        insertValues(100000, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        final int[] valuesInserted = insertValues(1000, false);
        removeValues(valuesInserted.length, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        final int[] valuesInserted = insertValues(10000, false);
        removeValues(valuesInserted.length, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        final int[] valuesInserted = insertValues(100000, false);
        removeValues(valuesInserted.length, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void removeHundredMillionValuesTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        final int[] valuesInserted = insertValues(1000000, false);
        removeValues(valuesInserted.length, true);

        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    @Test
    public void containsTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        final int[] valuesInserted = insertValues(100000, false);
        final int firstValue = valuesInserted[5555];
        final int secondValue = valuesInserted[1994];
        final int thirdValue = valuesInserted[99999];

        assertThat("Result does not match.", myMinHeap.contains(firstValue), is(defaultPriorityQueue.contains(firstValue)));
        assertThat("Result does not match.", myMinHeap.contains(secondValue), is(defaultPriorityQueue.contains(secondValue)));
        assertThat("Result does not match.", myMinHeap.contains(thirdValue), is(defaultPriorityQueue.contains(thirdValue)));

        removeValues(valuesInserted.length, false);

        assertThat("Result does not match.", myMinHeap.contains(firstValue), is(defaultPriorityQueue.contains(firstValue)));
        assertThat("Result does not match.", myMinHeap.contains(secondValue), is(defaultPriorityQueue.contains(secondValue)));
        assertThat("Result does not match.", myMinHeap.contains(thirdValue), is(defaultPriorityQueue.contains(thirdValue)));
    }

    @Test
    public void extractTest() {
        myMinHeap = new MyMinHeap<>();
        defaultPriorityQueue = new PriorityQueue<>(1);

        insertValues(100000, false);

        assertThat("Result does not match.", myMinHeap.extract(), is(defaultPriorityQueue.remove()));
        assertThat("Result does not match.", myMinHeap.extract(), is(defaultPriorityQueue.remove()));
        assertThat("Result does not match.", myMinHeap.extract(), is(defaultPriorityQueue.remove()));
        assertThat("Size is incorrect.", myMinHeap.getSize(), is(defaultPriorityQueue.size()));
        assertThat("Min Heap is incorrect.", myMinHeap.toString(), is(defaultPriorityQueue.toString()));
    }

    private int[] insertValues(final int valueCount, final boolean shouldLog) {
        final Random random = new Random();
        final int[] valuesToInsert = random.ints(valueCount).toArray();

        final long insertMyStartTime = System.nanoTime();

        for (final int value : valuesToInsert) {
            myMinHeap.insert(value);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (final int value : valuesToInsert) {
            defaultPriorityQueue.add(value);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my min heap: {} vs default priority queue: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }

        return valuesToInsert;
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myMinHeap.extract();
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultPriorityQueue.remove();
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my min heap: {} vs default priority queue: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
