import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public abstract class Movie extends Database { //inheritance and abstract class
    
    private String id;
    private String name;
    private String release_date;
    private String status;
    private int is_rental;
    
    public Movie() {
        super();
        this.id = id;
        this.name = name;
        this.release_date = release_date;
        this.status = status;
    }

    //get set method
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

    public int getIs_rental() {
        return is_rental;
    }

    public void setIs_rental(int is_rental) {
        this.is_rental = is_rental;
    }
    
    //get movies list
    public ResultSet getMovieList(){
        ResultSet rs=null;
        try {
            Connection conn=this.makeConnection(); //database class
            Statement stmt=conn.createStatement();
            PreparedStatement p=null;           
            String query2 ="SELECT * from movies where status=0"; //get all movies which are not rented yet
            p=conn.prepareStatement(query2);
            rs=p.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    //abstract method
    public abstract int rentMovie(int movie_id,int customer_id,String payment_type);
    
    public abstract int isRental(int movie_id);
    
    public abstract int isLimitExceed(int customer_id);

    //return movie
    public int returnMovie(int movie_id){
            int handleupdate = 0;
            int handleupdate1 = 0;

            try {
                ResultSet rs;
                Connection conn=this.makeConnection();
                Statement stmt=conn.createStatement();             
                PreparedStatement p=null;           
                
                String query2 ="SELECT * from movies where status=1 && id='"+movie_id+"'"; //check if movie is rented
                p=conn.prepareStatement(query2);
                rs=p.executeQuery();
                if(rs.next()){ 
                    String query1 ="update movies set status=0 where id='"+movie_id+"'"; //change status to available
                    handleupdate=stmt.executeUpdate(query1);
                    String query3 ="update rent set status=0 where movie_id='"+movie_id+"'"; //update rent status to 0 mean title is return by particular person
                    stmt.executeUpdate(query3);
                    if(handleupdate==1){
                        return handleupdate;
                    }
                    else{
                        return handleupdate;
                    }
                }
                else{
                    return handleupdate1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
            return handleupdate;
    }
    
}
