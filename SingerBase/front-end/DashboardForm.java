package singerbase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JFrame {
    JPanel dashboardPanel;
    JLabel lblAdmin;
    JButton btnLogin, btnRegister, btnSearchSinger;

    public DashboardForm() {
        setTitle("Dashborad");
//        setModal(true);
//        setContentPane(dashboardPanel);
//        setMinimumSize(new Dimension(500, 429));
//        setSize(1200, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        lblAdmin = new JLabel();
        btnLogin = new JButton("LOGIN");
        btnSearchSinger = new JButton("SEARCH SINGER");
        btnRegister = new JButton("REGISTER");
        dashboardPanel = new JPanel(new GridLayout(4, 1));
        dashboardPanel.add(lblAdmin);
        dashboardPanel.add(btnLogin);
        dashboardPanel.add(btnSearchSinger);
        dashboardPanel.add(btnRegister); 
        add(dashboardPanel, BorderLayout.CENTER);
        
        boolean hasRegistredUsers = connectToDatabase();
        if (hasRegistredUsers) {
            //show Login form
            LoginForm loginForm = new LoginForm(DashboardForm.this);
            User user = loginForm.user;

            if (user != null) {
                lblAdmin.setText("User: " + user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        else {
            //show Registration form
            RegistrationForm registrationForm = new RegistrationForm(this);
            User user = registrationForm.user;

            if (user != null) {
                lblAdmin.setText("User: " + user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                dispose();
            }
        }
        
        btnRegister.addActionListener(e -> { 
                RegistrationForm registrationForm = new RegistrationForm(DashboardForm.this);
                User user = registrationForm.user;

                if (user != null) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user: " + user.name,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        );
        btnLogin.addActionListener(e -> { 
                LoginForm loginForm = new LoginForm(DashboardForm.this);
            User user = loginForm.user;

            if (user != null) {
                lblAdmin.setText("User: " + user.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            }
        );
       btnSearchSinger.addActionListener(e -> {

            if(!lblAdmin.getText().equals("User: ")) { 
                SearchSinger objSearchSinger = new SearchSinger(DashboardForm.this); }

            else {
                JOptionPane.showMessageDialog(DashboardForm.this,"User Must Login To Search Singer","Login Failed",JOptionPane.INFORMATION_MESSAGE);
            }
   

}

);
    }

    private boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        final String MYSQL_SERVER_URL = "jdbc:mysql://localhost/";
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS MyStore");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SB_User ("
                    + "user_id VARCHAR(5) NOT NULL,"
                    + "username VARCHAR(20) NOT NULL,"
                    + "password VARCHAR(20) NOT NULL,"
                    + "gender VARCHAR(10),"
                    + "email VARCHAR(50) NOT NULL,"
                    + "mobile_no CHAR(10) NOT NULL,"
                    + "country VARCHAR(30),"
                    + "PRIMARY KEY(user_id),"
                    + "FOREIGN KEY (country) REFERENCES Country (country_name))";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM SB_User");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }

    public static void main(String[] args) {
        
        try  
        {  
            //create instance of the LoginForm  
            DashboardForm objDashboardForm = new DashboardForm();
            objDashboardForm.setSize(500,200);
            objDashboardForm.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }
}
