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
    private String dataQuality;

    public Earthquake(int id, String date, String time, String name, String region,
                      Type type,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality) {
        // Constructor for earthquake will full informations
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.time = time;
        this.type = type;
    }
    public Earthquake(int id, String date, String name, String region,
                      Type type,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality) {
        // Constructor for earthquakes without information about the time
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.type = type;
    }

    public Earthquake(int id, String date, String time, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality){
        // Constructor for earthquakes without information about the type
        this(id, date, name, region, xPos, yPos, xPosWGS, yPosWGS, magnitude, dataQuality);
        this.time = time;
    }

    public Earthquake(int id, String date, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality){
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
    }
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public String getRegion(){
        return region;
    }
    public Type getType(){
        return type;
    }
    public float getXPos() {return xPos;}
    public float getYPos() {return yPos;}
    public float getXPosWGS(){return  xPosWGS;}
    public float getYPosWGS(){return yPosWGS;}
    public float getMagnitude(){return magnitude;}
    public String getDataQuality(){return dataQuality;}
    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + type + "\n Longitude RGF93/L93 : "+xPos+"\n Latitude RGF93/L93 "
                + yPos + "\n Longitude WSG84 : " + xPosWGS+"\n Latitude WSG94 : "
                + yPosWGS+"\n Intensité : " + magnitude + "\n Data quality : " + dataQuality+"\n");
        return  earthquakeInfo;
    }
}
