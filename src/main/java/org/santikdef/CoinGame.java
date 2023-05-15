package org.santikdef;

import org.santikdef.util.Utils;

import java.util.Scanner;

public class CoinGame {
    private final Scanner scanner;
    private Double amount, optimalBetSizePercentageFraction;

    public CoinGame() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        init();

        printOptimalBetSize();

        playGame();
    }

    private void init() {
        boolean isNotCorrectAmount = true;

        while (isNotCorrectAmount) {
            try {
                System.out.print("Please enter the amount you have: ");
                amount = scanner.nextDouble();

                if (amount > 0) {
                    amount = Utils.round(amount, 2); //Added rounding with scale 2,
                    // because the number of digits to the right of the decimal point
                    // in the most real currencies can't be greater than 2
                    isNotCorrectAmount = false;
                }
            } catch (Exception e) {
                System.out.println("Incorrect answer. Please try again");
                scanner.nextLine();
            }
        }

        System.out.println("The current amount: " + amount);

        optimalBetSizePercentageFraction = calculateOptimalBetSizeFraction();
    }

    //the calculation of an optimal bet size using Kelly criterion
    private double calculateOptimalBetSizeFraction() {
        double winProbability = 0.5;  //the probability of a win
        double lossProbability = 0.5; //the probability of a loss
        double winProportion = 1.8;  //the proportion of the bet gained with a win

        return (winProbability - (lossProbability/winProportion));
    }

    private void printOptimalBetSize() {
        double optimalBetSize = Utils.round(amount * optimalBetSizePercentageFraction, 2);

        double optimalBetSizePercentage = (optimalBetSize/amount) * 100;

        System.out.println("The optimal bet size of the current amount: "
                + optimalBetSize + " (" + optimalBetSizePercentage + "%)");
    }

    private void playGame() {
        double bet;
        Coin coin;
        Coin.Side chosenCoinSide;

        coin = new Coin();

        while ((continueGame())) {
            bet = placeBet();
            chosenCoinSide = chooseSide();
            System.out.println("Tossing..");
            coin.toss();
            coin.printCurrentSide();

            if (chosenCoinSide.equals(coin.getCurrentSide())) {
                System.out.println("You won!");
                increaseAmount(bet);
                printOptimalBetSize();
            } else {
                System.out.println("You lose!");
                decreaseAmount(bet);
                printOptimalBetSize();
            }
        }
    }

    private void increaseAmount(double bet) {
        amount = amount + (bet * 1.8);

        amount = Utils.round(amount, 2);

        System.out.println("The current amount: " + amount);
    }

    private void decreaseAmount(double bet) {
        amount = amount - (bet * 0.5);

        amount = Utils.round(amount, 2);

        System.out.println("The current amount: " + amount);
    }

    private boolean continueGame() {
        while (true) {
            System.out.print("Do you want to toss a coin (Y/N)?: ");
            String answerPlay = scanner.next();
            if ("Y".equalsIgnoreCase(answerPlay)) {
                return true;
            } else if ("N".equalsIgnoreCase(answerPlay)) {
                System.out.println("Game is finished!");
                return false;
            } else {
                System.out.println("Incorrect answer. Please try again");
            }
        }
    }

    private double placeBet() {
        double bet = -1;

        while (true) {
            try {
                System.out.println("Please enter a bet: ");
                bet = scanner.nextDouble();

                if (amount >= bet && bet > 0) {
                    return Utils.round(bet, 2);
                } else {
                    System.out.println("Incorrect answer. Please try again");
                }
            } catch (Exception e) {
                System.out.println("Incorrect answer. Please try again");
                scanner.nextLine();
            }
        }
    }

    private Coin.Side chooseSide() {
        Coin.Side chosenCoinSide = null;
        boolean isNotChosen = true;

        while (isNotChosen) {
            System.out.print("Please choose a side of the coin (Head or Tail) (H/T)?: ");
            String answerSide = scanner.next();

            if ("H".equalsIgnoreCase(answerSide)) {
                chosenCoinSide = Coin.Side.HEAD;
                isNotChosen = false;
            } else if ("T".equalsIgnoreCase(answerSide)) {
                chosenCoinSide = Coin.Side.TAIL;
                isNotChosen = false;
            } else {
                System.out.println("Incorrect answer. Please try again");
            }
        }

        System.out.println("You chose: " + ((chosenCoinSide.equals(Coin.Side.HEAD)) ? "HEAD" : "TAIL"));

        return chosenCoinSide;
    }

}
