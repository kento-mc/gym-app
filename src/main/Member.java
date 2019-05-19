import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;
    private HashMap<String, Assessment> assessments;

    public Member() {
    }

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap<>();
    }

    /**
     * Instantiates new assessment and adds it to the assessments HashMap
     */
    public void addAssessment(String date, Float weight, Float thigh, Float waist, Trainer trainer) {
        Assessment assessment = new Assessment(weight, thigh, waist, trainer);
        assessments.put(date, assessment);
    }

    /**
     * Returns the latest assessment based on last entry (by calendar date).
     *
     * @return  latest assessment
     */
    public Assessment latestAssessment() {
        if (!assessments.isEmpty()) {
            Assessment assessment = assessments.get(sortedAssessmentDates().last());
            return assessment;
        } else {
            return null;
        }
    }

    /**
     * Returns a chronologically sorted set of assessment dates
     *
     * @return  sorted assessment dates
     */
    public TreeSet<String> sortedAssessmentDates() {
        TreeSet<String> sorted = new TreeSet<>();
        Iterator<String> it = assessments.keySet().iterator();
        while (it.hasNext()) {
            String date = it.next();
            sorted.add(date);
        }
        return sorted;
    }

    /**
     * Abstract method with concrete implementation in Member subclasses
     */
    public abstract void chosenPackage(String chosenPackage);

    /**
     * Builds a string to display the member's progress in weight measurements
     *
     * @return  weight progress String
     */
    public String weightProgress() {
        float currentWeight = (assessments.get(sortedAssessmentDates().last())).getWeight();
        String current = "You currently weigh " + currentWeight + " kg.";
        String progress = "";

        if (assessments.size() >= 2) {
            // lower method returns the assessment dated before the one specified
            float previousWeight = (assessments.get(sortedAssessmentDates().lower(sortedAssessmentDates().last())).getWeight());

            if (currentWeight < previousWeight) {
                progress = "\nYou're making progress!"
                         + "\nYou've lost " + (previousWeight - currentWeight) + " kg since your last assessment.\n"
                         + current;
            } else if (currentWeight > previousWeight) {
                progress = "\nYou're going to have to work harder."
                         + "\nYou've gained " + (currentWeight - previousWeight) + " kg since your last assessment.\n"
                         + current;
            } else if (currentWeight == previousWeight) {
                progress = "\nYou're stuck."
                         + "\nYou weigh exactly the same as when you were last assessed.\n"
                         + current;
            }
        } else if (assessments.size() == 1) {
            if (currentWeight < getStartWeight()) {
                progress = "\nYou're making progress!"
                         + "\nYou've lost " + (getStartWeight() - currentWeight) + " kg since you joined.\n"
                         + current;
            } else if (currentWeight > getStartWeight()) {
                progress = "\nYou're going to have to work harder."
                         + "\nYou've gained " + (currentWeight - getStartWeight()) + " kg since you joined.\n"
                         + current;
            } else if (currentWeight == getStartWeight()) {
                progress = "\nYou're stuck."
                         + "\nYou weigh exactly the same as when you joined.\n"
                         + current;
            }

        } else {
            progress = "\nIt looks like your trainer hasn't recorded an assessment for you yet."
                       + current;

        }
        return progress;
    }

    /**
     * Builds a string to display the member's progress in waist measurements
     *
     * @return  waist progress String
     */
    public String waistProgress() {
        float currentWaist = (assessments.get(sortedAssessmentDates().last())).getWaist();
        String current = "Your current waist measurement is " + currentWaist + " cm.";
        String progress = "";

        if (assessments.size() >= 2) {
            // lower method returns the assessment dated before the one specified
            float previousWaist = (assessments.get(sortedAssessmentDates().lower(sortedAssessmentDates().last())).getWaist());

            if (currentWaist < previousWaist) {
                progress = "\nYou're making progress!"
                         + "\nYour waist measurement has decreased by " + (previousWaist - currentWaist)
                         + " cm since your last assessment.\n"
                         + current;
            } else if (currentWaist > previousWaist) {
                progress = "\nYou're going to have to work harder."
                         + "\nYour waist measurement has increased by " + (currentWaist - previousWaist)
                         + " cm since your last assessment.\n"
                         + current;
            } else if (currentWaist == previousWaist) {
                progress = "\nYou're stuck."
                         + "\nYour waist measurement is exactly the same as when you were last assessed.\n"
                         + current;
            }
        } else if (assessments.size() == 1) {
            progress = "\nSorry, but you've only recorded one assessment so far. No progress to track yet!";
        } else {
            progress = "\nIt looks like your trainer hasn't recorded an assessment for you yet."
                      +"\nComplete your first assessment to start tracking your waist measurement.";
        }
        return progress;
    }

    /**
     * Builds a String representing a user friendly representation of member info, specicifcally for use
     * in the member profile.
     *
     * @return Details of the member
     */
    public String profileString(Member member)
    {
        if (member.assessments.isEmpty()) {
        return "\n" +
                "//-----------------------------PROFILE-----------------------------//\n" +
                "   " + getName().toUpperCase() + " - " + getEmail() + " - " + chosenPackage + " package\n" +
                "   -----------------------------------------------------------------   \n" +
                "   Gender: " + getGender() + "     Weight: " + getStartWeight() + " kg" + "     Height: " + getHeight() + " m\n" +
                "   Address: " + getAddress() +
                "\n//-----------------------------------------------------------------//";
        } else {
            double bmi = GymUtility.calculateBMI(member, latestAssessment());
            return "\n" +
                    "//-----------------------------PROFILE-----------------------------//\n" +
                    "   " + getName().toUpperCase() + " - " + getEmail() + " - " + chosenPackage + " package\n" +
                    "   -----------------------------------------------------------------   \n" +
                    "   Gender: " + getGender() + "     Weight: " + latestAssessment().getWeight() + " kg" + "     Height: " + getHeight() + " m\n" +
                    "   Address: " + getAddress() + "\n" +
                    "   -----------------------------------------------------------------   \n" +
                    "   BMI: " + bmi + " - " + GymUtility.determineBMICategory(bmi) + "\n" +
                    "   Weight is " + ((GymUtility.isIdealBodyWeight(member, latestAssessment())) ? "ideal!" : "not ideal...") +
                    "\n//-----------------------------------------------------------------//";
        }
    }

    /**
     * Builds a String representing a user friendly representation of member info
     *
     * @return Details of the member
     */
    public String toString()
    {
        return super.toString()
                + ", height: " + getHeight() + "m"
                + ", starting weight: " + getStartWeight() + "kg"
                + ", " + ((getChosenPackage().equals("Platinum") || getChosenPackage().equals("Gold")
                        || getChosenPackage().equals("Duff")?getChosenPackage() + " package" : "Student package, "));
    }

    //-----------------------getters & setters-----------------------//


    public float getHeight() {
        return height;
    }

    public float getStartWeight() {
        return startWeight;
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public HashMap<String, Assessment> getAssessments() {
        return assessments;
    }

    public void setHeight(float height) {
        if (height >= 1 && height <= 3) {
            this.height = (float) ((height *100) / 100.0);
        } else {
            this.height = 1;
        }
    }

    public void setStartWeight(float startWeight) {
        if (startWeight >= 35 && startWeight <= 250) {
            this.startWeight = startWeight;
        } else {
            this.startWeight = 35;
        }
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }
}