
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PayRental extends Movie { //inheritance
    
    public PayRental(){
        super();
    }

    //body of abstract methods
    //check if movie is rental or not
    public int isRental(int movie_id) {
        this.setIs_rental(0);
        try{
            ResultSet rs;
            Connection conn=this.makeConnection();
            PreparedStatement p=null; 
            String query ="SELECT * from movies where status=0 && id='"+movie_id+"'";
            p=conn.prepareStatement(query);
            rs=p.executeQuery();
            if(rs.next()){
                this.setIs_rental(1);
                return this.getIs_rental();
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return this.getIs_rental();
    }
    
    //rent movie
    public int rentMovie(int movie_id,int customer_id,String payment_type){
        int handleupdate = 0;
        try {
            Connection conn=this.makeConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs=null;
            PreparedStatement p=null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); //current date
            String date = format.format(new java.util.Date());
            String query1 ="update movies set status='1' where id='"+movie_id+"'";
            handleupdate=stmt.executeUpdate(query1);           
            if(handleupdate==1){
                String query ="insert into rent(created_date,movie_id,customer_id,payment_type) values ('"+date+"','"+movie_id+"','"+customer_id+"','"+payment_type+"')";
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
    
    //check if limit exceeds or not
    public int isLimitExceed(int customer_id){
            int handleupdate = 0;
            try {
                ResultSet rs;
                Connection conn=this.makeConnection();
                Statement stmt=conn.createStatement();             
                PreparedStatement p=null;           
                String query2 ="SELECT * from rent where status=1 && customer_id='"+customer_id+"'";
                p=conn.prepareStatement(query2);
                rs=p.executeQuery();
                int count=0;
                while(rs.next()){
                    count++;
                }
                if(count<4){ //check if the person already rented 4 moview
                    handleupdate=1;
                    return handleupdate;
                }       
                else{
                    handleupdate=0; 
                    return handleupdate;
                }
            } catch (SQLException ex) {
                Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
            }
            return handleupdate;
    }
    
}
