package com.adityamlk.codelibrary.datastructure.collection;

import java.util.Stack;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyStackTest {

    private MyStack<Integer> myStack = new MyStack<>();

    private Stack<Integer> defaultStack = new Stack<>();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(1000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(10000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(100000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(100000, false);
        removeValues(100000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void removeMillionValuesTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(1000000, false);
        removeValues(1000000, true);

        assertThat("Size is incorrect.", myStack.getSize(), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myStack.toString(), is(defaultStack.toString()));
    }

    @Test
    public void containsTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(100000, false);

        assertThat("Result does not match.", myStack.contains(5555), is(defaultStack.contains(5555)));
        assertThat("Result does not match.", myStack.contains(1994), is(defaultStack.contains(1994)));
        assertThat("Result does not match.", myStack.contains(-1), is(defaultStack.contains(-1)));
        assertThat("Result does not match.", myStack.contains(99999), is(defaultStack.contains(99999)));
        assertThat("Result does not match.", myStack.contains(444444), is(defaultStack.contains(444444)));

        removeValues(50000, false);

        assertThat("Result does not match.", myStack.contains(5555), is(defaultStack.contains(5555)));
        assertThat("Result does not match.", myStack.contains(1994), is(defaultStack.contains(1994)));
        assertThat("Result does not match.", myStack.contains(-1), is(defaultStack.contains(-1)));
        assertThat("Result does not match.", myStack.contains(99999), is(defaultStack.contains(99999)));
        assertThat("Result does not match.", myStack.contains(444444), is(defaultStack.contains(444444)));
    }

    @Test
    public void peekAndPopTest() {
        myStack = new MyStack<>();
        defaultStack = new Stack<>();

        insertValues(100000, false);

        assertThat("Result does not match.", myStack.peek(), is(defaultStack.peek()));
        assertThat("Result does not match.", myStack.pop(), is(defaultStack.pop()));
        assertThat("Result does not match.", myStack.peek(), is(defaultStack.peek()));
    }

    private void insertValues(final int valueCount, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myStack.push(i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultStack.push(i);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my stack: {} vs default stack: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myStack.pop();
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultStack.pop();
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my stack: {} vs default stack: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
