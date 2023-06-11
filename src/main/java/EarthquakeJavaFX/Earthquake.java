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
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.region = region;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.dataQuality = dataQuality;
    }
    public Earthquake(int id, String date, String name, String region,
                      Type type,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.region = region;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.dataQuality = dataQuality;
    }

    public Earthquake(int id, String date, String time, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality){
        this.id = id;
        this.date = date;
        this.name = name;
        this.region = region;
        this.time = time;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.dataQuality = dataQuality;
    }

    public Earthquake(int id, String date, String name,
                      String region,float xPos, float yPos,
                      float  xPosWGS, float yPosWGS, float magnitude, String dataQuality){
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
    
    public Earthquake(){
    }

    public String typeToString(Type type){
        switch (type){
            case E:
                return "SECOUSSE INDIVIDUALISEE D UN ESSAIM";
            case P:
                return "PRECURSEUR";
            case R:
                return "REPLIQUE";
            case Z:
                return "GROUPE DE SECOUSSES D UN ESSAIM";
        }
        return null;
    }

    public Type stringToType(String str){
        switch (str){
            case "SECOUSSE INDIVIDUALISEE D UN ESSAIM":
                return Type.E;
            case "PRECURSEUR":
                return Type.P;
            case "REPLIQUE":
                return Type.R;
            case "GROUPE DE SECOUSSES D UN ESSAIM":
                return Type.Z;
        }
        return null;
    }

    @Override
    public String toString(){
        String earthquakeInfo = ("Id : " + id +"\n Date : "+ date + "\n Time : " + time
                + "\n Name : " + name +"\n Type : " + type + "\n Longitude RGF93/L93 : "+xPos+"\n Latitude RGF93/L93 "
                + yPos + "\n Longitude WSG84 : " + xPosWGS+"\n Latitude WSG94 : "
                + yPosWGS+"\n Intensité : " + magnitude + "\n Data quality : " + dataQuality+"\n");
        return  earthquakeInfo;
    }
}
