package main;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static HashSet<String> processFileIntoSet(InputStream input) throws IOException {
        InputStreamReader inS = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(inS);
        HashSet<String> set = new HashSet<String>();
        String line;

        while ((line = reader.readLine()) != null) {
            set.add(line);
        }

        reader.close();
        inS.close();
        return set;
    }

    /* Helper methods */
    private static int calcTotal(char[] arr) {
        int val = 0;

        for (int i: arr) {
            val += i;
        }
        return val;
    }

    private static boolean neighborCheck(char[] arr, char[] arr2) {
        int mismatch = 0;

        // For loop to compare the c[] arr and c[] arr2
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                mismatch++;
            }
        }

        if (mismatch == 1) {
            return true;
        }
        return false;
    }
}
