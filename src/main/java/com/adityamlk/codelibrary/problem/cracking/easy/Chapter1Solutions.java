package com.adityamlk.codelibrary.problem.cracking.easy;

import lombok.NonNull;

/**
 * Covers problems from Chapter 1: Arrays and Strings.
 */
public class Chapter1Solutions {

    /*
     * Problem 1: Determine whether the provided string has all unique characters.
     */

    /**
     * Use a character array to keep track of characters in the character set that have shown up in the string. If no
     * characters repeat, then the string has unique characters.
     * <p>
     * Alternatives that take up more space are using a set, storing the characters in the set, and making sure the
     * lengths are the same.
     * <p>
     * Alternatives that take up no additional space would require either looping through the string twice or using a
     * sorting algorithm. Both would be slower.
     * <p>
     * Time Complexity: O(min(C, N)), where C is the character set being used; as soon as a duplicate hits, then the
     * solution stops. Minimum of the two because the length of the string can be less than the character set.
     * Alternatives would be O(N), O(N^2), and O(N*log(N)) for set approach, nested loop approach, and sorting approach,
     * respectively.
     * <p>
     * Space Complexity: O(C), where C is the character set being used; since only C characters can be marked this is
     * additional space used. Alternatives would be O(N) for set approach and possibly sorting approach.
     *
     * @param inputString String to check for unique characters.
     * @return True if the string has unique characters, false otherwise.
     */
    public static boolean isUnique(@NonNull final String inputString) {
        final boolean[] charFlagArray = new boolean[128];
        boolean isUnique = true;

        for (int index = 0; index < inputString.length(); index++) {
            final int characterIndex = inputString.charAt(index);

            if (charFlagArray[characterIndex]) {
                isUnique = false;
                break;
            } else {
                charFlagArray[characterIndex] = true;
            }
        }

        return isUnique;
    }

    /*
     * Problem 2: Given two strings, check whether one is a permutation of the other.
     */

    /**
     * Use a character array to keep track of count of characters in the character set that have shown up in the
     * original string. Then, for each character in string to check, see if that character has a count. If so, then
     * subtract one from the count and continue to the end.
     * <p>
     * Alternatives that take up more space are using a hash table, storing the characters in the set with their counts,
     * subtracting the counts with the string to check, and ensuring none of the counts are zero when searching.
     * <p>
     * Alternatives that take up no additional space would require using a sorting algorithm on both strings and checking
     * if they are equal. This would be slower.
     * <p>
     * Time Complexity: O(N); each string requires a single iteration and storing the character counts is constant.
     * Alternatives would be O(N) and O(N*log(N)) for hash table approach and sorting approach, respectively.
     * <p>
     * Space Complexity: O(C), where C is the character set being used; since only C characters can be incremented this
     * is additional space used. Alternatives would be O(N) for hash table approach and possibly sorting approach.
     *
     * @param originalString String to check against for permutation.
     * @param stringToCheck  String to check for permutation.
     * @return True if the string to check is a permutation of the original string, false otherwise.
     */
    public static boolean isPermutation(@NonNull final String originalString, @NonNull final String stringToCheck) {
        final int[] charCountArray = new int[128];
        boolean isPermutation = true;

        if (originalString.length() != stringToCheck.length()) {
            isPermutation = false;
        } else {
            for (int index = 0; index < originalString.length(); index++) {
                final int characterIndex = originalString.charAt(index);
                charCountArray[characterIndex] += 1;
            }

            for (int index = 0; index < stringToCheck.length(); index++) {
                final int characterIndex = stringToCheck.charAt(index);

                if (0 == charCountArray[characterIndex]) {
                    isPermutation = false;
                    break;
                } else {
                    charCountArray[characterIndex] -= 1;
                }
            }
        }

        return isPermutation;
    }

    /*
     * Problem 3: Replace all spaces in a string with `%20` to convert it into URL format. Assume string has trailing
     * spaces to support additional characters.
     */

    /**
     * Use the provided string as a character array to determine the number of spaces and work backwards to update the
     * array in-place and transform the string.
     * <p>
     * Alternatives that take up more space include using a string builder; while traversing the string copy each letter
     * 1-to-1 that is not a space and inject %20 if it is a space instead.
     * <p>
     * Time Complexity: O(N); iterate through the string once by working backwards one character at a time. Alternatives
     * would be O(N) for the string builder approach.
     * <p>
     * Space Complexity: O(1); additional space is not needed outside the original string. Alternatives would be O(N)
     * for string builder approach.
     *
     * @param stringToConvert String with spaces to convert to URI format.
     * @param stringLength    Length of string without the count of trailing spaces included.
     * @return Modified string converted to URI.
     */
    public static String stringURIConverter(@NonNull final String stringToConvert, final int stringLength) {
        final char[] characterArray = stringToConvert.toCharArray();
        int numberOfSpaces = 0;

        for (int index = 0; index < stringLength; index++) {
            if (' ' == characterArray[index]) {
                numberOfSpaces += 1;
            }
        }

        final int totalCharacters = stringLength + numberOfSpaces * 2;

        if (totalCharacters < characterArray.length) {
            characterArray[totalCharacters] = '\0';
        }

        int fillIndex = totalCharacters - 1;

        for (int traverseIndex = stringLength - 1; traverseIndex >= 0; traverseIndex--) {
            final char currentChar = characterArray[traverseIndex];
            if (' ' == currentChar) {
                characterArray[fillIndex--] = '0';
                characterArray[fillIndex--] = '2';
                characterArray[fillIndex--] = '%';
            } else {
                characterArray[fillIndex--] = currentChar;
            }
        }

        return String.valueOf(characterArray, 0, totalCharacters);
    }

    /*
     * Problem 4: Given a string, write a function to check if it is a permutation of a palindrome.
     */

    /**
     * Use a character array to keep track of characters in the character set that have shown up in the string. Use a
     * counter to track how many letters show up an odd number of times. Each time a letter shows up flip the index in
     * the array; if it shows up a second time, then decrement the odd count, otherwise increment the count. At the end,
     * check whether the count is 0 or 1.
     * <p>
     * Time Complexity: O(N); have to go through the entire array once to get all the counts.
     * <p>
     * Space Complexity: O(C), where C is the character set being used; since only C characters can be marked this is
     * additional space used.
     *
     * @param stringToCheck String to check for unique characters.
     * @return True if the string to check is a permutation of a palindrome, false otherwise.
     */
    public static boolean isPermutationOfPalindrome(@NonNull final String stringToCheck) {
        final boolean[] charFlagArray = new boolean[128];
        int oddLetterCount = 0;
        boolean isPermutationOfPalindrome = false;

        for (int index = 0; index < stringToCheck.length(); index++) {
            final char characterToCheck = stringToCheck.charAt(index);

            if (' ' != characterToCheck) {
                final char lowercaseCharacter = Character.toLowerCase(characterToCheck);
                boolean isCharacterInArray = charFlagArray[lowercaseCharacter];

                if (isCharacterInArray) {
                    oddLetterCount -= 1;
                } else {
                    oddLetterCount += 1;
                }

                charFlagArray[lowercaseCharacter] = !isCharacterInArray;
            }
        }

        if (1 >= oddLetterCount) {
            isPermutationOfPalindrome = true;
        }

        return isPermutationOfPalindrome;
    }
}
