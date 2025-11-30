import java.util.Scanner;

public class MainLoop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Foot Doctor's Database!");
        boolean loop = true;

        while (loop) {
            System.out.println("""
                    Select a section:
                    1. Patients
                    2. Procedures
                    3. Patient History
                    4. Exit
                    """);

            int section = scanner.nextInt();

            switch (section) {

                case 1 -> {
                    System.out.println("""
                            1. View Patients
                            2. Add Patient
                            3. Update Patient
                            4. Delete Patient
                            """);
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> PatientDAO.viewPatients();
                        case 2 -> PatientDAO.addPatient(scanner);
                        case 3 -> PatientDAO.updatePatient(scanner);
                        case 4 -> PatientDAO.deletePatient(scanner);
                    }
                }

                case 2 -> {
                    System.out.println("""
                            1. View Procedures
                            2. Add Procedure
                            3. Update Procedure
                            4. Delete Procedure
                            """);
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> ProcedureDAO.viewProcedures();
                        case 2 -> ProcedureDAO.addProcedure(scanner);
                        case 3 -> ProcedureDAO.updateProcedure(scanner);
                        case 4 -> ProcedureDAO.deleteProcedure(scanner);
                    }
                }

                case 3 -> {
                    System.out.println("Patient History not implemented yet.");
                    // Call HistoryDAO methods here
                }

                case 4 -> {
                    loop = false;
                    System.out.println("Goodbye!");
                }

                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
