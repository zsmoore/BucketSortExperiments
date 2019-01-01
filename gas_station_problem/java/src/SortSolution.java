import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;

public class SortSolution {

    public static ArrayList<Integer> getGasStations(JSONArray inputArr) {
        ArrayList<Integer> sortedArr = getSortedList(inputArr);
        ArrayList<Integer> outputList = new ArrayList<>();

        int currentMile = 0;
        int lastStation = 0;
        for(Integer station : sortedArr) {
            if (station - currentMile >= Constants.MILEAGE) {
                outputList.add(lastStation);
                currentMile = lastStation;
            } else {
                lastStation = station;
            }

            if (station == Constants.END_MILE) {
                outputList.add(station);
                break;
            }
        }

        return outputList;
    }

    private static ArrayList<Integer> getSortedList(JSONArray inputArr) {
        ArrayList<Integer> sorted = new ArrayList<>(inputArr.length());
        for (int i = 0; i < inputArr.length(); i++) {
            sorted.add(inputArr.optInt(i));
        }
        Collections.sort(sorted);
        return sorted;
    }


}
