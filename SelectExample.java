import java.sql.*;

public class SelectExample {
   static final String DB_URL = "jdbc:mysql://localhost/podiatry";
   static final String USER = "root";
   static final String PASS = "Root456!&";
   static final String QUERY = "SELECT p_id, name FROM patient";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);) {
         // Extract data from result set
         while (rs.next()) {
            // Retrieve by column name
            System.out.print("p_id: " + rs.getInt("p_id"));
            System.out.print(", name: " + rs.getString("name") + "\n");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}