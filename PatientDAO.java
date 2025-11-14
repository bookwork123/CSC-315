import java.sql.*;
import java.util.Scanner;

public class PatientDAO {

    // ---------------------------
    // VIEW ALL PATIENTS
    // ---------------------------
    public static void viewPatients() {

        String query = "SELECT p_id, name, height FROM patient";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Patient List ---");
            while (rs.next()) {
                int id = rs.getInt("p_id");
                String name = rs.getString("name");
                double height = rs.getDouble("height");

                System.out.println(id + " | " + name + " | " + height);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // ADD PATIENT
    // ---------------------------
    public static void addPatient(Scanner scanner) {

        System.out.println("Enter patient name:");
        String name = scanner.next();

        System.out.println("Enter patient height:");
        double height = scanner.nextDouble();

        String query = "INSERT INTO Patients(name, height) VALUES (?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, height);

            ps.executeUpdate();
            System.out.println("Patient added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // UPDATE PATIENT
    // ---------------------------
    public static void updatePatient(Scanner scanner) {

        System.out.println("Enter patient ID to update:");
        int id = scanner.nextInt();

        System.out.println("Enter new name:");
        String name = scanner.next();

        System.out.println("Enter new height:");
        double height = scanner.nextDouble();

        String query = "UPDATE Patients SET name = ?, height = ? WHERE p_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, height);
            ps.setInt(3, id);

            ps.executeUpdate();
            System.out.println("Patient updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // DELETE PATIENT
    // ---------------------------
    public static void deletePatient(Scanner scanner) {

        System.out.println("Enter patient ID to delete:");
        int id = scanner.nextInt();

        String query = "DELETE FROM Patients WHERE p_id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Patient deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
