package address.data;

/**
 *
 * @author Ryan Johnson
 */
public class AddressEntry {

    private Name name;
    private Address address;
    private String Telephone;
    private String Email;
    private int ID;

    public AddressEntry()
    {
        name = new Name();
        address = new Address();
    }

    /**

    @param n Name (first and last)
     @param a Address
     @param t telephone number
     @param e email address
*/
    public AddressEntry(Name n, Address a, String t, String e, int i)
    {
        name = n;
        address = a;
        Telephone = t;
        Email = e;
        ID = i;
    }

    /**
     *
     * @param f First name
     * @param l Last name
     * @param str street
     * @param c city
     * @param sta state
     * @param z zip code
     * @param t telephone number
     * @param e email address
     * @param i ID
     */
    public AddressEntry(String f, String l, String str, String c, String sta, int z, String t, String e, int i)
    {
        name = new Name(f, l);
        address = new Address(str, c, sta, z);
        Telephone = t;
        Email = e;
        ID = i;
    }

    /**
     *
     * @return the string "result" representing the address entry
     */
    public String toString()
    {
        String result = "First Name: " + getFirstName() + ", Last Name: " + getLastName() + ", Street: "  + getStreet() + ", City: "  + getCity() + ", State: " + getState() + ", ZIP: " + getZip() + ", Telephone: " + getTelephone() + ", Email: " + getEmail() + ", ID: " + getID() + ".\n";
        return result;
    }

    /**
     *
     * @return string representing first name
     */
    public String getFirstName()
    {
        return name.getFirstName();
    }

    /**
     *
     * @return string representing last name
     */
    public String getLastName()
    {
        return name.getLastName();
    }

    /**
     *
     * @return string representing street
     */
    public String getStreet()
    {
        return address.getStreet();
    }

    /**
     *
     * @return string representing city
     */
    public String getCity()
    {
        return address.getCity();
    }

    /**
     *
     * @return string representing state
     */
    public String getState()
    {
        return address.getState();
    }

    /**
     *
     * @return int representing zip code
     */
    public int getZip()
    {
        return address.getZip();
    }

    /**
     *
     * @return string representing phone number
     */
    public String getTelephone()
    {
        return Telephone;
    }

    /**
     *
     * @return string representing email address
     */
    public String getEmail()
    {
        return Email;
    }

    /**
     *
     * @return int representing ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     *
     * @param input represents input for first name
     */
    public void setFirstName(String input)
    {
        name.setFirstName(input);
    }

    /**
     *
     * @param input represents input for last name
     */
    public void setLastName(String input)
    {
        name.setLastName(input);
    }

    /**
     *
     * @param input represents input for street
     */
    public void setStreet(String input)
    {
        address.setStreet(input);
    }

    /**
     *
     * @param input represents input for city
     */
    public void setCity(String input)
    {
        address.setCity(input);
    }

    /**
     *
     * @param input represents input for state
     */
    public void setState(String input)
    {
        address.setState(input);
    }

    /**
     *
     * @param input represents input for zip code
     */
    public void setZip(int input)
    {
        address.setZip(input);
    }

    /**
     *
     * @param input represents input for telephone number
     */
    public void setTelephone(String input)
    {
        Telephone = input;
    }

    /**
     *
     * @param input represents input for email
     */
    public void setEmail(String input)
    {
        Email = input;
    }

    /**
     *
     * @param input represents input for ID
     */
    public void setID(int input)
    {
        ID = input;
    }
}
