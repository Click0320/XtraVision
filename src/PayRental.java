
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayRental extends Movie {
    
    
    public int rentMovie(int movie_id,String payment_type){
        int handleupdate = 0;
        try {
            Connection conn=db.makeConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=null;
            PreparedStatement p=null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(new java.util.Date());
            String query1 ="update movies set status='1' where id='"+movie_id+"'";
            handleupdate=stmt.executeUpdate(query1);           
            if(handleupdate==1){
                String query ="insert into rent(created_date,movie_id,customer_id,payment_type) values ('"+date+"','"+movie_id+"','1','"+payment_type+"')";
                stmt.executeUpdate(query);
                return handleupdate;
            }
            else{
                return handleupdate;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        return handleupdate;
    }
    
}
