public class PremiumMember extends Member  {

    public PremiumMember() {}

    public PremiumMember(String email, String name, String address, String gender,
                         float height, float startWeight, String chosenPackage)
    {
        super(email, name, address, gender, height, startWeight, chosenPackage);
    }

    /**
     * Sets package choice String.
     */
    public void chosenPackage(String packageChoice) {
        super.setChosenPackage(packageChoice);
    }

    /**
     * Builds a String representing a user friendly representation of premium member info.
     *
     * @return Details of the premium member
     */
    public String toString() {
        return super.toString()
            + ", Assessments: " + getAssessments().size();
    }

}
