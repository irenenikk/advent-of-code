package advent.of.code.oneToFive;

import advent.of.code.Solution;

import java.util.Scanner;

public class Second implements Solution {
    private static Scanner reader;

    public void run() {
        reader = new Scanner(System.in);
        twoStars();
    }

    private static void oneStar() {
        int sizeOfTable = Integer.parseInt(reader.nextLine());
        int checkSum = 0;
        for (int i = 0; i < sizeOfTable; i++) {
            String input = reader.nextLine();
            String[] integers = input.split("\\s+");
            int smallest = Integer.MAX_VALUE;
            int biggest = Integer.MIN_VALUE;
            for (int j = 0; j < integers.length; j++) {
                int integer = Integer.parseInt(integers[j]);
                smallest = Math.min(smallest, integer);
                biggest = Math.max(biggest, integer);
            }
            checkSum += (biggest - smallest);
        }
        System.out.println(checkSum);
    }

    private static void twoStars() {
        int sizeOfTable = Integer.parseInt(reader.nextLine());
        int checkSum = 0;
        for (int i = 0; i < sizeOfTable; i++) {
            String input = reader.nextLine();
            String[] integers = input.split("\\s+");
            for (int j = 0; j < integers.length; j++) {
                int integer = Integer.parseInt(integers[j]);
                boolean found = false;
                for (int k = 1; k < integers.length; k++) {
                    int index = j+k;
                    if (index >= integers.length) {
                        index = 0;
                    }
                    int integer2 = Integer.parseInt(integers[index]);
                    int result = integer % integer2;
                    int result2 = integer2 % integer;
                    if (result == 0) {
                        checkSum += integer / integer2;
                        found = true;
                    } else if (result2 == 0) {
                        checkSum += integer2 / integer;
                        found = true;
                    }
                }
                if (found) {
                    break;
                }
            }
        }
        System.out.println(checkSum);
    }

}
