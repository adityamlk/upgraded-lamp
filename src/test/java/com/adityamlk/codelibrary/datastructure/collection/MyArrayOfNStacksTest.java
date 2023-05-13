package com.adityamlk.codelibrary.datastructure.collection;

import java.util.Stack;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyArrayOfNStacksTest {

    private MyArrayOfNStacks<Integer> myArrayOfNStacks = new MyArrayOfNStacks<>(3);

    private Stack<Integer> defaultStack = new Stack<>();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(1000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(10000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(100000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(1000000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(1000, 2, false);
        removeValues(1000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(10000, 2, false);
        removeValues(10000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(100000, 2, false);
        removeValues(100000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void removeMillionValuesTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(1000000, 2, false);
        removeValues(1000000, 2, true);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));
    }

    @Test
    public void containsTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);
        defaultStack = new Stack<>();

        insertValues(100000, 2, false);

        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 5555), is(defaultStack.contains(5555)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 1994), is(defaultStack.contains(1994)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, -1), is(defaultStack.contains(-1)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 99999), is(defaultStack.contains(99999)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 444444), is(defaultStack.contains(444444)));

        removeValues(50000, 2, false);

        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 5555), is(defaultStack.contains(5555)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 1994), is(defaultStack.contains(1994)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, -1), is(defaultStack.contains(-1)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 99999), is(defaultStack.contains(99999)));
        assertThat("Result does not match.", myArrayOfNStacks.containsInStack(2, 444444), is(defaultStack.contains(444444)));
    }

    @Test
    public void multiStackTest() {
        myArrayOfNStacks = new MyArrayOfNStacks<>(3);

        defaultStack = new Stack<>();
        insertValues(250, 1, false);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(1), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(1), is(defaultStack.toString()));

        defaultStack = new Stack<>();
        insertValues(350, 2, false);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(2), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(2), is(defaultStack.toString()));

        defaultStack = new Stack<>();
        insertValues(400, 3, false);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(3), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(3), is(defaultStack.toString()));

        removeValues(200, 3, false);

        assertThat("Size is incorrect.", myArrayOfNStacks.getStackSize(3), is(defaultStack.size()));
        assertThat("Stack is incorrect.", myArrayOfNStacks.getStackAsString(3), is(defaultStack.toString()));

    }

    private void insertValues(final int valueCount, final int stackNumber, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myArrayOfNStacks.insertIntoStack(stackNumber, i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultStack.push(i);
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

    private void removeValues(final int valueCount, final int stackNumber, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myArrayOfNStacks.removeFromStack(stackNumber);
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultStack.pop();
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
