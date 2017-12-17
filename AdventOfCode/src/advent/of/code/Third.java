package advent.of.code;

import java.util.Scanner;

public class Third {
    private static int rightCornerDigit;
    private static int input;
    private static  int inputLevel;
    private static int inputSideLength;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        input = Integer.parseInt(reader.nextLine());
        firstStar();
        secondStar();
    }

    private static void firstStar() {
        findLevel();
        int cornerDigit = findCornerDigitOfSameSide();
        int middleDigit = getMiddleDigit(cornerDigit);
        int stepsToMiddle = Math.abs(input - middleDigit);
        int stepsTo1 = stepsToMiddle + inputLevel - 1;
        System.out.println(stepsTo1);
    }

    private static void findLevel() {
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

    private static int findCornerDigitOfSameSide() {
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

    private static int getMiddleDigit(int beginning) {
        int end = beginning + inputSideLength;
        return beginning + (end - beginning)/2;
    }

    private static void secondStar() {

    }
}
