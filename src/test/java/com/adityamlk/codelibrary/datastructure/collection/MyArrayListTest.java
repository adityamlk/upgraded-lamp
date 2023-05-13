package com.adityamlk.codelibrary.datastructure.collection;

import java.util.ArrayList;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyArrayListTest {

    private MyArrayList<Integer> myArrayList;

    private ArrayList<Integer> defaultArrayList;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void defaultConstructorTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void oneParameterConstructorTest() {
        myArrayList = new MyArrayList<>(10);
        defaultArrayList = new ArrayList<>(10);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void twoParameterConstructorTest() {
        myArrayList = new MyArrayList<>(10, 3);
        defaultArrayList = new ArrayList<>(10);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void insertThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(1000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(10000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, false);
        removeValues(100000, true);

        assertThat("Size is incorrect.", myArrayList.getSize(), is(defaultArrayList.size()));
        assertThat("List is incorrect.", myArrayList.toString(), is(defaultArrayList.toString()));
    }

    @Test
    public void indexOfTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, false);

        assertThat("Index does not match.", myArrayList.indexOf(5555), is(defaultArrayList.indexOf(5555)));
        assertThat("Index does not match.", myArrayList.indexOf(1994), is(defaultArrayList.indexOf(1994)));
        assertThat("Index does not match.", myArrayList.indexOf(0), is(defaultArrayList.indexOf(0)));
        assertThat("Index does not match.", myArrayList.indexOf(99999), is(defaultArrayList.indexOf(99999)));
        assertThat("Index does not match.", myArrayList.indexOf(67893), is(defaultArrayList.indexOf(67893)));

        removeValues(50000, false);

        assertThat("Index does not match.", myArrayList.indexOf(5555), is(defaultArrayList.indexOf(5555)));
        assertThat("Index does not match.", myArrayList.indexOf(1994), is(defaultArrayList.indexOf(1994)));
        assertThat("Index does not match.", myArrayList.indexOf(0), is(defaultArrayList.indexOf(0)));
        assertThat("Index does not match.", myArrayList.indexOf(99999), is(defaultArrayList.indexOf(99999)));
        assertThat("Index does not match.", myArrayList.indexOf(67893), is(defaultArrayList.indexOf(67893)));
    }

    @Test
    public void containsTest() {
        myArrayList = new MyArrayList<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, false);

        assertThat("Result does not match.", myArrayList.contains(5555), is(defaultArrayList.contains(5555)));
        assertThat("Result does not match.", myArrayList.contains(1994), is(defaultArrayList.contains(1994)));
        assertThat("Result does not match.", myArrayList.contains(-1), is(defaultArrayList.contains(-1)));
        assertThat("Result does not match.", myArrayList.contains(99999), is(defaultArrayList.contains(99999)));
        assertThat("Result does not match.", myArrayList.contains(444444), is(defaultArrayList.contains(444444)));

        removeValues(50000, false);

        assertThat("Result does not match.", myArrayList.contains(5555), is(defaultArrayList.contains(5555)));
        assertThat("Result does not match.", myArrayList.contains(1994), is(defaultArrayList.contains(1994)));
        assertThat("Result does not match.", myArrayList.contains(-1), is(defaultArrayList.contains(-1)));
        assertThat("Result does not match.", myArrayList.contains(99999), is(defaultArrayList.contains(99999)));
        assertThat("Result does not match.", myArrayList.contains(444444), is(defaultArrayList.contains(444444)));
    }

    private void insertValues(final int valueCount, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myArrayList.insert(i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultArrayList.add(i);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my array list: {} vs default array list: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myArrayList.remove(i);
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultArrayList.remove((Integer) i);
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my array list: {} vs default array list: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
