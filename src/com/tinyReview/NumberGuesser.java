package com.tinyReview;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {

    private static Integer numberToGuess;

    private void playTheGame() {
        this.showWelcomeMessages();
        this.generateANumberToBeGuessed();
        this.runCoreGameLogic();
        this.displayExitMessages();
    }


    private void generateANumberToBeGuessed() {
        Random random = new Random();
        this.numberToGuess = random.nextInt(101) - 1;
    }


    private void showWelcomeMessages() {
        System.out.println("This is a guess a number game, the goal is to guess" +
                " randomly generated integer number from range from 1 to 100");
    }


    private void runCoreGameLogic() {
        System.out.print("Guess a number between 0 and 100: ");

        Integer guess;

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                guess = scanner.nextInt();
                if (guess == numberToGuess) {
                    System.out.println("Nice, the number we were looking for was " + numberToGuess);
                    break;
                } else if (guess > numberToGuess) {
                    System.out.print("Your guess is too large, try again: ");
                } else if (guess < numberToGuess) {
                    System.out.print("Your guess is too low, try again: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("This is not a number, try again: ");
                scanner.next();
            }
        }
    }


    private void displayExitMessages() {
        System.out.println("Thank you for playing!");
    }
}
