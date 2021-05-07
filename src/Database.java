import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    
    String dbpath="jdbc:mysql://localhost:3306/xtravision"; //db path
    String dbuser="root"; //db username
    String dbpassword ="";  //db password default null

    public Database() {
        
    }
    
    //getter & setter
    public String getDbpath() {
        return dbpath;
    }

    public void setDbpath(String dbpath) {
        this.dbpath = dbpath;
    }

    public String getDbuser() {
        return dbuser;
    }

    public void setDbuser(String dbuser) {
        this.dbuser = dbuser;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }
    
    //make connection
    public Connection makeConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.getDbpath(),this.getDbuser(),this.getDbpassword());
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
