package EarthquakeJavaFX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Collections;
import java.lang.Math;

public class EarthquakeData {
    EarthquakeMap earthquakeMap = new EarthquakeMap();
    private List<Earthquake> earthquakeList;
    private List<EarthquakeMap> earthquakeMapList;
    ArrayList<Integer> nbrOfEarthquake = new ArrayList<>();
    ArrayList<String> region = new ArrayList<>();
    ArrayList<Float> avgEarthquake = new ArrayList<>();
    ArrayList<Integer> higherNbrOfEarthquake = new ArrayList<>();
    ArrayList<String> regionMostHitByEarthquake = new ArrayList<>();
    ArrayList<Integer> frequencyOfTypes = new ArrayList<>();
    ArrayList<String> typesOfEarthquake = new ArrayList<>();
    ArrayList<Integer> nbrEarthquakePerDecade = new ArrayList<>();
    ArrayList<Integer> dateInDecade = new ArrayList<>();

    public void EarthquakeViewModel() {
        earthquakeList = new ArrayList<Earthquake>();
        earthquakeMapList = new ArrayList<EarthquakeMap>();
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
                            , Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), data.get(9)));
                    earthquakeMapList.add(new EarthquakeMap (earthquakeMap.dateToYear(data.get(1)), Float.parseFloat(data.get(6)),
                            Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), false));
                } else if (data.size() == 11) {
                    // 11 argument-size entries don't have either time or type
                    char firstChar = data.get(2).charAt(0);
                    if ((Character.isDigit(firstChar))) {
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2),
                                data.get(3), data.get(4),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(5)),
                                Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                data.get(5)));
                        earthquakeMapList.add(new EarthquakeMap (earthquakeMap.dateToYear(data.get(1)), Float.parseFloat(data.get(7))
                                , Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)), false));
                    }else{
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3),
                                Type.stringToType(data.get(4)),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(5)),
                                Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), data.get(5)));
                        earthquakeMapList.add(new EarthquakeMap (earthquakeMap.dateToYear(data.get(1)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                Float.parseFloat(data.get(9)), false));
                    }
                } else if (data.size() == 12) {
                    // 12 argument-size entries has all informations
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3)
                            , data.get(4), Type.stringToType(data.get(5)), Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7))
                            , Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)), Float.parseFloat(data.get(10))
                            , data.get(11)));
                    earthquakeMapList.add(new EarthquakeMap (earthquakeMap.dateToYear(data.get(1)), Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)),
                            Float.parseFloat(data.get(10)), false));
                }
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    public List<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }
    public List<EarthquakeMap> getEarthquakeMapList() {
        return earthquakeMapList;
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

    public void avgEarthquakePerRegion(){
        for (Earthquake earthquake : earthquakeList) {
            String earthQuakeIndex = earthquake.getRegion();
            if (avgEarthquake.isEmpty()){
                avgEarthquake.add(earthquake.getMagnitude());
            }
            for(int index = 0; index < avgEarthquake.size();++index){
                if (earthQuakeIndex.equals(region.get(index))) {
                    avgEarthquake.set(index, avgEarthquake.get(index) + earthquake.getMagnitude());
                    break;
                }
            }
            if(!avgEarthquake.contains(earthquake)){
                avgEarthquake.add(earthquake.getMagnitude());
            }
        }
        for(int index = 0; index < region.size();++index){
            avgEarthquake.set(index, avgEarthquake.get(index) / nbrOfEarthquake.get(index));
        }
    }
    public void earthquakesPerRegion(){
        String earthQuakeIndex;
        for (Earthquake earthquake : earthquakeList) {
            earthQuakeIndex = earthquake.getRegion();
            boolean isInArray = false;
            if (region.isEmpty()){
                region.add(earthQuakeIndex);
                nbrOfEarthquake.add(1);
                continue;
            }
            for(int index = 0; index < region.size();++index){
                if (earthQuakeIndex.equals(region.get(index))) {
                    isInArray = true;
                    nbrOfEarthquake.set(index, nbrOfEarthquake.get(index) + 1);
                    break;
                }
            }
            if(!isInArray){
                region.add(earthquake.getRegion());
                nbrOfEarthquake.add(1);
            }
        }
    }
    public void mostHitRegions(int x){
        ArrayList<String> tmpRegion = region;
        for (int index = 0; index < x; ++index){
            int intTmp = 0;
            String strTmp = "";
            for (int index1=0; index1<tmpRegion.size(); ++index1){
                if (tmpRegion.get(index1) != ""){
                    if (nbrOfEarthquake.get(index1) > intTmp){
                        intTmp = nbrOfEarthquake.get(index1);
                        strTmp = tmpRegion.get(index1);
                    }
                }
            }
            for (int index1=0; index1<tmpRegion.size(); ++index1){
                if (strTmp.equals(tmpRegion.get(index1))){
                    regionMostHitByEarthquake.add(strTmp);
                    higherNbrOfEarthquake.add(intTmp);
                    tmpRegion.set(index1, "");
                    break;
                }
            }
        }
    }

    public void typesAndTheirFrequency(){
        String tmpType = "";
        for (Earthquake earthquake : earthquakeList) {
            if(earthquake.getType() != null) {
                tmpType = Type.typeToString(earthquake.getType());
                if (typesOfEarthquake.isEmpty()) {
                    typesOfEarthquake.add(tmpType);
                    frequencyOfTypes.add(1);
                    continue;
                }
                for (int index = 0; index < typesOfEarthquake.size(); ++index) {
                    if (typesOfEarthquake.get(index).equals(tmpType)) {
                        frequencyOfTypes.set(index, frequencyOfTypes.get(index) + 1);
                        break;
                    }
                }
                if(!typesOfEarthquake.contains(tmpType)){
                    typesOfEarthquake.add(tmpType);
                    frequencyOfTypes.add(1);
                }
            }
        }
    }

    public int dateInDecade(int year){
        return (int) Math.floor(year/100);
    }

    public void earthquakePerDecade() {
        int decade = 0;
        for (Earthquake earthquake : earthquakeList) {
            decade = dateInDecade(earthquakeMap.dateToYear(earthquake.getDate()));
            if(dateInDecade.isEmpty()){
                dateInDecade.add(decade);
                nbrEarthquakePerDecade.add(1);
                continue;
            }
            for (int index = 0; index < dateInDecade.size(); ++index) {
                if (decade == dateInDecade.get(index)) {
                    nbrEarthquakePerDecade.set(index, nbrEarthquakePerDecade.get(index) + 1);
                    break;
                }
            }
            if (!dateInDecade.contains(decade)){
                dateInDecade.add(decade);
                nbrEarthquakePerDecade.add(1);
            }
        }
    }

    public ArrayList getRegion(){
        return region;
    }
    public ArrayList getNbrOfEarthquake(){
        return nbrOfEarthquake;
    }
    public ArrayList getAvgEarthquake(){
        return avgEarthquake;
    }

}