package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
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
    public static void main(String[] args) {

        AddressBook ab = new AddressBook();
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
    public static void initAddressBookExercise(AddressBook ab)
    {
        AddressEntry ae1 = new AddressEntry("A", "B", "C", "D", "E", 0, "F", "G");
        AddressEntry ae2 = new AddressEntry("H", "I", "J", "K", "L", 1, "O", "P");

        ab.add(ae1);
        ab.add(ae2);

        ab.list();
    }

    /**

    @param filename the name of the file
*/
    public static void init(String filename)
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

        for(int i = 0; i < al.size()-7; i += 8)
        {
            AddressEntry ae = new AddressEntry(al.get(i), al.get(i+1), al.get(i+2), al.get(i+3), al.get(i+4), Integer.parseInt(al.get(i+5)), al.get(i+6), al.get(i+7));
            ab.add(ae);
        }

        ab.list();
    }

    public static void inuit(AddressBook ab)
    {
        JFrame f = new JFrame("Address Book");
        f.setSize(500,600);
        f.setVisible(true);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,1,5,2));
        f.add(jp);
        jp.setVisible(true);

        MenuBar myBar = new MenuBar();
        f.setMenuBar(myBar);
        java.awt.Menu fileMenu = new java.awt.Menu("File");
        java.awt.Menu editMenu = new java.awt.Menu("Edit");
        java.awt.Menu helpMenu = new java.awt.Menu("Help");
        myBar.add(fileMenu);
        myBar.add(editMenu);
        myBar.add(helpMenu);

        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileClose = new MenuItem("Close");
        fileMenu.add(fileSave);
        fileMenu.add(fileClose);

        JButton addButton = new JButton("Add");
        JButton displayButton = new JButton("Display");
        JButton removeButton = new JButton("Remove");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> displayList = new JList<String>(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(4);
        JScrollPane scrollDisplay = new JScrollPane(displayList);



        jp.add(addButton);
        jp.add(removeButton);
        jp.add(displayButton);
        jp.add(scrollDisplay);

        addButton.setSize(450,50);
        addButton.setLocation(25,25);
        displayButton.setSize(450,50);
        displayButton.setLocation(25,75);
        removeButton.setSize(450,50);
        removeButton.setLocation(25,125);
        scrollDisplay.setSize(450, 50);
        scrollDisplay.setLocation(25,175);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame addF = new JFrame();                   //consider internal frame
                addF.setLayout(new BorderLayout());
                addF.setSize(400,500);
                addF.setVisible(true);

                JPanel aPanel = new JPanel();
                aPanel.setLayout(new GridLayout(9,2));
                addF.add(aPanel);
                aPanel.setVisible(true);

                JLabel fl,ll,sl,cl,stl,zl,el,pl;
                JTextField ff,lf,sf,cf,stf,zf,ef,pf;

                fl = new JLabel("First Name: ");
                ll = new JLabel("Last Name: ");
                sl = new JLabel("Street: ");
                cl = new JLabel("City: ");
                stl = new JLabel("State: ");
                zl = new JLabel("Zip: ");
                el = new JLabel("Email: ");
                pl = new JLabel("Phone Number: ");

                ff = new JTextField();
                lf = new JTextField();
                sf = new JTextField();
                cf = new JTextField();
                stf = new JTextField();
                zf = new JTextField();
                ef = new JTextField();
                pf = new JTextField();

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

                        ab.add(newEntry);
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
                    if(ab.size() > displayList.getSelectedIndex())
                    ab.remove(displayList.getSelectedIndex());
                    else
                    {
                        listModel.clear();
                        if(ab.size() == 0)
                        {
                            listModel.addElement("The list is empty!");
                        }
                        else
                        listModel.addElement("List element not found!");
                    }

                }
                catch(Exception e)
                {
                    System.out.println("Uh oh! Remove failed!");
                }
            }
        });

        JScrollPane s = new JScrollPane();
        JScrollBar b = new JScrollBar();
        s.add(b);
        f.add(s);
    }
}
