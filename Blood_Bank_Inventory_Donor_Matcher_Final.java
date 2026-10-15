import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Blood_Bank_Inventory_Donor_Matcher_Final {

    static final String FILE_NAME = "records.txt";

    static final String[] VALID_GROUPS = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> recordList = new ArrayList<>();
        int menuChoice;

        loadRecordsFromFile(recordList);

        do {
            System.out.println("\n===== Blood Bank Inventory Donor System =====");
            System.out.println("1. Check donor-recipient compatibility");
            System.out.println("2. View all past checks");
            System.out.println("3. Exit");
            menuChoice = readInt(sc, "Enter your choice: ");

            switch (menuChoice) {

                case 1:
                    checkCompatibility(sc, recordList);
                    break;

                case 2:
                    System.out.println("\n----- Past Checks -----");
                    if (recordList.isEmpty()) {
                        System.out.println("No checks performed yet.");
                    } else {
                        for (String record : recordList) {
                            printRecord(record);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the Blood Bank Inventory Donor System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }

        } while (menuChoice != 3);

        sc.close();
    }



    public static void printRecord(String result) {
        System.out.println(result);
    }

    public static void printRecord(String result, boolean showDetails) {
        if (showDetails) {
            System.out.println("---- Detailed Record ----");
        }
        System.out.println(result);
    }

   
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }


    public static String readBloodGroup(Scanner sc, String prompt) {
        System.out.print(prompt);
        String group = sc.nextLine().trim().toUpperCase();

        while (!isValidGroup(group)) {
            System.out.print("Invalid blood group. Enter A+, A-, B+, B-, AB+, AB-, O+ or O-: ");
            group = sc.nextLine().trim().toUpperCase();
        }
        return group;
    }

    public static boolean isValidGroup(String group) {
        for (String valid : VALID_GROUPS) {
            if (valid.equals(group)) {
                return true;
            }
        }
        return false;
    }

    
    public static void saveRecordToFile(String result) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_NAME),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(result);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Could not save record to file: " + e.getMessage());
        }
    }


    public static void loadRecordsFromFile(ArrayList<String> recordList) {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return;
        }
        try {
            recordList.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            System.out.println("Could not read saved records: " + e.getMessage());
        }
    }



    public static void checkCompatibility(Scanner sc, ArrayList<String> recordList) {

        System.out.println("\nPlease enter the following information to check blood group compatibility:");

        System.out.println("-------DONOR DETAILS:-------");

        System.out.print("Enter the name of the donor: ");
        String donorName = sc.nextLine().trim();

        int donorAge = readInt(sc, "Enter the age of the donor: ");
        while (donorAge < 18 || donorAge > 65) {
            System.out.println("Donor age must be between 18 and 65.");
            donorAge = readInt(sc, "Please enter a valid age: ");
        }

        System.out.println("-------RECIPIENT DETAILS:-------");

        System.out.print("Enter the name of the recipient: ");
        String recipientName = sc.nextLine().trim();

        int recipientAge = readInt(sc, "Enter the age of the recipient: ");
        while (recipientAge < 0 || recipientAge > 120) {
            System.out.println("Recipient age must be between 0 and 120.");
            recipientAge = readInt(sc, "Please enter a valid age: ");
        }

        System.out.println("-------BLOOD GROUP DETAILS:-------");

        String donorBloodGroup = readBloodGroup(sc, "Enter the blood group of the donor: ");
        String recipientBloodGroup = readBloodGroup(sc, "Enter the blood group of the recipient: ");

        System.out.println("============================");
        System.out.println("All details are valid.");
        System.out.println("============================");

        if (donorBloodGroup.equals("O-")) {
            System.out.println("Donor blood group is O-. This blood group can donate to all other blood groups.");

          } else if (donorBloodGroup.equals("O+")) {
            System.out.println("Donor blood group is O+. This blood group can donate to A+, B+, AB+, and O+.");

            } else if (donorBloodGroup.equals("A-")) {
            System.out.println("Donor blood group is A-. This blood group can donate to A-, A+, AB-, and AB+.");

              } else if (donorBloodGroup.equals("A+")) {
            System.out.println("Donor blood group is A+. This blood group can donate to A+ and AB+.");

                } else if (donorBloodGroup.equals("B-")) {
            System.out.println("Donor blood group is B-. This blood group can donate to B-, B+, AB-, and AB+.");

                   } else if (donorBloodGroup.equals("B+")) {
            System.out.println("Donor blood group is B+. This blood group can donate to B+ and AB+.");

                     } else if (donorBloodGroup.equals("AB-")) {
            System.out.println("Donor blood group is AB-. This blood group can donate to AB- and AB+.");

                         } else if (donorBloodGroup.equals("AB+")) {
            System.out.println("Donor blood group is AB+. This blood group can only donate to AB+.");

        }

        boolean compatible = false;

        switch (recipientBloodGroup) {

            case "A+":
                compatible = donorBloodGroup.equals("A+") || donorBloodGroup.equals("A-")
                        || donorBloodGroup.equals("O+") || donorBloodGroup.equals("O-");
                break;

            case "A-":
                compatible = donorBloodGroup.equals("A-") || donorBloodGroup.equals("O-");
                break;

            case "O+":
                compatible = donorBloodGroup.equals("O+") || donorBloodGroup.equals("O-");
                break;

            case "O-":
                compatible = donorBloodGroup.equals("O-");
                break;

            case "B+":
                compatible = donorBloodGroup.equals("B+") || donorBloodGroup.equals("B-")
                        || donorBloodGroup.equals("O+") || donorBloodGroup.equals("O-");
                break;

            case "B-":
                compatible = donorBloodGroup.equals("B-") || donorBloodGroup.equals("O-");
                break;

            case "AB+":
                compatible = true;
                break;

            case "AB-":
                compatible = donorBloodGroup.equals("AB-") || donorBloodGroup.equals("A-")
                        || donorBloodGroup.equals("B-") || donorBloodGroup.equals("O-");
                break;
        }

        String result;
        if (compatible) {
            result = donorName + " (" + donorBloodGroup + ") CAN donate to " + recipientName + " (" + recipientBloodGroup + ")";
            printRecord(result);
        } else {
            result = donorName + " (" + donorBloodGroup + ") CANNOT donate to " + recipientName + " (" + recipientBloodGroup + ")";
            printRecord(result, true);
        }

        recordList.add(result);
        saveRecordToFile(result);

        System.out.print("\nDo you want to check another donor (Y/N): ");
        String choice = sc.nextLine().trim();

        if (choice.equalsIgnoreCase("Y")) {
            checkCompatibility(sc, recordList);
        } else {
            System.out.println("Thank you for using the Blood Bank Inventory Donor System");
        }
    }
}