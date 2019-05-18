import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

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
                case 1:     runLoginMenu();
                            break;
                case 2:     runRegisterMenu();
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
        System.out.println("\nWelcome back! " +
                           "Are you a member or trainer?");
        System.out.println("---------");
        System.out.println("  1) Login as a member");
        System.out.println("  2) Login as a trainer");
        System.out.println("---------");
        System.out.println("  3) Return to main menu");
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
        System.out.println("\nWelcome! " +
                           "Are you registering as a member or trainer?");
        System.out.println("---------");
        System.out.println("  1) Register as a member");
        System.out.println("  2) Register as a trainer");
        System.out.println("---------");
        System.out.println("  3) View membership packages");
        System.out.println("---------");
        System.out.println("  4) Return to main menu");
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
                case 3:     runReviewPackagesMenu();
                            break;
                case 4:     runWelcomeMenu();
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
     * reviewPackagesMenu() - This method displays the menu to view
     * each membership package.
     *
     * @return     the user's menu choice
     */
    private int reviewPackagesMenu() {
        System.out.println("\nMember Packages - select option ro review details");
        System.out.println("---------");
        System.out.println("  1) Platinum package");
        System.out.println("  2) Gold package");
        System.out.println("  3) Duff package");
        System.out.println("  4) Student package");
        System.out.println("---------");
        System.out.println("  5) Return to main menu");
        System.out.println("---------");
        System.out.println("  0) Save & Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the reviewPackagesMenu() loop.
     */
    private void runReviewPackagesMenu()
    {
        int option = reviewPackagesMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     System.out.println("\nPlatinum package\n");
                            System.out.println(gymPackage.get("Platinum"));
                            runReturnFromPackagesMenu();
                            break;
                case 2:     System.out.println("\nGold package\n");
                            System.out.println(gymPackage.get("Gold"));
                            runReturnFromPackagesMenu();
                            break;
                case 3:     System.out.println("\nDuff package\n");
                            System.out.println(gymPackage.get("Duff"));
                            runReturnFromPackagesMenu();
                            break;
                case 4:     System.out.println("\nStudent Package\n");
                            System.out.println(gymPackage.get("WIT"));
                            runReturnFromPackagesMenu();
                            break;
                case 5:     runWelcomeMenu();
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = reviewPackagesMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye!");
        System.exit(0);
    }

    /**
     * returnFromPackagesMenu() - This method displays the menu to
     * return from the packages menu.
     *
     * * @return     the user's menu choice
     */
    private int returnFromPackagesMenu() {
        System.out.println("---------");
        System.out.println("  1) Return to packages menu");
        System.out.println("  2) Return to main menu");
        System.out.println("---------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the returnFromPackagesMenu() loop.
     */
    private void runReturnFromPackagesMenu()
    {
        int option = returnFromPackagesMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     runReviewPackagesMenu();
                            break;
                case 2:     runWelcomeMenu();
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = returnFromPackagesMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("\nExiting... bye!");
        System.exit(0);
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
                case 1:     System.out.println(member.profileString(member));
                            if (member.getAssessments().isEmpty()) {
                                System.out.println("\nNo assessments");
                            } else {
                                System.out.println("\nLatest assessment (" + member.sortedAssessmentDates().last() + "):");
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
                            System.out.println("\n" + member.getName() + " - current package: " + member.getChosenPackage());
                            runUpdatePackagesMenu(member);
                            System.out.println("\nPackage updated: " + member.getChosenPackage());
                            break;
                case 7:     runMemberMenu(member);
                            break;
                default:    System.out.println("\nInvalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            //input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = updateProfileMenu(member);
        }

        //the user chose option 0, so exit the program
        try{
            gymAPI.save();
        }
        catch(Exception e) {
            System.err.println("\nError saving to file: " + e);
        }
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
                            if (member.getAssessments().isEmpty()) {
                                System.out.println("It looks like your trainer hasn't recorded an assessment for you yet.");
                            } else {
                                for (String date : member.sortedAssessmentDates().descendingSet()) {
                                    System.out.println(date + ": " + member.getAssessments().get(date) + "\n");
                                }
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
    private int trainerMenu(Trainer trainer)
    {
        System.out.println("\nTrainer Menu - " + trainer.getName() + " - " + trainer.getSpecialty() + " specialist");
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
        int option = trainerMenu(trainer);
        while (option != 0)
        {

            switch (option)
            {
                case 1:     registerMember();
                            break;
                case 2:     System.out.println(gymAPI.listMembers());
                            System.out.println("\nWould you like to view one of these member's details? (Y/N)");
                            input.nextLine();   // dummy read
                            String yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                System.out.println("\nEnter member index to view member details:");
                                int index = input.nextInt();
                                Member member = gymAPI.getMembers().get(index);
                                runAssessmentMenu(member, trainer);
                            } else {
                                System.out.println("\nInvalid member index");
                                runTrainerMenu(trainer);
                            }
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
            option = trainerMenu(trainer);
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
        System.out.println(member.profileString(member));
        if (member.getAssessments().isEmpty()) {
            System.out.println("\nNo assessments");
        } else if (member.getAssessments().size() != 1) {
            System.out.print("\n" + member.getAssessments().size() + " assessments");
            } else {
            System.out.print("\n" + member.getAssessments().size() + " assessment");
        }
        if (!member.getAssessments().isEmpty()) {
            System.out.println("\nLatest (" + member.sortedAssessmentDates().last() + "):");
            System.out.println(member.latestAssessment());
        }
        System.out.println("\n---------");
        System.out.println("  1) Add new assessment");
        System.out.println("  2) Comment on assessment");
        System.out.println("  3) List all assessments");
        System.out.println("---------");
        System.out.println("  4) Return to trainer menu");
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
                            member.addAssessment(date, weight, thigh, waist, trainer);
                            System.out.println("\nAssessment added for " + member.getName());
                            input.nextLine();   // Scanner class bug
                            break;
                case 2:     input.nextLine();   // dummy read
                            commentOnAssessmentMenu(member, trainer);
                            /*System.out.println("\nPlease enter the assessment date (YY/MM/DD):");
                            date = input.nextLine();
                            Assessment assessment = (Assessment) member.getAssessments().get(date);
                            System.out.println("\nPlease enter your comment:");
                            String comment = input.nextLine();
                            assessment.addComment(comment);
                            System.out.println("\nComment \"" + assessment.getComment() + "\" added to assessment dated "
                                    + date);*/
                            break;
                case 3:     if (member.getAssessments().isEmpty()) {
                                System.out.println("\nIt looks like your trainer hasn't recorded an assessment for you yet.");
                            } else {
                                System.out.println("\n" + member.getName() + "'s " + ((member.getAssessments().size() == 1) ? "assessment:\n" : "assessments:\n"));
                            }
                            for (String assessmentDate : member.sortedAssessmentDates().descendingSet()) {
                                System.out.println(assessmentDate + ": " + member.getAssessments().get(assessmentDate) + "\n");
                            }
                            input.nextLine();   // dummy read
                            break;
                case 4:     runTrainerMenu(trainer);
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            //input.nextLine();   // Scanner class bug
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
     * commentOnAssessmentMenu() - This method displays the menu
     * for adding comments to an assessment, reads the menu option
     * that the use enters and executes it.
     */
    private void commentOnAssessmentMenu(Member member, Trainer trainer)
    {
        if (member.getAssessments().isEmpty()) {
            System.out.println("\nIt looks like your trainer hasn't recorded an assessment for you yet.");
        } else if (member.getAssessments().size() == 1) {
            Assessment assessment = member.latestAssessment();
            System.out.println(assessment);
            System.out.println("\nPlease enter your comment:");
            //input.nextLine();  // dummy read
            String comment = input.nextLine();
            assessment.addComment(comment, trainer);
            System.out.println("\nComment \"" + assessment.getComment() + "\" added to assessment");
            runTrainerMenu(trainer);
        } else {
            TreeSet<String> sortedTemp = new TreeSet<>();               // temporary clone of sortedAssessmentDates TreeSet
            HashMap<Integer, Assessment> commentMenu = new HashMap<>(); // HashMap to store menu choice ints as keys instead of dates
            sortedTemp.addAll(member.sortedAssessmentDates());          // dates cloned
            System.out.println("\nWhich of " + member.getName() + "'s assessments would you like to comment on?");
            System.out.println("\n---------");
            int i = 1;
            for (String date : member.sortedAssessmentDates().descendingSet()) {
                System.out.println("  " + i + ") " + date
                                    + " - Weight: " + member.getAssessments().get(date).getWeight() + " kg"
                                    + " - Waist: " + member.getAssessments().get(date).getWaist() + " cm"
                                    + " - Thigh: " + member.getAssessments().get(date).getThigh() + " cm"
                                    + " - Comment: " + member.getAssessments().get(date).getComment());
                commentMenu.put(i, member.getAssessments().get(sortedTemp.pollLast()));  // dates removed from Set after being returned
                i++;
            }
            System.out.println("---------");
            System.out.println("  " + i + ") Return to trainer menu");
            System.out.println("---------");
            System.out.println("  0) Exit");
            System.out.print("==>> ");

            int option = input.nextInt();

            while (option != 0) {
                if (option < (member.sortedAssessmentDates().size() + 1) && option > 0) {
                    Assessment assessment = commentMenu.get(option);
                    System.out.println("\nPlease enter your comment:");
                    input.nextLine();  // dummy read
                    String comment = input.nextLine();
                    assessment.addComment(comment, trainer);
                    System.out.println("\nComment \"" + assessment.getComment() + "\" added to assessment");
                    option = member.sortedAssessmentDates().size() + 1;
                } else if (option == member.sortedAssessmentDates().size() + 1) {
                    runTrainerMenu(trainer);
                } else if (option == 0) {
                    try {
                        gymAPI.save();
                    } catch (Exception e) {
                        System.err.println("\nError saving to file: " + e);
                    }
                    System.out.println("\nExiting... bye!");
                    System.exit(0);
                } else {
                    System.out.println("\nInvalid option entered: " + option);
                }
            }
        }
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
                System.out.println("\nEmail address already registered. " +
                        "Please sign in or register using a different email address.");
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

        String chosenPackage = runSetPackagesMenu();

        if (chosenPackage.equals("SE") || chosenPackage.equals("WIT")) {
            String collegeName = "";
            if (chosenPackage.equals("SE")) {
                collegeName = "Springfield Elementary";
            } else if (chosenPackage.equals("WIT")) {
                collegeName = "WIT";
            }

            System.out.print("\nStudent ID: ");
            String studentID = input.nextLine();

            gymAPI.addMember(new StudentMember(email, memberName, address, gender, height, startWeight, chosenPackage,
                    studentID, collegeName));
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
     * setPackagesMenu() - This method displays the menu to set the
     * membership package at the time of registration.
     *
     * @return     the user's menu choice
     */
    private int setPackagesMenu() {
        System.out.println("\nChoose package");
        System.out.println("---------");
        System.out.println("  1) Platinum package");
        System.out.println("  2) Gold package");
        System.out.println("  3) Duff package");
        System.out.println("  4) Student package");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the setPackagesMenu() loop.
     */
    private String runSetPackagesMenu()
    {
        int option = setPackagesMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     String chosenPackage = "Platinum";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            String yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                return chosenPackage;
                            } else {
                                runSetPackagesMenu();
                            }
                            break;
                case 2:     chosenPackage = "Gold";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                return chosenPackage;
                            } else {
                                runSetPackagesMenu();
                            }
                            break;
                case 3:     chosenPackage = "Duff";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                return chosenPackage;
                            } else {
                                runSetPackagesMenu();
                            }
                            break;
                case 4:     System.out.println("\nWhich university do you attend?");
                            boolean goodInput = false;
                            while (!goodInput)
                            {
                                input.nextLine();   //dummy read
                                String uni = input.nextLine();
                                if (uni.equals("WIT") || uni.equals("wit") || uni.equals("Waterford Institute of Technology")) {
                                    goodInput = true;
                                    chosenPackage = "WIT";
                                    return chosenPackage;
                                } else if (uni.equals("SE" ) || uni.equals("se") || uni.equals("Springfield Elementary")) {
                                    goodInput = true;
                                    chosenPackage = "SE";
                                    return chosenPackage;
                                } else {
                                    System.out.println("\nYour entry does not match any of our partner universities. " +
                                            "Please enter another university.");
                                }
                            }
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();   // Scanner class bug
            input.nextLine();

            //display the main menu again
            option = setPackagesMenu();
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

        return null;
    }

    /**
     * updatePackagesMenu() - This method displays the menu to update
     * membership package.
     *
     * @return     the user's menu choice
     */
    private int updatePackagesMenu() {
        System.out.println("\nChoose package");
        System.out.println("---------");
        System.out.println("  1) Platinum package");
        System.out.println("  2) Gold package");
        System.out.println("  3) Duff package");
        System.out.println("  4) Student package");
        System.out.print("==>> ");

        int option = input.nextInt();
        return option;
    }

    /**
     * This is the method that controls the updatePackagesMenu() loop.
     */
    private String runUpdatePackagesMenu(Member member)
    {
        int option = updatePackagesMenu();
        while (option != 0)
        {

            switch (option)
            {
                case 1:     String chosenPackage = "Platinum";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            String yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                member.chosenPackage(chosenPackage);
                                return chosenPackage;
                            } else {
                                runUpdatePackagesMenu(member);
                            }
                            break;
                case 2:     chosenPackage = "Gold";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                member.chosenPackage(chosenPackage);
                                return chosenPackage;
                            } else {
                                runUpdatePackagesMenu(member);
                            }
                            break;
                case 3:     chosenPackage = "Duff";
                            System.out.println("\n" + chosenPackage + " package:\n");
                            System.out.println(gymPackage.get(chosenPackage));
                            System.out.println("\nSelect this package? (Y/N)");
                            input.nextLine();   // dummy read
                            yesNo = input.nextLine();
                            if (yesNo.startsWith("Y") || yesNo.startsWith("y")) {
                                member.chosenPackage(chosenPackage);
                                return chosenPackage;
                            } else {
                                runUpdatePackagesMenu(member);
                            }
                            break;
                case 4:     System.out.println("\nWhich university do you attend?");
                            boolean goodInput = false;
                            while (!goodInput)
                            {
                                input.nextLine();   //dummy read
                                String uni = input.nextLine();
                                if (uni.equals("WIT") || uni.equals("wit") || uni.equals("Waterford Institute of Technology")) {
                                    goodInput = true;
                                    chosenPackage = "WIT";
                                    return chosenPackage;
                                } else if (uni.equals("SE" ) || uni.equals("se") || uni.equals("Springfield Elementary")) {
                                    goodInput = true;
                                    chosenPackage = "SE";
                                    return chosenPackage;
                                } else {
                                    System.out.println("\nYour entry does not match any of our partner universities. " +
                                            "Please enter another university.");
                                }
                            }
                            break;
                default:    System.out.println("Invalid option entered: " + option);
                            break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            //input.nextLine();   // Scanner class bug
            //input.nextLine();

            //display the main menu again
            option = updatePackagesMenu();
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

        return null;
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
            System.err.println("\nError saving to file: " + e);
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
