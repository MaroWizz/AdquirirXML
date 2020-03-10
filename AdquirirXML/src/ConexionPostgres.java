import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionPostgres {

	
	public void realizaConexion(){
        Connection conn = null;
        String urlDatabase =  "jjdbc:postgresql://192.168.2.39:5432/dterecepprodos"; 
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(urlDatabase,  "azrecepprd", "azrecepprd");
        } catch (Exception e) {
            System.out.println("Ocurrio un error : "+e.getMessage());
        }
        System.out.println("La conexión se realizo sin problemas! =) ");
}
	
}
