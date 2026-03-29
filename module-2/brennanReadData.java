// Brennan Cheatwood
// Assignment 2.2
// 3/29/26
// Write/Read Program pt 2

import java.io.*;

    public class brennanReadData {

    public static void main(String[] args) {
        File file = new File("Brennan datafile.dat");

        //check if file already exists before attempting read
        if (!file.exists()) {
            System.out.println("\n\nFile not found! Please run brennanWriteData first!\nIt's in the same folder as this program.");
            return;
        }

        System.out.println("\n\nReading data...");
        System.out.println("Reading 'Brennan datafile.dat'...");
        System.out.println("-----------------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading this file...");
            e.printStackTrace();
        }

        System.out.println("--------------------------------------");
        System.out.println("Done reading file. No errors.");

    }

}