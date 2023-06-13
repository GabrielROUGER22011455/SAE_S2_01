package EarthquakeJavaFX;

public enum Type {
    // About the type of the earthquake
    P,R,E,Z,NULL;
    public static String typeToString(Type type){
        switch (type){
            case E:
                return "SECOUSSE INDIVIDUALISEE D UN ESSAIM";
            case P:
                return "PRECURSEUR";
            case R:
                return "REPLIQUE";
            case Z:
                return "GROUPE DE SECOUSSES D UN ESSAIM";
            default :
                return "null";
        }
    }
    public static Type stringToType(String str){
        switch (str){
            case "SECOUSSE INDIVIDUALISEE D UN ESSAIM":
                return Type.E;
            case "PRECURSEUR":
                return Type.P;
            case "REPLIQUE":
                return Type.R;
            case "GROUPE DE SECOUSSES D UN ESSAIM":
                return Type.Z;
            default :
                return NULL;
        }
    }
}
