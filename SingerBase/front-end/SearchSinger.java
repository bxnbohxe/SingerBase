/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
/**
 *
 * @author Lenovo
 */
public class SearchSinger extends JDialog {
    JTextField tfSearchString;
    JTextArea tfResults;
    JButton btnSearch, btnCancel;
    JLabel lblSearchString, lblResults;
    JPanel searchPanel;

    public SearchSinger(JFrame parent) {
        super(parent);
        setTitle("Search Singer");
//        setContentPane(loginPanel);
        setMinimumSize(new Dimension(700,200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
        
//
//        setVisible(true);
        tfSearchString = new JTextField(15);
        lblSearchString = new JLabel();  
        lblSearchString.setText("Search String");      
          
        
//        tfEmail = new JTextField(15);
//        lblPassword = new JLabel();  
//        lblPassword.setText("Password"); 
//        
//        pfPassword = new JPasswordField(15);

        btnSearch = new JButton("SEARCH");
        btnCancel = new JButton("CANCEL");
        
        tfResults = new JTextArea(5, 30);
        tfResults.setEditable(false);
        lblResults = new JLabel();  
        lblResults.setText("Results");      
        
        searchPanel = new JPanel(new GridLayout(3, 1));  
        searchPanel.add(lblSearchString);
        searchPanel.add(tfSearchString);  
        searchPanel.add(btnSearch);
        searchPanel.add(btnCancel);   
        searchPanel.add(lblResults);
        searchPanel.add(tfResults);
        add(searchPanel, BorderLayout.CENTER);
        
        btnSearch.addActionListener(e -> {
            String searchString = tfSearchString.getText();
            if(searchString.isEmpty()){
                JOptionPane.showMessageDialog(SearchSinger.this,
                        "Search String Cannot Be Blank",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                String results = getSearchResults(searchString);
                if (results.isEmpty()) { JOptionPane.showMessageDialog(SearchSinger.this,
                        "No Matching Record Found",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE); }
                else {
                    tfResults.setText(results);
                }
            }
        });
        btnCancel.addActionListener(e -> dispose());
        setVisible(true);
    }

    private String getSearchResults(String searchString) {
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";
        String strResult = ""; 
        String strCurRow = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM singer WHERE fname like '%" + searchString + "%' or lname like '%" + searchString + "%' or stage_name like '%" + searchString + "%'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                strCurRow = resultSet.getString("fname") + " " + resultSet.getString("lname") + " (" + resultSet.getString("stage_name") +  ")";
                if(resultSet.getString("gender").equals("f")) { strCurRow += " | Female"; } else if(resultSet.getString("gender").equals("m")) { strCurRow += " | Male"; }
                strCurRow += " | " + resultSet.getString("country");
                strResult += strCurRow + "" + "\n";
            }

            stmt.close();
            conn.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }


        return strResult;
    }

    public static void main(String[] args) {
        try  
        {  
            //create instance of the SearchSinger  
            SearchSinger objSearchSinger = new SearchSinger(null);  
            objSearchSinger.setSize(500,300);
            objSearchSinger.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
//        JFrame objJFrame = new JFrame();
//        SearchSinger objSearchSinger = new SearchSinger(null);
//        User user = objSearchSinger.user;
//        if (user != null) {
//            System.out.println("Successful Authentication of: " + user.name);
//            System.out.println("          Email: " + user.email);
//            System.out.println("          Phone: " + user.phone);
//            System.out.println("          Address: " + user.address);
//        }
//        else {
//            System.out.println("Authentication canceled");
//        }
    }
}
