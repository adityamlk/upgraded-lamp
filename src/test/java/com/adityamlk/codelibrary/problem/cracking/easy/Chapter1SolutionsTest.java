package com.adityamlk.codelibrary.problem.cracking.easy;

import org.junit.jupiter.api.Test;

import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter1Solutions.isPermutation;
import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter1Solutions.isPermutationOfPalindrome;
import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter1Solutions.isUnique;
import static com.adityamlk.codelibrary.problem.cracking.easy.Chapter1Solutions.stringURIConverter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Chapter1SolutionsTest {

    /*
     * Problem 1: Determine whether the provided string has all unique characters.
     */

    @Test
    public void isUniqueFirstTest() {
        final boolean isUniqueResult = isUnique("quick");

        assertThat("Result is incorrect.", isUniqueResult, is(true));
    }

    @Test
    public void isUniqueSecondTest() {
        final boolean isUniqueResult = isUnique("abba");

        assertThat("Result is incorrect.", isUniqueResult, is(false));
    }

    @Test
    public void isUniqueThirdTest() {
        final boolean isUniqueResult = isUnique("thequickbrownfoxjumpsoverthelazydog");

        assertThat("Result is incorrect.", isUniqueResult, is(false));
    }

    @Test
    public void isUniqueFourthTest() {
        final boolean isUniqueResult = isUnique("penzmyblackfoxwithdrugsDRUGS1234!@#$/.,");

        assertThat("Result is incorrect.", isUniqueResult, is(true));
    }

    /*
     * Problem 2: Given two strings, check whether one is a permutation of the other.
     */

    @Test
    public void isPermutationFirstTest() {
        final boolean isPermutationResult = isPermutation("racecar", "racecar");

        assertThat("Result is incorrect.", isPermutationResult, is(true));
    }

    @Test
    public void isPermutationSecondTest() {
        final boolean isPermutationResult = isPermutation("the earth quakes", "that queer shake");

        assertThat("Result is incorrect.", isPermutationResult, is(true));
    }

    @Test
    public void isPermutationThirdTest() {
        final boolean isPermutationResult = isPermutation("boolean", "Boolean");

        assertThat("Result is incorrect.", isPermutationResult, is(false));
    }

    @Test
    public void isPermutationFourthTest() {
        final boolean isPermutationResult = isPermutation("hello world_", "hello world ");

        assertThat("Result is incorrect.", isPermutationResult, is(false));
    }

    /*
     * Problem 3: Given two strings, check whether one is a permutation of the other.
     */

    @Test
    public void stringURIConverterFirstTest() {
        final String convertedStringResult = stringURIConverter("A bad day.    ", 10);

        assertThat("Result is incorrect.", convertedStringResult, is("A%20bad%20day."));
    }

    @Test
    public void stringURIConverterSecondTest() {
        final String convertedStringResult = stringURIConverter("Mr John Smith    ", 13);

        assertThat("Result is incorrect.", convertedStringResult, is("Mr%20John%20Smith"));
    }

    @Test
    public void stringURIConverterThirdTest() {
        final String convertedStringResult = stringURIConverter("            ", 4);

        assertThat("Result is incorrect.", convertedStringResult, is("%20%20%20%20"));
    }

    @Test
    public void stringURIConverterFourthTest() {
        final String convertedStringResult = stringURIConverter("              ", 4);

        assertThat("Result is incorrect.", convertedStringResult, is("%20%20%20%20"));
    }

    /*
     * Problem 4: Given a string, write a function to check if it is a permutation of a palindrome.
     */

    @Test
    public void isPermutationOfPalindromeFirstTest() {
        final boolean isPermutationResult = isPermutationOfPalindrome("Tact Coa");

        assertThat("Result is incorrect.", isPermutationResult, is(true));
    }

    @Test
    public void isPermutationOfPalindromeSecondTest() {
        final boolean isPermutationResult = isPermutationOfPalindrome(" AAA bb bb ");

        assertThat("Result is incorrect.", isPermutationResult, is(true));
    }

    @Test
    public void isPermutationOfPalindromeThirdTest() {
        final boolean isPermutationResult = isPermutationOfPalindrome("aaabbccc");

        assertThat("Result is incorrect.", isPermutationResult, is(false));
    }

    @Test
    public void isPermutationOfPalindromeFourthTest() {
        final boolean isPermutationResult = isPermutationOfPalindrome("racer");

        assertThat("Result is incorrect.", isPermutationResult, is(false));
    }
}
