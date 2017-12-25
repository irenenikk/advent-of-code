// two stars is based on http://markheath.net/post/advent-of-code-2017-day-3

package advent.of.code.oneToFive;

import advent.of.code.Solution;

import java.util.HashMap;
import java.util.Scanner;

public class Third implements Solution {
    // one star
    private int rightCornerDigit;
    private int input;
    private int inputLevel;
    private int inputSideLength;

    // two stars
    private int d;
    private HashMap<Coordinates, Integer> grid;
    private Coordinates currentCoordinates;
    private int level;

    public void run() {
        Scanner reader = new Scanner(System.in);
        int goal = Integer.parseInt(reader.nextLine());
        secondStar(goal);
    }

    private void firstStar() {
        findLevel();
        int cornerDigit = findCornerDigitOfSameSide();
        int middleDigit = getMiddleDigit(cornerDigit);
        int stepsToMiddle = Math.abs(input - middleDigit);
        int stepsTo1 = stepsToMiddle + inputLevel - 1;
        System.out.println(stepsTo1);
    }

    private void findLevel() {
        rightCornerDigit = 1;
        inputSideLength = 1;
        inputLevel = 1;
        for (inputSideLength = 1; inputSideLength < Integer.MAX_VALUE; inputSideLength += 2) {
            rightCornerDigit += inputSideLength * 4 - 4;
            if (rightCornerDigit >= input) {
                return;
            }
            inputLevel++;
        }
    }

    private int findCornerDigitOfSameSide() {
        int cornerDigit = rightCornerDigit;
        for (int i = 0; i < 4; i++) {
            cornerDigit -= inputSideLength - 1;
            if (input >= cornerDigit) {
                return cornerDigit;
            }
        }
        System.out.println("Couldn\'t find side");
        return -666;
    }

    private int getMiddleDigit(int beginning) {
        int end = beginning + inputSideLength;
        return beginning + (end - beginning)/2;
    }

    // Second Star
    private void secondStar(int goal) {
        String directions = "RULD";
        d = 0;
        level = 1;
        grid = new HashMap<>();
        this.currentCoordinates = new Coordinates(0, 0);
        this.grid.put(this.currentCoordinates, 1);
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < level; j++) {
                    Coordinates currentCoordinates = getNextCoordinates(directions);
                    this.currentCoordinates = currentCoordinates;
                    int currentNumber = getNextNumber();
                    if (currentNumber > goal) {
                        System.out.println(currentNumber);
                        return;
                    }
                    this.grid.put(currentCoordinates, currentNumber);
                }
                d++;
                d = d % directions.length();
            }
            level++;
        }
    }

    private Coordinates getNextCoordinates(String directions) {
        Coordinates coord = null;
        char c = directions.charAt(d);
        switch (c) {
            case 'R':
                coord = new Coordinates(this.currentCoordinates.x+1, this.currentCoordinates.y);
                break;
            case 'L':
                coord = new Coordinates(this.currentCoordinates.x-1, this.currentCoordinates.y);
                break;
            case 'U':
                coord = new Coordinates(this.currentCoordinates.x, this.currentCoordinates.y-1);
                break;
            case 'D':
                coord = new Coordinates(this.currentCoordinates.x, this.currentCoordinates.y+1);
                break;
        }
        return coord;
    }

    private int getNextNumber() {
        int nextNumber = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Coordinates c = new Coordinates(this.currentCoordinates.x+i, this.currentCoordinates.y+j);
                if (this.grid.containsKey(c)) {
                    nextNumber += this.grid.get(c);
                }
            }
        }
        return nextNumber;
    }

    private class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinates that = (Coordinates) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Coordinates{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
