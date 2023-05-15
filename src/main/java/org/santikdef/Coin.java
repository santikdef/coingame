package org.santikdef;

import java.util.Random;

public class Coin {
    private static final String ASCII_TAIL = "                                      ███████████                                      \n" +
            "                                  ██████      ███████                                  \n" +
            "                                ████              ███                                  \n" +
            "                                ██       █████     ███                                \n" +
            "                              ████         █        ███                                \n" +
            "                              ██           █         ███                              \n" +
            "                              ██           █         ███                              \n" +
            "                              ██           █         ███                              \n" +
            "                              ██                     ███                             \n" +
            "                              ██                     ███                              \n" +
            "                              ██                     ███                              \n" +
            "                              ████                  ███                                \n" +
            "                                ██                  ███                                \n" +
            "                                ████              ████                                  \n" +
            "                                  ██████      ███████                                  \n" +
            "                                      ████████████  ";

    private static final String ASCII_HEAD = "                                      ███████████                                      \n" +
            "                                  ██████      ████████                                  \n" +
            "                                ████              ████                                  \n" +
            "                                ██     █    █       ███                                \n" +
            "                              ████     █    █        ███                                \n" +
            "                              ██       ██████        ███                              \n" +
            "                              ██       █    █        ███                              \n" +
            "                              ██       █    █        ███                              \n" +
            "                              ██                     ███                              \n" +
            "                              ██                     ███                              \n" +
            "                              ██                     ███                              \n" +
            "                              ████                  ███                                \n" +
            "                                ██                  ███                                \n" +
            "                                ████              ████                                  \n" +
            "                                  ██████      ███████                                  \n" +
            "                                      ████████████  ";

    private final Random random;

    private Side currentSide;

    public Coin() {
        random = new Random();
    }

    public void toss() {
        currentSide = random.nextBoolean() ? Side.HEAD : Side.TAIL;
    }

    public void printCurrentSide() {
        System.out.println(currentSide.equals(Side.HEAD) ? ASCII_HEAD : ASCII_TAIL);
        System.out.println(currentSide.equals(Side.HEAD) ? "The side of the tossed coin is HEAD" : "The side of the tossed coin is TAIL");
    }

    public Side getCurrentSide() {
        return currentSide;
    }

    public void setCurrentSide(Side currentSide) {
        this.currentSide = currentSide;
    }

    public enum Side {
        TAIL, HEAD
    }
}
