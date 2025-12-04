import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part2 {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/data");
        ArrayList<Long> productIDs = new ArrayList<Long>();

        for (String s : fileData.get(0).split(",")) {
            long start = Long.parseLong(s.split("-")[0]);
            long end = Long.parseLong(s.split("-")[1]);

            for (long i = start; i <= end; i++) {
                productIDs.add(i);
            }
        }

        long answer = 0;

        for (long p : productIDs) {
            if (isInvalidPartTwo(p)) {
                answer += p;
            }
        }

        System.out.println("Part two answer: " + answer);
    }

    public static boolean isInvalidPartTwo(long productID) {
        String s = "" + productID;
        int len = s.length();

        // try every possible pattern length
        for (int patLen = 1; patLen <= len / 2; patLen++) {

            // only check patterns that divide length evenly
            if (len % patLen == 0) {
                String pattern = s.substring(0, patLen);
                int repeatCount = len / patLen;

                String built = "";
                for (int i = 0; i < repeatCount; i++) {
                    built += pattern;
                }

                if (built.equals(s)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals("")) {
                    fileData.add(line);
                }
            }
            return fileData;
        } catch (FileNotFoundException e) {
            return fileData;
        }
    }
}