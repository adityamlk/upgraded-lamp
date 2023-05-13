package com.adityamlk.codelibrary.datastructure.collection;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a HashTable implementation. Maintains capacity, auto-resizes by configured factor to accommodate
 * additional elements. Resizes based on a configured load factor. This will help minimize hash collisions.
 * <p>
 * Hashes entries based on the hash code of the key. If there is a collision, then maintains a list of entries with
 * the same bucket within the table.
 * <p>
 * Insertion is O(1). Either the insertion is straightforward, where the bucket is empty or has limited size, or it
 * requires resizing, which gets amortized across all the other inserts.
 * Deletion is O(1). Deletes based on the bucket that the provided key belongs in.
 * Search is O(1). Searches based on the bucket that the provided key belongs in.
 *
 * @param <K> Generic data type that represents keys in the hash table.
 * @param <V> Generic data type that represents values in the hash table.
 */
@Log4j2
@EqualsAndHashCode
public class MyHashTable<K, V> {

    private static final double DEFAULT_LOAD_FACTOR_LIMIT = 0.75;

    private static final int DEFAULT_INITIAL_CAPACITY = 1000;

    private static final int DEFAULT_RESIZE_FACTOR = 4;

    /*
     * The number of entries stored in the hash table. Once the size gets to a specific point, the table should be
     * resized to minimize hash collisions and reduce the size of the list in each entry.
     */
    @NonNull
    private Integer size;

    /*
     * Stores the values inserted into the data structure. Grows over time to accommodate additional values. Each entry
     * represents a bucket in the hash table. If more than one key-value pair needs to be written to a bucket, then
     * tracks them all using a linked list.
     */
    private LinkedList<TableEntry<K, V>>[] internalCollection;

