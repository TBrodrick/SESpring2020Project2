package address;

import address.data.Address;
import address.data.AddressEntry;
import address.data.Name;
import javafx.geometry.HorizontalDirection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Ryan Johnson
 */
public class AddressBookApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {

        AddressBook ab = new AddressBook();
        ab.loadFromDatabase();
        inuit(ab);
        //Menu menu = new Menu();
        //menu.menuLoop(ab);
        //String file = "C:\\Users\\The_r\\OneDrive\\Desktop\\ImportantStuff\\AddressInputDataFile.txt";
        //init(file);
        //initAddressBookExercise(ab);
    }

    /**

    @param ab the addressbook used to input entries.
*/
    public static void initAddressBookExercise(AddressBook ab) throws SQLException, ClassNotFoundException
    {
        Name nam1 = new Name("A", "B");
        Name nam2 = new Name("C", "D");
        Address addr1 = new Address("E", "F", "G", 0);
        Address addr2 = new Address("H", "I", "J", 1);
        AddressEntry ae1 = new AddressEntry(nam1, addr1, "F", "G", 0);
        AddressEntry ae2 = new AddressEntry(nam2, addr2, "O", "P", 1);

        ab.add(ae1);
        ab.add(ae2);

        ab.list();
    }

    /**

    @param filename the name of the file
*/
    public static void init(String filename) throws SQLException, ClassNotFoundException
    {
        AddressBook ab = new AddressBook();
        ArrayList<String> al = new ArrayList<String>();
        try
        {
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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for(int i = 0; i < al.size()-8; i += 9)
        {
            Name nam = new Name(al.get(i), al.get(i+1));
            Address addr = new Address(al.get(i+2), al.get(i+3), al.get(i+4), Integer.parseInt(al.get(i+5)));
            AddressEntry ae = new AddressEntry(nam, addr, al.get(i+6), al.get(i+7), Integer.parseInt(al.get(i+8)));
            ab.add(ae);
        }

        ab.list();
    }

    public static void inuit(AddressBook ab) throws SQLException, ClassNotFoundException
    {
        JFrame f = new JFrame("Address Book");
        f.setSize(650,500);
        f.setVisible(true);
        f.setResizable(false);

        JPanel jp = new JPanel(new GridLayout(4, 4));
        f.add(jp);
        jp.setVisible(true);


        JButton addButton = new JButton("Add");
        JButton displayButton = new JButton("Display");
        JButton removeButton = new JButton("Remove");
        JButton findButton = new JButton("Find");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> displayList = new JList<String>(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(4);
        JScrollPane scrollDisplay = new JScrollPane(displayList);



        jp.add(addButton);
        jp.add(removeButton);
        jp.add(displayButton);
        jp.add(findButton);
        jp.add(scrollDisplay);

        addButton.setSize(125,100);
        addButton.setLocation(25,350);
        displayButton.setSize(125,100);
        displayButton.setLocation(170,350);
        removeButton.setSize(125,100);
        removeButton.setLocation(315,350);
        findButton.setSize(125, 100);
        findButton.setLocation(460, 350);
        scrollDisplay.setSize(525, 300);
        scrollDisplay.setLocation(25,25);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame addF = new JFrame();                   //consider internal frame
                addF.setSize(400,500);
                addF.setVisible(true);
                addF.setResizable(false);

                JPanel aPanel = new JPanel();
                aPanel.setLayout(new GridLayout(10,2, 5, 10));
                addF.add(aPanel);
                aPanel.setVisible(true);

                JLabel fl,ll,sl,cl,stl,zl,el,pl,il;
                JTextField ff,lf,sf,cf,stf,zf,ef,pf,idf;

                fl = new JLabel("First Name: ");
                ll = new JLabel("Last Name: ");
                sl = new JLabel("Street: ");
                cl = new JLabel("City: ");
                stl = new JLabel("State: ");
                zl = new JLabel("Zip: ");
                el = new JLabel("Email: ");
                pl = new JLabel("Phone Number: ");
                il = new JLabel("ID: ");

                ff = new JTextField();
                lf = new JTextField();
                sf = new JTextField();
                cf = new JTextField();
                stf = new JTextField();
                zf = new JTextField();
                ef = new JTextField();
                pf = new JTextField();
                idf = new JTextField();

                aPanel.add(fl);
                aPanel.add(ff);

                aPanel.add(ll);
                aPanel.add(lf);

                aPanel.add(sl);
                aPanel.add(sf);

                aPanel.add(cl);
                aPanel.add(cf);

                aPanel.add(stl);
                aPanel.add(stf);

                aPanel.add(zl);
                aPanel.add(zf);

                aPanel.add(el);
                aPanel.add(ef);

                aPanel.add(pl);
                aPanel.add(pf);

                aPanel.add(il);
                aPanel.add(idf);


                JButton subButton = new JButton("Submit");
                subButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        AddressEntry newEntry = new AddressEntry();

                        newEntry.setFirstName(ff.getText());
                        newEntry.setLastName(lf.getText());
                        newEntry.setStreet(sf.getText());
                        newEntry.setCity(cf.getText());
                        newEntry.setState(stf.getText());
                        newEntry.setZip(Integer.parseInt(zf.getText()));
                        newEntry.setEmail(ef.getText());
                        newEntry.setTelephone(pf.getText());
                        newEntry.setID(Integer.parseInt(idf.getText()));
                        listModel.clear();
                        boolean added = false;
                        try {
                            added = ab.add(newEntry);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if(added)
                            listModel.addElement("Add successful!");
                        else
                            listModel.addElement("That ID already exists.");
                        addF.setVisible(false);
                    }
                });
                Button exitButton = new Button("Cancel");
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a)
                    {
                        addF.setVisible(false);
                    }
                });

                aPanel.add(subButton);
                aPanel.add(exitButton);
                subButton.setSize(75,30);
                exitButton.setSize(75,30);
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent actionEvent) {
                listModel.clear();
                if(ab.size() != 0)
                {
                    ab.sort();
                    for(int i = 0; i < ab.size(); i++)
                    {
                        listModel.addElement(ab.show(i));
                    }
                }
                else listModel.addElement("The list is empty.\n");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[] line = listModel.getElementAt(displayList.getSelectedIndex()).split(",");
                    if(line.length < 9)
                    {
                        listModel.clear();
                        listModel.addElement("Click display to refresh the entries.");
                    }
                    else
                    {
                        String idString = line[8].replaceAll("[^0-9]", "");
                        ab.remove(ab.searchByID(Integer.parseInt(idString)));
                        listModel.clear();
                        listModel.addElement("Remove successful!");
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Uh oh! Remove failed!");
                }
            }
        });
        findButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFrame findF = new JFrame();                   //consider internal frame
            findF.setSize(300,200);
            findF.setVisible(true);
            findF.setResizable(false);

            JPanel aPanel = new JPanel();
            aPanel.setLayout(null);
            findF.add(aPanel);
            aPanel.setVisible(true);

            JLabel fl;
            JTextField ff;

            fl = new JLabel("Last Name: ");
            ff = new JTextField();

            Button searchButton = new Button("Search");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a)
                {
                    if(ff.getText().equals(""))
                    {
                        listModel.clear();
                        listModel.addElement("No results found.");
                    }
                    else
                    {
                        int[] results;
                        results = ab.searchByLast(ff.getText());
                        listModel.clear();
                        if(results.length == 0)
                        {
                            listModel.addElement("No results found.");
                        }
                        for(int i = 0; i < results.length; i++)
                        {
                            listModel.addElement(ab.show(results[i]));
                        }
                    }
                    findF.setVisible(false);
                }
            });

            fl.setLocation(25,25);
            fl.setSize(100, 25);
            aPanel.add(fl);
            ff.setLocation(150, 25);
            ff.setSize(125,25);
            aPanel.add(ff);
            searchButton.setSize(100, 25);
            searchButton.setLocation(100, 100);
            aPanel.add(searchButton);
        }
        });

        JScrollPane s = new JScrollPane();
        JScrollBar b = new JScrollBar();
        s.add(b);
        f.add(s);
    }
}
