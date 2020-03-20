
package address;

import address.data.Address;
import address.data.AddressEntry;
import address.data.Name;

import java.sql.*;

/**
 *
 * @author Ryan Johnson
 */
public class AddressBook {
    private AddressEntry[] addressEntryList;
    private static final String conString = "jdbc:oracle:thin:mcs1009/cMEY1Myo@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu";

    public void loadFromDatabase() throws SQLException, ClassNotFoundException
    {
        Class.forName ("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection(conString);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");
        int rows = 0;
        if (rset.last()) {
            rows = rset.getRow();
            rset.beforeFirst();
        }

        addressEntryList = new AddressEntry[rows];
        for(int i = 0; i < rows; i++)
        {
            rset.next();
            Name nam = new Name(rset.getString(1), rset.getString(2));
            Address addr = new Address(rset.getString(3), rset.getString(4), rset.getString(5), Integer.parseInt(rset.getString(6)));
            addressEntryList[i] = new AddressEntry(nam, addr, rset.getString(7), rset.getString(8), Integer.parseInt(rset.getString(9)));
        }

        rset.close();

        stmt.close();

        conn.close();

        sort();
    }

    public void addToDataBase(AddressEntry ae) throws SQLException, ClassNotFoundException
    {


        // Load the Oracle JDBC driver
        Class.forName ("oracle.jdbc.OracleDriver"); //name of driver may change w/ versions

        //check Oracle documentation online
        // Or could do DriverManager.registerDriver (new oracle.jdbc.OracleDriver());



        // Connect to the database
        // generic host url = jdbc:oracle:thin:login/password@host:port/SID for Oracle SEE Account INFO you
        // were given by our CS tech in an email ---THIS WILL BE DIFFERENT
        //jdbc:oracle:thin:@//adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu
        Connection conn = DriverManager.getConnection(conString);

        // Create a Statement
        Statement stmt = conn.createStatement ();


        // Select the all (*) from the table JAVATEST
        String entryString1 = "'" + ae.getFirstName() + "','" + ae.getLastName() + "','" + ae.getStreet() + "','" + ae.getCity() + "','" + ae.getState() + "'," + Integer.toString(ae.getZip()) + ",'" + ae.getTelephone() + "','" + ae.getEmail() + "'," + Integer.toString(ae.getID());
        String entryString2 = "INSERT INTO ADDRESSENTRYTABLE VALUES(" + entryString1 + ")";
        stmt.executeUpdate(entryString2);
        ResultSet rset = stmt.executeQuery("SELECT * FROM ADDRESSENTRYTABLE");



        System.out.println(rset);


        // Iterate through the result and print the employee names

        while (rset.next()) //get next row of table returned

        {

            for(int i=1; i<=rset.getMetaData().getColumnCount(); i++) //visit each column

                System.out.print(rset.getString(i) + " | ");

            System.out.println(" ");

            System.out.println("========================================");

        }



        //Close access to everything...will otherwise happen when disconnect

        // from database.

        rset.close();

        stmt.close();

        conn.close();

    }

    public void removeFromDatabase(int ID) throws SQLException, ClassNotFoundException
    {
        Class.forName ("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection(conString);
        Statement stmt = conn.createStatement();
        String removeString = "DELETE FROM ADDRESSENTRYTABLE WHERE ID = '" + ID + "'";
        stmt.executeUpdate(removeString);
        stmt.close();
        conn.close();
    }


    public void list()
    {
        for(int i = 0; i < addressEntryList.length; i++)
        {
            String text = addressEntryList[i].toString();
            System.out.println(text);
        }
    }

    /** method to add an address entry
     @param ae the address entry to be added
     */
    public boolean add(AddressEntry ae) throws SQLException, ClassNotFoundException {
        try
        {
            boolean uniqueID = true;
            for(int i = 0; i < addressEntryList.length; i++)
            {
                if(ae.getID() == addressEntryList[i].getID())
                {
                    uniqueID = false;
                }
            }
            if(uniqueID)
            {
                if(addressEntryList == null)
                {
                    addressEntryList = new AddressEntry[1];
                    addressEntryList[0] = ae;
                }
                else
                {
                    AddressEntry[] temp = addressEntryList;
                    addressEntryList = new AddressEntry[temp.length+1];
                    for(int i = 0; i < temp.length; i++)
                    {
                        addressEntryList[i] = temp[i];
                    }
                    addressEntryList[temp.length] = ae;
                }
                addToDataBase(ae);
                return true;
            }
            else
            {
                throw new Exception("You cannot add an already existing ID.");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }

    /**
     *
     * @param index of entry to be removed
     */
    public void remove(int index) throws SQLException, ClassNotFoundException
    {
        AddressEntry[] temp = addressEntryList;
        addressEntryList = new AddressEntry[temp.length-1];
        boolean found = false;
        for(int i = 0; i < temp.length; i++)
        {
            if(i == index)
            {
                found = true;
                removeFromDatabase(temp[i].getID());
            }
            else if(found)
            {
                addressEntryList[i - 1] = temp[i];
            }
            else
            {
                addressEntryList[i] = temp[i];
            }
        }

    }

    /**
     *
     * @param index of entry to be shown
     * @return the toString of the address entry
     */
    public String show(int index)
    {
        return addressEntryList[index].toString();
    }

    /**
     *
     * @param input is the last name to be searched
     * @return the indexes of the matching last names
     */
    public int[] searchByLast(String input)
    {
        int count = 0;
        for(int i = 0; i < addressEntryList.length; i++)
        {
            if(addressEntryList[i].getLastName().toLowerCase().startsWith(input.toLowerCase()))
            {
                count++;
            }
        }
        int[] indexes = new int[count];
        for(int i = 0; i < addressEntryList.length; i++)
        {
            count = 0;
            if(addressEntryList[i].getLastName().toLowerCase().startsWith(input.toLowerCase()))
            {
                indexes[count] = i;
                count++;
            }
        }

        return indexes;
    }

    /**
     *
     * @param input is the id to be searched
     * @return the indexes of the matching id
     */
    public int searchByID(int input)
    {
        for(int i = 0; i < addressEntryList.length; i++)
        {
            if(addressEntryList[i].getID() == input)
            {
                return i;
            }
        }
        return -1;
    }

    public void sort()
    {
        AddressEntry temp;
        for (int i = 0; i < addressEntryList.length; i++)
        {
            for (int j = i+1; j < addressEntryList.length; j++) {
                if (addressEntryList[i].getLastName().compareToIgnoreCase(addressEntryList[j].getLastName()) > 0) {
                    temp = addressEntryList[i];
                    addressEntryList[i] = addressEntryList[j];
                    addressEntryList[j] = temp;
                }
                else if(addressEntryList[i].getLastName().compareToIgnoreCase(addressEntryList[j].getLastName()) == 0)
                {
                    if(addressEntryList[i].getFirstName().compareToIgnoreCase(addressEntryList[j].getFirstName()) > 0)
                    {
                        temp = addressEntryList[i];
                        addressEntryList[i] = addressEntryList[j];
                        addressEntryList[j] = temp;
                    }
                }
            }
        }
    }

    public int size()
    {
        if(addressEntryList != null)
        return addressEntryList.length;
        return 0;
    }
}
