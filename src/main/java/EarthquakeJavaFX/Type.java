package EarthquakeJavaFX;

/**
 * The Type enum represents different types of earthquakes.
 * It provides methods for converting between Type enum values and their corresponding strings.
 */
public enum Type {
    /**
     * Represents an individual shock of an earthquake swarm.
     */
    E,

    /**
     * Represents a precursor earthquake.
     */
    P,

    /**
     * Represents a replica earthquake.
     */
    R,

    /**
     * Represents a group of shocks from an earthquake swarm.
     */
    Z,

    /**
     * Represents a null or unspecified type.
     */
    NULL;

    /**
     * Converts a Type enum value to its corresponding string representation.
     *
     * @param type the Type enum value
     * @return the string representation of the Type
     */
    public static String typeToString(Type type) {
        switch (type) {
            case E:
                return "SECOUSSE INDIVIDUALISEE D UN ESSAIM";
            case P:
                return "PRECURSEUR";
            case R:
                return "REPLIQUE";
            case Z:
                return "GROUPE DE SECOUSSES D UN ESSAIM";
            default:
                return "NON RENSEIGNE";
        }
    }

    /**
     * Converts a string representation to its corresponding Type enum value.
     *
     * @param str the string representation of the Type
     * @return the Type enum value
     */
    public static Type stringToType(String str) {
        switch (str) {
            case "SECOUSSE INDIVIDUALISEE D UN ESSAIM":
                return Type.E;
            case "PRECURSEUR":
                return Type.P;
            case "REPLIQUE":
                return Type.R;
            case "GROUPE DE SECOUSSES D UN ESSAIM":
                return Type.Z;
            default:
                return NULL;
        }
    }
}