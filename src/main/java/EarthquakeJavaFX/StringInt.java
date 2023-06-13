package EarthquakeJavaFX;

/**
 * The StringInt class represents a string paired with an integer value.
 * It is used in the separateString function in the EarthQuakeViewModel class.
 * Note: This class may be removed in the future if a better solution is found.
 */
public class StringInt {
    /**
     * The string value.
     */
    private String str;
    /**
     * The integer value.
     */
    private int nbr;
    /**
     * Constructs a StringInt object with the specified string and integer values.
     *
     * @param str the string value
     * @param nbr the integer value
     */
    public StringInt (String str, int nbr){
        this.str = str;
        this.nbr = nbr;
    }
    /**
     * Returns the string value.
     *
     * @return the string value
     */
    public String getStr() {return str;}
    /**
     * Returns the integer value.
     *
     * @return the integer value
     */
    public int getNbr() {return nbr;}
}
