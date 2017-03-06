import java.util.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;

class DataSorter {

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Wrong number of arguments");
            System.exit(1);
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        double primaryFailureProbability = Double.parseDouble(args[2]);
        double backupFailureProbability = Double.parseDouble(args[3]);

        int timeLimit = Integer.parseInt(args[4]);

        int[] data = null;

        try {
            data = readInputFile(inputFilename);
        } catch (IOException ioe) {
            System.out.println("Problem reading file");
            System.out.println(ioe.getMessage());
        }

        Executive exec = new Executive(data, primaryFailureProbability, backupFailureProbability, timeLimit);

        int[] result = null;

        try {
            result = exec.runSort();
        } catch (FailureException fe) {
            System.out.println("FAILURE. No output file was written");
            System.out.println(fe.getMessage());
        }

        try {
            writeOutputFile(outputFilename, result);
        } catch (IOException ioe) {
            System.out.println("Problem writing file");
            System.out.println(ioe.getMessage());
        }
    }

    private static int[] readInputFile(String inputFilename) throws IOException {
        FileReader fr = null;
        StringBuffer buf = new StringBuffer();
        char c;

        Path path = FileSystems.getDefault().getPath(inputFilename);
        List<String> tokens = Files.readAllLines(path, Charset.defaultCharset());

        int[] result = new int[tokens.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(tokens.get(i));
        }

        return result;
    }

    private static void writeOutputFile(String outputFilename, int[] data) throws IOException {
        if (data == null) return;

        FileWriter fw = null;

        try {
            fw = new FileWriter(outputFilename);

            for (Integer nbr : data) {
                fw.write(nbr.toString());
                fw.write('\n');
            }
        } finally {
            fw.close();
        }

    }

}
