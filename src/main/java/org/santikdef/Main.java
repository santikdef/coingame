package org.santikdef;

/*
Let’s consider a game, in which you are tossing a coin with 50/50 winning/losing probability. In case of a
win you get +80% of your bet. In the other case you lose 50% of the bet.
You are proposed to write a program that simulates the game process and calculates the optimal bet
size as the percentage of the amount you have.
*/

public class Main {
    public static void main(String[] args) {
        CoinGame coinGame = new CoinGame();
        coinGame.start();
    }
}