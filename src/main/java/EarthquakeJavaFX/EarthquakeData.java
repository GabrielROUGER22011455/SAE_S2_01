package EarthquakeJavaFX;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The EarthquakeData class represents a collection of earthquake data.
 *
 * It provides methods for loading earthquake data from a CSV file and performing data processing operations.
 */
public class EarthquakeData {
    /**
     * List of earthquakes.
     */
    private ArrayList<Earthquake> earthquakeList;
    /**
     * Constructs an EarthquakeData object by loading earthquake data from a CSV file.
     *
     * @param file the CSV file containing earthquake data
     */
    public EarthquakeData(File file) {
        earthquakeList = new ArrayList<Earthquake>();
        try {
            // Load data from the CSV file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            reader.readLine();
            // Read each entries in the CSV file to fill earthquakeList attribute
            while ((line = reader.readLine()) != null) {
                earthquakeList.add(new Earthquake(separateString(line)));
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    /**
     * Separates strings from one entry on the CSV file.
     *
     * @param str an entry from the CSV file
     * @return an ArrayList of 10 to 12 strings
     */
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
            else stringList.add(null);
            index = tmp.getNbr();
        }
        return stringList;
    }
    /**
     * Retrieves the string at the given position in the CSV file.
     *
     * @param str   an entry from the CSV file
     * @param index the index position
     * @return a String
     */
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
                        return new StringInt(tmp, index+1);
                    }
                    tmp += j;
                }
                // End of the " reading
            }
            tmp += i;
        }
        return new StringInt(tmp, index + 1);
    }
    /**
     * Returns a HashMap containing each region and the number of earthquakes they had.
     *
     * @return a HashMap with region as the key and the number of earthquakes as the value
     */
    public HashMap<String, Integer> getEarthquakePerRegion(){
        // Returns each regions and the number of earthquakes they had
        HashMap<String, Integer> nbrEarthquake = new HashMap<String, Integer>();
        for (Earthquake earthquake : earthquakeList) {
            // If region already in list : add 1 to the number of earthquake
            if (earthquake.isShown() && nbrEarthquake.containsKey(earthquake.getRegion())) {
                nbrEarthquake.replace(earthquake.getRegion(), nbrEarthquake.get(earthquake.getRegion()) + 1);
            }
            // If region not in list : set the number of earthquake to 1
            else if (earthquake.isShown()){
                nbrEarthquake.put(earthquake.getRegion(), 1);
            }
        }
        return nbrEarthquake;
    }
    /**
     * Returns a HashMap containing the average earthquake magnitude per region.
     *
     * @return a HashMap with region as the key and the average magnitude as the value
     */
    public HashMap<String, Float> getAvgMagnitude(){
        // Returns the average earthquake magnitude per region
        HashMap<String, Float> sumMagnitude = new HashMap<String, Float>();
        for (Earthquake earthquake : earthquakeList) {
            // If region already in list : add its magnitude to the total
            if (earthquake.isShown() && sumMagnitude.containsKey(earthquake.getRegion())) {
                sumMagnitude.replace(earthquake.getRegion(), sumMagnitude.get(earthquake.getRegion()) + earthquake.getMagnitude());
            }
            // If region not in list : add its magnitude
            else if (earthquake.isShown()){
                sumMagnitude.put(earthquake.getRegion(), earthquake.getMagnitude());
            }
        }
        // Then, divide each total of magnitude by the amount of earthquakes
        HashMap<String, Integer> nbrEarthquake = getEarthquakePerRegion();
        HashMap<String, Float> avgMagnitude = new HashMap<String, Float>();
        for (String region : sumMagnitude.keySet()) {
            avgMagnitude.put(region, Float.valueOf(sumMagnitude.get(region) / nbrEarthquake.get(region)));
        }
        return avgMagnitude;
    }
    /**
     * Returns a list of the 'range' most hit regions by earthquakes.
     *
     * @param range the number of regions to return
     * @return an ArrayList of the most hit regions
     */
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
    /**
     * Returns a list of all the earthquake types.
     *
     * @return an ArrayList of earthquake types
     */
    public ArrayList<String> getTypes () {
        // Returns a list of all the earthquake types
        ArrayList<String> types = new ArrayList<String>();
        for (Earthquake earthquake : earthquakeList) {
            if (earthquake.isShown() && earthquake.getType() != Type.NULL
                    && !types.contains(Type.typeToString(earthquake.getType()))) {
                types.add(Type.typeToString(earthquake.getType()));
            }
        }
        return types;
    }
    /**
     * Returns the frequency of each earthquake type.
     *
     * @return an ArrayList of type frequencies
     */
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
            if (earthquake.isShown() && earthquake.getType() != Type.NULL) {
                int index = types.indexOf(Type.typeToString(earthquake.getType()));
                frequency.set(index, frequency.get(index) + 1);
            }
        }
        return frequency;
    }
    /**
     * Returns the centuries in which we have earthquake information.
     *
     * @return an ArrayList of centuries
     */
    public ArrayList<Integer> getCenturies () {
        // Returns the centuries in wich we have informations
        ArrayList<Integer> centuries = new ArrayList<Integer>();
        for (Earthquake earthquake : earthquakeList) {
            int century = earthquake.getCentury();
            if (earthquake.isShown() && !centuries.contains(century)){
                centuries.add(century);
            }
        }
        return centuries;
    }
    /**
     * Returns the number of earthquakes per century.
     *
     * @return an ArrayList of the number of earthquakes per century
     */
    public ArrayList<Integer> getEarthquakePerCentury () {
        // First, separate and sort the centuries and their number of earthquake. They share the same indexes.
        ArrayList<Integer> earthquakePerCentury = new ArrayList<Integer>();
        ArrayList<Integer> centuries = getCenturies();
        // Fill the number of earthquake list with zeros
        for (int century : centuries) {
            earthquakePerCentury.add(0);
        }
        // Count the earthquakes for each decades
        for (Earthquake earthquake : earthquakeList) {
            if (earthquake.isShown()) {
                int index = centuries.indexOf(earthquake.getCentury());
                earthquakePerCentury.set(index, earthquakePerCentury.get(index)+1);
            }
        }
        return earthquakePerCentury;
    }
    /**
     * Returns the minimum year in the earthquake data.
     *
     * @return the minimum year
     */
    public int getMinYear () {
        int minYear = earthquakeList.get(0).getYear();
        for (Earthquake earthquake : earthquakeList) {
            if (earthquake.getYear() <= minYear) {
                minYear = earthquake.getYear();
            }
        }
        return minYear;
    }
    /**
     * Returns the maximum year in the earthquake data.
     *
     * @return the maximum year
     */
    public int getMaxYear () {
        int maxYear = earthquakeList.get(0).getYear();
        for (Earthquake earthquake : earthquakeList) {
            if (earthquake.getYear() >= maxYear) {
                maxYear = earthquake.getYear();
            }
        }
        return maxYear;
    }
    /**
     * Returns the list of earthquakes.
     *
     * @return an ArrayList of earthquakes
     */
    public ArrayList<Earthquake> getEarthquakeList () {
        return earthquakeList;
    }
    /**
     * Filters the earthquakes based on the specified minimum and maximum years.
     *
     * @param minYear the minimum year
     * @param maxYear the maximum year
     */
    public void yearFilter(int minYear, int maxYear) {
        for (Earthquake earthquake : earthquakeList) {
            if (earthquake.getYear() >= minYear && earthquake.getYear() <= maxYear) {
                earthquake.show();
            } else {
                earthquake.hide();
            }
        }
    }
    public void magnitudeFilterChecked(int magnitude) {
        for (Earthquake earthquake : earthquakeList) {
            if ((int) earthquake.getMagnitude() == magnitude || (magnitude == 10 && earthquake.getMagnitude() >= 10)) {
                System.out.println("show");
                earthquake.show();
            }
        }
    }
    public void magnitudeFilterUnchecked(int magnitude) {
        for (Earthquake earthquake : earthquakeList) {
            if ((int) earthquake.getMagnitude() == magnitude || (magnitude == 10 && earthquake.getMagnitude() >= 10)) {
                System.out.println("hide");
                earthquake.hide();
            }
        }
    }
}