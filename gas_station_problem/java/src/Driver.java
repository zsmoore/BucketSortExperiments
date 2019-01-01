import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.FileReader;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws Exception {
        String filename = args[0];
        JSONTokener tokener = new JSONTokener(new FileReader(filename));
        JSONArray inputArr = new JSONArray(tokener);

        String approachChoice = args[1];

        if (approachChoice.equals("sort")) {
            long start = System.nanoTime();
            ArrayList<Integer> outputList = SortSolution.getGasStations(inputArr);
            long end = System.nanoTime();
            System.out.println(outputList);
            System.out.println("Sort solution took:\n" + ((end - start) / 1_000_000_000.0));
        } else if (approachChoice.equals("bucket")) {
            long startBucket = System.nanoTime();
            ArrayList<Integer> outputListBucket = BucketSolution.getGasStations(inputArr);
            long endBucket = System.nanoTime();
            System.out.println(outputListBucket);
            System.out.println("Bucket solution took:\n" + ((endBucket - startBucket) / 1_000_000_000.0));
        }
    }
}
