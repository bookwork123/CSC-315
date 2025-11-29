import java.sql.*;
import java.util.Scanner;

public class ProcedureDAO {

    // ---------------------------
    // VIEW ALL PROCEDURES
    // ---------------------------
    public static void viewProcedures() {

        String query = "SELECT proc_id, description, cost FROM procedure";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Procedure List ---");
            while (rs.next()) {
                int id = rs.getInt("proc_id");
                String name = rs.getString("Name");
                double duration = rs.getDouble("Duration (minutes)");
                String cause = rs.getString("Cause");
                Boolean surgical = rs.getBoolean("Surgical?");

                System.out.println(id + " | " + name + " | " + duration + " | " + cause + " | " + surgical);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ---------------------------
    // ADD PROCEDURE
    // ---------------------------
    public static void addProcedure(Scanner scanner) {

        System.out.println("Enter procedure name:");
        scanner.nextLine();  // Clear scanner buffer
        String name = scanner.nextLine(); // Allows spaces  

        System.out.println("Enter procedure duration (minutes):");
        double duration = scanner.nextDouble();

        System.out.println("Enter procedure cause:");
        scanner.nextLine();  
        String cause = scanner.nextLine(); 

        System.out.println("Is the procedure surgical? (true/false):");
        boolean surgical = scanner.nextBoolean();

        String query = "INSERT INTO procedure(name, duration, cause, surgical) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, duration);
            ps.setString(3, cause);
            ps.setBoolean(4, surgical);

            ps.executeUpdate();
            System.out.println("Procedure added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // ---------------------------
    // UPDATE PROCEDURE
    // ---------------------------
    public static void updateProcedure(Scanner scanner) {
        System.out.println("Enter procedure ID to update:");
        int id = scanner.nextInt();

        System.out.println("Enter new name:");
        String name = scanner.next();

        System.out.println("Enter new duration (minutes):");
        double duration = scanner.nextDouble();

        System.out.println("Enter new cause:");
        String cause = scanner.next();

        System.out.println("Is the procedure surgical? (true/false):");
        boolean surgical = scanner.nextBoolean();

        String query = "UPDATE procedure SET name = ?, duration = ?, cause = ?, surgical = ? WHERE proc_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, duration);
            ps.setString(3, cause);
            ps.setBoolean(4, surgical);
            ps.setInt(5, id);

            ps.executeUpdate();
            System.out.println("Procedure updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ---------------------------
    // DELETE PROCEDURE
    // ---------------------------
    public static void deleteProcedure(Scanner scanner) {
        System.out.println("Enter procedure ID to delete:");
        int id = scanner.nextInt();

        String query = "DELETE FROM procedure WHERE proc_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Procedure deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
