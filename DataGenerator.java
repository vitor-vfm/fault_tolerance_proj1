import java.util.*;
import java.io.*;

class DataGenerator {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
            System.exit(1);
        }

        String outputFile = args[0];
        int N = Integer.parseInt(args[1]);

        if (N < 0) {
            System.out.println("Number of integers needs to be a non-negative number");
            System.exit(1);
        }
        
        List<Integer> randomInts = generateRandomIntegers(N);

        try {
            outputResult(outputFile, randomInts);
        } catch (IOException ioe) {
            System.out.println("There was a problem writing to file: ");
            System.out.println(ioe.getMessage());
            System.exit(1);
        }

    }

    static List<Integer> generateRandomIntegers(int N) {
        Random rnd = new Random();

        List<Integer> res = new ArrayList<Integer>(N);

        while (res.size() < N) {
            res.add(rnd.nextInt());
        }

        return res;
    }

    static void outputResult(String outputFile, List<Integer> numbers) throws IOException {
        FileWriter fw = null;

        try {
            fw = new FileWriter(outputFile);

            for (Integer nbr : numbers) {
                fw.write(nbr.toString());
                fw.write('\n');
            }
        } finally {
            fw.close();
        }
    }
}
