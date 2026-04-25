import java.util.Arrays;
import java.util.Comparator;

/**
 * BubbleSortTest.java
 *
 * Test class for BubbleSort.java.
 * Tests both sort methods across several different scenarios
 * to make sure they work correctly, including some edge cases.
 *
 * Each test prints PASS or FAIL with details so it's easy to see
 * what went wrong if something breaks.
 *
 * @author Brennan Cheatwood
 * CSD420 - Advanced Java
 * Assignment 6.2
 * 4/25/26
 */
public class BubbleSortTest {

    // Simple counters to track overall results
    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("         BubbleSort Test Suite              ");
        System.out.println("============================================\n");

        // -------------------------------------------------------
        // GROUP 1: Comparable tests (natural / built-in ordering)
        // -------------------------------------------------------
        System.out.println("--- Comparable Interface Tests ---\n");

        // Test 1: Basic integer sort
        Integer[] intArray = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        BubbleSort.bubbleSort(intArray);
        verify("Integer array - ascending order (Comparable)",
                intArray, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});

        //Test 2: Already sorted - should stay the same, no unnecessary swaps
        Integer[] alreadySorted = {1, 2, 3, 4, 5};
        BubbleSort.bubbleSort(alreadySorted);
        verify("Already sorted array (Comparable)",
                alreadySorted, new Integer[]{1, 2, 3, 4, 5});

        // Test 3: Reverse sorted - worst case for bubble sort (most swaps needed)
        Integer[] reversed = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        BubbleSort.bubbleSort(reversed);
        verify("Reverse sorted array - worst case (Comparable)",
                reversed, new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});

        // Test 4: Single element - nothing to sort, should not crash
        Integer[] single = {42};
        BubbleSort.bubbleSort(single);
        verify("Single element array (Comparable)",
                single, new Integer[]{42});

        // Test 5: Array with duplicate values
        Integer[] dupes = {4, 2, 4, 1, 2, 3};
        BubbleSort.bubbleSort(dupes);
        verify("Array with duplicate values (Comparable)",
                dupes, new Integer[]{1, 2, 2, 3, 4, 4});

        //Test 6: String array - sorted alphabetically (A-Z by default)
        String[] strArray = {"banana", "apple", "date", "cherry"};
        BubbleSort.bubbleSort(strArray);
        verify("String array - alphabetical order (Comparable)",
                strArray, new String[]{"apple", "banana", "cherry", "date"});

        // Test 7: Double array
        Double[] doubleArray = {3.14, 2.71, 1.41, 1.73, 0.57};
        BubbleSort.bubbleSort(doubleArray);
        verify("Double array - ascending order (Comparable)",
                doubleArray, new Double[]{0.57, 1.41, 1.73, 2.71, 3.14});

        // -------------------------------------------------------
        // GROUP 2: Comparator tests (custom ordering)
        // -------------------------------------------------------
        System.out.println("\n--- Comparator Interface Tests ---\n");

        // Test 8: Sort integers in reverse order using a lambda comparator
        // Lambda (a, b) -> b - a flips the normal comparison so bigger comes first
        Integer[] revInts = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        BubbleSort.bubbleSort(revInts, (a, b) -> b - a);
        verify("Integer array - descending order (Comparator lambda)",
                revInts, new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0});

        // Test 9: Sort strings by length, shortest to longest
        // Comparator.comparingInt lets us pick which property to compare by
        String[] byLength = {"banana", "fig", "apple", "kiwi", "date"};
        BubbleSort.bubbleSort(byLength, Comparator.comparingInt(String::length));
        verify("String array - sorted by length (Comparator)",
                byLength, new String[]{"fig", "kiwi", "date", "apple", "banana"});

        // Test 10: Sort strings in reverse alphabetical order (Z-A)
        String[] revAlpha = {"banana", "apple", "date", "cherry"};
        BubbleSort.bubbleSort(revAlpha, Comparator.reverseOrder());
        verify("String array - reverse alphabetical (Comparator)",
                revAlpha, new String[]{"date", "cherry", "banana", "apple"});

        // Test 11: Sort strings by length descending (longest to shortest)
        String[] longFirst = {"cat", "elephant", "ox", "giraffe"};
        BubbleSort.bubbleSort(longFirst, Comparator.comparingInt(String::length).reversed());
        verify("String array - sorted by length descending (Comparator)",
                longFirst, new String[]{"elephant", "giraffe", "cat", "ox"});

        // Test 12: Single element with Comparator - should not crash
        Double[] singleDouble = {9.99};
        BubbleSort.bubbleSort(singleDouble, Comparator.naturalOrder());
        verify("Single element with Comparator",
                singleDouble, new Double[]{9.99});

        // -------------------------------------------------------
        // SUMMARY
        // -------------------------------------------------------
        System.out.println("============================================");
        System.out.println("  Tests Passed: " + passed);
        System.out.println("  Tests Failed: " + failed);
        System.out.println("  " + (failed == 0 ? "All tests passed!" : "Some tests FAILED. See details above."));
        System.out.println("============================================");
    }

    /**
     * Compares the sorted result to the expected array and prints PASS or FAIL.
     * Using Arrays.equals() because == won't work for object array comparison.
     *
     * @param testName label shown in the output
     * @param result   the array after sorting
     * @param expected what we expected it to look like
     */
    static <E> void verify(String testName, E[] result, E[] expected) {
        if (Arrays.equals(result, expected)) {
            System.out.println("[PASS] " + testName);
            passed++;
        } else {
            System.out.println("[FAIL] " + testName);
            System.out.println("       Expected : " + Arrays.toString(expected));
            System.out.println("       Got      : " + Arrays.toString(result));
            failed++;
        }
    }
}