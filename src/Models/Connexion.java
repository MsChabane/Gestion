
package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connexion {
    
    public static Connection connecter () throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionstock","root","chabane"); 
    }  
}
