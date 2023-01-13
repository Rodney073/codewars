package org.example;

public class SolutionTime {
    public static String timeCorrect(String timestring) {

        if (timestring == null || !isInputFormatOk(timestring)) {
            return null;
        }

        if (timestring.isEmpty() || isInputPerfect(timestring)) {
            return timestring;
        }

        int hour = Integer.parseInt(timestring.substring(0, 2));
        int mins = Integer.parseInt(timestring.substring(3, 5));
        int secs = Integer.parseInt(timestring.substring(6, 8));

        if (secs > 59) {
            secs = secs - 60;
            mins++;
        }
        if (mins > 59) {
            mins = mins - 60;
            hour++;
        }

        while (hour > 23) {
            hour = hour - 24;
        }

        return String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs);
    }

    private static boolean isInputPerfect(String timestring) {
        return timestring.matches("^(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)$");
    }

    private static boolean isInputFormatOk(String timestring) {
        return timestring.matches("^\\d{2}:\\d{2}:\\d{2}$");
    }

}