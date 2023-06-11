package EarthquakeJavaFX;

public class EarthquakeMap {
    private int year;
    private float xPosWGS;
    private float yPosWGS;
    private float magnitude;
    private boolean showable;
    public EarthquakeMap (){};
    public EarthquakeMap (int year, float xPosWGS, float yPosWGS, float magnitude, boolean showable){
        this.year = year;
        this.xPosWGS = xPosWGS;
        this.yPosWGS = yPosWGS;
        this.magnitude = magnitude;
        this.showable = showable;
    }
    public int getYear(){
        return year;
    }
    public float getxPosWGS(){
        return  xPosWGS;
    }
    public float getyPosWGS(){
        return  yPosWGS;
    }
    public boolean getShowable(){
        return showable;
    }
    public float getMagnitude(){
        return magnitude;
    }
    public void setShowable(boolean showableState){
        showable = showableState;
    }
    public int dateToYear(String date){
        String year = "";
        for(int i = 0; i < 4; ++i){
            year += date.charAt(i);
        }
        return Integer.parseInt(year);
    }
}
