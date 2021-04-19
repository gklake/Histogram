/*
 * Tile: Histogram.java
 * Abstract: This program will create a histogram based on data read in from a file
 * Author: Gabrielle Lake
 * Date: 02/05/2021
 */
import java.io.*;
import java.util.Scanner;
public class Histogram {
    public static void main(String[] args) {
        //The main driver for the program
        String userFileName = getFileName();
        char[] letter = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        int[] letterCount = new int[11];
        read(letter, letterCount, userFileName);
        sort(letter, letterCount);
        display(letter, letterCount);
    }

    public static String getFileName() {
        //This method will read the file name from the user
        Scanner userFile = new Scanner(System.in);
        System.out.print("Input file name :  ");
        return userFile.nextLine();
    }

    public static void read(char[] letter, int[] letterCount, String filename) {
        //This method will read the letters from the filename provided in the previous method
        Scanner scan = null;
        File f = new File(filename);
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("could not find the file" + e);
        }
        while (scan != null && scan.hasNextLine()) {
            String input = scan.nextLine();
            char c = input.charAt(0);
            for (int i = 0; i < letter.length; i++) {
                if (c == letter[i]) {
                    letterCount[i]++;
                }
            }
        }
    }

    public static void sort(char[] letter, int[] letterCount) {
        /*
         *This method will sort the letters (lowest to highest occurrence)
         *NOTE: keep track of both letter and letterCount arrays
         *Using insertion sort
         */
        for (int i = 1; i < letter.length; i++) {
            int current = letterCount[i];
            char current2 = letter[i];
            int j = i - 1;
            while (j >= 0 && current < letterCount[j]) {
                letterCount[j + 1] = letterCount[j];
                letter[j + 1] = letter[j];
                j--;
            }
            letterCount[j + 1] = current;
            letter[j + 1] = current2;
        }
    }

    public static void display(char[] letter, int[] letterCount) {
        //This method will display the histogram
        System.out.println("Char occurrence");
        //print letter from least to greatest occurrence
        for (int i = 0; i < letter.length; i++) {
            if (letterCount[i] > 0) {
                System.out.println(letter[i] + " " + letterCount[i]);
            }
        }
        System.out.println();

        for (int i = 0; i < 29; i++) {
            System.out.print("="); // formatting
        }
        System.out.println();

        //Finding maximum count
        int max = 0;
        for (int j : letterCount) {
            if (j > max) {
                max = j;
            }
        }

        //Printing Data
        for (int i = max; i > 0; i--) {
            if (max != 0) {
                System.out.format("|  %3d|", max);// printing vertical axis
                max--;
            }
            for (int k = 0; k < letterCount.length; k++) {
                if (letterCount[k] >= i) {
                    System.out.print(letter[k] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < 29; i++) {
            System.out.print("-"); // formatting
        }
        System.out.println();

        System.out.print("\t   ");//printing horizontal axis
        for (char c : letter) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}