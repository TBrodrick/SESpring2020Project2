package com.company;

/**
 *
 * @author Ryan Johnson
 */
public class AddressEntry {

    private String FirstName;
    private String LastName;
    private String Street;
    private String City;
    private String State;
    private int ZIP;
    private String Telephone;
    private String Email;

    public AddressEntry()
    {

    }

    /**

    @param fn first name
     @param ln last name
     @param str street
     @param c city
     @param sta state
     @param z ZIP
     @param t telephone number
     @param e email address
*/
    public AddressEntry(String fn, String ln, String str, String c, String sta, int z, String t, String e)
    {
        FirstName = fn;
        LastName = ln;
        Street = str;
        City = c;
        State = sta;
        ZIP = z;
        Telephone = t;
        Email = e;
    }

    /**
     *
     * @return the string "result" representing the address entry
     */
    public String toString()
    {
        String result = "First Name: " + getFirstName() + ", Last Name: " + getLastName() + ", Street: "  + getStreet() + ", City: "  + getCity() + ", State: " + getState() + ", ZIP: " + getZip() + ", Telephone: " + getTelephone() + ", Email: " + getEmail() + ".\n";
        return result;
    }

    /**
     *
     * @return string representing first name
     */
    public String getFirstName()
    {
        return FirstName;
    }

    /**
     *
     * @return string representing last name
     */
    public String getLastName()
    {
        return LastName;
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
     * @param input represents input for first name
     */
    public void setFirstName(String input)
    {
        FirstName = input;
    }

    /**
     *
     * @param input represents input for last name
     */
    public void setLastName(String input)
    {
        LastName = input;
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
}
