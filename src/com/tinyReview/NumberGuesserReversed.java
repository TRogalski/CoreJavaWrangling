package com.tinyReview;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumberGuesserReversed {

    private List<String> playerGuidanceOptions = Arrays.asList("higher", "lower", "hit");

    public static void main(String[] args) {
        NumberGuesserReversed numberGuesserReversed = new NumberGuesserReversed();
        numberGuesserReversed.playTheGame();
    }

    public void playTheGame() {
        this.showWelcomeMessages();
        this.runCoreGameLogic();
    }

    private void showWelcomeMessages() {
        System.out.println("Think of a number between 0 and 1000. I will try to guess it in 10 attempts.");
        System.out.println("Write: 'higher', 'lower' or 'hit' to indicate how close I am.");
    }

    private void runCoreGameLogic() {
        Integer min = 0;
        Integer max = 1000;
        Integer guessAttemptsCount = 10;
        Scanner scanner = new Scanner(System.in);

        while (guessAttemptsCount > 0) {
            Integer guess = (max - min) / 2 + min;
            try {
                System.out.println("This is my " + (11 - guessAttemptsCount) + " try, is your number equal to: " + guess + "?");
                System.out.print("Respond [higher/lower/hit]: ");
                String playerGuidance = scanner.nextLine();
                this.validatePlayerGuidance(playerGuidance);
                if ("hit".equals(playerGuidance)) {
                    System.out.println("I guessed your number on my " + (11 - guessAttemptsCount) + " try!");
                    break;
                } else if ("higher".equals(playerGuidance)) {
                    guessAttemptsCount--;
                    min = guess;
                } else if ("lower".equals(playerGuidance)) {
                    guessAttemptsCount--;
                    max = guess;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("This is not a valid response, please try again.");
            }
        }

        if (guessAttemptsCount == 0) {
            System.out.println("You must have cheated!");
        }

    }

    private void validatePlayerGuidance(String playerGuidance) throws IllegalArgumentException {
        if (!this.playerGuidanceOptions.contains(playerGuidance)) {
            throw new IllegalArgumentException();
        }
    }


}
