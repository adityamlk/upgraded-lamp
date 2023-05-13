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
public class MySingleLinkedListTest {

    private MySingleLinkedList<Integer> myLinkedList = new MySingleLinkedList<>();

    private LinkedList<Integer> defaultLinkedList = Lists.newLinkedList();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(100000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myLinkedList.getSize(), is(defaultLinkedList.size()));
        assertThat("List is incorrect.", myLinkedList.toString(), is(defaultLinkedList.toString()));
    }

    @Test
    public void getTest() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, false);

        assertThat("Index does not match.", myLinkedList.get(555), is(defaultLinkedList.get(555)));
        assertThat("Index does not match.", myLinkedList.get(194), is(defaultLinkedList.get(194)));
        assertThat("Index does not match.", myLinkedList.get(0), is(defaultLinkedList.get(0)));
        assertThat("Index does not match.", myLinkedList.get(9999), is(defaultLinkedList.get(9999)));
        assertThat("Index does not match.", myLinkedList.get(6789), is(defaultLinkedList.get(6789)));

        removeValues(5000, false);

        assertThat("Index does not match.", myLinkedList.get(555), is(defaultLinkedList.get(555)));
        assertThat("Index does not match.", myLinkedList.get(194), is(defaultLinkedList.get(194)));
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
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        insertValues(10000, false);

        assertThat("Result does not match.", myLinkedList.contains(555), is(defaultLinkedList.contains(555)));
        assertThat("Result does not match.", myLinkedList.contains(194), is(defaultLinkedList.contains(194)));
        assertThat("Result does not match.", myLinkedList.contains(-1), is(defaultLinkedList.contains(-1)));
        assertThat("Result does not match.", myLinkedList.contains(9999), is(defaultLinkedList.contains(9999)));
        assertThat("Result does not match.", myLinkedList.contains(44444), is(defaultLinkedList.contains(44444)));

        removeValues(5000, false);

        assertThat("Result does not match.", myLinkedList.contains(555), is(defaultLinkedList.contains(555)));
        assertThat("Result does not match.", myLinkedList.contains(194), is(defaultLinkedList.contains(194)));
        assertThat("Result does not match.", myLinkedList.contains(-1), is(defaultLinkedList.contains(-1)));
        assertThat("Result does not match.", myLinkedList.contains(9999), is(defaultLinkedList.contains(9999)));
        assertThat("Result does not match.", myLinkedList.contains(44444), is(defaultLinkedList.contains(44444)));
    }

    @Test
    public void insertToTail_multipleGets_multipleRemoves_Test() {
        myLinkedList = new MySingleLinkedList<>();
        defaultLinkedList = Lists.newLinkedList();

        final Random random = new Random();
        final int[] integerArray = random.ints(1000, 32, 128).toArray();

        for (final int index : integerArray) {
            myLinkedList.insertToTail(index);
            defaultLinkedList.addLast(index);
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
            myLinkedList.insertToHead(i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultLinkedList.addFirst(i);
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
