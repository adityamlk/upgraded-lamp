package com.adityamlk.codelibrary.datastructure.tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Represents a Trie implementation for a dictionary of words. Tracks all nodes using a root node. Each node stores a
 * letter and a set of child nodes. All paths down the tree terminate to form words entered into a tree. Leaf nodes are
 * terminating points of words.
 * <p>
 * Insertion is O(K) for length of a word K.
 * Deletion is O(K) for length of a word K.
 * Search is O(K) for length of a word or prefix K.
 */
@Log4j2
@EqualsAndHashCode
public class MyPrefixTree {

    @NonNull
    private final TrieNode<Character> root;

    @NonNull
    private Integer wordCount;

    /**
     * Default Constructor.
     */
    public MyPrefixTree() {
        this.root = new TrieNode<>(' ', Maps.newHashMap(), false);
        this.wordCount = 0;
    }

    /**
     * Inserts the given word. Will return true if able to add the word, false if the word already exists.
     *
     * @param wordToInsert Sequence of characters to insert into the tree as a word.
     * @return True if able to add the word, false if word already exists.
     */
    public boolean insert(@NonNull final String wordToInsert) {
        final char[] characterArray = wordToInsert.toCharArray();
        TrieNode<Character> currentNode = root;

        // For each character, find the child node corresponding to it. If no node, then create new one, thus forming a
        // new path in the tree. Do so for all characters in the array.
        for (final Character characterToInsert : characterArray) {
            TrieNode<Character> childNode = currentNode.children.get(characterToInsert);

            if (null == childNode) {
                childNode = new TrieNode<>(characterToInsert, Maps.newHashMap(), false);
                currentNode.children.put(characterToInsert, childNode);
            }

            currentNode = childNode;
        }

        // If a word terminating with the same letter already exists in the tree, then return false. Otherwise, mark the
        // character as the end of the new word in the tree and increment the word count.
        if (currentNode.isTerminatingNode) {
            return false;
        } else {
            currentNode.isTerminatingNode = true;
            wordCount += 1;
        }

        return true;
    }

    /**
     * Removes the given word. Will return true if able to remove the word, false if the word does not exist.
     *
     * @param wordToRemove Sequence of characters to remove from the tree as a word.
     * @return True if able to remove the word, false if word does not exist.
     */
    public boolean remove(@NonNull final String wordToRemove) {
        // Retrieve the nodes in the path of the word in the form of a stack, last letter in the word at the top and the
        // root node at the bottom.
        final Stack<TrieNode<Character>> stackOfCharacters = getNodesFromCharacterPath(wordToRemove.toCharArray());

        // If the returned stack is null, then a path for the word does not exist and return false.
        if (null == stackOfCharacters) {
            return false;
        }

        final TrieNode<Character> nodeToUpdate = stackOfCharacters.peek();

        // If the final node in the path is not a terminating node/final letter of the word, then the path is not a word
        // and return false.
        if (!nodeToUpdate.isTerminatingNode) {
            return false;
        }

        // This is a path for the word, update the terminating node to indicate the character does not complete a word
        // and decrement the word count.
        nodeToUpdate.isTerminatingNode = false;
        wordCount -= 1;

        // Traverse through the stack and for each node check if it should be removed. If yes, then peek at the parent
        // and remove the reference to this node from the map of children. Loop until the root is remaining, but do not
        // process it; root is in the stack to update its children map only.
        //
        // For removal, the node must not have children and must not be a terminating node. This means another word does
        // not terminate at this node and this node is not part of another path.
        while (stackOfCharacters.size() > 1) {
            final TrieNode<Character> nodeToCheckForRemoval = stackOfCharacters.pop();

            if (nodeToCheckForRemoval.children.isEmpty() && !nodeToCheckForRemoval.isTerminatingNode) {
                final TrieNode<Character> parentToUpdate = stackOfCharacters.peek();
                parentToUpdate.children.remove(nodeToCheckForRemoval.value);
            }
        }

        return true;
    }

    /**
     * Checks for the given prefix in the tree.
     *
     * @param prefixToSearch Sequence of characters to check in the tree.
     * @return True if the string forms a path in the tree, false otherwise.
     */
    public boolean containsPrefix(@NonNull final String prefixToSearch) {
        final TrieNode<Character> nodeToCheck = getNodeFromCharacterPath(prefixToSearch.toCharArray());
        return null != nodeToCheck && !nodeToCheck.value.equals(root.value);
    }

