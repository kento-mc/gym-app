import java.util.Scanner;

public class MenuController {

    private Scanner input;
    private GymAPI api;

    public static void main(String[] args) {
        MenuController menu = new MenuController();
    }

    public MenuController(){
        input = new Scanner(System.in);
        api = new GymAPI();
        // load XML
        runWelcomeMenu();
    }

    /**
     * welcomeMenu() - This method displays the welcome menu for the
     * application - asking whether the user wants to register or log in -
     * reads the menu option that the user enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int welcomeMenu()
    {
        System.out.println("\nWelcome to this totally sweet gym!");
        System.out.println("---------");
        System.out.println("  1) Login");
        System.out.println("  2) Register");
        System.out.println("---------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the welcomeMenu() loop.
     */
    private void runWelcomeMenu()
    {
        int option = welcomeMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("Logging in...");
                            runLoginMenu();
                            break;
                case 2:     System.out.println("Registering...");
                            runRegisterMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = welcomeMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }


    /** loginMenu() - This method displays the menu to select the user type,
     * reads the menu option that the user enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int loginMenu()
    {
        System.out.println("\nWelcome back!\n" +
                           "\nAre you a member or trainer?");
        System.out.println("---------");
        System.out.println("  1) Login as a member");
        System.out.println("  2) Login as a trainer");
        System.out.println("---------");
        System.out.println("  3) Return to Login/Register menu");
        System.out.println("---------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the loginMenu() loop.
     */
    private void runLoginMenu()
    {
        int option = loginMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     runMemberMenu();
                            break;
                case 2:     runTrainerMenu();
                            break;
                case 3:     runWelcomeMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = loginMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /** registerMenu() - This method displays the menu to select the user type,
     * reads the menu option that the user enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int registerMenu()
    {
        System.out.println("\nWelcome!\n" +
                           "\nAre you registering as a member or trainer?");
        System.out.println("---------");
        System.out.println("  1) Register as a member");
        System.out.println("  2) Register as a trainer");
        System.out.println("---------");
        System.out.println("  3) Return to Login/Register menu");
        System.out.println("---------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the registerMenu() loop.
     */
    private void runRegisterMenu()
    {
        int option = registerMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("I am a member!");
                            break;
                case 2:     System.out.println("I am a trainer!");
                            break;
                case 3:     runWelcomeMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = registerMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /**
     * memberMenu() - This method displays the main member menu
     * for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int memberMenu()
    {
        System.out.println("\nMember Menu");
        System.out.println("---------");
        System.out.println("  1) View profile");
        System.out.println("  2) Update profile");
        System.out.println("---------");
        System.out.println("  3) Progress Menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the memberMenu() loop.
     */
    private void runMemberMenu()
    {
        int option = memberMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("Nice profile!");
                            break;
                case 2:     System.out.println("Change that profile!");
                            break;
                case 3:     runProgressMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = memberMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /**
     * progressMenu() - This method displays the member progress
     * menu for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int progressMenu()
    {
        System.out.println("\nProgress Menu");
        System.out.println("---------");
        System.out.println("  1) View progress by weight");
        System.out.println("  2) View progress by waist measurement");
        System.out.println("---------");
        System.out.println("  3) Return to main menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the progressMenu() loop.
     */
    private void runProgressMenu()
    {
        int option = progressMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("You're fat!");
                            break;
                case 2:     System.out.println("You're really FAT!");
                            break;
                case 3:     runMemberMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = progressMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /**
     * trainerMenu() - This method displays the main trainer menu
     * for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int trainerMenu()
    {
        System.out.println("\nTrainer Menu");
        System.out.println("---------");
        System.out.println("  1) Add a new member");
        System.out.println("  2) List all members");
        System.out.println("  3) Search members by email");
        System.out.println("---------");
        System.out.println("  4) Assessment Menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the trainerMenu() loop.
     */
    private void runTrainerMenu()
    {
        int option = trainerMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("New member!");
                            break;
                case 2:     System.out.println("All the members!");
                            break;
                case 3:     System.out.println("Search the members!");
                            break;
                case 4:     runAssessmentMenu();
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = trainerMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /**
     * assessmentMenu() - This method displays the member assessment
     * menu for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int assessmentMenu()
    {
        System.out.println("\nAssessment Menu");
        System.out.println("---------");
        System.out.println("  1) Add new member assessment");
        System.out.println("  2) Update comment on member assessment");
        System.out.println("---------");
        System.out.println("  3) Return to main menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the assessmentMenu() loop.
     */
    private void runAssessmentMenu()
    {
        int option = assessmentMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("Assess!");
                    break;
                case 2:     System.out.println("Comment the assessment!");
                    break;
                case 3:     runTrainerMenu();
                    break;
                default:    System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = assessmentMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

}
