package com.adityamlk.codelibrary.datastructure.collection;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
public class MyDoubleLinkedListTest {

    private MyDoubleLinkedList<Integer> myLinkedList = new MyDoubleLinkedList<>();

    private LinkedList<Integer> defaultLinkedList = Lists.newLinkedList();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(100000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000000, false);
        removeValues(1000000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeMillionValuesTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(100000, false);
        removeValues(100000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void getTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(100000, false);

        assertThat("Index does not match.", myLinkedList.get(5555), is(defaultLinkedList.get(5555)));
        assertThat("Index does not match.", myLinkedList.get(1994), is(defaultLinkedList.get(1994)));
        assertThat("Index does not match.", myLinkedList.get(0), is(defaultLinkedList.get(0)));
        assertThat("Index does not match.", myLinkedList.get(99999), is(defaultLinkedList.get(99999)));
        assertThat("Index does not match.", myLinkedList.get(67893), is(defaultLinkedList.get(67893)));

        removeValues(50000, false);

        assertThat("Index does not match.", myLinkedList.get(5555), is(defaultLinkedList.get(5555)));
        assertThat("Index does not match.", myLinkedList.get(1994), is(defaultLinkedList.get(1994)));
        assertThat("Index does not match.", myLinkedList.get(0), is(defaultLinkedList.get(0)));

        IndexOutOfBoundsException exceptionThrown =
                assertThrows(
                        IndexOutOfBoundsException.class,
                        () -> myLinkedList.get(99999),
                        "Incorrect exception thrown.");
        assertThat(
                "Exception message is incorrect.",
                exceptionThrown.getMessage(),
                containsString("Index not found in the list."));

        exceptionThrown =
                assertThrows(
                        IndexOutOfBoundsException.class,
                        () -> myLinkedList.get(67893),
                        "Incorrect exception thrown.");
        assertThat(
                "Exception message is incorrect.",
                exceptionThrown.getMessage(),
                containsString("Index not found in the list."));
    }

    @Test
    public void containsTest() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(100000, false);

        assertThat("Result does not match.", myLinkedList.contains(5555), is(defaultLinkedList.contains(5555)));
        assertThat("Result does not match.", myLinkedList.contains(1994), is(defaultLinkedList.contains(1994)));
        assertThat("Result does not match.", myLinkedList.contains(-1), is(defaultLinkedList.contains(-1)));
        assertThat("Result does not match.", myLinkedList.contains(99999), is(defaultLinkedList.contains(99999)));
        assertThat("Result does not match.", myLinkedList.contains(444444), is(defaultLinkedList.contains(444444)));

        removeValues(50000, false);

        assertThat("Result does not match.", myLinkedList.contains(5555), is(defaultLinkedList.contains(5555)));
        assertThat("Result does not match.", myLinkedList.contains(1994), is(defaultLinkedList.contains(1994)));
        assertThat("Result does not match.", myLinkedList.contains(-1), is(defaultLinkedList.contains(-1)));
        assertThat("Result does not match.", myLinkedList.contains(99999), is(defaultLinkedList.contains(99999)));
        assertThat("Result does not match.", myLinkedList.contains(444444), is(defaultLinkedList.contains(444444)));
    }

    @Test
    public void insertToTail_multipleGets_multipleRemoves_Test() {
        myLinkedList = new MyDoubleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        final Random random = new Random();
        final int[] integerArray = random.ints(10000, 32, 128).toArray();

        for (final int index : integerArray) {
            myLinkedList.insertToHead(index);
            defaultLinkedList.addFirst(index);
        }

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));

        assertThat("Result is not correct.", myLinkedList.removeFromHead(), is(defaultLinkedList.removeFirst()));
        assertThat("Result is not correct.", myLinkedList.removeFromHead(), is(defaultLinkedList.removeFirst()));
        assertThat("Result is not correct.", myLinkedList.removeFromTail(), is(defaultLinkedList.removeLast()));
        assertThat("Result is not correct.", myLinkedList.removeFromTail(), is(defaultLinkedList.removeLast()));

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));

        assertThat("Result is not correct.", myLinkedList.getFromHead(), is(defaultLinkedList.getFirst()));
        assertThat("Result is not correct.", myLinkedList.getFromTail(), is(defaultLinkedList.getLast()));
    }

    private void insertValues(final int valueCount, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myLinkedList.insertToTail(i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultLinkedList.addLast(i);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my linked list: {} vs default linked list: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myLinkedList.remove(i);
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultLinkedList.remove((Integer) i);
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my linked list: {} vs default linked list: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
