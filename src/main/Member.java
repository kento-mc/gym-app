import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;
    private HashMap<String, Assessment> assessments;

    protected final String PACK_PLATINUM = "Platinum";
    protected final String PACK_GOLD = "Gold";
    protected final String PACK_DUFF = "Duff";
    protected final String PACK_SE = "Springfield Elementary Student";
    protected final String PACK_WIT = "WIT Student";

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

    public void addAssessment(String date, Float weight, Float thigh, Float waist) {
        Assessment assessment = new Assessment(weight, thigh, waist);
        assessments.put(date, assessment);
    }

    public Assessment latestAssessment() {
        //Returns the latest assessment based on last entry (by calendar date).
        if (!assessments.isEmpty()) {
            Assessment assessment = assessments.get(sortedAssessmentDates().last());
            return assessment;
        } else {
            return null;
        }
    }

    public TreeSet<String> sortedAssessmentDates() {
        TreeSet<String> sorted = new TreeSet<>();
        Iterator<String> it = assessments.keySet().iterator();
        while (it.hasNext()) {
            String date = it.next();
            sorted.add(date);
        }
        return sorted;
    }

    public abstract void chosenPackage(String chosenPackage);

    public String weightProgress() {
        float currentWeight = (assessments.get(sortedAssessmentDates().last())).getWeight();
        String current = "You currently weigh " + currentWeight + " kg.";
        String progress = "";

        if (assessments.size() >= 2) {
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

    public String waistProgress() {
        float currentWaist = (assessments.get(sortedAssessmentDates().last())).getWaist();
        String current = "Your current waist measurement is " + currentWaist + " cm.";
        String progress = "";

        if (assessments.size() >= 2) {
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
     * Builds a String representing a user friendly representation of member info
     * @return Details of the member
     */
    public String toString()
    {
        return super.toString()
                + ", height: " + getHeight() + "m"
                + ", starting weight: " + getStartWeight() + "kg"
                + ", " + getChosenPackage();
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