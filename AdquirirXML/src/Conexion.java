import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Conexion {
  protected static Map cons = new HashMap();

  public static Connection getConnection(String basedatos) throws SQLException {
    String conexion = "";
    Connection MiCon = null;
  
    try {
      String user = "azrecepprd";
      String password = "azrecepprd";
      String stringconexion = "jdbc:postgresql://192.168.2.39:5432/dterecepprodoaaaaas";

      try {
//        String classname = "com.ibm.db2.jcc.DB2Driver";
//        Class.forName(classname).newInstance();
        Class.forName("org.postgresql.Driver"); 
        return DriverManager.getConnection(
            stringconexion, 
            user, 
            password);

      }
      catch (Exception e) {
        System.out.println("[Error][DataBaseConnectionProvider]: " + e.getMessage() + " : " + basedatos);
        e.printStackTrace(System.err);
      
      }
    
    }
    catch (Exception exception) {}
    return MiCon;
  }

  
  public static void close(ResultSet resultset) {
    
    try { if (resultset != null) {
        resultset.close();
      } }
    catch (SQLException sQLException) {  }
    finally
    { resultset = null; }
  
  }
  
  public static void close(Statement statement) {
    
    try { if (statement != null) {
        statement.close();
      } }
    catch (SQLException sQLException) {  }
    finally
    { statement = null; }
  
  }

  
  public static void close(Connection connection) {
    
    try { if (connection != null) {
        connection.close();
      } }
    catch (SQLException sQLException) {  }
    finally
    { if (System.getProperty("debubcons") != null) {
        cons.remove(connection);
      }
      connection = null; }
  
  }
 
  public static void close(Connection connection, Statement statement, ResultSet resultset) {
    close(resultset);
    close(statement);
    close(connection);
  }

  
  public static Map listaConexionesAbiertas() { return cons; }

  public static void limpiaConexionesAbiertas() { cons.clear(); }
}
