package com.example.sae_s2_01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EarthquakeViewModel {
    private List<Earthquake>  earthquakeList;

    public void EarthquakeViewModel() {
        earthquakeList = new ArrayList<Earthquake>();
        // Load data from the CSV file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/sae_s2_01/SisFrance_seismes_20230604151458.csv"));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                ArrayList<String> data = separateString(line);
                if (data.size()==10) {
                    //System.out.println(data);
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)),data.get(1),data.get(2),data.get(3)
                            ,Float.parseFloat(data.get(4)),Float.parseFloat(data.get(5)),Float.parseFloat(data.get(6))
                            ,Float.parseFloat(data.get(7)),Float.parseFloat(data.get(8)),data.get(9)));
                    // Heure non donnée
                    // Type non donné
                }
                else if (data.size()==11){
                    //System.out.println(data);
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)),data.get(1),data.get(2),data.get(3)
                            ,data.get(4),Float.parseFloat(data.get(5)),Float.parseFloat(data.get(6))
                            ,Float.parseFloat(data.get(7)),Float.parseFloat(data.get(8)),Float.parseFloat(data.get(9))
                            ,data.get(10)));
                    // Type non donné
                }
                else if (data.size()==12){
                    //System.out.println(data);
                    earthquakeList.add(new Earthquake(Integer.parseInt(data.get(0)),data.get(1),data.get(2),data.get(3)
                            ,data.get(4),data.get(5),Float.parseFloat(data.get(6)),Float.parseFloat(data.get(7))
                            ,Float.parseFloat(data.get(8)),Float.parseFloat(data.get(9)),Float.parseFloat(data.get(10))
                            ,data.get(11)));
                    // Toutes les informations
                }
            }
            reader.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    public List<Earthquake> getEarthquakeList() {
        return earthquakeList;
    }
    private ArrayList<String> separateString (String str) {
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
            ++ index;
            if (i == ',') {
                return new StringInt(tmp, index);
            }
            else if (i == '\"') {
                // Start of the " reading
                for (char j : str.substring(index).toCharArray()) {
                    ++ index;
                    if (j == '\"') {
                        return new StringInt(tmp, index);
                    }
                    tmp += j;
                }
                // End of the " reading
            }
            tmp += i;
        }
        return new StringInt(tmp, index+1);
    }
}