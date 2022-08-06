package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class LoginForm extends JDialog {
    JTextField tfEmail;
    JPasswordField pfPassword;
    JButton btnOK, btnCancel;
    JLabel lblEmail, lblPassword;
    JPanel loginPanel;
    MySQLDB objMySQLDB = new MySQLDB();

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
//        setContentPane(loginPanel);
        setMinimumSize(new Dimension(300, 100));
        setModal(true);
        setLocationRelativeTo(parent);
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
        setVisible(true);
    }

    public User user = new User();
    public User getAuthenticatedUser(String email, String password) {
        User user = new User();
        if(email.equals("") && password.equals("")) { return user; }

        try{
            ResultSet resultSet = objMySQLDB.ExecuteDataAdapter("SELECT * FROM sb_user WHERE email='" + email + "' AND password='" + password + "'");
            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("username");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("mobile_no");
                user.user_id = resultSet.getString("user_id");
                user.password = resultSet.getString("password");
            }

                    if (user.user_id != null) {
            System.out.println("Successful Authentication of: " + user.name);
            System.out.println("          Email: " + user.email);
            System.out.println("          Phone: " + user.phone);
//            System.out.println("          Address: " + user.address);
        }
        else {
            System.out.println("Authentication canceled");
        }
        }catch(Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args) {
        try  
        {  
            //create instance of the LoginForm  
            LoginForm objLoginForm = new LoginForm(null);  
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
