package singerbase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog {
    JTextField tfUserName, tfEmail, tfPhone;
//    JTextField tfAddress;
    JPasswordField pfPassword, pfConfirmPassword;
    JLabel lblUserName, lblEmail, lblPhone, lblPassword, lblConfirmPassword;
    JButton btnRegister, btnCancel;
    JPanel loginPanel;

    public RegistrationForm() {
//        super(parent);
        setTitle("Create a new account");
//        setContentPane(registerPanel);
//        setMinimumSize(new Dimension(450, 474));
        setModal(true);
//        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblUserName = new JLabel();  
        lblUserName.setText("Username");      
        tfUserName = new JTextField(15);
        
        lblEmail = new JLabel();  
        lblEmail.setText("Email");      
        tfEmail = new JTextField(15);
        
        lblPhone = new JLabel();  
        lblPhone.setText("Mobile");      
        tfPhone = new JTextField(15);
        
        lblPassword = new JLabel();  
        lblPassword.setText("Password"); 
        pfPassword = new JPasswordField(15);
        
        lblConfirmPassword = new JLabel();  
        lblConfirmPassword.setText("Confirm Password"); 
        pfConfirmPassword = new JPasswordField(15);
        
        btnRegister = new JButton("REGISTER");
        btnCancel = new JButton("CANCEL");
        
        loginPanel = new JPanel(new GridLayout(6, 1));
        loginPanel.add(lblUserName);
        loginPanel.add(tfUserName); 
        loginPanel.add(lblEmail);
        loginPanel.add(tfEmail);  
        loginPanel.add(lblPhone);
        loginPanel.add(tfPhone);
        loginPanel.add(lblPassword);
        loginPanel.add(pfPassword);   
        loginPanel.add(lblConfirmPassword);
        loginPanel.add(pfConfirmPassword);
        loginPanel.add(btnRegister);
        loginPanel.add(btnCancel);
        add(loginPanel, BorderLayout.CENTER);
        
//        btnRegister.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                registerUser();
//            }
//        });
        btnRegister.addActionListener(e -> { registerUser(); });
        btnCancel.addActionListener(e -> dispose());

//        setVisible(true);
    }

    private void registerUser() {
        String name = tfUserName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
//        String address = tfAddress.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name, email, phone, password);
        if (user != null) {
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Failed to register new user",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String name, String email, String phone, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/MyStore?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            // Connected to database successfully...

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO sb_user (user_id, username, email, mobile_no, password) " +
                    "select (SELECT CONCAT('U', (count(*) + 1)) FROM sb_user), ?, ?, ?, ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
//            preparedStatement.setString(4, address);
            preparedStatement.setString(4, password);

            //Insert row into the table
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
//                user.address = address;
                user.password = password;
            }
            if (user != null) {
                System.out.println("Successful registration of: " + user.name);
            }
            else {
                System.out.println("Registration canceled");
            }

            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
//        RegistrationForm myForm = new RegistrationForm(null);
//        User user = myForm.user;
//        if (user != null) {
//            System.out.println("Successful registration of: " + user.name);
//        }
//        else {
//            System.out.println("Registration canceled");
//        }
    try  
        {  
            //create instance of the LoginForm  
            RegistrationForm objRegistrationForm = new RegistrationForm();  
            objRegistrationForm.setSize(500,200);
            objRegistrationForm.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            //handle exception   
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }
}
