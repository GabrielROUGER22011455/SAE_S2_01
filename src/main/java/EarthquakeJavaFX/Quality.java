package EarthquakeJavaFX;

/**
 * The Quality enum represents different qualities of earthquake information, indicating their trustworthiness.
 * It provides methods for converting between Quality enum values and their corresponding strings.
 */
public enum Quality {

    /**
     * Represents a highly reliable and certain quality of earthquake information.
     */
    A,

    /**
     * Represents a fairly reliable quality of earthquake information.
     */
    B,

    /**
     * Represents an uncertain quality of earthquake information.
     */
    C,

    /**
     * Represents an isolated or limited information about the earthquake.
     */
    D,

    /**
     * Represents an arbitrary or unreliable quality of earthquake information.
     */
    E,

    /**
     * Represents a null or unspecified quality.
     */
    NULL;

    /**
     * Converts a Quality enum value to its corresponding string representation.
     *
     * @param quality the Quality enum value
     * @return the string representation of the Quality
     */
    public static String qualityToString(Quality quality) {
        switch (quality) {
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
            default:
                return "null";
        }
    }

    /**
     * Converts a string representation to its corresponding Quality enum value.
     *
     * @param str the string representation of the Quality
     * @return the Quality enum value
     */
    public static Quality stringToQuality(String str) {
        switch (str) {
            case "SURE":
                return Quality.A;
            case "ASSEZ SURE":
                return Quality.B;
            case "INCERTAINE":
                return Quality.C;
            case "INFORMATION ISOLÉE":
                return Quality.D;
            case "ARBITRAIRE":
                return Quality.E;
            default:
                return NULL;
        }
    }
}