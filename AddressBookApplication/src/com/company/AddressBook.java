
package com.company;

/**
 *
 * @author Ryan Johnson
 */
public class AddressBook {
    private AddressEntry[] addressEntryList;

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
    public void add(AddressEntry ae)
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
    }

    /**
     *
     * @param index of entry to be removed
     */
    public void remove(int index)
    {
        AddressEntry[] temp = addressEntryList;
        addressEntryList = new AddressEntry[temp.length-1];
        boolean found = false;
        for(int i = 0; i < temp.length; i++)
        {
            if(i == index)
            {
                found = true;
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
    public int[] search(String input)
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
