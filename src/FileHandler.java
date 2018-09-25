import java.io.*;
import java.util.*;

public class FileHandler {

    public static Map processFile(File file) throws IOException {
        // Start with HashSet for performance
        Set initialSet = new HashSet<String>();
        Map map = new HashMap<String, TreeSet<String>>();

        FileReader fileR = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileR);
        String line;

        while ((line = reader.readLine()) != null) {
            initialSet.add(line);
        }

        // Convert set to TreeSet
        Set finalSet = new TreeSet<String>(initialSet);

        for (Object obj: finalSet) {
            HashSet<String> wordSet;
            // Convert the String "Object" into a String-type
            String str = (String) obj;
            // Get individual characters
            char[] charArr = str.toCharArray();

            if (!map.containsKey(str)) { // If map doesn't contain the word, create the entry
                map.put(str, new HashSet<String>());
            }
            wordSet = (HashSet<String>) map.get(str);

            // Iterate through the finalSet and find neighbors
            for (Object o: finalSet) {
                boolean neighbor = false;
                String s = (String) o;

                if (!s.equals(str) && s.length() == str.length()) { // Make sure it's not the same word and it's the same length
                    char[] compCharArr = s.toCharArray();
                    neighbor = neighborCheck(charArr, compCharArr);

                    if (neighbor) {
                        System.out.println("Original word: " + str + " Neighbor: " + s);
                        wordSet.add(s);
                    }
                }
            }
        }

        fileR.close();
        reader.close();
        return map;
    }

    public static Map processFileMap(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        Set initialSet = new HashSet<String>();
        Map map = new HashMap<String, TreeSet<String>>();
        boolean keyValue = true;
        String key = "";

        while (scan.hasNext()) {
            String str = scan.next();
            if (str.equals("----------------")) {
                keyValue = !keyValue;
            } else if (keyValue) {
                key = str;
                map.put(key, new TreeSet<String>());
            } else {
                Set set = (TreeSet<String>) map.get(key);
                set.add(str);
            }
        }
        return map;
    }

    public static void writeMap(Map<String, Set<String>> m) throws IOException {
        FileOutputStream stream = new FileOutputStream("src/data/finalDictMap.txt");
        Iterator it = m.keySet().iterator();
        byte[] borderBytes = "----------------\n".getBytes();

        while (it.hasNext()) {
            String str = (String) it.next();
            byte[] strBytes = str.getBytes();
            Iterator setIt = m.get(str).iterator();

            stream.write(strBytes);
            stream.write("\n".getBytes());
            stream.write(borderBytes);

            while (setIt.hasNext()) {
                String value = (String) setIt.next();

                stream.write(value.getBytes());
                stream.write("\n".getBytes());
            }
            stream.write(borderBytes);
        }
        System.out.println("DONE WRITING");
        stream.close();
    }

    public static HashSet<String> processFileToHashSet(File file) throws IOException {
        FileReader fileR = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileR);
        HashSet set = new HashSet<String>();
        String line;

        while ((line = reader.readLine()) != null) {
            set.add(line);
        }

        reader.close();
        fileR.close();
        return set;
    }

    public static HashSet<String> processFileIntoSet(File file) throws IOException {
        FileReader fileR = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileR);
        HashSet<String> set = new HashSet<String>();
        String line;

        while ((line = reader.readLine()) != null) {
            set.add(line);
        }

        reader.close();
        fileR.close();
        return set;
    }

    public static TreeMap<Integer, Set<String>> processFileBasedOnLength(File file) throws IOException {
        FileReader fileR = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileR);
        TreeMap<Integer, Set<String>> map = new TreeMap<Integer, Set<String>>();
        String line;

        while ((line = reader.readLine()) != null) {
            int length = line.length();
            if (!map.containsKey(length)) {
                map.put(length, new TreeSet<String>());
            }
            TreeSet<String> set = (TreeSet<String>) map.get(length);
            set.add(line);
        }

        reader.close();
        fileR.close();
        return map;
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
