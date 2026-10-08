import java.util.Scanner;

public class Blood_Bank_Inventory_Donor_Matcher6 {


    static String[] records = new String[100];
    static int count = 0;   

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int menuChoice;

        do {
            System.out.println("\n===== Blood Bank Inventory Donor System =====");
            System.out.println("1. Check donor-recipient compatibility");
            System.out.println("2. View all past checks");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            menuChoice = sc.nextInt();
            sc.nextLine(); 

            switch (menuChoice) {

                case 1:
                    checkCompatibility(sc);
                    break;

                case 2: 
                    System.out.println("\n----- Past Checks -----");
                    if (count == 0) {
                        System.out.println("No checks performed yet.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println(records[i]);
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

    public static void checkCompatibility(Scanner sc) {

        System.out.println("\nPlease enter the following information to check blood group compatibility:");

        System.out.println("-------DONOR AGE DETAILS:-------");

        System.out.print("Enter the name of the donor: ");
        String donorName = sc.nextLine();

        System.out.print("Enter the age of the donor: ");
        int donorAge = sc.nextInt();
        sc.nextLine();

        System.out.println("-------RECIPIENT AGE DETAILS:-------");

        System.out.print("Enter the name of the recipient: ");
        String recipientName = sc.nextLine();

        System.out.print("Enter the age of the recipient: ");
        int recipientAge = sc.nextInt();
        sc.nextLine();

        System.out.println("-------BLOOD GROUP DETAILS:-------");

        System.out.print("Enter the blood group of the donor: ");
        String donorBloodGroup = sc.nextLine().trim();

        System.out.print("Enter the blood group of the recipient: ");
        String recipientBloodGroup = sc.nextLine().trim();

        while (donorAge < 18 || donorAge > 65) {
            System.out.println("Donor age must be between 18 and 65. Please enter a valid age: ");
            donorAge = sc.nextInt();
        }
        System.out.println("============================");
        System.out.println("Donor age is valid.");

        while (recipientAge < 0 || recipientAge > 120) {
            System.out.println("Recipient age must be between 0 and 120. Please enter a valid age: ");
            recipientAge = sc.nextInt();
        }

        System.out.println("Recipient age is valid.");
        System.out.println("============================");

        if (donorBloodGroup.equalsIgnoreCase("O-")) {
            System.out.println("Donor blood group is O-. This blood group can donate to all other blood groups.");


        } else if (donorBloodGroup.equalsIgnoreCase("O+")) {

            System.out.println("Donor blood group is O+. This blood group can donate to A+, B+, AB+, and O+.");

        } else if (donorBloodGroup.equalsIgnoreCase("A-")) {

            System.out.println("Donor blood group is A-. This blood group can donate to A-, A+, AB-, and AB+.");

        } else if (donorBloodGroup.equalsIgnoreCase("A+")) {

            System.out.println("Donor blood group is A+. This blood group can donate to A+ and AB+.");

        } else if (donorBloodGroup.equalsIgnoreCase("B-")) {

            System.out.println("Donor blood group is B-. This blood group can donate to B- and O-");
        
        } else if (donorBloodGroup.equalsIgnoreCase("B+")) {

            System.out.println("Donor blood group is B+. This blood group can donate to B+ and AB+.");

        } else if (donorBloodGroup.equalsIgnoreCase("AB-")) {

            System.out.println("Donor blood group is AB-. This blood group can donate to AB- and AB+.");

        } else if (donorBloodGroup.equalsIgnoreCase("AB+")) {
            
            System.out.println("Donor blood group is AB+. This blood group can only donate to AB+.");

        } else {
            System.out.println("Invalid donor blood group entered. Please enter a valid blood group: ");
            donorBloodGroup = sc.nextLine();
        }

        boolean compatible = false;

        switch (recipientBloodGroup) {

            case "A+":
                compatible = donorBloodGroup.equalsIgnoreCase ("A+") || donorBloodGroup.equalsIgnoreCase ("A-") || donorBloodGroup.equalsIgnoreCase ("O+") || donorBloodGroup.equalsIgnoreCase ("O-");
                break;

            case "A-":
                compatible = donorBloodGroup.equalsIgnoreCase ("A-") || donorBloodGroup.equalsIgnoreCase ("O-");
                break;

                case "O+":
                    compatible = donorBloodGroup.equalsIgnoreCase ("O+") || donorBloodGroup.equalsIgnoreCase ("O-");
                    break;

                    case "O-":
                        compatible = donorBloodGroup.equalsIgnoreCase ("O-");
                        break;

                        case "B-":
                            compatible = donorBloodGroup.equalsIgnoreCase ("B-") || donorBloodGroup.equalsIgnoreCase ("O-") || donorBloodGroup.equalsIgnoreCase ("AB-") || donorBloodGroup.equalsIgnoreCase ("AB+");
                            break;

                            case "B+":
                                compatible = donorBloodGroup.equalsIgnoreCase ("B+") || donorBloodGroup.equalsIgnoreCase ("B-") || donorBloodGroup.equalsIgnoreCase ("O+") || donorBloodGroup.equalsIgnoreCase ("O-");
                                break;

                                case "AB-":
                                    compatible = donorBloodGroup.equalsIgnoreCase ("AB-") || donorBloodGroup.equalsIgnoreCase ("B-") || donorBloodGroup.equalsIgnoreCase ("A-") || donorBloodGroup.equalsIgnoreCase ("O-");
                                    break;

                                    case "AB+":
                                        compatible = true;
                                        break;

                                        default: System.out.println("Invalid recipient blood group entered. Please enter a valid blood group: ");
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

        if (count < records.length) {
            records[count] = result;
            count++;
        } else {
            System.out.println("Record storage is full. This check was not saved.");
        }

        System.out.print("\nDo you want to check another donor (Y/N): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            checkCompatibility(sc);
        } else {
            System.out.println("Thank you for using the Blood Bank Inventory Donor System");
        }
    }
}