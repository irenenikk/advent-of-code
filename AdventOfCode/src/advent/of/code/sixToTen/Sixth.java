package advent.of.code.sixToTen;

import advent.of.code.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Sixth implements Solution {

    @Override
    public void run() {
        String input = "10\t3\t15\t10\t5\t15\t5\t15\t9\t2\t5\t8\t5\t2\t3\t6";
        String input1 = "0 2 7 0";
        secondStar(input);
    }

    private void firstStar(String input) {
        int[] digits = inputToIntArray(input);
        Set<Bank> oldConfigurations = new HashSet<>();
        while (true) {
            int highest = 0;
            int indexOfHighestNumber = -1;
            for (int i = 0; i < digits.length; i++) {
                if (highest < digits[i]) {
                    highest = digits[i];
                    indexOfHighestNumber = i;
                }
            }
            int distributable = highest;
            int index = indexOfHighestNumber;
            digits[index] = 0;
            while (distributable > 0) {
                index++;
                index = index % digits.length;
                digits[index]++;
                distributable--;
            }
            Bank newBank = new Bank(digits);
            if (oldConfigurations.contains(newBank)) {
                break;
            }
            oldConfigurations.add(new Bank(digits));
        }
        System.out.println(oldConfigurations.size()+1);
    }

    private void secondStar(String input) {
        int[] digits = inputToIntArray(input);
        int cycle = 0;
        HashMap<Bank, Integer> banks = new HashMap();
        int olderBankCycles = -1;
        int newerBankCycles = -1;
        while (true) {
            cycle++;
            int highest = 0;
            int indexOfHighestNumber = -1;
            for (int i = 0; i < digits.length; i++) {
                if (highest < digits[i]) {
                    highest = digits[i];
                    indexOfHighestNumber = i;
                }
            }
            int distributable = highest;
            int index = indexOfHighestNumber;
            digits[index] = 0;
            while (distributable > 0) {
                index++;
                index = index % digits.length;
                digits[index]++;
                distributable--;
            }
            Bank newBank = new Bank(digits);
            if (banks.keySet().contains(newBank)) {
                olderBankCycles = banks.get(newBank);
                newerBankCycles = cycle;
                break;
            }
            banks.put(new Bank(digits), cycle);
        }
        System.out.println(newerBankCycles - olderBankCycles);
    }

    private int[] inputToIntArray(String input) {
        String[] digits = input.split("\\s+");
        int[] instructions = new int[digits.length];
        for (int i = 0; i < digits.length; i++) {
            int digit = Integer.parseInt(digits[i]);
            instructions[i] = digit;
        }
        return instructions;
    }

    private class Bank {
        int[] content;

        public Bank(int[] content) {
            this.content = Arrays.copyOf(content,content.length);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bank bank = (Bank) o;

            return Arrays.equals(content, bank.content);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(content);
        }

        @Override
        public String toString() {
            return "Bank{" +
                    "content=" + Arrays.toString(content) +
                    '}';
        }
    }
}
