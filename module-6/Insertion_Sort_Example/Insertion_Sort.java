/*
 *
 * Professor Darrell Payne
 * Bellevue University
 *
 * Insertion Sort
 *
 * First at line 31 - All the rest are at line 51
 * 9 - 8 - 7 - 6 - 5 - 4 - 3 - 2 - 1
 * 8 - 9 - 7 - 6 - 5 - 4 - 3 - 2 - 1
 * 7 - 8 - 9 - 6 - 5 - 4 - 3 - 2 - 1
 * 6 - 7 - 8 - 9 - 5 - 4 - 3 - 2 - 1
 * 5 - 6 - 7 - 8 - 9 - 4 - 3 - 2 - 1
 * 4 - 5 - 6 - 7 - 8 - 9 - 3 - 2 - 1
 * 3 - 4 - 5 - 6 - 7 - 8 - 9 - 2 - 1
 * 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 1
 * 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9
 */
public class Insertion_Sort{

  public static void main(String [] args){

     // int [] testValues = {55, 66, 44, 77, 33, 88, 22, 99, 11};

    // Test using this array first as you will then be better able to understand the actions taken
    int [] testValues = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    // temp is used for swapping
    int temp;

    // Print arrays to show no sorting yet
    printArray(testValues);

    int i = 1;
    int j = 0;

    // i starting at 1, not 0
    while(i < testValues.length){

      // The temp value, testValues[1]
      temp = testValues[i]; // Temp = testValues[1]
      // Now lower then i
      // i started at 1, so to start j is 0, the first element
      j = i - 1; // j = i - 1 or j = 0

      // So if testValues[0] > temp
      // This is testValues[0] > testValues[1]
      while(j >= 0 && testValues[j] > temp){

        // swap is made if:
        // Then testValues[1] = testValues[0]
        testValues[j + 1] = testValues[j];
        --j; // j now == -1

        System.out.println("Switch made");
      }

      // Then testValues[0] = testValues[1];
      testValues[j + 1] = temp;
      ++i;

      // After this we have
      //  8 - 9 - 7 - 6 - 5 - 4 - 3 - 2 - 1

      printArray(testValues);

      // when i == length, while loop stops
    }
      }

  // Prints array
  public static void printArray(int [] arrayParam){

    System.out.print("\narray = {");

    for(int e : arrayParam){

      System.out.print(" [" + e + "] ");
    }

    System.out.println("};\n");
  }
}