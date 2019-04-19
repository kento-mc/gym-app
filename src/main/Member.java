


public class Member extends Person {
    private float height;
    private float startWeight;
    private String chosenPackage;


    public Member() {}

    public Member(String email, String name, String address,
                  String gender, float height, float startWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startWeight);
        setChosenPackage(chosenPackage);
    }


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

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
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
}

