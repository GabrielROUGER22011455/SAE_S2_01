package EarthquakeJavaFX;

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
    private boolean showable;

    public Earthquake(int id, String date, String time, String name, String region,
                      Type type,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, Quality dataQuality) {
        // Constructor for earthquake will full informations
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.time = time;
        this.type = type;
    }
    public Earthquake(int id, String date, String name, String region,
                      Type type,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, Quality dataQuality) {
        // Constructor for earthquakes without information about the time
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.type = type;
    }

    public Earthquake(int id, String date, String time, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, Quality dataQuality){
        // Constructor for earthquakes without information about the type
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.time = time;
    }

    public Earthquake(int id, String date, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, Quality dataQuality){
        // Constructor for earthquakes without information about time and type
        this.id = id;
        this.date = date;
        this.name = name;
        this.region = region;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.dataQuality = dataQuality;
        showable = false;
    }
    public String getDate() {
        return date;
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
    public int getDecade() {
        return (int) Math.floor(getYear()/100);
    }
}

