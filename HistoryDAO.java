import java.sql.*;
import java.util.Scanner;

public class HistoryDAO {

    // ---------------------------
    // VIEW ALL HISTORY
    // ---------------------------
    public static void viewHistory() {
        String query = """
            SELECT h.Date, h.Notes, p.name AS patient_name, pr.name AS procedure_name
            FROM patient_history h
            JOIN patient p ON h.p_id = p.p_id
            JOIN procedures pr ON h.proc_id = pr.proc_id
            ORDER BY STR_TO_DATE(h.Date, '%Y-%m-%d')
        """;

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Patient History ---");
            System.out.printf("%-12s %-20s %-25s %-30s%n", "Date", "Patient", "Procedure", "Notes");
            System.out.println("----------------------------------------------------------------------------");

            while (rs.next()) {
                String date = rs.getString("Date");
                String patient = rs.getString("patient_name");
                String procedure = rs.getString("procedure_name");
                String notes = rs.getString("Notes");

                System.out.printf("%-12s %-20s %-25s %-30s%n", date, patient, procedure, notes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // ADD HISTORY
    // ---------------------------
    public static void addHistory(Scanner scanner) {
        try {
            System.out.println("Enter patient ID:");
            int patientId = scanner.nextInt();

            System.out.println("Enter procedure ID:");
            int procedureId = scanner.nextInt();

            System.out.println("Enter visit date (yyyy-mm-dd):");
            scanner.nextLine(); // clear buffer
            String date = scanner.nextLine();

            System.out.println("Enter notes:");
            String notes = scanner.nextLine();

            String query = "INSERT INTO patient_history (Date, Notes, p_id, proc_id) VALUES (?, ?, ?, ?)";

            try (Connection conn = Database.connect();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, date);
                ps.setString(2, notes);
                ps.setInt(3, patientId);
                ps.setInt(4, procedureId);

                ps.executeUpdate();
                System.out.println("Patient history added successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error adding history: " + e.getMessage());
        }
    }

    // ---------------------------
    // UPDATE HISTORY
    // ---------------------------
    public static void updateHistory(Scanner scanner) {
        try {
            System.out.println("Enter patient ID for the history entry to update:");
            int patientId = scanner.nextInt();

            System.out.println("Enter procedure ID for the history entry to update:");
            int procedureId = scanner.nextInt();

            System.out.println("Enter new visit date (yyyy-mm-dd):");
            scanner.nextLine(); // clear buffer
            String date = scanner.nextLine();

            System.out.println("Enter new notes:");
            String notes = scanner.nextLine();

            String query = "UPDATE patient_history SET Date=?, Notes=? WHERE p_id=? AND proc_id=?";

            try (Connection conn = Database.connect();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, date);
                ps.setString(2, notes);
                ps.setInt(3, patientId);
                ps.setInt(4, procedureId);

                ps.executeUpdate();
                System.out.println("Patient history updated successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error updating history: " + e.getMessage());
        }
    }

    // ---------------------------
    // DELETE HISTORY
    // ---------------------------
    public static void deleteHistory(Scanner scanner) {
        try {
            System.out.println("Enter patient ID for the history entry to delete:");
            int patientId = scanner.nextInt();

            System.out.println("Enter procedure ID for the history entry to delete:");
            int procedureId = scanner.nextInt();

            String query = "DELETE FROM patient_history WHERE p_id=? AND proc_id=?";

            try (Connection conn = Database.connect();
                 PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setInt(1, patientId);
                ps.setInt(2, procedureId);

                ps.executeUpdate();
                System.out.println("Patient history deleted successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error deleting history: " + e.getMessage());
        }
    }
}
