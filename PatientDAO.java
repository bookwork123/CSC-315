import java.sql.*;
import java.util.Scanner;

public class PatientDAO {

    // ---------------------------
    // VIEW ALL PATIENTS
    // ---------------------------
    public static void viewPatients() {

        String query = "SELECT p_id, name, height, foot_size, sex, age, weight, phone, address, email FROM patient";

        try (Connection conn = Database.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n--- Patient List ---");

            // Column headers (email before address)
            System.out.printf("%-5s %-20s %-8s %-10s %-8s %-5s %-8s %-15s %-25s %-40s%n",
                    "ID", "Name", "Height", "FootSize", "Sex", "Age", "Weight", "Phone", "Email", "Address");

            // Separator
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {

                int id = rs.getInt("p_id");
                String name = rs.getString("name");
                double height = rs.getDouble("height");
                double footSize = rs.getDouble("foot_size");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                int weight = rs.getInt("weight");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                // Row (email before address)
                System.out.printf("%-5d %-20s %-8.2f %-10.2f %-8s %-5d %-8d %-15s %-25s %-40s%n",
                        id, name, height, footSize, sex, age, weight, phone, email, address);

                System.out.println(); // Blank line between rows
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
        scanner.nextLine(); // Clear scanner buffer
        String name = scanner.nextLine(); // Allows spaces

        System.out.println("Enter patient height:");
        double height = scanner.nextDouble();

        System.out.println("Enter patient's feet size:");
        double foot_size = scanner.nextDouble();

        System.out.println("Enter patient's sex (Male/Female):");
        String sex = scanner.next();

        System.out.println("Enter patient's age:");
        int age = scanner.nextInt();

        System.out.println("Enter patient's weight:");
        int weight = scanner.nextInt();

        System.out.println("Enter patient's phone number:");
        String phone = scanner.next();

        System.out.println("Enter patient's address:");
        scanner.nextLine(); // Clear scanner buffer
        String address = scanner.nextLine(); // Allows spaces

        System.out.println("Enter patient's email:");
        String email = scanner.next();

        String query = "INSERT INTO patient(name, height, foot_size, sex, age, weight, phone, address, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, height);
            ps.setDouble(3, foot_size);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ps.setInt(6, weight);
            ps.setString(7, phone);
            ps.setString(8, address);
            ps.setString(9, email);

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
        scanner.nextLine(); // Clear scanner buffer
        String name = scanner.nextLine(); // Allows spaces

        System.out.println("Enter new height:");
        double height = scanner.nextDouble();

        System.out.println("Enter new foot size:");
        double foot_size = scanner.nextDouble();

        System.out.println("Enter new sex:");
        String sex = scanner.next();

        System.out.println("Enter new age:");
        int age = scanner.nextInt();

        System.out.println("Enter new weight:");
        int weight = scanner.nextInt();

        System.out.println("Enter new phone number:");
        String phone = scanner.next();

        System.out.println("Enter new address:");
        scanner.nextLine(); // Clear scanner buffer
        String address = scanner.nextLine(); // Allows spaces

        System.out.println("Enter new email:");
        String email = scanner.next();

        String query = "UPDATE patient SET name = ?, height = ?, foot_size = ?, sex = ?, age = ?, weight = ?, phone = ?, address = ?, email = ? WHERE p_id = ?";

        try (Connection conn = Database.connect();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setDouble(2, height);
            ps.setDouble(3, foot_size);
            ps.setString(4, sex);
            ps.setInt(5, age);
            ps.setInt(6, weight);
            ps.setString(7, phone);
            ps.setString(8, address);
            ps.setString(9, email);
            ps.setInt(10, id);

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

        String query = "DELETE FROM patient WHERE p_id = ?";

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
