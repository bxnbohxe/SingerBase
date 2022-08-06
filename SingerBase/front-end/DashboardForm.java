package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class DashboardForm extends JFrame {
    JPanel dashboardPanel;
    JLabel lblAdmin;
    JButton btnLogin, btnRegister, btnSearchSinger, btnLogOut, btnSongs, btnExit;
    User curUser = new User();
    MySQLDB objMySQLDB = new MySQLDB();
    
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
        btnSongs = new JButton("MANAGE SONGS");
        btnRegister = new JButton("REGISTER");
        btnLogOut = new JButton("LOG-OUT");
        btnExit = new JButton("EXIT");
        dashboardPanel = new JPanel(new GridLayout(7, 1));
        dashboardPanel.add(lblAdmin);
        dashboardPanel.add(btnLogin);
        dashboardPanel.add(btnSearchSinger);
        dashboardPanel.add(btnSongs);
        dashboardPanel.add(btnRegister); 
        dashboardPanel.add(btnLogOut); 
        dashboardPanel.add(btnExit); 
        add(dashboardPanel, BorderLayout.CENTER);
        
        boolean hasRegistredUsers = connectToDatabase();
        if (hasRegistredUsers) {
            //show Login form
            LoginForm loginForm = new LoginForm(DashboardForm.this);
            User user = loginForm.user;

            if (user.user_id != null) {
                curUser = user;
                lblAdmin.setText("User: " + curUser.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                lblAdmin.setText("Please Login...!");
                dispose();
            }
        }
        else {
            //show Registration form
            RegistrationForm registrationForm = new RegistrationForm(this);
            User user = registrationForm.user;

            if (user.user_id != null) {
                curUser = user;
                lblAdmin.setText("User: " + curUser.name);
                setLocationRelativeTo(null);
                setVisible(true);
            }
            else {
                lblAdmin.setText("Please Register...!");
                dispose();
            }
        }
        btnExit.addActionListener(e -> { System.exit(0); });
        btnRegister.addActionListener(e -> { 
                RegistrationForm registrationForm = new RegistrationForm(DashboardForm.this);
                User user = registrationForm.user;

                if (user.user_id != null) {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "New user: " + user.name,
                            "Successful Registration",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        );
        btnLogOut.addActionListener(e -> { 
            if(curUser.user_id != null) { 
                curUser = new User();
                lblAdmin.setText("Please Login...!");
                JOptionPane.showMessageDialog(DashboardForm.this,
                            "LogOut Successfull...!",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
            }
                else {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "Login First...!","Message",
                            JOptionPane.ERROR_MESSAGE);
                }
                
            }
        );
        btnLogin.addActionListener(e -> { 
            if(curUser.user_id == null) { 
                LoginForm loginForm = new LoginForm(DashboardForm.this);
            User user = loginForm.user;

            if (user.user_id != null) {
                curUser = user;
                lblAdmin.setText("User: " + curUser.name);
                setLocationRelativeTo(null);
                setVisible(true);
            } else { lblAdmin.setText("Please Login...!"); }
            }
                else {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "You Are Already Logged In...!","Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        );
        btnSearchSinger.addActionListener(e -> { 
                if(curUser.user_id != null) { SearchSinger objSearchSinger = new SearchSinger(DashboardForm.this, curUser); }
                else {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "User Must Login To Search Singer","Login Failed",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        );
        btnSongs.addActionListener(e -> { 
                if(curUser.user_id != null) { ManageSongs objManageSongs = new ManageSongs(DashboardForm.this, curUser); }
                else {
                    JOptionPane.showMessageDialog(DashboardForm.this,
                            "User Must Login To Manage Songs","Login Failed",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        );
    }

    private boolean connectToDatabase() {
        boolean hasRegistredUsers = false;

        try{
            //First, connect to MYSQL server and create the database if not created
            objMySQLDB.ExecuteNonQueryAtRoot("CREATE DATABASE IF NOT EXISTS MyStore");

            //Second, connect to the database and create the table "users" if cot created
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
            objMySQLDB.ExecuteNonQuery(sql);
            //check if we have users in the table users
            ResultSet resultSet = objMySQLDB.ExecuteDataAdapter("SELECT COUNT(*) FROM SB_User");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }
        }catch(Exception e){ e.printStackTrace(); }
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
