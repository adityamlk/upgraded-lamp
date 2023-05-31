package com.adityamlk.codelibrary.datastructure.tree;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class MyPrefixTreeTest {

    private MyPrefixTree myPrefixTree;

    @AfterAll
    public static void cleanup() {
        System.gc();
    }

    @Test
    public void insertBasicTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.containsWord("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hack"), is(true));
    }

    @Test
    public void insertAdvancedTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(false));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.containsWord("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hulk"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("honey"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("worked"), is(false));
    }

    @Test
    public void removeBasicTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.remove("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("watch"), is(true));

        log.info("Prefix tree after removal: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(3));

        assertThat("Result is incorrect.", myPrefixTree.containsWord("hello"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("helios"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("world"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("watch"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hack"), is(true));
    }

    @Test
    public void removeAdvancedTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.remove("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("hello"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.remove("helios"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.remove("banana"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.remove("apple"), is(false));

        log.info("Prefix tree after removal: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(3));

        assertThat("Result is incorrect.", myPrefixTree.containsWord("hello"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("helios"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("world"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("watch"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("banana"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsWord("apple"), is(false));
    }

    @Test
    public void containsPrefixBasicTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hel"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("w"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("heck"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hal"), is(false));
    }

    @Test
    public void containsPrefixAdvancedTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hel"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("w"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("heck"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hal"), is(false));

        assertThat("Result is incorrect.", myPrefixTree.remove("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.remove("watch"), is(true));

        log.info("Prefix tree after removal: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(3));

        assertThat("Result is incorrect.", myPrefixTree.insert("hippo"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hints"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(5));

        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hello"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("helios"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("world"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("watch"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hack"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hel"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("w"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("heck"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hal"), is(false));
        assertThat("Result is incorrect.", myPrefixTree.containsPrefix("hi"), is(true));
    }

    @Test
    public void getWordsFromPrefixTest() {
        myPrefixTree = new MyPrefixTree();

        assertThat("Result is incorrect.", myPrefixTree.insert("hello"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("helios"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("world"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("watch"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hell"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hacking"), is(true));
        assertThat("Result is incorrect.", myPrefixTree.insert("hack"), is(true));

        log.info("Prefix tree after insertion: {}.", myPrefixTree.toString());

        assertThat("Size is incorrect.", myPrefixTree.getWordCount(), is(7));

        assertThat("List is incorrect.", myPrefixTree.getWordsForPrefix("hell"), is(Lists.newArrayList("hell", "hello")));
        assertThat("List is incorrect.", myPrefixTree.getWordsForPrefix("w"), is(Lists.newArrayList("watch", "world")));
        assertThat("List is incorrect.", myPrefixTree.getWordsForPrefix("hack"), is(Lists.newArrayList("hack", "hacking")));
    }
}
