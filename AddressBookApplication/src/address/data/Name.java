package address.data;

public class Name {

    private String FirstName;
    private String LastName;

    public Name()
    {

    }

    /**
     *
     * @param first First name
     * @param last Last name
     */
    public Name(String first, String last)
    {
        FirstName = first;
        LastName = last;
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
     * @param f represents input for first name
     */
    public void setFirstName(String f)
    {
        FirstName = f;
    }

    /**
     *
     * @param l represents input for last name
     */
    public void setLastName(String l)
    {
        LastName = l;
    }

}
