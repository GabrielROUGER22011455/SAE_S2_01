package EarthquakeJavaFX;

import java.util.ArrayList;

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

    public boolean isShown() {
        // Only earthquake with show = true are considered in any methods
        return show;
    }
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
    public String getRegion(){
        return region;
    }
    public Type getType(){
        return type;
    }
    public float getMagnitude(){return magnitude;}
    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + Type.typeToString(type) + "\n Longitude RGF93/L93 : "+xPos+"\n Latitude RGF93/L93 "
                + yPos + "\n Longitude WSG84 : " + xPosWGS+"\n Latitude WSG94 : "
                + yPosWGS+"\n Intensité : " + magnitude + "\n Data quality : " + Quality.qualityToString(dataQuality)+"\n");
        return  earthquakeInfo;
    }
    public int getYear() {
        String year = "";
        for(int i = 0; i < 4; ++i){
            year += date.charAt(i);
        }
        return Integer.parseInt(year);
    }
    public int getCentury() {
        return (int) Math.floor(getYear()/100);
    }

    public float getxPosWGS(){
        return xPosWGS;
    }

    public float getyPosWGS(){
        return yPosWGS;
    }
}

