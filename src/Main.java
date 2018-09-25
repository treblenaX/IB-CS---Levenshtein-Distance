import com.sun.org.apache.bcel.internal.generic.LDC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final File FILE_NAME = new File("src/data/dict.txt");
    private static HashSet<String> dict;
    public static long startTime;
    public static long endTime;
    public static long totalTime;

    public static String src;
    public static String tgt;

    public static void main(String[] args) {
        System.out.println("Enter two words (preferably same length): ");
        System.out.print("> ");
        Scanner input = new Scanner(System.in);
        if (input.hasNext() && (src = input.next()).contains(src)) {
            if (input.hasNext() && (tgt = input.next()).contains(tgt)) {
                System.out.println("Words gotten!");
            } else {
                try {
                    throw new Exception("WHERE'S THE OTHER WORD?");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            // Set text file to a map
            setStartTime();
            dict = FileHandler.processFileIntoSet(FILE_NAME);
            setEndTime();
            System.out.println("Time to Map: " + calcTimes());    // Calc time to set to map

            // Calculate LS Distance
            setStartTime();
            System.out.println(LDCalculator.LDCalc(src, tgt, dict));
            setEndTime();
            System.out.println("Total time: " + calcTimes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Helper methods */
    private static double setStartTime() {
        startTime = System.nanoTime();
        return startTime;
    }
    private static double setEndTime() {
        endTime = System.nanoTime();
        return endTime;
    }
    private static double calcTimes() {
        long time = endTime - startTime;
        totalTime += time;
        return totalTime / 1_000_000_000.0;
    }
}
