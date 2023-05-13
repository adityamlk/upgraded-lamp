package com.adityamlk.codelibrary.datastructure.collection;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyStringBuilderTest {

    private static final String SAMPLE_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
            " eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud" +
            " exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
            " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
            " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private MyStringBuilder myStringBuilder = new MyStringBuilder();

    private StringBuilder defaultStringBuilder = new StringBuilder();

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void appendThousandCharactersTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendCharacters(1000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendTenThousandCharactersTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendCharacters(10000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendHundredThousandCharactersTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendCharacters(100000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendMillionCharactersTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendCharacters(1000000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendThousandStringsTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendStrings(1000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendTenThousandStringsTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendStrings(10000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void appendHundredThousandStringsTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        appendStrings(100000);

        assertThat("Size is incorrect.", myStringBuilder.getLength(), is(defaultStringBuilder.length()));
        assertThat("String is incorrect.", myStringBuilder.toString(), is(defaultStringBuilder.toString()));
    }

    @Test
    public void indexOfTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        myStringBuilder.append(SAMPLE_TEXT);
        defaultStringBuilder.append(SAMPLE_TEXT);

        assertThat("Index does not match.", myStringBuilder.indexOf('L'), is(defaultStringBuilder.indexOf("L")));
        assertThat("Index does not match.", myStringBuilder.indexOf('.'), is(defaultStringBuilder.indexOf(".")));
    }

    @Test
    public void containsTest() {
        myStringBuilder = new MyStringBuilder();
        defaultStringBuilder = new StringBuilder();

        myStringBuilder.append(SAMPLE_TEXT);
        defaultStringBuilder.append(SAMPLE_TEXT);

        assertThat("Index does not match.", myStringBuilder.contains('?'), is(false));
        assertThat("Index does not match.", myStringBuilder.contains('U'), is(true));
        assertThat("Index does not match.", myStringBuilder.contains('o'), is(true));
    }

    private void appendCharacters(final int valueCount) {
        final Random random = new Random();
        final int[] integerArray = random.ints(valueCount, 32, 128).toArray();

        final long insertMyStartTime = System.nanoTime();

        for (final int index : integerArray) {
            myStringBuilder.append((char) index);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (final int index : integerArray) {
            defaultStringBuilder.append((char) index);
        }

        final long insertDefaultStopTime = System.nanoTime();

        log.info(
                "Time to insert {} characters into my string builder: {} vs default string builder: {}.",
                valueCount,
                (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
    }

    private void appendStrings(final int valueCount) {
        final List<String> uuidList =
                IntStream.range(0, valueCount)
                        .mapToObj(value -> UUID.randomUUID().toString())
                        .toList();

        final long insertMyStartTime = System.nanoTime();

        for (final String stringToAppend : uuidList) {
            myStringBuilder.append(stringToAppend);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (final String stringToAppend : uuidList) {
            defaultStringBuilder.append(stringToAppend);
        }

        final long insertDefaultStopTime = System.nanoTime();

        log.info(
                "Time to insert {} strings into my string builder: {} vs default string builder: {}.",
                valueCount,
                (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
    }
}
