package advent.of.code.sixToTen;

import advent.of.code.Solution;

import java.util.*;

/**
 * Created by nikkaire on 26.12.2017.
 */
public class Eighth implements Solution {
    Map<String, Integer> registerContents;

    @Override
    public void run() {
        bothStars();
    }

    private void bothStars() {
        registerContents = new HashMap<>();
        int highestValueInRegisters = Integer.MIN_VALUE;
        Scanner reader = new Scanner(System.in);
        while (reader.hasNextLine()) {
            String input = reader.nextLine();
            if (input.isEmpty()) {
                break;
            }
            String[] holder = input.split(" ");
            String modifiableRegister = holder[0];
            String command = holder[1];
            String amount = holder[2];
            String checkedRegister = holder[4];
            String conditionRelation = holder[5];
            String conditionAmount = holder[6];

            if (conditionApplies(checkedRegister, conditionRelation, conditionAmount)) {
                int change = getChange(command, amount);
                int oldValue = 0;
                if (registerContents.containsKey(modifiableRegister)) {
                    oldValue = registerContents.get(modifiableRegister);
                }
                int newValue = oldValue + change;
                highestValueInRegisters = Math.max(highestValueInRegisters, newValue);
                registerContents.put(modifiableRegister, newValue);
            }
        }
        OptionalInt maxValue = registerContents.values().stream().mapToInt(Integer::intValue).max();;
        maxValue.ifPresent(v -> System.out.println("Highest value in registers at the moment (one star): " + v));
        System.out.println("Total highest value (two stars): " + highestValueInRegisters);
    }

    private boolean conditionApplies(String checkedRegister, String conditionRelation, String conditionAmount) {
        int checkedRegisterContent = 0;
        if (registerContents.containsKey(checkedRegister)) {
            checkedRegisterContent = registerContents.get(checkedRegister);
        }
        int amount = Integer.parseInt(conditionAmount);
        switch (conditionRelation) {
            case "<":
                return checkedRegisterContent < amount;
            case ">":
                return checkedRegisterContent > amount;
            case "<=":
                return checkedRegisterContent <= amount;
            case ">=":
                return checkedRegisterContent >= amount;
            case "!=":
                return checkedRegisterContent != amount;
            case "==":
                return checkedRegisterContent == amount;
        }
        return false;
    }

    private int getChange(String command, String amount) {
        int a = Integer.parseInt(amount);
        if (command.equals("dec")) {
            return -a;
        }
        return a;
    }
}
