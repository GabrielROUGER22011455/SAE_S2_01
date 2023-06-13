package EarthquakeJavaFX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.lang.Math;

public class EarthquakeData {
    private List<Earthquake> earthquakeList;
    ArrayList<Integer> higherNbrOfEarthquake = new ArrayList<>();
    ArrayList<String> regionMostHitByEarthquake = new ArrayList<>();
    ArrayList<Integer> nbrEarthquakePerDecade = new ArrayList<>();
    ArrayList<Integer> dateInDecade = new ArrayList<>();
    public EarthquakeData() {
        earthquakeList = new ArrayList<Earthquake>();
        try {
            // Load data from the CSV file
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/SisFrance_seismes_20230604151458.csv"));
            String line;
            reader.readLine();
            // Read each entries in the CSV file to fill earthquakeList attribute
            while ((line = reader.readLine()) != null) {
                // Each entry can contain 10 to 12 arguments
                ArrayList<String> data = separateString(line);
                if (data.size() == 10) {
                    // 10 argument-size entries don't have informations avout time and type
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3)
                            , Float.parseFloat(data.get(4)), Float.parseFloat(data.get(5)), Float.parseFloat(data.get(6))
                            , Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), Quality.stringToQuality(data.get(9))));
                } else if (data.size() == 11) {
                    // 11 argument-size entries don't have either time or type
                    char firstChar = data.get(2).charAt(0);
                    if ((Character.isDigit(firstChar))) {
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2),
                                data.get(3), data.get(4),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(6)),
                                Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)),
                                Quality.stringToQuality(data.get(10))));
                    }else{
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3),
                                Type.stringToType(data.get(4)),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(6)),
                                Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)),
                                Quality.stringToQuality(data.get(10))));
                    }
                } else if (data.size() == 12) {
                    // 12 argument-size entries has all informations
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3)
                            , data.get(4), Type.stringToType(data.get(5)), Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7))
                            , Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)), Float.parseFloat(data.get(10))
                            , Quality.stringToQuality(data.get(11))));
                }
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    private ArrayList<String> separateString(String str) {
        // Separate strings from one entry on the CSV file
        // Takes an entry from the CSV file in parameter
        // Returns an ArrayList of 10 to 12 String
        int index = 0;
        StringInt tmp;
        ArrayList<String> stringList = new ArrayList<String>();
        while (index <= str.length()) {
            tmp = getStringAt(str, index);
            if (tmp.getStr() != "") stringList.add(tmp.getStr());
            index = tmp.getNbr();
        }
        return stringList;
    }
    private StringInt getStringAt(String str, int index) {
        // Give the string at the given position in the CSV file
        // Takes an entry from the CSV file and an index in parameter
        // Returns a String
        String tmp = "";
        for (char i : str.substring(index).toCharArray()) {
            ++index;
            if (i == ',') {
                return new StringInt(tmp, index);
            } else if (i == '\"') {
                // Start of the " reading
                for (char j : str.substring(index).toCharArray()) {
                    ++index;
                    if (j == '\"') {
                        return new StringInt(tmp, index);
                    }
                    tmp += j;
                }
                // End of the " reading
            }
            tmp += i;
        }
        return new StringInt(tmp, index + 1);
    }
    public HashMap<String, Integer> getEarthquakePerRegion(){
        // Returns each regions and the number of earthquakes they had
        HashMap<String, Integer> nbrEarthquake = new HashMap<String, Integer>();
        for (Earthquake earthquake : earthquakeList) {
            // If region already in list : add 1 to the number of earthquake
            if (nbrEarthquake.containsKey(earthquake.getRegion())) {
                nbrEarthquake.replace(earthquake.getRegion(), nbrEarthquake.get(earthquake.getRegion()) + 1);
            }
            // If region not in list : set the number of earthquake to 1
            else {
                nbrEarthquake.put(earthquake.getRegion(), 1);
            }
        }
        return nbrEarthquake;
    }
    public HashMap<String, Float> getAvgMagnitude(){
        // Returns the average earthquake magnitude per region
        HashMap<String, Float> sumMagnitude = new HashMap<String, Float>();
        for (Earthquake earthquake : earthquakeList) {
            // If region already in list : add its magnitude to the total
            if (sumMagnitude.containsKey(earthquake.getRegion())) {
                sumMagnitude.replace(earthquake.getRegion(), sumMagnitude.get(earthquake.getRegion()) + earthquake.getMagnitude());
            }
            // If region not in list : add its magnitude
            else {
                sumMagnitude.put(earthquake.getRegion(), earthquake.getMagnitude());
            }
        }
        // Then, divide each total of magnitude by the amount of earthquakes
        HashMap<String, Integer> nbrEarthquake = getEarthquakePerRegion();
        HashMap<String, Float> avgMagnitude = new HashMap<String, Float>();
        for (String region : sumMagnitude.keySet()) {
            avgMagnitude.put(region,sumMagnitude.get(region)/nbrEarthquake.get(region));
        }
        return avgMagnitude;
    }
    public ArrayList<String> getMostHitRegions(int range) {
        // Returns the 'range' most hit regions by earthquakes
        // First, separate in two list the regions and their number of earthquakes
        HashMap<String, Integer> tmpHashMap = getEarthquakePerRegion();
        ArrayList<String> regions = new ArrayList<String>(tmpHashMap.keySet());
        ArrayList<Integer> nbrEarthquake = new ArrayList<Integer>();
        for (String region : regions ) {
            nbrEarthquake.add(tmpHashMap.get(region));
        }
        // Then, create the result list :
        ArrayList<String> mostHitRegions = new ArrayList<String>();
        for (int i = 0 ; i < range ; ++i) {
            // Get the index of the highest element in the list
            int maxIndex = 0;
            for (int j = 0; j < nbrEarthquake.size(); j++) {
                maxIndex = nbrEarthquake.get(j) > nbrEarthquake.get(maxIndex) ? j : maxIndex;
            }
            // Add this element to the result list
            mostHitRegions.add(regions.get(maxIndex));
            // Delete the element from the other lists
            regions.remove(maxIndex);
            nbrEarthquake.remove(maxIndex);
        }
        return mostHitRegions;
    }
    public ArrayList<String> getTypes () {
        // Returns a list of all the earthquake types
        ArrayList<String> types = new ArrayList<String>();
        for (Earthquake earthquake : earthquakeList) {
            if (!types.contains(Type.typeToString(earthquake.getType()))) {
                types.add(Type.typeToString(earthquake.getType()));
            }
        }
        return types;
    }
    public ArrayList<Integer> getTypeFrequency () {
        // Returns the frequency of each type
        // Firts, separate in two list the types and their frequency. They share the same indexes.
        ArrayList<String> types = getTypes();
        ArrayList<Integer> frequency = new ArrayList<Integer>();
        // Fill the frequency list with zeros
        for (String type : types) {
            frequency.add(0);
        }
        // Count the frequency of each type
        for (Earthquake earthquake : earthquakeList) {
            int index = types.indexOf(Type.typeToString(earthquake.getType()));
            frequency.add(index, frequency.get(index)+1);
        }
        return frequency;
    }
    public ArrayList<Integer> getDecades () {
        // Returns the decades in wich we have informations
        ArrayList<Integer> decades = new ArrayList<Integer>();
        for (Earthquake earthquake : earthquakeList) {
            int decade = earthquake.getDecade();
            if (!decades.contains(decade)){
                decades.add(decade);
            }
        }
        return decades;
    }
    public ArrayList<Integer> getEarthquakePerDecade () {
        // First, separate and sort the decades and their number of earthquake. They share the same indexes.
        ArrayList<Integer> earthquakePerDecade = new ArrayList<Integer>();
        ArrayList<Integer> decades = getDecades();
        Collections.sort(decades);
        // Fill the number of earthquake list with zeros
        for (int decade : decades) {
            earthquakePerDecade.add(0);
        }
        // Count the earthquakes for each decades
        for (Earthquake earthquake : earthquakeList) {
            int index = decades.indexOf(earthquake.getDecade());
            earthquakePerDecade.add(index, earthquakePerDecade.get(index)+1);
        }
        return earthquakePerDecade;
    }
}