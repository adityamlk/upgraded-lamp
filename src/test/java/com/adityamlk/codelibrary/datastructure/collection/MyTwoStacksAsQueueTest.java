package com.adityamlk.codelibrary.datastructure.collection;

import com.google.common.collect.Lists;
import java.util.Queue;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyTwoStacksAsQueueTest {

    private MyTwoStacksAsQueue<Integer> myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();

    private Queue<Integer> defaultQueue = Lists.newLinkedList();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(1000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(10000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(100000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(100000, false);
        removeValues(100000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void removeMillionValuesTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(1000000, false);
        removeValues(1000000, true);

        assertThat("Size is incorrect.", myTwoStacksAsQueue.getSize(), is(defaultQueue.size()));
        assertThat("Stack is incorrect.", myTwoStacksAsQueue.toString(), is(defaultQueue.toString()));
    }

    @Test
    public void containsTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(100000, false);

        assertThat("Result does not match.", myTwoStacksAsQueue.contains(5555), is(defaultQueue.contains(5555)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(1994), is(defaultQueue.contains(1994)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(-1), is(defaultQueue.contains(-1)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(99999), is(defaultQueue.contains(99999)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(444444), is(defaultQueue.contains(444444)));

        removeValues(50000, false);

        assertThat("Result does not match.", myTwoStacksAsQueue.contains(5555), is(defaultQueue.contains(5555)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(1994), is(defaultQueue.contains(1994)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(-1), is(defaultQueue.contains(-1)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(99999), is(defaultQueue.contains(99999)));
        assertThat("Result does not match.", myTwoStacksAsQueue.contains(444444), is(defaultQueue.contains(444444)));
    }

    @Test
    public void dequeueTest() {
        myTwoStacksAsQueue = new MyTwoStacksAsQueue<>();
        defaultQueue = Lists.newLinkedList();

        insertValues(1000, false);

        assertThat("Result does not match.", myTwoStacksAsQueue.dequeue(), is(defaultQueue.poll()));
    }

    private void insertValues(final int valueCount, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myTwoStacksAsQueue.enqueue(i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultQueue.offer(i);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my array of stacks: {} vs default stack: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myTwoStacksAsQueue.dequeue();
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultQueue.poll();
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my array of stacks: {} vs default stack: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
