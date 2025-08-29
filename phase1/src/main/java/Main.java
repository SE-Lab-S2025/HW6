import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnergyManagementSystem system = new EnergyManagementSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Initial status message 
        System.out.println("System starting up...");
        System.out.println(system.getCurrentStatus());

        while (!exit) {
            System.out.println("\n--- Smart Energy Management System Menu ---");
            System.out.println("1. Change System State");
            System.out.println("2. Change Pricing Policy");
            System.out.println("3. View Current System Status");
            System.out.println("4. Simulate Energy Cost");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Change State [cite: 34]
                    System.out.println("Select new state: 1-Active, 2-Eco, 3-Shutdown");
                    int stateChoice = scanner.nextInt();
                    if (stateChoice == 1) system.setState(new ActiveState());
                    else if (stateChoice == 2) system.setState(new EcoState());
                    else if (stateChoice == 3) system.setState(new ShutdownState());
                    else System.out.println("Invalid choice.");
                    break;
                case 2: // Change Policy [cite: 33]
                    System.out.println("Select new policy: 1-Standard, 2-Peak, 3-Green");
                    int policyChoice = scanner.nextInt();
                    if (policyChoice == 1) system.setPricingStrategy(new StandardPricingStrategy());
                    else if (policyChoice == 2) system.setPricingStrategy(new PeakHoursPricingStrategy());
                    else if (policyChoice == 3) system.setPricingStrategy(new GreenModePricingStrategy());
                    else System.out.println("Invalid choice.");
                    System.out.println("Pricing policy updated.");
                    break;
                case 3: // View Status [cite: 36]
                    System.out.println("Current Status: " + system.getCurrentStatus());
                    break;
                case 4: // Simulate Cost [cite: 38]
                    System.out.print("Enter energy units to simulate: ");
                    int units = scanner.nextInt();
                    int cost = system.calculateEnergyCost(units);
                    System.out.println("Simulated cost for " + units + " units: " + cost + " Tomans.");
                    break;
                case 5: // Exit [cite: 43]
                    exit = true;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}