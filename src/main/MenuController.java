import java.util.HashMap;
import java.util.Scanner;

public class MenuController {

    private Scanner input;
    private GymAPI gymAPI;
    private HashMap gymPackage;

    public static void main(String[] args) {
        MenuController menu = new MenuController();
    }

    public MenuController(){
        input = new Scanner(System.in);
        gymAPI = new GymAPI();
        gymPackage = new HashMap();
        fillGymPackageMap();

        try{
            gymAPI.load();
        }
        catch(Exception e) {
            System.err.println("\nError loading from file: " + e);
        }

        System.out.println("\nWelcome to the Duff Light(™) Gym!");
        System.out.println("It's either us or Moe's Tavern...");
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
        System.out.println("\nMain Menu");
        System.out.println("---------");
        System.out.println("  1) Login");
        System.out.println("  2) Register");
        System.out.println("---------");
        System.out.println("  3) View membership packages");
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
                case 1:     System.out.println("\nLogging in...");
                            runLoginMenu();
                            break;
                case 2:     System.out.println("\nRegistering...");
                            runRegisterMenu();
                            break;
                case 3:     runPackagesMenu();
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
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
        System.out.println("\nExiting... bye!");
        System.exit(0);
    }


    /** loginMenu() - This method displays the menu to select the user type,
     * reads the menu option that the user enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int loginMenu()
    {
        System.out.println("\nWelcome back!" +
                           "Are you a member or trainer?");
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
                case 1:     input.nextLine();   // dummy read of String to clear the buffer - bug in Scanner class.
                            System.out.print("\nPlease enter your email address:  ");
                            String email = input.nextLine();
                            Member member = gymAPI.searchMembersByEmail(email);
                            if (member != null) {
                                System.out.println("Login successful!");
                                runMemberMenu(member);
                                break;
                            } else {
                                System.out.println("\nInvalid email address, please try again.");
                                runLoginMenu();
                                break;
                            }
                case 2:     input.nextLine();   // dummy read of String to clear the buffer - bug in Scanner class.
                            System.out.print("\nPlease enter your email address:  ");
                            email = input.nextLine();
                            Trainer trainer = gymAPI.searchTrainersByEmail(email);
                            if (trainer != null) {
                                System.out.println("Login successful!");
                                runTrainerMenu(trainer);
                                break;
                            } else {
                        System.out.println("\nInvalid email address, please try again.");
                        runLoginMenu();
                        break;
                    }
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
        System.out.println("\nWelcome!" +
                           "Are you registering as a member or trainer?");
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
                case 1:     Member member = registerMember();
                            runMemberMenu(member);
                            break;
                case 2:     Trainer trainer = registerTrainer();
                            runTrainerMenu(trainer);
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
     * packagesMenu() - This method displays the menu to view
     * each membership package.
     *
     * * @return     the user's menu choice
     */
    private int packagesMenu() {
        System.out.println("\nMember Packages - select option ro review details");
        System.out.println("---------");
        System.out.println("Premium packages");
        System.out.println("  1) Platinum package");
        System.out.println("  2) Gold package");
        System.out.println("  3) Duff package");
        System.out.println("Student packages");
        System.out.println("  4) Springfield Elementary Package");
        System.out.println("  5) WIT Package");
        System.out.println("---------");
        System.out.println("  6) Return to Login/Register menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the packagesMenu() loop.
     */
    private void runPackagesMenu()
    {
        int option = packagesMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("\nPlatinum package\n");
                            System.out.println(gymPackage.get("Package 1"));
                            returnFromPackagesMenu();
                            break;
                case 2:     System.out.println("\nGold package\n");
                            System.out.println(gymPackage.get("Package 2"));
                            returnFromPackagesMenu();
                            break;
                case 3:     System.out.println("\nDuff package\n");
                            System.out.println(gymPackage.get("Package 3"));
                            returnFromPackagesMenu();
                            break;
                case 4:     System.out.println("\nSpringfield Elementary package\n");
                            System.out.println(gymPackage.get("SE"));
                            returnFromPackagesMenu();
                            break;
                case 5:     System.out.println("\nWIT package\n");
                            System.out.println(gymPackage.get("WIT"));
                            returnFromPackagesMenu();
                            break;
                case 6:     runWelcomeMenu();
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = packagesMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye!");
        System.exit(0);
    }

    private void returnFromPackagesMenu() {
        System.out.println("---------");
        System.out.println("  1) Return to packages menu");
        System.out.println("  2) Return to Main menu");
        System.out.println("---------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        input.nextLine();   // dummy read
        String option2 = input.nextLine();
        if (option2.equals("1")) {
            runPackagesMenu();
        } else if(option2.equals("2")) {
            runWelcomeMenu();
        } else if (option2.equals("0")) {
            System.out.println("\nExiting... bye!");
            System.exit(0);
        } else {
            System.out.println("Invalid option entered: " + option2);
        }
    }

    /**
     * memberMenu() - This method displays the main member menu
     * for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int memberMenu(Member member)
    {
        System.out.println("\nMember Menu - " + member.getName());
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
    private void runMemberMenu(Member member)
    {
        int option = memberMenu(member);
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("\n" + member);
                            if (member.getAssessments().isEmpty()) {
                                System.out.println("\nNo assessments");
                            } else {
                                System.out.println("\nLatest assessment:");
                                System.out.println(member.latestAssessment());
                            }
                            break;
                case 2:     runUpdateProfileMenu(member);
                            break;
                case 3:     runProgressMenu(member);
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = memberMenu(member);
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye!");
        System.exit(0);
    }

    /**
     * updateProfileMenu() - This method displays options for which
     * instance fields the member would like to update.
     *
     * @return     the user's menu choice
     */
    private int updateProfileMenu(Member member)
    {
        System.out.println("\nUpdate Menu - " + member.getName());
        System.out.println("\nWhich field would you like to update?");
        System.out.println("---------");
        System.out.println("  1) Name");
        System.out.println("  2) Address");
        System.out.println("  3) Gender");
        System.out.println("  4) Height");
        System.out.println("  5) Starting Weight");
        System.out.println("  6) Gym Package");
        System.out.println("---------");
        System.out.println("  7) Return to member menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the updateProfileMenu() loop.
     */
    private void runUpdateProfileMenu(Member member)
    {
        int option = updateProfileMenu(member);
        while (option != 0)
        {

            switch (option)
            {
                case 1:     input.nextLine();   // dummy read
                            System.out.println("\nEnter new name: ");
                            String name = input.nextLine();
                            member.setName(name);
                            System.out.println("\nName updated: " + member.getName());
                            break;
                case 2:     input.nextLine();   // dummy read
                            System.out.println("\nEnter new address: ");
                            String address = input.nextLine();
                            member.setAddress(address);
                            System.out.println("\nAddress updated: " + member.getAddress());
                            break;
                case 3:     input.nextLine();   // dummy read
                            System.out.println("\nEnter gender: ");
                            String gender = input.nextLine();
                            member.setGender(gender);
                            System.out.println("\nGender updated: " + member.getGender());
                            break;
                case 4:     Float height;
                            boolean goodInput = false;
                            do {
                                try {
                                    input.nextLine();   // dummy read
                                    System.out.println("\nEnter height: ");
                                    height = input.nextFloat();
                                    member.setHeight(height);
                                    System.out.println("\nHeight updated: " + member.getHeight());
                                    goodInput = true;
                                } catch (Exception e) {
                                    input.nextLine();   // dummy read
                                    System.out.println("\nNumber expected - you entered text.");
                                    System.out.println("\nPress any key to try again...");
                                }
                            } while (!goodInput);
                            break;
                case 5:     Float startWeight;
                            goodInput = false;
                            do {
                                try {
                                    input.nextLine();   // dummy read
                                    System.out.println("\nEnter starting weight: ");
                                    startWeight = input.nextFloat();
                                    member.setStartWeight(startWeight);
                                    System.out.println("\nStarting weight updated: " + member.getStartWeight());
                                    goodInput = true;
                                } catch (Exception e) {
                                    input.nextLine();   // dummy read
                                    System.out.println("\nNumber expected - you entered text.");
                                    System.out.println("\nPress any key to try again...");
                                }
                            } while (!goodInput);
                            break;
                case 6:     input.nextLine();   // dummy read
                            System.out.println("\nEnter new package: ");
                            String gymPackage = input.nextLine();
                            member.setChosenPackage(gymPackage);
                            System.out.println("\nPackage updated: " + member.getChosenPackage());
                            break;
                case 7:     runMemberMenu(member);
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = updateProfileMenu(member);
        }

        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye!");
        System.exit(0);
    }

    /**
     * progressMenu() - This method displays the member progress
     * menu for the application, reads the menu option that the user
     * enters and returns it.
     *
     * @return     the user's menu choice
     */
    private int progressMenu(Member member)
    {
        System.out.println("\nProgress Menu - " + member.getName());

        if (member.latestAssessment() == null) {
            System.out.println("No assessments");
        } else {
            System.out.println("\nLatest assessment:");
            System.out.println(member.latestAssessment());
        }
        System.out.println("---------");
        System.out.println("  1) List all assessments");
        System.out.println("  2) View progress by weight");
        System.out.println("  3) View progress by waist measurement");
        System.out.println("---------");
        System.out.println("  4) Return to main menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the progressMenu() loop.
     */
    private void runProgressMenu(Member member)
    {
        int option = progressMenu(member);
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("");
                            for (String date : member.sortedAssessmentDates().descendingSet()) {
                                System.out.println(date + ": " + member.getAssessments().get(date));
                            }
                            break;
                case 2:     if (member.getAssessments().isEmpty()) {
                                System.out.println("\nIt looks like your trainer hasn't recorded an assessment for you yet.");
                            } else {
                                System.out.println(member.weightProgress());
                                System.out.println("\nWeight tracker");
                                System.out.println("---------");
                                for (String date : member.sortedAssessmentDates().descendingSet()) {
                                    System.out.println(date + ": " + member.getAssessments().get(date).getWeight() + " kg");
                                }
                                System.out.println("Starting weight: " + member.getStartWeight() + " kg");

                            }
                            break;
                case 3:     if (member.getAssessments().isEmpty()) {
                                System.out.println("\nIt looks like your trainer hasn't recorded an assessment for you yet.");
                            } else {
                                System.out.println(member.waistProgress());
                                System.out.println("\nWaist tracker");
                                System.out.println("---------");
                                for (String date : member.sortedAssessmentDates().descendingSet()) {
                                    System.out.println(date + ": " + member.getAssessments().get(date).getWaist() + " cm");
                                }
                            }
                            break;
                case 4:     runMemberMenu(member);
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = progressMenu(member);
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
    private void runTrainerMenu(Trainer trainer)
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
                case 3:     input.nextLine();   // dummy read
                            System.out.println("\nEmail to search:");
                            String memberEmail = input.nextLine();
                            Member member = gymAPI.searchMembersByEmail(memberEmail);
                            if (member != null) {
                                System.out.println("Member found!\n");
                                member.toString();
                                System.out.println("\nAssessments!");
                                runAssessmentMenu(member, trainer);
                            } else {
                                System.out.println("\nInvalid email address, please try again.");
                                runTrainerMenu(trainer);
                                break;
                            }
                            break;
                case 4:     input.nextLine();   // dummy read
                            System.out.println("\nEnter email address of member whose assessments you'd like to view:");
                            memberEmail = input.nextLine();
                            member = gymAPI.searchMembersByEmail(memberEmail);
                            if (member != null) {
                                System.out.println("Member found!\n");
                                runAssessmentMenu(member, trainer);
                            } else {
                                System.out.println("\nInvalid email address, please try again.");
                                runTrainerMenu(trainer);
                                break;
                            }
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
    private int assessmentMenu(Member member)
    {
        System.out.println("\nAssessment Menu");
        if (member.getAssessments().size() != 1) {
            System.out.println("Member: " + member.getName() + " - " + member.getAssessments().size() + " assessments");
        } else {
            System.out.println("Member: " + member.getName() + " - " + member.getAssessments().size() + " assessment");
        }
        System.out.println("---------");
        System.out.println("  1) Add new member assessment");
        System.out.println("  2) Comment on member assessment");
        System.out.println("  3) List all assessments");
        System.out.println("---------");
        System.out.println("  4) Return to Trainer menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the assessmentMenu() loop.
     */
    private void runAssessmentMenu(Member member, Trainer trainer)
    {
        int option = assessmentMenu(member);
        while (option != 0)
        {

            switch (option)
            {
                case 1:     input.nextLine();   // dummy read
                            System.out.println("\nPlease enter the assessment date (YY/MM/DD):");
                            String date = input.nextLine();
                            System.out.println("\nPlease enter weight measurement (kg):");
                            Float weight = input.nextFloat();
                            System.out.println("\nPlease enter thigh measurement (cm):");
                            Float thigh = input.nextFloat();
                            System.out.println("\nPlease enter waist measurement (cm):");
                            Float waist = input.nextFloat();
                            member.addAssessment(date, weight, thigh, waist);
                            System.out.println("\nAssessment added for " + member.getName());
                            break;
                case 2:     input.nextLine();   // dummy read
                            System.out.println("\nPlease enter the assessment date (YY/MM/DD):");
                            date = input.nextLine();
                            Assessment assessment = (Assessment) member.getAssessments().get(date);
                            System.out.println("\nPlease enter your comment:");
                            String comment = input.nextLine();
                            assessment.addComment(comment);
                            System.out.println("\nComment \"" + assessment.getComment() + "\" added to assessment dated " + date);
                            break;
                case 3:     System.out.println("\n" + member.getAssessments());
                            break;
                case 4:     runTrainerMenu(trainer);
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = assessmentMenu(member);
        }

        //the user chose option 0, so exit the program
        try{
            gymAPI.save();
        }
        catch(Exception e) {
            System.err.println("Error saving to file: " + e);
        }
        System.out.println("Exiting... bye!");
        System.exit(0);
    }


    /**
     * Gather the member data from the user and create a new member.
     */
    private Member registerMember(){
        input.nextLine();   // dummy read
        System.out.print("\nName (first & last):  ");
        String memberName = input.nextLine();

        System.out.print("\nEmail address: ");
        String email = input.nextLine();
        for (Member member : gymAPI.getMembers()) {
            if (member.getEmail().equals(email)) {
                System.out.println("\nEmail address already registered. Please sign in or register using a different email address.");
                runWelcomeMenu();
            }
        }

        System.out.print("\nAddress: ");
        String address = input.nextLine();

        System.out.println("\nGender (M/F/other): ");
        String gender = input.nextLine();

        float height = 0;
        boolean goodInput = false; 	//Loop  Control Variable
        do {
            try {
                System.out.print("\nHeight (m): ");
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
                System.out.print("\nStarting weight (kg): ");
                startWeight = input.nextFloat();
                goodInput = true;
            }
            catch (Exception e) {
                input.nextLine();  //swallows Scanner bug
                System.out.println("\nNumber expected - you entered text");
            }
        }  while (!goodInput);

        String chosenPackage = "";

        System.out.println("\nWhich package?: ");
        System.out.println("---------");
        System.out.println("  1) Platinum");
        System.out.println("  2) Gold");
        System.out.println("  3) Duff");
        System.out.println("  4) Springfield Elementary");
        System.out.println("  5) WIT");
        System.out.print("==>> ");
        int option = input.nextInt();

        switch (option)
        {
            case 1:     chosenPackage = "Platinum";
                        break;
            case 2:     chosenPackage = "Gold";
                        break;
            case 3:     chosenPackage = "Duff";
                        break;
            case 4:     chosenPackage = "SE";
                        break;
            case 5:     chosenPackage = "WIT";
                        break;
            default:    System.out.println("Invalid option entered: " + option);
                        break;
        }

        if (chosenPackage == "SE" || chosenPackage == "WIT") {
            input.nextLine();   // dummy read
            System.out.print("\nCollege name:  ");
            String collegeName = input.nextLine();

            System.out.print("\nStudent ID:  ");
            String studentID = input.nextLine();

            gymAPI.addMember(new StudentMember(email, memberName, address, gender, height, startWeight, chosenPackage, studentID, collegeName));
        } else {
            gymAPI.addMember(new PremiumMember(email, memberName, address, gender, height, startWeight, chosenPackage));
        }

        try{
            gymAPI.save();
        }
        catch(Exception e) {
            System.err.println("Error saving to file: " + e);
        }

        Member member = gymAPI.searchMembersByEmail(email);

        System.out.println("\nNew member - " + memberName + " - has been registered.");

        return member;
    }

    /**
     * Gather the trainer data from the user and create a new trainer.
     */
    private Trainer registerTrainer(){
        input.nextLine();   // dummy read of String to clear the buffer - bug in Scanner class.
        System.out.print("\nName:  ");
        String trainerName = input.nextLine();

        System.out.print("\nEmail address: ");
        String email = input.nextLine();

        System.out.print("\nAddress: ");
        String address = input.nextLine();

        System.out.println("\nGender (M/F/other): ");
        String gender = input.nextLine();

        System.out.println("\nSpecialty: ");
        String specialty = input.nextLine();

        gymAPI.addTrainer(new Trainer(email, trainerName, address, gender, specialty));

        try{
            gymAPI.save();
        }
        catch(Exception e) {
            System.err.println("Error saving to file: " + e);
        }

        Trainer trainer = gymAPI.searchTrainersByEmail(email);

        System.out.println("\nNew trainer - " + trainerName + " - has been registered.");

        return trainer;
    }

    /**
     * Fill gymPackage HashMap.
     */
    public void fillGymPackageMap()
    {
        gymPackage.put("Platinum",
                        "Allowed access anytime to gym.\nFree access to all classes.\n" +
                        "Access to all changing areas including deluxe changing rooms.");
        gymPackage.put("Gold",
                        "Allowed access anytime to gym.\n€3 fee for all classes.\n" +
                        "Access to all changing areas including deluxe changing rooms.");
        gymPackage.put("Duff",
                        "Allowed access to gym at off-peak times.\n" +
                        "\n€5 fee for all classes. \nNo access to deluxe changing rooms.");
        gymPackage.put("SE",
                        "Allowed access to gym during term time.\n" +
                        "\n€2 fee for all classes. \nNo access to deluxe changing rooms.");
        gymPackage.put("WIT",
                        "Allowed access to gym during term time.\n" +
                        "\n€4 fee for all classes. \nNo access to deluxe changing rooms.");
    }
}
