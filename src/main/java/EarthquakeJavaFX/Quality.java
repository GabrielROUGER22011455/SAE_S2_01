package EarthquakeJavaFX;

public enum Quality {
    // About the quality of the earthquake information (how trustworthy it is)
    A, B, C, D, E;
    public static String qualityToString(Quality quality){
        switch (quality){
            case A:
                return "SURE";
            case B:
                return "ASSEZ SURE";
            case C:
                return "INCERTAINE";
            case D:
                return "INFORMATION ISOLÉE";
            case E:
                return "ARBITRAIRE";
        }
        return null;
    }
    public static Quality stringToQuality(String str){
        switch (str){
            case "SURE":
                return Quality.A;
            case "ASSEZ SURE":
                return Quality.A;
            case "INCERTAINE":
                return Quality.A;
            case "INFORMATION ISOLÉE":
                return Quality.A;
            case "ARBITRAIRE":
                return Quality.A;
        }
        return null;
    }
}
