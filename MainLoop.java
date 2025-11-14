import java.util.Scanner;
import java.sql.*;

public class MainLoop {
    static String user = "root";
    static String password = "Root456!&";
    static String query = "";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to the Foot Doctor's Database!");
        System.out.println();
        System.out.println("oOOO() ()OOOo\r\n" + //
                        "/  _)   (_  \\\r\n" + //
                        "|  (     )  |\r\n" + //
                        "\\__)     (__/");
        System.out.println();
        System.out.println("Please select a section of the database:");
        boolean loop = true;
        while (loop == true) {
            System.out.println("""
                    1. Patients
                    2. Procedures
                    3. Patient History
                    """);
            int section = scanner.nextInt();

            if (section == 1) {
                System.out.println();
                System.out.println("Would you like to:");
                System.out.println("""
                        1. View Patients
                        2. Add Patient
                        3. Update Patient
                        4. Delete Patient
                        """);
            } else if (section == 2) {
                System.out.println();
                System.out.println("Would you like to:");
                System.out.println("""
                        1. View Procedures
                        2. Add Procedure
                        3. Update Procedure
                        4. Delete Procedure
                        """);

            } else if (section == 3) {
                System.out.println();
                System.out.println("Would you like to:");
                System.out.println("""
                        1. View Patient History
                        2. Add Patient History
                        3. Update Patient History
                        4. Delete Patient History
                        """);
            } else {
                System.out.println();
                System.out.println("Please make a valid choice.");
                System.out.println();
                continue;
            }            
        }




    } // main

}// mainLoop