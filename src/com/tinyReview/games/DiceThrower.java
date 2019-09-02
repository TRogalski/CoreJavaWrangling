package com.tinyReview.games;

import java.sql.SQLOutput;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiceThrower implements Game{

    private List<String> availableDices = new ArrayList<>(Arrays.asList("D3", "D4", "D6", "D8", "D10", "D12", "D20", "D100"));

    public void playTheGame() {
        this.showWelcomeMessages();
        this.getInputsFromPlayerAndGenerateResuts();
        this.displayExitMessages();
    }

    private void showWelcomeMessages() {
        System.out.println("This is a dice throwing simulator.\n" +
                "Define a dice throw according to this formula: xDy+z\n" +
                "x - number of throws \n" +
                "y - number of dice sides \n" +
                "z - number to be added/subtracted from the result");
        System.out.println("Available dices: " + this.availableDices.toString());
    }

    private void getInputsFromPlayerAndGenerateResuts() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Please define the dice: ");
                String throwSpecification = scanner.nextLine();
                this.validateThrowSepcification(throwSpecification);
                this.calculateAndReturnTheResult(throwSpecification);
                break;
            } catch (IllegalStateException e) {
                System.out.println("The throw is not defined properly, please try again.");
            }
        }
    }

    private void validateThrowSepcification(String throwSpecification) throws IllegalStateException {
        Pattern dicePattern = Pattern.compile("D\\d+");
        Matcher matcher = dicePattern.matcher(throwSpecification);

        if (matcher.find()) {
            String diceSidesDefinition = matcher.group();
            this.validateDiceSides(diceSidesDefinition);

        } else {
            throw new IllegalStateException();
        }

    }

    private void validateDiceSides(String diceSidesDefinition) {
        if (!availableDices.contains(diceSidesDefinition)) {
            throw new IllegalStateException();
        }
    }

    private Integer getDiceSidesNumber(String throwSpecification) throws IllegalStateException {
        Pattern dicePattern = Pattern.compile("D(\\d*)");
        Matcher matcher = dicePattern.matcher(throwSpecification);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        } else {
            throw new IllegalStateException();
        }
    }

    private void calculateAndReturnTheResult(String throwSpecification) {
        Integer numberOfThrows = this.getNumberOfThrows(throwSpecification);
        Integer numberOfSides = this.getDiceSidesNumber(throwSpecification);
        Integer numberToBeAdded = this.getNumberToBeAdded(throwSpecification);

        System.out.println("Dice formula translated as:");
        System.out.println("Number of throws = " + numberOfThrows);
        System.out.println("Number of sides = " + numberOfSides);
        System.out.println("Number to be added = " + numberToBeAdded);

        Random random = new Random();

        Integer throwTotal = 0;

        for (int i = 1; i <= numberOfThrows; i++) {
            Integer diceResult = random.nextInt(numberOfSides)+1;
            System.out.println("Throw number: " + i + " Result on the dice: " + diceResult);
            throwTotal += diceResult;
        }

        throwTotal+=numberToBeAdded;

        System.out.println("The final result equals to: " + throwTotal);
    }

    private Integer getNumberOfThrows(String throwSpecification) {
        Pattern dicePattern = Pattern.compile("\\d*D");
        Matcher matcher = dicePattern.matcher(throwSpecification);

        if (matcher.find()) {
            String match = matcher.group().replaceAll("D", "");
            return "".equals(match) ? 1 : Integer.valueOf(match);
        }

        return 1;
    }

    private Integer getNumberToBeAdded(String throwSpecification) {
        Pattern dicePattern = Pattern.compile("[-+]\\d*");
        Matcher matcher = dicePattern.matcher(throwSpecification);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group());
        } else {
            return 0;
        }
    }

    private void displayExitMessages() {
        System.out.println("Thank you for using this tool!\n");
    }
}