    /**
     * Default constructor.
     */
    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this.size = 0;
        this.internalCollection = new LinkedList[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Inserts the given key and value pair. Will resize the collection based on the configured resize factor.
     *
     * @param keyToInsert   {@link K}
     * @param valueToInsert {@link V}
     */
    public void insert(@NonNull final K keyToInsert, @NonNull final V valueToInsert) {
        if (shouldResizeInternalCollection()) {
            resizeCollection();
        }

        final TableEntry<K, V> entryToInsert =
                TableEntry.<K, V>builder()
                        .key(keyToInsert)
                        .value(valueToInsert)
                        .entryHashCode(keyToInsert.hashCode())
                        .build();

        insertIntoInternalCollection(entryToInsert);
        size += 1;
    }

    /**
     * Retrieves the value for the provided key.
     *
     * @param keyForRetrieval {@link K}
     * @return Value associated with the key, or null if the key does not exist.
     */
    public V get(@NonNull final K keyForRetrieval) {
        final int bucket = getBucketForKey(keyForRetrieval);
        V valueToReturn = null;

        final LinkedList<TableEntry<K, V>> bucketList = internalCollection[bucket];

        // If the bucket has at least one entry, then traverse and find the matching key and store the value.
        if (shouldTraverseBucketList(bucketList)) {
            for (final TableEntry<K, V> tableEntry : bucketList) {
                if (keyForRetrieval.equals(tableEntry.key)) {
                    valueToReturn = tableEntry.value;
                    break;
                }
            }
        }

        return valueToReturn;
    }

    /**
     * Removes the value for the provided key.
     *
     * @param keyForRemoval {@link K}
     * @return Value associated with the key, or null if the key does not exist.
     */
    public V remove(@NonNull final K keyForRemoval) {
        final int bucket = getBucketForKey(keyForRemoval);
        V valueRemoved = null;
        TableEntry<K, V> tableEntryToRemove = null;

        final LinkedList<TableEntry<K, V>> bucketList = internalCollection[bucket];

        // If the bucket has at least one entry, then traverse and find the matching key and store the entry.
        if (shouldTraverseBucketList(bucketList)) {
            for (final TableEntry<K, V> tableEntry : bucketList) {
                if (keyForRemoval.equals(tableEntry.key)) {
                    tableEntryToRemove = tableEntry;
                    break;
                }
            }
        }

        if (null != tableEntryToRemove) {
            valueRemoved = tableEntryToRemove.value;
            bucketList.remove(tableEntryToRemove);
            size -= 1;

            if (bucketList.isEmpty()) {
                internalCollection[bucket] = null;
            }
        }

        return valueRemoved;
    }

    /**
     * Determines whether a value exists for the provided key.
     *
     * @param keyToSearch {@link K}
     * @return True if the key exists in the hash table, false otherwise.
     */
    public boolean contains(@NonNull final K keyToSearch) {
        final V valueRetrieved = get(keyToSearch);

        return null != valueRetrieved;
    }

    /**
     * @return Number of values in the data structure.
     */
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the data structure. Uses curly braces to identify start and end of collection.
     * Separates the entries using comma and space. Separates the keys and values using '='.
     */
    @Override
    public String toString() {
        if (0 == size) {
            return "{}";
        }

        int numEntriesProcessed = 0;
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int bucket = 0; bucket < getInternalCollectionSize(); bucket++) {
            final LinkedList<TableEntry<K, V>> bucketList = internalCollection[bucket];

            if (shouldTraverseBucketList(bucketList)) {
                // For each entry in the bucket list add the key and value pairs.
                for (final TableEntry<K, V> tableEntry : bucketList) {
                    stringBuilder.append(tableEntry.key);
                    stringBuilder.append('=');
                    stringBuilder.append(tableEntry.value);

                    numEntriesProcessed += 1;

                    // If there is still one entry to process, then use comma and space to separate the strings.
                    if (size - numEntriesProcessed != 0) {
                        stringBuilder.append(",").append(" ");
                    }
                }
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    /*
     * Returns bucket for the key provided.
     */
    private int getBucketForKey(final K key) {
        return getBucketForHashCode(key.hashCode());
    }

    /*
     * Returns bucket for the hash code provided. The bucket is the hash code % capacity to ensure the key fits in the
     * internal collection even if it leads to hash collisions.
     */
    private int getBucketForHashCode(final int keyHashCode) {
        return keyHashCode % getInternalCollectionSize();
    }

    /*
     * Retrieves the length of the internal collection.
     */
    private int getInternalCollectionSize() {
        return internalCollection.length;
    }

    /*
     * Determines whether to resize the collection. If the load factor, which is the number of entries in the table
     * divided by the total size of the internal collection, equals or exceeds the limit, then should resize.
     */
    private boolean shouldResizeInternalCollection() {
        final double loadFactor = (double) size / getInternalCollectionSize();

        return loadFactor >= DEFAULT_LOAD_FACTOR_LIMIT;
    }

    /*
     * Resizes the internal collection.
     *
     * Uses a temporary array to track the values, creates a new array with new size using the configured resize factor,
     * and copies the entries from the temporary array into the new array using each entry's hash code value.
     */
    @SuppressWarnings("unchecked")
    private void resizeCollection() {
        final int currentCollectionSize = getInternalCollectionSize();

        // Point a temporary variable to the current collection and create a new collection with resize factor.
        final LinkedList<TableEntry<K, V>>[] tempArray = internalCollection;
        internalCollection = new LinkedList[currentCollectionSize * DEFAULT_RESIZE_FACTOR];

        // Go through the buckets in the old collection and transfer the entries to the new internal collection.
        for (final LinkedList<TableEntry<K, V>> bucketList : tempArray) {
            if (shouldTraverseBucketList(bucketList)) {
                transferBucketList(bucketList);
            }
        }
    }

    /*
     * Copies the entries in a bucket to the internal collection. The goal is to move all entries to their new bucket in
     * the internal collection.
     */
    private void transferBucketList(final LinkedList<TableEntry<K, V>> bucketListToTransfer) {
        for (final TableEntry<K, V> tableEntry : bucketListToTransfer) {
            insertIntoInternalCollection(tableEntry);
        }
    }

    /*
     * Inserts the provided table entry into the internal collection. Does this by retrieving the bucket based on the
     * entry hash code the is stored. If a bucket list does not exist, then creates one. Either way, adds the entry to
     * the bucket list.
     */
    private void insertIntoInternalCollection(final TableEntry<K, V> tableEntry) {
        final int entryHashCode = tableEntry.entryHashCode;
        final int bucket = getBucketForHashCode(entryHashCode);

        // If the bucket is untouched, then set up a new list for the entry.
        if (null == internalCollection[bucket]) {
            internalCollection[bucket] = new LinkedList<>();
        }

        // Add the entry to the list, whether it was empty or had existing values.
        internalCollection[bucket].add(tableEntry);
    }

    /*
     * Determines whether to traverse the bucket list provided. If not null and not empty, then should traverse.
     */
    private boolean shouldTraverseBucketList(final List<TableEntry<K, V>> bucketList) {
        return null != bucketList && !bucketList.isEmpty();
    }

    /**
     * Class that stores the key-value pair for a hash table. Also tracks the hash code used for storing the entry.
     *
     * @param <K> Generic data type that represents the key in the table entry.
     * @param <V> Generic data type that represents the value in the table entry.
     */
    @Data
    @Builder
    @AllArgsConstructor
    public static class TableEntry<K, V> {
        /*
         * Key stored in the table entry.
         */
        @NonNull
        private final K key;

        /*
         * Value stored in the table entry.
         */
        @NonNull
        private final V value;

        /*
         * Calculated hash code for the entry. Likely based on the key specified, but could be a custom value.
         */
        @NonNull
        private final Integer entryHashCode;
    }
}
