
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Customer {
    private String name;
    private int id;
    private String email;
    Database db;

    public Customer() {
        this.name = name;
        this.email = email;
        this.id=id;
        db=new Database();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int addCustomer(String name, String email){
        int handleupdate=0;
        try{
        ResultSet rs;
        Connection conn=db.makeConnection();
        Statement stmt=conn.createStatement();                     
        PreparedStatement p=null;           
                
        String query2 ="SELECT * from customers where email='"+email+"'";
        p=conn.prepareStatement(query2);
        rs=p.executeQuery();
            if(!rs.next()){ 
                String query ="insert into customers(name,email) values ('"+name+"','"+email+"')";
                stmt.executeUpdate(query);              
                addCustomer(name,email);
            }
            else
            {
                this.setId(Integer.parseInt(rs.getString("id")));
                return this.getId();
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(index.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.getId();

    }
    
    
}
