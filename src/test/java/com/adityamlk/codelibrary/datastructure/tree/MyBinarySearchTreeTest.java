package com.adityamlk.codelibrary.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyBinarySearchTreeTest {

    private MyBinarySearchTree<Integer> myBinarySearchTree;

    private ArrayList<Integer> defaultArrayList;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        insertValues(1000, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        insertValues(10000, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void insertMillionValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        insertValues(1000000, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void removeThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        final int[] valuesInserted = insertValues(1000, false);
        removeValues(valuesInserted, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        final int[] valuesInserted = insertValues(10000, false);
        removeValues(valuesInserted, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        final int[] valuesInserted = insertValues(100000, false);
        removeValues(valuesInserted, true);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Size is incorrect.", myBinarySearchTree.getSize(), is(sortedDefaultArray.size()));
        assertThat("BST is incorrect.", myBinarySearchTree.toString(), is(sortedDefaultArray.toString()));
    }

    @Test
    public void containsTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        final int[] valuesInserted = insertValues(100000, false);
        final int firstValue = valuesInserted[5555];
        final int secondValue = valuesInserted[1994];
        final int thirdValue = valuesInserted[99999];

        assertThat("Result does not match.", myBinarySearchTree.contains(firstValue), is(defaultArrayList.contains(firstValue)));
        assertThat("Result does not match.", myBinarySearchTree.contains(secondValue), is(defaultArrayList.contains(secondValue)));
        assertThat("Result does not match.", myBinarySearchTree.contains(thirdValue), is(defaultArrayList.contains(thirdValue)));

        removeValues(valuesInserted, false);

        assertThat("Result does not match.", myBinarySearchTree.contains(firstValue), is(defaultArrayList.contains(firstValue)));
        assertThat("Result does not match.", myBinarySearchTree.contains(secondValue), is(defaultArrayList.contains(secondValue)));
        assertThat("Result does not match.", myBinarySearchTree.contains(thirdValue), is(defaultArrayList.contains(thirdValue)));
    }

    @Test
    public void getMinMaxTest() {
        myBinarySearchTree = new MyBinarySearchTree<>();
        defaultArrayList = new ArrayList<>();

        insertValues(100000, false);

        final List<Integer> sortedDefaultArray = defaultArrayList.stream().sorted().toList();

        assertThat("Result does not match.", myBinarySearchTree.getMinValue(), is(sortedDefaultArray.get(0)));
        assertThat("Result does not match.", myBinarySearchTree.getMaxValue(), is(sortedDefaultArray.get(99999)));
    }

    private int[] insertValues(final int valueCount, final boolean shouldLog) {
        final Random random = new Random();
        final int[] valuesToInsert = random.ints(valueCount).toArray();

        final long insertMyStartTime = System.nanoTime();

        for (final int value : valuesToInsert) {
            myBinarySearchTree.insert(value);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (final int value : valuesToInsert) {
            defaultArrayList.add(value);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my binary search tree: {} vs default array list: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }

        return valuesToInsert;
    }

    private void removeValues(final int[] valuesToRemove, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (final int value : valuesToRemove) {
            myBinarySearchTree.remove(value);
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (final int value : valuesToRemove) {
            defaultArrayList.remove((Integer) value);
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            final int valueCount = valuesToRemove.length;
            log.info(
                    "Time to remove {} values from my binary search tree: {} vs default array list: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
