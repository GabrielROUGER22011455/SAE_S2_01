package com.example.sae_s2_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class EarthquakeViewModel {

    Earthquake earthquake = new Earthquake();
    HelloController helloController = new HelloController();
    EarthquakeMap earthquakeMap = new EarthquakeMap();
    private List<Earthquake> earthquakeList;
    private List<EarthquakeMap> earthquakeMapList;

    ArrayList<Integer> nbrOfEarthquake = new ArrayList<>();
    ArrayList<String> region = new ArrayList<>();


    public void EarthquakeViewModel() {
        earthquakeList = new ArrayList<Earthquake>();
        earthquakeMapList = new ArrayList<EarthquakeMap>();
        // Load data from the CSV file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/sae_s2_01/SisFrance_seismes_20230604151458.csv"));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                ArrayList<String> data = separateString(line);

                if (data.size() == 10) {
                    //System.out.println(data);
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3)
                            , Float.parseFloat(data.get(4)), Float.parseFloat(data.get(5)), Float.parseFloat(data.get(6))
                            , Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)), data.get(9)));
                    earthquakeMapList.add(new EarthquakeMap (earthquakeMap.setDateToYears(data.get(1)), Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7)),
                            Float.parseFloat(data.get(8)), false));
                    // Heure non donnée
                    // Type non donné
                } else if (data.size() == 11) {
                    char firstChar = data.get(2).charAt(0);
                    if ((Character.isDigit(firstChar))) {
                        //System.out.println(data);
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2),
                                data.get(3), data.get(4),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(5)),
                                Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                data.get(5)));
                        earthquakeMapList.add(new EarthquakeMap (earthquakeMap.setDateToYears(data.get(1)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                Float.parseFloat(data.get(9)), false));
                    }else{
                        earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2),
                                data.get(3), earthquake.stringToType(data.get(4)),Float.parseFloat(data.get(5)), Float.parseFloat(data.get(5)),
                                Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                data.get(5)));
                        earthquakeMapList.add(new EarthquakeMap (earthquakeMap.setDateToYears(data.get(1)), Float.parseFloat(data.get(7)), Float.parseFloat(data.get(8)),
                                Float.parseFloat(data.get(9)), false));
                    }
                    // Type non donné
                } else if (data.size() == 12) {
                    //System.out.println(data);
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)), data.get(1), data.get(2), data.get(3)
                            , data.get(4), earthquake.stringToType(data.get(5)), Float.parseFloat(data.get(6)), Float.parseFloat(data.get(7))
                            , Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)), Float.parseFloat(data.get(10))
                            , data.get(11)));
                    earthquakeMapList.add(new EarthquakeMap (earthquakeMap.setDateToYears(data.get(1)), Float.parseFloat(data.get(8)), Float.parseFloat(data.get(9)),
                            Float.parseFloat(data.get(10)), false));
                    // Toutes les informations
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void getRegionAndNumberOfEarthquake(){
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
                }
            }
            if(isInArray == false){
                region.add(earthquake.getRegion());
                nbrOfEarthquake.add(1);
            }
        }
    }

    public void changeEarthquakeOnMap(){
        int cmpt = 0;
        int magnitude;
        for (Earthquake earthquake : earthquakeList) {
            magnitude = (int)earthquakeMap.getMagnitude();
            if (helloController.minDate.get() > earthquakeMap.getYear()
                    || helloController.maxDate.get() > earthquakeMap.getYear()
                    || helloController.checkBoxsState.get(magnitude-1) == false)
            {
                earthquakeMap.setShowable(false);
            }else{
                earthquakeMap.setShowable(true);
            }
            cmpt +=1;
        }
    }
}