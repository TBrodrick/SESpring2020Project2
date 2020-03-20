package address.data;

public class Address {
    private String Street;
    private String City;
    private String State;
    private int ZIP;

    public Address()
    {

    }

    /**
     *
     * @param str Street
     * @param c City
     * @param sta State
     * @param z ZIP
     */
    public Address(String str, String c, String sta, int z)
    {
        Street = str;
        City = c;
        State = sta;
        ZIP = z;
    }

    /**
     *
     * @return string representing street
     */
    public String getStreet()
    {
        return Street;
    }

    /**
     *
     * @return string representing city
     */
    public String getCity()
    {
        return City;
    }

    /**
     *
     * @return string representing state
     */
    public String getState()
    {
        return State;
    }

    /**
     *
     * @return int representing zip code
     */
    public int getZip()
    {
        return ZIP;
    }

    /**
     *
     * @param input represents input for street
     */
    public void setStreet(String input)
    {
        Street = input;
    }

    /**
     *
     * @param input represents input for city
     */
    public void setCity(String input)
    {
        City = input;
    }

    /**
     *
     * @param input represents input for state
     */
    public void setState(String input)
    {
        State = input;
    }

    /**
     *
     * @param input represents input for zip code
     */
    public void setZip(int input)
    {
        ZIP = input;
    }
}
