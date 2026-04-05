//Brennan Cheatwood
//CSD420 - Advanced Java
//Assignment 3.2 | 4/5/2026

import java.util.ArrayList;

public class removeDuplicates {

    //Static generic method that removes duplicates from an arraylist
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> newList = new ArrayList<>();

        for (E element : list) {
            //Only add the element if it's not already in the new list
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }

        return newList;
    }

    public static void main(String[] args) {
        // Create the original arralist and fill with 50 random values from 1-20
        ArrayList<Integer> originalList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            //math: Math.random() gives a double from 0.0 to 1.0
            //Multiplying by 20 and adding 1 gives a range of 1-20
            int randomNum = (int)(Math.random() * 20) + 1;
            originalList.add(randomNum);
        }

        //call method to get new list without duplicates
        ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);

        //print the original list
        System.out.println("\n\nOriginal List (50 Values): ");
        System.out.println(originalList);
        System.out.println("Size: " + originalList.size());

        //Print the new list without dups
        System.out.println("\nList With Duplicates Removed: ");
        System.out.println(noDuplicatesList);
        System.out.println("Size: " + noDuplicatesList.size());
    }
}