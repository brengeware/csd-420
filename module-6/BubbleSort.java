//Brennan Cheatwood
//CSD420 - Programming Assignment
//Assignment 6.2
//4/25/26
import java.util.Comparator;

public class BubbleSort {
    //sorts an array in ascending order using the Comparable interface
    public static <E extends Comparable<E>> void bubbleSort(E[] array) {
        //outer loop: tracks how many passes have been done.
        //After pass i, the last i+1 elements are already in their correct spots,
        //so we don't need to check them again (that's the - 1 - i part).
        for (int i = 0; i < array.length - 1; i++) {

            //inner loop: walks through the unsorted portion comparing neighbors
            for (int j = 0; j < array.length - 1 - i; j++) {
                //compareTo() returns a positive number if array[j] is greater
                //than array[j+1], meaning theyre out of order and need to be swapped.
                if (array[j].compareTo(array[j+1]) > 0) {
                    //swap the two neighbors
                    E temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //Sorts an array using a comparator provided by the caller
    public static <E> void bubbleSort(E[] array, Comparator<E> comparator) {
        //same outer loop structure as the Comparable version
        for (int i = 0; i < array.length - 1; i++) {
            //same inner loop, shrinking each pass
            for (int j = 0; j < array.length - 1 - i; j++) {
                //instead of compareTo(), we use comparator.compare()
                if (comparator.compare(array[j], array[j+1]) > 0) {
                    E temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
