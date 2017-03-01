import java.util.List;

class DataGenerator {

    public static void main(String[] args) {
        if (args.length != 3)
            throw new IllegalArgumentException("Wrong number of arguments");

        String outputFile = args[1];
        int N = Integer.parseInt(args[2]);

        if (N < 0)
            throw new IllegalArgumentException("Number of integers needs to be a non-negative number");
        
        List<Integer> randomInts = generateRandomIntegers(N);

        outputResult(outputFile, randomInts);
    }

    static List<Integer> generateRandomIntegers(int N) {
        return null;
    }

    static void outputResult(String outputFile, List<Integer> numbers) {
    }
}
