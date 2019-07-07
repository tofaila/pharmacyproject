
package exam;

import java.sql.*;


public class DBConnection {
   

    public static Connection getDBconnection(){
         Connection con=null;
    
    try{
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacyproject","root","123");
        
     
    }catch(Exception e){
     e.printStackTrace();
    }
    return con;
    
}

    static void close(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
