package com.tinyReview;

import com.tinyReview.games.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private List<Game> games = Arrays.asList(new LottoMachine(),
            new NumberGuesser(),
            new NumberGuesserReversed(),
            new DiceThrower());

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        this.displayStartMessages();

        while (true) {
            try {
                System.out.println("0 - Exit\n" +
                        "1 - Lotto machine\n" +
                        "2 - Number guesser\n" +
                        "3 - Number guesser reversed\n" +
                        "4 - Dice thrower\n");

                int choice = scanner.nextInt();
                if (choice == 0) {
                    break;
                } else {
                    this.selectTheGame(choice);
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice, try again.");
                scanner.next();
            }
        }

        this.displayEndingMessages();
    }

    private void displayStartMessages() {
        System.out.println("Please select a game:");
    }

    private void selectTheGame(int nextInt) {
        switch (nextInt) {
            case 1:
                games.get(0).playTheGame();
                break;
            case 2:
                games.get(1).playTheGame();
                break;
            case 3:
                games.get(2).playTheGame();
                break;
            case 4:
                games.get(3).playTheGame();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void displayEndingMessages() {
        System.out.println("Thank you for using the app!");
    }

}
