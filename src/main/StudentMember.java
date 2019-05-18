public class StudentMember extends Member {

    private String studentID;
    private String collegeName;

    public StudentMember() {}

    public StudentMember(String email, String name, String address, String gender,
                         float height, float startWeight, String chosenPackage, String studentID, String collegeName)
    {
        super(email, name, address, gender, height, startWeight, chosenPackage);
        this.studentID = studentID;
        this.collegeName = collegeName;

    }


    public void chosenPackage(String packageChoice) {
        if (packageChoice.equals("SE") || packageChoice.equals("WIT")) {
            super.setChosenPackage(packageChoice);
        } else {
            super.setChosenPackage("Duff");
        }
    }

    public String toString()
    {
        return super.toString()
                + "College: " + getCollegeName() + ", "
                + "Student ID: " + getStudentID() + ", "
                + "Assessments: " + getAssessments().size();
    }


    public String getStudentID() {
        return studentID;
    }

    public String getCollegeName() {
        return collegeName;
    }
}
