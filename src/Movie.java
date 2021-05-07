import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public abstract class Movie extends Database {
    
    private String id;
    private String name;
    private String release_date;
    private String status;
    Database db;

    public Movie() {
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.status = status;
        db=new Database();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ResultSet getMovieList(){
        ResultSet rs=null;
        try {
            Connection conn=db.makeConnection();
            Statement stmt=conn.createStatement();
            PreparedStatement p=null;           
            String query2 ="SELECT * from movies where status=0";
            p=conn.prepareStatement(query2);
            rs=p.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public abstract int rentMovie(int movie_id,String payment_type);
    
    public int returnMovie(int movie_id){
            int handleupdate = 0;

            try {
                Connection conn=db.makeConnection();
                Statement stmt=conn.createStatement();               
                String query1 ="update movies set status='0' where id='"+movie_id+"'";
                handleupdate=stmt.executeUpdate(query1);     
                if(handleupdate==1){
                    return handleupdate;
                }
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
            return handleupdate;
    }
    
}
