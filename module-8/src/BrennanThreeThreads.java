//Brennan Cheatwood
//CSD420
//5/9/26
//BrennanThreeThreads.java
// Uses three concurrent threads to generate and display:
// Thread 1: random letters (a-z, A-Z)
// Thread 2: random digits (0-9)
// Thread 3: random symbols (!, @, #, $, %, &, *)

//Characters are printed interleaved as they are generated,
//not grouped by a thread.


import java.util.Random;


public class BrennanThreeThreads {
    //Shared state
    //Total characters each thread must produce
    private static final int TARGET = 10_000;

    private static final Object PRINT_LOCK = new Object();

    //THREAD 1 random letters
    static class LetterThread extends Thread {

        private final Random rng;
        private int count = 0; // characters produced so far

        LetterThread() {
            super("LetterThread");
            this.rng = new Random();
        }

        //Returns number of characters this thread produced
        public int getCount() { return count; }

        @Override
        public void run() {
            //string pool = 52 characters a-z + A-Z
            String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

            while (count < TARGET) {
                char ch = pool.charAt(rng.nextInt(pool.length()));
                synchronized (PRINT_LOCK) {
                    System.out.print(ch);
                }
                count++;
            }
            synchronized (PRINT_LOCK) {
                System.out.println(); // newline after thread finishes
            }
        }

    }

    //THREAD 2 - Random digits
    static class DigitThread extends Thread {

        private final Random rng;
        private int count = 0;

        DigitThread() {
            super("DigitThread");
            this.rng = new Random();
        }

        public int getCount() {
            return count;
        }

        @Override
        public void run() {
            while (count < TARGET) {
                char ch = (char) ('0' + rng.nextInt(10));
                synchronized (PRINT_LOCK) {
                    System.out.print(ch);
                }
                count++;
            }
            synchronized (PRINT_LOCK) {
                System.out.println(); // newline after thread finishes
            }
        }
    }

    //THREAD 3 - random special characters
    static class SymbolThread extends Thread {

        private final Random rng;
        private int count = 0;

        SymbolThread() {
            super("SymbolThread");
            this.rng = new Random();
        }
        public int getCount() { return count;}

        @Override
        public void run() {
            String pool = "!@#$%&*";

            while (count < TARGET) {
                char ch = pool.charAt(rng.nextInt(pool.length()));
                synchronized (PRINT_LOCK) {
                    System.out.print(ch);
                }
                count++;
            }
            synchronized (PRINT_LOCK) {
                System.out.println(); // newline after thread finishes
            }
        }
    }

    //MAIN METHOD
    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== ThreeThreads CSD420 ===\n");

        //run tests first
        runTests();

        System.out.println("\nSTARTING CHARACTER GENERATION...\n");

        //create threads
        LetterThread letterThread = new LetterThread();
        DigitThread digitThread = new DigitThread();
        SymbolThread symbolThread = new SymbolThread();

        //start each thread one at a time so each gets its own clean line
        letterThread.start();
        letterThread.join();  // wait for letters to finish before starting digits

        digitThread.start();
        digitThread.join();   // wait for digits to finish before starting symbols

        symbolThread.start();
        symbolThread.join();

        //summary
        System.out.println("\n\nGENERATION COMPLETE!\n\n");
        System.out.println("Letters produced: " + letterThread.getCount());
        System.out.println("Digits produced: " + digitThread.getCount());
        System.out.println("Symbols produced: " + symbolThread.getCount());
        System.out.println("Total characters: " +
                (letterThread.getCount() + digitThread.getCount() +
                        symbolThread.getCount()));
    }



    //TEST METHODS
    static void runTests() {
        System.out.println("\nRUNNING TESTS...");
        testLetterThreadProducesOnlyLetters();
        testDigitThreadProducesOnlyDigits();
        testSymbolThreadProducesOnlySymbols();
        testEachThreadMeetsTarget();
        testThreadNamesAreCorrect();

        System.out.println("\nALL TESTS PASS & ARE COMPLETE!\n");
    }

    //Verify LetterThread produces only a-zA-z characters
    static void testLetterThreadProducesOnlyLetters() {
        String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rng = new Random(42);
        boolean allLetters = true;

        for (int i = 0; i < 1000; i++) {
            char ch = pool.charAt(rng.nextInt(pool.length()));
            if (!Character.isLetter(ch)) {
                allLetters = false;
                break;
            }
        }

        assert_true(allLetters, "testLetterThreadProducesOnlyLetters");
    }

    //verify DigitThread produces only 0-9 characters
    static void testDigitThreadProducesOnlyDigits() {
        Random rng = new Random(42);
        boolean allDigits = true;

        for (int i = 0; i < 1000; i++) {
            char ch = (char) ('0' + rng.nextInt(10));
            if (!Character.isDigit(ch)) {
                allDigits = false;
                break;
            }
        }

        assert_true(allDigits, "testDigitThreadProducesOnlyDigits");
    }

    //verify SymbolThread produces only characters from the symbol pool
    static void testSymbolThreadProducesOnlySymbols() {
        String pool = "!@#$%&*";
        Random rng = new Random(42);
        boolean allSymbols = true;

        for (int i = 0; i < 1000; i++) {
            char ch = pool.charAt(rng.nextInt(pool.length()));
            if (pool.indexOf(ch) == -1) {
                allSymbols = false;
                break;
            }
        }

        assert_true(allSymbols, "testSymbolThreadProducesOnlySymbols");


    }


    /**
     * Run each thread in isolation and verify it reaches exactly
     * TARGET characters
     */

    static void testEachThreadMeetsTarget() {
        java.io.PrintStream original = System.out;
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override public void write(int b) {}
        }));

        try {
            LetterThread lt = new LetterThread();
            DigitThread dt = new DigitThread();
            SymbolThread st = new SymbolThread();

            lt.start(); dt.start(); st.start();
            lt.join(); dt.join(); st.join();

            assert_true(lt.getCount() == TARGET,
                    "testEachThreadMeetsTarget - LetterThread count == " + TARGET);
            assert_true(dt.getCount() == TARGET,
                    "testEachThreadMeetsTarget - DigitThread count == " + TARGET);
            assert_true(st.getCount() == TARGET,
                    "testEachThreadMeetsTarget - SymbolThread count == " + TARGET);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("TEST INTERRUPTED", e);
        } finally {
            System.setOut(original);
        }
    }

    //verify each thread has the expected name
    static void testThreadNamesAreCorrect() {
        assert_true(new LetterThread().getName().equals("LetterThread"),
                "testThreadNamesAreCorrect - LetterThread name");
        assert_true(new DigitThread().getName().equals("DigitThread"),
                "testThreadNamesAreCorrect - DigitThread name");
        assert_true(new SymbolThread().getName().equals("SymbolThread"),
                "testThreadNamesAreCorrect - SymbolThread name");
    }

    //Helper
    private static void assert_true(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
            throw new RuntimeException("TEST FAILED: " + testName);
        }
    }




}