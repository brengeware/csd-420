// Brennan Cheatwood
// Assignment 2.2
// 3/29/26
// Write/Read Program

import java.io.*;
import java.util.Random;

public class brennanWriteData {
    public static void main(String[] args) {
        //array of five random ints
        int[] integers = new int[5];
        //arr of five random doubles
        double[] doubles = new double[5];

        Random rand = new Random();

        // Fill arrays with random values
        for (int i = 0; i < 5; i++) {
            integers[i] = rand.nextInt(100);
            doubles[i] = Math.round(rand.nextDouble() * 100.0 * 100.0) / 100.0;
        }

        //writing to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("Brennan datafile.dat", true))) {
            writer.println("--- Integer Data ---");
            for (int num : integers) {
                writer.println(num);
            }

            writer.println("--- Double Data ---");
            for (double num : doubles) {
                writer.println(num);
            }

            writer.println();
            System.out.println("Data written to 'Brennan datafile.dat' successfully!");

            System.out.println("\nIntegers written:");
            for (int num : integers) {
                System.out.println(" " + num);
            }
            System.out.println("Doubles written:");
            for (double num : doubles) {
                System.out.println(" " + num);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file...");
            e.printStackTrace();
        }
    }
    
}
