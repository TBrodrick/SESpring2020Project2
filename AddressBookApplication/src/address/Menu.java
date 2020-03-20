package address;

import address.data.Address;
import address.data.AddressEntry;
import address.data.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Ryan Johnson
 */
public class Menu {

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_FirstName()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("First Name: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_LastName()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Last Name: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_Street()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Street: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_City()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("City: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_State()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("State: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static int prompt_Zip()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("ZIP: ");
        int output = Integer.valueOf(myObj.nextLine());
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_Telephone()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Telephone: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static String prompt_Email()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Email: ");
        String output = myObj.nextLine();
        return output;
    }

    /**
     *
     * @return answer to prompt
     */
    public static int prompt_ID()
    {
        Scanner myObj = new Scanner(System.in);
        System.out.println("ID: ");
        int output = Integer.valueOf(myObj.nextLine());
        return output;
    }

    /**
     *
     * @return the created addressbook
     */
    public static AddressBook loadFromFile() throws SQLException, ClassNotFoundException
    {
        AddressBook ab = new AddressBook();
        ArrayList<String> al = new ArrayList<String>();
        try
        {
            System.out.println("Please enter filename.\n");
            Scanner myObj0 = new Scanner(System.in);
            String filename = myObj0.nextLine();

            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
            {
                al.add(myReader.nextLine());
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist. Returning blank address book.\n");
            e.printStackTrace();
            AddressBook ab1 = new AddressBook();
            return ab1;
        }

        for(int i = 0; i < al.size()-8; i += 9)
        {
            Name nam = new Name(al.get(i), al.get(i+1));
            Address addr = new Address(al.get(i+2), al.get(i+3), al.get(i+4), Integer.parseInt(al.get(i+5)));
            AddressEntry ae = new AddressEntry(nam, addr, al.get(i+6), al.get(i+7), Integer.parseInt(al.get(i+8)));
            ab.add(ae);
        }

        return ab;
    }

    /**
     *
     * @param ab AddressBook to be manipulated
     * @return the updated address book
     */
    public static AddressBook addEntry(AddressBook ab)
    {
        try
        {
            Scanner myObj = new Scanner(System.in);
            String in1 = prompt_FirstName();
            String in2 = prompt_LastName();
            String in3 = prompt_Street();
            String in4 = prompt_City();
            String in5 = prompt_State();
            int in6 = prompt_Zip();
            String in7 = prompt_Telephone();
            String in8 = prompt_Email();
            int in9 = prompt_ID();

            Name nam = new Name(in1, in2);
            Address addr = new Address(in3, in4, in5, in6);
            AddressEntry ae = new AddressEntry(nam, addr, in7, in8, in9);
            ab.add(ae);
        }
        catch(Exception e)
        {
            System.out.println("Error adding entry.\n");
            return ab;
        }
        return ab;
    }

    /**
     *
     * @param ab AddressBook to be manipulated
     * @return the updated address book
     */
    public static AddressBook removeEntry(AddressBook ab) throws SQLException, ClassNotFoundException
    {
        // prompt for last name (or partial)
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter last name (or partial) to search for./n");
        String term = myObj.nextLine();

        // create a list of ints (indexes) to match the indexes of the entries
        int[] indexes = ab.search(term);

        if(indexes.length > 0)
        {
            System.out.println("Select which entry to delete.\n");
            for(int i = 0; i < indexes.length; i++)
            {
                System.out.println((i+1) + ": \n");
                ab.show(indexes[i]);
            }

            // prompt which to delete
            int choice = Integer.valueOf(myObj.nextLine());
            while(choice > indexes.length || choice < 1)
            {
                System.out.println("Invalid choice. Try again.\n");
                choice = Integer.valueOf(myObj.nextLine());
            }


            // show the one asked to be deleted again, asking y/n confirm
            ab.show(indexes[choice-1]);
            System.out.println("Confirm deletion by entering \"y\". Otherwise will continue without deleting.");

            String choice2 = myObj.nextLine();
            // delete it
            if(choice2.startsWith("y"))
            {
                ab.remove(indexes[choice-1]);
            }
        }
        else
            System.out.println("No entries found.");

        return ab;
    }

    /** method loops through a menu, giving and executing choices
     *
     * @param ab the addressbook used to input entries.
     */
    public static void menuLoop(AddressBook ab) throws SQLException, ClassNotFoundException
    {
        boolean quit = false;
        while(!quit)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("What would you like to do? Pick an option 1 through 6.\n1: Load entries from file.\n2: Add a new entry.\n3: Remove an entry.");
            System.out.println("4: Search for an entry.\n5: List the entries in alphabetical order.\n6: Quit.\n");

            int choice = Integer.valueOf(myObj.nextLine());
            while(choice < 1 || choice > 6)
            {
                System.out.println("Bad input. Try again, inputting your choice 1 through 6.\n");
                choice = Integer.valueOf(myObj.nextLine());
            }

            switch(choice) {
                case 1:
                    ab = loadFromFile();
                    break;
                case 2:
                    ab = addEntry(ab);
                    break;
                case 3:
                    ab = removeEntry(ab);
                    break;
                case 4:
                    System.out.println("Enter search string.\n");
                    String choice2 = myObj.nextLine();
                    int[] indexes = ab.search(choice2);
                    System.out.println("Found the following entries;\n");
                    for(int i = 0; i < indexes.length; i++)
                    {
                        ab.show(indexes[i]);
                    }
                    break;
                case 5:
                    ab.sort();
                    ab.list();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }
}
