
package singerbase;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class ManageSongs extends JDialog {
    JPanel searchPanel;
    JLabel lblCounter;
    ResultSet rs = null;
    User curUser = new User();
    MySQLDB objMySQLDB = new MySQLDB();
    JButton btnCancel, btnPlayLists, btnSongLists, btnAddAlbum;
    
    public ManageSongs(JFrame parent, User user) {
        super(parent);
        curUser = user;
        setTitle("Search Singer");
        setMinimumSize(new Dimension(700,200));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblCounter = new JLabel();
        btnCancel = new JButton("CANCEL");
        btnPlayLists = new JButton("PLAY LISTS");
        btnSongLists = new JButton("SONG LISTS");
        btnAddAlbum = new JButton("ADD ALBUM");
        
        searchPanel = new JPanel(new GridLayout(5, 1));  
        searchPanel.add(lblCounter); 
        searchPanel.add(btnAddAlbum); 
        searchPanel.add(btnPlayLists);   
        searchPanel.add(btnSongLists);   
        searchPanel.add(btnCancel);   
        add(searchPanel, BorderLayout.CENTER);
        lblCounter.setText(getCounter());
        
        btnAddAlbum.addActionListener(e -> {
            objMySQLDB.ExecuteNonQuery("INSERT INTO song_list select (SELECT CONCAT('SL', (count(*) + 1)) FROM song_list), '" + curUser.user_id.toString() + "';");
            JOptionPane.showMessageDialog(ManageSongs.this,
                        "New Album Created Successfully",
                        "Try again",
                        JOptionPane.INFORMATION_MESSAGE);
            lblCounter.setText(getCounter());
        });
        
        btnSongLists.addActionListener(e -> {
                rs = objMySQLDB.ExecuteDataAdapter("select TAB1.song_list_id as Play_List_Name, TAB2.title as SongTitle, TAB2.genre as SongGenre, TAB2.release_year as SongReleaseYear, CONCAT(TAB3.fname, ' ', TAB3.lname) as SingerName, TAB4.title as AlbumTitle, TAB4.genre as AlbumGenre, TAB4.release_year as AlbumReleaseYear from song_song_list_junction as TAB1 inner join song as TAB2 on TAB1.song_id = TAB2.song_id inner join singer as TAB3 on TAB2.SID = TAB3.SID inner join album as TAB4 on TAB2.AID = TAB4.AID where TAB1.song_list_id in (select song_list_id from song_list where user_id = '" + curUser.user_id.toString() + "') order by TAB1.song_list_id, TAB2.title;");
                if (rs == null) { JOptionPane.showMessageDialog(ManageSongs.this,
                        "No Matching Record Found",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE); }
                else {
                    JFrame frame1 = new JFrame("Songs List");
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Play_List_Name", "SongTitle", "SongGenre", "SongReleaseYear", "SingerName", "AlbumTitle", "AlbumGenre", "AlbumReleaseYear"};
        model.setColumnIdentifiers(columnNames);
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String Play_List_Name = "";
        String SongTitle = "";
        String SongGenre = "";
        String SongReleaseYear = "";
        String SingerName = "";
        String AlbumTitle = "";
        String AlbumGenre = "";
        String AlbumReleaseYear = "";
        try {
            int i = 0;
            
            while(rs.next()) {
                Play_List_Name = rs.getString("Play_List_Name");
                if(Play_List_Name != null) { Play_List_Name = Play_List_Name.toUpperCase(); } else { Play_List_Name = ""; }
                SongTitle = rs.getString("SongTitle");
                if(SongTitle != null) { SongTitle = SongTitle.toUpperCase(); } else { SongTitle = ""; }
                SongGenre = rs.getString("SongGenre");
                if(SongGenre != null) { SongGenre = SongGenre.toUpperCase(); } else { SongGenre = ""; }
                SongReleaseYear = rs.getString("SongReleaseYear");
                if(SongReleaseYear != null) { SongReleaseYear = SongReleaseYear.toUpperCase(); } else { SongReleaseYear = ""; }
                SingerName = rs.getString("SingerName");
                if(SingerName != null) { SingerName = SingerName.toUpperCase(); } else { SingerName = ""; }
                AlbumTitle = rs.getString("AlbumTitle");
                if(AlbumTitle != null) { AlbumTitle = AlbumTitle.toUpperCase(); } else { AlbumTitle = ""; }
                AlbumGenre = rs.getString("AlbumGenre");
                if(AlbumGenre != null) { AlbumGenre = AlbumGenre.toUpperCase(); } else { AlbumGenre = ""; }
                AlbumReleaseYear = rs.getString("AlbumReleaseYear");
                if(AlbumReleaseYear != null) { AlbumReleaseYear = AlbumReleaseYear.toUpperCase(); } else { AlbumReleaseYear = ""; }
                model.addRow(new Object[]{Play_List_Name, SongTitle, SongGenre, SongReleaseYear, SingerName, AlbumTitle, AlbumGenre, AlbumReleaseYear});
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
        
        btnPlayLists.addActionListener(e -> {
                rs = objMySQLDB.ExecuteDataAdapter("SELECT TAB1.song_list_id as Play_List_Name, COALESCE(TAB2.no_of_songs, 0) as No_Of_Songs FROM song_list as TAB1 left join (select song_list_id, count(song_id) as no_of_songs from song_song_list_junction group by song_list_id) as TAB2 on TAB1.song_list_id = TAB2.song_list_id where user_id = '" + curUser.user_id.toString() + "' order by no_of_songs desc, Play_List_Name;");
                if (rs == null) { JOptionPane.showMessageDialog(ManageSongs.this,
                        "No Matching Record Found",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE); }
                else {
                    JFrame frame1 = new JFrame("Album Lists");
//        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        //TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNames = {"Play_List_Name", "No_Of_Songs"};
        model.setColumnIdentifiers(columnNames);
        JTable table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        String Play_List_Name = "";
        String No_Of_Songs = "";
        try {
            int i = 0;
            
            while(rs.next()) {
                Play_List_Name = rs.getString("Play_List_Name");
                if(Play_List_Name != null) { Play_List_Name = Play_List_Name.toUpperCase(); } else { Play_List_Name = ""; }
                No_Of_Songs = rs.getString("No_Of_Songs");
                if(No_Of_Songs == null) { No_Of_Songs = ""; }
                model.addRow(new Object[]{Play_List_Name, No_Of_Songs});
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

    private String getCounter() {
        String strResponse = "";
        strResponse = objMySQLDB.ExecuteScalar("select Count(*) as NoOfRecords from song_list where user_id = '" + curUser.user_id.toString() + "';") + " Albums";
        strResponse += " And " + objMySQLDB.ExecuteScalar("select Count(*) as NoOfRecords from song_song_list_junction where song_list_id in (select song_list_id from song_list where user_id = '" + curUser.user_id.toString() + "');") + " Songs";
        return strResponse;
    }
    
    public static void main(String[] args) {
        try  
        {  
            //create instance of the SearchSinger  
            ManageSongs objManageSongs = new ManageSongs(null, null); 
            objManageSongs.setSize(500,300);
            objManageSongs.setVisible(true);  
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
