package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObservedPin {

    private static int[][] pinTerminal;
    private static final int terminalRowSize = 3;
    private static final int terminalColSize = terminalRowSize;


    public static List<String> getPINs(String observed) {
        List<String> potentialPins = new ArrayList<>();
        if (!isObservedNumberValid(observed)) {
            System.out.println("The observed pin is not valid.");
            return potentialPins;
        }

        pinTerminal = createPinTerminal();

        for (int observedDigit = 0; observedDigit < observed.length(); observedDigit++) {
            String potentialDigits = "";

            if (observed.charAt(observedDigit) - '0' == 0) {
                potentialDigits = "08";
            } else {
                potentialDigits = getPotentialDigits(observed, observedDigit, potentialDigits);
            }

            if (potentialPins.size() == 0) {
                addPotentialDigitsToPinList(potentialPins, potentialDigits);
            } else {
                List<String> multipliedPinList = appendPinList(potentialPins, potentialDigits);
                potentialPins.clear();
                potentialPins.addAll(multipliedPinList);
            }
        }
        return potentialPins;
    }

    private static String getPotentialDigits(String observed, int observedDigit, String potentialDigits) {
        for (int row = 0; row < terminalRowSize; row++) {
            for (int col = 0; col < terminalColSize; col++) {
                if (observed.charAt(observedDigit) - '0' == pinTerminal[row][col]) {
                    potentialDigits = collectPotentials(row, col);
                }
            }
        }
        return potentialDigits;
    }

    private static boolean isObservedNumberValid(String observed) {
        return observed.length() <= 8;
    }

    private static List<String> appendPinList(List<String> potentialPins, String potentialDigits) {
        int numOfPotentialPins = potentialPins.size();

        List<String> multipliedPinList =
                new ArrayList<>(Collections.nCopies(potentialDigits.length(), potentialPins)
                        .stream()
                        .flatMap(List::stream).toList());

        for (int i = 0; i < potentialDigits.length(); i++) {
            for (int j = 0; j < numOfPotentialPins; j++) {
                multipliedPinList.set(((i * numOfPotentialPins) + j), potentialPins.get(j) + potentialDigits.charAt(i));
            }
        }
        return multipliedPinList;
    }

    private static void addPotentialDigitsToPinList(List<String> potentialPins, String potentialDigits) {
        for (int i = 0; i < potentialDigits.length(); i++) {
            potentialPins.add(String.valueOf(potentialDigits.charAt(i)));
        }
    }


    private static int[][] createPinTerminal() {
        final int[][] pinTerminal = new int[terminalRowSize][terminalColSize];

        int num = 1;
        for (int i = 0; i < terminalRowSize; i++) {
            for (int j = 0; j < terminalColSize; j++) {
                pinTerminal[i][j] = num;
                num++;
            }
        }
        return pinTerminal;
    }

    private static String collectPotentials(int row, int col) {


        StringBuilder sb = new StringBuilder();

        int rowStart = Math.max(row - 1, 0);
        int rowFinish = Math.min(row + 1, terminalRowSize - 1);
        int colStart = Math.max(col - 1, 0);
        int colFinish = Math.min(col + 1, terminalColSize - 1);


        for (int curRow = rowStart; curRow <= rowFinish; curRow++) {
            sb.append(pinTerminal[curRow][col]);
        }

        for (int curCol = colStart; curCol <= colFinish; curCol++) {
            if (curCol == col) {
                continue;
            }
            sb.append(pinTerminal[row][curCol]);
        }

        if (pinTerminal[row][col] == 8) {
            sb.append(0);
        }

        return sb.toString();
    }
}
