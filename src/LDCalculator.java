import java.util.*;

public class LDCalculator {
    public static final int RANGE = (int) ('z' - 'a');
    public static final int MIN = (int) 'a';

    public static String LDCalc(String src, String tgt, HashSet<String> set) {
        // Initialize variables
        ArrayList<String> paths = new ArrayList<>();
        HashSet<String> used = new HashSet<>();
        int setCount = 0;

        // Add the source word into the path
        paths.add(src);
        // Iterate through the path
        while (!paths.contains(tgt)) {
            // Check for all possible words and reset the path
            paths = checkForPossible(paths, set, used);
            // Add to count
            setCount++;
            if (paths.isEmpty()) {
                return "Not possible!";
            }
        }
        return "Total Levenshtein Distance: " + setCount;
    }

    /* Helper methods */
    private static boolean neighborCheck(char[] arr, char[] arr2) {
        if (arr.length == arr2.length) {
            int mismatch = 0;

            // For loop to compare the c[] arr and c[] arr2
            for (int i = 0; i < arr2.length; i++) {
                if (arr[i] != arr2[i]) {
                    mismatch++;
                }
            }

            if (mismatch == 1) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<String> checkForPossible(ArrayList<String> paths, HashSet<String> set, HashSet<String> dis) {
        ArrayList<String> list = new ArrayList<>();
        char c;

        for (String str: paths) {
            for (int i = 0; i < str.length(); i++) {
                String[] restArr = returnRest(i, str);
                for (int j = 0; j < RANGE; j++) {
                    // Iterate through and make sure you aren't matching the charAt character
                    if ((c = (char)(MIN + j)) != str.charAt(i)) {
                        // Combine the broken parts into a String
                        String word = restArr[0] + c + restArr[1];
                        // If it is a valid word and it isn't already grabbed
                        if (set.contains(word) && !dis.contains(word)) {
                            // Add to the list as a neighbor word
                            list.add(word);
                            // Trash the word so it can't be grabbed again
                            dis.add(word);
                        }
                    }
                }
            }
        }
        return list;
    }

    private static String[] returnRest(int index, String s) {
        String[] str = {"", ""};
        if (index != 0 && index < s.length() - 1) {
            str[0] = s.substring(0, index);
            str[1] = s.substring(index + 1, s.length());
            return str;
        } else if (index == 0) { // Get everything after the first char
            str[1] = s.substring(index + 1, s.length());
            return str;
        } else { // Get everything before the last char
            str[0] = s.substring(0, index);
        }
        return str;
    }
}
