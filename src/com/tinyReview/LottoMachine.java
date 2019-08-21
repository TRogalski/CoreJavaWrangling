package com.tinyReview;

import java.util.*;

public class LottoMachine {

    private List<Integer> lotteryNumbers;
    private List<Integer> couponNumbers;

    public void playTheGame() {
        this.showWelcomeMessages();
        this.getNumbersFromPlayer();
        this.getNumbersFromTheLottery();
        this.displayResultMessages(this.getNumberOfHits());
        this.displayEndingMessages();
    }

    private void showWelcomeMessages() {
        System.out.println("This is a lotto game simulator, you have to select 6 unique numbers from 1 to 49./n" +
                "You win if you score 3, 4, 5 or 6 hits, good luck!");
    }

    private void getNumbersFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        this.couponNumbers = new ArrayList<>();

        System.out.println("Please select numbers.");

        while (couponNumbers.size() - 6 < 0) {
            Integer couponNumber = null;
            try {
                System.out.print("Your number is: ");
                couponNumber = scanner.nextInt();
                this.validatePlayerInput(couponNumber);
                this.couponNumbers.add(couponNumber);
            } catch (InputMismatchException e) {
                System.out.println("This is not a correct number, please try again.");
                scanner.next();
            } catch (IllegalArgumentException e1) {
                System.out.println("The number should be from 1-49 range, please try again.");
            } catch (Exception e2) {
                System.out.println("The number is already selected, please try again.");
            }
            System.out.println("Selected number is: " + couponNumber + ", you need to select " + Math.abs(this.couponNumbers.size() - 6) + " more.");
        }
    }

    private void validatePlayerInput(Integer couponNumber) throws Exception, IllegalArgumentException {
        if (couponNumber > 49 || couponNumber < 1) {
            throw new IllegalArgumentException();
        } else if (this.couponNumbers.contains(couponNumber)) {
            throw new Exception();
        }
    }

    public void setCouponNumbers(List<Integer> couponNumbers) {
        this.couponNumbers = couponNumbers;
    }

    private void getNumbersFromTheLottery() {
        Random random = new Random();
        this.lotteryNumbers = new ArrayList<Integer>();
        while (lotteryNumbers.size() - 6 < 0) {
            Integer randomCandidate = random.nextInt(50);
            if (!lotteryNumbers.contains(randomCandidate)) {
                lotteryNumbers.add(randomCandidate);
            }
        }
    }

    private Integer getNumberOfHits() {
        Integer hits = 0;
        for (Integer couponNumber : this.couponNumbers) {
            if (this.lotteryNumbers.contains(couponNumber)) {
                hits++;
            }
        }
        return hits;
    }

    private void displayResultMessages(Integer numberOfHits) {
        System.out.println("Your numbers: " + this.couponNumbers.toString());
        System.out.println("Lottery numbers: " + this.lotteryNumbers.toString());

        System.out.print("You managed to score ");

        switch (numberOfHits) {
            case 0:
                System.out.println("0 hits, it will be better next time!");
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println(numberOfHits + " hits, you won!");
                break;
        }
    }

    private void displayEndingMessages() {
        System.out.println("Thank you for playing!");
    }
}
