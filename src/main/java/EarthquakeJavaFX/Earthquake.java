package EarthquakeJavaFX;

import java.util.ArrayList;

/**
 * This class represents an earthquake.
 * It contains information such as the ID, date, time,
 * name, region, type, coordinates, magnitude, data quality, and whether the event is shown.
 */
public class Earthquake {
    private int id;
    private String date;
    private String time;
    private String name;
    private String region;
    private Type type;
    private float xPos;
    private float yPos;
    private float xPosWGS;
    private float yPosWGS;
    private float magnitude;
    private Quality dataQuality;
    private boolean show;

    /**
     * Checks if the earthquake is shown.
     * Only earthquakes with show = true are considered in other methods.
     * @return true if the earthquake is shown, false otherwise.
     */
    public boolean isShown() {
        // Only earthquake with show = true are considered in any methods
        return show;
    }
    /**
     * Hides the earthquake by marking it as not shown.
     */
    public void hide() {show = false;}
    /**
     * Shows the earthquake by marking it as shown.
     */
    public void show() {show = true;}
    /**
     * Constructor for the Earthquake class.
     * Initializes the attributes based on the data provided in a list.
     * @param data List containing the earthquake data.
     */
    public Earthquake (ArrayList<String> data) {
        if (data.get(0) != null)this.id = Integer.parseInt(data.get(0));
        else this.id = 0;
        if (data.get(1) != null)this.date = data.get(1);
        else this.date = "0000/00/00";
        if (data.get(2) != null)this.time = data.get(2);
        else this.time = "00";
        if (data.get(3) != null)this.name = data.get(3);
        else this.name = "NON RENSEIGNE";
        if (data.get(4) != null)this.region = data.get(4);
        else this.region = "NON RENSEIGNE";
        if (data.get(5) != null) this.type = Type.stringToType(data.get(5));
        else this.type = Type.NULL;
        if (data.get(6) != null)this.xPos = Float.parseFloat(data.get(6));
        else this.xPos = 0;
        if (data.get(7) != null)this.yPos = Float.parseFloat(data.get(7));
        else this.yPos = 0;
        if (data.get(8) != null)this.xPosWGS = Float.parseFloat(data.get(8));
        else this.xPosWGS = 0;
        if (data.get(9) != null)this.yPosWGS = Float.parseFloat(data.get(9));
        else this.yPosWGS = 0;
        if (data.get(10) != null)this.magnitude = Float.parseFloat(data.get(10));
        else this.magnitude = 0;
        if (data.get(11) != null)this.dataQuality = Quality.stringToQuality(data.get(11));
        else this.dataQuality = Quality.NULL;
        show = true;
    }
    /**
     * Gets the region of the earthquake.
     * @return The region of the earthquake.
     */
    public String getRegion(){
        return region;
    }
    /**
     * Gets the type of the earthquake.
     * @return The type of the earthquake.
     */
    public Type getType(){
        return type;
    }
    /**
     * Gets the magnitude of the earthquake.
     * @return The magnitude of the earthquake.
     */
    public float getMagnitude(){return magnitude;}
    /**
     * Returns a string representation of the earthquake.
     * @return A string containing the earthquake information.
     */
    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + Type.typeToString(type) + "\n Longitude RGF93/L93 : "+xPos+"\n Latitude RGF93/L93 "
                + yPos + "\n Longitude WSG84 : " + xPosWGS+"\n Latitude WSG94 : "
                + yPosWGS+"\n Intensité : " + magnitude + "\n Data quality : " + Quality.qualityToString(dataQuality)+"\n");
        return  earthquakeInfo;
    }
    /**
     * Gets the year of the earthquake.
     * @return The year of the earthquake.
     */
    public int getYear() {
        // Extract the year from the date and return it as an integer
        String year = "";
        for(int i = 0; i < 4; ++i){
            year += date.charAt(i);
        }
        return Integer.parseInt(year);
    }
    /**
     * Gets the century of the earthquake.
     * @return The century of the earthquake.
     */
    public int getCentury() {
        // Calculate the century based on the year and return it
        return (int) Math.floor(getYear()/100);
    }
    /**
     * Gets the WGS84 longitude of the earthquake.
     * @return The WGS84 longitude of the earthquake.
     */
    public float getxPosWGS(){
        return xPosWGS;
    }
    /**
     * Gets the WGS84 latitude of the earthquake.
     * @return The WGS84 latitude of the earthquake.
     */
    public float getyPosWGS(){
        return yPosWGS;
    }

}

