package com.adityamlk.codelibrary.problem.cracking.easy;

/**
 * Covers problems from Chapter 5: Bit Manipulation.
 */
public class Chapter5Solutions {

    /*
     * Insert one number into another using the specified indices.
     */

    /**
     * Uses the provided integers to insert one number into another using bit manipulation.
     * <p>
     * First, this constructs a mask that will make room for the bits in the number to update. It does this by using the
     * high and low bits provided. It constructs the left side of the mask by negating 0 and shifting until ones are to
     * the left of the high bit. It then shifts 1 to the left until it reaches the low bit and subtracts one to ensure
     * ones are to the right of the low bit. It then combines the two sides into a single mask.
     * <p>
     * Second, this modifies the number to insert so that the least significant digit is in-line with the low bit to
     * replace.
     * <p>
     * Third, this performs an AND between the number to update and the mask. This makes sure there are 0s when inserting
     * the bits for the number to insert.
     * <p>
     * Lastly, this performs an OR between the two modified numbers to get the final result.
     *
     * @param numToUpdate Number whose bits will be updated using the rest of the values.
     * @param numToInsert Number whose bits will be inserted using the rest of the values.
     * @param bitLow      Number that represents the least significant bit that will be updated in the range.
     * @param bitHigh     Number that represents the most significant bit that will be updated in the range.
     * @return Number that is the result of inserting one number into another through bit manipulation.
     */
    public static int insertNumber(final int numToUpdate, final int numToInsert, final int bitLow, final int bitHigh) {
        final int leftMask = ~0 << (bitHigh + 1);
        final int rightMask = ((1 << bitLow) - 1);
        final int numToInsertMask = leftMask | rightMask;

        final int modifiedNumToInsert = (numToInsert << bitLow);
        final int modifiedNumToUpdate = numToUpdate & numToInsertMask;

        return modifiedNumToUpdate | modifiedNumToInsert;
    }
}
