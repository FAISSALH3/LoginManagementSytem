package com.Faissal;


import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class LoginSystem implements ActionListener {
    private JTable table;
    private DefaultTableModel model;
    private String[] columnNames;
    private JButton button;
    final String[] colNames = new String[]{"USER NAME", "PASSWORD"};
    LinkedList<USER> data = new LinkedList<>();
    final JFrame addFrame = new JFrame("Display users and their passwords");
    JFrame sf = new JFrame();
    JFrame frame = new JFrame();
    JButton AddnewUser = new JButton("Add user");
    JButton delete = new JButton("Delete");
    JButton search = new JButton("Search");
    JButton showUsers = new JButton("users");
    JButton save = new JButton("save");
    JButton Load = new JButton("load");
    JLabel logo = new JLabel();
    JLabel logo2 = new JLabel();
    JLabel title2 = new JLabel();
    final String FILE_NAME = "C:\\Users\\lenovo\\Desktop\\users.data";  //the data file location
    JTextField userIDField = new JTextField();
    JLabel userIDLabel = new JLabel();
    JLabel messageLabel = new JLabel();


    public LoginSystem() {

        //Interface design
        frame.setBackground(Color.DARK_GRAY);
        frame.setTitle("Wuhan university of technology");
        logo2.setIcon(new ImageIcon(getClass().getResource("/com/Faissal/head_logo.png")));
        logo.setIcon(new ImageIcon(getClass().getResource("/com/Faissal/BackGround.png")));

        frame.setSize(540, 400);
        frame.setResizable(true);
        frame.setLayout(null);

        frame.setVisible(true);
        frame.add(title2); // title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        delete.setFocusable(false);
        search.setFocusable(false);
        search.addActionListener(this);
        title2.setText("Users management system");
        title2.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));

        title2.setBounds(160, 150, 200, 20);
        title2.setVisible(true);


        search.addActionListener(this);



        title2.setVisible(true);

        frame.setBackground(Color.DARK_GRAY);
        frame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        frame.setTitle("Wuhan university of technology");
        frame.setVisible(true);

        //shapes sizes
        logo2.setBounds(170, 70, 250, 50);
        logo.setBounds(0, -100, 1800, 600);
        delete.setBounds(210, 200, 100, 25);
        save.setBounds(310, 200, 100, 25);
        showUsers.setBounds(110, 200, 100, 25);
        AddnewUser.setBounds(110, 250, 100, 25);
        search.setBounds(210, 250, 100, 25);
        Load.setBounds(310, 250, 100, 25);


        frame.add(title2); // title

        //Action listners
        delete.addActionListener(this);
        search.addActionListener(this::actionPerformed);
        Load.addActionListener(this::actionPerformed);
        showUsers.addActionListener(this::actionPerformed);
        save.addActionListener(this::actionPerformed);
        AddnewUser.addActionListener(this);


        //Buttons

        frame.add(delete);
        frame.add(AddnewUser);
        frame.add(search);
        frame.add(showUsers);
        frame.add(save);
        frame.add(Load);
        frame.add(logo2);
        frame.add(logo);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 400);
        frame.setResizable(true);
        frame.setLayout(null);
        frame.setVisible(true);

    }


    protected void ShowUsers() {

        //Interface Design
        final JFrame showFrame = new JFrame("Display users and their passwords");

        showFrame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        showFrame.setTitle("Wuhan university of technology");
        showFrame.setLayout(new BorderLayout());
        showFrame.setBounds(250, 250, 550, 350);


        final String[][] datax = new String[this.data.size()][2];
        int i = -1;

        USER u;
        for (Iterator ptr = this.data.iterator(); ptr.hasNext(); datax[i][1] = u.getPassword()) {
            ++i;
            u = (USER) ptr.next();
            datax[i][0] = u.getName();
        }

        JTable jtab = new JTable(datax, this.colNames);//creates a new table with its data (username and password)
        JScrollPane jsp = new JScrollPane(jtab);
        showFrame.setLayout(new BorderLayout());
        showFrame.add(jsp, "Center");
        showFrame.setBounds(250, 250, 550, 350);
        showFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                showFrame.dispose();
            }
        });

        showFrame.setVisible(true);
    }

    protected void addUser() {
        logo.setIcon(new ImageIcon(getClass().getResource("/com/Faissal/BackGround.png")));
        addFrame.setBackground(Color.DARK_GRAY);
        addFrame.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        addFrame.setTitle("Wuhan university of technology");
        addFrame.setVisible(true);


        String[][] datax = new String[][]{{"Input user name", "Input password"}};
        JButton reset = new JButton("Reset");
        JButton submit = new JButton("Submit");

        final JTable jtab = new JTable(datax, this.colNames);
        JScrollPane jsp = new JScrollPane(jtab);
        addFrame.setLayout(new BorderLayout());
        addFrame.add(jsp, "Center");
        JPanel jp = new JPanel();
        jp.add(reset);
        jp.add(submit);
        addFrame.add(jp, "South");


        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel tabMod = jtab.getModel();
                tabMod.setValueAt("", 0, 0);
                tabMod.setValueAt("", 0, 1);
            }
        });


        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel modelTab = jtab.getModel();
                String name = (String) modelTab.getValueAt(0, 0);//Get String name
                String password = (String) modelTab.getValueAt(0, 1); //get Password
                USER u = new USER(name, password);
                LoginSystem.this.data.add(u);//Add user to the linkedList

                MessageBox("User Added ! ");


            }
        });
        addFrame.setBounds(250, 250, 550, 350);
        addFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                addFrame.dispose();
            }
        });

        addFrame.setVisible(true);
    }

    public void MessageBox(String message) { //a floating box to notify the user
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, message);
    }

    protected void saveData() throws Exception {
        FileOutputStream fos = new FileOutputStream(this.FILE_NAME);
        ObjectOutputStream objectoStream = new ObjectOutputStream(fos);
        objectoStream.writeObject(LoginSystem.this.data);
        objectoStream.flush();
        objectoStream.close();
        MessageBox("Data saved successfully");
    }

    public void deleteUser() {

        JFrame delete = new JFrame();

        delete.setTitle("Delete users");
        String[][] datax = new String[this.data.size()][2];
        int i = -1;

        USER user;
        for (Iterator ptr = LoginSystem.this.data.iterator(); ptr.hasNext(); datax[i][1] = user.getPassword()) {
            ++i;
            user = (USER) ptr.next();
            datax[i][0] = user.getName();
        }//get name and passwords stored in the linked list

        columnNames = new String[]{"Username", "Password"};
        model = new DefaultTableModel(datax, columnNames);
        table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        button = new JButton("Remove");
        delete.add(new JScrollPane(table), BorderLayout.CENTER);
        delete.add(button, BorderLayout.SOUTH);
        delete.setSize(400, 300);
        delete.setLocationRelativeTo(null);

        delete.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (table.getSelectedRow() != -1) {
                    // remove selected row from the model
                    TableModel tm = table.getModel();
                    int row = table.getSelectedRow();


                    String name = (String) tm.getValueAt(row, 0);//return selected username
                    LoginSystem.this.data.remove(searchMethod(name));//removes the user
                    model.removeRow(table.getSelectedRow());//removes the selected row


                    MessageBox("user deleted successfully");
                }
            }
        });

    }


    public void search() {
        //This method will search for the user by its username

        //Design interface
        sf.setTitle("Search for users");
        JButton search2 = new JButton("search");

        logo.setIcon(new ImageIcon(getClass().getResource("/com/Faissal/BackGround.png")));

        search2.setBounds(50, 150, 75, 25);
        JLabel label = new JLabel("Search for Users");
        label.setBounds(150, 85, 200, 50);
        label.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        userIDField.setBounds(125, 150, 200, 25);
        sf.add(userIDLabel);
        sf.add(label);
        sf.add(search2);
        sf.add(messageLabel);
        sf.add(userIDField);
        sf.setResizable(true);

        sf.setSize(420, 300);
        sf.add(logo);
        logo.setSize(420, 600);


        sf.setVisible(true);


        //Action Listeneer for Search button
        search2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                USER foundPosition = searchMethod(userIDField.getText());//it will return null if it doesn't exist

                if (foundPosition != null) {
                    MessageBox("user Found !");
                } else {
                    MessageBox("user doesn't exist !");
                }
            }
        });

    }

    private USER searchMethod(String username) {//  Method to search for a User in the linked list

        for (USER u : this.data) {
            if (u.getName().equals(username)) {
                return u;
            }
        }
        return null; //it search for the username , if there is a user with that username, it will return it , otherwise it will return null
    }


    public void Load(java.awt.event.ActionEvent evt) throws IOException, ClassNotFoundException {
        //To Load Data from file
        FileInputStream fis = new FileInputStream(this.FILE_NAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        LinkedList oldData = (LinkedList) ois.readObject();
        LoginSystem.this.data.addAll(oldData);
        ois.close();
        MessageBox("Data Loaded !");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showUsers) {
            ShowUsers();//the users will be shown
        }
        if (e.getSource() == save) {
            try {
                saveData();//data will be saved
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (e.getSource() == AddnewUser) {
            addUser();//to add user
        }

        if (e.getSource() == delete) {
            //delete();
            deleteUser();
        }
        if (e.getSource() == search) {
            search();
        }
        if (e.getSource() == Load) {
            try {
                Load(e);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
