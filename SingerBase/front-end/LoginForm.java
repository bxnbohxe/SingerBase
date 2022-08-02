package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm extends JDialog {
    JTextField tfEmail;
    JPasswordField pfPassword;
    JButton btnOK, btnCancel;
    JLabel lblEmail, lblPassword;
    JPanel loginPanel;

    public LoginForm() {
//        super(parent);
        setTitle("Login");
//        setContentPane(loginPanel);
//        setMinimumSize(new Dimension(450, 474));
        setModal(true);
//        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
        
//
//        setVisible(true);
        lblEmail = new JLabel();  
        lblEmail.setText("Username");      
          
        
        tfEmail = new JTextField(15);
        lblPassword = new JLabel();  
        lblPassword.setText("Password"); 
        
        pfPassword = new JPasswordField(15);
        btnOK = new JButton("SUBMIT");
        btnCancel = new JButton("CANCEL");
        loginPanel = new JPanel(new GridLayout(3, 1));  
        loginPanel.add(lblEmail);
        loginPanel.add(tfEmail);  
        loginPanel.add(lblPassword);
        loginPanel.add(pfPassword);   
        loginPanel.add(btnOK);
        loginPanel.add(btnCancel);
        add(loginPanel, BorderLayout.CENTER);
        btnOK.addActionListener(e -> {
            String email = tfEmail.getText();
            String password = String.valueOf(pfPassword.getPassword());

            user = getAuthenticatedUser(email, password);

            if (user != null) {
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(LoginForm.this,
                        "Email or Password Invalid",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        btnCancel.addActionListener(e -> dispose());

    }

    public User user;
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM sb_user WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("username");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("mobile_no");
//                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }

                    if (user != null) {
            System.out.println("Successful Authentication of: " + user.name);
            System.out.println("          Email: " + user.email);
            System.out.println("          Phone: " + user.phone);
//            System.out.println("          Address: " + user.address);
        }
        else {
            System.out.println("Authentication canceled");
        }
            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {
        try  
        {  
            //create instance of the LoginForm  
            LoginForm objLoginForm = new LoginForm();  
            objLoginForm.setSize(300,100);
            objLoginForm.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
//        JFrame objJFrame = new JFrame();
//        LoginForm loginForm = new LoginForm(null);
//        User user = loginForm.user;
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
