/*
 *
 * Professor Darrell Payne
 * Bellevue University
 *
 * Reason to study sorting algorithms
 *  Illustrations for creative approaches to problem solving
 *  Good practice for fundamental programming techniques
 *    such as loop types
 *  Help in demonstrating algorithm performances
 *
 * Java has several overloaded sort methods for sorting 
 *   primitives and objects in java.util.Arrays and java.util.Collections
 *  
 * The primary of this Module is to 
 *  sort integers, sort data in arrays, and sort in ascending order
 *

 9,  8,  7,  6,  5,  4,  3,  2,  1,  0

 8,  7,  6,  5,  4,  3,  2,  1,  0,  9 

 7,  6,  5,  4,  3,  2,  1,  0,  8,  9 

 6,  5,  4,  3,  2,  1,  0,  7,  8,  9 

 5,  4,  3,  2,  1,  0,  6,  7,  8,  9 

 4,  3,  2,  1,  0,  5,  6,  7,  8,  9 

 3,  2,  1,  0,  4,  5,  6,  7,  8,  9 

 2,  1,  0,  3,  4,  5,  6,  7,  8,  9 

 1,  0,  2,  3,  4,  5,  6,  7,  8,  9 

 0,  1,  2,  3,  4,  5,  6,  7,  8,  9 

 0,  1,  2,  3,  4,  5,  6,  7,  8,  9 

 */
public class Bubble_Sort{

  public static void main(String [] args){

     int [] testValues = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};

    // Test using this array first as you will then be better able to understand the actions taken
    //int [] testValues = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    // temp is used for swapping
    int temp;

    // Print arrays to show no sorting yet
    printArray(testValues);

    // i starting at 0
    for(int i = 0; i < testValues.length - 1; ++i){

      // j starting at 0
      for(int j = 0; j < testValues.length - 1; ++j){

        // if testValues[0] > testValues[1]
        // Then swap
        if(testValues[j] > testValues[j + 1]){

          // temp = testValues[0];
          temp = testValues[j];
          // testValues[0] = testValues[1]
          testValues[j] = testValues[j + 1];
          // testValues[1] = temp or testValues[0]
          testValues[j + 1] = temp;
          // Notice that a swap was made
          System.out.println("Switch made");
        }
      }

      // Print array after swap
      printArray(testValues);
    }
    
    printArray(testValues);
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