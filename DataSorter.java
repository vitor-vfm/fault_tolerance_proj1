import java.util.*;
import java.io.*;

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
    }

}
