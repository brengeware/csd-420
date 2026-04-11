/*
Brennan Cheatwood
CSD420 - Advanced Java
Assignment 4.2
4/11/26

* This program tests how long it takes to traverse a LinkedList two ways:
 * using an Iterator vs using get(index). I tested with 50,000 and 500,000
 * integers to see how the difference scales.
 *
 * Results on my machine:
 *   50,000 elements  -- iterator: ~1ms,    get(index): ~614ms
 *   500,000 elements -- iterator: ~3ms,    get(index): skipped (too slow)
 *   (UNSKIPPED) 500,000 elements -- iterator 4ms,       get(index): 71,151ms (sum 124,999,750,000)
 *
 * Why is get(index) so much slower?
 * A LinkedList doesn't store elements in an array, so it can't jump directly
 * to an index. Every call to get(i) starts at the beginning and walks node
 * by node until it reaches position i. So for index 0 it does 1 step, for
 * index 1 it does 2 steps, and so on -- that adds up to roughly n*n/2 total
 * steps for the whole loop, which gets bad fast.
 *
 * The iterator just keeps a pointer to the current node and moves it forward
 * one step at a time, so the whole traversal is just n steps total.
 *
 * Going from 50k to 500k (10x more data), the iterator time barely changed,
 * but get(index) would have taken roughly 100x longer -- that's the difference
 * between O(n) and O(n^2). For 500k I skipped the full get(index) test and
 * just timed the first 5,000 calls to show how slow each step gets.
 * 
*/

import java.util.LinkedList;
import java.util.Iterator;

public class linkedListBenchmark {
    
    //fills a LinkedList with ints from 0 to size-1
    private static LinkedList<Integer> buildList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;

    }

    //traverses the list with an iterator, returns sum
    private static long traverseWithIterator(LinkedList<Integer> list) {
        long sum = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            sum += it.next();
        }
        return sum;
    }

    // traverses the list using get(index), returns the sum
    //*this is really slow on large lists*
    private static long traverseWithGetIndex(LinkedList<Integer> list) {
        long sum = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    //runs both traversals on a list of given size and prints the timings
    private static void runBenchmark(int size, boolean runGetIndex) {
        System.out.println("----------------------------");
        System.out.printf("List size: %,d%n", size);
        System.out.println("----------------------------");

        LinkedList<Integer> list = buildList(size);

        long start = System.currentTimeMillis();
        long sumIter = traverseWithIterator(list);
        long iterTime = System.currentTimeMillis() - start;
        System.out.printf("   Iterator:   %,d ms (sum = %,d)%n", iterTime, sumIter);


        if (runGetIndex) {
            start = System.currentTimeMillis();
            long sumGet = traverseWithGetIndex(list);
            long getTime = System.currentTimeMillis() - start;
            System.out.printf("  get(index): %,d ms (sum = %,d)%n", getTime, sumGet);

            if (sumIter != sumGet) {
                System.out.println("ERROR: Sums don't match!");
            } else {
                System.out.printf(" get(index) was ~%dx slower%n",
                    getTime / Math.max(iterTime, 1));
            }
        } else {
            System.out.println("get(index): skipped - would take way too long");
        }

        System.out.println();
    }

    //basic tests to make sure both methods are working
    private static void runTests() {
        System.out.println("\n\nRunning Tests...\n\n");
        int passed = 0;
        int failed = 0;

        //empty list should return 0
        {
            LinkedList<Integer> list = buildList(0);
            if (traverseWithIterator(list) == 0 && traverseWithGetIndex(list) == 0) {
                System.out.println("   PASS: empty list");
                passed++;
            } else {
                System.out.println("   FAILED: empty list");
                failed++;
            }
        }

        //single element (value 0) should return 0
        {
            LinkedList<Integer> list = buildList(1);
            if (traverseWithIterator(list) == 0 && traverseWithGetIndex(list) == 0) {
                System.out.println("   PASS: single element");
                passed++;
            } else {
                System.out.println("   FAIL: single element");
                failed++;
            }
        }

        // 0+1+2+...+9=45
        {
            LinkedList<Integer> list = buildList(10);
            long expected = 45;
            if (traverseWithIterator(list) == expected && traverseWithGetIndex(list) == expected) {
                System.out.println("   PASS: 10 elements, sum=45");
                passed++;
            } else {
                System.out.println("   FAIL: 10 elements");
                failed++;
            }
        }

        // 0+1+...+999 = 499500
        {
            LinkedList<Integer> list = buildList(1000);
            long expected = 499_500;
            if (traverseWithIterator(list) == expected && traverseWithGetIndex(list) == expected) {
                System.out.println("   PASS: 1,000 elements, sum=499,500");
                passed++;
            } else {
                System.out.println("   FAIL: 1,000 elements");
                failed++;
            }
        }

        //both methods should agree on the same answer
        {
            LinkedList<Integer> list = buildList(5_000);
            long sumIter = traverseWithIterator(list);
            long sumGet = traverseWithGetIndex(list);
            if (sumIter == sumGet) {
                System.out.printf("   PASS: 5,000 elements, both methods agree (%,d)%n", sumIter);
                passed++;
            } else {
                System.out.println("   FAIL: 5,000 elements, methods disagree");
                failed++;
            }
        }

        //traversal shouldnt change the size of the list
        {
            LinkedList<Integer> list = buildList(200);
            traverseWithIterator(list);
            traverseWithGetIndex(list);
            if (list.size() == 200) {
                System.out.println("   PASS: list size unchanged after traversal");
                passed++;
            } else {
                System.out.println("   FAIL: list size changed!");
                failed++;
            }
        }

        System.out.printf("%nResults: %d passed, %d failed%n%n", passed, failed);
    }

    public static void main(String[] args) {

        runTests();

        //test with 50,000 -- get(index) is slow here but still finishes
        runBenchmark(50_000, true);

        //test with 500,000 |  skips get(index) for the full run
        //since it would take WAY too long. Instead, i'll run time just the first 5,000
        //calls below to show how much slower it gets per step.
        runBenchmark(500_000, false);

        System.out.println("Partial get(index) test on 500,000-element list (first 5,000 calls):");
        LinkedList<Integer> bigList = buildList(500_000);
        long start = System.currentTimeMillis();
        long partialSum = 0;
        for (int i = 0; i < 5_000; i++) {
            partialSum += bigList.get(i);
        }
        long partialTime = System.currentTimeMillis() - start;
        System.out.printf("   5,000 get() calls took %,d ms on a 500k list%n", partialTime);
        System.out.printf("   Extrapolated full traversal: ~%,d seconds%n", (partialTime * 100) / 1000);
        System.out.printf("   (partial sum: %,d)%n", partialSum);
    }

}