    /**
     * Checks for the given word in the tree.
     *
     * @param wordToSearch Sequence of characters, that form a word, to check in the tree.
     * @return True if the string forms a path in the tree and terminates into a word, false otherwise.
     */
    public boolean containsWord(@NonNull final String wordToSearch) {
        final TrieNode<Character> nodeToCheck = getNodeFromCharacterPath(wordToSearch.toCharArray());
        return null != nodeToCheck && !nodeToCheck.value.equals(root.value) && nodeToCheck.isTerminatingNode;
    }

    /**
     * @return Number of words in the data structure.
     */
    public int getWordCount() {
        return wordCount;
    }

    /**
     * Returns a list of words in the tree that start with the provided prefix.
     *
     * @param prefixToSearch Sequence of characters that start words in the tree.
     * @return List of words in the tree that start with the provided prefix; empty if no words match; null if prefix
     * does not exist.
     */
    public List<String> getWordsForPrefix(@NonNull final String prefixToSearch) {
        final TrieNode<Character> nodeToCheck = getNodeFromCharacterPath(prefixToSearch.toCharArray());

        if (null == nodeToCheck || nodeToCheck.value.equals(root.value)) {
            return null;
        }

        final List<String> wordList = Lists.newLinkedList();
        addWords(nodeToCheck, prefixToSearch.substring(0, prefixToSearch.length() - 1), wordList);

        Collections.sort(wordList);
        return wordList;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns string version of the data structure. Uses brackets to identify start and end of collection. Separates
     * the values using comma and space.
     */
    @Override
    public String toString() {
        final List<String> wordList = Lists.newArrayListWithCapacity(wordCount);

        for (final TrieNode<Character> childNode : root.children.values()) {
            addWords(childNode, "", wordList);
        }

        Collections.sort(wordList);
        return wordList.toString();
    }

    /*
     * Recursive method that adds words that terminate as part of this node's children and paths. If this is a
     * terminating node, then add the word associated with this node. Either way, for each child of this node call this
     * method recursively.
     */
    private void addWords(final TrieNode<Character> nodeToProcess, final String wordPath, final List<String> wordList) {
        final String updatedWordPath = wordPath + nodeToProcess.value;

        if (nodeToProcess.isTerminatingNode) {
            wordList.add(updatedWordPath);
        }

        for (final TrieNode<Character> childNode : nodeToProcess.children.values()) {
            addWords(childNode, updatedWordPath, wordList);
        }
    }

    /*
     * Returns the node at the end of the provided character path.
     */
    private TrieNode<Character> getNodeFromCharacterPath(final char[] characterArray) {
        TrieNode<Character> currentNode = root;

        for (final Character characterToInsert : characterArray) {
            final TrieNode<Character> childNode = currentNode.children.get(characterToInsert);

            if (null == childNode) {
                return null;
            }

            currentNode = childNode;
        }

        return currentNode;
    }

    /*
     * Returns a stack of nodes that make up the provided character path. Pushes the root node first and then the rest
     * of the nodes while traversing through the characters. This means popping from the stack will return nodes with
     * the characters in reverse order.
     */
    private Stack<TrieNode<Character>> getNodesFromCharacterPath(final char[] characterArray) {
        final Stack<TrieNode<Character>> stackToReturn = new Stack<>();
        stackToReturn.push(root);
        TrieNode<Character> currentNode = root;

        for (final Character characterToInsert : characterArray) {
            final TrieNode<Character> childNode = currentNode.children.get(characterToInsert);

            if (null == childNode) {
                return null;
            }

            currentNode = childNode;
            stackToReturn.push(currentNode);
        }

        return stackToReturn;
    }

    /**
     * Node class that stores the associated value and has a map to the child nodes in a collection of nodes.
     *
     * @param <T> Generic data type supported by the Node.
     */
    @Data
    @Builder
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class TrieNode<T> {
        /*
         * Value stored in the node.
         */
        @NonNull
        private final T value;

        /*
         * Map of child nodes based on value.
         */
        @NonNull
        private Map<T, TrieNode<T>> children;

        /*
         * Indicates whether this node is the final node in a path. The node can still have children for other paths,
         * but a path can still end here.
         */
        private boolean isTerminatingNode;
    }
}
