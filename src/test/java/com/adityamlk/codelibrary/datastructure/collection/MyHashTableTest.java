package com.adityamlk.codelibrary.datastructure.collection;

import java.util.Hashtable;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyHashTableTest {

    private MyHashTable<String, Double> myHashTable;

    private Hashtable<String, Double> defaultHashTable;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(1000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void insertTenThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(10000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void insertHundredThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(100000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void insertMillionThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(1000000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void removeThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(1000, false);
        removeValues(1000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void removeTenThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(10000, false);
        removeValues(10000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void removeHundredThousandValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(100000, false);
        removeValues(100000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void removeMillionValuesTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(1000000, false);
        removeValues(1000000, true);

        assertThat("Size is incorrect.", myHashTable.getSize(), is(defaultHashTable.size()));
        assertThat("List is incorrect.", myHashTable.toString().length(), is(defaultHashTable.toString().length()));
    }

    @Test
    public void getTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(100000, false);

        assertThat("Value does not match.", myHashTable.get("5555"), is(defaultHashTable.get("5555")));
        assertThat("Value does not match.", myHashTable.get("1994"), is(defaultHashTable.get("1994")));
        assertThat("Value does not match.", myHashTable.get("0"), is(defaultHashTable.get("0")));
        assertThat("Value does not match.", myHashTable.get("99999"), is(defaultHashTable.get("99999")));
        assertThat("Value does not match.", myHashTable.get("67893"), is(defaultHashTable.get("67893")));

        removeValues(50000, false);

        assertThat("Value does not match.", myHashTable.get("5555"), is(defaultHashTable.get("5555")));
        assertThat("Value does not match.", myHashTable.get("1994"), is(defaultHashTable.get("1994")));
        assertThat("Value does not match.", myHashTable.get("0"), is(defaultHashTable.get("0")));
        assertThat("Value does not match.", myHashTable.get("99999"), is(defaultHashTable.get("99999")));
        assertThat("Value does not match.", myHashTable.get("67893"), is(defaultHashTable.get("67893")));
    }

    @Test
    public void containsTest() {
        myHashTable = new MyHashTable<>();
        defaultHashTable = new Hashtable<>(1000);

        insertValues(100000, false);

        assertThat("Result does not match.", myHashTable.contains("5555"), is(defaultHashTable.containsKey("5555")));
        assertThat("Result does not match.", myHashTable.contains("1994"), is(defaultHashTable.containsKey("1994")));
        assertThat("Result does not match.", myHashTable.contains("-1"), is(defaultHashTable.containsKey("-1")));
        assertThat("Result does not match.", myHashTable.contains("99999"), is(defaultHashTable.containsKey("99999")));
        assertThat("Result does not match.", myHashTable.contains("444444"), is(defaultHashTable.containsKey("444444")));

        removeValues(50000, false);

        assertThat("Result does not match.", myHashTable.contains("5555"), is(defaultHashTable.containsKey("5555")));
        assertThat("Result does not match.", myHashTable.contains("1994"), is(defaultHashTable.containsKey("1994")));
        assertThat("Result does not match.", myHashTable.contains("-1"), is(defaultHashTable.containsKey("-1")));
        assertThat("Result does not match.", myHashTable.contains("99999"), is(defaultHashTable.containsKey("99999")));
        assertThat("Result does not match.", myHashTable.contains("444444"), is(defaultHashTable.containsKey("444444")));
    }

    private void insertValues(final int valueCount, final boolean shouldLog) {
        final long insertMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myHashTable.insert(String.valueOf(i), (double) i);
        }

        final long insertMyStopTime = System.nanoTime();

        final long insertDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultHashTable.put(String.valueOf(i), (double) i);
        }

        final long insertDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to insert {} values into my hash table: {} vs default hash table: {}.",
                    valueCount,
                    (insertMyStopTime - insertMyStartTime) / (double) valueCount,
                    (insertDefaultStopTime - insertDefaultStartTime) / (double) valueCount);
        }
    }

    private void removeValues(final int valueCount, final boolean shouldLog) {
        final long removeMyStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            myHashTable.remove(String.valueOf(i));
        }

        final long removeMyStopTime = System.nanoTime();

        final long removeDefaultStartTime = System.nanoTime();

        for (int i = 0; i < valueCount; i++) {
            defaultHashTable.remove(String.valueOf(i));
        }

        final long removeDefaultStopTime = System.nanoTime();

        if (shouldLog) {
            log.info(
                    "Time to remove {} values from my hash table: {} vs default hash table: {}.",
                    valueCount,
                    (removeMyStopTime - removeMyStartTime) / (double) valueCount,
                    (removeDefaultStopTime - removeDefaultStartTime) / (double) valueCount);
        }
    }
}
