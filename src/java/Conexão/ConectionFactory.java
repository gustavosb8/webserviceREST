
package Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectionFactory {
    
       public Connection getConnection() {
        
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/projetolp3","root","senharoot");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}
