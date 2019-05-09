import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;
    private HashMap assessments;


    public Member() {}

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
        assessments = new HashMap();
    }

    public void addAssessment(Float weight, Float thigh, Float waist) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd"); // HH:mm:ss
        String dateString = dateFormat.format(date);
        Assessment assessment = new Assessment(weight, thigh, waist);
        assessments.put(dateString, assessment);
    }

    /**
     * Builds a String representing a user friendly representation of member info
     * @return Details of the member
     */
    public String toString()
    {
        return "Member name: " + getName()
                + ", email: " + getEmail()
                + ", address: " + getAddress()
                + ", gender: " + getGender()
                + ", height: " + getHeight() + "m"
                + ", starting weight: " + getStartWeight() + "kg"
                + ", " + getChosenPackage();
    }

    //-----------------------getters & setters-----------------------//


    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height >= 1 && height <= 3) {
            this.height = height;
        } else {
            this.height = 1;
        }
    }

    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        if (startWeight >= 35 && startWeight <= 250) {
            this.startWeight = startWeight;
        } else {
            this.startWeight = 35;
        }
    }

    public String getChosenPackage() {
        return chosenPackage;
    }

    public HashMap getAssessments() {
        return assessments;
    }

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

}

