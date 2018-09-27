import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final File FILE_NAME = new File("src/data/dict.txt");
    private static final double BILLION = 1_000_000_000.0;

    private static HashSet<String> dict;
    public static long startTime;
    public static long endTime;
    public static long totalTime;

    public static String src;
    public static String tgt;

    private static boolean isRunning;

    public static void main(String[] args) {
        System.out.println("Enter two words (preferably same length) or type exit to close application: ");
        isRunning = true;
        while (isRunning) {
            if (dict == null) {
                setSet();
                resetTime();
            }
            System.out.print("> ");
            Scanner input = new Scanner(System.in);
            if (input.hasNextLine()) {
                String nextLine = input.nextLine();
                Scanner scan = new Scanner(nextLine);
                if (scan.hasNext()) {
                    src = scan.next();
                    if (scan.hasNext()) {
                        tgt = scan.next();
                        calculate();
                        resetTime();
                    } else {
                        System.out.println("Please try again!");
                        System.out.print("> ");
                    }
                } else {
                    System.out.println("Please try again!");
                    System.out.print("> ");
                }
            }
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
        return time / BILLION;
    }
    private static void setSet() {
        try {
            setStartTime();
            dict = FileHandler.processFileIntoSet(FILE_NAME);
            setEndTime();
            System.out.println("Time to Set: " + calcTimes());    // Calc time to set to map
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void calculate() {
        // Calculate LS Distance
        setStartTime();
        System.out.println(LDCalculator.LDCalc(src, tgt, dict));
        setEndTime();
        System.out.println("Time to process: " + calcTimes());
    }
    private static void resetTime() {
        totalTime = 0;
        startTime = 0;
        endTime = 0;
    }
}
