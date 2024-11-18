import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    private static PerkManager perkManager = new PerkManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Do you have an account? [y/n]:");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("y")) {
                login();
            } else if (response.equalsIgnoreCase("n")) {
                register();
            } else {
                System.out.println("Invalid. Please enter 'y' or 'n'.");
            }

            if (currentUser != null) {
                managePerks();
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                currentUser = user;
                System.out.println("Logged in successfully.");
                return;
            }
        }
        System.out.println("Incorrect username or password.");
    }

    private static void register() {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        users.add(newUser);
        currentUser = newUser;
        System.out.println("Account created.");
    }

    private static void managePerks() {
        while (true) {
            System.out.println("\n1. Upload a perk\n2. View your perks\n3. Logout");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("-----Upload was selected-----\n");
                    uploadPerk();
                    break;
                case "2":
                    System.out.println("-----View was selected-----n\n");
                    System.out.println(perkManager.perksToString());
                    break;
                case "3":
                    logout();
                    return;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }
    private static void logout() {
        System.out.println("Logged out.");
        currentUser = null;
    }

    private static void uploadPerk()
    {
        System.out.print("Enter perk value (ex: 10% off): ");
        String perkValue = scanner.nextLine();

        System.out.println("*Please enter membership information*");
        Membership membership = generateMembership();

        System.out.println("*Please enter product information for which the perk applies*");
        Product product = generateProduct();

        System.out.print("Enter perk code: ");
        String codeInput = scanner.nextLine();

        int perkCode = 0;
        try {
            perkCode = Integer.parseInt(codeInput);
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid Code: error with string");
        }

        Perk newPerk = new Perk(perkValue, new ArrayList<>(), perkCode, product, membership);
        perkManager.addPerk(newPerk);

    }

    private static Product generateProduct()
    {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        return new Product(productName);
    }

    private static Membership generateMembership()
    {
        System.out.print("Enter membership name: ");
        String productName = scanner.nextLine();

        return new Membership(productName, new ArrayList<>());
    }

    private static void viewPerk()
    {

    }

}
