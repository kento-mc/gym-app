import java.util.Scanner;

public class MenuController {

    private Scanner input;
    private GymAPI gymAPI;

    public static void main(String[] args) {
        MenuController menu = new MenuController();
    }

    public MenuController(){
        input = new Scanner(System.in);
        gymAPI = new GymAPI();
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
                case 1:     registerMember();
                            runMemberMenu();
                            break;
                case 2:     registerTrainer();
                            runTrainerMenu();
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
                case 1:     registerMember();
                            break;
                case 2:     System.out.println(gymAPI.listMembers());
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

    /**
     * Gather the member data from the user and create a new member.
     */
    private void registerMember(){
        input.nextLine();   // dummy read of String to clear the buffer - bug in Scanner class.
        System.out.print("\nEnter the member's name:  ");
        String memberName = input.nextLine();

        System.out.print("\nEnter the member's email address: ");
        String email = input.nextLine();

        System.out.print("\nEnter the member's address: ");
        String address = input.nextLine();

        System.out.println("\nEnter the member's gender (M/F/other): ");
        String gender = input.nextLine();

        float height = 0;
        boolean goodInput = false; 	//Loop  Control Variable
        do {
            try {
                System.out.print("\nEnter the member's height (m):  ");
                height = input.nextFloat();
                goodInput = true;
            }
            catch (Exception e) {
                input.nextLine();  //swallows Scanner bug
                System.out.println("\nNumber expected - you entered text");
            }
        }  while (!goodInput);

        float startWeight = 0; 	//Loop  Control Variable
        do {
            try {
                System.out.print("\nEnter the member's starting weight (kg):  ");
                startWeight = input.nextFloat();
                goodInput = true;
            }
            catch (Exception e) {
                input.nextLine();  //swallows Scanner bug
                System.out.println("\nNumber expected - you entered text");
            }
        }  while (!goodInput);

        String chosenPackage = "";

        System.out.println("\nWhich package has the member chosen?: ");
        System.out.println("---------");
        System.out.println("  1) Package 1");
        System.out.println("  2) Package 2");
        System.out.println("  3) Package 3");
        System.out.println("  4) WIT Package");
        System.out.print("==>> ");
        int option = input.nextInt();

        switch (option)
        {
            case 1:     chosenPackage = "Package 1";
                        break;
            case 2:     chosenPackage = "Package 2";
                        break;
            case 3:     chosenPackage = "Package 3";
                        break;
            case 4:     chosenPackage = "WIT Package";
                        break;
            default:    System.out.println("Invalid option entered: " + option);
                        break;
        }

        gymAPI.addMember(new Member(email, memberName, address, gender, height, startWeight, chosenPackage));

        System.out.println("\nNew member - " + memberName + " - has been registered.");
    }

    /**
     * Gather the member data from the user and create a new trainer.
     */
    private void registerTrainer(){
        input.nextLine();   // dummy read of String to clear the buffer - bug in Scanner class.
        System.out.print("\nEnter the trainer's name:  ");
        String trainerName = input.nextLine();

        System.out.print("\nEnter the trainer's email address: ");
        String email = input.nextLine();

        System.out.print("\nEnter the trainer's address: ");
        String address = input.nextLine();

        System.out.println("\nEnter the trainer's gender (M/F/other): ");
        String gender = input.nextLine();

        System.out.println("\nEnter the trainer's specialty: ");
        String specialty = input.nextLine();

        gymAPI.addTrainer(new Trainer(email, trainerName, address, gender, specialty));

        System.out.println("\nNew trainer - " + trainerName + " - has been registered.");
    }
}
