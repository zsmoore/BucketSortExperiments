import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class BucketSolution {

    public static ArrayList<Integer> getGasStations(JSONArray inputArr) {
        HashMap<Integer, ArrayList<Integer>> buckets = genBuckets(inputArr);
        ArrayList<Integer> outputArr = new ArrayList<>();

        int currentDistance = 0;
        int currentBucket = 0;
        while (currentDistance != Constants.END_MILE) {
            int maxStation = currentDistance;
            for (Integer station : buckets.get(currentBucket)) {
                if (station < currentDistance + Constants.MILEAGE && station > maxStation) {
                    maxStation = station;
                }
            }

            if (maxStation == currentDistance) {
                for (Integer station : buckets.get(currentBucket - 1)) {
                    if (station > maxStation) {
                        maxStation = station;
                    }
                }
            } else {
                currentBucket += 1;
            }

            currentDistance = maxStation;
            outputArr.add(maxStation);
        }

        return outputArr;
    }

    private static HashMap<Integer, ArrayList<Integer>> genBuckets(JSONArray inputArray) {
        HashMap<Integer, ArrayList<Integer>> buckets = new HashMap<>();
        for (int i = 0; i < inputArray.length(); i++) {
            int station = inputArray.optInt(i);
            int distance = station / Constants.MILEAGE;
            if (!buckets.containsKey(distance)) {
                buckets.put(distance, new ArrayList<>());
            }
            buckets.get(distance).add(station);
        }
        return buckets;
    }
}
