
package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class SearchSinger extends JDialog {
    JTextField tfSearchString;
    JButton btnSearch, btnCancel, btnFanSingerList;
    JLabel lblSearchString;
    JPanel searchPanel;
    ResultSet rs = null;
    User curUser = new User();
    MySQLDB objMySQLDB = new MySQLDB();

    public SearchSinger(JFrame parent, User user) {
        super(parent);
        curUser = user;
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
        btnFanSingerList = new JButton("FAN SINGERS LIST");
        
        searchPanel = new JPanel(new GridLayout(3, 1));  
        searchPanel.add(lblSearchString);
        searchPanel.add(tfSearchString);  
        searchPanel.add(btnSearch);
        searchPanel.add(btnCancel);   
        searchPanel.add(btnFanSingerList);   
        add(searchPanel, BorderLayout.CENTER);
        btnFanSingerList.addActionListener(e -> {
            String searchString = tfSearchString.getText();
            
                rs = objMySQLDB.ExecuteDataAdapter("SELECT TAB1.*, coalesce(TAB2.SID, 'NO') as fan_status FROM singer as TAB1 left join (SELECT * FROM user_fans_singer_junction where user_id = '" + curUser.user_id.toString() + "') as TAB2 on TAB1.SID = TAB2.SID WHERE TAB2.SID is not null and fname like '%" + searchString + "%' or lname like '%" + searchString + "%' or stage_name like '%" + searchString + "%' having fan_status is not null and fan_status != 'NO' order by fname, lname, stage_name;");
                if (rs == null) { JOptionPane.showMessageDialog(SearchSinger.this,
                        "No Matching Record Found",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE); }
                else {
                    JFrame frame1 = new JFrame("Singers Search List");
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"SID", "fan_status", "fname", "lname", "stage_name", "gender", "country", "group_name"};
        model.setColumnIdentifiers(columnNames);
        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
        //table = new JTable(model);
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String SID = "";
        String fname = "";
        String lname = "";
        String stage_name = "";
        String gender = "";
        String country = "";
        String group_name = "";
        String fan_status = "NO";
        table.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            try{
                if(model.getValueAt(row, 1).toString().equals("YES")) {
                    String sql = "delete from user_fans_singer_junction where user_id = '" + curUser.user_id + "' and SID = '" + model.getValueAt(row, 0) + "'";
                    objMySQLDB.ExecuteNonQuery(sql);
                    model.setValueAt("NO", row, 1);
                }
                else {
                    String sql = "insert into user_fans_singer_junction values('" + curUser.user_id + "','" + model.getValueAt(row, 0) + "');";
                    objMySQLDB.ExecuteNonQuery(sql);
                    model.setValueAt("YES", row, 1);
                }
            }catch(Exception e){ e.printStackTrace(); }
            
//            System.out.println(model.getValueAt(row, col));
//            model.setValueAt("Clicked", row, col);
        }
    }
});
        try {
            int i = 0;
            
            while(rs.next()) {
                SID = rs.getString("SID");
                if(SID != null) { SID = SID.toUpperCase(); } else { SID = ""; }
                fan_status = rs.getString("fan_status");
                if(!fan_status.equals("NO")) { fan_status = "YES"; }
                fname = rs.getString("fname");
                if(fname != null) { fname = fname.toUpperCase(); } else { fname = ""; }
                lname = rs.getString("lname");
                if(lname != null) { lname = lname.toUpperCase(); } else { lname = ""; }
                stage_name = rs.getString("stage_name");
                if(stage_name != null) { stage_name = stage_name.toUpperCase(); } else { stage_name = ""; }
                gender = rs.getString("gender");
                if(gender.toUpperCase().equals("F")) { gender = "Female"; } else { gender = "Male"; }
                if(gender != null) { gender = gender.toUpperCase(); } else { SID = ""; }
                country = rs.getString("country");
                if(country != null) { country = country.toUpperCase(); } else { country = ""; }
                group_name = rs.getString("group_name");
                if(group_name != null) { group_name = group_name.toUpperCase(); } else { group_name = ""; }
                model.addRow(new Object[]{SID, fan_status, fname, lname, stage_name, gender, country, group_name});
                i++;
            }
            if (i < 1) { JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE); }
            else {
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400, 300);
                if (i == 1) { System.out.println(i + " Record Found"); } else { System.out.println(i + " Records Found"); }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
                }
        });
        btnSearch.addActionListener(e -> {
            String searchString = tfSearchString.getText();
            
                rs = objMySQLDB.ExecuteDataAdapter("SELECT TAB1.*, coalesce(TAB2.SID, 'NO') as fan_status FROM singer as TAB1 left join (SELECT * FROM user_fans_singer_junction where user_id = '" + curUser.user_id.toString() + "') as TAB2 on TAB1.SID = TAB2.SID WHERE fname like '%" + searchString + "%' or lname like '%" + searchString + "%' or stage_name like '%" + searchString + "%' order by fname, lname, stage_name;");
                if (rs == null) { JOptionPane.showMessageDialog(SearchSinger.this,
                        "No Matching Record Found",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE); }
                else {
                    JFrame frame1 = new JFrame("Singers Search List");
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"SID", "fan_status", "fname", "lname", "stage_name", "gender", "country", "group_name"};
        model.setColumnIdentifiers(columnNames);
        //DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
        //table = new JTable(model);
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String SID = "";
        String fname = "";
        String lname = "";
        String stage_name = "";
        String gender = "";
        String country = "";
        String group_name = "";
        String fan_status = "NO";
        table.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        if (row >= 0 && col >= 0) {
            try{
                if(model.getValueAt(row, 1).toString().equals("YES")) {
                    String sql = "delete from user_fans_singer_junction where user_id = '" + curUser.user_id + "' and SID = '" + model.getValueAt(row, 0) + "'";
                    objMySQLDB.ExecuteNonQuery(sql);
                    model.setValueAt("NO", row, 1);
                }
                else {
                    String sql = "insert into user_fans_singer_junction values('" + curUser.user_id + "','" + model.getValueAt(row, 0) + "');";
                    objMySQLDB.ExecuteNonQuery(sql);
                    model.setValueAt("YES", row, 1);
                }
            }catch(Exception e){ e.printStackTrace(); }
            
//            System.out.println(model.getValueAt(row, col));
//            model.setValueAt("Clicked", row, col);
        }
    }
});
        try {
            int i = 0;
            
            while(rs.next()) {
                SID = rs.getString("SID");
                if(SID != null) { SID = SID.toUpperCase(); } else { SID = ""; }
                fan_status = rs.getString("fan_status");
                if(!fan_status.equals("NO")) { fan_status = "YES"; }
                fname = rs.getString("fname");
                if(fname != null) { fname = fname.toUpperCase(); } else { fname = ""; }
                lname = rs.getString("lname");
                if(lname != null) { lname = lname.toUpperCase(); } else { lname = ""; }
                stage_name = rs.getString("stage_name");
                if(stage_name != null) { stage_name = stage_name.toUpperCase(); } else { stage_name = ""; }
                gender = rs.getString("gender");
                if(gender.toUpperCase().equals("F")) { gender = "Female"; } else { gender = "Male"; }
                if(gender != null) { gender = gender.toUpperCase(); } else { SID = ""; }
                country = rs.getString("country");
                if(country != null) { country = country.toUpperCase(); } else { country = ""; }
                group_name = rs.getString("group_name");
                if(group_name != null) { group_name = group_name.toUpperCase(); } else { group_name = ""; }
                model.addRow(new Object[]{SID, fan_status, fname, lname, stage_name, gender, country, group_name});
                i++;
            }
            if (i < 1) { JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE); }
            else {
                frame1.add(scroll);
                frame1.setVisible(true);
                frame1.setSize(400, 300);
                if (i == 1) { System.out.println(i + " Record Found"); } else { System.out.println(i + " Records Found"); }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
                }
        });
        btnCancel.addActionListener(e -> dispose());
        setVisible(true);
    }

    public static void main(String[] args) {
        try  
        {  
            //create instance of the SearchSinger  
            SearchSinger objSearchSinger = new SearchSinger(null, null);  
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
